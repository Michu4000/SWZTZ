package pl.swztz.portal.windows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.shared.ui.datefield.DateTimeResolution;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.models.Blad;
import pl.swztz.portal.repositories.BladRepository;

public class OknoElementuBlad extends OknoElementu {
	
	private Blad obj;
	private TextField textField[];
	private DateTimeField date;
	private CheckBox checkbox;
	private ComboBox<String[]> user;
	
	public OknoElementuBlad(String title, BladRepository repo, boolean windowType) {
		super(title, repo); // konstruktor klasy bazowej
		
		// inicjalizacja elementów formularza
		textField = new TextField[2];
		textField[0] = new TextField("Temat");
		textField[1] = new TextField("Opis");
		
		date = new DateTimeField("Data zgłoszenia");
		date.setDateFormat("dd-MM-yyyy HH:mm:ss");
		date.setResolution(DateTimeResolution.SECOND);
		date.setTextFieldEnabled(false);
		date.setValue(LocalDateTime.now());
		
		checkbox = new CheckBox("Rozpatrzono");
		
		// inicjalizacja i pobranie danych do ComboBoxa (przepisanie jednej listy do drugiej)
		user = new ComboBox<>("Zgłaszający");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findUsers()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		user.setItems(stringArrayList);
		user.setItemCaptionGenerator(x -> x[1]); // ustawia wyswietlanie nazwy użykownika w ComboBoxie
		
		form.addComponents(textField[0], textField[1], date, checkbox, user); // dodanie elementów do formularza
		
		// w zależności od tego czy to okno dodawania czy edycji
		if(windowType)
			oknoDodawania();
		else
			oknoEdycji();
	}
	
	// tylko dla okna dodawania
	private void oknoDodawania() {
		okButton.setCaption("Dodaj");
		
		// listener przycisku dodaj
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !user.isEmpty()) {
					addNew();
					close();
				}
				else {
					// wyskakujące okienko z komunikatem o błędzie
					ConfirmDialog dialog = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						public void onClose(ConfirmDialog dialog) {}
					});
					dialog.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	private void addNew() {
		Long iduser = Long.parseLong(user.getSelectedItem().orElse(null)[0]);
		Blad newObj = new Blad(iduser, textField[0].getValue(), textField[1].getValue(), date.getValue(), checkbox.getValue());
		repo.save(newObj);
		clearForm();
	}
	
	private void clearForm() {
		for (TextField tf : textField)
			tf.clear();
		date.clear();
		checkbox.clear();
		user.clear();
	}
	
	// tylko dla okna edycji
	private void oknoEdycji() {
		okButton.setCaption("Wprowadź zmiany");
	}
	
	// dla okna edycji: ustaw id edytowanego wpisu
	@Override
	public void setElement(Long id) {
		obj = ((BladRepository)repo).findByIdBlad(id);
		loadToForm();
		
		// listener przycisku wprowadź zmiany
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !user.isEmpty()) {
					updateObj(); 
					close();
				}
				else {
					ConfirmDialog dialog = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						public void onClose(ConfirmDialog dialog) {}
					});
					dialog.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	// dla okna edycji: pobierz dane formularza
	private void loadToForm() {
		Object[] objectArray = ((BladRepository)repo).findUser(obj.getIdUzytkownik()).get(0);
		String[] objString = new String[] { ""+objectArray[0], (String)objectArray[1] };
		
		textField[0].setValue(obj.getTemat());
		textField[1].setValue(obj.getOpis());
		date.setValue(obj.getDataZgloszenia());
		checkbox.setValue(obj.isRozpatrzono());
		user.setSelectedItem(objString);
	}
	
	// dla okna edycji: aktualizuj wpis w bazie danych
	private void updateObj() {
		obj.setTemat(textField[0].getValue());
		obj.setOpis(textField[1].getValue());
		obj.setDataZgloszenia(date.getValue());
		obj.setRozpatrzono(checkbox.getValue());
		obj.setIdUzytkownik(Long.parseLong(user.getSelectedItem().orElse(null)[0]));
		repo.save(obj);
	}
}
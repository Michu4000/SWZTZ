package pl.swztz.portal.windows;

import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.models.Dyrektor;
import pl.swztz.portal.repositories.DyrektorRepository;

public class OknoElementuDyrektor extends OknoElementu {
	
	private Dyrektor obj;
	private TextField textField[];
	private ComboBox<String[]> instytut;
	
	public OknoElementuDyrektor(String title, DyrektorRepository repo, boolean windowType) {
		super(title, repo); // konstruktor klasy bazowej
		
		// inicjalizacja elementów formularza
		textField = new TextField[4];
		textField[0] = new TextField("PESEL");
		textField[1] = new TextField("Imię");
		textField[2] = new TextField("Nazwisko");
		textField[3] = new TextField("Pokój");
		
		// inicjalizacja i pobranie danych do ComboBoxa (przepisanie jednej listy do drugiej)
		instytut = new ComboBox<>("Instytut");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findInstytuty()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		instytut.setItems(stringArrayList);
		instytut.setItemCaptionGenerator(x -> x[1]); // ustawia wyświetlanie nazwy instytutu w ComboBoxie
		
		form.addComponents(instytut, textField[0], textField[1], textField[2], textField[3]); // dodanie elementów do formularza
		
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
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !textField[2].isEmpty() && !textField[3].isEmpty() && !instytut.isEmpty()) {
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
		Long idDyrektor = Long.parseLong(instytut.getSelectedItem().orElse(null)[0]);
		Dyrektor newObj = new Dyrektor(idDyrektor, Long.parseLong(textField[0].getValue()), textField[1].getValue(), textField[2].getValue(), textField[3].getValue());
		repo.save(newObj);
		clearForm();
	}
	
	private void clearForm() {
		for (TextField tf : textField)
			tf.clear();
		instytut.clear();
	}
	
	// tylko dla okna edycji
	private void oknoEdycji() {
		okButton.setCaption("Wprowadź zmiany");
	}
	
	// dla okna edycji: ustaw id edytowanego wpisu
	@Override
	public void setElement(Long id) {
		obj = ((DyrektorRepository)repo).findByIdDyrektor(id);
		loadToForm();
		
		// listener przycisku wprowadź zmiany
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !textField[2].isEmpty() && !textField[3].isEmpty() && !instytut.isEmpty()) {
					updateObiekt(); 
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
		Object[] objectArray = ((DyrektorRepository)repo).findInstytut(obj.getIdInstytut()).get(0);
		String[] objString = new String[] { ""+objectArray[0], (String)objectArray[1] };
		
		textField[0].setValue(""+obj.getPESEL());
		textField[1].setValue(obj.getImie());
		textField[2].setValue(obj.getNazwisko());
		textField[3].setValue(obj.getPokoj());
		instytut.setSelectedItem(objString);
	}
	
	// dla okna edycji: aktualizuj wpis w bazie danych
	private void updateObiekt() {
		obj.setPESEL(Long.parseLong(textField[0].getValue()));
		obj.setImie(textField[1].getValue());
		obj.setNazwisko(textField[2].getValue());
		obj.setPokoj(textField[3].getValue());
		obj.setIdInstytut(Long.parseLong(instytut.getSelectedItem().orElse(null)[0]));
		repo.save(obj);
	}
}
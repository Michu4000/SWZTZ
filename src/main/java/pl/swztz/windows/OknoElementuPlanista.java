package pl.swztz.windows;

import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.models.Planista;
import pl.swztz.portal.repositories.PlanistaRepository;

public class OknoElementuPlanista extends OknoElementu {

	private Planista obj;
	private TextField textField[];
	private ComboBox<String[]> wydzial;
		
	public OknoElementuPlanista(String title, PlanistaRepository repo, boolean windowType) {
		super(title, repo); // konstruktor klasy bazowej
			
		// inicjalizacja elementów formularza
		textField = new TextField[3];
		textField[0] = new TextField("PESEL");
		textField[1] = new TextField("Imię");
		textField[2] = new TextField("Nazwisko");
			
		// inicjalizacja i pobranie danych do ComboBoxa (przepisanie jednej listy do drugiej)
		wydzial = new ComboBox<>("Wydzial");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findWydzialy()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		wydzial.setItems(stringArrayList);
		wydzial.setItemCaptionGenerator(x -> x[1]); // ustawia wyświetlanie nazwy użytkownika w ComboBoxie
			
		form.addComponents(wydzial, textField[0], textField[1], textField[2]); // dodanie elementów do formularza
			
		// w zależnosci od tego czy to okno dodawania czy edycji
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
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !textField[2].isEmpty() && !wydzial.isEmpty()) {
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
		Long idPlanista = Long.parseLong(wydzial.getSelectedItem().orElse(null)[0]);
		Planista newObj = new Planista(idPlanista, Long.parseLong(textField[0].getValue()), textField[1].getValue(), textField[2].getValue());
		repo.save(newObj);
		clearForm();
	}
		
	private void clearForm() {
		for (TextField tf : textField)
			tf.clear();
		wydzial.clear();
	}
		
	// tylko dla okna edycji
	private void oknoEdycji() {
		okButton.setCaption("Wprowadź zmiany");
	}
		
	// dla okna edycji: ustaw id edytowanego wpisu
	@Override
	public void setElement(Long id) {
		obj = ((PlanistaRepository)repo).findByIdPlanista(id);
		loadToForm();
			
		// listener przycisku wprowadź zmiany
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !textField[2].isEmpty() && !wydzial.isEmpty()) {
					updateobj(); 
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
		Object[] objectArray = ((PlanistaRepository)repo).findWydzial(obj.getIdWydzial()).get(0);
		String[] objString = new String[] { ""+objectArray[0], (String)objectArray[1] };
			
		textField[0].setValue(""+obj.getPESEL());
		textField[1].setValue(obj.getImie());
		textField[2].setValue(obj.getNazwisko());
		wydzial.setSelectedItem(objString);
	}
		
	// dla okna edycji: aktualizuj wpis w bazie danych
	private void updateobj() {
		obj.setPESEL(Long.parseLong(textField[0].getValue()));
		obj.setImie(textField[1].getValue());
		obj.setNazwisko(textField[2].getValue());
		obj.setIdWydzial(Long.parseLong(wydzial.getSelectedItem().orElse(null)[0]));
		repo.save(obj);
	}
}
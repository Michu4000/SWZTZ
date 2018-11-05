package pl.swztz.windows;

import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.models.Urlop;
import pl.swztz.portal.repositories.UrlopRepository;
import pl.swztz.windows.OknoElementu;

public class OknoElementuUrlop extends OknoElementu {

	private Urlop obj;
	private DateField startDate, endDate;
	private ComboBox<String[]> prowadzacy;
	private TextField textField;
		
	public OknoElementuUrlop(String title, UrlopRepository repo, boolean windowType) {
		super(title, repo); // konstruktor klasy bazowej

		// inicjalizacja elementów formularza
		textField = new TextField("Powód");
			
		startDate = new DateField("Data rozpoczecia");
		startDate.setDateFormat("dd-MM-yyyy");
		startDate.setTextFieldEnabled(false);
		
		endDate = new DateField("Data zakończenia");
		endDate.setDateFormat("dd-MM-yyyy");
		endDate.setTextFieldEnabled(false);
		
		// inicjalizacja i pobranie danych do ComboBoxa (przepisanie jednej listy do drugiej)
		prowadzacy = new ComboBox<>("Prowadzacy");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findProwadzacych()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		prowadzacy.setItems(stringArrayList);
		prowadzacy.setItemCaptionGenerator(x -> x[1]); // ustawia wyświetlanie nazwy użytkownika w ComboBoxie

		form.addComponents(prowadzacy, startDate, endDate, textField); // dodanie elementów do formularza 
		
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
				if(!textField.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty() &&  !prowadzacy.isEmpty()) {
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
		Long idProwadzacy = Long.parseLong(prowadzacy.getSelectedItem().orElse(null)[0]);
		Urlop newObj = new Urlop(idProwadzacy, startDate.getValue(), endDate.getValue(), textField.getValue());
		repo.save(newObj);
		clearForm();
	}
	
	private void clearForm() {
		textField.clear();
		prowadzacy.clear();
		startDate.clear();
		endDate.clear();
	}
	
	// tylko dla okna edycji
	private void oknoEdycji() {
		okButton.setCaption("Wprowadź zmiany");
	}
	
	// dla okna edycji: ustaw id edytowanego wpisu
	@Override
	public void setElement(Long id) {
		obj = ((UrlopRepository)repo).findByIdUrlop(id);
		loadToForm();
		
		// listener przycisku wprowadź zmiany
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!prowadzacy.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty() && !textField.isEmpty()) {
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
		Object[] objectArray = ((UrlopRepository)repo).findProwadzacy(obj.getIdProwadzacy()).get(0);
		String[] objString = new String[] { ""+objectArray[0], (String)objectArray[1] };
		
		startDate.setValue(obj.getDataRozpoczecia());
		endDate.setValue(obj.getDataZakonczenia());
		textField.setValue(obj.getPowod());
		prowadzacy.setSelectedItem(objString);
	}
	
	// dla okna edycji: aktualizuj wpis w bazie danych
	private void updateobj() {
		obj.setDataRozpoczecia(startDate.getValue());
		obj.setDataZakonczenia(endDate.getValue());
		obj.setIdProwadzacy(Long.parseLong(prowadzacy.getSelectedItem().orElse(null)[0]));
		obj.setPowod(textField.getValue());
		repo.save(obj);
	}
}
package pl.swztz.portal;

import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.OknoElementu;
import pl.swztz.portal.Models.Urlop;
import pl.swztz.portal.Repositories.UrlopRepository;

public class OknoElementuUrlop extends OknoElementu {

	private Urlop obiekt;
	private DateField dataRozpoczecia, dataZakonczenia;
	private ComboBox<String[]> prowadzacy;
	private TextField powod;
		
	public OknoElementuUrlop(String nazwa, UrlopRepository repo, boolean typOkna) {
		super(nazwa, repo); //konstruktor klasy bazowej

		// inicjalizacja elementow formularza
		powod = new TextField("Powód");
			
		dataRozpoczecia = new DateField("Data rozpoczecia");
		dataRozpoczecia.setDateFormat("dd-MM-yyyy");
		dataRozpoczecia.setTextFieldEnabled(false);
		
		dataZakonczenia = new DateField("Data zakończenia");
		dataZakonczenia.setDateFormat("dd-MM-yyyy");
		dataZakonczenia.setTextFieldEnabled(false);
		
		// inicjalizacja i pobranie danych do ComboBoxa (musialem przepisac jedna liste do drugiej)
		prowadzacy = new ComboBox<>("Prowadzacy");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findProwadzacych()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		prowadzacy.setItems(stringArrayList);
		prowadzacy.setItemCaptionGenerator(x -> x[1]); // ustawia wyswietlanie nazwy uzytkownika w ComboBoxie

		form.addComponents(prowadzacy, dataRozpoczecia, dataZakonczenia, powod); // dodanie elementow do formularza 
		
		// w zaleznosci od tego czy to okno dodawania czy edycji
		if(typOkna)
			oknoDodawania();
		else
			oknoEdycji();
	}
	
	// tylko dla okna dodawania
	private void oknoDodawania() {
		ok.setCaption("Dodaj");
		
		// listener przycisku dodaj
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!powod.isEmpty() && !dataRozpoczecia.isEmpty() && !dataZakonczenia.isEmpty() &&  !prowadzacy.isEmpty()) {
					addNew();
					close();
				}
				else {
					// wyskakujace okienko z komunikatem o bledzie
					ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						public void onClose(ConfirmDialog dialog) {}
					});
					d.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	private void addNew() {
		Long idProwadzacy = Long.parseLong(prowadzacy.getSelectedItem().orElse(null)[0]);
		Urlop u = new Urlop(idProwadzacy, dataRozpoczecia.getValue(), dataZakonczenia.getValue(), powod.getValue());
		repo.save(u);
		clearForm();
	}
	
	private void clearForm() {
		powod.clear();
		prowadzacy.clear();
		dataRozpoczecia.clear();
		dataZakonczenia.clear();
	}
	
	// tylko dla okna edycji
	private void oknoEdycji() {
		ok.setCaption("Wprowadź zmiany");
	}
	
	// dla okna edycji: ustaw id edytowanego wpisu
	@Override
	public void setElement(Long id) {
		obiekt = ((UrlopRepository)repo).findByIdUrlop(id);
		loadToForm();
		
		// listener przycisku wprowadz zmiany
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!prowadzacy.isEmpty() && !dataRozpoczecia.isEmpty() && !dataZakonczenia.isEmpty() && !powod.isEmpty()) {
					updateObiekt(); 
					close();
				}
				else {
					ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						public void onClose(ConfirmDialog dialog) {}
					});
					d.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	// dla okna edycji: pobierz dane formularza
	private void loadToForm() {
		Object[] objectArray = ((UrlopRepository)repo).findProwadzacy(obiekt.getIdProwadzacy()).get(0);
		String[] sobiekt = new String[] { ""+objectArray[0], (String)objectArray[1] };
		
		dataRozpoczecia.setValue(obiekt.getDataRozpoczecia());
		dataZakonczenia.setValue(obiekt.getDataZakonczenia());
		powod.setValue(obiekt.getPowod());
		prowadzacy.setSelectedItem(sobiekt);
	}
	
	// dla okna edycji: aktualizuj wpis w bazie danych
	private void updateObiekt() {
		obiekt.setDataRozpoczecia(dataRozpoczecia.getValue());
		obiekt.setDataZakonczenia(dataZakonczenia.getValue());
		obiekt.setIdProwadzacy(Long.parseLong(prowadzacy.getSelectedItem().orElse(null)[0]));
		obiekt.setPowod(powod.getValue());
		repo.save(obiekt);
	}
}
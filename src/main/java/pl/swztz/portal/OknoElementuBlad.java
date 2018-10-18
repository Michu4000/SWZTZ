package pl.swztz.portal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.shared.ui.datefield.DateTimeResolution;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.Models.Blad;
import pl.swztz.portal.Repositories.BladRepository;

public class OknoElementuBlad extends OknoElementu {
	
	private Blad obiekt;
	private TextField tf[];
	private DateTimeField date;
	private CheckBox checkbox;
	private ComboBox<String[]> uzytkownik;
	
	public OknoElementuBlad(String nazwa, BladRepository repo, boolean typOkna) {
		super(nazwa, repo); // konstruktor klasy bazowej
		
		// inicjalizacja elementow formularza
		tf = new TextField[2];
		tf[0] = new TextField("Temat");
		tf[1] = new TextField("Opis");
		
		date = new DateTimeField("Data zgłoszenia");
		date.setDateFormat("dd-MM-yyyy HH:mm:ss");
		date.setResolution(DateTimeResolution.SECOND);
		date.setTextFieldEnabled(false);
		date.setValue(LocalDateTime.now());
		
		checkbox = new CheckBox("Rozpatrzono");
		
		// inicjalizacja i pobranie danych do ComboBoxa (musialem przepisac jedna liste do drugiej)
		uzytkownik = new ComboBox<>("Zgłaszający");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findUsers()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		uzytkownik.setItems(stringArrayList);
		uzytkownik.setItemCaptionGenerator(x -> x[1]); // ustawia wyswietlanie nazwy uzytkownika w ComboBoxie
		
		form.addComponents(tf[0], tf[1], date, checkbox, uzytkownik); // dodanie elementow do formularza
		
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
				if(!tf[0].isEmpty() && !tf[1].isEmpty() && !uzytkownik.isEmpty()) {
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
		Long idUzytkownik = Long.parseLong(uzytkownik.getSelectedItem().orElse(null)[0]);
		Blad b = new Blad(idUzytkownik, tf[0].getValue(), tf[1].getValue(), date.getValue(), checkbox.getValue());
		repo.save(b);
		clearForm();
	}
	
	private void clearForm() {
		for (TextField t : tf)
			t.clear();
		date.clear();
		checkbox.clear();
		uzytkownik.clear();
	}
	
	// tylko dla okna edycji
	private void oknoEdycji() {
		ok.setCaption("Wprowadź zmiany");
	}
	
	// dla okna edycji: ustaw id edytowanego wpisu
	@Override
	public void setElement(Long id) {
		obiekt = ((BladRepository)repo).findByIdBlad(id);
		loadToForm();
		
		// listener przycisku wprowadz zmiany
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!tf[0].isEmpty() && !tf[1].isEmpty() && !uzytkownik.isEmpty()) {
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
		Object[] objectArray = ((BladRepository)repo).findUser(obiekt.getIdUzytkownik()).get(0);
		String[] sobiekt = new String[] { ""+objectArray[0], (String)objectArray[1] };
		
		tf[0].setValue(obiekt.getTemat());
		tf[1].setValue(obiekt.getOpis());
		date.setValue(obiekt.getDataZgloszenia());
		checkbox.setValue(obiekt.isRozpatrzono());
		uzytkownik.setSelectedItem(sobiekt);
	}
	
	// dla okna edycji: aktualizuj wpis w bazie danych
	private void updateObiekt() {
		obiekt.setTemat(tf[0].getValue());
		obiekt.setOpis(tf[1].getValue());
		obiekt.setDataZgloszenia(date.getValue());
		obiekt.setRozpatrzono(checkbox.getValue());
		obiekt.setIdUzytkownik(Long.parseLong(uzytkownik.getSelectedItem().orElse(null)[0]));
		repo.save(obiekt);
	}
}
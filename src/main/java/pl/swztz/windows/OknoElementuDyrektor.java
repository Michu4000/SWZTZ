package pl.swztz.windows;

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
	
	private Dyrektor obiekt;
	private TextField tf[];
	private ComboBox<String[]> instytut;
	
	public OknoElementuDyrektor(String nazwa, DyrektorRepository repo, boolean typOkna) {
		super(nazwa, repo); // konstruktor klasy bazowej
		
		// inicjalizacja elementow formularza
		tf = new TextField[4];
		tf[0] = new TextField("PESEL");
		tf[1] = new TextField("Imię");
		tf[2] = new TextField("Nazwisko");
		tf[3] = new TextField("Pokój");
		
		// inicjalizacja i pobranie danych do ComboBoxa (musialem przepisac jedna liste do drugiej)
		instytut = new ComboBox<>("Instytut");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findInstytuty()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		instytut.setItems(stringArrayList);
		instytut.setItemCaptionGenerator(x -> x[1]); // ustawia wyswietlanie nazwy instytutu w ComboBoxie
		
		form.addComponents(instytut, tf[0], tf[1], tf[2], tf[3]); // dodanie elementow do formularza
		
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
				if(!tf[0].isEmpty() && !tf[1].isEmpty() && !tf[2].isEmpty() && !tf[3].isEmpty() && !instytut.isEmpty()) {
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
		Long idDyrektor = Long.parseLong(instytut.getSelectedItem().orElse(null)[0]);
		Dyrektor d = new Dyrektor(idDyrektor, Long.parseLong(tf[0].getValue()), tf[1].getValue(), tf[2].getValue(), tf[3].getValue());
		repo.save(d);
		clearForm();
	}
	
	private void clearForm() {
		for (TextField t : tf)
			t.clear();
		instytut.clear();
	}
	
	// tylko dla okna edycji
	private void oknoEdycji() {
		ok.setCaption("Wprowadź zmiany");
	}
	
	// dla okna edycji: ustaw id edytowanego wpisu
	@Override
	public void setElement(Long id) {
		obiekt = ((DyrektorRepository)repo).findByIdDyrektor(id);
		loadToForm();
		
		// listener przycisku wprowadz zmiany
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!tf[0].isEmpty() && !tf[1].isEmpty() && !tf[2].isEmpty() && !tf[3].isEmpty() && !instytut.isEmpty()) {
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
		Object[] objectArray = ((DyrektorRepository)repo).findInstytut(obiekt.getIdInstytut()).get(0);
		String[] sobiekt = new String[] { ""+objectArray[0], (String)objectArray[1] };
		
		tf[0].setValue(""+obiekt.getPESEL());
		tf[1].setValue(obiekt.getImie());
		tf[2].setValue(obiekt.getNazwisko());
		tf[3].setValue(obiekt.getPokoj());
		instytut.setSelectedItem(sobiekt);
	}
	
	// dla okna edycji: aktualizuj wpis w bazie danych
	private void updateObiekt() {
		obiekt.setPESEL(Long.parseLong(tf[0].getValue()));
		obiekt.setImie(tf[1].getValue());
		obiekt.setNazwisko(tf[2].getValue());
		obiekt.setPokoj(tf[3].getValue());
		obiekt.setIdInstytut(Long.parseLong(instytut.getSelectedItem().orElse(null)[0]));
		repo.save(obiekt);
	}
}
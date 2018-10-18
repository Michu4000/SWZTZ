package pl.swztz.portal;

import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.OknoElementu;
import pl.swztz.portal.Models.Instytut;
import pl.swztz.portal.Repositories.InstytutRepository;

public class OknoElementuInstytut extends OknoElementu {

	private Instytut obiekt;	
	private ComboBox<String[]> wydzial;
	private TextField nazwaInstytut;
	
	public OknoElementuInstytut(String nazwa, InstytutRepository repo, boolean typOkna) {
		super(nazwa, repo); //konstruktor klasy bazowej

		// inicjalizacja elementow formularza
		nazwaInstytut= new TextField("Nazwa Instytutu");
		
		// inicjalizacja i pobranie danych do ComboBoxa (musialem przepisac jedna liste do drugiej)
		wydzial = new ComboBox<>("Wydział");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findWydzialy()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		wydzial.setItems(stringArrayList);
		wydzial.setItemCaptionGenerator(x -> x[1]); // ustawia wyswietlanie nazwy uzytkownika w ComboBoxie

		form.addComponents(nazwaInstytut, wydzial); // dodanie elementow do formularza 
		
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
				if(!nazwaInstytut.isEmpty() && !wydzial.isEmpty()) {
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
		Long idWydzial = Long.parseLong(wydzial.getSelectedItem().orElse(null)[0]);
		Instytut i = new Instytut(nazwaInstytut.getValue(), idWydzial);
		repo.save(i);
		clearForm();
	}
	
	private void clearForm() {
		nazwaInstytut.clear();
		wydzial.clear();
	}
	
	// tylko dla okna edycji
	private void oknoEdycji() {
		ok.setCaption("Wprowadź zmiany");
	}
	
	// dla okna edycji: ustaw id edytowanego wpisu
	@Override
	public void setElement(Long id) {
		obiekt = ((InstytutRepository)repo).findByIdInstytut(id);
		loadToForm();
		
		// listener przycisku wprowadz zmiany
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!wydzial.isEmpty() &&   !nazwaInstytut.isEmpty()) {
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
		Object[] objectArray = ((InstytutRepository)repo).findWydzial(obiekt.getIdWydzial()).get(0);
		String[] sobiekt = new String[] { ""+objectArray[0], (String)objectArray[1] };
		
		nazwaInstytut.setValue(obiekt.getNazwaInstytut());
		wydzial.setSelectedItem(sobiekt);
	}
	
	// dla okna edycji: aktualizuj wpis w bazie danych
	private void updateObiekt() {
		obiekt.setNazwaInstytut(nazwaInstytut.getValue());
		obiekt.setIdWydzial(Long.parseLong(wydzial.getSelectedItem().orElse(null)[0]));
		
		repo.save(obiekt);
	}
}
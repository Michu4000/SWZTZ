package pl.swztz.portal.windows;

import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.models.Instytut;
import pl.swztz.portal.repositories.InstytutRepository;
import pl.swztz.portal.windows.OknoElementu;

public class OknoElementuInstytut extends OknoElementu {

	private Instytut obj;	
	private ComboBox<String[]> wydzial;
	private TextField nazwaInstytut;
	
	public OknoElementuInstytut(String title, InstytutRepository repo, boolean windowType) {
		super(title, repo); // konstruktor klasy bazowej

		// inicjalizacja elementów formularza
		nazwaInstytut= new TextField("Nazwa Instytutu");
		
		// inicjalizacja i pobranie danych do ComboBoxa (przepisanie jednej listy do drugiej)
		wydzial = new ComboBox<>("Wydział");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findWydzialy()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		wydzial.setItems(stringArrayList);
		wydzial.setItemCaptionGenerator(x -> x[1]); // ustawia wyświetlanie nazwy użytkownika w ComboBoxie

		form.addComponents(nazwaInstytut, wydzial); // dodanie elementów do formularza 
		
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
				if(!nazwaInstytut.isEmpty() && !wydzial.isEmpty()) {
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
		Long idWydzial = Long.parseLong(wydzial.getSelectedItem().orElse(null)[0]);
		Instytut newObj = new Instytut(nazwaInstytut.getValue(), idWydzial);
		repo.save(newObj);
		clearForm();
	}
	
	private void clearForm() {
		nazwaInstytut.clear();
		wydzial.clear();
	}
	
	// tylko dla okna edycji
	private void oknoEdycji() {
		okButton.setCaption("Wprowadź zmiany");
	}
	
	// dla okna edycji: ustaw id edytowanego wpisu
	@Override
	public void setElement(Long id) {
		obj = ((InstytutRepository)repo).findByIdInstytut(id);
		loadToForm();
		
		// listener przycisku wprowadź zmiany
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!wydzial.isEmpty() &&   !nazwaInstytut.isEmpty()) {
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
		Object[] objectArray = ((InstytutRepository)repo).findWydzial(obj.getIdWydzial()).get(0);
		String[] objString = new String[] { ""+objectArray[0], (String)objectArray[1] };
		
		nazwaInstytut.setValue(obj.getNazwaInstytut());
		wydzial.setSelectedItem(objString);
	}
	
	// dla okna edycji: aktualizuj wpis w bazie danych
	private void updateObiekt() {
		obj.setNazwaInstytut(nazwaInstytut.getValue());
		obj.setIdWydzial(Long.parseLong(wydzial.getSelectedItem().orElse(null)[0]));
		
		repo.save(obj);
	}
}
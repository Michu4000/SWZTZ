package pl.swztz.portal;

import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.Models.Sala;
import pl.swztz.portal.Repositories.SalaRepository;

public class OknoElementuSala extends OknoElementu {

	private Sala obiekt;
	private TextField tf[];
		
	public OknoElementuSala(String nazwa, SalaRepository repo, boolean typOkna) {
		super(nazwa, repo);
		// inicjalizacja elementow formularza
		tf = new TextField[4];
		tf[0] = new TextField("Numer Sali");
		tf[1] = new TextField("Budynek");
		tf[2] = new TextField("Typ Sali");
		tf[3] = new TextField("Liczba miejsc");
		
		form.addComponents(tf[0], tf[1], tf[2], tf[3]); // dodanie elementow do formularza
		
		 //w zaleznosci od tego czy to okno dodawania czy edycji
		if(typOkna)
			oknoDodawania();
		else
			oknoEdycji();
	}
	
	private void oknoDodawania() {
		ok.setCaption("Dodaj");
		
		// listener przycisku dodaj
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!tf[0].isEmpty() && !tf[1].isEmpty() && !tf[2].isEmpty() && !tf[3].isEmpty()) {
					addNew();
					close();
				}
				else {
					// wyskakujace okienko z komunikatem o bledzie
					ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(), "Sala", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						public void onClose(ConfirmDialog dialog) {}
					});
					d.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	private void addNew() {
		Sala s = new Sala(tf[0].getValue(), tf[1].getValue(), tf[2].getValue(), Integer.parseInt(tf[3].getValue()));
		repo.save(s);
		clearForm();
	}
	
	private void clearForm() {
		for (TextField t : tf)
			t.clear();
	}
	
	// tylko dla okna edycji
	private void oknoEdycji() {
		ok.setCaption("Wprowadź zmiany");
	}
	
	@Override
	public void setElement(Long id) {
		obiekt = ((SalaRepository)repo).findByIdSala(id);
		loadToForm();
		
		// listener przycisku wprowadz zmiany
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!tf[0].isEmpty() && !tf[1].isEmpty() && !tf[2].isEmpty() && !tf[3].isEmpty()) {
					updateObiekt(); 
					close();
				}
				else {
					ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(), "Sala", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						public void onClose(ConfirmDialog dialog) {}
					});
					d.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	// dla okna edycji: pobierz dane formularza
	private void loadToForm() {
		tf[0].setValue(obiekt.getNrSala());
		tf[1].setValue(obiekt.getBudynek());
		tf[2].setValue(obiekt.getTypSala());
		tf[3].setValue(Integer.toString(obiekt.getIloscMiejsc()));
	}
		
	// dla okna edycji: aktualizuj wpis w bazie danych
	private void updateObiekt() {
		obiekt.setNrSala(tf[0].getValue());
		obiekt.setBudynek(tf[1].getValue());
		obiekt.setTypSala(tf[2].getValue());
		obiekt.setIloscMiejsc(Integer.parseInt(tf[3].getValue()));
		repo.save(obiekt);
	}
}
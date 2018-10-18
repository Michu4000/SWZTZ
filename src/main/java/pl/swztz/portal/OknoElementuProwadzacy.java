package pl.swztz.portal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.Models.Blad;
import pl.swztz.portal.Models.Prowadzacy;
import pl.swztz.portal.Repositories.ProwadzacyRepository;

public class OknoElementuProwadzacy extends OknoElementu {
	
	private Prowadzacy obiekt;
	private TextField tf[];

	public OknoElementuProwadzacy(String nazwa, JpaRepository repo, boolean typOkna) {
		super(nazwa, repo);
		
		// inicjalizacja elementow formularza
		tf = new TextField[5];
		tf[0] = new TextField("PESEL");
		tf[1] = new TextField("Stopień");
		tf[2] = new TextField("Imię");
		tf[3] = new TextField("Nazwisko");
		tf[4] = new TextField("Pokój");
		
		form.addComponents(tf[0], tf[1], tf[2], tf[3], tf[4]); // dodanie elementow do formularza
		
		// w zaleznosci od tego czy to okno dodawania czy edycji
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
					ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						public void onClose(ConfirmDialog dialog) {}
					});
					d.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	private void addNew() {
		Prowadzacy p = new Prowadzacy(Long.parseLong(tf[0].getValue()), tf[1].getValue(), tf[2].getValue(), tf[3].getValue(), tf[4].getValue());
		repo.save(p);
		clearForm();
	}
	
	private void clearForm() {
		for (TextField t : tf)
			t.clear();
	}
	
	private void oknoEdycji() {
		ok.setCaption("Wprowadź zmiany");
	}

	@Override
	public void setElement(Long id) {
		obiekt = ((ProwadzacyRepository)repo).findByIdProwadzacy(id);
		loadToForm();
		
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!tf[0].isEmpty() && !tf[1].isEmpty() && !tf[2].isEmpty() && !tf[3].isEmpty()) {
					updateObiekt();
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
	
	private void loadToForm() {
		StringBuilder sb = new StringBuilder();
		sb.append(obiekt.getPESEL());
		tf[0].setValue(sb.toString());
		tf[1].setValue(obiekt.getStopien());
		tf[2].setValue(obiekt.getImie());
		tf[3].setValue(obiekt.getNazwisko());
		
		if(obiekt.getPokoj() != null)
			tf[4].setValue(obiekt.getPokoj());
		else
			tf[4].setValue("");
	}
	
	private void updateObiekt() {
		obiekt.setPESEL(Long.parseLong(tf[0].getValue()));
		obiekt.setStopien(tf[1].getValue());
		obiekt.setImie(tf[2].getValue());
		obiekt.setNazwisko(tf[3].getValue());
		
		if(!tf[4].isEmpty())
			obiekt.setPokoj(tf[4].getValue());
		else
			obiekt.setPokoj("");
			
		repo.save(obiekt);
	}
}
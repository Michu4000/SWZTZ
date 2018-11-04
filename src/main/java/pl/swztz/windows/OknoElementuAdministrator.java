package pl.swztz.windows;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import pl.swztz.portal.models.Administrator;
import pl.swztz.portal.repositories.AdministratorRepository;

public class OknoElementuAdministrator extends OknoElementu {

	private Administrator obiekt;
	private TextField tf[];
	
	public OknoElementuAdministrator(String nazwa, JpaRepository repo, boolean typOkna) {
		super(nazwa, repo);
		tf = new TextField[4];
		tf[0] = new TextField("PESEL");
		tf[1] = new TextField("Imię");
		tf[2] = new TextField("Nazwisko");
		tf[3] = new TextField("Uprawnienia");
		form.addComponents(tf[0],tf[1],tf[2],tf[3]);
		
		if(typOkna)
			oknoDodawania();
		else
			oknoEdycji();
	}

	private void oknoDodawania() {
		ok.setCaption("Dodaj");
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!tf[0].isEmpty() && !tf[1].isEmpty() && !tf[2].isEmpty() && !tf[3].isEmpty()) {
					addNew();
					close();
				}
				
				else {
					ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Nie wprowadzono wszystkich danych", "OK", "", new ConfirmDialog.Listener() {
						@Override
						public void onClose(ConfirmDialog arg0) {}
					});
					d.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	private void addNew() {
		Administrator a = new Administrator(Long.parseLong(tf[0].getValue()), tf[1].getValue(), tf[2].getValue(), tf[3].getValue());
		repo.save(a);
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
	public void setElement(Long idAdministrator) {
		obiekt = ((AdministratorRepository)repo).findByIdAdministrator(idAdministrator);
		loadToForm();
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!tf[0].isEmpty() && !tf[1].isEmpty() && !tf[2].isEmpty() && !tf[3].isEmpty()) {
					updateObiekt();
					close();
				}
				else {
					ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Nie wprowadzono wszystkich danych", "OK", "", new ConfirmDialog.Listener() {
						@Override
						public void onClose(ConfirmDialog arg0) {}
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
		tf[1].setValue(obiekt.getImie());
		tf[2].setValue(obiekt.getNazwisko());
		tf[3].setValue(obiekt.getUprawnienia());
	}
	
	private void updateObiekt() {
		obiekt.setPESEL(Long.parseLong(tf[0].getValue()));
		obiekt.setImie(tf[1].getValue());
		obiekt.setNazwisko(tf[2].getValue());
		obiekt.setUprawnienia(tf[3].getValue());
		repo.save(obiekt);
	}
}
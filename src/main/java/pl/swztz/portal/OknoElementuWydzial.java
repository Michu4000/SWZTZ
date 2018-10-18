package pl.swztz.portal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import pl.swztz.portal.Models.Wydzial;
import pl.swztz.portal.Repositories.WydzialRepository;

public class OknoElementuWydzial extends OknoElementu {

	private Wydzial obiekt;
	private TextField tf;
	
	public OknoElementuWydzial(String nazwa, JpaRepository repo, boolean typOkna) {
		super(nazwa, repo);
		tf = new TextField("Nazwa");
		
		form.addComponents(tf);
		
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
				if(!tf.isEmpty()) {
					addNew();
					close();
				}
				else {
					ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						@Override
						public void onClose(ConfirmDialog arg0) {}
					});
					d.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	private void addNew() {
		Wydzial w = new Wydzial(tf.getValue());
		repo.save(w);
		clearForm();
	}
	
	private void clearForm() {
		tf.clear();
	}
	
	private void oknoEdycji() {
		ok.setCaption("Wprowadź zmiany");
	}
	
	@Override
	public void setElement(Long id) {
		obiekt = ((WydzialRepository)repo).findByIdWydzial(id);
		loadToForm();
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!tf.isEmpty()) {
					updateObiekt();
					close();
				}
				else {
					ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						@Override
						public void onClose(ConfirmDialog arg0) {}
					});
					d.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	private void loadToForm() {
		tf.setValue(obiekt.getNazwaWydzial());
	}
	
	private void updateObiekt() {
		obiekt.setNazwaWydzial(tf.getValue());
		repo.save(obiekt);
	}
}
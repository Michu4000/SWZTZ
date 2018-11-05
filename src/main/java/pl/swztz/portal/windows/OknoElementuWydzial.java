package pl.swztz.portal.windows;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import pl.swztz.portal.models.Wydzial;
import pl.swztz.portal.repositories.WydzialRepository;

public class OknoElementuWydzial extends OknoElementu {

	private Wydzial obj;
	private TextField textField;
	
	public OknoElementuWydzial(String title, JpaRepository repo, boolean windowType) {
		super(title, repo);
		textField = new TextField("Nazwa");
		
		form.addComponents(textField);
		
		if(windowType)
			addWindow();
		else
			editWindow();
	}

	private void addWindow() {
		okButton.setCaption("Dodaj");
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField.isEmpty()) {
					addNew();
					close();
				}
				else {
					ConfirmDialog dialog = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						@Override
						public void onClose(ConfirmDialog arg0) {}
					});
					dialog.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	private void addNew() {
		Wydzial newObj = new Wydzial(textField.getValue());
		repo.save(newObj);
		clearForm();
	}
	
	private void clearForm() {
		textField.clear();
	}
	
	private void editWindow() {
		okButton.setCaption("Wprowadź zmiany");
	}
	
	@Override
	public void setElement(Long id) {
		obj = ((WydzialRepository)repo).findByIdWydzial(id);
		loadToForm();
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField.isEmpty()) {
					updateobj();
					close();
				}
				else {
					ConfirmDialog dialog = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						@Override
						public void onClose(ConfirmDialog arg0) {}
					});
					dialog.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	private void loadToForm() {
		textField.setValue(obj.getNazwaWydzial());
	}
	
	private void updateobj() {
		obj.setNazwaWydzial(textField.getValue());
		repo.save(obj);
	}
}
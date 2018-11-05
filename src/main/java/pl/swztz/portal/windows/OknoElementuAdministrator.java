package pl.swztz.portal.windows;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.models.Administrator;
import pl.swztz.portal.repositories.AdministratorRepository;

public class OknoElementuAdministrator extends OknoElementu {

	private Administrator obj;
	private TextField textField[];
	
	public OknoElementuAdministrator(String title, JpaRepository repo, boolean windowType) {
		super(title, repo);
		textField = new TextField[4];
		textField[0] = new TextField("PESEL");
		textField[1] = new TextField("Imię");
		textField[2] = new TextField("Nazwisko");
		textField[3] = new TextField("Uprawnienia");
		form.addComponents(textField[0],textField[1],textField[2],textField[3]);
		
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
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !textField[2].isEmpty() && !textField[3].isEmpty()) {
					addNew();
					close();
				}
				
				else {
					ConfirmDialog dialog = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Nie wprowadzono wszystkich danych", "OK", "", new ConfirmDialog.Listener() {
						@Override
						public void onClose(ConfirmDialog arg0) {}
					});
					dialog.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	private void addNew() {
		Administrator newObj = new Administrator(Long.parseLong(textField[0].getValue()), textField[1].getValue(), textField[2].getValue(), textField[3].getValue());
		repo.save(newObj);
		clearForm();
	}
	
	private void clearForm() {
		for (TextField tf : textField)
			tf.clear();
	}
	
	private void editWindow() {
		okButton.setCaption("Wprowadź zmiany");
	}
	
	@Override
	public void setElement(Long idAdministrator) {
		obj = ((AdministratorRepository)repo).findByIdAdministrator(idAdministrator);
		loadToForm();
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !textField[2].isEmpty() && !textField[3].isEmpty()) {
					updateObj();
					close();
				}
				else {
					ConfirmDialog dialog = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Nie wprowadzono wszystkich danych", "OK", "", new ConfirmDialog.Listener() {
						@Override
						public void onClose(ConfirmDialog arg0) {}
					});
					dialog.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	private void loadToForm() {
		StringBuilder stingBuild = new StringBuilder();
		stingBuild.append(obj.getPESEL());
		textField[0].setValue(stingBuild.toString());
		textField[1].setValue(obj.getImie());
		textField[2].setValue(obj.getNazwisko());
		textField[3].setValue(obj.getUprawnienia());
	}
	
	private void updateObj() {
		obj.setPESEL(Long.parseLong(textField[0].getValue()));
		obj.setImie(textField[1].getValue());
		obj.setNazwisko(textField[2].getValue());
		obj.setUprawnienia(textField[3].getValue());
		repo.save(obj);
	}
}
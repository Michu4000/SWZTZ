package pl.swztz.windows;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.models.Prowadzacy;
import pl.swztz.portal.repositories.ProwadzacyRepository;

public class OknoElementuProwadzacy extends OknoElementu {
	
	private Prowadzacy obj;
	private TextField textField[];

	public OknoElementuProwadzacy(String title, JpaRepository repo, boolean windowType) {
		super(title, repo);
		
		// inicjalizacja elementów formularza
		textField = new TextField[5];
		textField[0] = new TextField("PESEL");
		textField[1] = new TextField("Stopień");
		textField[2] = new TextField("Imię");
		textField[3] = new TextField("Nazwisko");
		textField[4] = new TextField("Pokój");
		
		form.addComponents(textField[0], textField[1], textField[2], textField[3], textField[4]); // dodanie elementów do formularza
		
		// w zależności od tego czy to okno dodawania czy edycji
		if(windowType)
			oknoDodawania();
		else
			oknoEdycji();
	}
	
	private void oknoDodawania() {
		okButton.setCaption("Dodaj");
		
		// listener przycisku dodaj
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !textField[2].isEmpty() && !textField[3].isEmpty()) {
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
		Prowadzacy newObj = new Prowadzacy(Long.parseLong(textField[0].getValue()), textField[1].getValue(), textField[2].getValue(), textField[3].getValue(), textField[4].getValue());
		repo.save(newObj);
		clearForm();
	}
	
	private void clearForm() {
		for (TextField tf : textField)
			tf.clear();
	}
	
	private void oknoEdycji() {
		okButton.setCaption("Wprowadź zmiany");
	}

	@Override
	public void setElement(Long id) {
		obj = ((ProwadzacyRepository)repo).findByIdProwadzacy(id);
		loadToForm();
		
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !textField[2].isEmpty() && !textField[3].isEmpty()) {
					updateobj();
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
	
	private void loadToForm() {
		StringBuilder stringBuild = new StringBuilder();
		stringBuild.append(obj.getPESEL());
		textField[0].setValue(stringBuild.toString());
		textField[1].setValue(obj.getStopien());
		textField[2].setValue(obj.getImie());
		textField[3].setValue(obj.getNazwisko());
		
		if(obj.getPokoj() != null)
			textField[4].setValue(obj.getPokoj());
		else
			textField[4].setValue("");
	}
	
	private void updateobj() {
		obj.setPESEL(Long.parseLong(textField[0].getValue()));
		obj.setStopien(textField[1].getValue());
		obj.setImie(textField[2].getValue());
		obj.setNazwisko(textField[3].getValue());
		
		if(!textField[4].isEmpty())
			obj.setPokoj(textField[4].getValue());
		else
			obj.setPokoj("");
			
		repo.save(obj);
	}
}
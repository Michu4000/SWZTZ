package pl.swztz.windows;

import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.models.Sala;
import pl.swztz.portal.repositories.SalaRepository;

public class OknoElementuSala extends OknoElementu {

	private Sala obj;
	private TextField textField[];
		
	public OknoElementuSala(String title, SalaRepository repo, boolean windowType) {
		super(title, repo);
		
		// inicjalizacja elementów formularza
		textField = new TextField[4];
		textField[0] = new TextField("Numer Sali");
		textField[1] = new TextField("Budynek");
		textField[2] = new TextField("Typ Sali");
		textField[3] = new TextField("Liczba miejsc");
		
		form.addComponents(textField[0], textField[1], textField[2], textField[3]); // dodanie elementow do formularza
		
		 //w zależności od tego czy to okno dodawania czy edycji
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
					ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(), "Sala", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						public void onClose(ConfirmDialog dialog) {}
					});
					d.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	private void addNew() {
		Sala newObj = new Sala(textField[0].getValue(), textField[1].getValue(), textField[2].getValue(), Integer.parseInt(textField[3].getValue()));
		repo.save(newObj);
		clearForm();
	}
	
	private void clearForm() {
		for (TextField tf : textField)
			tf.clear();
	}
	
	// tylko dla okna edycji
	private void oknoEdycji() {
		okButton.setCaption("Wprowadź zmiany");
	}
	
	@Override
	public void setElement(Long id) {
		obj = ((SalaRepository)repo).findByIdSala(id);
		loadToForm();
		
		// listener przycisku wprowadź zmiany
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !textField[2].isEmpty() && !textField[3].isEmpty()) {
					updateobj(); 
					close();
				}
				else {
					ConfirmDialog dialog = ConfirmDialog.show(UI.getCurrent(), "Sala", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						public void onClose(ConfirmDialog dialog) {}
					});
					dialog.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	// dla okna edycji: pobierz dane formularza
	private void loadToForm() {
		textField[0].setValue(obj.getNrSala());
		textField[1].setValue(obj.getBudynek());
		textField[2].setValue(obj.getTypSala());
		textField[3].setValue(Integer.toString(obj.getIloscMiejsc()));
	}
		
	// dla okna edycji: aktualizuj wpis w bazie danych
	private void updateobj() {
		obj.setNrSala(textField[0].getValue());
		obj.setBudynek(textField[1].getValue());
		obj.setTypSala(textField[2].getValue());
		obj.setIloscMiejsc(Integer.parseInt(textField[3].getValue()));
		repo.save(obj);
	}
}
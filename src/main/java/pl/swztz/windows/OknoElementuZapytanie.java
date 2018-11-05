package pl.swztz.windows;

import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import pl.swztz.portal.models.Zapytanie;
import pl.swztz.portal.repositories.ZapytanieRepository;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class OknoElementuZapytanie extends OknoElementu {
	
	private Zapytanie obj;
	private TextField textField[];
	private CheckBox checkbox;
	private ComboBox<String[]> prowadzacy;
	private String parentWindowTitle;

	public OknoElementuZapytanie(String title, ZapytanieRepository repo, boolean windowType, String parentWindowTitle) {
		super(title, repo);
		
		textField = new TextField[2];
		textField[0] = new TextField("Id studenta");
		textField[1] = new TextField("Treść zapytania");
		
		this.parentWindowTitle = parentWindowTitle;
		
		checkbox = new CheckBox("Decyzja");
		
		prowadzacy = new ComboBox<>("Prowadzący");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object [] objectArray :  repo.findProwadzacych()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}
		
		prowadzacy.setItems(stringArrayList);
		prowadzacy.setItemCaptionGenerator(x -> x[1]);
		
		form.addComponents(textField[0], textField[1], prowadzacy, checkbox);
		
		if(windowType)
			oknoDodawania();
		else
			oknoEdycji();
	}

	private void oknoEdycji() {
		okButton.setCaption("Wprowadź zmiany");
	}

	private void oknoDodawania() {
		okButton.setCaption("Dodaj");
		
		// listener przycisku dodaj
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !prowadzacy.isEmpty()) {
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
		Long idProwadzacy = Long.parseLong(prowadzacy.getSelectedItem().orElse(null)[0]);
		Zapytanie newObj = new Zapytanie(Long.parseLong(textField[0].getValue()), idProwadzacy, textField[1].getValue(), checkbox.getValue());
		repo.save(newObj);
		
		clearForm();
	}

	private void clearForm() {
		textField[0].clear();
		textField[1].clear();
		checkbox.clear();
		prowadzacy.clear();
	}

	@Override
	public void setElement(Long id) {
		obj = ((ZapytanieRepository)repo).findByIdZapytanie(id);
		loadToForm();
		
		// listener przycisku wprowadź zmiany
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !prowadzacy.isEmpty()) {
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

	private void updateObiekt() {
		obj.setIdStudent(((ZapytanieRepository)repo).findStudentID(textField[0].getValue()));
		obj.setTrescZapytania((textField[1].getValue()));
		obj.setDecyzjaProwadzacego((checkbox.getValue()));
		obj.setIdProwadzacy(Long.parseLong(prowadzacy.getSelectedItem().orElse(null)[0]));
		repo.save(obj);
	}

	private void loadToForm() {
		Object[] objectArray = ((ZapytanieRepository)repo).findProwadzacy(obj.getIdProwadzacy()).get(0);
		String[] objString = new String[] { ""+objectArray[0], (String)objectArray[1] };
		
		textField[0].setValue(((ZapytanieRepository)repo).findStudent(obj.getIdStudent()));
		textField[1].setValue(obj.getTrescZapytania());
		checkbox.setValue(obj.getDecyzjaProwadzacego());
		prowadzacy.setSelectedItem(objString);
	}
}
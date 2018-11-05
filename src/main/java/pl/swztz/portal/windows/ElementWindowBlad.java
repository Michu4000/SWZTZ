package pl.swztz.portal.windows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.shared.ui.datefield.DateTimeResolution;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.models.Blad;
import pl.swztz.portal.repositories.BladRepository;

public class ElementWindowBlad extends ElementWindow {
	
	private Blad obj;
	private TextField textField[];
	private DateTimeField date;
	private CheckBox checkbox;
	private ComboBox<String[]> user;
	
	public ElementWindowBlad(String title, BladRepository repo, boolean windowType) {
		super(title, repo); // constructor of the base class
		
		// initialize elements of the form
		textField = new TextField[2];
		textField[0] = new TextField("Temat");
		textField[1] = new TextField("Opis");
		
		date = new DateTimeField("Data zgłoszenia");
		date.setDateFormat("dd-MM-yyyy HH:mm:ss");
		date.setResolution(DateTimeResolution.SECOND);
		date.setTextFieldEnabled(false);
		date.setValue(LocalDateTime.now());
		
		checkbox = new CheckBox("Rozpatrzono");
		
		// initialize combobox and get data
		user = new ComboBox<>("Zgłaszający");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findUsers()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		user.setItems(stringArrayList);
		user.setItemCaptionGenerator(x -> x[1]); // display user name in combobox
		
		form.addComponents(textField[0], textField[1], date, checkbox, user); // add elements to form
		
		// depending on whether it's a add window or edit window
		if(windowType)
			addWindow();
		else
			editWindow();
	}
	
	// only for add window
	private void addWindow() {
		okButton.setCaption("Dodaj");
		
		// set listener for add button
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !user.isEmpty()) {
					addNew();
					close();
				}
				else {
					// pop-up window with error message
					ConfirmDialog dialog = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						public void onClose(ConfirmDialog dialog) {}
					});
					dialog.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	private void addNew() {
		Long iduser = Long.parseLong(user.getSelectedItem().orElse(null)[0]);
		Blad newObj = new Blad(iduser, textField[0].getValue(), textField[1].getValue(), date.getValue(), checkbox.getValue());
		repo.save(newObj);
		clearForm();
	}
	
	private void clearForm() {
		for (TextField tf : textField)
			tf.clear();
		date.clear();
		checkbox.clear();
		user.clear();
	}
	
	// only for edit window
	private void editWindow() {
		okButton.setCaption("Wprowadź zmiany");
	}
	
	// for edit window: set id for edited entry
	@Override
	public void setElement(Long id) {
		obj = ((BladRepository)repo).findByIdBlad(id);
		loadToForm();
		
		// set listener for apply changes button
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !user.isEmpty()) {
					updateObj(); 
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
	
	// for edit window: get data to form
	private void loadToForm() {
		Object[] objectArray = ((BladRepository)repo).findUser(obj.getIdUzytkownik()).get(0);
		String[] objString = new String[] { ""+objectArray[0], (String)objectArray[1] };
		
		textField[0].setValue(obj.getTemat());
		textField[1].setValue(obj.getOpis());
		date.setValue(obj.getDataZgloszenia());
		checkbox.setValue(obj.isRozpatrzono());
		user.setSelectedItem(objString);
	}
	
	// for edit window: update entry in database
	private void updateObj() {
		obj.setTemat(textField[0].getValue());
		obj.setOpis(textField[1].getValue());
		obj.setDataZgloszenia(date.getValue());
		obj.setRozpatrzono(checkbox.getValue());
		obj.setIdUzytkownik(Long.parseLong(user.getSelectedItem().orElse(null)[0]));
		repo.save(obj);
	}
}
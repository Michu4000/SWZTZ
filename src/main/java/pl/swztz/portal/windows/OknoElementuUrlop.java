package pl.swztz.portal.windows;

import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.models.Urlop;
import pl.swztz.portal.repositories.UrlopRepository;
import pl.swztz.portal.windows.OknoElementu;

public class OknoElementuUrlop extends OknoElementu {

	private Urlop obj;
	private DateField startDate, endDate;
	private ComboBox<String[]> prowadzacy;
	private TextField textField;
		
	public OknoElementuUrlop(String title, UrlopRepository repo, boolean windowType) {
		super(title, repo); // constructor of the base class

		// initialize elements of the form
		textField = new TextField("Powód");
			
		startDate = new DateField("Data rozpoczecia");
		startDate.setDateFormat("dd-MM-yyyy");
		startDate.setTextFieldEnabled(false);
		
		endDate = new DateField("Data zakończenia");
		endDate.setDateFormat("dd-MM-yyyy");
		endDate.setTextFieldEnabled(false);
		
		// initialize combobox and get data
		prowadzacy = new ComboBox<>("Prowadzacy");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findProwadzacych()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		prowadzacy.setItems(stringArrayList);
		prowadzacy.setItemCaptionGenerator(x -> x[1]); // set displaying user name in combobox

		form.addComponents(prowadzacy, startDate, endDate, textField); // add elements to form 
		
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
				if(!textField.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty() &&  !prowadzacy.isEmpty()) {
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
		Long idProwadzacy = Long.parseLong(prowadzacy.getSelectedItem().orElse(null)[0]);
		Urlop newObj = new Urlop(idProwadzacy, startDate.getValue(), endDate.getValue(), textField.getValue());
		repo.save(newObj);
		clearForm();
	}
	
	private void clearForm() {
		textField.clear();
		prowadzacy.clear();
		startDate.clear();
		endDate.clear();
	}
	
	// only for edit window
	private void editWindow() {
		okButton.setCaption("Wprowadź zmiany");
	}
	
	// for edit window: set id for edited entry
	@Override
	public void setElement(Long id) {
		obj = ((UrlopRepository)repo).findByIdUrlop(id);
		loadToForm();
		
		// set listener for apply changes button
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!prowadzacy.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty() && !textField.isEmpty()) {
					updateobj(); 
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
		Object[] objectArray = ((UrlopRepository)repo).findProwadzacy(obj.getIdProwadzacy()).get(0);
		String[] objString = new String[] { ""+objectArray[0], (String)objectArray[1] };
		
		startDate.setValue(obj.getDataRozpoczecia());
		endDate.setValue(obj.getDataZakonczenia());
		textField.setValue(obj.getPowod());
		prowadzacy.setSelectedItem(objString);
	}
	
	// for edit window: update entry in database
	private void updateobj() {
		obj.setDataRozpoczecia(startDate.getValue());
		obj.setDataZakonczenia(endDate.getValue());
		obj.setIdProwadzacy(Long.parseLong(prowadzacy.getSelectedItem().orElse(null)[0]));
		obj.setPowod(textField.getValue());
		repo.save(obj);
	}
}
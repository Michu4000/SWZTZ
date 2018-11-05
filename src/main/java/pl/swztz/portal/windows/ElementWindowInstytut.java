package pl.swztz.portal.windows;

import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.models.Instytut;
import pl.swztz.portal.repositories.InstytutRepository;
import pl.swztz.portal.windows.ElementWindow;

public class ElementWindowInstytut extends ElementWindow {

	private Instytut obj;	
	private ComboBox<String[]> wydzial;
	private TextField nazwaInstytut;
	
	public ElementWindowInstytut(String title, InstytutRepository repo, boolean windowType) {
		super(title, repo); // constructor of the base class

		// initialize elements of the form
		nazwaInstytut= new TextField("Nazwa Instytutu");
		
		// initialize combobox and get data
		wydzial = new ComboBox<>("Wydział");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findWydzialy()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		wydzial.setItems(stringArrayList);
		wydzial.setItemCaptionGenerator(x -> x[1]); // set displaying user name in combobox

		form.addComponents(nazwaInstytut, wydzial); // add elements to form 
		
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
				if(!nazwaInstytut.isEmpty() && !wydzial.isEmpty()) {
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
		Long idWydzial = Long.parseLong(wydzial.getSelectedItem().orElse(null)[0]);
		Instytut newObj = new Instytut(nazwaInstytut.getValue(), idWydzial);
		repo.save(newObj);
		clearForm();
	}
	
	private void clearForm() {
		nazwaInstytut.clear();
		wydzial.clear();
	}
	
	// only for edit window
	private void editWindow() {
		okButton.setCaption("Wprowadź zmiany");
	}
	
	// for edit window: set id for edited entry
	@Override
	public void setElement(Long id) {
		obj = ((InstytutRepository)repo).findByIdInstytut(id);
		loadToForm();
		
		// set listener for apply changes button
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!wydzial.isEmpty() &&   !nazwaInstytut.isEmpty()) {
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
	
	// for edit window: get data to form
	private void loadToForm() {
		Object[] objectArray = ((InstytutRepository)repo).findWydzial(obj.getIdWydzial()).get(0);
		String[] objString = new String[] { ""+objectArray[0], (String)objectArray[1] };
		
		nazwaInstytut.setValue(obj.getNazwaInstytut());
		wydzial.setSelectedItem(objString);
	}
	
	// for edit window: update entry in database
	private void updateObiekt() {
		obj.setNazwaInstytut(nazwaInstytut.getValue());
		obj.setIdWydzial(Long.parseLong(wydzial.getSelectedItem().orElse(null)[0]));
		
		repo.save(obj);
	}
}
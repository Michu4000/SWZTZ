package pl.swztz.portal.windows;

import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.models.Przedmiot;
import pl.swztz.portal.repositories.PrzedmiotRepository;

public class ElementWindowPrzedmiot extends ElementWindow {

	private Przedmiot obj;
	private TextField textField;
	private ComboBox<String[]> instytut;
	
	public ElementWindowPrzedmiot(String title, PrzedmiotRepository repo, boolean windowType) {
		super(title, repo);
		
		textField= new TextField("Nazwa przedmiotu");
		
		instytut = new ComboBox<>("Instytut");
		List<String[]> stringArrayList = new ArrayList<>();
		
		for(Object[] objectArray : repo.findInstytuty()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}
		
		instytut.setItems(stringArrayList);
		instytut.setItemCaptionGenerator(x -> x[1]);
		form.addComponents(textField, instytut);
		
		if(windowType)
			addWindow();
		else
			editWindow();
	}

	private void addWindow()
	{
		okButton.setCaption("Dodaj");
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField.isEmpty() && !instytut.isEmpty())
				{
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
		Long idInstytut = Long.parseLong(instytut.getSelectedItem().orElse(null)[0]);
		Przedmiot newObj = new Przedmiot(textField.getValue(), idInstytut);
		repo.save(newObj);
		clearForm();
	}
	
	private void clearForm() {
		textField.clear();
		instytut.clear();
	}
	
	private void editWindow() {
		okButton.setCaption("Wprowadź zmiany");
	}
	
	@Override
	public void setElement(Long id) {
		obj = ((PrzedmiotRepository)repo).findByIdPrzedmiot(id);
		loadToForm();
		
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField.isEmpty() && !instytut.isEmpty()) {
					updateobj();
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
	
	private void loadToForm() {
		Object[] objectArray = ((PrzedmiotRepository)repo).findInstytut(obj.getIdInstytut()).get(0);
		String [] objString = new String[] { ""+objectArray[0], (String)objectArray[1] };
		
		textField.setValue(obj.getNazwaPrzedmiot());
		instytut.setSelectedItem(objString);
	}
	
	private void updateobj() {
		obj.setNazwaPrzedmiot(textField.getValue());
		obj.setIdInstytut(Long.parseLong(instytut.getSelectedItem().orElse(null)[0]));
		repo.save(obj);
	}
}
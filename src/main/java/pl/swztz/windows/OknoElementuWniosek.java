package pl.swztz.windows;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import pl.swztz.portal.models.Wniosek;
import pl.swztz.portal.repositories.WniosekRepository;

public class OknoElementuWniosek extends OknoElementu {

	private Wniosek obj;
	private ComboBox<String[]> prowadzacy;
	private ComboBox<String[]> dyrektor;
	private TextField textField;
	private CheckBox decyzja;
	private CheckBox czyZmieniono;
	
	public OknoElementuWniosek(String title, WniosekRepository repo, boolean windowType) {
		super(title, repo);
		
	  prowadzacy = new ComboBox<>("Prowadzacy");
	  dyrektor = new ComboBox<>("Dyrektor");
	  textField = new TextField("Treść");
	  decyzja = new CheckBox("Decyzja");
	  czyZmieniono = new CheckBox("Czy zmieniono");
	  List<String[]> stringArrayList = new ArrayList<>();
	  
	  for(Object[] objectArray : repo.findProwadzacych()) {
		  stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
	  }
	  
	  prowadzacy.setItems(stringArrayList);
	  prowadzacy.setItemCaptionGenerator(x -> x[1]);
	  
	  List<String[]> stringArrayList1 = new ArrayList<>();
	  
	  for(Object[] objectArray : repo.findDyrektorzy()) {
		  stringArrayList1.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
	  }
	  
	  dyrektor.setItems(stringArrayList1);
	  dyrektor.setItemCaptionGenerator(x -> x[1]);
	  form.addComponents(prowadzacy, dyrektor, textField, decyzja, czyZmieniono);
	  
	  if(windowType)
		  oknoDodawania();
	  else
		  oknoEdycji();
	}

	private void oknoDodawania() {
		okButton.setCaption("Dodaj");
		
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!prowadzacy.isEmpty() && !dyrektor.isEmpty() && !textField.isEmpty()) {
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
		Long idProwadzacy = Long.parseLong(prowadzacy.getSelectedItem().orElse(null)[0]);
		Long idDyrektor = Long.parseLong(dyrektor.getSelectedItem().orElse(null)[0]);
		Wniosek newObj = new Wniosek(idProwadzacy, idDyrektor, textField.getValue(), decyzja.getValue(), czyZmieniono.getValue());
		repo.save(newObj);
		clearForm();
	}
	
	private void clearForm() {
		textField.clear();
		prowadzacy.clear();
		dyrektor.clear();
		decyzja.clear();
		czyZmieniono.clear();
	}
	
	private void oknoEdycji() {
		okButton.setCaption("Wprowadź zmiany");
	}
	
	@Override
	public void setElement(Long id) {
		obj = ((WniosekRepository)repo).findByIdWniosek(id);
		loadToForm();

		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!prowadzacy.isEmpty() && !dyrektor.isEmpty() && !textField.isEmpty()) {
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
		Object[] objectArray = ((WniosekRepository)repo).findProwadzacy(obj.getIdProwadzacy()).get(0);
		String[] objString = new String[] { ""+objectArray[0], (String)objectArray[1] };
		Object[] objectArray1 = ((WniosekRepository)repo).findDyrektor(obj.getIdDyrektor()).get(0);
		String[] objString1 = new String[] { ""+objectArray1[0], (String)objectArray1[1] };
		
		textField.setValue(obj.getTrescWniosek());
		decyzja.setValue(obj.isDecyzja());
		czyZmieniono.setValue(obj.isZmienionoPlan());
		prowadzacy.setSelectedItem(objString);
		dyrektor.setSelectedItem(objString1);
	}
	
	private void updateobj() {
		obj.setIdProwadzacy(Long.parseLong(prowadzacy.getSelectedItem().orElse(null)[0]));
		obj.setIdDyrektor(Long.parseLong(dyrektor.getSelectedItem().orElse(null)[0]));
		obj.setTrescWniosek(textField.getValue());
		obj.setDecyzja(decyzja.getValue());
		obj.setZmienionoPlan(czyZmieniono.getValue());
		repo.save(obj);
	}
}
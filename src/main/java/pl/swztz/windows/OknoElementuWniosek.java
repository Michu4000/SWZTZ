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

	private Wniosek obiekt;
	private ComboBox<String[]> prowadzacy;
	private ComboBox<String[]> dyrektor;
	private TextField tf;
	private CheckBox decyzja;
	private CheckBox czyZmieniono;
	
	public OknoElementuWniosek(String nazwa, WniosekRepository repo, boolean typOkna) {
		super(nazwa, repo);
		
	  prowadzacy = new ComboBox<>("Prowadzacy");
	  dyrektor = new ComboBox<>("Dyrektor");
	  tf = new TextField("Treść");
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
	  form.addComponents(prowadzacy, dyrektor, tf, decyzja, czyZmieniono);
	  
	  if(typOkna)
		  oknoDodawania();
	  else
		  oknoEdycji();
	}

	private void oknoDodawania() {
		ok.setCaption("Dodaj");
		
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!prowadzacy.isEmpty() && !dyrektor.isEmpty() && !tf.isEmpty()) {
					addNew();
					close();
				}
				else {
					ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						
						@Override
						public void onClose(ConfirmDialog arg0) {}
					});
					d.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	private void addNew() {
		Long idProwadzacy = Long.parseLong(prowadzacy.getSelectedItem().orElse(null)[0]);
		Long idDyrektor = Long.parseLong(dyrektor.getSelectedItem().orElse(null)[0]);
		Wniosek w = new Wniosek(idProwadzacy, idDyrektor, tf.getValue(), decyzja.getValue(), czyZmieniono.getValue());
		repo.save(w);
		clearForm();
	}
	
	private void clearForm() {
		tf.clear();
		prowadzacy.clear();
		dyrektor.clear();
		decyzja.clear();
		czyZmieniono.clear();
	}
	
	private void oknoEdycji() {
		ok.setCaption("Wprowadź zmiany");
	}
	
	@Override
	public void setElement(Long id) {
		obiekt = ((WniosekRepository)repo).findByIdWniosek(id);
		loadToForm();

		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!prowadzacy.isEmpty() && !dyrektor.isEmpty() && !tf.isEmpty()) {
					updateObiekt();
					close();
				}
				else {
					ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						@Override
						public void onClose(ConfirmDialog arg0) {}
					});
					d.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	private void loadToForm() {
		Object[] objectArray = ((WniosekRepository)repo).findProwadzacy(obiekt.getIdProwadzacy()).get(0);
		String[] sobiekt = new String[] { ""+objectArray[0], (String)objectArray[1] };
		Object[] objectArray1 = ((WniosekRepository)repo).findDyrektor(obiekt.getIdDyrektor()).get(0);
		String[] sobiekt1 = new String[] { ""+objectArray1[0], (String)objectArray1[1] };
		
		tf.setValue(obiekt.getTrescWniosek());
		decyzja.setValue(obiekt.isDecyzja());
		czyZmieniono.setValue(obiekt.isZmienionoPlan());
		prowadzacy.setSelectedItem(sobiekt);
		dyrektor.setSelectedItem(sobiekt1);
	}
	
	private void updateObiekt() {
		obiekt.setIdProwadzacy(Long.parseLong(prowadzacy.getSelectedItem().orElse(null)[0]));
		obiekt.setIdDyrektor(Long.parseLong(dyrektor.getSelectedItem().orElse(null)[0]));
		obiekt.setTrescWniosek(tf.getValue());
		obiekt.setDecyzja(decyzja.getValue());
		obiekt.setZmienionoPlan(czyZmieniono.getValue());
		repo.save(obiekt);
	}
}
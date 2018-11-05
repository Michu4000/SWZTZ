package pl.swztz.portal.windows;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.addons.ComboBoxMultiselect;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.models.Zajecia;
import pl.swztz.portal.repositories.ZajeciaRepository;

public class OknoElementuZajecia extends OknoElementu {

	private Zajecia obj;
	private TextField textField;
	private DateField date;
	private ComboBox<Long> nrBloku;
	private ComboBox<String[]> sala, przedmiot, prowadzacy;
	private ComboBoxMultiselect<String[]> grupy;
	
	public OknoElementuZajecia(String title, JpaRepository repo, boolean windowType) {
		super(title, repo);
		
		// initialize elements of the form
		textField = new TextField("Typ zajęć");

		date = new DateField("Data zajęć");
		date.setDateFormat("dd-MM-yyyy");
		date.setTextFieldEnabled(false);
		
		nrBloku = new ComboBox<>("Numer Bloku");
		nrBloku.setItems(1L, 2L, 3L, 4L, 5L, 6L, 7L);
		
		sala = new ComboBox<>("Sala");
		List<String[]> stringArrayList1 = new ArrayList<>();
		
		for(Object [] objectArray :  ((ZajeciaRepository)repo).findSale()) {
			stringArrayList1.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}
		
		sala.setItems(stringArrayList1);
		sala.setItemCaptionGenerator(x -> x[1]);
		
		przedmiot = new ComboBox<>("Przedmiot");
		List<String[]> stringArrayList2 = new ArrayList<>();
		
		for(Object [] objectArray :  ((ZajeciaRepository)repo).findPrzedmioty()) {
			stringArrayList2.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}
		
		przedmiot.setItems(stringArrayList2);
		przedmiot.setItemCaptionGenerator(x -> x[1]);
		
		prowadzacy = new ComboBox<>("Prowadzący");
		List<String[]> stringArrayList3 = new ArrayList<>();
		
		for(Object [] objectArray :  ((ZajeciaRepository)repo).findProwadzacych()) {
			stringArrayList3.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}
		
		prowadzacy.setItems(stringArrayList3);
		prowadzacy.setItemCaptionGenerator(x -> x[1]);
		
		grupy = new ComboBoxMultiselect<>("Grupy");
		List<String[]> stringArrayList4 = new ArrayList<>();
		
		for(Object [] objectArray :  ((ZajeciaRepository)repo).findGrupy()) {
			stringArrayList4.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}
		
		grupy.setItems(stringArrayList4);
		grupy.setItemCaptionGenerator(x -> x[1]);
		
		form.addComponents(date, nrBloku, sala, przedmiot, textField, prowadzacy, grupy);
		
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
				if(!date.isEmpty() && !nrBloku.isEmpty() && !sala.isEmpty() && !przedmiot.isEmpty() && !textField.isEmpty() && !prowadzacy.isEmpty() && !grupy.isEmpty()) {
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
		Zajecia newObj = new Zajecia(date.getValue(), nrBloku.getValue(), Long.parseLong(sala.getSelectedItem().orElse(null)[0]),
						Long.parseLong(przedmiot.getSelectedItem().orElse(null)[0]), textField.getValue(), Long.parseLong(prowadzacy.getSelectedItem().orElse(null)[0]));
		repo.save(newObj);
		
		// insert into table ZAJECIA_GRUPY
		for( String[] gr : grupy.getSelectedItems()) {
			((ZajeciaRepository)repo).insertZajeciaGrupy(Long.parseLong(gr[0]), newObj.getIdZajecia());
		}
		
		clearForm();
	}
	
	private void clearForm() {
		date.clear();
		nrBloku.clear();
		sala.clear();
		przedmiot.clear();
		textField.clear();
		prowadzacy.clear();
		grupy.clear();
	}
	
	private void editWindow() {
		okButton.setCaption("Wprowadź zmiany");
	}
	
	@Override
	public void setElement(Long id) {
		obj = ((ZajeciaRepository)repo).findByIdZajecia(id);
		loadToForm();
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!date.isEmpty() && !nrBloku.isEmpty() && !sala.isEmpty() && !przedmiot.isEmpty() && !textField.isEmpty() && !prowadzacy.isEmpty() && !grupy.isEmpty()) {
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
		date.setValue(obj.getData());
		nrBloku.setValue(obj.getNrBloku());
		
		Object[] objectArray1 = ((ZajeciaRepository) repo).findSala(obj.getIdSala()).get(0);
		String[] objString1 = new String[] { ""+objectArray1[0], (String)objectArray1[1] };
		sala.setSelectedItem(objString1);
		
		Object[] objectArray2 = ((ZajeciaRepository) repo).findPrzedmiot(obj.getIdPrzedmiot()).get(0);
		String[] objString2 = new String[] { ""+objectArray2[0], (String)objectArray2[1] };
		przedmiot.setSelectedItem(objString2);
		
		textField.setValue(obj.getTypZajec());
		
		Object[] objectArray3 = ((ZajeciaRepository) repo).findProwadzacy(obj.getIdProwadzacy()).get(0);
		String[] objString3 = new String[] { ""+objectArray3[0], (String)objectArray3[1] };
		prowadzacy.setSelectedItem(objString3);
		
		// load groups for given class from ZAJECIA_GRUPY table and select in form - something is wrong, only deselect
		grupy.deselectAll();
		
		/*
		for(Object [] objectArray :  ((ZajeciaRepository)repo).findZajeciaGrupy(obiekt.getIdZajecia())) {
			grupy.select(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}
		*/
	}
	
	private void updateobj()	{
		obj.setData(date.getValue());
		obj.setNrBloku(nrBloku.getValue());
		obj.setIdSala(Long.parseLong(sala.getSelectedItem().orElse(null)[0]));
		obj.setIdPrzedmiot(Long.parseLong(przedmiot.getSelectedItem().orElse(null)[0]));
		obj.setTypZajec(textField.getValue());
		obj.setIdProwadzacy(Long.parseLong(prowadzacy.getSelectedItem().orElse(null)[0]));
		repo.save(obj);
		
		// update on ZAJECIA_GRUPY (delete all and then inserts)
		((ZajeciaRepository)repo).deleteZajeciaGrupy(obj.getIdZajecia());
		
		for( String[] gr : grupy.getSelectedItems()) {
			((ZajeciaRepository)repo).insertZajeciaGrupy(Long.parseLong(gr[0]), obj.getIdZajecia());
		}
	}
}
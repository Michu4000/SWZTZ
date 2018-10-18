package pl.swztz.portal;

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
import pl.swztz.portal.Models.Zajecia;
import pl.swztz.portal.Repositories.ZajeciaRepository;

public class OknoElementuZajecia extends OknoElementu {

	private Zajecia obiekt;
	private TextField tf;
	private DateField data;
	private ComboBox<Long> nrBloku;
	private ComboBox<String[]> sala, przedmiot, prowadzacy;
	private ComboBoxMultiselect<String[]> grupy;
	
	public OknoElementuZajecia(String nazwa, JpaRepository repo, boolean typOkna) {
		super(nazwa, repo);
		
		//inicjalizacja elementow formularza
		tf = new TextField("Typ zajęć");

		data = new DateField("Data zajęć");
		data.setDateFormat("dd-MM-yyyy");
		data.setTextFieldEnabled(false);
		
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
		
		form.addComponents(data, nrBloku, sala, przedmiot, tf, prowadzacy, grupy);
		
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
				if(!data.isEmpty() && !nrBloku.isEmpty() && !sala.isEmpty() && !przedmiot.isEmpty() && !tf.isEmpty() && !prowadzacy.isEmpty() && !grupy.isEmpty()) {
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
		Zajecia z = new Zajecia(data.getValue(), nrBloku.getValue(), Long.parseLong(sala.getSelectedItem().orElse(null)[0]),
						Long.parseLong(przedmiot.getSelectedItem().orElse(null)[0]), tf.getValue(), Long.parseLong(prowadzacy.getSelectedItem().orElse(null)[0]));
		repo.save(z);
		
		// insert do tabeli ZAJECIA_GRUPY
		for( String[] gr : grupy.getSelectedItems()) {
			((ZajeciaRepository)repo).insertZajeciaGrupy(Long.parseLong(gr[0]), z.getIdZajecia());
		}
		
		clearForm();
	}
	
	private void clearForm() {
		data.clear();
		nrBloku.clear();
		sala.clear();
		przedmiot.clear();
		tf.clear();
		prowadzacy.clear();
		grupy.clear();
	}
	
	private void oknoEdycji() {
		ok.setCaption("Wprowadź zmiany");
	}
	
	@Override
	public void setElement(Long id) {
		obiekt = ((ZajeciaRepository)repo).findByIdZajecia(id);
		loadToForm();
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!data.isEmpty() && !nrBloku.isEmpty() && !sala.isEmpty() && !przedmiot.isEmpty() && !tf.isEmpty() && !prowadzacy.isEmpty() && !grupy.isEmpty()) {
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
		data.setValue(obiekt.getData());
		nrBloku.setValue(obiekt.getNrBloku());
		
		Object[] objectArray1 = ((ZajeciaRepository) repo).findSala(obiekt.getIdSala()).get(0);
		String[] sobiekt1 = new String[] { ""+objectArray1[0], (String)objectArray1[1] };
		sala.setSelectedItem(sobiekt1);
		
		Object[] objectArray2 = ((ZajeciaRepository) repo).findPrzedmiot(obiekt.getIdPrzedmiot()).get(0);
		String[] sobiekt2 = new String[] { ""+objectArray2[0], (String)objectArray2[1] };
		przedmiot.setSelectedItem(sobiekt2);
		
		tf.setValue(obiekt.getTypZajec());
		
		Object[] objectArray3 = ((ZajeciaRepository) repo).findProwadzacy(obiekt.getIdProwadzacy()).get(0);
		String[] sobiekt3 = new String[] { ""+objectArray3[0], (String)objectArray3[1] };
		prowadzacy.setSelectedItem(sobiekt3);
		
		// zaladuj grupy dla danego zajecia z ZAJECIA_GRUPY i zaznacz w formularzu - cos nie dziala, wiec tylko deselect
		grupy.deselectAll();
		
		/*for(Object [] objectArray :  ((ZajeciaRepository)repo).findZajeciaGrupy(obiekt.getIdZajecia())) {
			grupy.select(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}*/
	}
	
	private void updateObiekt()	{
		obiekt.setData(data.getValue());
		obiekt.setNrBloku(nrBloku.getValue());
		obiekt.setIdSala(Long.parseLong(sala.getSelectedItem().orElse(null)[0]));
		obiekt.setIdPrzedmiot(Long.parseLong(przedmiot.getSelectedItem().orElse(null)[0]));
		obiekt.setTypZajec(tf.getValue());
		obiekt.setIdProwadzacy(Long.parseLong(prowadzacy.getSelectedItem().orElse(null)[0]));
		repo.save(obiekt);
		
		// update na ZAJECIA_GRUPY (najpierw usuwamy wszystko, a potem robimy inserty)
		((ZajeciaRepository)repo).deleteZajeciaGrupy(obiekt.getIdZajecia());
		
		for( String[] gr : grupy.getSelectedItems()) {
			((ZajeciaRepository)repo).insertZajeciaGrupy(Long.parseLong(gr[0]), obiekt.getIdZajecia());
		}
	}
}
package pl.swztz.portal;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.Models.Blad;
import pl.swztz.portal.Models.Dyrektor;
import pl.swztz.portal.Models.Student;
import pl.swztz.portal.Repositories.StudentRepository;

public class OknoElementuStudent extends OknoElementu {
	
	private Student obiekt;
	private TextField tf[];
	private ComboBox<String[]> grupy;

	public OknoElementuStudent(String nazwa, StudentRepository repo, boolean typOkna) {
		super(nazwa, repo);
		
		// inicjalizacja elementow formularza
		tf = new TextField[3];
		tf[0] = new TextField("PESEL");
		tf[1] = new TextField("Imię");
		tf[2] = new TextField("Nazwisko");
		
		grupy = new ComboBox<>("Grupa");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findGrupy()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		grupy.setItems(stringArrayList);
		grupy.setItemCaptionGenerator(x -> x[1]); // ustawia wyswietlanie nazwy instytutu w ComboBoxie
		
		form.addComponents(tf[0], tf[1], tf[2], grupy);
		
		if(typOkna)
			oknoDodawania();
		else
			oknoEdycji();
	}
	
	private void oknoDodawania() {
		ok.setCaption("Dodaj");
		
		// listener przycisku dodaj
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!tf[0].isEmpty() && !tf[1].isEmpty() && !tf[2].isEmpty() && !grupy.isEmpty()) {
					addNew();
					close();
				}
				else {
					// wyskakujace okienko z komunikatem o bledzie
					ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						public void onClose(ConfirmDialog dialog) {}
					});
					d.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	private void addNew() {
		Long idGrupy = Long.parseLong(grupy.getSelectedItem().orElse(null)[0]);
		Student s = new Student(Long.valueOf(tf[0].getValue()), tf[1].getValue(), tf[2].getValue(), idGrupy);
		repo.save(s);
		clearForm();
	}
	
	private void clearForm() {
		for (TextField t : tf)
			t.clear();
		grupy.clear();
	}
	
	private void oknoEdycji() {
		ok.setCaption("Wprowadź zmiany");
	}

	@Override
	public void setElement(Long id) {
		obiekt = ((StudentRepository) repo).findByIdStudent(id);
		loadToForm();
		
		// listener przycisku wprowadz zmiany
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!tf[0].isEmpty() && !tf[1].isEmpty() && !tf[2].isEmpty() && !grupy.isEmpty()) {
					updateObiekt(); 
					close();
				}
				else {
					ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						public void onClose(ConfirmDialog dialog) {}
					});
					d.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	private void loadToForm() {
		Object[] objectArray = ((StudentRepository)repo).findGrupa(obiekt.getIdGrupa()).get(0);
		String[] sobiekt = new String[] { ""+objectArray[0], (String)objectArray[1] };
		
		tf[0].setValue(String.valueOf(obiekt.getPESEL()));
		tf[1].setValue(obiekt.getImie());
		tf[2].setValue(obiekt.getNazwisko());
		grupy.setSelectedItem(sobiekt);
	}

	private void updateObiekt() {
		obiekt.setPESEL(Long.valueOf(tf[0].getValue()));
		obiekt.setImie(tf[1].getValue());
		obiekt.setNazwisko(tf[2].getValue());
		obiekt.setIdGrupa(Long.parseLong(grupy.getSelectedItem().orElse(null)[0]));
		repo.save(obiekt);
	}
}
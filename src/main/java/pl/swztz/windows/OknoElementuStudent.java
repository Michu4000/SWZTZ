package pl.swztz.windows;

import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.models.Student;
import pl.swztz.portal.repositories.StudentRepository;

public class OknoElementuStudent extends OknoElementu {
	
	private Student obj;
	private TextField textField[];
	private ComboBox<String[]> grupy;

	public OknoElementuStudent(String title, StudentRepository repo, boolean windowType) {
		super(title, repo);
		
		// inicjalizacja elementów formularza
		textField = new TextField[3];
		textField[0] = new TextField("PESEL");
		textField[1] = new TextField("Imię");
		textField[2] = new TextField("Nazwisko");
		
		grupy = new ComboBox<>("Grupa");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findGrupy()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		grupy.setItems(stringArrayList);
		grupy.setItemCaptionGenerator(x -> x[1]); // ustawia wyświetlanie nazwy instytutu w ComboBoxie
		
		form.addComponents(textField[0], textField[1], textField[2], grupy);
		
		if(windowType)
			oknoDodawania();
		else
			oknoEdycji();
	}
	
	private void oknoDodawania() {
		okButton.setCaption("Dodaj");
		
		// listener przycisku dodaj
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !textField[2].isEmpty() && !grupy.isEmpty()) {
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
		Long idGrupy = Long.parseLong(grupy.getSelectedItem().orElse(null)[0]);
		Student newObj = new Student(Long.valueOf(textField[0].getValue()), textField[1].getValue(), textField[2].getValue(), idGrupy);
		repo.save(newObj);
		clearForm();
	}
	
	private void clearForm() {
		for (TextField tf : textField)
			tf.clear();
		grupy.clear();
	}
	
	private void oknoEdycji() {
		okButton.setCaption("Wprowadź zmiany");
	}

	@Override
	public void setElement(Long id) {
		obj = ((StudentRepository) repo).findByIdStudent(id);
		loadToForm();
		
		// listener przycisku wprowadź zmiany
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !textField[2].isEmpty() && !grupy.isEmpty()) {
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
	
	private void loadToForm() {
		Object[] objectArray = ((StudentRepository)repo).findGrupa(obj.getIdGrupa()).get(0);
		String[] objString = new String[] { ""+objectArray[0], (String)objectArray[1] };
		
		textField[0].setValue(String.valueOf(obj.getPESEL()));
		textField[1].setValue(obj.getImie());
		textField[2].setValue(obj.getNazwisko());
		grupy.setSelectedItem(objString);
	}

	private void updateobj() {
		obj.setPESEL(Long.valueOf(textField[0].getValue()));
		obj.setImie(textField[1].getValue());
		obj.setNazwisko(textField[2].getValue());
		obj.setIdGrupa(Long.parseLong(grupy.getSelectedItem().orElse(null)[0]));
		repo.save(obj);
	}
}
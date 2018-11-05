package pl.swztz.portal.windows;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import pl.swztz.portal.models.Inspekcja;
import pl.swztz.portal.repositories.InspekcjaRepository;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class OknoElementuInspekcja extends OknoElementu {
	
	private Inspekcja obj;
	private DateField date;
	private ComboBox<String> blok;
	private ComboBox<String[]> zajecia;
	private ComboBox<String[]> dyrektor;
	private TextField textField;
	
	public OknoElementuInspekcja(String title, InspekcjaRepository repo, boolean windowType) {
		super(title, repo); // constructor of the base class
		
		// initialize elements of the form
		textField = new TextField("Komentarz");
		
		date = new DateField("Data zajęć");
		date.setDateFormat("dd-MM-yyyy");
		date.setTextFieldEnabled(false);
		
		// set listener for date field
		date.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				// get classes blocks for given date
				Date datasql = (event.getValue() == null ? null : Date.valueOf((LocalDate) event.getValue()));
				List<String> bloki = repo.findBloki(datasql);
				blok.setItems(bloki);
				
				if(!bloki.isEmpty())
					blok.setEnabled(true);
				else {
					blok.setValue("");
					blok.setEnabled(false);
				}
			}
		});
		
		// initialize comboboxes
		blok = new ComboBox<>("Nr bloku");
		blok.setEmptySelectionAllowed(false);
		blok.setEnabled(false);
		
		// set listener for class field
		blok.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				
				if(event.getValue() == "") {
					zajecia.setItems(new ArrayList<String[]>());
					zajecia.setValue( new String[] {"",""});
					zajecia.setEnabled(false);
					return;
				}
				
				// get classes for given date and block number
				List<String[]> stringArrayList = new ArrayList<>();
				
				Date datasql = (date.getValue() == null ? null : Date.valueOf((LocalDate) (date.getValue())));
				for( Object[] objectArray : repo.findZajecia(datasql, Long.parseLong(""+event.getValue())) ) {
					stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
				}

				zajecia.setItems(stringArrayList);
				zajecia.setEnabled(true);
			}
		});
		
		zajecia = new ComboBox<>("Zajęcia");
		zajecia.setItemCaptionGenerator(x -> x[1]); // set displaying name in combobox
		zajecia.setEnabled(false);
		zajecia.setWidth("100%");
		
		dyrektor = new ComboBox<>("Dyrektor Instytutu");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findDyrektorzy()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		dyrektor.setItems(stringArrayList);
		dyrektor.setItemCaptionGenerator(x -> x[1]); // set displaying name in combobox
		
		form.addComponents(date, blok, zajecia, dyrektor, textField); // add elements to form
		
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
				if(!zajecia.isEmpty() && !dyrektor.isEmpty() && !textField.isEmpty()) {
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
		Long idZajecia = Long.parseLong(zajecia.getSelectedItem().orElse(null)[0]);
		Long idDyrektor = Long.parseLong(dyrektor.getSelectedItem().orElse(null)[0]);
		Inspekcja newObj = new Inspekcja(idZajecia, idDyrektor, textField.getValue());
		repo.save(newObj);
		clearForm();
	}
	
	private void clearForm() {
		date.clear();
		blok.clear();
		zajecia.clear();
		dyrektor.clear();
		textField.clear();
	}
	
	// only for edit window
	private void editWindow() {
		okButton.setCaption("Wprowadź zmiany");
	}
	
	// for edit window: set id for edited entry
	@Override
	public void setElement(Long id) {
		obj = ((InspekcjaRepository)repo).findByIdInspekcja(id);
		loadToForm();
		
		// set listener for apply changes button
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!zajecia.isEmpty() && !dyrektor.isEmpty() && !textField.isEmpty()) {
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
		Object[] objectArray1 = ((InspekcjaRepository)repo).findZajecie(obj.getIdZajecia()).get(0);
		String[] objString1 = new String[] { ""+objectArray1[0], (String)objectArray1[3] };
		
		Object[] objectArray2 = ((InspekcjaRepository)repo).findDyrektor(obj.getIdDyrektor()).get(0);
		String[] objString2 = new String[] { ""+objectArray2[0], (String)objectArray2[1] };
		
		date.setValue( ((Date)objectArray1[1]).toLocalDate() );
		blok.setValue(""+objectArray1[2]);
		zajecia.setSelectedItem(objString1);
		dyrektor.setSelectedItem(objString2);
		textField.setValue(obj.getKomentarzInspekcja());
	}
	
	// for edit window: update entry in database
	private void updateObiekt() {
		obj.setIdZajecia(Long.parseLong(zajecia.getSelectedItem().orElse(null)[0]));
		obj.setIdDyrektor(Long.parseLong(dyrektor.getSelectedItem().orElse(null)[0]));
		obj.setKomentarzInspekcja(textField.getValue());
		repo.save(obj);
	}
}
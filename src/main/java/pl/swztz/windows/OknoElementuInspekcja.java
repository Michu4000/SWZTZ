package pl.swztz.windows;

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
		super(title, repo); // konstruktor klasy bazowej
		
		// inicjalizacja elementów formularza
		textField = new TextField("Komentarz");
		
		date = new DateField("Data zajęć");
		date.setDateFormat("dd-MM-yyyy");
		date.setTextFieldEnabled(false);
		
		// dodanie listenera do pola daty
		date.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				// pobierz bloki zajęć z danej daty
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
		
		// inicjalizacja ComboBoxów
		blok = new ComboBox<>("Nr bloku");
		blok.setEmptySelectionAllowed(false);
		blok.setEnabled(false);
		
		// dodanie listenera do pola bloku
		blok.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				
				if(event.getValue() == "") {
					zajecia.setItems(new ArrayList<String[]>());
					zajecia.setValue( new String[] {"",""});
					zajecia.setEnabled(false);
					return;
				}
				
				// pobierz zajęcia z danej daty i nr bloku
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
		zajecia.setItemCaptionGenerator(x -> x[1]); // ustawia wyświetlanie nazwy w ComboBoxie
		zajecia.setEnabled(false);
		zajecia.setWidth("100%");//
		
		dyrektor = new ComboBox<>("Dyrektor Instytutu");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findDyrektorzy()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		dyrektor.setItems(stringArrayList);
		dyrektor.setItemCaptionGenerator(x -> x[1]); // ustawia wyświetlanie nazwy w ComboBoxie
		
		form.addComponents(date, blok, zajecia, dyrektor, textField); // dodanie elementów do formularza
		
		// w zależności od tego czy to okno dodawania czy edycji
		if(windowType)
			oknoDodawania();
		else
			oknoEdycji();
	}
	
	// tylko dla okna dodawania
	private void oknoDodawania() {
		okButton.setCaption("Dodaj");
		
		// listener przycisku dodaj
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!zajecia.isEmpty() && !dyrektor.isEmpty() && !textField.isEmpty()) {
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
	
	// tylko dla okna edycji
	private void oknoEdycji() {
		okButton.setCaption("Wprowadź zmiany");
	}
	
	// dla okna edycji: ustaw id edytowanego wpisu
	@Override
	public void setElement(Long id) {
		obj = ((InspekcjaRepository)repo).findByIdInspekcja(id);
		loadToForm();
		
		// listener przycisku wprowadź zmiany
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
	
	// dla okna edycji: pobierz dane formularza
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
	
	// dla okna edycji: aktualizuj wpis w bazie danych
	private void updateObiekt() {
		obj.setIdZajecia(Long.parseLong(zajecia.getSelectedItem().orElse(null)[0]));
		obj.setIdDyrektor(Long.parseLong(dyrektor.getSelectedItem().orElse(null)[0]));
		obj.setKomentarzInspekcja(textField.getValue());
		repo.save(obj);
	}
}
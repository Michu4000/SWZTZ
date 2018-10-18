package pl.swztz.portal;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import pl.swztz.portal.Models.Inspekcja;
import pl.swztz.portal.Repositories.InspekcjaRepository;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class OknoElementuInspekcja extends OknoElementu {
	
	private Inspekcja obiekt;
	private DateField date;
	private ComboBox<String> blok;
	private ComboBox<String[]> zajecia;
	private ComboBox<String[]> dyrektor;
	private TextField tf;
	
	public OknoElementuInspekcja(String nazwa, InspekcjaRepository repo, boolean typOkna) {
		super(nazwa, repo); // konstruktor klasy bazowej
		
		// inicjalizacja elementow formularza
		tf = new TextField("Komentarz");
		
		date = new DateField("Data zajęć");
		date.setDateFormat("dd-MM-yyyy");
		date.setTextFieldEnabled(false);
		
		// dodanie listenera do pola daty
		date.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				// pobierz bloki zajec z danej daty
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
		
		// inicjalizacja ComboBoxow
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
				
				// pobierz zajecia z danej daty i nr bloku
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
		zajecia.setItemCaptionGenerator(x -> x[1]); // ustawia wyswietlanie nazwy w ComboBoxie
		zajecia.setEnabled(false);
		zajecia.setWidth("100%");//
		
		dyrektor = new ComboBox<>("Dyrektor Instytutu");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findDyrektorzy()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		dyrektor.setItems(stringArrayList);
		dyrektor.setItemCaptionGenerator(x -> x[1]); // ustawia wyswietlanie nazwy w ComboBoxie
		
		form.addComponents(date, blok, zajecia, dyrektor, tf); // dodanie elementow do formularza
		
		// w zaleznosci od tego czy to okno dodawania czy edycji
		if(typOkna)
			oknoDodawania();
		else
			oknoEdycji();
	}
	
	// tylko dla okna dodawania
	private void oknoDodawania() {
		ok.setCaption("Dodaj");
		
		// listener przycisku dodaj
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!zajecia.isEmpty() && !dyrektor.isEmpty() && !tf.isEmpty()) {
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
		Long idZajecia = Long.parseLong(zajecia.getSelectedItem().orElse(null)[0]);
		Long idDyrektor = Long.parseLong(dyrektor.getSelectedItem().orElse(null)[0]);
		Inspekcja i = new Inspekcja(idZajecia, idDyrektor, tf.getValue());
		repo.save(i);
		clearForm();
	}
	
	private void clearForm() {
		date.clear();
		blok.clear();
		zajecia.clear();
		dyrektor.clear();
		tf.clear();
	}
	
	// tylko dla okna edycji
	private void oknoEdycji() {
		ok.setCaption("Wprowadź zmiany");
	}
	
	// dla okna edycji: ustaw id edytowanego wpisu
	@Override
	public void setElement(Long id) {
		obiekt = ((InspekcjaRepository)repo).findByIdInspekcja(id);
		loadToForm();
		
		// listener przycisku wprowadz zmiany
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!zajecia.isEmpty() && !dyrektor.isEmpty() && !tf.isEmpty()) {
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
	
	// dla okna edycji: pobierz dane formularza
	private void loadToForm() {
		Object[] objectArray1 = ((InspekcjaRepository)repo).findZajecie(obiekt.getIdZajecia()).get(0);
		String[] sobiekt1 = new String[] { ""+objectArray1[0], (String)objectArray1[3] };
		
		Object[] objectArray2 = ((InspekcjaRepository)repo).findDyrektor(obiekt.getIdDyrektor()).get(0);
		String[] sobiekt2 = new String[] { ""+objectArray2[0], (String)objectArray2[1] };
		
		date.setValue( ((Date)objectArray1[1]).toLocalDate() );
		blok.setValue(""+objectArray1[2]);
		zajecia.setSelectedItem(sobiekt1);
		dyrektor.setSelectedItem(sobiekt2);
		tf.setValue(obiekt.getKomentarzInspekcja());
	}
	
	// dla okna edycji: aktualizuj wpis w bazie danych
	private void updateObiekt() {
		obiekt.setIdZajecia(Long.parseLong(zajecia.getSelectedItem().orElse(null)[0]));
		obiekt.setIdDyrektor(Long.parseLong(dyrektor.getSelectedItem().orElse(null)[0]));
		obiekt.setKomentarzInspekcja(tf.getValue());
		repo.save(obiekt);
	}
}
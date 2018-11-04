package pl.swztz.windows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.shared.ui.datefield.DateTimeResolution;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import pl.swztz.portal.models.Wiadomosc;
import pl.swztz.portal.repositories.WiadomoscRepository;

public class OknoElementuWiadomosc extends OknoElementu {

	private Wiadomosc obiekt;
	private TextField tf[];
	private TextArea tresc;
	private ComboBox<String[]> nadawca;
	private ComboBox<String[]> odbiorca;
	private DateTimeField date;
	
	public OknoElementuWiadomosc(String nazwa, WiadomoscRepository repo, boolean typOkna) {
		super(nazwa, repo); // konstruktor klasy bazowej
		
		// inicjalizacja elementow formularza
		tf = new TextField[2];
		tf[0] = new TextField("Temat");
		tf[1] = new TextField("Nadawca");
		
		tresc = new TextArea("Treść");
				
		date = new DateTimeField("Data wysłania");
		date.setDateFormat("dd-MM-yyyy HH:mm:ss");
		date.setResolution(DateTimeResolution.SECOND);
		date.setEnabled(false);
				
		// inicjalizacja i pobranie danych do ComboBoxa (musialem przepisac jedna liste do drugiej)
		odbiorca = new ComboBox<>("Odbiorca");
		
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findUzytkownicy()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		odbiorca.setItems(stringArrayList);
		odbiorca.setItemCaptionGenerator(x -> x[1]); // ustawia wyswietlanie nazwy uzytkownika w ComboBoxie
		
		form.addComponents(tf[1], odbiorca, tf[0], tresc); // dodanie elementow do formularza
		
		// w zaleznosci od tego czy to okno dodawania czy edycji
		if(typOkna)
			oknoDodawania();
		else
			oknoEdycji();
	}
	
	public void oknoDodawania() {
		ok.setCaption("Wyślij");
		
		// listener przycisku dodaj
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!tf[0].isEmpty() && !tf[1].isEmpty() && !tresc.isEmpty() && !odbiorca.isEmpty()) {
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
		Long idOdbiorca = Long.parseLong(odbiorca.getSelectedItem().orElse(null)[0]);
		Wiadomosc w = new Wiadomosc(Long.parseLong(tf[1].getValue()), idOdbiorca, tf[0].getValue(), tresc.getValue(), LocalDateTime.now());
		repo.save(w);
		clearForm();
	}
	
	private void clearForm() {
		for (TextField t : tf)
			t.clear();
		odbiorca.clear();
		tresc.clear();
	}
	
	public void oknoEdycji() {
		ok.setVisible(false);
		anuluj.setCaption("Zamknij");
		
		tf[0].setEnabled(false);
		tf[1].setEnabled(false);
		tresc.setEnabled(false);
		odbiorca.setEnabled(false);
		form.addComponent(date);
	}
	
	@Override
	public void setElement(Long id) {
		obiekt = ((WiadomoscRepository)repo).findByIdWiadomosc(id);
		loadToForm();
	}
	
	private void loadToForm() {
		tf[0].setValue(obiekt.getTemat());
		
		Object[] ob = ((WiadomoscRepository) repo).findUzytkownik(obiekt.getIdNadawca()).get(0);
		tf[1].setValue((String)ob[1]);
		
		tresc.setValue(obiekt.getTresc());
		date.setValue(obiekt.getDataWyslania());
		
		Object[] objectArray = ((WiadomoscRepository) repo).findUzytkownik(obiekt.getIdOdbiorca()).get(0);
		String[] sobiekt = new String[] { ""+objectArray[0], (String)objectArray[1] };
		odbiorca.setSelectedItem(sobiekt);
	}
}
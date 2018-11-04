package pl.swztz.windows;

import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;

import pl.swztz.portal.models.Konsultacja;
import pl.swztz.portal.repositories.KonsultacjaRepository;

public class OknoElementuKonsultacja extends OknoElementu {

	private Konsultacja obiekt;
	private TextField tf[];
	private DateField date;
	private ComboBox<String[]> sala;
	private ComboBox<Long> bloki;
	
	public OknoElementuKonsultacja(String nazwa, KonsultacjaRepository repo, boolean typOkna) {
		super(nazwa, repo);
		tf = new TextField[2];
		tf[0] = new TextField("Prowadzący");
		tf[1] = new TextField("Komentarz");
		
		bloki = new ComboBox<>("Numer Bloku");
		bloki.setItems(1L, 2L, 3L, 4L, 5L, 6L, 7L);
		
		date = new DateField("Data konsultacji");
		date.setDateFormat("dd-MM-yyyy");
		date.setTextFieldEnabled(false);
		
		sala = new ComboBox<>("Sala");
		List<String[]> stringArrayList = new ArrayList<>();
		
		for(Object [] objectArray :  repo.findSale()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}
		
		sala.setItems(stringArrayList);
		sala.setItemCaptionGenerator(x -> x[1]);
		
		form.addComponents(date, bloki, sala, tf[0], tf[1]); // dodanie elementow do formularza
		
		// w zaleznosci od tego czy to okno dodawania czy edycji
		if(typOkna)
			oknoDodawania();
		else
			oknoEdycji();
	}

	private void oknoEdycji() {
		ok.setCaption("Wprowadź zmiany");
	}

	private void oknoDodawania() {
		ok.setCaption("Dodaj");
		// listener przycisku dodaj
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!date.isEmpty() && !bloki.isEmpty() && !tf[0].isEmpty()) {
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

	protected void addNew() {
		Konsultacja k;
		if(sala.getSelectedItem().orElse(null) != null)
			k = new Konsultacja(date.getValue(), bloki.getValue(), Long.parseLong(sala.getSelectedItem().orElse(null)[0]), Long.parseLong(tf[0].getValue()), tf[1].getValue());
		else
			k = new Konsultacja(date.getValue(), bloki.getValue(), null, Long.parseLong(tf[0].getValue()), tf[1].getValue());
		repo.save(k);
		clearform();
	}

	private void clearform() {
		for (TextField t : tf)
			t.clear();
		date.clear();
		bloki.clear();
		sala.clear();
	}

	@Override
	public void setElement(Long id) {
		obiekt = ((KonsultacjaRepository)repo).findByIdKonsultacja(id);
		loadToForm();
		
		// listener przycisku wprowadz zmiany
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!date.isEmpty() && !bloki.isEmpty() && !tf[0].isEmpty()) {
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
		if(obiekt.getIdSala() != null) {
			Object[] objectArray = ((KonsultacjaRepository) repo).findSala(obiekt.getIdSala()).get(0);
			String[] sobiekt = new String[] { ""+objectArray[0], (String)objectArray[1] };
			sala.setSelectedItem(sobiekt);
		}
		else
			sala.clear();
		
		date.setValue(obiekt.getData());
		bloki.setValue(obiekt.getNrBloku());
		tf[0].setValue(((KonsultacjaRepository) repo).findProwadzacy(obiekt.getIdKonsultacja()));
		
		if(obiekt.getKomentarz() != null)
			tf[1].setValue(obiekt.getKomentarz());
		else
			tf[1].setValue("");
	}
	
	protected void updateObiekt() {
		obiekt.setData(date.getValue());
		obiekt.setNrBloku(bloki.getValue());
		
		String[] ss = sala.getSelectedItem().orElse(null);
		if(ss != null)
			obiekt.setIdSala(Long.parseLong(ss[0]));
		else
			obiekt.setIdSala(null);
		
		obiekt.setProwadzacy(((KonsultacjaRepository)repo).findIdProwadzacy(tf[0].getValue()));
		
		if(!tf[1].isEmpty())
			obiekt.setKomentarz(tf[1].getValue());
		else
			obiekt.setKomentarz("");

		repo.save(obiekt);
	}
}
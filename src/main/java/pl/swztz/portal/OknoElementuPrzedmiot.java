package pl.swztz.portal;

import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.Models.Przedmiot;
import pl.swztz.portal.Repositories.PrzedmiotRepository;

public class OknoElementuPrzedmiot extends OknoElementu {

	private Przedmiot obiekt;
	private TextField tf;
	private ComboBox<String[]> instytut;
	
	public OknoElementuPrzedmiot(String nazwa, PrzedmiotRepository repo, boolean typOkna) {
		super(nazwa, repo);
		
		tf = new TextField("Nazwa przedmiotu");
		
		instytut = new ComboBox<>("Instytut");
		List<String[]> stringArrayList = new ArrayList<>();
		
		for(Object[] objectArray : repo.findInstytuty()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}
		
		instytut.setItems(stringArrayList);
		instytut.setItemCaptionGenerator(x -> x[1]);
		form.addComponents(tf, instytut);
		
		if(typOkna)
			oknoDodawania();
		else
			oknoEdycji();
	}

	private void oknoDodawania()
	{
		ok.setCaption("Dodaj");
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!tf.isEmpty() && !instytut.isEmpty())
				{
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
		Long idInstytut = Long.parseLong(instytut.getSelectedItem().orElse(null)[0]);
		Przedmiot p = new Przedmiot(tf.getValue(), idInstytut);
		repo.save(p);
		clearForm();
	}
	
	private void clearForm() {
		tf.clear();
		instytut.clear();
	}
	
	private void oknoEdycji() {
		ok.setCaption("Wprowadź zmiany");
	}
	
	@Override
	public void setElement(Long id) {
		obiekt = ((PrzedmiotRepository)repo).findByIdPrzedmiot(id);
		loadToForm();
		
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!tf.isEmpty() && !instytut.isEmpty()) {
					updateObiekt();
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
	
	private void loadToForm() {
		Object[] objectArray = ((PrzedmiotRepository)repo).findInstytut(obiekt.getIdInstytut()).get(0);
		String [] sobiekt = new String[] { ""+objectArray[0], (String)objectArray[1] };
		
		tf.setValue(obiekt.getNazwaPrzedmiot());
		instytut.setSelectedItem(sobiekt);
	}
	
	private void updateObiekt() {
		obiekt.setNazwaPrzedmiot(tf.getValue());
		obiekt.setIdInstytut(Long.parseLong(instytut.getSelectedItem().orElse(null)[0]));
		repo.save(obiekt);
	}
}
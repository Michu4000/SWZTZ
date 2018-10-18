package pl.swztz.portal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import pl.swztz.portal.Models.Grupa;
import pl.swztz.portal.Repositories.GrupaRepository;

public class OknoElementuGrupa extends OknoElementu {

	private Grupa obiekt;
	private TextField tf[];
	private ComboBox<String[]> starosta;
	
	public OknoElementuGrupa(String nazwa, JpaRepository repo, boolean typOkna) {
		super(nazwa, repo);
		
		//inicjalizacja elementow formularza
		tf = new TextField[3];
		tf[0] = new TextField("Nazwa");
		tf[1] = new TextField("Kierunek");
		tf[2] = new TextField("Rok");
			
		form.addComponents(tf[0], tf[1], tf[2]);
		
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
				if(!tf[0].isEmpty() && !tf[1].isEmpty() && !tf[2].isEmpty()) {
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
		Grupa g = new Grupa(tf[0].getValue(), tf[1].getValue(), Long.parseLong(tf[2].getValue()), null);
		repo.save(g);
		clearForm();
	}
	
	private void clearForm() {
		for (TextField t : tf)
			t.clear();
	}
	
	private void oknoEdycji() {
		ok.setCaption("Wprowadź zmiany");
		
		starosta = new ComboBox<>("Starosta");
		form.addComponents(starosta);
	}
	
	@Override
	public void setElement(Long id) {
		obiekt = ((GrupaRepository)repo).findByIdGrupa(id);
		loadToForm();
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!tf[0].isEmpty() && !tf[1].isEmpty() && !tf[2].isEmpty()) {
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
		StringBuilder sb = new StringBuilder();
		sb.append(obiekt.getRok());
		
		tf[0].setValue(obiekt.getNazwa());
		tf[1].setValue(obiekt.getKierunek());
		tf[2].setValue(sb.toString());
		
		List<String[]> stringArrayList = new ArrayList<>();
		
		for(Object[] objectArray : ((GrupaRepository)repo).findStudenci(obiekt.getId())) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String)objectArray[1] });
		}
		
		starosta.setItems(stringArrayList);
		starosta.setItemCaptionGenerator(x -> x[1]);
		
		if(obiekt.getIdStarosta() != null) {
			Object[] objectArray = ((GrupaRepository) repo).findStudent(obiekt.getIdStarosta()).get(0);
			String[] sobiekt = new String[] { ""+objectArray[0], (String)objectArray[1] };
			starosta.setSelectedItem(sobiekt);
		}
		else
			starosta.setSelectedItem(new String[] { "", "" });
	}
	
	private void updateObiekt()	{
		obiekt.setNazwa(tf[0].getValue());
		obiekt.setKierunek(tf[1].getValue());
		obiekt.setRok(Long.parseLong(tf[2].getValue()));
		
		String[] ss = starosta.getSelectedItem().orElse(null);
		if(ss != null)
			obiekt.setIdStarosta(Long.parseLong(ss[0]));
		else
			obiekt.setIdStarosta(null);
		
		repo.save(obiekt);
	}
}
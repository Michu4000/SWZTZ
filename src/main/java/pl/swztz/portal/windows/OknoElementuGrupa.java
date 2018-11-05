package pl.swztz.portal.windows;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import pl.swztz.portal.models.Grupa;
import pl.swztz.portal.repositories.GrupaRepository;

public class OknoElementuGrupa extends OknoElementu {

	private Grupa obj;
	private TextField textField[];
	private ComboBox<String[]> starosta;
	
	public OknoElementuGrupa(String title, JpaRepository repo, boolean windowType) {
		super(title, repo);
		
		// inicjalizacja elementów formularza
		textField = new TextField[3];
		textField[0] = new TextField("Nazwa");
		textField[1] = new TextField("Kierunek");
		textField[2] = new TextField("Rok");
			
		form.addComponents(textField[0], textField[1], textField[2]);
		
		if(windowType)
			oknoDodawania();
		else
			oknoEdycji();
	}
	
	private void oknoDodawania() {
		okButton.setCaption("Dodaj");
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !textField[2].isEmpty()) {
					addNew();
					close();
				}
				else {
					ConfirmDialog dialog = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						@Override
						public void onClose(ConfirmDialog arg0) {}
					});
					dialog.getCancelButton().setVisible(false);
				}
			}
		});
	}

	private void addNew() {
		Grupa newObj = new Grupa(textField[0].getValue(), textField[1].getValue(), Long.parseLong(textField[2].getValue()), null);
		repo.save(newObj);
		clearForm();
	}
	
	private void clearForm() {
		for (TextField tf : textField)
			tf.clear();
	}
	
	private void oknoEdycji() {
		okButton.setCaption("Wprowadź zmiany");
		
		starosta = new ComboBox<>("Starosta");
		form.addComponents(starosta);
	}
	
	@Override
	public void setElement(Long id) {
		obj = ((GrupaRepository)repo).findByIdGrupa(id);
		loadToForm();
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !textField[2].isEmpty()) {
					updateObiekt();
					close();
				}
				else {
					ConfirmDialog dialog = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Wypełnij wszystkie pola", "OK", "", new ConfirmDialog.Listener() {
						@Override
						public void onClose(ConfirmDialog arg0) {}
					});
					dialog.getCancelButton().setVisible(false);
				}
			}
		});
	}
	
	private void loadToForm() {
		StringBuilder stringBuild = new StringBuilder();
		stringBuild.append(obj.getRok());
		
		textField[0].setValue(obj.getNazwa());
		textField[1].setValue(obj.getKierunek());
		textField[2].setValue(stringBuild.toString());
		
		List<String[]> stringArrayList = new ArrayList<>();
		
		for(Object[] objectArray : ((GrupaRepository)repo).findStudenci(obj.getId())) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String)objectArray[1] });
		}
		
		starosta.setItems(stringArrayList);
		starosta.setItemCaptionGenerator(x -> x[1]);
		
		if(obj.getIdStarosta() != null) {
			Object[] objectArray = ((GrupaRepository) repo).findStudent(obj.getIdStarosta()).get(0);
			String[] objString = new String[] { ""+objectArray[0], (String)objectArray[1] };
			starosta.setSelectedItem(objString);
		}
		else
			starosta.setSelectedItem(new String[] { "", "" });
	}
	
	private void updateObiekt()	{
		obj.setNazwa(textField[0].getValue());
		obj.setKierunek(textField[1].getValue());
		obj.setRok(Long.parseLong(textField[2].getValue()));
		
		String[] ss = starosta.getSelectedItem().orElse(null);
		if(ss != null)
			obj.setIdStarosta(Long.parseLong(ss[0]));
		else
			obj.setIdStarosta(null);
		
		repo.save(obj);
	}
}
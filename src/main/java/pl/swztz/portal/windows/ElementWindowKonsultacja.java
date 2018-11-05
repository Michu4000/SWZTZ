package pl.swztz.portal.windows;

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

public class ElementWindowKonsultacja extends ElementWindow {

	private Konsultacja obj;
	private TextField textField[];
	private DateField date;
	private ComboBox<String[]> sala;
	private ComboBox<Long> bloki;
	
	public ElementWindowKonsultacja(String title, KonsultacjaRepository repo, boolean windowType) {
		super(title, repo);
		textField = new TextField[2];
		textField[0] = new TextField("Prowadzący");
		textField[1] = new TextField("Komentarz");
		
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
		
		form.addComponents(date, bloki, sala, textField[0], textField[1]); // add elements to form
		
		// depending on whether it's a add window or edit window
		if(windowType)
			addWindow();
		else
			editWindow();
	}

	private void editWindow() {
		okButton.setCaption("Wprowadź zmiany");
	}

	private void addWindow() {
		okButton.setCaption("Dodaj");
		// set listener for add button
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!date.isEmpty() && !bloki.isEmpty() && !textField[0].isEmpty()) {
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

	protected void addNew() {
		Konsultacja newObj;
		if(sala.getSelectedItem().orElse(null) != null)
			newObj = new Konsultacja(date.getValue(), bloki.getValue(), Long.parseLong(sala.getSelectedItem().orElse(null)[0]), Long.parseLong(textField[0].getValue()), textField[1].getValue());
		else
			newObj = new Konsultacja(date.getValue(), bloki.getValue(), null, Long.parseLong(textField[0].getValue()), textField[1].getValue());
		repo.save(newObj);
		clearform();
	}

	private void clearform() {
		for (TextField tf : textField)
			tf.clear();
		date.clear();
		bloki.clear();
		sala.clear();
	}

	@Override
	public void setElement(Long id) {
		obj = ((KonsultacjaRepository)repo).findByIdKonsultacja(id);
		loadToForm();
		
		// set listener for apply changes button
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!date.isEmpty() && !bloki.isEmpty() && !textField[0].isEmpty()) {
					updateobj(); 
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
		if(obj.getIdSala() != null) {
			Object[] objectArray = ((KonsultacjaRepository) repo).findSala(obj.getIdSala()).get(0);
			String[] objString = new String[] { ""+objectArray[0], (String)objectArray[1] };
			sala.setSelectedItem(objString);
		}
		else
			sala.clear();
		
		date.setValue(obj.getData());
		bloki.setValue(obj.getNrBloku());
		textField[0].setValue(((KonsultacjaRepository) repo).findProwadzacy(obj.getIdKonsultacja()));
		
		if(obj.getKomentarz() != null)
			textField[1].setValue(obj.getKomentarz());
		else
			textField[1].setValue("");
	}
	
	protected void updateobj() {
		obj.setData(date.getValue());
		obj.setNrBloku(bloki.getValue());
		
		String[] ss = sala.getSelectedItem().orElse(null);
		if(ss != null)
			obj.setIdSala(Long.parseLong(ss[0]));
		else
			obj.setIdSala(null);
		
		obj.setProwadzacy(((KonsultacjaRepository)repo).findIdProwadzacy(textField[0].getValue()));
		
		if(!textField[1].isEmpty())
			obj.setKomentarz(textField[1].getValue());
		else
			obj.setKomentarz("");

		repo.save(obj);
	}
}
package pl.swztz.portal.windows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.shared.ui.datefield.DateTimeResolution;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import pl.swztz.portal.models.Wiadomosc;
import pl.swztz.portal.repositories.WiadomoscRepository;

public class OknoElementuWiadomosc extends OknoElementu {

	private Wiadomosc obj;
	private TextField textField[];
	private TextArea desc;
	private ComboBox<String[]> sender;
	private ComboBox<String[]> recipient;
	private DateTimeField date;
	
	public OknoElementuWiadomosc(String title, WiadomoscRepository repo, boolean windowType) {
		super(title, repo); // constructor of the base class
		
		// initialize elements of the form
		textField = new TextField[2];
		textField[0] = new TextField("Temat");
		textField[1] = new TextField("Nadawca");
		
		desc = new TextArea("Treść");
				
		date = new DateTimeField("Data wysłania");
		date.setDateFormat("dd-MM-yyyy HH:mm:ss");
		date.setResolution(DateTimeResolution.SECOND);
		date.setEnabled(false);
				
		// initialize combobox and get data
		recipient = new ComboBox<>("Odbiorca");
		
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object[] objectArray : repo.findUzytkownicy()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}

		recipient.setItems(stringArrayList);
		recipient.setItemCaptionGenerator(x -> x[1]); // set displaying user name in combobox
		
		form.addComponents(textField[1], recipient, textField[0], desc); // add elements to form
		
		// depending on whether it's a add window or edit window
		if(windowType)
			addWindow();
		else
			editWindow();
	}
	
	public void addWindow() {
		okButton.setCaption("Wyślij");
		
		// set listener for add button
		okButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!textField[0].isEmpty() && !textField[1].isEmpty() && !desc.isEmpty() && !recipient.isEmpty()) {
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
		Long idrecipient = Long.parseLong(recipient.getSelectedItem().orElse(null)[0]);
		Wiadomosc newObj = new Wiadomosc(Long.parseLong(textField[1].getValue()), idrecipient, textField[0].getValue(), desc.getValue(), LocalDateTime.now());
		repo.save(newObj);
		clearForm();
	}
	
	private void clearForm() {
		for (TextField tf : textField)
			tf.clear();
		recipient.clear();
		desc.clear();
	}
	
	public void editWindow() {
		okButton.setVisible(false);
		cancelButton.setCaption("Zamknij");
		
		textField[0].setEnabled(false);
		textField[1].setEnabled(false);
		desc.setEnabled(false);
		recipient.setEnabled(false);
		form.addComponent(date);
	}
	
	@Override
	public void setElement(Long id) {
		obj = ((WiadomoscRepository)repo).findByIdWiadomosc(id);
		loadToForm();
	}
	
	private void loadToForm() {
		textField[0].setValue(obj.getTemat());
		
		Object[] ob = ((WiadomoscRepository) repo).findUzytkownik(obj.getIdNadawca()).get(0);
		textField[1].setValue((String)ob[1]);
		
		desc.setValue(obj.getTresc());
		date.setValue(obj.getDataWyslania());
		
		Object[] objectArray = ((WiadomoscRepository) repo).findUzytkownik(obj.getIdOdbiorca()).get(0);
		String[] objString = new String[] { ""+objectArray[0], (String)objectArray[1] };
		recipient.setSelectedItem(objString);
	}
}
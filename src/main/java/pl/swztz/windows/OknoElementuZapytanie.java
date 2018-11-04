package pl.swztz.windows;

import java.util.ArrayList;
import java.util.List;
import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
import org.vaadin.dialogs.ConfirmDialog;
//import pl.swztz.portal.PortalApplication;
import pl.swztz.portal.models.Zapytanie;
import pl.swztz.portal.repositories.ZapytanieRepository;
//import pl.swztz.utils.EmailSender;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class OknoElementuZapytanie extends OknoElementu {
	
	private Zapytanie obiekt;
	private TextField tf[];
	private CheckBox checkbox;
	private ComboBox<String[]> prowadzacy;
	//private EmailSender mail;
	private String parentWindowTitle;
	private TemplateEngine templateEngine;

	public OknoElementuZapytanie(String nazwa, ZapytanieRepository repo, boolean typOkna, String parentWindowTitle) {
		super(nazwa, repo);
		
		tf = new TextField[2];
		tf[0] = new TextField("Id studenta");
		tf[1] = new TextField("Treść zapytania");
		
		this.parentWindowTitle=parentWindowTitle;
		
		checkbox = new CheckBox("Decyzja");
		
		prowadzacy = new ComboBox<>("Prowadzący");
		List<String[]> stringArrayList = new ArrayList<>();

		for(Object [] objectArray :  repo.findProwadzacych()) {
			stringArrayList.add(new String[] { ""+objectArray[0], (String) objectArray[1] });
		}
		
		prowadzacy.setItems(stringArrayList);
		prowadzacy.setItemCaptionGenerator(x -> x[1]);
		
		form.addComponents(tf[0], tf[1], prowadzacy, checkbox);
		
		//mail = PortalApplication.getApplicationContext().getBean(EmailSender.class);
		//templateEngine = PortalApplication.getApplicationContext().getBean(TemplateEngine.class);
		
		if(typOkna)
			oknoDodawania();
		else
			oknoEdycji();
	}

	private void oknoEdycji() {

	}

	private void oknoDodawania() {
		ok.setCaption("Dodaj");
		
		// listener przycisku dodaj
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!tf[0].isEmpty() && !tf[1].isEmpty() && !prowadzacy.isEmpty()) {
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
		Long idProwadzacy = Long.parseLong(prowadzacy.getSelectedItem().orElse(null)[0]);
		Zapytanie z = new Zapytanie(Long.parseLong(tf[0].getValue()), idProwadzacy, tf[1].getValue(), checkbox.getValue());
		repo.save(z);
		
		/*
		String adres = ((ZapytanieRepository) repo).findEmailProwadzacy(idProwadzacy);
		String nadawca = ((ZapytanieRepository) repo).findStudent(Long.parseLong(tf[0].getValue()));
		
		Context context = new Context();
        context.setVariable("header", "Nowy powiadomienie na SWZTZ");
        context.setVariable("title", "Otrzymałeś zapytanie w sekcji "+parentWindowTitle);
        context.setVariable("description", "Treść zapytania: "+tf[1].getValue() + " od użytkownika "+nadawca+".");

        String body = templateEngine.process("template", context);
		
		mail.sendEmail(adres, "Portal SWZTZ", body);
		*/
		clearForm();
	}

	private void clearForm() {
		tf[0].clear();
		tf[1].clear();
		checkbox.clear();
		prowadzacy.clear();
	}

	@Override
	public void setElement(Long id) {
		obiekt = ((ZapytanieRepository)repo).findByIdZapytanie(id);
		loadToForm();
		
		// listener przycisku wprowadz zmiany
		ok.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(!tf[0].isEmpty() && !tf[1].isEmpty() && !prowadzacy.isEmpty()) {
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

	private void updateObiekt() {
		obiekt.setIdStudent(((ZapytanieRepository)repo).findStudentID(tf[0].getValue()));
		obiekt.setTrescZapytania((tf[1].getValue()));
		obiekt.setDecyzjaProwadzacego((checkbox.getValue()));
		obiekt.setIdProwadzacy(Long.parseLong(prowadzacy.getSelectedItem().orElse(null)[0]));
		repo.save(obiekt);
	}

	private void loadToForm() {
		Object[] objectArray = ((ZapytanieRepository)repo).findProwadzacy(obiekt.getIdProwadzacy()).get(0);
		String[] sobiekt = new String[] { ""+objectArray[0], (String)objectArray[1] };
		
		tf[0].setValue(((ZapytanieRepository)repo).findStudent(obiekt.getIdStudent()));
		tf[1].setValue(obiekt.getTrescZapytania());
		checkbox.setValue(obiekt.getDecyzjaProwadzacego());
		prowadzacy.setSelectedItem(sobiekt);
	}
}
package pl.swztz.portal;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Klasa abstrakcyjna, po ktorej dziedzicza klasy operacji na formularzach
 * @author SWZTZ Team
 *
 */
public abstract class OknoElementu extends Window {
	
	protected JpaRepository repo;
	private VerticalLayout layout = new VerticalLayout();
	protected FormLayout form = new FormLayout();
	private HorizontalLayout hlayout = new HorizontalLayout();
	protected Button ok = new Button("OK");
	protected Button anuluj = new Button("Anuluj");
	
	/**
	 * Konstruktor klasy Okno Elementu
	 * @param nazwa Nazwa - tytul okna
	 * @param repo Repozytorium do tabel
	 */
	public OknoElementu(String nazwa, JpaRepository repo) {
		super(nazwa); // nazwa okienka
		this.repo = repo;
	
		hlayout.addComponents(ok, anuluj);
		layout.addComponents(form, hlayout);
		
		// listener przycisku anuluj
		anuluj.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				close();
			}
		});

		setWidth("50%");
		//setHeight("60%");
		
		setResizable(false); // zablokowanie zmiany rozmiaru okienka
		setDraggable(true); // zablokowanie przesuwania okienka
		setContent(layout); // ustaw glowny layout okienka
	}
	
	public abstract void setElement(Long id);
}
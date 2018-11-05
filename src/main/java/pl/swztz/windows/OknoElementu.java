package pl.swztz.windows;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public abstract class OknoElementu extends Window {
	
	protected JpaRepository repo;
	private VerticalLayout layout = new VerticalLayout();
	protected FormLayout form = new FormLayout();
	private HorizontalLayout hlayout = new HorizontalLayout();
	protected Button okButton = new Button("OK");
	protected Button cancelButton = new Button("Anuluj");
	
	public OknoElementu(String title, JpaRepository repo) {
		super(title); // nazwa okienka
		this.repo = repo;
	
		hlayout.addComponents(okButton, cancelButton);
		layout.addComponents(form, hlayout);
		
		// listener przycisku anuluj
		cancelButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				close();
			}
		});

		setWidth("50%");
		
		setResizable(false); // zablokowanie zmiany rozmiaru okienka
		setDraggable(true); // zablokowanie przesuwania okienka
		setContent(layout); // ustaw główny layout okienka
	}
	
	public abstract void setElement(Long id);
}
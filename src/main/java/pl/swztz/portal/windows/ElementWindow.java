package pl.swztz.portal.windows;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public abstract class ElementWindow extends Window {
	
	protected JpaRepository repo;
	private VerticalLayout layout = new VerticalLayout();
	protected FormLayout form = new FormLayout();
	private HorizontalLayout hlayout = new HorizontalLayout();
	protected Button okButton = new Button("OK");
	protected Button cancelButton = new Button("Anuluj");
	
	public ElementWindow(String title, JpaRepository repo) {
		super(title); // window title
		this.repo = repo;
	
		hlayout.addComponents(okButton, cancelButton);
		layout.addComponents(form, hlayout);
		
		// set listener for cancel button
		cancelButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				close();
			}
		});

		setWidth("50%");
		
		setResizable(false); // block window size change
		setDraggable(true); // allow window moving
		setContent(layout); // set main window layout
	}
	
	public abstract void setElement(Long id);
}
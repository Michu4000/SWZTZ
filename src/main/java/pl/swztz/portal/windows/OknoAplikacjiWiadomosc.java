package pl.swztz.portal.windows;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaadin.ui.Grid.SelectionMode;

public class OknoAplikacjiWiadomosc<T, S> extends OknoAplikacji {

	public OknoAplikacjiWiadomosc(String title, JpaRepository viewRepo, JpaRepository tableRepo, Class viewTyp,
			Class tableTyp, OknoElementu addWindow, OknoElementu editWindow) {
		
		super(title, viewRepo, tableRepo, viewTyp, tableTyp, addWindow, editWindow);
		deleteButton.setVisible(false);
		addButton.setCaption("Nowa wiadomość");
		editButton.setCaption("Podgląd");
		
		grid.setSelectionMode(SelectionMode.SINGLE); // signle-select mode for grid
	}
}

package pl.swztz.portal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaadin.ui.Grid.SelectionMode;

public class OknoAplikacjiWiadomosc<T, S> extends OknoAplikacji {

	public OknoAplikacjiWiadomosc(String nazwa, JpaRepository viewRepo, JpaRepository tableRepo, Class viewTyp,
			Class tableTyp, OknoElementu oknoDodawania, OknoElementu oknoEdycji) {
		
		super(nazwa, viewRepo, tableRepo, viewTyp, tableTyp, oknoDodawania, oknoEdycji);
		usun.setVisible(false);
		dodaj.setCaption("Nowa wiadomość");
		edytuj.setCaption("Podgląd");
		
		tabela.setSelectionMode(SelectionMode.SINGLE); // tryb zaznaczania jednego rekordu
	}
}

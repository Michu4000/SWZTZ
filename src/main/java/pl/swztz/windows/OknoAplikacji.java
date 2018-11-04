package pl.swztz.windows;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Klasa glowna okna aplikacji
 * @author SWZTZ Team
 *
 */
public class OknoAplikacji<T,S> extends Window {
	
	private VerticalLayout layout = new VerticalLayout(); // Główny layout okienka
	private HorizontalLayout hlayout = new HorizontalLayout(); // Layout dla przyciskow
	protected Grid<T> tabela;
	protected Button edytuj = new Button("Edytuj");
	protected Button dodaj = new Button("Dodaj");
	protected Button usun = new Button("Usuń");
	
	/**
	 * Konstruktor glownego okna formularza
	 * 
	 * @param nazwa Naglowek - tytul okna
	 * @param repo Repozytorium do danej tabeli
	 * @param typ Typ obiektu
	 */
	public OknoAplikacji(String nazwa, JpaRepository viewRepo, JpaRepository tableRepo, Class<T> viewTyp, Class<S> tableTyp, OknoElementu oknoDodawania, OknoElementu oknoEdycji) {
		super(nazwa); // nazwa okienka
		this.tabela = new Grid<>(viewTyp);
		
		/**
		 * Ustawienie wlasciwej kolejnosci kolumn
		 */
		try {
			Method getFieldNames = viewTyp.getMethod("getFieldNames");
			String[] s = (String[]) getFieldNames.invoke(null);
			tabela.setColumnOrder(s);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		tabela.setSelectionMode(SelectionMode.MULTI); // tryb zaznaczania wielu rekordow
			
		// usuniecie niepotrzebnej kolumny id
		tabela.removeColumn(tabela.getColumn("id"));
						
		// ustawienie min/max szerokosci kolumn
		/*tabela.getColumn("nazwa").setMinimumWidth(90);
		tabela.getColumn("nazwa").setMaximumWidth(90);*/
		
		hlayout.addComponents(dodaj, edytuj, usun);
		layout.addComponents(tabela, hlayout);
		
		/**
		 * Ustawienie listenera przycisku Dodaj
		 */
		dodaj.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				OknoElementu o = oknoDodawania;
				o.addCloseListener(new CloseListener() {
		            public void windowClose(CloseEvent e) {
		            	tabela.setItems(viewRepo.findAll()); // odswiez widok
		            }
		        });
				o.setModal(true);
				o.center();
				UI.getCurrent().addWindow(o);
			}
		});
		
		/**
		 * Ustawienie listenera przycisku Edytuj
		 */
		edytuj.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Set<T> doEdycji = tabela.getSelectedItems();
				
				if(doEdycji.size() != 1) {
					ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Wybierz jeden element", "OK", "", new ConfirmDialog.Listener() {
						public void onClose(ConfirmDialog dialog) {}
					});
					d.getCancelButton().setVisible(false);
				}
				else {
					OknoElementu o = oknoEdycji;
					T el = (T)doEdycji.toArray()[0];
					try {
						Method getId = viewTyp.getMethod("getId");
						Long i = (Long) getId.invoke(el);
						o.setElement(i);
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					o.addCloseListener(new CloseListener() {
			            public void windowClose(CloseEvent e) {
			            	tabela.setItems(viewRepo.findAll());
			            }
			        });
					o.setModal(true);
					o.center();
					UI.getCurrent().addWindow(o);
				}
			}
		});
		
		/**
		 * Ustawienie listenera przycisku Usun
		 */
		usun.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Set<T> doUsuniecia = tabela.getSelectedItems();
				
				if(doUsuniecia.isEmpty()) {
					ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Nie wybrano żadnego elementu", "OK", "", new ConfirmDialog.Listener() {
						public void onClose(ConfirmDialog dialog) {}
					});
					d.getCancelButton().setVisible(false);
				}
				else {
					ConfirmDialog d = ConfirmDialog.show(UI.getCurrent(), "Usuwanie elementów", "Czy jesteś pewien?",
					        "TAK", "NIE", new ConfirmDialog.Listener() {
						
						public void onClose(ConfirmDialog dialog) {
							if (dialog.isConfirmed()) {
								
								try{
									
									Method getId = viewTyp.getMethod("getId");
									Method delById = tableTyp.getMethod("delById", JpaRepository.class, Long.class);
									
									for(T u : doUsuniecia) {
										Long x = (Long) getId.invoke(u, null);
										delById.invoke(null, tableRepo, x);
									}
								}catch(Exception e){
									e.printStackTrace();
								}
								
								tabela.setItems(viewRepo.findAll());
							}
						}
					});
				}
			}
		});

		/**
		 * Ustawienie listenera dwukliku na rekordzie tabeli
		 */
		tabela.addItemClickListener(e -> {
			if (e.getMouseEventDetails().getType() == 2) {
				T doEdycji = (T) e.getItem();
				
				OknoElementu o = oknoEdycji;
				try {
					Method getId = viewTyp.getMethod("getId");
					Long i = (Long) getId.invoke(doEdycji);
					o.setElement(i);
				} catch(Exception ex) {
					ex.printStackTrace();
				}
				o.addCloseListener(new CloseListener() {
		            public void windowClose(CloseEvent e) {
		            	tabela.setItems(viewRepo.findAll());
		            }
		        });
				o.setModal(true);
				o.center();
				UI.getCurrent().addWindow(o);
			}
		});
		
		// pobranie danych z widoku
		tabela.setItems(viewRepo.findAll());
				
		setWidth("60%");
		//setHeight("60%");
		
		setResizable(false); // zablokowanie zmiany rozmiaru okienka
		setDraggable(true); // zablokowanie przesuwania okienka
		setContent(layout); // ustaw glowny layout okienka
	}
	
	/**
	 * Funkcja zwraca nazwy kolumn
	 * @return String reprezentujacy nazwe pliku
	 */
	public String[] getColumnNames() {
		List<Column<T,?>> columns = tabela.getColumns();
		String[] k = new String[columns.size()];
		for(int i=0; i<columns.size(); i++)
			k[i] = new String(columns.get(i).getCaption());
		return k;
	}
	
	/**
	 * Funkcja zmienia nazwy kolumn
	 * @param s Podawane są nazwy kolumn jako parametry wejsciowe
	 */
	public void changeColumnsNames(String... s) {
		List<Column<T,?>> columns = tabela.getColumns();
		for(int i=0; i<s.length; i++) {
			tabela.getColumn(columns.get(i).getCaption().toLowerCase()).setCaption(s[i]);
		}
	}
	
/**
 * 	Funkcja pozwala ustawic wysokosc tabeli na podstawie wysokosci ekranu (w pikselach)
 * @param resY Wysokosc ekranu
 */
	public void setResY(int resY) {
		tabela.setHeight(resY, Unit.PIXELS);
	}
	
	/**
	 * 	Funkcja pozwala ustawic szerokosc tabeli na podstawie wysokosci ekranu (w pikselach)
	 * @param resX Szerokosc ekranu
	 */
	public void setResX(int resX) {
		tabela.setWidth(resX, Unit.PIXELS);
	}
}
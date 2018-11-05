package pl.swztz.portal.windows;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.dialogs.ConfirmDialog;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.SelectionMode;

public class OknoAplikacji<T, S> extends Window {

	private VerticalLayout layout = new VerticalLayout(); // główny layout okienka
	private HorizontalLayout hlayout = new HorizontalLayout(); // layout dla przycisków
	protected Grid<T> grid;
	protected Button editButton = new Button("Edytuj");
	protected Button addButton = new Button("Dodaj");
	protected Button deleteButton = new Button("Usuń");

	public OknoAplikacji(String title, JpaRepository viewRepo, JpaRepository tableRepo, Class<T> viewTyp,
			Class<S> tableTyp, OknoElementu oknoDodawania, OknoElementu oknoEdycji) {
		super(title); // nazwa okienka
		this.grid = new Grid<>(viewTyp);

		// ustawienie właściwej kolejności kolumn
		try {
			Method getFieldNames = viewTyp.getMethod("getFieldNames");
			String[] fields = (String[]) getFieldNames.invoke(null);
			grid.setColumnOrder(fields);
		} catch (Exception e) {
			e.printStackTrace();
		}

		grid.setSelectionMode(SelectionMode.MULTI); // tryb zaznaczania wielu rekordów

		// usunięcie niepotrzebnej kolumny id
		grid.removeColumn(grid.getColumn("id"));

		hlayout.addComponents(addButton, editButton, deleteButton);
		layout.addComponents(grid, hlayout);

		// ustawienie listenera przycisku Dodaj
		addButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				OknoElementu window = oknoDodawania;
				window.addCloseListener(new CloseListener() {
					public void windowClose(CloseEvent e) {
						grid.setItems(viewRepo.findAll()); // odśwież widok
					}
				});
				window.setModal(true);
				window.center();
				UI.getCurrent().addWindow(window);
			}
		});

		// ustawienie listenera przycisku Edytuj
		editButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Set<T> toEdit = grid.getSelectedItems();

				if (toEdit.size() != 1) {
					ConfirmDialog dialog = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Wybierz jeden element", "OK",
							"", new ConfirmDialog.Listener() {
								public void onClose(ConfirmDialog dialog) {
								}
							});
					dialog.getCancelButton().setVisible(false);
				} else {
					OknoElementu window = oknoEdycji;
					T el = (T) toEdit.toArray()[0];
					try {
						Method getId = viewTyp.getMethod("getId");
						Long i = (Long) getId.invoke(el);
						window.setElement(i);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					window.addCloseListener(new CloseListener() {
						public void windowClose(CloseEvent e) {
							grid.setItems(viewRepo.findAll());
						}
					});
					window.setModal(true);
					window.center();
					UI.getCurrent().addWindow(window);
				}
			}
		});

		// ustawienie listenera przycisku Usuń
		deleteButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Set<T> toDelete = grid.getSelectedItems();

				if (toDelete.isEmpty()) {
					ConfirmDialog dialog = ConfirmDialog.show(UI.getCurrent(), "Błąd", "Nie wybrano żadnego elementu",
							"OK", "", new ConfirmDialog.Listener() {
								public void onClose(ConfirmDialog dialog) {
								}
							});
					dialog.getCancelButton().setVisible(false);
				} else {
					ConfirmDialog dialog = ConfirmDialog.show(UI.getCurrent(), "Usuwanie elementów",
							"Czy jesteś pewien?", "TAK", "NIE", new ConfirmDialog.Listener() {

								public void onClose(ConfirmDialog dialog) {
									if (dialog.isConfirmed()) {

										try {

											Method getId = viewTyp.getMethod("getId");
											Method delById = tableTyp.getMethod("delById", JpaRepository.class,
													Long.class);

											for (T u : toDelete) {
												Long x = (Long) getId.invoke(u, null);
												delById.invoke(null, tableRepo, x);
											}
										} catch (Exception e) {
											e.printStackTrace();
										}

										grid.setItems(viewRepo.findAll());
									}
								}
							});
				}
			}
		});

		// ustawienie listenera dwukliku na rekordzie tabeli
		grid.addItemClickListener(e -> {
			if (e.getMouseEventDetails().getType() == 2) {
				T doEdycji = (T) e.getItem();

				OknoElementu window = oknoEdycji;
				try {
					Method getId = viewTyp.getMethod("getId");
					Long i = (Long) getId.invoke(doEdycji);
					window.setElement(i);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				window.addCloseListener(new CloseListener() {
					public void windowClose(CloseEvent e) {
						grid.setItems(viewRepo.findAll());
					}
				});
				window.setModal(true);
				window.center();
				UI.getCurrent().addWindow(window);
			}
		});

		// pobranie danych z widoku
		grid.setItems(viewRepo.findAll());

		setWidth("60%");

		setResizable(false); // zablokowanie zmiany rozmiaru okienka
		setDraggable(true); // zablokowanie przesuwania okienka
		setContent(layout); // ustaw główny layout okienka
	}

	// funkcja zwraca nazwy kolumn
	public String[] getColumnNames() {
		List<Column<T, ?>> columns = grid.getColumns();
		String[] columnNames = new String[columns.size()];
		for (int i = 0; i < columns.size(); i++)
			columnNames[i] = new String(columns.get(i).getCaption());
		return columnNames;
	}

	// funkcja zmienia nazwy kolumn
	public void changeColumnsNames(String... s) {
		List<Column<T, ?>> columns = grid.getColumns();
		for (int i = 0; i < s.length; i++) {
			grid.getColumn(columns.get(i).getCaption().toLowerCase()).setCaption(s[i]);
		}
	}

	// funkcja pozwala ustawić wysokośc tabeli na podstawie wysokości ekranu (w pikselach)
	public void setResY(int resY) {	grid.setHeight(resY, Unit.PIXELS); }

	// funkcja pozwala ustawić szerokość tabeli na podstawie wysokości ekranu (w pikselach)
	public void setResX(int resX) {	grid.setWidth(resX, Unit.PIXELS); }
}
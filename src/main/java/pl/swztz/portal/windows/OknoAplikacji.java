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

	private VerticalLayout layout = new VerticalLayout(); // main window layout
	private HorizontalLayout hlayout = new HorizontalLayout(); // layout for buttons
	protected Grid<T> grid;
	protected Button editButton = new Button("Edytuj");
	protected Button addButton = new Button("Dodaj");
	protected Button deleteButton = new Button("Usuń");

	public OknoAplikacji(String title, JpaRepository viewRepo, JpaRepository tableRepo, Class<T> viewType,
			Class<S> tableType, OknoElementu addWindow, OknoElementu editWindow) {
		super(title); // window title
		this.grid = new Grid<>(viewType);

		// set correct column order
		try {
			Method getFieldNames = viewType.getMethod("getFieldNames");
			String[] fields = (String[]) getFieldNames.invoke(null);
			grid.setColumnOrder(fields);
		} catch (Exception e) {
			e.printStackTrace();
		}

		grid.setSelectionMode(SelectionMode.MULTI); // multi-selecting mode for grid

		// delete unwanted id column
		grid.removeColumn(grid.getColumn("id"));

		hlayout.addComponents(addButton, editButton, deleteButton);
		layout.addComponents(grid, hlayout);

		// set listener for add button
		addButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				OknoElementu window = addWindow;
				window.addCloseListener(new CloseListener() {
					public void windowClose(CloseEvent e) {
						grid.setItems(viewRepo.findAll()); // refresh grid
					}
				});
				window.setModal(true);
				window.center();
				UI.getCurrent().addWindow(window);
			}
		});

		// set listener for edit button
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
					OknoElementu window = editWindow;
					T el = (T) toEdit.toArray()[0];
					try {
						Method getId = viewType.getMethod("getId");
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

		// set listener for delete button
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

											Method getId = viewType.getMethod("getId");
											Method delById = tableType.getMethod("delById", JpaRepository.class,
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

		// set listener for double click on the grid
		grid.addItemClickListener(e -> {
			if (e.getMouseEventDetails().getType() == 2) {
				T doEdycji = (T) e.getItem();

				OknoElementu window = editWindow;
				try {
					Method getId = viewType.getMethod("getId");
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

		// get data from view to grid
		grid.setItems(viewRepo.findAll());

		setWidth("60%");

		setResizable(false); // block window size change
		setDraggable(true); // allow window moving
		setContent(layout); // set main window layout
	}

	// returns column names
	public String[] getColumnNames() {
		List<Column<T, ?>> columns = grid.getColumns();
		String[] columnNames = new String[columns.size()];
		for (int i = 0; i < columns.size(); i++)
			columnNames[i] = new String(columns.get(i).getCaption());
		return columnNames;
	}

	// set column names
	public void changeColumnsNames(String... s) {
		List<Column<T, ?>> columns = grid.getColumns();
		for (int i = 0; i < s.length; i++) {
			grid.getColumn(columns.get(i).getCaption().toLowerCase()).setCaption(s[i]);
		}
	}

	// set grid's height based on screen's height (in px)
	public void setResY(int resY) {	grid.setHeight(resY, Unit.PIXELS); }

	// set grid's width based on screen's height (in px)
	public void setResX(int resX) {	grid.setWidth(resX, Unit.PIXELS); }
}
package pl.swztz.portal;

import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import pl.swztz.portal.models.*;
import pl.swztz.portal.repositories.*;
import pl.swztz.portal.windows.*;

@SpringUI
@Theme("mytheme")
@Widgetset("MyWidgetset.Widgetset.gwt.xml")
public class VaadinUI extends UI {

	// repositories for database tables
	private AdministratorRepository administratorRepo;
	private BladRepository bladRepo;
	private DyrektorRepository dyrektorRepo;
	private GrupaRepository grupaRepo;
	private InspekcjaRepository inspekcjaRepo;
	private InstytutRepository instytutRepo;
	private KonsultacjaRepository konsultacjaRepo;
	private PlanistaRepository planistaRepo;
	private ProwadzacyRepository prowadzacyRepo;
	private PrzedmiotRepository przedmiotRepo;
	private SalaRepository salaRepo;
	private StudentRepository studentRepo;
	private UrlopRepository urlopRepo;
	private WiadomoscRepository wiadomoscRepo;
	private WniosekRepository wniosekRepo;
	private WydzialRepository wydzialRepo;
	private ZajeciaRepository zajeciaRepo;
	private ZapytanieRepository zapytanieRepo;

	// repositories for database views
	private AdministratorViewRepository administratorViewRepo;
	private BladViewRepository bladViewRepo;
	private DyrektorViewRepository dyrektorViewRepo;
	private GrupaViewRepository grupaViewRepo;
	private InspekcjaViewRepository inspekcjaViewRepo;
	private InstytutViewRepository instytutViewRepo;
	private KonsultacjaViewRepository konsultacjaViewRepo;
	private PlanistaViewRepository planistaViewRepo;
	private ProwadzacyViewRepository prowadzacyViewRepo;
	private PrzedmiotViewRepository przedmiotViewRepo;
	private SalaViewRepository salaViewRepo;
	private StudentViewRepository studentViewRepo;
	private UrlopViewRepository urlopViewRepo;
	private WiadomoscViewRepository wiadomoscViewRepo;
	private WniosekViewRepository wniosekViewRepo;
	private WydzialViewRepository wydzialViewRepo;
	private ZajeciaViewRepository zajeciaViewRepo;
	private ZapytanieViewRepository zapytanieViewRepo;

	// screen resolution
	private static int resX, resY;

	// UI components
	private Image logo;
	private Label title;
	private Button[] buttonApp = new Button[19];
	private CustomLayout customLayout;
	private Panel panel1;
	private HorizontalLayout layout;
	private OknoAplikacji appWindow;

	@Autowired
	public VaadinUI(ZapytanieViewRepository zapytanieViewRepo, ZapytanieRepository zapytanieRepo,
			KonsultacjaViewRepository konsultacjaViewRepo, KonsultacjaRepository konsultacjaRepo,
			WniosekViewRepository wniosekViewRepo, WniosekRepository wniosekRepo, UrlopViewRepository urlopViewRepo,
			UrlopRepository urlopRepo, InspekcjaViewRepository inspekcjaViewRepo, InspekcjaRepository inspekcjaRepo,
			SalaViewRepository salaViewRepo, SalaRepository salaRepo, GrupaViewRepository grupaViewRepo,
			GrupaRepository grupaRepo, StudentViewRepository studentViewRepo,
			ProwadzacyViewRepository prowadzacyViewRepo, StudentRepository studentRepo,
			ProwadzacyRepository prowadzacyRepo, InstytutViewRepository instytutViewRepo,
			InstytutRepository instytutRepo, DyrektorViewRepository dyrektorViewRepo, DyrektorRepository dyrektorRepo,
			PlanistaViewRepository planistaViewRepo, PlanistaRepository planistaRepo,
			AdministratorViewRepository administratorViewRepo, AdministratorRepository administratorRepo,
			BladViewRepository bladViewRepo, BladRepository bladRepo, PrzedmiotViewRepository przedmiotViewRepo,
			PrzedmiotRepository przedmiotRepo, WydzialViewRepository wydzialViewRepo, WydzialRepository wydzialRepo,
			ZajeciaViewRepository zajeciaViewRepo, ZajeciaRepository zajeciaRepo,
			WiadomoscViewRepository wiadomoscViewRepo, WiadomoscRepository wiadomoscRepo) {

		this.zapytanieViewRepo = zapytanieViewRepo;
		this.zapytanieRepo = zapytanieRepo;
		this.konsultacjaViewRepo = konsultacjaViewRepo;
		this.konsultacjaRepo = konsultacjaRepo;
		this.wniosekViewRepo = wniosekViewRepo;
		this.wniosekRepo = wniosekRepo;
		this.urlopViewRepo = urlopViewRepo;
		this.urlopRepo = urlopRepo;
		this.inspekcjaViewRepo = inspekcjaViewRepo;
		this.inspekcjaRepo = inspekcjaRepo;
		this.salaViewRepo = salaViewRepo;
		this.salaRepo = salaRepo;
		this.grupaViewRepo = grupaViewRepo;
		this.grupaRepo = grupaRepo;
		this.studentViewRepo = studentViewRepo;
		this.studentRepo = studentRepo;
		this.prowadzacyViewRepo = prowadzacyViewRepo;
		this.prowadzacyRepo = prowadzacyRepo;
		this.instytutViewRepo = instytutViewRepo;
		this.instytutRepo = instytutRepo;
		this.dyrektorViewRepo = dyrektorViewRepo;
		this.dyrektorRepo = dyrektorRepo;
		this.planistaViewRepo = planistaViewRepo;
		this.planistaRepo = planistaRepo;
		this.administratorViewRepo = administratorViewRepo;
		this.administratorRepo = administratorRepo;
		this.bladViewRepo = bladViewRepo;
		this.bladRepo = bladRepo;
		this.przedmiotViewRepo = przedmiotViewRepo;
		this.przedmiotRepo = przedmiotRepo;
		this.wydzialViewRepo = wydzialViewRepo;
		this.wydzialRepo = wydzialRepo;
		this.zajeciaViewRepo = zajeciaViewRepo;
		this.zajeciaRepo = zajeciaRepo;
		this.wiadomoscViewRepo = wiadomoscViewRepo;
		this.wiadomoscRepo = wiadomoscRepo;
	}

	@Override
	protected void init(VaadinRequest request) {

		// get screen resolution
		resX = (int) UI.getCurrent().getPage().getBrowserWindowWidth();
		resY = (int) UI.getCurrent().getPage().getBrowserWindowHeight();

		// initialize UI components
		initComponents();

		// add components to layout and set layout
		setPageContent();

		// add listeners to buttons
		addListeners();

	}

	private void initComponents() {
		
		logo = new Image("", new ThemeResource("images/logo.png"));
		logo.setWidth("118px");
		logo.setHeight("93px");

		title = new Label("System Wspomagania Zmiany Terminu Zajęć");
		title.setStyleName("title");

		buttonApp[0] = new Button("Plany zajęć");
		buttonApp[1] = new Button("Lista zajęć");
		buttonApp[2] = new Button("Zapytania o zmianę terminu zajęć");
		buttonApp[3] = new Button("Wiadomości");
		buttonApp[4] = new Button("Konsultacje");
		buttonApp[5] = new Button("Wnioski o przeniesienie zajęć");
		buttonApp[6] = new Button("Urlopy");
		buttonApp[7] = new Button("Inspekcje");
		buttonApp[8] = new Button("Sale");
		buttonApp[9] = new Button("Grupy studenckie");
		buttonApp[10] = new Button("Studenci");
		buttonApp[11] = new Button("Prowadzący zajęcia");
		buttonApp[12] = new Button("Instytuty");
		buttonApp[13] = new Button("Dyrektorzy instytutów");
		buttonApp[14] = new Button("Planiści");
		buttonApp[15] = new Button("Administratorzy");
		buttonApp[16] = new Button("Błędy");
		buttonApp[17] = new Button("Wydziały");
		buttonApp[18] = new Button("Przedmioty");

		for (int i = 0; i < 19; i++) {
			buttonApp[i].removeStyleNames("mytheme", "v-widget");
		}
	}

	private void setPageContent() {
		
		customLayout = new CustomLayout("mylayout");
		customLayout.addComponent(logo, "logo");
		customLayout.addComponent(title, "title");
		
		for(int i = 0; i < 19; i++)
			customLayout.addComponent(buttonApp[i], "buttonApp" + i);
		
		customLayout.setSizeFull();
		panel1 = new Panel();
		panel1.setContent(customLayout);
		panel1.setSizeUndefined();

		layout = new HorizontalLayout();
		layout.addComponents(panel1);

		setContent(layout);
	}

	private void addListeners() {

		buttonApp[0].addClickListener(e -> {
			UI.getCurrent().addWindow(new OknoPlan(zajeciaViewRepo));
		});

		buttonApp[1].addClickListener(e -> {
			appListener(appWindow = new OknoAplikacji<ZajeciaView, Zajecia>("Lista zajęć", zajeciaViewRepo,
					zajeciaRepo, ZajeciaView.class, Zajecia.class,
					new OknoElementuZajecia("Dodaj nowy element", zajeciaRepo, true),
					new OknoElementuZajecia("Edytuj element", zajeciaRepo, false)));
		});

		buttonApp[2].addClickListener(e -> {
			appListener(appWindow = new OknoAplikacji<ZapytanieView, Zapytanie>("Zapytania o przełożenie zajęć",
					zapytanieViewRepo, zapytanieRepo, ZapytanieView.class, Zapytanie.class,
					new OknoElementuZapytanie("Dodaj nowy element", zapytanieRepo, true, "Zapytanie"),
					new OknoElementuZapytanie("Edytuj element", zapytanieRepo, false, "Zapytanie")));
		});

		buttonApp[3].addClickListener(e -> {
			appListener(appWindow = new OknoAplikacjiWiadomosc<WiadomoscView, Wiadomosc>("Wiadomosc",
					wiadomoscViewRepo, wiadomoscRepo, WiadomoscView.class, Wiadomosc.class,
					new OknoElementuWiadomosc("Dodaj nowy element", wiadomoscRepo, true),
					new OknoElementuWiadomosc("Edytuj element", wiadomoscRepo, false)));
		});

		buttonApp[4].addClickListener(e -> {
			appListener(appWindow = new OknoAplikacji<KonsultacjaView, Konsultacja>("Konsultacje",
					konsultacjaViewRepo, konsultacjaRepo, KonsultacjaView.class, Konsultacja.class,
					new OknoElementuKonsultacja("Dodaj nowy element", konsultacjaRepo, true),
					new OknoElementuKonsultacja("Edytuj element", konsultacjaRepo, false)));
		});

		buttonApp[5].addClickListener(e -> {
			appListener(appWindow = new OknoAplikacji<WniosekView, Wniosek>("Wnioski o przełożenie zajęć",
					wniosekViewRepo, wniosekRepo, WniosekView.class, Wniosek.class,
					new OknoElementuWniosek("Dodaj nowy element", wniosekRepo, true),
					new OknoElementuWniosek("Edytuj element", wniosekRepo, false)));
		});

		buttonApp[6].addClickListener(e -> {
			appListener(appWindow = new OknoAplikacji<UrlopView, Urlop>("Urlopy", urlopViewRepo, urlopRepo,
					UrlopView.class, Urlop.class, new OknoElementuUrlop("Dodaj nowy element", urlopRepo, true),
					new OknoElementuUrlop("Edytuj element", urlopRepo, false)));
		});

		buttonApp[7].addClickListener(e -> {
			appListener(appWindow = new OknoAplikacji<InspekcjaView, Inspekcja>("Inspekcje", inspekcjaViewRepo,
					inspekcjaRepo, InspekcjaView.class, Inspekcja.class,
					new OknoElementuInspekcja("Dodaj nowy element", inspekcjaRepo, true),
					new OknoElementuInspekcja("Edytuj element", inspekcjaRepo, false)));
		});

		buttonApp[8].addClickListener(e -> {
			appListener(appWindow = new OknoAplikacji<SalaView, Sala>("Sale", salaViewRepo, salaRepo,
					SalaView.class, Sala.class, new OknoElementuSala("Dodaj nowy element", salaRepo, true),
					new OknoElementuSala("Edytuj element", salaRepo, false)));
		});

		buttonApp[9].addClickListener(e -> {
			appListener(
					appWindow = new OknoAplikacji<GrupaView, Grupa>("Grupy studenckie", grupaViewRepo, grupaRepo,
							GrupaView.class, Grupa.class, new OknoElementuGrupa("Dodaj nowy element", grupaRepo, true),
							new OknoElementuGrupa("Edytuj element", grupaRepo, false)));
		});

		buttonApp[10].addClickListener(e -> {
			appListener(appWindow = new OknoAplikacji<StudentView, Student>("Studenci", studentViewRepo,
					studentRepo, StudentView.class, Student.class,
					new OknoElementuStudent("Dodaj nowy element", studentRepo, true),
					new OknoElementuStudent("Edytuj element", studentRepo, false)));
		});

		buttonApp[11].addClickListener(e -> {
			appListener(appWindow = new OknoAplikacji<ProwadzacyView, Prowadzacy>("Prowadzący zajęcia",
					prowadzacyViewRepo, prowadzacyRepo, ProwadzacyView.class, Prowadzacy.class,
					new OknoElementuProwadzacy("Dodaj nowy element", prowadzacyRepo, true),
					new OknoElementuProwadzacy("Edytuj element", prowadzacyRepo, false)));
		});

		buttonApp[12].addClickListener(e -> {
			appListener(appWindow = new OknoAplikacji<InstytutView, Instytut>("Instytuty", instytutViewRepo,
					instytutRepo, InstytutView.class, Instytut.class,
					new OknoElementuInstytut("Dodaj nowy element", instytutRepo, true),
					new OknoElementuInstytut("Edytuj element", instytutRepo, false)));
		});

		buttonApp[13].addClickListener(e -> {
			appListener(appWindow = new OknoAplikacji<DyrektorView, Dyrektor>("Dyrektorzy instytutów",
					dyrektorViewRepo, dyrektorRepo, DyrektorView.class, Dyrektor.class,
					new OknoElementuDyrektor("Dodaj nowy element", dyrektorRepo, true),
					new OknoElementuDyrektor("Edytuj element", dyrektorRepo, false)));
		});

		buttonApp[14].addClickListener(e -> {
			appListener(appWindow = new OknoAplikacji<PlanistaView, Planista>("Planiści", planistaViewRepo,
					planistaRepo, PlanistaView.class, Planista.class,
					new OknoElementuPlanista("Dodaj nowy element", planistaRepo, true),
					new OknoElementuPlanista("Edytuj element", planistaRepo, false)));
		});

		buttonApp[15].addClickListener(e -> {
			appListener(appWindow = new OknoAplikacji<AdministratorView, Administrator>("Administratorzy",
					administratorViewRepo, administratorRepo, AdministratorView.class, Administrator.class,
					new OknoElementuAdministrator("Dodaj nowy element", administratorRepo, true),
					new OknoElementuAdministrator("Edytuj element", administratorRepo, false)));
		});

		buttonApp[16].addClickListener(e -> {
			appListener(appWindow = new OknoAplikacji<BladView, Blad>("Błędy", bladViewRepo, bladRepo,
					BladView.class, Blad.class, new OknoElementuBlad("Dodaj nowy element", bladRepo, true),
					new OknoElementuBlad("Edytuj element", bladRepo, false)));
		});

		buttonApp[17].addClickListener(e -> {
			appListener(appWindow = new OknoAplikacji<WydzialView, Wydzial>("Wydziały", wydzialViewRepo,
					wydzialRepo, WydzialView.class, Wydzial.class,
					new OknoElementuWydzial("Dodaj nowy element", wydzialRepo, true),
					new OknoElementuWydzial("Edytuj element", wydzialRepo, false)));
		});

		buttonApp[18].addClickListener(e -> {
			appListener(appWindow = new OknoAplikacji<PrzedmiotView, Przedmiot>("Przedmioty", przedmiotViewRepo,
					przedmiotRepo, PrzedmiotView.class, Przedmiot.class,
					new OknoElementuPrzedmiot("Dodaj nowy element", przedmiotRepo, true),
					new OknoElementuPrzedmiot("Edytuj element", przedmiotRepo, false)));
		});

	}

	private void appListener(OknoAplikacji window) {
		
		lockButtons();
		
		// new window
		appWindow = window;
		appWindow.setPosition((int) (getResX() * 0.2 + 10), 123);
		addWindow(appWindow);
		appWindow.setResY((int) (VaadinUI.getResY() * 0.45));
		appWindow.setResX((int) (VaadinUI.getResX() * 0.6));

		appWindow.addCloseListener(new CloseListener() {
			public void windowClose(CloseEvent e) {
				appWindow = null;
				unlockButtons();
			}
		});
	}

	public void unlockButtons() {
		for (int i = 0; i < 19; i++)
			buttonApp[i].setEnabled(true);
	}

	public void lockButtons() {
		for (int i = 0; i < 19; i++)
			buttonApp[i].setEnabled(false);
	}

	// get screen width (in px)
	public static int getResX() { return resX; }

	// get screen height (in px)
	public static int getResY() { return resY; }
}
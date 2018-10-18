package pl.swztz.portal;

import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import pl.swztz.portal.Models.*;
import pl.swztz.portal.Repositories.*;

/**
 * Klasa Frameworka Vaadin
 * @author SWZTZ Team
 *
 */
@SpringUI
@Theme("mytheme")
@Widgetset("MyWidgetset.Widgetset.gwt.xml")
public class VaadinUI extends UI {
	
	// repozytoria do tabel sql
	private AdministratorRepository administratorRepo;
	/*private AnkietaRepository ankietaRepo;*/
	private BladRepository bladRepo;
	private DyrektorRepository dyrektorRepo;
	private GrupaRepository grupaRepo;
	private InspekcjaRepository inspekcjaRepo;
	private InstytutRepository instytutRepo;
	private KonsultacjaRepository konsultacjaRepo;
	/*private ObecnoscRepository obecnoscRepo;
	private OdpowiedzAnkietyRepository odpowiedzAnkietyRepo;*/
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
	
	// repozytoria do widoków sql
	private AdministratorViewRepository administratorViewRepo;
	/*private AnkietaViewRepository ankietaViewRepo;*/
	private BladViewRepository bladViewRepo;
	private DyrektorViewRepository dyrektorViewRepo;
	private GrupaViewRepository grupaViewRepo;
	private InspekcjaViewRepository inspekcjaViewRepo;
	private InstytutViewRepository instytutViewRepo;
	private KonsultacjaViewRepository konsultacjaViewRepo;
	/*private ObecnoscViewRepository obecnoscViewRepo;
	private OdpowiedzAnkietyViewRepository odpowiedzAnkietyViewRepo;*/
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
	//TODO
	
	// rozdzielczosc ekranu
	private static int resX, resY;
	
	// elementy UI
	private Image logo;
	private Label tytul;
	private Button[] przyciskApp = new Button[22];
	private CustomLayout customLayout;
	private Panel panel1;
	private HorizontalLayout layout;
	private OknoAplikacji oknoAplikacji;
	
	/**
	 * Kontruktor VaadinUI
	 * 
	 * @param zapytanieRepo repzytorium do tabeli zapytania
	 * @param konsultacjaRepo repzytorium do tabeli
	 * @param wniosekRepo repzytorium do tabeli wnioski
	 * @param urlopRepo repzytorium do tabeli urlopy
	 * @param inspekcjaRepo repzytorium do tabeli inspekcje
 	 * @param salaRepo repzytorium do tabeli sale
	 * @param grupaRepo repzytorium do tabeli grupy
	 * @param studentRepo repzytorium do tabeli studenci 
	 * @param starostaRepo repzytorium do tabeli starosci
	 * @param prowadzacyRepo repzytorium do tabeli prowadzacy
	 * @param instytutRepo repzytorium do tabeli instytuty
	 * @param dyrektorRepo repzytorium do tabeli dyrektorzy
	 * @param planistaRepo repzytorium do tabeli planisci
	 * @param adminRepo repzytorium do tabeli  admini
	 * @param bladRepo repzytorium do tabeli bledy
	 */
	@Autowired
	public VaadinUI(ZapytanieViewRepository zapytanieViewRepo, ZapytanieRepository zapytanieRepo, KonsultacjaViewRepository konsultacjaViewRepo, KonsultacjaRepository konsultacjaRepo,
			WniosekViewRepository wniosekViewRepo, WniosekRepository wniosekRepo, UrlopViewRepository urlopViewRepo, UrlopRepository urlopRepo, InspekcjaViewRepository inspekcjaViewRepo, InspekcjaRepository inspekcjaRepo,
			SalaViewRepository salaViewRepo, SalaRepository salaRepo, GrupaViewRepository grupaViewRepo, GrupaRepository grupaRepo,
			StudentViewRepository studentViewRepo, ProwadzacyViewRepository prowadzacyViewRepo, StudentRepository studentRepo,
			ProwadzacyRepository prowadzacyRepo, InstytutViewRepository instytutViewRepo, InstytutRepository instytutRepo,
			DyrektorViewRepository dyrektorViewRepo, DyrektorRepository dyrektorRepo, PlanistaViewRepository planistaViewRepo, PlanistaRepository planistaRepo,
			AdministratorViewRepository administratorViewRepo, AdministratorRepository administratorRepo, BladViewRepository bladViewRepo,
			BladRepository bladRepo, PrzedmiotViewRepository przedmiotViewRepo, PrzedmiotRepository przedmiotRepo, WydzialViewRepository wydzialViewRepo, WydzialRepository wydzialRepo,
			ZajeciaViewRepository zajeciaViewRepo, ZajeciaRepository zajeciaRepo, WiadomoscViewRepository wiadomoscViewRepo, WiadomoscRepository wiadomoscRepo) {
		
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
		//TODO
	}

	/**
	 * Inicjalizacja widoku głównego
	 */
	@Override
	protected void init(VaadinRequest request) {
		
		// pobierz rozdzielczosc ekranu
		resX = (int) UI.getCurrent().getPage().getBrowserWindowWidth();
		resY = (int) UI.getCurrent().getPage().getBrowserWindowHeight();
		
		// inicjalizacja elementow UI
		initComponents();
						
		// dodaj komponenty do layoutu i go ustaw
		setPageContent();

		// dodaj listenery do przyciskow
		addListeners();
		
		lockButtons(); //
		unlockButtons(); //
	}
	
	/**
	 * Inicjalizacja elementow UI
	 */
	private void initComponents() {
		logo = new Image("", new ThemeResource("images/logo.png"));
		logo.setWidth("118px");
		logo.setHeight("93px");
		
		tytul = new Label("System Wspomagania Zmiany Terminu Zajęć");
		tytul.setStyleName("tytul");
		
		przyciskApp[0] = new Button ("Plany zajęć");
		przyciskApp[1] = new Button ("Lista zajęć");
		przyciskApp[2] = new Button ("Ankiety o zmianę terminu zajęć");
		przyciskApp[3] = new Button ("Zapytania o zmianę terminu zajęć");
		przyciskApp[4] = new Button ("Wiadomości");
		przyciskApp[5] = new Button ("Konsultacje");
		przyciskApp[6] = new Button ("Wnioski o przeniesienie zajęć");
		przyciskApp[7] = new Button ("Listy obecności");
		przyciskApp[8] = new Button ("Urlopy");
		przyciskApp[9] = new Button ("Inspekcje");
		przyciskApp[10] = new Button ("Sale");
		przyciskApp[11] = new Button ("Grupy studenckie");
		przyciskApp[12] = new Button ("Studenci");
		przyciskApp[13] = new Button ("Prowadzący zajęcia");
		przyciskApp[14] = new Button ("Instytuty");
		przyciskApp[15] = new Button ("Dyrektorzy instytutów");
		przyciskApp[16] = new Button ("Planiści");
		przyciskApp[17] = new Button ("Administratorzy");
		przyciskApp[18] = new Button ("Błędy");
		przyciskApp[19] = new Button ("Wydziały");
		przyciskApp[20] = new Button ("Przedmioty");
		przyciskApp[21] = new Button ("Panel użytkownika"); //zmiana hasla, maila, ustawienia powiadomien itp.
		
		for(int i=0; i<22; i++) {
			przyciskApp[i].removeStyleNames("mytheme", "v-widget");
		}
	}
	
	/**
	 *Dodanie elementow do GUI 
	 */
	private void setPageContent() {
		customLayout = new CustomLayout("mylayout");
		customLayout.addComponent(logo, "logo");
		customLayout.addComponent(tytul, "tytul");
		customLayout.addComponent(przyciskApp[0], "przyciskApp0");
		customLayout.addComponent(przyciskApp[1], "przyciskApp1");
		customLayout.addComponent(przyciskApp[2], "przyciskApp2");
		customLayout.addComponent(przyciskApp[3], "przyciskApp3");
		customLayout.addComponent(przyciskApp[4], "przyciskApp4");
		customLayout.addComponent(przyciskApp[5], "przyciskApp5");
		customLayout.addComponent(przyciskApp[6], "przyciskApp6");
		customLayout.addComponent(przyciskApp[7], "przyciskApp7");
		customLayout.addComponent(przyciskApp[8], "przyciskApp8");
		customLayout.addComponent(przyciskApp[9], "przyciskApp9");
		customLayout.addComponent(przyciskApp[10], "przyciskApp10");
		customLayout.addComponent(przyciskApp[11], "przyciskApp11");
		customLayout.addComponent(przyciskApp[12], "przyciskApp12");
		customLayout.addComponent(przyciskApp[13], "przyciskApp13");
		customLayout.addComponent(przyciskApp[14], "przyciskApp14");
		customLayout.addComponent(przyciskApp[15], "przyciskApp15");
		customLayout.addComponent(przyciskApp[16], "przyciskApp16");
		customLayout.addComponent(przyciskApp[17], "przyciskApp17");
		customLayout.addComponent(przyciskApp[18], "przyciskApp18");
		customLayout.addComponent(przyciskApp[19], "przyciskApp19");
		customLayout.addComponent(przyciskApp[20], "przyciskApp20");
		customLayout.addComponent(przyciskApp[21], "przyciskApp21");
		
		customLayout.setSizeFull();
		panel1 = new Panel();
		panel1.setContent(customLayout);
		panel1.setSizeUndefined();
		//panel1.setSizeFull();
		layout = new HorizontalLayout();
		layout.addComponents(panel1);
		//layout.setSizeFull();
		setContent(layout);
	}
	
	/**
	 * Ustawienie listenerow do przyciskow
	 */
	private void addListeners() {
		przyciskApp[0].addClickListener(e -> {
			UI.getCurrent().addWindow(new OknoPlan(zajeciaViewRepo));
		});
		
		przyciskApp[1].addClickListener(e -> {
			appListener( oknoAplikacji = new OknoAplikacji<ZajeciaView, Zajecia>("Lista zajęć", zajeciaViewRepo, zajeciaRepo, ZajeciaView.class, Zajecia.class, new OknoElementuZajecia("Dodaj nowy element", zajeciaRepo, true), new OknoElementuZajecia("Edytuj element", zajeciaRepo, false)) );
		});
		
		/*przyciskApp[2].addClickListener(e -> {
			//...
		});*/
		
		przyciskApp[3].addClickListener(e -> {
			appListener( oknoAplikacji = new OknoAplikacji<ZapytanieView, Zapytanie>("Zapytania o przełożenie zajęć", zapytanieViewRepo, zapytanieRepo, ZapytanieView.class, Zapytanie.class, new OknoElementuZapytanie("Dodaj nowy element", zapytanieRepo, true, "Zapytanie"), new OknoElementuZapytanie("Edytuj element", zapytanieRepo, false, "Zapytanie")) );
		});
		
		przyciskApp[4].addClickListener(e -> {
			appListener( oknoAplikacji = new OknoAplikacjiWiadomosc<WiadomoscView, Wiadomosc>("Wiadomosc", wiadomoscViewRepo, wiadomoscRepo, WiadomoscView.class, Wiadomosc.class, new OknoElementuWiadomosc("Dodaj nowy element", wiadomoscRepo, true), new OknoElementuWiadomosc("Edytuj element", wiadomoscRepo, false)) );
		});
		
		przyciskApp[5].addClickListener(e -> {
			appListener( oknoAplikacji = new OknoAplikacji<KonsultacjaView, Konsultacja>("Konsultacje", konsultacjaViewRepo, konsultacjaRepo, KonsultacjaView.class, Konsultacja.class, new OknoElementuKonsultacja("Dodaj nowy element", konsultacjaRepo, true), new OknoElementuKonsultacja("Edytuj element", konsultacjaRepo, false)) );
		});
		
		przyciskApp[6].addClickListener(e -> {
			appListener( oknoAplikacji = new OknoAplikacji<WniosekView, Wniosek>("Wnioski o przełożenie zajęć", wniosekViewRepo, wniosekRepo, WniosekView.class, Wniosek.class, new OknoElementuWniosek("Dodaj nowy element", wniosekRepo, true), new OknoElementuWniosek("Edytuj element", wniosekRepo, false)) );
		});
		
		/*przyciskApp[7].addClickListener(e -> {
			//...
		});*/
		
		przyciskApp[8].addClickListener(e -> {
			appListener( oknoAplikacji = new OknoAplikacji<UrlopView,Urlop>("Urlopy", urlopViewRepo, urlopRepo, UrlopView.class, Urlop.class, new OknoElementuUrlop("Dodaj nowy element", urlopRepo, true), new OknoElementuUrlop("Edytuj element", urlopRepo, false)) );
		});
		
		przyciskApp[9].addClickListener(e -> {
			appListener( oknoAplikacji = new OknoAplikacji<InspekcjaView, Inspekcja>("Inspekcje", inspekcjaViewRepo, inspekcjaRepo, InspekcjaView.class, Inspekcja.class, new OknoElementuInspekcja("Dodaj nowy element", inspekcjaRepo, true), new OknoElementuInspekcja("Edytuj element", inspekcjaRepo, false)) );
		});
		
		przyciskApp[10].addClickListener(e -> {
			appListener( oknoAplikacji = new OknoAplikacji<SalaView, Sala>("Sale", salaViewRepo, salaRepo, SalaView.class, Sala.class, new OknoElementuSala("Dodaj nowy element", salaRepo, true), new OknoElementuSala("Edytuj element", salaRepo, false)) );
		});
		
		przyciskApp[11].addClickListener(e -> {
			appListener( oknoAplikacji = new OknoAplikacji<GrupaView, Grupa>("Grupy studenckie", grupaViewRepo, grupaRepo, GrupaView.class, Grupa.class, new OknoElementuGrupa("Dodaj nowy element", grupaRepo, true), new OknoElementuGrupa("Edytuj element", grupaRepo, false)) );
		});
		
		przyciskApp[12].addClickListener(e -> {
			appListener( oknoAplikacji = new OknoAplikacji<StudentView, Student>("Studenci", studentViewRepo, studentRepo, StudentView.class, Student.class, new OknoElementuStudent("Dodaj nowy element", studentRepo, true), new OknoElementuStudent("Edytuj element", studentRepo, false)) );
		});
		
		przyciskApp[13].addClickListener(e -> {
			appListener( oknoAplikacji = new OknoAplikacji<ProwadzacyView, Prowadzacy>("Prowadzący zajęcia", prowadzacyViewRepo, prowadzacyRepo, ProwadzacyView.class, Prowadzacy.class, new OknoElementuProwadzacy("Dodaj nowy element", prowadzacyRepo, true), new OknoElementuProwadzacy("Edytuj element", prowadzacyRepo, false)) );
		});
		
		przyciskApp[14].addClickListener(e -> {
			appListener( oknoAplikacji = new OknoAplikacji<InstytutView,Instytut>("Instytuty", instytutViewRepo, instytutRepo, InstytutView.class, Instytut.class, new OknoElementuInstytut("Dodaj nowy element", instytutRepo, true), new OknoElementuInstytut("Edytuj element", instytutRepo, false)) );
		});
		
		przyciskApp[15].addClickListener(e -> {
			appListener( oknoAplikacji = new OknoAplikacji<DyrektorView, Dyrektor>("Dyrektorzy instytutów", dyrektorViewRepo, dyrektorRepo, DyrektorView.class, Dyrektor.class, new OknoElementuDyrektor("Dodaj nowy element", dyrektorRepo, true), new OknoElementuDyrektor("Edytuj element", dyrektorRepo, false)) );
		});
		
		przyciskApp[16].addClickListener(e -> {
			appListener( oknoAplikacji = new OknoAplikacji<PlanistaView, Planista>("Planiści", planistaViewRepo, planistaRepo, PlanistaView.class, Planista.class, new OknoElementuPlanista("Dodaj nowy element", planistaRepo, true), new OknoElementuPlanista("Edytuj element", planistaRepo, false)) );
		});
		
		przyciskApp[17].addClickListener(e -> {
			appListener( oknoAplikacji = new OknoAplikacji<AdministratorView, Administrator>("Administratorzy", administratorViewRepo, administratorRepo, AdministratorView.class, Administrator.class, new OknoElementuAdministrator("Dodaj nowy element", administratorRepo, true), new OknoElementuAdministrator("Edytuj element", administratorRepo, false)) );
		});
		
		przyciskApp[18].addClickListener(e -> {
			appListener( oknoAplikacji = new OknoAplikacji<BladView, Blad>("Błędy", bladViewRepo, bladRepo, BladView.class, Blad.class, new OknoElementuBlad("Dodaj nowy element", bladRepo, true), new OknoElementuBlad("Edytuj element", bladRepo, false)) );
		});
		
		przyciskApp[19].addClickListener(e -> {
			appListener( oknoAplikacji = new OknoAplikacji<WydzialView, Wydzial>("Wydziały", wydzialViewRepo, wydzialRepo, WydzialView.class, Wydzial.class, new OknoElementuWydzial("Dodaj nowy element", wydzialRepo, true), new OknoElementuWydzial("Edytuj element", wydzialRepo, false)));
		});
		
		przyciskApp[20].addClickListener(e -> {
			appListener( oknoAplikacji = new OknoAplikacji<PrzedmiotView,Przedmiot>("Przedmioty", przedmiotViewRepo, przedmiotRepo, PrzedmiotView.class, Przedmiot.class, new OknoElementuPrzedmiot("Dodaj nowy element", przedmiotRepo, true), new OknoElementuPrzedmiot("Edytuj element", przedmiotRepo, false)));
		});
		
		/*przyciskApp[21].addClickListener(e -> {
			UI.getCurrent().addWindow(new OknoPanelUzytkownika());
		});*/
		//TODO
	}
	
	private void appListener(OknoAplikacji oknoApki) {
		lockButtons();
		//nowe okno
		oknoAplikacji = oknoApki;
		oknoAplikacji.setPosition((int)(getResX()*0.2+10), 123);
    	addWindow(oknoAplikacji);
    	oknoAplikacji.setResY((int)(VaadinUI.getResY()*0.45));
    	oknoAplikacji.setResX((int)(VaadinUI.getResX()*0.6));

    	oknoAplikacji.addCloseListener(new CloseListener() {
			public void windowClose(CloseEvent e) {
				oknoAplikacji = null;
				unlockButtons();
			}
		});
	}
	
	/**
	 * Odblokowanie przyciskow, do ktorych zostaly dostarczone rozwiazania w postaci zaimplementowanych formularzy
	 */
	public void unlockButtons() {
		for(int i=0; i<22; i++) {
			if(i==0 || i==1 || i==3 || i==4 || i==5 || i==6 || i==8 || i==9 || i==10 || i==11 || i==12 || i==13 || i==14 || i==15 || i==16 || i==17 || i==18 || i==19 || i==20) //TODO
				przyciskApp[i].setEnabled(true);
		}
	}
	
	/**
	 * Zablokowanie przyciskow, do ktorych nie zostaly dostarczone rozwiazania w postaci zaimplementowanych formularzy
	 */
	public void lockButtons() {
		for(int i=0; i<22; i++) {
			przyciskApp[i].setEnabled(false);
		}
	}
	/**
	 * 	Funkcja zwraca szerokosc ekranu w pikselach
	 *@return resX Zwraca szerokosc
	 */
	public static int getResX() {
		return resX;
	}
		
	/**
	 * 	Funkcja zwraca wysokosc ekranu w pikselach
	 * 	 @return resY Zwraca wysokosc
	 */
	public static int getResY() {
		return resY;
	}
}
package pl.swztz.portal;

import java.util.Locale;
import org.vaadin.addon.calendar.Calendar;
import org.vaadin.addon.calendar.item.BasicItemProvider;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import pl.swztz.portal.Repositories.ZajeciaViewRepository;
import pl.swztz.portal.Models.ZajeciaView;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class OknoPlan extends Window {

	private ZajeciaViewRepository zajeciaRepo;
	private MeetingDataProvider eventProvider = new MeetingDataProvider();
	private VerticalLayout lay = new VerticalLayout();
	
	public OknoPlan(ZajeciaViewRepository zajeciaRepo) {
		super("Plan");
		this.zajeciaRepo = zajeciaRepo;
		
		Calendar<MeetingItem> cal = new Calendar(eventProvider);
		//cal.setWidth("900px");
		//cal.setHeight("450px");
		
		cal.addStyleName("meetings");
		cal.setWidth(100.0f, Unit.PERCENTAGE);
		cal.setHeight(100.0f, Unit.PERCENTAGE);
		cal.setResponsive(true);

		cal.setItemCaptionAsHtml(true);
		cal.setContentMode(ContentMode.HTML);
		
		cal.setLocale(new Locale("pl", "PL"));
		cal.withMonth(ZonedDateTime.now().getMonth());
		
		
		//cal.setSizeFull();//
		lay.addComponent(cal);
		//lay.setSizeFull();//
		
		//setWidth("60%");
		//setHeight("60%");
		
		setResizable(false); // zablokowanie zmiany rozmiaru okienka
		setDraggable(true); // zablokowanie przesuwania okienka
		setContent(lay);
		
		addAllMeetings();
	}
	
	private void addAllMeetings() {
		//addMeeting(ZonedDateTime.parse("2018-01-13T10:30:40+01:00[GMT+01:00]"), ZonedDateTime.parse("2018-01-13T12:30:40+01:00[GMT+01:00]"), "opis");
		
		for(ZajeciaView z : zajeciaRepo.findAll()){
			String opis = z.getNazwaPrzedmiot() + " (" + z.getTypZajec() + ")<br>" + z.getSala() + "; " + z.getGrupy() + "; " + z.getProwadzacy();
			
			LocalTime time1, time2;
			
			switch(z.getNrBloku().intValue()) {
			case 1:
				time1 = LocalTime.of(8, 0);
				time2 = LocalTime.of(9, 35);
				break;
			case 2:
				time1 = LocalTime.of(9, 50);
				time2 = LocalTime.of(11, 25);
				break;
			case 3:
				time1 = LocalTime.of(11, 40);
				time2 = LocalTime.of(13, 15);
				break;
			case 4:
				time1 = LocalTime.of(13, 30);
				time2 = LocalTime.of(15, 05);
				break;
			case 5:
				time1 = LocalTime.of(15, 45);
				time2 = LocalTime.of(17, 25);
				break;
			case 6:
				time1 = LocalTime.of(17, 40);
				time2 = LocalTime.of(19, 15);
				break;
			case 7:
				time1 = LocalTime.of(19, 25);
				time2 = LocalTime.of(21, 0);
				break;
			default:
				time1 = LocalTime.of(0, 0);
				time2 = LocalTime.of(0, 0);
				break;
			}
			
			ZonedDateTime pocz = ZonedDateTime.of(LocalDateTime.of(z.getData(), time1), ZoneId.of("Europe/Warsaw"));
			ZonedDateTime kon = ZonedDateTime.of(LocalDateTime.of(z.getData(), time2), ZoneId.of("Europe/Warsaw"));
			
			addMeeting(pocz, kon, opis);
		}
	}
	
	private void addMeeting(ZonedDateTime pocz, ZonedDateTime kon, String tresc) {

        Meeting meeting = new Meeting(false);
        meeting.setStart(pocz);
        meeting.setEnd(kon);
        //meeting.setDetails("A Detail<br>with HTML<br> with more lines");
        meeting.setDetails(tresc);
        meeting.setState(Meeting.State.planned);

        eventProvider.addItem(new MeetingItem(meeting));
	}
	
	private final class MeetingDataProvider extends BasicItemProvider<MeetingItem> {

        void removeAllEvents() {
            this.itemList.clear();
            fireItemSetChanged();
        }
    }
}

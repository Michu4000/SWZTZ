package pl.swztz.portal.windows;

import java.util.Locale;
import org.vaadin.addon.calendar.Calendar;
import org.vaadin.addon.calendar.item.BasicItemProvider;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import pl.swztz.portal.models.ZajeciaView;
import pl.swztz.portal.repositories.ZajeciaViewRepository;
import pl.swztz.portal.utils.Meeting;
import pl.swztz.portal.utils.MeetingItem;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ApplicationWindowPlan extends Window {

	private ZajeciaViewRepository zajeciaRepo;
	private MeetingDataProvider eventProvider = new MeetingDataProvider();
	private VerticalLayout lay = new VerticalLayout();
	
	public ApplicationWindowPlan(ZajeciaViewRepository zajeciaRepo) {
		super("Plan");
		this.zajeciaRepo = zajeciaRepo;
		
		Calendar<MeetingItem> cal = new Calendar(eventProvider);
		
		cal.addStyleName("meetings");
		cal.setWidth(100.0f, Unit.PERCENTAGE);
		cal.setHeight(100.0f, Unit.PERCENTAGE);
		cal.setResponsive(true);

		cal.setItemCaptionAsHtml(true);
		cal.setContentMode(ContentMode.HTML);
		
		cal.setLocale(new Locale("pl", "PL"));
		cal.withMonth(ZonedDateTime.now().getMonth());

		lay.addComponent(cal);
		
		setResizable(false); // block window size change
		setDraggable(true); // allow window moving
		setContent(lay);
		
		addAllMeetings();
	}
	
	private void addAllMeetings() {
		
		for(ZajeciaView z : zajeciaRepo.findAll()){
			String desc = z.getNazwaPrzedmiot() + " (" + z.getTypZajec() + ")<br>" + z.getSala() + "; " + z.getGrupy() + "; " + z.getProwadzacy();
			
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
			
			ZonedDateTime start = ZonedDateTime.of(LocalDateTime.of(z.getData(), time1), ZoneId.of("Europe/Warsaw"));
			ZonedDateTime end = ZonedDateTime.of(LocalDateTime.of(z.getData(), time2), ZoneId.of("Europe/Warsaw"));
			
			addMeeting(start, end, desc);
		}
	}
	
	private void addMeeting(ZonedDateTime start, ZonedDateTime end, String desc) {

        Meeting meeting = new Meeting(false);
        meeting.setStart(start);
        meeting.setEnd(end);
        meeting.setDetails(desc);
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

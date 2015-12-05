import org.junit.*;
import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

public class TestMeeting {

	Meeting meeting;
	  
	@Before
	public void setup(){
		meeting = new MeetingImpl();
	}
	
	@Test
	public void testGetId(){		  
		assertEquals(0,meeting.getId());
	}
	
	@Test
	public void testSetId(){
		((MeetingImpl)meeting).setId(100);
		assertEquals(100,meeting.getId());
	}
	
	
	@Test
	public void testGetDate(){		  
		assertEquals(null,meeting.getDate());
	}
	
	@Test
	public void testSetDate(){
		assertEquals(null,meeting.getDate());
		Calendar day1 = new GregorianCalendar(2015, 10, 1, 18, 45);
		((MeetingImpl)meeting).setDate(day1);
		assertEquals(day1.get(Calendar.YEAR),meeting.getDate().get(Calendar.YEAR));
		assertEquals(day1.get(Calendar.MONTH),meeting.getDate().get(Calendar.MONTH));
		assertEquals(day1.get(Calendar.DAY_OF_MONTH),meeting.getDate().get(Calendar.DAY_OF_MONTH));
		assertEquals(day1.get(Calendar.HOUR_OF_DAY),meeting.getDate().get(Calendar.HOUR_OF_DAY));
		assertEquals(day1.get(Calendar.MINUTE),meeting.getDate().get(Calendar.MINUTE));
	}
	
	/*
	@Test
	public void testGetContacts(){		  
		assertEquals(null,meeting.getContacts());
	}
	
	*/
	
	
}
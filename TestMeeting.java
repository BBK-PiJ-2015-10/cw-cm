import org.junit.*;
import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.HashSet;

public class TestMeeting {

	Meeting meeting;
	
	Set<Contact> participants;
	
	Calendar testdate;
	  
	@Before
	public void setup(){
		testdate = new GregorianCalendar(2015, 10, 1, 18, 45);
		Contact participantone = new ContactImpl(45,"Andrea Agassi","United States");
		Contact participanttwo = new ContactImpl(35,"Rafael Nadal","Spain");
		Contact participantthree = new ContactImpl(39,"Roger Federer","Helvetic Republic");
		participants = new HashSet<Contact>();
		participants.add(participantone);
		participants.add(participanttwo);
		participants.add(participantthree);	
		meeting = new MeetingImpl(10,testdate,participants);
	}
	
	@Test
	public void testGetId(){		  
		assertEquals(10,meeting.getId());
	}
	
	@Test
	public void testSetId(){
		((MeetingImpl)meeting).setId(100);
		assertEquals(100,meeting.getId());
	}
	
	
	@Test
	public void testGetDate(){		  	
		assertEquals(testdate,meeting.getDate());
	}
	
	@Test
	public void testSetDate(){
		assertEquals(testdate,meeting.getDate());
		Calendar day2 = new GregorianCalendar(2016, 5, 2, 23, 15);
		((MeetingImpl)meeting).setDate(day2);
		assertEquals(day2.get(Calendar.YEAR),meeting.getDate().get(Calendar.YEAR));
		assertEquals(day2.get(Calendar.MONTH),meeting.getDate().get(Calendar.MONTH));
		assertEquals(day2.get(Calendar.DAY_OF_MONTH),meeting.getDate().get(Calendar.DAY_OF_MONTH));
		assertEquals(day2.get(Calendar.HOUR_OF_DAY),meeting.getDate().get(Calendar.HOUR_OF_DAY));
		assertEquals(day2.get(Calendar.MINUTE),meeting.getDate().get(Calendar.MINUTE));
	}
	
	@Test
	public void testGetContacts(){		  
		assertEquals(participants,meeting.getContacts());
	}
	
	@Test
	public void testSetParticipants(){	
		Contact participantone = new ContactImpl(509,"Serana Williams","United States");
		Contact participanttwo = new ContactImpl(350,"Maria Sharampova","Russia");
		Contact participantthree = new ContactImpl(390,"Monica Seles","Yugoslavia");
		Set<Contact> participants = new HashSet<Contact>();
		participants.add(participantone);
		participants.add(participanttwo);
		participants.add(participantthree);
		((MeetingImpl)meeting).setParticipants(participants);
		assertEquals(participants,meeting.getContacts());
	}
	
	/*
	*These are the tests for the Constructor methods to ensure that exceptions are thrown
	*/
	@Test
	public void testTestIDforCase0Constructor(){
		try {
			Meeting TestIDMeeting = new MeetingImpl(0,testdate,participants);
		}
		catch (Exception ex1){
			assertEquals("java.lang.IllegalArgumentException",ex1.toString());
		}
	}
	
	@Test
	public void testTestIDforCaseNegativeConstructor(){
		try {
			Meeting TestIDMeeting = new MeetingImpl(-10,testdate,participants);
		}
		catch (Exception ex1){
			assertEquals("java.lang.IllegalArgumentException",ex1.toString());
		}
	}
	
	@Test
	public void testTestNullforNullDateConstructor(){
		Calendar NullTestdate = new GregorianCalendar();
		NullTestdate = null;
		try {
			Meeting TestNullDateMeeting = new MeetingImpl(10,NullTestdate,participants);
		}
		catch (Exception ex1){
			assertEquals("java.lang.NullPointerException",ex1.toString());
		}
	}
	
	@Test
	public void testTestNullforNullParticipantsConstructor(){
		Set<Contact> nullparticipants = new HashSet<Contact>();
		try {
			Meeting TestNullParticipantsMeeting = new MeetingImpl(10,testdate,nullparticipants);
		}
		catch (Exception ex1){
			assertEquals("java.lang.NullPointerException",ex1.toString());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.HashSet;

public class TestPastMeeting {

	PastMeeting testmeeting;
	
	Set<Contact> testparticipants;
	
	Calendar testdate;
	
	String testnotes;
	  
	@Before
	public void setup(){
		testdate = new GregorianCalendar(2015, 10, 1, 18, 45);
		Contact participantone = new ContactImpl(45,"Andrea Agassi","United States");
		Contact participanttwo = new ContactImpl(35,"Rafael Nadal","Spain");
		Contact participantthree = new ContactImpl(39,"Roger Federer","Helvetic Republic");
		testparticipants = new HashSet<Contact>();
		testparticipants.add(participantone);
		testparticipants.add(participanttwo);
		testparticipants.add(participantthree);	
		testnotes = "Dummy notes for this test";
		testmeeting = new PastMeetingImpl(10,testdate,testparticipants,testnotes);
	}
	
	@Test
	public void testGetId(){		  
		assertEquals(10,testmeeting.getId());
	}
	
	@Test
	public void testSetId(){
		((MeetingImpl)testmeeting).setId(100);
		assertEquals(100,testmeeting.getId());
	}
	
	
	@Test
	public void testGetDate(){		  	
		assertEquals(testdate,testmeeting.getDate());
	}
	
	@Test
	public void testSetDate(){
		assertEquals(testdate,testmeeting.getDate());
		Calendar day2 = new GregorianCalendar(2016, 5, 2, 23, 15);
		((MeetingImpl)testmeeting).setDate(day2);
		assertEquals(day2.get(Calendar.YEAR),testmeeting.getDate().get(Calendar.YEAR));
		assertEquals(day2.get(Calendar.MONTH),testmeeting.getDate().get(Calendar.MONTH));
		assertEquals(day2.get(Calendar.DAY_OF_MONTH),testmeeting.getDate().get(Calendar.DAY_OF_MONTH));
		assertEquals(day2.get(Calendar.HOUR_OF_DAY),testmeeting.getDate().get(Calendar.HOUR_OF_DAY));
		assertEquals(day2.get(Calendar.MINUTE),testmeeting.getDate().get(Calendar.MINUTE));
	}
	
	@Test
	public void testGetContacts(){		  
		assertEquals(testparticipants,testmeeting.getContacts());
	}
	
	@Test
	public void testSetParticipants(){	
		Contact participantone = new ContactImpl(509,"Serana Williams","United States");
		Contact participanttwo = new ContactImpl(350,"Maria Sharampova","Russia");
		Contact participantthree = new ContactImpl(390,"Monica Seles","Yugoslavia");
		Set<Contact> testparticipants2 = new HashSet<Contact>();
		testparticipants2.add(participantone);
		testparticipants2.add(participanttwo);
		testparticipants2.add(participantthree);
		((MeetingImpl)testmeeting).setParticipants(testparticipants2);
		assertEquals(testparticipants2,testmeeting.getContacts());
	}
	
	@Test
	public void testGetNotes(){
		assertEquals("Dummy notes for this test",testmeeting.getNotes());		
	}
	
	@Test
	public void testSetNotes(){
		((PastMeetingImpl)testmeeting).setNotes("I am changing this notes");
		assertEquals("I am changing this notes",testmeeting.getNotes());		
	}
	
	
	/*
	*These are the tests for the Constructor methods to ensure that exceptions are thrown
	*/
	@Test
	public void testTestIDforCase0Constructor(){
		try {
			PastMeeting TestIDMeeting = new PastMeetingImpl(0,testdate,testparticipants,testnotes);
		}
		catch (Exception ex1){
			assertEquals("java.lang.IllegalArgumentException",ex1.toString());
		}
	}
	
	@Test
	public void testTestIDforCaseNegativeConstructor(){
		try {
			PastMeeting TestIDMeeting = new PastMeetingImpl(-10,testdate,testparticipants,testnotes);
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
			PastMeeting TestNullDatesMeeting = new PastMeetingImpl(10,NullTestdate,testparticipants,testnotes);
		}
		catch (Exception ex1){
			assertEquals("java.lang.NullPointerException",ex1.toString());
		}
	}
	
	@Test
	public void testTestNullforNullParticipantsConstructor(){
		Set<Contact> nulltestparticipants = new HashSet<Contact>();
		try {
			PastMeeting TestNulltestparticipantsmeeting = new PastMeetingImpl(10,testdate,nulltestparticipants,testnotes);
		}
		catch (Exception ex1){
			assertEquals("java.lang.NullPointerException",ex1.toString());
		}
	}
	
	@Test
	public void testTestEmptyforNotesConstructor(){
		String testnotes2 = "";
		try {
			PastMeeting TestEmptyNotesMeeting = new PastMeetingImpl(10,testdate,testparticipants,testnotes2);
		}
		catch (Exception ex1){
			assertEquals("java.lang.NullPointerException",ex1.toString());
		}
	}
	
	@Test
	public void testTestNullforNotesConstructor(){
		String testnotes2 = null;
		try {
			PastMeeting TestEmptyNotesMeeting = new PastMeetingImpl(10,testdate,testparticipants,testnotes2);
		}
		catch (Exception ex1){
			assertEquals("java.lang.NullPointerException",ex1.toString());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
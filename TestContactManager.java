import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.HashSet;


public class TestContactManager {

	private ContactManager testerContactManager;
	
	private String nullstring = null;
	
	private String emptystring = "  ";
	
	private int emptyint;
	
	private Calendar currentdate = new GregorianCalendar(2016, 0, 14, 15, 30);
	
	private Calendar pastdate = new GregorianCalendar(2016, 0, 10, 15, 30);
	
	private Calendar futuredate = new GregorianCalendar(2016, 9, 14, 15, 30);
	
	private Calendar furtherfuturedate = new GregorianCalendar(2017, 1, 14, 15, 30);
	
	
	private Calendar emptydate = null;
	
	private Set<Contact> nullContactSet = null;
	
	/*
	* This method adds a list of Contacts to the ContactManager instance. 
	* This is to be used on several tests.
	*/
	public static void addcontacts(ContactManager input){
		input.addNewContact("John Mcenroe","United States");
		input.addNewContact("Rafael Nadal","Spain");
		input.addNewContact("Andrew Murray","U.K");
		input.addNewContact("Novak Djokovic","Serbia");
	}
	
	/*
	* This method adds an additional list of Contacts to the ContactManager instance. 
	* This is to be used on several tests.
	*/
	public static void addMoreContacts(ContactManager input){
		input.addNewContact("Pete Sampras","United States");
		input.addNewContact("Jimmi Connors","United States");
		input.addNewContact("Ivan Lendl","Czeck Republic");
		input.addNewContact("Yannick Noah","France");
	}
	
	/*
	* This method returns an a set of Contacts that is in the list of Contacts. 
	* This is to be used on several tests for the method:
    *    - addFutureMeeting(Set<Contact> contacts, Calendar date)
	*/
	public static Set<Contact> validContactSet(){
		Set<Contact> ValidContactSetResult = new HashSet<Contact>();
		ValidContactSetResult.add(new ContactImpl(1,"John Mcenroe","United States"));
		ValidContactSetResult.add(new ContactImpl(2,"Ravael Nadal","Spain"));
		ValidContactSetResult.add(new ContactImpl(3,"Andrew Murray","U.K"));
		ValidContactSetResult.add(new ContactImpl(4,"Novak Djokovic","Serbia"));
		return ValidContactSetResult;
	}
	
	/*
	* This method returns an a set of Contacts that is in the list of Contacts. 
	* This is to be used on several tests for the method:
    *    //- addFutureMeeting(Set<Contact> contacts, Calendar date)
	*/
	public static Set<Contact> validContactSmallerSet(){
		Set<Contact> ValidContactSetResult = new HashSet<Contact>();
		ValidContactSetResult.add(new ContactImpl(1,"John Mcenroe","United States"));
		ValidContactSetResult.add(new ContactImpl(2,"Ravael Nadal","Spain"));
		return ValidContactSetResult;
	}
			
	/*
	* This method returns an a set of Contacts that are not in the list of Contacts. 
	* This is to be used on several tests for the method:
    *    - addFutureMeeting(Set<Contact> contacts, Calendar date)
	*/
	public static Set<Contact> invalidContactSet(){
		Set<Contact> InValidContactSetResult = new HashSet<Contact>();
		InValidContactSetResult.add(new ContactImpl(500,"Serena Williams","United States"));
		InValidContactSetResult.add(new ContactImpl(505,"Maria Sharampova","Russia"));
		return InValidContactSetResult;
	}
	
	/*
	* This method adds a couple of valid Future meetings to a ContactManager input. 
	* This is to be used on several tests for the method:
    *    - FutureMeeting getFutureMeeting(int id);
	*/
	public static void add3futuremeetings (ContactManager input){
		input.addFutureMeeting(validContactSet(),new GregorianCalendar(2018, 9, 14, 15, 30));
		input.addFutureMeeting(validContactSet(),new GregorianCalendar(2019, 1, 14, 15, 30));
		input.addFutureMeeting(validContactSet(),new GregorianCalendar(2020, 2, 14, 15, 30));
	}
	
	/*
	* This method adds a couple of valid Future meetings to a ContactManager input. 
	* This is to be used on several tests for the method:
    *    - FutureMeeting getFutureMeeting(int id);
	*/
	public static void add4FutureMeetings3onSameDate (ContactManager input){
		TestContactManager.addcontacts(input);
		TestContactManager.addMoreContacts(input);
		Set<Contact> meeting1Set = new HashSet<Contact>();
		meeting1Set.add(new ContactImpl(1,"John Mcenroe","United States"));
		meeting1Set.add(new ContactImpl(2,"Rafael Nadal","Spain"));
		input.addFutureMeeting(meeting1Set,new GregorianCalendar(2016, 0, 22, 18, 45));
		Set<Contact> meeting2Set = new HashSet<Contact>();
		meeting2Set.add(new ContactImpl(5,"Pete Sampras","United States"));
		meeting2Set.add(new ContactImpl(6,"Jimmi Connors","United States"));
		input.addFutureMeeting(meeting2Set,new GregorianCalendar(2016, 0, 23, 18, 45));
		Set<Contact> meeting3Set = new HashSet<Contact>();
		meeting3Set.add(new ContactImpl(3,"Andrew Murray","U.K."));
		meeting3Set.add(new ContactImpl(4,"Novak Djokovic","Serbia"));
		input.addFutureMeeting(meeting3Set,new GregorianCalendar(2016, 0, 22, 20, 00));
		Set<Contact> meeting4Set = new HashSet<Contact>();
		meeting4Set.add(new ContactImpl(7,"Ivan Lendl","Czeck Republic"));
		meeting4Set.add(new ContactImpl(8,"Yannick Noah","France"));
		input.addFutureMeeting(meeting4Set,new GregorianCalendar(2016, 0, 22, 10, 00));
	}
	
	
	@Before
	public void setup(){
		testerContactManager = new ContactManagerImpl();
		((ContactManagerImpl)testerContactManager).setCurrentDate(currentdate);
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/*
	*  Below are the tests related to the AddNewContact(String name, String notes) method.
	*/
	
	
	/* Passing a valid set of arguments, testing that a meeting ID is generated.	
	*/
	@Test
	public void testAddNewContacttestIDgeneration(){		  
		assertEquals(1,testerContactManager.addNewContact("John Mcenroe","United States"));
	}
	
	/* Passing a valid set of arguments, testing that a meeting IDs are generated and that
	* the second ID # is the next integer of the first ID number.	
	*/
	@Test
	public void testAddNewContacttestIDgenerationfortwoContacts(){		  
		assertEquals(1,testerContactManager.addNewContact("John Mcenroe","United States"));
		assertEquals(2,testerContactManager.addNewContact("Rafael Nadal","Spain"));
	}
	
	/* Passing an empty String on name and notes, testing that a NullPointerException.
	* is thrown.	
	*/
	@Test
	public void testAddNewContactTestExceptionThrowforEmptyContactNotes(){
		TestContactManager.addcontacts(testerContactManager);
		try {
			testerContactManager.addNewContact(emptystring,emptystring);
		}
		catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
	}
	
	/* Passing an null String on name and notes, testing that a NullPointerException.
	* is thrown.	
	*/
	@Test
	public void testAddNewContactTestExceptionThrowforNullContactNotes(){
		TestContactManager.addcontacts(testerContactManager);
		try {
			testerContactManager.addNewContact(nullstring,nullstring);
		}
		catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
	}
	
	/* Passing an empty String on name and notes, testing that nothing got added to the meeting list.
	* is thrown.	
	*/
	@Test
	public void testAddNewContacttestEmptyStringContactNotAddedtotheContactList(){
		TestContactManager.addcontacts(testerContactManager);
		try {
			testerContactManager.addNewContact(emptystring,emptystring);
		}
		catch (NullPointerException ex){
			// I am just capturing the exception.
		}
		assertEquals(4,(((ContactManagerImpl)testerContactManager).getsizeofcontactlist()));
	}


	/* Passing a null String on name and notes, testing that nothing got added to the meeting list.
	* is thrown.	
	*/
	@Test
	public void testAddNewContacttestNullStringContactNotAddedtotheContactList(){
		TestContactManager.addcontacts(testerContactManager);
		try {
			testerContactManager.addNewContact(nullstring,nullstring);
		}
		catch (NullPointerException ex){
			//I am just capturing the exception.
		}
		assertEquals(4,(((ContactManagerImpl)testerContactManager).getsizeofcontactlist()));
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/*
	*  Below are the tests related to the Set<Contact> getContact(String name);
	*/
	
	
	/* Passing a null String, testing that a NullPointerException is thrown.	
	*/
	@Test
	public void testGetContactsTestNullStringThrowsException(){
		TestContactManager.addcontacts(testerContactManager);
		try {
			testerContactManager.getContacts(nullstring);
		}
		catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
	}
	
	/* Passing a empty String, testing that the full list of Contacts is returned.	
	*/
	@Test
	public void testGetContactsTestEmptyStringReturn(){
		TestContactManager.addcontacts(testerContactManager);
		assertEquals(4,testerContactManager.getContacts(emptystring).size());
	}
	
	/* Passing a Contact name not in the list, testing that the return Set is size 0.	
	*/
	@Test
	public void testGetContactsTestStringReturnWithNameNotInList(){
		TestContactManager.addcontacts(testerContactManager);
		assertEquals(0,testerContactManager.getContacts("Serena Williams").size());
	}
	
	/* Passing a String that is included in all the contact names in the list, 
	* testing that the return Set is the size of the list added.	
	*/
	@Test
	public void testGetContactsTestStringReturnWithNameInList(){
		testerContactManager.addNewContact("Alexander Baltimora","United States");
		testerContactManager.addNewContact("Alfred Pantinolli","Italia");
		testerContactManager.addNewContact("Alexei Poppof","Russia");
		testerContactManager.addNewContact("Ali Muhammed","Egypt");
		assertEquals(4,testerContactManager.getContacts("Al").size());
	}

////////////////////////////////////////////////////////////////////////////////////////////////////	
	/*
	*  Below are the tests related to the Set<Contact> getContact(int id);
	*/
	
	/* Passing a valid contact ID, testing if the return Set is not empty.	
	*/
	@Test
	public void testGetContactViaIDsTestSingleValidIDprovided(){
		TestContactManager.addcontacts(testerContactManager);
		assertEquals(false,testerContactManager.getContacts(1).isEmpty());
	}
	
	/* Passing a contact ID not in the list, testing that an IllegalArgumentException is thrown.	
	*/
	@Test
	public void testGetContactViaIDsTestSingleInvalidIDprovided(){
		TestContactManager.addcontacts(testerContactManager);
		try {
			testerContactManager.getContacts(4);
		}
		catch (Exception ex){
			assertEquals("java.lang.IllegalArgumentException",ex.toString());
		}
	}
	
	/* Passing an empty contact ID, testing that an IllegalArgumentException is thrown.	
	*/
	@Test
	public void testGetContactViaIDsTestSingleEmptyIDprovided(){
		TestContactManager.addcontacts(testerContactManager);
		try {
			testerContactManager.getContacts(emptyint);
		}
		catch (Exception ex){
			assertEquals("java.lang.IllegalArgumentException",ex.toString());
		}
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	*  Below are the tests related to the Set<Contact> getContact(List<Integer> ids);
	*/
	@Test
	public void testGetContactViaIDsTestMultipleValidIDprovided(){
		List<Integer> testerList = new LinkedList<Integer>();
		testerList.add(1);
		testerList.add(2);
		TestContactManager.addcontacts(testerContactManager);
		assertEquals(2,testerContactManager.getContacts(testerList).size());
	}
	
	@Test
	public void testGetContactViaIDsTestMultipleInvalidIDprovided(){
		List<Integer> testerList = new LinkedList<Integer>();
		testerList.add(1);
		testerList.add(2);
		testerList.add(3);
		TestContactManager.addcontacts(testerContactManager);
		try {
			testerContactManager.getContacts(testerList);
		}
		catch (Exception ex){
			assertEquals("java.lang.IllegalArgumentException",ex.toString());
		}	
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/*
	* Below are the tests for the int addFutureMeeting(Set<Contact> contacts, Calendar date);
	*/
	
	/* This test needs to return 1 since it is the first meeting being set up and all arguments are valid.	
	*/
	@Test
	public void testAddFutureMeetingValidInputs(){
		TestContactManager.addcontacts(testerContactManager);
		assertEquals(1,testerContactManager.addFutureMeeting(TestContactManager.validContactSet(),futuredate));
	}
	
	/* This test needs to return 1 and 2 since we are setting up two meetings and all arguments are valid.	
	*/
	@Test
	public void testAddFutureMeetingValidInputsforTwoMeetings(){
		TestContactManager.addcontacts(testerContactManager);
		assertEquals(1,testerContactManager.addFutureMeeting(TestContactManager.validContactSet(),futuredate));
		assertEquals(2,testerContactManager.addFutureMeeting(TestContactManager.validContactSet(),furtherfuturedate));
	}
	
	/* This test needs to throw and catch an exception since we are passing a date in the past.	
	*/
	@Test
	public void testAddFutureMeetingInvalidDate(){
		try {
			testerContactManager.addFutureMeeting(TestContactManager.validContactSet(),pastdate);
		}
		catch (Exception ex){
			assertEquals("java.lang.IllegalArgumentException",ex.toString());
		}
	}
	
	/* This test needs to throw and catch an exception since we are passing an emptydate.	
	*/
	@Test
	public void testAddFutureMeetingNullDate(){
		try {
			testerContactManager.addFutureMeeting(TestContactManager.validContactSet(),emptydate);
		}
		catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
	}
	
	/* This test needs to throw and catch an exception since we are passing a Null Contactset.	
	*/
	@Test
	public void testAddFutureMeetingNullContacts(){
		try {
			testerContactManager.addFutureMeeting(nullContactSet,futuredate);
		}
		catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
	}
	
	/* This test needs to throw and catch an exception since we are passing a set of Contacts not in the list.	
	*/
	@Test
	public void testAddFutureMeetingInvalidContacts(){
		TestContactManager.addcontacts(testerContactManager);
		try {
			testerContactManager.addFutureMeeting(TestContactManager.invalidContactSet(),futuredate);	
		}
		catch (Exception ex){
			assertEquals("java.lang.IllegalArgumentException",ex.toString());
		}	
	}	
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/*
	* Below are the tests for the int FutureMeeting getFutureMeeting(int id);
	*/
	
	/* Passing a valid input, testing that FutureMeeting returned matches the id, date, size, and each element
	* of the participants entered into that meeting.
	*/
	@Test
	public void testGetFutureMeetingValidMeetingInput(){
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> testParticipantsMeeting1 = validContactSet();
		Set<Contact> testNotParticipantsMeeting1 = invalidContactSet();
		testerContactManager.addFutureMeeting(testParticipantsMeeting1,futuredate);
		assertEquals(1,testerContactManager.getFutureMeeting(1).getId());
		assertEquals(futuredate,testerContactManager.getFutureMeeting(1).getDate());
		assertEquals(testParticipantsMeeting1.size(),testerContactManager.getFutureMeeting(1).getContacts().size());
		Set<Contact> meeting1Participants = testerContactManager.getFutureMeeting(1).getContacts();
		assertEquals(true,meeting1Participants.equals(testParticipantsMeeting1));
		assertEquals(false,meeting1Participants.equals(testNotParticipantsMeeting1));
	}	
	
	/* Passing a meeting ID non existent, expecting the return to be null.
	*/
	@Test
	public void testGetFutureMeetingNotExistingMeetingInput(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		assertEquals(null,testerContactManager.getFutureMeeting(4));
	}	
	
	/* Passing a meeting ID that is in the past, expecting the return to be an IllegalArgumentException.
	*/
	@Test
	public void testGetFutureMeetingPastMeetingInput(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		((ContactManagerImpl)testerContactManager).setCurrentDate(new GregorianCalendar(2018, 9, 15, 15, 30));
		try {
			testerContactManager.getFutureMeeting(1);
		}
		catch (Exception ex) {
			assertEquals("java.lang.IllegalArgumentException",ex.toString());
		}
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/*
	* Below are the tests for the PastMeeting addMeetingNotes(int id, String text);
	*/
	
	/* Passing a valid input, expecting a PastMeeting to be created and added to PastMeetingList. Testing the size
	* of the list before and after the AddMeetingNotes() method is executed.
	*/
	@Test
	public void testAddMeetingNotesValidInput(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		((ContactManagerImpl)testerContactManager).setCurrentDate(new GregorianCalendar(2019, 3, 14, 15, 30));
		assertEquals(0,(((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList()));
		testerContactManager.addMeetingNotes(1,"Game 1 was a succcess.");
		assertEquals(1,(((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList()));
	}


	/* Passing a an invalidinput (a meeting ID with a date in the future), expecting an IllegalStateException
    * to be thrown, no PastMeeting to be created or added to PastMeetingList. Testing that the exception is thrown
	* and that the size of of the list before and after the AddMeetingNotes() doesn't change.
	*/
	@Test
	public void testAddMeetingNotesInValidDateInput(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		((ContactManagerImpl)testerContactManager).setCurrentDate(new GregorianCalendar(2019, 3, 14, 15, 30));
		assertEquals(0,(((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList()));
		try {
			testerContactManager.addMeetingNotes(3,"Game 1 was a succcess.");
		}
		catch (Exception ex){
			assertEquals("java.lang.IllegalStateException",ex.toString());
		}
		assertEquals(0,(((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList()));
	}
	
	/* Passing an invalidinput (a meeting ID that doesn't exist), expecting an IllegalArgumentException
    * to be thrown, no PastMeeting to be created or added to PastMeetingList. Testing that the exception is thrown
	* and that the size of of the list before and after the AddMeetingNotes() doesn't change.
	*/
	@Test
	public void testAddMeetingNotesInValidMeetingIDInput(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		((ContactManagerImpl)testerContactManager).setCurrentDate(new GregorianCalendar(2019, 3, 14, 15, 30));
		assertEquals(0,(((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList()));
		try {
			testerContactManager.addMeetingNotes(4,"Game 1 was a succcess.");
		}
		catch (Exception ex){
			assertEquals("java.lang.IllegalArgumentException",ex.toString());
		}
		assertEquals(0,(((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList()));
	}
	
	/* Passing an invalidinput (null notes), expecting a NullPointerException
    * to be thrown, no PastMeeting to be created or added to PastMeetingList. Testing that the exception is thrown
	* and that the size of of the list before and after the AddMeetingNotes() doesn't change.
	*/
	@Test
	public void testAddMeetingNotesInValidMeetingNullNotesInput(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		((ContactManagerImpl)testerContactManager).setCurrentDate(new GregorianCalendar(2019, 3, 14, 15, 30));
		assertEquals(0,(((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList()));
		try {
			testerContactManager.addMeetingNotes(1,nullstring);
		}
		catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
		assertEquals(0,(((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList()));
	}
	
	/* Passing an invalidinput (empty notes), expecting a NullPointerException
    * to be thrown, no PastMeeting to be created or added to PastMeetingList. Testing that the exception is thrown
	* and that the size of of the list before and after the AddMeetingNotes() doesn't change.
	*/
	@Test
	public void testAddMeetingNotesInValidMeetingEmptyNotesInput(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		((ContactManagerImpl)testerContactManager).setCurrentDate(new GregorianCalendar(2019, 3, 14, 15, 30));
		assertEquals(0,(((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList()));
		try {
			testerContactManager.addMeetingNotes(1,emptystring);
		}
		catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
		assertEquals(0,(((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList()));
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/*
	* Below are the tests for the Meeting getMeeting(int id);
	*/

	/* Passing an invalidinput (a meeting ID that doesn't exist), expecting a Nullreturn.
	*/
	@Test
	public void testGetMeetingInValidMeetingIDInput(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		assertEquals(null,testerContactManager.getMeeting(5));
		
	}
	
	/* Passing a validinput expecting a Meeting to be return. Testing that the field of the meeting
	*  (id, participants, date) match the field of the meeting that I had previously added to the list.
	*/
	@Test
	public void testGetMeetingValidMeetingIDInput(){
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> testParticipantsMeeting1 = validContactSet();
		Set<Contact> testNotParticipantsMeeting1 = invalidContactSet();
		testerContactManager.addFutureMeeting(testParticipantsMeeting1,futuredate);
		assertEquals(1,testerContactManager.getMeeting(1).getId());
		assertEquals(futuredate,testerContactManager.getMeeting(1).getDate());
		assertEquals(testParticipantsMeeting1.size(),testerContactManager.getMeeting(1).getContacts().size());
		Set<Contact> meeting1Participants = testerContactManager.getMeeting(1).getContacts();
		assertEquals(true,meeting1Participants.equals(testParticipantsMeeting1));
		assertEquals(false,meeting1Participants.equals(testNotParticipantsMeeting1));
	}
	
	/* Passing a validinput expecting a Meeting to be return. Testing that the field of the meeting
	*  (id, participants, date) match the field of the meeting that I had previously added to the list.
	*/
	@Test
	public void testGetMeetingValidMeetingIDInputwthMultipleMeetingsinTheListPart1(){
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> testParticipantsMeeting1 = validContactSet();
		Set<Contact> testParticipantsMeeting2 = validContactSmallerSet();
		testerContactManager.addFutureMeeting(testParticipantsMeeting1,futuredate);
		testerContactManager.addFutureMeeting(testParticipantsMeeting2,furtherfuturedate);
		assertEquals(2,testerContactManager.getMeeting(2).getId());
	}
	
	@Test
	public void testGetMeetingValidMeetingIDInputwthMultipleMeetingsinTheListPart2(){
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> testParticipantsMeeting1 = validContactSet();
		Set<Contact> testParticipantsMeeting2 = validContactSmallerSet();
		testerContactManager.addFutureMeeting(testParticipantsMeeting1,futuredate);
		testerContactManager.addFutureMeeting(testParticipantsMeeting2,furtherfuturedate);
		assertEquals(furtherfuturedate,testerContactManager.getMeeting(2).getDate());
	}
	
	@Test
	public void testGetMeetingValidMeetingIDInputwthMultipleMeetingsinTheListPart3(){
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> testParticipantsMeeting1 = validContactSet();
		Set<Contact> testParticipantsMeeting2 = validContactSmallerSet();
		testerContactManager.addFutureMeeting(testParticipantsMeeting1,futuredate);
		testerContactManager.addFutureMeeting(testParticipantsMeeting2,furtherfuturedate);
		assertEquals(testParticipantsMeeting2.size(),testerContactManager.getMeeting(2).getContacts().size());
	}
	
	@Test
	public void testGetMeetingValidMeetingIDInputwthMultipleMeetingsinTheListPart4(){
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> testParticipantsMeeting1 = validContactSet();
		Set<Contact> testParticipantsMeeting2 = validContactSmallerSet();
		testerContactManager.addFutureMeeting(testParticipantsMeeting1,futuredate);
		testerContactManager.addFutureMeeting(testParticipantsMeeting2,furtherfuturedate);
		Set<Contact> meeting2Participants = testerContactManager.getMeeting(2).getContacts();
		assertEquals(true,meeting2Participants.equals(testParticipantsMeeting2));
		assertEquals(false,meeting2Participants.equals(testParticipantsMeeting1));
	}
	
	@Test
	public void testGetMeetingValidMeetingIDInputwthMultipleMeetingsinTheListPart5(){
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> testParticipantsMeeting1 = validContactSet();
		Set<Contact> testParticipantsMeeting2 = validContactSmallerSet();
		testerContactManager.addFutureMeeting(testParticipantsMeeting1,futuredate);
		testerContactManager.addFutureMeeting(testParticipantsMeeting2,furtherfuturedate);
		Set<Contact> meeting2Participants = testerContactManager.getMeeting(2).getContacts();
		assertEquals(false,meeting2Participants.equals(testParticipantsMeeting1));
	}
		

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/*
	* Below are the tests for the List<Meeting> getMeetingListOn(Calendar date);
	*/

	/* Passing a validinput (testing that the output list contains the proper meetings).
	*  More precisely, adding 4 meetings, 3 of them on the same date, and the second one
	* on a different date. Testing that the List being return from this method based on
	* the date of meetings (1,3,4) will be size 3 and will include those 3 meetings an
	ordered from older to more recent.
	*/
	@Test
	public void testGetMeetingListOnValidinputPart1(){
		TestContactManager.add4FutureMeetings3onSameDate(testerContactManager);
		List<Meeting> testresult = testerContactManager.getMeetingListOn(new GregorianCalendar(2016, 0, 22));
		assertEquals(3,testresult.size());	
	}
	
	@Test
	public void testGetMeetingListOnValidinputPart2(){
		TestContactManager.add4FutureMeetings3onSameDate(testerContactManager);
		List<Meeting> testresult = testerContactManager.getMeetingListOn(new GregorianCalendar(2016, 0, 22));
		assertEquals(false,testresult.contains(testerContactManager.getMeeting(2)));	
	}
	
	@Test
	public void testGetMeetingListOnValidinputPart3(){
		TestContactManager.add4FutureMeetings3onSameDate(testerContactManager);
		List<Meeting> testresult = testerContactManager.getMeetingListOn(new GregorianCalendar(2016, 0, 22));
		assertEquals(4,testresult.get(0).getId());	
	}
	
	@Test
	public void testGetMeetingListOnValidinputPart4(){
		TestContactManager.add4FutureMeetings3onSameDate(testerContactManager);
		List<Meeting> testresult = testerContactManager.getMeetingListOn(new GregorianCalendar(2016, 0, 22));
		assertEquals(1,testresult.get(1).getId());	
	}
	
	@Test
	public void testGetMeetingListOnValidinputPart5(){
		TestContactManager.add4FutureMeetings3onSameDate(testerContactManager);
		List<Meeting> testresult = testerContactManager.getMeetingListOn(new GregorianCalendar(2016, 0, 22));
		assertEquals(3,testresult.get(2).getId());	
	}
	
	
	/* Passing an Invalidinput (testing that a NullPointerException is thrown).
	*/
	@Test
	public void testGetMeetingListOnInValidinputPart1(){
		TestContactManager.add4FutureMeetings3onSameDate(testerContactManager);
		try {
			List<Meeting> testresult = testerContactManager.getMeetingListOn(emptydate);
		}
		catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
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
	
	private Contact nullContact = null;
	
	
	/**
	* This method adds a list of Contacts to the ContactManager instance. 
	*/
	public static void addcontacts(ContactManager input){
		input.addNewContact("John Mcenroe","United States");
		input.addNewContact("Rafael Nadal","Spain");
		input.addNewContact("Andrew Murray","U.K");
		input.addNewContact("Novak Djokovic","Serbia");
	}
	
	/**
	* This method adds an additional list of Contacts to the ContactManager instance. 
	*/
	public static void addMoreContacts(ContactManager input){
		input.addNewContact("Pete Sampras","United States");
		input.addNewContact("Jimmi Connors","United States");
		input.addNewContact("Ivan Lendl","Czeck Republic");
		input.addNewContact("Yannick Noah","France");
	}
	
	/**
	* This method returns an a set of Contacts that is in the list of Contacts. 
	*/
	public static Set<Contact> validContactSet(){
		Set<Contact> ValidContactSetResult = new HashSet<Contact>();
		ValidContactSetResult.add(new ContactImpl(1,"John Mcenroe","United States"));
		ValidContactSetResult.add(new ContactImpl(2,"Ravael Nadal","Spain"));
		ValidContactSetResult.add(new ContactImpl(3,"Andrew Murray","U.K"));
		ValidContactSetResult.add(new ContactImpl(4,"Novak Djokovic","Serbia"));
		return ValidContactSetResult;
	}
	
	/**
	* This method returns an a set of Contacts that is in the list of Contacts. 
	*/
	public static Set<Contact> validContactSmallerSet(){
		Set<Contact> ValidContactSetResult = new HashSet<Contact>();
		ValidContactSetResult.add(new ContactImpl(1,"John Mcenroe","United States"));
		ValidContactSetResult.add(new ContactImpl(2,"Ravael Nadal","Spain"));
		return ValidContactSetResult;
	}
			
	/**
	* This auxiliary method returns an a set of Contacts that are not in the list of Contacts. 
	*/
	public static Set<Contact> invalidContactSet(){
		Set<Contact> InValidContactSetResult = new HashSet<Contact>();
		InValidContactSetResult.add(new ContactImpl(500,"Serena Williams","United States"));
		InValidContactSetResult.add(new ContactImpl(505,"Maria Sharampova","Russia"));
		return InValidContactSetResult;
	}
	
	/**
	* This auxiliary method adds 3 Future meetings to a ContactManager input. 
	*/
	public static void add3futuremeetings (ContactManager input){
		input.addFutureMeeting(validContactSet(),new GregorianCalendar(2018, 9, 14, 15, 30));
		input.addFutureMeeting(validContactSet(),new GregorianCalendar(2019, 1, 14, 15, 30));
		input.addFutureMeeting(validContactSet(),new GregorianCalendar(2020, 2, 14, 15, 30));
	}
	
	/**
	* This is an auxiliary method adds a couple of valid Future meetings to a ContactManager input. 
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
	
	/**
	* This is a auxiliary method that adds two PastMeetings.
	*/	
	public static void add2PastMeetings (ContactManager input){
		TestContactManager.addcontacts(input);
		TestContactManager.addMoreContacts(input);
		TestContactManager.add3futuremeetings(input);
		((ContactManagerImpl)input).setCurrentDate(new GregorianCalendar(2020,0,14,15,30));
		input.addMeetingNotes(2,"This was the second meeting");
		input.addMeetingNotes(1, "This was the first meeting");
	}
	
	/**
	* This is an instance creator of ContactManager to be used for each of the test.
	* Setting the current date of ContactManager to be equal to a particular date instead
	* of the computer internal clock to faciliate the testing of all the features and exceptions
	* generated by each method of ContactManager.
	*/
	@Before
	public void setup(){
		testerContactManager = new ContactManagerImpl();
		((ContactManagerImpl)testerContactManager).cleanUp();
		((ContactManagerImpl)testerContactManager).setCurrentDate(currentdate);
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	*  Below are the tests related to the AddNewContact(String name, String notes) method.
	*/
	
	/**
   	* Passing a valid set of arguments, testing that a meeting ID is generated.	
	*/
	@Test
	public void testAddNewContacttestIDgeneration(){		  
		assertEquals(1,testerContactManager.addNewContact("John Mcenroe","United States"));
	}
	
	/**
    * Passing a valid set of arguments, testing that a meeting IDs are generated and that
	* the second ID # is the next integer of the first ID number.	
	*/
	@Test
	public void testAddNewContacttestIDgenerationfortwoContacts(){		  
		assertEquals(1,testerContactManager.addNewContact("John Mcenroe","United States"));
		assertEquals(2,testerContactManager.addNewContact("Rafael Nadal","Spain"));
	}
	
	/** 
	* Passing an empty String on name and notes, testing that a NullPointerException.
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
	
	/**
    * Passing an null String on name and notes, testing that a NullPointerException.
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
	
	/**
    * Passing an empty String on name and notes, testing that nothing got added to the meeting list.
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

	/**
    * Passing a null String on name and notes, testing that nothing got added to the meeting list.
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
	
	/**
	*  Below are the tests related to the Set<Contact> getContact(String name);
	*/
	
	/**
    * Passing a null String, testing that a NullPointerException is thrown.	
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
	
	/**
    * Passing a empty String, testing that the full list of Contacts is returned.	
	*/
	@Test
	public void testGetContactsTestEmptyStringReturn(){
		TestContactManager.addcontacts(testerContactManager);
		assertEquals(4,testerContactManager.getContacts(emptystring).size());
	}
	
	/**
    * Passing a Contact name not in the list, testing that the return Set is size 0.	
	*/
	@Test
	public void testGetContactsTestStringReturnWithNameNotInList(){
		TestContactManager.addcontacts(testerContactManager);
		assertEquals(0,testerContactManager.getContacts("Serena Williams").size());
	}
	
	/**
    * Passing a String that is included in all the contact names in the list, 
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

//////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	*  Below are the tests related to the Set<Contact> getContacts(int... ids);
	*/
	
	/** 
	* Passing a valid contact ID, testing if the return Set is not empty.	
	*/
	@Test
	public void testGetContactViaIDsTestSingleValidIDprovided(){
		TestContactManager.addcontacts(testerContactManager);
		assertEquals(false,testerContactManager.getContacts(1).isEmpty());
	}
	
	/** 
	* Passing two valids contact IDs, testing if the return Set is equal to 2.	
	*/
	@Test
	public void testGetContactViaIDsTestSeveralValidIDprovided(){
		TestContactManager.addcontacts(testerContactManager);
		assertEquals(2,testerContactManager.getContacts(1,4).size());
	}
	
	/**
	* Passing a contact ID not in the list, testing that an IllegalArgumentException is thrown.	
	*/
	@Test
	public void testGetContactViaIDsTestSingleInvalidIDprovided(){
		TestContactManager.addcontacts(testerContactManager);
		try {
			testerContactManager.getContacts(7);
		}
		catch (Exception ex){
			assertEquals("java.lang.IllegalArgumentException",ex.toString());
		}
	}
	
	/**
	* Passing two contacts, one of them with an ID not in the list, testing that an IllegalArgumentException is thrown.	
	*/
	@Test
	public void testGetContactViaIDsTestVariousInvalidIDprovided(){
		TestContactManager.addcontacts(testerContactManager);
		try {
			testerContactManager.getContacts(1,7);
		}
		catch (Exception ex){
			assertEquals("java.lang.IllegalArgumentException",ex.toString());
		}
	}
	
	/**
	* Passing an empty contact ID, testing that an IllegalArgumentException is thrown.	
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
		
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	* Below are the tests for the int addFutureMeeting(Set<Contact> contacts, Calendar date);
	*/
	
	/** 
	* This test needs to return 1 since it is the first meeting being set up and all arguments are valid.	
	*/
	@Test
	public void testAddFutureMeetingValidInputs(){
		TestContactManager.addcontacts(testerContactManager);
		assertEquals(1,testerContactManager.addFutureMeeting(TestContactManager.validContactSet(),futuredate));
	}
	
	/**
	* This test needs to return 2 since we are setting up two meetings and all arguments are valid.	
	*/
	@Test
	public void testAddFutureMeetingValidInputsforTwoMeetings(){
		TestContactManager.addcontacts(testerContactManager);
		testerContactManager.addFutureMeeting(TestContactManager.validContactSet(),futuredate);
		assertEquals(2,testerContactManager.addFutureMeeting(TestContactManager.validContactSet(),furtherfuturedate));
	}
	
	/**
    * This test needs to throw and catch an exception since we are passing a date in the past.	
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
	
	/** 
	* This test needs to throw and catch an exception since we are passing an emptydate.	
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
	
	/**
    * This test needs to throw and catch an exception since we are passing a Null Contactset.	
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
	
	/**
    * This test needs to throw and catch an exception since we are passing a set of Contacts not in the list.	
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
	
	/**
	* Below are the tests for the int FutureMeeting getFutureMeeting(int id);
	*/
	
	/** Passing a valid input, testing that FutureMeeting returned matches the id, date, size, and each element
	* of the participants entered into that meeting.
	*/
	@Test
	public void testGetFutureMeetingValidMeetingInputTestSize(){
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> testParticipantsMeeting1 = validContactSet();
		Set<Contact> testNotParticipantsMeeting1 = invalidContactSet();
		testerContactManager.addFutureMeeting(testParticipantsMeeting1,futuredate);
		assertEquals(testParticipantsMeeting1.size(),testerContactManager.getFutureMeeting(1).getContacts().size());
		Set<Contact> meeting1Participants = testerContactManager.getFutureMeeting(1).getContacts();
	}

	/** Passing a valid input, testing that FutureMeeting returned matches the date entered into that meeting.
	*/
	@Test
	public void testGetFutureMeetingValidMeetingInputTestDate(){
		TestContactManager.addcontacts(testerContactManager);
		testerContactManager.addFutureMeeting(validContactSet(),futuredate);
		assertEquals(futuredate,testerContactManager.getFutureMeeting(1).getDate());
	}

	/** Passing a valid input, testing that FutureMeeting returned matches the Id entered into that meeting.
	*/
	@Test
	public void testGetFutureMeetingValidMeetingInputTestId(){
		TestContactManager.addcontacts(testerContactManager);
		testerContactManager.addFutureMeeting(validContactSet(),futuredate);
		assertEquals(1,testerContactManager.getFutureMeeting(1).getId());
	}
	
	/**
	* Passing a valid input, testing that FutureMeeting returned matches the participants entered into that meeting.
	*/
	@Test
	public void testGetFutureMeetingValidMeetingInputTestParticipants(){
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> testParticipantsMeeting1 = validContactSet();
		testerContactManager.addFutureMeeting(testParticipantsMeeting1,futuredate);
		Set<Contact> meeting1Participants = testerContactManager.getFutureMeeting(1).getContacts();
		assertEquals(true,meeting1Participants.equals(testParticipantsMeeting1));
	}
	
	/**
	* Passing an valid input testing that FutureMeeting returned doesn't matches a set of Participants not
	entered into that meeting.
	*/
	@Test
	public void testGetFutureMeetingValidMeetingInputTestParticipantsNotInList(){
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> testParticipantsMeeting1 = validContactSet();
		Set<Contact> testNotParticipantsMeeting1 = invalidContactSet();
		testerContactManager.addFutureMeeting(testParticipantsMeeting1,futuredate);
		Set<Contact> meeting1Participants = testerContactManager.getFutureMeeting(1).getContacts();
		assertEquals(false,meeting1Participants.equals(testNotParticipantsMeeting1));
	}
	
	/** Passing a meeting ID non existent, expecting the return to be null.
	*/
	@Test
	public void testGetFutureMeetingNotExistingMeetingInput(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		assertEquals(null,testerContactManager.getFutureMeeting(4));
	}	
	
	/** Passing a meeting ID that is in the past, expecting the return to be an IllegalArgumentException.
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

	/**
	* Below are the tests for the PastMeeting addMeetingNotes(int id, String text);
	*/
	
	/** Passing a valid input, expecting a PastMeeting to be created and added to PastMeetingList. Testing the size
	* of the list before and after the AddMeetingNotes() method is executed.
	*/
	@Test
	public void testAddMeetingNotesValidInput(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		((ContactManagerImpl)testerContactManager).setCurrentDate(new GregorianCalendar(2019, 3, 14, 15, 30));
		int originalsize =   ((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList();
		testerContactManager.addMeetingNotes(1,"Game 1 was a succcess.");
		assertEquals((originalsize+1),(((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList()));
	}


	/** Passing an invalidinput (a meeting ID with a date in the future), testing that an IllegalStateException
    * is thrown.
	*/
	@Test
	public void testAddMeetingNotesInValidDateInput(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		((ContactManagerImpl)testerContactManager).setCurrentDate(new GregorianCalendar(2019, 3, 14, 15, 30));
		try {
			testerContactManager.addMeetingNotes(3,"Game 1 was a succcess.");
		}
		catch (Exception ex){
			assertEquals("java.lang.IllegalStateException",ex.toString());
		}
	}
	
	/** Passing a an invalidinput (a meeting ID with a date in the future), expecting an IllegalStateException
    * to be thrown. Testing that the size of of the list before and after the AddMeetingNotes() doesn't change.
	*/
	@Test
	public void testAddMeetingNotesInValidDateInputTestNoMeetingAdded(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		((ContactManagerImpl)testerContactManager).setCurrentDate(new GregorianCalendar(2019, 3, 14, 15, 30));
		int originalsize = (((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList());
		try {
			testerContactManager.addMeetingNotes(3,"Game 1 was a succcess.");
		}
		catch (Exception ex){
            //Do nothing.  
		}
		assertEquals(originalsize,(((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList()));
	}
		
	/** Passing an invalidinput (a meeting ID that doesn't exist), expecting an IllegalArgumentException
    * to be thrown. Testing that an IllegalArgumentException is thrown.
	*/
	@Test
	public void testAddMeetingNotesInValidMeetingIDInput(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		((ContactManagerImpl)testerContactManager).setCurrentDate(new GregorianCalendar(2019, 3, 14, 15, 30));
		try {
			testerContactManager.addMeetingNotes(4,"Game 1 was a succcess.");
		}
		catch (Exception ex){
			assertEquals("java.lang.IllegalArgumentException",ex.toString());
		}
	}
	
	/** Passing an invalidinput (a meeting ID that doesn't exist). Testing that no meeting is added,
	* by comparing the size of of the list before and after the AddMeetingNotes().
	*/
	@Test
	public void testAddMeetingNotesInValidMeetingIDInputTestNoMeetingAdded(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		((ContactManagerImpl)testerContactManager).setCurrentDate(new GregorianCalendar(2019, 3, 14, 15, 30));
		int originalsize = (((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList());
		try {
			testerContactManager.addMeetingNotes(4,"Game 1 was a succcess.");
		}
		catch (Exception ex){
			// Do nothing just catch.
		}
		assertEquals(originalsize,(((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList()));
	}
	
	/** Passing an invalidinput (null notes), expecting a NullPointerException
    * to be thrown. Testing that the exception is thrown and it is a NullPointerException.
	*/
	@Test
	public void testAddMeetingNotesInValidMeetingNullNotesInput(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		((ContactManagerImpl)testerContactManager).setCurrentDate(new GregorianCalendar(2019, 3, 14, 15, 30));
		try {
			testerContactManager.addMeetingNotes(1,nullstring);
		}
		catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
	}
	
	/** Passing an invalidinput (null notes), expecting no PastMeeting to be created or added to PastMeetingList. 
	* Testing that the size of of the list before and after the AddMeetingNotes() doesn't change.
	*/
	@Test
	public void testAddMeetingNotesInValidMeetingNullNotesInputTestNoMeetingAdded(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		((ContactManagerImpl)testerContactManager).setCurrentDate(new GregorianCalendar(2019, 3, 14, 15, 30));
		int originalsize = (((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList());
		try {
			testerContactManager.addMeetingNotes(1,nullstring);
		}
		catch (Exception ex){
			// Do nothing, just catch.
		}
		assertEquals(originalsize,(((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList()));
	}
	
	/** Passing an invalidinput (empty notes), expecting a NullPointerException
    * to be thrown. Testing that a NullPointerException is thrown.
	*/
	@Test
	public void testAddMeetingNotesInValidMeetingEmptyNotesInput(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		((ContactManagerImpl)testerContactManager).setCurrentDate(new GregorianCalendar(2019, 3, 14, 15, 30));
		try {
			testerContactManager.addMeetingNotes(1,emptystring);
		}
		catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
	}
	
	/** Passing an invalidinput (empty notes). Testing that the size of of the list before
	* and after the AddMeetingNotes() doesn't change.
	*/
	@Test
	public void testAddMeetingNotesInValidMeetingEmptyNotesInputTestNoMettingAdded(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		((ContactManagerImpl)testerContactManager).setCurrentDate(new GregorianCalendar(2019, 3, 14, 15, 30));
		int originalsize = (((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList());
		try {
			testerContactManager.addMeetingNotes(1,emptystring);
		}
		catch (Exception ex){
			// Do nothing. Just catch.
		}
		assertEquals(originalsize,(((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList()));
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	* Below are the tests for the Meeting getMeeting(int id);
	*/

	/** 
	*Passing an invalidinput (a meeting ID that doesn't exist), testing a Null is returned.
	*/
	@Test
	public void testGetMeetingInValidMeetingIDInput(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		assertEquals(null,testerContactManager.getMeeting(5));
	}
	
	/**
	* Passing a validinput expecting a Meeting to be returned. Testing that the field of the meeting
	* (id) match the field of the meeting that I had previously added to the list.
	*/
	@Test
	public void testGetMeetingValidMeetingIDInput(){
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> testParticipantsMeeting1 = validContactSet();
		testerContactManager.addFutureMeeting(testParticipantsMeeting1,futuredate);
		assertEquals(1,testerContactManager.getMeeting(1).getId());
	}
	
	/**
	* Passing a validinput expecting a Meeting to be returned. Testing that the field of the meeting
	* (date) match the field of the meeting that I had previously added to the list.
	*/
	@Test
	public void testGetMeetingValidMeetingInputDate(){
		TestContactManager.addcontacts(testerContactManager);
		testerContactManager.addFutureMeeting(validContactSet(),futuredate);
		assertEquals(futuredate,testerContactManager.getMeeting(1).getDate());
	}
	
	/**
	* Passing a validinput expecting a Meeting to be returned. Testing that the field of the meeting
	* (size of participants set) match the field of the meeting that I had previously added to the list.
	*/
	@Test
	public void testGetMeetingValidMeetingInputSize(){
		TestContactManager.addcontacts(testerContactManager);
		testerContactManager.addFutureMeeting(validContactSet(),futuredate);
		assertEquals(validContactSet().size(),testerContactManager.getMeeting(1).getContacts().size());
	}
	
	/**
	* Passing a validinput expecting a Meeting to be returned. Testing that the field of the meeting
	* (participants) match the field of the meeting that I had previously added to the list.
	*/
	@Test
	public void testGetMeetingValidMeetingInputParticipants(){
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> testParticipantsMeeting1 = validContactSet();
		testerContactManager.addFutureMeeting(testParticipantsMeeting1,futuredate);
		Set<Contact> meeting1Participants = testerContactManager.getMeeting(1).getContacts();
		assertEquals(true,meeting1Participants.equals(testParticipantsMeeting1));
	}
	
	/**
	* Passing a validinput expecting a Meeting to be returned. Testing that the field of the meeting
	* (participants) returned doesn't match a random list of participants.
	*/
	@Test
	public void testGetMeetingValidMeetingInputParticipantsNotEqualInvalidSet(){
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> testParticipantsMeeting1 = validContactSet();
		Set<Contact> testNotParticipantsMeeting1 = invalidContactSet();
		testerContactManager.addFutureMeeting(testParticipantsMeeting1,futuredate);
		Set<Contact> meeting1Participants = testerContactManager.getMeeting(1).getContacts();
		assertEquals(false,meeting1Participants.equals(testNotParticipantsMeeting1));
	}
		
	/** Passing a validinput expecting a Meeting to be returned. Testing that the field of the meeting
	*  (id) match the field of the meeting that I had previously added to the list.
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
	
	/** Passing a validinput expecting a Meeting to be returned. Testing that the field of the meeting
	*  (date) match the field of the meeting that I had previously added to the list.
	*/
	@Test
	public void testGetMeetingValidMeetingIDInputwthMultipleMeetingsinTheListPart2(){
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> testParticipantsMeeting1 = validContactSet();
		Set<Contact> testParticipantsMeeting2 = validContactSmallerSet();
		testerContactManager.addFutureMeeting(testParticipantsMeeting1,futuredate);
		testerContactManager.addFutureMeeting(testParticipantsMeeting2,furtherfuturedate);
		assertEquals(furtherfuturedate,testerContactManager.getMeeting(2).getDate());
	}
	
	/** Passing a validinput expecting a Meeting to be returned. Testing that the field of the meeting
	*  (participants size) match the field of the meeting that I had previously added to the list.
	*/
	@Test
	public void testGetMeetingValidMeetingIDInputwthMultipleMeetingsinTheListPart3(){
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> testParticipantsMeeting1 = validContactSet();
		Set<Contact> testParticipantsMeeting2 = validContactSmallerSet();
		testerContactManager.addFutureMeeting(testParticipantsMeeting1,futuredate);
		testerContactManager.addFutureMeeting(testParticipantsMeeting2,furtherfuturedate);
		assertEquals(testParticipantsMeeting2.size(),testerContactManager.getMeeting(2).getContacts().size());
	}
	
	/** Passing a validinput expecting a Meeting to be returned. Testing that the field of the meeting
	*  (participants) match the field of the meeting that I had previously added to the list.
	*/
	@Test
	public void testGetMeetingValidMeetingIDInputwthMultipleMeetingsinTheListPart4(){
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> testParticipantsMeeting1 = validContactSet();
		Set<Contact> testParticipantsMeeting2 = validContactSmallerSet();
		testerContactManager.addFutureMeeting(testParticipantsMeeting1,futuredate);
		testerContactManager.addFutureMeeting(testParticipantsMeeting2,furtherfuturedate);
		Set<Contact> meeting2Participants = testerContactManager.getMeeting(2).getContacts();
		assertEquals(true,meeting2Participants.equals(testParticipantsMeeting2));
	}
	
	/** Passing a validinput expecting a Meeting to be returned. Testing that the field of the meeting
	*  (participants) match doesn't match a random set of participants.
	*/
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
	
	/**
	* Below are the tests for the List<Meeting> getMeetingListOn(Calendar date);
	*/

	/** Passing a validinput (testing that the output list contains the proper meetings).
	* More precisely, adding 4 meetings, 3 of them on the same date, and the second one
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
	
	/** Passing a validinput (testing that the output list contains the proper meetings).
	* More precisely, adding 4 meetings, 3 of them on the same date, and the second one
	* on a different date. Testing that the List being return from this method based on
	* the date of meetings (1,3,4) will not include meeting # 2.
	*/
	@Test
	public void testGetMeetingListOnValidinputPart2(){
		TestContactManager.add4FutureMeetings3onSameDate(testerContactManager);
		List<Meeting> testresult = testerContactManager.getMeetingListOn(new GregorianCalendar(2016, 0, 22));
		assertEquals(false,testresult.contains(testerContactManager.getMeeting(2)));	
	}
	
	
	/** Passing a validinput (testing that the output list contains the proper meetings).
	* More precisely, adding 4 meetings, 3 of them on the same date, and the second one
	* on a different date. Testing that the List being return from this method based on
	* the date of meetings (1,3,4) testing that the first element in the returned list
	* is the one with the oldest date (chronologically ordered).
	*/
	@Test
	public void testGetMeetingListOnValidinputPart3(){
		TestContactManager.add4FutureMeetings3onSameDate(testerContactManager);
		List<Meeting> testresult = testerContactManager.getMeetingListOn(new GregorianCalendar(2016, 0, 22));
		assertEquals(4,testresult.get(0).getId());	
	}
	
	/** Passing a validinput (testing that the output list contains the proper meetings).
	* More precisely, adding 4 meetings, 3 of them on the same date, and the second one
	* on a different date. Testing that the List being return from this method based on
	* the date of meetings (1,3,4) testing that the second element in the returned list
	* is the second one with the oldest date (chronologically ordered).
	*/
	@Test
	public void testGetMeetingListOnValidinputPart4(){
		TestContactManager.add4FutureMeetings3onSameDate(testerContactManager);
		List<Meeting> testresult = testerContactManager.getMeetingListOn(new GregorianCalendar(2016, 0, 22));
		assertEquals(1,testresult.get(1).getId());	
	}
	
	/** Passing a validinput (testing that the output list contains the proper meetings).
	* More precisely, adding 4 meetings, 3 of them on the same date, and the second one
	* on a different date. Testing that the List being return from this method based on
	* the date of meetings (1,3,4) testing that the third and last element in the returned list
	* is the third and last one with the most recent date (chronologically ordered).
	*/
	@Test
	public void testGetMeetingListOnValidinputPart5(){
		TestContactManager.add4FutureMeetings3onSameDate(testerContactManager);
		List<Meeting> testresult = testerContactManager.getMeetingListOn(new GregorianCalendar(2016, 0, 22));
		assertEquals(3,testresult.get(2).getId());	
	}
	
	
	/**
	* Passing an Invalidinput (testing that a NullPointerException is thrown).
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
	
	/**
    * Passing a valid input, but no meeting on that date (testing that an empty list is returned).
	*/
	@Test
	public void testGetMeetingListOnValidinputNoMeetingonDate(){
		TestContactManager.add4FutureMeetings3onSameDate(testerContactManager);
		List<Meeting> testresult = testerContactManager.getMeetingListOn(new GregorianCalendar(2016, 0, 30));
		assertEquals(true,testresult.isEmpty());
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	* Below are the tests for the List<Meeting> getFutureMeetingList(Calendar contact);
	*/
		
	/**
	* Passing a valid input. Testing that returns all meetings added to the list, since
	* they all meet the passing criteria of the method.
	*/
	@Test
	public void testGetFutureMeetingListValidinputPart1(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		Contact testContact = new ContactImpl(1,"John Mcenroe","United States");
		assertEquals(3,testerContactManager.getFutureMeetingList(testContact).size());	
	}
	
	/**
	* Passing a valid input. Testing if the first meeting in the list is the one
	* with the earliest date.
	*/
	@Test
	public void testGetFutureMeetingListValidinputPart2TestingChronoOrder(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		Calendar testDate = new GregorianCalendar(2016, 0, 14, 16, 30);
		testerContactManager.addFutureMeeting(validContactSet(),testDate);
		Contact testContact = new ContactImpl(1,"John Mcenroe","United States");
		assertEquals(testDate,testerContactManager.getFutureMeetingList(testContact).get(0).getDate());	
	}
		
	/**
	* Passing a valid input, but this time changing the date of CalendarManager.
	* Testing that returns only 2 of the 3 meetings added to the list, since
	* one of them is already in the past.
	*/
	@Test
	public void testGetFutureMeetingListDateInthePast(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		((ContactManagerImpl)testerContactManager).setCurrentDate(new GregorianCalendar(2018, 9, 15, 15, 30));
		Contact testContact = new ContactImpl(1,"John Mcenroe","United States");
		assertEquals(2,testerContactManager.getFutureMeetingList(testContact).size());	
	}
	
	/**
	* Passing a valid input, but who doesn't have any meetings scheduled.
	* Testing that returns a list that is empty.
	*/
	@Test
	public void testGetFutureMeetingListContactWithNoMeetings(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.addMoreContacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		Contact testContact = new ContactImpl(5,"Pete Sampras","United States");
		assertEquals(true,testerContactManager.getFutureMeetingList(testContact).isEmpty());	
	}
	
	/**
	* Passing an invalid input (a Contact that doesn't exist), testing the IllegalArgumentException.
	*/
	@Test
	public void testGetFutureMeetingListforContactNonExistent(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		Contact testContact = new ContactImpl(25,"Non Existent Contact","Mars");
		try {
			testerContactManager.getFutureMeetingList(testContact);
		}
		catch (Exception ex){
			assertEquals("java.lang.IllegalArgumentException",ex.toString());
		}
	}
	
	/**
	* Passing an invalid input (a null Contact), testing the NullPointerException.
	*/
	@Test
	public void testGetFutureMeetingListforNullContact(){
		TestContactManager.addcontacts(testerContactManager);
		TestContactManager.add3futuremeetings(testerContactManager);
		try {
			testerContactManager.getFutureMeetingList(nullContact);
		}
		catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
	* Below are the tests for the List<PastMeeting> getPastMeetingListFor(Calendar contact);
	*/
		
	/**
	* Passing a valid input. Testing that returns all meetings added to the list, since
	* they all meet the passing criteria of the method.
	*/
	@Test
	public void testGetPastMeetingListForValidinputPart1(){
		TestContactManager.add2PastMeetings(testerContactManager);
		Contact testContact = new ContactImpl(1,"John Mcenroe","United States");
		assertEquals(2,testerContactManager.getPastMeetingListFor(testContact).size());	
	}

	/**
	* Passing a valid input. Testing if the first meeting in the list is the one
	* with the earliest date.
	*/
	@Test
	public void testGetPastMeetingListValidinputPart2TestingChronoOrder(){
		TestContactManager.add2PastMeetings(testerContactManager);
		Contact testContact = new ContactImpl(1,"John Mcenroe","United States");
		assertEquals(new GregorianCalendar(2018, 9, 14, 15, 30),testerContactManager.getPastMeetingListFor(testContact).get(0).getDate());	
	}
	
   /**
	* Passing a valid input, but who doesn't have any past meetings.
	* Testing that returns a list that is empty.
	*/
	@Test
	public void testGetPastMeetingListForContactWithNoMeetings(){
		TestContactManager.add2PastMeetings(testerContactManager);
		Contact testContact = new ContactImpl(5,"Pete Sampras","United States");
		assertEquals(true,testerContactManager.getPastMeetingListFor(testContact).isEmpty());	
	}
	
	/**
	* Passing an invalid input (a Contact that doesn't exist), testing the IllegalArgumentException.
	*/
	@Test
	public void testGetPastMeetingListForContactNonExistent(){
		TestContactManager.add2PastMeetings(testerContactManager);
		Contact testContact = new ContactImpl(25,"Non Existent Contact","Mars");
		try {
			testerContactManager.getPastMeetingListFor(testContact);
		}
		catch (Exception ex){
			assertEquals("java.lang.IllegalArgumentException",ex.toString());
		}
	}
	
	/**
	* Passing an invalid input (a null Contact), testing the NullPointerException.
	*/
	@Test
	public void testGetPastMeetingListForNullContact(){
		TestContactManager.add2PastMeetings(testerContactManager);
		try {
			testerContactManager.getPastMeetingListFor(nullContact);
		}
		catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////  
  
    /**
	* Below are all the tests for the flush method. I am creating a ContactManager instance, 
	* adding 8 contact, 3 meetings, making two of them in past, 
	*/
	
	/**
	* Testing that the new instance has a PastMeeting list equal to 2 meetings,
	* based on the prior ContactManager execution..
	*/
	@Test
	public void testFlushPartITestPastMeetingsList(){
		TestContactManager.add2PastMeetings(testerContactManager);
		Contact testContact = new ContactImpl(1,"John Mcenroe","United States");
		ContactManager test = new ContactManagerImpl();
		((ContactManagerImpl)test).setCurrentDate(new GregorianCalendar(2020,0,14,15,30));
		assertEquals(2,test.getPastMeetingListFor(testContact).size());
	}
	
	/**
	* Testing that the new instance has a FutureMeeting list equal to 1 meeting,
	* based on the prior ContactManager execution.
	*/
	@Test
	public void testFlushPartIITestFutureMeetingsList(){
		TestContactManager.add2PastMeetings(testerContactManager);
		Contact testContact = new ContactImpl(1,"John Mcenroe","United States");
		ContactManager test = new ContactManagerImpl();
		((ContactManagerImpl)test).setCurrentDate(new GregorianCalendar(2020,0,14,15,30));
		assertEquals(1,test.getFutureMeetingList(testContact).size());
	}
	
	/**
	* Testing that the new instance has a Contact List of 8 based on the prior ContactManager execution.
	*/
	@Test
	public void testFlushPartIVTestContactList(){
		TestContactManager.add2PastMeetings(testerContactManager);
		Contact testContact = new ContactImpl(1,"John Mcenroe","United States");
		ContactManager test = new ContactManagerImpl();
		((ContactManagerImpl)test).setCurrentDate(new GregorianCalendar(2020,0,14,15,30));
		assertEquals(8,((ContactManagerImpl)testerContactManager).getsizeofcontactlist());
	}
	
	/**
	* Testing that the future Meeting saved and retrived, matches the ID originally assigned.
	*/
	@Test
	public void testFlushPartVTestFutureMeetingListID(){
		TestContactManager.add2PastMeetings(testerContactManager);
		assertEquals(3,(testerContactManager.getMeetingListOn(new GregorianCalendar(2020, 2, 14, 15, 30)).get(0).getId()));
	}
	
	/**
	* Testing that the future Meeting saved and retrived, matches the Participants list size originally assigned.
	*/
	@Test
	public void testFlushPartVITestFutureMeetingListContacts(){
		TestContactManager.add2PastMeetings(testerContactManager);
		assertEquals(4,(testerContactManager.getMeetingListOn(new GregorianCalendar(2020, 2, 14, 15, 30)).get(0).getContacts().size()));
	}
	
	/**
	* Testing that if a new contact is added, the contactidcount will return a 9.
	*/
	@Test
	public void testFlushPartVIITestContactIdCount(){
		TestContactManager.add2PastMeetings(testerContactManager);
		assertEquals(9,(testerContactManager.addNewContact("Juan Martin Potro","Argentina")));
	}
	
	
	/**
	* Testing that if a new meeting is added to the list, it will return a value of 4,
	* since 3 meetings were added in the previous session of the ContactManager.
	*/
	@Test
	public void testFlushPartVIIITestMeetingIdCount(){
		TestContactManager.add2PastMeetings(testerContactManager);
		assertEquals(4,(testerContactManager.addFutureMeeting(validContactSmallerSet(),new GregorianCalendar(2020, 5, 14, 15, 30))));
	}
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

   /**
   * Below are the tests for the void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text)
   */
     
   /**
   * Passing valid inputs, testing the size of the list of PastMeetings before and after the execution.  
   */
   @Test
   public void testaddNewPastMeetingValidInput(){
	   TestContactManager.addcontacts(testerContactManager);
	   int sizebefore = ((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList();
	   testerContactManager.addNewPastMeeting(validContactSet(),pastdate,"This was the first past meeting");
	   assertEquals(sizebefore+1,((ContactManagerImpl)testerContactManager).getSizeofPastMeetingList());
   }
    
   /**
   * Passing a null note, testing that a NullPointerException is thrown.
   */
   @Test
   public void testaddNewPastMeetingInValidInputNullNotes(){
	   TestContactManager.addcontacts(testerContactManager);
	   try {
		   testerContactManager.addNewPastMeeting(validContactSet(),pastdate,nullstring);
	   }
	   catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
   }
   
   /**
   * Passing an empty note, testing that a NullPointerException is thrown.
   */
   @Test
   public void testaddNewPastMeetingInValidInputEmptyNotes(){
	   TestContactManager.addcontacts(testerContactManager);
	   try {
		   testerContactManager.addNewPastMeeting(validContactSet(),pastdate,emptystring);
	   }
	   catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
   }
   
   /**
   * Passing an empty date, testing that a NullPointerException is thrown.
   */
   @Test
   public void testaddNewPastMeetingInValidInputNullDate(){
	   TestContactManager.addcontacts(testerContactManager);
	   try {
		   testerContactManager.addNewPastMeeting(validContactSet(),emptydate,"This meeting has an empty date");
	   }
	   catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
   }
   
   /**
   * Passing a null list of meeting participants, testing that a NullPointerException is thrown.
   */
   @Test
   public void testaddNewPastMeetingInValidInputNullParticipants(){
	   TestContactManager.addcontacts(testerContactManager);
	   try {
		   testerContactManager.addNewPastMeeting(nullContactSet,pastdate,"This meeting has an empty date");
	   }
	   catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
   }
   
   /**
   * Passing an invalid list of meeting participants, testing that an IllegalArgumentException is thrown.
   */
   @Test
   public void testaddNewPastMeetingInValidInputInvalidParticipants(){
	   TestContactManager.addcontacts(testerContactManager);
	   try {
		   testerContactManager.addNewPastMeeting(invalidContactSet(),pastdate,"This meeting has an empty date");
	   }
	   catch (Exception ex){
			assertEquals("java.lang.IllegalArgumentException",ex.toString());
		}
   }
   
   /**
   * Passing a date in the future, testing that an IllegalStateException is thrown.
   */
   @Test
   public void testaddNewPastMeetingInValidInputDateInFuture(){
	   TestContactManager.addcontacts(testerContactManager);
	   try {
		   testerContactManager.addNewPastMeeting(validContactSet(),futuredate,"This meeting has an empty date");
	   }
	   catch (Exception ex){
			assertEquals("java.lang.IllegalStateException",ex.toString());
		}
   }
   
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
   
	
}
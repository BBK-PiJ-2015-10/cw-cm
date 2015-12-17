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
	
	private Calendar emptydate = null;
	
	private Set<Contact> nullContactSet = null;
	
	
	
	public static void addcontacts(ContactManager input){
		input.addNewContact("John Mcenroe","United States");
		input.addNewContact("Rafael Nadal","Spain");
		input.addNewContact("Andrew Murray","U.K");
		input.addNewContact("Novak Djokovic","Serbia");
	}
	
	
	@Before
	public void setup(){
		testerContactManager = new ContactManagerImpl();
	}
	
	
	/*
	*  Below are the tests related to the AddNewContact(String name, String notes) method.
	*/
	@Test
	public void testAddNewContacttestIDgeneration(){		  
		assertEquals(1,testerContactManager.addNewContact("John Mcenroe","United States"));
	}
	
	@Test
	public void testAddNewContacttestIDgenerationfortwoContacts(){		  
		assertEquals(1,testerContactManager.addNewContact("John Mcenroe","United States"));
		assertEquals(2,testerContactManager.addNewContact("Rafael Nadal","Spain"));
	}
	
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
	
	
	/*
	*  Below are the tests related to the Set<Contact> getContact(String name);
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
	
	@Test
	public void testGetContactsTestEmptyStringReturn(){
		TestContactManager.addcontacts(testerContactManager);
		assertEquals(4,testerContactManager.getContacts(emptystring).size());
	}
	
	@Test
	public void testGetContactsTestStringReturnWithNameNotInList(){
		TestContactManager.addcontacts(testerContactManager);
		assertEquals(0,testerContactManager.getContacts("Serena Williams").size());
	}
	
	@Test
	public void testGetContactsTestStringReturnWithNameInList(){
		testerContactManager.addNewContact("Alexander Baltimora","United States");
		testerContactManager.addNewContact("Alfred Pantinolli","Italia");
		testerContactManager.addNewContact("Alexei Poppof","Russia");
		testerContactManager.addNewContact("Ali Muhammed","Egypt");
		assertEquals(4,testerContactManager.getContacts("Al").size());
	}
	
	/*
	*  Below are the tests related to the Set<Contact> getContact(int id);
	*/
	
	@Test
	public void testGetContactViaIDsTestSingleValidIDprovided(){
		TestContactManager.addcontacts(testerContactManager);
		assertEquals(false,testerContactManager.getContacts(1).isEmpty());
	}
	
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
	
	
	/*
	* Below are the tests for the int addFutureMeeting(Set<Contact> contacts, Calendar date);
	*/
	@Test
	public void testAddFutureMeetingValidInputs(){
		((ContactManagerImpl)testerContactManager).setCurrentDate(currentdate);
		List<Integer> testerList = new LinkedList<Integer>();
		testerList.add(1);
		testerList.add(2);
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> validContactSet = testerContactManager.getContacts(testerList);
		assertEquals(1,testerContactManager.addFutureMeeting(validContactSet,futuredate));
	}
	
	@Test
	public void testAddFutureMeetingInvalidDate(){
		((ContactManagerImpl)testerContactManager).setCurrentDate(currentdate);
		List<Integer> testerList = new LinkedList<Integer>();
		testerList.add(1);
		testerList.add(2);
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> validContactSet = testerContactManager.getContacts(testerList);
		try {
			testerContactManager.addFutureMeeting(validContactSet,pastdate);
		}
		catch (Exception ex){
			assertEquals("java.lang.IllegalArgumentException",ex.toString());
		}
	}
	
	@Test
	public void testAddFutureMeetingNullDate(){
		((ContactManagerImpl)testerContactManager).setCurrentDate(currentdate);
		List<Integer> testerList = new LinkedList<Integer>();
		testerList.add(1);
		testerList.add(2);
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> validContactSet = testerContactManager.getContacts(testerList);
		try {
			testerContactManager.addFutureMeeting(validContactSet,emptydate);
		}
		catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
	}
	
	@Test
	public void testAddFutureMeetingNullContacts(){
		((ContactManagerImpl)testerContactManager).setCurrentDate(currentdate);
		List<Integer> testerList = new LinkedList<Integer>();
		testerList.add(1);
		testerList.add(2);
		TestContactManager.addcontacts(testerContactManager);
		Set<Contact> validContactSet = testerContactManager.getContacts(testerList);
		try {
			testerContactManager.addFutureMeeting(nullContactSet,futuredate);
		}
		catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
	}
			
	@Test
	public void testAddFutureMeetingInvalidContacts(){
		((ContactManagerImpl)testerContactManager).setCurrentDate(currentdate);
		TestContactManager.addcontacts(testerContactManager);
		Contact invalid1 = new ContactImpl(100,"Maria Sharampova","Russia");
		Contact invalid2 = new ContactImpl(200,"Jennifer Capriati","USA");
		Set<Contact> inValidContactSet = new HashSet<Contact>();
		inValidContactSet.add(invalid1);
		inValidContactSet.add(invalid2);
		try {
			testerContactManager.addFutureMeeting(inValidContactSet,futuredate);	
		}
		catch (Exception ex){
			assertEquals("java.lang.//IllegalArgumentException",ex.toString());
		}	
	}	
		
	
	
	
	
	
}
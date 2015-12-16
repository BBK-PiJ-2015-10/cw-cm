import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.LinkedList;

public class TestContactManager {

	ContactManager testerContactManager;
	
	String nullstring = null;
	
	String emptystring = "  ";
	
	int emptyint;
	
	
	public static void addcontacts(ContactManager input){
		input.addNewContact("John Mcenroe","United States");
		input.addNewContact("Rafael Nadal","Spain");
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
		assertEquals(2,(((ContactManagerImpl)testerContactManager).getsizeofcontactlist()));
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
		assertEquals(2,(((ContactManagerImpl)testerContactManager).getsizeofcontactlist()));
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
		assertEquals(2,testerContactManager.getContacts(emptystring).size());
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
	
	
}
import org.junit.*;
import static org.junit.Assert.*;

public class TestContactManager {

	ContactManager testerContactManager;
	
	String nullstring = null;
	
	String emptystring = "  ";
	  
	@Before
	public void setup(){
		testerContactManager = new ContactManagerImpl();
	}
	
	
	/*
	*  Below are the tests related to the AddNewContact(String name, String notes) method.
	*
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
	public void testAddNewContacttestIDgenerationforInvalidContact(){
		assertEquals(1,testerContactManager.addNewContact("John Mcenroe","United States"));
		assertEquals(2,testerContactManager.addNewContact("Rafael Nadal","Spain"));
		try {
			assertEquals(3,testerContactManager.addNewContact("  ","  "));
		}
		catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
	}
	
	
	@Test
	public void testAddNewContacttestEmptyStringContactNotAddedtotheContactList(){
		assertEquals(1,testerContactManager.addNewContact("John Mcenroe","United States"));
		assertEquals(2,testerContactManager.addNewContact("Rafael Nadal","Spain"));
		
		try {
			assertEquals(3,testerContactManager.addNewContact(emptystring,emptystring));
		}
		catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
		assertEquals(2,(((ContactManagerImpl)testerContactManager).getsizeofcontactlist()));
	}
	
	@Test
	public void testAddNewContacttestNullStringContactNotAddedtotheContactList(){
		assertEquals(1,testerContactManager.addNewContact("John Mcenroe","United States"));
		assertEquals(2,testerContactManager.addNewContact("Rafael Nadal","Spain"));
		try {
			assertEquals(3,testerContactManager.addNewContact(nullstring,nullstring));
		}
		catch (Exception ex){
			assertEquals("java.lang.NullPointerException",ex.toString());
		}
		assertEquals(2,(((ContactManagerImpl)testerContactManager).getsizeofcontactlist()));
	}
	
	
	
	
	
}
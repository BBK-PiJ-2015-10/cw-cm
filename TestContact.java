import org.junit.*;
import static org.junit.Assert.*;

public class TestContact {

	Contact testercontact;
	  
	@Before
	public void setup(){
		testercontact = new ContactImpl(31,"Andy Murray","England");
	}
	
	@Test
	public void testGetId(){		  
		assertEquals(31,testercontact.getId());
	}
	
	@Test
	public void testGetName(){		  
		assertEquals("Andy Murray",testercontact.getName());
	}
	
	@Test
	public void testGetNotes(){		  
		assertEquals("England",testercontact.getNotes());
	}
	
	@Test
	public void testAddNotes(){		  
		assertEquals("England",testercontact.getNotes());
		testercontact.addNotes("Great Britain");
		assertEquals("Great Britain",testercontact.getNotes());
	}
	
	
	/*
	*These are the tests to ensure that IDs less than zero, can't be passed as arguments.
	*/
	@Test
	public void testTestIDforCase0Constructor1(){
		try {
			Contact TestIDContact = new ContactImpl(0,"Roger Federer","Helvetic Confederation");
		}
		catch (Exception ex1){
			assertEquals("java.lang.IllegalArgumentException",ex1.toString());
		}
	}
	
	@Test
	public void testTestIDforCaseNegativeConstructor1(){
		try {
			Contact TestIDContact = new ContactImpl(-5,"Roger Federer","Helvetic Confederation");
		}
		catch (Exception ex1){
			assertEquals("java.lang.IllegalArgumentException",ex1.toString());
		}
	}
	
	@Test
	public void testTestIDforCase0Constructor2(){
		try {
			Contact TestIDContact = new ContactImpl(0,"Roger Federer");
		}
		catch (Exception ex1){
			assertEquals("java.lang.IllegalArgumentException",ex1.toString());
		}
	}
	
	
	@Test
	public void testTestIDforCaseNegativeConstructor2(){
		try {
			Contact TestIDContact = new ContactImpl(-5,"Roger Federer");
		}
		catch (Exception ex1){
			assertEquals("java.lang.IllegalArgumentException",ex1.toString());
		}
	}
	
	/*
	*These are the tests to ensure that null names and/or notes can't be passed as arguments.
	*/
	@Test
	public void testTestNullforNameConstructor1(){
		String empty=null;
		try {
			Contact TestIDContact = new ContactImpl(31,empty,"Helvetic Confederation");
		}
		catch (Exception ex1){
			assertEquals("java.lang.NullPointerException",ex1.toString());
		}
	}
	
	@Test
	public void testTestNullforNotesConstructor1(){
		String empty=null;
		try {
			Contact TestIDContact = new ContactImpl(31,"Roger Federer",empty);
		}
		catch (Exception ex1){
			assertEquals("java.lang.NullPointerException",ex1.toString());
		}
	}
	
	@Test
	public void testTestNullforNameConstructor2(){
		String empty=null;
		try {
			Contact TestIDContact = new ContactImpl(31,empty);
		}
		catch (Exception ex1){
			assertEquals("java.lang.NullPointerException",ex1.toString());
		}
	}
	
	
	
	
	
	
	
	
	
	

	
	
	
}



import org.junit.*;
import static org.junit.Assert.*;

public class TestContact {

	Contact testercontact;
	
	String nullString=null;
	
	String emptyString="";
	  
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
	public void testSetId(){		  
		assertEquals(31,testercontact.getId());
		((ContactImpl)testercontact).setId(45);
		assertEquals(45,testercontact.getId());
	}
	
	
	@Test
	public void testSetName(){		  
		assertEquals("Andy Murray",testercontact.getName());
		((ContactImpl)testercontact).setName("Pete Sampras");
		assertEquals("Pete Sampras",testercontact.getName());
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
		try {
			Contact TestIDContact = new ContactImpl(31,nullString,"Helvetic Confederation");
		}
		catch (Exception ex1){
			assertEquals("java.lang.NullPointerException",ex1.toString());
		}
	}
	
	@Test
	public void testTestNullforNotesConstructor1(){
		try {
			Contact TestIDContact = new ContactImpl(31,"Roger Federer",nullString);
		}
		catch (Exception ex1){
			assertEquals("java.lang.NullPointerException",ex1.toString());
		}
	}
	
	@Test
	public void testTestNullforNameConstructor2(){
		try {
			Contact TestIDContact = new ContactImpl(31,nullString);
		}
		catch (Exception ex1){
			assertEquals("java.lang.NullPointerException",ex1.toString());
		}
	}
	
	/*
	*These are the tests to ensure that empty names and/or notes can't be passed as arguments.
	*/
	@Test
	public void testTestEmptyforNameConstructor1(){
		try {
			Contact TestIDContact = new ContactImpl(31,emptyString,"Helvetic Confederation");
		}
		catch (Exception ex1){
			assertEquals("java.lang.NullPointerException",ex1.toString());
		}
	}
	
	@Test
	public void testTestEmptyforNotesConstructor1(){
		try {
			Contact TestIDContact = new ContactImpl(31,"Roger Federer",emptyString);
		}
		catch (Exception ex1){
			assertEquals("java.lang.NullPointerException",ex1.toString());
		}
	}
	
	@Test
	public void testTestEmptyforNameConstructor2(){
		try {
			Contact TestIDContact = new ContactImpl(31,emptyString);
		}
		catch (Exception ex1){
			assertEquals("java.lang.NullPointerException",ex1.toString());
		}
	}
	
	
	
	
	
	
	
	
	
	
	

	
	
	
}



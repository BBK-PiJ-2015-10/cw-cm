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
	
	@Test
	public void testTestIDforCase0(){
		try {
			Contact TestIDContact = new ContactImpl(0,"Roger Federer","Helvetic Confederation");
		}
		catch (Exception ex1){
			assertEquals("java.lang.IllegalArgumentException",ex1.toString());
		}
	}
	
	@Test
	public void testTestIDforCaseNegative(){
		try {
			Contact TestIDContact = new ContactImpl(-5,"Roger Federer","Helvetic Confederation");
		}
		catch (Exception ex1){
			assertEquals("java.lang.IllegalArgumentException",ex1.toString());
		}
	}
	

	
	
	
}



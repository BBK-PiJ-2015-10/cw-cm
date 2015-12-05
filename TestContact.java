import org.junit.*;
import static org.junit.Assert.*;

public class TestContact {

	Contact testercontact;
	  
	@Before
	public void setup(){
		testercontact = new ContactImpl("Andy Murray");
	}
	
	@Test
	public void testGetId(){		  
		assertEquals(0,testercontact.getId());
	}
	
	@Test
	public void testGetName(){		  
		assertEquals("Andy Murray",testercontact.getName());
	}
	
	@Test
	public void testGetNotes(){		  
		assertEquals(null,testercontact.getNotes());
	}
	
	@Test
	public void testAddNotes(){		  
		assertEquals(null,testercontact.getNotes());
		testercontact.addNotes("Great Britain");
		assertEquals("Great Britain",testercontact.getNotes());
	}
	
}



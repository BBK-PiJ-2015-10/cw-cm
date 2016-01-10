import java.io.Serializable;
/**
* This is the implementation {@see Contact} with some additional methods.
*
* @author YAP
*/
public class ContactImpl implements Contact, Serializable {

	/**
	* This is the field that will store each Contact id, it is a unique field.
	*/
	private int id;
	
	/**
	* This is the field that will store each Contact name (First and Last Name).
	*/
	private String name;
	
	/**
	* This is the field to host any relevant notes about the contact.
	*/
	private String notes;
	
	/**
	* This is one of the Constructors for the ContactImpl class.
	* The id must be greater than zero, otherwise IllegalArgumentException is thrown
	* via the testId method call. Similarly, if the name and notes passed are null,
	* a NullPointerException is thrown.
	*
	*@throws IllegalArgumentException if id is less than zero.
	*@throws NullPointerException if name or notes are null.
	*/
	public ContactImpl(int id, String name, String notes){
		    testId(id);
			setId(id);
			testNull(name);
			setName(name);
			testNull(notes);
			addNotes(notes);
	}
	
	/**
	* This is one of the Constructors for the ContactImpl class.
	* The id must be greater than zero, otherwise IllegalArgumentException is thrown
	* via the testId method call. Similarly, if the name is null,
	* a NullPointerException is thrown.
	*
	*@throws IllegalArgumentException if id is less than zero.
	*@throws NullPointerException if name is null.
	*/
	public ContactImpl(int id, String name){
		    testId(id);
			setId(id);
			testNull(name);
			setName(name);
	}
	
	/**
	* This is an auxiliary method that test if an int value is negative or zero.
	* @throws IllegalArgumentException if value passed is negative or zero.
	* This method is to be used in the Constructor methods for ContactImpl.
    */
	public void testId(int id){
		if (id <=0 ){
			throw new IllegalArgumentException();
		}		
	}
	
	/**
	* This is an auxiliary method that test if a String value is null or empty
	* and that it contains at least one letter.
	* @throws IllegalArgumentException if value passed is null or negative.
	* This method is to be used in the Constructor methods for ContactImpl.
	* Leveraring a regular expression to test that it contains at least 1 letter.
    */
	public void testNull (String input ){
	    if ( (input == null) || (input.matches(".*[a-zA-Z]+.*")==false) ){
			throw new NullPointerException();
		}
	}
	
	/**
	* Implementation of method from {@see Contact} interface based on its specifications.
	*/
	@Override
	public int getId(){
		return id;
	}
	
	/**
	* This is auxialiary method to set an Id for a contact. This is leveraged in the Constructor
	* method for ContactImpl.	
	*/
	public void setId(int id){
		this.id=id;
	}
	
	/**
	* Implementation of method from {@see Contact} interface based on its specifications.
	*/
	@Override
	public String getName(){
		return this.name;
	}
	
	/**
	* This is auxialiary method to set the name for a contact. This is leveraged in the Constructor
	* method for ContactImpl.	
	*/
	public void setName(String name){
		this.name = name;
	}
	
	/**
	* Implementation of method from {@see Contact} interface based on its specifications.
	*/
	@Override
	public String getNotes(){
		return this.notes;
	}
	
	/**
	* Implementation of method from {@see Contact} interface based on its specifications.
	*/
	public void addNotes(String notes){
		this.notes = notes;
	}


}
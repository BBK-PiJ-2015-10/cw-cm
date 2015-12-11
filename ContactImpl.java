/**
* This is the implementation of the Contact Interface.
*
* @see Contact for details on method definitions.
*
* @author YAP
*/
public class ContactImpl implements Contact {

	/*
	* This is the field that will store each Contact id, it is a unique field.
	*/
	private int id;
	
	/*
	* This is the field that will store each Contact name (First and Last Name).
	*/
	private String name;
	
	/*
	* This is the field to host any relevant notes about the contact.
	*/
	private String notes;
	
	/*
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
			this.id=id;
			testNull(name);
			this.name = name;
			testNull(notes);
			this.notes = notes;
	}
	
	
	/*
	*  The ids for each contact can't be 0 or negatives. This method is to be used
	*  in the Constructor methods for ContactImpl.
    */
	public void testId(int id){
		if (id <=0 ){
			throw new IllegalArgumentException();
		}		
	}
	
	/*
	*  This method is to be used in the Constructor to ensure that neither name 
	*  or notes are null.
	*/
	public void testNull (String input ){
	    if (input == null){
			throw new NullPointerException();
		}
	}
	
	/*
	* @see Contact for details on method definition.
	*/
	@Override
	public int getId(){
		return id;
	}
	
	/*
	* @see Contact for details on method definition.
	*/
	public String getName(){
		return this.name;
	}
	
	/*
	* @see Contact for details on method definition.
	*/
	public String getNotes(){
		return this.notes;
	}
	
	/*
	* @see Contact for details on method definition.
	*/
	public void addNotes(String notes){
		this.notes = notes;
	}


}
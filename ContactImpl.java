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
	
	public ContactImpl(String name){
		this.name = name;
		this.notes = null;
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
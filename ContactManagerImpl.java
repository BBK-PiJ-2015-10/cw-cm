import java.util.Calendar;
import java.util.List;
import java.util.Set;
//import java.util.ArrayList;
import java.util.LinkedList;

/*
*  This is the implementation of ContactManager using LinkedList.
*
*/
public class ContactManagerImpl implements ContactManager {
	
	/*
	* This field keeps track of the # of contacts added to the Contact Manager. This is the generator
	* of unique IDs for each contact added to the list.
	*/
	private int contactidcount =0;
	
	
	/*
	* This field has the list of all Contacts added to the ContactManager.
	*/
	private List<Contact> contactlist;
	
	/*
	* Contructor method it initializes the list.
	*/
	public ContactManagerImpl (){
		this.contactlist = new LinkedList<Contact>();
	}
	
	/*
	* Implementation of method from interface, it leverages the contactidcount to generate unique IDs.
	*/
	@Override
	public int addNewContact (String name, String notes){
			Contact newContact = new ContactImpl(contactidcount+1,name,notes);
			contactidcount ++;
			contactlist.add(newContact);		
		return contactidcount;
	}
	
	/*
	* This is an auxiliary method that displays the size of the list of contacts in the contact manager. To 
	* determine if it needs to be kept or not in the future.
	*/
	public int getsizeofcontactlist(){
		return contactlist.size();
	}
	
	

}
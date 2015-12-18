import java.util.Calendar;
import java.util.List;
import java.util.Set;
//import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;

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
	* This field keeps track of the # of meetings added to the Contact Manager. This is the generator
	* of unique IDs for each meeting added to the list.
	*/
	private int meetingidcount =0;
	
	/*
	* This field captures the current date of the Calendar Manager. 
	*/
	private Calendar currentdate;
	
	/*
	* This field has the list of all Contacts added to the ContactManager.
	*/
	private List<Contact> contactlist;
		
	/*
	* This field has the list of all Contacts added to the ContactManager.
	*/
	private List<FutureMeeting> futuremeetinglist;
	
	/*
	* Contructor method it initializes the list.
	*/
	public ContactManagerImpl (){
		this.contactlist = new LinkedList<Contact>();
		this.futuremeetinglist = new LinkedList<FutureMeeting>();
	}
	
	/*
	* This is a setter method for the current date in the CalendarManager.
	*/
	public void setCurrentDate(Calendar currentdate){
		this.currentdate = currentdate;
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
	* Implementation of method from interface.
	* TestNull method being leveraged to throw NullPointerException in case of null input.
	* trim().isEmpty() methods being leveraged to test if input is an empty String or a
	* String of white spaces.
	*/
	@Override
	public Set<Contact> getContacts(String name){
		testNull(name);
		Set<Contact> result = new HashSet<Contact>();
		if (name.trim().isEmpty()){
			for (int i=0; i< contactlist.size();i++){
				result.add(contactlist.get(i));
			}		
		}
		else {
			for (int i=0; i< contactlist.size();i++){
				if (contactlist.get(i).getName().contains(name)) {	
				result.add(contactlist.get(i));
				}
			}
		}
		return result;
	}
	
	/*
	* This is an auxiliary method that displays the size of the list of contacts in the contact manager. To 
	* determine if it needs to be kept or not in the future.
	*/
	public int getsizeofcontactlist(){
		//return contactlist.size();
		return contactidcount;
	}
	
	/*
	* This is an NullPointerException method to be leveraged in the getContact method.
	*/
	public void testNull (String input ){
	    if ( input == null) {
			throw new NullPointerException();
		}
	}
	
	public void testNull (Calendar input ){
	    if ( input == null) {
			throw new NullPointerException();
		}
	}
	
	public void testNull (Set<Contact> input ){
	    if ( input == null) {
			throw new NullPointerException();
		}
	}
	
	public void testNull (Integer id){
		if ( id == null) {
			throw new IllegalArgumentException();
		}
	}
	
	/*
	*  This method compares the date being input versus the current date on the CalendarManager.
	*  If the input date in earlier than current date, then an IllegalArgumentException is thrown.
	*  This method is to be used within the int addFutureMeeting(Set<Contact> contacts, Calendar date)
	*  method.
	*/
	public void testDate (Calendar input){
		if (input.before(currentdate)){
			throw new IllegalArgumentException();
		}
	}
	
	//Work in process
	public Set<Contact> getContacts(int id){
		boolean invalidID = true;
		Set<Contact> result = new HashSet<Contact>();
		for (int i=0; i< contactlist.size();i++){
			if (contactlist.get(i).getId() == id) {	
			result.add(contactlist.get(i));
			invalidID = false;
			}
		}
		if (invalidID){
			throw new IllegalArgumentException();
		}
		return result;
	}
	
	//work in process
	public Set<Contact> getContacts(List<Integer> ids){
		Set<Contact> result = new HashSet<Contact>();
		for (int i=0; i < ids.size();i++){
			Set<Contact> temp = getContacts(ids.get(i).intValue());
			for (Contact eachContact : temp){
				result.add(eachContact);
			}
		}
		return result;
	}
	
	
	//Need to delete this later.
	public void printContact (Set<Contact> contacts){
		for (Contact eachContact : contacts){
			int eachContactid = eachContact.getId();
			if (eachContactid >0 && eachContactid<=contactidcount){	
			    System.out.println("This is a valid contact " +eachContact.getId());
			}
			else {
				System.out.println("This is an invalid contact " +eachContact.getId());
			}
		}
	}
	
	
	
	
	
	
	//work in process
	public Set<Contact> getContacts(int... ids){
		Set<Contact> result = new HashSet<Contact>();
		return result;
	}
	
	
	/*
	* This an auxiliary method to test if all Set<Contacts> are in the contact list. If 
	* they all are, then return is true, if not return is false.
	*/
	public boolean validContact (Set<Contact> contacts){
		boolean result = true;
		for (Contact eachContact : contacts){
			int eachContactid = eachContact.getId();
			if (eachContactid >0 && eachContactid<=contactidcount){	
			}
			else {
				result = false;
			}
		}
		return result;
	}
	
	/*
	* This is an IllegalArgumentException thrower in case that input pass is false.
	*/
	public void validEvaluator (boolean input){
		if (input == false){
			throw new IllegalArgumentException();
		}
	}
	
	/*
	* The first test ensures that if pastdate is entered, then InvalidArgumentException is thrown.
	* the second and third tests ensures that if Null dates or contacts are entered NullPointerExceptions
	* are thrown.
	* validEvaluator throws an InvalidArgumentException if any of the contacts are not in the contact list.
	*/
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date){
		testDate(date);
		testNull(date);
		testNull(contacts);
		validEvaluator(validContact(contacts));
		FutureMeeting newMeeting = new FutureMeetingImpl(meetingidcount+1,date,contacts);
		meetingidcount ++;
		futuremeetinglist.add(newMeeting);		
		return meetingidcount;
	}
	
	//This is for testing only
	public List<Contact> getContactList(){
		return contactlist;
	}
	

}
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
* A class to manage your contacts and meetings.
*
*/


public interface ContactManager {
	
	/**
	* Create a new contact with the specified name and notes.
	* 
	* @param name the name of the contact.
	* @param notes notes to be added about the contact.
	* @return the ID for the new contact
	* @throws IllegalArgument if the name or the notes are empty strings.
	* @throws NullPointerException if the name or the notes are nulll.
	*
	*/
	int addNewContact (String name, String notes);
	
	/**
	* Returns a list with the contacts whose name contains that string.
	*
	* If the string is the empty string, this method returns the set that
	* contains all current contacts.
	* 
	* @param name the string to search for
	* @return a list with the contacts whose name contains that string
	* @throw NullPointerException if the parameter is null
	*
	*/
	Set<Contact> getContacts(String name);
	
	/**
	* Returns a list containing the contacts that corresponds to the IDs
	* Note that this method can be used to retrieve just one contact by passing only one ID.
	*
	* @param ids an arbitrary number of contact IDs
	* @return a list containing the contacts that corresponds to the IDs
	* @throws IllegaArgumentException if no IDs are provided or if any of the provided
	* IDs does not correspond to real contact.
	*
	*/
	Set<Contact> getContacts(int id);
	
	Set<Contact> getContacts(List<Integer> ids);
	
	Set<Contact> getContacts(int... ids);

	//int addFutureMeeting (Set<Contact> contacts, Calendar date);
	
	//PastMeeting getPastMeeting(int id);
	
	//FutureMeeting getFutureMeeting(int id);
	
	//Meeting getMeeting(int id);
	
	//List<Meeting> getFutureMeetingList(Contact contact);
	
	//List<Meeting> getFutureMeetingList(Calendar date);
	
	//List<Meeting> getPastMeetingList(Contact contact);
	
	//void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text);
	
	//void addMeetingNotes(int id, String txt);
	
	//void addNewContact (String name, String notes);
	
	//Set<Contact> getContacts (int ids);
	
	//Set<Contact> getContacts(String name);
	
	//void flush();
		
	
}
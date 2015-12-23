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
	
	
	/**
	* Add a meeting to be held in the future.
	*
	* An ID is returned when the meeting is put into the system. This ID
	* must be possitive and non-zero.
	*
	* @param contacts a list of the contacts that will participate in the meeting.
	* @param date the date on which the meeting will take place.
	* @return the ID for the meeting.
	* @throws IllegaArgumentException if the meeting is set for a time
	* in the past, or if any contact is unknown / non-existent.
	* @throws NullPointerException if the Contact or the date are null.
	*
	*/
	int addFutureMeeting(Set<Contact> contacts, Calendar date);
	
	/**
	* Returns the FUTURE meeting with the requested ID, or null if there is none.
	* 
	* @param id the ID for the meeting.
	* @return the meeting with the requested ID, or null if there is none.
	* @throws IllegaArgumentException if there is a meeting with that ID happening in the past.
	*
	*/
	FutureMeeting getFutureMeeting(int id);
	
	/**
	* Add notes to a meeting
	* 
	* This method is used when a future meeting takes place, and is
	* then converted to a past meeting (with notes) and returned.
	*
	* It can also be used to add notes to a past meeting at a later date.
	*
	* @param id the ID of the meeting.
	* @param text messages to be added about the meeting.
	* @throws IllegaArgumentException if the meeting doesn't existent.
	* @throws IllegalStateException if the meeting is set for a date in the future.
	* @throws NullPointerException if the notes are null.
	*
	*/
	PastMeeting addMeetingNotes (int id, String text);
	
	/**
	* Returns the meeting with the requested ID.
	* 
	* @param id the ID of the meeting.
	* @return the meeting with the requested ID, or null if there is none.
	*
	*/
	Meeting getMeeting(int id);
	
	
	/**
	* Returns the list of meetings that are scheduled for, or that took
	* place on, the specified date.
	*
	* If there are none, the returned list will be empty. Otherwise,
	* the list will be chrnologically sorted and will not contain any
	* duplicates.
	*
	* @param date the date
	* @return the list of meetings
	* @throws NullPointerException if the date is null
	*/
	List<Meeting> getMeetingListOn(Calendar date);
	
	/**
	* Returns the list of future meetings scheduled for this contact.
	*
	* If there are none, the returned list will be empty. Otherwise,
	* the list will be chrnologically sorted and will not contain any
	* duplicates.
	*
	* @param contact one of the user contacts
	* @return the list of future meeting(s) scheduled with this contact (maybe empty).
	* @throws IllegaArgumentException if the contact does not exist.
	* @throws NullPointerException if the contact is null.
	*/
	List<Meeting> getFutureMeetingList(Contact contact);
	

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
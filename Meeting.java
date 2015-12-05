import java.util.Calendar;
import java.util.Set;
/**
* A class tha represents meetings
*
* Meetings have unique IDs, scheduled date and a list of participating contacts
*
*/
public interface Meeting {
	
	/**
	 * Returns the id of the meeting
	 *
	 *@return the id of the meeting
	 */
	int getId();
	
	/**
	 * Returns the date of the meeting
	 *
	 *@return the date of the meeting
	 */	
	Calendar getDate();
	
	/**
	 * Return the details of the people that attended the meeting.
	 *
	 * The list contains a minimum of one contact (if there were just two people:
	 * the user and the contact) and may contain an arbitrary number of them.
	 *
	 *@return the details of the people that attended this meeting.
	 */
	Set<Contact> getContacts();

}
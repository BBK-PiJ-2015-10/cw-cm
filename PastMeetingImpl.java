import java.util.Calendar;
import java.util.Set;
import java.io.Serializable;

/**
* This is the implementation {@see PastMeeting} with some additional methods.
*
* @author YAP
*/
public class PastMeetingImpl extends MeetingImpl implements PastMeeting, Serializable {
	
	/**
	* This is to hold the meeting notes.
	*/
	private String notes;
	
	/**
	* This is the Constructor for PastMeetingImpl.
	* The notes must not be null or empty, otherwise IllegalArgumentException is thrown.
	* Leveraging testNoteNull method to perform the null test on notes.
	* @throws NullPointerException if notes are null or empty.
	*/
	public PastMeetingImpl(int id, Calendar date, Set<Contact> participants, String notes){
		super(id,date,participants);
		testNotesNull(notes);
		setNotes(notes);
	}
	
	/**
	* This is an auxiliary method that test if a string value is null, empty, or doesn't 
	* contain at least one letter.
	* @throws IllegalArgumentException if value passed is null, empty, or doesn't contain
	* at least one letter.
	* This method is to be used in the Constructor methods for PastMeetingImpl.
    */
	public void testNotesNull (String notes){
	    if ( (notes == null) || (notes.matches(".*[a-zA-Z]+.*")==false) || (notes.trim().isEmpty()==true)  {
			throw new NullPointerException();
		}
	}
	
	/**
	* Implementation of method from {@see PastMeeting} interface based on its specifications.
	*/
	@Override
	public String getNotes(){
		return this.notes;
	}
	
	/**
	* This is auxialiary method to set the notes for a PastMeeting. This is leveraged in the Constructor
	* method for PastMeetingImpl.	
	*/
	public void setNotes(String notes){
		this.notes=notes;
	}
	
	/**
	* This is a method to append notes to the existing notes field in a meeting.
	*/
	public void appendNotes(String notes){
		String result = this.notes;
		result = result + " . " + notes;
		this.notes = result;
	}
	
}

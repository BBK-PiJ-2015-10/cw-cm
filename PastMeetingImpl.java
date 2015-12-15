import java.util.Calendar;
import java.util.Set;

public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
	
	/*
	* This is to hold the meeting notes.
	*/
	private String notes;
	
	/*
	* This is the Constructor for PastMeetingImpl.
	* The notes must not be null or empty, otherwise IllegalArgumentException is thrown
	*
	*@throws NullPointerException if notes are null or empty.
	*/
	public PastMeetingImpl(int id, Calendar date, Set<Contact> participants, String notes){
		super(id,date,participants);
		testNotesNull(notes);
		setNotes(notes);
	}
	
	/*
	*  Notes can't be null or empty or not contain at least on alphabet letter. This method is to be leveraged in the Constructor method.
	*/
	public void testNotesNull (String notes){
	    if ( (notes == null) || (notes.matches(".*[a-zA-Z]+.*")==false) ) {
			throw new NullPointerException();
		}
	}
	
	
	/*
	*  This is display the notes of the meeting.
	*/
	@Override
	public String getNotes(){
		return this.notes;
	}
	
	/*
	*  This is to manually set the notes of the meeting.
	*/
	public void setNotes(String notes){
		this.notes=notes;
	}

}

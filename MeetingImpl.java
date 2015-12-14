import java.util.Calendar;
import java.util.Set;

public abstract class MeetingImpl implements Meeting {

	/*
	* This is to hold the unique meeting id.
	*/
	private int id;
	
	/*
	* This is to hold the date and time of the meeting.
	*
	*/
	private Calendar date;
	
	
	/*
	* This is to hold the set of participants for the meeting.
	*
	*/
	private Set<Contact> participants;
	
	/*
	* This is the Constructor for MeetingImpl.
	* The id must be greater than zero, otherwise IllegalArgumentException is thrown
	* via the testId method call. Similarly, if the date or participants passed are null,
	* a NullPointerException is thrown.
	*
	*@throws IllegalArgumentException if id is less than zero.
	*@throws NullPointerException if name or participants are null.
	*/
	public MeetingImpl(int id, Calendar date, Set<Contact> participants){
		testId(id);
		this.id=id;
		testNull(date);
		this.date=date;
		testNull(participants);
		this.participants=participants;
	}
	
	/*
	*  The ids for each meeting can't be 0 or negatives. This method is to be used
	*  in the Constructor methods for MeetingImpl.
    */
	public void testId(int id){
		if (id <=0 ){
			throw new IllegalArgumentException();
		}		
	}
	
	/*
	*  Dates can't be null. This method is to be leveraged in the Constructor method.
	*/
	public void testNull (Calendar date ){
	    if ( date == null ){
			throw new NullPointerException();
		}
	}
		
	
	/*
	*  Participants list can't be null. This method is to be leveraged in the Constructor method.
	*/
	public void testNull (Set<Contact> participants ){
	    if ( (participants == null) || (participants.isEmpty()==true) ) {
			throw new NullPointerException();
		}
	}
	
	/*
	* Returns id of this meeting.
	*/
	public int getId(){
		return this.id;
	}
	
	/*
	* Sets id of this meeting.
	*/
	public void setId(int id){
		this.id=id;
	}
	
	
	/*
	* Returns date of this meeting.
	*/
	public Calendar getDate(){
		return this.date;
	}
	
	/*
	* Sets date of this meeting.
	*/
	public void setDate(Calendar date){
		this.date=date;
	}
	
	/*
	* Returns Set of Contacts of this meeting.
	*
	*/
	public Set<Contact> getContacts(){
		return this.participants;
	}
	
	/*
	* Sets the Participants for this meeting.
	*/
	public void setParticipants(Set<Contact> participants){
		this.participants=participants;
	}
	
	

	
}
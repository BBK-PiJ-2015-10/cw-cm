import java.util.Calendar;
import java.util.Set;
import java.io.Serializable;

/**
* An implementation of Meeting with additional methods.
*
*/
public abstract class MeetingImpl implements Meeting, Serializable {

	/**
	* This is to hold the unique meeting id.
	*/
	private int id;
	
	/**
	* This is to hold the date and time of the meeting.
	*/
	private Calendar date;
	
	
	/**
	* This is to hold the set of participants for the meeting.
	*/
	private Set<Contact> participants;
	
	/**
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
		setId(id);
		testNull(date);
		setDate(date);
		testNull(participants);
		setParticipants(participants);
	}
	
	/**
	* This is an auxiliary method that test if a value is negative or zero.
	* @throws IllegalArgumentException if value passed is negative or zero.
	* This method is to be used in the Constructor methods for MeetingImpl.
    */
	public void testId(int id){
		if (id <=0 ){
			throw new IllegalArgumentException();
		}		
	}
	
	/**  
	* This is an auxiliary method that test if a Calendar input type value is null.
	* @throws NullPointerException if value passed is null.
	* This method is to be used in the Constructor methods for MeetingImpl to test dates.
	*/
	public void testNull (Calendar date ){
	    if ( date == null ){
			throw new NullPointerException();
		}
	}
		
	
	/**
	* This is an auxiliary method that test if a Set<Contact> input type value is null or empty.
	* @throws NullPointerException if value passed is null or empty.
	* This method is to be used in the Constructor methods for MeetingImpl to test dates.
	*/
	public void testNull (Set<Contact> participants ){
	    if ( (participants == null) || (participants.isEmpty()==true) ) {
			throw new NullPointerException();
		}
	}
	
	/**
	* Implementation of method from {@see Meeting} interface based on its specifications.
	*/
	@Override
	public int getId(){
		return this.id;
	}
	
	/**
	* This is auxialiary method to set an Id for a meeting. This is leveraged in the Constructor
	* method for MeetingImpl.	
	*/
	public void setId(int id){
		this.id=id;
	}
	
	/**
	* Implementation of method from {@see Meeting} interface based on its specifications.
	*/
	@Override
	public Calendar getDate(){
		return this.date;
	}
	
	/**
	* This is auxialiary method to set the date for a meeting. This is leveraged in the Constructor
	* method for MeetingImpl.	
	*/
	public void setDate(Calendar date){
		this.date=date;
	}
	
	/**
	* Implementation of method from {@see Meeting} interface based on its specifications.
	* Refer to {@see Meeting} interface for specifications details.
	*/
	@Override
	public Set<Contact> getContacts(){
		return this.participants;
	}
	
	/**
	* This is auxialiary method to set the participants for a meeting. This is leveraged in the Constructor
	* method for MeetingImpl.	
	*/
	public void setParticipants(Set<Contact> participants){
		this.participants=participants;
	}
	
	
}
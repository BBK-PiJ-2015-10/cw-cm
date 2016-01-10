import java.util.Calendar;
import java.util.Set;
import java.io.Serializable;

/**
* This is the implementation {@see FutureMeeting} with some additional methods.
*
* @author YAP
*/
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting, Serializable {
		
	/**
	* This is the Constructor for FutureMeetingImpl.
	*/
	public FutureMeetingImpl(int id, Calendar date, Set<Contact> participants){
		super(id,date,participants);
	}

}

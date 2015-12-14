import java.util.Calendar;
import java.util.Set;

public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {
		
	/*
	* This is the Constructor for FutureMeetingImpl.
	*/
	public FutureMeetingImpl(int id, Calendar date, Set<Contact> participants){
		super(id,date,participants);
	}

}

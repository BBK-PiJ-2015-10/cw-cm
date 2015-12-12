import java.util.Calendar;
import java.util.Set;

public class MeetingImpl implements Meeting {

	private int id;
	
	private Calendar date;
	
	private Set<Contact> participants;
	
	public MeetingImpl(int id, Calendar date, Set<Contact> participants){
		this.id=id;
		this.date=date;
		this.participants=participants;
	}
	
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public Calendar getDate(){
		return this.date;
	}
	
	public void setDate(Calendar date){
		this.date=date;
	}
	
	public Set<Contact> getContacts(){
		return this.participants;
	}
	
	
	public void setParticipants(Set<Contact> participants){
		this.participants=participants;
	}
	
	

	
}
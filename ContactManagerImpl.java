import java.util.Calendar;
import java.util.List;
import java.util.Set;
//import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Comparator;
import java.util.Collections;

/*
*  This is the implementation of ContactManager using LinkedList.
*
*/
public class ContactManagerImpl implements ContactManager {
	
	/*
	* This field keeps track of the # of contacts added to the Contact Manager. This is the generator
	* of unique IDs for each contact added to the list.
	*/
	private int contactidcount =0;
	
	/*
	* This field keeps track of the # of meetings added to the Contact Manager. This is the generator
	* of unique IDs for each meeting added to the list.
	*/
	private int meetingidcount =0;
	
	/*
	* This field captures the current date of the Calendar Manager. 
	*/
	private Calendar currentdate;
	
	/*
	* This field has the list of all Contacts added to the ContactManager.
	*/
	private List<Contact> contactlist;
		
	/*
	* This field has the list of all Contacts added to the ContactManager.
	*/
	private List<FutureMeeting> meetinglist;
	
	private List<PastMeeting> pastmeetinglist;
	
	private List<Integer> pastmeetingidlist;
	
	/*
	* Contructor method it initializes the list.
	*/
	public ContactManagerImpl (){
		this.contactlist = new LinkedList<Contact>();
		this.meetinglist = new LinkedList<FutureMeeting>();
		this.pastmeetinglist = new LinkedList<PastMeeting>();
		this.pastmeetingidlist = new LinkedList<Integer>();
	}
	
	/*
	* This is a setter method for the current date in the CalendarManager.
	*/
	public void setCurrentDate(Calendar currentdate){
		this.currentdate = currentdate;
	}
	
	
	/*
	* Implementation of method from interface, it leverages the contactidcount to generate unique IDs.
	*/
	@Override
	public int addNewContact (String name, String notes){
		Contact newContact = new ContactImpl(contactidcount+1,name,notes);
		contactidcount ++;
		contactlist.add(newContact);		
		return contactidcount;
	}
	
	/*
	* Implementation of method from interface.
	* TestNull method being leveraged to throw NullPointerException in case of null input.
	* trim().isEmpty() methods being leveraged to test if input is an empty String or a
	* String of white spaces.
	*/
	@Override
	public Set<Contact> getContacts(String name){
		testNull(name);
		Set<Contact> result = new HashSet<Contact>();
		if (name.trim().isEmpty()){
			for (int i=0; i< contactlist.size();i++){
				result.add(contactlist.get(i));
			}		
		}
		else {
			for (int i=0; i< contactlist.size();i++){
				if (contactlist.get(i).getName().contains(name)) {	
				result.add(contactlist.get(i));
				}
			}
		}
		return result;
	}
	
	/*
	* This is an auxiliary method that displays the size of the list of contacts in the contact manager. To 
	* determine if it needs to be kept or not in the future.
	*/
	public int getsizeofcontactlist(){
		return contactlist.size();
		//return contactidcount;
	}
	
	public int getSizeofMeetingList(){
		return meetinglist.size();
	}
	
	public int getSizeofPastMeetingList(){
		return pastmeetinglist.size();
	}
	
	
	/*
	* This is an NullPointerException method to be leveraged in the getContact method.
	*/
	public void testNull (String input ){
	    if ( input == null) {
			throw new NullPointerException();
		}
	}
	
	public void testNull (Calendar input ){
	    if ( input == null) {
			throw new NullPointerException();
		}
	}
	
	public void testNull (Set<Contact> input ){
	    if ( input == null) {
			throw new NullPointerException();
		}
	}
	
	public void testNull (Integer id){
		if ( id == null) {
			throw new IllegalArgumentException();
		}
	}
	
	/*
	*  This method compares the date being input versus the current date on the CalendarManager.
	*  If the input date in earlier than current date, then an IllegalArgumentException is thrown.
	*  This method is to be used within the int addFutureMeeting(Set<Contact> contacts, Calendar date)
	*  method.
	*/
	public void testDate (Calendar input){
		if (input.before(currentdate)){
			throw new IllegalArgumentException();
		}
	}
	
	
	/*
	*  This method compares the date being input versus the current date on the CalendarManager.
	*  If the input date is later than current date, then an IllegalStateException is thrown.
	*  This method is to be used within the int addMeetingNotes(int id, String txt) method
	*/
	public void testDateInPast (Calendar input){
		if (input.after(currentdate)){
			throw new IllegalStateException();
		}
	}
		
	//Work in process
	public Set<Contact> getContacts(int id){
		boolean invalidID = true;
		Set<Contact> result = new HashSet<Contact>();
		for (int i=0; i< contactlist.size();i++){
			if (contactlist.get(i).getId() == id) {	
				result.add(contactlist.get(i));
				invalidID = false;
			}
		}
		if (invalidID){
			throw new IllegalArgumentException();
		}
		return result;
	}
	
	//work in process
	public Set<Contact> getContacts(List<Integer> ids){
		Set<Contact> result = new HashSet<Contact>();
		for (int i=0; i < ids.size();i++){
			Set<Contact> temp = getContacts(ids.get(i).intValue());
			for (Contact eachContact : temp){
				result.add(eachContact);
			}
		}
		return result;
	}
	
	
	//work in process
	public Set<Contact> getContacts(int... ids){
		Set<Contact> result = new HashSet<Contact>();
		return result;
	}
	
	
	/*
	* This an auxiliary method to test if all Set<Contacts> are in the contact list. If 
	* they all are, then return is true, if not return is false.
	*/
	public boolean validContact (Set<Contact> contacts){
		boolean result = true;
		for (Contact eachContact : contacts){
			int eachContactid = eachContact.getId();
			if (eachContactid >0 && eachContactid<=contactidcount){	
			}
			else {
				result = false;
			}
		}
		return result;
	}
	
	/*
	* This is an IllegalArgumentException thrower in case that input being passed is false.
	* This method is being leveraged on getFutureMeeting(), addFutureMeeting() methods.
	*/
	public void validEvaluator (boolean input){
		if (input == false){
			throw new IllegalArgumentException();
		}
	}
	
	/*
	* The first test ensures that if pastdate is entered, then InvalidArgumentException is thrown.
	* the second and third tests ensures that if Null dates or contacts are entered NullPointerExceptions
	* are thrown.
	* validEvaluator throws an InvalidArgumentException if any of the contacts are not in the contact list.
	*/
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date){
		testDate(date);
		testNull(date);
		testNull(contacts);
		validEvaluator(validContact(contacts));
		FutureMeeting newMeeting = new FutureMeetingImpl(meetingidcount+1,date,contacts);
		meetingidcount ++;
		meetinglist.add(newMeeting);		
		return meetingidcount;
	}
	
	//This is for testing only
	public List<Contact> getContactList(){
		return contactlist;
	}
	
	/*
	* This method compares a particular meeting id with the list of meeting ids of PastMeetings.
	* Returns false if the meeting id entered is in the PastMeeting list of IDs.
	* The method is to be used in the getFutureMeeting method.
	*/
	public boolean testNotInPastMeetingList (int id){
		boolean result = true;
		for (int i=0; i< pastmeetinglist.size();i++){
			if (pastmeetinglist.get(i).getId() == id) {	
				result = false;
			}	
		}
		return result;	
	}
	
	public boolean testInMeetingList (int id){
		boolean result = false;
		for (int i=0; i< meetinglist.size();i++){
			if (meetinglist.get(i).getId() == id) {	
				result = true;
			}	
		}
		return result;	
	}
	
	
	/*
	* Implementation of method from interface.
	* testDate will throw an IllegalArgumentException if meeting date is before the CurrentDate of ContactManager.
	* If no meeting is found under that ID, null is returned.
	*/
	@Override
	public FutureMeeting getFutureMeeting(int id){
		for (int i=0; i< meetinglist.size();i++){
			if (meetinglist.get(i).getId() == id) {	
					testDate(meetinglist.get(i).getDate());
					return meetinglist.get(i);
			}	
		}
		return null;
	}
	
	/*
	* Implementation of method from interface.
	* testInMeetinglist will return true if a meeting with that id is found in the meetinglist.
	* validEvaluator will throw an IllegalArgumentException if the value passed by testinMeeting is false. Meaning,
	* if the is is not found in the meeting id.
	* testDate will throw an IllegalStateException if meeting date is after the CurrentDate of ContactManager.
	* If notes are null or empty a NullPointerException is thrown, this is coded directly in the PastMeetingImpl 
	* constructor method.
	* If any input is invalid (date, id, notes) this method returns null and nothing is added to the PastMeetingList.
	*/
	@Override
	public PastMeeting addMeetingNotes (int id, String notes){
		validEvaluator(testInMeetingList(id));
		if (testInMeetingList(id)){
			for (int i=0; i< meetinglist.size();i++){
				if (meetinglist.get(i).getId() == id) {	
					testDateInPast(meetinglist.get(i).getDate());
					PastMeeting newPastMeeting = new PastMeetingImpl(id,meetinglist.get(i).getDate(),meetinglist.get(i).getContacts(),notes);
					pastmeetinglist.add(newPastMeeting);
					return newPastMeeting;
				}
			}
		}
		return null;
	}
	
	/*
	* Implementation of method from interface.
	* If no id is found it returns null, otherwise it returns the Meeting requested. 
	*/
	@Override
	public Meeting getMeeting (int id){
		for (int i=0; i< meetinglist.size();i++){
			if (meetinglist.get(i).getId() == id) {	
					return meetinglist.get(i);
			}	
		}
		return null;
	}
	
	
	/*
	* This a method to compare if two calendar objects ocurred in the same date. 
	* This method is being leveraged on @see (getMeetingListOn).
	*/
	public boolean sameDate (Calendar input1, Calendar input2){
		boolean result = false;
		if ((input1.get(Calendar.YEAR)==input2.get(Calendar.YEAR)) && 
			    (input1.get(Calendar.MONTH)==input2.get(Calendar.MONTH)) &&
			     (input1.get(Calendar.DAY_OF_MONTH)==input2.get(Calendar.DAY_OF_MONTH))
			) 
			{
				result = true;
			}
			return result;
	}
	
	
	/*
	* This a chronologically list sorter to be leveraged on getMeetingListOn method.
	*/
	public void sortMeetingList(List<Meeting> input){
		Collections.sort(input, (Meeting input1, Meeting input2) ->
			(input1.getDate().compareTo(input2.getDate()))		
		);
	}
	
	
	/*
	* Implement method from interface.
	* testNull(date) will throw a NullPointerException if date is empty
	* sameDate is used to evaluate each date of the meetinglist to the date passed in
	* the argument.
	* sortMeetingList is a sorter based on chronological meeting date.
	*/
	@Override
	public List<Meeting> getMeetingListOn(Calendar date){
		testNull(date);
		List<Meeting> result = new LinkedList<Meeting>();
		for (int i=0; i< meetinglist.size();i++){
			if (sameDate(meetinglist.get(i).getDate(),date)) {	
					result.add(meetinglist.get(i));
			}	
		}
		sortMeetingList(result);
		return result;
	}
	
	
	
	

}
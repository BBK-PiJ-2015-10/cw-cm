import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Comparator;
import java.util.Collections;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.File;

/**
*  This is the implementation of (@see ContactManager) interface with some additional methods.
*
*/
public class ContactManagerImpl implements ContactManager {
	
	/**
	* This field keeps track of the # of contacts added to the Contact Manager. This is the generator
	* of unique IDs for each contact added to the list.
	*/
	private int contactidcount =0;
	
	/**
	* This field keeps track of the # of meetings added to the Contact Manager. This is the generator
	* of unique IDs for each meeting added to the list.
	*/
	private int meetingidcount =0;
	
	/**
	* This field captures the current date of the Calendar Manager. 
	*/
	private Calendar currentdate = Calendar.getInstance();
	
	/**
	* This field has the list of all Contacts added to the ContactManager.
	*/
	private List<Contact> contactlist;
		
	/**
	* This field has the list of FutureMeetings currently in the ContactManager.
	*/
	private List<FutureMeeting> meetinglist;
	
	/**
	* This field has the list of PastMeeting currently in the ContactManager.
	*/
	private List<PastMeeting> pastmeetinglist;
	
	/**
	* This field has the name of the file that saves all data entered into the ContactManager.
	*/
	private File file = new File("ContactManager.ser");
		
	/**
	* Contructor method.
	* It read prior instances of the ContactManager if they exist, if not
	* it inizilizes the list data structures.
	*/
	public ContactManagerImpl (){
		if (file.exists()){
			capture();
		}
		else {
			this.contactlist = new LinkedList<Contact>();
			this.meetinglist = new LinkedList<FutureMeeting>();
			this.pastmeetinglist = new LinkedList<PastMeeting>();
		}
	}
	
	/**
	* This is a setter method for the current date in the CalendarManager.
	* This method was mainly developed for User Testing Purposes, since the default process
	* is that the current date of CalendarManager is synched up with computer internal clock.
	* The flush() method is being called to save the update done to ContactManager to disk.
	*/
	public void setCurrentDate(Calendar currentdate){
		this.currentdate = currentdate;
		this.flush();
	}
	
	/**
	* This method returns the current time in the CalendarManager.
	*/
	public Calendar getCurrentDate(){
		return this.currentdate;
	}
	
	/**
	* Implementation of method from {@see ContactManager} interface based on its specifications.
	* Refer to {@see ContactManager} for specifications details.
	* The flush() method is being called to save the update done to ContactManager to disk.
	*/
	@Override
	public int addNewContact (String name, String notes){
		Contact newContact = new ContactImpl(contactidcount+1,name,notes);
		contactidcount ++;
		contactlist.add(newContact);
		this.flush();
		return contactidcount;
	}
	
	/**
	* Implementation of method from {@see ContactManager} interface based on its specifications.
	* Refer to {@see ContactManager} for specifications details.
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
	
	/**
	* This is an auxiliary method that displays the size of the list of contacts in the contact manager. This
	* is being leverated in {@see TestContactManager } to evaluate testing results across many of the methods 
	* implemented in ContactManagerImpl.
	*/
	public int getsizeofcontactlist(){
		return contactlist.size();
	}
	
	/**
	* This method returns the size of the FutureMeetings in the ContactManager.
	* This is being leverated in {@see TestContactManager } to evaluate testing results across many of the 
	* methods implemented in ContactManagerImpl.
.	*/
	public int getSizeofMeetingList(){
		return meetinglist.size();
	}
	
	/**
	* This method returns the size of the PastMeetings in the ContactManager.
	* This is being leverated in {@see TestContactManager } to evaluate testing results across many of the 
	* methods implemented in ContactManagerImpl.
.	*/
	public int getSizeofPastMeetingList(){
		return pastmeetinglist.size();
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/**
	* Below are 5 testNull methods with slightly different signatures. They are being used to throw the NullPointerException
	* and IllegalArgumentExceptions specified on the different methods of {@see ContactManager } interface.
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
	
	public void testNull (Contact input ){
	    if ( input == null) {
			throw new NullPointerException();
		}
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	*  This method compares the date being entered versus the current date in the CalendarManager.
	*  If the input date in earlier than current date an IllegalArgumentException is thrown.
	*  This method is to be used within the int addFutureMeeting(Set<Contact> contacts, Calendar date)
	*  method.
	*/
	public void testDate (Calendar input){
		if (input.before(currentdate)){
			throw new IllegalArgumentException();
		}
	}
	
	/**
	*  This method compares the date being input versus the current date in the CalendarManager.
	*  If the input date is later than current date, then an IllegalStateException is thrown.
	*  This method is to be used within the int addMeetingNotes(int id, String txt) method.
	*/
	public void testDateInPast (Calendar input){
		if (input.after(currentdate)){
			throw new IllegalStateException();
		}
	}
	
	/**
	* This is an auxiliary method to support the implementation of Set<Contact> getContact(int ids).
	* It evaluates an id provided against the list of IDs in ContactManager. If the ID is not found
	* an IllegalAccessException is thrown. If the ID is found, a Set<Contact> is return with the Contact
	* of the id provided added to the Set<Contact>.
	* @param id of a contact to be evaluated.
	* @param Set<Contact> the Set of Contact to be appended in case the ID provided is valid.
	* @throws IllegalArgumentException if the ID provided doesn't belong to a Contact in the ContactManager.
	* @return a Set<Contact> with the Contact whose ID was provided added to the Set.
	*/
	public Set<Contact> contactEvaluator(int id, Set<Contact> input){
		boolean invalidID = true;
		for (int i=0; i< contactlist.size();i++){
			if (contactlist.get(i).getId() == id) {	
				input.add(contactlist.get(i));
				invalidID = false;
			}
		}
		if (invalidID){
			throw new IllegalArgumentException();
		}
		return input;
	}
	
	
	/**
	* Implementation of method from {@see ContactManager} interface based on its specifications.
	* Refer to {@see ContactManager} for specifications details.
	* Leverages a List<Integer> temp to faciliate the iteration of ids provided.
	* Leverages the method Set<Contact> contactEvaluator(int id, Set<Contact> input)
	* to evaluate if the ID provided is in the ContactManager list.
	*/
	public Set<Contact> getContacts(int... ids){
		List<Integer> temp = new ArrayList<Integer>();
		for (int i=0;i<ids.length;i++){
			temp.add(ids[i]);
		}	
		Set<Contact> result = new HashSet<Contact>();
		for (int i=0;i<temp.size();i++){
			result = contactEvaluator(temp.get(i),result);
		}
		return result;
	}
		
	
	/**
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
	
	/**
	* This is an IllegalArgumentException thrower in case that input being passed is false.
	* This method is being leveraged on getFutureMeeting(), addFutureMeeting() methods.
	*/
	public void validEvaluator (boolean input){
		if (input == false){
			throw new IllegalArgumentException();
		}
	}
	
	/**
	* Implementation of method from {@see ContactManager} interface based on its specifications.
	* Refer to {@see ContactManager} for specifications details.
	* The first test ensures that if pastdate is entered an InvalidArgumentException is thrown.
	* The second and third tests ensures that if Null dates or contacts are entered NullPointerExceptions
	* are thrown.
	* validEvaluator throws an InvalidArgumentException if any of the contacts are not in the contact list.
	* meetingidcount is the counter of unique meeting ids added into ContactManager.
	* The flush() method is being called to save the update done to ContactManager to disk.
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
		this.flush();
		return meetingidcount;
	}
	
	/**
	
	
	* This is an auxiliary method. It returns the List of Contacts in ContactManager. It was 
	* built with the objective of facilitating the testing of ContactManager in the 
	* TestContactManager class.
	*/
	public List<Contact> getContactList(){
		return contactlist;
	}
	
	/**
	* This method compares a particular meeting id with the list of meeting IDs of pastMeetings.
	* Returns false if the meeting id entered is in the pastMeeting list of IDs.
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
	
	/**
	* This method compares a particular meeting id with the list of meeting IDs of meetinglist.
	* Returns true if the meeting id entered is in the meeting list of IDs. This method is to
	* being leveraged across ContactManager.
	*/
	public boolean testInMeetingList (int id){
		boolean result = false;
		for (int i=0; i< meetinglist.size();i++){
			if (meetinglist.get(i).getId() == id) {	
				result = true;
			}	
		}
		return result;	
	}
	
	
	/**
	* Implementation of method from {@see ContactManager} interface based on its specifications.
	* Refer to {@see ContactManager} for specifications details.
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
	
	/**
	* Implementation of method from {@see ContactManager} interface based on its specifications.
	* Refer to {@see ContactManager} for specifications details.
	* testDateInPast will throw an IllegalStateException if the date is in the future.
	* testNull will throw a NullPointerException if any of the arguments are null.
	* validEvaluator and validContact will throw an IllegalArgumentException if the
    * list of contacts being passed is empty or contains a contact that doesn't exist.
    * The flush() method is being called to save the update done to ContactManager to disk.	
	*/
	@Override
	public void addNewPastMeeting (Set<Contact> contacts, Calendar date, String text){
		testDateInPast(date);
		testNull(date);
		testNull(contacts);
		testNull (text);
		validEvaluator(validContact(contacts));		
		PastMeeting newMeeting = new PastMeetingImpl(meetingidcount+1,date,contacts,text);
		pastmeetinglist.add(newMeeting);
		meetingidcount ++;
		this.flush();
	}
	
	/**
	* Implementation of method from {@see ContactManager} interface based on its specifications.
	* Refer to {@see ContactManager} for specifications details.
	* testInMeetinglist will return true if a meeting with that id is found in the meetinglist.
	* validEvaluator will throw an IllegalArgumentException if the value passed by testinMeeting is false. Meaning,
	* if the is is not found in the meeting id.
	* testDate will throw an IllegalStateException if meeting date is after the CurrentDate of ContactManager.
	* If notes are null or empty a NullPointerException is thrown, this is coded directly in the PastMeetingImpl 
	* constructor method.
	* If any input is invalid (date, id, notes) this method returns null and nothing is added to the PastMeetingList.
	* The flush() method is being called to save the update done to ContactManager to disk.
	*/
	@Override
	public PastMeeting addMeetingNotes (int id, String notes){
		if (!testNotInPastMeetingList(id)){
			for (int i=0; i< pastmeetinglist.size();i++){
				if (pastmeetinglist.get(i).getId() == id){
					((PastMeetingImpl)pastmeetinglist.get(i)).appendNotes(notes);
					this.flush();
					return pastmeetinglist.get(i);
				}
			}
		}
		else {
			validEvaluator(testInMeetingList(id));
			if (testInMeetingList(id)){
				for (int i=0; i< meetinglist.size();i++){
					if (meetinglist.get(i).getId() == id) {	
						testDateInPast(meetinglist.get(i).getDate());
						PastMeeting newPastMeeting = new PastMeetingImpl(id,meetinglist.get(i).getDate(),meetinglist.get(i).getContacts(),notes);
						pastmeetinglist.add(newPastMeeting);
						meetinglist.remove(i);
						this.flush();
						return newPastMeeting;
					}
				}
			}
		}
		this.flush();
		return null;
	}
		
	/**
	* Implementation of method from {@see ContactManager} interface based on its specifications.
	* Refer to {@see ContactManager} for specifications details.
	* If no id is found it returns null, otherwise it returns the Meeting requested. 
	*/
	@Override
	public Meeting getMeeting (int id){
		boolean meetinginfuture = false;
		for (int i=0; i< meetinglist.size();i++){
			if (meetinglist.get(i).getId() == id) {	
					meetinginfuture = true;
					return meetinglist.get(i);
			}
		}
		if (!meetinginfuture){
			for (int i=0; i< pastmeetinglist.size();i++){
				if (pastmeetinglist.get(i).getId() == id) {	
						return pastmeetinglist.get(i);
				}
			}
		}
		return null;
	}
	
	
	/**
	* This a method to compare if two calendar objects ocurred in the same date. 
	* This method is being leveraged on getMeetingListOn(Calendar date) method.
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
	
	
	/**
	* This a chronologically list sorter to be leveraged on getMeetingListOn(Calendar date) 
	* method.
	*/
	public void sortMeetingList(List<Meeting> input){
		Collections.sort(input, (Meeting input1, Meeting input2) ->
			(input1.getDate().compareTo(input2.getDate()))		
		);
	}
	
	
	/**
	* This a chronologically list sorter to be leveraged on getPastMeetingListFor(Contact contact) 
	* method.
	*/
	public void sortPastMeetingList(List<PastMeeting> input){
		Collections.sort(input, (PastMeeting input1, PastMeeting input2) ->
			(input1.getDate().compareTo(input2.getDate()))		
		);
	}
	
	
	/**
	* Implementation of method from {@see ContactManager} interface based on its specifications.
	* Refer to {@see ContactManager} for specifications details.
	* testNull(date) will throw a NullPointerException if date is empty.
	* sameDate is used to evaluate each date of the meetinglist to the date passed in
	* the argument.
	* sortMeetingList is a sorter based on chronological meeting date.
	*/
	@Override
	public List<Meeting> getMeetingListOn(Calendar date){
		testNull(date);
		List<Meeting> result = new LinkedList<Meeting>();
		boolean meetinginfuture = false;
		for (int i=0; i< meetinglist.size();i++){
			if (sameDate(meetinglist.get(i).getDate(),date)) {	
					result.add(meetinglist.get(i));
					meetinginfuture = true;
			}	
		}
		if (!meetinginfuture){
			for (int i=0; i< pastmeetinglist.size();i++){
				if (sameDate(meetinglist.get(i).getDate(),date)) {	
					result.add(pastmeetinglist.get(i));
				}	
			}
		}
		sortMeetingList(result);
		return result;
	}
	
	/**
	* This a method to test if a Contact is in a particular Set of contacts.
	* This is to be leveraged in the getFutureMeeting(Contact contact)
	* method as well as the getPastMeetingListFor(Contact contact) methods.
	*/
	public boolean contactInMeeting (Set<Contact> participants, Contact contact){
		boolean result = false;
		for (Contact eachcontact : participants){
			if (eachcontact.getId() == contact.getId()) {
				result = true;
			}
		}
		return result;
	}
	
	/**
	* Implementation of method from {@see ContactManager} interface based on its specifications.
	* Refer to {@see ContactManager} for specifications details.
	* testNull will throw a NullPointerException if contact is null.
	* validEvaluator and validContact test if the Contact exists in
	* the current list of contacts. Using these two methods since they are
	* already being leveraged in other methods in this program. An Illegal-
	* argumentException will be thrown if the contact is not in the list of Contacts.
	* Any meetings that have dates occurring before the currentdate of the CalendarManager
	* are excluded from the list to be returned.
	* sortMeetingList just sorts the list on chronological order.
	*/
	@Override
	public List<Meeting> getFutureMeetingList(Contact contact){
		testNull(contact);
		Set<Contact> tempset = new HashSet<Contact>();
		tempset.add(contact);
		validEvaluator(validContact(tempset));
		List<Meeting> result = new LinkedList<Meeting>();
		for (int i=0; i< meetinglist.size();i++){
			if (contactInMeeting(meetinglist.get(i).getContacts(),contact)) {	
					if (!meetinglist.get(i).getDate().before(currentdate)){
						result.add(meetinglist.get(i));
					}
			}	
		}
		sortMeetingList(result);
		return result;
	}
	
	/**
	* Implementation of method from {@see ContactManager} interface based on its specifications.
	* Refer to {@see ContactManager} for specifications details.
	* testNull will throw a NullPointerException if contact is null.
	* validEvaluator and validContact test if the Contact exists in
	* current list of contacts. Using these two methods since they are
	* already being leveraged in other methods in this program. An Illegal-
	* argumentException will be thrown if the contact is not in the list of Contacts.
	* Any meetings that have dates occurring after the currentdate of the CalendarManager
	* are excluded from the list to be returned.
	* sortMeetingList just sorts the list on chronological order.
	*/
	@Override
	public List<PastMeeting> getPastMeetingListFor(Contact contact){
		testNull(contact);
		Set<Contact> tempset = new HashSet<Contact>();
		tempset.add(contact);
		validEvaluator(validContact(tempset));
		List<PastMeeting> result = new LinkedList<PastMeeting>();
		for (int i=0; i< pastmeetinglist.size();i++){
			if (contactInMeeting(pastmeetinglist.get(i).getContacts(),contact)) {	
					if (pastmeetinglist.get(i).getDate().before(currentdate)){
						result.add(pastmeetinglist.get(i));
					}
			}	
		}
		sortPastMeetingList(result);
		return result;
	}
	
	/**
	* Implemenation of interface based on specifications.
	* Implementation of method from {@see ContactManager} interface based on its specifications.
	* Leverages APIs: ObjectOutputStream, BufferedOutputStream (for performance), and FileOutputStream.
	* Encapsulated in try and catch statements in case an IOException occurs.
	*/
	@Override
	public void flush (){
		try (ObjectOutputStream encode = new ObjectOutputStream ( new BufferedOutputStream (new FileOutputStream ("ContactManager.ser")));)
		{
			encode.writeObject(contactlist);
			encode.writeObject(contactidcount);
			encode.writeObject(meetinglist);
			encode.writeObject(meetingidcount);
			encode.writeObject(pastmeetinglist);
		}
		catch (IOException ex){
			System.err.println(ex);
		}
	}
	
	/**
	* This method ensures that any new instances from ContactManager reads any saved data structures from prior instances
	* of ContactManager.
	* Leverages APIs: ObjectInputStream, BufferedInputStream (for performance), and FileInputStream.
	* Encapsulated in try and catch statements in case an IOException occurs.
	*/
	@SuppressWarnings("unchecked")
	public void capture (){
		try (ObjectInputStream d = new ObjectInputStream( new BufferedInputStream( new FileInputStream("ContactManager.ser")));) {
            contactlist = (List<Contact>) d.readObject();
			contactidcount = (Integer) d.readObject();
			meetinglist = (List<FutureMeeting>) d.readObject();
			meetingidcount = (Integer) d.readObject();
			pastmeetinglist = (List<PastMeeting>) d.readObject();
			
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("On write error " + ex);
        }
	}
	
	/**
	* This method is to be used for UnitTesting purposes. It ensures that a new Instance of ContactManager starts with
	* a clean list of meetings, contacts, and counters.
	*/
	public void cleanUp(){
		this.contactidcount = 0;
		this.meetingidcount = 0;
		this.contactlist = new LinkedList<Contact>();
		this.meetinglist = new LinkedList<FutureMeeting>();
		this.pastmeetinglist = new LinkedList<PastMeeting>();		
	}
	
	
	

}
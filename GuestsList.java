package Curs11Proiect1;

import java.util.ArrayList;
import java.util.List; 

public class GuestsList {

	private int available;
	private ArrayList<Guest> guests; 
	private ArrayList<Guest> waitList;  

	public GuestsList(int guestsCapacity) {
		this.available = guestsCapacity;
		this.guests = new ArrayList<>();
		this.waitList = new ArrayList<>();
	}

	/**
	 * Add a new, unique guest to the list.
	 *
	 * @param g the guest to be added
	 * @return '-1' if the guest is already present, '0' if he is a guest, or the
	 *         number on the waiting list
	 */
	public int add(Guest g) {
		if (isOnTheListAlready(g)) {
			return -1;
		}
		
		if (this.available == 0) {
			this.waitList.add(g);
			int position = this.waitList.size(); 
			
			return position;
		}
		
	    this.guests.add(g);
		this.available--; 
		
		return 0;
	}

	/**
	 * Check if someone is already registered (either as a guest, or on the waiting
	 * list).
	 *
	 * @param g the guest we are searching for
	 * @return true if present, false if not
	 */
	private boolean isOnTheListAlready(Guest g) {
		if (this.guests.contains(g) || this.waitList.contains(g)) {
			return true;
		}
		
		return false;
	}

	/**
	 * Search for a guest based on first and last name. Return the first result.
	 *
	 * @param firstName first name of the guest
	 * @param lastName  last name of the guest
	 * @return the guest if found, null if not
	 */
	public Guest search(String lastName, String firstName) {
		for (Guest g : this.guests) {
			if (g.getFirstName().toLowerCase().equals(firstName.toLowerCase()) 
					&& g.getLastName().toLowerCase().equals(lastName.toLowerCase())) {
				return g;
			}
		} 
		
		for (Guest g : this.waitList) {
			if (g.getFirstName().toLowerCase().equals(firstName.toLowerCase()) 
					&& g.getLastName().toLowerCase().equals(lastName.toLowerCase())) {
				return g;
			}
		}
		
		return null;
	} 

	/**
	 * Search for a guest based on email or phone number. Return the first result.
	 *
	 * @param opt   option to use for searching: 2 for email, 3 for phone number
	 * @param match the match we are searching for
	 * @return the guest if found, null if not
	 */
	public Guest search(int opt, String match) {
		if (opt != 2 && opt != 3) {
			throw new IllegalArgumentException("Invalid search option");
		} 
		
		for (Guest g : this.guests) {
			if (opt == 2 && g.getEmail().toLowerCase().equals(match.toLowerCase())) {
				return g;
			}
			
			if (opt == 3 && g.getPhoneNumber().toLowerCase().equals(match.toLowerCase())) {
				return g;
			}
		} 
		
		for (Guest g : this.waitList) {
			if (opt == 2 && g.getEmail().toLowerCase().equals(match.toLowerCase())) {
				return g;
			}
			
			if (opt == 3 && g.getPhoneNumber().toLowerCase().equals(match.toLowerCase())) {
				return g;
			}
		}
		
		return null;
	} 

	/**
	 * Remove a guest based on first and last name. Remove the first result.
	 *
	 * @param firstName first name of the guest
	 * @param lastName  last name of the guest
	 * @return true if removed, false if not
	 */
	public boolean remove(String lastName, String firstName) { 
		Guest guestToRemove = search(lastName, firstName);  
		
		return removeGuest(guestToRemove); 
	} 

	/**
	 * Remove a guest based on email or phone number. Remove the first result.
	 *
	 * @param opt   option to use for searching: 2 for email, 3 for phone number
	 * @param match the match we are searching for
	 * @return true if removed, false if not
	 */
	public boolean remove(int opt, String match) { 
		Guest guestToRemove = search(opt, match);
		
		return removeGuest(guestToRemove);
	}
	
	private boolean removeGuest(Guest guestToRemove) {  
		if (guestToRemove != null) {
			
			if (this.guests.contains(guestToRemove)) {
				this.guests.remove(guestToRemove);  
				this.available++;
				
				if (!this.waitList.isEmpty()) {
					if (this.waitList.contains(guestToRemove)) {
						this.waitList.remove(guestToRemove);
					}
					
					if (this.waitList.size() > 0) {
						Guest nextGuest = this.waitList.get(0);
					
						if (this.available > 0) {
							this.guests.add(nextGuest);
							
							System.out.println("Felicitari " + nextGuest.getLastName() 
							+ " " + nextGuest.getFirstName()
							+ "! Locul tau la eveniment este confirmat. Te asteptam!");
							
							this.available--; 
						}
					} 
				}
				
				return true;
			} 
			
			if (!this.waitList.isEmpty() && this.waitList.contains(guestToRemove)) {
				this.waitList.remove(guestToRemove); 
				
				return true;
			}  
		}  
		 
		return false;
	}

	// Show the list of guests.
	public void showGuestsList() { 
		for (int i = 0; i < this.guests.size(); i++) {
			System.out.println(i + 1 + ". " + this.guests.get(i).toString());
		} 
	}

	// Show the people on the waiting list.
	public void showWaitingList() { 
		if (this.waitList.size() == 0) {
			System.out.println("Lista de asteptare este goala...");
		} else { 
			for (int i = 0; i < this.waitList.size(); i++) {
				System.out.println(i + 1 + ". " + this.waitList.get(i).toString());
			} 
		}
	}

	/**
	 * Show how many free spots are left.
	 *
	 * @return the number of spots left for guests
	 */
	public int numberOfAvailableSpots() { 
	    return this.available;
	}

	/**
	 * Show how many guests there are.
	 *
	 * @return the number of guests
	 */
	public int numberOfGuests() { 
		return this.guests.size();
	}

	/**
	 * Show how many people are on the waiting list.
	 *
	 * @return number of people on the waiting list
	 */
	public int numberOfPeopleWaiting() { 
		return this.waitList.size();
	}

	/**
	 * Show how many people there are in total, including guests.
	 *
	 * @return how many people there are in total
	 */
	public int numberOfPeopleTotal() { 
		return this.guests.size() + this.waitList.size();
	}

	/**
	 * Find all people based on a partial value search.
	 *
	 * @param match the match we are looking for
	 * @return a list of people matching the criteria
	 */
	public List<Guest> partialSearch(String match) { 
		List<Guest> matchingGuests = new ArrayList<>();
		String mathchLower = match.toLowerCase();
		
		for (Guest g : this.guests) {
			if (g.getFirstName().toLowerCase().contains(mathchLower) ||
					g.getLastName().toLowerCase().contains(mathchLower) ||
					g.getEmail().toLowerCase().contains(mathchLower) ||
					g.getPhoneNumber().toLowerCase().contains(mathchLower)) {
				matchingGuests.add(g);
			}
		}
		
		for (Guest g : this.waitList) {
			if (g.getFirstName().toLowerCase().contains(mathchLower) ||
					g.getLastName().toLowerCase().contains(mathchLower) ||
					g.getEmail().toLowerCase().contains(mathchLower) ||
					g.getPhoneNumber().toLowerCase().contains(mathchLower)) {
				matchingGuests.add(g);
			}
		}
		
		return matchingGuests;
	}

	@Override
	public String toString() { 
		return super.toString();
	}
}

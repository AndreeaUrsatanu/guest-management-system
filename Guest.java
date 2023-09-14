package Curs11Proiect1;

import java.util.Objects;

public class Guest {

	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;

	public Guest(String lastName, String firstName, String email, String phoneNumber) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, lastName, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		Guest other = (Guest) obj;
		return Objects.equals(email, other.email) 
				&& Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) 
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}

	@Override
	public String toString() {
		return "Nume: " + this.lastName + " " + this.firstName 
				+ ", Email: " + this.email 
				+ ", Telefon: " + this.phoneNumber;
	}

	public String fullName() {
		return this.lastName + " " + this.firstName;
	}
}

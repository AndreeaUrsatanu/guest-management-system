package Curs11Proiect1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static void showCommands() {
		System.out.println("help         - Afiseaza aceasta lista de comenzi");
		System.out.println("add          - Adauga o noua persoana (inscriere)");
		System.out.println("check        - Verifica daca o persoana este inscrisa la eveniment");
		System.out.println("remove       - Sterge o persoana existenta din lista");
		System.out.println("update       - Actualizeaza detaliile unei persoane");
		System.out.println("guests       - Lista de persoane care participa la eveniment");
		System.out.println("waitlist     - Persoanele din lista de asteptare");
		System.out.println("available    - Numarul de locuri libere");
		System.out.println("guests_no    - Numarul de persoane care participa la eveniment");
		System.out.println("waitlist_no  - Numarul de persoane din lista de asteptare");
		System.out.println("subscribe_no - Numarul total de persoane inscrise");
		System.out.println("search       - Cauta toti invitatii conform sirului de caractere introdus");
		System.out.println("save         - Salveaza lista cu invitati");
		System.out.println("restore      - Completeaza lista cu informatii salvate anterior");
		System.out.println("reset        - Sterge informatiile salvate despre invitati");
		System.out.println("quit         - Inchide aplicatia");
	}

	private static void addNewGuest(Scanner sc, GuestsList list) { 
		System.out.println("Introdu numele: "); // nu trebuie adaugat pe devmind
		String lastName = sc.nextLine();
		
		System.out.println("Introdu prenumele: "); // nu trebuie adaugat pe devmind
		String firstName = sc.nextLine(); 
		
		System.out.println("Introdu emailul: "); // nu trebuie adaugat pe devmind
		String email = sc.nextLine(); 
		
		System.out.println("Numarul de telefon: "); // nu trebuie adaugat pe devmind
		String phoneNumber = sc.nextLine();
		
		Guest newGuest = new Guest(lastName, firstName, email, phoneNumber);
		
		int addNewGuest = list.add(newGuest);
		
		if (addNewGuest > 0){ 
			System.out.println("[" + lastName + " " + firstName + "] " 
					+ "Te-ai inscris cu succes in lista de asteptare si "
					+ "ai primit numarul de ordine " + addNewGuest + ". "
					+ "Te vom notifica daca un loc devine disponibil.");
		} else if (addNewGuest == 0) { 
			System.out.println("[" + lastName + " " + firstName 
					+ "] Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
		} else if (addNewGuest == -1) {  // nu trebuie adaugat pe devmind
			System.out.println("Persoana este deja inscrisa la eveniment");
		} 
	}

	private static void checkGuest(Scanner sc, GuestsList list) { 
		int opt = sc.nextInt();
		sc.nextLine();
	
		Guest foundGuest = findGuest(opt, sc, list); 
		
		if (foundGuest != null) { 
			System.out.println(foundGuest.toString());
		} else {
			System.out.println("Persoana cautata nu este inscrisa la eveniment");
		}
	}
	
	private static Guest findGuest(int opt, Scanner sc, GuestsList list) { 
		Guest foundGuest = null;
		
		switch(opt) {
			case 1: 
				foundGuest = findByNames(sc, list); 
				break; 
			case 2:
				foundGuest = findByEmail(sc, list);
				break; 
			case 3:
				foundGuest = findByPhoneNumber(sc, list);
				break;
			default:
				break;
		}
		
		if (foundGuest != null) {
			return foundGuest;
		}
		
		return null;
	}
	
	private static Guest findByNames(Scanner sc, GuestsList list) {
		Guest foundGuest = null;
		String lastName = sc.nextLine().toLowerCase();  
		String firstName = sc.nextLine().toLowerCase();
		
		foundGuest = list.search(lastName, firstName); 
		
		if (foundGuest != null 
				&& !foundGuest.getLastName().toLowerCase().equals(lastName) 
				&& !foundGuest.getFirstName().toLowerCase().equals(firstName)) {
	        foundGuest = null;
	    }
		
		return foundGuest;
	}
	
	private static Guest findByEmail(Scanner sc, GuestsList list) {
		Guest foundGuest = null; 
		String email = sc.nextLine().toLowerCase();
		
		foundGuest = list.search(2, email);
		
		return foundGuest;
	}
	
	private static Guest findByPhoneNumber(Scanner sc, GuestsList list) {
		Guest foundGuest = null; 
		String phoneNumber = sc.nextLine().toLowerCase();
		
		foundGuest = list.search(3, phoneNumber);
		
		return foundGuest;
	}

	private static void removeGuest(Scanner sc, GuestsList list) { 
		int opt = sc.nextInt();
		sc.nextLine();
		
		Guest foundGuest = findGuest(opt, sc, list);  
		
		if (foundGuest != null) {
			switch(opt) {
				case 1:  
					String lastName = foundGuest.getLastName().toLowerCase();
		            String firstName = foundGuest.getFirstName().toLowerCase();

		            list.remove(lastName, firstName);
		            
					break; 
				case 2:
					String email = foundGuest.getEmail().toLowerCase();
					
					list.remove(opt, email);
					break; 
				case 3:
					list.remove(opt, foundGuest.getPhoneNumber());
					break;
				default: 
					break;
			}   
		} 
	}
	
	private static void updateGuest(Scanner sc, GuestsList list) { 
		int opt = sc.nextInt();
		sc.nextLine();
		
		Guest foundGuest = findGuest(opt, sc, list);
		
		if (foundGuest != null) { 
			int optUpdate = sc.nextInt();
			sc.nextLine();
			
			switch(optUpdate) {
				case 1: 
					foundGuest.setLastName(sc.nextLine());
					break; 
				case 2:
					foundGuest.setFirstName(sc.nextLine());;
					break; 
				case 3:
					foundGuest.setEmail(sc.nextLine());
					break;
				case 4:
					foundGuest.setPhoneNumber(sc.nextLine());
					break;
				default:
					break;
			}
		} 
	}

	private static void searchList(Scanner sc, GuestsList list) {
		String match = sc.nextLine(); 
		List<Guest> matchingGuests = new ArrayList<>();
		matchingGuests = list.partialSearch(match.toLowerCase());
		
		if (matchingGuests.size() == 0) {
			System.out.println("Nothing found");
		} else {
			for (Guest g : matchingGuests) {
					System.out.println(g); 
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int size = scanner.nextInt();
		scanner.nextLine();

		GuestsList list = new GuestsList(size); 

		boolean running = true;
		while (running) {
			String command = scanner.nextLine();

			switch (command) {
			case "help":
				showCommands();
				break;
			case "add":
				addNewGuest(scanner, list);
				break;
			case "check":
				checkGuest(scanner, list);
				break;
			case "remove":
				removeGuest(scanner, list);
				break;
			case "update":
				updateGuest(scanner, list);
				break;
			case "guests":
				list.showGuestsList();
				break;
			case "waitlist":
				list.showWaitingList();
				break;
			case "available":
				System.out.println("Numarul de locuri ramase: " + list.numberOfAvailableSpots());
				break;
			case "guests_no":
				System.out.println("Numarul de participanti: " + list.numberOfGuests());
				break;
			case "waitlist_no":
				System.out.println("Dimensiunea listei de asteptare: " + list.numberOfPeopleWaiting());
				break;
			case "subscribe_no":
				System.out.println("Numarul total de persoane: " + list.numberOfPeopleTotal());
				break;
			case "search":
				searchList(scanner, list);
				break;
			case "quit":
				System.out.println("Aplicatia se inchide...");
				scanner.close();
				running = false;
				break;
			default:
				System.out.println("Comanda introdusa nu este valida.");
				System.out.println("Incercati inca o data.");

			}
		}
	}
}

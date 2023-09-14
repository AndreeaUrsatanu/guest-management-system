# guest-management-system

This project implements a back-end system for managing guest lists. The system consists of three main components: a Guest class to represent a guest, a GuestsList class to manage the guest list, and a Main class to run the application.

The Guest class represents a guest in the system. 
It stores the guest's last name, first name, email address, and phone number. 
The Guest class provides methods for managing the guest's data.

The GuestsList class manages the guest list for an event. 
It stores the number of available seats at the event, the list of confirmed guests, and the list of guests on the waiting list. 
The GuestsList class also provides methods for managing the guest list, such as adding, removing, searching, and updating guests.

The Main class provides a command-line interface for the user to interact with the guest list. The following commands are available:

  - help - Displays this list of commands
  - add - Adds a new person (registration)
  - check - Checks if a person is registered for the event
  - remove - Deletes an existing person from the list
  - update - Updates the details of a person
  - guests - List of people attending the event
  - waitlist - People on the waiting list
  - available - Number of available seats
  - guests_no - Number of people attending the event
  - waitlist_no - Number of people on the waiting list
  - subscribe_no - Total number of people registered
  - search - Searches for all invitations according to the character sequence entered
  - quit - Closes the application

import java.util.Scanner;

public class UserInterface {

	private Scanner reader;
	private RequestManager reqManager;
	
	public UserInterface() {
		reader = new Scanner(System.in);
	}
	
	public void setRequestManager(RequestManager reqManager) {
		this.reqManager = reqManager;
	}
	
	public void printMainMenu() {
		System.out.println("Welcome to the bookins service");
		System.out.println("Enter 1 to see schedule");
		System.out.println("Enter 2 to see your reservations");
		System.out.println("Enter 0 to quit");
		
		String input = reader.nextLine();
		if (input.equals("1")) {
			printSchedule();
		} else if (input.equals("2")) {
			printReservations();
		} else if (input.equals("0")){
			reqManager.processRequest(new Request("finish"));
		}
		
	}

	
	public void printSchedule() {
		System.out.println("Today's movies");
		reqManager.processRequest(new Request("schedule"));		
		System.out.println("Enter 1 to book a ticket. Keep in mind there are only 10 rows with 10 seats each."
				+ " Enter 0 to return to main menu.");
		String bookInput = reader.nextLine();
		if (bookInput.equals("0")) {
			printMainMenu();
		} else if (bookInput.equals("1")) {
			System.out.println("Enter the id of the movie (the number you see in front of the movie name)");
			String movieInput = reader.nextLine();
			reqManager.processRequest(new Request("book", Integer.parseInt(movieInput)));
			
			System.out.println("Your reservation is recieved. "
					+ "You should quit application from the main menu for it to be saved." + "Press 0 to return to main menu");
			String exitInput = reader.nextLine();
			if (exitInput.equals("0")) {
				printMainMenu();
			}
			
		}
	}
	
	public void printReservations() {
		System.out.println("Your reservations");
		reqManager.processRequest(new Request("bookings"));		
		System.out.println("Enter the number of the reservation to see it's details. Enter 0 to return to main menu.");
		
		String input = reader.nextLine();
		if (input.equals("0")) {
			printMainMenu();
		} else {
			printSpecificReservation(input);
		} 
	}
	
	public void printSpecificReservation(String id) {
		reqManager.processRequest(new Request("reservation", Integer.parseInt(id)));		
		System.out.println("Enter 1 to return to your reservations. Press 2 to cancel this reservation. Press 0 to return to main menu");
		String input = reader.nextLine();
		
		if (input.equals("1")) {
			printReservations();
		} else if (input.equals("2")) {
			reqManager.processRequest(new Request("cancel", Integer.parseInt(id)));
			System.out.println("Enter 0 to return to main menu");
			String input2 = reader.nextLine();
			if (input2.equals("0")) {
				printMainMenu();
			}
		} else if (input.equals("0")) {
			printMainMenu();
		} else {
			System.out.println("Wrong input!");
		}
	}
	
	
	
	
	
}

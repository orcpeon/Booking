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
		System.out.println("Press 2 to see your reservations");
	}
	
	public void showSchedule() {
		reqManager.processRequst(new Request("schedule"));
	}
	
	public void showReservations() {
		reqManager.processRequst(new Request("bookings"));
	}
	
	public void printSchedule() {
		System.out.println("Today's movies");
		
		
		System.out.println("Enter 1 to book a ticket. Enter 0 to return to main menu.");
	}
	
	public void printReservations() {
		System.out.println("Your reservations");
		
		
		System.out.println("Enter 1 to see specific reservation details. Enter 0 to return to main menu.");
	}
	
	public void printSpecificReservation() {
		
		
		System.out.println("Enter 1 to return to your reservations. Press 2 to cancel this reservation. Press 0 to return to main menu");
	}
	
	
	
	
	
}

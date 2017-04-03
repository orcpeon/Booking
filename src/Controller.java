import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller implements RequestManager{
	
	private UserInterface ui;
	private FileUtils model;
	
	public Controller(UserInterface ui, FileUtils fu) {
		this.ui = ui;
		model = fu;
	}

	@Override
	public void processRequest(Request request) {
		if (request.getType().equals("schedule")) {
			showSchedule();
		}
		if (request.getType().equals("bookings")) {
			showBookings();
		}
		if (request.getType().equals("reservation")) {
			showReservation(request.getId());
		}
		if (request.getType().equals("cancel")) {
			cancelReservation(request.getId());
		}
		if (request.getType().equals("book")) {
			book(request.getId(), askForSeats(new Scanner(System.in), request.getId()));
		}
		if (request.getType().equals("finish")) {
			try {
				model.writeBookings(model.getBookingsList());
			} catch (IOException e) {
				System.out.println("Something went wrong");
			}
			System.out.println("Job's done. Restart the program.");
		}		
	}
	
	private void showSchedule() { 
		for (String movie : model.getMovieList()) {
			System.out.println(movie);
		}
	}
	
	private void showBookings() { 
		for (Reservation reservation : model.getBookingsList()) {
			System.out.println(reservation.getId() + "." + reservation.getMovieName() + ", " + reservation.getSeats().size() + " seats");
		}
	}
	
	private void showReservation(int id) { 
		System.out.println(model.getBookingsList().get(id-1));
	}

	private void cancelReservation(int id) {
		model.getBookingsList().remove(id-1);
		System.out.println("Reservation canceled"); //DELETE?
	}
	
	private void book(int id, ArrayList<Seat> seats) {
		
		String movie = model.getMovieList().get(id-1);
		String[] movieSplit = movie.split("\\."); //0 - id, 1 - name and time
		String[] nameTime = movieSplit[1].split("\\|"); //0 - name, 1 - time
		Reservation newReservation = new Reservation(model.getBookingsList().size()+1, nameTime[0], nameTime[1], seats);
		
		Boolean newRes = false; //TERRIBLE HACK AHEAD
								//REWRITE USING ITERATOR
		
			if (!newReservation.getSeats().isEmpty()) {
				newRes = true;
			}
		
		if (newRes) {
			model.getBookingsList().add(newReservation);
		}
		System.out.println();
		
	}

	private ArrayList<Seat> askForSeats(Scanner sc, int id) { //asks user for seats
		ArrayList<Seat> list = new ArrayList<>();
		while (true) {			
			System.out.println("Enter row: ");
			int row = Integer.parseInt(sc.nextLine());
			System.out.println("Enter seat number: ");
			int number = Integer.parseInt(sc.nextLine());

			Seat newSeat = new Seat(row, number);
			if (isBooked(newSeat, model.getBookingsList().get(id - 1).getMovieName())) {
				System.out.println("Sorry, this seat is already booked. Choose another one.");

			} else {
				list.add(newSeat);				
			}

			System.out.println("Do you want to book more seats?[Y/N]");
			if (sc.nextLine().equalsIgnoreCase("n")) {
				break;
			}
		}
		return list;
	}
	
	public boolean isBooked(Seat seat, String movieName) { //checks if the seat is already booked
		for (Reservation booking : model.getBookingsList()) {
			if (booking.getMovieName().equals(movieName)) {
				for (Seat bookedSeat : booking.getSeats()) {
					if (seat.equals(bookedSeat)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
}














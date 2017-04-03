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
	public void processRequst(Request request) {
		
	}
	
	public void showSchedule() { //CHANGE TO PRIVATE, INVOKE WITH REQUEST
		for (String movie : model.getMovieList()) {
			System.out.println(movie);
		}
	}
	
	public void showBookings() { //CHANGE TO PRIVATE, INVOKE WITH REQUEST
		for (Reservation reservation : model.getBookingsList()) {
			System.out.println(reservation.getMovieName() + ", " + reservation.getSeats().size() + " seats");
		}
	}
	
	public void showReservation(int id) { //CHANGE TO PRIVATE, INVOKE WITH REQUEST
		System.out.println(model.getBookingsList().get(id-1));
	}

	public void cancelReservation(int id) { //CHANGE TO PRIVATE, INVOKE WITH REQUEST
		model.getBookingsList().remove(id-1);
		System.out.println("Reservation canceled"); //DELETE?
	}
	
	public void book(int id, ArrayList<Seat> seats) {
		String movie = model.getMovieList().get(id-1);
		String[] movieSplit = movie.split("\\."); //0 - id, 1 - name and time
		String[] nameTime = movieSplit[1].split("\\|"); //0 - name, 1 - time
		Reservation newReservation = new Reservation(model.getBookingsList().size()+1, nameTime[0], nameTime[1], seats);
		model.getBookingsList().add(newReservation);
	}

	public void askForSeats(Scanner sc, int id) { //asks user for seats
		while (true) {
			ArrayList<Seat> list = new ArrayList<>();
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














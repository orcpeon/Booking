import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		FileUtils fu = new FileUtils();
		UserInterface ui = new UserInterface();
		Controller contr = new Controller(ui, fu);
		contr.showSchedule();
		System.out.println("_____________________");
		contr.showBookings();
		System.out.println("_____________________");
		contr.showReservation(2);
		System.out.println("_____________________");
		ArrayList<Seat> list = new ArrayList<>();
		list.add(new Seat(1,1));
		list.add(new Seat(1,2));
		contr.book(1, list);
		contr.showBookings();
		contr.showReservation(3);
		System.out.println("_____________________");
		Seat s = new Seat(2,10);
		System.out.println(contr.isBooked(s, "Titanic"));
		System.out.println("_____________________");
		Scanner sc = new Scanner(System.in);
		contr.askForSeats(sc, 2);
		System.out.println("_____________________");
	}

}

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtils {
	
	private File schedule = new File("schedule.txt");
	private File bookings = new File ("bookings.txt");
	private Scanner scheduleReader;
	private Scanner bookingReader;
	private ArrayList<String> movieList;
	private ArrayList<Reservation>  bookingsList;
	
	public FileUtils() throws IOException {
		scheduleReader = new Scanner(schedule);
		bookingReader = new Scanner(bookings);
		movieList = readSchedule();
		bookingsList = readBookings();
	}
	
	
	
	public ArrayList<Reservation> getBookingsList() {
		return bookingsList;
	}

	public ArrayList<String> getMovieList() {
		return movieList;
	}


	private ArrayList<String> readSchedule() { //returns arraylist with movies
		ArrayList<String> returnable = new ArrayList<>();
		while (scheduleReader.hasNextLine()) {
			returnable.add(scheduleReader.nextLine());
		}
		//System.out.println(returnable); //DELETE
		return returnable;
	}
	
	private ArrayList<Reservation> readBookings() { //reads booked seats and adds them to arraylist, returns said arraylist
		ArrayList<Reservation> returnable = new ArrayList<>();
		while (bookingReader.hasNextLine()) {			
			returnable.add(readReservation(bookingReader.nextLine()));
		}
		//System.out.println(returnable); //DELETE
		return returnable;
	}
	
	public void writeBookings(ArrayList<Reservation> list) throws IOException { //Rewrites bookings file using both old and new reservations
		FileWriter writer = new FileWriter(bookings);
		for (Reservation item : list) {
			writer.append(item+"\n");
		}
		writer.close();
	}
	
	private Reservation readReservation(String str) { //reads booked seat from string and converts it to Reservation object
		String[] strSplit = str.split("\\|"); //0 - id, name, time; 1 - booked seats
		String[] idNameTime = strSplit[0].split("\\."); //0 - id; 1 - name, time
		String[] nameTime = idNameTime[1].split(","); //0 - name; 1- time
		
		ArrayList<Seat> list = new ArrayList<>(); //booked seats to the array of seats
		String[] seatArray = strSplit[1].split(",");
		for (String s : seatArray) {
			String[] seat = s.split("/");
			list.add(new Seat(Integer.parseInt(seat[0]), Integer.parseInt(seat[1])));
		}
		return new Reservation(Integer.parseInt(idNameTime[0]), nameTime[0], nameTime[1], list);
	}
	
}

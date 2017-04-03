import java.util.ArrayList;

public class Reservation {

	private int id;
	private String movieName;
	private String time;
	private ArrayList<Seat> seats;
	
	public Reservation(int id, String movieName, String time, ArrayList<Seat> list) {
		this.id = id;
		this.movieName = movieName;
		this.time = time;
		this.seats = list;
		
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}	

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
		
	public ArrayList<Seat> getSeats() {
		return seats;
	}


	public void setSeats(ArrayList<Seat> seats) {
		this.seats = seats;
	}
	
	private String printSeats() {
		StringBuilder sb = new StringBuilder();
		for (Seat seat : seats) {
			sb.append(seat.toString()+",");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();		
	}

	@Override
	public String toString() {
		return id + "." + movieName + "," + time + "|" + printSeats();
	}

}

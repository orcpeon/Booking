import java.util.ArrayList;

public class Request {
	
	private int id;
	private String type;
	private ArrayList<Seat> seats;
	
	public Request(String type) {
		this.type = type;
	}
	
	public Request(String type, int id) {
		this.type = type;
		this.id = id;
	}
	
	public Request(String type, int id, ArrayList<Seat> seats) {
		this.type = type;
		this.id = id;
		this.seats = seats;
	}
	
	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}
	
	public ArrayList<Seat> getSeats() {
		return seats;
	}
	
}

package experiment8;

public class MovieDemo {
	 public static void main(String[] args) {
	     MovieTheater theater = new MovieTheater(10);
	     try {
	         theater.bookSeat(5);
	         theater.bookSeat(5); 
	     } catch (SeatAlreadyBookedException e) {
	         System.out.println("Booking error: " + e.getMessage());
	     }
	 }
	}
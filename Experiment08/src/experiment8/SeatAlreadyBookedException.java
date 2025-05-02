package experiment8;

class SeatAlreadyBookedException extends Exception {
	private static final long serialVersionUID = 1L;
 public SeatAlreadyBookedException(String message) {
     super(message);
 }
}

class MovieTheater {
 private boolean[] seats;
 
 public MovieTheater(int totalSeats) {
     seats = new boolean[totalSeats]; 
 }
 
 public void bookSeat(int seatNumber) throws SeatAlreadyBookedException {
     if (seatNumber < 0 || seatNumber >= seats.length) {
         throw new IllegalArgumentException("Invalid Seat number.");
     }
     if (seats[seatNumber]) {
         throw new SeatAlreadyBookedException("Seat " + seatNumber + " is already been booked.");
     }
     seats[seatNumber] = true;
     System.out.println("Seat " + seatNumber + " booked successfully.");
 }
}



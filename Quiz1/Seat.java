package Quizes.Quiz1;

public class Seat {
    private int seatID;
    private boolean status;
    private Passenger passenger;

    public Seat(int seatID, boolean status, Passenger passenger) {
        this.seatID = seatID;
        this.status = status;
        this.passenger = passenger;
    }

    //getter and setters
    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public Passenger getPassenger() {
        return passenger;
    }
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
    //till here

    public void displaySeat(){
        String state = "Empty";
        if (status){
            state = "Reserved";
        }
        System.out.format("Seat: %d Status: %s%n ", seatID , state);
        passenger.displayPassenger();
    }
}

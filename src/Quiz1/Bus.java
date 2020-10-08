package Quizes.Quiz1;

public class Bus {
    private String plate;
    private int seatCount;
    private Seat[] seats;

    public Bus(String plate, int seatCount) {
        this.plate = plate;
        this.seatCount = seatCount;
        seats = new Seat[seatCount];
        for(int i = 0; i< seatCount; i++){
            seats[i] = new Seat(i+1,false,null);
        }
    }
    //getter and setters
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public Seat[] getSeats() {
        return seats;
    }

    public void setSeats(Seat[] seats) {
        this.seats = seats;
    }
    //till here
    public void bookSeat(Passenger p, int seatID){
        if (seatID > seatCount){
            System.out.println("This seat couldn't be reserved since its not a valid number");
        }
        else if (seats[seatID-1].isStatus()){
            System.out.println("This seat is already reserved.");
        }
        else{
            seats[seatID-1].setPassenger(p);
            seats[seatID-1].setStatus(true);
        }


    }

    public void printAllPassengers(){
        for (int i = 0; i < seats.length ; i++){
            if(seats[i].getPassenger() != null){
                seats[i].displaySeat();
            }
        }
    }

    public void printAllEmptySeats(){
        for (int i = 0; i < seats.length; i++){
            if(seats[i].getPassenger() == null){
                System.out.format("Seat: %d Status: Empty %n ", i+1 );
            }
        }
    }
    public void printAllSeats(){
        for (int i = 0; i < seats.length ; i++){
            if(seats[i].getPassenger() != null){
                seats[i].displaySeat();
            }
            else{
                System.out.format("Seat: %d Status: Empty %n ", i+1 );
            }
        }
    }
    public void search(String name, String surname){
        boolean found = false;
        for(Seat curr: seats){
            //checking the status to prevent checking the empty seats (which cause java.lang.NullPointerException)
            if(curr.isStatus()){
                if( curr.getPassenger().getName().toLowerCase().equals(name.toLowerCase())){
                    if( curr.getPassenger().getSurname().toLowerCase().equals(surname.toLowerCase())) {
                        curr.getPassenger().displayPassenger();
                        found = true;
                        break;
                    }
                }
            }
        }
        if (!found){
            System.out.println("Couldn't found such passenger.");
        }
    }

}

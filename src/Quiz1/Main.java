package Quizes.Quiz1;

import java.util.Scanner;

/*display menu as
 * 1: Book a seat
 * 2: Display all passengers
 * 3: Display all available seats
 * 4: Display all seats
 * 5: Search
 * 6: exit
 * */
public class Main {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args){

        Bus bus = new Bus("06 HUBM 06", 42);

        while (true){
            int choice = Integer.parseInt(userInputString("1: Book a seat\n" +
                    "2: Display all passengers\n" +
                    "3: Display all available seats\n" +
                    "4: Display all seats\n" +
                    "5: Search\n" +
                    "6: exit\n Enter your choose: "));
            if (choice == 1){
                //all inputs collected
                int seatId = Integer.parseInt(userInputString("Enter Seat ID: "));
                String name = userInputString("Enter Name: ");
                String surname = userInputString("Enter Surname: ");
                String gender = userInputString("Enter Gender (M/W): ");
                //used nextLine on this input to make it available to skip the question.
                String countryCode = userInputString("Enter Country Code: ");
                String code = userInputString("Enter code: ");
                int number = Integer.parseInt(userInputString("Enter Number: "));
                String type = userInputString("Enter Phone Type: ");
                //phone
                Phone phone;
                if (countryCode.equals("")){ phone = new Phone(code, number, type);}
                else {phone = new Phone(countryCode, code, number, type);}
                //passenger
                Passenger person = new Passenger(name, surname, gender, phone);
                bus.bookSeat(person,seatId);

            }else if (choice == 2){
                bus.printAllPassengers();
            }else if (choice == 3){
                bus.printAllEmptySeats();
            }else if (choice == 4){
                bus.printAllSeats();
            }else if (choice == 5){
                String name = userInputString("Enter Name: ");
                String surname = userInputString("Enter Surname: ");
                bus.search(name, surname);
            }else if (choice == 6){
                break;
            }else{
                System.out.println("That is not a valid option please try again.");
            }
        }
    }
    //to take faster user String inputs
    private static String userInputString(String message){
        System.out.print(message);
        return scan.nextLine();
    }


}

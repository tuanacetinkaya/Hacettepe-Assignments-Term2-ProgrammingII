public class Chief extends FullTimeEmployee {
    /**
     *Will use super method from Assignments.Asgn2.FullTimeEmployee class for calculation
     * by assigning its maximum overwork hour, overwork salary and payment per day
     */
    Chief (String nameAndSurname, String registrationNumber, String yearOfStart){
        super(nameAndSurname, registrationNumber,"CHIEF", yearOfStart, 8,15,125);
    }
}

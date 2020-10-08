/**
 * A class to help visualize hierarchy since there are 2 types of employees:
 *          -Assignments.Asgn2.FullTimeEmployee
 *               >>Assignments.Asgn2.Worker
 *               >>Assignments.Asgn2.Chief
 *          -Assignments.Asgn2.PartTimeEmployee
 */

public class Employee extends Personnel {
    Employee(String nameAndSurname, String registrationNumber, String position, String yearOfStart){
        super(nameAndSurname, registrationNumber,position, yearOfStart);
    }
}

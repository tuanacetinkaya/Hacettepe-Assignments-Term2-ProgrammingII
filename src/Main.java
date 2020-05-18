/**
 * Hospital Management System using Decorator Design Pattern and Data Access Object
 * Assignment 3
 * (due date: 22.05.2020)
 * (delivered: 18.05.2020)
 * @author 21985164
 */
public class Main {
    /**
     * Precondition: patient.txt and admission.txt must be in the working directory.
     * Post condition: You will see the output.txt monitoring your input commands.
     *                  admission.txt and patient.txt will be updated depending on your actions.
     * @param args must take input.txt
     */
    public static void main (String[] args){
        System.out.println("\tWarning: Check the feedbacks below for possible errors and extra steps taken " +
                "\n\twithout a request which will not be mentioned in your 'output.txt'.\n\t |\n\t V\n");


        Hospital beytepeHospital = null;
        try{
            //handled exceptions for Hospital class with argument input file name
            beytepeHospital = new Hospital(args[0]);

        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("You must enter the input file name with console arguments");
            System.exit(0);
        }
        //read input commands and perform operations
        beytepeHospital.performCommands();
        //update input and output files and close them to prevent data loss
        beytepeHospital.updateFiles();

    }

}

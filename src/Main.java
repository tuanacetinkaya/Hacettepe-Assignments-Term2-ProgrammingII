import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main (String[] args){

        try{
            Hospital beytepeHospital = new Hospital("input.txt");

        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("You must enter the input file name with console arguments");
        }

        System.out.println("IT IS OVER");

    }

}

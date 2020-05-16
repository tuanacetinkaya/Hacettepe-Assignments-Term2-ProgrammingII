import java.nio.file.Paths;

public class Main {
    public static void main (String[] args){

        ReadFiles reader = new ReadFiles("src\\input.txt");
        TextPatientDAO patientDAO = new TextPatientDAO();
        TextAdmissionDAO admissionDAO = new TextAdmissionDAO();

        System.out.println("IT IS OVER");

    }

}

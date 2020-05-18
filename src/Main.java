import java.nio.file.Paths;

public class Main {
    public static void main (String[] args){

        ReadFiles reader = new ReadFiles("src\\input.txt");
        TextPatientDAO patientDAO = new TextPatientDAO();
        TextAdmissionDAO admissionDAO = new TextAdmissionDAO();

        System.out.println("IT IS OVER");

        admissionDAO.createAdmission(7,23);
        admissionDAO.addExamination(7,"Inpatient", new String[]{"test", "doctorvisit", "measurements", "test"});
        //admissionDAO.addExamination(9,"Inpatient", new String[]{"test", "doctorvisit", "measurements", "test"});
        admissionDAO.deleteAdmission(3);
        //admissionDAO.totalCost(7);
        for(Admission admission: admissionDAO.getAdmissions()){
            System.out.println(admission.toString());
        }
    }

}

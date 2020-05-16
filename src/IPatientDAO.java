import java.util.ArrayList;

public interface IPatientDAO {
    void addPatient(Patient p);
    boolean deletePatient(int patientID);
    void updatePatient(Patient oldP, Patient newP);
    ArrayList<Patient> getPatientList();

}

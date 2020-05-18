import java.util.ArrayList;

/**
 * this interface will be used to reach the patient database
 */
public interface IPatientDAO {
    /**
     * Adds new patient to database
     * @param p is the short for patient
     */
    void addPatient(Patient p);

    /**
     * deletes the patient with the given ID
     * @param patientID of patient
     * @return false if the patient does not exist on database
     */
    boolean deletePatient(int patientID);

    /**
     * if a patient has misinformation that method updates the patient
     * @param oldP to be replaced with
     * @param newP stated
     */
    void updatePatient(Patient oldP, Patient newP);

    /**
     * to reach the list more efficiently
     * @return the patient list in ArrayList format
     */
    ArrayList<Patient> getPatientList();

}

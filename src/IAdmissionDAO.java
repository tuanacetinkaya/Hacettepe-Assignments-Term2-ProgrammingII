import java.util.ArrayList;

/**
 * this interface will be used to reach the admission database
 */
public interface IAdmissionDAO {

    /**
     * creates admission and adds it to the database.
     * @param admissionID and
     * @param patientID of the given patient
     * @return the admission to reach it more efficiently.
     */
    Admission createAdmission(int admissionID, int patientID);

    /**
     * deletes the patient with given
     * @param admissionID from database
     * @return false if the admission does not exist
     */
    boolean deleteAdmission(int admissionID);

    /**
     * adds new examination with the dynamic decorator design pattern
     * @param admissionID of patient
     * @param type of condition
     * @param operations maximum of 3
     */
    void addExamination(int admissionID, String type, String[] operations);

    /**
     * prints the total cost information of given patient with admissionID
     * @param admissionID as stated
     * @return the string format of admission cost
     */
    String totalCost(int admissionID);

    /**
     * to reach the whole database
     * @return the admissions sorting their admissionID
     */
    ArrayList<Admission> getAdmissions();
}

import java.util.ArrayList;

public interface IAdmissionDAO {

    Admission createAdmission(int admissionID, int patientID);
    boolean deleteAdmission(int admissionID);
    void addExamination(int admissionID, String[] operations);
    int totalCost(int admissionID);
    ArrayList<Admission> getAdmissions();
}

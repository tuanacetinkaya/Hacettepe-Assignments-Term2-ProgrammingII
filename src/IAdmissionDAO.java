import java.util.ArrayList;

public interface IAdmissionDAO {

    Admission createAdmission(int admissionID, int patientID);
    boolean deleteAdmission(int admissionID);
    void addExamination(int admissionID, String type, String[] operations);
    String totalCost(int admissionID);
    ArrayList<Admission> getAdmissions();
}

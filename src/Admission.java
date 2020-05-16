import java.util.ArrayList;
import java.util.Arrays;

public class Admission {
    private Patient patient;
    private int admissionID;
    private int patientID;
    // size 2 for inpatient and outpatient decorated examinations
    private ArrayList<IExamination> examinations = new ArrayList<IExamination>();

    public Admission(int admissionID, int patientID) {
        this.admissionID = admissionID;
        this.patientID = patientID;
    }

    public void addExamination(String type, String operations){
        System.out.println("still so much to do"); //todo
    }

    public String toString(){
        String[] examinationString = new String[examinations.size()+1];
        examinationString[0] = String.format("%d\t%d",admissionID,patientID);
        int index = 1;
        for(IExamination e: examinations){
            examinationString[index++] = e.toString();//todo toString method for examination class
        }
        return String.join("\n",examinationString );
    }

    public int getAdmissionID() {
        return admissionID;
    }

    public void setAdmissionID(int admissionID) {
        this.admissionID = admissionID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public ArrayList<IExamination> getExaminations() {
        return examinations;
    }

    public void setExaminations(ArrayList<IExamination> examinations) {
        this.examinations = examinations;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


}

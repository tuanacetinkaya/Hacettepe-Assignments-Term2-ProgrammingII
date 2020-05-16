import java.util.ArrayList;
import java.util.Arrays;

public class Admission {
    private Patient patient;
    private int admissionID;
    private int patientID;
    // size 2 for inpatient and outpatient decorated examinations
    private ArrayList<Examination> examinations = new ArrayList<Examination>();

    public Admission(int admissionID, int patientID) {
        this.admissionID = admissionID;
        this.patientID = patientID;
    }

    //todo as an alternative i set the patient option to track the patient more efficiently
    public Admission(int admissionID, Patient patient) {
        this.patient = patient;
        this.admissionID = admissionID;
    }

    public void addExamination(String type, String operations){
        System.out.println("still so much to do"); //todo
    }

    public String toString(){
        String[] examinationString = new String[examinations.size()+1];
        examinationString[0] = String.format("%d\t%d",admissionID,patientID);
        int index = 1;
        for(Examination e: examinations){
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

    public ArrayList<Examination> getExaminations() {
        return examinations;
    }

    public void setExaminations(ArrayList<Examination> examinations) {
        this.examinations = examinations;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


}

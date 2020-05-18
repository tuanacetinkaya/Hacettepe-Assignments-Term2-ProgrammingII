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
    public Admission(int admissionID,int patientID, IExamination e){
        this(admissionID,patientID);
        examinations.add(e);
    }

    public void addExamination(String type, String[] allOperations){
        if(allOperations.length > 3){
            System.out.println("A patient cannot have more than 3 operations. No operation added for the patient.");
            return;
        }
        IExamination temp = null;
        switch (type){
            case("Inpatient"):
                temp = new Inpatient();
                break;
            case("Outpatient"):
                temp = new Outpatient();
                break;
        }
        assert temp != null;
        for (String op: allOperations) {
            switch (op){
                case("doctorvisit"):
                    temp = new DoctorVisit(temp);
                    break;
                case ("imaging"):
                    temp = new Imaging(temp);
                    break;
                case ("measurements"):
                    temp = new Measurements(temp);
                    break;
                case("tests"):
                    temp = new Test(temp);
            }
        }
        examinations.add(temp);
    }

    public void addExamination(String type, String operations){
            addExamination(type, operations.split(" "));
    }

    public String toString(){
        String[] examinationString = new String[examinations.size()+1];
        examinationString[0] = String.format("%d\t%d",admissionID,patientID);
        int index = 1;
        for(IExamination e: examinations){
            examinationString[index++] = e.getDescription();
        }
        return String.join("\n",examinationString );
    }

    public String totalCost(){
        String description = "";
        int totalCost = 0;
        for(IExamination content: examinations){
            description += content.getDescription() + content.getCost() + "$\n";
            totalCost += content.getCost();
        }
        return "TotalCost for admission " + admissionID +"\n"+
                description + "Total: " + totalCost + "$";
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

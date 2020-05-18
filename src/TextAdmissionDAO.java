import java.util.ArrayList;
import java.util.Objects;

public class TextAdmissionDAO implements IAdmissionDAO {

    private final ArrayList<String> initialAdmissionList;
    private ArrayList<Admission> admissions;

    TextAdmissionDAO(){

        ReadFiles reader = new ReadFiles("admission.txt");
        this.initialAdmissionList = reader.getListFormat();
        this.admissions = new ArrayList<Admission>();
        initializeAdmissions();
    }

    private void initializeAdmissions(){
        Admission temp = null;
        for(String info: initialAdmissionList){
            String[] infoSplit = info.split("\t");
            if(isNumeric(infoSplit[0])){
                temp = createAdmission(
                        Integer.parseInt(infoSplit[0].trim()),//admissionID
                        Integer.parseInt(infoSplit[1].trim())//patientID
                );//createAdmission
            }else{
                if (temp == null) System.out.println("Your admission file is not in correct format.");
                else temp.addExamination(infoSplit[0], infoSplit[1]);
            }
        }
    }

    private boolean isNumeric(String value){
        try{
            Integer.valueOf(value);
            return true;
        }catch (NumberFormatException n){
            return false;
        }
    }

    @Override
    public Admission createAdmission(int admissionID, int patientID) {
        for(Admission admission: admissions){
            if(admission.getPatientID() == patientID){
                System.out.println("You already created an admission for patient " + patientID +
                        "\n\tAdmission ID: " + admission.getAdmissionID());
                return null;
            }
        }
        Admission newAdmission = new Admission(admissionID,patientID);
        admissions.add(newAdmission);
        return newAdmission;
    }

    @Override
    public boolean deleteAdmission(int admissionID) {
        for(Admission admission: admissions){
            if(admission.getAdmissionID() == admissionID){
                admissions.remove(admission);
                return true;
            }
        }
        return false;
    }

    private Admission findAdmission(int admissionID){
        for(Admission admission: admissions){
            if(admissionID == admission.getAdmissionID()){
                return admission;
            }
        }
        System.out.println("Patient with admission ID " + admissionID + " cannot be found.");
        return null;
    }

    @Override
    public void addExamination(int admissionID, String type, String[] operations) {
        Admission patient = findAdmission(admissionID);
        if (patient != null) {
            patient.addExamination(type, operations);
        }else{
            System.out.println("Error while adding examination:\n" +
                    "\tNo such patient with admission ID " + admissionID);
        }
    }

    public void addExamination(Admission admission, String type, String[] operations){
        addExamination(admission.getAdmissionID(),type,operations);
        //todo might delete
    }

    @Override
    public String totalCost(int admissionID) {
        Admission patient = findAdmission(admissionID);
        if(patient != null){
            return patient.totalCost();
        }
        else return "TotalCost for admission " + admissionID + " cannot be called.";
    }

    @Override
    public ArrayList<Admission> getAdmissions() {
        return admissions;
    }

    public void removedPatientUpdate(int patientID){
        for(Admission admission: admissions){
            if(admission.getPatientID() == patientID){
                admissions.remove(admission);
                System.out.println("Admission of patient " + patientID +
                        " removed because this patient has removed from the patient database");
            }
        }
    }
}

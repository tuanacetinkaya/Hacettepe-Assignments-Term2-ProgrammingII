import java.util.ArrayList;

/**
 * whenever a patient is removed or added, informAdmissionDAO() will be called to synchronize the data
 */
public class TextPatientDAO implements IPatientDAO {
    private final ArrayList<String> initialPatientList;
    private ArrayList<Patient> patients;

    TextPatientDAO(){
        ReadFiles patientFile = new ReadFiles("patient.txt");
        initialPatientList = patientFile.getListFormat();
        this.patients = createPatients();
    }


    private ArrayList<Patient> createPatients(){
        ArrayList<Patient> initialPatients = new ArrayList<Patient>();
        for(String info: initialPatientList){
            String[] splitInfo = info.split("\t");
            initialPatients.add(new Patient(
                    splitInfo[0],//patientID
                    splitInfo[1],//nameAndSurname
                    splitInfo[2],
                    splitInfo[3])
            );//add new Patient
        }
        return initialPatients;
    }

    @Override
    public void addPatient(Patient p) {
        patients.add(p);
    }

    @Override
    public boolean deletePatient(int patientID) {
        for(Patient p: patients){
            if(patientID == p.getPatientID()){
                patients.remove(p);
                return true;
            }
        }
        System.out.println("Patient you tried to remove does not exist. Patient ID: " + patientID);
        return false;
    }

    @Override
    public void updatePatient(Patient oldP, Patient newP) {
        patients.remove(oldP);
        patients.add(newP);
    }

    @Override
    public ArrayList<Patient> getPatientList() {
        return patients;
    }
}

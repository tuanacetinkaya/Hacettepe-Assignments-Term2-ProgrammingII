import java.util.ArrayList;

/**
 * whenever a patient is removed or added, informAdmissionDAO() will be called to synchronize the data
 */
public class TextPatientDAO implements IPatientDAO {
    private ArrayList<String> initialPatientList;
    private ArrayList<Patient> patients;

    TextPatientDAO(){
        //todo find a way to reach the file without declaring the absolute path
        ReadFiles patientFile = new ReadFiles("src\\patient.txt");
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
        //todo
    }

    @Override
    public boolean deletePatient(int patientID) {
        //todo
        return false;
    }

    @Override
    public void updatePatient(Patient oldP, Patient newP) {
        //todo
    }

    @Override
    public ArrayList<Patient> getPatientList() {
        return patients;
    }
}

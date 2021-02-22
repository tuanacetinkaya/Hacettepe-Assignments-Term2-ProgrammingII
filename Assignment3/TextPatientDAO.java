package Assignment3;

import java.util.ArrayList;

/**
 * this is the implementation of IPatientDAO for Text file database.
 */
public class TextPatientDAO implements IPatientDAO {
    private final ArrayList<String> initialPatientList;
    private ArrayList<Patient> patients;

    TextPatientDAO(){
        ReadFiles patientFile = new ReadFiles("Assignment3/patient.txt");
        initialPatientList = patientFile.getListFormat();
        this.patients = createPatients();
    }

    /**
     * initially creates all patient according to the given text file
     * @return the list of patients
     */
    private ArrayList<Patient> createPatients(){
        ArrayList<Patient> initialPatients = new ArrayList<Patient>();
        for(String info: initialPatientList){
            String[] splitInfo = info.split("\t");
            initialPatients.add(new Patient(
                    splitInfo[0],//patientID
                    splitInfo[1],//nameAndSurname
                    splitInfo[2],//phone
                    splitInfo[3])//address with semi column
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
        Patient toDelete = null;
        boolean isRemoved = false;
        for(Patient p: patients){
            if(patientID == p.getPatientID()){
                toDelete = p;
                isRemoved = true;
            }
        }
        if(isRemoved){
            patients.remove(toDelete);
        }else {
            System.out.println("Patient you tried to remove does not exist. Patient ID: " + patientID);
        }
        return isRemoved;
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

    public Patient getPatient(int patientID){
        for(Patient p: patients){
            if(patientID == p.getPatientID()){
                return p;
            }
        }
        return null;
    }
}

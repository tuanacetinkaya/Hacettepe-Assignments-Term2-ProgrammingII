package Assignment3;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

public class Hospital {
    private final ArrayList<String> inputList;
    private final TextPatientDAO patientDAO;
    private final TextAdmissionDAO admissionDAO;
    private PrintWriter outFile = null;

    /**
     * Precondition: All files must be in right format. Double spaces mostly handled but I advice you to check for them
     *          in case a couple has missed my sight.
     * Hospital class arrange all operations between IAdmissionDAO and IPatientDAO and operate input commands.
     * @param inputFileName is your input file name
     */
    Hospital(String inputFileName){
        ReadFiles adminDocument = new ReadFiles(inputFileName);
        inputList = adminDocument.getListFormat();
        patientDAO = new TextPatientDAO();
        admissionDAO = new TextAdmissionDAO();

        try {
            outFile = new PrintWriter("Assignment3/output.txt");
        } catch (FileNotFoundException e) {
            System.out.println("An error occured while creating the output file.\n" +
                    "\t tip: Please be sure you do not have any folder named output.txt");
            System.exit(0);
        }
    }

    /**
     * Precondition: Commands written in input.txt has to be formatted correctly.
     * List of commands is as below:
     * -AddPatient space < patientID > space < name > space < surname > space < phone number > space < address >
     * -RemovePatient  space < patient ID >
     * -CreateAdmission space < AdmissionID > space < PatientID >
     * -AddExamination space < AdmissionID > space < examination type > space <operation >
     * -TotalCost space < AdmissionID >
     * -ListPatients
     */
    public void performCommands(){
        for(String command: inputList){
            String[] commandList = command.split(" ");

            switch (commandList[0]){
                case("AddPatient"):
                    addPatient(
                            commandList[1],//patientID
                            commandList[2], //name
                            commandList[3], //surname
                            commandList[4], //phone
                            String.join(" ", cropArray(commandList, 5, commandList.length-1))//address
                    );//addPatient
                    break;
                case("RemovePatient"):
                    removePatient(Integer.parseInt(commandList[1]));
                    break;
                case("CreateAdmission"):
                    createAdmission(Integer.parseInt(commandList[1]), Integer.parseInt(commandList[2]));
                    break;
                case("AddExamination"):
                    addExamination(
                            Integer.parseInt(commandList[1]), //admissionID
                            commandList[2],  //type Inpatient or Outpatient
                            cropArray(commandList,3, commandList.length -1) //operations array
                    );
                    break;
                case("TotalCost"):
                    totalCost(Integer.parseInt(commandList[1])); //admissionID
                    break;
                case("ListPatients"):
                    listPatients();
                    break;
            }
        }
    }

    /**
     * Post condition: Sorts all lists as requested and update them by overwriting those files.Then close all files.
     *          In order to not face any missing data, this method has to be called from Main
     */
    public void updateFiles(){
        ArrayList<Patient> finalPatients = patientDAO.getPatientList();
        ArrayList<Admission> finalAdmissions = admissionDAO.getAdmissions();
        finalPatients.sort(Comparator.comparing(Patient::getPatientID));
        finalAdmissions.sort(Comparator.comparing(Admission::getAdmissionID));

        PrintWriter patientUpdated = null;
        PrintWriter admissionUpdated = null;
        try {
            patientUpdated = new PrintWriter("Assignment3/patient.txt");
            admissionUpdated = new PrintWriter("Assignment3/admission.txt");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while updating patient and admission files");
            System.exit(0);
        }

        for(Patient p: finalPatients){
            patientUpdated.println(p.toString());
        }
        for(Admission a: finalAdmissions){
            admissionUpdated.println(a.toString());
        }

        outFile.close();
        patientUpdated.close();
        admissionUpdated.close();
    }

    /**
     * Precondition:
     * @param phone has to be 000-0000000 format otherwise will not be added
     * @param address has to be given without stating "Address:" while adding patient in input.txt
     * Post condition: Patient object is created with given information and added to patients list.
     *                Send a feedback to output file stating the task is performed.
     */
    private void addPatient(String patientID, String name, String surname, String phone, String address){
        if(phone.length() != 11){
            phone = "not-added";
            System.out.println("Phone number of patient " + patientID + " is not in right format, thus cannot be added.");
        }
        Patient newPatient = new Patient(patientID, name, surname, phone, address); //regular constructor
        patientDAO.addPatient(newPatient);

        outFile.format("Patient %s %s added\n", patientID, name);
    }

    /**
     * Removed and deleted admissions of the patient with patientID
     * @param patientID stated.
     * Post condition: Send a feedback to output file stating the task is performed.
     */
    private void removePatient(int patientID){
        Patient toRemove = patientDAO.getPatient(patientID);
        if(toRemove != null){
            String name = toRemove.getName();
            patientDAO.deletePatient(patientID);
            admissionDAO.removedPatientUpdate(patientID); //will remove the admission of deleted patient
            outFile.format("Patient %d %s removed\n", patientID, name);
        }else{
            System.out.println("Patient you would like to remove with patient ID '" + patientID + "' does not exist.");
        }
    }

    /**
     * creates an empty admission with
     * @param admissionID and
     * @param patientID stated.
     * Post condition: Send a feedback to output file stating the task is performed.
     */
    private void createAdmission(int admissionID, int patientID){
        admissionDAO.createAdmission(admissionID, patientID);
        outFile.println("Admission " + admissionID + " created");

    }

    /**
     * Adds an Examination object using its decorator pattern inside the admissionDAO
     * @param admissionID need to be created before
     * @param type can be Inpatient or Outpatient
     * @param operations will be checked inside the Admission object
     */
    private void addExamination(int admissionID, String type, String[] operations){
        admissionDAO.addExamination(admissionID, type, operations);
        outFile.println(type + " examination added to admission " + admissionID);
    }

    /**
     * prints the operations and total cost of the patient in the output file
     * @param admissionID of patient
     */
    private void totalCost(int admissionID){
        outFile.println(admissionDAO.totalCost(admissionID));
    }

    /**
     * lists all patients alphabetically in the output file
     */
    private void listPatients(){
        outFile.println("PatientList:");
        ArrayList<Patient> finalList = patientDAO.getPatientList();
        //sort list alphabetically
        finalList.sort(Comparator.comparing(Patient::getName));
        for(Patient p: patientDAO.getPatientList()){
            outFile.println(p.toString());
        }
    }

    /**
     * helps cropping an array with indexes
     * @param array to be cropped
     * @param startIndex and
     * @param endIndex needs to be regular indexes starting from zero
     * @return that part of the given array as a whole
     */
    private String[] cropArray(String[] array, int startIndex, int endIndex){
        String[] cropped = new String[endIndex - startIndex + 1];
        for(int i = 0; i < cropped.length; i++){
            cropped[i] = array[startIndex + i];
        }
        return cropped;
    }

}

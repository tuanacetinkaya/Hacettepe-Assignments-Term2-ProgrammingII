import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

public class Hospital {
    private ArrayList<String> inputList;
    private TextPatientDAO patientDAO;
    private TextAdmissionDAO admissionDAO;
    private PrintWriter outFile = null;

    Hospital(String inputFileName){
        ReadFiles adminDocument = new ReadFiles(inputFileName);
        inputList = adminDocument.getListFormat();
        patientDAO = new TextPatientDAO();
        admissionDAO = new TextAdmissionDAO();

        try {
            outFile = new PrintWriter("output.txt");
        } catch (FileNotFoundException e) {
            System.out.println("An error occured while creating the output file.\n" +
                    "\t tip: Please be sure you do not have any folder named output.txt");
            System.exit(0);
        }
    }

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

    public void updateFiles(){
        ArrayList<Patient> finalPatients = patientDAO.getPatientList();
        ArrayList<Admission> finalAdmissions = admissionDAO.getAdmissions();
        finalPatients.sort(Comparator.comparing(Patient::getPatientID));
        finalAdmissions.sort(Comparator.comparing(Admission::getAdmissionID));

        PrintWriter patientUpdated = null;
        PrintWriter admissionUpdated = null;
        try {
            patientUpdated = new PrintWriter("patient.txt");
            admissionUpdated = new PrintWriter("admission.txt");
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

    private void addPatient(String patientID, String name, String surname, String phone, String address){
        if(phone.length() != 11){
            phone = "not-added";
            System.out.println("Phone number of patient " + patientID + " is not in right format, thus cannot be added.");
        }
        Patient newPatient = new Patient(patientID, name, surname, phone, address); //regular constructor
        patientDAO.addPatient(newPatient);

        outFile.format("Patient %s %s added\n", patientID, name);
    }

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

    private void createAdmission(int admissionID, int patientID){
        admissionDAO.createAdmission(admissionID, patientID);
        outFile.println("Admission " + admissionID + " created");

    }

    private void addExamination(int admissionID, String type, String[] operations){
        admissionDAO.addExamination(admissionID, type, operations);
        outFile.println(type + " examination added to admission " + admissionID);
    }
    private void totalCost(int admissionID){
        outFile.println(admissionDAO.totalCost(admissionID));
    }

    private void listPatients(){
        outFile.println("PatientList:");
        ArrayList<Patient> finalList = patientDAO.getPatientList();
        //sort list alphabetically
        finalList.sort(Comparator.comparing(Patient::getName));
        for(Patient p: patientDAO.getPatientList()){
            outFile.println(p.toString());
        }
    }


    private String[] cropArray(String[] array, int startIndex, int endIndex){
        String[] cropped = new String[endIndex - startIndex + 1];
        for(int i = 0; i < cropped.length; i++){
            cropped[i] = array[startIndex + i];
        }
        return cropped;
    }

}

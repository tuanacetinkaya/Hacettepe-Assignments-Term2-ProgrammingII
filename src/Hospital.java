import java.util.ArrayList;

public class Hospital {
    ArrayList<String> inputList;
    TextPatientDAO patientDAO;
    TextAdmissionDAO admissionDAO;

    Hospital(String inputFileName){
        ReadFiles adminDocument = new ReadFiles(inputFileName);
        inputList = adminDocument.getListFormat();
        patientDAO = new TextPatientDAO();
        admissionDAO = new TextAdmissionDAO();
    }

    public void performCommands(){
        //todo this section edits all Admissions and patients.
        for(String command: inputList){
            String[] commandList = command.split(" ");

            switch (commandList[0]){
                case("AddPatient"):
                    addPatient(
                            commandList[1],//patientID
                            commandList[2], //name
                            commandList[3], //surname
                            commandList[4], //phone
                            String.join(" ", cropArray(commandList, 5, commandList.length))//address
                    );//addPatient
                    break;
                case("RemovePatient"):
                    break;
                case("CreateAdmission"):
                    break;
                case("AddExamination"):
                    break;
                case("TotalCost"):
                    break;
                case("ListPatients"):
                    break;
            }

        }
    }

    private void addPatient(String patientID, String name, String surname, String phone, String address){
        if(phone.length() != 11){
            phone = "not-added";
            System.out.println("Phone number of patient " + patientID + " is not in right format, thus cannot be added.");
        }
        Patient newPatient = new Patient(patientID, name, surname, phone, address);
        patientDAO.addPatient(newPatient);
    }

    private void removePatient(int patientID){
        patientDAO.deletePatient(patientID);
        admissionDAO.removedPatientUpdate(patientID); //will remove the admission of deleted patient
    }
    private void createAdmission(){
        //todo
    }
    private void addExamination(){
        //todo
    }
    private void totalCost(){
        //todo
    }
    private void listPatients(){
        //todo
    }

    //todo planning to write multiple classes for each task. notify the user when they failed.
    private void notify(Boolean task){
        if(!task){
            System.out.println("Task Failed to Perform");
        }
    }

    private void updateFiles(){
        //todo editing the output file will be the last task.
    }

    private String[] cropArray(String[] array, int startIndex, int endIndex){
        String[] cropped = new String[endIndex - startIndex + 1];
        for(int i = 0; i < cropped.length; i++){
            cropped[i] = array[startIndex + i];
        }
        return cropped;
    }

}

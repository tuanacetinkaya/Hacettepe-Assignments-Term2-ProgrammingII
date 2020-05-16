import java.util.ArrayList;

public class Hospital {
    ArrayList<String> inputList;
    Hospital(String inputFileName){
        ReadFiles adminDocument = new ReadFiles(inputFileName);
        inputList = adminDocument.getListFormat();
    }

    public void performCommands(){
        //todo this section edits all Admissions and patients.
        for(String command: inputList){
            String[] commandList = command.split(" ");

            switch (commandList[0]){
                case("AddPatient"):
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

    //todo planning to write multiple classes for each task. notify the user when they failed.
    private void notify(Boolean task){
        if(!task){
            System.out.println("Task Failed to Perform");
        }
    }

    private void updateFiles(){
        //todo editing the output file will be the last task.
    }

}

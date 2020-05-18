import java.util.ArrayList;


public class Admission {

    private int admissionID;
    private int patientID;
    //stored all examination added here
    private ArrayList<IExamination> examinations = new ArrayList<IExamination>();

    public Admission(int admissionID, int patientID) {
        this.admissionID = admissionID;
        this.patientID = patientID;
    }

    /**
     * checks the number of operations and creates the respective IExamination object and adds it in examinations list.
     * @param type Inpatient or Outpatient
     * @param allOperations given as array
     */
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
    //that is a helper overloading method in situations spliting the operations will make the code more complicated
    public void addExamination(String type, String operations){
            addExamination(type, operations.split(" "));
    }

    //to print the Admission in right format
    public String toString(){
        String[] examinationString = new String[examinations.size()+1];
        examinationString[0] = String.format("%d\t%d",admissionID,patientID);
        int index = 1;
        for(IExamination e: examinations){
            examinationString[index++] = e.getDescription();
        }
        return String.join("\n",examinationString );
    }

    //will be used to print the total cost output to output.txt
    public String totalCost(){
        String description = "";
        int totalCost = 0;
        for(IExamination content: examinations){
            description += "\t" + content.getDescription() + content.getCost() + "$\n";
            totalCost += content.getCost();
        }
        return "TotalCost for admission " + admissionID +"\n"+
                description + "\tTotal: " + totalCost + "$";
    }

    //getters and setters
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



}

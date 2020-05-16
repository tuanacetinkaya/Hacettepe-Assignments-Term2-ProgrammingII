import java.util.ArrayList;

public class TextAdmissionDAO implements IAdmissionDAO {

    private ArrayList<String> initialAdmissionList;
    private ArrayList<Admission> admissions;

    TextAdmissionDAO(){

        //todo find a way to reach the wile without actual path
        ReadFiles reader = new ReadFiles("src\\admission.txt");
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
                temp.addExamination(infoSplit[0], infoSplit[1]);
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

    @Override
    public void addExamination(int admissionID, String[] operations) {

    }

    @Override
    public int totalCost(int admissionID) {
        return 0;
    }

    @Override
    public ArrayList<Admission> getAdmissions() {
        return null;
    }
}

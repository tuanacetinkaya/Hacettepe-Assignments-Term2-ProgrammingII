public class Administration {
    private Personnel[] personnels; //a list to store all personnel
    private String[][] personnelList; //list format of personnel.txt
    private int index = 0; //number of personnel

    Administration(String[][] personnelList){
        this.personnelList = personnelList;
        personnels = new Personnel[personnelList.length];
        readPersonnelList();
    }

    /**
     * Create personnel by their info in personnelList
     */
    private void readPersonnelList(){
        for(String[] personnelInfo: personnelList){
            createPersonnel(personnelInfo[0], personnelInfo[1], personnelInfo[2], personnelInfo[3]);
        }
    }

    /**
     * Create proper personnel objects by their positions
     * @param nameAndSurname as one variable will be split in Assignments.Asgn2.Personnel class for practical usage
     * @param registrationNumber and
     * @param position and
     * @param yearOfStart taken in String format and assigned to Assignments.Asgn2.Personnel.
     */
    private void createPersonnel(String nameAndSurname,
                                  String registrationNumber, String position ,String yearOfStart){
        switch (position){
            case ("FACULTY_MEMBER"):
                personnels[index] = new FacultyMember(nameAndSurname,registrationNumber,yearOfStart);
                index++;
                break;
            case("RESEARCH_ASSISTANT"):
                personnels[index] = new ResearchAssistant(nameAndSurname,registrationNumber,yearOfStart);
                index++;
                break;
            case("OFFICER"):
                personnels[index] = new Officer(nameAndSurname,registrationNumber,yearOfStart);
                index++;
                break;
            case("PARTTIME_EMPLOYEE"):
                personnels[index] = new PartTimeEmployee(nameAndSurname,registrationNumber,yearOfStart);
                index++;
                break;
            case("WORKER"):
                personnels[index] = new Worker(nameAndSurname,registrationNumber,yearOfStart);
                index++;
                break;
            case("CHIEF"):
                personnels[index] = new Chief(nameAndSurname,registrationNumber,yearOfStart);
                index++;
                break;
            case("SECURITY"):
                personnels[index] = new Security(nameAndSurname,registrationNumber,yearOfStart);
                index++;
                break;
        }
    }

    /**
     * a helper method to see if the personnels created correctly before calculating their salary.
     * @return nothing, instead it prints the results
     */
    @Override
    public String toString() {
        for(Personnel p: personnels){
            System.out.println(p.toString());
            System.out.println("--------------------");
        }
        return "";
    }

    //getters and setters
    public Personnel[] getPersonnels() {
        return personnels;
    }

    public void setPersonnels(Personnel[] personnels) {
        this.personnels = personnels;
    }

    public String[][] getPersonnelList() {
        return personnelList;
    }

    public void setPersonnelList(String[][] personnelList) {
        this.personnelList = personnelList;
    }
}

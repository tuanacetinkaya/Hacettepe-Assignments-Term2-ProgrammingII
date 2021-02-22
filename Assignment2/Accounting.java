import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class is responsible for calculating personnel salaries and printing their information in text files
 */
public class Accounting {

    private Personnel[] personnels; //list of personnel registered by Administration
    private String[][] monitoringList; //worked hours of every personnel

    Accounting(String[][] monitoringList, Personnel[] personnels){
        this.monitoringList = monitoringList;
        this.personnels = personnels;
        readMonitoring();
    }

    /**
     * Precondition: readMonitoring need to collect correct data from monitoring.txt no errors handled in non integer inputs
     * Read the monitoring data and assign it to personnel with respect to their registration numbers
     * Post Condition: every personnel class has an integer list of their weekly working hours.
     */
    private void readMonitoring(){
        int[] workingData;
        for(String[] data: monitoringList){
            for (Personnel personnel: personnels) {
                if(data[0].equals(personnel.getREGISTER_NUMBER())){
                    workingData = new int[]{Integer.valueOf(data[1]), Integer.valueOf(data[2]), Integer.valueOf(data[3]), Integer.valueOf(data[4])};
                    calculateSalaries(personnel,workingData);
                }
            }
        }
    }

    /**
     * Calculate salary for all personnel, cast them on proper types to use respective
     * @param personnel for each personnel on list
     * @param workingData is the integer list of weekly working hours
     */
    private void calculateSalaries(Personnel personnel, int[]workingData){
        personnel.setWorkingHours(workingData);
        personnel.calculateSalary();
    }

    /**
     * write output text files for personnel named by their registration numbers
     */
    public void writeOutputFileForPersonnels(){
        for(Personnel person: personnels){
            String fileName = person.getREGISTER_NUMBER()+ ".txt";
            try {
                PrintWriter outputFile = new PrintWriter(new FileWriter(fileName));
                outputFile.print(person.toString());
                outputFile.close();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
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

//getters and setters in case of need
    public Personnel[] getPersonnels() {
        return personnels;
    }
    public void setPersonnels(Personnel[] personnels) {
        this.personnels = personnels;
    }
    public String[][] getMonitoringList() {
        return monitoringList;
    }
    public void setMonitoringList(String[][] monitoringList) {
        this.monitoringList = monitoringList;
    }
}

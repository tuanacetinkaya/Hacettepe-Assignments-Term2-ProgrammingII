import java.util.MissingFormatArgumentException;

/**
 * Assignment 2
 *   >> due: 29.04.2020
 *   >> delivered: 24.04.2020
 * Subject: Inheritance
 * @author 21985164
 */
public class Main {
    public static void main(String[] args){
        //for fast execution below code can be used
//        String fileNameMonitoring = "monitoring.txt";
//        String fileNamePersonnel = "personnel.txt";

        String fileNamePersonnel;
        String fileNameMonitoring;
        // this block provides error messages: simply take console input and initialize the value to shopList and priceList
        fileNameMonitoring = fileNamePersonnel = "did not specified";
        try {
            fileNamePersonnel = args[0];
            fileNameMonitoring = args[1];

        } catch (MissingFormatArgumentException i) {
            System.out.println("Input Missing: Monitoring file and Personnel file has to be specified ");
            System.exit(0);
        } catch (ArrayIndexOutOfBoundsException n) {
            System.out.format("Be sure to enter both files. Here is what you have: \n " +
                    "\tMonitoring: %s %n \t Personnel: %s %n", fileNameMonitoring,fileNamePersonnel);
            System.exit(0);
        }//end of the console input block

        //setup the lists for use
        ReadFromFile myFile = new ReadFromFile(fileNameMonitoring,fileNamePersonnel);

        //create all personnel with respect to personnel.txt
        Administration universityAdministration = new Administration(myFile.getListPersonnel());

        //calculate their salary with respect to their weekly working hours on monitoring.txt
        Accounting universityAccounting = new Accounting(myFile.getListMonitoring(), universityAdministration.getPersonnels());

        //write output files named "<registrationNumber>.txt"
        universityAccounting.writeOutputFileForPersonnels();

        //to see all outputs in console below line could be used
        //System.out.println(universityAccounting.toString());
    }
}

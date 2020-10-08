import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFromFile {
    //file name information has to be in "fileName.txt" format
    private String strMonitoring;
    private String strPersonnel;
    //string arrays to store file information
    private String[][] listMonitoring;
    private String[][] listPersonnel;

    ReadFromFile(String strMonitoring, String strPersonnel){
        //get the file names
        this.strMonitoring = strMonitoring;
        this.strPersonnel = strPersonnel;

        //create temp list to split each line and get the list length
        String[] tempMonitoring = readFile(strMonitoring);
        String[] tempPersonnel = readFile(strPersonnel);

        //initialize the lists by corresponding lengths of their files

        listMonitoring = new String[tempMonitoring.length][5];
        listPersonnel = new String[tempPersonnel.length][4];

        /*
        Filling the actual lists from corresponding temporary list
           > listMonitoring format as below:
                [registration number] tab [1. week] tab [2. week] tab [3. week]tab [4. week] newline
           > listPersonnel format as below:
                [name and surname] tab [registration number] tab [position of pesonnel] tab [and year of start] newline
         */
        fillLists(tempMonitoring, listMonitoring);
        fillLists(tempPersonnel, listPersonnel);
    }

    //Assigned the lines to actual lists separate items by tab
    private void fillLists(String[] fileList, String[][] targetList){
        int index = 0;
        for (String line: fileList){
            targetList[index] = line.split("\t");
            index++;
        }
    }

    /**
     * Read the corresponding file and create temporary list without spliting items
     * @param path is the name of the file
     * @return the temporary list of file
     */
    private String[] readFile(String path){
        try {
            if(fileCheck()){
                int i = 0;
                int length = Files.readAllLines(Paths.get(path)).size();
                String[] results = new String[length];
                for (String line: Files.readAllLines(Paths.get(path))){
                    results[i++] = line;
                }
                return results;
            }else{
                System.out.println("File format is wrong");
                System.exit(0);
                return null;
            }


        }catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Please check the files and try again");
            System.exit(-1);
            return null;
        }
    }

    /**
     * Be sure the given files in txt format and has a proper name
     * @return true if nothing's wrong
     */
    private boolean fileCheck(){
        return  (strMonitoring.endsWith(".txt") && strPersonnel.endsWith(".txt") &&
                strMonitoring.length() > 4 && strPersonnel.length() > 4);
        //since the shortest input is a.txt (length 5)
    }

    //getters and setters
    public String[][] getListMonitoring() {return listMonitoring;}

    public String[][] getListPersonnel() { return listPersonnel;}
}

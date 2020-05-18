import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Will only be used once to get the initial data. Other modifications will be handled inside the
 * program and  the file will be rewritten depending on that modifications.
 */
public class ReadFiles {

    private ArrayList<String> listFormat; //file content transferred to list line by line
    private final String fileName;

    ReadFiles(String fileName){
        this.fileName = fileName;
    }

    /**
     * @return the file content in ArrayList<String> format
     */
    public ArrayList<String> getListFormat(){
        this.listFormat = readFile();
        return this.listFormat;
    }

    //in case you need to see the file content clearly
    public String toString(){
        return String.join("\n", listFormat);
    }

    /**
     * reads the given file and
     * @return the ArrayList
     */
    private ArrayList<String> readFile(){
        ArrayList<String> allLines = new ArrayList<String>();
        Scanner fileScan = null;
        try {
            File theFile = new File(fileName);
            fileScan = new Scanner(theFile);
        } catch (FileNotFoundException e) {
            System.out.println("This file cannot be opened: " + fileName);
            System.exit(0);
        }
        int i = 0;
        while (fileScan.hasNextLine()){
            allLines.add(fileScan.nextLine());
        }
        fileScan.close();
        return allLines;
    }
}

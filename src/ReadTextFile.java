import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadTextFile {
    private final String[] listFormat; //file content transferred to list line by line
    private final String fileName;

    /**
     * Reads the spesific text file and return the content inside a String array.
     * @param fileName is the file to be read
     */
    ReadTextFile(String fileName){
        this.fileName = fileName;
        this.listFormat = readFile();
    }

    /**
     * @return the file content in String[] format
     */
    public String[] getListFormat(){
        return this.listFormat;
    }

    //in case you need to see the file content clearly
    public String toString(){
        return String.join("\n", listFormat);
    }

    /**
     * reads the given file and
     * @return the Array
     */
    private String[] readFile(){
        String[] allLines = null;
        Scanner fileScan = null;
        try {
            int length = Files.readAllLines(Paths.get(fileName)).size();
            if(length == 0){
                System.out.println("The file is empty. Please check and try again.");
                System.exit(0);
            }
            allLines = new String[length];
            File theFile = new File(fileName);
            fileScan = new Scanner(theFile);
        } catch (FileNotFoundException e) {
            System.out.println("This file cannot be opened: " + fileName);
            System.exit(0);
        }catch (IOException i){
            System.out.println("Check the file name again!");
            System.exit(0);
        }
        int i = 0;
        while (fileScan.hasNextLine()){
            allLines[i++] = (fileScan.nextLine());
        }
        fileScan.close();
        return allLines;
    }
}

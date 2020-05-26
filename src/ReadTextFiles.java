import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadTextFiles {
    private final int[] listFormat; //file content transferred to list line by line
    private final String fileName;

    ReadTextFiles(String fileName){
        this.fileName = fileName;
        this.listFormat = readFile();
    }

    /**
     * @return the file content in int[] format
     */
    public int[] getListFormat(){
        return this.listFormat;
    }

    //in case you need to see the file content clearly
    public String toString(){
        StringBuilder list = new StringBuilder();
        for(int element: listFormat){
            list.append(element + " ");
        }
        return list.toString();
    }

    /**
     * reads the given file and
     * @return the Array
     */
    private int[] readFile(){
        int[] allList = null;
        Scanner fileScan = null;
        try {

            File theFile = new File(fileName);
            fileScan = new Scanner(theFile);
            int length = 0;
            while (fileScan.hasNextInt()){
                fileScan.nextInt();
                length++;
            }
            allList = new int[length];
            fileScan = new Scanner(theFile);

        } catch (FileNotFoundException e) {
            System.out.println("This file cannot be opened: " + fileName);
            System.exit(0);
        }
        int i = 0;
        while (fileScan.hasNextInt()){
            allList[i] = fileScan.nextInt();
            i++;
        }
        fileScan.close();
        return allList;
    }
}

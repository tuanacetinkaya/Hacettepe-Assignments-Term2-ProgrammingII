package Quizes.Quiz2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.SplittableRandom;

/**
 * That class handle file reading and send the whole context to String array, splited by tabs
 * -> Will handle the semi colon ":" between the result later
 * sample input file format:
 * [First letter of sport]tab[name of first club]tab[name of second club]tab[match result]newline
 */

public class FileStatistics {
    private String fileName;
    private String[][] allStatistics;

    FileStatistics(String fileName){
        this.fileName = fileName;
        this.allStatistics = fileRead();
    }
    //reads the statistics and pass it to String array allStatistics
    private String[][] fileRead(){
        try {
            List<String> myFile = Files.readAllLines(Paths.get(fileName));

            //@length can be replaced with 24 as hard code but i like to do it old way
            // length explained under sample input
            int length = myFile.size();
            String[][] results = new String[length][4];

            int i =0;
            for (String line : myFile ){
                results[i++] = line.split("\t");
            }
            return results;
        } catch (FileNotFoundException e) {
            System.err.println("No such file exist.");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("An error occured while opening file as stated:");
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return null;
    }

    //getter
    public String[][] getAllStatistics() {
        return allStatistics;
    }

}

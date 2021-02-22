package Quizes.Quiz2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SportsChannel {
    private String[][] sportsList;
    private Basketball basketball;
    private Handball handball;
    private IceHockey iceHockey;
    private Volleyball volleyball;

    SportsChannel(String[][] sportsList){
        this.sportsList = sportsList;
        this.basketball = new Basketball();
        this.handball = new Handball();
        this.iceHockey = new IceHockey();
        this.volleyball = new Volleyball();
        readList();

    }

    /**
     * Send the matches to related Sports. All points and results will be handled there
     */
    private void readList(){
        for( String[] line: sportsList){
            int scoreHome = Integer.valueOf(line[3].split(":")[0]);
            int scoreAgainst = Integer.valueOf(line[3].split(":")[1]);
            switch(line[0]){
                case("B"):
                    basketball.newMatch(line[1], line[2],scoreHome, scoreAgainst);
                    continue;
                case("H"):
                    handball.newMatch(line[1], line[2],scoreHome, scoreAgainst);
                    continue;
                case("I"):
                    iceHockey.newMatch(line[1], line[2],scoreHome, scoreAgainst);
                    continue;
                case("V"):
                    volleyball.newMatch(line[1], line[2],scoreHome, scoreAgainst);
            }
        }
    }

    /**
     * For every Sport this method writes a txt file that contains results in it.
     */
    public void writeOutput(){
        try {
            for (Sports sport: new Sports[]{basketball, volleyball, handball, iceHockey}) {
                String fileName = String.format("%s.txt", sport.getSportsName());
                PrintWriter out = new PrintWriter(new FileWriter(fileName));
                //output to the file a line
                out.println(sport.teamOutputs());
                //close the file
                out.close();
            }
        }
        catch(IOException e1) {
            System.out.println("Error during reading/writing");
        }
    }
}

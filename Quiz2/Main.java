package Quizes.Quiz2;

public class Main {
    /**
     * @author 21985164
     * Quiz2 (12.04.2020)
     * Prerequisite: Console arguments cannot be wrong or missing, these exceptions are not handled in this code.
     */
    public static void main(String[] args){
        FileStatistics matches = new FileStatistics(args[0]);
        SportsChannel channel  = new SportsChannel(matches.getAllStatistics());
        channel.writeOutput();
    }
}

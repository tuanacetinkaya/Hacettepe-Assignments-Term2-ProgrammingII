package Quizes.Quiz2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Team{

    private final String nameOfClub;
    private int awardWon, awardLose, awardEven;
    private int numPlayed, numWon, numEven, numLoss, setsFor, setsAgainst, totalPoint;

    Team(String nameOfClub, int awardWon, int awardLose, int awardEven){
        this.nameOfClub = nameOfClub;
        this.awardWon = awardWon;
        this.awardLose = awardLose;
        this.awardEven = awardEven;
        numPlayed = numWon = numEven = numLoss = setsFor = setsAgainst = totalPoint = 0;

    }

    /**
     * all calculations handled here
     * @param setsFor is first team's sets
     * @param setsAgainst is second team's sets
     */
    public void newMatchOfTeam(int setsFor, int setsAgainst){

        this.numPlayed++;
        this.setsFor += setsFor;
        this.setsAgainst += setsAgainst;
        if (setsFor > setsAgainst){
            this.numWon ++;
            this.totalPoint += awardWon;
        }else if(setsFor == setsAgainst){
            this.numEven ++;
            this.totalPoint += awardEven;
        }else{
            this.numLoss++;
            this.totalPoint+= awardLose;
        }

    }

    public String getNameOfClub() {
        return nameOfClub;
    }


    public void setScores(int awardWon, int awardLose, int awardEven) {
        this.awardWon = awardWon;
        this.awardLose = awardLose;
        this.awardEven = awardEven;
    }
    public String getScores(){
        return String.format("win: %d - tie: %d - lose: %d",awardWon , awardEven , awardLose);
    }

    public int getTotalPoint() {
        return totalPoint;
    }

    public int getSetsFor() {
        return setsFor;
    }

    public int getSetsAgainst() {
        return setsAgainst;
    }

    /**
     * [ranking.]tab[name of club]tab[number of played matches]tab[ number of matches won]tab[match
     * with an even score or tie]tab[ number of matches loss]tab[ number of sets For : the number of sets
     * Against]tab[total points]newline
     * @return the whole thing as a string splited by tabs
     */

    public String teamText(){
        return String.format("%s\t%d\t%d\t%d\t%d\t%d:%d\t%d",
                nameOfClub,numPlayed,numWon,numEven,numLoss,setsFor,setsAgainst,totalPoint);
    }

}

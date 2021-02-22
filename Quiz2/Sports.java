package Quizes.Quiz2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Parent class for Sports, all other Sports only has award points for win, lose and tie.
 * sample output file format:
 * [ranking.]tab[name of club]tab[number of played matches]tab[ number of matches won]tab[match
 * with an even score or tie]tab[ number of matches loss]tab[ number of sets For : the number of sets
 * Against]tab[total points]newline
 */

public class Sports{
    private final String sportsName;
    private int awardWinner;
    private int awardLoser;
    private int onATie;
    private Team[] teamList;
    private int index = 0;

    Sports(){this.sportsName = "";} //default constructor has to exist for super class

    Sports(String sportsName, int awardWinner, int awardLoser, int onATie){
        this.sportsName = sportsName;
        this.awardWinner = awardWinner;
        this.awardLoser = awardLoser;
        this.onATie = onATie;
        //4 teams for each sport by default
        this.teamList = new Team[4];
    }


    public void newMatch(String homeTeam, String againstTeam, int scoreHome, int scoreAgainst){
        addTeam(homeTeam, scoreHome, scoreAgainst);
        addTeam(againstTeam, scoreAgainst, scoreHome);
    }

    /**
     * Setting up points for teams, will be override in Volleyball class
     * @param team is the team we take as home
     * @param scoreOfTeam obvious
     * @param scoreAgainst again :)
     */
    public void addTeam(String team, int scoreOfTeam, int scoreAgainst){
        for(Team club : teamList){
            if(club != null){
                if (club.getNameOfClub().equals(team)){
                    club.newMatchOfTeam(scoreOfTeam, scoreAgainst);
                    return;
                }
            }
        }
        teamList[index] = new Team(team, awardWinner,awardLoser,onATie);
        teamList[index].newMatchOfTeam(scoreOfTeam, scoreAgainst);
        index++;
    }

    /**
     * Override comparator to be able to compare Team objects to their total points
     */
    private Comparator<Team> compareTeams = new Comparator<Team>() {
        @Override
        public int compare(Team o1, Team o2) {
            int o1Total = o1.getTotalPoint();
            int o2Total = o2.getTotalPoint();
            if (o1Total > o2Total){
                return 1;
            }else if(o1Total == o2Total){
                if( (o1.getSetsFor() - o1.getSetsAgainst()) > (o2.getSetsFor() - o2.getSetsAgainst())){
                    return 1;
                }
            }
            return -1;
        }
    };


    public String teamOutputs(){
        Arrays.sort(teamList, Collections.reverseOrder(compareTeams));
        String output = "";
        for(int i = 0; i < 4; i++){
            output += String.format("%d.\t", i+1)+ teamList[i].teamText() + "\n";
        }
        return output;
    }

    public Team[] getTeamList() {
        return teamList;
    }
    public int getIndex(){
        return index;
    }

    public void setTeamList(Team[] teamList) {
        this.teamList = teamList;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getSportsName() {
        return sportsName;
    }
}

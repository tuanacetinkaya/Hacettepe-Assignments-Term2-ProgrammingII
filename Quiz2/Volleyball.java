package Quizes.Quiz2;

public class Volleyball extends Sports {
    private Team[] teamList;
    private int index;


    Volleyball(){
        super("Volleyball",0,0,0); //scores will be updated later below
        this.teamList = super.getTeamList();
        this.index = super.getIndex();
    }

    /**
     * An exception to super class. We need to check scores to decide the points given
     * @param team is the team we take as home
     * @param scoreOfTeam obvious
     * @param scoreAgainst again :)
     */
    @Override
    public void addTeam(String team, int scoreOfTeam, int scoreAgainst){
        this.teamList = super.getTeamList();
        this.index = super.getIndex();
        for(Team club : teamList){
            if(club != null){
                if (club.getNameOfClub().equals(team)){
                    findScore(club, scoreOfTeam, scoreAgainst);
                    club.newMatchOfTeam(scoreOfTeam, scoreAgainst);
                    return;
                }
            }
        }
        teamList[index] = new Team(team,0,0,0);
        findScore(teamList[index], scoreOfTeam, scoreAgainst);
        teamList[index].newMatchOfTeam(scoreOfTeam, scoreAgainst);
        index++;
        super.setTeamList(this.teamList);
        super.setIndex(this.index);
    }

    //helper method for addTeam
    private void findScore(Team club, int scoreOfTeam, int scoreAgainst){
        //where the score is 3:1 or 3:0
        if((scoreOfTeam == 3 && (scoreAgainst == 1 || scoreAgainst == 0))
                || scoreAgainst == 3 && (scoreOfTeam == 1 || scoreOfTeam == 0)){
            club.setScores(3,0,0);

            //where the score is 3:2
        }else if((scoreOfTeam == 3 && scoreAgainst == 2)
                || scoreAgainst == 3 && scoreOfTeam == 2){
            club.setScores(2,1,0);
        }

    }
}

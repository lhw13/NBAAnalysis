package server.po;

import java.util.ArrayList;
import java.util.Calendar;

public class MatchPO {
	public MatchPO() {
	}

	public MatchPO(String season, Calendar date, ScorePO finalScore,
			ArrayList<ScorePO> scores, TeamInMatchesPO team1,
			TeamInMatchesPO team2) {
		super();
		this.season = season;
		this.date = date;
		this.finalScore = finalScore;
		this.scores = scores;
		this.team1 = team1;
		this.team2 = team2;
	}
	public String toString()
	{
		int month = date.get(Calendar.MONTH)+1;
		int day = date.get(Calendar.DAY_OF_MONTH);
		return (season+"_"+month+"-"+day+"_"+team1.abbreviation+"-"+team2.abbreviation);
	}
	String fileName;
	String season;
	Calendar date;
	ScorePO finalScore;
	ArrayList<ScorePO> scores = new ArrayList<ScorePO>();
	TeamInMatchesPO team1;
	TeamInMatchesPO team2;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public ScorePO getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(ScorePO finalScore) {
		this.finalScore = finalScore;
	}

	public ArrayList<ScorePO> getScores() {
		return scores;
	}

	public void setScores(ArrayList<ScorePO> scores) {
		this.scores = scores;
	}

	public TeamInMatchesPO getTeam1() {
		return team1;
	}

	public void setTeam1(TeamInMatchesPO team1) {
		this.team1 = team1;
	}

	public TeamInMatchesPO getTeam2() {
		return team2;
	}

	public void setTeam2(TeamInMatchesPO team2) {
		this.team2 = team2;
	}
}

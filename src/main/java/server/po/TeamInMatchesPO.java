package server.po;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import server.businesslogic.Player;

public final  class TeamInMatchesPO {
	public TeamInMatchesPO() {
	}

	public TeamInMatchesPO(String abbreviation,
			ArrayList<PlayerInMatchesPO> players) {
		super();
		this.abbreviation = abbreviation;
		this.players = players;
	}

	String abbreviation;
	ArrayList<Integer> scores;// 每节得分
	ArrayList<PlayerInMatchesPO> players = new ArrayList<PlayerInMatchesPO>();

	public static Comparator<PlayerInMatchesPO> comparePointDesc = new Comparator<PlayerInMatchesPO>() {  
		  
	    @Override  
	    public int compare(PlayerInMatchesPO o1, PlayerInMatchesPO o2) {  
	    	Integer i2=o2.getScore();
	    	Integer i1=o1.getScore();
	        return i2.compareTo(i1);
	    	//return o2.getPoint() > o1.getPoint() ? 1 : -1;
	    }
	};
	
	void sortPlayersByScore() {
		Collections.sort(players, comparePointDesc);
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public ArrayList<PlayerInMatchesPO> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<PlayerInMatchesPO> players) {
		this.players = players;
	}
	
	public PlayerInMatchesPO getHighestScore()
	{
		int high =0;
		PlayerInMatchesPO result=null;
		for(int i=0;i<players.size();i++)
			if(high<players.get(i).getScore())
			{
				result = players.get(i);
				high = result.getScore();
			}
		return result;
	}
	
	public PlayerInMatchesPO getHighestRebound()
	{
		int high =0;
		PlayerInMatchesPO result=null;
		for(int i=0;i<players.size();i++)
			if(high<players.get(i).getTotalRebound())
			{
				result = players.get(i);
				high = result.getTotalRebound();
			}
		return result;
	}
	
	public PlayerInMatchesPO getHighestAssist()
	{
		int high =0;
		PlayerInMatchesPO result=null;
		for(int i=0;i<players.size();i++)
			if(high<players.get(i).getAssist())
			{
				result = players.get(i);
				high = result.getAssist();
			}
		return result;
	}
	
	public PlayerInMatchesPO getHighestSteal()
	{
		int high =0;
		PlayerInMatchesPO result=null;
		for(int i=0;i<players.size();i++)
			if(high<players.get(i).getSteal())
			{
				result = players.get(i);
				high = result.getSteal();
			}
		return result;
	}
	
	public PlayerInMatchesPO getHighestBlock()
	{
		int high =0;
		PlayerInMatchesPO result=null;
		for(int i=0;i<players.size();i++)
			if(high<players.get(i).getBlock())
			{
				result = players.get(i);
				high = result.getBlock();
			}
		return result;
	}
}

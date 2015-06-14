package server.data;

import java.util.ArrayList;

import server.po.MatchPO;

public class DataClass {
	private  ArrayList<MatchPO> oneSeasonMatch=null;
	private boolean isOK=false;
	public void setIsOK(boolean IsOK){
		this.isOK=IsOK;
	}
	public boolean isOK(){
		return this.isOK;
	}
	public ArrayList<MatchPO> getList(){
		return this.oneSeasonMatch;
	}
	public void setList( ArrayList<MatchPO> oneSeason){
		this.oneSeasonMatch=oneSeason;
	}
}

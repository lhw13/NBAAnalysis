package server.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.ScorePO;

public class DataTransformation {
	public static void MatchPOToText(MatchPO matchPO,String address) throws IOException{
		String Abbreviation1=matchPO.getTeam1().getAbbreviation();
		String Abbreviation2=matchPO.getTeam2().getAbbreviation();
		File f=new File(address+"/"+matchPO.getFileName());
		FileWriter fw=new FileWriter(f);
		String text="";
		text=matchPO.getSeason()+";"+Abbreviation1+"-"+Abbreviation2+";"+matchPO.getFinalScore().getTeam1()+"-"+matchPO.getFinalScore().getTeam2()+";\r\n";
		for(ScorePO i:matchPO.getScores()){
			text=text+i.getTeam1()+"-"+i.getTeam2()+";";
		}
		text=text+"\r\n"+Abbreviation1;
		for(PlayerInMatchesPO i:matchPO.getTeam1().getPlayers()){
			text=text+"\r\n"+i.getName()+";"+i.getPosition()+";"+secondToformat(i.getPlayTime())+";"+i.getHit()+";"+i.getShot()+";"+i.getThirdHit()+";"+i.getThirdshot()+";"+i.getFreeHit()+";"+i.getFreeshot()+";"+i.getOffensiveRebound()+";"+i.getDefensiveRebound()+";"+i.getTotalRebound()+";"+i.getAssist()+";"+i.getSteal()+";"+i.getBlock()+";"+i.getMiss()+";"+i.getFoul()+";"+i.getScore()+";";
		}
		text=text+"\r\n"+Abbreviation2;
		for(PlayerInMatchesPO i:matchPO.getTeam2().getPlayers()){
			text=text+"\r\n"+i.getName()+";"+i.getPosition()+";"+secondToformat(i.getPlayTime())+";"+i.getHit()+";"+i.getShot()+";"+i.getThirdHit()+";"+i.getThirdshot()+";"+i.getFreeHit()+";"+i.getFreeshot()+";"+i.getOffensiveRebound()+";"+i.getDefensiveRebound()+";"+i.getTotalRebound()+";"+i.getAssist()+";"+i.getSteal()+";"+i.getBlock()+";"+i.getMiss()+";"+i.getFoul()+";"+i.getScore()+";";
		}
		fw.write(text);
		fw.close();
	}
private static String secondToformat(int second){
	String result="";
	int se=second%60;
	int mi=second/60;
	DecimalFormat d = new DecimalFormat("00");
	result=d.format(mi)+":"+d.format(se);
	return result;
}
}

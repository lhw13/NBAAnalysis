package server.data;

import java.util.HashMap;

public class TransToStandard {
	private static HashMap<String, String> data=new HashMap<>(100);
	private static String[] seasonList=new String[30];
	static{
		for(int y=1985;y<2015;y++){
			String y1 = (y + "").substring(2, 4);
			String y2 = ((y + 1) + "").substring(2, 4);
			String season = y1 + "-" + y2;
			seasonList[y-1985]=season;
		}
		for(int y=1985;y<2015;y++){
			if(y>=2012){
			data.put(seasonList[y-1985]+"BRK","BKN");
			}else{
				data.put(seasonList[y-1985]+"NJN","BKN");	
			}
		}
		for(int y=1985;y<2015;y++){
			if(y==2014){
				data.put(seasonList[y-1985]+"CHO","CHA");
			}
			else if(y>=2004){
				data.put(seasonList[y-1985]+"CHA","CHA");
			}
			else{
				data.put(seasonList[y-1985]+"CHH","CHA");
			}
		}
		for(int y=1985;y<2015;y++){
			if(y>=2001){
				data.put(seasonList[y-1985]+"MEM","MEM");
			}
			else{
				data.put(seasonList[y-1985]+"VAN","MEM");
			}
		}
		for(int y=1985;y<2015;y++){
			if(y>=2013){
				data.put(seasonList[y-1985]+"NOP","NOP");
			}
			else if(y>=2007){
				data.put(seasonList[y-1985]+"NOH","NOP");
			}
			else if(y>=2005){
				data.put(seasonList[y-1985]+"NOK","NOP");
			}
			else if(y>=2002){
				data.put(seasonList[y-1985]+"NOH","NOP");
			}
		}
		for(int y=1985;y<2015;y++){
			if(y>=2008){
				data.put(seasonList[y-1985]+"OKC","OKC");
			}
			else{
				data.put(seasonList[y-1985]+"SEA","OKC");
			}
		}
		for(int y=1985;y<2015;y++){
			if(y>=1997){
				data.put(seasonList[y-1985]+"WAS","WAS");
			}
			else{
				data.put(seasonList[y-1985]+"WSB","WAS");
			}
		}
		for(int y=1985;y<2015;y++){
			data.put(seasonList[y-1985]+"PHO","PHX");
		}
	}
	public static void main(String[] args) {
		System.out.println(getStandard("NOH"));
	}
	public static String getStandard(String ab){
		String result="";
		result=data.get(DatabaseController.getSeason()+ab);
		if(result==null){
			result=ab;
		}
		return result;
	}
}

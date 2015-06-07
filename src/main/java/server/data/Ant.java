package server.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.ScorePO;
import server.po.TeamInMatchesPO;

public class Ant {

	public static void main(String[] args) throws IOException {
		 //TODO Auto-generated method stub
		Properties prop = System.getProperties();
		prop.setProperty("http.proxyHost", "localhost"); 
		prop.setProperty("http.proxyPort", "56670"); 
		getOneSeasonData(2008);
	}
	private static void getOneSeasonData(int year) throws IOException{
		int year2=year+1;
		String filename=year+"-"+year2;
		File f=new File("E:\\dataText\\"+filename);
		f.mkdirs();
		String address="E:/dataText/"+filename;
		int start=year*10000+10*100+29;
		int end=year2*10000+631;
		int i=start;
		for(;i<=end;i++){
			if(i%100<=31){
				if(i%10000-i%100<=1200){
					String[] matchUrlList=analysePageByDay("http://www.nba.com/gameline/"+i+"/");
					if(matchUrlList!=null){
						for(String url:matchUrlList){
							MatchPO mp=analyseteaminfo(url);
							if(mp!=null){
							//DataTransformation.MatchPOToText(mp,address );
							}
						}
					}
				}
			}
		}
	}
	private static String[] analysePageByDay(String url) {
		// 解析某一天含有的赛事
		int number = -1;
		String list[] = new String[100];
		String formatOfUrl = "/games/\\d{8}/[A-Z]{6}/gameinfo.html";
		Pattern p = Pattern.compile(formatOfUrl);
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url)
					.openConnection();
			conn.setRequestMethod("GET");
			conn.setUseCaches(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			String str = null;
			while ((str = reader.readLine()) != null) {
				Matcher m = p.matcher(str);
				while (m.find()) {
					number++;
					list[number] = "http://www.nba.com" + m.group();
				}
			}
		} catch (Exception e) {
			return null;
		}
		if (number > -1) {
			String gameInfo[] = new String[(number + 1) / 2];
			for (int i = 0; number > -1; i++) {
				number -= 2;
				gameInfo[i] = list[2 * i];
			}
			return gameInfo;
		} else
			return null;
	}

	public static MatchPO analyseteaminfo(String url) {
		String a = url.substring(34, 37);
		String b = url.substring(37, 40);
		String dateString = url.substring(25, 33);
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
		Date date=null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String season=getSeason(dateString);
		String html = "";
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url)
					.openConnection();
			conn.setRequestMethod("GET");
			conn.setUseCaches(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			String str = null;

			while ((str = reader.readLine()) != null) {
				html = html + "\r\n" + str;
			}
		} catch (Exception e) {
			return null;
		}
		Document doc = Jsoup.parse(html);
		 String c =doc.select("div[id=nbaGIQtrScrs]").html();
		 String[] score=new String[8];
		 int n=0;
		 Pattern p=Pattern.compile("<td class=\"nbaGIQtrPts\">(?<abc>.*)</td>");
		 Matcher m=p.matcher(c);
		 while(m.find()){
			 score[n]=m.group("abc");
			 n++;
		 }
		 int t1score1=stringToInt(score[0]);
		 int t1score2=stringToInt(score[1]);
		 int t1score3=stringToInt(score[2]);
		 int t1score4=stringToInt(score[3]);
		 int t2score1=stringToInt(score[4]);
		 int t2score2=stringToInt(score[5]);
		 int t2score3=stringToInt(score[6]);
		 int t2score4=stringToInt(score[7]);
		 int t1final=t1score1+t1score2+t1score3+t1score4;
		 int t2final=t2score1+t2score2+t2score3+t2score4;
		 ArrayList<ScorePO> scores = new ArrayList<ScorePO>(10);
		 scores.add(new ScorePO(t1score1,t2score1));
		 scores.add(new ScorePO(t1score2,t2score2));
		 scores.add(new ScorePO(t1score3,t2score3));
		 scores.add(new ScorePO(t1score4,t2score4));
		 ScorePO finalScore=new ScorePO(t1final,t2final);
		Elements d = doc
				.select("table[id=nbaGITeamStats][cellpadding=0][cellspacing=0]");
		if(d.first()==null){
			return null;
		}

		String firstTeam = d.first().html();
		String secondTeam = d.get(1).html();
		ArrayList<PlayerInMatchesPO> players1=analyseTeam(firstTeam);
		ArrayList<PlayerInMatchesPO> players2=analyseTeam(secondTeam);
		if(players1==null||players2==null){
			return null;
		}
		TeamInMatchesPO team1=new TeamInMatchesPO(a,players1);
		TeamInMatchesPO team2=new TeamInMatchesPO(b,players2);
		MatchPO matchPO= new MatchPO( season,  calendar,  finalScore,scores,  team1, team2);
		matchPO.setFileName(season+"_"+dateString.substring(4, 6)+"-"+dateString.substring(6, 8)+"_"+a+"-"+b);
		return matchPO;
	}

	private static ArrayList<PlayerInMatchesPO> analyseTeam(String teaminfo) {
		ArrayList<PlayerInMatchesPO> result = new ArrayList<PlayerInMatchesPO>(20);
		Pattern p1 = Pattern.compile("<tr class=(\"odd\"|\"even\").*?</tr>",
				Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m1 = p1.matcher(teaminfo);
		while (m1.find()) {
			PlayerInMatchesPO a=analysePlayerScore(m1.group());
			if(a!=null)
			result.add(a);

		}
		if(result.size()==0){
			return null;
		}
		return result;
	}

	private static PlayerInMatchesPO analysePlayerScore(
			String PlayerInMatchesInfo) {
		Pattern p1 = Pattern
				.compile("td id=.*/playerfile/(?<abc>.*)/index.html.*</td>");
		Matcher m1 = p1.matcher(PlayerInMatchesInfo);
		String name="";
		String position="";
		
		while (m1.find()) {
			String a[] = m1.group("abc").split("_");
			for (String i:a){
				char first=(char) (i.charAt(0) + 'A' - 'a');
				name=name+first+i.substring(1) + " ";
			}
			name=name.trim();
			break;
		}
		if (name.equals("")){
			return null;
		}
	
		Pattern p2 = Pattern
				.compile("<td class=\"nbaGIPosition\">(?<abc>.*)</td>");
		Matcher m2 = p2.matcher(PlayerInMatchesInfo);
		if (m2.find()) {
			String po = m2.group("abc");
			if (po.equals("&nbsp;")||po.equals("")) {
				position="";
			} else {
				position=po;
			
			}
		
		}
		else{
			return null;
		}
		String list[] = new String[15];
		int n = 0;
		Pattern p3 = Pattern.compile("<td>(?<abc>.*)</td>");
		Matcher m3 = p3.matcher(PlayerInMatchesInfo);
		while (m3.find()) {
			list[n] = m3.group("abc");
			n++;
		}
		int playTimeBySeconds = -1;// 脏数据取负值
	
			String sa[] = list[0].split(":");
		if (sa.length == 2) {
			playTimeBySeconds = stringToInt(sa[0]) * 60 + stringToInt(sa[1]);
		}
		if(playTimeBySeconds==0){
			return null;
		}
		String[] FGMA=list[1].split("-");
		int hit = stringToInt(FGMA[0]);// 命中
		int shot = stringToInt(FGMA[1]);// 出手
		String[] threePMA=list[2].split("-");
		int thirdHit = stringToInt(threePMA[0]);
		int thirdshot = stringToInt(threePMA[1]);
		String[] FTMA=list[3].split("-");
		int freeHit = stringToInt(FTMA[0]);
		int freeshot = stringToInt(FTMA[1]);
		int offensiveRebound = stringToInt(list[5]);
		int defensiveRebound = stringToInt(list[6]);
		int totalRebound = stringToInt(list[7]);
		int assist = stringToInt(list[8]);
		int steal = stringToInt(list[10]);
		int block = stringToInt(list[12]);
		int miss = stringToInt(list[11]);
		int foul = stringToInt(list[9]);
		int score = stringToInt(list[14]);
		PlayerInMatchesPO result = new PlayerInMatchesPO(name, position, playTimeBySeconds, hit,
				shot, thirdHit, thirdshot, freeHit, freeshot, offensiveRebound,
				defensiveRebound, totalRebound, assist, steal, block, miss,
				foul, score);
//		System.out.print(name+" ");
//		System.out.print(position+" ");
//		System.out.print(playTimeBySeconds+" ");
//		System.out.print(hit+" ");
//		System.out.print(shot+" ");
//		System.out.print(thirdHit+" ");
//		System.out.print(thirdshot+" ");
//		System.out.print(freeHit+" ");
//		System.out.print(freeshot+" ");
//		System.out.print(offensiveRebound+" ");
//		System.out.print(defensiveRebound+" ");
//		System.out.print(totalRebound+" ");
//		System.out.print(assist+" ");
//		System.out.print(steal+" ");
//		System.out.print(block+" ");
//		System.out.print(miss+" ");
//		System.out.print(foul+" ");
//		System.out.print(score+" ");
//		System.out.print("\n");
		return result;
	}
	private static final String getSeason(String dateString){
		String result="";
		int year=stringToInt(dateString.substring(2, 4));
		int month=stringToInt(dateString.substring(4, 6));
		if(month>=10){
			int year2=year+1;
			result=year+"-"+year2;
		}
		else{
			int year2=year-1;
			result=year2+"-"+year;
		}
		return result;
	}
	private static final int stringToInt(String str) {
		int result;
		try {
			result = Integer.parseInt(str);
		} catch (Exception e) {
			result = -1;
		}
		return result;
	}
}

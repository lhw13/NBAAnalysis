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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import server.po.MatchPO;
import server.po.PlayerInMatchesPO;
import server.po.ScorePO;
import server.po.TeamInMatchesPO;

public class AntBasketball implements Runnable {
	private static String season = "15-16";
	private static int seasonNumber = 30;
	private static ArrayList<String> oneSeason = null;
	private static int exChange = 0;
	private static boolean th1 = false;
	private static boolean th2 = false;
	private static boolean th3 = false;
	int threadN = 0;

	public static void main(String[] args) throws InterruptedException {
		// getSeasonUrl("http://www.basketball-reference.com/leagues/");
		 //getOneSeason("http://www.basketball-reference.com/leagues/NBA_2016_games.html");
		// TODO Auto-generated method stub
//		String seasonUrl[] = getSeasonUrl("http://www.basketball-reference.com/leagues/");
//
//		for (int j = 0; j < 1; j++) {
//			th1 = false;
//			th2 = false;
//			th3 = false;
//		//	oneSeason = getOneSeason(seasonUrl[j]);
//			oneSeason = getOneSeason("http://www.basketball-reference.com/leagues/NBA_2016_games.html");
//			File f1 = new File("D:\\dataBasketball\\" + season + "regular");
//			File f2 = new File("D:\\dataBasketball\\" + season + "playOff");
//			if (!f1.exists())
//				f1.mkdirs();
//			if (!f2.exists())
//				f2.mkdirs();
//			exChange = 0;
//			for (int i = 0; i < oneSeason.size(); i++) {
//				if (oneSeason.get(i).equals("playOff")) {
//					exChange = i;
//				}
//			}
//			AntBasketball a = new AntBasketball();
//			AntBasketball b = new AntBasketball();
//			AntBasketball c = new AntBasketball();
//			a.setN(0);
//			b.setN(1);
//			c.setN(2);
//			Thread ta = new Thread(a);
//			Thread tb = new Thread(b);
//			Thread tc = new Thread(c);
//			ta.start();
//			tb.start();
//			tc.start();
//			while (!(th1 && th2 && th3)) {
//				Thread.sleep(1000);
//			}
//		}
		
		try {
			DataTransformation.MatchPOToText(
					getMatchPO("http://www.basketball-reference.com/boxscores/201603020HOU.html"),"D:\\dataBasketball\\15-16regular");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}

	public void run() {
		try {
			work(oneSeason, exChange, threadN);
			switch (this.threadN) {
			case 0:
				th1 = true;
				break;
			case 1:
				th2 = true;
				break;
			case 2:
				th3 = true;
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setN(int n) {
		this.threadN = n;
	}

	private static void work(ArrayList<String> oneSeason, int exChange, int n)
			throws Exception {
		String address1 = "D:\\dataBasketball\\" + season + "regular";
		String address2 = "D:\\dataBasketball\\" + season + "playOff";
		for (int i = 0; i < oneSeason.size(); i++) {
			if (i % 3 == n) {
				if (i < exChange) {
					try {
						DataTransformation.MatchPOToText(
								getMatchPO(oneSeason.get(i)), address1);
					} catch (Exception e) {
						System.out.println(oneSeason.get(i));
						e.printStackTrace();
					}
				} else if (i > exChange) {
					try {
						DataTransformation.MatchPOToText(
								getMatchPO(oneSeason.get(i)), address2);
					} catch (Exception e) {
						System.out.println(oneSeason.get(i));
						e.printStackTrace();
					}
				}
			}
		}
	}

	private static String[] getSeasonUrl(String url) {
		String[] result = new String[100];
		int number = 0;
		// http://www.basketball-reference.com/leagues/NBA_2015.html
		String formatOfUrl = "<td.*/leagues/NBA_(?<abc>\\d{4}).html.*\\d{4}-\\d{2}</a></td>";
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
					result[number] = "http://www.basketball-reference.com/leagues/NBA_"
							+ m.group("abc") + "_games.html";
					number++;
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static ArrayList<String> getOneSeason(String url) {
		Pattern px = Pattern.compile("\\d{4}");
		Matcher mx = px.matcher(url);
		while (mx.find()) {
			String year2 = mx.group().substring(2, 4);
			int y2 = stringToInt(mx.group());
			int y1 = y2 - 1;
			String year1 = (y1 + "").substring(2, 4);
			season = year1 + "-" + year2;
		}
		ArrayList<String> result = new ArrayList<String>(2000);
		String formatOfUrl = "<td align=\"center\" ><a href=\"(?<abc>/boxscores/\\d{9}[A-Z]{3}.html)\">Box Score</a></td>";
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
				if (str.indexOf("<h2 data-mobile-header=\"\" style=\"\">Playoffs</h2>") >= 0) {
					System.out.println(result.size());
					result.add("playOff");
				}
				Matcher m = p.matcher(str);
				while (m.find()) {
					result.add("http://www.basketball-reference.com"
							+ m.group("abc"));
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// http://www.basketball-reference.com/leagues/NBA_2015.html
		System.out.println(result.size());
		return result;
	}

	private static MatchPO getMatchPO(String url) {
		String dateString = url.substring(46, 54);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
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
			reader.close();
		} catch (Exception e) {
			System.out.println(url);
			return null;
		}
		Document doc = Jsoup.parse(html);
		String c = doc.select("table[class=nav_table stats_table]").html();
		String[] score = new String[20];
		int n = 0;
		Pattern p = Pattern
				.compile("<td class=\"align_right\">(?<abc>.*)</td>");
		Matcher m = p.matcher(c);
		while (m.find()) {
			try {
				score[n] = m.group("abc");
			} catch (Exception e) {
				System.out.println(url);
			}
			n++;
		}
		int t = (n - 8) / 2;
		int t1score1 = stringToInt(score[0]);
		int t1score2 = stringToInt(score[1]);
		int t1score3 = stringToInt(score[2]);
		int t1score4 = stringToInt(score[3]);
		int t2score1 = stringToInt(score[t + 4]);
		int t2score2 = stringToInt(score[t + 5]);
		int t2score3 = stringToInt(score[t + 6]);
		int t2score4 = stringToInt(score[t + 7]);
		Pattern p1 = Pattern
				.compile("<td><a href=\"/teams/[A-Z]{3}/\\d{4}.html\">(?<abc>[A-Z]{3})</a></td>");
		Matcher m1 = p1.matcher(c);
		String ab[] = new String[2];
		int n1 = 0;
		while (m1.find()) {
			ab[n1] = m1.group("abc");
			n1++;
		}
		String a = ab[0];
		String b = ab[1];
		Pattern p2 = Pattern
				.compile("<td class=\"align_right bold_text\">(?<abc>\\d*)</td> ");
		Matcher m2 = p2.matcher(c);
		String finalS[] = new String[2];
		int n2 = 0;
		while (m2.find()) {
			finalS[n2] = m2.group("abc");
			n2++;
		}
		int t1final = stringToInt(finalS[0]);
		int t2final = stringToInt(finalS[1]);
		ArrayList<ScorePO> scores = new ArrayList<ScorePO>(10);
		scores.add(new ScorePO(t1score1, t2score1));
		scores.add(new ScorePO(t1score2, t2score2));
		scores.add(new ScorePO(t1score3, t2score3));
		scores.add(new ScorePO(t1score4, t2score4));
		ScorePO finalScore = new ScorePO(t1final, t2final);
		Elements doca = doc.select("div [id=div_" + a + "_basic]").select(
				"tbody");
		Elements docb = doc.select("div [id=div_" + b + "_basic]").select(
				"tbody");
		String firstTeam = doca.html();
		String secondTeam = docb.html();
		ArrayList<PlayerInMatchesPO> players1 = analyseTeam(firstTeam);
		ArrayList<PlayerInMatchesPO> players2 = analyseTeam(secondTeam);
		if (players1 == null || players2 == null) {
			System.out.println(url + "1");
			return null;
		}
		TeamInMatchesPO team1 = new TeamInMatchesPO(a, players1);
		TeamInMatchesPO team2 = new TeamInMatchesPO(b, players2);
		MatchPO matchPO = new MatchPO(season, calendar, finalScore, scores,
				team1, team2);
		matchPO.setFileName(season + "_" + dateString.substring(4, 6) + "-"
				+ dateString.substring(6, 8) + "_" + a + "-" + b);
		return matchPO;

	}

	private static ArrayList<PlayerInMatchesPO> analyseTeam(String teaminfo) {
		ArrayList<PlayerInMatchesPO> result = new ArrayList<PlayerInMatchesPO>(
				20);
		Pattern p1 = Pattern.compile("<tr class=\"\">.*?</tr>",
				Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m1 = p1.matcher(teaminfo);
		while (m1.find()) {
			PlayerInMatchesPO a = analysePlayerScore(m1.group());
			if (a != null)
				result.add(a);
		}
		if (result.size() == 0) {
			return null;
		}
		return result;

	}

	private static PlayerInMatchesPO analysePlayerScore(
			String PlayerInMatchesInfo) {
		Pattern p1 = Pattern
				.compile(" <td align=\"left\" .*><a href=\"/players/[a-z]/.*(?<number>\\d{2}).html\">(?<abc>.*)</a></td> ");
		Matcher m1 = p1.matcher(PlayerInMatchesInfo);
		String name = "";
		while (m1.find()) {
			name = m1.group("abc") + m1.group("number");
		}
		String position = "";
		int playTimeBySeconds = -1;// 脏数据取负值
		Pattern p2 = Pattern
				.compile("<td align=\"right\" csk=\"(?<abc>\\d*)\">.*</td>");
		Matcher m2 = p2.matcher(PlayerInMatchesInfo);
		while (m2.find()) {
			playTimeBySeconds = stringToInt(m2.group("abc"));
		}
		String list[] = new String[20];
		int n = 0;
		Pattern p3 = Pattern.compile("<td align=\"right\">(?<abc>.*)</td> ");
		Matcher m3 = p3.matcher(PlayerInMatchesInfo);
		while (m3.find()) {
			list[n] = m3.group("abc");
			n++;
		}
		if (n < 5) {
			return null;
		}
		int hit = stringToInt(list[0]);// 命中
		int shot = stringToInt(list[1]);// 出手
		int thirdHit = stringToInt(list[3]);
		int thirdshot = stringToInt(list[4]);
		int freeHit = stringToInt(list[6]);
		int freeshot = stringToInt(list[7]);
		int offensiveRebound = stringToInt(list[9]);
		int defensiveRebound = stringToInt(list[10]);
		int totalRebound = stringToInt(list[11]);
		int assist = stringToInt(list[12]);
		int steal = stringToInt(list[13]);
		int block = stringToInt(list[14]);
		int miss = stringToInt(list[15]);
		int foul = stringToInt(list[16]);
		int score = stringToInt(list[17]);
		PlayerInMatchesPO result = new PlayerInMatchesPO(name, position,
				playTimeBySeconds, hit, shot, thirdHit, thirdshot, freeHit,
				freeshot, offensiveRebound, defensiveRebound, totalRebound,
				assist, steal, block, miss, foul, score);
		// System.out.print(name+" ");
		// System.out.print(position+" ");
		// System.out.print(playTimeBySeconds+" ");
		// System.out.print(hit+" ");
		// System.out.print(shot+" ");
		// System.out.print(thirdHit+" ");
		// System.out.print(thirdshot+" ");
		// System.out.print(freeHit+" ");
		// System.out.print(freeshot+" ");
		// System.out.print(offensiveRebound+" ");
		// System.out.print(defensiveRebound+" ");
		// System.out.print(totalRebound+" ");
		// System.out.print(assist+" ");
		// System.out.print(steal+" ");
		// System.out.print(block+" ");
		// System.out.print(miss+" ");
		// System.out.print(foul+" ");
		// System.out.print(score+" ");
		// System.out.print("\n");
		return result;
	}
	public static final int stringToInt(String str) {
		int result;
		try {
			result = Integer.parseInt(str);
		} catch (Exception e) {
			result = -1;
		}
		return result;
	}
}

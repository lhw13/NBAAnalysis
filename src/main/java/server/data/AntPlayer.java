package server.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import server.po.PlayerPO;

public class AntPlayer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		playerList('a');
	}
	private static ArrayList<NewPlayerPO> playerList(char first){
		ArrayList<NewPlayerPO> playerList=new ArrayList<NewPlayerPO>(500);
		String html="";
		int n=0;
		String url="http://www.basketball-reference.com/players/"+first+"/";
		Pattern p=Pattern.compile("<tr  class=\"\">.*?</tr>",Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Pattern p1=Pattern.compile("/players/"+first+"/.*.html");
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
				html=html+str;
			}
			reader.close();
		} catch (Exception e) {
			return null;
		}
		Document doc = Jsoup.parse(html);
		Elements d = doc
				.select("tbody");
		
		return playerList;
	}
	private class NewPlayerPO {
		PlayerPO pp;
		String url;
	}
}

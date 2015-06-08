package presentation.matchui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LiveThread extends TimerTask {

	public static String content = "";

	@Override
	public void run() {
		content = "";
		readGoodsFile();
		MatchLivePanel.jtext.setText(content);
		
	}
	
	public static void readGoodsFile(){
		try { 
			String pathname = "D:\\nba.txt";
			File filename = new File(pathname); 
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename),"utf-8"); 
			BufferedReader br = new BufferedReader(reader); 
			String line = " ";  

			while (line != null) {  
				line = br.readLine(); 
				if(line == null){
					break;
				}
				Pattern pattern1 = Pattern.compile(">(.*)</td>");
				Matcher matcher1 = pattern1.matcher(line);  
				if (matcher1.find()) {
					line = matcher1.group(1).replaceAll("<b>", "");
					line = line.replaceAll("</b>", "");
					content += line + "\n";
				}
				if(line.substring(0, 1).equals(" ")){
					line = line.replaceAll("</td>","").trim();
					content += line + "\n";
				}
			}

		}
		catch (Exception e) {  
			e.printStackTrace();  
		} 
	}

}

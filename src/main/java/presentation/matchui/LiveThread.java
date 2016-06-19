package presentation.matchui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LiveThread extends TimerTask {

	public static HashMap<String,String> team_score;
	public static HashMap<String,String> each_part;
	public static HashMap<String,String[]> player_data_1;
	public static HashMap<String,String[]> player_data_2;
	
	public static ArrayList<String[]> content;
	
	@Override
	public void run() {
		
		Process proc;
		try {
			proc = Runtime.getRuntime().exec("python live.py");
			StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(),"Error");  
			StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(),"Output");  
			errorGobbler.start();  
			outputGobbler.start(); 
			proc.waitFor(); 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//get team_score===================================
		team_score = new HashMap<String,String>();
		readTeamScore();
		
		//get each_part====================================
		each_part = new HashMap<String,String>();
		readEachPart();
		
		if(MatchLivePanel.live_or_data==1){
			//get Live=====================================
			readLive();
			MatchLivePanel.updateLabel();
			MatchLivePanel.updateLiveInfo();
			MatchLivePanel.updateMatchInfo(content);
			
		}else if(MatchLivePanel.live_or_data==-1){
			//get player_data==============================
			player_data_1 = new HashMap<String,String[]>();
			player_data_2 = new HashMap<String,String[]>();
			readPlayerData1();
			readPlayerData2();
			MatchLivePanel.updateLabel();
			MatchLivePanel.updateLiveInfo();
			MatchLivePanel.updateTheData();
		}else{
			//get Live=====================================
			content = new ArrayList<String[]>();
			readLive();
			MatchLivePanel.updateMatchInfo(content);
			//get player_data==============================
			player_data_1 = new HashMap<String,String[]>();
			player_data_2 = new HashMap<String,String[]>();
			readPlayerData1();
			readPlayerData2();
		}

	}

	public static void readTeamScore(){
		try { 
			String pathname = "team_score.txt";
			File filename = new File(pathname); 
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename),"utf-8"); 
			BufferedReader br = new BufferedReader(reader); 
			String line = " ";  

			int index = 0;
			while (line != null) {
				line = br.readLine(); 
				if(line == null){
					break;
				}
				if(line.contains("</h2>") && index < 13){
					String team_1_score = line.substring(line.indexOf("<h2>")+4, line.indexOf("</h2>"));
					team_score.put("team_1_score", team_1_score);
				}
				if(line.contains("</h2>") && index > 13){
					String team_2_score = line.substring(line.indexOf("<h2>")+4, line.indexOf("</h2>"));
					team_score.put("team_2_score", team_2_score);
				}
				if(line.contains("</a>") && line.contains("href") && index < 13){
					String team_1_name = line.substring(line.indexOf("blank'>")+7, line.indexOf("</a>"));
					team_score.put("team_1_name", team_1_name);
				}
				if(line.contains("</a>") && line.contains("href") && index > 13){
					String team_2_name = line.substring(line.indexOf("blank'>")+7, line.indexOf("</a>"));
					team_score.put("team_2_name", team_2_name);
				}
				if(line.contains("</p>") && line.contains("(") && line.contains(")") && index < 13){
					String team_1_zong = line.substring(0, line.indexOf("</p>"));
					team_1_zong = team_1_zong.trim();
					team_score.put("team_1_zong", team_1_zong);
				}
				if(line.contains("</p>") && line.contains("(") && line.contains(")") && index > 13){
					String team_2_zong = line.substring(0, line.indexOf("</p>"));
					team_2_zong = team_2_zong.trim();
					team_score.put("team_2_zong", team_2_zong);
				}
				if(line.contains("主队")){
					String team_1_state = line.substring(line.indexOf("<div>")+5, line.indexOf("</div>"));
					team_score.put("team_1_state", team_1_state);
				}
				if(line.contains("客队")){
					String team_2_state = line.substring(line.indexOf("<div>")+5, line.indexOf("</div>"));
					team_score.put("team_2_state", team_2_state);
				}
				
				index++;
			}

		}
		catch (Exception e) {  
			e.printStackTrace();  
		} 
	}
	
	public static void readEachPart(){
		try { 
			String pathname = "each_part.txt";
			File filename = new File(pathname); 
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename),"utf-8"); 
			BufferedReader br = new BufferedReader(reader); 
			String line = " ";  

			ArrayList<String> content = new ArrayList<String>();
			while (line != null) { 
				line = br.readLine(); 
				if(line == null){
					break;
				}
				content.add(line);
			}
			
			if(content.size()==26){
				line = content.get(10);
				String team_1_name = content.get(10).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_name", team_1_name);
				line = content.get(18);
				String team_2_name = content.get(18).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_name", team_2_name);
				
				line = content.get(11);
				String team_1_one = content.get(11).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_one", team_1_one);
				line = content.get(12);
				String team_1_two = content.get(12).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_two", team_1_two);
				line = content.get(13);
				String team_1_three = content.get(13).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_three", team_1_three);
				line = content.get(14);
				String team_1_four = content.get(14).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_four", team_1_four);
				line = content.get(15);
				String team_1_all = content.get(15).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_all", team_1_all);
				
				line = content.get(19);
				String team_2_one = content.get(19).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_one", team_2_one);
				line = content.get(20);
				String team_2_two = content.get(20).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_two", team_2_two);
				line = content.get(21);
				String team_2_three = content.get(21).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_three", team_2_three);
				line = content.get(22);
				String team_2_four = content.get(22).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_four", team_2_four);
				line = content.get(23);
				String team_2_all = content.get(23).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_all", team_2_all);
				
			}
			if(content.size()==29){
				line = content.get(11);
				String team_1_name = content.get(11).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_name", team_1_name);
				line = content.get(20);
				String team_2_name = content.get(20).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_name", team_2_name);
				
				line = content.get(12);
				String team_1_one = content.get(12).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_one", team_1_one);
				line = content.get(13);
				String team_1_two = content.get(13).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_two", team_1_two);
				line = content.get(14);
				String team_1_three = content.get(14).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_three", team_1_three);
				line = content.get(15);
				String team_1_four = content.get(15).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_four", team_1_four);
				line = content.get(16);
				String team_1_add1 = content.get(16).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_add1", team_1_add1);
				line = content.get(17);
				String team_1_all = content.get(17).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_all", team_1_all);
				
				line = content.get(21);
				String team_2_one = content.get(21).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_one", team_2_one);
				line = content.get(22);
				String team_2_two = content.get(22).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_two", team_2_two);
				line = content.get(23);
				String team_2_three = content.get(23).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_three", team_2_three);
				line = content.get(24);
				String team_2_four = content.get(24).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_four", team_2_four);
				line = content.get(25);
				String team_2_add1 = content.get(25).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_add1", team_2_add1);
				line = content.get(26);
				String team_2_all = content.get(26).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_all", team_2_all);
				
			}
			if(content.size()==32){
				line = content.get(12);
				String team_1_name = content.get(12).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_name", team_1_name);
				line = content.get(22);
				String team_2_name = content.get(22).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_name", team_2_name);
				
				line = content.get(13);
				String team_1_one = content.get(12).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_one", team_1_one);
				line = content.get(14);
				String team_1_two = content.get(13).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_two", team_1_two);
				line = content.get(15);
				String team_1_three = content.get(15).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_three", team_1_three);
				line = content.get(16);
				String team_1_four = content.get(16).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_four", team_1_four);
				line = content.get(17);
				String team_1_add1 = content.get(17).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_add1", team_1_add1);
				line = content.get(18);
				String team_1_add2 = content.get(18).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_add2", team_1_add2);
				line = content.get(19);
				String team_1_all = content.get(19).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_1_all", team_1_all);

				line = content.get(23);
				String team_2_one = content.get(23).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_one", team_2_one);
				line = content.get(24);
				String team_2_two = content.get(24).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_two", team_2_two);
				line = content.get(25);
				String team_2_three = content.get(25).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_three", team_2_three);
				line = content.get(26);
				String team_2_four = content.get(26).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_four", team_2_four);
				line = content.get(27);
				String team_2_add1 = content.get(27).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_add1", team_2_add1);
				line = content.get(28);
				String team_2_add2 = content.get(28).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_add2", team_2_add2);
				line = content.get(29);
				String team_2_all = content.get(29).substring(line.indexOf("<td>")+4, line.indexOf("</td>"));
				each_part.put("team_2_all", team_2_all);
			}

		}
		catch (Exception e) {  
			e.printStackTrace();  
		} 
	}

	public static void readPlayerData1(){
		try { 
			String pathname = "player_data_1.txt";
			File filename = new File(pathname); 
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename),"utf-8"); 
			BufferedReader br = new BufferedReader(reader); 
			String line = " ";  

			ArrayList<String> content = new ArrayList<String>();
			while (line != null) {  
				line = br.readLine(); 
				if(line == null){
					break;
				}
				content.add(line);
			}
			
			int n = 0;
			int k = 0;
			boolean jude = true;
			String[] player_info;
			while(jude){
				if(n >= content.size()){
					jude = false;
				}
				else{
					String s = content.get(n);
					if(s.contains("</a>")){
						player_info = new String[16];
						
						String s0 = content.get(n);
						if(s0.contains("span")){
							player_info[0] = s0.substring(s0.indexOf("high\">")+6, s0.indexOf("</span>")).trim();
						}else{
							player_info[0] = s0.substring(0, s0.indexOf("</a>")).trim();
						}
						
						String s1 = content.get(n+2);
						if(s1.contains("span")){
							player_info[1] = s1.substring(s1.indexOf("high\">")+6, s1.indexOf("</span>")).trim();
						}else{
							player_info[1] = s1.substring(s1.indexOf("<td>")+4, s1.indexOf("</td>")).trim();
						}
						
						String s2 = content.get(n+3);
						if(s2.contains("span")){
							player_info[2] = s2.substring(s2.indexOf("high\">")+6, s2.indexOf("</span>")).trim();
						}else{
							player_info[2] = s2.substring(s2.indexOf("<td>")+4, s2.indexOf("</td>")).trim();
						}
						
						String s3 = content.get(n+4);
						if(s3.contains("span")){
							player_info[3] = s3.substring(s3.indexOf("high\">")+6, s3.indexOf("</span>")).trim();
						}else{
							player_info[3] = s3.substring(s3.indexOf("<td>")+4, s3.indexOf("</td>")).trim();
						}
						
						String s4 = content.get(n+5);
						if(s4.contains("span")){
							player_info[4] = s4.substring(s4.indexOf("high\">")+6, s4.indexOf("</span>")).trim();
						}else{
							player_info[4] = s4.substring(s4.indexOf("<td>")+4, s4.indexOf("</td>")).trim();
						}
						
						String s5 = content.get(n+6);
						if(s5.contains("span")){
							player_info[5] = s5.substring(s5.indexOf("high\">")+6, s5.indexOf("</span>")).trim();
						}else{
							player_info[5] = s5.substring(s5.indexOf("<td>")+4, s5.indexOf("</td>")).trim();
						}
						
						String s6 = content.get(n+7);
						if(s6.contains("span")){
							player_info[6] = s6.substring(s6.indexOf("high\">")+6, s6.indexOf("</span>")).trim();
						}else{
							player_info[6] = s6.substring(s6.indexOf("<td>")+4, s6.indexOf("</td>")).trim();
						}
						
						String s7 = content.get(n+8);
						if(s7.contains("span")){
							player_info[7] = s7.substring(s7.indexOf("high\">")+6, s7.indexOf("</span>")).trim();
						}else{
							player_info[7] = s7.substring(s7.indexOf("<td>")+4, s7.indexOf("</td>")).trim();
						}
						
						String s8 = content.get(n+9);
						if(s8.contains("span")){
							player_info[8] = s8.substring(s8.indexOf("high\">")+6, s8.indexOf("</span>")).trim();
						}else{
							player_info[8] = s8.substring(s8.indexOf("<td>")+4, s8.indexOf("</td>")).trim();
						}
						
						String s9 = content.get(n+10);
						if(s9.contains("span")){
							player_info[9] = s9.substring(s9.indexOf("high\">")+6, s9.indexOf("</span>")).trim();
						}else{
							player_info[9] = s9.substring(s9.indexOf("<td>")+4, s9.indexOf("</td>")).trim();
						}
						
						String s10 = content.get(n+11);
						if(s10.contains("span")){
							player_info[10] = s10.substring(s10.indexOf("high\">")+6, s10.indexOf("</span>")).trim();
						}else{
							player_info[10] = s10.substring(s10.indexOf("<td>")+4, s10.indexOf("</td>")).trim();
						}
						
						String s11 = content.get(n+12);
						if(s11.contains("span")){
							player_info[11] = s11.substring(s11.indexOf("high\">")+6, s11.indexOf("</span>")).trim();
						}else{
							player_info[11] = s11.substring(s11.indexOf("<td>")+4, s11.indexOf("</td>")).trim();
						}
						
						String s12 = content.get(n+13);
						if(s12.contains("span")){
							player_info[12] = s12.substring(s12.indexOf("high\">")+6, s12.indexOf("</span>")).trim();
						}else{
							player_info[12] = s12.substring(s12.indexOf("<td>")+4, s12.indexOf("</td>")).trim();
						}
						
						String s13 = content.get(n+14);
						if(s13.contains("span")){
							player_info[13] = s13.substring(s13.indexOf("high\">")+6, s13.indexOf("</span>")).trim();
						}else{
							player_info[13] = s13.substring(s13.indexOf("<td>")+4, s13.indexOf("</td>")).trim();
						}
						
						String s14 = content.get(n+15);
						if(s14.contains("span")){
							player_info[14] = s14.substring(s14.indexOf("high\">")+6, s14.indexOf("</span>")).trim();
						}else{
							player_info[14] = s14.substring(s14.indexOf("<td>")+4, s14.indexOf("</td>")).trim();
						}
						
						String s15 = content.get(n+16);
						if(s15.contains("span")){
							player_info[15] = s15.substring(s15.indexOf("high\">")+6, s15.indexOf("</span>")).trim();
						}else{
							player_info[15] = s15.substring(s15.indexOf("<td>")+4, s15.indexOf("</td>")).trim();
						}
						
						player_data_1.put("player"+k, player_info);
						k++;
						n += 17;
					}
					else{
						n++;
					}
				}
				
			}

		}
		catch (Exception e) {  
			e.printStackTrace();  
		} 
	}
	
	public static void readPlayerData2(){
		try { 
			String pathname = "player_data_2.txt";
			File filename = new File(pathname); 
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename),"utf-8"); 
			BufferedReader br = new BufferedReader(reader); 
			String line = " ";  

			ArrayList<String> content = new ArrayList<String>();
			while (line != null) {  
				line = br.readLine(); 
				if(line == null){
					break;
				}
				content.add(line);
			}
			
			int n = 0;
			int k = 0;
			boolean jude = true;
			String[] player_info;
			while(jude){
				if(n >= content.size()){
					jude = false;
				}
				else{
					String s = content.get(n);
					if(s.contains("</a>")){
						player_info = new String[16];
						
						String s0 = content.get(n);
						if(s0.contains("span")){
							player_info[0] = s0.substring(s0.indexOf("high\">")+6, s0.indexOf("</span>")).trim();
						}else{
							player_info[0] = s0.substring(0, s0.indexOf("</a>")).trim();
						}
						
						String s1 = content.get(n+2);
						if(s1.contains("span")){
							player_info[1] = s1.substring(s1.indexOf("high\">")+6, s1.indexOf("</span>")).trim();
						}else{
							player_info[1] = s1.substring(s1.indexOf("<td>")+4, s1.indexOf("</td>")).trim();
						}
						
						String s2 = content.get(n+3);
						if(s2.contains("span")){
							player_info[2] = s2.substring(s2.indexOf("high\">")+6, s2.indexOf("</span>")).trim();
						}else{
							player_info[2] = s2.substring(s2.indexOf("<td>")+4, s2.indexOf("</td>")).trim();
						}
						
						String s3 = content.get(n+4);
						if(s3.contains("span")){
							player_info[3] = s3.substring(s3.indexOf("high\">")+6, s3.indexOf("</span>")).trim();
						}else{
							player_info[3] = s3.substring(s3.indexOf("<td>")+4, s3.indexOf("</td>")).trim();
						}
						
						String s4 = content.get(n+5);
						if(s4.contains("span")){
							player_info[4] = s4.substring(s4.indexOf("high\">")+6, s4.indexOf("</span>")).trim();
						}else{
							player_info[4] = s4.substring(s4.indexOf("<td>")+4, s4.indexOf("</td>")).trim();
						}
						
						String s5 = content.get(n+6);
						if(s5.contains("span")){
							player_info[5] = s5.substring(s5.indexOf("high\">")+6, s5.indexOf("</span>")).trim();
						}else{
							player_info[5] = s5.substring(s5.indexOf("<td>")+4, s5.indexOf("</td>")).trim();
						}
						
						String s6 = content.get(n+7);
						if(s6.contains("span")){
							player_info[6] = s6.substring(s6.indexOf("high\">")+6, s6.indexOf("</span>")).trim();
						}else{
							player_info[6] = s6.substring(s6.indexOf("<td>")+4, s6.indexOf("</td>")).trim();
						}
						
						String s7 = content.get(n+8);
						if(s7.contains("span")){
							player_info[7] = s7.substring(s7.indexOf("high\">")+6, s7.indexOf("</span>")).trim();
						}else{
							player_info[7] = s7.substring(s7.indexOf("<td>")+4, s7.indexOf("</td>")).trim();
						}
						
						String s8 = content.get(n+9);
						if(s8.contains("span")){
							player_info[8] = s8.substring(s8.indexOf("high\">")+6, s8.indexOf("</span>")).trim();
						}else{
							player_info[8] = s8.substring(s8.indexOf("<td>")+4, s8.indexOf("</td>")).trim();
						}
						
						String s9 = content.get(n+10);
						if(s9.contains("span")){
							player_info[9] = s9.substring(s9.indexOf("high\">")+6, s9.indexOf("</span>")).trim();
						}else{
							player_info[9] = s9.substring(s9.indexOf("<td>")+4, s9.indexOf("</td>")).trim();
						}
						
						String s10 = content.get(n+11);
						if(s10.contains("span")){
							player_info[10] = s10.substring(s10.indexOf("high\">")+6, s10.indexOf("</span>")).trim();
						}else{
							player_info[10] = s10.substring(s10.indexOf("<td>")+4, s10.indexOf("</td>")).trim();
						}
						
						String s11 = content.get(n+12);
						if(s11.contains("span")){
							player_info[11] = s11.substring(s11.indexOf("high\">")+6, s11.indexOf("</span>")).trim();
						}else{
							player_info[11] = s11.substring(s11.indexOf("<td>")+4, s11.indexOf("</td>")).trim();
						}
						
						String s12 = content.get(n+13);
						if(s12.contains("span")){
							player_info[12] = s12.substring(s12.indexOf("high\">")+6, s12.indexOf("</span>")).trim();
						}else{
							player_info[12] = s12.substring(s12.indexOf("<td>")+4, s12.indexOf("</td>")).trim();
						}
						
						String s13 = content.get(n+14);
						if(s13.contains("span")){
							player_info[13] = s13.substring(s13.indexOf("high\">")+6, s13.indexOf("</span>")).trim();
						}else{
							player_info[13] = s13.substring(s13.indexOf("<td>")+4, s13.indexOf("</td>")).trim();
						}
						
						String s14 = content.get(n+15);
						if(s14.contains("span")){
							player_info[14] = s14.substring(s14.indexOf("high\">")+6, s14.indexOf("</span>")).trim();
						}else{
							player_info[14] = s14.substring(s14.indexOf("<td>")+4, s14.indexOf("</td>")).trim();
						}
						
						String s15 = content.get(n+16);
						if(s15.contains("span")){
							player_info[15] = s15.substring(s15.indexOf("high\">")+6, s15.indexOf("</span>")).trim();
						}else{
							player_info[15] = s15.substring(s15.indexOf("<td>")+4, s15.indexOf("</td>")).trim();
						}
						
						player_data_2.put("player"+k, player_info);
						k++;
						n += 17;
					}
					else{
						n++;
					}
				}
				
			}

		}
		catch (Exception e) {  
			e.printStackTrace();  
		} 
	}

	public static void readLive(){
		try { 
			String pathname = "live.txt";
			File filename = new File(pathname); 
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename),"utf-8"); 
			BufferedReader br = new BufferedReader(reader); 
			String line = " ";  

			int index = 0;
			String[] strArray = new String[4]; 
			while (line != null) {  
				line = br.readLine(); 
				if(line == null){
					break;
				}
				if(line.contains("<td colspan=\"4\" style=\"text-align:center\">")){
					Pattern pattern1 = Pattern.compile(">(.*)</td>");
					Matcher matcher1 = pattern1.matcher(line);  
					if (matcher1.find()) {
						line = matcher1.group(1).replaceAll("<b>", "");
						line = line.replaceAll("</b>", "");
						String[] info = {"","",line,""};
						content.add(info);
						index = 0;
					}
				}
				Pattern pattern1 = Pattern.compile(">(.*)</td>");
				Matcher matcher1 = pattern1.matcher(line);  
				if (matcher1.find()) {
					line = matcher1.group(1).replaceAll("<b>", "");
					line = line.replaceAll("</b>", "");
					strArray[index] = line;
					index++;
					if(index==4){
						index = 0;
						content.add(strArray);
						strArray = null;
						strArray = new String[4];
					}
				}
				if(line.substring(0, 1).equals(" ")){
					line = line.replaceAll("</td>","").trim();
					strArray[index] = line;
					index++;
				}
			}

		}
		catch (Exception e) {  
			e.printStackTrace();  
		} 
	}
	
	public class StreamGobbler extends Thread {  
		  
	    InputStream is;  
	    String type;  
	  
	    public StreamGobbler(InputStream is, String type) {  
	        this.is = is;  
	        this.type = type;  
	    }  
	  
	    public void run() {  
	        try {  
	            InputStreamReader isr = new InputStreamReader(is);  
	            BufferedReader br = new BufferedReader(isr);  
	            String line = null;  
	            while ((line = br.readLine()) != null) {  
	                if (type.equals("Error")) {  
	                    System.out.println("Error   :" + line);  
	                } else {  
	                    System.out.println("Debug:" + line);  
	                }  
	            }  
	        } catch (IOException ioe) {  
	            ioe.printStackTrace();  
	        }  
	    }  
	}

}

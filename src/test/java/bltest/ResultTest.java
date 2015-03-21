package bltest;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import blservice.BLService;
import server.businesslogic.BLController;
import vo.PlayerVO;
import vo.TeamVO;

public class ResultTest {
	@Test
	public void testPlayerResult() {
		BLService bl = BLController.getInstance();
		ArrayList<PlayerVO> players = bl.getPlayerAnalysis();
		Collections.sort(players, new SortPlayersByName());
		File f = new File("test\\players.txt");
		if (!f.exists())// 若文件不存在，自动创建
			try {
				if (f.getParentFile() != null)
					f.getParentFile().mkdirs();
				f.createNewFile();
			} catch (IOException e) {
				System.out.println(e);
			}
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < players.size(); i++)
			pw.println(players.get(i).toString());
		pw.close();
	}

	@Test
	public void testTeamResult() {
		BLService bl = BLController.getInstance();
		ArrayList<TeamVO> teams = bl.getTeamAnalysis();
		Collections.sort(teams, new SortTeamsByName());
		File f = new File("test\\teams.txt");
		if (!f.exists())// 若文件不存在，自动创建
			try {
				if (f.getParentFile() != null)
					f.getParentFile().mkdirs();
				f.createNewFile();
			} catch (IOException e) {
				System.out.println(e);
			}
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < teams.size(); i++)
			pw.println(teams.get(i).toString());
		pw.close();
	}
}

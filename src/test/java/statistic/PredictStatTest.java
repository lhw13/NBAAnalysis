package statistic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.junit.Ignore;
import org.junit.Test;

import server.businesslogic.BLController;
import server.businesslogic.GenerateXML;
import server.businesslogic.SortMatchesByCalendar;
import server.data.DataController;
import server.po.MatchPO;
import dataservice.DataService;

public class PredictStatTest {
	GenerateXML g = new GenerateXML();
	double theta=0.048;
	//double k=0.024;
	double k=0.0;
	@Test
	public void testResult() {
		DataService data = new DataController();
		ArrayList<MatchPO> h = data.getAllMatch();
		//System.out.println(h.size());
		Collections.sort(h, new SortMatchesByCalendar());
		//System.out.println(h.size());
		try
		{
			File f = Opendoc("result.xml");
			WritableWorkbook book = Workbook.createWorkbook(f);
			WritableSheet sheet = book.createSheet("第一页", 0);
			Label label = null;
			label = new Label(0, 0, "球队1");
			sheet.addCell(label);
			label = new Label(1, 0, "球队2");
			sheet.addCell(label);
			label = new Label(2, 0, "本场比赛得分");
			sheet.addCell(label);
			label = new Label(3, 0, "最近20场本队平均进球");
			sheet.addCell(label);
			label = new Label(4, 0, "最近10场对手平均失球");
			sheet.addCell(label);
			label = new Label(5, 0, "主场");
			sheet.addCell(label);
			label = new Label(6, 0, "最近5场两队比赛进球");
			sheet.addCell(label);
			label = new Label(7, 0, "进步指数");
			sheet.addCell(label);
			label = new Label(8, 0, "最近5场对手平均进攻回合");
			sheet.addCell(label);
			int row=1;
			for (int i = 5800; i < h.size(); i++,row++)
			{
				MatchPO po = h.get(i);
				
				
				
				label = new Label(0, row, po.getTeam1().getAbbreviation());
				sheet.addCell(label);
				label = new Label(1, row, po.getTeam2().getAbbreviation());
				sheet.addCell(label);
				label = new Label(2, row, Integer.toString(po.getFinalScore().getTeam1()));
				sheet.addCell(label);
				label = new Label(3, row, Integer.toString(g.computeScore(h,po.getTeam1().getAbbreviation(),20,i)));
				sheet.addCell(label);
				label = new Label(4, row, Integer.toString(g.computeScore2(h,po.getTeam1().getAbbreviation(),10,i)));
				sheet.addCell(label);
				label = new Label(5, row, "1");
				sheet.addCell(label);
				label = new Label(6, row, Integer.toString(g.computeScore(h,po.getTeam1().getAbbreviation(),po.getTeam2().getAbbreviation(),3,i)));
				sheet.addCell(label);
				label = new Label(7, row, Integer.toString(g.computeScore(h,po.getTeam1().getAbbreviation(),5,i)));
				sheet.addCell(label);
				label = new Label(8, row, Double.toString(g.computeRound(h,po.getTeam2().getAbbreviation(),10,i)));
				sheet.addCell(label);
			}
			
			for (int i = 5800; i < h.size(); i++,row++)
			{
				MatchPO po = h.get(i);
				
				
				
				label = new Label(0, row, po.getTeam1().getAbbreviation());
				sheet.addCell(label);
				label = new Label(1, row, po.getTeam2().getAbbreviation());
				sheet.addCell(label);
				label = new Label(2, row, Integer.toString(po.getFinalScore().getTeam2()));
				sheet.addCell(label);
				label = new Label(3, row, Integer.toString(g.computeScore(h,po.getTeam2().getAbbreviation(),20,i)));
				sheet.addCell(label);
				label = new Label(4, row, Integer.toString(g.computeScore2(h,po.getTeam2().getAbbreviation(),10,i)));
				sheet.addCell(label);
				label = new Label(5, row, "-1");
				sheet.addCell(label);
				label = new Label(6, row, Integer.toString(g.computeScore(h,po.getTeam2().getAbbreviation(),po.getTeam1().getAbbreviation(),3,i)));
				sheet.addCell(label);
				label = new Label(7, row, Integer.toString(g.computeScore(h,po.getTeam2().getAbbreviation(),5,i)));
				sheet.addCell(label);
				label = new Label(8, row, Double.toString(g.computeRound(h,po.getTeam1().getAbbreviation(),10,i)));
				sheet.addCell(label);
			}
			book.write();
			book.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	@Test
	public void testOneTeamResult() {
		DataService data = new DataController();
		ArrayList<MatchPO> h = data.getAllMatch();
		//System.out.println(h.size());
		Collections.sort(h, new SortMatchesByCalendar());
		//System.out.println(h.size());
		try
		{
			File f = Opendoc("oneTeamResult.xml");
			WritableWorkbook book = Workbook.createWorkbook(f);
			WritableSheet sheet = book.createSheet("第一页", 0);
			Label label = null;
			label = new Label(0, 0, "球队1");
			sheet.addCell(label);
			label = new Label(1, 0, "球队2");
			sheet.addCell(label);
			label = new Label(2, 0, "本场比赛得分");
			sheet.addCell(label);
			label = new Label(3, 0, "最近20场本队平均进球");
			sheet.addCell(label);
			label = new Label(4, 0, "最近10场对手平均失球");
			sheet.addCell(label);
			label = new Label(5, 0, "主场");
			sheet.addCell(label);
			label = new Label(6, 0, "最近5场两队比赛进球");
			sheet.addCell(label);
			label = new Label(7, 0, "进步指数");
			sheet.addCell(label);
			label = new Label(8, 0, "最近5场对手平均进攻回合");
			sheet.addCell(label);
			int row=1;
			for (int i = 4800; i < h.size(); i++,row++)
			{
				MatchPO po = h.get(i);
				
				
				
				label = new Label(0, row, po.getTeam1().getAbbreviation());
				sheet.addCell(label);
				label = new Label(1, row, po.getTeam2().getAbbreviation());
				sheet.addCell(label);
				label = new Label(2, row, Integer.toString(po.getFinalScore().getTeam1()));
				sheet.addCell(label);
				label = new Label(3, row, Integer.toString(g.computeScore(h,po.getTeam1().getAbbreviation(),20,i)));
				sheet.addCell(label);
				label = new Label(4, row, Integer.toString(g.computeScore2(h,po.getTeam2().getAbbreviation(),10,i)));
				sheet.addCell(label);
				label = new Label(5, row, "1");
				sheet.addCell(label);
				label = new Label(6, row, Integer.toString(g.computeScore(h,po.getTeam1().getAbbreviation(),po.getTeam2().getAbbreviation(),4,i)));
				sheet.addCell(label);
				label = new Label(7, row, Integer.toString(g.computeScore(h,po.getTeam1().getAbbreviation(),5,i)));
				sheet.addCell(label);
				label = new Label(8, row, Double.toString(g.computeRound(h,po.getTeam2().getAbbreviation(),5,i)));
				sheet.addCell(label);
			}
			
			for (int i = 4800; i < h.size(); i++,row++)
			{
				MatchPO po = h.get(i);
				
				
				
				label = new Label(0, row, po.getTeam1().getAbbreviation());
				sheet.addCell(label);
				label = new Label(1, row, po.getTeam2().getAbbreviation());
				sheet.addCell(label);
				label = new Label(2, row, Integer.toString(po.getFinalScore().getTeam2()));
				sheet.addCell(label);
				label = new Label(3, row, Integer.toString(g.computeScore(h,po.getTeam2().getAbbreviation(),20,i)));
				sheet.addCell(label);
				label = new Label(4, row, Integer.toString(g.computeScore2(h,po.getTeam1().getAbbreviation(),10,i)));
				sheet.addCell(label);
				label = new Label(5, row, "-1");
				sheet.addCell(label);
				label = new Label(6, row, Integer.toString(g.computeScore(h,po.getTeam2().getAbbreviation(),po.getTeam1().getAbbreviation(),4,i)));
				sheet.addCell(label);
				label = new Label(7, row, Integer.toString(g.computeScore(h,po.getTeam2().getAbbreviation(),5,i)));
				sheet.addCell(label);
				label = new Label(8, row, Double.toString(g.computeRound(h,po.getTeam1().getAbbreviation(),5,i)));
				sheet.addCell(label);
			}
			book.write();
			book.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	@Test
	public void testWin() {
		DataService data = new DataController();
		ArrayList<MatchPO> h = data.getAllMatch();
		Collections.sort(h, new SortMatchesByCalendar());
		try
		{
			File f = Opendoc("win.xml");
			WritableWorkbook book = Workbook.createWorkbook(f);
			WritableSheet sheet = book.createSheet("第一页", 0);
			Label label = null;
			label = new Label(0, 0, "球队1");
			sheet.addCell(label);
			label = new Label(1, 0, "球队2");
			sheet.addCell(label);
			label = new Label(2, 0, "本场净胜");
			sheet.addCell(label);
			label = new Label(3, 0, "最近20场本队平均净胜");
			sheet.addCell(label);
			label = new Label(4, 0, "最近10场对手平均净输");
			sheet.addCell(label);
			label = new Label(5, 0, "主场");
			sheet.addCell(label);
			label = new Label(6, 0, "最近5场两队比赛净胜");
			sheet.addCell(label);
			label = new Label(7, 0, "最近5场净胜");
			sheet.addCell(label);
			label = new Label(8, 0, "最近5场本队平均进攻回合");
			sheet.addCell(label);
			label = new Label(9, 0, "最近5场对手平均进攻回合");
			sheet.addCell(label);
			int row=1;
			for (int i = 5800; i < h.size(); i++,row++)
			{
				MatchPO po = h.get(i);
				
				
				
				label = new Label(0, row, po.getTeam1().getAbbreviation());
				sheet.addCell(label);
				label = new Label(1, row, po.getTeam2().getAbbreviation());
				sheet.addCell(label);
				label = new Label(2, row, Double.toString((double)(po.getFinalScore().getTeam1()-po.getFinalScore().getTeam2())/po.getFinalScore().getTeam2()));
				sheet.addCell(label);
				label = new Label(3, row, Double.toString(g.computeWinScore(h,po.getTeam1().getAbbreviation(),20,i)));
				sheet.addCell(label);
				label = new Label(4, row, Double.toString(g.computeWinScore(h,po.getTeam1().getAbbreviation(),10,i)));
				sheet.addCell(label);
				label = new Label(5, row, "1");
				sheet.addCell(label);
				label = new Label(6, row, Double.toString(g.computeWinScore(h,po.getTeam1().getAbbreviation(),po.getTeam2().getAbbreviation(),3,i)));
				sheet.addCell(label);
				label = new Label(7, row, Double.toString(g.computeWinScore(h,po.getTeam1().getAbbreviation(),5,i)));
				sheet.addCell(label);
				label = new Label(8, row, Double.toString(g.computeRound(h,po.getTeam1().getAbbreviation(),10,i)));
				sheet.addCell(label);
				label = new Label(9, row, Double.toString(g.computeRound(h,po.getTeam2().getAbbreviation(),10,i)));
				sheet.addCell(label);
			}
			
			for (int i = 5800; i < h.size(); i++,row++)
			{
				MatchPO po = h.get(i);
				
				
				
				label = new Label(0, row, po.getTeam1().getAbbreviation());
				sheet.addCell(label);
				label = new Label(1, row, po.getTeam2().getAbbreviation());
				sheet.addCell(label);
				label = new Label(2, row, Double.toString((double)(po.getFinalScore().getTeam2()-po.getFinalScore().getTeam1())/po.getFinalScore().getTeam2()));
				sheet.addCell(label);
				label = new Label(3, row, Double.toString(g.computeWinScore(h,po.getTeam2().getAbbreviation(),20,i)));
				sheet.addCell(label);
				label = new Label(4, row, Double.toString(g.computeWinScore(h,po.getTeam2().getAbbreviation(),10,i)));
				sheet.addCell(label);
				label = new Label(5, row, "-1");
				sheet.addCell(label);
				label = new Label(6, row, Double.toString(g.computeWinScore(h,po.getTeam2().getAbbreviation(),po.getTeam1().getAbbreviation(),3,i)));
				sheet.addCell(label);
				label = new Label(7, row, Double.toString(g.computeWinScore(h,po.getTeam2().getAbbreviation(),5,i)));
				sheet.addCell(label);
				label = new Label(8, row, Double.toString(g.computeRound(h,po.getTeam2().getAbbreviation(),10,i)));
				sheet.addCell(label);
				label = new Label(9, row, Double.toString(g.computeRound(h,po.getTeam1().getAbbreviation(),10,i)));
				sheet.addCell(label);
			}
			book.write();
			book.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	@Test
	public void testWinByStrength() {
		BLController bl = BLController.getInstance();
		bl.analyse();
		ArrayList<MatchPO> h = bl.getAllMatch();
		try
		{
			File f = Opendoc("stanfordpredict.xml");
			WritableWorkbook book = Workbook.createWorkbook(f);
			WritableSheet sheet = book.createSheet("第一页", 0);
			Label label = null;
			label = new Label(0, 0, "文件名");
			sheet.addCell(label);
			label = new Label(1, 0, "本场净胜");
			sheet.addCell(label);
			label = new Label(2, 0, "实力差");
			sheet.addCell(label);
			label = new Label(3, 0, "主场");
			sheet.addCell(label);
			int row=1;
			int n=500;
			for (int i = 4800; i < h.size(); i++,row++,n++)
			{
				MatchPO po = h.get(i);
				label = new Label(0, row, po.getFileName());
				sheet.addCell(label);
				label = new Label(1, row, Double.toString((double)(po.getFinalScore().getTeam1()-po.getFinalScore().getTeam2())));
				sheet.addCell(label);
				label = new Label(2, row, Double.toString(bl.getStrengthDiff(po.getTeam1().getAbbreviation(), po.getTeam2().getAbbreviation(), n, i,theta,k)));
				sheet.addCell(label);
				label = new Label(3, row, "1");
				sheet.addCell(label);
				
				row++;
				label = new Label(0, row, po.getFileName());
				sheet.addCell(label);
				label = new Label(1, row, Double.toString((double)(po.getFinalScore().getTeam2()-po.getFinalScore().getTeam1())));
				sheet.addCell(label);
				label = new Label(2, row, Double.toString(bl.getStrengthDiff(po.getTeam2().getAbbreviation(), po.getTeam1().getAbbreviation(), n, i,theta,k)));
				sheet.addCell(label);
				label = new Label(3, row, "-1");
				sheet.addCell(label);
			}
			book.write();
			book.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	@Ignore
	public void testFindTheta() {
		BLController bl = BLController.getInstance();
		bl.analyse();
		ArrayList<MatchPO> h = bl.getAllMatch();
			int row=1;
			int n=100;
			theta=0.01;
			k=0.024;
			ArrayList<Integer> result = new ArrayList<Integer>();
			for(int j=0;j<2;j++,theta+=0.01)
			{
				h = bl.getAllMatch();
				int count=0;
			for (int i = 4800; i < h.size(); i++,row++,n++)
			{
				MatchPO po = h.get(i);
				if((po.getFinalScore().getTeam1()-po.getFinalScore().getTeam2())*
				bl.getStrengthDiff(po.getTeam1().getAbbreviation(), po.getTeam2().getAbbreviation(), n, i,theta,k)>0)
					count++;
			}
			result.add(count);
			System.out.println(count);
			}
			

	}
	@Test
	public void testHome() {
		DataService data = new DataController();
		ArrayList<MatchPO> h = data.getAllMatch();
		Collections.sort(h, new SortMatchesByCalendar());
		try
		{
			File f = Opendoc("home.xml");
			WritableWorkbook book = Workbook.createWorkbook(f);
			WritableSheet sheet = book.createSheet("第一页", 0);
			Label label = null;
			label = new Label(0, 0, "净胜分");
			sheet.addCell(label);
			label = new Label(1, 0, "主场");
			sheet.addCell(label);
			label = new Label(2, 0, "客场");
			sheet.addCell(label);
			//label = new Label(4, 0, "最近5场两队比赛进球");
			//sheet.addCell(label);
			int row=1;
			for (int i = 5800; i < h.size(); i++,row++)
			{
				MatchPO po = h.get(i);
				
				label = new Label(0, row, Integer.toString(po.getFinalScore().getTeam1()-po.getFinalScore().getTeam2()));
				sheet.addCell(label);
				label = new Label(1, row, "1");
				sheet.addCell(label);
				label = new Label(2, row, "0");
				sheet.addCell(label);
				//label = new Label(4, row, "0");
				//sheet.addCell(label);
			}
			
			for (int i = 5800; i < h.size(); i++,row++)
			{
				MatchPO po = h.get(i);
				label = new Label(0, row, Integer.toString(po.getFinalScore().getTeam2()-po.getFinalScore().getTeam1()));
				sheet.addCell(label);
				label = new Label(1, row, "0");
				sheet.addCell(label);
				label = new Label(2, row, "1");
				sheet.addCell(label);
				//label = new Label(4, row, "0");
				//sheet.addCell(label);
			}
			book.write();
			book.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	public static File Opendoc(String s)
	{//参数为需要打开的文件路径
	    File f = new File(s);
	    if(!f.exists())//若文件不存在，自动创建
	    try
	    {
	    	if(f.getParentFile() != null)
	    		f.getParentFile().mkdirs();
	        f.createNewFile();
	        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f));
	        oos.writeObject(null);
	        oos.close();
	    }
	    catch(IOException e)
	    {
	        System.out.println(e);
	    }
		return f;
	}
}

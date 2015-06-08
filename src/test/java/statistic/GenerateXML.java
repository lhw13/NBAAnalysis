package statistic;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import dataservice.DataService;
import server.businesslogic.BLController;
import server.businesslogic.SortMatchesByCalendar;
import server.data.DataController;
import server.po.MatchPO;
import blservice.BLService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class GenerateXML {
	@Test
	public void testResult() {
		DataService data = new DataController();
		ArrayList<MatchPO> h = data.getAllMatch();
		Collections.sort(h, new SortMatchesByCalendar());
		try
		{
			File f = Opendoc("result.xml");
			WritableWorkbook book = Workbook.createWorkbook(f);
			WritableSheet sheet = book.createSheet("第一页", 0);
			Label label = null;
			label = new Label(0, 0, "本场比赛得分");
			sheet.addCell(label);
			label = new Label(1, 0, "最近20场本队平均进球");
			sheet.addCell(label);
			label = new Label(2, 0, "最近10场对手平均失球");
			sheet.addCell(label);
			label = new Label(3, 0, "主场");
			sheet.addCell(label);
			label = new Label(4, 0, "最近5场两队比赛进球");
			sheet.addCell(label);
			label = new Label(5, 0, "进步指数");
			sheet.addCell(label);
			int row=1;
			for (int i = 5800; i < h.size(); i++,row++)
			{
				MatchPO po = h.get(i);
				
				label = new Label(0, row, Integer.toString(po.getFinalScore().getTeam1()));
				sheet.addCell(label);
				label = new Label(1, row, Integer.toString(computeScore(h,po.getTeam1().getAbbreviation(),20,i)));
				sheet.addCell(label);
				label = new Label(2, row, Integer.toString(computeScore2(h,po.getTeam1().getAbbreviation(),10,i)));
				sheet.addCell(label);
				label = new Label(3, row, "1");
				sheet.addCell(label);
				label = new Label(4, row, Integer.toString(computeScore(h,po.getTeam1().getAbbreviation(),po.getTeam2().getAbbreviation(),3,i)));
				sheet.addCell(label);
				label = new Label(5, row, Integer.toString(computeScore(h,po.getTeam1().getAbbreviation(),5,i)));
				sheet.addCell(label);
			}
			
			for (int i = 5800; i < h.size(); i++,row++)
			{
				MatchPO po = h.get(i);
				label = new Label(0, row, Integer.toString(po.getFinalScore().getTeam2()));
				sheet.addCell(label);
				label = new Label(1, row, Integer.toString(computeScore(h,po.getTeam2().getAbbreviation(),20,i)));
				sheet.addCell(label);
				label = new Label(2, row, Integer.toString(computeScore2(h,po.getTeam2().getAbbreviation(),10,i)));
				sheet.addCell(label);
				label = new Label(3, row, "-1");
				sheet.addCell(label);
				label = new Label(4, row, Integer.toString(computeScore(h,po.getTeam2().getAbbreviation(),po.getTeam1().getAbbreviation(),3,i)));
				sheet.addCell(label);
				label = new Label(5, row, Integer.toString(computeScore(h,po.getTeam2().getAbbreviation(),5,i)));
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
	
	public static int computeScore(ArrayList<MatchPO> h, String abr,int num, int end)
	{
		int result=0;
		int count=0;
		for(int i=end-1;i>=0;i--)
		{
			MatchPO po = h.get(i);
			if(po.containsTeam(abr))
			{
				count++;
				result+=po.getScore(abr);
			}
			if(count>=num)
				return result/num;
		}
		System.out.println("abnormal");
		return result/num;
	}
	
	public static int computeScore(ArrayList<MatchPO> h, String abr, String abr2,int num, int end)
	{
		int result=0;
		int count=0;
		for(int i=end-1;i>=0;i--)
		{
			MatchPO po = h.get(i);
			if(po.containsTeam(abr) && po.containsTeam(abr2))
			{
				count++;
				result+=po.getScore(abr);
			}
			if(count>=num)
				return result/num;
		}
		System.out.println("abnormal");
		return result/num;
	}
	
	public static int computeScore2(ArrayList<MatchPO> h, String abr,int num, int end)
	{
		int result=0;
		int count=0;
		for(int i=end-1;i>=0;i--)
		{
			MatchPO po = h.get(i);
			if(po.containsTeam(abr))
			{
				count++;
				result+=po.getScore2(abr);
			}
			if(count>=num)
				return result/num;
		}
		System.out.println("abnormal");
		return result/num;
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

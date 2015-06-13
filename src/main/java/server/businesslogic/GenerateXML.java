package server.businesslogic;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Ignore;
import org.junit.Test;

import dataservice.DataService;
import server.data.DataController;
import server.po.MatchPO;
import blservice.BLService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class GenerateXML {
	double theta=0.048;
	//double k=0.024;
	double k=0.0;

	
	public static double computeOffensiveRebound(ArrayList<MatchPO> h, String abr,int num, int end)
	{
		double result=0;
		int count=0;
		for(int i=end-1;i>=0;i--)
		{
			MatchPO po = h.get(i);
			if(po.containsTeam(abr)>0)
			{
				TeamInMatches timtemp1 = new TeamInMatches(po.getTeam1(),
						po.getFinalScore().getTeam1(), new ArrayList(), po.getFinalScore().getTeam1()
								- po.getFinalScore().getTeam2());
				TeamInMatches timtemp2 = new TeamInMatches(po.getTeam2(),
						po.getFinalScore().getTeam2(), new ArrayList(), po.getFinalScore().getTeam2()
								- po.getFinalScore().getTeam1());
				timtemp1.setTeamInMatches2(timtemp2);
				timtemp2.setTeamInMatches2(timtemp1);
				timtemp1.computeTotal();
				timtemp1.computeRound();
				timtemp2.computeTotal();
				timtemp2.computeRound();
				count++;
				if(abr.equals(timtemp1.getAbbreviation()))
					result += timtemp1.getOffensiveRebound();
				else
					result += timtemp2.getOffensiveRebound();
			}
			if(count>=num)
				return result/num;
		}
		System.out.println("abnormal");
		return result/num;
	}
	
	public static double computeRound(ArrayList<MatchPO> h, String abr,int num, int end)
	{
		double result=0;
		int count=0;
		for(int i=end-1;i>=0;i--)
		{
			MatchPO po = h.get(i);
			if(po.containsTeam(abr)>0)
			{
				TeamInMatches timtemp1 = new TeamInMatches(po.getTeam1(),
						po.getFinalScore().getTeam1(), new ArrayList(), po.getFinalScore().getTeam1()
								- po.getFinalScore().getTeam2());
				TeamInMatches timtemp2 = new TeamInMatches(po.getTeam2(),
						po.getFinalScore().getTeam2(), new ArrayList(), po.getFinalScore().getTeam2()
								- po.getFinalScore().getTeam1());
				timtemp1.setTeamInMatches2(timtemp2);
				timtemp2.setTeamInMatches2(timtemp1);
				timtemp1.computeTotal();
				timtemp1.computeRound();
				timtemp2.computeTotal();
				timtemp2.computeRound();
				count++;
				/*if(abr.equals(timtemp1.getAbbreviation()))
					result += timtemp1.getOffendRound();
				else
					result += timtemp2.getOffendRound();*/
					result += timtemp2.getOffendRound()+ timtemp1.getOffendRound();
			}
			if(count>=num)
				return result/num;
		}
		System.out.println("abnormal");
		return result/num;
	}
	
	public static double computeOffensiveRound(ArrayList<MatchPO> h, String abr,int num, int end)
	{
		double result=0;
		int count=0;
		for(int i=end-1;i>=0;i--)
		{
			MatchPO po = h.get(i);
			if(po.containsTeam(abr)>0)
			{
				TeamInMatches timtemp1 = new TeamInMatches(po.getTeam1(),
						po.getFinalScore().getTeam1(), new ArrayList(), po.getFinalScore().getTeam1()
								- po.getFinalScore().getTeam2());
				TeamInMatches timtemp2 = new TeamInMatches(po.getTeam2(),
						po.getFinalScore().getTeam2(), new ArrayList(), po.getFinalScore().getTeam2()
								- po.getFinalScore().getTeam1());
				timtemp1.setTeamInMatches2(timtemp2);
				timtemp2.setTeamInMatches2(timtemp1);
				timtemp1.computeTotal();
				timtemp1.computeRound();
				timtemp2.computeTotal();
				timtemp2.computeRound();
				count++;
				if(abr.equals(timtemp1.getAbbreviation()))
					result += timtemp1.getOffendRound();
				else
					result += timtemp2.getOffendRound();
			}
			if(count>=num)
				return result/num;
		}
		System.out.println("abnormal");
		return result/num;
	}
	
	public static double computeCountWin(ArrayList<MatchPO> h, String abr,String abr2, int num, int end)
	{
		double result=0;
		int count=0;
		for(int i=end-1;i>=0;i--)
		{
			MatchPO po = h.get(i);
			if(po.containsTeam(abr)>0 && po.containsTeam(abr2)>0)
			{
				count++;
				int win = po.getFinalScore().getTeam1()-po.getFinalScore().getTeam2();
				if(po.getTeam1().getAbbreviation().equals(abr))
					if(win>0)
						result++;
				if(po.getTeam1().getAbbreviation().equals(abr))
					if(win<0)
						result++;
			}
			if(count>=num)
				return result;
		}
		System.out.println(abr);
		System.out.println(abr2);
		System.out.println("abnormal");
		return result;
	}
	
	public static double computeTurnOver(ArrayList<MatchPO> h, String abr,int num, int end)
	{
		double result=0;
		int count=0;
		for(int i=end-1;i>=0;i--)
		{
			MatchPO po = h.get(i);
			if(po.containsTeam(abr)>0)
			{
				TeamInMatches timtemp1 = new TeamInMatches(po.getTeam1(),
						po.getFinalScore().getTeam1(), new ArrayList(), po.getFinalScore().getTeam1()
								- po.getFinalScore().getTeam2());
				TeamInMatches timtemp2 = new TeamInMatches(po.getTeam2(),
						po.getFinalScore().getTeam2(), new ArrayList(), po.getFinalScore().getTeam2()
								- po.getFinalScore().getTeam1());
				timtemp1.setTeamInMatches2(timtemp2);
				timtemp2.setTeamInMatches2(timtemp1);
				timtemp1.computeTotal();
				timtemp1.computeRound();
				timtemp2.computeTotal();
				timtemp2.computeRound();
				count++;
				if(abr.equals(timtemp1.getAbbreviation()))
					result += timtemp1.getMiss();
				else
					result += timtemp2.getMiss();
			}
			if(count>=num)
				return result/num;
		}
		System.out.println("abnormal");
		return result/num;
	}
	
	public static int computeScore(ArrayList<MatchPO> h, String abr,int num, int end)
	{
		int result=0;
		int count=0;
		for(int i=end-1;i>=0;i--)
		{
			MatchPO po = h.get(i);
			if(po.containsTeam(abr)>0)
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
	
	public static double computeWinScore(ArrayList<MatchPO> h, String abr,int num, int end)
	{
		double result=0;
		int count=0;
		for(int i=end-1;i>=0;i--)
		{
			MatchPO po = h.get(i);
			if(po.containsTeam(abr)>0)
			{
				count++;
				result+=(double)po.getWin(abr)/(double)po.getScore(abr);
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
			if(po.containsTeam(abr)>0 && po.containsTeam(abr2)>0)
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
	
	public static double computeWinScore(ArrayList<MatchPO> h, String abr, String abr2,int num, int end)
	{
		double result=0;
		int count=0;
		for(int i=end-1;i>=0;i--)
		{
			MatchPO po = h.get(i);
			if(po.containsTeam(abr)>0 && po.containsTeam(abr2)>0)
			{
				count++;
				result+=(double)po.getWin(abr)/(double)po.getScore(abr);
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
			if(po.containsTeam(abr)>0)
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
	
	
}

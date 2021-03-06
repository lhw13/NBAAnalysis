package consoletest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import console.Console;

public class HotTest {
	static ArrayList<String> playerHot = new ArrayList<String>();
	static ArrayList<String> teamHot = new ArrayList<String>();
	Console c = new Console();
	static {
		
		//playerNormal information
		playerHot.add("score");
		playerHot.add("rebound");
		playerHot.add("assist");
		
		//teamNormal information
		teamHot.add("score");
		teamHot.add("rebound");
		teamHot.add("assist");
		teamHot.add("blockShot");
		teamHot.add("steal");
		teamHot.add("foul");
		teamHot.add("fault");
		teamHot.add("shot");
		teamHot.add("three");
		teamHot.add("penalty");
		teamHot.add("defendRebound");
		teamHot.add("offendRebound");
		teamHot.add("rebound");
		
	}
	
	@Ignore
	public void testHotPlayer()
	{
		for(int i=0; i<playerHot.size();i++)
		{
			String sortCondition = playerHot.get(i);
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-player-hot"+sortCondition+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-player","-hot",sortCondition};
			c.execute(p, ss);
		}
	}
	
	@Ignore
	public void testHotTeam()
	{
		for(int i=0; i<teamHot.size();i++)
		{
			String sortCondition = teamHot.get(i);
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-team-hot"+sortCondition+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-team","-hot",sortCondition};
			c.execute(p, ss);
		}
	}
	@Ignore
	public void testHotPlayerWithNormalN()
	{
		n=35;
		testHotPlayerWithN();
	}
	
	
	@Ignore
	public void testHotTeamWithNormalN()
	{
		n=25;
		testHotTeamWithN();
	}
	
	@Ignore
	public void testHotPlayerWithAbnormalN()
	{
		n=900;
		testHotPlayerWithN();
	}
	
	
	@Ignore
	public void testHotTeamWithAbnormalN()
	{
		n=40;
		testHotTeamWithN();
	}
	
	int n=20;
	private void testHotPlayerWithN() {
		for(int i=0; i<playerHot.size();i++)
		{
			String sortCondition = playerHot.get(i);
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-player-hot"+sortCondition+"-n"+n+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-player","-hot",sortCondition,"-n",Integer.toString(n)};
			c.execute(p, ss);
		}
	}
	private void testHotTeamWithN() {
		for(int i=0; i<teamHot.size();i++)
		{
			String sortCondition = teamHot.get(i);
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-team-hot"+sortCondition+"-n"+n+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-team","-hot",sortCondition,"-n",Integer.toString(n)};
			c.execute(p, ss);
		}
	}
}

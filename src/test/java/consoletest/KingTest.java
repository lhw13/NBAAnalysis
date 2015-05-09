package consoletest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Test;

import console.Console;

public class KingTest {
	static ArrayList<String> playerKing = new ArrayList<String>();
	Console c = new Console();
	static {
		playerKing.add("score");
		playerKing.add("rebound");
		playerKing.add("assist");
	}
	@Test
	public void testDailyKing() {
		
		
		for(int i=0; i<playerKing.size();i++)
		{
			String sortCondition = playerKing.get(i);
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-player-king"+sortCondition+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-player","-king",sortCondition,"-daily"};
			c.execute(p, ss);
		}
	}
	
	@Test
	public void testDailyNumberKing() {
		
		
		for(int i=0; i<playerKing.size();i++)
		{
			String sortCondition = playerKing.get(i);
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-player-kingnumber"+sortCondition+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-player","-king",sortCondition,"-n","10","-daily"};
			c.execute(p, ss);
		}
	}
}

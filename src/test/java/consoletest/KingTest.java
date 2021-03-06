package consoletest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import console.Console;

public class KingTest {
	static ArrayList<String> playerKing = new ArrayList<String>();
	static ArrayList<String> sd = new ArrayList<String>();
	Console c = new Console();
	static {
		playerKing.add("score");
		playerKing.add("rebound");
		playerKing.add("assist");
		sd.add("-daily");
		sd.add("-season");
	}
	@Ignore
	public void testDailyKing() {
		
	for(int j=0;j<sd.size();j++)	
		for(int i=0; i<playerKing.size();i++)
		{
			String sortCondition = playerKing.get(i);
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-player-king"+sortCondition+sd.get(j)+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-player","-king",sortCondition,sd.get(j)};
			c.execute(p, ss);
		}
	}
	
	@Ignore
	public void testDailyNumberKing() {
		
	for(int j=0;j<sd.size();j++)	
		for(int i=0; i<playerKing.size();i++)
		{
			String sortCondition = playerKing.get(i);
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-player-kingnumber"+sortCondition+sd.get(j)+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-player","-king",sortCondition,"-n","10",sd.get(j)};
			c.execute(p, ss);
		}
	}
}

package consoletest;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.junit.Test;

import server.businesslogic.BLController;
import server.businesslogic.Team;

public class PerformanceTest {
	String[] args={"point","blockShot","winRate","penalty"};
	ArrayList<Team> teams = BLController.getInstance().getTeams();
	static final int num=10000;
	@Test
	public void testBeanComparator()
	{
			for(int j=0;j<num;j++)
				for(int i=0;i<args.length;i++)
					Collections.sort(teams,new BeanComparator<Team>(args[i]));			
	}
	
	@Test
	public void testNormalComparator()
	{
			for(int j=0;j<num;j++)
				for(int i=0;i<args.length;i++)
				{
					Comparator<Team> c = null;
					switch(args[i]) {
					case "point":c=new Comparator(){
					public int compare(Object o1, Object o2) {
						return ((Team)o1).getPoint()-((Team)o2).getPoint();
					}};break;
					case "winRate":c=new Comparator(){
						public int compare(Object o1, Object o2) {
							return (int)(((Team)o1).getWinRate()-((Team)o2).getWinRate());
						}};break;
					case "penalty":c=new Comparator(){
						public int compare(Object o1, Object o2) {
							return (int)(((Team)o1).getPenalty()-((Team)o2).getPenalty());
						}};break;
					case "blockShot":c=new Comparator(){
						public int compare(Object o1, Object o2) {
							return ((Team)o1).getBlockShot()-((Team)o2).getBlockShot();
						}};break;
					}
					Collections.sort(teams,c);
				}				
	}
}

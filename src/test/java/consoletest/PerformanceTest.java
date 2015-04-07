package consoletest;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.junit.Test;

import server.businesslogic.BLController;
import server.businesslogic.Player;
import server.businesslogic.Team;

public class PerformanceTest {
	String[] args={"point","blockShot","winRate","penalty"};
	ArrayList<Team> teams = BLController.getInstance().getTeams();
	ArrayList<Player> players = BLController.getInstance().getPlayers();
	
	List<Comparator<Player>> sortConsList = new ArrayList<Comparator<Player>>();
	static final int num=10000;
	
	public void testBeanComparator()
	{
			for(int j=0;j<num;j++)
				for(int i=0;i<args.length;i++)
					Collections.sort(teams,new BeanComparator<Team>(args[i]));			
	}
	@Test
	public void testNewComparator()
	{
		for(int j=0;j<num;j++){
			for(String temp : args) {//遍历所有排序命令
				
				
				
					switch(temp) {
					case "point": sortConsList.add(comparePoint); break;
					
					case "rebound": sortConsList.add(compareRebound);break;
					case "assist": sortConsList.add(compareAssist);break;					
					case "blockShot": sortConsList.add(compareBlockShot);break;
					case "steal": sortConsList.add(compareSteal);break;
					case "foul": sortConsList.add(compareFoul);break;
					case "fault": sortConsList.add(compareFault);break;
					case "minute": sortConsList.add(compareMinute);break;
					case "efficient": sortConsList.add(compareEfficient);break;
					case "shot": sortConsList.add(compareShot);break;
					case "three": sortConsList.add(compareThree);break;
					case "penalty": sortConsList.add(comparePenalty);break;
					case "doubleTwo": sortConsList.add(compareDoubleTwo);break;
					case "realShot": sortConsList.add(compareRealShot);break;
					case "GmSc": sortConsList.add(compareGmSc);break;
					case "shotEfficient": sortConsList.add(compareShotEfficient);break;
					case "reboundEfficient": sortConsList.add(compareReboundEfficient);break;
					case "offendReboundEfficient": sortConsList.add(compareOffendReboundEfficient);break;
					case "defendReboundEfficient": sortConsList.add(compareDefendReboundEfficient);break;
					case "assistEfficient": sortConsList.add(compareAssistEfficient);break;
					case "stealEfficient": sortConsList.add(compareStealEfficient);break;		
					case "blockShotEfficient": sortConsList.add(compareBlockShotEfficient);break;
					case "faultEfficient": sortConsList.add(compareFaultEfficient);break;
					case "frequency": sortConsList.add(compareFrequency);break;
					}
			
					
			} 
			sort(players, sortConsList);
		}
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
	public Comparator<Player> comparePoint = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getPoint() > o1.getPoint() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareRebound = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getRebound() > o1.getRebound() ? 1 : -1;
        }
    };
    private Comparator<Player> compareAssist = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getAssist() > o1.getAssist() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareBlockShot = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getBlockShot() > o1.getBlockShot() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareSteal = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getSteal() > o1.getSteal() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareFoul = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getFoul() > o1.getFoul() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareFault = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getFault() > o1.getFault() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareMinute = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getMinute() > o1.getMinute() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareEfficient = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getEfficient() > o1.getEfficient() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareShot = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getShot() > o1.getShot() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareThree = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getThree() > o1.getThree() ? 1 : -1;
        }
    };
    
    private Comparator<Player> comparePenalty = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getPenalty() > o1.getPenalty() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareDoubleTwo = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getDoubleTwo() > o1.getDoubleTwo() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareRealShot = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getRealShot() > o1.getRealShot() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareGmSc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getGmSc() > o1.getGmSc() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareShotEfficient = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getShotEfficient() > o1.getShotEfficient() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareReboundEfficient = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getReboundEfficient() > o1.getReboundEfficient() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareOffendReboundEfficient = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getOffendReboundEfficient() > o1.getOffendReboundEfficient() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareDefendReboundEfficient = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getDefendReboundEfficient() > o1.getDefendReboundEfficient() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareAssistEfficient = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getAssistEfficient() > o1.getAssistEfficient() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareStealEfficient = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getStealEfficient() > o1.getStealEfficient() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareBlockShotEfficient = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getBlockShotEfficient() > o1.getBlockShotEfficient() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareFaultEfficient = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getFaultEfficient() > o1.getFaultEfficient() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareFrequency = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getFrequency() > o1.getFrequency() ? 1 : -1;
        }
    };


	//用于多重排序
	public void sort(List<Player> list, final List<Comparator<Player>> comList) {  
        if (comList == null)  
            return;  
        Comparator<Player> cmp = new Comparator<Player>() {  
            @Override  
            public int compare(Player o1, Player o2) {  
                for (Comparator<Player> comparator : comList) {  
                    if (comparator.compare(o1, o2) > 0) {  
                        return 1;  
                    } else if (comparator.compare(o1, o2) < 0) {  
                        return -1;  
                    }  
                }  
                return 0;  
            }  
        };  
        Collections.sort(list, cmp);  
    }  
}

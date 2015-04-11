package consoletest;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.junit.Ignore;
import org.junit.Test;

import server.businesslogic.BLController;
import server.businesslogic.Player;
import server.businesslogic.Team;

public class PerformanceTest {
	String[] args={"blockShot","point",};
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
	@Ignore
	public void testNewComparator()
	{
		for(int j=0;j<num;j++){
			for(String temp : args) {//遍历所有排序命令												
					switch(temp) {
					case "point": sortConsList.add(comparePointDesc); break;
					case "rebound": sortConsList.add(compareReboundDesc);break;
					case "assist": sortConsList.add(compareAssistDesc);break;					
					case "blockShot": sortConsList.add(compareBlockShotDesc);break;
					case "steal": sortConsList.add(compareStealDesc);break;
					case "foul": sortConsList.add(compareFoulDesc);break;
					case "fault": sortConsList.add(compareFaultDesc);break;
					case "minute": sortConsList.add(compareMinuteDesc);break;
					case "efficient": sortConsList.add(compareEfficientDesc);break;
					case "shot": sortConsList.add(compareShotDesc);break;
					case "three": sortConsList.add(compareThreeDesc);break;
					case "penalty": sortConsList.add(comparePenaltyDesc);break;
					case "doubleTwo": sortConsList.add(compareDoubleTwoDesc);break;
					case "realShot": sortConsList.add(compareRealShotDesc);break;
					case "GmSc": sortConsList.add(compareGmScDesc);break;
					case "shotEfficient": sortConsList.add(compareShotEfficientDesc);break;
					case "reboundEfficient": sortConsList.add(compareReboundEfficientDesc);break;
					case "offendReboundEfficient": sortConsList.add(compareOffendReboundEfficientDesc);break;
					case "defendReboundEfficient": sortConsList.add(compareDefendReboundEfficientDesc);break;
					case "assistEfficient": sortConsList.add(compareAssistEfficientDesc);break;
					case "stealEfficient": sortConsList.add(compareStealEfficientDesc);break;		
					case "blockShotEfficient": sortConsList.add(compareBlockShotEfficientDesc);break;
					case "faultEfficient": sortConsList.add(compareFaultEfficientDesc);break;
					case "frequency": sortConsList.add(compareFrequencyDesc);break;
					}
			} 
			sortConsList.add(comparePlayerNameAsc);
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
	
	
	//降序Comparator
		public Comparator<Player> comparePlayerNameDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getName().compareTo(o1.getName());
	        }
	    };
		
		public Comparator<Player> comparePointDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getPoint() > o1.getPoint() ? 1 : -1;
	        }
	    };
	    
	    public Comparator<Player> compareReboundDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getRebound() > o1.getRebound() ? 1 : -1;
	        }
	    };
	    private Comparator<Player> compareAssistDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getAssist() > o1.getAssist() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareBlockShotDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getBlockShot() > o1.getBlockShot() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareStealDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getSteal() > o1.getSteal() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareFoulDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getFoul() > o1.getFoul() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareFaultDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getFault() > o1.getFault() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareMinuteDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getMinute() > o1.getMinute() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareEfficientDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getEfficient() > o1.getEfficient() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareShotDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getShot() > o1.getShot() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareThreeDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getThree() > o1.getThree() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> comparePenaltyDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getPenalty() > o1.getPenalty() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareDoubleTwoDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getDoubleTwo() > o1.getDoubleTwo() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareRealShotDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getRealShot() > o1.getRealShot() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareGmScDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getGmSc() > o1.getGmSc() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareShotEfficientDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getShotEfficient() > o1.getShotEfficient() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareReboundEfficientDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getReboundEfficient() > o1.getReboundEfficient() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareOffendReboundEfficientDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getOffendReboundEfficient() > o1.getOffendReboundEfficient() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareDefendReboundEfficientDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getDefendReboundEfficient() > o1.getDefendReboundEfficient() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareAssistEfficientDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getAssistEfficient() > o1.getAssistEfficient() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareStealEfficientDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getStealEfficient() > o1.getStealEfficient() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareBlockShotEfficientDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getBlockShotEfficient() > o1.getBlockShotEfficient() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareFaultEfficientDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getFaultEfficient() > o1.getFaultEfficient() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareFrequencyDesc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getFrequency() > o1.getFrequency() ? 1 : -1;
	        }
	    };
	    
	    //升序Comparator
	    public Comparator<Player> comparePlayerNameAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o1.getName().compareTo(o2.getName());
	        }
	    };
	    
	    public Comparator<Player> comparePointAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getPoint() < o1.getPoint() ? 1 : -1;
	        }
	    };
	    
	    public Comparator<Player> compareReboundAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getRebound() < o1.getRebound() ? 1 : -1;
	        }
	    };
	    private Comparator<Player> compareAssistAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getAssist() < o1.getAssist() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareBlockShotAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getBlockShot() < o1.getBlockShot() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareStealAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getSteal() < o1.getSteal() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareFoulAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getFoul() < o1.getFoul() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareFaultAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getFault() < o1.getFault() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareMinuteAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getMinute() < o1.getMinute() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareEfficientAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getEfficient() < o1.getEfficient() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareShotAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getShot() < o1.getShot() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareThreeAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getThree() < o1.getThree() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> comparePenaltyAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getPenalty() < o1.getPenalty() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareDoubleTwoAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getDoubleTwo() < o1.getDoubleTwo() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareRealShotAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getRealShot() < o1.getRealShot() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareGmScAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getGmSc() < o1.getGmSc() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareShotEfficientAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getShotEfficient() < o1.getShotEfficient() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareReboundEfficientAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getReboundEfficient() < o1.getReboundEfficient() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareOffendReboundEfficientAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getOffendReboundEfficient() < o1.getOffendReboundEfficient() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareDefendReboundEfficientAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getDefendReboundEfficient() < o1.getDefendReboundEfficient() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareAssistEfficientAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getAssistEfficient() < o1.getAssistEfficient() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareStealEfficientAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getStealEfficient() < o1.getStealEfficient() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareBlockShotEfficientAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getBlockShotEfficient() < o1.getBlockShotEfficient() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareFaultEfficientAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getFaultEfficient() < o1.getFaultEfficient() ? 1 : -1;
	        }
	    };
	    
	    private Comparator<Player> compareFrequencyAsc = new Comparator<Player>() {  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return o2.getFrequency() < o1.getFrequency() ? 1 : -1;
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

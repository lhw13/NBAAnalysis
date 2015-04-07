package consoletest;

import java.util.Comparator;

import org.junit.Test;

import server.businesslogic.Player;

public class SortTest {
	
	@Test
	public void testMyComparator(){
		
	}
	
	@Test
	public void testNormalComparator(){
		
		
	}
	
	private Comparator<Player> comparePoint = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getPoint() > o1.getPoint() ? 1 : -1;
        }
    };
    
    private Comparator<Player> compareRebound = new Comparator<Player>() {  
		  
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
    
    private Comparator<Player> compareGmsc = new Comparator<Player>() {  
		  
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

}

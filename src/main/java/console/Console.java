package console;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import server.businesslogic.BLController;
import server.businesslogic.Player;
import server.businesslogic.Team;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

public class Console {
	public static String path="./nba";
	BLController bl = BLController.getInstance();
	public void execute(PrintStream out, String[] args){
		//for detailed information, see http://dongwei.iteye.com/blog/230458
		if(args[0].equals("-team"))
			team(out,args);
		if(args[0].equals("-player"))
			player(out,args);
		if(args[0].equals("--datasource"))
			path=args[1];
	}
	
	
	public void player(PrintStream out, String[] args) {
		ArrayList<Player> players = bl.getPlayers();
		List<Player> playerList = players;
		List<Comparator<Player>> sortConsList = new ArrayList<Comparator<Player>>();
		int n=50;//default
		
		boolean total = false;
		boolean high = false;
		boolean sort = false;//是否有sort命令
		boolean filter = false;//是否有filter命令
		boolean hot = false;//是否有热点命令
		boolean king = false;//是否有数据王命令
		
		
		String[] sortCons = null;
		String[] filterCons = null;
		String condition = null;
		String timeCon = null;//判断是赛季还是当日
		
		
		for(int i=1;i<args.length;i++)
			switch(args[i])
			{
				case "-total":total = true;break;
				case "-n":n=Integer.parseInt(args[++i]);break;
				case "-high":high=true; break;
				
				case "-hot": hot = true; 
							 condition = args[++i];
							 break;
				case "-king":king = true; 
							 condition = args[++i];
							 timeCon = args[++i];
							 n=5; 
							 break;
				
				case "-sort":	sort = true;
								sortCons=args[++i].split(",");
								break;
				case "-filter":	filter = true;
								filterCons=args[++i].split(",");
								break;
				
			}
		
		if(hot) {
			sortCons[0] = condition+".desc";
			sort = true;
		}
		
		if(king) {//数据王的season/daily接口还没弄好
			sortCons[0] = condition+".desc";
			if(timeCon.equals("season"))sort = true;
			else playerList = bl.getDailyHotPlayer(condition);
		}
		
		if(filter){
			for(String temp : filterCons) {//遍历所有排序命令
			    final String[] temps = temp.split("\\.");
			    if(!temps[1].equals("All")){
			    	playerList = (List<Player>) CollectionUtils.select(playerList,
			                new Predicate() {
			                    public boolean evaluate(Object arg0) {
			                    	Player p = (Player) arg0;
			                    	switch(temps[0]){
			                    	case "position": return temps[1].equals(p.getPosition());
			                    	case "league": return temps[1].equals(p.getLeague());
			                    	case "age": 
			                    		switch(temps[1]){
			                    		case "<=22": 
			                    			if(p.getAge()<=22){
			                    				return true;
			                    			}
			                    			break;
			                    		case "22<X<=25": 
			                    			if(22<p.getAge() && p.getAge()<=25){
			                    				return true;
			                    			}
			                    			break;
			                    		case "25<X<=30":
			                    			if(25<p.getAge() && p.getAge()<=30){
			                    				return true;
			                    			}
			                    			break;
			                    		case ">30": 
			                    			if(p.getAge()>30){
			                    				return true;
			                    			}
			                    			break;
			                    		}
			                    	default: return false;
			                    	}
			                        
			                    }
			                });
			    	playerList = (ArrayList<Player>) CollectionUtils.collect(
							playerList, new Transformer() {
			                    public Object transform(Object arg0) {
			                    	Player p = (Player) arg0;
			                        return p;
			                    }
			                });
			    }
				
			}
			
		}
		
		if(total) {//返回的数据是总数据
			if(sort) {//如果有sort命令			                
				for(String temp : sortCons) {//遍历所有排序命令
					String[] temps = temp.split("\\.");
					if(temps[1].equals("asc")) {//升序
						switch(temps[0]) {
						case "score": sortConsList.add(comparePointAsc); break;
						case "point": sortConsList.add(comparePointAsc); break;
						case "rebound": sortConsList.add(compareReboundAsc);break;
						case "assist": sortConsList.add(compareAssistAsc);break;					
						case "blockShot": sortConsList.add(compareBlockShotAsc);break;
						case "steal": sortConsList.add(compareStealAsc);break;
						case "foul": sortConsList.add(compareFoulAsc);break;
						case "fault": sortConsList.add(compareFaultAsc);break;
						case "minute": sortConsList.add(compareMinuteAsc);break;
						case "efficient": sortConsList.add(compareEfficientAsc);break;
						case "shot": sortConsList.add(compareShotAsc);break;
						case "three": sortConsList.add(compareThreeAsc);break;
						case "penalty": sortConsList.add(comparePenaltyAsc);break;
						case "doubleTwo": sortConsList.add(compareDoubleTwoAsc);break;
						case "realShot": sortConsList.add(compareRealShotAsc);break;
						case "GmSc": sortConsList.add(compareGmScAsc);break;
						case "shotEfficient": sortConsList.add(compareShotEfficientAsc);break;
						case "reboundEfficient": sortConsList.add(compareReboundEfficientAsc);break;
						case "offendReboundEfficient": sortConsList.add(compareOffendReboundEfficientAsc);break;
						case "defendReboundEfficient": sortConsList.add(compareDefendReboundEfficientAsc);break;
						case "assistEfficient": sortConsList.add(compareAssistEfficientAsc);break;
						case "stealEfficient": sortConsList.add(compareStealEfficientAsc);break;		
						case "blockShotEfficient": sortConsList.add(compareBlockShotEfficientAsc);break;
						case "faultEfficient": sortConsList.add(compareFaultEfficientAsc);break;
						case "frequency": sortConsList.add(compareFrequencyAsc);break;
						}				
					} else {//降序
						sortConsList.add(bl.getPlayerComparator(temps[0]));
						/*switch(temps[0]) {
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
						}*/
					}
				}
				
			} else {//没有sort命令，使用默认排序命令
				if(high){//如果是高阶数据，用高阶数据的默认，否则用基本数据
					sortConsList.add(bl.compareRealShotDesc);
				}else{ 
					sortConsList.add(bl.comparePointDesc);
				} 
			}
			if(timeCon.equals("season")) {
				sortConsList.add(comparePlayerNameAsc);
				sort(playerList, sortConsList);
			}
			
				for(int i=0;i<n && i<playerList.size();i++)//这是模仿刘瀚文，不知道干嘛
				{
					out.println(playerList.get(i).toNormalInfo());//to use which function
					out.println(playerList.get(i).toVO());
				}
		} else {//返回的数据是场均数据
			if(sort) {//如果有sort命令			                
				for(String temp : sortCons) {//遍历所有排序命令
					String[] temps = temp.split("\\.");
					if(temps[1].equals("asc")) {//升序
						switch(temps[0]) {
						case "score": sortConsList.add(comparePointAvgAsc); break;
						case "point": sortConsList.add(comparePointAvgAsc); break;
						case "rebound": sortConsList.add(compareReboundAvgAsc);break;
						case "assist": sortConsList.add(compareAssistAvgAsc);break;					
						case "blockShot": sortConsList.add(compareBlockShotAvgAsc);break;
						case "steal": sortConsList.add(compareStealAvgAsc);break;
						case "foul": sortConsList.add(compareFoulAvgAsc);break;
						case "fault": sortConsList.add(compareFaultAvgAsc);break;
						case "minute": sortConsList.add(compareMinuteAvgAsc);break;
						case "efficient": sortConsList.add(compareEfficientAsc);break;
						case "shot": sortConsList.add(compareShotAsc);break;
						case "three": sortConsList.add(compareThreeAsc);break;
						case "penalty": sortConsList.add(comparePenaltyAsc);break;
						case "doubleTwo": sortConsList.add(compareDoubleTwoAsc);break;
						case "realShot": sortConsList.add(compareRealShotAsc);break;
						case "GmSc": sortConsList.add(compareGmScAsc);break;
						case "shotEfficient": sortConsList.add(compareShotEfficientAsc);break;
						case "reboundEfficient": sortConsList.add(compareReboundEfficientAsc);break;
						case "offendReboundEfficient": sortConsList.add(compareOffendReboundEfficientAsc);break;
						case "defendReboundEfficient": sortConsList.add(compareDefendReboundEfficientAsc);break;
						case "assistEfficient": sortConsList.add(compareAssistEfficientAsc);break;
						case "stealEfficient": sortConsList.add(compareStealEfficientAsc);break;		
						case "blockShotEfficient": sortConsList.add(compareBlockShotEfficientAsc);break;
						case "faultEfficient": sortConsList.add(compareFaultEfficientAsc);break;
						case "frequency": sortConsList.add(compareFrequencyAsc);break;
						}				
					} else {//降序
						
						switch(temps[0]) {
						case "point": 
						case "rebound": 
						case "assist":					
						case "blockShot": 
						case "steal": 
						case "foul": 
						case "fault": 
						case "minute": sortConsList.add(bl.getPlayerAvgComparator(temps[0]));break;
						case "efficient": 
						case "shot": 
						case "three": 
						case "penalty": 
						case "doubleTwo": 
						case "realShot": 
						case "GmSc": 
						case "shotEfficient": 
						case "reboundEfficient": 
						case "offendReboundEfficient": ;
						case "defendReboundEfficient": 
						case "assistEfficient": 
						case "stealEfficient": 
						case "blockShotEfficient": 
						case "faultEfficient": 
						case "frequency": sortConsList.add(bl.getPlayerComparator(temps[0]));break;
						}
					}
				}
				
			} else {//没有sort命令，使用默认排序命令
				if(high){//如果是高阶数据，用高阶数据的默认，否则用基本数据
					sortConsList.add(bl.compareRealShotDesc);
				}else{ 
					sortConsList.add(bl.comparePointDesc);
				} 
			}
			if(timeCon.equals("season")) {
				sortConsList.add(comparePlayerNameAsc);
				sort(playerList, sortConsList);
			}
			
			for(int i=0;i<n && i<playerList.size();i++)
			{
				out.println(playerList.get(i).toNormalInfoAvg());//to use which function
				out.println(playerList.get(i).toVO());
			}
		}
	
	}
	public void team(PrintStream out, String[] args) {
		ArrayList<Team> teams = BLController.getInstance().getTeams();
		//注意是Team 不是 TeamVO
		boolean total = false;
		int n=30;
		String sortCondition = "score";
		boolean high = false;
		for(int i=1;i<args.length;i++)
			switch(args[i])
			{
				case "-total":total = true;break;
				case "-n":n=Integer.parseInt(args[++i]);break;
				case "-sort":sortCondition=args[++i].split(".")[1];break;
				case "-high":high=true;
			}
		if(total)
		{
			Comparator mycmp = ComparableComparator.getInstance();              
			mycmp = ComparatorUtils.reversedComparator(mycmp);
			BeanComparator<Team> bc = new BeanComparator<Team>(sortCondition,mycmp);
			Collections.sort(teams,bc);
			for(int i=0;i<n && i<teams.size();i++)
			{
				out.println(teams.get(i).toNormalInfo());//to use which function
				//out.println(teams.get(i).toVO());
			}
			
		}
	}
	
	//avg player asc comparator
		public Comparator<Player> comparePointAvgAsc = new Comparator<Player>(){  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return (double)o2.getPoint()/(double)o2.getAppearance() < (double)o1.getPoint()/(double)o1.getAppearance()? 1 : -1;
	        }
	    };
	    
	    public Comparator<Player> compareReboundAvgAsc = new Comparator<Player>(){  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return (double)o2.getRebound()/(double)o2.getAppearance() < (double)o1.getRebound()/(double)o1.getAppearance()? 1 : -1;
	        }
	    };
	    public Comparator<Player> compareAssistAvgAsc = new Comparator<Player>(){  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return (double)o2.getAssist()/(double)o2.getAppearance() < (double)o1.getAssist()/(double)o1.getAppearance()? 1 : -1;
	        }
	    };
	    
	    public Comparator<Player> compareBlockShotAvgAsc = new Comparator<Player>(){  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return (double)o2.getBlockShot()/(double)o2.getAppearance() < (double)o1.getBlockShot()/(double)o1.getAppearance()? 1 : -1;
	        }
	    };
	    
	    public Comparator<Player> compareStealAvgAsc = new Comparator<Player>(){  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return (double)o2.getSteal()/(double)o2.getAppearance() < (double)o1.getSteal()/(double)o1.getAppearance()? 1 : -1;
	        }
	    };
	    
	    public Comparator<Player> compareFoulAvgAsc = new Comparator<Player>(){  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return (double)o2.getFoul()/(double)o2.getAppearance() < (double)o1.getFoul()/(double)o1.getAppearance()? 1 : -1;
	        }
	    };
	    
	    public Comparator<Player> compareFaultAvgAsc = new Comparator<Player>(){  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return (double)o2.getFault()/(double)o2.getAppearance() < (double)o1.getFault()/(double)o1.getAppearance()? 1 : -1;
	        }
	    };
	    
	    public Comparator<Player> compareMinuteAvgAsc = new Comparator<Player>(){  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	            return (double)o2.getMinute()/(double)o2.getAppearance() < (double)o1.getMinute()/(double)o1.getAppearance()? 1 : -1;
	        }
	    };
/*	//降序Comparator
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
    public Comparator<Player> compareAssistDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getAssist() > o1.getAssist() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareBlockShotDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getBlockShot() > o1.getBlockShot() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareStealDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getSteal() > o1.getSteal() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareFoulDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getFoul() > o1.getFoul() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareFaultDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getFault() > o1.getFault() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareMinuteDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getMinute() > o1.getMinute() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareEfficientDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getEfficient() > o1.getEfficient() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareShotDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getShot() > o1.getShot() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareThreeDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getThree() > o1.getThree() ? 1 : -1;
        }
    };
    
    public Comparator<Player> comparePenaltyDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getPenalty() > o1.getPenalty() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareDoubleTwoDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getDoubleTwo() > o1.getDoubleTwo() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareRealShotDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getRealShot() > o1.getRealShot() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareGmScDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getGmSc() > o1.getGmSc() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareShotEfficientDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getShotEfficient() > o1.getShotEfficient() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareReboundEfficientDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getReboundEfficient() > o1.getReboundEfficient() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareOffendReboundEfficientDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getOffendReboundEfficient() > o1.getOffendReboundEfficient() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareDefendReboundEfficientDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getDefendReboundEfficient() > o1.getDefendReboundEfficient() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareAssistEfficientDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getAssistEfficient() > o1.getAssistEfficient() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareStealEfficientDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getStealEfficient() > o1.getStealEfficient() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareBlockShotEfficientDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getBlockShotEfficient() > o1.getBlockShotEfficient() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareFaultEfficientDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getFaultEfficient() > o1.getFaultEfficient() ? 1 : -1;
        }
    };
    
    public Comparator<Player> compareFrequencyDesc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o2.getFrequency() > o1.getFrequency() ? 1 : -1;
        }
    };*/
    
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

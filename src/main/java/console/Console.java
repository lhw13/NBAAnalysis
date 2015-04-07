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
	
	
	public void execute(PrintStream out, String[] args){
		//for detailed information, see http://dongwei.iteye.com/blog/230458
		if(args[0].equals("-team"))
			team(out,args);
		if(args[0].equals("-player"))
			player(out,args);
	}
	
	
	public void player(PrintStream out, String[] args) {
		ArrayList<Player> players = BLController.getInstance().getPlayers();
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
		
		if(king) {
			sortCons[0] = condition+".desc";
			sort = true;
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
					players = (ArrayList<Player>) CollectionUtils.collect(
							playerList, new Transformer() {
			                    public Object transform(Object arg0) {
			                    	Player p = (Player) arg0;
			                        return p;
			                    }
			                });
			    }
				
			}
			
		}
		
		
		if(sort) {//如果有sort命令			                
			for(String temp : sortCons) {//遍历所有排序命令
				String[] temps = temp.split("\\.");
				if(temps[1].equals("asc")) {//升序
					switch(temps[0]) {
/*					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;					
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;
					case "": sortConsList.add(e);break;*/
					}				
				} else {//降序 
					switch(temps[0]) {
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
			}			
		} else {//没有sort命令，使用默认排序命令
			if(high)//如果是高阶数据，用高阶数据的默认，否则用基本数据
				sortConsList.add(compareRealShot);
			else 
				sortConsList.add(comparePoint);			
		}
		sort(players, sortConsList);
		
		if(total) {//返回的数据是总数据
			
			for(int i=0;i<n && i<players.size();i++)//这是模仿刘瀚文，不知道干嘛
			{
				out.println(players.get(i).toNormalInfo());//to use which function
				out.println(players.get(i).toVO());
				System.out.println();
			}
		} else {//返回的数据是场均数据
			
			for(int i=0;i<n && i<players.size();i++)//这是模仿刘瀚文，不知道干嘛
			{
				out.println(players.get(i).toNormalInfoAvg());//to use which function
				out.println(players.get(i).toVO());
				
				System.out.println();
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
				out.println(teams.get(i).toVO());
			}
			//new ComparatorChain();
			//out.append(c);
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
        System.out.println("!!!!");
        Collections.sort(list, cmp);  
    }  
}

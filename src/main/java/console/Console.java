package console;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import server.businesslogic.BLController;
import server.businesslogic.Comparators;
import server.businesslogic.Player;
import server.businesslogic.Team;
import test.data.PlayerHotInfo;
import test.data.PlayerKingInfo;
import test.data.TeamHotInfo;
import vo.PlayerVO;

public class Console {
	public static String path="./conf/nba";
	BLController bl = null;
	//BLController bl = BLController.getInstance();
	public void execute(PrintStream out, String[] args){
		//for detailed information, see http://dongwei.iteye.com/blog/230458
		try {
			if(args[0].equals("-team"))
				team(out,args);
			if(args[0].equals("-player"))
				player(out,args);
			if(args[0].equals("--datasource"))
			{
				path=args[1];
				bl = BLController.getInstance();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void player(PrintStream out, String[] args) {
		if(bl==null)
			bl = BLController.getInstance();
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
		String condition = "score";
		String timeCon = "season";//判断是赛季还是当日
		
		
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
							 boolean isN=false;
							 for(int j=1;j<args.length;j++)	{
								 if(args[j].equals("-n")) {isN = true;break;}
							 }
							 if(!isN)	
							 	n=5; 
							 break;
				
				case "-sort":	sort = true;
								sortCons=args[++i].split(",");
								break;
				case "-filter":	filter = true;
								filterCons=args[++i].split(",");
								break;
				
			}
		
		if(king) {	
			sortCons = new String[1];
			sortCons[0] = condition+".desc";
			if(timeCon.equals("-season"))sort = true;
			else playerList = bl.getDailyHotPlayer(condition);
			for(int i=0;i<5;i++){
				System.out.println(playerList.get(i).toNormalInfo());
			}
		}
		
		if(filter){
			for(String temp : filterCons) {//遍历所有排序命令
			    final String[] temps = temp.split("\\.");
			    if(!temps[1].equals("All")){
			    	ArrayList<Player> result=new ArrayList<Player>();
			    	for(int i=0;i<playerList.size();i++) {
			                    Player p = playerList.get(i);
			                    if(satisfy(temps,p))
			                        result.add(p);
			                }
			    	playerList = result;
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
						sortConsList.add(Comparators.getPlayerComparator(temps[0]));
						
					}
				}
				
			} else {//没有sort命令，使用默认排序命令
				if(high){//如果是高阶数据，用高阶数据的默认，否则用基本数据
					sortConsList.add(Comparators.compareRealShotDesc);
				}else{
					sortConsList.add(Comparators.comparePointDesc);
				} 
			}
			if(!timeCon.equals("-daily")) {
				sortConsList.add(comparePlayerNameAsc);
				sort(playerList, sortConsList);
			}
		
				for(int i=0;i<n && i<playerList.size();i++)//这是模仿刘瀚文，不知道干嘛
				{
					out.println(i+1);
					out.println(playerList.get(i).toNormalInfo());//to use which function
					//out.println(playerList.get(i).toVO());
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
						case "score":
						case "rebound": 
						case "assist":					
						case "blockShot": 
						case "steal": 
						case "foul": 
						case "fault": 
						case "minute": sortConsList.add(Comparators.getPlayerAvgComparator(temps[0]));break;
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
						case "frequency": sortConsList.add(Comparators.getPlayerComparator(temps[0]));break;
						}
						String s = temps[0];
						Comparator<Player> c = Comparators.getPlayerAvgComparator(s);
						if(c==null)
							c = Comparators.getPlayerComparator(s);
						sortConsList.add(c);
					}
				}
				
			} else {//没有sort命令，使用默认排序命令
				if(high){//如果是高阶数据，用高阶数据的默认，否则用基本数据
					sortConsList.add(Comparators.compareRealShotDesc);
				}else{ 
					sortConsList.add(Comparators.comparePointAvgDesc);
				} 
			}
			if(!timeCon.equals("-daily")&&!hot) {
				sortConsList.add(comparePlayerNameAsc);
				sort(playerList, sortConsList);
			}
			if(high) {
				for(int i=0;i<n && i<playerList.size();i++)
				{
					out.println(i+1);
					out.println(playerList.get(i).toHighInfo());//to use which function
					//out.println(playerList.get(i).toVO());
				}
			} else if(hot) {
				ArrayList<Player> list = bl.getBestPromotionForConsole(condition, n);
				for(int i=0;i<n && i<list.size();i++)
				{
					Player p = list.get(i);
					PlayerHotInfo playerHot = new PlayerHotInfo();
					playerHot.setName(p.getName());
					playerHot.setTeamName(p.getTeamAbbreviation());
					playerHot.setField(condition);
					playerHot.setValue(p.getValue(condition));
					playerHot.setPosition(p.getPosition());
					playerHot.setUpgradeRate(p.getUpgradeRate(condition));
					out.println(i+1);
					out.println(playerHot);//to use which function
				}
			} else if (king) {
				for(int i=0;i<n && i<playerList.size();i++)
				{
					Player p = playerList.get(i);
					PlayerKingInfo playerKing = new PlayerKingInfo();
					playerKing.setName(p.getName());
					playerKing.setTeamName(p.getTeam().getAbbreviation());
					playerKing.setField(condition);
					playerKing.setValue(p.getValue(condition));
					playerKing.setPosition(p.getPosition());
					out.println(i+1);
					out.println(playerKing);//to use which function
				}
			}else {
				for(int i=0;i<n && i<playerList.size();i++)
				{
					out.println(i+1);
					out.println(playerList.get(i).toNormalInfoAvg());//to use which function
					//out.println(playerList.get(i).toVO());
				}
			}
		}
	
	}
	public void team(PrintStream out, String[] args) {
		if(bl==null)
			bl = BLController.getInstance();
		Comparator<Team> teamByName = Comparators.TeamByNameAsc;
		ArrayList<Team> teams = bl.getTeams();
		//注意是Team 不是 TeamVO
		boolean total = false;
		int n=30;
		String sortCondition = "score";
		String sortOrder = "desc";
		boolean high = false;
		boolean hot = false;
		boolean sort=false;
		for(int i=1;i<args.length;i++)
			switch(args[i])
			{
				case "-total":total = true;break;
				case "-n":n=Integer.parseInt(args[++i]);break;
				case "-sort": 
					sort = true;
					String temps[] = args[++i].split("\\."); 
					sortCondition =temps[0]; sortOrder=temps[1];
					break;
				case "-high":high=true;
				case "-hot": hot = true; sortCondition = args[++i]; break;				
			}
		ArrayList<Comparator<Team>> sortConsList = new ArrayList<Comparator<Team>>();
		if(high && !sort)
			sortCondition = "winRate";
		if(!high)
		{
		if(total)
		{         
			//mycmp = ComparatorUtils.reversedComparator(mycmp);
			//BeanComparator<Team> bc = new BeanComparator<Team>(sortCondition,mycmp);
				if(sortOrder.equals("desc"))
				{
					sortConsList.add(Comparators.getTeamComparator(sortCondition));
				}
				else
				{
					Collections.sort(teams,Comparators.getTeamComparatorAsc(sortCondition));
				}
				sortConsList.add(teamByName);
				sortTeam(teams,sortConsList);
			for(int i=0;i<n && i<teams.size();i++)
			{
				out.println(i+1);
				out.println(teams.get(i).toNormalInfo());//to use which function
				//out.println(teams.get(i).toVO());
			}
		}
		else if(hot)
		{
			sortConsList.add(Comparators.getTeamComparatorAvg(sortCondition));
			sortConsList.add(teamByName);
			sortTeam(teams,sortConsList);
			for(int i=0;i<n && i<teams.size();i++)
			{
				Team ts = teams.get(i);
				TeamHotInfo td = new TeamHotInfo();
				td.setField(sortCondition);
				td.setLeague(ts.getLeague());
				td.setTeamName(ts.getAbbreviation());
				td.setValue(ts.getValue(sortCondition));
				out.println(i+1);
				out.println(td);//to use which function
				//out.println(teams.get(i).toVO());
			}
		}
		else {//avg
			if(sortOrder.equals("desc"))
			{
				sortConsList.add(Comparators.getTeamComparatorAvg(sortCondition));
			}
			else
			{
				sortConsList.add(Comparators.getTeamComparatorAvgAsc(sortCondition));
			}
			sortConsList.add(teamByName);
			sortTeam(teams,sortConsList);
			for(int i=0;i<n && i<teams.size();i++)
			{
				out.println(i+1);
				out.println(teams.get(i).toNormalInfoAvg());//to use which function
				//out.println(teams.get(i).toVO());
			}
		}
		}
		else {//高阶数据
			if(sortOrder.equals("desc"))
			{
				sortConsList.add(Comparators.getTeamComparator(sortCondition));
			}
			else
			{
				Collections.sort(teams,Comparators.getTeamComparatorAsc(sortCondition));
			}
			sortConsList.add(teamByName);
			sortTeam(teams,sortConsList);
		for(int i=0;i<n && i<teams.size();i++)
		{
			out.println(i+1);
			out.println(teams.get(i).toHighInfo());//to use which function
			//out.println(teams.get(i).toVO());
		}
		}
	}
	
	//avg player asc comparator
		public static final Comparator<Player> comparePointAvgAsc = new Comparator<Player>(){  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	        	double d2 = (double)o2.getPoint()/(double)o2.getAppearance();
	        	double d1 = (double)o1.getPoint()/(double)o1.getAppearance();
	            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
	        	// return (double)o2.getPoint()/(double)o2.getAppearance() < (double)o1.getPoint()/(double)o1.getAppearance()? 1 : -1;
	        }
	    };
	    
	    public static final Comparator<Player> compareReboundAvgAsc = new Comparator<Player>(){  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {
	        	double d2 = (double)o2.getRebound()/(double)o2.getAppearance();
	        	double d1 = (double)o1.getRebound()/(double)o1.getAppearance();
	            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
	            //return (double)o2.getRebound()/(double)o2.getAppearance() < (double)o1.getRebound()/(double)o1.getAppearance()? 1 : -1;
	        }
	    };
	    public static final Comparator<Player> compareAssistAvgAsc = new Comparator<Player>(){  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {
	        	double d2 = (double)o2.getAssist()/(double)o2.getAppearance();
	        	double d1 = (double)o1.getAssist()/(double)o1.getAppearance();
	            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
	            //return (double)o2.getAssist()/(double)o2.getAppearance() < (double)o1.getAssist()/(double)o1.getAppearance()? 1 : -1;
	        }
	    };
	    
	    public static final Comparator<Player> compareBlockShotAvgAsc = new Comparator<Player>(){  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	        	double d2 = (double)o2.getBlockShot()/(double)o2.getAppearance();
	        	double d1 = (double)o1.getBlockShot()/(double)o1.getAppearance();
	            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
	        	//return (double)o2.getBlockShot()/(double)o2.getAppearance() < (double)o1.getBlockShot()/(double)o1.getAppearance()? 1 : -1;
	        }
	    };
	    
	    public static final Comparator<Player> compareStealAvgAsc = new Comparator<Player>(){  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	        	double d2 = (double)o2.getSteal()/(double)o2.getAppearance();
	        	double d1 = (double)o1.getSteal()/(double)o1.getAppearance();
	            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
	        	//return (double)o2.getSteal()/(double)o2.getAppearance() < (double)o1.getSteal()/(double)o1.getAppearance()? 1 : -1;
	        }
	    };
	    
	    public static final Comparator<Player> compareFoulAvgAsc = new Comparator<Player>(){  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	        	double d2 = (double)o2.getFoul()/(double)o2.getAppearance();
	        	double d1 = (double)o1.getFoul()/(double)o1.getAppearance();
	            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
	        	//return (double)o2.getFoul()/(double)o2.getAppearance() < (double)o1.getFoul()/(double)o1.getAppearance()? 1 : -1;
	        }
	    };
	    
	    public static final Comparator<Player> compareFaultAvgAsc = new Comparator<Player>(){  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	        	double d2 = (double)o2.getFault()/(double)o2.getAppearance();
	        	double d1 = (double)o1.getFault()/(double)o1.getAppearance();
	            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
	        	//return (double)o2.getFault()/(double)o2.getAppearance() < (double)o1.getFault()/(double)o1.getAppearance()? 1 : -1;
	        }
	    };
	    
	    public static final Comparator<Player> compareMinuteAvgAsc = new Comparator<Player>(){  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	        	double d2 = (double)o2.getMinute()/(double)o2.getAppearance();
	        	double d1 = (double)o1.getMinute()/(double)o1.getAppearance();
	            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
	        	//return (double)o2.getMinute()/(double)o2.getAppearance() < (double)o1.getMinute()/(double)o1.getAppearance()? 1 : -1;
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
    public static final Comparator<Player> comparePlayerNameAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
            return o1.getName().compareTo(o2.getName());
        }
    };
    
    public static final Comparator<Player> comparePointAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	int d2 = o2.getPoint();
        	int d1 = o1.getPoint();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	//return o2() < o1.getPoint() ? 1 : -1;
        }
    };
    
    public static final Comparator<Player> compareReboundAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	int d2 = o2.getRebound();
        	int d1 = o1.getRebound();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	//return o2.getRebound() < o1.getRebound() ? 1 : -1;
        }
    };
    private static final Comparator<Player> compareAssistAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	int d2 = o2.getAssist();
        	int d1 = o1.getAssist();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	//return o2.getAssist() < o1.getAssist() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareBlockShotAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	int d2 = o2.getBlockShot();
        	int d1 = o1.getBlockShot();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	//return o2.getBlockShot() < o1.getBlockShot() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareStealAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	int d2 = o2.getSteal();
        	int d1 = o1.getSteal();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	// return o2.getSteal() < o1.getSteal() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareFoulAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	int d2 = o2.getFoul();
        	int d1 = o1.getFoul();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	//return o2.getFoul() < o1.getFoul() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareFaultAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	int d2 = o2.getFault();
        	int d1 = o1.getFault();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	// return o2.getFault() < o1.getFault() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareMinuteAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	double d2 = o2.getMinute();
        	double d1 = o1.getMinute();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	//return o2.getMinute() < o1.getMinute() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareEfficientAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	double d2 = o2.getEfficient();
        	double d1 = o1.getEfficient();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	// return o2.getEfficient() < o1.getEfficient() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareShotAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	double d2 = o2.getShot();
        	double d1 = o1.getShot();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	//return o2.getShot() < o1.getShot() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareThreeAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	double d2 = o2.getThree();
        	double d1 = o1.getThree();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	// return o2.getThree() < o1.getThree() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> comparePenaltyAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	double d2 = o2.getPenalty();
        	double d1 = o1.getPenalty();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	//return o2.getPenalty() < o1.getPenalty() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareDoubleTwoAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	double d2 = o2.getDoubleTwo();
        	double d1 = o1.getDoubleTwo();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	//return o2.getDoubleTwo() < o1.getDoubleTwo() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareRealShotAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	double d2 = o2.getRealShot();
        	double d1 = o1.getRealShot();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	//return o2.getRealShot() < o1.getRealShot() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareGmScAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	double d2 = o2.getGmSc();
        	double d1 = o1.getGmSc();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	// return o2.getGmSc() < o1.getGmSc() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareShotEfficientAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	double d2 = o2.getShotEfficient();
        	double d1 = o1.getShotEfficient();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	//return o2.getShotEfficient() < o1.getShotEfficient() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareReboundEfficientAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	double d2 = o2.getReboundEfficient();
        	double d1 = o1.getReboundEfficient();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	//return o2.getReboundEfficient() < o1.getReboundEfficient() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareOffendReboundEfficientAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	double d2 = o2.getOffendReboundEfficient();
        	double d1 = o1.getOffendReboundEfficient();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	//return o2.getOffendReboundEfficient() < o1.getOffendReboundEfficient() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareDefendReboundEfficientAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	double d2 = o2.getDefendReboundEfficient();
        	double d1 = o1.getDefendReboundEfficient();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	//return o2.getDefendReboundEfficient() < o1.getDefendReboundEfficient() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareAssistEfficientAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	double d2 = o2.getAssistEfficient();
        	double d1 = o1.getAssistEfficient();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	// return o2.getAssistEfficient() < o1.getAssistEfficient() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareStealEfficientAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	double d2 = o2.getStealEfficient();
        	double d1 = o1.getStealEfficient();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	//return o2.getStealEfficient() < o1.getStealEfficient() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareBlockShotEfficientAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	double d2 = o2.getBlockShotEfficient();
        	double d1 = o1.getBlockShotEfficient();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	//return o2.getBlockShotEfficient() < o1.getBlockShotEfficient() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareFaultEfficientAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	double d2 = o2.getFaultEfficient();
        	double d1 = o1.getFaultEfficient();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	//return o2.getFaultEfficient() < o1.getFaultEfficient() ? 1 : -1;
        }
    };
    
    private static final Comparator<Player> compareFrequencyAsc = new Comparator<Player>() {  
		  
        @Override  
        public int compare(Player o1, Player o2) {  
        	double d2 = o2.getFrequency();
        	double d1 = o1.getFrequency();
            if(d1>d2)return 1;else if(d1<d2)return -1;else return 0;
        	//return o2.getFrequency() < o1.getFrequency() ? 1 : -1;
        }
    };


	//用于多重排序
	public final void sort(List<Player> list, final List<Comparator<Player>> comList) {  
        if (comList == null)  
            return;  
        Comparator<Player> cmp = new Comparator<Player>() {  
            @Override  
            public final int compare(Player o1, Player o2) {  
                for (Comparator<Player> comparator : comList) {
                    int x = comparator.compare(o1, o2);
                    if(x!=0)
                        return x;
                }  
                return 0;  
            }  
        };  
        Collections.sort(list, cmp);  
    }  
	public final void sortTeam(ArrayList<Team> list, final ArrayList<Comparator<Team>> comList) {  
        if (comList == null)  
            return;  
        Comparator<Team> cmp = new Comparator<Team>() {  
            @Override  
            public final int compare(Team o1, Team o2) {  
                for (Comparator<Team> comparator : comList) {  
                    int x = comparator.compare(o1, o2);
                    if(x!=0)
                        return x;
                }  
                return 0;  
            }  
        };  
        Collections.sort(list, cmp);  
    }
	private boolean satisfy(String[] temps,Player p) {
		switch(temps[0]){
    	case "position": 
    		return handleThePosition(temps[1], p.getPosition());
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
	public boolean handleThePosition(String temp, String position){
		if(position == null) return false;
		switch(temp){
		case "F":
			if(position.equals("F")||position.equals("F-C")||position.equals("F-G")||
					position.equals("C-F")||position.equals("G-F")){
				return true;
			}
			else{
				return false;
			}
		case "C":
			if(position.equals("C")||position.equals("C-F")||position.equals("C-G")||
					position.equals("F-C")||position.equals("G-C")){
				return true;
			}
			else{
				return false;
			}
		case "G":
			if(position.equals("G")||position.equals("G-C")||position.equals("G-F")||
					position.equals("C-G")||position.equals("F-G")){
				return true;
			}
			else{
				return false;
			}
		default:
			return false;
		}
		
	}
}

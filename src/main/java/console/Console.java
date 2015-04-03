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
	
	@SuppressWarnings("unchecked")
	public void player(PrintStream out, String[] args) {
		ArrayList<Player> players = BLController.getInstance().getPlayers();
		List<Player> playerList = players;
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
		
		String filterCondition = "position.F,league.West";
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
		
		Comparator mycmp = ComparableComparator.getInstance();              
		mycmp = ComparatorUtils.reversedComparator(mycmp);//逆序
		ArrayList<Object> sortFields = new ArrayList<Object>();
		
		if(sort) {//如果有sort命令			                
			for(String temp : sortCons) {//遍历所有排序命令
				String[] temps = temp.split("\\.");
				if(temps[1].equals("asc")) {//升序
					sortFields.add(new BeanComparator<Player>(temps[0]));				
				} else {//降序 
					sortFields.add(new BeanComparator<Player>(temps[0],mycmp));
				}
			}
						
		} else {//没有sort命令，使用默认排序命令
			if(high)//如果是高阶数据，用高阶数据的默认，否则用基本数据
				sortFields.add(new BeanComparator<Player>("realShot",mycmp));
			else 
				sortFields.add(new BeanComparator<Player>("score",mycmp));
			sortFields.add(new BeanComparator<Player>("name"));				
		}
		
		sortFields.add(new BeanComparator<Player>("name"));//如果之前的排序结果都一样，就按照姓名升序排列
		ComparatorChain multiSort = new ComparatorChain(sortFields);//多重排序链
		Collections.sort(players,multiSort);
		
		
		if(total) {//返回的数据是总数据
			
			for(int i=0;i<n && i<players.size();i++)//这是模仿刘瀚文，不知道干嘛
			{
				out.println(players.get(i).toNormalInfo());//to use which function
				out.println(players.get(i).toVO());
			}
		} else {//返回的数据是场均数据
			
			for(int i=0;i<n && i<players.size();i++)//这是模仿刘瀚文，不知道干嘛
			{
				out.println(players.get(i).toNormalInfoAvg());//to use which function
				out.println(players.get(i).toVO());
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
}

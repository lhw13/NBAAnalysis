package console;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import server.businesslogic.BLController;
import server.businesslogic.Player;
import server.businesslogic.Team;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
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
		int n=50;//default
		
		boolean total = false;
		boolean high = false;
		boolean sort = false;//是否有sort命令
		boolean filter = false;//是否有filter命令
		
		String[] sortCons = null;
		String[] filterCons;
		
		String filterCondition = "position.F,league.West";
		for(int i=1;i<args.length;i++)
			switch(args[i])
			{
				case "-total":total = true;break;
				case "-n":n=Integer.parseInt(args[++i]);break;
				case "-high":high=true;
				case "-hot":;
				case "-king":;
				case "-sort":	sort = true;
								sortCons=args[++i].split(",");
								break;
				case "filter":	filter = true;
								filterCons=args[++i].split(",");
								break;
				
			}
		if(total) {//返回的数据is总数据
			Comparator mycmp = ComparableComparator.getInstance();              
			mycmp = ComparatorUtils.reversedComparator(mycmp);//逆序
			if(sort) {//如果有sort命令
				ArrayList<Object> sortFields = new ArrayList<Object>();      
		        sortFields.add(new BeanComparator("id", mycmp)); //id逆序  (主)      
		        sortFields.add(new BeanComparator("name"));      //name正序 (副)   
				for(String temp : sortCons) {//遍历所有排序命令
					String[] temps = temp.split("\\.");
					if(temps[1].equals("asc")) {//升序
						sortFields.add(new BeanComparator<Player>(temps[0]));				
					} else {//降序 
						sortFields.add(new BeanComparator<Player>(temps[0],mycmp))	;
					}
				}
				sortFields.add(new BeanComparator<Player>("name"));//如果之前的排序结果都一样，就按照姓名升序排列
				ComparatorChain multiSort = new ComparatorChain(sortFields);//多重排序链
				Collections.sort(players,multiSort);
				for(int i=0;i<n && i<players.size();i++)
				{
					out.println(players.get(i).toNormalInfo());//to use which function
					out.println(players.get(i).toVO());
				}
			} else {//没有sort命令，使用默认排序命令
				
			}
		} else {//返回的数据是场均数据
			
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

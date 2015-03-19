package server.businesslogic;

import java.util.Comparator;

public class SortPlayersByTeam implements Comparator<Player>{
	public int compare(Player p1, Player p2)
	{//mainly used in BLController to improve the efficiency
		String s1 = p1.team.getAbbreviation();
		String s2 = p2.team.getAbbreviation();
		return s1.compareTo(s2);
	}

}

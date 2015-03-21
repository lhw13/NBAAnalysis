package bltest;

import java.util.Comparator;

import vo.PlayerVO;


public class SortPlayersByName implements Comparator<PlayerVO>{
	public int compare(PlayerVO p1, PlayerVO p2)
	{
		String s1 = p1.getName();
		String s2 = p2.getName();
		return s1.compareTo(s2);
	}

}

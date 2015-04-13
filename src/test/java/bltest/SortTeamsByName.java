package bltest;

import java.util.Comparator;

import vo.PlayerVO;
import vo.TeamVO;

public class SortTeamsByName implements Comparator<TeamVO> {
	public int compare(TeamVO t1, TeamVO t2) {
		String s1 = t1.getFullName();
		String s2 = t2.getFullName();
		return s1.compareTo(s2);  
	}
}

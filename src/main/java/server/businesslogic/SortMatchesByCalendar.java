package server.businesslogic;

import java.util.Calendar;
import java.util.Comparator;

import server.po.MatchPO;

public class SortMatchesByCalendar implements Comparator<MatchPO> {
	public int compare(MatchPO m1, MatchPO m2) {// mainly used in BLController to
		// improve the efficiency
		Calendar c1 = m1.getDate();
		Calendar c2 = m2.getDate();
		return c1.compareTo(c2);
	}
}

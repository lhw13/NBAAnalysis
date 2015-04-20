package server.businesslogic;

import java.util.Comparator;

public final  class Comparators {

	public static final Comparator<Player> compareAssistAvgDesc = new Comparator<Player>(){  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = (double)o2.getAssist()/(double)o2.appearance;
	    	Double d1 = (double)o1.getAssist()/(double)o1.appearance;
	        return d2.compareTo(d1);
	       // return (double)o2.getAssist()/(double)o2.appearance > (double)o1.getAssist()/(double)o1.appearance? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareAssistDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Integer i2=o2.getAssist();
	    	Integer i1=o1.getAssist();
	        return i2.compareTo(i1);
	    	//return o2.getAssist() > o1.getAssist() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareAssistEfficientDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = o2.getAssistEfficient();
	    	Double d1 = o1.getAssistEfficient();
	        return d2.compareTo(d1);
	    	//return o2.getAssistEfficient() > o1.getAssistEfficient() ? 1 : -1;
	    }
	};
	//Promotion
	public static final Comparator<Player> compareAssistPromotionDesc = new Comparator<Player>(){  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 =o2.assistPromotion;
	    	Double d1 =o1.assistPromotion ;
	    	return d2.compareTo(d1);  
	    }
	};
	public static final Comparator<Player> compareBlockShotAvgDesc = new Comparator<Player>(){  
			  
	        @Override  
	        public int compare(Player o1, Player o2) {  
	//  	return (double)o2.getBlockShot()/(double)o2.appearance > (double)o1.getBlockShot()/(double)o1.appearance? 1 : -1;
	        	Double d2 = (double)o2.getBlockShot()/(double)o2.appearance;
	        	Double d1 = (double)o1.getBlockShot()/(double)o1.appearance;
	            return d2.compareTo(d1);
	        }
	    };
	public static final Comparator<Player> compareBlockShotDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Integer i2=o2.getBlockShot();
	    	Integer i1=o1.getBlockShot();
	        return i2.compareTo(i1);
	    }
	};
	public static final Comparator<Player> compareBlockShotEfficientDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = o2.getBlockShotEfficient();
	    	Double d1 = o1.getBlockShotEfficient();
	        return d2.compareTo(d1);
	    	//return o2.getBlockShotEfficient() > o1.getBlockShotEfficient() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareDefendReboundEfficientDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = o2.getDefendReboundEfficient();
	    	Double d1 = o1.getDefendReboundEfficient();
	        return d2.compareTo(d1);
	    	//return o2.getDefendReboundEfficient() > o1.getDefendReboundEfficient() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareDoubleTwoDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = o2.getDoubleTwo();
	    	Double d1 = o1.getDoubleTwo();
	        return d2.compareTo(d1);
	    	//return o2.getDoubleTwo() > o1.getDoubleTwo() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareEfficientDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = o2.getEfficient();
	    	Double d1 = o1.getEfficient();
	        return d2.compareTo(d1);
	    	//return o2.getEfficient() > o1.getEfficient() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareFaultAvgDesc = new Comparator<Player>(){  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 =(double)o2.getFault()/(double)o2.appearance ;
	    	Double d1 =(double)o1.getFault()/(double)o1.appearance ;
	    	return d2.compareTo(d1);  
	    }
	};
	public static final Comparator<Player> compareFaultDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Integer d2 = o2.getFault();
	    	Integer d1 = o1.getFault();
	        return d2.compareTo(d1);
	    	//return o2.getFault() > o1.getFault() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareFaultEfficientDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = o2.getFaultEfficient();
	    	Double d1 = o1.getFaultEfficient();
	        return d2.compareTo(d1);
	    	// return o2.getFaultEfficient() > o1.getFaultEfficient() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareFoulAvgDesc = new Comparator<Player>(){  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 =(double)o2.getFoul()/(double)o2.appearance ;
	    	Double d1 =(double)o1.getFoul()/(double)o1.appearance ;
	    	return d2.compareTo(d1);            	
	    }
	};
	public static final Comparator<Player> compareFoulDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Integer d2 = o2.getFoul();
	    	Integer d1 = o1.getFoul();
	        return d2.compareTo(d1);
	    	//return o2.getFoul() > o1.getFoul() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareFrequencyDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = o2.getFrequency();
	    	Double d1 = o1.getFrequency();
	        return d2.compareTo(d1);
	    	//return o2.getFrequency() > o1.getFrequency() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareGmScDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = o2.getGmSc();
	    	Double d1 = o1.getGmSc();
	        return d2.compareTo(d1);
	    	//return o2.getGmSc() > o1.getGmSc() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareMinuteAvgDesc = new Comparator<Player>(){  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 =(double)o2.getMinute()/(double)o2.appearance ;
	    	Double d1 =(double)o1.getMinute()/(double)o1.appearance ;
	    	return d2.compareTo(d1);  
	    }
	};
	public static final Comparator<Player> compareMinuteDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = o2.getMinute();
	    	Double d1 = o1.getMinute();
	        return d2.compareTo(d1);
	    	//return o2.getMinute() > o1.getMinute() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareOffendReboundEfficientDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = o2.getOffendReboundEfficient();
	    	Double d1 = o1.getOffendReboundEfficient();
	        return d2.compareTo(d1);
	    	// return o2.getOffendReboundEfficient() > o1.getOffendReboundEfficient() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> comparePenaltyDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = o2.getPenalty();
	    	Double d1 = o1.getPenalty();
	        return d2.compareTo(d1);
	    	//return o2.getPenalty() > o1.getPenalty() ? 1 : -1;
	    }
	};
	//球员姓名应该是按升序排
	public static final Comparator<Player> comparePlayerNameAsc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	        return o1.getName().compareTo(o2.getName());
	    }
	};
	//avg player descent comparator
	public static final Comparator<Player> comparePointAvgDesc = new Comparator<Player>(){  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = (double)o2.getPoint()/(double)o2.appearance;
	    	Double d1 = (double)o1.getPoint()/(double)o1.appearance;
	        return d2.compareTo(d1);
	    //	return (double)o2.getPoint()/(double)o2.appearance > (double)o1.getPoint()/(double)o1.appearance? 1 : -1;
	    }
	};
	public static final Comparator<Player> comparePointDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Integer i2=o2.getPoint();
	    	Integer i1=o1.getPoint();
	        return i2.compareTo(i1);
	    	//return o2.getPoint() > o1.getPoint() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareRealShotDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = o2.getRealShot();
	    	Double d1 = o1.getRealShot();
	        return d2.compareTo(d1);
	    	//return o2.getRealShot() > o1.getRealShot() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareReboundAvgDesc = new Comparator<Player>(){  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = (double)o2.getRebound()/(double)o2.appearance;
	    	Double d1 = (double)o1.getRebound()/(double)o1.appearance;
	        return d2.compareTo(d1);
	    	//return (double)o2.getRebound()/(double)o2.appearance > (double)o1.getRebound()/(double)o1.appearance? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareReboundDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {
	    	Integer i2=o2.getRebound();
	    	Integer i1=o1.getRebound();
	        return i2.compareTo(i1);
	       // return o2.getRebound() > o1.getRebound() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareReboundEfficientDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = o2.getReboundEfficient();
	    	Double d1 = o1.getReboundEfficient();
	        return d2.compareTo(d1);
	    	// return o2.getReboundEfficient() > o1.getReboundEfficient() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareReboundPromotionDesc = new Comparator<Player>(){  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 =o2.reboundPromotion;
	    	Double d1 =o1.reboundPromotion ;
	    	return d2.compareTo(d1);
	    	//return o2.reboundPromotion > o1.reboundPromotion? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareScorePromotionDesc = new Comparator<Player>(){  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 =o2.scorePromotion;
	    	Double d1 =o1.scorePromotion ;
	    	return d2.compareTo(d1);
	    	//return o2.scorePromotion > o1.scorePromotion? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareShotDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = o2.getShot();
	    	Double d1 = o1.getShot();
	        return d2.compareTo(d1);
	    	// return o2.getShot() > o1.getShot() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareShotEfficientDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = o2.getShotEfficient();
	    	Double d1 = o1.getShotEfficient();
	        return d2.compareTo(d1);
	    	//return o2.getShotEfficient() > o1.getShotEfficient() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareStealAvgDesc = new Comparator<Player>(){  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 =(double)o2.getSteal()/(double)o2.appearance ;
	    	Double d1 =(double)o1.getSteal()/(double)o1.appearance ;
	    	return d2.compareTo(d1);      
	    }
	};
	public static final Comparator<Player> compareStealDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Integer d2 = o2.getSteal();
	    	Integer d1 = o1.getSteal();
	        return d2.compareTo(d1);
	    	//return o2.getSteal() > o1.getSteal() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareStealEfficientDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = o2.getStealEfficient();
	    	Double d1 = o1.getStealEfficient();
	        return d2.compareTo(d1);
	    	//return o2.getStealEfficient() > o1.getStealEfficient() ? 1 : -1;
	    }
	};
	public static final Comparator<Player> compareThreeDesc = new Comparator<Player>() {  
		  
	    @Override  
	    public int compare(Player o1, Player o2) {  
	    	Double d2 = o2.getThree();
	    	Double d1 = o1.getThree();
	        return d2.compareTo(d1);
	    	//return o2.getThree() > o1.getThree() ? 1 : -1;
	    }
	};
	public static final Comparator<Team> TeamByAssist = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer d2 = t2.assist;
			Integer d1 = t1.assist;
			return d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByAssistAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer d2 = t2.assist;
			Integer d1 = t1.assist;
			return -d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByAssistAvg = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.assist/t2.appearance;
			Double d1 = (double)t1.assist/t1.appearance;
			return d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByAssistAvgAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.assist/t2.appearance;
			Double d1 = (double)t1.assist/t1.appearance;
			return -d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByAssistEfficient = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getAssistEfficient();
	    	Double d1 = t1.getAssistEfficient();
	        return d2.compareTo(d1);
			//return t1.getAssistEfficient()<t2.getAssistEfficient()?1:-1;
		}
	};
	public static final Comparator<Team> TeamByAssistEfficientAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getAssistEfficient();
	    	Double d1 = t1.getAssistEfficient();
	        return -d2.compareTo(d1);
			//return -t1.getAssistEfficient()<t2.getAssistEfficient()?1:-1;
		}
	};
	public static final Comparator<Team> TeamByBlock = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer d2 = t2.blockShot;
			Integer d1 = t1.blockShot;
			return d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByBlockAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer d2 = t2.blockShot;
			Integer d1 = t1.blockShot;
			return -d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByBlockAvg = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.blockShot/t2.appearance;
			Double d1 = (double)t1.blockShot/t1.appearance;
			return d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByBlockAvgAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.blockShot/t2.appearance;
			Double d1 = (double)t1.blockShot/t1.appearance;
			return -d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByDefendEfficient = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getDefendEfficient();
	    	Double d1 = t1.getDefendEfficient();
	        return d2.compareTo(d1);
			//return t1.getDefendEfficient()<t2.getDefendEfficient()?1:-1;
		}
	};
	public static final Comparator<Team> TeamByDefendEfficientAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getDefendEfficient();
	    	Double d1 = t1.getDefendEfficient();
	        return -d2.compareTo(d1);
			//return -t1.getDefendEfficient()<t2.getDefendEfficient()?1:-1;
		}
	};
	public static final Comparator<Team> TeamByDefendRebound = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer i1= t1.defendRebound;
			Integer i2 = t2.defendRebound;
			return i2.compareTo(i1);
		}
	};
	public static final Comparator<Team> TeamByDefendReboundAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer i1= t1.defendRebound;
			Integer i2 = t2.defendRebound;
			return -i2.compareTo(i1);
		}
	};
	public static final Comparator<Team> TeamByDefendReboundAvg = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double i1= (double)t1.defendRebound/t1.appearance;
			Double i2 = (double)t2.defendRebound/t2.appearance;
			return i2.compareTo(i1);
		}
	};
	public static final Comparator<Team> TeamByDefendReboundAvgAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double i1= (double)t1.defendRebound/t1.appearance;
			Double i2 = (double)t2.defendRebound/t2.appearance;
			return -i2.compareTo(i1);
		}
	};
	public static final Comparator<Team> TeamByDefendReboundEfficient = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getDefendReboundEfficient();
	    	Double d1 = t1.getDefendReboundEfficient();
	        return d2.compareTo(d1);
			//return t1.getDefendReboundEfficient()<t2.getDefendReboundEfficient()?1:-1;
		}
	};
	public static final Comparator<Team> TeamByDefendReboundEfficientAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getDefendReboundEfficient();
	    	Double d1 = t1.getDefendReboundEfficient();
	        return -d2.compareTo(d1);
			//return -t1.getDefendReboundEfficient()<t2.getDefendReboundEfficient()?1:-1;
		}
	};
	public static final Comparator<Team> TeamByFault = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer i2 = t2.fault;
			Integer i1 = t1.fault;
			return i2.compareTo(i1);
		}
	};
	public static final Comparator<Team> TeamByFaultAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer i2 = t2.fault;
			Integer i1 = t1.fault;
			return -i2.compareTo(i1);
		}
	};
	public static final Comparator<Team> TeamByFaultAvg = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer i2 = t2.fault/t2.appearance;
			Integer i1 = t1.fault/t2.appearance;
			return i2.compareTo(i1);
		}
	};
	public static final Comparator<Team> TeamByFaultAvgAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer i2 = t2.fault;
			Integer i1 = t1.fault;
			return -i2.compareTo(i1);
		}
	};
	public static final Comparator<Team> TeamByFoul = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer i2 = t2.foul;
			Integer i1 = t1.foul;
			return i2.compareTo(i1);
		}
	};
	public static final Comparator<Team> TeamByFoulAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer i2 = t2.foul;
			Integer i1 = t1.foul;
			return -i2.compareTo(i1);
		}
	};
	public static final Comparator<Team> TeamByFoulAvg = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.foul/t2.appearance;
			Double d1 = (double)t1.foul/t1.appearance;
			return d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByFoulAvgAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.foul/t2.appearance;
			Double d1 = (double)t1.foul/t1.appearance;
			return -d2.compareTo(d1);
		}
	};
	//升序
	public static final Comparator<Team> TeamByNameAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			return t1.getAbbreviation().compareTo(t2.getAbbreviation());
		}
	};
	public static final Comparator<Team> TeamByOffendEfficient = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getOffendEfficient();
	    	Double d1 = t1.getOffendEfficient();
	        return d2.compareTo(d1);
			//return t1.getOffendEfficient()<t2.getOffendEfficient()?1:-1;
		}
	};
	public static final Comparator<Team> TeamByOffendEfficientAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getOffendEfficient();
	    	Double d1 = t1.getOffendEfficient();
	        return -d2.compareTo(d1);
			//return -t1.getOffendEfficient()<t2.getOffendEfficient()?1:-1;
		}
	};
	public static final Comparator<Team> TeamByOffendRebound = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer d2 = t2.offendRebound;
			Integer d1 = t1.offendRebound;
	        return d2.compareTo(d1);
			//return t1.offendRebound<t2.offendRebound?1:-1;
		}
	};
	public static final Comparator<Team> TeamByOffendReboundAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer d2 = t2.offendRebound;
			Integer d1 = t1.offendRebound;
	        return -d2.compareTo(d1);
			//return -t1.offendRebound<t2.offendRebound?1:-1;
		}
	};
	public static final Comparator<Team> TeamByOffendReboundAvg = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.offendRebound/t2.appearance;
			Double d1 = (double)t1.offendRebound/t1.appearance;
	        return d2.compareTo(d1);
			//return t1.offendRebound<t2.offendRebound?1:-1;
		}
	};
	public static final Comparator<Team> TeamByOffendReboundAvgAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.offendRebound/t2.appearance;
			Double d1 = (double)t1.offendRebound/t1.appearance;
	        return -d2.compareTo(d1);
			//return t1.offendRebound<t2.offendRebound?1:-1;
		}
	};
	public static final Comparator<Team> TeamByOffendReboundEfficient = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getOffendReboundEfficient();
	    	Double d1 = t1.getOffendReboundEfficient();
	        return d2.compareTo(d1);
			//return t1.getOffendReboundEfficient()<t2.getOffendReboundEfficient()?1:-1;
		}
	};
	public static final Comparator<Team> TeamByOffendReboundEfficientAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getOffendReboundEfficient();
	    	Double d1 = t1.getOffendReboundEfficient();
	        return -d2.compareTo(d1);
			//return -t1.getOffendReboundEfficient()<t2.getOffendReboundEfficient()?1:-1;
		}
	};
	public static final Comparator<Team> TeamByOffendRound = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getOffendRound();
	    	Double d1 = t1.getOffendRound();
	        return d2.compareTo(d1);
			//return t1.getOffendRound()<t2.getOffendRound()?1:-1;
		}
	};
	public static final Comparator<Team> TeamByOffendRoundAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getOffendRound();
	    	Double d1 = t1.getOffendRound();
	        return -d2.compareTo(d1);
			//return -t1.getOffendRound()<t2.getOffendRound()?1:-1;
		}
	};
	public static final Comparator<Team> TeamByOffendRoundAvg = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getOffendRound()/t2.appearance;
	    	Double d1 = t1.getOffendRound()/t1.appearance;
	        return d2.compareTo(d1);
			//return t1.getOffendRound()<t2.getOffendRound()?1:-1;
		}
	};
	public static final Comparator<Team> TeamByOffendRoundAvgAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getOffendRound()/t2.appearance;
	    	Double d1 = t1.getOffendRound()/t1.appearance;
	        return -d2.compareTo(d1);
			//return t1.getOffendRound()<t2.getOffendRound()?1:-1;
		}
	};
	public static final Comparator<Team> TeamByPenalty = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getPenalty();
			Double d1 = t1.getPenalty();
			return d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByPenaltyAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getPenalty();
			Double d1 = t1.getPenalty();
			return -d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByPoint = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer d2 = t2.score;
			Integer d1 = t1.score;
			return d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByPointAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer d2 = t2.score;
			Integer d1 = t1.score;
			return -d2.compareTo(d1);
		}
	};
	//avgteam comparator
	
	
	
	
	
	
	public static final Comparator<Team> TeamByPointAvg = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.score/t2.appearance;
			Double d1 = (double)t1.score/t1.appearance;
			return d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByPointAvgAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.score/t2.appearance;
			Double d1 = (double)t1.score/t1.appearance;
			return -d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByRebound = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer d2 = t2.rebound;
			Integer d1 = t1.rebound;
			return d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByReboundAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer d2 = t2.rebound;
			Integer d1 = t1.rebound;
			return -d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByReboundAvg = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.rebound/t2.appearance;
			Double d1 = (double)t1.rebound/t1.appearance;
			return d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByReboundAvgAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.rebound/t2.appearance;
			Double d1 = (double)t1.rebound/t1.appearance;
			return -d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByShot = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getShot();
			Double d1 = t1.getShot();
			return d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByShotAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getShot();
			Double d1 = t1.getShot();
			return -d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamBySteal = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer d2 = t2.steal;
			Integer d1 = t1.steal;
			return d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByStealAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Integer d2 = t2.steal;
			Integer d1 = t1.steal;
			return -d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByStealAvg = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.steal/t2.appearance;
			Double d1 = (double)t1.steal/t1.appearance;
			return d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByStealAvgAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = (double)t2.steal/t2.appearance;
			Double d1 = (double)t1.steal/t1.appearance;
			return -d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByStealEfficient = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getStealEfficient();
	    	Double d1 = t1.getStealEfficient();
	        return d2.compareTo(d1);
			//return t1.getStealEfficient()<t2.getStealEfficient()?1:-1;
		}
	};
	public static final Comparator<Team> TeamByStealEfficientAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getStealEfficient();
	    	Double d1 = t1.getStealEfficient();
	        return -d2.compareTo(d1);
			//return -t1.getStealEfficient()<t2.getStealEfficient()?1:-1;
		}
	};
	public static final Comparator<Team> TeamByThree = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getThree();
			Double d1 = t1.getThree();
			return d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByThreeAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getThree();
			Double d1 = t1.getThree();
			return -d2.compareTo(d1);
		}
	};
	public static final Comparator<Team> TeamByWinRate = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getWinRate();
			Double d1 = t1.getWinRate();
	        return d2.compareTo(d1);
			//return t1.offendRebound<t2.offendRebound?1:-1;
		}
	};
	public static final Comparator<Team> TeamByWinRateAsc = new Comparator<Team>() {
		public int compare(Team t1,Team t2) {
			Double d2 = t2.getWinRate();
			Double d1 = t1.getWinRate();
	        return -d2.compareTo(d1);
			//return -t1.offendRebound<t2.offendRebound?1:-1;
		}
	};

	public static final Comparator<Team> getTeamComparator(String sort) {
		switch(sort) {
		case "point": return TeamByPoint;
		case "score": return TeamByPoint;
		case "rebound": return TeamByRebound;
		case "blockShot": return TeamByBlock;
		case "assist": return TeamByAssist;
		case "steal": return TeamBySteal;
		case "foul": return TeamByFoul;
		case "fault": return TeamByFault;
		case "three": return TeamByThree;
		case "shot": return TeamByShot;
		case "penalty": return TeamByPenalty;
		case "defendRebound": return TeamByDefendRebound;
		case "offendRebound": return TeamByOffendRebound;
		case "winRate": return TeamByWinRate;
		case "offendRound": return TeamByOffendRound;
		case "offendEfficient": return TeamByOffendEfficient;
		case "defendEfficient": return TeamByDefendEfficient;
		case "offendReboundEfficient": return TeamByOffendReboundEfficient;
		case "defendReboundEfficient": return TeamByDefendReboundEfficient;
		case "stealEfficient": return TeamByStealEfficient;
		case "assistEfficient": return TeamByAssistEfficient;
		default: return null;
		}
	}

	public static final Comparator<Team> getTeamComparatorAsc(String sort) {
		switch(sort) {
		case "point": return TeamByPointAsc;
		case "score": return TeamByPointAsc;
		case "rebound": return TeamByReboundAsc;
		case "blockShot": return TeamByBlockAsc;
		case "assist": return TeamByAssistAsc;
		case "steal": return TeamByStealAsc;
		case "foul": return TeamByFoulAsc;
		case "fault": return TeamByFaultAsc;
		case "three": return TeamByThreeAsc;
		case "shot": return TeamByShotAsc;
		case "penalty": return TeamByPenaltyAsc;
		case "defendRebound": return TeamByDefendReboundAsc;
		case "offendRebound": return TeamByOffendReboundAsc;
		case "winRate": return TeamByWinRateAsc;
		case "offendRound": return TeamByOffendRoundAsc;
		case "offendEfficient": return TeamByOffendEfficientAsc;
		case "defendEfficient": return TeamByDefendEfficientAsc;
		case "offendReboundEfficient": return TeamByOffendReboundEfficientAsc;
		case "defendReboundEfficient": return TeamByDefendReboundEfficientAsc;
		case "stealEfficient": return TeamByStealEfficientAsc;
		case "assistEfficient": return TeamByAssistEfficientAsc;
		default: return null;
		}
	}

	public static final Comparator<Team> getTeamComparatorAvg(String sort) {
		switch(sort) {
		case "point": return TeamByPointAvg;
		case "score": return TeamByPointAvg;
		case "rebound": return TeamByReboundAvg;
		case "blockShot": return TeamByBlockAvg;
		case "assist": return TeamByAssistAvg;
		case "steal": return TeamByStealAvg;
		case "foul": return TeamByFoulAvg;
		case "fault": return TeamByFaultAvg;
		case "three": return TeamByThree;
		case "shot": return TeamByShot;
		case "penalty": return TeamByPenalty;
		case "defendRebound": return TeamByDefendReboundAvg;
		case "offendRebound": return TeamByOffendReboundAvg;
		case "winRate": return TeamByWinRate;
		case "offendRound": return TeamByOffendRoundAvg;
		case "offendEfficient": return TeamByOffendEfficient;
		case "defendEfficient": return TeamByDefendEfficient;
		case "offendReboundEfficient": return TeamByOffendReboundEfficient;
		case "defendReboundEfficient": return TeamByDefendReboundEfficient;
		case "stealEfficient": return TeamByStealEfficient;
		case "assistEfficient": return TeamByAssistEfficient;
		default: return null;
		}
	}

	public static final Comparator<Team> getTeamComparatorAvgAsc(String sort) {
		switch(sort) {
		case "point": return TeamByPointAvgAsc;
		case "score": return TeamByPointAvgAsc;
		case "rebound": return TeamByReboundAvgAsc;
		case "blockShot": return TeamByBlockAvgAsc;
		case "assist": return TeamByAssistAvgAsc;
		case "steal": return TeamByStealAvgAsc;
		case "foul": return TeamByFoulAvgAsc;
		case "fault": return TeamByFaultAvgAsc;
		case "three": return TeamByThreeAsc;
		case "shot": return TeamByShotAsc;
		case "penalty": return TeamByPenaltyAsc;
		case "defendRebound": return TeamByDefendReboundAvgAsc;
		case "offendRebound": return TeamByOffendReboundAvgAsc;
		case "winRate": return TeamByWinRateAsc;
		case "offendRound": return TeamByOffendRoundAvgAsc;
		case "offendEfficient": return TeamByOffendEfficientAsc;
		case "defendEfficient": return TeamByDefendEfficientAsc;
		case "offendReboundEfficient": return TeamByOffendReboundEfficientAsc;
		case "defendReboundEfficient": return TeamByDefendReboundEfficientAsc;
		case "stealEfficient": return TeamByStealEfficientAsc;
		case "assistEfficient": return TeamByAssistEfficientAsc;
		default: return null;
		}
	}

	public static final Comparator<Player> getPlayerAvgComparator(String sort) {
		switch(sort) {
		case "score": return comparePointAvgDesc;
		case "point": return comparePointAvgDesc;
		case "rebound": return compareReboundAvgDesc;
		case "assist": return compareAssistAvgDesc;					
		case "blockShot": return compareBlockShotAvgDesc;
		case "steal": return compareStealAvgDesc;
		case "foul": return compareFoulAvgDesc;
		case "fault": return compareFaultAvgDesc;
		case "minute": return compareMinuteAvgDesc;
		default: return null;
		}
	}

	public static final Comparator<Player> getPlayerComparator(String sort) {
		switch(sort) {
		case "score": return comparePointDesc;
		case "point": return comparePointDesc;
		case "rebound": return compareReboundDesc;
		case "assist": return compareAssistDesc;					
		case "blockShot": return compareBlockShotDesc;
		case "steal": return compareStealDesc;
		case "foul": return compareFoulDesc;
		case "fault": return compareFaultDesc;
		case "minute": return compareMinuteDesc;
		case "efficient": return compareEfficientDesc;
		case "shot": return compareShotDesc;
		case "three": return compareThreeDesc;
		case "penalty": return comparePenaltyDesc;
		case "doubleTwo": return compareDoubleTwoDesc;
		case "realShot": return compareRealShotDesc;
		case "GmSc": return compareGmScDesc;
		case "shotEfficient": return compareShotEfficientDesc;
		case "reboundEfficient": return compareReboundEfficientDesc;
		case "offendReboundEfficient": return compareOffendReboundEfficientDesc;
		case "defendReboundEfficient": return compareDefendReboundEfficientDesc;
		case "assistEfficient": return compareAssistEfficientDesc;
		case "stealEfficient": return compareStealEfficientDesc;		
		case "blockShotEfficient": return compareBlockShotEfficientDesc;
		case "faultEfficient": return compareFaultEfficientDesc;
		case "frequency": return compareFrequencyDesc;
		default: return null;
		}
	}

}

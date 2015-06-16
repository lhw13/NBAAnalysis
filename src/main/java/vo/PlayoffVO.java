package vo;

public class PlayoffVO {
	public PlayoffVO(String abr1, String abr2) {
		super();
		this.abr1 = abr1;
		this.abr2 = abr2;
	}
	String abr1;
	String abr2;
	int win1=0;
	int win2=0;
	public void incre1() {
		win1++;
	}
	public void incre2() {
		win2++;
	}
	public String getAbr1() {
		return abr1;
	}
	public void setAbr1(String abr1) {
		this.abr1 = abr1;
	}
	public String getAbr2() {
		return abr2;
	}
	public void setAbr2(String abr2) {
		this.abr2 = abr2;
	}
	public int getWin1() {
		return win1;
	}
	public void setWin1(int win1) {
		this.win1 = win1;
	}
	public int getWin2() {
		return win2;
	}
	public void setWin2(int win2) {
		this.win2 = win2;
	}
	public boolean contains(String abr1, String abr2) {
		return ((this.abr1.equals(abr1)&&this.abr2.equals(abr2))||(this.abr1.equals(abr2)&&this.abr2.equals(abr1)));
	}
	
	public boolean contains(String abr) {
		return ((this.abr1.equals(abr) || this.abr2.equals(abr)));
	}
}

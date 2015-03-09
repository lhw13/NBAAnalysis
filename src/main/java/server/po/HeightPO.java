package server.po;

public class HeightPO {
	public HeightPO() {}
	public HeightPO(int feet, int inch) {
		super();
		this.feet = feet;
		this.inch = inch;
	}
	int feet;
	int inch;
	public int getFeet() {
		return feet;
	}
	public void setFeet(int feet) {
		this.feet = feet;
	}
	public int getInch() {
		return inch;
	}
	public void setInch(int inch) {
		this.inch = inch;
	}
}

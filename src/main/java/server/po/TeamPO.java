package server.po;

public class TeamPO {
	public TeamPO() {}
	public TeamPO(String fullName, String abbreviation, String location,
			char division, String zone, String home, int setupTime) {
		super();
		this.fullName = fullName;
		this.abbreviation = abbreviation;
		this.location = location;
		this.division = division;
		this.zone = zone;
		this.home = home;
		this.setupTime = setupTime;
	}
	String fullName;
	String abbreviation;
	String location;
	char division;
	String zone;
	String home;
	int setupTime;
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public char getDivision() {
		return division;
	}
	public void setDivision(char division) {
		this.division = division;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public int getSetupTime() {
		return setupTime;
	}
	public void setSetupTime(int setupTime) {
		this.setupTime = setupTime;
	}
}

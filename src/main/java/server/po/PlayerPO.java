package server.po;

import java.util.Calendar;

public class PlayerPO {
	public PlayerPO(String name) {
		this.name = name;
		birth = Calendar.getInstance();
		birth.set(0, 0, 0, 0, 0, 0);
	}

	public PlayerPO(String name, int number, String position, HeightPO height,
			int weight, Calendar birth, int age, int exp, String school) {
		super();
		this.name = name;
		this.number = number;
		this.position = position;
		this.height = height;
		this.weight = weight;
		this.birth = birth;
		this.age = age;
		this.exp = exp;
		this.school = school;
	}

	String name;
	int number;
	String position;
	HeightPO height = new HeightPO();
	int weight;
	Calendar birth;
	int age;
	int exp;
	String school;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public HeightPO getHeight() {
		return height;
	}

	public void setHeight(HeightPO height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Calendar getBirth() {
		return birth;
	}

	public void setBirth(Calendar birth) {
		this.birth = birth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
}

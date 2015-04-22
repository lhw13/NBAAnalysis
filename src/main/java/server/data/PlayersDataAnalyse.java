package server.data;

import java.util.ArrayList;
import java.util.Calendar;

import server.po.HeightPO;
import server.po.PlayerPO;

public  final class PlayersDataAnalyse {
	private static String fileName;

	public static PlayerPO PlayerPOMade(ArrayList<String> playerForm) {
		fileName = playerForm.get(0);
		String name;
		int number;
		String position;
		HeightPO height;
		int weight;
		Calendar birth;
		int age;
		int exp;
		String school;
		ArrayList<String> playerData = new ArrayList<String>(10);
		for (int i = 2; i < playerForm.size(); i = i + 2) {
			playerData.add(playerForm.get(i).split("\\│")[1].replace("║", ""));
		}
		name = playerData.get(0).trim();
		number = stringToInt(playerData.get(1).replace("\t", ""));
		position = (playerData.get(2).replace("\t", ""));
		height = stringToHeightPO(playerData.get(3).replace("\t", ""));
		weight = stringToInt(playerData.get(4).replace("\t", ""));
		birth = stringToCalendar(playerData.get(5).replace("\t", ""));
		age = stringToInt(playerData.get(6).replace("\t", ""));
		exp = stringToInt(playerData.get(7).replace("\t", ""));
		school = playerData.get(8).trim();
		return new PlayerPO(name, number, position, height, weight, birth, age,
				exp, school);
	}

	private static final int stringToInt(String str) {
		int result;
		try {
			result = Integer.parseInt(str);
		} catch (Exception e) {
			result = -1;
		}
		return result;
	}

	private static HeightPO stringToHeightPO(String str) {
		int feet;
		int inch;
		feet = stringToInt(str.split("-")[0]);
		inch = stringToInt(str.split("-")[1]);
		return new HeightPO(feet, inch);
	}

	private static Calendar stringToCalendar(String str) {
		Calendar result = Calendar.getInstance();
		int year = stringToInt(str.split(",")[1].replace(" ", ""));
		int month = getMonth(str.split(",")[0].split(" ")[0]);
		int day = stringToInt(str.split(",")[0].split(" ")[1]);
		result.set(year, month, day, 0, 0, 0);
		return result;
	}

	public static int getMonth(String str) {
		int month = -1;
		switch (str) {
		case "JAN":
			month = 0;
			break;
		case "FEB":
			month = 1;
			break;
		case "MAR":
			month = 2;
			break;
		case "APR":
			month = 3;
			break;
		case "MAY":
			month = 4;
			break;
		case "JUN":
			month = 5;
			break;
		case "JUL":
			month = 6;
			break;
		case "AUG":
			month = 7;
			break;
		case "SEP":
			month = 8;
			break;
		case "OCT":
			month = 9;
			break;
		case "NOV":
			month = 10;
			break;
		case "DEC":
			month = 11;
			break;
		}
		return month;
	};
}

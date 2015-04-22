package server.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public  final class DataReader {
	private static BufferedReader br;
	private static InputStreamReader file;

	public static synchronized ArrayList<String> dataReader(File filePoint) {
		ArrayList<String> result = new ArrayList<String>(40);
		try {
			file = new InputStreamReader(new FileInputStream(filePoint), "UTF-8");
			br = new BufferedReader(file);
			String line = "";
			result.add(filePoint.getName());
			while ((line = br.readLine()) != null) {
				result.add(line);
			}
			file.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}

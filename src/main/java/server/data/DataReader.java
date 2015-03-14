package server.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
public class DataReader {
private static BufferedReader br;
private static FileReader file;
public static ArrayList<String> dataReader(File filePoint)  {
	ArrayList<String>  result=new ArrayList<String> ();
	try{
		file=new FileReader(filePoint);
		br = new BufferedReader(file); 
		String line="";
		result.add(filePoint.getName());
		while((line=br.readLine())!=null){
			result.add(line);
		}
		file.close();
		br.close();
	}catch(Exception e){
		e.printStackTrace();
	}	
	return result;
}
}

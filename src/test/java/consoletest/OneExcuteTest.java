package consoletest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Test;

import console.Console;

public class OneExcuteTest {
	Console c = new Console();
	@Test
	public void executableTest()
	{
		PrintStream p = null;
		try {
			p = new PrintStream(new File("test0.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ss[] = {"-player","-hot","assist","-n","5"};
		c.execute(p, ss);
	}
	
	@Test
	public void testTeam1()
	{
		
		String ss[] = {"-team"};
		PrintStream p = null;
		try {
			p = new PrintStream(new File("test1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss);
	}
	
	@Test
	public void testTeam2()
	{
		
		String ss[] = {"-team","hot","assist","-n","5"};
		PrintStream p = null;
		try {
			p = new PrintStream(new File("test2.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss);
	}
	
	@Test
	public void testTeam3()
	{
		
		String ss[] = {"-team","-total","-all","-n","10","-sort","shot.desc"};
		PrintStream p = null;
		try {
			p = new PrintStream(new File("test3.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss);
	}
	
	@Test
	public void testTeam4()
	{
		
		String ss[] = {"-team","-high","-n","5","-sort","stealEfficient.asc"};
		PrintStream p = null;
		try {
			p = new PrintStream(new File("test4.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss);
	}
	
	@Test
	public void testAllTeam()
	{
		
		String ss[] = {"-team","-all","-total"};
		PrintStream p = null;
		try {
			p = new PrintStream(new File("testAllTeam.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss);
	}
	
	@Test
	public void testAllPlayer()
	{
		
		String ss[] = {"-player","-all","n","50","-total"};
		PrintStream p = null;
		try {
			p = new PrintStream(new File("testAllPlayer.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss);
	}
	
	@Test
	public void test1()
	{
		String ss[] = {"-player","-hot","assist","-n","5"};
		String ss2[] = {"-player"};
		
		PrintStream p = null;
		try {
			p = new PrintStream(new File("test11.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss);
			c.execute(p, ss2);
		
	}
	
	@Test
	public void test2()
	{
		String ss[] = {"-player","-all","-n","10"};
		
		PrintStream p = null;
		try {
			p = new PrintStream(new File("test12.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss);
		
	}
	
	@Test
	public void test3()
	{
		String ss[] = {"-player","-high","-n","10","-sort","frequency.desc"};
		
		PrintStream p = null;
		try {
			p = new PrintStream(new File("test13.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss);
		
	}
	
	@Test
	public void test4()
	{
		String ss[] = {"-player","-king","score","-season"};
		
		PrintStream p = null;
		try {
			p = new PrintStream(new File("test14.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss);
	}
	
	@Test
	public void test5()
	{
		String ss[] = {"-player","-king","score","-season"};
		
		PrintStream p = null;
		try {
			p = new PrintStream(new File("test15.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss);
	}
	
	@Test
	public void test6()
	{
		String ss[] = {"-player","-total","-all","-n","10","-filter","position.F,league.west","-sort","shot.desc"};
		
		PrintStream p = null;
		try {
			p = new PrintStream(new File("test16.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss);
	}
	
	@Test
	public void test7()
	{
		String ss[] = {"-team","-all","-n","10"};
		
		PrintStream p = null;
		try {
			p = new PrintStream(new File("test17.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss);
	}
}

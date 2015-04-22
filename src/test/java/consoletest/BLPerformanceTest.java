/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consoletest;

import console.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import org.junit.Test;

/**
 *
 * @author soft
 */
public class BLPerformanceTest {
	@Test
	public void testBLPerformance()
        {
                Console c = new Console();
            for(int i=0;i<200;i++)
            {
            	PrintStream p = null;
        		try {
        			p = new PrintStream(new File("test0.txt"));
        		} catch (FileNotFoundException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
		String ss1[] = {"-player","-hot","assist","-n","5"};
		c.execute(p, ss1);
		
		String ss2[] = {"-team"};
		 p = null;
		try {
			p = new PrintStream(new File("test1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss2);
		
		String ss3[] = {"-team","hot","assist","-n","5"};
		 p = null;
		try {
			p = new PrintStream(new File("test2.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss3);
		
		String ss4[] = {"-team","-total","-all","-n","10","-sort","shot.desc"};
		 p = null;
		try {
			p = new PrintStream(new File("test3.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss4);
		
		String ss5[] = {"-team","-high","-n","5","-sort","stealEfficient.asc"};
		 p = null;
		try {
			p = new PrintStream(new File("test4.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss5);
		
		String ss6[] = {"-team","-all","-total"};
		 p = null;
		try {
			p = new PrintStream(new File("testAllTeam.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss6);
	
		String ss7[] = {"-player","-all","n","50","-total"};
		 p = null;
		try {
			p = new PrintStream(new File("testAllPlayer.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss7);
		String ss8[] = {"-player","-hot","assist","-n","5"};
		String ss9[] = {"-player"};
		
		 p = null;
		try {
			p = new PrintStream(new File("test11.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss8);
			c.execute(p, ss9);
		
		String ss10[] = {"-player","-all","-n","10"};
		
		 p = null;
		try {
			p = new PrintStream(new File("test12.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss10);
		
		String ss11[] = {"-player","-high","-n","10","-sort","frequency.desc"};
		
		 p = null;
		try {
			p = new PrintStream(new File("test13.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss11);
		
		String ss12[] = {"-player","-king","score","-season"};
		
		 p = null;
		try {
			p = new PrintStream(new File("test14.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss12);

		String ss13[] = {"-player","-king","score","-season"};
		
		 p = null;
		try {
			p = new PrintStream(new File("test15.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss13);

		String ss14[] = {"-player","-total","-all","-n","10","-filter","position.F,league.west","-sort","shot.desc"};
		
		 p = null;
		try {
			p = new PrintStream(new File("test16.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss14);

		String ss15[] = {"-team","-all","-n","10"};
		
		 p = null;
		try {
			p = new PrintStream(new File("test17.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss15);
            }
	}

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

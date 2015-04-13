package consoletest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.junit.Test;

import server.businesslogic.BLController;
import server.businesslogic.Player;

public class SortTest {
//	private static String a;
//	private static String b;
//	private static String c;
//	private static String d;
//	private static String e;
//	private static String f;
//	private static String g;
//	private static String h;
//	private static String i;
//	private static String j;
//	private static String k;
//	private static String l;
//	private static String m;
//	
//	String[] args={"point","assist","foul"};
//	ArrayList<Player> players = BLController.getInstance().getPlayers();
//	static final int num=1000;
//	@Test
//	public void testMyComparator(){
//		
//		for(int i=0;i<num;i++){
//			for(int n=0;n<args.length;n++){
//				a=args[0];
//				Collections.sort(players,comparator1);
//			}
//			
//		}
//		
//	}
//	
//	private static ArrayList<Integer> compareTwoObj(Player a1, Player a2){
//		ArrayList<Integer> result = new ArrayList<Integer>();
//		int A1 = 0,A2 = 0;
//        int B1 = 0,B2 = 0;
//        int C1 = 0,C2 = 0;
//        int D1 = 0,D2 = 0;
//        int E1 = 0,E2 = 0;
//        int F1 = 0,F2 = 0;
//        int G1 = 0,G2 = 0;
//        int H1 = 0,H2 = 0;
//        int I1 = 0,I2 = 0;
//        int J1 = 0,J2 = 0;
//        int K1 = 0,K2 = 0;
//        int L1 = 0,L2 = 0;
//        int M1 = 0,M2 = 0;
//        if(a!=null){
//        	switch(a){
//            case "point":
//            	A2=a2.getPoint();
//            	A1=a1.getPoint();
//            	break;
//            case "rebound":
//            	A2=a2.getRebound();
//            	A1=a1.getRebound();
//            	break;
//            case "assist":
//            	A2=a2.getAssist();
//            	A1=a1.getAssist();
//            	break;
//            case "blockShot":
//            	A2=a2.getBlockShot();
//            	A1=a1.getBlockShot();
//            	break;
//            case "steal":
//            	A2=a2.getSteal();
//            	A1=a1.getSteal();
//            	break;
//            case "foul":
//            	A2=a2.getFoul();
//            	A1=a1.getFoul();
//            	break;
//            case "fault":
//            	A2=a2.getFault();
//            	A1=a1.getFault();
//            	break;
//            case "minute":
//            	if(a2.getMinute()-a1.getMinute()>0){
//            		A2=1;
//            		A1=-1;
//            	}else if(a2.getMinute()-a1.getMinute()<0){
//            		A2=-1;
//            		A1=1;
//            	}else{
//            		A2=0;
//            		A1=0;
//            	}
//            	break;
//            case "efficient":
//            	if(a2.getEfficient()-a1.getEfficient()>0){
//            		A2=1;
//            		A1=-1;
//            	}else if(a2.getEfficient()-a1.getEfficient()<0){
//            		A2=-1;
//            		A1=1;
//            	}else{
//            		A2=0;
//            		A1=0;
//            	}
//            	break;
//            case "shot":
//            	if(a2.getShot()-a1.getShot()>0){
//            		A2=1;
//            		A1=-1;
//            	}else if(a2.getShot()-a1.getShot()<0){
//            		A2=-1;
//            		A1=1;
//            	}else{
//            		A2=0;
//            		A1=0;
//            	}
//            	break;
//            case "three":
//            	if(a2.getThree()-a1.getThree()>0){
//            		A2=1;
//            		A1=-1;
//            	}else if(a2.getThree()-a1.getThree()<0){
//            		A2=-1;
//            		A1=1;
//            	}else{
//            		A2=0;
//            		A1=0;
//            	}
//            	break;
//            case "penalty":
//            	if(a2.getPenalty()-a1.getPenalty()>0){
//            		A2=1;
//            		A1=-1;
//            	}else if(a2.getPenalty()-a1.getPenalty()<0){
//            		A2=-1;
//            		A1=1;
//            	}else{
//            		A2=0;
//            		A1=0;
//            	}
//            	break;
//            case "doubleTwo":
//            	if(a2.getDoubleTwo()-a1.getDoubleTwo()>0){
//            		A2=1;
//            		A1=-1;
//            	}else if(a2.getDoubleTwo()-a1.getDoubleTwo()<0){
//            		A2=-1;
//            		A1=1;
//            	}else{
//            		A2=0;
//            		A1=0;
//            	}
//            	break;
//            }
//        }
//        
//        if(b!=null){
//        	switch(b){
//            case "point":
//            	B2=a2.getPoint();
//            	B1=a1.getPoint();
//            	break;
//            case "rebound":
//            	B2=a2.getRebound();
//            	B1=a1.getRebound();
//            	break;
//            case "assist":
//            	B2=a2.getAssist();
//            	B1=a1.getAssist();
//            	break;
//            case "blockShot":
//            	B2=a2.getBlockShot();
//            	B1=a1.getBlockShot();
//            	break;
//            case "steal":
//            	B2=a2.getSteal();
//            	B1=a1.getSteal();
//            	break;
//            case "foul":
//            	B2=a2.getFoul();
//            	B1=a1.getFoul();
//            	break;
//            case "fault":
//            	B2=a2.getFault();
//            	B1=a1.getFault();
//            	break;
//            case "minute":
//            	if(a2.getMinute()-a1.getMinute()>0){
//            		B2=1;
//            		B1=-1;
//            	}else if(a2.getMinute()-a1.getMinute()<0){
//            		B2=-1;
//            		B1=1;
//            	}else{
//            		B2=0;
//            		B1=0;
//            	}
//            	break;
//            case "efficient":
//            	if(a2.getEfficient()-a1.getEfficient()>0){
//            		B2=1;
//            		B1=-1;
//            	}else if(a2.getEfficient()-a1.getEfficient()<0){
//            		B2=-1;
//            		B1=1;
//            	}else{
//            		B2=0;
//            		B1=0;
//            	}
//            	break;
//            case "shot":
//            	if(a2.getShot()-a1.getShot()>0){
//            		B2=1;
//            		B1=-1;
//            	}else if(a2.getShot()-a1.getShot()<0){
//            		B2=-1;
//            		B1=1;
//            	}else{
//            		B2=0;
//            		B1=0;
//            	}
//            	break;
//            case "three":
//            	if(a2.getThree()-a1.getThree()>0){
//            		B2=1;
//            		B1=-1;
//            	}else if(a2.getThree()-a1.getThree()<0){
//            		B2=-1;
//            		B1=1;
//            	}else{
//            		B2=0;
//            		B1=0;
//            	}
//            	break;
//            case "penalty":
//            	if(a2.getPenalty()-a1.getPenalty()>0){
//            		B2=1;
//            		B1=-1;
//            	}else if(a2.getPenalty()-a1.getPenalty()<0){
//            		B2=-1;
//            		B1=1;
//            	}else{
//            		B2=0;
//            		B1=0;
//            	}
//            	break;
//            case "doubleTwo":
//            	if(a2.getDoubleTwo()-a1.getDoubleTwo()>0){
//            		B2=1;
//            		B1=-1;
//            	}else if(a2.getDoubleTwo()-a1.getDoubleTwo()<0){
//            		B2=-1;
//            		B1=1;
//            	}else{
//            		B2=0;
//            		B1=0;
//            	}
//            	break;
//            }
//        }
//        
//        if(c!=null){
//        	switch(c){
//            case "point":
//            	C2=a2.getPoint();
//            	C1=a1.getPoint();
//            	break;
//            case "rebound":
//            	C2=a2.getRebound();
//            	C1=a1.getRebound();
//            	break;
//            case "assist":
//            	C2=a2.getAssist();
//            	C1=a1.getAssist();
//            	break;
//            case "blockShot":
//            	C2=a2.getBlockShot();
//            	C1=a1.getBlockShot();
//            	break;
//            case "steal":
//            	C2=a2.getSteal();
//            	C1=a1.getSteal();
//            	break;
//            case "foul":
//            	C2=a2.getFoul();
//            	C1=a1.getFoul();
//            	break;
//            case "fault":
//            	C2=a2.getFault();
//            	C1=a1.getFault();
//            	break;
//            case "minute":
//            	if(a2.getMinute()-a1.getMinute()>0){
//            		C2=1;
//            		C1=-1;
//            	}else if(a2.getMinute()-a1.getMinute()<0){
//            		C2=-1;
//            		C1=1;
//            	}else{
//            		C2=0;
//            		C1=0;
//            	}
//            	break;
//            case "efficient":
//            	if(a2.getEfficient()-a1.getEfficient()>0){
//            		C2=1;
//            		C1=-1;
//            	}else if(a2.getEfficient()-a1.getEfficient()<0){
//            		C2=-1;
//            		C1=1;
//            	}else{
//            		C2=0;
//            		C1=0;
//            	}
//            	break;
//            case "shot":
//            	if(a2.getShot()-a1.getShot()>0){
//            		C2=1;
//            		C1=-1;
//            	}else if(a2.getShot()-a1.getShot()<0){
//            		C2=-1;
//            		C1=1;
//            	}else{
//            		C2=0;
//            		C1=0;
//            	}
//            	break;
//            case "three":
//            	if(a2.getThree()-a1.getThree()>0){
//            		C2=1;
//            		C1=-1;
//            	}else if(a2.getThree()-a1.getThree()<0){
//            		C2=-1;
//            		C1=1;
//            	}else{
//            		C2=0;
//            		C1=0;
//            	}
//            	break;
//            case "penalty":
//            	if(a2.getPenalty()-a1.getPenalty()>0){
//            		C2=1;
//            		C1=-1;
//            	}else if(a2.getPenalty()-a1.getPenalty()<0){
//            		C2=-1;
//            		C1=1;
//            	}else{
//            		C2=0;
//            		C1=0;
//            	}
//            	break;
//            case "doubleTwo":
//            	if(a2.getDoubleTwo()-a1.getDoubleTwo()>0){
//            		C2=1;
//            		C1=-1;
//            	}else if(a2.getDoubleTwo()-a1.getDoubleTwo()<0){
//            		C2=-1;
//            		C1=1;
//            	}else{
//            		C2=0;
//            		C1=0;
//            	}
//            	break;
//            }
//        }
//        
//        if(e!=null){
//        	switch(e){
//            case "point":
//            	E2=a2.getPoint();
//            	E1=a1.getPoint();
//            	break;
//            case "rebound":
//            	E2=a2.getRebound();
//            	E1=a1.getRebound();
//            	break;
//            case "assist":
//            	E2=a2.getAssist();
//            	E1=a1.getAssist();
//            	break;
//            case "blockShot":
//            	E2=a2.getBlockShot();
//            	E1=a1.getBlockShot();
//            	break;
//            case "steal":
//            	E2=a2.getSteal();
//            	E1=a1.getSteal();
//            	break;
//            case "foul":
//            	E2=a2.getFoul();
//            	E1=a1.getFoul();
//            	break;
//            case "fault":
//            	E2=a2.getFault();
//            	E1=a1.getFault();
//            	break;
//            case "minute":
//            	if(a2.getMinute()-a1.getMinute()>0){
//            		E2=1;
//            		E1=-1;
//            	}else if(a2.getMinute()-a1.getMinute()<0){
//            		E2=-1;
//            		E1=1;
//            	}else{
//            		E2=0;
//            		E1=0;
//            	}
//            	break;
//            case "efficient":
//            	if(a2.getEfficient()-a1.getEfficient()>0){
//            		E2=1;
//            		E1=-1;
//            	}else if(a2.getEfficient()-a1.getEfficient()<0){
//            		E2=-1;
//            		E1=1;
//            	}else{
//            		E2=0;
//            		E1=0;
//            	}
//            	break;
//            case "shot":
//            	if(a2.getShot()-a1.getShot()>0){
//            		E2=1;
//            		E1=-1;
//            	}else if(a2.getShot()-a1.getShot()<0){
//            		E2=-1;
//            		E1=1;
//            	}else{
//            		E2=0;
//            		E1=0;
//            	}
//            	break;
//            case "three":
//            	if(a2.getThree()-a1.getThree()>0){
//            		E2=1;
//            		E1=-1;
//            	}else if(a2.getThree()-a1.getThree()<0){
//            		E2=-1;
//            		E1=1;
//            	}else{
//            		E2=0;
//            		E1=0;
//            	}
//            	break;
//            case "penalty":
//            	if(a2.getPenalty()-a1.getPenalty()>0){
//            		E2=1;
//            		E1=-1;
//            	}else if(a2.getPenalty()-a1.getPenalty()<0){
//            		E2=-1;
//            		E1=1;
//            	}else{
//            		E2=0;
//            		E1=0;
//            	}
//            	break;
//            case "doubleTwo":
//            	if(a2.getDoubleTwo()-a1.getDoubleTwo()>0){
//            		E2=1;
//            		E1=-1;
//            	}else if(a2.getDoubleTwo()-a1.getDoubleTwo()<0){
//            		E2=-1;
//            		E1=1;
//            	}else{
//            		E2=0;
//            		E1=0;
//            	}
//            	break;
//            }
//        }
//        
//        if(f!=null){
//        	switch(f){
//            case "point":
//            	F2=a2.getPoint();
//            	F1=a1.getPoint();
//            	break;
//            case "rebound":
//            	F2=a2.getRebound();
//            	F1=a1.getRebound();
//            	break;
//            case "assist":
//            	F2=a2.getAssist();
//            	F1=a1.getAssist();
//            	break;
//            case "blockShot":
//            	F2=a2.getBlockShot();
//            	F1=a1.getBlockShot();
//            	break;
//            case "steal":
//            	F2=a2.getSteal();
//            	F1=a1.getSteal();
//            	break;
//            case "foul":
//            	F2=a2.getFoul();
//            	F1=a1.getFoul();
//            	break;
//            case "fault":
//            	F2=a2.getFault();
//            	F1=a1.getFault();
//            	break;
//            case "minute":
//            	if(a2.getMinute()-a1.getMinute()>0){
//            		F2=1;
//            		F1=-1;
//            	}else if(a2.getMinute()-a1.getMinute()<0){
//            		F2=-1;
//            		F1=1;
//            	}else{
//            		F2=0;
//            		F1=0;
//            	}
//            	break;
//            case "efficient":
//            	if(a2.getEfficient()-a1.getEfficient()>0){
//            		F2=1;
//            		F1=-1;
//            	}else if(a2.getEfficient()-a1.getEfficient()<0){
//            		F2=-1;
//            		F1=1;
//            	}else{
//            		F2=0;
//            		F1=0;
//            	}
//            	break;
//            case "shot":
//            	if(a2.getShot()-a1.getShot()>0){
//            		F2=1;
//            		F1=-1;
//            	}else if(a2.getShot()-a1.getShot()<0){
//            		F2=-1;
//            		F1=1;
//            	}else{
//            		F2=0;
//            		F1=0;
//            	}
//            	break;
//            case "three":
//            	if(a2.getThree()-a1.getThree()>0){
//            		F2=1;
//            		F1=-1;
//            	}else if(a2.getThree()-a1.getThree()<0){
//            		F2=-1;
//            		F1=1;
//            	}else{
//            		F2=0;
//            		F1=0;
//            	}
//            	break;
//            case "penalty":
//            	if(a2.getPenalty()-a1.getPenalty()>0){
//            		F2=1;
//            		F1=-1;
//            	}else if(a2.getPenalty()-a1.getPenalty()<0){
//            		F2=-1;
//            		F1=1;
//            	}else{
//            		F2=0;
//            		F1=0;
//            	}
//            	break;
//            case "doubleTwo":
//            	if(a2.getDoubleTwo()-a1.getDoubleTwo()>0){
//            		F2=1;
//            		F1=-1;
//            	}else if(a2.getDoubleTwo()-a1.getDoubleTwo()<0){
//            		F2=-1;
//            		F1=1;
//            	}else{
//            		F2=0;
//            		F1=0;
//            	}
//            	break;
//            }
//        }
//        
//        if(d!=null){
//        	switch(d){
//            case "point":
//            	D2=a2.getPoint();
//            	D1=a1.getPoint();
//            	break;
//            case "rebound":
//            	D2=a2.getRebound();
//            	D1=a1.getRebound();
//            	break;
//            case "assist":
//            	D2=a2.getAssist();
//            	D1=a1.getAssist();
//            	break;
//            case "blockShot":
//            	D2=a2.getBlockShot();
//            	D1=a1.getBlockShot();
//            	break;
//            case "steal":
//            	D2=a2.getSteal();
//            	D1=a1.getSteal();
//            	break;
//            case "foul":
//            	D2=a2.getFoul();
//            	D1=a1.getFoul();
//            	break;
//            case "fault":
//            	D2=a2.getFault();
//            	D1=a1.getFault();
//            	break;
//            case "minute":
//            	if(a2.getMinute()-a1.getMinute()>0){
//            		D2=1;
//            		D1=-1;
//            	}else if(a2.getMinute()-a1.getMinute()<0){
//            		D2=-1;
//            		D1=1;
//            	}else{
//            		D2=0;
//            		D1=0;
//            	}
//            	break;
//            case "efficient":
//            	if(a2.getEfficient()-a1.getEfficient()>0){
//            		D2=1;
//            		D1=-1;
//            	}else if(a2.getEfficient()-a1.getEfficient()<0){
//            		D2=-1;
//            		D1=1;
//            	}else{
//            		D2=0;
//            		D1=0;
//            	}
//            	break;
//            case "shot":
//            	if(a2.getShot()-a1.getShot()>0){
//            		D2=1;
//            		D1=-1;
//            	}else if(a2.getShot()-a1.getShot()<0){
//            		D2=-1;
//            		D1=1;
//            	}else{
//            		D2=0;
//            		D1=0;
//            	}
//            	break;
//            case "three":
//            	if(a2.getThree()-a1.getThree()>0){
//            		D2=1;
//            		D1=-1;
//            	}else if(a2.getThree()-a1.getThree()<0){
//            		D2=-1;
//            		D1=1;
//            	}else{
//            		D2=0;
//            		D1=0;
//            	}
//            	break;
//            case "penalty":
//            	if(a2.getPenalty()-a1.getPenalty()>0){
//            		D2=1;
//            		D1=-1;
//            	}else if(a2.getPenalty()-a1.getPenalty()<0){
//            		D2=-1;
//            		D1=1;
//            	}else{
//            		D2=0;
//            		D1=0;
//            	}
//            	break;
//            case "doubleTwo":
//            	if(a2.getDoubleTwo()-a1.getDoubleTwo()>0){
//            		D2=1;
//            		D1=-1;
//            	}else if(a2.getDoubleTwo()-a1.getDoubleTwo()<0){
//            		D2=-1;
//            		D1=1;
//            	}else{
//            		D2=0;
//            		D1=0;
//            	}
//            	break;
//            }
//        }
//        
//        if(g!=null){
//        	switch(g){
//            case "point":
//            	G2=a2.getPoint();
//            	G1=a1.getPoint();
//            	break;
//            case "rebound":
//            	G2=a2.getRebound();
//            	G1=a1.getRebound();
//            	break;
//            case "assist":
//            	G2=a2.getAssist();
//            	G1=a1.getAssist();
//            	break;
//            case "blockShot":
//            	G2=a2.getBlockShot();
//            	G1=a1.getBlockShot();
//            	break;
//            case "steal":
//            	G2=a2.getSteal();
//            	G1=a1.getSteal();
//            	break;
//            case "foul":
//            	G2=a2.getFoul();
//            	G1=a1.getFoul();
//            	break;
//            case "fault":
//            	G2=a2.getFault();
//            	G1=a1.getFault();
//            	break;
//            case "minute":
//            	if(a2.getMinute()-a1.getMinute()>0){
//            		G2=1;
//            		G1=-1;
//            	}else if(a2.getMinute()-a1.getMinute()<0){
//            		G2=-1;
//            		G1=1;
//            	}else{
//            		G2=0;
//            		G1=0;
//            	}
//            	break;
//            case "efficient":
//            	if(a2.getEfficient()-a1.getEfficient()>0){
//            		G2=1;
//            		G1=-1;
//            	}else if(a2.getEfficient()-a1.getEfficient()<0){
//            		G2=-1;
//            		G1=1;
//            	}else{
//            		G2=0;
//            		G1=0;
//            	}
//            	break;
//            case "shot":
//            	if(a2.getShot()-a1.getShot()>0){
//            		G2=1;
//            		G1=-1;
//            	}else if(a2.getShot()-a1.getShot()<0){
//            		G2=-1;
//            		G1=1;
//            	}else{
//            		G2=0;
//            		G1=0;
//            	}
//            	break;
//            case "three":
//            	if(a2.getThree()-a1.getThree()>0){
//            		G2=1;
//            		G1=-1;
//            	}else if(a2.getThree()-a1.getThree()<0){
//            		G2=-1;
//            		G1=1;
//            	}else{
//            		G2=0;
//            		G1=0;
//            	}
//            	break;
//            case "penalty":
//            	if(a2.getPenalty()-a1.getPenalty()>0){
//            		G2=1;
//            		G1=-1;
//            	}else if(a2.getPenalty()-a1.getPenalty()<0){
//            		G2=-1;
//            		G1=1;
//            	}else{
//            		G2=0;
//            		G1=0;
//            	}
//            	break;
//            case "doubleTwo":
//            	if(a2.getDoubleTwo()-a1.getDoubleTwo()>0){
//            		G2=1;
//            		G1=-1;
//            	}else if(a2.getDoubleTwo()-a1.getDoubleTwo()<0){
//            		G2=-1;
//            		G1=1;
//            	}else{
//            		G2=0;
//            		G1=0;
//            	}
//            	break;
//            }
//        }
//        
//        result.add(A1);
//        result.add(A2);
//        result.add(B1);
//        result.add(B2);
//        result.add(C1);
//        result.add(C2);
//        result.add(D1);
//        result.add(D2);
//        result.add(E1);
//        result.add(E2);
//        result.add(F1);
//        result.add(F2);
//        result.add(G1);
//        result.add(G2);
//        result.add(H1);
//        result.add(H2);
//        result.add(I1);
//        result.add(I2);
//        result.add(J1);
//        result.add(J2);
//        result.add(K1);
//        result.add(K2);
//        result.add(L1);
//        result.add(L2);
//        result.add(M1);
//        result.add(M2);
//        return result;
//	}
//	
//	private final static Comparator<Player> comparator1 = new Comparator<Player>(){
//        @Override
//        public int compare(Player a1, Player a2) {
//            int cr = 0;
//            int A1 = 0,A2 = 0;
//            
//            ArrayList<Integer> result = compareTwoObj(a1, a2);
//            A1=result.get(0);
//            A2=result.get(1);
//            
//            int a = A2 - A1;
//            if(a != 0)
//                cr = (a>0)?1:-1;
//            
//            return cr;
//        }       
//    };
//    
//    private final static Comparator<Player> comparator2 = new Comparator<Player>(){
//        @Override
//        public int compare(Player a1, Player a2) {
//            int cr = 0;
//            int A1 = 0,A2 = 0;
//            int B1 = 0,B2 = 0;
//            
//            ArrayList<Integer> result = compareTwoObj(a1, a2);
//            A1=result.get(0);
//            A2=result.get(1);
//            B1=result.get(2);
//            B2=result.get(3);
//            
//            int a = A2 - A1;
//            if(a != 0)
//                cr = (a>0)?1:-1;
//            else{
//                a = B2 - B1;
//                if(a != 0)
//                    cr = (a>0)?1:-1;
//            }
//            return cr;
//        }       
//    };
//	
//	private final static Comparator<Player> comparator4 = new Comparator<Player>(){
//        @Override
//        public int compare(Player a1, Player a2) {
//            int cr = 0;
//            int A1 = 0,A2 = 0;
//            int B1 = 0,B2 = 0;
//            int C1 = 0,C2 = 0;
//            int D1 = 0,D2 = 0;
//            
//            ArrayList<Integer> result = compareTwoObj(a1, a2);
//            A1=result.get(0);
//            A2=result.get(1);
//            B1=result.get(2);
//            B2=result.get(3);
//            C1=result.get(4);
//            C2=result.get(5);
//            D1=result.get(6);
//            D2=result.get(7);
//            
//            int a = A2 - A1;
//            if(a != 0)
//                cr = (a>0)?1:-1;
//            else{
//                a = B2 - B1;
//                if(a != 0)
//                    cr = (a>0)?1:-1;
//                else{
//                    a = C2 - C1;
//                    if(a != 0)
//                        cr = (a>0)?1:-1;
//                    else{
//                        a = D2 - D1;
//                        if(a != 0)
//                            cr = (a>0)?1:-1;
//                    }
//                }
//            }
//            return cr;
//        }       
//    };
//    
//    private final static Comparator<Player> comparator5 = new Comparator<Player>(){
//        @Override
//        public int compare(Player a1, Player a2) {
//            int cr = 0;
//            int A1 = 0,A2 = 0;
//            int B1 = 0,B2 = 0;
//            int C1 = 0,C2 = 0;
//            int D1 = 0,D2 = 0;
//            int E1 = 0,E2 = 0;
//            
//            ArrayList<Integer> result = compareTwoObj(a1, a2);
//            A1=result.get(0);
//            A2=result.get(1);
//            B1=result.get(2);
//            B2=result.get(3);
//            C1=result.get(4);
//            C2=result.get(5);
//            D1=result.get(6);
//            D2=result.get(7);
//            E1=result.get(8);
//            E2=result.get(9);
//            
//            int a = A2 - A1;
//            if(a != 0)
//                cr = (a>0)?1:-1;
//            else{
//                a = B2 - B1;
//                if(a != 0)
//                    cr = (a>0)?1:-1;
//                else{
//                    a = C2 - C1;
//                    if(a != 0)
//                        cr = (a>0)?1:-1;
//                    else{
//                        a = D2 - D1;
//                        if(a != 0)
//                            cr = (a>0)?1:-1;
//                        else{
//                            a = E2 - E1;
//                            if(a != 0)
//                                cr = (a>0)?1:-1;
//                        }
//                    }
//                }
//            }
//            return cr;
//        }       
//    };
//    
//    private final static Comparator<Player> comparator6 = new Comparator<Player>(){
//        @Override
//        public int compare(Player a1, Player a2) {
//            int cr = 0;
//            int A1 = 0,A2 = 0;
//            int B1 = 0,B2 = 0;
//            int C1 = 0,C2 = 0;
//            int D1 = 0,D2 = 0;
//            int E1 = 0,E2 = 0;
//            int F1 = 0,F2 = 0;
//            
//            ArrayList<Integer> result = compareTwoObj(a1, a2);
//            A1=result.get(0);
//            A2=result.get(1);
//            B1=result.get(2);
//            B2=result.get(3);
//            C1=result.get(4);
//            C2=result.get(5);
//            D1=result.get(6);
//            D2=result.get(7);
//            E1=result.get(8);
//            E2=result.get(9);
//            F1=result.get(10);
//            F2=result.get(11);
//            
//            int a = A2 - A1;
//            if(a != 0)
//                cr = (a>0)?1:-1;
//            else{
//                a = B2 - B1;
//                if(a != 0)
//                    cr = (a>0)?1:-1;
//                else{
//                    a = C2 - C1;
//                    if(a != 0)
//                        cr = (a>0)?1:-1;
//                    else{
//                        a = D2 - D1;
//                        if(a != 0)
//                            cr = (a>0)?1:-1;
//                        else{
//                            a = E2 - E1;
//                            if(a != 0)
//                                cr = (a>0)?1:-1;
//                            else{
//                                a = F2 - F1;
//                                if(a != 0)
//                                    cr = (a>0)?1:-1;
//                            }
//                        }
//                    }
//                }
//            }
//            return cr;
//        }       
//    };
//    
//    private final static Comparator<Player> comparator7 = new Comparator<Player>(){
//        @Override
//        public int compare(Player a1, Player a2) {
//            int cr = 0;
//            int A1 = 0,A2 = 0;
//            int B1 = 0,B2 = 0;
//            int C1 = 0,C2 = 0;
//            int D1 = 0,D2 = 0;
//            int E1 = 0,E2 = 0;
//            int F1 = 0,F2 = 0;
//            int G1 = 0,G2 = 0;
//            
//            ArrayList<Integer> result = compareTwoObj(a1, a2);
//            A1=result.get(0);
//            A2=result.get(1);
//            B1=result.get(2);
//            B2=result.get(3);
//            C1=result.get(4);
//            C2=result.get(5);
//            D1=result.get(6);
//            D2=result.get(7);
//            E1=result.get(8);
//            E2=result.get(9);
//            F1=result.get(10);
//            F2=result.get(11);
//            G1=result.get(12);
//            G2=result.get(13);
//            
//            int a = A2 - A1;
//            if(a != 0)
//                cr = (a>0)?1:-1;
//            else{
//                a = B2 - B1;
//                if(a != 0)
//                    cr = (a>0)?1:-1;
//                else{
//                    a = C2 - C1;
//                    if(a != 0)
//                        cr = (a>0)?1:-1;
//                    else{
//                        a = D2 - D1;
//                        if(a != 0)
//                            cr = (a>0)?1:-1;
//                        else{
//                            a = E2 - E1;
//                            if(a != 0)
//                                cr = (a>0)?1:-1;
//                            else{
//                                a = F2 - F1;
//                                if(a != 0)
//                                    cr = (a>0)?1:-1;
//                                else{
//                                    a = G2 - G1;
//                                    if(a != 0)
//                                        cr = (a>0)?1:-1;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            return cr;
//        }       
//    };
//    
//    private final static Comparator<Player> comparator8 = new Comparator<Player>(){
//        @Override
//        public int compare(Player a1, Player a2) {
//            int cr = 0;
//            int A1 = 0,A2 = 0;
//            int B1 = 0,B2 = 0;
//            int C1 = 0,C2 = 0;
//            int D1 = 0,D2 = 0;
//            int E1 = 0,E2 = 0;
//            int F1 = 0,F2 = 0;
//            int G1 = 0,G2 = 0;
//            int H1 = 0,H2 = 0;
//            
//            ArrayList<Integer> result = compareTwoObj(a1, a2);
//            A1=result.get(0);
//            A2=result.get(1);
//            B1=result.get(2);
//            B2=result.get(3);
//            C1=result.get(4);
//            C2=result.get(5);
//            D1=result.get(6);
//            D2=result.get(7);
//            E1=result.get(8);
//            E2=result.get(9);
//            F1=result.get(10);
//            F2=result.get(11);
//            G1=result.get(12);
//            G2=result.get(13);
//            H1=result.get(14);
//            H2=result.get(15);
//            
//            int a = A2 - A1;
//            if(a != 0)
//                cr = (a>0)?1:-1;
//            else{
//                a = B2 - B1;
//                if(a != 0)
//                    cr = (a>0)?1:-1;
//                else{
//                    a = C2 - C1;
//                    if(a != 0)
//                        cr = (a>0)?1:-1;
//                    else{
//                        a = D2 - D1;
//                        if(a != 0)
//                            cr = (a>0)?1:-1;
//                        else{
//                            a = E2 - E1;
//                            if(a != 0)
//                                cr = (a>0)?1:-1;
//                            else{
//                                a = F2 - F1;
//                                if(a != 0)
//                                    cr = (a>0)?1:-1;
//                                else{
//                                    a = G2 - G1;
//                                    if(a != 0)
//                                        cr = (a>0)?1:-1;
//                                    else{
//                                        a = H2 - H1;
//                                        if(a != 0)
//                                            cr = (a>0)?1:-1;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            return cr;
//        }       
//    };
//    
//    private final static Comparator<Player> comparator9 = new Comparator<Player>(){
//        @Override
//        public int compare(Player a1, Player a2) {
//            int cr = 0;
//            int A1 = 0,A2 = 0;
//            int B1 = 0,B2 = 0;
//            int C1 = 0,C2 = 0;
//            int D1 = 0,D2 = 0;
//            int E1 = 0,E2 = 0;
//            int F1 = 0,F2 = 0;
//            int G1 = 0,G2 = 0;
//            int H1 = 0,H2 = 0;
//            int I1 = 0,I2 = 0;
//            
//            ArrayList<Integer> result = compareTwoObj(a1, a2);
//            A1=result.get(0);
//            A2=result.get(1);
//            B1=result.get(2);
//            B2=result.get(3);
//            C1=result.get(4);
//            C2=result.get(5);
//            D1=result.get(6);
//            D2=result.get(7);
//            E1=result.get(8);
//            E2=result.get(9);
//            F1=result.get(10);
//            F2=result.get(11);
//            G1=result.get(12);
//            G2=result.get(13);
//            H1=result.get(14);
//            H2=result.get(15);
//            I1=result.get(16);
//            I2=result.get(17);
//            
//            int a = A2 - A1;
//            if(a != 0)
//                cr = (a>0)?1:-1;
//            else{
//                a = B2 - B1;
//                if(a != 0)
//                    cr = (a>0)?1:-1;
//                else{
//                    a = C2 - C1;
//                    if(a != 0)
//                        cr = (a>0)?1:-1;
//                    else{
//                        a = D2 - D1;
//                        if(a != 0)
//                            cr = (a>0)?1:-1;
//                        else{
//                            a = E2 - E1;
//                            if(a != 0)
//                                cr = (a>0)?1:-1;
//                            else{
//                                a = F2 - F1;
//                                if(a != 0)
//                                    cr = (a>0)?1:-1;
//                                else{
//                                    a = G2 - G1;
//                                    if(a != 0)
//                                        cr = (a>0)?1:-1;
//                                    else{
//                                        a = H2 - H1;
//                                        if(a != 0)
//                                            cr = (a>0)?1:-1;
//                                        else{
//                                            a = I2 - I1;
//                                            if(a != 0)
//                                                cr = (a>0)?1:-1;
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            return cr;
//        }       
//    };
//    
//    private final static Comparator<Player> comparator10 = new Comparator<Player>(){
//        @Override
//        public int compare(Player a1, Player a2) {
//            int cr = 0;
//            int A1 = 0,A2 = 0;
//            int B1 = 0,B2 = 0;
//            int C1 = 0,C2 = 0;
//            int D1 = 0,D2 = 0;
//            int E1 = 0,E2 = 0;
//            int F1 = 0,F2 = 0;
//            int G1 = 0,G2 = 0;
//            int H1 = 0,H2 = 0;
//            int I1 = 0,I2 = 0;
//            int J1 = 0,J2 = 0;
//            
//            ArrayList<Integer> result = compareTwoObj(a1, a2);
//            A1=result.get(0);
//            A2=result.get(1);
//            B1=result.get(2);
//            B2=result.get(3);
//            C1=result.get(4);
//            C2=result.get(5);
//            D1=result.get(6);
//            D2=result.get(7);
//            E1=result.get(8);
//            E2=result.get(9);
//            F1=result.get(10);
//            F2=result.get(11);
//            G1=result.get(12);
//            G2=result.get(13);
//            H1=result.get(14);
//            H2=result.get(15);
//            I1=result.get(16);
//            I2=result.get(17);
//            J1=result.get(18);
//            J2=result.get(19);
//            
//            int a = A2 - A1;
//            if(a != 0)
//                cr = (a>0)?1:-1;
//            else{
//                a = B2 - B1;
//                if(a != 0)
//                    cr = (a>0)?1:-1;
//                else{
//                    a = C2 - C1;
//                    if(a != 0)
//                        cr = (a>0)?1:-1;
//                    else{
//                        a = D2 - D1;
//                        if(a != 0)
//                            cr = (a>0)?1:-1;
//                        else{
//                            a = E2 - E1;
//                            if(a != 0)
//                                cr = (a>0)?1:-1;
//                            else{
//                                a = F2 - F1;
//                                if(a != 0)
//                                    cr = (a>0)?1:-1;
//                                else{
//                                    a = G2 - G1;
//                                    if(a != 0)
//                                        cr = (a>0)?1:-1;
//                                    else{
//                                        a = H2 - H1;
//                                        if(a != 0)
//                                            cr = (a>0)?1:-1;
//                                        else{
//                                            a = I2 - I1;
//                                            if(a != 0)
//                                                cr = (a>0)?1:-1;
//                                            else{
//                                                a = J2 - J1;
//                                                if(a != 0)
//                                                    cr = (a>0)?1:-1;
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            return cr;
//        }       
//    };
//    
//    private final static Comparator<Player> comparator11 = new Comparator<Player>(){
//        @Override
//        public int compare(Player a1, Player a2) {
//            int cr = 0;
//            int A1 = 0,A2 = 0;
//            int B1 = 0,B2 = 0;
//            int C1 = 0,C2 = 0;
//            int D1 = 0,D2 = 0;
//            int E1 = 0,E2 = 0;
//            int F1 = 0,F2 = 0;
//            int G1 = 0,G2 = 0;
//            int H1 = 0,H2 = 0;
//            int I1 = 0,I2 = 0;
//            int J1 = 0,J2 = 0;
//            int K1 = 0,K2 = 0;
//            
//            ArrayList<Integer> result = compareTwoObj(a1, a2);
//            A1=result.get(0);
//            A2=result.get(1);
//            B1=result.get(2);
//            B2=result.get(3);
//            C1=result.get(4);
//            C2=result.get(5);
//            D1=result.get(6);
//            D2=result.get(7);
//            E1=result.get(8);
//            E2=result.get(9);
//            F1=result.get(10);
//            F2=result.get(11);
//            G1=result.get(12);
//            G2=result.get(13);
//            H1=result.get(14);
//            H2=result.get(15);
//            I1=result.get(16);
//            I2=result.get(17);
//            J1=result.get(18);
//            J2=result.get(19);
//            K1=result.get(20);
//            K2=result.get(21);
//            
//            int a = A2 - A1;
//            if(a != 0)
//                cr = (a>0)?1:-1;
//            else{
//                a = B2 - B1;
//                if(a != 0)
//                    cr = (a>0)?1:-1;
//                else{
//                    a = C2 - C1;
//                    if(a != 0)
//                        cr = (a>0)?1:-1;
//                    else{
//                        a = D2 - D1;
//                        if(a != 0)
//                            cr = (a>0)?1:-1;
//                        else{
//                            a = E2 - E1;
//                            if(a != 0)
//                                cr = (a>0)?1:-1;
//                            else{
//                                a = F2 - F1;
//                                if(a != 0)
//                                    cr = (a>0)?1:-1;
//                                else{
//                                    a = G2 - G1;
//                                    if(a != 0)
//                                        cr = (a>0)?1:-1;
//                                    else{
//                                        a = H2 - H1;
//                                        if(a != 0)
//                                            cr = (a>0)?1:-1;
//                                        else{
//                                            a = I2 - I1;
//                                            if(a != 0)
//                                                cr = (a>0)?1:-1;
//                                            else{
//                                                a = J2 - J1;
//                                                if(a != 0)
//                                                    cr = (a>0)?1:-1;
//                                                else{
//                                                    a = K2 - K1;
//                                                    if(a != 0)
//                                                        cr = (a>0)?1:-1;
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            return cr;
//        }       
//    };
//    
//    private final static Comparator<Player> comparator12 = new Comparator<Player>(){
//        @Override
//        public int compare(Player a1, Player a2) {
//            int cr = 0;
//            int A1 = 0,A2 = 0;
//            int B1 = 0,B2 = 0;
//            int C1 = 0,C2 = 0;
//            int D1 = 0,D2 = 0;
//            int E1 = 0,E2 = 0;
//            int F1 = 0,F2 = 0;
//            int G1 = 0,G2 = 0;
//            int H1 = 0,H2 = 0;
//            int I1 = 0,I2 = 0;
//            int J1 = 0,J2 = 0;
//            int K1 = 0,K2 = 0;
//            int L1 = 0,L2 = 0;
//            
//            ArrayList<Integer> result = compareTwoObj(a1, a2);
//            A1=result.get(0);
//            A2=result.get(1);
//            B1=result.get(2);
//            B2=result.get(3);
//            C1=result.get(4);
//            C2=result.get(5);
//            D1=result.get(6);
//            D2=result.get(7);
//            E1=result.get(8);
//            E2=result.get(9);
//            F1=result.get(10);
//            F2=result.get(11);
//            G1=result.get(12);
//            G2=result.get(13);
//            H1=result.get(14);
//            H2=result.get(15);
//            I1=result.get(16);
//            I2=result.get(17);
//            J1=result.get(18);
//            J2=result.get(19);
//            K1=result.get(20);
//            K2=result.get(21);
//            L1=result.get(22);
//            L2=result.get(23);
//            
//            int a = A2 - A1;
//            if(a != 0)
//                cr = (a>0)?1:-1;
//            else{
//                a = B2 - B1;
//                if(a != 0)
//                    cr = (a>0)?1:-1;
//                else{
//                    a = C2 - C1;
//                    if(a != 0)
//                        cr = (a>0)?1:-1;
//                    else{
//                        a = D2 - D1;
//                        if(a != 0)
//                            cr = (a>0)?1:-1;
//                        else{
//                            a = E2 - E1;
//                            if(a != 0)
//                                cr = (a>0)?1:-1;
//                            else{
//                                a = F2 - F1;
//                                if(a != 0)
//                                    cr = (a>0)?1:-1;
//                                else{
//                                    a = G2 - G1;
//                                    if(a != 0)
//                                        cr = (a>0)?1:-1;
//                                    else{
//                                        a = H2 - H1;
//                                        if(a != 0)
//                                            cr = (a>0)?1:-1;
//                                        else{
//                                            a = I2 - I1;
//                                            if(a != 0)
//                                                cr = (a>0)?1:-1;
//                                            else{
//                                                a = J2 - J1;
//                                                if(a != 0)
//                                                    cr = (a>0)?1:-1;
//                                                else{
//                                                    a = K2 - K1;
//                                                    if(a != 0)
//                                                        cr = (a>0)?1:-1;
//                                                    else{
//                                                        a = L2 - L1;
//                                                        if(a != 0)
//                                                            cr = (a>0)?1:-1;
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            return cr;
//        }       
//    };
//    
//    private final static Comparator<Player> comparator13 = new Comparator<Player>(){
//        @Override
//        public int compare(Player a1, Player a2) {
//            int cr = 0;
//            int A1 = 0,A2 = 0;
//            int B1 = 0,B2 = 0;
//            int C1 = 0,C2 = 0;
//            int D1 = 0,D2 = 0;
//            int E1 = 0,E2 = 0;
//            int F1 = 0,F2 = 0;
//            int G1 = 0,G2 = 0;
//            int H1 = 0,H2 = 0;
//            int I1 = 0,I2 = 0;
//            int J1 = 0,J2 = 0;
//            int K1 = 0,K2 = 0;
//            int L1 = 0,L2 = 0;
//            int M1 = 0,M2 = 0;
//            
//            ArrayList<Integer> result = compareTwoObj(a1, a2);
//            A1=result.get(0);
//            A2=result.get(1);
//            B1=result.get(2);
//            B2=result.get(3);
//            C1=result.get(4);
//            C2=result.get(5);
//            D1=result.get(6);
//            D2=result.get(7);
//            E1=result.get(8);
//            E2=result.get(9);
//            F1=result.get(10);
//            F2=result.get(11);
//            G1=result.get(12);
//            G2=result.get(13);
//            H1=result.get(14);
//            H2=result.get(15);
//            I1=result.get(16);
//            I2=result.get(17);
//            J1=result.get(18);
//            J2=result.get(19);
//            K1=result.get(20);
//            K2=result.get(21);
//            L1=result.get(22);
//            L2=result.get(23);
//            M1=result.get(24);
//            M2=result.get(25);
//            
//            int a = A2 - A1;
//            if(a != 0)
//                cr = (a>0)?1:-1;
//            else{
//                a = B2 - B1;
//                if(a != 0)
//                    cr = (a>0)?1:-1;
//                else{
//                    a = C2 - C1;
//                    if(a != 0)
//                        cr = (a>0)?1:-1;
//                    else{
//                        a = D2 - D1;
//                        if(a != 0)
//                            cr = (a>0)?1:-1;
//                        else{
//                            a = E2 - E1;
//                            if(a != 0)
//                                cr = (a>0)?1:-1;
//                            else{
//                                a = F2 - F1;
//                                if(a != 0)
//                                    cr = (a>0)?1:-1;
//                                else{
//                                    a = G2 - G1;
//                                    if(a != 0)
//                                        cr = (a>0)?1:-1;
//                                    else{
//                                        a = H2 - H1;
//                                        if(a != 0)
//                                            cr = (a>0)?1:-1;
//                                        else{
//                                            a = I2 - I1;
//                                            if(a != 0)
//                                                cr = (a>0)?1:-1;
//                                            else{
//                                                a = J2 - J1;
//                                                if(a != 0)
//                                                    cr = (a>0)?1:-1;
//                                                else{
//                                                    a = K2 - K1;
//                                                    if(a != 0)
//                                                        cr = (a>0)?1:-1;
//                                                    else{
//                                                        a = L2 - L1;
//                                                        if(a != 0)
//                                                            cr = (a>0)?1:-1;
//                                                        else{
//                                                            a = M2 - M1;
//                                                            if(a != 0)
//                                                                cr = (a>0)?1:-1;
//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            return cr;
//        }       
//    };
//	
//	
//	private Comparator<Player> comparePoint = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getPoint() > o1.getPoint() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareRebound = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getRebound() > o1.getRebound() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareAssist = new Comparator<Player>() {  
//		
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getAssist() > o1.getAssist() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareBlockShot = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getBlockShot() > o1.getBlockShot() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareSteal = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getSteal() > o1.getSteal() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareFoul = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getFoul() > o1.getFoul() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareFault = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getFault() > o1.getFault() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareMinute = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getMinute() > o1.getMinute() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareEfficient = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getEfficient() > o1.getEfficient() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareShot = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getShot() > o1.getShot() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareThree = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getThree() > o1.getThree() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> comparePenalty = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getPenalty() > o1.getPenalty() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareDoubleTwo = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getDoubleTwo() > o1.getDoubleTwo() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareRealShot = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getRealShot() > o1.getRealShot() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareGmSc = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getGmSc() > o1.getGmSc() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareShotEfficient = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getShotEfficient() > o1.getShotEfficient() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareReboundEfficient = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getReboundEfficient() > o1.getReboundEfficient() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareOffendReboundEfficient = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getOffendReboundEfficient() > o1.getOffendReboundEfficient() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareDefendReboundEfficient = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getDefendReboundEfficient() > o1.getDefendReboundEfficient() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareAssistEfficient = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getAssistEfficient() > o1.getAssistEfficient() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareStealEfficient = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getStealEfficient() > o1.getStealEfficient() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareBlockShotEfficient = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getBlockShotEfficient() > o1.getBlockShotEfficient() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareFaultEfficient = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getFaultEfficient() > o1.getFaultEfficient() ? 1 : -1;
//        }
//    };
//    
//    private Comparator<Player> compareFrequency = new Comparator<Player>() {  
//		  
//        @Override  
//        public int compare(Player o1, Player o2) {  
//            return o2.getFrequency() > o1.getFrequency() ? 1 : -1;
//        }
//    };

}
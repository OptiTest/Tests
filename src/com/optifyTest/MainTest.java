package com.optifyTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class MainTest extends Thread{
	private boolean case1;
	private boolean case2;
	private boolean case3;
	private boolean case4;
	private boolean case5;
	
	public MainTest(boolean select[]){
		int i=0;
		this.case1=select[i];i++;
		this.case2=select[i];i++;
		this.case3=select[i];i++;
		this.case4=select[i];i++;
		this.case5=select[i];i++;
	}
	
    //=========================================================================
	public void run(){
		if(case1)
			testDashBoard();
		
		if(case2)
			testKeywords();
		
		if(case3)
			testPages();		
			
	}
		
    //=========================================================================
	public void testDashBoard(){
		 Result result = JUnitCore.runClasses(DashBoard.class);
 	     for (Failure failure : result.getFailures()) {
 	      System.out.println(failure.toString());
 	    }
	}
	
	//=========================================================================
	public void testKeywords(){
		 Result result = JUnitCore.runClasses(Keywords.class);
 	     for (Failure failure : result.getFailures()) {
 	      System.out.println(failure.toString());
 	    }
	}
	
	//=========================================================================
	public void testPages(){
		 Result result = JUnitCore.runClasses(Pages.class);
 	     for (Failure failure : result.getFailures()) {
 	      System.out.println(failure.toString());
 	    }
	}
	
	//=========================================================================
	public void setCase1(boolean set){
		this.case1=set;
	}
	
	//=========================================================================
	public void setCase2(boolean set){
		this.case2=set;
	}
	
	//=========================================================================
	public void setCase3(boolean set){
		this.case3=set;
	}
}
package com.optifyTest;

import java.io.FileOutputStream;
import java.io.PrintStream;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;


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
		
		if(case4)
			testPageDetail();
		
		if(case5)
			testLinks();
	}
		
    //=========================================================================
	public void testDashBoard(){
		JUnitCore core= new JUnitCore();
		core.addListener(new TraceListener());
		 core.run(DashBoard.class);
 	     //for (Failure failure : result.getFailures()) {
 	    	//writeReport(result);
 	      //System.out.println(failure.toString()+"\n\n");
 	    }
	
	
	//=========================================================================
	public void testKeywords(){
		 Result result = JUnitCore.runClasses(Keywords.class);
 	     for (Failure failure : result.getFailures()) {
 	    	writeReport(result);
 	      //System.out.println(failure.toString()+"\n\n");
 	    }
	}
	
	//=========================================================================
	public void testPages(){
		 Result result = JUnitCore.runClasses(Pages.class);
 	     for (Failure failure : result.getFailures()) {
 	      System.out.println(failure.toString()+"\n\n");
 	    }
	}
	
	//=========================================================================
	public void testPageDetail(){
		 Result result = JUnitCore.runClasses(PageDetail.class);
 	     for (Failure failure : result.getFailures()) {
 	      System.out.println(failure.toString()+"\n\n");
 	    }
	}
	
	//=========================================================================
	public void testLinks(){
		 Result result = JUnitCore.runClasses(Links.class);
 	     for (Failure failure : result.getFailures()) {
 	      System.out.println(failure.toString()+"\n\n");
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
	
	//=========================================================================
	public void setCase4(boolean set){
		this.case4=set;
	}
	
	//=========================================================================
	public void setCase5(boolean set){
		this.case5=set;
	}
	
	//=========================================================================
	private void writeReport(Result result){
		Object[]failure = null;
		failure=result.getFailures().toArray();
		
		for(int i=0;i<failure.length;i++){
			System.out.println(failure[i].toString()+"\n");
		    System.out.println(result.getFailureCount()+"\n");
		    System.out.println(result.getIgnoreCount()+"\n");
		    System.out.println(result.getRunTime()+"\n");
		    System.out.println(result.wasSuccessful()+"\n");
		    System.out.println(result.getClass()+"\n");
		}
	}
	
	//=========================================================================
	public class TraceListener extends RunListener {
	    public void testFailure(Failure failure) throws java.lang.Exception {
	    	PrintStream oldoutps = System.out; //get the current output //stream

	    	try {FileOutputStream outfos = new FileOutputStream("data\\Test.html"); //create new //output stream
	    		 PrintStream newoutps = new PrintStream(outfos); //create new output stream
	    		 System.setOut(newoutps); //set the output stream
	    		 System.out.print("<html><head><title>HTML Online Editor Sample</title>"+
	                              "</head><body><table border='1' cellpadding='1' cellspacing='1' dir='ltr' style='width: 477px; height: 107px;'>"+
	    				          "<tbody><tr><td><span style='color: rgb(255, 255, 255);'><span style='background-color: rgb(173, 216, 230);'>Optify automatio suit report</span></span></td>" +
	    				          "<td><span style='color: rgb(255, 255, 255);'><span style='background-color: rgb(173, 216, 230);'>&nbsp;</span></span></td>"+
	    				          "</tr><tr><td>&nbsp;</td><td>"+failure.getTrace().toString()+"</td></tr><tr><td></td><td></td></tr><tr><td>&nbsp;</td><td>"+
	    				          "</td></tr></tbody></table></body></html>");
	    		 System.setOut(oldoutps); //for resetting the output stream
	    	} 
	    	catch (Exception e) {
	    		System.out.println("some error");
	    	}
	    	
	    	System.out.println("Test Header:");
	    	System.out.println(failure.getTestHeader().toString()+"\n\n\n");
	    	System.out.println("Get Message:");
	    	System.out.println(failure.getMessage().toString()+"\n\n\n");
	    	System.out.println("Get Class:");
	    	System.out.println(failure.getClass().toString()+"\n\n\n");
	    	System.out.println("Get discription:");
	    	System.out.println(failure.getDescription().toString()+"\n\n\n");
	    	System.out.println("Get excepion:");
	    	System.out.println(failure.getException().toString()+"\n\n\n");
	    	System.out.println("Get trace:");
	    	System.out.println(failure.getTrace().toString()+"\n");
	    	
	    }
	 }
}


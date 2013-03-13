package com.optifyTest;

import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Scanner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;


public class MainTest extends Thread{
	//Main test attributes:
	private Report report; //Report 
	public String []headInformation; //Headline report information.
	private String setReportSavePath;
	private String serverPath;
	private PrintStream oldoutps;
	private boolean select[]; //Running test flag.
	private Settings set;
	
	//Main test constructor:
	public MainTest(boolean select[]){
		final int SIZE=10;//Headline report information array size.
		int i=0;
		this.set=new Settings();
		this.headInformation=new String[SIZE];
		this.select=select;

		this.serverPath=set.getServerUrl();
		this.oldoutps = System.out;
		this.setReportSavePath="data/";
	}
	
    //=========================================================================
	public void run(){
		String arrList[]=getTestList();
		final int SIZE=arrList.length;
		 
		JUnitCore core= new JUnitCore();
		core.addListener(new TraceListener());
		
        Result result[]=new Result[SIZE];
		
        while(true){
        	setHeadInfo(serverPath);
        	this.report=new Report(this.setReportSavePath,this.headInformation,this.oldoutps);
        	
        	for(int i=0;i<SIZE;i++){
        		if(this.select[i]){
	        		result[i]=null;
	        		
	        		//Kill webDriver process
	        		cleanTest();
	        		
	        		
	        		 try {result[i]=core.run(Class.forName("com.optifyTest."+arrList[i]));
	        		} catch (ClassNotFoundException e) {
	        			System.out.println("Test class "+arrList[i]+" not found!");
	        		}

        		}
        	}
        	
        	//Create report.
        	writeReport(result);
        }
	}
	
	//=========================================================================
	private void writeReport(Result result[]){
		int numOfTest=0;
		int numOfFailure=0;
		double timeInSec=0;
		
		final int SIZE=result.length;
		
		for(int i=0;i<SIZE;i++){
			if(result[i]!=null){
				numOfTest+=result[i].getRunCount();
				numOfFailure+=result[i].getFailureCount();
				timeInSec+=result[i].getRunTime();
			}
		}
		
		int numOfSuccess=(numOfTest-numOfFailure);
		
		if(numOfTest!=0){
			double rate=((double)numOfSuccess/(double)numOfTest);
			this.headInformation[6]=Integer.toString((int)(rate*100))+"%";
		}
		else{this.headInformation[6]="N/A";
		}
		
		this.headInformation[4]=Integer.toString(numOfTest);
		this.headInformation[5]=Integer.toString(numOfFailure);
		
		//Set time results:
		timeInSec/=3600;
		int hour=(int)(timeInSec/3600);
		int min=(int)(timeInSec/60);
		int sec=(int)(timeInSec%60);
		
		this.headInformation[7]=hour+":"+min+":"+sec;
		//Generate the report:
		report.createReport();
		System.setOut(this.oldoutps); 
		
	}
	
	//=========================================================================
	public class TraceListener extends RunListener {
	    public void testFailure(Failure failure) throws java.lang.Exception {
	    	PrintStream oldoutps = System.out; //get the current output //stream
	    	
	    	report.addFailure(failure);
	    	System.setOut(oldoutps); 
	    }
	 }
	
	//Generate today date======================================================
	private String getTodayDate(){
		Calendar todayDate=Calendar.getInstance();
		
		return Integer.toString(todayDate.get(Calendar.MONTH)+1)+"/"+
			   Integer.toString(todayDate.get(Calendar.DAY_OF_MONTH))+"/"+
			   Integer.toString(todayDate.get(Calendar.YEAR));
	}
	
	//Generate correct time======================================================
	private String getTime(){
		String min,hour="";
		Calendar todayDate=Calendar.getInstance();
		
		if(todayDate.get(Calendar.MINUTE)<10)
			min="0"+Integer.toString(todayDate.get(Calendar.MINUTE));
		else
			min=Integer.toString(todayDate.get(Calendar.MINUTE));
					
		if(todayDate.get(Calendar.HOUR_OF_DAY)<10)
			hour="0"+Integer.toString(todayDate.get(Calendar.HOUR_OF_DAY));
		else
			hour=Integer.toString(todayDate.get(Calendar.HOUR_OF_DAY));
		
		return hour+":"+min;
	}
	//=========================================================================
	private void setHeadInfo(String serverPath){
		int i=0;
		
		//Fill Head line report information:
		this.headInformation[i]=serverPath;i++;
		this.headInformation[i]=getTodayDate();i++;
		this.headInformation[i]=getTime();i++;
		this.headInformation[i]="Google Chrome 23.0m";i++;
		this.headInformation[i]="0";i++;
		this.headInformation[i]="0";i++;
		this.headInformation[i]="0";i++;
		this.headInformation[i]="0";i++;
		this.headInformation[i]="N/A";i++;
		this.headInformation[i]="N/A";i++;
	}
	
	//Clean Finished test======================================================
	private void cleanTest(){
		 try {Runtime.getRuntime().exec("killall chromedriver");
		  } 
		  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }
	}
	
	//Load test least==========================================================
	private String[] getTestList(){
		String arrList[]={"DashBoard","Keywords","Pages","PageDetail","Links","LeadIntelligence","TwitterForBusiness"};
		
		return arrList;
	}
}


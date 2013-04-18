package com.optifyTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;
import javax.swing.tree.DefaultMutableTreeNode;
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
	private Settings set;
	private DefaultMutableTreeNode root;
	
	//Main test constructor:
	public MainTest(DefaultMutableTreeNode root){
		final int SIZE=10;//Headline report information array size.
		this.set=new Settings();
		this.headInformation=new String[SIZE];
		this.root=root;
		this.serverPath=set.getServerUrl();
		this.oldoutps = System.out;
		this.setReportSavePath="data/";
	}
	
    //=========================================================================
	public void run(){
		final int SIZE=this.root.getChildCount();
		DefaultMutableTreeNode value;
		JUnitCore core= new JUnitCore();
		core.addListener(new TraceListener());
        Result result[]=new Result[SIZE];
		
        while(true){
        	 DefaultMutableTreeNode p=this.root;
        	 value = ((DefaultMutableTreeNode) p.getFirstChild());
        	 
        	 for(int i=0;i<SIZE;i++){   
        		 Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
        		            
        		 if (userObject instanceof CheckBoxNode) {
        		        CheckBoxNode node = (CheckBoxNode) userObject;
    		     
    		            if(node.selected || checkIfScriptTrue((DefaultMutableTreeNode) value)){ 
	    		         	setHeadInfo(this.serverPath);
	    		     	    this.report=new Report(this.setReportSavePath,this.headInformation,this.oldoutps);
	    		            	 
	    		     	    //Kill webDriver process
	    			        cleanTest();
	    			        		
			        		try {result[i]=core.run(Class.forName("com.optifyTest."+node.text));
			        		} catch (ClassNotFoundException e) {
			        			System.out.println("Test class "+node.text+" not found!");
			        		}
    		            }
	            }
        		 
        		 value = ((DefaultMutableTreeNode) value.getNextSibling());
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
	
	//=========================================================================
	private boolean checkIfScriptTrue(DefaultMutableTreeNode value){
		boolean flag=false; //Set if exist script to run.
		File file=new File("data/data3");
		FileWriter fstream;
		final int SIZE=value.getChildCount();
		DefaultMutableTreeNode child=(DefaultMutableTreeNode)value.getFirstChild();
		
		 try { fstream = new FileWriter(file,false);
	      	   BufferedWriter out = new BufferedWriter(fstream);
	      	   
	      	   for(int i=0;i<SIZE;i++){
		      		 Object userObject = ((DefaultMutableTreeNode) child)
		    		            .getUserObject();  
	      		   if (userObject instanceof CheckBoxNode) {
				             CheckBoxNode node = (CheckBoxNode) userObject;
				             if(node.selected){
				            	 flag=true;
			  
				                 //Write to file
						    	 out.write(node.text.toString()+"\n");
				             }
				      }
	      		   
	      		   child=(DefaultMutableTreeNode)child.getNextLeaf();
			   }
		     out.close();
		 
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
		            		 
		return flag;
			   
	}	 
}


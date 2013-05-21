/*
 * This project was written by Asnin Or to the Optify Company 2013.
 * Parts of the OATS system.
 * The MainTest class.
 * ============================================================================
 * 
 * This class gathering all tests information in order to filter with test 
 * or test script will run and which not. The main idea of understanding this 
 * issue by using a Jtree that pass to this class from the MainMenu class by 
 * reference. The Jtree builds from Check boxes nodes, which each check box 
 * responsible a bout test or script.
 * 
 * In addition this class responsible for gathering all reporting information 
 * for the report class in order to create it (failures info, time generating,
 * scripts etc).
 * 
 * 
 * MainTest methods:
 * =================
 * 
 * 1. MainTest(DefaultMutableTreeNode root):
 * 
 *    The MainTest constructor get the JTree root, as explained before.
 *    and initialize all class attributes.
 * 
 * 
 * 2. run():
 *    
 *    This is the operational method responsible for all running test.
 *    The method check for with test/script as the privilege to run according
 *    to the JTree that explained before.
 *    For now this method will run in endless loop. 
 * 
 * 
 * 3. writeReport(Result result[]):
 * 
 * 	  This method responsible of collecting the test report head information:
 *    time, number of test executed, number of tests failed, rate etc.
 *    
 * 
 * 4. class TraceListener:
 *    
 *    Responsible to "Listen" test executed and record all failures.
 *    
 *  
 * 5. getTodayDate():
 * 
 *    Setting the system clock info into a visible date format.
 *    
 * 
 * 6. getTime():
 * 
 *    Setting the system clock info into a visible time format.
 *    
 * 
 * 7. setHeadInfo(String serverPath)
 * 
 *    Setting the main head report information.
 *    
 *    
 * 8. cleanTest():
 * 
 *    Using the system run time in order to clean all test process.
 *    
 *    
 * 9. checkIfScriptTrue(DefaultMutableTreeNode value):
 * 
 *    This method receive JTree node of page test, in order to check if there
 *    is any run privilege on this specific test file, those scripts will write
 *    into data file and "will sent" into the test executed in order to "tell"
 *    to the page test executed which script will run.
 *    
 *    this method will return a TRUE only if at least one of the scripts has 
 *    a running privilege.
 */

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
	private ManageFailure mFailures;
	public  String []headInformation; //Headline report information.
	private String setReportSavePath;
	private String serverPath;
	private PrintStream oldoutps;
	private Settings set;
	private DefaultMutableTreeNode root;
	
	//Main test constructor:
	public MainTest(DefaultMutableTreeNode root){
		final int SIZE=10;                     //Headline report information array size.
		this.set=new Settings();               //Get the settings info.
		this.headInformation=new String[SIZE]; //The headline info array.
		this.root=root;                        //The JTree root. 
		this.serverPath=set.getServerUrl();    //Get the server path information.
		this.oldoutps = System.out;            //Save the old print out stream.
		this.setReportSavePath="data/";        //The report save path.
		this.report=null;                      //Initialization the report pointer to null.
		this.mFailures=null;
	}
	
    //=========================================================================
	public void run(){
		final int SIZE=this.root.getChildCount(); //Get the number of test pages.
		DefaultMutableTreeNode value;             
		JUnitCore core= new JUnitCore();          //Create JUnitCore object in
		                                          //order to run the tests.
		core.addListener(new TraceListener());    //Add the failure test listener.
        Result result[]=new Result[SIZE+1];         //Create the results array.
		
        
        while(true){
        	 this.mFailures=new ManageFailure(this.setReportSavePath,this.oldoutps);
        	 
        	 this.report=new Report(this.setReportSavePath,this.headInformation,this.oldoutps,this.mFailures);
        	 
        	 DefaultMutableTreeNode p=this.root;
        	 value = ((DefaultMutableTreeNode) p.getFirstChild());
        	 setHeadInfo(this.serverPath);
        	 
        	 //Run server alive test.
     		 try {result[SIZE]=core.run(Class.forName("com.optifyTest.LogIn"));
     	 	 } catch (ClassNotFoundException e) {
     			System.out.println("Couldn't run the server alive test");
     		 }
        	  
        	 for(int i=0;i<SIZE;i++){   
        		 Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
        		            
        		 if (userObject instanceof CheckBoxNode) {
        		        CheckBoxNode node = (CheckBoxNode) userObject;
    		     
        		        //Check if one of the scripts true.
    		            if(checkIfScriptTrue((DefaultMutableTreeNode) value)){ 
    		            	//Kill webDriver process
	    			        cleanTest();
	    			        
	    			        //Run the test.
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
        	
        	this.mFailures.saveToLog();
        }
	}
	
	//=========================================================================
	private void writeReport(Result result[]){
		int numOfTest=0;          //Save the sum of script test number as executable.
		int numOfFailure=0;       //Save the sum failure number detected.
		double timeInSec=0;       //Save the sum of run time in seconds.
		
		final int SIZE=result.length;
		
		for(int i=0;i<SIZE;i++){
			if(result[i]!=null){
				numOfTest+=result[i].getRunCount();
				numOfFailure+=result[i].getFailureCount();
				timeInSec+=result[i].getRunTime();
			}
		}
		
		int numOfSuccess=(numOfTest-numOfFailure);  //Save the number of succeed
		                                            //test out of all executed.
		
		if(numOfTest>=numOfSuccess){ //If number of test executed equal 0. don't check success rate.
			double rate=((double)numOfSuccess/(double)numOfTest);
			this.headInformation[6]=Integer.toString((int)(rate*100))+"%";
		}
		else{this.headInformation[6]="N/A";
		}
		
		this.headInformation[4]=Integer.toString(numOfTest);
		this.headInformation[5]=Integer.toString(mFailures.getNewFailureList().size());
		
		//Set time results:
		timeInSec/=3600;
		int hour=(int)(timeInSec/3600);
		int min=(int)((timeInSec/60)%60);
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
	    	
	    	mFailures.addNewFailure(failure);
	    	//report.addFailure(failure);
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
		boolean flag=false;                   //Set if at least exist on script with privilege to run.
		File file=new File("data/data3");     //Destination writing to file.
		FileWriter fstream;
		final int SIZE=value.getChildCount(); //Number of scripts file exist in test file.
		
		//Get the test scripts nodes of the test page.
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


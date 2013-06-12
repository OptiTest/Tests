/*
 * This project was written by Asnin Or to the Optify Company 2013.
 * Parts of the OATS system.
 * 
 * The ManageFailure class.
 * ============================================================================
 * 
 * This class manage all Optify failures: new, old, dates, number of repetitions 
 * Can use by reports to display table of knowing issues, new issues, display graph
 * information about how much those old failures repeat. In addition can manage by
 * deleting those old (knowing) failures from the log file. 
 * 
 * 
 * ManageFailure methods:
 * =================
 * 
 * 1. MnageFailureManageFailure(String savePath,String []headInformation,PrintStream oldoutps):
 */

package com.optifyTest;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

import org.junit.runner.notification.Failure;


public class ManageFailure {
	private LinkedList <NodeFailure> oldFailureList;  //Store the list of old failures.
	private LinkedList <NodeFailure> newFailureList;  //Store the list of new failures.
	private PrintStream oldoutps;                     //Store the original printing stream.
	private String savePath;                           //The log save path.
	private double reportTime;                        //Working with massive failure to alert every 30 minutes.
	
	public ManageFailure(String savePath,PrintStream oldoutps,double time){
		this.oldFailureList=new LinkedList<NodeFailure>();
		this.newFailureList=new LinkedList<NodeFailure>();
		this.oldoutps=oldoutps;
		this.savePath=savePath;
		this.reportTime=time;
		loadOldList();
	}
	
	//ManageFailure methods:===================================================
	public LinkedList<NodeFailure> getOldFailureList(){
		return this.oldFailureList;
	}
	
	//=========================================================================
	public LinkedList<NodeFailure> getNewFailureList(){
		return this.newFailureList;
	}
	
	//=========================================================================
	private void loadOldList(){
		String line="",buffer="";
		
		String arr[];
		
		try{FileReader fstreamReader = new FileReader("data/log");
			BufferedReader reader=new BufferedReader(fstreamReader);
			
			reader.readLine();
			line = reader.readLine();

			while(line!=null){
				line+=reader.readLine();
				
				while(line!=null){
					if(line.equals(""))
						break;
					
					buffer+=line+"\n";
					line = reader.readLine();
				}
					
			    arr=buffer.split(",,");
			    
			    oldFailureList.add(createNodeFailure(arr));
			    
				line = reader.readLine();
				buffer="";
			}
			
			reader.close();
	
		}catch (IOException e){
			PrintStream newoutps = new PrintStream(this.oldoutps);
			System.setOut(newoutps); //set the output stream
			System.out.println("Error occurred while trying reading log file");
		}
	}
	
	//=========================================================================
	public void addNewFailure(Failure failure){
		NodeFailure p=failureExist(failure);
		
		if(p!=null)
			this.newFailureList.add(p);
	}
	
    //Parsing object name=============================================
  	private String parseObjectName(Failure failure){
  		String getName=failure.getTestHeader();
  		final int SIZE=getName.length();
  		int i;
  		
  		for(i=0;i<SIZE;i++)
  			if(getName.substring(i,i+1).equals("("))
  				break;
  		
  		return getName.substring(0,i);
  	}
  	
  	//Parsing object name======================================================
  	private String parsePageName(Failure failure){
  		String getName=failure.getTestHeader();
  		final int SIZE=getName.length();
  		int i,j=0;
  		
  		for(i=0;i<SIZE;i++){
  			if(getName.substring(i,i+1).equals("("))
  				j=i+1;
  			
  			if(getName.substring(i,i+1).equals(")"))
  				break;
  		}
  		
  		return getName.substring(j,i);
  	}
  	
  	//Parsing failure description==============================================
  	private String getDescription(String failure){
  		String arr[]=failure.split("@");
  		arr=arr[0].split("\n");
  		
  		return arr[0];
  	}
  	
  	
    //Read the last action performed===========================================
  	private String readFromActionStream(){
  		String line="N/A";
  		
  		try{FileReader fstreamReader=new FileReader("data/actionStram");
  			BufferedReader out=new BufferedReader(fstreamReader);
  			line = out.readLine();
  			
  			out.close();
  			
  		}catch (IOException e){
  			System.out.println("Error occurred while trying reading from action stream");
  		}
  		
  		return line;
  	}
  	
  	//Create new failure node using arr============================================
  	private NodeFailure createNodeFailure(String arr[]){
  		return new NodeFailure(arr);
  	}
  	
    //Create new failure node using Failure==================================================
  	private NodeFailure createNodeFailure(Failure failure){
  		String pageName=parsePageName(failure);
		String objectName=parseObjectName(failure);
		String actionPerformed=readFromActionStream();
		
  		return new NodeFailure(pageName,objectName,
                actionPerformed, 
                failure.getMessage(),failure.getTrace());
  	}
  	
  	//Check if failure exist===================================================
  	private NodeFailure failureExist(Failure failure){
  		for(NodeFailure i:this.oldFailureList){
  			
  			if(i.returnActionPerformed().equals(readFromActionStream())&&
  			   i.returnPageName().equals(parsePageName(failure))&&
  			   i.returnObjectName().equals(parseObjectName(failure))&&
  			   failure.toString().contains(i.returnErrorDescription())){
  				
  				i.setNum(i.getNum()+1);
  				i.setLastDate();
  				
  				return null;
  			}
  		}
  
  		return createNodeFailure(failure);		
  	}
  	
  	//Save failures list=======================================================
  	//This method will save all knowing failures (old list) into the log file
  	//including the new failure list.
  	public void saveToLog(){
  		FileOutputStream outfos=null;
  		PrintStream newoutps=null;

  		//If least empty don't do nothing.
  		//if(this.newFailureList.isEmpty())
  		//	return;
  		
  		try{outfos = new FileOutputStream(this.savePath+"log"); //create new output stream
  			newoutps = new PrintStream(outfos);
  			System.setOut(newoutps); 				  //set the output stream
  			
  			//Save system today date:
  			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
  			System.out.println(sdf.format(Calendar.getInstance().getTime()));
  			
  			//Save all new failures list.
  			for(NodeFailure i:this.newFailureList){
  				
  				//Don't save server fault as old failure.
  				if(i.returnErrorDescription().equals("Can't log to server!"))
  					continue;
  				
  				System.out.print(i.returnPageName()+",,"+i.returnObjectName()+",,"+
  						         i.returnActionPerformed()+",,"+getDescription(i.returnErrorDescription())+",,"+
  						         i.returnTrace()+",,"+i.getNum()+",,");
  				
  			   //Save all dates:
  				String dateArr[]=i.getDates();
  				
  				for(int j=0;j<dateArr.length;j++){
					if(dateArr[j].equals("\n"))
						break;
  					
  					System.out.print(dateArr[j]+"::");
  				}
					System.out.print("\n\n");
  			}
		
  		    //Save all knowing (old) failures list.
  			for(NodeFailure i:this.oldFailureList){
  				System.out.print(i.returnPageName()+",,"+i.returnObjectName()+",,"+
  						         i.returnActionPerformed()+",,"+i.returnErrorDescription()+",,"+
  						         i.returnTrace()+",,"+i.getNum()+",,");
  				
  				//Save all dates:
  				String dateArr[]=i.getDates();
  				
  				for(int j=0;j<dateArr.length;j++){
					if(dateArr[j].equals("\n"))
						break;
  					
  					System.out.print(dateArr[j]+"::");
  				}
					System.out.print("\n\n");
  			}
  			
  			System.setOut(this.oldoutps); //set the output stream
  			newoutps.close();
  			outfos.close();
		}
		catch (Exception e) {
			newoutps.close();
  			try {
				outfos.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.setOut(this.oldoutps); //set the output stream
			System.out.println("Failed while trying writing log");	
		}
  		
  	}
  	
  	//Check for new failures exist:============================================
  	public boolean checkIfNewFailureExist(){
  		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
  		
  		if(checkForMassiveFailure())
  			return true;
  		
  		if((this.newFailureList.isEmpty() && sdf.format(Calendar.getInstance().getTime()).equals(getReportDate())))		                       
  			return false;
  		
  		else
  			updateReportDate();

  		return true;
  	}
  	
  	//Check for massive failure and report every 30 minutes:===================
  	private boolean checkForMassiveFailure(){
  		final int SET_REPORT_TIME=30;
  		
  		if(Thread.currentThread().isInterrupted())
  			return false;
  		
  		if(!this.newFailureList.isEmpty())
	  		for(NodeFailure i:this.newFailureList){
	  			double current=System.currentTimeMillis()/1000/60;
	  			double sum=current-this.reportTime;
	  
	  			
	  			if(i.returnErrorDescription()!=null&&i.returnErrorDescription().equals("Can't log to server!")&&
	  			          (sum>SET_REPORT_TIME)){
	  				//update report time:
	  				this.reportTime=current;
	  				return true;
	  			}	
	  		}
	  		
  		return false;
  	}
  	
  	//Return the updated report time difference:=========================================
  	public double getReportTimeDifference(){
  		return this.reportTime;
  	}
  	
  	//Get report date:===================================================================
  	private String getReportDate(){
  		try{FileReader fstreamReader = new FileReader("data/log");
			BufferedReader reader=new BufferedReader(fstreamReader);
			String line="";
			
			line = reader.readLine();
			reader.close();
			return line;
		
		}catch (IOException e){
			PrintStream newoutps = new PrintStream(this.oldoutps);
			System.setOut(newoutps); //set the output stream
			System.out.println("Error occurred while trying reading log file");
			return null;
		}
  	}
  	
  	//=========================================================================
  	private void updateReportDate(){
  		FileOutputStream outfos=null;
  		PrintStream newoutps=null;
  		
  		try{outfos = new FileOutputStream(this.savePath+"log"); //create new output stream
  			newoutps = new PrintStream(outfos);
  			System.setOut(newoutps); 				  //set the output stream
  			
  			//Save system today date:
  			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
  			System.out.println(sdf.format(Calendar.getInstance().getTime()));
  			
  		    //Save all new failures list.
  			for(NodeFailure i:this.newFailureList){
  				
				//Don't save server fault as old failure.
				if(i.returnErrorDescription().equals("Can't log to server!"))
					continue;
			
				System.out.print(i.returnPageName()+",,"+i.returnObjectName()+",,"+
	  						         i.returnActionPerformed()+",,"+getDescription(i.returnErrorDescription())+",,"+
	  						         i.returnTrace()+",,"+i.getNum()+",,");
  			
				//Save all dates:
  				String dateArr[]=i.getDates();
  				
  				for(int j=0;j<dateArr.length;j++){
					if(dateArr[j].equals("\n"))
						break;
  					
  					System.out.print(dateArr[j]+"::");
  				}
					System.out.print("\n\n");
			}
  			
  		    //Save all knowing (old) failures list.
  			for(NodeFailure i:this.oldFailureList){
  				System.out.print(i.returnPageName()+",,"+i.returnObjectName()+",,"+
  						         i.returnActionPerformed()+",,"+i.returnErrorDescription()+",,"+
  						         i.returnTrace()+",,"+i.getNum()+",,");
  				
  				//Save all dates:
  				String dateArr[]=i.getDates();
  				
  				for(int j=0;j<dateArr.length;j++){
					if(dateArr[j].equals("\n"))
						break;
  					
  					System.out.print(dateArr[j]+"::");
  				}
					System.out.print("\n\n");
  			}
  			
  			
  			System.setOut(this.oldoutps); //set the output stream
  			newoutps.close();
  			outfos.close();
		}
		catch (Exception e) {
			newoutps.close();
  			try {
				outfos.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.setOut(this.oldoutps); //set the output stream
			System.out.println("Failed while trying writing log");	
		}
  	}
}

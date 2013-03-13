package com.optifyTest;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Console;
import java.util.Scanner;

import org.junit.runner.notification.Failure;

public class Report {
	
	//Report class attributes:
	private String savePath;
	private String []headInformation;  //Vector of headline information (System tested, head etc).
	private ListFailure listFailure;   //Vector of failure information (Page name, Objects etc).
	private String report;             //Include the copy of the generated report.
	private EMail email;               //Using email object to send the report throw
	private PrintStream oldoutps;
	private String action;
	
	//Report class constructor:
	Report(String savePath,String []headInformation,PrintStream oldoutps){
		this.savePath=savePath;
		this.listFailure=new ListFailure();
		this.headInformation=headInformation;
		this.report="";
		this.oldoutps=oldoutps;
		this.action="n/a";
	}
	
	//Report class methods:
	
	//Creat report====================================================
	public void createReport(){
		try{FileOutputStream outfos = new FileOutputStream(savePath+generateFileName()); //create new //output stream
			PrintStream newoutps = new PrintStream(outfos);
			System.setOut(newoutps); //set the output stream
			this.report=("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>\n"+
		                     "<html xmlns='http://www.w3.org/1999/xhtml'>\n"+
		                     "<head>\n"+
		                     "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />\n"+
		                     "<title>Optify Automation Report</title>\n"+
							 "</head>\n"+
							 "<body>\n\n"+
							 "<table width='873' height='328' border='0'>\n"+
							 "<tr>\n"+
							 "<th height='68' colspan='6' bgcolor='#7AC8F1' scope='col'><h1 align='left' class='Head_line'>    <em> &nbsp;  &nbsp;  Optify Automation Test Report </em></h1></th>\n"+
							 "</tr>\n"+
							 "<tr>\n"+
							 "<th height='21' colspan='6' bgcolor='#CCCCCC' class='Summary' style='font-size: 14px; font-style: italic;' scope='col'><div align='left'>Tested by Optify Automation Suite ver2.0 </th>\n"+
							 "</tr>\n"+
							 "<tr>\n"+
							 "  <td height='38' colspan='6' bgcolor='#FFFFFF' class='summary'>&nbsp;</td>\n"+
							 "</tr>\n"+
							 "<tr>\n"+
							 "<td height='31' colspan='2' bgcolor='#7AC8F1' class='summary'><strong><span class='summary_headline'>System tested</span></td>\n"+
							 "<td height='31' bgcolor='#7AC8F1' class='summary'><strong><span class='summary_headline'>Today date</span></td>\n"+
							 "<td height='31' bgcolor='#7AC8F1' class='summary'><strong><span class='summary_headline'>Generate time</td>\n"+
							 "<td height='31' colspan='2' bgcolor='#7AC8F1' class='summary'><strong><span class='summary_headline'>Browser</span></td>\n"+
							 "</tr>\n"+
							 "<tr>\n"+
							 "	<td height='24' colspan='2' bgcolor='#CCCCCC' class='summary'><span class='summary_headline' style='font-weight: normal'>"+this.headInformation[0]+"</span></td>\n"+
							 "	<td height='24' bgcolor='#CCCCCC' class='summary_headline' style='font-weight: normal'>"+this.headInformation[1]+"</td>\n"+
							 "	<td height='24' bgcolor='#CCCCCC' class='summary_headline' style='font-weight: normal'>"+this.headInformation[2]+"</td>\n"+
							 "	<td height='24' colspan='2' bgcolor='#CCCCCC' class='summary'><span class='summary_headline' style='font-weight: normal'>"+this.headInformation[3]+"</span></td>\n"+
							 "</tr>\n"+
							 "<tr>\n"+
							 "<td height='46' colspan='5' class='summary' style='font-size: 20px' align='left' scope='col'><p>Summary</p></th>\n"+
							 "</tr>\n"+
							 "<tr>\n"+
							 "	<td width='94' height='26' bgcolor='#7AC8F1' class='summary_headline'><strong>Tests</td>\n"+
							 "	<td width='148' bgcolor='#7AC8F1' class='summary_headline'><strong>Failures</td>\n"+
							 "	<td width='129' bgcolor='#7AC8F1' class='summary_headline'><strong>Success Rate</td>\n"+
							 "	<td width='175' bgcolor='#7AC8F1' class='summary_headline'><strong>Average Time </td>\n"+
							 "	<td width='133' bgcolor='#7AC8F1' class='summary_headline'><strong>Min Time</td>\n"+
							 "	<td width='168' bgcolor='#7AC8F1' class='summary_headline'><strong>Max Time</td>\n"+
							 "</tr>\n"+
							 "<tr>\n"+
							 "  <td height='22' bgcolor='#CCCCCC'>"+this.headInformation[4]+"</td>\n"+
							 "   <td height='22' bgcolor='#CCCCCC'>"+this.headInformation[5]+"</td>\n"+
							 "   <td height='22' bgcolor='#CCCCCC'>"+this.headInformation[6]+"</td>\n"+
							 "   <td height='22' bgcolor='#CCCCCC'>"+this.headInformation[7]+"</td>\n"+
							 "   <td height='22' bgcolor='#CCCCCC'>"+this.headInformation[8]+"</td>\n"+
							 "   <td height='22' bgcolor='#CCCCCC'>"+this.headInformation[9]+"</td>\n"+
							 "</tr>\n"+
							 "<tr>\n"+
							 "	<td height='17' colspan='6' class='summary' style='font-size: 10px'>&nbsp;</td>\n"+
							 "</tr>\n"+
							 "</table>\n"+
							 "<table width='1229' border='0'>\n"+
							 "<tr>\n"+
							 "<th height='46' colspan='5' class='summary' style='font-size: 20px' align='left' scope='col'><p>Failures</p></th>\n"+
							 "</tr>\n"+
							 "<tr>\n"+
							 "  <td width='192' height='27' bgcolor='#7AC8F1'><strong><span class='summary_headline'>Page name</span></td>\n"+
							 "  <td width='203' bgcolor='#7AC8F1'><strong><span class='summary_headline'>Object name</span></td>\n"+
							 "  <td width='204' bgcolor='#7AC8F1'><strong><span class='summary_headline'>Action performed</span></td>\n"+
							 "  <td width='285' bgcolor='#7AC8F1'><strong><span class='summary_headline'>Error description</span></td>\n"+
							 "  <td width='323' bgcolor='#7AC8F1'><strong><span class='summary_headline'>Trace</span></td>\n"+
							 "</tr>\n");
			
							NodeFailure p=listFailure.getHead(); //Pointer for the link list.
							while(p!=null){
								this.report+=("<tr>\n"+
													"<td height='37' bgcolor='#CCCCCC'>"+p.returnPageName()+"</td>\n"+
													"<td bgcolor='#CCCCCC'>"+p.returnObjectName()+"</td>\n"+
													"<td bgcolor='#CCCCCC'>"+p.returnActionPerformed()+"</td>\n"+
													"<td bgcolor='#CCCCCC' style='text-align: left'>"+p.returnErrorDescription()+"</td>\n"+
													"<td bgcolor='#CCCCCC'><form id='form1' name='form1' method='post' action=''>\n"+
													"<textarea name='Trace' cols='135' rows='10' disabled='disabled' readonly='readonly' id='Trace'>\n"+
													 p.returnTrace()+
													"</textarea>\n"+
													"</form></td>\n"+
												"</tr>\n");
								
								p=p.getNext();
							}
								
							this.report+=("</table>\n"+
											   "<p>&nbsp;</p>\n"+
											   "</body>\n"+
											   "</html>");
							
							System.out.print(this.report);

		}
		catch (Exception e) {
			PrintStream newoutps = new PrintStream(this.oldoutps);
			System.setOut(newoutps); //set the output stream
    		System.out.println("Failed while writing report");
    	}
		
		//Save to log
		if(createLog())
			sendReportByEMail(this.report);
	}
	
	//Set head information=====================Console.this.=======================
	public void setHeadInformation(String []headInformation){
		this.headInformation=headInformation;
	}
	
	//Add failure=====================================================
	public void addFailure(Failure failure){
		String pageName=parsePageName(failure);
		String objectName=parseObjectName(failure);
		String actionPerformed=readFromActionStream();
		
		listFailure.addNewFailure(pageName,objectName,
				                  actionPerformed, 
				                  failure.getMessage(),failure.getTrace());
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
	
	//Generate file name=======================================================
	private String generateFileName(){
		return "test.html";
	}
	
	//Create log===============================================================
	private boolean createLog(){
		if(!newFailures())
			return false;
		
		try{FileOutputStream outfos = new FileOutputStream(savePath+"log"); //create new //output stream
			PrintStream newoutps = new PrintStream(outfos);
			System.setOut(newoutps); //set the output stream
			NodeFailure p=this.listFailure.getHead(); //Pointer for the link list.
			
			System.out.print(this.headInformation[1]+"\n");
					
			while(p!=null){
				System.out.print(p.returnPageName()+",,"+p.returnObjectName()+",,"+
							     p.returnActionPerformed()+",,"+p.returnErrorDescription()+",,"+
							     p.returnTrace()+"\n");

				p=p.getNext();
			}
		}
		catch (Exception e) {
			PrintStream newoutps = new PrintStream(this.oldoutps);
			System.setOut(newoutps); //set the output stream
    		System.out.println("Failed while trying writing log");
    		return false;
    	}
		
		return true;
	}
	
	//Check for new failures===================================================
	@SuppressWarnings("null")
	private boolean newFailures(){
		String line="",buffer="";
		final int ARR_SIZE=this.listFailure.getFailuresNumber();
		String[][]arr=new String[ARR_SIZE][];
		NodeFailure p; //Pointer for the failure link list.
		
		try{FileReader fstreamReader = new FileReader("data/log");
			BufferedReader reader=new BufferedReader(fstreamReader);
			
			line = reader.readLine();
			
			if(line==null||!line.equals(this.headInformation[1]))
				return true;
			
			
			int j=0; //Reset index.
			
			while(j<ARR_SIZE){
				line=reader.readLine();
				
				while(line!=null&&!line.equals("")){
					buffer+=line;
					line = reader.readLine();
				}
					
			    arr[j]=buffer.split(",,");
			
			    for(p=this.listFailure.getHead();p!=null;p=p.getNext()){
					if((p.returnPageName().equals(arr[j][0]))&&
					   (p.returnObjectName().equals(arr[j][1]))&&
					   (p.returnActionPerformed().equals(arr[j][2])))
							break;
					
					if(p.getNext()==null)
						return true;
					
			    }
			    
				line = reader.readLine();
				j++;
			}
	
		}catch (IOException e){
			PrintStream newoutps = new PrintStream(this.oldoutps);
			System.setOut(newoutps); //set the output stream
			System.out.println("Error occurred while trying reading log file");
		}
		
		return false;
	}
	
	//Send report by email=====================================================
	private void sendReportByEMail(String text){
		 String from="optifyautomation@gmail.com";
	     String to[]={"orasnin@gmail.com","anthonyp@optify.net"};
	     String subject="Optify automation report for "+this.headInformation[1]+" generate time "+this.headInformation[2];
 
	     this.email = new EMail(to,from,subject,text);
	     email.send();
	}
	
	//Add action performed=====================================================
	public void setActionPerformed(String act){
		this.action=act;
	}
	
	//Read the last action performed===========================================
	private String readFromActionStream(){
		String line="N/A";
		
		try{FileReader fstreamReader=new FileReader("data/actionStram");
			BufferedReader out=new BufferedReader(fstreamReader);
			line = out.readLine();
		}catch (IOException e){
			System.out.println("Error occurred while trying reading from action stream");
		}
		
		return line;
	}
	
}

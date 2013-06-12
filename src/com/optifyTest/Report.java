/*
 * This project was written by Asnin Or to the Optify Company 2013.
 * Parts of the OATS system.
 * The Report class.
 * ============================================================================
 * 
 * This class creates the testing scenario report and has the ability to send
 * throw mail using the EMail class.
 * 
 * 
 * Report methods:
 * =================
 * 
 * 1. Report(String savePath,String []headInformation,PrintStream oldoutps):
 * 
 *    The Report constructor receive the headInformation array that include all
 *    report head information from the MainTest class. In addition the
 *    constructor receive the printStream of the console in order to print 
 *    information to the user screen.The method received the server tested path
 *    and list of failure. 
 * 
 * 
 * 2. public void createReport():
 *    
 *    This method using the HTML code in order to create the design. In 
 *    addition the method using the headInformation as explained before to
 *    insert the report. The list of failure also mentioned added to the
 *    
 *    
 * 3. public void setHeadInformation(String []headInformation):
 * 
 *    This method update the headInfromation array.
 *    
 *    
 * 4. public void addFailure(Failure failure):
 * 
 *    This method receive the failure and parsing is information in order to
 *    display it in the report. After parsing it will add the information
 *    to the listFailure.
 *   
 *    
 * 5. private String parseObjectName(Failure failure):
 * 
 *    The method parse the name of the object that as been tested.
 *    
 *  
 * 6. private String parsePageName(Failure failure):
 * 
 *    The method parse the page name that has been tested.
 *    
 *    
 * 7. private String generateFileName():
 * 
 *    This method generate the name of the report file.
 *    
 *   
 * 8. private boolean createLog():
 * 
 *    This method create a log file of the last failures report. It's
 *    return true if a new log file has been created or false if not.
 *    The idea is to understand if any new failures reported and return true
 *    in order to update the user throw an email or any other way.
 *    
 *    
 * 9. private boolean newFailures():
 * 
 *    This method reads the log file and compare it with the new failures list,
 *    if any new failure doesn't appear the method will return true.
 *    
 *    
 * 10.private void sendReportByEMail(String text):
 * 
 *    This method receiving the email body text, and set it to send throw the
 *    EMail class. This include from, to and subject. For now all those 
 *    attributes set manually.
 *    
 *    
 * 11.public void setActionPerformed(String act):
 * 
 *    This method updating the action that has been testing. The idea is to use
 *    This action information in case of failure.
 *     
 *   
 * 12.private String readFromActionStream():
 * 
 *    Read the last actions has performed from data/actionStream.
 */


package com.optifyTest;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class Report {
	
	//Report class attributes:
	private String savePath;
	private String []headInformation;  //Vector of headline information (System tested, head etc).
	private String report;             //Include the copy of the generated report.
	private EMail email;               //Using email object to send the report throw
	private PrintStream oldoutps;
	private String action;
	private ManageFailure mFailures;
	
	//Report class constructor:
	Report(String savePath,String []headInformation,PrintStream oldoutps, ManageFailure mFailure){
		this.savePath=savePath;
		this.headInformation=headInformation;
		this.report="";
		this.oldoutps=oldoutps;
		this.action="n/a";
		this.mFailures=mFailure;
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
							 "	<td height='17' colspan='6' style='font-size: 10px'>&nbsp;</td>\n"+
							 "</tr>\n"+
							 "</table>\n"+
							 "<table width='1229' border='0'>\n"+
							 "<tr>\n"+
							 "<th height='46' colspan='5' style='font-size: 20px' align='left' scope='col'>New issues</th>\n"+
							 "</tr>\n"+
							 "<tr>\n"+
							 "  <td width='192' height='27' bgcolor='#7AC8F1'><strong><span class='summary_headline'>ID</span></td>\n"+
							 "  <td width='192' height='27' bgcolor='#7AC8F1'><strong><span class='summary_headline'>Page name</span></td>\n"+
							 "  <td width='203' bgcolor='#7AC8F1'><strong><span class='summary_headline'>Object name</span></td>\n"+
							 "  <td width='204' bgcolor='#7AC8F1'><strong><span class='summary_headline'>Action performed</span></td>\n"+
							 "  <td width='285' bgcolor='#7AC8F1'><strong><span class='summary_headline'>Error description</span></td>\n"+
							 "  <td width='323' bgcolor='#7AC8F1'><strong><span class='summary_headline'>Trace</span></td>\n"+
							 "</tr>\n");
			
							int id=1;
							for(NodeFailure i:this.mFailures.getNewFailureList()){
								this.report+=("<tr>\n"+
										            "<td height='37' bgcolor='#CCCCCC'>"+id+"</td>\n"+
													"<td height='37' bgcolor='#CCCCCC'>"+i.returnPageName()+"</td>\n"+
													"<td bgcolor='#CCCCCC'>"+i.returnObjectName()+"</td>\n"+
													"<td bgcolor='#CCCCCC'>"+i.returnActionPerformed()+"</td>\n"+
													"<td bgcolor='#CCCCCC' style='text-align: left'>"+i.returnErrorDescription()+"</td>\n"+
													"<td bgcolor='#CCCCCC'><form id='form1' name='form1' method='post' action=''>\n"+
													"<textarea name='Trace' cols='135' rows='10' disabled='disabled' readonly='readonly' id='Trace'>\n"+
													 i.returnTrace()+
													"</textarea>\n"+
													"</form></td>\n"+
												"</tr>\n");
								id++;
								
							}
							this.report+=("</table>\n"+
							 "<table width='1229' border='0'>\n"+
							 "<tr>\n"+
							 "<th height='46' colspan='5' style='font-size: 20px' align='left' scope='col'>Exisiting issues</th>\n"+
							 "</tr>\n"+
							 "<tr>\n"+
							 "  <td width='192' height='27' bgcolor='#7AC8F1'><strong><span class='summary_headline'>ID</span></td>\n"+
							 "  <td width='192' height='27' bgcolor='#7AC8F1'><strong><span class='summary_headline'>Ins</span></td>\n"+
							 "  <td width='192' height='27' bgcolor='#7AC8F1'><strong><span class='summary_headline'>Page name</span></td>\n"+
							 "  <td width='203' bgcolor='#7AC8F1'><strong><span class='summary_headline'>Object name</span></td>\n"+
							 "  <td width='204' bgcolor='#7AC8F1'><strong><span class='summary_headline'>Action performed</span></td>\n"+
							 "  <td width='285' bgcolor='#7AC8F1'><strong><span class='summary_headline'>Error description</span></td>\n"+
							 "  <td width='323' bgcolor='#7AC8F1'><strong><span class='summary_headline'>Trace</span></td>\n"+
							 "</tr>\n");
			
							id=0;
							for(NodeFailure i:this.mFailures.getOldFailureList()){
								this.report+=("<tr>\n"+
										            "<td height='37' bgcolor='#CCCCCC'>"+id+"</td>\n"+
										            "<td height='37' bgcolor='#CCCCCC'>"+i.getNum()+"</td>\n"+
													"<td height='37' bgcolor='#CCCCCC'>"+i.returnPageName()+"</td>\n"+
													"<td bgcolor='#CCCCCC'>"+i.returnObjectName()+"</td>\n"+
													"<td bgcolor='#CCCCCC'>"+i.returnActionPerformed()+"</td>\n"+
													"<td bgcolor='#CCCCCC' style='text-align: left'>"+i.returnErrorDescription()+"</td>\n"+
													"<td bgcolor='#CCCCCC'><form id='form1' name='form1' method='post' action=''>\n"+
													"<textarea name='Trace' cols='135' rows='10' disabled='disabled' readonly='readonly' id='Trace'>\n"+
													 i.returnTrace()+
													"</textarea>\n"+
													"</form></td>\n"+
												"</tr>\n");
								
								id++;
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
		if(mFailures.checkIfNewFailureExist())
			sendReportByEMail(this.report);
	}
	
	//Set head information=====================Console.this.=======================
	public void setHeadInformation(String []headInformation){
		this.headInformation=headInformation;
	}
	
	//Generate file name=======================================================
	private String generateFileName(){
		return "test.html";
	}
	
	//Send report by email=====================================================
	private void sendReportByEMail(String text){
		 String subject="";
		 String from="optifyautomation@gmail.com";
	     String to[]={"orasnin@gmail.com"};
	     
	     //Set high priority email if massive failure detected.
	     if(checkIfmassiveFailure())
	    	 subject="Optify automation [[[Massive Failure!!!!]]] report for "+this.headInformation[1]+" generate time "+this.headInformation[2];
	     
	     else
	    	 subject="Optify automation report for "+this.headInformation[1]+" generate time "+this.headInformation[2];
 
	     this.email = new EMail(to,from,subject,text);
	     email.send();
	}
	
	//Add action performed=====================================================
	public void setActionPerformed(String act){
		this.action=act;
	}
	
	//Check for massive failure================================================
	private boolean checkIfmassiveFailure(){
		for(NodeFailure i:this.mFailures.getNewFailureList())
  			if(i.returnErrorDescription()!=null&&i.returnErrorDescription().equals("Can't log to server!"))
  				return true;
  						
  			return false;
	}
}

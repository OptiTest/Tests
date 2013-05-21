package com.optifyTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NodeFailure {

	//NodeFailures class attributes:
	private String pageName,
					objectName,
					actionPerformed,
					errorDescription,
					trace,
					date;
	
	private int num;                    //The number of instances
	
	private NodeFailure next;
	
	//NodeFailures class constructor:
	public NodeFailure(String pageName,String objectName,
					   String actionPerformed, String errorDescription,
					   String trace){
		this.pageName=pageName;
		this.objectName=objectName;
		this.actionPerformed=actionPerformed;
		this.errorDescription=errorDescription;
		this.trace=trace;
		this.date=new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		this.num=1;
		this.next=null;
	}
	
	public NodeFailure(String arr[]){
		int i=0; //arr index.
		this.pageName=arr[i];i++;
		this.objectName=arr[i];i++;
		this.actionPerformed=arr[i];i++;
		this.errorDescription=arr[i];i++;
		this.trace=arr[i];
		this.date=new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		this.num=1;
		this.next=null;
}

	//Set next=================================================================
	public void setNext(NodeFailure next){
		this.next=next;
	}
	
	//Return next==============================================================
	public NodeFailure getNext(){
		return this.next;
	}
	
	//Return page name=========================================================
	public String returnPageName(){
		return this.pageName;
	}
	
	//Return object name=========================================================
	public String returnObjectName(){
		return this.objectName;
	}
	
	//Return action performed=========================================================
	public String returnActionPerformed(){
		return this.actionPerformed;
	}
	
	//Return error description=========================================================
	public String returnErrorDescription(){
		return this.errorDescription;
	}
	
	//Return trace===================================================================
	public String returnTrace(){
		return this.trace;
	}
	
	//Set num======================================================================
	public void setNum(int num){
		this.num=num;
	}
	
	//Get num==================================================================
	public int getNum(){
		return num;
	}
}

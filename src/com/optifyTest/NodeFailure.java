package com.optifyTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NodeFailure {

	//NodeFailures class attributes:
	private String pageName,
					objectName,
					actionPerformed,
					errorDescription,
					trace;
	private String date[];
	private int num;                    //The number of instances
	
	private NodeFailure next;
	
	//NodeFailures class constructor:
	public NodeFailure(String pageName,String objectName,
					   String actionPerformed, String errorDescription,
					   String trace){
		final int SIZE=1;
		
		this.pageName=pageName;
		this.objectName=objectName;
		this.actionPerformed=actionPerformed;
		this.errorDescription=errorDescription;
		this.trace=trace;
		this.date=new String[SIZE];
	    this.date[0]=new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		this.num=1;
		this.next=null;
	}
	
	public NodeFailure(String arr[]){
		int i=0; //arr index.
		this.pageName=arr[i];i++;
		this.objectName=arr[i];i++;
		this.actionPerformed=arr[i];i++;
		this.errorDescription=arr[i];i++;
		this.trace=arr[i];i++;
		this.num=Integer.parseInt(arr[i]);i++;
		this.date=deepCopyPluse(arr[i]);
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
		return this.num;
	}
	
	//Get dates================================================================
	public String[] getDates(){
		return this.date;
	}
	
	//Set last date:===========================================================
	public void setLastDate(){
		this.date[this.date.length-1]=new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	}
	
	//=========================================================================
	private String[] deepCopyPluse(String from){
		String a[]=from.split("::");
		final int SIZE=2;
		
		if(a[a.length-1].length()>SIZE)
			if(a[a.length-1].substring(a[a.length-1].length()-2,a[a.length-1].length()).equals("\n"))
				a[a.length-1]=a[a.length-1].substring(0,a[a.length-1].length()-2);
			
		return a;
	}
	
	//=========================================================================
	private String[] deepCopy(String from){
		String a[]=from.split("::");
		String b[]=null;
		
		if(a!=null){
			b=new String[a.length-1];
			
			for(int i=0;i<b.length;i++)
				b[i]=a[i];
		}
		
		return b;
	}
}

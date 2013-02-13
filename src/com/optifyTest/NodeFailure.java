package com.optifyTest;

public class NodeFailure {

	//NodeFailures class attributes:
	private String pageName,
					objectName,
					actionPerformed,
					errorDescription,
					trace;
	
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
	
	//Return trace=============================================================
	public String returnTrace(){
		return this.trace;
	}
}

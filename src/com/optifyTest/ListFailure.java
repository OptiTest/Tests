package com.optifyTest;

public class ListFailure {
	
	//ListFailures class attributes:
	private NodeFailure head;
	private int num;  //Number of failures.
	
	//ListFailure constructor:
	public ListFailure(){
		this.head=null;
		this.num=0;
	}
	
	//ListFailure methods:
	
	//Add new node==========================================================
	public void addNewFailure(String pageName,String objectName,
				String actionPerformed, String errorDescription,
				String trace){
		
		if(this.head==null){
			this.head=new NodeFailure(pageName,objectName,actionPerformed,
					errorDescription,trace);
		}
		
		else{NodeFailure p;
			
			for(p=this.head;p!=null;p=p.getNext());
			p=new NodeFailure(pageName,objectName,actionPerformed,
					errorDescription,trace);
			p.setNext(this.head);
			this.head=p;
		}
		
		num++; //Increase failures number.
	}
	
	//Get head==================================================
	public NodeFailure getHead(){
		return this.head;
	}
	
	//Get the failures number==================================================
	public int getFailuresNumber(){
		return this.num;
	}
	
}

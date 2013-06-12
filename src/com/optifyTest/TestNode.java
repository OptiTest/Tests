package com.optifyTest;

import java.io.File;
import java.util.LinkedList;

public class TestNode {
	 private final String fileName;
	 private LinkedList<String> scriptName;
	 private int size;
	 
	 public TestNode(String fileName){
		 this.fileName=fileName;
		 this.scriptName=new LinkedList<String>();
		 this.size=0;
	 }
	 
	 //========================================================================
	 public String getFileName(){
		 return this.fileName;
	 }
	 
	 //========================================================================
	 public void addScriptName(String name){
		 if(name!=null){
			 this.scriptName.add(name);
			 this.size++;
		 } 
	 }
	 
	 //=======================================================================
	 public void printScriptList(){
		 for(int i=0;i<this.scriptName.size();i++)
				System.out.println(this.scriptName.get(i).toString());
	 }
	 
	 //=======================================================================
	 public String[] getScriptArrayList(){
		 final int SIZE=this.scriptName.size();
		 
		 String listArray[]=new String[SIZE];
		 
		 for(int i=0;i<SIZE;i++)
			 listArray[i]=this.scriptName.get(i);
		 
		 return listArray;
	 }
	 
	 //========================================================================
	 public int size(){
		 return this.size;
	 }
	 
	 //=======================================================================
	 public LinkedList<String> getScriptList(){
		 return this.scriptName;
	 }
	 
	 //=========================================================================
	
}

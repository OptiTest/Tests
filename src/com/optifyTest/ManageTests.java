package com.optifyTest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.Test;


/*Written by Asnin Or for the Optify Company 2013.
 * 
 * This class scans all src/com/optifyTest for new test files and their 
 * scripts. Compiling them and register them on data/data2 file. this 
 * data allow the Optify Automation program to manage all her test actions and
 * schedule and gives the user the opportunity to decide with test and scripts 
 * will run. This information will display to the user buy a JTree in the Main
 * Menu program.
 *  
 * Methods:
 * 
 * 1. The textFilter method will filter all files in src folder that can be use
 *    as test files, she will search all filed end with "Java".
 *    
 * 2. The modifiedTestFiles method will search on all files that has been 
 *    candidate by the textFilter method as test files, with one of them is 
 *    a real JUnit test file.
 */

public class ManageTests {
	//ManageTests attributes:
	private File dir;   //Holds path directory to test list.
	private File[] files;     //Holds the path list files.
	private LinkedList<File> testFileList; //Holds the actual test files
	LinkedList<TestNode> testList; //Holds test files name and scripts
	private FileReader fstreamRead;
	
	public ManageTests(){
		this.testFileList=new LinkedList<File>();
		this.testList=new LinkedList<TestNode>();
		this.dir=new File("src/com/optifyTest");
		this.files=this.dir.listFiles(textFilter);
		modifiedTestFiles(this.files);
		packList();
		//printList();
		printScripts();
	}
	
	//=========================================================================
	private FilenameFilter textFilter = new FilenameFilter() {
		public boolean accept(File dir, String name) {
			String lowercaseName = name.toLowerCase();
			if (lowercaseName.endsWith(".java")) {
				return true;
			} else {
				return false;
			}
		}
	};
	
	//=========================================================================
	public void printList(){
		System.out.println("Test files as loaded:");
		
		for(int i=0;i<this.testFileList.size();i++)
			System.out.println(this.testFileList.get(i).toString());
	}
	
	//===========================================================================
	public void printScripts(){
		for(int i=0;i<this.testList.size();i++){
			System.out.println(this.testList.get(i).getFileName().toString());
		    this.testList.get(i).printScriptList();
		}
	}
	
	//Prepare test files=========================================================
	public File[] prepareTestFiles(File list[]){
		//getMo
		//list[0].
		return list;
	}
	
	//Return modified test file==================================================
	@SuppressWarnings("null")
	public void modifiedTestFiles(File list[]){
		final int SIZE=list.length;
		String line="";
		BufferedReader reader=null;
		String splitList[];
		boolean FINAL=false;
		
		
		for(int i=0;i<SIZE;i++){
			try {this.fstreamRead=new FileReader(list[i]);
				 reader=new BufferedReader(this.fstreamRead);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			try {do{splitList=line.split(" ");
				    final int SIZE2=splitList.length;
					
				    for(int j=0;j<SIZE2;j++){
				    	if(splitList[j].equals("class"))
				    		FINAL=true;
				    	
				    	if(splitList[j].equals("TestCase"))
				    		this.testFileList.add(list[i]);
				    	
				    }
			
			
					line = reader.readLine();
					
				}while(line!=null&&!FINAL);
		 		
				
		 
			} catch (IOException e) {
				System.out.println("Error occurred while trying reading file");
			}
			
			FINAL=false;
			
		}
			
	}
	
	//Return list of tests=====================================================
	public LinkedList<File> getList(){
		return this.testFileList;
	}
	
	//Return test name array==================================================
	public String[] getNameArray(){
		final int SIZE=this.testFileList.size(); 
		String nameArr[]=new String[SIZE];
		
		for(int i=0;i<SIZE;i++)
        	 nameArr[i]=returnFileName(this.testFileList.get(i).toString()); 
		
		return nameArr;
	}
	
	//Parse files name========================================================
	public String returnFileName(String path){
		String arr[]=path.split("optifyTest");
		
		return arr[arr.length-1].substring(1,arr[arr.length-1].length()-5);
	}
	
	//Pack to test list:=======================================================
	public void packList(){
		String line=null;
		BufferedReader reader=null;
		String splitList[];
		String fileName=null;
		boolean FINAL=false;
		
		
		for(int i=0;i<this.testFileList.size();i++){
			fileName=this.testFileList.get(i).toString();
			TestNode node=new TestNode(fileName);
			this.testList.add(node);
			
			try {this.fstreamRead=new FileReader(fileName);
			 reader=new BufferedReader(this.fstreamRead);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			try {do{if(line!=null && line.contains("@Test")){
						String scriptName=getScriptName(reader);
						if(scriptName!=null){
							node.addScriptName(scriptName);
						}
					}
			
					line = reader.readLine();
					
				}while(line!=null&&!FINAL);
		 		
				
		 
			} catch (IOException e) {
				System.out.println("Error occurred while trying reading file");
			}
			
			FINAL=false;
			
		}
			
	}
	
	//Get script name:=========================================================
	public String getScriptName(BufferedReader reader){
		int i,j=0;
		
		if(reader==null){
			return null;
		}
		
		String line=null;
		
		try {line = reader.readLine();         //After we have found the '@Test' sign 
		} catch (IOException e) {              //We will call reader to read new line 
			                                   //In order to collect the script '@Test' name.
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String splitList[]=line.split(" ");   //Split and return the third string (name of script).  
		
		for(i=0;i<splitList.length;i++)
			if(splitList[i].equals("void"))
				break;
		
		//Split breakers name in script function name:
		for(j=0;j<splitList[i+1].length()-1;j++)
			if(splitList[i+1].substring(i,i+2).equals("()"))
				break;
		
		if(j!=0)
			j--;
		
		//Return the first name (without breakers).
		return splitList[i+1].substring(0,j);
		
	}
}
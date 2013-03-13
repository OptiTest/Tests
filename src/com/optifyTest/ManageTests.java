package com.optifyTest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.LinkedList;


/*Written by Asnin Or to the Optify Company 2013.
 * 
 * This Class scanning all src/com/optifyTest for new test files,
 * Compiling them and register them on data/data2 file.
 * 
 */

public class ManageTests {
	//ManageTests attributes:
	private File dir;   //Holds path directory to test list.
	private File[] files;     //Holds the path list files.
	private LinkedList<File> testFileList; //Holds the actual test files.
	private FileReader fstreamRead;
	
	public ManageTests(){
		this.testFileList=new LinkedList<File>();
		this.dir=new File("src/com/optifyTest");
		this.files=this.dir.listFiles(textFilter);
		modifiedTestFiles(this.files);
		printList();
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
		final int SIZE=this.files.length;
		
		System.out.println("Test files as loaded:");
		
		for(int i=0;i<this.testFileList.size();i++)
			System.out.println(this.testFileList.get(i).toString());
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

}

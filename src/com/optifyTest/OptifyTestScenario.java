package com.optifyTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

@RunWith(BlockJUnit4ClassRunner.class)
public class OptifyTestScenario {
	public static MainMenu ts=new MainMenu();
	public static Settings st=new Settings();
	
	//Set test parameters:
	public static ChromeDriverService service;
	public static WebDriver driver;
	Actions builder = new Actions(driver);
  
	static String homeAddress=st.getServerUrl();
	static String userName=ts.getUserName();
	static String setPath=st.getSeleniumBit();
	static String password=ts.getUserPassword();
	static public String object;
	static public String pageName="";
	static public double time=0; 
	static List<String>scripList; //Loads all enable script list.
	public boolean junit=false;           //The default should be false. True for JUnit test only!
	
	//=================================================================
	public static void printSuccess(){
		  double sumTime=(System.currentTimeMillis()-time)/1000;
		  System.out.printf("%-5s","Success");
		  System.out.printf("%5.0f",(sumTime/60)%60);
		  System.out.printf(".%-5.0f",sumTime%60);
		  System.out.printf("%-30s %s%n",object,pageName);
		  time=System.currentTimeMillis();
	  }
	  
	  //=================`===============================================
	  public static void printFailed(){
		  double sumTime=(System.currentTimeMillis()-time)/1000;
		  System.out.printf("%-7s","Failed");
		  System.out.printf("%5.0f",(sumTime/60)%60);
		  System.out.printf(".%-5.0f",sumTime%60);
		  System.out.printf("%-30s %s%n",object,pageName);
		  time=System.currentTimeMillis();
	  }
	  
	 //===========================================================================
	 public static List<String> getScriptList(){
		  	//Load all file info into Contact List.
			BufferedReader reader=null;
			File file=new File("data/data3");
			List<String> list=new ArrayList<String>();
			String line="";
			
			try { FileReader fstreamRead=new FileReader(file);
			  reader=new BufferedReader(fstreamRead);
			  line = reader.readLine();
			  
			  while(line!=null){
				  list.add(line);
				  line = reader.readLine();
			  }
			  
			  reader.close();
		
			} catch (Exception e) {
			// TODO Auto-generated catch block
				System.out.println("Can't load scripts list from file data/data3!");
				e.printStackTrace();
			}
			
		 	return list;
	}
	 
	//================================================================================================
	public int getRowCount(By by) throws Exception {
	      try { WebElement table = driver.findElement(by);
	            List<WebElement> rows = table.findElements(By.tagName("tr"));
	            return rows.size();
	      
	      } catch (Exception e) {
	          return -1;
	      }
	}
	  
	//================================================================================================
	public int getRowsNum(By by) throws Exception{
		  WebDriverWait wait = new WebDriverWait(driver, 10);
		  int num=0;
		  int sum=0;
		  int MAX_ROWS=100;
		  
		  while((num=getRowCount(by))>=MAX_ROWS){
			  sum+=num;
			  wait.until(presenceOfElementLocated(By.xpath("//a[@class='next']")));
			  driver.findElement(By.xpath("//a[@class='next']")).click();
			  wait.until(presenceOfElementLocated(by));
		  }
		  
		  sum+=num;
		  return sum;
	  }
	  
	 
	//===========================================================================
	public boolean enable(String name){
		  if(junit)
				 return true;
		  
		  for(String elem:DashBoard.scripList)
	    	 if(elem!=null && elem.toString().equals(name))
	    		 return true;
	      
	    return false; 
	}
	
	//==========================================================================================================
	public static void print(String action){
		  FileWriter fstreamWrite=null;
		  
		  System.out.printf("%-52s",action);
		  
		  try{fstreamWrite = new FileWriter("data/actionStram");
			 }catch(IOException e) {
			 	// TODO Auto-generated catch block
				e.printStackTrace();
			 }
			 
			BufferedWriter out = new BufferedWriter(fstreamWrite);
			try {out.write(action);
				 out.close();
			} catch (IOException e) {
				System.err.println("Error: " + e.getMessage());
			}
	 }
	
	//===============================================================================================
	  public static Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
		    return new Function<WebDriver, WebElement>() {
		        public WebElement apply(WebDriver driver) {
		            return driver.findElement(locator);
		          }
	       };
	  }
	  
	  //================================================================================================
	  public void switcWindow(){
		  for(String winHandle : driver.getWindowHandles())
			  driver.switchTo().window(winHandle);
	  }
	  
	  //================================================================================================
	  public String getTime(){
		  return Integer.toString((int)((System.currentTimeMillis()/(1000*60*60))%24))+
		         Integer.toString((int) ((System.currentTimeMillis()/(1000*60))%60));
	  }
	  
	  //==================================================================================================
	  public int returnMonthInt(String month){
		  final int SUM_MONTH=12;
		  final int MONTH_NUM[]={0,1,2,3,4,5,6,7,8,9,10,11};
		  final String MONTH_STR[]={"January","February","March","April","May","June","July","August",
				  "September","October","November","December"};
		  for(int i=0;i<SUM_MONTH;i++){
			 if(month.equals(MONTH_STR[i]))
				 return MONTH_NUM[i];
		  }

		  return -1;
	  }
	  
	  //=================================================================================================
	  public static void selectValue(String valToBeSelected){
	      List <WebElement> options = driver.findElements(By.tagName("option"));
			for (WebElement option : options) {
				if (valToBeSelected.equalsIgnoreCase(option.getText())){
					option.click();
				}
		    }
		}
	  
}

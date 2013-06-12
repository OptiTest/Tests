package com.optifyTest;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LogIn extends OptifyTestScenario {
	
	public LogIn(){
		super();
	}
	
	@BeforeClass
	public static void createAndStartService() throws Throwable {
		service = new ChromeDriverService.Builder()
	    	.usingDriverExecutable(new File(setPath))
	        .usingAnyFreePort()
	        .build();
		service.start();
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		String[] listCapability={"--start-minimized","--disable-extensions","--disable-translate"};
		capabilities.setCapability("chrome.switches", listCapability);
		driver = new RemoteWebDriver(service.getUrl(),capabilities);
		
		System.out.println("\n");
	}
	
	@Test
	public void LogInToOptify() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("Testing Optify site server alive.");
	  LogInToOptify_test(numTry);
	  System.out.println(" v");
	}
	
	@AfterClass
	public static void summary(){
		 driver.close();
	}
	 
	 //========================================================================
	 private void LogInToOptify_test(int numTry)throws Exception{
		 try{driver.get(homeAddress+"/login");
		  driver.findElement(By.id("j_username")).sendKeys(userName);
		  driver.findElement(By.id("j_password")).sendKeys(password);
		  driver.findElement(By.id("login_button")).click();
		  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
		 
		 }catch(Exception e){
		 
			 if(numTry>2)
				 throw new Exception("Can't log to server!");
			 
			 numTry++;
			 LogInToOptify_test(numTry);
		  }
	 }
}

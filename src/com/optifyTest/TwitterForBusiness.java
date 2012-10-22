package com.optifyTest;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

@RunWith(BlockJUnit4ClassRunner.class)
public class TwitterForBusiness extends TestCase  {
	
//Set test parameters:
  private static ChromeDriverService service;
  private static WebDriver driver;
  Actions builder = new Actions(driver);
  static String homeAddress="https://dashboard.optify.net";
  static String userName="your username";
  static String password="your password";
  static String setPath="D:\\selenium-2.23.1\\chromedriver.exe";
  
  @BeforeClass
  public static void createAndStartService() throws Throwable {
    service = new ChromeDriverService.Builder()
    	.usingDriverExecutable(new File(setPath))
        .usingAnyFreePort()
        .build();
	service.start();
	
	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	String[] listCapability={"--start-maximized","--disable-extensions","--disable-translate"};
	capabilities.setCapability("chrome.switches", listCapability);
	driver = new RemoteWebDriver(service.getUrl(),capabilities);
	
	enterToTwitterForBusiness();
  }
  
  public static void enterToTwitterForBusiness() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  Actions builder = new Actions(driver);
	  
	  //Log in Optify:
	  driver.get(homeAddress+"/login");
	  driver.findElement(By.id("j_username")).sendKeys(userName);
	  driver.findElement(By.id("j_password")).sendKeys(password);
	  driver.findElement(By.id("login_button")).click();
	  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
	  
	  //Enter to Twitter For Business:
	  wait.until(presenceOfElementLocated(By.xpath("//li[@class='enable']/a/span")));
	  builder.clickAndHold(driver.findElement(By.xpath("//li[@class='drive']/a/span"))).perform();
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//*[@id='header-nav']/li[2]/ul/li[4]/a"))).click();
	  assertEquals("Twitter for Business:","Twitter for Business | Optify",driver.getTitle());
	  Thread.sleep(3000);
  }
  
  @Test
  public void postTwitt() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String message = testMessage();
	  
	  //Test twitt:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_text']/textarea"))).sendKeys(message);
	  wait.until(presenceOfElementLocated(By.xpath("//a[@id='post_tweet_submit']/img"))).click();
	  Thread.sleep(3000);
	  
	  //Get account owner name:
	  String account = driver.findElement(By.xpath("//*[@id='inbox_grid']/tbody/tr/td[2]/div/span/a")).getText();
	
	  //Check if twitt message has been sent:
	  Thread.sleep(3000);
	  driver.navigate().refresh();
	  String getMessage=wait.until(presenceOfElementLocated(By.className("inbox_tweet"))).getAttribute("textContent");
	  assertEquals("Message",message,getMessage.substring(account.length()+1, 1+account.length()+message.length()));
	  
	  Thread.sleep(3000);
  } 
  
  //===============================================================================================
  private static Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
		    return new Function<WebDriver, WebElement>() {
		        public WebElement apply(WebDriver driver) {
		            return driver.findElement(locator);
		          }
	     };
	}
	
  //==================================================================================================
  private void switcWindow(){
		  for(String winHandle : driver.getWindowHandles())
			  driver.switchTo().window(winHandle);
	}
  
  //================================================================================================
  private int getRowCount(By by) throws Exception {
	  Thread.sleep(3000);
      try { WebElement table = driver.findElement(by);
            List<WebElement> rows = table.findElements(By.tagName("tr"));
            return rows.size();
      
      } catch (Exception e) {
          return -1;
      }
  }
  
  //==================================================================================================
  private int returnMonthInt(String month){
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
  
  //================================================================================================
  private int getRowsNum(By by) throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  int num=0;
	  int sum=0;
	  
	  while(true){
		  wait.until(presenceOfElementLocated(by));
		  num=getRowCount(by);
		  sum+=num;
		  
		  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='next']"))).click();
		  }
		  catch (Exception e) {
	          return sum;
	      }
	  }
	   
  }
  
//================================================================================================
  private String testMessage(){
	  return Integer.toString((int)((System.currentTimeMillis()/(1000*60*60))%24))+
	         Integer.toString((int) ((System.currentTimeMillis()/(1000*60))%60));
  }
}

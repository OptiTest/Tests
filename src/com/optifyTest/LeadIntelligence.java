package com.optifyTest;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import junit.framework.TestCase;

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
public class LeadIntelligence extends TestCase {

  //Set test parameters:
  private static ChromeDriverService service;
  private static WebDriver driver;
  Actions builder = new Actions(driver);
  String homeAddress="http://dashboard.optify.net";
  String userName="orasnin@gmail.com";
  String password="wrwmfy9m";
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
  }
  
  @Test
  public void enterToLeadIntelligence() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
		  //Log in Optify:
		  driver.get(homeAddress+"/login");
		  driver.findElement(By.id("j_username")).sendKeys(userName);
		  driver.findElement(By.id("j_password")).sendKeys(password);
		  driver.findElement(By.id("login_button")).click();
		  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
		  
		  //Enter to LeadIntelligence:
		  wait.until(presenceOfElementLocated(By.xpath("//li[@class='enable']/a/span")));
		  builder.clickAndHold(driver.findElement(By.xpath("//li[@class='enable']/a/span"))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//html/body/div/div[2]/ul/li[4]/ul/li/a"))).click();
		  assertEquals("Lead Intelligence page","Lead Intelligence | Optify",driver.getTitle());
		  Thread.sleep(3000);
  }
  
  //@Test
  public void helpWithThisPage() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='help_for_this_page iconlink track']")));
	  String winHandleBefore = driver.getWindowHandle();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='help_for_this_page iconlink track']"))).click();
	  switcWindow();
	  assertEquals("Help with this page:","Lead Intelligence overview : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  Thread.sleep(3000);
  } 
  
  //@Test
  public void showResults() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  int SUMFIF=50;
	  int SUMTF=25;
	  int SUMTH=10;
	  
	  //show 50:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/span/select")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='results_count_pager']/span/select"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/span/select/option[3]"))).click();
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/span/select"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody")));
	  Thread.sleep(3000);
	  assertEquals("count 50:",SUMFIF,getRowCount(By.xpath("//table[@id='lead_table']/tbody")));
	  Thread.sleep(3000);
	  
	  //show 25:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/span/select")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='results_count_pager']/span/select"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/span/select/option[2]"))).click();
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/span/select"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody")));
	  Thread.sleep(3000);
	  assertEquals("count 25:",SUMTF,getRowCount(By.xpath("//table[@id='lead_table']/tbody")));
	  Thread.sleep(3000);
	  
	  //show 10:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/span/select")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='results_count_pager']/span/select"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/span/select/option"))).click();
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/span/select"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody")));
	  Thread.sleep(3000);
	  assertEquals("count 10:",SUMTH,getRowCount(By.xpath("//table[@id='lead_table']/tbody")));
	  Thread.sleep(3000);
  }
  
  //@Test
  public void nextAndPrev() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='lead_pager']//a[text()='2']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr/td")));
	  wait.until(presenceOfElementLocated(By.xpath("//span[@class='active number']")));
	  assertEquals("Pgae active number:","2",driver.findElement(By.xpath("//span[@class='active number']")).getText()); 
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='lead_pager']//a[@class='next']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr/td")));
	  wait.until(presenceOfElementLocated(By.xpath("//span[@class='active number']")));
	  assertEquals("Pgae active number:","3",driver.findElement(By.xpath("//span[@class='active number']")).getText()); 
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='lead_pager']//a[@class='previous']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr/td")));
	  wait.until(presenceOfElementLocated(By.xpath("//span[@class='active number']")));
	  assertEquals("Pgae active number:","2",driver.findElement(By.xpath("//span[@class='active number']")).getText()); 
  }
  
  @Test
  public void calendar() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  Calendar todayDate=Calendar.getInstance();
	  final int SEVEN = -7;
	  final int THIRTY = -30;
	  final int NINETY = -90;
	  
	  //Test 7d dates:
	  todayDate.add(Calendar.DAY_OF_MONTH,SEVEN);
	  try{ wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-7d']"))).click();
	  }
	  catch(Exception e){
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-24h']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-7d']"))).click();
	  }
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr")));
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-7d selected']")));
	  
	  driver.findElement(By.xpath("//div[@class='interval_date_picker']/button/span")).click();
	  Thread.sleep(3000);
	  assertEquals("7d day",Integer.toString(todayDate.get(Calendar.DAY_OF_MONTH)+1),driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).getText());
	  assertEquals("7d month",todayDate.get(Calendar.MONTH),returnMonthInt(driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText()));
	  System.out.print(todayDate.get(Calendar.YEAR));
	  assertEquals("7d year",Integer.toString(todayDate.get(Calendar.YEAR)),driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());
	  
	  //Test 90d dates:
	  todayDate=Calendar.getInstance();
	  todayDate.add(Calendar.DAY_OF_MONTH,NINETY);
	  driver.findElement(By.xpath("//a[@class='interval_change_link interval-90d']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr")));
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-90d selected']")));
	  
	  driver.findElement(By.xpath("//div[@class='interval_date_picker']/button/span")).click();
	  Thread.sleep(3000);
	  assertEquals("90d day",Integer.toString(todayDate.get(Calendar.DAY_OF_MONTH)+1),driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).getText());
	  assertEquals("90d month",todayDate.get(Calendar.MONTH),returnMonthInt(driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText()));
	  assertEquals("90d year",Integer.toString(todayDate.get(Calendar.YEAR)),driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());
	  
	  //Test 30d dates:
	  todayDate=Calendar.getInstance();
	  todayDate.add(Calendar.DAY_OF_MONTH,THIRTY);
	  driver.findElement(By.xpath("//a[@class='interval_change_link interval-30d']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr")));
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-30d selected']")));
	  
	  driver.findElement(By.xpath("//div[@class='interval_date_picker']/button/span")).click();
	  Thread.sleep(3000);
	  assertEquals("30d day",Integer.toString(todayDate.get(Calendar.DAY_OF_MONTH)+1),driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).getText());
	  assertEquals("30d month",todayDate.get(Calendar.MONTH),returnMonthInt(driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText()));
	  assertEquals("30d year",Integer.toString(todayDate.get(Calendar.YEAR)),driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());
	  
	  //Test 24h dates:
	  todayDate=Calendar.getInstance();
	  driver.findElement(By.xpath("//a[@class='interval_change_link interval-24h']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr")));
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-24h selected']")));
	  
	  todayDate.add(Calendar.DAY_OF_MONTH,-1);
	  driver.findElement(By.xpath("//div[@class='interval_date_picker']/button/span")).click();
	  Thread.sleep(3000);
	  assertEquals("24h day",Integer.toString(todayDate.get(Calendar.DAY_OF_MONTH)),driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).getText());
	  assertEquals("24h month",todayDate.get(Calendar.MONTH),returnMonthInt(driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText()));
	  assertEquals("24h year",Integer.toString(todayDate.get(Calendar.YEAR)),driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());
  }
  
  //===============================================================================================
  private Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
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
}
  
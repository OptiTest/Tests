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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

@RunWith(BlockJUnit4ClassRunner.class)
public class Keywords extends TestCase {

  //Set test parameters:
  private static ChromeDriverService service;
  private static WebDriver driver;
  Actions builder = new Actions(driver);
  String homeAddress="http://dashboard.optify.net";
  String userName="your username";
  String password="your password";
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
  public void dashboardLogIn() {
	  driver.get(homeAddress+"/login");
	  driver.findElement(By.id("j_username")).sendKeys(userName);
	  driver.findElement(By.id("j_password")).sendKeys(password);
	  driver.findElement(By.id("login_button")).click();
	  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
  }
  @Test
  public void enterToKeywords() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//li[@class='drive']/a/span")));
	  builder.clickAndHold(driver.findElement(By.xpath("//li[@class='drive']/a/span"))).perform();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//html/body/div/div[2]/ul/li[2]/ul/li/a")).click();
	  assertEquals("Keywords page","Keywords | Optify",driver.getTitle());
  }
  
  @Test
  public void helpWithThisPage() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  String winHandleBefore = driver.getWindowHandle();
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='help_for_this_page iconlink']"))).click();
	  Thread.sleep(3000);
	  switcWindow();
	  assertEquals("Help with this page","Keywords application reference : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
  }
  
  @Test
  public void calendar() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  Calendar todayDate=Calendar.getInstance();
	  final int SEVEN = -7;
	  final int THIRTY = -30;
	  
	  //Test 7d dates:
	  todayDate.add(Calendar.DAY_OF_YEAR,SEVEN);
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-7d']")));
	  driver.findElement(By.xpath("//a[@class='interval_change_link interval-7d']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//tr[@class='record hover-menu']")));
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-7d selected']")));
	  
	  driver.findElement(By.xpath("//div[@class='interval_date_picker']/button/span")).click();
	  Thread.sleep(3000);
	  assertEquals("7d day",todayDate.get(Calendar.DAY_OF_MONTH),driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).getText());
	  assertEquals("7d month",todayDate.get(Calendar.MONTH),driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText());
	  assertEquals("7d year",todayDate.get(Calendar.YEAR),driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());
	  
	  //Test 30d dates:
	  todayDate=Calendar.getInstance();
	  todayDate.add(Calendar.DAY_OF_YEAR,THIRTY);
	  driver.findElement(By.xpath("//a[@class='interval_change_link interval-30d']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//tr[@class='record hover-menu']")));
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-30d selected']")));
	  
	  driver.findElement(By.xpath("//div[@class='interval_date_picker']/button/span")).click();
	  Thread.sleep(3000);
	  assertEquals("30d day",todayDate.get(Calendar.DAY_OF_MONTH),driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).getText());
	  assertEquals("30d month",todayDate.get(Calendar.MONTH),driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText());
	  assertEquals("30d year",todayDate.get(Calendar.YEAR),driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());
	  
	  //Test 24h dates:
	  todayDate=Calendar.getInstance();
	  driver.findElement(By.xpath("//a[@class='interval_change_link interval-24h']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//tr[@class='record hover-menu']")));
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-24h selected']")));
	  
	  driver.findElement(By.xpath("//div[@class='interval_date_picker']/button/span")).click();
	  Thread.sleep(3000);
	  assertEquals("24h day",todayDate.get(Calendar.DAY_OF_MONTH),driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).getText());
	  assertEquals("24h month",todayDate.get(Calendar.MONTH),driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText());
	  assertEquals("24h year",todayDate.get(Calendar.YEAR),driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());
  }
  
  @Test 
  public void overview(){
	  driver.findElement(By.xpath("//div[@class='first overview_statbox']/h5/span/span")).click();
	  driver.findElement(By.xpath("//div[@class='last overview_statbox']/h5/span/span")).click();
	  
	  //Adding some equals values test to information display. 
  }
  
  @Test
  public void importKeywordList() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String winHandleBefore = driver.getWindowHandle();
	  
	  driver.findElement(By.xpath("//a[@id='import_keywords_open']")).click();
	                  //We can add some files to upload & equal with the keywords table data base.
	  
	  wait.until(presenceOfElementLocated(By.xpath("//html/body/div[10]/div[2]/div[2]/a")));
	  driver.findElement(By.xpath("//html/body/div[10]/div[2]/div[2]/a")).click();
	  Thread.sleep(3000);
	  switcWindow();
	  assertEquals("How to format your file","Importing and exporting keywords : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//html/body/div[10]/div[3]/div/button[2]")));
	  driver.findElement(By.xpath("//html/body/div[10]/div[3]/div/button[2]")).click();
  }
  
  @Test
  public void exportKeywordList() throws Exception{
	  goBase();
	 
	  driver.findElement(By.xpath("//a[@id='keyword_grid_export']")).click();
	  Thread.sleep(3000);
	 
	                  //Some file check method.
  }
  
  @Test
  public void addKeyword() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String getId="";
	  
	  //Set cc1:
	  driver.findElement(By.xpath("//button[@id='add_keywords_open']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='keyword_add_text']")));
	  driver.findElement(By.xpath("//textarea[@id='keyword_add_text']")).sendKeys("cc1");
	  Thread.sleep(3000);
	  getId=driver.findElement(By.xpath("//div[@class='add-to-list-box']/div/div")).getAttribute("id");
	  builder.clickAndHold(driver.findElement(By.xpath("//html/body/div[8]/div[2]/div[2]/div/div/div"))).perform();
	  Thread.sleep(3000);
	  System.out.print(getId);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li/input")));
	  driver.findElement(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li/input")).sendKeys("test");
	  driver.findElement(By.xpath("//button[@class='orange ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span")).click();
	  
	  //Wait for the add success message:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='success_message hidden-none clear-both']")));
	  builder.release(driver.findElement(By.xpath("//html/body/div[8]/div[2]/div[2]/div/div/div")));
	  
	  //Set cc2:
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//button[@id='add_keywords_open']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='keyword_add_text']")));
	  driver.findElement(By.xpath("//textarea[@id='keyword_add_text']")).sendKeys("cc2");
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//button[@class='orange ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span")).click();
  }
  
 @Test
 public void tableSearch() throws Exception{
	  goBase();
	  
	  //Save cc2 to test group.
	  driver.findElement(By.xpath("//div[@class='data-text-filter data-filter-off']/input")).sendKeys("cc2");
	  Thread.sleep(5000);
	  assertEquals("get cc2","cc2",driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr/td/span/span")).getText());
 }
 
 @Test 
 public void tableActions() throws Exception{
	  goBase();
	  String winHandleBefore = driver.getWindowHandle();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	 
	  //Save cc1 into test group:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='keyword_table']/tbody/tr")));
	  builder.clickAndHold(driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr"))).perform();
	  Thread.sleep(3000);
	  builder.release(driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='Assign to list']"))).perform();
	  builder.release(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='Assign to list']")));
	  Thread.sleep(3000);
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='data-table menu']//span[text()='test']"))).perform();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//div[@class='data-table menu']//span[text()='test']")).click();
	  Thread.sleep(5000);
	  
	  //Check cc1 & cc2 are in test group:
	  driver.findElement(By.xpath("//div[@class='data-text-filter-clear']")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//span[@class='filter_selection tags']//a[text()='test']")).click();
	  Thread.sleep(3000);
	  assertEquals("Equale cc1","cc1",driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr[2]/td/span")).getText());
	  assertEquals("Equale cc2","cc2",driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr/td/span")).getText());
	  
	  //Test view results:
	  
	  //Test Google search:
	  builder.clickAndHold(driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr"))).perform();
	  Thread.sleep(3000);
	  builder.release(driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='View results']"))).perform();
	  builder.release(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='View results']")));
	  Thread.sleep(3000);
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='data-table menu']//div[text()='See results on Google US']"))).perform();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//div[@class='data-table menu']//div[text()='See results on Google US']")).click();
	  Thread.sleep(5000);
	  
	  switcWindow();
	  Thread.sleep(5000);
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  Thread.sleep(3000);
	  
	  //Test Twitter search:
	  builder.clickAndHold(driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr"))).perform();
	  Thread.sleep(3000);
	  builder.release(driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='View results']"))).perform();
	  builder.release(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='View results']")));
	  Thread.sleep(3000);
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='data-table menu']//div[text()='See results on Twitter']"))).perform();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//div[@class='data-table menu']//div[text()='See results on Twitter']")).click();
	  Thread.sleep(5000);
	  
	  switcWindow();
	  Thread.sleep(5000);
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  //Test Bing search:
	  builder.clickAndHold(driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr"))).perform();
	  Thread.sleep(3000);
	  builder.release(driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='View results']"))).perform();
	  builder.release(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='View results']")));
	  Thread.sleep(3000);
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='data-table menu']//div[text()='See results on Bing US']"))).perform();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//div[@class='data-table menu']//div[text()='See results on Bing US']")).click();
	  Thread.sleep(3000);
	  
	  switcWindow();
	  Thread.sleep(3000);
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  //Test Yahoo search:
	  builder.clickAndHold(driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr"))).perform();
	  Thread.sleep(3000);
	  builder.release(driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='View results']"))).perform();
	  builder.release(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='View results']")));
	  Thread.sleep(3000);
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='data-table menu']//div[text()='See results on Yahoo!']"))).perform();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//div[@class='data-table menu']//div[text()='See results on Yahoo!']")).click();
	  Thread.sleep(3000);
	  
	  switcWindow();
	  Thread.sleep(5000);
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  //Remove cc1 & cc2:
	  builder.clickAndHold(driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr"))).perform();
	  Thread.sleep(3000);
	  builder.release(driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='Remove']"))).perform();
	  builder.release(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='Remove']")));
	  driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='Remove']")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//button[@class='confirm-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")).click();
	  Thread.sleep(3000);
	  
	  builder.clickAndHold(driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr"))).perform();
	  Thread.sleep(3000);
	  builder.release(driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='Remove']"))).perform();
	  builder.release(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='Remove']")));
	  driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='Remove']")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//button[@class='confirm-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")).click();
  }
	  
  //@Test
  public void removeKeywords() throws Exception{
	  
  
  }
  
  //@Test 
  public void getSuggestions() throws Exception{
	  driver.findElement(By.xpath("//button[@id='keyword-open-suggest']"));
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
  private void goBase(){
	  if(!driver.getTitle().equals("Keywords | Optify"))
		  driver.get(homeAddress+"/keyword/overview");
  }
  
  //==================================================================================================
  private void switcWindow(){
	  for(String winHandle : driver.getWindowHandles())
		  driver.switchTo().window(winHandle);
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
  
  //==================================================================================================
}
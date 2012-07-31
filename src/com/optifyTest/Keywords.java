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
public class Keywords extends TestCase {

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
  
  //@Test
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
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-7d']")));
	  driver.findElement(By.xpath("//a[@class='interval_change_link interval-7d']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//tr[@class='record hover-menu']")));
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-7d selected']")));
	  
	  driver.findElement(By.xpath("//a[@class='interval_change_link interval-30d']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//tr[@class='record hover-menu']")));
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-30d selected']")));
	  
	  driver.findElement(By.xpath("//a[@class='interval_change_link interval-24h']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//tr[@class='record hover-menu']")));
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-24h selected']")));
	  
	  //Test 24h dates:
	  driver.findElement(By.xpath("//div[@class='interval_date_picker']/button/span")).click();
	  Thread.sleep(3000);
	  System.out.print(driver.findElement(By.xpath("//input[@id='interval_end_date']")));
	  assertEquals("24h day",todayDate.get(Calendar.DAY_OF_MONTH),driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).getText());
	  assertEquals("24h month",todayDate.get(Calendar.MONTH),driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText());
	  assertEquals("24h year",todayDate.get(Calendar.YEAR),driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText());
	  
	  //assertEquals("24h day ",todayDate.get(Calendar.DAY_OF_MONTH),driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).getText());
	  //assertEquals("24h month",todayDate.get(Calendar.MONTH),driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText());
	  //assertEquals("24h year",todayDate.get(Calendar.YEAR),driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText());
	  
	  //Test 7d dates:
	
	  driver.get(homeAddress);
  }
  
  @Test 
  public void overview(){
	  driver.findElement(By.xpath("//div[@class='first overview_statbox']/h5/span/span")).click();
	  driver.findElement(By.xpath("//div[@class='last overview_statbox']/h5/span/span")).click();
	  
	  //Adding some equals values test to information display. 
  }
  
  @Test
  public void KeywordList() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String winHandleBefore = driver.getWindowHandle();
	  
	  //Test import:
	  driver.findElement(By.xpath("//a[@id='import_keywords_open']")).click();
	                  //We can add some files to upload & equal with the keywords table data base.
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='transparent ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")));
	  driver.findElement(By.xpath("//button[@class='transparent ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")).click();
	  
	  //Test export:
	  driver.findElement(By.xpath("//a[@id='keyword_grid_export']")).click();
	  driver.switchTo().alert();
	  builder.sendKeys(Keys.ENTER);
	  driver.switchTo().window(winHandleBefore);
	                  //Some file check method.
  }
  
  @Test
  public void addKeyword() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Set cc1:
	  driver.findElement(By.xpath("//button[@id='add_keywords_open']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='keyword_add_text']")));
	  driver.findElement(By.xpath("//textarea[@id='keyword_add_text']")).sendKeys("cc1");
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='add-to-list-box']")));
	  wait.until(presenceOfElementLocated(By.xpath("//input[@class='add-link-input']")));
	  driver.findElement(By.xpath("//input[@class='add-link-input']")).sendKeys("test");
	  driver.findElement(By.xpath("//button[@class='orange ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span")).click();
	  
	  Thread.sleep(5000);
	  
	  //Set cc2:
	  driver.findElement(By.xpath("//button[@id='add_keywords_open']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='keyword_add_text']")));
	  driver.findElement(By.xpath("//textarea[@id='keyword_add_text']")).sendKeys("cc2");
	  driver.findElement(By.xpath("//button[@class='orange ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span")).click();
	  
	  //Save cc2 to test group.
	  driver.findElement(By.xpath("//input[@id='keyword-text-filter']")).sendKeys("cc2");
	  Thread.sleep(5000);
	  assertEquals("get cc2","cc2",driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr/td")).getText());
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='action button-action action-tags']"))).perform();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//div[@class='contents tag-menu']//span[text()='test']")).click();
	  
	  //Check test list:
	  driver.findElement(By.xpath("//span[@class='icon-remove icon-white']")).click();
	  driver.findElement(By.xpath("//span[@class='filter_selection tags']//a[text()='test']")).click();
	  Thread.sleep(3000);
	  assertEquals("Equale cc1","cc1",driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr/td")).getText());
	  assertEquals("Equale cc2","cc2",driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr/td")).getText());
  }
	  
  @Test
  public void removeKeywords() throws Exception{
	  
  
  }
  
  @Test 
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
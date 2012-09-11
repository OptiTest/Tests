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
  String homeAddress="http://staging.optifyit.com/";
  String userName="Your username";
  String password="Your password";
  static String setPath="D:\\selenium-2.23.1\\chromedriver.exe";
  String keyWord="";
  String keyWordUrl="";
  
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
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  String winHandleBefore = driver.getWindowHandle();
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='help_for_this_page iconlink track']"))).click();
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
	  
	  wait.until(presenceOfElementLocated(By.linkText("How to format your file"))).click();
	 
	  Thread.sleep(3000);
	  
	  switcWindow();
	  
	  assertEquals("How to format your file","Importing and exporting keywords : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//html/body/div[11]/div[3]/div/button[2]/span"))).click();
	  Thread.sleep(10000);
  }
  
  @Test
  public void exportKeywordList() throws Exception{
	  goBase();
	 
	  driver.findElement(By.xpath("//a[@id='keyword_grid_export']")).click();
	  Thread.sleep(3000);
	 
	                  //Some file check method.
  }
  
  @Test
  public void testView() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//select[@id='keyword_view_selection']")));
	  builder.clickAndHold(driver.findElement(By.xpath("//select[@id='keyword_view_selection']"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//select[@id='keyword_view_selection']/option[2]"))).click();
	  builder.clickAndHold(driver.findElement(By.xpath("//select[@id='keyword_view_selection']"))).perform();
	  
	  Thread.sleep(5000);
	  
	  builder.clickAndHold(driver.findElement(By.xpath("//select[@id='keyword_view_selection']"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//select[@id='keyword_view_selection']/option[3]"))).click();
	  builder.clickAndHold(driver.findElement(By.xpath("//select[@id='keyword_view_selection']"))).perform();
	  
	  Thread.sleep(5000);
	  
	  builder.clickAndHold(driver.findElement(By.xpath("//select[@id='keyword_view_selection']"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//select[@id='keyword_view_selection']/option"))).click();
	  builder.clickAndHold(driver.findElement(By.xpath("//select[@id='keyword_view_selection']"))).perform();
	  
	  builder.release(driver.findElement(By.xpath("//select[@id='keyword_view_selection']")));
  }
  
  @Test
  public void editKeywordsReport() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//span[@class='icon-cog white-icon']"))).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//div[@id='keywords_edit_dialog']/dl/dd[6]/input")).click();
	  driver.findElement(By.xpath("//div[@id='keywords_edit_dialog']/dl/dd[7]/input")).click();
	  driver.findElement(By.xpath("//div[@id='keywords_edit_dialog']/dl/dd[8]/input")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//html/body/div[10]/div[3]/div/button/span"))).click();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']//div[text()='Min CPC']")));
	  wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']//div[text()='Max CPC']")));
	  wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']//div[text()='Ranking URL']")));
	  
	  wait.until(presenceOfElementLocated(By.xpath("//span[@class='icon-cog white-icon']"))).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//div[@id='keywords_edit_dialog']/dl/dd[6]/input")).click();
	  driver.findElement(By.xpath("//div[@id='keywords_edit_dialog']/dl/dd[7]/input")).click();
	  driver.findElement(By.xpath("//div[@id='keywords_edit_dialog']/dl/dd[8]/input")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//html/body/div[10]/div[3]/div/button/span"))).click();
  }
  
  @Test
  public void testRanks() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@id='handle_rank_slider_min']")));
	  builder.clickAndHold(driver.findElement(By.xpath("//a[@id='handle_rank_slider_min']"))).perform();
	  builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_min']")), Keys.ARROW_RIGHT).perform();
	  builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_min']")), Keys.ARROW_RIGHT).perform();
	  
	  Thread.sleep(2000);
	  
	  builder.clickAndHold(driver.findElement(By.xpath("//a[@id='handle_rank_slider_max']"))).perform();
	  builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_max']")), Keys.ARROW_LEFT).perform();
	  builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_max']")), Keys.ARROW_LEFT).perform();
	  
	  Thread.sleep(3000);
	  
	  builder.clickAndHold(driver.findElement(By.xpath("//a[@id='handle_rank_slider_min']"))).perform();
	  builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_min']")), Keys.ARROW_LEFT).perform();
	  builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_min']")), Keys.ARROW_LEFT).perform();
	  
	  Thread.sleep(2000);
	  
	  builder.clickAndHold(driver.findElement(By.xpath("//a[@id='handle_rank_slider_max']"))).perform();
	  builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_max']")), Keys.ARROW_RIGHT).perform();
	  builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_max']")), Keys.ARROW_RIGHT).perform();
	  
	  Thread.sleep(3000);
	  
	  driver.findElement(By.xpath("//table[@class='data-table active']/tbody/tr/td[2]/span/a/span")).click();
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//button[@id='keyword_rank_ok']"))).click();
  }
  
  @Test
  public void addKeyword() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String getId="";
	  
	  //Set cc1:
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//button[@id='add_keywords_open']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='keyword_add_text']")));
	  driver.findElement(By.xpath("//textarea[@id='keyword_add_text']")).sendKeys("cc1");
	  Thread.sleep(3000);
	  getId=driver.findElement(By.xpath("//div[@class='add-to-list-box']/div/div")).getAttribute("id");
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@id='"+getId+"']/div/span[2]"))).perform();
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li/input")));
	  driver.findElement(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li/input")).sendKeys("test");
	  driver.findElement(By.xpath("//button[@class='orange ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span")).click();
	
	  //Wait for the add success message:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='success_message hidden-none clear-both']")));
	  builder.release().perform();
	  
	  //Set cc2:
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//button[@id='add_keywords_open']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='keyword_add_text']")));
	  driver.findElement(By.xpath("//textarea[@id='keyword_add_text']")).sendKeys("cc2");
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//button[@class='orange ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span")).click();
  }
  
  @Test 
  public void getSuggestions() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{
		  //Open Get Suggestion:
		  wait.until(presenceOfElementLocated(By.xpath("//button[@id='keyword-open-suggest']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//input[@id='keyword-suggest-input']"))).sendKeys("seo");
		  driver.findElement(By.xpath("//button[@class='suggest-keywords-submit ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span")).click();
		  String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='add-to-list-box']/div/div"))).getAttribute("id");
		  
		  //Save suggested keyword:
		  keyWord=wait.until(presenceOfElementLocated(By.xpath("//div[@class='table-wrapper']/table/tbody/tr/td/div/table/tbody/tr/td[2]/span"))).getText();
		  driver.findElement(By.xpath("//div[@class='table-wrapper']/table/tbody/tr/td/div/table/tbody/tr/td/input")).click();
		  Thread.sleep(3000);
		  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='add-to-list-box']/div/div/div/span"))).perform();
		  System.out.print("//div[@id='"+getId+"-pulldown']//li[text()='test']");
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']//li[text()='test']"))).click();
		  Thread.sleep(3000);
		  
		  driver.findElement(By.xpath("//button[@id='suggest-keywords-save']")).click();
		  Thread.sleep(3000);
		  
		  //Open Get Suggestion:
		  wait.until(presenceOfElementLocated(By.xpath("//button[@id='keyword-open-suggest']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='suggest-type']/input[2]"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//input[@id='keyword-suggest-input']"))).sendKeys("www.optify.net");
		  driver.findElement(By.xpath("//button[@class='suggest-keywords-submit ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span")).click();
		  getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='add-to-list-box']/div/div"))).getAttribute("id");
		  
		  //Save suggested Url keyword:
		  keyWordUrl=wait.until(presenceOfElementLocated(By.xpath("//div[@class='table-wrapper']/table/tbody/tr/td/div/table/tbody/tr/td[2]/span"))).getText();
		  driver.findElement(By.xpath("//div[@class='table-wrapper']/table/tbody/tr/td/div/table/tbody/tr/td/input")).click();
		  Thread.sleep(3000);
		  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='add-to-list-box']/div/div/div/span"))).perform();
		  System.out.print("//div[@id='"+getId+"-pulldown']//li[text()='test']");
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']//li[text()='test']"))).click();
		  Thread.sleep(3000);
		  builder.release(driver.findElement(By.xpath("//div[@class='add-to-list-box']/div/div/div/span")));
		  
		  driver.findElement(By.xpath("//button[@id='suggest-keywords-save']")).click();
	  }
	  catch(WebDriverException ex){
		  wait.until(presenceOfElementLocated(By.xpath("//*[@id='keyword_suggestions']/div[5]/button[1]/span"))).click();
		  throw ex;
	  }
  }
  
  
 @Test
 public void tableSearch() throws Exception{
	  goBase();
	  Thread.sleep(5000);
	  
	  //Search for cc2.
	  driver.findElement(By.xpath("//div[@class='data-text-filter data-filter-off']/input")).sendKeys("cc2");
	  Thread.sleep(5000);
	  assertEquals("get cc2","cc2",driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr/td/span/span")).getText());
 }
 
 @Test 
 public void tableActions() throws Exception{
	  goBase();
	  String winHandleBefore = driver.getWindowHandle();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	 
	  builder.sendKeys(Keys.PAGE_DOWN).perform();
	  Thread.sleep(1000);
	  builder.sendKeys(Keys.PAGE_DOWN).perform();
	  Thread.sleep(1000);
	  builder.sendKeys(Keys.PAGE_DOWN).perform();
	  Thread.sleep(2000);
	  
	  //Save cc2 into test group:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='keyword_table']/tbody/tr")));
	  builder.clickAndHold(driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr"))).perform();
	  Thread.sleep(3000);
	 
	  builder.release(driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='Assign to list']"))).perform();
	  builder.release(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='Assign to list']")));
	  Thread.sleep(3000);
	  
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='data-table menu']/div/ul//span[text()='test']"))).perform();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//div[@class='data-table menu']//span[text()='test']")).click();
	  Thread.sleep(5000);
	  
	  //Check cc1 & cc2 are in test group:
	  driver.findElement(By.xpath("//div[@class='data-text-filter-clear']")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//span[@class='filter_selection tags']//a[text()='test']")).click();
	  Thread.sleep(3000);
	  //assertEquals("Equale cc1","cc1",driver.findElement(By.xpath("//table[@id='keyword_table']/tbody//span[text()='cc1']")).getText());
	  //assertEquals("Equale cc2","cc2",driver.findElement(By.xpath("//table[@id='keyword_table']/tbody//span[text()='cc2']")).getText());
	  
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
	  
	  //Remove:
	  while(!driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr")).getText().equals("Your search has returned no results. Clear search box or adjust your search query.")){
		  builder.clickAndHold(driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr"))).perform();
		  Thread.sleep(3000);
		  builder.release(driver.findElement(By.xpath("//table[@id='keyword_table']/tbody/tr")));
		  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='Remove']"))).perform();
		  builder.release(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='Remove']")));
		  driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='Remove']")).click();
		  Thread.sleep(3000);
		  driver.findElement(By.xpath("//button[@class='confirm-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")).click();
		  Thread.sleep(3000);
	  }
  }
 
  @Test
  public void searchEngines() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//select[@id='search_engine_select']")));
	  builder.clickAndHold(driver.findElement(By.xpath("//select[@id='search_engine_select']"))).perform();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//select[@id='search_engine_select']//option[text()='US, Bing']")).click();
	  builder.clickAndHold(driver.findElement(By.xpath("//select[@id='search_engine_select']"))).perform();
	  Thread.sleep(5000);
	  
	  builder.clickAndHold(driver.findElement(By.xpath("//select[@id='search_engine_select']"))).perform();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//select[@id='search_engine_select']//option[text()='US, Google']")).click();
	  builder.release(driver.findElement(By.xpath("//select[@id='search_engine_select']")));
	  builder.clickAndHold(driver.findElement(By.xpath("//select[@id='search_engine_select']"))).perform();
	  
	  builder.release(driver.findElement(By.xpath("//select[@id='search_engine_select']")));
  }
	  
  @Test
  public void needHelp() throws Exception{
	  goBase();
	  String winHandleBefore = driver.getWindowHandle();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Open need help:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-dialog trainer-minimized']/div/div/div/a"))).click();
	  Thread.sleep(3000);
	  
	  String help1=('"'+"Keywords"+'"'+" is where you'll manage your keyword strategy and track your ranking success. Let's get started!");
	  assertEquals("help 1/9:",help1,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Open video:
	  driver.findElement(By.xpath("//a[@class='launch-video']/img")).click();
	  
	  //Close video:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='video-close']")));
	  driver.findElement(By.xpath("//div[@class='video-close']")).click();
	  Thread.sleep(3000);
	  
	  //Learn more about Keywords:
	  driver.findElement(By.xpath("//div[@class='trainer-actions']/ul/li")).click();
	  switcWindow();
	  assertEquals("Learn more about Keywords:","Keywords application reference : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  //Go forward2:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a")));
	  driver.findElement(By.xpath("//div[@class='trainer-paginator-forward']/a")).click();
	  
	  String help2=("The "+'"'+"Overview"+'"'+" gives you a summary of the value you're driving from organic (unpaid) searches on your target keywords.");
	  assertEquals("help 2/9:",help2,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Watch a video on Keywords:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-actions']//a[text()='Watch a video on Keywords']")));
	  driver.findElement(By.xpath("//div[@class='trainer-actions']//a[text()='Watch a video on Keywords']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='video-close']")));
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all video-dialog ui-draggable']//div[@class='video-close']")).click();
	  Thread.sleep(3000);
	  
	  //Learn more about Keywords:
	  driver.findElement(By.xpath("//div[@class='trainer-actions']//a[text()='Learn more about Keywords']")).click();
	  switcWindow();
	  assertEquals("Learn more about Keywords:","Keywords application reference : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  //Optify Best Practices: Choosing Target keywords:
	  driver.findElement(By.xpath("//div[@class='trainer-actions']//a[text()='Optify Best Practices: Choosing Target keywords']")).click();
	  switcWindow();
	  assertEquals("Optify Best Practices: Choosing Target keywords:","Optify best practices: Choosing target keywords : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  //Go forward3:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a")));
	  driver.findElement(By.xpath("//div[@class='trainer-paginator-forward']/a")).click();
	  
	  String help3=("The Keyword List shows the target keywords you are tracking as well as helpful details to create a successful keyword strategy.");
	  assertEquals("help 3/9:",help3,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Go forward4:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a")));
	  driver.findElement(By.xpath("//div[@class='trainer-paginator-forward']/a")).click();
	  
	  String help4=("Use the Add Keywords button when you know what keywords you want to track. A good starting point is to add words or phrases that describe your product or company.");
	  assertEquals("help 4/9:",help4,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Go forward5:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a")));
	  driver.findElement(By.xpath("//div[@class='trainer-paginator-forward']/a")).click();
	  
	  String help5=("Great job! Now, keyword research is also key to find relevant search terms that can drive quality traffic to your site. Enter a term, phrase or URL and we'll suggest the keywords!");
	  assertEquals("help 5/9:",help5,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Go forward6:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a")));
	  driver.findElement(By.xpath("//div[@class='trainer-paginator-forward']/a")).click();
	  
	  String help6=("There are several actions you can take at the keyword level to stay organized and manage your SEO efforts and campaigns.");
	  assertEquals("help 6/9:",help6,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Go forward7:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a")));
	  driver.findElement(By.xpath("//div[@class='trainer-paginator-forward']/a")).click();
	  
	  String help7=("Assigning keywords to lists helps you focus your SEO efforts. You can use these lists to associate keywords with a campaign, or assign keywords to various projects.");
	  assertEquals("help 7/9:",help7,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Go forward8:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a")));
	  driver.findElement(By.xpath("//div[@class='trainer-paginator-forward']/a")).click();
	  
	  String help8=("We offer several "+'"'+"views"+'"'+" of your keyword data to help with decision making across all stages of your keyword selection, optimization, and performance tracking.");
	  assertEquals("help 8/9:",help8,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Go forward9:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a")));
	  driver.findElement(By.xpath("//div[@class='trainer-paginator-forward']/a")).click();
	  
	  String help9=("And, that's it! If you have additional questions, check out our help pages, send us an email or give us a call at 1-877-2-OPTIFY");
	  assertEquals("help 9/9:",help9,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  Thread.sleep(3000);
	  
	  //Test help:
	  driver.findElement(By.xpath("//div[@class='trainer-text']/a")).click();
	  switcWindow();
	  assertEquals("Help:","Help and Support : Using Optify",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  Thread.sleep(3000);
	  
	  //Next: Take the Pages Tour
	  driver.findElement(By.xpath("//div[@class='trainer-next-actions']/a")).click();
	  assertEquals("Next: Take the Pages Tour:","Pages | Optify",driver.getTitle());
	  driver.navigate().back();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-buttons']/a")));
	  driver.findElement(By.xpath("//div[@class='trainer-buttons']/a")).click();
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
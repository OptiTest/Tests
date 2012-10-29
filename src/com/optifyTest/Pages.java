package com.optifyTest;

import java.io.File;
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
public class Pages extends TestCase {

  //Set test parameters:
  private static ChromeDriverService service;
  private static WebDriver driver;
  Actions builder = new Actions(driver);
  static String homeAddress="https://staging.optifyit.com";
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
	
	enterToPages();
  }
  
  public static void enterToPages() throws Throwable{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  Actions builder = new Actions(driver);
	  
	  driver.get(homeAddress+"/login");
	  driver.findElement(By.id("j_username")).sendKeys(userName);
	  driver.findElement(By.id("j_password")).sendKeys(password);
	  driver.findElement(By.id("login_button")).click();
	  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
	  
	  wait.until(presenceOfElementLocated(By.xpath("//li[@class='drive']/a/span")));
	  builder.clickAndHold(driver.findElement(By.xpath("//li[@class='drive']/a/span"))).perform();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//html/body/div/div[2]/ul/li[2]/ul/li[2]/a")).click();
	  assertEquals("Pages page","Pages | Optify",driver.getTitle());
  }
  
  @Test
  public void addPages() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span"))).click();
	  
	  //Add page & save to test:
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='add-pages-button medium orange ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='add_pages_textarea']"))).sendKeys("www.walla.co.il");
	  Thread.sleep(3000);
	  String getId=driver.findElement(By.xpath("//div[@class='add-to-list-box']/div/div")).getAttribute("id");
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@id='add-pages-dialog']/div/div/div/div/span[2]"))).perform();
	  builder.release(driver.findElement(By.xpath("//div[@id='add-pages-dialog']/div/div/div/div/span[2]")));

	  Thread.sleep(3000);
	  
	  driver.findElement(By.xpath("//div[@id='"+getId+"-pulldown']//input")).sendKeys("test");
	  driver.findElement(By.xpath("//button[@id='add_pages']")).click();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//html/body/div[2]/div[2]/div[3]/div")));
	  assertEquals("Newly added pages","Optify is gathering data for your newly added pages. We'll let you know as soon as we are finished.",wait.until(presenceOfElementLocated(By.xpath("//div[@class='info_message']"))).getText());
	  
	  Thread.sleep(3000);
	  
	  //Add page without adding to group:
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='add-pages-button medium orange ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='add_pages_textarea']"))).sendKeys("www.ynet.co.il");
	  
	  Thread.sleep(3000);
	
	  driver.findElement(By.xpath("//button[@id='add_pages']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//html/body/div[2]/div[2]/div[3]/div")));
	  
	  assertEquals("Newly added pages","Optify is gathering data for your newly added pages. We'll let you know as soon as we are finished.",driver.findElement(By.xpath("//html/body/div[2]/div[2]/div[3]/div")).getText());
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void tableSort() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span"))).click();
	  
	  //Test title sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-title first last']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td/a")));
	  
	  try{driver.findElement(By.xpath("//div[@id='overview_tab']//th[@class='first sorted-desc']"));
	  		
	  }catch(WebDriverException ex){
		  driver.findElement(By.xpath("//div[@class='th-inner tip-title first last']")).click();
	  }
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td/a")));
	  
	  try{if(driver.findElement(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td/a")).getText().compareTo(driver.findElement(By.xpath("//div[@class='ui-tabs ui-widget ui-widget-content ui-corner-all']/div/table/tbody/tr[2]/td/a")).getText())==1)
		  	throw new Exception("Sort down abnormal");
	  	
	  //Check up down sort:
	 	  driver.findElement(By.xpath("//div[@class='th-inner tip-title first last']")).click();
	 	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td/a")));
	 	  
	 	  if(driver.findElement(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td/a")).getText().compareTo(driver.findElement(By.xpath("//div[@class='ui-tabs ui-widget ui-widget-content ui-corner-all']/div/table/tbody/tr[2]/td/a")).getText())==-1)
	  			throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
	  
	  //Test Optify score sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-optifyScore first last']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[2]/div")));
	  
	  try{driver.findElement(By.xpath("//div[@id='overview_tab']//th[@class='align-right sorted-desc']"));
	  		
	  }catch(WebDriverException ex){
		  driver.findElement(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[2]/div")).click();
	  }
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[2]/div")));
	  
	  try{if(driver.findElement(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[2]/div")).getText().compareTo(driver.findElement(By.xpath("//div[@class='ui-tabs ui-widget ui-widget-content ui-corner-all']/div/table/tbody/tr[2]/td[2]/div")).getText())==1)
		  	throw new Exception("Sort down abnormal");
	  
	  //Check up down sort:
	      driver.findElement(By.xpath("//div[@class='th-inner tip-optifyScore first last']")).click();
	      wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[2]/div")));
	 	  
	 	  if(driver.findElement(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[2]/div")).getText().compareTo(driver.findElement(By.xpath("//div[@class='ui-tabs ui-widget ui-widget-content ui-corner-all']/div/table/tbody/tr[2]/td[2]/div")).getText())==-1)
	  			throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
	  
	  //Test Views sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-pageviewsRaw first last']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[3]/div")));
	  
	  try{driver.findElement(By.xpath("//div[@id='overview_tab']//th[@class='align-right sorted-desc']"));
	  		
	  }catch(WebDriverException ex){
		  driver.findElement(By.xpath("//div[@class='th-inner tip-pageviewsRaw first last']")).click();
	  }
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[3]/div")));
	  
	  try{if(driver.findElement(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[3]/div")).getText().compareTo(driver.findElement(By.xpath("//div[@class='ui-tabs ui-widget ui-widget-content ui-corner-all']/div/table/tbody/tr[2]/td[3]/div")).getText())==1)
		  	throw new Exception("Sort down abnormal");
	  	
	  //Check up down sort:
	 	  driver.findElement(By.xpath("//div[@class='th-inner tip-pageviewsRaw first last']")).click();
	 	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[3]/div")));
	 	  
	 	  if(driver.findElement(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[3]/div")).getText().compareTo(driver.findElement(By.xpath("//div[@class='ui-tabs ui-widget ui-widget-content ui-corner-all']/div/table/tbody/tr[2]/td[3]/div")).getText())==-1)
	  			throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
	  
	//Test Links sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-numFollowedDomains first last']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[4]")));
	  
	  try{driver.findElement(By.xpath("//div[@id='overview_tab']//th[@class='align-right sorted-desc']"));
	  		
	  }catch(WebDriverException ex){
		  driver.findElement(By.xpath("//div[@class='th-inner tip-numFollowedDomains first last']")).click();
	  }
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[4]/a")));
	  
	  try{if(driver.findElement(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[4]/div")).getText().compareTo(driver.findElement(By.xpath("//div[@class='ui-tabs ui-widget ui-widget-content ui-corner-all']/div/table/tbody/tr[2]/td[4]/a")).getText())==1)
		  	throw new Exception("Sort down abnormal");
	  	
	  //Check up down sort:
	 	  driver.findElement(By.xpath("//div[@class='th-inner tip-numFollowedDomains first last']")).click();
	 	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[4]/div")));
	 	  
	 	  if(driver.findElement(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[4]/a")).getText().compareTo(driver.findElement(By.xpath("//div[@class='ui-tabs ui-widget ui-widget-content ui-corner-all']/div/table/tbody/tr[2]/td[4]/a")).getText())==-1)
	  			;
  		  
	  }
	  catch(WebDriverException ex){
		  if(driver.findElement(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[4]/div")).getText().compareTo(driver.findElement(By.xpath("//div[@class='ui-tabs ui-widget ui-widget-content ui-corner-all']/div/table/tbody/tr[2]/td[4]/div")).getText())==-1)
		  throw new Exception("Sort up abnormal");
	  }
  }
  
  @Test
  public void inboundsLink() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String winHandleBefore = driver.getWindowHandle();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span"))).click();
	  
	  //Set up down sort for checking:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-numFollowedDomains first last']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[4]")));
	  
	  try{driver.findElement(By.xpath("//div[@id='overview_tab']//th[@class='align-right sorted-desc']"));
	  		
	  }catch(WebDriverException ex){
		  driver.findElement(By.xpath("//div[@class='th-inner tip-numFollowedDomains first last']")).click();
	  }
	  
	  Thread.sleep(3000);
	  
	  //Go to inbounds links:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[4]/a"))).click();
	  
	  //Test "Go to links application":
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='page_links_top_bar']/a"))).click();
	  switcWindow();
	  assertEquals("Go to links application:","Links | Optify",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  //Wait for data:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@class='inner_table']/tbody/tr/td[2]/span/a")));
	  
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='ui-dialog-titlebar-close ui-corner-all']/span"))).click();
  }
  
  @Test
  public void needHelp() throws Exception{
	  goBase();
	  String winHandleBefore = driver.getWindowHandle();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span"))).click();
	  
	  //Open need help:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-dialog trainer-minimized']/div/div/div/a"))).click();
	  Thread.sleep(3000);
	  
	  String help1=("The Pages application analyzes the pages on your website and suggests ways to improve your rankings in search results. Let's get started!");
	  assertEquals("help 1/9:",help1,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  
	  //Learn more about Pages:
	  driver.findElement(By.xpath("//div[@class='trainer-actions']/ul/li/a")).click();
	  switcWindow();
	  assertEquals("Learn more about Pages:","Pages overview : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  //Go forward2:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
	  String help=("The Overview gives you a summary of your website's performance in search engines and shows opportunities for improvement.");
	  assertEquals("help 2/9:",help,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Check qtip-tip:
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Average Optify Score shows how well the pages on your website are optimized for search.']")));
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='These numbers indicate how many issues you have across your site. Each issue is an opportunity to improve your pages and drive up your search rankings.']")));
	  
	  //Go forward3:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
	  help=("The Pages table shows details about how well each of your web pages are optimized for search engines.");
	  assertEquals("help 3/9:",help,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Check qtip-tip:
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Your pages and their Optify scores are listed here.']")));
	  
	  //Go forward4:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
	  help=("Add Pages lets you add one or more web addresses that you are interested in tracking.");
	  assertEquals("help 4/9:",help,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Check qtip-tip:
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Go ahead and click to add some pages!']")));
	  
	  //Go forward5:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
	  help=("The table gives you several page-level tools for staying organized and managing your SEO efforts and campaigns.");
	  assertEquals("help 5/9:",help,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Check qtip-tip: 
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Hover over a row to: ']")));
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()=' lets you organize your pages into custom categories like timeframe or campaign. ']")));
	  
	  //Go forward6:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
	  help=("Lists help you focus your SEO efforts.");
	  assertEquals("help 6/9:",help,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Check qtip-tip:
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Any lists that you create will be displayed here. Selecting a list will display only the pages that have been assigned to that list.']")));
	  
	  //Go forward7:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
	  help=("The Pages tab shows details about how well each of your web pages are optimized for search engines.");
	  assertEquals("help 7/9:",help,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Check qtip-tip:
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Click on a page title now to see details and optimization issues for that page. ']")));
	  
	  driver.findElement(By.xpath("//div[@class='trainer-hide']")).click();
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-dialog trainer-minimized']/div/div/div/a"))).click();
	 
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='done']"))).click();
  }
  
  @Test
  public void showLinks() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  int SUMFIF=50;
	  int SUMTF=25;
	  int SUMTH=10;
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span"))).click();
	  Thread.sleep(3000);
	  //show 50:
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='numPages show-50']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']/tbody")));
	  Thread.sleep(5000);
	  assertEquals("count 50:",SUMFIF,getRowCount(By.xpath("//table[@id='page_table']/tbody")));
	  Thread.sleep(3000);
	  
	  //show 25:
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='numPages show-25']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']/tbody")));
	  Thread.sleep(5000);
	  assertEquals("count 25:",SUMTF,getRowCount(By.xpath("//table[@id='page_table']/tbody")));
	  Thread.sleep(3000);
	  
	  //show 10:
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='numPages show-10']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']/tbody")));
	  Thread.sleep(5000);
	  assertEquals("count 10:",SUMTH,getRowCount(By.xpath("//table[@id='page_table']/tbody")));
	  Thread.sleep(3000);
  }
  
  @Test
  public void nextAndPrev() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Preparation for test:
	  //Set to pages:
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span"))).click();
	  //Set minimum display rows (for max next rate):
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='numPages show-10']"))).click();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='next']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']/tbody")));
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='pages_pager']//a[text()='3']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']/tbody")));
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='previous']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='previous']"))).click();
  }
  
  @Test
  public void tableAction() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String winHandleBefore = driver.getWindowHandle();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span"))).click();
	  
	  wait = new WebDriverWait(driver, 330);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='success_message']/a"))).click();
	  
	  wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-text-filter data-filter-off']/input"))).sendKeys("ynet");
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']//a[text()='ynet חדשות תוכן ועדכונים - ידיעות אחרונות']")));
	  
	  builder.moveToElement(driver.findElement(By.xpath("//tr[@class='record hover-menu last']"))).perform();	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='action button-action action-tags']")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='action button-action action-tags']"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//input[@class='new-tag']")));
	  driver.findElement(By.xpath("//input[@class='new-tag']")).sendKeys("test");
	  Thread.sleep(2000);
	  builder.sendKeys(Keys.ENTER).perform();
	  Thread.sleep(3000);
	  
	  //Go to test group:
	  wait.until(presenceOfElementLocated(By.xpath("//ul[@class='user-lists data-list']//a[text()='test']"))).click();
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-text-filter-clear']"))).click();
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']//a[text()='וואלה!']")));
	  wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']//a[text()='ynet חדשות תוכן ועדכונים - ידיעות אחרונות']")));
	  
	  //Test links:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']/tbody/tr/td/a"))).click();
	  switcWindow();
	  assertEquals("Page Detail:","Website Page | Optify",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']//a[text()='וואלה!']"))).click();
	  switcWindow();
	  assertEquals("link page","וואלה!",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  //Remove pages:
	  builder.moveToElement(driver.findElement(By.xpath("//table[@class='data-table active']/tbody/tr"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']/div[4]"))).click();
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='confirm-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
	  Thread.sleep(3000);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']")));
	  builder.moveToElement(driver.findElement(By.xpath("//table[@class='data-table active']/tbody/tr"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']/div[4]"))).click();
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='confirm-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//*[@id='page-tag-filter']/ul[2]/li[6]/a[2]/span")).click();
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='confirm-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
  }

  @Test
  public void sortIssuesTable() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Load issues table:
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-issues']/span"))).click();
	  
	  //Test Issues sort:=======================================================
	  wait = new WebDriverWait(driver, 350);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='jqgh_rule']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div")));
	
	  wait = new WebDriverWait(driver, 10);
	  
	  try{driver.findElement(By.xpath("//div[@id='jqgh_status']//img[@src='/js/ext/jqGrid/themes/optify/images/sort_desc.gif']"));
	  		
	  }catch(WebDriverException ex){
		  driver.findElement(By.xpath("//div[@id='jqgh_rule']")).click();
	  }
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
	  
	  try{if(driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")).getText().compareTo(driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[3]/td/div/a")).getText())!=-1)
		  	throw new Exception("Sort down abnormal");
	 
	  //Check up down sort:
	 	  driver.findElement(By.xpath("//div[@id='jqgh_rule']")).click();
	 	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
	 	  
	 	  if(driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")).getText().compareTo(driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[3]/td/div/a")).getText())!=1)
	  			throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
	  
	  //Test Impact sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//th[@class='jqgh_impact']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[2]/div")));
	  
	  try{driver.findElement(By.xpath("//div[@id='jqgh_status']//img[@src='/js/ext/jqGrid/themes/optify/images/sort_desc.gif']"));
	  		
	  }catch(WebDriverException ex){
		  driver.findElement(By.xpath("//th[@class='jqgh_impact']")).click();
	  }
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[2]/div")));
	  
	  try{if(driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[2]/div")).getText().compareTo(driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[3]/td[2]/div")).getText())==1)
		  	throw new Exception("Sort down abnormal");
	  
	  //Check up down sort:
	      driver.findElement(By.xpath("//th[@class='jqgh_impact']")).click();
	      wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[2]/div")));
	 	  
	 	  if(driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[2]/div")).getText().compareTo(driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[3]/td[2]/div")).getText())==-1)
	  			throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
	  
	  //Test Category sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='jqgh_category']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[3]/div")));
	  
	  try{driver.findElement(By.xpath("//div[@id='jqgh_status']//img[@src='/js/ext/jqGrid/themes/optify/images/sort_desc.gif']']"));
	  		
	  }catch(WebDriverException ex){
		  driver.findElement(By.xpath("//div[@class='th-inner tip-pageviewsRaw first last']")).click();
	  }
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[3]/div")));
	  
	  try{if(driver.findElement(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr[2]/td[3]/div")).getText().compareTo(driver.findElement(By.xpath("//div[@class='ui-tabs ui-widget ui-widget-content ui-corner-all']/div/table/tbody/tr[3]/td[3]/div")).getText())==1)
		  	throw new Exception("Sort down abnormal");
	  	
	  //Check up down sort:
	 	  driver.findElement(By.xpath("//div[@class='th-inner tip-pageviewsRaw first last']")).click();
	 	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[3]/div")));
	 	  
	 	  if(driver.findElement(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr[2]/td[3]/div")).getText().compareTo(driver.findElement(By.xpath("//div[@class='ui-tabs ui-widget ui-widget-content ui-corner-all']/div/table/tbody/tr[2]/td[3]/div")).getText())==-1)
	  			throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
	  
	  //Test status sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='jqgh_status']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[4]")));
	  
	  try{driver.findElement(By.xpath("//div[@id='jqgh_status']//img[@src='/js/ext/jqGrid/themes/optify/images/sort_desc.gif']"));
	  		
	  }catch(WebDriverException ex){
		  driver.findElement(By.xpath("//div[@class='th-inner tip-numFollowedDomains first last']")).click();
	  }
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[4]/a")));
	  
	  try{if(driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[4]/div")).getText().compareTo(driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[3]/td[4]/a")).getText())!=1)
		  	throw new Exception("Sort down abnormal");
	  	
	  //Check up down sort:
	 	  driver.findElement(By.xpath("//div[@class='th-inner tip-numFollowedDomains first last']")).click();
	 	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[4]/div")));
	 	  
	 	  if(driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[4]/a")).getText().compareTo(driver.findElement(By.xpath("//table[@id='issue_list']/table/tbody/tr[3]/td[4]/a")).getText())!=-1)
	  			;
  		  
	  }
	  catch(WebDriverException ex){
		  if(driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[4]/div")).getText().compareTo(driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[3]/td[4]/div")).getText())==-1)
		  throw new Exception("Sort up abnormal");
	  }
  }
  
  @Test
  public void issuesAddNote() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Add the note test:
	  //Go to Issues:
      wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-issues']/span"))).click();
	  wait = new WebDriverWait(driver, 350);
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
	  wait = new WebDriverWait(driver, 10);
	  
	  builder.click(driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[5]/span/a"))).perform();
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='add_note dropdown_action_links']")));
	  }
	  catch(Exception ex){wait.until(presenceOfElementLocated(By.xpath("//*[@id='1']/td[1]/span/div/a/img"))).click();
	  		Thread.sleep(3000);
	  		builder.click(driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[5]/span/a"))).perform();
	  		wait.until(presenceOfElementLocated(By.xpath("//a[@class='add_note dropdown_action_links']")));
	  }
	  
	  builder.click(driver.findElement(By.xpath("//a[@class='add_note dropdown_action_links']"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/span/div/textarea"))).sendKeys("test");
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='sticky_save']/a"))).click();
	  Thread.sleep(2000);
	  
	  assertEquals("Check note","test",driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/span/div/textarea")).getAttribute("value"));
	  
	  //Delete the note:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='stickynote']/a"))).click();
  }
  
  @Test
  public void setIssueToGroup() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-issues']/span"))).click();
	  wait = new WebDriverWait(driver, 350);
	  String val=wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a[2]"))).getAttribute("href");
	  wait = new WebDriverWait(driver, 10);
	  
	  builder.click(driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[5]/span[2]/a"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//li[@class='create_new input_wrapper']/input"))).sendKeys("test");
	  Thread.sleep(2000);
	  builder.sendKeys(Keys.ENTER).perform();
	  Thread.sleep(3000);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='filter_selection']//a[text()='test']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
	  assertEquals("Check Issue to group:",val,driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a[2]")).getAttribute("href"));
	  
	  //Delete the test group:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='filter_selection']/span[5]/a[2]/span"))).click();
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='confirm-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
  }
  
  @Test
  public void issuesLinks() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String winHandleBefore = driver.getWindowHandle();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-issues']/span"))).click();
	  wait = new WebDriverWait(driver, 350);
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
	  wait = new WebDriverWait(driver, 10);
	  
	  //Check havering qtip:
	  builder.moveToElement(driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='qtip qtip-cream']")));
	  
	  //Check links:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a"))).click();
	  assertEquals("Page Detail:","Website Page | Optify",driver.getTitle());
	  driver.navigate().back();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-issues']/span"))).click();
	  wait = new WebDriverWait(driver, 350);
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
	  wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a[2]"))).click();
	  switcWindow();
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
  }
  
  @Test
  public void issuesPrevNext() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Test preparations:
	  //Set to Issues:
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-issues']/span"))).click();
	  wait = new WebDriverWait(driver, 350);
	  //Wait for table to be loaded:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
	  wait = new WebDriverWait(driver, 10);
	  //Set minimum display rows (for max next rate) 
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='show-10']"))).click();
	  }
	  catch(Exception ex){}
	  
	  //Get the sum of pages:
	  Thread.sleep(2000);
	  String sumIssues=driver.findElement(By.xpath("//span[@class='page_status']")).getText().substring(10);
	  System.out.printf(sumIssues);
	  Thread.sleep(3000);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='next_page']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
	  assertEquals("Check page num 2:","Page 2 of "+sumIssues,driver.findElement(By.xpath("//span[@class='page_status']")).getText());
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='previous_page hidden-none']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
	  assertEquals("Check page num 1:","Page 1 of "+sumIssues,driver.findElement(By.xpath("//span[@class='page_status']")).getText());
  }
  
  @Test
  public void issuesShow() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  int FIFNUM=51;
	  int TFNUM=26;
	  int TENNUM=11;
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-issues']/span"))).click();
	  wait = new WebDriverWait(driver, 350);
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
	  wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='show-50']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
	  assertEquals("Count 50 results:",FIFNUM,getRowCount(By.xpath("//table[@id='issue_list']/tbody")));
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='show-25']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
	  assertEquals("Count 25 results:",TFNUM,getRowCount(By.xpath("//table[@id='issue_list']/tbody")));
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='show-10']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
	  assertEquals("Count 10 results:",TENNUM,getRowCount(By.xpath("//table[@id='issue_list']/tbody")));
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
  private void goBase(){
		  if(!driver.getTitle().equals("Pages | Optify"))
			  driver.get(homeAddress+"/page/overview");
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
}

package com.optifyTest;

import java.io.File;

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
public class PageDetail extends TestCase {

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
	
	enterToPgaeDetail();
  }
  
  public static void enterToPgaeDetail() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  Actions builder = new Actions(driver);
	  
	  //Log in Optify
	  driver.get(homeAddress+"/login");
	  driver.findElement(By.id("j_username")).sendKeys(userName);
	  driver.findElement(By.id("j_password")).sendKeys(password);
	  driver.findElement(By.id("login_button")).click();
	  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
	  
	  //Enter to pages:
	  wait.until(presenceOfElementLocated(By.xpath("//li[@class='drive']/a/span")));
	  builder.clickAndHold(driver.findElement(By.xpath("//li[@class='drive']/a/span"))).perform();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//html/body/div/div[2]/ul/li[2]/ul/li[2]/a")).click();
	  assertEquals("Pages page","Pages | Optify",driver.getTitle());
	  Thread.sleep(3000);
	  
	  //Get the page detail...
	  wait.until(presenceOfElementLocated(By.xpath("//input[@id='page-text-filter']"))).sendKeys("walla");
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td/a"))).click();
	  Thread.sleep(2000);
	  switcWindow();
	  
	  assertEquals("Website Page | Optify:","Website Page | Optify",driver.getTitle());
  }
  
  @Test
  public void helpWithThisPage() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String winHandleBefore = driver.getWindowHandle();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='help_for_this_page iconlink track']/span"))).click();
	  switcWindow();
	  assertEquals("Page Detail : Help and Support:","Page Detail : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
  }

  @Test
  public void export() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='sharing_div']/a"))).click();
  }
  
  @Test
  public void pageLink() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String winHandleBefore = driver.getWindowHandle();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='quiet out']"))).click();
	  switcWindow();
	  assertEquals("link page","וואלה!",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
  }
  
  @Test
  public void socialShare() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Test Social Share:
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//input[@id='account-checkbox-172668869']"))).click();
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='share-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span"))).click();
	  
	  //Test Email Share:
	  wait.until(presenceOfElementLocated(By.xpath("//li[@class='ui-state-default ui-corner-top']"))).click();
	  String getId=driver.findElement(By.xpath("//div[@id='emailTo']/div")).getAttribute("id");
	  Thread.sleep(2000);
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@id='emailTo']/div"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul//label[text()='orasnin@gmail.com']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='share-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//span[@class='post-message success']")));
	  assertEquals("Your email has been sent:","Your email has been sent.",driver.findElement(By.xpath("//span[@class='post-message success']")).getText());
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='close ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
  }
  
  @Test
  public void tableSort() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	//Test Status sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-status first last']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td/div")));
	  
	  try{driver.findElement(By.xpath("//div[@id='overview_tab']//th[@class='sorted-desc']"));
	  		
	  }catch(WebDriverException ex){
		  driver.findElement(By.xpath("//div[@class='th-inner tip-status first last']")).click();
	  }
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td/div")));
	  
	  try{if(driver.findElement(By.xpath("//table[@id='list']/tbody/tr/td/div")).getText().compareTo(driver.findElement(By.xpath("//table[@id='list']/tbody/tr[2]/td/div")).getText())==1)
		  	throw new Exception("Sort down abnormal");
	  
	  //Check up down sort:
	      driver.findElement(By.xpath("//div[@class='th-inner tip-status first last']")).click();
	      wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td/div")));
	 	  
	 	  if(driver.findElement(By.xpath("//table[@id='list']/tbody/tr/td/div")).getText().compareTo(driver.findElement(By.xpath("//table[@id='list']/tbody/tr[2]/td/div")).getText())==-1)
	  			throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
	  
	  //Test issues sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-issues first last']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[2]/a")));
	  
	  try{driver.findElement(By.xpath("//div[@id='overview_tab']//th[@class='sorted-desc']"));
	  		
	  }catch(WebDriverException ex){
		  driver.findElement(By.xpath("//div[@class='th-inner tip-issues first last']")).click();
	  }
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[2]/a")));
	  
	  try{if(driver.findElement(By.xpath("//table[@id='list']/tbody/tr/td[2]/a")).getText().compareTo(driver.findElement(By.xpath("//table[@id='list']/tbody/tr[2]/td[2]/a")).getText())==-1)
		  	throw new Exception("Sort down abnormal");
	  	
	  //Check up down sort:
	 	  driver.findElement(By.xpath("//div[@class='th-inner tip-issues first last']")).click();
	 	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[2]/a")));
	 	  Thread.sleep(2000);
	 	  if(driver.findElement(By.xpath("//table[@id='list']/tbody/tr/td[2]/a")).getText().compareTo(driver.findElement(By.xpath("//table[@id='list']/tbody/tr[2]/td[2]/a")).getText())==1)
	 		  throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
	  
	  //Test impact sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-impact first last']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[3]/div")));
	  
	  try{driver.findElement(By.xpath("//div[@id='overview_tab']//th[@class='sorted-desc']"));
	  		
	  }catch(WebDriverException ex){
		  driver.findElement(By.xpath("//div[@class='th-inner tip-impact first last']")).click();
	  }
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[3]/div")));
	  
	  try{if(driver.findElement(By.xpath("//table[@id='list']/tbody/tr/td[3]/div")).getText().compareTo(driver.findElement(By.xpath("//table[@id='list']/tbody/tr[2]/td[3]/div")).getText())==1)
		  	throw new Exception("Sort down abnormal");
	  
	  //Check up down sort:
	      driver.findElement(By.xpath("//div[@class='th-inner tip-impact first last']")).click();
	      wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[3]/div")));
	 	  
	 	  if(driver.findElement(By.xpath("//table[@id='list']/tbody/tr/td[3]/div")).getText().compareTo(driver.findElement(By.xpath("//table[@id='list']/tbody/tr[2]/td[3]/div")).getText())==-1)
	  			throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
	  
	  //Test Category sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-category first last']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[4]")));
	  
	  try{driver.findElement(By.xpath("//div[@id='overview_tab']//th[@class='sorted-desc']"));
	  		
	  }catch(WebDriverException ex){
		  driver.findElement(By.xpath("//div[@class='th-inner tip-category first last']")).click();
	  }
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[4]")));
	  
	  try{if(driver.findElement(By.xpath("//table[@id='list']/tbody/tr/td[4]")).getText().compareTo(driver.findElement(By.xpath("//table[@id='list']/tbody/tr[2]/td[4]")).getText())==1)
		  	throw new Exception("Sort down abnormal");
	  
	  //Check up down sort:
	      driver.findElement(By.xpath("//div[@class='th-inner tip-category first last']")).click();
	      wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[4]")));
	 	  
	 	  if(driver.findElement(By.xpath("//table[@id='list']/tbody/tr/td[4]")).getText().compareTo(driver.findElement(By.xpath("//table[@id='list']/tbody/tr[2]/td[4]")).getText())==-1)
	  			throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
  }
  
  @Test
  public void tableAction() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Assign to list:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td")));
	  
	  String getIssueName=driver.findElement(By.xpath("//table[@id='list']/tbody/tr/td[2]")).getText();
	  
	  builder.moveToElement(driver.findElement(By.xpath("//table[@id='list']/tbody/tr"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']/div")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='data-table on-hover-menu']/div"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//input[@class='new-tag']"))).sendKeys("test");
	  Thread.sleep(2000);
	  builder.sendKeys(Keys.ENTER).perform();
	  Thread.sleep(3000);
	  
	  //Add note:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td")));
	  builder.moveToElement(driver.findElement(By.xpath("//table[@id='list']/tbody/tr"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']/div[2]"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//textarea[@class='edit-note']"))).sendKeys("test");
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='save-note ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
	  Thread.sleep(3000);
	  assertEquals("Test note value:","test",driver.findElement(By.xpath("//table[@id='list']/tbody/tr/td[2]/div")).getText());
	  Thread.sleep(3000);
	  
	  //Edit note:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td")));
	  builder.moveToElement(driver.findElement(By.xpath("//table[@id='list']/tbody/tr"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']/div[2]"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//textarea[@class='edit-note']"))).sendKeys("123");
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='save-note ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
	  Thread.sleep(3000);
	  assertEquals("Test note value:","test123",driver.findElement(By.xpath("//table[@id='list']/tbody/tr/td[2]/div")).getText());
	  
	  //Remove note:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td")));
	  builder.moveToElement(driver.findElement(By.xpath("//table[@id='list']/tbody/tr"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']/div[3]"))).click();
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='confirm-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
	  Thread.sleep(3000);
	  assertEquals("Test note value:","",driver.findElement(By.xpath("//table[@id='list']/tbody/tr/td[2]/div")).getText());
	  
	  //Check new list:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='filter_selection']//a[text()='test']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td")));
	  
	  try{assertEquals("Check issue name in test group",getIssueName,driver.findElement(By.xpath("//table[@id='list']/tbody/tr/td[2]")).getText());
	  
	  }
	  
	  catch(WebDriverException ex){wait.until(presenceOfElementLocated(By.xpath("//div[@class='filter_selection']//a[text()='test']//span[@class='icon-remove']"))).click();
	  
	  		driver.close();
	  		throw ex;
	  }
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='filter_selection']//a[text()='test']")));
	  driver.findElement(By.xpath("//html/body/div[2]/div[2]/div[5]/div/div/div/div[2]/span[5]/a[2]/span")).click();
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='confirm-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
	  Thread.sleep(3000);
  }
  
  @Test
  public void needHelp() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String winHandleBefore = driver.getWindowHandle();
	  
	  //Open need help:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-dialog trainer-minimized']/div/div/div/a"))).click();
	  Thread.sleep(3000);
	  
	  String help1=("Page Detail shows more information about your page, its keywords and areas to improve. Let's get started!");
	  assertEquals("help 1/5:",help1,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  
	  //Learn more about Page Detail:
	  driver.findElement(By.xpath("//div[@class='trainer-actions']/ul/li/a")).click();
	  switcWindow();
	  assertEquals("Learn more about Page Detail:","Page Detail : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  //Go forward2:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
	  String help=("The Page Detail box shows performance and traffic data as well as links that can affect search engine rankings.");
	  assertEquals("help 2/5:",help,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Check qtip-tip:
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Status shows whether the page is optimized for search engines. An Optify score of more than 75 is considered optimized.']")));
	  
	  //Go forward3:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
	  help=("Optimize With shows which keywords are associated with your page.");
	  assertEquals("help 3/5:",help,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Check qtip-tip:
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Select 1-3 keywords you would like to target for this page.']")));
	  
	  //Go forward4:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
	  help=("Page Optimization Results gives you detailed analysis and suggestions for improving your page's performance.");
	  assertEquals("help 4/5:",help,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Check qtip-tip:
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Status indicates whether or not an issue has been solved.']")));
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Hover over an issue to see more detail.']")));
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Impact shows the relative SEO effect of the issue. Tackle the high impact issues first.']")));
	  
	  //Go forward5:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
	  help=("And, that's it! If you have additional questions, check out our help pages, send us an email or give us a call at 1-877-2-OPTIFY");
	  assertEquals("help 5/5:",help,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Test help:
	  driver.findElement(By.xpath("//div[@class='trainer-text']/a")).click();
	  switcWindow();
	  assertEquals("Help:","Help and Support : Using Optify",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  Thread.sleep(3000);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-buttons']/a")));
	  driver.findElement(By.xpath("//div[@class='trainer-buttons']/a")).click();
  }
  
  @Test
  public void searchInOptimizeWith() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  String val=wait.until(presenceOfElementLocated(By.xpath("//ul[@id='keyword_list']/li"))).getText();
	  wait.until(presenceOfElementLocated(By.xpath("//input[@placeholder='Search keywords']"))).sendKeys(val);
	  Thread.sleep(3000);
	  assertEquals("Search result",val,driver.findElement(By.xpath("//ul[@id='keyword_list']/li")).getText());
  }
  
  @Test
  public void manageKeywords() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='manage-keywords-link']"))).click();
	  assertEquals("Manage keywords link:","Keywords | Optify",driver.getTitle());
	  driver.navigate().back();
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
  private static void switcWindow(){
		  for(String winHandle : driver.getWindowHandles())
			  driver.switchTo().window(winHandle);
	}
  
  //================================================================================================

 }

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
import org.openqa.selenium.JavascriptExecutor;
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
  String homeAddress="http://dashboard.optify.net/";
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
  public void dashboardLogIn1() {
	  driver.get(homeAddress+"/login");
	  driver.findElement(By.id("j_username")).sendKeys(userName);
	  driver.findElement(By.id("j_password")).sendKeys(password);
	  driver.findElement(By.id("login_button")).click();
	  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
  }
  
  @Test
  public void enterToPages2() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//li[@class='drive']/a/span")));
	  builder.clickAndHold(driver.findElement(By.xpath("//li[@class='drive']/a/span"))).perform();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//html/body/div/div[2]/ul/li[2]/ul/li[2]/a")).click();
	  assertEquals("Pages page","Pages | Optify",driver.getTitle());
  }
  
  //@Test
  public void addPages() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Add page & save to test:
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='add-pages-button medium orange ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='add_pages_textarea']"))).sendKeys("www.walla.co.il");
	  Thread.sleep(3000);
	  String getId=driver.findElement(By.xpath("//div[@class='add-to-list-box']/div/div")).getAttribute("id");
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@id='add-pages-dialog']/div/div/div/div/span[2]"))).perform();
	  builder.release(driver.findElement(By.xpath("//div[@id='add-pages-dialog']/div/div/div/div/span[2]")));

	  Thread.sleep(2000);
	  
	  driver.findElement(By.xpath("//div[@id='"+getId+"-pulldown']//input")).sendKeys("test");
	  driver.findElement(By.xpath("//button[@id='add_pages']")).click();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//html/body/div[2]/div[2]/div[3]/div")));
	  assertEquals("Newly added pages","Optify is gathering data for your newly added pages. We'll let you know as soon as we are finished.",driver.findElement(By.xpath("//html/body/div[2]/div[2]/div[3]/div")).getText());
	  
	  Thread.sleep(2000);
	  
	  //Add page without adding to group:
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='add-pages-button medium orange ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='add_pages_textarea']"))).sendKeys("www.ynet.co.il");
	  
	  Thread.sleep(3000);
	
	  driver.findElement(By.xpath("//button[@id='add_pages']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//html/body/div[2]/div[2]/div[3]/div")));
	  
	  assertEquals("Newly added pages","Optify is gathering data for your newly added pages. We'll let you know as soon as we are finished.",driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/div")).getText());
  }
  
  //@Test
  public void sortTable() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
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
  
  //@Test
  public void inboundsLink() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String winHandleBefore = driver.getWindowHandle();
	  
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
	  wait.until(presenceOfElementLocated(By.xpath("//html/body/div[12]/div[2]/div[3]/table/tbody/tr/td/div/table/tbody/tr/td[2]/span/a")));
	  
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='transparent ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
  }
  
  //@Test
  public void needHelp() throws Exception{
	  goBase();
	  String winHandleBefore = driver.getWindowHandle();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
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
  
  //@Test
  public void showLinks() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  int SUMFIF=50;
	  int SUMTF=25;
	  int SUMTH=10;
	  
	  //show 50:
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='numPages show-50']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']/tbody")));
	  Thread.sleep(3000);
	  assertEquals("count 50:",SUMFIF,getRowCount(By.xpath("//table[@id='page_table']/tbody")));
	  
	  //show 25:
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='numPages show-25']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']/tbody")));
	  Thread.sleep(3000);
	  assertEquals("count 25:",SUMTF,getRowCount(By.xpath("//table[@id='page_table']/tbody")));
	  
	  //show 10:
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='numPages show-10']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']/tbody")));
	  Thread.sleep(3000);
	  assertEquals("count 10:",SUMTH,getRowCount(By.xpath("//table[@id='page_table']/tbody")));
  }
  
  //@Test
  public void nextAndPrev() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='next']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']/tbody")));
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='pages_pager']//a[text()='3']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']/tbody")));
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='previous']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='previous']"))).click();
  }
  
  @Test
  public void pagesTableActions3() throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //wait.until(presenceOfElementLocated(By.xpath("//div[@class='success_message']/a"))).click();
	  wait.until(presenceOfElementLocated(By.id("page-text-filter")));
	  driver.findElement(By.xpath("//div[@class='data-text-filter data-filter-off']/input")).sendKeys("ynet");
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']//a[text()='ynet ����� ���� �������� - ������ �������']")));
	  
	  builder.moveToElement(driver.findElement(By.xpath("//tr[@class='record hover-menu last']")));
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='action button-action action-tags']")));
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table menu']//span[text()='test']"))).click();
	  
	  Thread.sleep(3000);
	  
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
		  if(!driver.getTitle().equals("Pages | Optify"))
			  driver.get(homeAddress+"/page/overview");
	}
	
  //==================================================================================================
  private void switcWindow(){
		  for(String winHandle : driver.getWindowHandles())
			  driver.switchTo().window(winHandle);
	}
	
  //==================================================================================================
  private void scrollPage(WebElement webElementObject) throws Exception{
		 ((JavascriptExecutor) driver).executeScript( 
	             "arguments[0].scrollIntoView(true);", webElementObject); 
		 
		for (int second = 0;; second++) {
	        if(second >=10){
	            break;
	        }
	            ((JavascriptExecutor) driver).executeScript("window.open(href, windowname, 'width=400,height=150,scrollbars=yes');",webElementObject);
	            Thread.sleep(1000);
	        }
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
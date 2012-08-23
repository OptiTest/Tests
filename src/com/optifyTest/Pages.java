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
  public void enterToPages() throws Exception{
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
  
  @Test
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
}

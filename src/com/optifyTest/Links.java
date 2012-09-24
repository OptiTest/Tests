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
public class Links extends TestCase {

  //Set test parameters:
  private static ChromeDriverService service;
  private static WebDriver driver;
  Actions builder = new Actions(driver);
  String homeAddress="https://dashboard.optify.net";
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
  public void enterToLinks() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Log in Optify
	  driver.get(homeAddress+"/login");
	  driver.findElement(By.id("j_username")).sendKeys(userName);
	  driver.findElement(By.id("j_password")).sendKeys(password);
	  driver.findElement(By.id("login_button")).click();
	  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
	  
	  //Enter to Links:
	  wait.until(presenceOfElementLocated(By.xpath("//li[@class='drive']/a/span")));
	  builder.clickAndHold(driver.findElement(By.xpath("//li[@class='drive']/a/span"))).perform();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//html/body/div/div[2]/ul/li[2]/ul/li[3]/a")).click();
	  assertEquals("Links page","Links | Optify",driver.getTitle());
	  Thread.sleep(3000);
  }
  
  @Test
  public void addUrls() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='view links_actions']/div/button"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='links_add_urls']"))).sendKeys("www.ynet.co.il");
	  Thread.sleep(2000);
	  
	  String getId=driver.findElement(By.xpath("//div[@id='list-control']/div")).getAttribute("id");
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='add-to-list ']/div/div/span[2]"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']//input[@placeholder='Create new list']"))).sendKeys("test");
	  Thread.sleep(2000);
	  builder.sendKeys(Keys.ENTER).perform();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//button[@id='add-url-save']/span")).click();
	  
	  Thread.sleep(5000);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='view links_actions']/div/button"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='links_add_urls']"))).sendKeys("www.walla.co.il");
	  Thread.sleep(2000);
	  
	  driver.findElement(By.xpath("//button[@id='add-url-save']/span")).click();
	  
	  Thread.sleep(5000);
	  
	  addNewFilter();
  }
  
  @Test
  public void addNewFilter() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Add filters
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='report-filter-list']/div[2]/span[2]")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@id='report-filter-list']/div[2]/span[2]"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='report-filter-list-pulldown']/ul/li"))).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//li[@class='token-input-input-token']/input")).sendKeys("ynet");
	  builder.sendKeys(Keys.ENTER).perform();
	  driver.findElement(By.xpath("//li[@class='token-input-input-token']/input")).sendKeys("walla");
	  builder.sendKeys(Keys.ENTER).perform();
	  
	  //Check received values:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']//a[text()='http://www.ynet.co.il']")));
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']//a[text()='http://www.walla.co.il']")));
	  
	  //Go over all values in table:
	  int sumOfRows=getRowCount(By.xpath("//table[@id='links-table']"));
	  for(int i=1; i<sumOfRows;i++){
		  if((driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr["+i+"]/td/span/a")).getAttribute("title").matches("(?i).*ynet.*")||driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr["+i+"]/td/span/a")).getAttribute("title").matches("(?i).*walla.*")))
			  ;
		  
		  else throw new Exception("Filter values hasn't be found!");
	  }
	  
	  //Delete filters:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-link_source_url0']/ul/li[2]/span"))).click();
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-link_source_url0']/ul/li/span"))).click();
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//span[@class='icon-remove icon-white']"))).click();
  }
  
  @Test
  public void assignToList() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Add url into new list:
	  String value=driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr/td/span/a")).getAttribute("title");
	  
	  builder.moveToElement(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr/td/span/a"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='action button-action action-tags']")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='action button-action action-tags']"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//input[@class='new-tag']"))).sendKeys("test");
	  Thread.sleep(2000);
	  builder.sendKeys(Keys.ENTER).perform();
	  Thread.sleep(3000);
	  
	  //Check values in list:
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@id='filter-list-pulldown']/div/span[2]"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-list-pulldown-pulldown']//label[text()='test']"))).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@id='filter-list-pulldown']/div/span[2]")).click();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']//a[text()='http://www.ynet.co.il']")));
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']//a[text()='"+value+"']")));
	  
	  Thread.sleep(3000);
	  
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@id='filter-list-pulldown']/div/span[2]"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-list-pulldown-pulldown']//label[text()='test']"))).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@id='filter-list-pulldown']/div/span[2]")).click();
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

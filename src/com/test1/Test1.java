package com.test1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.HasInputDevices;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Mouse;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

@RunWith(BlockJUnit4ClassRunner.class)
public class Test1 extends TestCase {

  private static ChromeDriverService service;
  private static WebDriver driver;

  @BeforeClass
  public static void createAndStartService() throws Throwable {
    service = new ChromeDriverService.Builder()
    	.usingDriverExecutable(new File("D:\\selenium-2.23.1\\chromedriver.exe"))
        .usingAnyFreePort()
        .build();
	service.start();
	
	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	String[] listCapability={"--ignore-certificate-errors", "--start-maximized"};
	capabilities.setCapability("chrome.switches", listCapability);
	driver = new RemoteWebDriver(service.getUrl(),capabilities);
  }
  
  @Test
  public void dashboardLogIn() {
	  driver.get("http://dashboard.optify.net/login");
	  driver.findElement(By.id("j_username")).sendKeys("orasnin@gmail.com");
	  driver.findElement(By.id("j_password")).sendKeys("wrwmfy9m");
	  driver.findElement(By.id("login_button")).click();
	  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
  }
  
  @Test
  public void seoWidget() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.linkText("Average score")));
	  driver.findElement(By.linkText("Average score")).click();
	  assertEquals("Average score","Pages | Optify",driver.getTitle());
	  driver.get("http://dashboard.optify.net");
	  
	  wait.until(presenceOfElementLocated(By.linkText("Homepage score")));
	  driver.findElement(By.linkText("Homepage score")).click();
	  assertEquals("Homepage score","Website Page | Optify",driver.getTitle());
	  driver.get("http://dashboard.optify.net");
	  
	  wait.until(presenceOfElementLocated(By.linkText("Estimated click value")));
	  driver.findElement(By.linkText("Estimated click value")).click();
	  assertEquals("Estimated click value","Keywords | Optify",driver.getTitle());
	  driver.get("http://dashboard.optify.net");
  }
  
  /*@Test
  public void gettingStartedWidget() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  Actions builder = new Actions(driver);
	  String winHandleBefore = driver.getWindowHandle();
	  
	  //Link1:===============================================================================================
	  wait.until(presenceOfElementLocated(By.linkText("1. Create your target keywords list")));
	  builder.clickAndHold(driver.findElement(By.linkText("1. Create your target keywords list"))).perform();
	  driver.findElement(By.linkText("LEARN MORE")).click();
	  
	  Thread.sleep(3000);
	  
	  switcWindow();
	  assertEquals("Lerarn more[1]","What are Keywords? : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  driver.findElement(By.linkText("1. Create your target keywords list")).click();
	  assertEquals("1. Create your target keywords list","Keywords | Optify",driver.getTitle());
	  driver.get("http://dashboard.optify.net");
	  
	  //Link2:===============================================================================================
	  wait.until(presenceOfElementLocated(By.linkText("2. Optimize your website pages")));
	  builder.clickAndHold(driver.findElement(By.linkText("2. Optimize your website pages"))).perform();
	  driver.findElement(By.linkText("LEARN MORE")).click();
	  
	  Thread.sleep(3000);
	  
	  switcWindow();
	  assertEquals("Lerarn more[2]","Pages overview : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  driver.findElement(By.linkText("2. Optimize your website pages")).click();
	  assertEquals("2. Optimize your website pages","Pages | Optify",driver.getTitle());
	  driver.get("http://dashboard.optify.net");
	  
	  //Link3:===============================================================================================
	  wait.until(presenceOfElementLocated(By.linkText("3. View and manage your leads")));
	  builder.clickAndHold(driver.findElement(By.linkText("3. View and manage your leads"))).perform();
	  driver.findElement(By.linkText("LEARN MORE")).click();
	  
	  Thread.sleep(3000);
	  
	  switcWindow();
	  assertEquals("Lerarn more[3]","Lead Intelligence overview : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  driver.findElement(By.linkText("3. View and manage your leads")).click();
	  assertEquals("3. View and manage your leads","Lead Intelligence | Optify",driver.getTitle());
	  driver.get("http://dashboard.optify.net");
	  
	  //Link4:===============================================================================================
	  wait.until(presenceOfElementLocated(By.linkText("4. Create a Twitter campaign")));
	  builder.clickAndHold(driver.findElement(By.linkText("4. Create a Twitter campaign"))).perform();
	  driver.findElement(By.linkText("LEARN MORE")).click();
	  
	  Thread.sleep(3000);
	  
	  switcWindow();
	  assertEquals("Lerarn more[4]","Twitter for Business overview : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  driver.findElement(By.linkText("4. Create a Twitter campaign")).click();
	  assertEquals("4. Create a Twitter campaign","Twitter for Business | Optify",driver.getTitle());
	  driver.get("http://dashboard.optify.net");
  }*/
  
  @Test public void TwitterForBusinessWidget() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String account;
	  String message = testMessage();
	  
	  //Test tweet:
	 /* driver.findElement(By.cssSelector("textarea.tweet-text.tweetbox")).sendKeys(message);
	  driver.findElement(By.xpath("//div[@id='dashboard']/div[7]/div[2]/div[2]/div[2]/button")).click();
	  Thread.sleep(5000);*/
	  wait.until(presenceOfElementLocated(By.linkText("Twitter for Business")));
	  System.out.print(driver.findElement(By.xpath("//*[@id='dashboard']/div[8]/div[2]/div[1]/span/div[1]/div[1]/span[1]")).getText());
	  driver.findElement(By.linkText("Twitter for Business")).click();
	  wait.until(presenceOfElementLocated(By.className("inbox_tweet")));
	  driver.findElement(By.className("inbox_tweet"));
	  System.out.print(driver.findElement(By.className("inbox_tweet")).getAttribute("textContent"));
	  System.out.print(driver.findElement(By.className("headline_tweet")).getAttribute("textContent"));
	  Thread.sleep(5000);
  }
	    
  //===============================================================================================
  Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
	    return new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            return driver.findElement(locator);
	          }
       };
  }
  
  //================================================================================================
  void switcWindow(){
	  for(String winHandle : driver.getWindowHandles())
		  driver.switchTo().window(winHandle);
  }
  
  //================================================================================================
  String testMessage(){
	  return Integer.toString((int)((System.currentTimeMillis()/(1000*60*60))%24))+
	         Integer.toString((int) ((System.currentTimeMillis()/(1000*60))%60));
  }
  
  //================================================================================================
  public By byDom(String domExpression) {
	  final Object o = ((JavascriptExecutor) driver).executeScript("return " + domExpression + ";");
	
	  if (o instanceof WebElement) {
	      return new By() {
	          @Override
	          public List<WebElement> findElements(SearchContext searchContext) {
	              return new ArrayList<WebElement>() {
	                  {
	                      add((WebElement) o);
	                  }
	              };
	          }
	      };
	  }
	return null;
	}

} 


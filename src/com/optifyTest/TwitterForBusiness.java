package com.optifyTest;

import java.io.File;
import java.util.Calendar;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

@RunWith(BlockJUnit4ClassRunner.class)
public class TwitterForBusiness extends TestCase  {
	
  //Set test parameters:
  private static ChromeDriverService service;
  private static WebDriver driver;
  Actions builder = new Actions(driver);
  static String homeAddress="https://dashboard.optify.net";
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
	
	enterToTwitterForBusiness();
  }
  
  public static void enterToTwitterForBusiness() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  Actions builder = new Actions(driver);
	  
	  //Log in Optify:
	  driver.get(homeAddress+"/login");
	  driver.findElement(By.id("j_username")).sendKeys(userName);
	  driver.findElement(By.id("j_password")).sendKeys(password);
	  driver.findElement(By.id("login_button")).click();
	  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
	  
	  //Enter to Twitter For Business:
	  wait.until(presenceOfElementLocated(By.xpath("//li[@class='enable']/a/span")));
	  builder.clickAndHold(driver.findElement(By.xpath("//li[@class='drive']/a/span"))).perform();
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//*[@id='header-nav']/li[2]/ul/li[4]/a"))).click();
	  assertEquals("Twitter for Business:","Twitter for Business | Optify",driver.getTitle());
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void postTwitt() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String message = testMessage();
	  
	  //Test twitt:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_text']/textarea"))).sendKeys(message);
	  wait.until(presenceOfElementLocated(By.xpath("//a[@id='post_tweet_submit']/img"))).click();
	  Thread.sleep(5000);
	  
	  //Get account owner name:
	  String account = wait.until(presenceOfElementLocated(By.xpath("//*[@id='twitter_screenname']//option[@selected='true']"))).getText();
	  
	  //Check if twitt message has been sent:
	  driver.navigate().refresh();
	  String getMessage=wait.until(presenceOfElementLocated(By.className("inbox_tweet"))).getAttribute("textContent");
	  assertEquals("Message",message,getMessage.substring(account.length()+1, 1+account.length()+message.length()));
	  
	  Thread.sleep(3000);
  } 
  
  @Test
  public void helpWithThisPage() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='help_for_this_page iconlink track']")));
	  String winHandleBefore = driver.getWindowHandle();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='help_for_this_page iconlink track']"))).click();
	  switcWindow();
	  assertEquals("Help with this page:","Twitter for Business overview : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  Thread.sleep(3000);
  } 
  
  @Test
  public void overview() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String winHandleBefore = driver.getWindowHandle();
	  
	  //Updates:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='last overview_statbox']/h5/a"))).click();
	  switcWindow();
	  Thread.sleep(3000);
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  //Followers:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='overview_fields']/small/a"))).click();
	  switcWindow();
	  Thread.sleep(3000);
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  //Followers:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='overview_fields']/small[2]/a"))).click();
	  switcWindow();
	  Thread.sleep(3000);
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void calendar() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  Calendar todayDate=Calendar.getInstance();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_date']/button/span"))).click();
	  Thread.sleep(3000);
	  
	  assertEquals("Day:",Integer.toString(todayDate.get(Calendar.DAY_OF_MONTH)), wait.until(presenceOfElementLocated(By.xpath("//a[@class='ui-state-default ui-state-highlight ui-state-active ui-state-hover']"))).getText());
	  assertEquals("Month:",todayDate.get(Calendar.MONTH),returnMonthInt(driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText()));
	  assertEquals("Year:",Integer.toString(todayDate.get(Calendar.YEAR)),driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_date']/button/span"))).click();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void pstList() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_time']/div/span[2]")))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_time-pulldown']/ul/li[2]"))).click();
	  Thread.sleep(3000);
	  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_time']/div/span[2]")))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_time-pulldown']/ul/li"))).click();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void campaignList() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_campaign_id']/div/span[2]")))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_campaign_id-pulldown']/ul/li[2]"))).click();
	  Thread.sleep(3000);
	  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_campaign_id']/div/span[2]")))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_campaign_id-pulldown']/ul/li"))).click();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void newCampaigns() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);

	  wait.until(presenceOfElementLocated(By.xpath("//button[@id='add_new_campaign']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//span[@id='ui-dialog-title-campaign_dialog']")));
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only transparent']/span"))).click();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void campaignsActions() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);

	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-campaigns']/span"))).click();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='action action-tweet button-action']"))).click();

	  builder.moveToElement(wait.until(presenceOfElementLocated(By.xpath("//*[@id='campaign_grid']/tbody/tr")))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//*[@id='campaign_grid']/tbody/tr[1]/td[4]/div[2]"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='ui-dialog-buttonpane ui-widget-content ui-helper-clearfix']/div/button[2]"))).click();
	  
	  Thread.sleep(3000);
  }
  
  @Test 
  public void userPopup() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-inbox']/span"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='inbox_grid']/tbody/tr/td[2]/div/span/a"))).click();
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='profile_box_close']"))).click();
	  
	  Thread.sleep(3000);
  }
  
  @Test 
  public void nextAndPrev() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Get into Inbox tab:
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-inbox']/span"))).click();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='next_page']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='show-3']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='previous_page hidden-none']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='previous_page hidden-none']"))).click();
	  
	  Thread.sleep(3000);
  }
  
  @Test 
  public void tableActionsManageList() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Get into Inbox tab:
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-inbox']/span"))).click();
	  
	  //Create new list:
	  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='action button-action action-tags']")))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//input[@class='new-tag']"))).sendKeys("test");
	  Thread.sleep(2000);
	  builder.sendKeys(Keys.ENTER).perform();
	  
	  //Delete new list (test list):
	  wait.until(presenceOfElementLocated(By.xpath("//ul[@class='user-lists data-list']//a[text()='test']"))).click();
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//ul[@class='user-lists data-list']/li/a[2]"))).click();
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='confirm_delete_ok']"))).click();
	  
	  Thread.sleep(3000);
  }
  
  @Test 
  public void tableActionsReplay() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  String account = wait.until(presenceOfElementLocated(By.xpath("//*[@id='twitter_screenname']//option[@selected='true']"))).getText();
	  String message = "@"+account+"test";
	  
	  //Get into Inbox tab:
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-inbox']/span"))).click();
	  
	  //Click replay:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='action action-reply button-action']"))).click();
	  Thread.sleep(3000);
	  
	  //Check replay output:
      wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_text']/textarea"))).sendKeys(message);
      Thread.sleep(2000);
      wait.until(presenceOfElementLocated(By.xpath("//a[@id='post_tweet_submit']/img"))).click();
	  Thread.sleep(3000);
	  driver.navigate().refresh();
	  
	  String getMessage=wait.until(presenceOfElementLocated(By.className("inbox_tweet"))).getAttribute("textContent");
	  assertEquals("Message",message,getMessage.substring(account.length()+1, (1+account.length()+message.length())));
	  
	  Thread.sleep(3000);
  }
  
  @Test 
  public void tableActionsRetweet() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  final int SIZE=4;
	  String account = wait.until(presenceOfElementLocated(By.xpath("//*[@id='twitter_screenname']//option[@selected='true']"))).getText();
	  String message = "RT @"+account+" "+wait.until(presenceOfElementLocated(By.className("inbox_tweet"))).getAttribute("textContent").substring(account.length()+1, (1+account.length()+SIZE));
	  
	  //Get into Inbox tab:
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-inbox']/span"))).click();
	  
	  //Click Retweet:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='action action-retweet button-action']"))).click();
      
      //Check replay output:
	  wait.until(presenceOfElementLocated(By.xpath("//a[@id='post_tweet_submit']/img"))).click();
	  Thread.sleep(3000);
	  driver.navigate().refresh();
	  String getMessage=wait.until(presenceOfElementLocated(By.className("inbox_tweet"))).getAttribute("textContent");
	  assertEquals("Message",message,getMessage.substring(account.length()+1, (1+account.length()+message.length())));
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void search() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Get into Inbox tab:
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-monitor']/span"))).click();
	  
	  //Add "ad" to search:
	  wait.until(presenceOfElementLocated(By.xpath("//input[@id='search-twitter-input']"))).sendKeys("ad");
	  Thread.sleep(2000);
	  builder.sendKeys(Keys.ENTER).perform();
	  
	  //Save "ad":
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='search-action']/a"))).click();
	  
	  //Check if saved "ad" exist & use it:
	  wait.until(presenceOfElementLocated(By.xpath("//ul[@id='twitter_searches']//a[text()='ad']"))).click();
	  
	  //Delete it:
	  wait.until(presenceOfElementLocated(By.xpath("//ul[@id='twitter_searches']/li/a[2]/span"))).click();
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='confirm_delete_ok']/button"))).click();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void campaignResults() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Get into Campaigns tab:
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-campaigns']/span"))).click();
	  Thread.sleep(3000);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='see-campaign-results']/a"))).click();
	  assertEquals("Campaign results:","Traffic Report | Optify",driver.getTitle());
	  
	  driver.navigate().back();
	
	  Thread.sleep(3000);
  }
  
  @Test
  public void outBox() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Get into Campaigns tab:
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-outbox']/span"))).click();
	  
	  String message = testMessage();
	  
	  //Send twitt:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_text']/textarea"))).sendKeys(message);
	  wait.until(presenceOfElementLocated(By.xpath("//a[@id='post_tweet_submit']/img"))).click();
	  Thread.sleep(5000);
	  
	  //Get account owner name:
	  String account = wait.until(presenceOfElementLocated(By.xpath("//*[@id='twitter_screenname']//option[@selected='true']"))).getText();
	  
	  //Check if twitt message appear in the outBox:
	  String getMessage=wait.until(presenceOfElementLocated(By.className("past_tweet"))).getAttribute("textContent");
	  assertEquals("Message",message,getMessage.substring(account.length()+1, 1+account.length()+message.length()));
	  
	  Thread.sleep(3000);
  }
  //===================================================================================================
  private static Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
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
  
//================================================================================================
  private String testMessage(){
	  return Integer.toString((int)((System.currentTimeMillis()/(1000*60*60))%24))+
	         Integer.toString((int) ((System.currentTimeMillis()/(1000*60))%60));
  }
}

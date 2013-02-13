package com.optifyTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import junit.framework.TestCase;

import org.junit.AfterClass;
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
	public static MainMenu ts=new MainMenu();
	public static Settings st=new Settings();
	
	//Set test parameters:
	private static ChromeDriverService service;
	private static WebDriver driver;
	Actions builder = new Actions(driver);
	static String homeAddress=st.getServerUrl();
	static String userName=ts.getUserName();
    static String password=ts.getUserPassword();
	static String setPath="selenium/Linux32/chromedriver";
  
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
	  
	  print("\n\nStarting Twitter for business:\n");
	  
	  print("\n\nLogin to Optify.");
	  
	  //Log in Optify:
	  driver.get(homeAddress+"/login");
	  driver.findElement(By.id("j_username")).sendKeys(userName);
	  driver.findElement(By.id("j_password")).sendKeys(password);
	  driver.findElement(By.id("login_button")).click();
	  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
	  
	  System.out.println(" v");
	  
	  System.out.print("\nEnter to Twitter for business.");
	  
	  //Enter to Twitter For Business:
	  wait.until(presenceOfElementLocated(By.xpath("//li[@class='enable']/a/span")));
	  builder.clickAndHold(driver.findElement(By.xpath("//li[@class='drive']/a/span"))).perform();
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//*[@id='header-nav']/li[2]/ul/li[4]/a"))).click();
	  assertEquals("Twitter for Business:","Twitter for Business | Optify",driver.getTitle());
	  
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void postTwitt() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  String message = testMessage();
	  
	  System.out.println("\n\nTesting show results:");
	  print("Testing send twitt.");
	  postTwitt_send(numTry,message);
	  System.out.println(" v");
	  print("Checking sent twitt.");
	  postTwitt_checkSend(numTry,message);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  } 
  
  @Test
  public void helpWithThisPage() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nHelp with this page.");
	  helpWithThisPage_test(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  } 
  
  @Test
  public void overview() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  String winHandleBefore = driver.getWindowHandle();
	  
	  System.out.println("\n\nTesting overview:");
	  print("Testing Updates.");
	  overview_updates(numTry,winHandleBefore);
	  System.out.println(" v");
	  print("Testing Followers.");
	  overview_followers(numTry,winHandleBefore);
	  System.out.println(" v");
	  print("Testing Following.");
	  overview_Following(numTry,winHandleBefore);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void calendar() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting calendar.");
	  calendar_test(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void pstList() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting PST list.");
	  pstList_test(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void campaignList() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting Campaign list.");
	  campaignList_test(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void newCampaigns() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting New Campaigns.");
	  newCampaigns_test(numTry);
	  System.out.println(" v");

	  Thread.sleep(3000);
  }
  
  @Test
  public void campaignsActions() throws Exception{
	  int numTry=0; //Counter the number of attempts.

	  print("\n\nTesting Campaigns actions.");
	  campaignsActions_test(numTry);
	  System.out.println(" v");
	 
	  Thread.sleep(3000);
  }
  
  @Test 
  public void userPopup() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting user popup.");
	  userPopup_test(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test 
  public void nextAndPrev() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting Next and prev.");
	  nextAndPrev_test(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test 
  public void tableActionsManageList() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting table actions manager list:");
	  print("\n\nTesting creating new list.");
	  tableActionsManageList_creatNewList(numTry);
	  System.out.println(" v");
	  print("\n\nTesting delete new list.");
	  tableActionsManageList_deleteList(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test 
  public void tableActionsReplay() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting table actions replay:");
	  print("\n\nTesting click replay.");
	  String message=tableActionsReplay_click(numTry);
	  System.out.println(" v");
	  print("\n\nTesting replay message.");
	  tableActionsReplay_message(numTry,message);
	  System.out.println(" v");
	 
	  Thread.sleep(3000);
  }
  
  @Test 
  public void tableActionsRetweet() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting table actions retweet:");
	  print("\n\nTesting click retweet.");
	  tableActionsRetweet_click(numTry);
	  System.out.println(" v");
	  print("\n\nTesting replay output.");
	  tableActionsRetweet_replayOutput(numTry);
	  System.out.println(" v");
	
	  Thread.sleep(3000);
  }
  
  @Test
  public void search() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting search:");
	  print("\n\nTesting search for 'ad'.");
	  search_searchForAd(numTry);
	  System.out.println(" v");
	  print("\n\nTesting save 'ad'.");
	  search_save(numTry);
	  System.out.println(" v");
	  print("\n\nTesting delete saved search object.");
	  search_deleteSavedObject(numTry);
	  System.out.println(" v");
	 
	  Thread.sleep(3000);
  }
  
  @Test
  public void campaignResults() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting campaign results.");
	  campaignResults_test(numTry);
	  System.out.println(" v");
	
	  Thread.sleep(3000);
  }
  
  @Test
  public void outBox() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting out box.");
	  outBox_test(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void addAccount() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting add acount.");
	  addAccount_test(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test 
  public void switchAccount() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting switch acount.");
	  switchAccount_test(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @AfterClass
  public static void summary(){
	  driver.close();
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
	  final String MONTH_STR[]={"January","February","March","April","May","June","July","August",
			  "September","October","November","December"};
	  
	  for(int i=0;i<SUM_MONTH;i++){
		 if(month.equals(MONTH_STR[i]))
			 return i;
	  }

	  return -1;
  }
  
  //================================================================================================
  private String testMessage(){
	  return Integer.toString((int)((System.currentTimeMillis()/(1000*60*60))%24))+
	         Integer.toString((int) ((System.currentTimeMillis()/(1000*60))%60));
  }
  
  //=========================================================================================================
  private void postTwitt_send(int numTry,String message)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{//Test twitt:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_text']/textarea"))).sendKeys(message);
		  wait.until(presenceOfElementLocated(By.xpath("//a[@id='post_tweet_submit']/img"))).click();
		  Thread.sleep(5000);
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 postTwitt_send(numTry,message);
	  }
  }
  
  //=========================================================================================================
  private void postTwitt_checkSend(int numTry,String message)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Get account owner name:
		  String account = wait.until(presenceOfElementLocated(By.xpath("//*[@id='twitter_screenname']//option[@selected='true']"))).getText();
		  //Check if twitt message has been sent:
		  driver.navigate().refresh();
		  String getMessage=wait.until(presenceOfElementLocated(By.className("inbox_tweet"))).getAttribute("textContent");
		  assertEquals("Message",message,getMessage.substring(account.length()+1, 1+account.length()+message.length()));
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 postTwitt_checkSend(numTry,message);
	  }
  }
  
  //=========================================================================================================
  private void helpWithThisPage_test(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='help_for_this_page iconlink track']")));
		  String winHandleBefore = driver.getWindowHandle();
		  
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='help_for_this_page iconlink track']"))).click();
		  switcWindow();
		  assertEquals("Help with this page:","Twitter for Business overview : Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 helpWithThisPage_test(numTry);
	  }
  }
  
  //=========================================================================================================
  private void overview_updates(int numTry,String winHandleBefore)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Updates:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='last overview_statbox']/h5/a"))).click();
		  switcWindow();
		  Thread.sleep(3000);
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 overview_updates(numTry,winHandleBefore);
	  }
  }
 
  //==========================================================
  private void overview_followers(int numTry,String winHandleBefore)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	
	  try{//Followers:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='overview_fields']/small/a"))).click();
		  switcWindow();
		  Thread.sleep(3000);
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 overview_followers(numTry,winHandleBefore);
	  }
  }
  
  //=========================================================================================================
  private void overview_Following(int numTry,String winHandleBefore)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Following:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='overview_fields']/small[2]/a"))).click();
		  switcWindow();
		  Thread.sleep(3000);
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 overview_Following(numTry,winHandleBefore);
	  }
  }
  
  //=========================================================================================================
  private void calendar_test(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  Calendar todayDate=Calendar.getInstance();
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_date']/button/span"))).click();
		  Thread.sleep(3000);
		  
		  assertEquals("Day:",Integer.toString(todayDate.get(Calendar.DAY_OF_MONTH)), wait.until(presenceOfElementLocated(By.xpath("//a[@class='ui-state-default ui-state-highlight ui-state-active ui-state-hover']"))).getText());
		  assertEquals("Month:",todayDate.get(Calendar.MONTH),returnMonthInt(wait.until(presenceOfElementLocated(By.xpath("//span[@class='ui-datepicker-month']"))).getText()));
		  assertEquals("Year:",Integer.toString(todayDate.get(Calendar.YEAR)),wait.until(presenceOfElementLocated(By.xpath("//span[@class='ui-datepicker-year']"))).getText());
		  
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_date']/button/span"))).click();
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 calendar_test(numTry);
	  }
  }
  
  //=========================================================================================================
  private void  pstList_test(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_time']/div/span[2]")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_time-pulldown']/ul/li[2]"))).click();
		  Thread.sleep(3000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_time']/div/span[2]")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_time-pulldown']/ul/li"))).click();
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 pstList_test(numTry);
	  }
  }
  
  //=========================================================================================================
  private void campaignList_test(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_campaign_id']/div/span[2]")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_campaign_id-pulldown']/ul/li[2]"))).click();
		  Thread.sleep(3000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_campaign_id']/div/span[2]")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_campaign_id-pulldown']/ul/li"))).click();
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 campaignList_test(numTry);
	  }
  }
  
  //=========================================================================================================
  private void newCampaigns_test(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);

	  try{wait.until(presenceOfElementLocated(By.xpath("//button[@id='add_new_campaign']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//span[@id='ui-dialog-title-campaign_dialog']")));
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only transparent']/span"))).click();
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 newCampaigns_test(numTry);
	  }
  }
  
  //=========================================================================================================
  private void campaignsActions_test(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-campaigns']/span"))).click();
  
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='action action-tweet button-action']"))).click();
		
		  builder.moveToElement(wait.until(presenceOfElementLocated(By.xpath("//*[@id='campaign_grid']/tbody/tr")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//*[@id='campaign_grid']/tbody/tr[1]/td[4]/div[2]"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='ui-dialog-buttonpane ui-widget-content ui-helper-clearfix']/div/button[2]"))).click();
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 campaignsActions_test(numTry);
	  }
  }
  
  //=========================================================================================================
  private void userPopup_test(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-inbox']/span"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='inbox_grid']/tbody/tr/td[2]/div/span/a"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='profile_box_close']"))).click();
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 userPopup_test(numTry);
	  }
  }
  
  //=========================================================================================================
  private void nextAndPrev_test(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Get into Inbox tab:
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-inbox']/span"))).click();
		  
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='next_page']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='show-3']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='previous_page hidden-none']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='previous_page hidden-none']"))).click();
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 nextAndPrev_test(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableActionsManageList_creatNewList(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Get into Inbox tab:
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-inbox']/span"))).click();
		  
		  //Create new list:
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='action button-action action-tags']")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//input[@class='new-tag']"))).sendKeys("test");
		  Thread.sleep(2000);
		  builder.sendKeys(Keys.ENTER).perform();
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 tableActionsManageList_creatNewList(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableActionsManageList_deleteList(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Delete new list (test list):
		  wait.until(presenceOfElementLocated(By.xpath("//ul[@class='user-lists data-list']//a[text()='test']"))).click();
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//ul[@class='user-lists data-list']/li/a[2]"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='confirm_delete_ok']"))).click();
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 tableActionsManageList_deleteList(numTry);
	  }
  }
  
  //=========================================================================================================
  private String tableActionsReplay_click(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String message="";
	  
	  try{String account = wait.until(presenceOfElementLocated(By.xpath("//*[@id='twitter_screenname']//option[@selected='true']"))).getText();
		  message = "@"+account+"test";
		  
		  //Get into Inbox tab:
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-inbox']/span"))).click();
		  
		  //Click replay:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='action action-reply button-action']"))).click();
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
 	    //Restart test
 	    numTry++;
	 	Thread.sleep(2000);
	 	tableActionsReplay_click(numTry);
	  }
	  
	  return message;
  }
  
  //=========================================================================================================
  private void tableActionsReplay_message(int numTry,String message)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String account = wait.until(presenceOfElementLocated(By.xpath("//*[@id='twitter_screenname']//option[@selected='true']"))).getText();
	  
	  try{//Check replay output:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='post_tweet_text']/textarea"))).sendKeys(message);
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//a[@id='post_tweet_submit']/img"))).click();
		  Thread.sleep(3000);
		  driver.navigate().refresh();
		  
		  String getMessage=wait.until(presenceOfElementLocated(By.className("inbox_tweet"))).getAttribute("textContent");
		  assertEquals("Message",message,getMessage.substring(account.length()+1, (1+account.length()+message.length())));
	  }
	  
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	     Thread.sleep(2000);
	 	 tableActionsReplay_message(numTry,message);
	  }
  }
  
  //=========================================================================================================
  private void tableActionsRetweet_click(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{//Get into Inbox tab:
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-inbox']/span"))).click();
		  
		  //Click Retweet:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='action action-retweet button-action']"))).click();
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 tableActionsRetweet_click(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableActionsRetweet_replayOutput(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  final int SIZE=4;
 
	  try{ String account = wait.until(presenceOfElementLocated(By.xpath("//*[@id='twitter_screenname']//option[@selected='true']"))).getText();
	       String message = "RT @"+account+" "+wait.until(presenceOfElementLocated(By.className("inbox_tweet"))).getAttribute("textContent").substring(account.length()+1, (1+account.length()+SIZE));
		  
		  //Check replay output:
		  wait.until(presenceOfElementLocated(By.xpath("//a[@id='post_tweet_submit']/img"))).click();
		  Thread.sleep(3000);
		  driver.navigate().refresh();
		  String getMessage=wait.until(presenceOfElementLocated(By.className("inbox_tweet"))).getAttribute("textContent");
		  assertEquals("Message",message,getMessage.substring(account.length()+1, (1+account.length()+message.length())));
	  }
	  
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 tableActionsRetweet_replayOutput(numTry);
	  }
  }
  
  //=========================================================================================================
  private void search_searchForAd(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Get into Inbox tab:
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-monitor']/span"))).click();
		  
		  //Add "ad" to search:
		  wait.until(presenceOfElementLocated(By.xpath("//input[@id='search-twitter-input']"))).sendKeys("ad");
		  Thread.sleep(2000);
		  builder.sendKeys(Keys.ENTER).perform();
	  }
	  
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 search_searchForAd(numTry);
	  }
  }
  
  //=========================================================================================================
  private void search_save(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);  
  
	  try{//Save "ad":
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='search-action']/a"))).click();
		  
		  //Check if saved "ad" exist & use it:
		  wait.until(presenceOfElementLocated(By.xpath("//ul[@id='twitter_searches']//a[text()='ad']"))).click();
	  }
	  
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 search_save(numTry);
	  }
  }
  
  //=========================================================================================================
  private void search_deleteSavedObject(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);  
  
	  try{//Delete it:
		  wait.until(presenceOfElementLocated(By.xpath("//ul[@id='twitter_searches']/li/a[2]/span"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='confirm_delete_ok']/button"))).click();
	  }
	  
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 search_deleteSavedObject(numTry);
	  }
  }
  
  //=========================================================================================================
  private void campaignResults_test(int numTry)throws Exception{
  	  WebDriverWait wait = new WebDriverWait(driver, 10);    
  
  	  try{//Get into Campaigns tab:
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-campaigns']/span"))).click();
		  Thread.sleep(3000);
		  
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='see-campaign-results']/a"))).click();
		  assertEquals("Campaign results:","Traffic Report | Optify",driver.getTitle());
		  
		  driver.navigate().back();
  	  }
  	  
  	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 campaignResults_test(numTry);
	  }
  }
  
  //=========================================================================================================
  private void outBox_test(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Get into Campaigns tab:
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
	  }
	  
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 outBox_test(numTry);
	  }
  }
  
  //=========================================================================================================
  private void addAccount_test(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//select[@id='twitter_screenname']")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//select[@id='twitter_screenname']//option[text()='Add a Twitter Account...']"))).click();
		  Thread.sleep(3000);
		  assertEquals("Add account:","������ / ����� �����", driver.getTitle());
		  
		  driver.navigate().back();
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 outBox_test(numTry);
	  }
  }
  
  //=========================================================================================================
  private void switchAccount_test(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//select[@id='twitter_screenname']")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//select[@id='twitter_screenname']/option[2]"))).click();
		  Thread.sleep(3000);
		  
		  try{assertEquals("Check account name:",wait.until(presenceOfElementLocated(By.xpath("//select[@id='twitter_screenname']//option[@selected='true']"))).getText(), wait.until(presenceOfElementLocated(By.xpath("//div[@id='tweet_control_me']/div"))).getText());
		  }
		  catch(Exception e){
			  driver.get(homeAddress+"/twitter/overview");
			  throw e;
		  }
		  
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//select[@id='twitter_screenname']")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//select[@id='twitter_screenname']/option[3]"))).click();
	  }
	  catch(Exception e){
	 	 if(numTry>3)
	 		  throw e;
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 outBox_test(numTry);
	  }
  }
  
  //==========================================================================================================
  private static void print(String action){
	  FileWriter fstreamWrite=null;
	  
	  System.out.print(action);
	  
	  try{fstreamWrite = new FileWriter("data/actionStram");
		 }catch(IOException e) {
		 	// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
		BufferedWriter out = new BufferedWriter(fstreamWrite);
		try {out.write(action);
			 out.close();
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
  }
}
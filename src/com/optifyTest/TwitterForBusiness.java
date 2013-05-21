package com.optifyTest;

import static org.junit.Assume.assumeTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
	static String setPath=st.getSeleniumBit();
	static public String object;
	static public String pageName=new Object(){}.getClass().getEnclosingClass().getSimpleName();
	static public double time=0;
	static List<String>scripList; //Loads all enable script list.
	boolean junit=false;           //The default should be false. True for JUnit test only!
  
  @BeforeClass
  public static void createAndStartService() throws Throwable {
	scripList=getScriptList();
	
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
	  time=System.currentTimeMillis();
	  object="";
	  
	  System.out.println();
	  print("Login to Optify.");
	  
	  //Log in Optify:
	  driver.get(homeAddress+"/login");
	  driver.findElement(By.id("j_username")).sendKeys(userName);
	  driver.findElement(By.id("j_password")).sendKeys(password);
	  driver.findElement(By.id("login_button")).click();
	  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
	  
	  printSuccess();
	  
	  print("Enter to Twitter for business.");
	  
	  //Enter to Twitter For Business:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='optify-nav-menu-icon']/span"))).click();
	  Thread.sleep(2000);
      wait.until(presenceOfElementLocated(By.xpath("//*[@id='main-menu-content']/div/div/div[2]/div[4]/div[2]/a"))).click();
      assertEquals("Twitter for Business:","Twitter for Business | Optify",driver.getTitle());
	  
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void postTwitt() throws Exception{
	  assumeTrue(enable("postTwitt")); 
	  int numTry=0; //Counter the number of attempts.
	  String message = testMessage();
	  
	  object="Testing show results";
	  
	  print("Testing send twitt.");
	  postTwitt_send(numTry,message);
	  printSuccess();
	  print("Checking sent twitt.");
	  postTwitt_checkSend(numTry,message);
	  printSuccess();
	  
	  Thread.sleep(3000);
  } 
  
  @Test
  public void helpWithThisPage() throws Exception{
	  assumeTrue(enable("helpWithThisPage")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Help with this page link";
	  
	  print("Entering Help with this page.");
	  helpWithThisPage_test(numTry);
	  printSuccess();
	  
	  Thread.sleep(3000);
  } 
  
  @Test
  public void overview() throws Exception{
	  assumeTrue(enable("overview")); 
	  int numTry=0; //Counter the number of attempts.
	  String winHandleBefore = driver.getWindowHandle();
	  
	  object="Overview bar";
	  
	  print("Testing Updates.");
	  overview_updates(numTry,winHandleBefore);
	  printSuccess();
	  print("Testing Followers.");
	  overview_followers(numTry,winHandleBefore);
	  printSuccess();
	  print("Testing Following.");
	  overview_Following(numTry,winHandleBefore);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void calendar() throws Exception{
	  assumeTrue(enable("calendar")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Calendar";
	  
	  print("Testing calendar.");
	  calendar_test(numTry);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void pstList() throws Exception{
	  assumeTrue(enable("pstList")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Pst list";
	  
	  print("Testing PST list.");
	  pstList_test(numTry);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void campaignList() throws Exception{
	  assumeTrue(enable("campaignList")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Campaign list";
	  
	  print("Testing Campaign list.");
	  campaignList_test(numTry);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void newCampaigns() throws Exception{
	  assumeTrue(enable("newCampaigns")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="New Campaigns button";
	  
	  print("Testing New Campaigns.");
	  newCampaigns_test(numTry);
	  printSuccess();

	  Thread.sleep(3000);
  }
  
  @Test
  public void campaignsActions() throws Exception{
	  assumeTrue(enable("campaignsActions")); 
	  int numTry=0; //Counter the number of attempts.

	  object="Campaigns actions";
	  
	  print("Testing Campaigns actions.");
	  campaignsActions_test(numTry);
	  printSuccess();
	 
	  Thread.sleep(3000);
  }
  
  @Test 
  public void userPopup() throws Exception{
	  assumeTrue(enable("userPopup")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="User popup";
	  
	  print("Testing user popup.");
	  userPopup_test(numTry);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test 
  public void nextAndPrev() throws Exception{
	  assumeTrue(enable("nextAndPrev")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Next & Prev";
	  
	  print("Testing Next and prev.");
	  nextAndPrev_test(numTry);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test 
  public void tableActionsManageList() throws Exception{
	  assumeTrue(enable("tableActionsManageList")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Table actions manager list";
	  
	  print("Testing creating new list.");
	  tableActionsManageList_creatNewList(numTry);
	  printSuccess();
	  print("Testing delete new list.");
	  tableActionsManageList_deleteList(numTry);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test 
  public void tableActionsReplay() throws Exception{
	  assumeTrue(enable("tableActionsReplay")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Table actions replay";
	  
	  print("Testing click replay.");
	  String message=tableActionsReplay_click(numTry);
	  printSuccess();
	  print("Testing replay message.");
	  tableActionsReplay_message(numTry,message);
	  printSuccess();
	 
	  Thread.sleep(3000);
  }
  
  @Test 
  public void tableActionsRetweet() throws Exception{
	  assumeTrue(enable("tableActionsRetweet")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Table actions retweet";
	  
	  print("Testing click retweet.");
	  tableActionsRetweet_click(numTry);
	  printSuccess();
	  print("Testing replay output.");
	  tableActionsRetweet_replayOutput(numTry);
	  printSuccess();
	
	  Thread.sleep(3000);
  }
  
  @Test
  public void search() throws Exception{
	  assumeTrue(enable("search")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="search";
	  
	  print("Testing search for 'ad'.");
	  search_searchForAd(numTry);
	  printSuccess();
	  print("Testing save 'ad'.");
	  search_save(numTry);
	  printSuccess();
	  print("Testing delete saved search object.");
	  search_deleteSavedObject(numTry);
	  printSuccess();
	 
	  Thread.sleep(3000);
  }
  
  @Test
  public void campaignResults() throws Exception{
	  assumeTrue(enable("campaignResults")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="campaign results";
	  
	  print("Testing campaign results.");
	  campaignResults_test(numTry);
	  printSuccess();
	
	  Thread.sleep(3000);
  }
  
  @Test
  public void outBox() throws Exception{
	  assumeTrue(enable("outBox")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Out Box";
	  
	  print("Testing out box.");
	  outBox_test(numTry);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void addAccount() throws Exception{
	  assumeTrue(enable("addAccount")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Add acount";
	  
	  print("Testing add acount.");
	  addAccount_test(numTry);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test 
  public void switchAccount() throws Exception{
	  assumeTrue(enable("switchAccount")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Switch account";
	  
	  print("Testing switch acount.");
	  switchAccount_test(numTry);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @AfterClass
  public static void summary(){
	  driver.close();
	  driver.quit();
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
		  }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
		  }
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 postTwitt_checkSend(numTry,message);
	  }
  }
  
  //=========================================================================================================
  private void helpWithThisPage_test(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{String winHandleBefore = driver.getWindowHandle();
		  
		  wait.until(presenceOfElementLocated(By.xpath("//i[@class='icon-help-circle nav-icon nav-icon-white optify-nav-button-icon']"))).click();
		  switcWindow();
		  assertEquals("Help with this page:","Twitter for Business overview : Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
		  if(numTry>2){
				 printFailed();
				 throw e; 
		  }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
		  }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
		  }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
		  }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
		  }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
		  }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
		  }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
		  }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
		  }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
		  }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
		  }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
		  }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
		  }
	 	
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
	  
	  try{message = "test"+System.currentTimeMillis()%100;
		  
		  //Get into Inbox tab:
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-inbox']/span"))).click();
		  
		  //Click replay:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='action action-reply button-action']"))).click();
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		 if(numTry>2){
			 printFailed();
			 throw e; 
		 }
		 
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
		  assertEquals("Message","@"+account+message,getMessage.substring(account.length()+1, (1+account.length()+("@"+account+message).length())));
	  }
	  
	  catch(Exception e){
		  if(numTry>2){
				 printFailed();
				 throw e; 
		  }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
			 }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
			 }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
			 }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
			 }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
			 }
	 	
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
  		if(numTry>2){
			 printFailed();
			 throw e; 
		 }
	 	
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
		  if(numTry>2){
				 printFailed();
				 throw e; 
			 }
	 	
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
		  assertEquals("Add account:","Twitter / Authorize an application", driver.getTitle());
		  
		  driver.navigate().back();
	  }
	  catch(Exception e){
		 //Restart test
		 if(!driver.getTitle().equals("Twitter for Business | Optify"))
	 			driver.navigate().back();
		  
	 	 if(numTry>2){
	 		printFailed();
	 		throw e;
	 	 }
	 	
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
		  if(numTry>2){
		 		printFailed();
		 		throw e;
		 	 }
	 	
	 	 //Restart test
	 	 numTry++;
	 	 Thread.sleep(2000);
	 	 outBox_test(numTry);
	  }
  }
  
  //==========================================================================================================
  private static void print(String action){
	  FileWriter fstreamWrite=null;
	  
	  System.out.printf("%-40s",action);
	  
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
  
  //===========================================================================
  public boolean enable(String name){
	 if(junit)
		return true;
	  
     for(String elem:TwitterForBusiness.scripList)
    	 if(elem!=null && elem.toString().equals(name))
    		 return true;
      
    return false; 
  }
  
  //===========================================================================
  private static List<String> getScriptList(){
	  	//Load all file info into Contact List.
		BufferedReader reader=null;
		File file=new File("data/data3");
		List<String> list=new ArrayList<String>();
		String line="";
		
		try { FileReader fstreamRead=new FileReader(file);
		  reader=new BufferedReader(fstreamRead);
		  line = reader.readLine();
		  
		  while(line!=null){
			  list.add(line);
			  line = reader.readLine();
		  }
		  
		  reader.close();
	
		} catch (Exception e) {
		// TODO Auto-generated catch block
			System.out.println("Can't load scripts list from file data/data3!");
			e.printStackTrace();
		}
		
	 	return list;
  }
  
  //=================================================================
  public static void printSuccess(){
	  double sumTime=(System.currentTimeMillis()-time)/1000;
	  System.out.printf("%-5s","Success");
	  System.out.printf("%5.0f",(sumTime/60)%60);
	  System.out.printf(".%-5.0f",sumTime%60);
	  System.out.printf("%-30s %s%n",object,pageName);
	  time=System.currentTimeMillis();
  }
  
  //=================================================================
  public static void printFailed(){
	  double sumTime=(System.currentTimeMillis()-time)/1000;
	  System.out.printf("%-7s","Failed");
	  System.out.printf("%5.0f",(sumTime/60)%60);
	  System.out.printf(".%-5.0f",sumTime%60);
	  System.out.printf("%-30s %s%n",object,pageName);
	  time=System.currentTimeMillis();
  }
}
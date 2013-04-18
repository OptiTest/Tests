package com.optifyTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

  @RunWith(BlockJUnit4ClassRunner.class)
  public class DashBoard extends TestCase {
  public static MainMenu ts=new MainMenu();
  public static Settings st=new Settings();
 
  
  //Set test parameters:
  public static ChromeDriverService service;
  public static WebDriver driver;
  Actions builder = new Actions(driver);
  
  static String homeAddress="https://dashboard.optify.net";
  static String userName="orasnin@gmail.com";
  static String setPath="selenium\\chromedriver.exe";
  static String password="wrwmfy9m";

  @BeforeClass
  public static void createAndStartService() throws Throwable {
	service = new ChromeDriverService.Builder()
    	.usingDriverExecutable(new File(setPath))
        .usingAnyFreePort()
        .build();
	service.start();
	
	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	String[] listCapability={"--start-minimized","--disable-extensions","--disable-translate"};
	capabilities.setCapability("chrome.switches", listCapability);
	driver = new RemoteWebDriver(service.getUrl(),capabilities);
	
	System.out.println("\n");
	
	dashboardLogIn(driver);
  }
  
  public static void dashboardLogIn(WebDriver driver) throws Throwable{
	  print("Login to Optify.");
	  
	  driver.get(homeAddress+"/login");
	  driver.findElement(By.id("j_username")).sendKeys(userName);
	  driver.findElement(By.id("j_password")).sendKeys(password);
	  driver.findElement(By.id("login_button")).click();
	  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
	  
	  System.out.println(" v");
  }
  
  @Test
  public void seoWidget() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting SEO performance widget:");
	  print("Entering Average score.");
	  seoWidget_EnteringAverageScore(numTry);
	  System.out.println(" v");
	  print("Entering Homepage score.");
	  seoWidget_EnteringHomepageScore(numTry);
	  System.out.println(" v");
	  print("Entering Estimated click value.");
	  seoWidget_EnteringEstimatedClickValue(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void gettingStartedWidget() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting Getting started widget:");
	  print("Entering 1.Create your target keywords list.");
	  gettingStartedWidget_CreateYourTargetKeywords(numTry);
	  System.out.println(" v");
	  print("Entering 1.Create your target keywords list LEARN MORE.");
	  gettingStartedWidget_CreateYourTargetKeywords_LEARN_MORE(numTry);
	  System.out.println(" v");
	  print("Entering 2.Optimize your website pages.");
	  gettingStartedWidget_OptimizeYourWebsitPages(numTry);
	  System.out.println(" v");
	  print("Entering 2.Optimize your website pages LEARN MORE.");
	  gettingStartedWidget_OptimizeYourWebsitPages_LEARN_MORE(numTry);
	  System.out.println(" v");
	  print("Entering 3.View and manage your leads.");
	  gettingStartedWidget_ViewAndManageYourLeads(numTry);
	  System.out.println(" v");
	  print("Entering 3.View and manage your leads LEARN MORE.");
	  gettingStartedWidget_ViewAndManageYourLeads_LEARN_MORE(numTry);
	  System.out.println(" v");
	  print("Entering 4.Create a Twitter campaign.");
	  gettingStartedWidget_CreateATwitterCampaign(numTry);
	  System.out.println(" v");
	  print("Entering 4.Create a Twitter campaign LEARN MORE.");
	  gettingStartedWidget_CreateATwitterCampaign_LEARN_MORE(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test 
  public void twitterForBusinessWidget() throws Exception{
	  int numTry=0;	//Counter the number of attempts.
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.linkText("Twitter for Business")));
	  
	  System.out.println("\n\nTesting Twitter for Business widget:");
	  print("Entering title link.");
	  twitterForBusinessWidget_titleLink(numTry);
	  System.out.println(" v");	  
	  print("Sending twitt.");
	  twitterForBusinessWidget_sendingTwitt(numTry);
	  System.out.println(" v");
	  print("Testing Social monitor.");
	  twitterForBusinessWidget_socialMonitor(numTry);
	  System.out.println(" v");
	  print("Testing drop down list.");
	  twitterForBusinessWidget_dropDownList(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void keywordPerformanceWidget() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  int numTry=0; //Counter the number of attempts.
	  goBase();
	  wait.until(presenceOfElementLocated(By.className("keyword_widget_title")));
	  
	  System.out.println("\n\nTesting Keyword Performance widget:");
	  
	  print("Entering title link.");
	  keywordPerformanceWidget_EnteringTitleLink(numTry);
	  System.out.println(" v");
	  print("Testing rank1 label.");
	  keywordPerformanceWidget_rank1(numTry);
	  System.out.println(" v");
	  print("Testing rank2 label.");
	  keywordPerformanceWidget_rank2(numTry);
	  System.out.println(" v");
	  print("Testing drop down list (search engine).");
	  keywordPerformanceWidget_searchEngineDropDawn(numTry);
	  System.out.println(" v");
	  print("Testing drop down list (Lists).");
	  keywordPerformanceWidget_dropDownList(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
	 
  @Test
  public void pageOptimizationWidget() throws Exception{
	  goBase();
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nPage Optimization widget:");
	  
	  print("Entering title link.");
	  pageOptimizationWidget_enteringTitleLink(numTry);
	  System.out.println(" v"); 
	  print("Entering Optify page score link.");
	  pageOptimizationWidget_enteringPageScoreLink(numTry);
	  System.out.println(" v");  
	  print("Testing drop down list.");
	  pageOptimizationWidget_dropDownList(numTry);
	  System.out.println(" v");  
	  print("Testing score bar.");
	  pageOptimizationWidget_testingScoreBar(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void linkOpportunitiesWidget() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  int numTry=0; //Counter the number of attempts.
	  goBase();
	  
	  System.out.println("\n\nLink Opportunities widget:");
	  
	  print("Entering title link.");
	  linkOpportunitiesWidget_enteringTitleLink(numTry);
	  System.out.println(" v");
	  print("Testing drop down list (View).");
	  linkOpportunitiesWidget_dropDownListView(numTry);
	  System.out.println(" v"); 
	  print("Testing drop down list (List).");
	  linkOpportunitiesWidget_dropDownList(numTry);
	  System.out.println(" v");
	  print("Testing Add more URLs.");
	  linkOpportunitiesWidget_addMoreURLs(numTry);
	  System.out.println(" v");
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all link widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span/div/div/span[2]")));
	  String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all link widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span/div"))).getAttribute("id");
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all link widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span/div/div/span[2]"))).perform();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li")).click();
	  Thread.sleep(5000);
  }
  
  @Test
  public void keywordTrendsWidget() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  goBase();
	  
	  System.out.println("\n\nLink Opportunities widget:");
	  
	  print("Entering title link.");
	  keywordTrendsWidget_enteringTitleLink(numTry);
	  System.out.println(" v");
	  print("Testing display graph.");
	  keywordTrendsWidget_testingDisplayGraph(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void websiteFeedWidget() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  int numTry=0; //Counter the number of attempts.
	  goBase();
	  
	  System.out.println("\n\nWebsite Feed widget:");
	  
	  print("Testing drop down list (Score set used).");
	  websiteFeedWidget_dropDownListScore(numTry);
	  System.out.println(" v");
	  
	  //Test record:  
	  try{if(!wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all website_feed widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[2]/ul/li/strong"))).getText().equals("No website activity at the moment but we'll keep you posted.")){
			  print("Testing lead detail link.");
			  websiteFeedWidget_leadDetailLink(numTry);
			  System.out.println(" v");
		      print("Testing Watch button.");
		      websiteFeedWidget_watchButton(numTry);
		      System.out.println(" v");
		  }
	  }
	  catch(Exception ex){
		  throw ex;
	  }
	  
	  if(!wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all website_feed widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[2]/ul/li/strong"))).getText().equals("No website activity at the moment but we'll keep you posted.")){
		      try{wait.until(presenceOfElementLocated(By.linkText("WATCH"))).click();
		      }
		      catch(Exception ex){
		    	  wait.until(presenceOfElementLocated(By.linkText("UNWATCH"))).click();
		    	  Thread.sleep(3000);
			      builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all website_feed widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[2]/ul/li/div[3]/span[2]/a")))).perform();
			      assertEquals("Lead alerts settings","Alerts | Optify",driver.getTitle());
			      driver.get(homeAddress);
		      
			      print("Testing hide button.");
			      websiteFeedWidget_hideButton(numTry);
			      System.out.println(" v");
			      print("Testing Unwatch button.");
			      websiteFeedWidget_hideButtonUnwatchButton(numTry);
			      System.out.println(" v");
	      }

	  }
	
	  Thread.sleep(3000);
  }
  
  @Test
  public void alertsWidget() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  goBase();
	  
	  System.out.println("\n\nWebsite Feed widget:");
	  print("Testing keywrods check box.");
	  alertsWidget_keywrodsCheckBox(numTry);
	  System.out.println(" v");
	  print("Testing Leads check box.");
	  alertsWidget_leadsCheckBox(numTry);
	  System.out.println(" v");
	  print("Testing records.");
	  alertsWidget_records(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void addWidgets() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  int numTry=0; //Counter the number of attempts.
	  String getId="";
	  goBase();
	  
	  System.out.println("\n\nTesting Remove/Add new widgets:");
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']")));
	  getId=driver.findElement(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[3]")).getAttribute("id");
	
	  print("Testing removing widget.");
	  addWidgets_removingWidget(getId,numTry);
	  System.out.println(" v");
	  print("Testing add widget.");
	  addWidgets_removingWidget_addWidget(numTry);
	  System.out.println(" v");
	  print("Testing menu widget feature collapse.");
	  getId=addWidgets_menuWidgetFeatureCollapse(numTry);
	  System.out.println(" v");
	  print("Testing menu widget feature edit.");
	  addWidgets_menuWidgetFeatureEdit(getId,numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void welcomeToolbar() throws Exception{
	  String winHandleBefore = driver.getWindowHandle();
	  int numTry=0; //Counter the number of attempts.
	  goBase();
	  
	  System.out.println("\n\nWelcome toolbar:");
	  
	  print("Testing Collapse/Expand.");
	  welcomeToolbar_collapseExpand(numTry);
	  System.out.println(" v");
	  print("Testing Add Keywords.");
	  welcomeToolbar_addKeywords(numTry);
	  System.out.println(" v");
	  print("Testing Send an email.");
	  welcomeToolbar_sendAnEmail(numTry);
	  System.out.println(" v");
	  print("Testing View Traffic.");
	  welcomeToolbar_viewTraffic(numTry);
	  System.out.println(" v");
	  print("Testing Create a report.");
	  welcomeToolbar_createAReport(numTry);
	  System.out.println(" v");
	  
	  //Test links:
	  print("Entering Take me to the Help home page link.");
	  welcomeToolbar_takeMeToTheHelHomePageLink(winHandleBefore,numTry);
	  System.out.println(" v");
	  print("Entering Help me customize my dashboard link.");
	  welcomeToolbar_HelpMeCustomizeMyDashboardLink(winHandleBefore,numTry);
	  System.out.println(" v");
	  print("Entering Submit a support ticket link.");
	  welcomeToolbar_SubmitASupportTicketLink(winHandleBefore,numTry);
	  System.out.println(" v");
	  
	  //Test see more actions:
	  print("Testing Add keywords.");
	  welcomeToolbar_moreAddKeywords(numTry);
	  System.out.println(" v");
	  print("Testing Add pages.");
	  welcomeToolbar_moreAddPages(numTry);
	  System.out.println(" v");
	  print("Testing Add Urls.");
	  welcomeToolbar_moreAddUrls(numTry);
	  System.out.println(" v");
	  print("Testing Import Contacts.");
	  welcomeToolbar_moreImportContacts(numTry);
	  System.out.println(" v");
	  //Create an Email:
	  //Send an email:
	  print("Testing Create a Landing Page.");
	  welcomeToolbar_moreCreateALandingPage(numTry);
	  System.out.println(" v");
	  print("Testing Create a Lead Score.");
	  welcomeToolbar_moreCreateALeadScore(numTry);
	  System.out.println(" v");
	  print("Testing View your Traffic.");
	  welcomeToolbar_moreViewYourTraffic(numTry);
	  System.out.println(" v");
	  print("Testing Create an alert.");
	  welcomeToolbar_moreCreateAnAlert(numTry);
	  System.out.println(" v");
	  print("Testing Create a report.");
	  welcomeToolbar_moreCreateAReport(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @AfterClass
  public static void summary(){
	 driver.close();
	 driver.quit();
  }
  
  //===============================================================================================
  private Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
	    return new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            return driver.findElement(locator);
	          }
       };
  }
  
  //================================================================================================
  private void switcWindow(){
	  for(String winHandle : driver.getWindowHandles())
		  driver.switchTo().window(winHandle);
  }
  
  //================================================================================================
  private String getTime(){
	  return Integer.toString((int)((System.currentTimeMillis()/(1000*60*60))%24))+
	         Integer.toString((int) ((System.currentTimeMillis()/(1000*60))%60));
  }
  
  //================================================================================================
  private void removeTwitterSearchSave() throws InterruptedException{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//*[@title='Remove Saved Search']/span")));
	  driver.findElement(By.xpath("//*[@title='Remove Saved Search']/span")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='confirm-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")));
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//button[@class='confirm-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")).click();
	  Thread.sleep(5000);
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
  
  //================================================================================================
  private int getRowsNum(By by) throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  int num=0;
	  int sum=0;
	  int MAX_ROWS=100;
	  
	  while((num=getRowCount(by))>=MAX_ROWS){
		  sum+=num;
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='next']")));
		  driver.findElement(By.xpath("//a[@class='next']")).click();
		  wait.until(presenceOfElementLocated(by));
	  }
	  
	  sum+=num;
	  return sum;
  }
  
  //==================================================================================================
  private void goBase(){
	  if(!driver.getTitle().equals("Dashboard | Optify"))
		  driver.get(homeAddress);
  }
  
  //==================================================================================================
  private void gettingStartedWidget_CreateYourTargetKeywords_LEARN_MORE(int numTry) throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{String winHandleBefore = driver.getWindowHandle();
	  
	  	  //Link1:===============================================================================================
	  	  wait.until(presenceOfElementLocated(By.linkText("1. Create your target keywords list")));
		  builder.clickAndHold(driver.findElement(By.linkText("1. Create your target keywords list"))).perform();
		  wait.until(presenceOfElementLocated(By.linkText("LEARN MORE"))).click();
		  
		  Thread.sleep(3000);
		  
		  switcWindow();
		  assertEquals("Lerarn more[1]","What are Keywords? : Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 gettingStartedWidget_CreateYourTargetKeywords_LEARN_MORE(numTry);
	  }
  }
  
  //=========================================================================================================
  private void gettingStartedWidget_CreateYourTargetKeywords(int numTry) throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{builder.click(wait.until(presenceOfElementLocated(By.linkText("1. Create your target keywords list")))).perform();
		  assertEquals("1. Create your target keywords list","Keywords | Optify",driver.getTitle());
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 gettingStartedWidget_CreateYourTargetKeywords(numTry);
	  }
  }
  
  //=========================================================================================================
  private void gettingStartedWidget_OptimizeYourWebsitPages_LEARN_MORE(int numTry) throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{String winHandleBefore = driver.getWindowHandle();
	  
		  //Link2:===============================================================================================
		  wait.until(presenceOfElementLocated(By.linkText("2. Optimize your website pages")));
		  builder.clickAndHold(driver.findElement(By.linkText("2. Optimize your website pages"))).perform();
		  wait.until(presenceOfElementLocated(By.linkText("LEARN MORE"))).click();
		  
		  Thread.sleep(3000);
		  
		  switcWindow();
		  assertEquals("Lerarn more[2]","Pages overview : Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 gettingStartedWidget_OptimizeYourWebsitPages_LEARN_MORE(numTry);
	  }
  }
  
  //=========================================================================================================
  private void gettingStartedWidget_OptimizeYourWebsitPages(int numTry) throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.linkText("2. Optimize your website pages"))).click();
		  assertEquals("2. Optimize your website pages","Pages | Optify",driver.getTitle());
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 gettingStartedWidget_OptimizeYourWebsitPages(numTry);
	  }
  }
  
  //=========================================================================================================
  private void gettingStartedWidget_ViewAndManageYourLeads_LEARN_MORE(int numTry) throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{String winHandleBefore = driver.getWindowHandle();
	  
		  //Link3:===============================================================================================
		  wait.until(presenceOfElementLocated(By.linkText("3. View and manage your leads")));
		  builder.clickAndHold(driver.findElement(By.linkText("3. View and manage your leads"))).perform();
		  wait.until(presenceOfElementLocated(By.linkText("LEARN MORE"))).click();
		  
		  Thread.sleep(3000);
		  
		  switcWindow();
		  assertEquals("Lerarn more[3]","Lead Intelligence overview : Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 gettingStartedWidget_ViewAndManageYourLeads_LEARN_MORE(numTry);
	  }
  }
  
  //=========================================================================================================
  private void gettingStartedWidget_ViewAndManageYourLeads(int numTry) throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.linkText("3. View and manage your leads"))).click();
		  assertEquals("3. View and manage your leads","Lead Intelligence | Optify",driver.getTitle());
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 gettingStartedWidget_ViewAndManageYourLeads(numTry);
	  }
  }
  
  //=========================================================================================================
  private void gettingStartedWidget_CreateATwitterCampaign_LEARN_MORE(int numTry) throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{String winHandleBefore = driver.getWindowHandle();
	  
		  //Link4:===============================================================================================
		  wait.until(presenceOfElementLocated(By.linkText("4. Create a Twitter campaign")));
		  builder.clickAndHold(driver.findElement(By.linkText("4. Create a Twitter campaign"))).perform();
		  wait.until(presenceOfElementLocated(By.linkText("LEARN MORE"))).click();
		  
		  Thread.sleep(3000);
		  
		  switcWindow();
		  assertEquals("Lerarn more[4]","Twitter for Business overview : Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 gettingStartedWidget_CreateATwitterCampaign_LEARN_MORE(numTry);
	  }
  }
  
  //=========================================================================================================
  private void gettingStartedWidget_CreateATwitterCampaign(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.linkText("4. Create a Twitter campaign"))).click();
		  assertEquals("4. Create a Twitter campaign","Twitter for Business | Optify",driver.getTitle());
		  driver.get(homeAddress);
	  }
	  catch(Exception ex){
		 if(numTry>3)
			 	throw ex;
			 
		 numTry++;
		 gettingStartedWidget_CreateATwitterCampaign(numTry);
	  }
  }
  
  //=========================================================================================================
  private void seoWidget_EnteringAverageScore(int numTry) throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{builder.click(wait.until(presenceOfElementLocated(By.linkText("Average score")))).perform();
		  assertEquals("Average score","Pages | Optify",driver.getTitle());
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 seoWidget_EnteringAverageScore(numTry);
	  }
  }
  
  //=========================================================================================================
  private void seoWidget_EnteringHomepageScore(int numTry) throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.linkText("Homepage score")));
	  	  wait.until(presenceOfElementLocated(By.linkText("Homepage score"))).click();
		  assertEquals("Homepage score","Website Page | Optify",driver.getTitle());
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 seoWidget_EnteringHomepageScore(numTry);
	  }
  }
	
  //=========================================================================================================
  private void seoWidget_EnteringEstimatedClickValue(int numTry) throws Exception{
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.linkText("Estimated click value")));
	  	  wait.until(presenceOfElementLocated(By.linkText("Estimated click value"))).click();
		  assertEquals("Estimated click value","Keywords | Optify",driver.getTitle());
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 seoWidget_EnteringEstimatedClickValue(numTry);
	  }
  }
  
  //=========================================================================================================
  private void keywordPerformanceWidget_EnteringTitleLink(int numTry) throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  Thread.sleep(5000);
	  
	  try{builder.click(wait.until(presenceOfElementLocated(By.className("keyword_widget_title")))).perform();
		  assertEquals("Keyword performance","Keywords | Optify",driver.getTitle());
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 keywordPerformanceWidget_EnteringTitleLink(numTry);
	  }
  }
  
  //=========================================================================================================
  private void keywordPerformanceWidget_rank1(int numTry) throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  String rank="";
	  int rows=0;
	 
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all keyword widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div[2]/div/div/div/div/a")));
		  rank=wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all keyword widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div[2]/div/div/div/div/a"))).getText();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all keyword widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div[2]/div/div/div/div/a"))).click();
		  Thread.sleep(5000);
		  rows=getRowsNum(By.xpath("//*[@id='keyword_table']/tbody"));
		
		  assertEquals("rank1",rank,Integer.toString(rows));
		 
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 keywordPerformanceWidget_rank1(numTry);
	  }
  }
  
  //=========================================================================================================
  private void keywordPerformanceWidget_rank2(int numTry) throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  String rank="";
	  int rows=0;
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all keyword widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div[2]/div/div/div[2]/div/a")));
		  rank=wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all keyword widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div[2]/div/div/div[2]/div/a"))).getText();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all keyword widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div[2]/div/div/div[2]/div/a"))).click();
		  Thread.sleep(7000);
		  rows=getRowsNum(By.xpath("//table[@id='keyword_table']/tbody"));
		  
		  assertEquals("rank2",rank,Integer.toString(rows));
		  
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 keywordPerformanceWidget_rank2(numTry);
	  }
  }
  
  //=========================================================================================================
  private void keywordPerformanceWidget_searchEngineDropDawn(int numTry) throws Exception{;
      WebDriverWait wait = new WebDriverWait(driver, 10);
      goBase();
      
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all keyword widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span[2]/div/div/span[2]")));
		  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all keyword widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span[2]/div/div/span[2]"))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//html/body/div[23]/ul/li[2]"))).click();
		  Thread.sleep(5000);
		  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all keyword widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span[2]/div/div/span[2]"))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//html/body/div[23]/ul/li"))).click();
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 keywordPerformanceWidget_searchEngineDropDawn(numTry);
	  }
  }
  
  //=========================================================================================================
  private void keywordPerformanceWidget_dropDownList(int numTry) throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all keyword widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span/div/div/span[2]")));
		  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all keyword widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span/div/div/span[2]"))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//html/body/div[22]/ul/li[2]"))).click();
		  Thread.sleep(5000);
		  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all keyword widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span/div/div/span[2]"))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//html/body/div[22]/ul/li"))).click();
		  Thread.sleep(5000);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 keywordPerformanceWidget_dropDownList(numTry);
	  }
  }
  
  //=========================================================================================================
  private void pageOptimizationWidget_enteringTitleLink(int numTry) throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{builder.click(driver.findElement(By.linkText("Page optimization"))).perform();
		  assertEquals("page optimization","Pages | Optify",driver.getTitle());
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 pageOptimizationWidget_enteringTitleLink(numTry);
	  }
  }
  
  //=========================================================================================================
  private void pageOptimizationWidget_enteringPageScoreLink(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='page_custom body pages-widget-bar clearfix']/div/a"))).click();
		  assertEquals("page optimization","Pages | Optify",driver.getTitle());
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 pageOptimizationWidget_enteringPageScoreLink(numTry);
	  }
  }
  
  //=========================================================================================================
  private void pageOptimizationWidget_dropDownList(int numTry) throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all page widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span/div/div/span[2]")));
		  String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all page widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span/div"))).getAttribute("id");
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all page widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span/div/div/span[2]")))).perform();
		  
		  Thread.sleep(2000);
		  
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li[2]")));
		  
		  Thread.sleep(5000);
		  
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all page widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span/div/div/span[2]")))).perform();
		  
		  Thread.sleep(2000);
		  
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li")));
	  }
	  catch(Exception e){
		 if(numTry>3)
			 throw e;
		 
		 numTry++;
		 pageOptimizationWidget_dropDownList(numTry);
	  }
  }
  
  //=========================================================================================================
  private void pageOptimizationWidget_testingScoreBar(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all page widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div[2]/div/div/div")));
		  String numerator=wait.until(presenceOfElementLocated(By.xpath("//span[@class='numerator']"))).getText();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all page widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div[2]/div/div/div"))).click();
		  assertEquals("page optimization","Pages | Optify",driver.getTitle()); 
		  assertEquals("numerator",numerator,driver.findElement(By.xpath("//span[@id='average_optify_score']")).getText()); 
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 pageOptimizationWidget_testingScoreBar(numTry);
	  }
  }
  
  //=========================================================================================================
  private void linkOpportunitiesWidget_enteringTitleLink(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{builder.click(wait.until(presenceOfElementLocated(By.linkText("Link opportunities")))).perform();
		  assertEquals("Link opportunities","Links | Optify",driver.getTitle());
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 linkOpportunitiesWidget_enteringTitleLink(numTry);
	  }
  }
  
  //=========================================================================================================
  private void linkOpportunitiesWidget_dropDownListView(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all link widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span[2]/div"))).getAttribute("id");
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all link widget-height-1 widget-width-1 ui-draggable']//li[@class='list_item']")));
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all link widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span[2]/div/div/span[2]")))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//*[@id='"+getId+"-pulldown']/ul/li[2]"))).click();
		  Thread.sleep(3000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all link widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span[2]/div/div/span[2]")))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li"))).click();
		  Thread.sleep(3000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all link widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span[2]/div/div/span[2]")))).perform();
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 linkOpportunitiesWidget_dropDownListView(numTry);
	  }
  }
  
  //=========================================================================================================
  private void linkOpportunitiesWidget_dropDownList(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all link widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span/div"))).getAttribute("id");
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all link widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div/span/div/div/span[2]")))).perform();
		  Thread.sleep(5000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li[3]"))).click();
		  Thread.sleep(5000);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 linkOpportunitiesWidget_dropDownList(numTry);
	  }
  }
  
  //=========================================================================================================
  private void linkOpportunitiesWidget_addMoreURLs(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all link widget-height-1 widget-width-1 ui-draggable']/div[2]/div/div[2]/ul/li//a[text()='Add more URLs']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='transparent ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")));
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='transparent ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
		  assertEquals("Link opportunities","Links | Optify",driver.getTitle());
		  driver.get(homeAddress); 
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 linkOpportunitiesWidget_addMoreURLs(numTry);
	  }
  }
  
  //=========================================================================================================
  private void keywordTrendsWidget_enteringTitleLink(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.linkText("Keyword trends")));
		  builder.click(wait.until(presenceOfElementLocated(By.linkText("Keyword trends")))).perform();
		  assertEquals("Keyword trends","Keyword Reports | Optify",driver.getTitle());
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 keywordTrendsWidget_enteringTitleLink(numTry);
	  }
  }
  
  //=========================================================================================================
  private void keywordTrendsWidget_testingDisplayGraph(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='body ui-corner-bottom']")));
		  try{assertEquals("Displaying graph","There are no results matching your filter settings",driver.findElement(By.xpath("//div[@class='chartMessage']")).getText());
			throw new Exception("Graph can't be display");        
		  }
		  catch(Exception e){}
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 keywordTrendsWidget_testingDisplayGraph(numTry);
	  }
  }
  
  //=========================================================================================================
  private void websiteFeedWidget_dropDownListScore(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all website_feed widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/div"))).getAttribute("id");
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all website_feed widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/div/div/span[2]")))).perform();
		  Thread.sleep(3000);
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li[2]")))).perform();
		  Thread.sleep(5000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all website_feed widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/div/div/span[2]")))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li"))).click();
		  Thread.sleep(5000); 
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 websiteFeedWidget_dropDownListScore(numTry);
	  }
  }
  
  //=========================================================================================================
  private void websiteFeedWidget_leadDetailLink(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all website_feed widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[2]/ul/li/div/a/strong"))).click();
		  assertEquals("Lead Detail","Lead Detail | Optify",driver.getTitle());
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 websiteFeedWidget_leadDetailLink(numTry);
	  }
  }
  
  //=========================================================================================================
  private void websiteFeedWidget_watchButton(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all website_feed widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[2]/ul/li/div/a/strong")))).perform();
	  }
  	  catch(Exception e){
		 if(numTry>3)
			 throw e;
		 
		 numTry++;
		 websiteFeedWidget_watchButton(numTry);
	  }
  }
  
  //=========================================================================================================
  private void websiteFeedWidget_hideButton(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all website_feed widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[2]/ul/li/div/a/strong")));
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all website_feed widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[2]/ul/li/div/a/strong")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all website_feed widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[2]/ul/li/div[2]/a[3]")));
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all website_feed widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[2]/ul/li/div[2]/a[3]"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all website_feed widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[2]/ul/li/div[2]/a"))).click();
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 websiteFeedWidget_hideButton(numTry);
	  }
  }
  
  //=========================================================================================================
  private void websiteFeedWidget_hideButtonUnwatchButton(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{try{ wait.until(presenceOfElementLocated(By.linkText("UNWATCH"))).click();
		  System.out.println(" v");
		  }
		  catch(Exception e){
			  wait.until(presenceOfElementLocated(By.linkText("WATCH"))).click();
			  System.out.println(" v");
		  }
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 websiteFeedWidget_hideButtonUnwatchButton(numTry);
	  }
  }
  
  //=========================================================================================================
  private void addWidgets_removingWidget(String getId, int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[3]/div/span/div")))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li[3]"))).click();
		  builder.release();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='confirm_delete_ok']/button"))).click();
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 addWidgets_removingWidget(getId,numTry);
	  }
  }
  
  //=========================================================================================================
  private void addWidgets_removingWidget_addWidget(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@class='add-widget-widget']/a/span")))).perform();
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//button[@value='0']")))).perform();
	  }
	  catch(Exception e){
		 if(numTry>3)
			 throw e;
		 
		 numTry++;
		 addWidgets_removingWidget_addWidget(numTry);
	  }
  }
  
  //=========================================================================================================
  private String addWidgets_menuWidgetFeatureCollapse(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String getId=null;
	  goBase();
	  
	  try{Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[3]/div/span/div")));
		  getId=driver.findElement(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[3]")).getAttribute("id");
		  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[3]/div/span/div"))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li"))).click();
		  builder.release();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"']/div/span/div")));
		  builder.clickAndHold(driver.findElement(By.xpath("//div[@id='"+getId+"']/div/span/div"))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li"))).click();
		  Thread.sleep(3000);
		  return getId;
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 return addWidgets_menuWidgetFeatureCollapse(numTry);
	  }
  }
  
  //=========================================================================================================
  private void addWidgets_menuWidgetFeatureEdit(String getId, int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{builder.clickAndHold(driver.findElement(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[3]/div/span/div"))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li[2]"))).click();
		  builder.release();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[5]/div/div[2]/div[3]"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget-settings ui-corner-bottom']/div/div[2]/div")));
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget-settings ui-corner-bottom']/div/div[2]/div"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget-settings ui-corner-bottom']/div[2]/a"))).click();
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 addWidgets_menuWidgetFeatureEdit(getId,numTry);
	  }
  }
  
  //=========================================================================================================
  private void welcomeToolbar_collapseExpand(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@id='fluidwrapper']/div[2]/div/div/span")))).perform();
		  Thread.sleep(5000); 
		  //Add check change value element when collapse.
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@id='fluidwrapper']/div[2]/div/div/span")))).perform();
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 welcomeToolbar_collapseExpand(numTry);
	  }
  }
  
  //=========================================================================================================
  private void welcomeToolbar_addKeywords(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{builder.click(wait.until(presenceOfElementLocated(By.xpath("//button[@class='add_keywords orange track button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only expanded']/span")))).perform();
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//button[@class='transparent ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")))).perform();
		  assertEquals("Add keywords","Keywords | Optify",driver.getTitle());
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 welcomeToolbar_addKeywords(numTry);
	  }
  }
  
  //=========================================================================================================
  private void welcomeToolbar_sendAnEmail(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//button[@class='send_email email-required orange track button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only expanded']/span")));
	      //Do something.
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 welcomeToolbar_addKeywords(numTry);
	  }
  }
  
  //=========================================================================================================
  private void welcomeToolbar_viewTraffic(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//button[@class='view_traffic orange track button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only expanded']/span"))).click();
		  assertEquals("View Traffic","Lead Intelligence | Optify",driver.getTitle());
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 welcomeToolbar_viewTraffic(numTry);
	  }
  }
  
  //=========================================================================================================
  private void welcomeToolbar_createAReport(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//button[@class='create_report orange track button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only expanded']/span"))).click();
		  assertEquals("Create a report","Report | Optify",driver.getTitle());
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 welcomeToolbar_createAReport(numTry);
	  }
  }
  
  //=========================================================================================================
  private void welcomeToolbar_takeMeToTheHelHomePageLink(String winHandleBefore, int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='collapsible newwin expanded']/a"))).click();
		  switcWindow();
		  assertEquals("Take me to the Help home page:","Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 welcomeToolbar_takeMeToTheHelHomePageLink(winHandleBefore,numTry);
	  }
  }
  
  //=========================================================================================================
  private void welcomeToolbar_HelpMeCustomizeMyDashboardLink(String winHandleBefore, int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='collapsible newwin expanded']/a[2]"))).click();
		  switcWindow();
		  assertEquals("Help me customize my dashboard:","Customizing your dashboard : Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 welcomeToolbar_HelpMeCustomizeMyDashboardLink(winHandleBefore,numTry);
	  }
  }
  
  //=========================================================================================================
  private void welcomeToolbar_SubmitASupportTicketLink(String winHandleBefore, int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='collapsible newwin expanded']/a[3]"))).click();
		  switcWindow();
		  assertEquals("Submit a support ticket:","Help and Support : Submit a request for assistance",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore); 
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 welcomeToolbar_SubmitASupportTicketLink(winHandleBefore,numTry);
	  }
  }
  
  //=========================================================================================================
  private void welcomeToolbar_moreAddKeywords(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='action faq_seemore expanded']/div[2]"))).getAttribute("id");
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='action faq_seemore expanded']/div[2]/div/span")))).perform();
		  Thread.sleep(3000);
		  
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']//li[text()='Add Keywords']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='transparent ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")));
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='transparent ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
		  assertEquals("Add keywords","Keywords | Optify",driver.getTitle());
		  driver.get(homeAddress);
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 welcomeToolbar_moreAddKeywords(numTry);
	  }
  }
  
  //=========================================================================================================
  private void welcomeToolbar_moreAddPages(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='action faq_seemore expanded']/div[2]"))).getAttribute("id");
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='action faq_seemore expanded']/div[2]/div/span")))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li[3]"))).click();
		
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='transparent ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")));
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='transparent ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
		  assertEquals("Add pages","Pages | Optify",driver.getTitle());
		  driver.get(homeAddress);
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 welcomeToolbar_moreAddPages(numTry);
	  }
  }
  
  //=========================================================================================================
  private void welcomeToolbar_moreAddUrls(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='action faq_seemore expanded']/div[2]"))).getAttribute("id");
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='action faq_seemore expanded']/div[2]/div/span")))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li[4]"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='transparent ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")));
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='transparent ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
		  assertEquals("Add Urls","Links | Optify",driver.getTitle());
		  driver.get(homeAddress);
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 welcomeToolbar_moreAddUrls(numTry);
	  }
  }
  
  //=========================================================================================================
  private void welcomeToolbar_moreImportContacts(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='action faq_seemore expanded']/div[2]"))).getAttribute("id");
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='action faq_seemore expanded']/div[2]/div/span")))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li[6]"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='transparent ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")));
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='transparent ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
		  assertEquals("Import Contacts","Contact Manager | Optify",driver.getTitle());
		  driver.get(homeAddress);
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 welcomeToolbar_moreImportContacts(numTry);
	  }
  }
  
  //=========================================================================================================
  private void welcomeToolbar_moreCreateALandingPage(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='action faq_seemore expanded']/div[2]"))).getAttribute("id");
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='action faq_seemore expanded']/div[2]/div/span")))).perform();
		  Thread.sleep(3000);
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']//li[text()='Create a Landing Page']")))).perform();
		  assertEquals("Create a Landing Page","Create Landing Page | Optify",driver.getTitle());
		  driver.get(homeAddress);
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 welcomeToolbar_moreCreateALandingPage(numTry);
	  }
  }
  
  //=========================================================================================================
  private void welcomeToolbar_moreCreateALeadScore(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='action faq_seemore expanded']/div[2]"))).getAttribute("id");
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='action faq_seemore expanded']/div[2]/div/span")))).perform();
		  Thread.sleep(2000);
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']//li[text()='Create a Lead Score']")))).perform();
		  assertEquals("Create a Lead Score","Lead Scoring | Optify",driver.getTitle());
		  driver.get(homeAddress);
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 welcomeToolbar_moreCreateALeadScore(numTry);
	  }
  }
  
  //=========================================================================================================
  private void welcomeToolbar_moreViewYourTraffic(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='action faq_seemore expanded']/div[2]"))).getAttribute("id");
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='action faq_seemore expanded']/div[2]/div/span")))).perform();
		  Thread.sleep(3000);
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']//li[text()='View your Traffic']")))).perform();
		  assertEquals("View your Traffic","Lead Intelligence | Optify",driver.getTitle());
		  driver.get(homeAddress);
		  Thread.sleep(3000);
	  }
	  
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 welcomeToolbar_moreViewYourTraffic(numTry);
	  }
  }
  
  //=========================================================================================================
  private void welcomeToolbar_moreCreateAnAlert(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='action faq_seemore expanded']/div[2]"))).getAttribute("id");
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='action faq_seemore expanded']/div[2]/div/span")))).perform();
		  Thread.sleep(3000);
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']//li[text()='Create an Alert']")))).perform();
		  assertEquals("Create an alert","Alerts | Optify",driver.getTitle());
		  driver.get(homeAddress);
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 welcomeToolbar_moreCreateAnAlert(numTry);
	  }
  }
  
  //=========================================================================================================
  private void welcomeToolbar_moreCreateAReport(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='action faq_seemore expanded']/div[2]"))).getAttribute("id");
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='action faq_seemore expanded']/div[2]/div/span")))).perform();
		  Thread.sleep(3000);
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']//li[text()='Create a Report']")))).perform();
		  assertEquals("Create a report","Report | Optify",driver.getTitle());
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 welcomeToolbar_moreCreateAReport(numTry);
	  }
  }
  
  //=========================================================================================================
  private void twitterForBusinessWidget_titleLink(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{builder.click(wait.until(presenceOfElementLocated(By.linkText("Twitter for Business")))).perform();
		  assertEquals("Twitter for Business","Twitter for Business | Optify",driver.getTitle());
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 twitterForBusinessWidget_titleLink(numTry);
	  }
  }
  
  //=========================================================================================================
  private void twitterForBusinessWidget_sendingTwitt(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String message = getTime();
	  String account = wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all twitter widget-height-2 widget-width-1 ui-draggable']/div[2]/div/span/div/div/span"))).getText();
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.cssSelector("textarea.tweet-text.tweetbox"))).sendKeys(message);
	  	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='post_tweet ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
		  Thread.sleep(5000);
		 
		  wait.until(presenceOfElementLocated(By.linkText("Twitter for Business"))).click();
		  wait.until(presenceOfElementLocated(By.className("inbox_tweet")));
		  
		  String getMessage=wait.until(presenceOfElementLocated(By.className("inbox_tweet"))).getAttribute("textContent");
		  assertEquals("Message",message,getMessage.substring(account.length()+1, 1+account.length()+message.length()));
		  
		  getMessage=wait.until(presenceOfElementLocated(By.className("headline_tweet"))).getAttribute("textContent");
		  assertEquals("Check message",message, getMessage.substring(0, message.length()));
		  
		  String getAccount=driver.findElement(By.id("my_twitter_name")).getAttribute("textContent");
		  assertEquals("Check account name",account,getAccount);
		  
		  getAccount=driver.findElement(By.className("inbox_tweet")).getAttribute("textContent");
		  assertEquals("Check account name",account,getAccount.substring(0, account.length()));
		  driver.get(homeAddress);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 twitterForBusinessWidget_sendingTwitt(numTry);
	  }
  }
  
  //=========================================================================================================
  private void twitterForBusinessWidget_socialMonitor(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  boolean contin=true;
	  int cathSum=0;
	  goBase();
	  
	  try{while(contin){
		  try{
			  try{contin=false;
				  wait.until(presenceOfElementLocated(By.xpath("//div[@class='saved-search-list']/a"))).click();
		  } 
		  finally{wait.until(presenceOfElementLocated(By.id("tab-searches")));
		  		  wait.until(presenceOfElementLocated(By.id("search-twitter-input"))).sendKeys("ad");
				  Thread.sleep(5000);
				  wait.until(presenceOfElementLocated(By.id("search-twitter-input"))).sendKeys(Keys.ENTER);
				  Thread.sleep(5000);
				  wait.until(presenceOfElementLocated(By.xpath("//*[@id='search-action']/a"))).click();
				  Thread.sleep(5000);
				  
				  driver.get(homeAddress);
				  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all twitter widget-height-2 widget-width-1 ui-draggable']/div[2]/div[2]/div[3]/div[2]/div/ul/li/div/div/div/div[2]/a")));
				  assertEquals("Check Social Monitor value","ad",driver.findElement(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all twitter widget-height-2 widget-width-1 ui-draggable']/div[2]/div[2]/div[3]/div[2]/div/ul/li/div/div/div/div[2]/a")).getText());
				  wait.until(presenceOfElementLocated(By.linkText("View all"))).click();
				  removeTwitterSearchSave();
				  driver.get(homeAddress);
				  wait.until(presenceOfElementLocated(By.linkText("Twitter for Business")));
			  }
		  }
		  catch(WebDriverException ex){
			  if(cathSum>3)
				  throw ex;
			  
			  cathSum++;
			  contin=true;
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='footer-controls']/a"))).click();
			  removeTwitterSearchSave();
			  driver.get(homeAddress);
			  wait.until(presenceOfElementLocated(By.linkText("Twitter for Business")));
			  }
  		}
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 twitterForBusinessWidget_socialMonitor(numTry);
	  }
  }
  
  //=========================================================================================================
  private void twitterForBusinessWidget_dropDownList(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all twitter widget-height-2 widget-width-1 ui-draggable']/div[2]/div/span/div/div/span[2]")));
		  String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all twitter widget-height-2 widget-width-1 ui-draggable']/div[2]/div/span/div"))).getAttribute("id");
		  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all twitter widget-height-2 widget-width-1 ui-draggable']/div[2]/div/span/div/div/span[2]"))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li"))).click();
		  Thread.sleep(3000);
		  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all twitter widget-height-2 widget-width-1 ui-draggable']/div[2]/div/span/div/div/span[2]"))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li[2]"))).click();
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 twitterForBusinessWidget_dropDownList(numTry);
	  }
  }
  
  //=========================================================================================================
  private void alertsWidget_keywrodsCheckBox(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{boolean isChecked=wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/span[3]/span/input"))).isSelected();
		  if(isChecked){
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/span[3]/span/input"))).click();
			  Thread.sleep(3000);
			  assertEquals("Check Uncheck box",!isChecked,wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/span[3]/span/input"))).isSelected());
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/span[3]/span/input"))).click();
			  Thread.sleep(3000);
			  assertEquals("Check Checked box",isChecked,wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/span[3]/span/input"))).isSelected());
		  }
		  else{
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/span[3]/span/input"))).click();
			  Thread.sleep(3000);
			  assertEquals("Check Checked box",isChecked,wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/span[3]/span/input"))).isSelected());
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/span[3]/span/input"))).click();
			  Thread.sleep(3000);
			  assertEquals("Check Uncheck box",!isChecked,wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/span[3]/span/input"))).isSelected()); 
		  }
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 alertsWidget_keywrodsCheckBox(numTry);
	  }
  }
  
  //=========================================================================================================
  private void alertsWidget_leadsCheckBox(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{boolean isChecked=wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/span[2]/span/input"))).isSelected();
		  if(isChecked){
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/span[2]/span/input"))).click();
			  Thread.sleep(3000);
			  assertEquals("Check Uncheck box",!isChecked,wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/span[2]/span/input"))).isSelected());
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/span[2]/span/input"))).click();
			  Thread.sleep(3000);
			  assertEquals("Check Checked box",isChecked,wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/span[2]/span/input"))).isSelected());
		  }
		  else{
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/span[2]/span/input"))).click();
			  Thread.sleep(3000);
			  assertEquals("Check Checked box",isChecked,wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/span[2]/span/input"))).isSelected());
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/span[2]/span/input"))).click();
			  Thread.sleep(3000);
			  assertEquals("Check Uncheck box",!isChecked,wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[4]/span[2]/span/input"))).isSelected()); 
		  }
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 alertsWidget_leadsCheckBox(numTry);
	  }
  }
  
  //=========================================================================================================
  private void alertsWidget_records(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);	
	  goBase();
	  
	  try{try{wait.until(presenceOfElementLocated(By.xpath("//li[@class='list_item alert_row lead_alert']/div/a"))).click();
	  		  assertEquals("Lead detail:","Lead Detail | Optify",driver.getTitle());
	  		  driver.get(homeAddress);
	  		  Thread.sleep(3000);
	 	  }
		  catch(Exception e){wait.until(presenceOfElementLocated(By.xpath("//div[@class='widget ui-widget ui-widget-content ui-corner-all alerts widget-height-1 widget-width-1 ui-draggable has-toolbar']/div[2]/div/ul/li/strong"))).getText().equals("No alerts now but we'll keep you posted.");
		  }
			//Do some test.
		}
		catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 alertsWidget_records(numTry);
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


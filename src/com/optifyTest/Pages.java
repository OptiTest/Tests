package com.optifyTest;

import static org.junit.Assume.assumeTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.ComparisonFailure;
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
public class Pages extends TestCase {
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
	
	enterToDashBoard();
  }
  
  public static void enterToDashBoard() throws Throwable{
	  int numTry=0; //Counter the number of attempts.
	  time=System.currentTimeMillis();
	  object="";
	  
	  System.out.println();
	  print("Login to Optify.");
	  
	  driver.get(homeAddress+"/login");
	  driver.findElement(By.id("j_username")).sendKeys(userName);
	  driver.findElement(By.id("j_password")).sendKeys(password);
	  driver.findElement(By.id("login_button")).click();
	  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
	  
	  printSuccess();
	  
	  print("Entering to Pages page.");
	  enterToPages(numTry);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void helpWithThisPage() throws Exception{
	  assumeTrue(enable("helpWithThisPage")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Help with this page";
	  
	  print("Entering Help with this page.");
	  helpWithThisPage_link(numTry);
	  printSuccess();
  }
  
  @Test
  public void addPages() throws Exception{
	  assumeTrue(enable("addPages")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Add pages";
	  
	  print("Enter to Add page.");
	  addPages_enter(numTry);
	  printSuccess();
	  print("Testing Add page.");
	  addPages_save(numTry);
	  printSuccess();
	  print("Testing Add page into group.");
	  addPages_saveGroup(numTry);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void tableSort() throws Exception{
	  assumeTrue(enable("tableSort")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Tabel sort";
	  
	  print("Testing title sort.");
	  tableSort_titleSort(numTry);
	  printSuccess();
	  print("Testing Optify score sort.");
	  tableSort_optifyScoreSort(numTry);
	  printSuccess();
	  print("Testing Views sort.");
	  tableSort_viewsSort(numTry);
	  printSuccess();
	  print("Testing Links sort.");
	  tableSort_linksSort(numTry);
	  printSuccess();
	 
	  Thread.sleep(3000);
  }
  
  @Test
  public void inboundsLink() throws Exception{
	  assumeTrue(enable("inboundsLink")); 
	  String winHandleBefore = driver.getWindowHandle();
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Inbound links";
	  
	  print("Entering inbound links.");
	  inboundsLink_enter(numTry);
	  printSuccess();
	  print("Entering Go to links application.");
	  inboundsLink_goToLinksApplication(numTry,winHandleBefore);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void needHelp() throws Exception{
	  assumeTrue(enable("needHelp")); 
	  String winHandleBefore = driver.getWindowHandle();
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Need help";
	  
	  print("Entering need help.");
	  needHelp_enter(numTry);
	  printSuccess();
	  print("Testing learn more about pages.");
	  needHelp_learnMoreAboutPages(numTry,winHandleBefore);
	  printSuccess();
	  print("Testing going forward to overview.");
	  needHelp_forwardOverview(numTry);
	  printSuccess();
	  print("Testing going forward to pages table.");
	  needHelp_forwardPageTable(numTry);
	  printSuccess();
	  print("Testing going forward to add pages.");
	  needHelp_forwardAddPages(numTry);
	  printSuccess();
	  print("Testing going forward to table actions.");
	  needHelp_forwardTableActions(numTry);
	  printSuccess();
	  print("Testing going forward to lists.");
	  needHelp_forwardLists(numTry);
	  printSuccess();
	  print("Testing going forward to pages tab.");
	  needHelp_forwardPagesTab(numTry);
	  printSuccess();
	  print("Testing minimize need help.");
	  needHelp_done(numTry);
	  printSuccess();
	 
	  Thread.sleep(3000);
  }
  
  @Test
  public void showLinks() throws Exception{
	  assumeTrue(enable("showLinks")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Show links";
	  
	  print("Testing showing 50 links.");
	  showLinks_fifty(numTry);
	  printSuccess();
	  print("Testing showing 25 links.");
	  showLinks_twentyFifth(numTry);
	  printSuccess();
	  print("Testing showing 10 links.");
	  showLinks_ten(numTry);
	  printSuccess();
	
	  Thread.sleep(3000);
  }
  
  @Test
  public void nextAndPrev() throws Exception{
	  assumeTrue(enable("nextAndPrev")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Next and prev";
	  
	  print("Testing next.");
	  nextAndPrev_next(numTry);
	  printSuccess();
	  print("Testing next3.");
	  nextAndPrev_next3(numTry);
	  printSuccess();
	  print("Testing prev.");
	  nextAndPrev_prev(numTry);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void tableAction() throws Exception{
	  assumeTrue(enable("tableAction")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Table actions";
	  
	  print("Testing saving page to group.");
	  tableAction_savingPageToGroup(numTry);
	  printSuccess();
	  print("Entering test group.");
	  tableAction_enterTestGroup(numTry);
	  printSuccess();
	  print("Testing links.");
	  tableAction_links(numTry);
	  printSuccess();
	  print("Testing remove pages.");
	  tableAction_removePages(numTry);
	  printSuccess();
	 
	  Thread.sleep(3000);
  }

  @Test
  public void sortIssuesTable() throws Exception{
	  assumeTrue(enable("sortIssuesTable")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Sort issues table";
	  
	  print("Testing issues sort.");
	  sortIssuesTable_issuesSort(numTry);
	  printSuccess();
	  print("Testing impact sort.");
	  sortIssuesTable_impactSort(numTry);
	  printSuccess();
	  print("Testing Category sort.");
	  sortIssuesTable_categorySort(numTry);
	  printSuccess();
	  print("Testing Status sort.");
	  sortIssuesTable_statusSort(numTry);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void issuesNote() throws Exception{
	  assumeTrue(enable("issuesNote")); 
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Adding not";
	  
	  print("Testing add a note.");
	  issuesNote_addNote(numTry);
	  printSuccess();
	  print("Testing delete a note.");
	  issuesNote_deleteNote(numTry);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void setIssueToGroup() throws Exception{
	  assumeTrue(enable("setIssueToGroup"));
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Add a issue to group";
	  
	  print("Testing add issue to group.");
	  setIssueToGroup_add(numTry);
	  printSuccess();
	  print("Testing delete the test group.");
	  setIssueToGroup_delete(numTry);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void issuesLinks() throws Exception{
	  assumeTrue(enable("issuesLinks"));
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Issues links";
	  
	  print("Testing issues links.");
	  issuesLinks_qtipHavering(numTry);
	  printSuccess();
	  print("Entering issues link.");
	  issuesLinks_enterLink(numTry);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void issuesPrevNext() throws Exception{
	  assumeTrue(enable("issuesPrevNext"));
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Issues prev & next";
	  
	  print("Testing issues next.");
	  String sumIssues=issuesPrevNext_next(numTry);
	  printSuccess();
	  print("Testing issues prev.");
	  issuesPrevNext_prev(numTry,sumIssues);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void issuesShow() throws Exception{
	  assumeTrue(enable("issuesShow"));
	  int numTry=0; //Counter the number of attempts.
	  
	  object="Issues show:";
	  
	  print("Testing issues show 50.");
	  issuesShow_fifty(numTry);
	  printSuccess();
	  print("Testing issues show 25.");
	  issuesShow_twentyFive(numTry);
	  printSuccess();
	  print("Testing issues show 10.");
	  issuesShow_ten(numTry);
	  printSuccess();
	  
	  Thread.sleep(3000);
  }
  
  @AfterClass
  public static void summary(){
	  driver.close();
	  driver.quit();
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
  
  //=========================================================================================================
  private void addPages_enter(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span"))).click();
	  	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='add-pages-button medium orange ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
	  	  
	  	  
		  
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		  if(numTry>2){
				 printFailed();
				 throw e; 
		  }
		  
		 //Close Add Pages dialog box & restaet test.
		 builder.click(wait.until(presenceOfElementLocated(By.xpath("//span[@class='ui-icon ui-icon-closethick']")))).perform();
		 numTry++;
		 addPages_enter(numTry);
  	  }
  }
  
  //=========================================================================================================
  private void addPages_save(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);

	  try{wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='add_pages_textarea']"))).sendKeys("www.ynet.co.il");
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//button[@id='add_pages']"))).click();
		  
		  assertEquals("Newly added pages","Optify is gathering data for your newly added pages. We'll let you know as soon as we are finished.",wait.until(presenceOfElementLocated(By.xpath("//div[@class='info_message']"))).getText());
		  
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		//Close Add Pages dialog box & restaet test.
	 	try{wait.until(presenceOfElementLocated(By.xpath("//span[@class='ui-icon ui-icon-closethick']"))).click();
	 	}
	 	catch(Exception ex){}
		 	
	 	if(numTry>2){
			 printFailed();
			 throw e; 
	  }
		
		 	
	 	numTry++;
	 	addPages_enter(numTry);
	 	addPages_save(numTry);
	  }
  }
  
  //=========================================================================================================
  private void addPages_saveGroup(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);

	  try{builder.click(wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span")))).perform();
	  	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='add-pages-button medium orange ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
		  
	  	  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='add_pages_textarea']"))).sendKeys("www.walla.co.il");
		  Thread.sleep(3000);
		  String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='add-to-list-box']/div/div"))).getAttribute("id");
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@id='add-pages-dialog']/div/div/div/div/span[2]")))).perform();
		
		  Thread.sleep(3000);
		  
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']//input"))).sendKeys("test");
		  wait.until(presenceOfElementLocated(By.xpath("//button[@id='add_pages']"))).click();
		  
		  assertEquals("Newly added pages","Optify is gathering data for your newly added pages. We'll let you know as soon as we are finished.",wait.until(presenceOfElementLocated(By.xpath("//div[@class='info_message']"))).getText());
		  
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		//Close Add Pages dialog box & restaet test.
	 	try{wait.until(presenceOfElementLocated(By.xpath("//span[@class='ui-icon ui-icon-closethick']"))).click();}catch(Exception ex){}
	 	
	 	if(numTry>2){
			 printFailed();
			 throw e; 
	    }
	 	
	 	
	 	numTry++;
	 	addPages_saveGroup(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableSort_titleSort(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span"))).click();
	  
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
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	tableSort_titleSort(numTry);
	  }
  }	
  
  //=========================================================================================================
  private void tableSort_optifyScoreSort(int numTry)throws Exception {
  	  goBase();
  	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Test Optify score sort:=======================================================
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
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	tableSort_optifyScoreSort(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableSort_viewsSort(int numTry)throws Exception {
  	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Test Views sort:=======================================================
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
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	tableSort_viewsSort(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableSort_linksSort(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	 
	  try{//Test Links sort:=======================================================
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-numFollowedDomains first last']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[4]")));
		  
		  try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']//th[@class='align-right sorted-desc']")));
		  		
		  }catch(WebDriverException ex){
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-numFollowedDomains first last']"))).click();
		  }
		  
		  //Check down up sort:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[4]/a")));
		  
		  try{if(wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[4]/div"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//div[@class='ui-tabs ui-widget ui-widget-content ui-corner-all']/div/table/tbody/tr[2]/td[4]/a"))).getText())==1)
			  	throw new Exception("Sort down abnormal");
		  	
		  	  //Check up down sort:
		  	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-numFollowedDomains first last']"))).click();
		 	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[4]/div")));
		 	  
		 	  if(wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[4]/a"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//div[@class='ui-tabs ui-widget ui-widget-content ui-corner-all']/div/table/tbody/tr[2]/td[4]/a"))).getText())==-1)
		  			;
	  		  
		  }
		  catch(WebDriverException ex){
			  if(wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[4]/div"))).getText().compareTo(driver.findElement(By.xpath("//div[@class='ui-tabs ui-widget ui-widget-content ui-corner-all']/div/table/tbody/tr[2]/td[4]/div")).getText())==-1)
			  throw new Exception("Sort up abnormal");
		  }
		  
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	tableSort_linksSort(numTry);
	  }
  }
  
  //=========================================================================================================
  private void inboundsLink_enter(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span"))).click();
  
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
		  
		  //Wait for data:
		  wait.until(presenceOfElementLocated(By.xpath("//table[@class='inner_table']/tbody/tr/td[2]/span/a")));
		  
	  }
	  catch(Exception e){
		    //Close Inbound links dialog box & restart test.
		 	try{wait.until(presenceOfElementLocated(By.xpath("//span[@class='ui-icon ui-icon-closethick']"))).click();
		 	}
		 	catch(Exception ex){}
		 	
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	inboundsLink_enter(numTry);
	  }
  }
  
  //=========================================================================================================
  private void inboundsLink_goToLinksApplication(int numTry,String winHandleBefore)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='page_links_top_bar']/a"))).click();
		  switcWindow();
		  assertEquals("Go to links application:","Links | Optify",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='ui-dialog-titlebar-close ui-corner-all']/span"))).click();
	  }
	  catch(Exception e){
		    //If failed close dialog box and restart test.
		    try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='show-links-popup']/div[5]/button/span"))).click();
		    }
		    catch(Exception ex){}
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	inboundsLink_goToLinksApplication(numTry,winHandleBefore);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_enter(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{builder.click(wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span")))).perform();
		  
		  //Open need help:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-dialog trainer-minimized']/div/div/div/a"))).click();
		  Thread.sleep(3000);
		  
		  String help1=("The Pages application analyzes the pages on your website and suggests ways to improve your rankings in search results. Let's get started!");
		  assertEquals("help 1/9:",help1,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	needHelp_enter(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_learnMoreAboutPages(int numTry,String winHandleBefore)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-actions']/ul/li/a"))).click();
		  switcWindow();
		  assertEquals("Learn more about Pages:","Pages overview : Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	needHelp_learnMoreAboutPages(numTry,winHandleBefore);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_forwardOverview(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
		  
		  String help=("The Overview gives you a summary of your website's performance in search engines and shows opportunities for improvement.");
		  assertEquals("help 2/9:",help,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
		  //Test qtip tips
		  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Average Optify Score shows how well the pages on your website are optimized for search.']")));
	  	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='These numbers indicate how many issues you have across your site. Each issue is an opportunity to improve your pages and drive up your search rankings.']")));
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	needHelp_forwardOverview(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_forwardPageTable(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
 
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
		  String help=("The Pages table shows details about how well each of your web pages are optimized for search engines.");
		  assertEquals("help 3/9:",help,wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-left']/div"))).getText());
		  
		  //Test qtip tips
		  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Your pages and their Optify scores are listed here.']")));
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	needHelp_forwardPageTable(numTry);
	  }
  }
  
  //=========================================================================================================
  private void  needHelp_forwardAddPages(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
		  String help=("Add Pages lets you add one or more web addresses that you are interested in tracking.");
		  assertEquals("help 4/9:",help,wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-left']/div"))).getText());
	  
		  //Test qtip tips
		  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Go ahead and click to add some pages!']")));
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	needHelp_forwardAddPages(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_forwardTableActions(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
  
		  String help=("The table gives you several page-level tools for staying organized and managing your SEO efforts and campaigns.");
		  assertEquals("help 5/9:",help,wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-left']/div"))).getText());
	  
		  //Test qtip tips
		  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Hover over a row to: ']")));
		  wait.until(presenceOfElementLocated(By.xpath("//div[text()=' lets you organize your pages into custom categories like timeframe or campaign. ']")));
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	needHelp_forwardTableActions(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_forwardLists(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
		  String help=("Lists help you focus your SEO efforts.");
		  assertEquals("help 6/9:",help,wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-left']/div"))).getText());
	  
		  //Test qtip tip
		  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Any lists that you create will be displayed here. Selecting a list will display only the pages that have been assigned to that list.']")));
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	needHelp_forwardLists(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_forwardPagesTab(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
		 
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
		  String help=("The Pages tab shows details about how well each of your web pages are optimized for search engines.");
		  assertEquals("help 7/9:",help,wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-left']/div"))).getText());
		  
		  //Check qtip-tip:
		  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Click on a page title now to see details and optimization issues for that page. ']")));
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	needHelp_forwardPagesTab(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_done(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	 
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-hide']"))).click();
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-dialog trainer-minimized']/div/div/div/a"))).click();
		 
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='done']"))).click();
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	needHelp_done(numTry);
	  }
  }
  
  //=========================================================================================================
  private void showLinks_fifty(int numTry)throws Exception {
	  goBase();
	  int SUMFIF=50;
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//span[@class='filter_selection tags']/ul/li/a"))).click();
		  Thread.sleep(3000);
		  try{builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-text-filter-clear']/span")))).perform();}catch(Exception e){}
		  
		  //show 50:
		  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='numPages show-50']"))).click();}catch(Exception e){}
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']/tbody")));
		  Thread.sleep(7000);
		  assertEquals("count 50:",SUMFIF,getRowCount(By.xpath("//table[@id='page_table']/tbody")));
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		 	
		
		 	numTry++;
		 	showLinks_fifty(numTry);
	  }
	  catch(ComparisonFailure ex){
		  printFailed();
			 throw ex; 
	  }
  }
  
  //=========================================================================================================
  private void showLinks_twentyFifth(int numTry)throws Exception {
	  goBase();
	  int SUMTF=25;
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='numPages show-25']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']/tbody")));
		  Thread.sleep(5000);
		  assertEquals("count 25:",SUMTF,getRowCount(By.xpath("//table[@id='page_table']/tbody")));
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	showLinks_twentyFifth(numTry);
	  }
  }
  
  //=========================================================================================================
  private void showLinks_ten(int numTry)throws Exception {
	  goBase();
	  int SUMTH=10;
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='numPages show-10']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']/tbody")));
		  Thread.sleep(5000);
		  assertEquals("count 10:",SUMTH,getRowCount(By.xpath("//table[@id='page_table']/tbody")));
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	showLinks_ten(numTry);
	  }
  }
  
  //=========================================================================================================
  private void nextAndPrev_next(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Preparation for test:
		  //Set to pages:
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span"))).click();
		  //Set minimum display rows (for max next rate):
		  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='numPages show-10']"))).click();
		  }
		  catch(Exception e){}
		  
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='next']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']/tbody")));
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	nextAndPrev_next(numTry);
	  }
  }
  
  //=========================================================================================================
  private void nextAndPrev_next3(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='pages_pager']//a[text()='3']"))).click();
	      wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']/tbody")));
	  }
      catch(Exception e){
  	 	if(numTry>2){
			 printFailed();
			 throw e; 
	    }
		
		 	numTry++;
		 	nextAndPrev_next3(numTry);
	  }
  }
  
  //=========================================================================================================
  private void nextAndPrev_prev(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='previous']"))).click();
	  	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='previous']"))).click();
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	nextAndPrev_prev(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableAction_savingPageToGroup(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{builder.click(wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span")))).perform();
	  
		  try{builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-text-filter-clear']/span")))).perform();}catch(Exception e){}
	      wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-text-filter data-filter-off']/input"))).sendKeys("ynet");
		  try{wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']//a[text()='ynet חדשות תוכן ועדכונים - ידיעות אחרונות']")));
		  }
		  catch(Exception e){
			  addPages();
			  
			  wait = new WebDriverWait(driver, 450);
			  
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='success_message']/a"))).click();
			  
			  wait = new WebDriverWait(driver, 10);
			  
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-text-filter data-filter-off']/input"))).sendKeys("ynet");
			  wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']//a[text()='ynet חדשות תוכן ועדכונים - ידיעות אחרונות']")));
		  }
		  
		  //Save page to test group:
		  Actions builder = new Actions(driver);
		  builder.sendKeys(Keys.HOME).perform();
		  Thread.sleep(2000);
		  builder.sendKeys(Keys.PAGE_DOWN).perform();
		  Thread.sleep(2000);
		  builder.moveToElement(wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']/tbody/tr")))).perform();	  
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']/div")))).perform();;
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//input[@class='new-tag']"))).sendKeys("test");
		  Thread.sleep(2000);
		  builder.sendKeys(Keys.ENTER).perform();
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		    //If failed close dialog box.
		  	try{driver.findElement(By.xpath("//div[@id='add-pages-dialog']/div[2]/button/span")).click();	
		  	}catch(Exception ex){}
		 	
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	tableAction_savingPageToGroup(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableAction_enterTestGroup(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
 
	  try{wait.until(presenceOfElementLocated(By.xpath("//ul[@class='user-lists data-list']//a[text()='test']"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-text-filter-clear']"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']//a[text()='ynet חדשות תוכן ועדכונים - ידיעות אחרונות']")));
	  }
	  catch(Exception e){
		  	//If failed close dialog box.
		  	try{driver.findElement(By.xpath("//div[@id='add-pages-dialog']/div[2]/button/span")).click();	
		  	}catch(Exception ex){}
		 	
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	tableAction_enterTestGroup(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableAction_links(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String winHandleBefore = driver.getWindowHandle();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']/tbody/tr/td/a"))).click();
		  switcWindow();
		  assertEquals("Page Detail:","Website Page | Optify",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
		  
		  wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']//a[text()='ynet.co.il/home/0,7340,L-8,00.html']"))).click();
		  switcWindow();
		  assertEquals("link page","ynet חדשות תוכן ועדכונים - ידיעות אחרונות",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
		  
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		  	//If failed close dialog box.
		  	try{driver.findElement(By.xpath("//div[@id='add-pages-dialog']/div[2]/button/span")).click();	
		  	}catch(Exception ex){}
		 	
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	tableAction_links(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableAction_removePages(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
 
	  try{builder.moveToElement(wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']/tbody/tr")))).perform();
		  Thread.sleep(2000);
	  	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']/div[4]"))).click();
		  Thread.sleep(3000);
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//a[@class='confirm_delete_ok']/button")))).perform();
		  Thread.sleep(3000);
		  
		  builder.moveToElement(wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']/tbody/tr")))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']/div[4]"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='confirm-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//*[@id='page-tag-filter']/ul[2]/li[6]/a[2]/span"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='confirm-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
	  }
	  catch(Exception e){
		   //If failed close dialog box.
		   try{driver.findElement(By.xpath("//div[@id='add-pages-dialog']/div[2]/button/span")).click();	
		   }catch(Exception ex){}
		   
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	tableAction_removePages(numTry);
	  }
  }
  
  //=========================================================================================================
  private void sortIssuesTable_issuesSort(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Load issues table:
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-issues']/span")))).perform();
		  
		  //Test Issues sort:=======================================================
		  wait = new WebDriverWait(driver, 350);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='jqgh_rule']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div")));
		
		  wait = new WebDriverWait(driver, 10);
		  
		  try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='jqgh_status']//img[@src='/js/ext/jqGrid/themes/optify/images/sort_desc.gif']")));
		  		
		  }catch(WebDriverException ex){
			  wait.until(presenceOfElementLocated(By.xpath("//div[@id='jqgh_rule']"))).click();
		  }
		  
		  //Check down up sort:
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
		  
		  try{if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[3]/td/div/a"))).getText())!=-1)
			  	throw new Exception("Sort down abnormal");
		 
		  	  //Check up down sort:
		  	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='jqgh_rule']"))).click();
		 	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
		 	  
		 	  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[3]/td/div/a"))).getText())!=1)
		  			throw new Exception("Sort up abnormal");
	  		  
		  }
		  finally{}
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	sortIssuesTable_issuesSort(numTry);
	  }
  }
  
  //=========================================================================================================
  private void sortIssuesTable_impactSort(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{//Test Impact sort:=======================================================
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
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	sortIssuesTable_impactSort(numTry);
	  }
  }
  
  //=========================================================================================================
  private void sortIssuesTable_categorySort(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='jqgh_category']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[3]/div")));
		  
		  try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='jqgh_status']//img[@src='/js/ext/jqGrid/themes/optify/images/sort_desc.gif']']")));
		  		
		  }catch(WebDriverException ex){
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-pageviewsRaw first last']"))).click();
		  }
		  
		  //Check down up sort:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[3]/div")));
		  
		  try{if(wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr[2]/td[3]/div"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//div[@class='ui-tabs ui-widget ui-widget-content ui-corner-all']/div/table/tbody/tr[3]/td[3]/div"))).getText())==1)
			  	throw new Exception("Sort down abnormal");
		  	
			  //Check up down sort:
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-pageviewsRaw first last']"))).click();
		 	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[3]/div")));
		 	  
		 	  if(wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr[2]/td[3]/div"))).getText().compareTo(driver.findElement(By.xpath("//div[@class='ui-tabs ui-widget ui-widget-content ui-corner-all']/div/table/tbody/tr[2]/td[3]/div")).getText())==-1)
		  			throw new Exception("Sort up abnormal");
				  
		  }
		  finally{}
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
		
		 	numTry++;
		 	sortIssuesTable_categorySort(numTry);
	  }
  }
  
  //=========================================================================================================
  private void sortIssuesTable_statusSort(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='jqgh_status']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[4]")));
		  
		  try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='jqgh_status']//img[@src='/js/ext/jqGrid/themes/optify/images/sort_desc.gif']")));
		  		
		  }catch(WebDriverException ex){
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-numFollowedDomains first last']"))).click();
		  }
		  
		  //Check down up sort:
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[4]/a")));
		  
		  try{if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[4]/div"))).getText().compareTo(driver.findElement(By.xpath("//table[@id='issue_list']/tbody/tr[3]/td[4]/a")).getText())!=1)
			  	throw new Exception("Sort down abnormal");
		  	
			  //Check up down sort:
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-numFollowedDomains first last']"))).click();
		 	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td[4]/div")));
		 	  
		 	  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[4]/a"))).getText().compareTo(driver.findElement(By.xpath("//table[@id='issue_list']/table/tbody/tr[3]/td[4]/a")).getText())!=-1)
		  			;
				  
		  }
		  catch(WebDriverException ex){
			  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[4]/div"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[3]/td[4]/div"))).getText())==-1)
			  throw new Exception("Sort up abnormal");
		  }
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
			
			 	numTry++;
			 	sortIssuesTable_statusSort(numTry);
	  }
  }
  
  //=========================================================================================================	  
  private void issuesNote_addNote(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Go to Issues:
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-issues']/span")))).perform();
		  wait = new WebDriverWait(driver, 350);
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
		  wait = new WebDriverWait(driver, 10);
		  
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[5]/span/a")))).perform();
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
		  
		  Thread.sleep(2000);
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
			
			 	numTry++;
			 	issuesNote_addNote(numTry);
	  }
  }
  
  //=========================================================================================================
  private void issuesNote_deleteNote(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='stickynote']/a"))).click();
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
			
			 	numTry++;
			 	issuesNote_deleteNote(numTry);
	  }
  }
  
  //=========================================================================================================
  private void setIssueToGroup_add(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-issues']/span"))).click();
		  wait = new WebDriverWait(driver, 350);
		  String val=wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a[2]"))).getAttribute("href");
		  wait = new WebDriverWait(driver, 10);
		  
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td[5]/span[2]/a")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//li[@class='create_new input_wrapper']/input"))).sendKeys("test");
		  Thread.sleep(2000);
		  builder.sendKeys(Keys.ENTER).perform();
		  Thread.sleep(3000);
		  
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='filter_selection']//a[text()='test']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
		  assertEquals("Check Issue to group:",val,wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a[2]"))).getAttribute("href"));
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
			
			 	numTry++;
			 	driver.navigate().refresh();
			 	setIssueToGroup_add(numTry);
	  }
  }
  
  //=========================================================================================================
  private void setIssueToGroup_delete(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{//Delete the test group:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='filter_selection']/span[5]/a[2]/span"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='confirm-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
			
			 	numTry++;
			 	setIssueToGroup_delete(numTry);
	  }
  }
  
  //=========================================================================================================
  private void issuesLinks_qtipHavering(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{builder.click(wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-issues']/span")))).perform();
		  wait = new WebDriverWait(driver, 350);
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
		  wait = new WebDriverWait(driver, 10);
		  
		  //Check havering:
		  builder.moveToElement(wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='qtip qtip-cream']")));
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
			
			 	numTry++;
			 	issuesLinks_qtipHavering(numTry);
	  }
  }
  
  //=========================================================================================================
  private void issuesLinks_enterLink(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String winHandleBefore = driver.getWindowHandle();
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a"))).click();
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
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
			
			 	numTry++;
			 	issuesLinks_enterLink(numTry);
	  }
  }
  
  //=========================================================================================================
  private String issuesPrevNext_next(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{//Test preparations:
		  //Set to Issues:
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-issues']/span")))).perform();
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
		  String sumIssues=wait.until(presenceOfElementLocated(By.xpath("//span[@class='page_status']"))).getText().substring(10);
		  Thread.sleep(3000);
		  
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='next_page']"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
		  assertEquals("Check page num 2:","Page 2 of "+sumIssues,wait.until(presenceOfElementLocated(By.xpath("//span[@class='page_status']"))).getText());
		  
		  return sumIssues;
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
			
			 	numTry++;
			 	return issuesPrevNext_next(numTry);
	  }
  }
  
//==================================================================================================
  private void helpWithThisPage_link(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
  
	  try{String winHandleBefore = driver.getWindowHandle();
	  
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//i[@class='icon-help-circle nav-icon nav-icon-white optify-nav-button-icon']")))).perform();
		  Thread.sleep(3000);
		  switcWindow();
		  assertEquals("Help with this page","Pages overview : Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
		  if(numTry>2){
				 printFailed();
				 throw e; 
		  }
			 
			 numTry++;
			 helpWithThisPage_link(numTry);
	  }
  }
  
  //=========================================================================================================
  private void issuesPrevNext_prev(int numTry,String sumIssues)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='previous_page hidden-none']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
		  assertEquals("Check page num 1:","Page 1 of "+sumIssues,wait.until(presenceOfElementLocated(By.xpath("//span[@class='page_status']"))).getText());
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
				
				 	numTry++;
				 	issuesPrevNext_prev(numTry,sumIssues);
	  }
  }
  
  //=========================================================================================================
  private void issuesShow_fifty(int numTry)throws Exception {
	  goBase();
	  int FIFNUM=51;
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{builder.click(wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-issues']/span")))).perform();
		  wait = new WebDriverWait(driver, 350);
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
		  wait = new WebDriverWait(driver, 10);
		  
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='show-50']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
		  assertEquals("Count 50 results:",FIFNUM,getRowCount(By.xpath("//table[@id='issue_list']/tbody")));
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
			
		 	numTry++;
		 	issuesShow_fifty(numTry);
  }
  }
  
  //=========================================================================================================
  private void issuesShow_twentyFive(int numTry)throws Exception {
	  goBase();
	  int TFNUM=26;
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='show-25']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
		  assertEquals("Count 25 results:",TFNUM,getRowCount(By.xpath("//table[@id='issue_list']/tbody")));
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
			
			 	numTry++;
			 	issuesShow_twentyFive(numTry);
	  }
  }

  //=========================================================================================================
  private void issuesShow_ten(int numTry)throws Exception {	 
	  goBase();
	  int TENNUM=11;
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='show-10']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
		  assertEquals("Count 10 results:",TENNUM,getRowCount(By.xpath("//table[@id='issue_list']/tbody")));
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
			
			 	numTry++;
			 	issuesShow_ten(numTry);
	  }
  }
  
  //=========================================================================================================
  private static void enterToPages(int numTry)throws Exception {	 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='optify-nav-menu-icon']/span"))).click();
	  	  Thread.sleep(2000);
	  	  wait.until(presenceOfElementLocated(By.xpath("//*[@id='main-menu-content']/div/div[1]/div[2]/div[2]/div[2]/a"))).click();
	      assertEquals("Pages page","Pages | Optify",driver.getTitle());
	  }
	  catch(Exception e){
		 	if(numTry>2){
				 printFailed();
				 throw e; 
		    }
			
			 	numTry++;
			 	enterToPages(numTry);
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
	  
     for(String elem:Pages.scripList)
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
	  
	  
  

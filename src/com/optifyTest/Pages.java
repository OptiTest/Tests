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
  static String setPath="selenium\\chromedriver.exe";
  
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
	
	enterToPages();
  }
  
  public static void enterToPages() throws Throwable{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  Actions builder = new Actions(driver);
	  
	  System.out.print("\nLogin to Optify.");
	  
	  driver.get(homeAddress+"/login");
	  driver.findElement(By.id("j_username")).sendKeys(userName);
	  driver.findElement(By.id("j_password")).sendKeys(password);
	  driver.findElement(By.id("login_button")).click();
	  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
	  
	  System.out.println(" v");
	  
	  System.out.print("\n\nEntering to Pages page.");
	  
	  wait.until(presenceOfElementLocated(By.xpath("//li[@class='drive']/a/span")));
	  builder.clickAndHold(driver.findElement(By.xpath("//li[@class='drive']/a/span"))).perform();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//html/body/div/div[2]/ul/li[2]/ul/li[2]/a")).click();
	  assertEquals("Pages page","Pages | Optify",driver.getTitle());
	  
	  System.out.println(" v");
	  Thread.sleep(3000);
  }
  
  @Test
  public void addPages() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.print("\n\nEntering Add pages.");
	  addPages_enter(numTry);
	  System.out.println(" v");
	  System.out.print("Testing Add page.");
	  addPages_save(numTry);
	  System.out.println(" v");
	  System.out.print("Testing Add page into group.");
	  addPages_saveGroup(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void tableSort() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting tabel sort:");
	  System.out.print("Testing title sort.");
	  tableSort_titleSort(numTry);
	  System.out.println(" v");
	  System.out.print("Testing Optify score sort.");
	  tableSort_optifyScoreSort(numTry);
	  System.out.println(" v");
	  System.out.print("Testing Views sort.");
	  tableSort_viewsSort(numTry);
	  System.out.println(" v");
	  System.out.print("Testing Links sort.");
	  tableSort_linksSort(numTry);
	  System.out.println(" v");
	 
	  Thread.sleep(3000);
  }
  
  @Test
  public void inboundsLink() throws Exception{
	  String winHandleBefore = driver.getWindowHandle();
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting inbound links:");
	  System.out.print("Entering inbound links.");
	  inboundsLink_enter(numTry);
	  System.out.println(" v");
	  System.out.print("Entering Go to links application.");
	  inboundsLink_goToLinksApplication(numTry,winHandleBefore);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void needHelp() throws Exception{
	  String winHandleBefore = driver.getWindowHandle();
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting need help:");
	  System.out.print("Entering need help.");
	  needHelp_enter(numTry);
	  System.out.println(" v");
	  System.out.print("Testing learn more about pages.");
	  needHelp_learnMoreAboutPages(numTry,winHandleBefore);
	  System.out.println(" v");
	  System.out.print("Testing going forward to overview.");
	  needHelp_forwardOverview(numTry);
	  System.out.println(" v");
	  System.out.print("Testing going forward to pages table.");
	  needHelp_forwardPageTable(numTry);
	  System.out.println(" v");
	  System.out.print("Testing going forward to add pages.");
	  needHelp_forwardAddPages(numTry);
	  System.out.println(" v");
	  System.out.print("Testing going forward to table actions.");
	  needHelp_forwardTableActions(numTry);
	  System.out.println(" v");
	  System.out.print("Testing going forward to lists.");
	  needHelp_forwardLists(numTry);
	  System.out.println(" v");
	  System.out.print("Testing going forward to pages tab.");
	  needHelp_forwardPagesTab(numTry);
	  System.out.println(" v");
	  System.out.print("Testing minimize need help.");
	  needHelp_minimizeNeedHelp(numTry);
	  System.out.println(" v");
	 
	  Thread.sleep(3000);
  }
  
  @Test
  public void showLinks() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting show links:");
	  System.out.print("Testing showing 50 links.");
	  showLinks_fifty(numTry);
	  System.out.println(" v");
	  System.out.print("Testing showing 25 links.");
	  showLinks_twentyFifth(numTry);
	  System.out.println(" v");
	  System.out.print("Testing showing 10 links.");
	  showLinks_ten(numTry);
	  System.out.println(" v");
	
	  Thread.sleep(3000);
  }
  
  @Test
  public void nextAndPrev() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting next and prev:");
	  System.out.print("Testing next.");
	  nextAndPrev_next(numTry);
	  System.out.println(" v");
	  System.out.print("Testing next3.");
	  nextAndPrev_next3(numTry);
	  System.out.println(" v");
	  System.out.print("Testing prev.");
	  nextAndPrev_prev(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void tableAction() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting table actions:");
	  System.out.print("Testing saving page to group.");
	  tableAction_savingPageToGroup(numTry);
	  System.out.println(" v");
	  System.out.print("Entering test group.");
	  tableAction_enterTestGroup(numTry);
	  System.out.println(" v");
	  System.out.print("Testing links.");
	  tableAction_links(numTry);
	  System.out.println(" v");
	  System.out.print("Testing remove pages.");
	  tableAction_removePages(numTry);
	  System.out.println(" v");
	 
	  Thread.sleep(3000);
  }

  @Test
  public void sortIssuesTable() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting sort issues table:");
	  System.out.print("Testing issues sort.");
	  sortIssuesTable_issuesSort(numTry);
	  System.out.println(" v");
	  System.out.print("Testing impact sort.");
	  sortIssuesTable_impactSort(numTry);
	  System.out.println(" v");
	  System.out.print("Testing Category sort.");
	  sortIssuesTable_categorySort(numTry);
	  System.out.println(" v");
	  System.out.print("Testing Status sort.");
	  sortIssuesTable_statusSort(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void issuesNote() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting adding not:");
	  System.out.print("Testing add a note.");
	  issuesNote_addNote(numTry);
	  System.out.println(" v");
	  System.out.print("Testing delete a note.");
	  issuesNote_deleteNote(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void setIssueToGroup() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.print("\n\nTesting add a issue to group.");
	  setIssueToGroup_add(numTry);
	  System.out.println(" v");
	  System.out.print("Testing delete the test group.");
	  setIssueToGroup_delete(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void issuesLinks() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting issues links.");
	  issuesLinks_qtipHavering(numTry);
	  System.out.println(" v");
	  System.out.print("Entering issues link.");
	  issuesLinks_enterLink(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void issuesPrevNext() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting issues prev & next:");
	  System.out.print("Testing issues next.");
	  String sumIssues=issuesPrevNext_next(numTry);
	  System.out.println(" v");
	  System.out.print("Testing issues prev.");
	  issuesPrevNext_prev(numTry,sumIssues);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void issuesShow() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting issues show:");
	  System.out.print("Testing issues show 50.");
	  issuesShow_fifty(numTry);
	  System.out.println(" v");
	  System.out.print("Testing issues show 25.");
	  issuesShow_twentyFive(numTry);
	  System.out.println(" v");
	  System.out.print("Testing issues show 10.");
	  issuesShow_ten(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
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
		 	if(numTry>3)
			  throw e;
		
		 	//Close Add Pages dialog box & restaet test.
		 	try{wait.until(presenceOfElementLocated(By.xpath("//span[@class='ui-icon ui-icon-closethick']"))).click();
		 	}
		 	catch(Exception ex){}
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
		 	if(numTry>3)
			  throw e;
		
		 	numTry++;
		 	addPages_save(numTry);
	  }
  }
  
  //=========================================================================================================
  private void addPages_saveGroup(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);

	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span"))).click();
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
		 	if(numTry>3)
			  throw e;
		 	
		 	//Close Add Pages dialog box & restaet test.
		 	try{wait.until(presenceOfElementLocated(By.xpath("//span[@class='ui-icon ui-icon-closethick']"))).click();
		 	}
		 	catch(Exception ex){}
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
		 	if(numTry>3)
			  throw e;
		
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
		 	if(numTry>3)
			  throw e;
		
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
		 	if(numTry>3)
			  throw e;
		
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
		 	if(numTry>3)
			  throw e;
		
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
		 	if(numTry>3)
			  throw e;
		
		 	//Close Inbound links dialog box & restart test.
		 	try{wait.until(presenceOfElementLocated(By.xpath("//span[@class='ui-icon ui-icon-closethick']"))).click();
		 	}
		 	catch(Exception ex){}
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
		 	if(numTry>3)
			  throw e;
		
		 	numTry++;
		 	inboundsLink_goToLinksApplication(numTry,winHandleBefore);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_enter(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span"))).click();
		  
		  //Open need help:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-dialog trainer-minimized']/div/div/div/a"))).click();
		  Thread.sleep(3000);
		  
		  String help1=("The Pages application analyzes the pages on your website and suggests ways to improve your rankings in search results. Let's get started!");
		  assertEquals("help 1/9:",help1,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  }
	  catch(Exception e){
		 	if(numTry>3)
			  throw e;
		
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
		 	if(numTry>3)
			  throw e;
		
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
		 	if(numTry>3)
			  throw e;
		
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
		 	if(numTry>3)
			  throw e;
		
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
		 	if(numTry>3)
			  throw e;
		
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
		 	if(numTry>3)
			  throw e;
		
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
		 	if(numTry>3)
			  throw e;
		
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
		 	if(numTry>3)
			  throw e;
		
		 	numTry++;
		 	needHelp_forwardPagesTab(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_minimizeNeedHelp(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	 
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-hide']"))).click();
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-dialog trainer-minimized']/div/div/div/a"))).click();
		 
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='done']"))).click();
	  }
	  catch(Exception e){
		 	if(numTry>3)
			  throw e;
		
		 	numTry++;
		 	needHelp_minimizeNeedHelp(numTry);
	  }
  }
  
  //=========================================================================================================
  private void showLinks_fifty(int numTry)throws Exception {
	  goBase();
	  int SUMFIF=50;
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span"))).click();
		  Thread.sleep(3000);
		  
		  //show 50:
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='numPages show-50']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']/tbody")));
		  Thread.sleep(5000);
		  assertEquals("count 50:",SUMFIF,getRowCount(By.xpath("//table[@id='page_table']/tbody")));
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		 	if(numTry>3)
			  throw e;
		
		 	numTry++;
		 	showLinks_fifty(numTry);
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
		 	if(numTry>3)
			  throw e;
		
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
		 	if(numTry>3)
			  throw e;
		
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
		 	if(numTry>3)
			  throw e;
		
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
		 	if(numTry>3)
			  throw e;
		
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
		 	if(numTry>3)
			  throw e;
		
		 	numTry++;
		 	nextAndPrev_prev(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableAction_savingPageToGroup(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-pages']/span"))).click();
	  
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
		  builder.moveToElement(wait.until(presenceOfElementLocated(By.xpath("//table[@id='page_table']/tbody/tr")))).perform();	  
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']/div")))).perform();;
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//input[@class='new-tag']"))).sendKeys("test");
		  Thread.sleep(2000);
		  builder.sendKeys(Keys.ENTER).perform();
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		 	if(numTry>3)
			  throw e;
		
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
		  wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']//a[text()='וואלה!']")));
		  wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']//a[text()='ynet חדשות תוכן ועדכונים - ידיעות אחרונות']")));
	  }
	  catch(Exception e){
		 	if(numTry>3)
			  throw e;
		
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
		  
		  wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']//a[text()='http://www.walla.co.il/']"))).click();
		  switcWindow();
		  assertEquals("link page","וואלה!",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
		  
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		 	if(numTry>3)
			  throw e;
		
		 	numTry++;
		 	tableAction_links(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableAction_removePages(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
 
	  try{builder.moveToElement(wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']/tbody/tr")))).perform();
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
		 	if(numTry>3)
			  throw e;
		
		 	numTry++;
		 	tableAction_removePages(numTry);
	  }
  }
  
  //=========================================================================================================
  private void sortIssuesTable_issuesSort(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Load issues table:
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-issues']/span"))).click();
		  
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
		 	if(numTry>3)
			  throw e;
		
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
		 	if(numTry>3)
			  throw e;
		
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
		 	if(numTry>3)
			  throw e;
		
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
		 	if(numTry>3)
				  throw e;
			
			 	numTry++;
			 	sortIssuesTable_statusSort(numTry);
	  }
  }
  
  //=========================================================================================================	  
  private void issuesNote_addNote(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Go to Issues:
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-issues']/span"))).click();
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
		 	if(numTry>3)
				  throw e;
			
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
		 	if(numTry>3)
				  throw e;
			
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
		 	if(numTry>3)
				  throw e;
			
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
		 	if(numTry>3)
				  throw e;
			
			 	numTry++;
			 	setIssueToGroup_delete(numTry);
	  }
  }
  
  //=========================================================================================================
  private void issuesLinks_qtipHavering(int numTry)throws Exception {
	  goBase();
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-issues']/span"))).click();
		  wait = new WebDriverWait(driver, 350);
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
		  wait = new WebDriverWait(driver, 10);
		  
		  //Check havering:
		  builder.moveToElement(wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='qtip qtip-cream']")));
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
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
		 	if(numTry>3)
				  throw e;
			
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
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-issues']/span"))).click();
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
		  System.out.printf(sumIssues);
		  Thread.sleep(3000);
		  
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='next_page']"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
		  assertEquals("Check page num 2:","Page 2 of "+sumIssues,wait.until(presenceOfElementLocated(By.xpath("//span[@class='page_status']"))).getText());
		  
		  return sumIssues;
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
			 	numTry++;
			 	issuesPrevNext_next(numTry);
	  }
	
	  return null;
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
			 	if(numTry>3)
					  throw e;
				
				 	numTry++;
				 	issuesPrevNext_prev(numTry,sumIssues);
	  }
  }
  
  //=========================================================================================================
  private void issuesShow_fifty(int numTry)throws Exception {
	  goBase();
	  int FIFNUM=51;
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='tip-issues']/span"))).click();
		  wait = new WebDriverWait(driver, 350);
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
		  wait = new WebDriverWait(driver, 10);
		  
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='show-50']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='issue_list']/tbody/tr[2]/td/div/a")));
		  assertEquals("Count 50 results:",FIFNUM,getRowCount(By.xpath("//table[@id='issue_list']/tbody")));
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
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
		 	if(numTry>3)
				  throw e;
			
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
		 	if(numTry>3)
				  throw e;
			
			 	numTry++;
			 	issuesShow_ten(numTry);
	  }
  }
  
  //=========================================================================================================
}
	  
	  
  

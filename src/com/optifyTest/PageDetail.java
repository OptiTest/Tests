package com.optifyTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
public class PageDetail extends TestCase {
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
	
	enterToDashBoard();
  }
  
  public static void enterToDashBoard() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\nLogin to Optify.");
	  
	  //Log in Optify
	  driver.get(homeAddress+"/login");
	  driver.findElement(By.id("j_username")).sendKeys(userName);
	  driver.findElement(By.id("j_password")).sendKeys(password);
	  driver.findElement(By.id("login_button")).click();
	  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
	  
	  System.out.println(" v");
	  
	  print("\n\nEntering Page Detail.");
	  enterToPages(numTry);
	  enterToPageDetail(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void helpWithThisPage() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nEntering Help with this page.");
	  helpWithThisPage_enter(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }

  @Test
  public void export() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting export.");
	  export_click(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void pageLink() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting page link.");
	  pageLink_click(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void share() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting Sharing:");
	  print("Testing Social sharing.");
	  share_social(numTry);
	  System.out.println(" v");
	  print("Testing Email sharing.");
	  share_email(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void tableSort() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting tabel sort:");
	  print("Testing status sort.");
	  tableSort_status(numTry);
	  System.out.println(" v");
	  print("Testing issues sort.");
	  tableSort_issues(numTry);
	  System.out.println(" v");
	  print("Testing impact sort.");
	  tableSort_impact(numTry);
	  System.out.println(" v");
	  print("Testing category sort.");
	  tableSort_category(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void assignToList() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting assign to list.");
	  assignToList_test(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void tableAction() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting tabel actions:");
	  print("Testing add note.");
	  tableAction_addNote(numTry);
	  System.out.println(" v");
	  print("Testing edit note.");
	  tableAction_editNote(numTry);
	  System.out.println(" v");
	  print("Testing remove note.");
	  tableAction_removeNote(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void needHelp() throws Exception{
	  String winHandleBefore = driver.getWindowHandle();
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting need help:");
	  print("Opening need help.");
	  needHelp_open(numTry);
	  System.out.println(" v");
	  print("Entering Learn more about Page Detail");
	  needHelp_learnMoreAboutPageDetail(numTry,winHandleBefore);
	  System.out.println(" v");
	  print("Going forward to Page Detail.");
	  needHelp_goingForwardToPageDetail(numTry);
	  System.out.println(" v");
	  print("Going forward to Optimize With.");
	  needHelp_goingForwardOptimizeWith(numTry);
	  System.out.println(" v");
	  print("Going forward Page Optimize Results.");
	  needHelp_goingForwardPageOptimizeResults(numTry);
	  System.out.println(" v");
	  print("Going forward done.");
	  needHelp_goingForwardDone(numTry,winHandleBefore);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void searchInOptimizeWith() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting search in optimize with.");
	  searchInOptimizeWith_test(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void manageKeywords() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting manage keywords.");
	  manageKeywords_test(numTry);
	  System.out.println(" v");
	  
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
  private static void switcWindow(){
		  for(String winHandle : driver.getWindowHandles())
			  driver.switchTo().window(winHandle);
	}
  
  //================================================================================================
  private static void enterToPages(int numTry)throws Exception {	 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  Actions builder = new Actions(driver);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//li[@class='drive']/a/span")));
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//li[@class='drive']/a/span")))).perform();
		  
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//html/body/div/div[2]/ul/li[2]/ul/li[2]/a"))).click();
		  assertEquals("Pages page","Pages | Optify",driver.getTitle());
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	enterToPages(numTry);
	  }
  }
  
  //=========================================================================================================
  private static void enterToPageDetail(int numTry)throws Exception {	 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{//Get the page detail...
		  Thread.sleep(5000);
		  wait.until(presenceOfElementLocated(By.xpath("//input[@id='page-text-filter']"))).sendKeys("microsoft");
		  Thread.sleep(3000);
		  try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td/a"))).click();
		  }
		  catch(Exception e){
			  //Add page & save to test:
			  wait.until(presenceOfElementLocated(By.xpath("//button[@class='add-pages-button medium orange ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
			  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='add_pages_textarea']"))).sendKeys("www.microsoft.com");
			  Thread.sleep(3000);
			  wait.until(presenceOfElementLocated(By.xpath("//button[@id='add_pages']"))).click();

			  Thread.sleep(3000);
			  
			   //Try to get the page detail second time...
			  Thread.sleep(2000);
			  wait.until(presenceOfElementLocated(By.xpath("//input[@id='page-text-filter']"))).sendKeys("microsoft");
			  Thread.sleep(3000);
			  wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']/div/table/tbody/tr/td/a"))).click();
		  }
		  Thread.sleep(2000);
		  switcWindow();
		  
		  assertEquals("Website Page | Optify:","Website Page | Optify",driver.getTitle());
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	driver.get(homeAddress+"/page/overview");
		 	enterToPageDetail(numTry);
	  }
  }
  
  //=========================================================================================================
  private void helpWithThisPage_enter(int numTry)throws Exception {	
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String winHandleBefore = driver.getWindowHandle();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='help_for_this_page iconlink track']/span"))).click();
		  switcWindow();
		  assertEquals("Page Detail : Help and Support:","Page Detail : Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	helpWithThisPage_enter(numTry);
	  }
  }
  
  //=========================================================================================================
  private void export_click(int numTry)throws Exception {	
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='sharing_div']/a"))).click();
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	export_click(numTry);
	  }
  }
  
  //=========================================================================================================
  private void pageLink_click(int numTry)throws Exception {	
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String winHandleBefore = driver.getWindowHandle();
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='quiet out']"))).click();
		  switcWindow();
		  assertEquals("link page","Microsoft Home Page | Devices and Services",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	pageLink_click(numTry);
	  }
  }
  
  //=========================================================================================================
  private void share_social(int numTry)throws Exception {	
  	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//button[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//input[@id='account-checkbox-172668869']"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='share-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span"))).click();
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	share_social(numTry);
	  }
  }
  
  //=========================================================================================================
  private void share_email(int numTry)throws Exception {	
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//button[@class='close ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();}catch(Exception e){}
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//li[@class='ui-state-default ui-corner-top']"))).click();
		  String getId=driver.findElement(By.xpath("//div[@id='emailTo']/div")).getAttribute("id");
		  Thread.sleep(2000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@id='emailTo']/div")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul//label[text()='orasnin@gmail.com']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='share-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
		  
		  wait.until(presenceOfElementLocated(By.xpath("//span[@class='post-message success']")));
		  assertEquals("Your email has been sent:","Your email has been sent.",wait.until(presenceOfElementLocated(By.xpath("//span[@class='post-message success']"))).getText());
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='close ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	share_email(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableSort_status(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-status first last']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td/div")));
		  
		  try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']//th[@class='sorted-desc']")));
		  		
		  }catch(WebDriverException ex){
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-status first last']"))).click();
		  }
		  
		  //Check down up sort:
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td/div")));
		  
		  try{if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td/div"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr[2]/td/div"))).getText())==1)
			  	throw new Exception("Sort down abnormal");
		  
		  	  //Check up down sort:
		  	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-status first last']"))).click();
		      wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td/div")));
		 	  
		 	  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td/div"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr[2]/td/div"))).getText())==-1)
		  			throw new Exception("Sort up abnormal");
				  
		  }
		  finally{}
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	tableSort_status(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableSort_issues(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-issues first last']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[2]/a")));
		  
		  try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']//th[@class='sorted-desc']")));
		  		
		  }catch(WebDriverException ex){
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-issues first last']"))).click();
		  }
		  
		  //Check down up sort:
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[2]/a")));
		  
		  try{if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[2]/a"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr[2]/td[2]/a"))).getText())==-1)
			  	throw new Exception("Sort down abnormal");
		  	
		  	  //Check up down sort:
		  	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-issues first last']"))).click();
		 	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[2]/a")));
		 	  Thread.sleep(2000);
		 	  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[2]/a"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr[2]/td[2]/a"))).getText())==1)
		 		  throw new Exception("Sort up abnormal");
				  
		  }
		  finally{}
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	tableSort_issues(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableSort_impact(int numTry)throws Exception {
  	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-impact first last']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[3]/div")));
		  
		  try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']//th[@class='sorted-desc']")));
		  		
		  }catch(WebDriverException ex){
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-impact first last']"))).click();
		  }
		  
		  //Check down up sort:
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[3]/div")));
		  
		  try{if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[3]/div"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr[2]/td[3]/div"))).getText())==1)
			  	throw new Exception("Sort down abnormal");
		  
		      //Check up down sort:
		  	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-impact first last']"))).click();
		      wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[3]/div")));
		 	  
		 	  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[3]/div"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr[2]/td[3]/div"))).getText())==-1)
		  			throw new Exception("Sort up abnormal");
				  
		  }
		  finally{}
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	tableSort_impact(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableSort_category(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{Thread.sleep(2000);
		  
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-category first last']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[4]")));
		  
		  try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='overview_tab']//th[@class='sorted-desc']")));
		  		
		  }catch(WebDriverException ex){
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-category first last']"))).click();
		  }
		  
		  //Check down up sort:
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[4]")));
		  
		  try{if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[4]"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr[2]/td[4]"))).getText())==1)
			  	throw new Exception("Sort down abnormal");
		  
		  	  //Check up down sort:
		  	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-category first last']"))).click();
		      wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[4]")));
		 	  
		 	  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[4]"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr[2]/td[4]"))).getText())==-1)
		  			throw new Exception("Sort up abnormal");
				  
		  }
		  finally{}
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	tableSort_category(numTry);
	  }
  }
  
  //=========================================================================================================
  private void assignToList_test(int numTry)throws Exception {
  	  WebDriverWait wait = new WebDriverWait(driver, 10);
   
	  try{wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td")));
	  
		  String getIssueName=wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[2]"))).getText();
		  
		  builder.moveToElement(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']/div")));
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']/div")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//input[@class='new-tag']"))).sendKeys("test");
		  Thread.sleep(2000);
		  builder.sendKeys(Keys.ENTER).perform();
		  Thread.sleep(3000);
		  
		  //Check new list:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='filter_selection']//a[text()='test']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td")));
		  
		  Thread.sleep(3000);
		  
		  try{assertEquals("Check issue name in test group",getIssueName,wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td[2]"))).getText());
		  	  assertEquals("Check number of issues in test group",1,getRowCount(By.xpath("table[@id='list']/tbody")));
		  }
		  catch(WebDriverException ex){wait.until(presenceOfElementLocated(By.xpath("//div[@class='filter_selection']//a[text()='test']//span[@class='icon-remove']"))).click();
		  
		  		driver.close();
		  		throw ex;
		  }
		  
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='filter_selection']//a[text()='test']")));
		  wait.until(presenceOfElementLocated(By.xpath("//html/body/div[2]/div[2]/div[5]/div/div/div/div[2]/span[5]/a[2]/span"))).click();
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='confirm-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	assignToList_test(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableAction_addNote(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{try{wait.until(presenceOfElementLocated(By.xpath("//span[@class='orglist']//a[text()='All'"))).click();
	  	  }
	      catch(Exception e){}
	  		
	      Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td")));
		  builder.moveToElement(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr")))).perform();
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']/div[2]")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//textarea[@class='edit-note']"))).sendKeys("test");
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='save-note ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
		  Thread.sleep(3000);
		  assertEquals("Test note value:","test",driver.findElement(By.xpath("//table[@id='list']/tbody/tr/td[2]/div")).getText());
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
			  
		 	numTry++;
		 	tableAction_addNote(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableAction_editNote(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td")));
		  builder.moveToElement(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']/div[2]"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//textarea[@class='edit-note']"))).sendKeys("123");
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='save-note ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
		  Thread.sleep(3000);
		  assertEquals("Test note value:","test123",driver.findElement(By.xpath("//table[@id='list']/tbody/tr/td[2]/div")).getText());
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	tableAction_editNote(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableAction_removeNote(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
 
	  try{wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr/td")));
		  builder.moveToElement(wait.until(presenceOfElementLocated(By.xpath("//table[@id='list']/tbody/tr")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']/div[2]"))).click();
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='transparent delete ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span"))).click();
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='confirm-button ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
		  Thread.sleep(3000);
		  assertEquals("Test note value:","",driver.findElement(By.xpath("//table[@id='list']/tbody/tr/td[2]/div")).getText());
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	tableAction_removeNote(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_open(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-dialog trainer-minimized']/div/div/div/a"))).click();
		  Thread.sleep(3000);
		  
		  String help1=("Page Detail shows more information about your page, its keywords and areas to improve. Let's get started!");
		  assertEquals("help 1/5:",help1,wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-left']/div"))).getText());
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	needHelp_open(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_learnMoreAboutPageDetail(int numTry,String winHandleBefore)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-actions']/ul/li/a"))).click();
		  switcWindow();
		  assertEquals("Learn more about Page Detail:","Page Detail : Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	needHelp_learnMoreAboutPageDetail(numTry,winHandleBefore);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_goingForwardToPageDetail(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
		  String help=("The Page Detail box shows performance and traffic data as well as links that can affect search engine rankings.");
		  assertEquals("help 2/5:",help,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
		  //Testing qtip tip
		  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Status shows whether the page is optimized for search engines. An Optify score of more than 75 is considered optimized.']")));
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	needHelp_goingForwardToPageDetail(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_goingForwardOptimizeWith(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
 
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
		  String help=("Optimize With shows which keywords are associated with your page.");
		  assertEquals("help 3/5:",help,wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-left']/div"))).getText());
	  
		  //Check qtip-tip:
		  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Select 1-3 keywords you would like to target for this page.']")));
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	needHelp_goingForwardOptimizeWith(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_goingForwardPageOptimizeResults(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
		  String help=("Page Optimization Results gives you detailed analysis and suggestions for improving your page's performance.");
		  assertEquals("help 4/5:",help,wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-left']/div"))).getText());
		  
		  //Check qtip-tip:
		  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Status indicates whether or not an issue has been solved.']")));
		  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Hover over an issue to see more detail.']")));
		  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Impact shows the relative SEO effect of the issue. Tackle the high impact issues first.']")));
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	needHelp_goingForwardPageOptimizeResults(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_goingForwardDone(int numTry,String winHandleBefore)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
		  String help=("And, that's it! If you have additional questions, check out our help pages, send us an email or give us a call at 1-877-2-OPTIFY");
		  assertEquals("help 5/5:",help,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
		  
		  //Test help:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-text']/a"))).click();
		  switcWindow();
		  assertEquals("Help:","Help and Support : Using Optify",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
		  Thread.sleep(3000);
		  
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-buttons']/a")));
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-buttons']/a"))).click();
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	needHelp_goingForwardDone(numTry,winHandleBefore);
	  }
  }
  
  //=========================================================================================================
  private void searchInOptimizeWith_test(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{String val=wait.until(presenceOfElementLocated(By.xpath("//ul[@id='keyword_list']/li"))).getText();
		  wait.until(presenceOfElementLocated(By.xpath("//input[@placeholder='Search keywords']"))).sendKeys(val);
		  Thread.sleep(3000);
		  assertEquals("Search result",val,wait.until(presenceOfElementLocated(By.xpath("//ul[@id='keyword_list']/li"))).getText());
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	searchInOptimizeWith_test(numTry);
	  }
  }
  
  //=========================================================================================================
  private void manageKeywords_test(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{builder.moveByOffset(0,wait.until(presenceOfElementLocated(By.xpath("//a[@class='manage-keywords-link']"))).getLocation().y);
	  
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='manage-keywords-link']"))).click();
		  assertEquals("Manage keywords link:","Keywords | Optify",driver.getTitle());
		  driver.navigate().back();
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	manageKeywords_test(numTry);
	  }
  }
  
  //=========================================================================================================
  private int getRowCount(By by) throws Exception {
      try { WebElement table = driver.findElement(by);
            List<WebElement> rows = table.findElements(By.tagName("tr"));
            return rows.size();
      
      } catch (Exception e) {
          return -1;
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

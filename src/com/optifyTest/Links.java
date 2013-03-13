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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

@RunWith(BlockJUnit4ClassRunner.class)
public class Links extends TestCase {
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
	  
	  System.out.println("\nStarting Links test:\n");
	  
	  print("\nLogin to Optify.");
	  
	  //Log in Optify:
	  driver.get(homeAddress+"/login");
	  driver.findElement(By.id("j_username")).sendKeys(userName);
	  driver.findElement(By.id("j_password")).sendKeys(password);
	  driver.findElement(By.id("login_button")).click();
	  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
	  
	  System.out.println(" v");
	  
	  print("\n\nEntering Links.");
	  enterToLinks(numTry);
	  System.out.println(" v");
  }
  
  @Test
  public void addUrls() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting add Urls.");
	  addUrls_test(numTry);
	  System.out.println(" v");
	  print("Testing add Urls to group.");
	  addUrls_toGroup(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void checkFilters() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting filters:");
	  print("Add new filter.");
	  checkFilters_add(numTry);
	  System.out.println(" v");
	  print("Check received filter values.");
	  checkFilters_checkReceivedValues(numTry);
	  System.out.println(" v");
	  print("Delete filters.");
	  checkFilters_delete(numTry);
	  System.out.println(" v");
	  Thread.sleep(3000);
  }
  
  @Test
  public void assignToList() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting adding url into list:");
	  assignToList_test(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void tableSort() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting table sort:");
	  print("Testing source sort.");
	  tableSort_source(numTry);
	  System.out.println(" v");
	  print("Testing priority sort.");
	  tableSort_priority(numTry);
	  System.out.println(" v");
	  print("Testing Link to my site sort.");
	  tableSort_linkToMySiteSort(numTry);
	  System.out.println(" v");
	  print("Testing link from my site sort.");
	  tableSort_linkFromMySiteSort(numTry);
	  System.out.println(" v");
	  print("Testing link to competitors sort.");
	  tableSort_linkToCompetitorsSort(numTry);
	  System.out.println(" v");
	  print("Testing page rank sort.");
	  tableSort_pageRank(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void helpWithThisPage() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting helpWithThisPage.");
	  helpWithThisPage_test(numTry);
	  System.out.println(" v");
	 
	  Thread.sleep(3000);
  }
  
  @Test
  public void importLinks() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	 
	  print("\n\nTesting import links.");
	  importLinks_test(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void exportCSV() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting export CSV.");
	  exportCSV_test(numTry);
	  System.out.println(" v");
	 
	  Thread.sleep(3000);
  }
  
  @Test
  public void exportPDF() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting export PDF.");
	  exportPDF_test(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void nextAndPrev() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\n\nTesting next and prev:");
	  nextAndPrev_test(numTry);
	  System.out.println(" v");
	 
	  Thread.sleep(3000);
  }
  
  @Test
  public void showResults() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting show results:");
	  print("Testing show fifty.");
	  showResults_fifty(numTry);
	  System.out.println(" v");
	  print("Testing show twentyfive.");
	  showResults_twentyfivey(numTry);
	  System.out.println(" v");
	  print("Testing show ten.");
	  showResults_ten(numTry);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void getSuggestions() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting get suggestions:");
	  print("Testing Get url suggest.");
	  String getUrl=getSuggestions_getUrlSuggest(numTry);
	  System.out.println(" v");
	  print("Testing save url to test group.");
	  getSuggestions_saveUrlToTestGroup(numTry);
	  System.out.println(" v");
	  print("check saved url and test group.");
	  getSuggestions_checkSaveUrlAndTestGroup(numTry,getUrl);
	  System.out.println(" v");
	  print("Testing delete url and group.");
	  getSuggestions_deleteUrlAndGroup(numTry);
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
  public void switcWindow(){
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
  private static void enterToLinks(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  Actions builder = new Actions(driver);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//li[@class='drive']/a/span")));
		  builder.clickAndHold(driver.findElement(By.xpath("//li[@class='drive']/a/span"))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//html/body/div/div[2]/ul/li[2]/ul/li[3]/a"))).click();
		  assertEquals("Links page","Links | Optify",driver.getTitle());
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	enterToLinks(numTry);
	  }
  }
  
  //=========================================================================================================
  private void  addUrls_test(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@class='view links_actions']/div/button")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='links_add_urls']"))).sendKeys("www.walla.co.il");
		  Thread.sleep(2000);
		  
		  wait.until(presenceOfElementLocated(By.xpath("//button[@id='add-url-save']/span"))).click();
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	try{wait.until(presenceOfElementLocated(By.xpath("//html/body/div[11]/div[1]/a/span"))).click();
		 	}
		 	catch(Exception ex){}
		 	
		 	numTry++;
		 	addUrls_test(numTry);
	  }
  }
  
  //=========================================================================================================
  private void addUrls_toGroup(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@class='view links_actions']/div/button")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='links_add_urls']"))).sendKeys("www.ynet.co.il");
		  Thread.sleep(2000);
		  
		  String getId=driver.findElement(By.xpath("//div[@id='list-control']/div")).getAttribute("id");
		  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='add-to-list ']/div/div/span[2]"))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']//input[@placeholder='Create new list']"))).sendKeys("test");
		  Thread.sleep(2000);
		  builder.sendKeys(Keys.ENTER).perform();
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//button[@id='add-url-save']/span"))).click();
	  }
	  
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	addUrls_toGroup(numTry);
	  }
  }
  
  //=========================================================================================================
  private void checkFilters_add(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='report-filter-list']/div[2]/span[2]")));
		  builder.clickAndHold(driver.findElement(By.xpath("//div[@id='report-filter-list']/div[2]/span[2]"))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='report-filter-list-pulldown']/ul/li"))).click();
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//li[@class='token-input-input-token']/input"))).sendKeys("ynet");
		  builder.sendKeys(Keys.ENTER).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//li[@class='token-input-input-token']/input"))).sendKeys("walla");
		  builder.sendKeys(Keys.ENTER).perform();
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
			
		 	numTry++;
		 	checkFilters_add(numTry);
	  }
  }
  
  //=========================================================================================================
  private void checkFilters_checkReceivedValues(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Check received values:
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']//a[text()='http://www.ynet.co.il']")));
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']//a[text()='http://www.walla.co.il']")));
		  
		  //Go over all values in table:
		  int sumOfRows=getRowCount(By.xpath("//table[@id='links-table']"));
		  for(int i=1; i<sumOfRows;i++){
			  if((wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr["+i+"]/td/span/a"))).getAttribute("title").matches("(?i).*ynet.*")||wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr["+i+"]/td/span/a"))).getAttribute("title").matches("(?i).*walla.*")))
				  ;
			  
			  else throw new Exception("Filter values hasn't be found!");
		  }
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	numTry++;
		 	checkFilters_checkReceivedValues(numTry);
	  }
  }
  
  //=========================================================================================================
  private void checkFilters_delete(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	 
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-link_source_url0']/ul/li[2]/span"))).click();
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-link_source_url0']/ul/li/span"))).click();
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//span[@class='icon-remove icon-white']"))).click();
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	numTry++;
		 	checkFilters_delete(numTry);
	  }
  }
  
  //=========================================================================================================
  private void assignToList_test(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Add url into new list:
		  String value=wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td/span/a"))).getAttribute("title");
		  
		  builder.moveToElement(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td/span/a")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='action button-action action-tags']")));
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='action button-action action-tags']")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//input[@class='new-tag']"))).sendKeys("test");
		  Thread.sleep(2000);
		  builder.sendKeys(Keys.ENTER).perform();
		  Thread.sleep(3000);
		  
		  //Check values in list:
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-list-pulldown']/div/span[2]")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-list-pulldown-pulldown']//label[text()='test']"))).click();
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-list-pulldown']/div/span[2]"))).click();
		  
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']//a[text()='http://www.ynet.co.il']")));
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']//a[text()='"+value+"']")));
		  
		  Thread.sleep(3000);
		  
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-list-pulldown']/div/span[2]")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-list-pulldown-pulldown']//label[text()='test']"))).click();
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-list-pulldown']/div/span[2]"))).click();
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	numTry++;
		 	assignToList_test(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableSort_source(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Test source sort:=======================================================
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_source_url first last']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td/span")));
		  
		  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/thead/tr/th"))).getAttribute("class").equals("first sorted-desc"))
			  ;
		  else
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_source_url first last']"))).click();
		  
		  
		  //Check down up sort:
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td/span")));
		  
		  try{if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td/span"))).getText().compareTo(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr[2]/td/span")).getText())==1)
			  	throw new Exception("Sort down abnormal");
		  
		  	  //Check up down sort:
		      wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_source_url first last']"))).click();
		      wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td/span")));
		 	  
		 	  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td/span"))).getText().compareTo(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr[2]/td/span")).getText())==-1)
		  			throw new Exception("Sort up abnormal");
				  
		  }
		  finally{}
	 }
	 catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	numTry++;
		 	tableSort_source(numTry);
	 }
  }
  
  //=========================================================================================================
  private void tableSort_priority(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Test priority sort:=======================================================
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_priority first last']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[2]/div/div")));
		  
		  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/thead/tr/th"))).getAttribute("class").equals("first sorted-desc"))
			  ;
		  else
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_priority first last']"))).click();
		  
		  //Check down up sort:
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[2]/div/div")));
		  
		  try{if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[2]/div/div/span"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[2]/div/div/span"))).getText())==-1)
			  	throw new Exception("Sort down abnormal");
		  	
		  //Check up down sort:
		  	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_priority first last']"))).click();
		 	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[2]/div/div")));
		 	  Thread.sleep(2000);
		 	  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[2]/div/div/span"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[2]/div/div/span"))).getText())==1)
		 		  throw new Exception("Sort up abnormal");
				  
		  }
		  finally{}
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	numTry++;
		 	tableSort_priority(numTry);
	 }
  }
  
  //=========================================================================================================
  private void tableSort_linkToMySiteSort(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Test Link to my site sort:=======================================================
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_to_my_site first last']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[3]/span")));
		  
		  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/thead/tr/th"))).getAttribute("class").equals("first sorted-desc"))
			  ;
		  else
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_to_my_site first last']"))).click();
		  
		  //Check down up sort:
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[3]/span")));
		  
		  try{if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[3]/span"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[3]/span"))).getText())==1)
			  	throw new Exception("Sort down abnormal");
		  
		  	  //Check up down sort:
		  	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_to_my_site first last']"))).click();
		      wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[3]/span")));
		 	  
		 	  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[3]/span"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[3]/span"))).getText())==-1)
		  			throw new Exception("Sort up abnormal");
				  
		  }
		  finally{}
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	numTry++;
		 	tableSort_linkToMySiteSort(numTry);
	 }
  }
  
  //=========================================================================================================
  private void tableSort_linkFromMySiteSort(int numTry)throws Exception {
  	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
  	  try{//Test link from my site sort:=======================================================
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_from_my_site first last']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[4]")));
		  
		  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/thead/tr/th"))).getAttribute("class").equals("first sorted-desc"))
			  ;
		  else
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_from_my_site first last']"))).click();
		  
		  //Check down up sort:
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[4]")));
		  
		  try{if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[4]"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[4]"))).getText())==1)
			  	throw new Exception("Sort down abnormal");
		  
			  //Check up down sort:
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_from_my_site first last']"))).click();
		      wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[4]")));
		 	  
		 	  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[4]"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[4]"))).getText())==-1)
		  			throw new Exception("Sort up abnormal");
				  
		  }
		  finally{}
  	  }
  	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	numTry++;
		 	tableSort_linkFromMySiteSort(numTry);
	 }
  }
  
  //=========================================================================================================
  private void tableSort_linkToCompetitorsSort(int numTry)throws Exception {
  	WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Test link to competitors sort:=======================================================
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-num_competitors_with_links first last']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[5]")));
		  
		  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/thead/tr/th"))).getAttribute("class").equals("first sorted-desc"))
			  ;
		  else
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-num_competitors_with_links first last']"))).click();
		  
		  //Check down up sort:
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[5]")));
		  
		  try{if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[5]"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[5]"))).getText())==1)
			  	throw new Exception("Sort down abnormal");
		  
			  //Check up down sort:
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-num_competitors_with_links first last']"))).click();
		      wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[5]")));
		 	  
		 	  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[5]"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[5]"))).getText())==-1)
		  			throw new Exception("Sort up abnormal");
				  
		  }
		  finally{}
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	numTry++;
		 	tableSort_linkToCompetitorsSort(numTry);
	 }
  }
  
  //=========================================================================================================
  private void tableSort_pageRank(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{//Test page rank sort:=======================================================
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_page_rank first last']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[6]/span")));
		  
		  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/thead/tr/th"))).getAttribute("class").equals("first sorted-desc"))
			  ;
		  else
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_page_rank first last']"))).click();
		  
		  //Check down up sort:
		  try{wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[6]/span")));
		  }
		  catch(Exception e){
			  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[6]")));
		  }
		  
		  try{if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[6]/span"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[6]/span"))).getText())==1)
			  	throw new Exception("Sort down abnormal");
		  }
		  catch(Exception e){
			  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[6]"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[6]"))).getText())==1)
				  throw new Exception("Sort down abnormal");
		  }
		  
		  //Check up down sort:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_page_rank first last']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[6]/span")));
		 	  
		  try{if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[6]/span"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[6]/span"))).getText())==-1)
		  			throw new Exception("Sort up abnormal");
		  }
		  catch(Exception e){
			  if(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[6]"))).getText().compareTo(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[6]"))).getText())==-1)
		  			throw new Exception("Sort up abnormal");
		  }
		  finally{}
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	numTry++;
		 	tableSort_pageRank(numTry);
	 }
  }
  
  //=========================================================================================================
  private void helpWithThisPage_test(int numTry)throws Exception {
  	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='help_for_this_page iconlink track']")));
		  String winHandleBefore = driver.getWindowHandle();
		  
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//a[@class='help_for_this_page iconlink track']")))).perform();
		  switcWindow();
		  assertEquals("Help with this page:","Links overview : Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	numTry++;
		 	helpWithThisPage_test(numTry);
	 }
  }
  
  //=========================================================================================================
  private void importLinks_test(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String winHandleBefore = driver.getWindowHandle();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='view sharing']/div/a"))).click();
  
	  try{//Test link "How to format your file":
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='import-links-dialog']/p[3]/a"))).click();
		  switcWindow();
		  assertEquals("How to format your file:","How to import links into Optify : Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
		  
		  //Here we can add some file to test.
		  
		  
		  //Close the frame:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@aria-labelledby='ui-dialog-title-import-links-dialog']/div/a/span"))).click();
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	//Restart test:
		 	try{driver.close();
		 	    driver.switchTo().window(winHandleBefore);
		 	}
		 	catch(Exception ex){}
		 	numTry++;
		 	importLinks_test(numTry);
	 }
  }
  
  //=========================================================================================================
  private void exportCSV_test(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='view sharing']/div/a[2]"))).click();
  
	  	  //Here we can add some file to test.
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	numTry++;
		 	exportCSV_test(numTry);
	 }
  }
  
  //=========================================================================================================
  private void exportPDF_test(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='view sharing']/div/a[3]"))).click();
  
	  	  //Here we can add some file to test.
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	numTry++;
		 	exportPDF_test(numTry);
	 }
  }
  
  //=========================================================================================================
  private void nextAndPrev_test(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{//Prepare for test:
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-list-pulldown']/div/span[2]")))).perform();
		  //wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-list-pulldown-pulldown']//label[text()='test']"))).click();
		  Thread.sleep(2000);
		  
		  if(wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-list-pulldown-pulldown']/ul/li/input"))).isSelected())
			  wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-list-pulldown-pulldown']/ul/li/input"))).click();	
		  
		  //Start test:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='table-pager data-pager']//a[text()='2']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td/span")));
		  assertEquals("Pgae active number:","2",wait.until(presenceOfElementLocated(By.xpath("//span[@class='active number']"))).getText()); 
		  
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='table-pager data-pager']//a[@class='next']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//span[@class='active number']")));
		  assertEquals("Pgae active number:","3",wait.until(presenceOfElementLocated(By.xpath("//span[@class='active number']"))).getText()); 
		  
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='table-pager data-pager']//a[@class='previous']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//span[@class='active number']")));
		  assertEquals("Pgae active number:","2",wait.until(presenceOfElementLocated(By.xpath("//span[@class='active number']"))).getText());
		  
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='table-pager data-pager']//a[text()='1']"))).click();
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='table-pager data-pager']//a[text()='1']"))).click();
		 	}
		 	catch(Exception ex){}
		 	numTry++;
		 	nextAndPrev_test(numTry);
	 }
  }
  
  //=========================================================================================================
  private void showResults_fifty(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  int SUMFIF=50;
	  
	  try{//show 50:
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select/option[3]"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody")));
		  Thread.sleep(5000);
		  assertEquals("count 50:",SUMFIF,getRowCount(By.xpath("//table[@id='links-table']/tbody")));
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	//Restart test:
		 	builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select")))).perform();
		    wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select/option"))).click();
		    builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select")))).perform();
		    wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody")));
		 	numTry++;
		 	showResults_fifty(numTry);
	 }
  }
  
  //=========================================================================================================
  private void showResults_twentyfivey(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  
	  int SUMTF=25;
	  
	  try{//show 25:
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select/option[2]"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody")));
		  Thread.sleep(3000);
		  assertEquals("count 25:",SUMTF,getRowCount(By.xpath("//table[@id='links-table']/tbody")));
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	//Restart test:
		 	builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select")))).perform();
			wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select/option[3]"))).click();
		    wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select"))).click();
			wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody")));
		 	numTry++;
		 	showResults_twentyfivey(numTry);
	  }
  }
  
  //=========================================================================================================
  private void showResults_ten(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  int SUMTH=10;
	  
	  try{//show 10:
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select/option"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody")));
		  Thread.sleep(3000);
		  assertEquals("count 10:",SUMTH,getRowCount(By.xpath("//table[@id='links-table']/tbody")));
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	//Restart test:
		 	builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select")))).perform();
			wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select/option[2]"))).click();
			wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select"))).click();
			wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody")));
		 	numTry++;
		 	showResults_ten(numTry);
	  }
  }
  
  //=========================================================================================================
  private String getSuggestions_getUrlSuggest(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String getUrl=null;
	  
	  try{//Get Url suggest:
		  wait.until(presenceOfElementLocated(By.xpath("//button[@id='suggest-links']/span"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//input[@id='suggest-links-input']"))).sendKeys("photograph");
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='suggest-url-popup']/div/button"))).click();
		  wait = new WebDriverWait(driver, 100);
		  getUrl=wait.until(presenceOfElementLocated(By.xpath("//table[@class='inner_table']/tbody/tr/td/span/a"))).getText();
		  wait = new WebDriverWait(driver, 10);
		  Thread.sleep(2000);
		  
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	numTry++;
		 	return getSuggestions_getUrlSuggest(numTry);
	  }
	  
	  return getUrl;
  }
  
  //=========================================================================================================
  private void getSuggestions_saveUrlToTestGroup(int numTry)throws Exception {
  	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
  	  try{//Save Url to test group:
		  wait.until(presenceOfElementLocated(By.xpath("//table[@class='inner_table']/tbody/tr/td/input"))).click();	
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='suggest-list-control']/div/div/span[2]")));	
		  String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@id='suggest-list-control']/div"))).getAttribute("id");
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@id='suggest-list-control']/div/div/span[2]")))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']//input[@placeholder='Create new list']"))).sendKeys("test");
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//button[@id='suggest-links-save']/span"))).click();
  	  }
  	  catch(Exception e){
	 	if(numTry>3)
			  throw e;
	 	
	 	numTry++;
	 	Thread.sleep(2000);
	 	getSuggestions_saveUrlToTestGroup(numTry);
	  }
  }
  
  //=========================================================================================================
  private void getSuggestions_checkSaveUrlAndTestGroup(int numTry,String getUrl)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{//Check saved url in status group:
		  Thread.sleep(3000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='report-filters']/div/div/div/span[2]")))).perform();
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//ul[@class='ui-pulldown-list ']//label[text()='test']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@class='inner_table']/tbody//a[text()='"+getUrl+"']")));
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='report-filters']/div/div/div/span[2]"))).click();
	  }
	  catch(Exception e){
	 	if(numTry>3)
			  throw e;
	 	
	 	numTry++;
	 	Thread.sleep(2000);
	 	getSuggestions_checkSaveUrlAndTestGroup(numTry,getUrl);
	  }
  }
  
  //=========================================================================================================
  private void getSuggestions_deleteUrlAndGroup(int numTry)throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String triger="No Results";
	  
	  try{//Delete url & group:
		  while(!triger.equals(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td"))).getText())){
			  builder.moveToElement(wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[3]")))).perform();
			  Thread.sleep(2000);
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']/div[2]"))).click();
			  Thread.sleep(2000);
			  builder.click(wait.until(presenceOfElementLocated(By.xpath("//a[@class='confirm_delete_ok']")))).perform();
			  Thread.sleep(2000);
		  }
		  
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='report-filters']/div/div/div/span[2]")))).perform();
		  Thread.sleep(2000);
		  builder.moveToElement(wait.until(presenceOfElementLocated(By.xpath("//ul[@class='ui-pulldown-list ']//label[text()='test']")))).perform();
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//ul[@class='ui-pulldown-list ']//a[@class='delete']"))).click();
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='confirm_delete_ok']"))).click();
		  Thread.sleep(2000);
	  }
	  catch(Exception e){
		 	if(numTry>3)
				  throw e;
		 	
		 	numTry++;
		 	getSuggestions_deleteUrlAndGroup(numTry);
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

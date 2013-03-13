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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

@RunWith(BlockJUnit4ClassRunner.class)
public class Keywords extends TestCase {
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
    String keyWord="";
    String keyWordUrl="";
    
  
  @BeforeClass
  public static void createAndStartService() throws Throwable {
    int numTry=0;
    
	service = new ChromeDriverService.Builder()
    	.usingDriverExecutable(new File(setPath))
        .usingAnyFreePort()
        .build();
	service.start();
	
	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	String[] listCapability={"--start-maximized","--disable-extensions","--disable-translate"};
	capabilities.setCapability("chrome.switches", listCapability);
	driver = new RemoteWebDriver(service.getUrl(),capabilities);
	
	print("\nLogin to Optify.");
	enterToKeywords(numTry);
  }
  
  public static void enterToKeywords(int numTry) throws Throwable{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  Actions builder = new Actions(driver);
	  
	  try{driver.get(homeAddress+"/login");
		  driver.findElement(By.id("j_username")).sendKeys(userName);
		  driver.findElement(By.id("j_password")).sendKeys(password);
		  driver.findElement(By.id("login_button")).click();
		  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
		  
		  System.out.println(" v");
		  
		  print("\n\nEntering to Keywords page.");
		  
		  wait.until(presenceOfElementLocated(By.xpath("//li[@class='drive']/a/span")));
		  builder.clickAndHold(driver.findElement(By.xpath("//li[@class='drive']/a/span"))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//html/body/div/div[2]/ul/li[2]/ul/li/a"))).click();
		  assertEquals("Keywords page","Keywords | Optify",driver.getTitle());
		  
		  System.out.println(" v");
	  }
	  catch(Exception e){
		  if(numTry>3)
			  throw e;
 
		  numTry++;
		  enterToKeywords(numTry);
	  }
		  
		  Thread.sleep(3000);
  }
  
  @Test
  public void helpWithThisPage() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("Entering Help with this page.");
	  helpWithThisPage_link(numTry);
	  System.out.println(" v");
  }
  
  @Test
  public void calendar() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  Calendar todayDate=Calendar.getInstance();
	  
	  System.out.println("\n\nTesting Keywords page Calendar:");
	  print("Testing 7d dates.");
	  calendar_7d(numTry,todayDate);
	  System.out.println(" v");
	  print("Testing 30d dates.");
	  calendar_30d(numTry,todayDate);
	  System.out.println(" v");
	  print("Testing 24h dates.");
	  calendar_24h(numTry,todayDate);
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test 
  public void overview()throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting Keywords page Overview:");
	  print("Testing Visits from Keywords.");
	  overview_visitsFromKeywords(numTry); 
	  System.out.println(" v");
	  print("Testing Estimated Click Value.");
	  overview_estimatedClickValue(numTry); 
	  System.out.println(" v");
  }
  
  @Test
  public void testView() throws Exception{
	  int numTry=0; //Counter the number of attempts.
		 
	  print("\nTesting view summary.");
	  testView_summary(numTry); 
	  System.out.println(" v");
	  print("Testing view competitor.");
	  testView_competitor(numTry); 
	  System.out.println(" v");
	  print("Testing view performance.");
	  testView_performance(numTry); 
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void importKeywordList() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  print("\nTesting Import keyword list.");
	  importKeywordList_get(numTry); 
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  //@Test
  public void exportKeywordList() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	 
	  print("\nTesting export keyword list.");
	  exportKeywordList_get(numTry); 
	  System.out.println(" v");
  }
  
  @Test
  public void editKeywordsReport() throws Exception{
	  int numTry=0; //Counter the number of attempts.
		 
	  print("\nTesting edit keywords report.");
	  editKeywordsReport_set(numTry); 
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void testRanks() throws Exception{
	  int numTry=0; //Counter the number of attempts.
		
	  System.out.print("\n\nTesting ranks:\n");
	  print("Testing left slider rank.");
	  testRanks_setLeftSlide(numTry); 
	  System.out.println(" v");
	  print("Testing Right slider rank.");
	  testRanks_setRightSlide(numTry); 
	  System.out.println(" v");
	  print("Testing keyword rank.");
	  testRanks_keyword(numTry); 
	  System.out.println(" v");
	    
	  Thread.sleep(3000);
  }
  
  @Test
  public void addKeyword() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting Add Keywords:");
	  print("Testing Add keyword & saving to test group.");
	  addKeyword_addAndSaveToGroup(numTry); 
	  System.out.println(" v");
	  print("Testing Add keyword.");
	  addKeyword_add(numTry); 
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
  
  @Test 
  public void getSuggestions() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{System.out.println("\n\nTesting Get suggestions:");
	      print("Testing Get suggestion popup frame.");
		  getSuggestions_popUp(numTry); 
		  System.out.println(" v");
		  print("Testing saveing suggested keyword.");
		  getSuggestions_savingSuggestKeywords(numTry); 
		  System.out.println(" v");
		  print("Testing saveing suggested Url.");
		  getSuggestions_savingSuggestUrl(numTry); 
		  System.out.println(" v");
	  }
	  catch(Exception e){
		  try{wait.until(presenceOfElementLocated(By.xpath("//div[@aria-labelledby='ui-dialog-title-keyword_suggestions']/div/a/span"))).click();
		  }
		  catch(Exception ex){}
		  throw e;
	  }
	
	  Thread.sleep(3000);
  }
  
  
 @Test
 public void tableSearch() throws Exception{
	 int numTry=0; //Counter the number of attempts.
	 
	 print("\n\nTesting table search...");
	 tableSearch_go(numTry); 
	 System.out.println(" v");
	  
	 Thread.sleep(3000);
 }
 
 @Test 
 public void tableActions() throws Exception{
	  String winHandleBefore = driver.getWindowHandle();
	  int numTry=0; //Counter the number of attempts.
		 
	  System.out.println("\n\nTesting table actions");
	  print("Testing saving keywords to group.");
	  tableActions_saveKeywordToGroup(numTry); 
	  System.out.println(" v");
	  print("Testing Google view results.");
	  tableActions_googleViewResults(numTry,winHandleBefore); 
	  System.out.println(" v");
	  print("Testing Twitter view results.");
	  tableActions_twitterViewResults(numTry,winHandleBefore); 
	  System.out.println(" v");
	  print("Testing Bing view results.");
	  tableActions_bingViewResults(numTry,winHandleBefore); 
	  System.out.println(" v");
	  print("Testing Yahoo! view results.");
	  tableActions_yahooViewResults(numTry,winHandleBefore); 
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
 
  @Test
  public void searchEngines() throws Exception{
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting search engines:");
	  print("Testing search engines replacing.");
	  searchEngines_searchEngineReplacing(numTry); 
	  System.out.println(" v");
	  print("Testing add more search engines.");
	  searchEngines_addMoreSearchEngines(numTry); 
	  System.out.println(" v");
	  
	  Thread.sleep(3000);
  }
	  
  @Test
  public void needHelp() throws Exception{
	  String winHandleBefore = driver.getWindowHandle();
	  int numTry=0; //Counter the number of attempts.
	  
	  System.out.println("\n\nTesting need help:");
	  print("Testing opeaning need help.");
	  needHelp_openHelp(numTry); 
	  System.out.println(" v");
	  print("Testing opeaning video.");
	  needHelp_openVideo(numTry); 
	  System.out.println(" v");
	  print("Entering Learn more about keywords.");
	  needHelp_learnMoreAboutKeywords(numTry,winHandleBefore); 
	  System.out.println(" v");
	  print("Testing going forward to Overview.");
	  needHelp_forwordOverview(numTry); 
	  System.out.println(" v");
	  print("Testing watch a video on keywords.");
	  needHelp_watchAVideoOnKeywords(numTry); 
	  System.out.println(" v");
	  print("Entering Learn more about keywords.");
	  needHelp_learnMoreAboutKeywords2(numTry,winHandleBefore); 
	  System.out.println(" v");
	  print("Entering Optify Best Practices: Choosing Target keywords.");
	  needHelp_optifyBestPractices(numTry,winHandleBefore); 
	  System.out.println(" v");
	  print("Testing going forward to Keyword list.");
	  needHelp_forwordKeywordList(numTry); 
	  System.out.println(" v");
	  print("Testing going forward to Add keyword.");
	  needHelp_forwordAddKeyword(numTry); 
	  System.out.println(" v");
	  print("Testing going forward to Get Suggestions.");
	  needHelp_forwordGetSuggestions(numTry); 
	  System.out.println(" v");
	  print("Testing going forward to table actions.");
	  needHelp_forwordTableActions(numTry); 
	  System.out.println(" v");
	  print("Testing going forward to keyword groups.");
	  needHelp_forwordKeywordGroup(numTry); 
	  System.out.println(" v");
	  print("Testing going forward to keyword View.");
	  needHelp_forwordKeywordView(numTry); 
	  System.out.println(" v");
	  print("Testing going forward to final.");
	  needHelp_forwordFinal(numTry); 
	  System.out.println(" v");
	  print("Entering Help.");
	  needHelp_enterHelp(numTry,winHandleBefore); 
	  System.out.println(" v");
	  print("Entering Next:  Take the Pages Tour.");
	  needHelp_nextTakePageTour(numTry); 
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
  private void goBase(){
	  if(!driver.getTitle().equals("Keywords | Optify"))
		  driver.get(homeAddress+"/keyword/overview");
  }
  
  //==================================================================================================
  private void switcWindow(){
	  for(String winHandle : driver.getWindowHandles())
		  driver.switchTo().window(winHandle);
  }
  
  //=================================================================================================
  public static void selectValue(String valToBeSelected){
      List <WebElement> options = driver.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (valToBeSelected.equalsIgnoreCase(option.getText())){
				option.click();
			}
	    }
	}
  
  //==================================================================================================
  private void helpWithThisPage_link(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
  
	  try{String winHandleBefore = driver.getWindowHandle();
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//a[@class='help_for_this_page iconlink track']")))).perform();
		  Thread.sleep(3000);
		  switcWindow();
		  assertEquals("Help with this page","Keywords application reference : Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 helpWithThisPage_link(numTry);
	  }
  }
  
  //=========================================================================================================
  private void calendar_7d(int numTry,Calendar todayDate)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  final int SEVEN = -7;
	  goBase();
	  
	  try{todayDate.add(Calendar.DAY_OF_YEAR,SEVEN);
		  try{ wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-7d']"))).click();
		  }
		  catch(Exception e){}
		 
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='keyword_table']/tbody/tr")));
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-7d selected']")));
  
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='interval_date_picker']/button/span"))).click();
		  Thread.sleep(3000);
		  assertEquals("7d day",Integer.toString(todayDate.get(Calendar.DAY_OF_MONTH)+1),wait.until(presenceOfElementLocated(By.xpath("//a[@class='ui-state-default ui-state-active']"))).getText());
		  assertEquals("7d month",todayDate.get(Calendar.MONTH),returnMonthInt(wait.until(presenceOfElementLocated(By.xpath("//span[@class='ui-datepicker-month']"))).getText()));
		  assertEquals("7d year",Integer.toString(todayDate.get(Calendar.YEAR)),wait.until(presenceOfElementLocated(By.xpath("//span[@class='ui-datepicker-year']"))).getText());
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 calendar_7d(numTry,todayDate);
	  }
  }
  
  //=========================================================================================================
  private void calendar_30d(int numTry, Calendar todayDate)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  final int THIRTY = -29;
  
	  try{todayDate=Calendar.getInstance();
		  todayDate.add(Calendar.DAY_OF_YEAR,THIRTY);
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-30d']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='keyword_table']/tbody/tr")));
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-30d selected']")));
		  
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='interval_date_picker']/button/span"))).click();
		  Thread.sleep(3000);
		  assertEquals("30d day",Integer.toString(todayDate.get(Calendar.DAY_OF_MONTH)),driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).getText());
		  assertEquals("30d month",todayDate.get(Calendar.MONTH),returnMonthInt(driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText()));
		  assertEquals("30d year",Integer.toString(todayDate.get(Calendar.YEAR)),driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 calendar_30d(numTry,todayDate);
	  }
  }
  
  //=========================================================================================================
  private void calendar_24h(int numTry, Calendar todayDate)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{todayDate=Calendar.getInstance();
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-24h']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='keyword_table']/tbody/tr")));
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-24h selected']")));
		  
		  todayDate.add(Calendar.DAY_OF_MONTH,-1);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='interval_date_picker']/button/span"))).click();
		  Thread.sleep(3000);
		  assertEquals("24h day",Integer.toString(todayDate.get(Calendar.DAY_OF_MONTH)),driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).getText());
		  assertEquals("24h month",todayDate.get(Calendar.MONTH),returnMonthInt(driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText()));
		  assertEquals("24h year",Integer.toString(todayDate.get(Calendar.YEAR)),driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());
	  
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='interval_date_picker']/button/span"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-30d']"))).click();
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 calendar_24h(numTry,todayDate);
	  }
  }
  
  //=========================================================================================================
  private void overview_visitsFromKeywords(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	 
	  try{builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@class='first overview_statbox']/h5/span/span")))).perform();
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 overview_visitsFromKeywords(numTry);
	  }	
  }
  
  //=========================================================================================================
  private void overview_estimatedClickValue(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='last overview_statbox']/h5/span/span"))).click();
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 overview_estimatedClickValue(numTry);
	  }	
  }	
  
  //=========================================================================================================
  private void importKeywordList_get(int numTry)throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 15);
	  goBase();
	  
	  try{String winHandleBefore = driver.getWindowHandle();
	  
		  wait.until(presenceOfElementLocated(By.xpath("//span[@class='icon-import']"))).click();
		                  //We can add some files to upload & equal with the keywords table data base.
		  
		  wait.until(presenceOfElementLocated(By.linkText("How to format your file"))).click();
		 
		  Thread.sleep(3000);
		  
		  switcWindow();
		  
		  assertEquals("How to format your file","Importing and exporting keywords : Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
		  
		  wait.until(presenceOfElementLocated(By.xpath("//html/body/div[11]/div[3]/div/button[2]/span"))).click();
		  Thread.sleep(10000);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 importKeywordList_get(numTry);
	  }	
  }
  
  //=========================================================================================================
  private void exportKeywordList_get(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 15);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//span[@class='icon-export']"))).click();
	  			Thread.sleep(3000);
	  		driver.switchTo().frame("Save As");
	  		Thread.sleep(3000);
	  		builder.sendKeys(Keys.ENTER).perform();
	  }
	  
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 exportKeywordList_get(numTry);
	  }	
  }

  //=========================================================================================================
  private void testView_summary(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//select[@id='keyword_view_selection']")));
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//select[@id='keyword_view_selection']")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//select[@id='keyword_view_selection']/option[2]"))).click();
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//select[@id='keyword_view_selection']")))).perform();
		  
		  Thread.sleep(5000);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 testView_summary(numTry);
	  }
  }
  
  //=========================================================================================================
  private void testView_competitor(int numTry)throws Exception{  
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//select[@id='keyword_view_selection']")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//select[@id='keyword_view_selection']/option[3]"))).click();
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//select[@id='keyword_view_selection']")))).perform();
  
		  Thread.sleep(5000);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 testView_competitor(numTry);
	  }
  }
  
  //=========================================================================================================
  private void testView_performance(int numTry)throws Exception{  
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//select[@id='keyword_view_selection']")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//select[@id='keyword_view_selection']/option"))).click();
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//select[@id='keyword_view_selection']")))).perform();
		  
		  Thread.sleep(5000);
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 testView_performance(numTry);
	  }
  }
  
  //=========================================================================================================
  private void editKeywordsReport_set(int numTry)throws Exception{ 
  	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
  	
	  try{wait.until(presenceOfElementLocated(By.xpath("//span[@class='icon-cog white-icon']"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='keywords_edit_dialog']/dl/dd[6]/input"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='keywords_edit_dialog']/dl/dd[7]/input"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='keywords_edit_dialog']/dl/dd[8]/input"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//html/body/div[10]/div[3]/div/button/span"))).click();
		  
		  wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']//div[text()='Min CPC']")));
		  wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']//div[text()='Max CPC']")));
		  wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']//div[text()='Ranking URL']")));
		  
		  wait.until(presenceOfElementLocated(By.xpath("//span[@class='icon-cog white-icon']"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='keywords_edit_dialog']/dl/dd[6]/input"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='keywords_edit_dialog']/dl/dd[7]/input"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='keywords_edit_dialog']/dl/dd[8]/input"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//html/body/div[10]/div[3]/div/button/span"))).click();
	  }
	  catch(Exception e){
			 if(numTry>3)
				 throw e;
			 
			 numTry++;
			 editKeywordsReport_set(numTry);
	  }
  }
  
  //=========================================================================================================
  private void testRanks_setLeftSlide(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
	  
  	  try{builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//a[@id='handle_rank_slider_min']")))).perform();
		  builder.sendKeys(wait.until(presenceOfElementLocated(By.xpath("//a[@id='handle_rank_slider_min']"))), Keys.ARROW_RIGHT).perform();
		  builder.sendKeys(wait.until(presenceOfElementLocated(By.xpath("//a[@id='handle_rank_slider_min']"))), Keys.ARROW_RIGHT).perform();
		  
		  Thread.sleep(2000);
  	  }
  	  catch(Exception e){
		 if(numTry>3)
			 throw e;
		 
		 //Set back slider left pin:
		 builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//a[@id='handle_rank_slider_min']")))).perform();
		 builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_min']")), Keys.ARROW_LEFT).perform();
		 builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_min']")), Keys.ARROW_LEFT).perform();
 		  
		 numTry++;
		 testRanks_setLeftSlide(numTry);
  	  }
  }
  
  //=========================================================================================================
  private void testRanks_setRightSlide(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
	  
  	  try{//Set slider Right pin:
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//a[@id='handle_rank_slider_max']")))).perform();
		  builder.sendKeys(wait.until(presenceOfElementLocated(By.xpath("//a[@id='handle_rank_slider_max']"))), Keys.ARROW_LEFT).perform();
		  builder.sendKeys(wait.until(presenceOfElementLocated(By.xpath("//a[@id='handle_rank_slider_max']"))), Keys.ARROW_LEFT).perform();
		  
		  Thread.sleep(2000);
  	  }
  	  catch(Exception e){
  		  if(numTry>3)
			 throw e;
		  
		  //Set back slider Right pin:
		  builder.clickAndHold(driver.findElement(By.xpath("//a[@id='handle_rank_slider_max']"))).perform();
		  builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_max']")), Keys.ARROW_RIGHT).perform();
		  builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_max']")), Keys.ARROW_RIGHT).perform();
  		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-text-filter data-filter-off']/input"))).sendKeys("cc2");
		  
  		  numTry++;
		  testRanks_setRightSlide(numTry);
 	  }
  }
  
  //=========================================================================================================
  private void testRanks_keyword(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
  
  	  try{wait.until(presenceOfElementLocated(By.xpath("//table[@class='data-table active']/tbody/tr/td[2]/span/a/span"))).click();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//button[@id='keyword_rank_ok']"))).click();
		  
		  //Set back slider left pin:
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//a[@id='handle_rank_slider_min']")))).perform();
		  builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_min']")), Keys.ARROW_LEFT).perform();
		  builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_min']")), Keys.ARROW_LEFT).perform();
		  
		  Thread.sleep(2000);
		  
		  //Set back slider Right pin:
		  builder.clickAndHold(driver.findElement(By.xpath("//a[@id='handle_rank_slider_max']"))).perform();
		  builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_max']")), Keys.ARROW_RIGHT).perform();
		  builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_max']")), Keys.ARROW_RIGHT).perform();
  	  }
	  catch(Exception e){
		  if(numTry>3)
			  throw e;
  		  
		  //Set slider Left pin:
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//a[@id='handle_rank_slider_min']")))).perform();
		  builder.sendKeys(wait.until(presenceOfElementLocated(By.xpath("//a[@id='handle_rank_slider_min']"))), Keys.ARROW_RIGHT).perform();
		  builder.sendKeys(wait.until(presenceOfElementLocated(By.xpath("//a[@id='handle_rank_slider_min']"))), Keys.ARROW_RIGHT).perform();
		  
		  //Set slider Right pin:
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//a[@id='handle_rank_slider_max']")))).perform();
		  builder.sendKeys(wait.until(presenceOfElementLocated(By.xpath("//a[@id='handle_rank_slider_max']"))), Keys.ARROW_LEFT).perform();
		  builder.sendKeys(wait.until(presenceOfElementLocated(By.xpath("//a[@id='handle_rank_slider_max']"))), Keys.ARROW_LEFT).perform();
		  
		  numTry++;
		  testRanks_keyword(numTry);
	  }
  }
  
  //=========================================================================================================
  private void addKeyword_addAndSaveToGroup(int numTry)throws Exception{  
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
  
 
	  try{builder.click(wait.until(presenceOfElementLocated(By.xpath("//button[@id='add_keywords_open']")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='keyword_add_text']")));
		  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='keyword_add_text']"))).sendKeys("cc1");
		  
		  Thread.sleep(3000);
		  String getId=driver.findElement(By.xpath("//div[@class='add-to-list-box']/div/div")).getAttribute("id");
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"']/div/span[2]")))).perform();
		  
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li/input")));
		  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']/ul/li/input"))).sendKeys("test");
		  wait.until(presenceOfElementLocated(By.xpath("//button[@class='orange ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span"))).click();
		
		  //Wait for the add success message:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='success_message hidden-none clear-both']")));
		 
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  addKeyword_addAndSaveToGroup(numTry);
	  }
  }
  
  //=========================================================================================================
  private void addKeyword_add(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
  
	  try{builder.click(wait.until(presenceOfElementLocated(By.xpath("//button[@id='add_keywords_open']")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='keyword_add_text']")));
		  driver.findElement(By.xpath("//textarea[@id='keyword_add_text']")).sendKeys("cc2");
		  Thread.sleep(3000);
		  driver.findElement(By.xpath("//button[@class='orange ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span")).click();
	  }
	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  addKeyword_add(numTry);
	  }
  }
  
  //=========================================================================================================
  private void getSuggestions_popUp(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
	  
	  try{builder.click(wait.until(presenceOfElementLocated(By.xpath("//button[@id='keyword-open-suggest']")))).perform();
		  
	  }
	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  getSuggestions_popUp(numTry);
	  }
  }
  
  //=========================================================================================================
  private void getSuggestions_savingSuggestKeywords(int numTry)throws Exception{  
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();

	  try{wait.until(presenceOfElementLocated(By.xpath("//input[@id='keyword-suggest-input']"))).sendKeys("seo");
	  	  wait.until(presenceOfElementLocated(By.xpath("//button[@class='suggest-keywords-submit ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span"))).click();
	  
		  String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='add-to-list-box']/div/div"))).getAttribute("id");
	  
		  keyWord=wait.until(presenceOfElementLocated(By.xpath("//div[@class='table-wrapper']/table/tbody/tr/td/div/table/tbody/tr/td[2]/span"))).getText();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='table-wrapper']/table/tbody/tr/td/div/table/tbody/tr/td/input"))).click();
		  
		  Thread.sleep(3000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='add-to-list-box']/div/div/div/span[2]")))).perform();
		  Thread.sleep(3000);
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']//li[text()='test']")))).perform();
		  
		  Thread.sleep(3000);
		  
		  wait.until(presenceOfElementLocated(By.xpath("//button[@id='suggest-keywords-save']"))).click();
		  
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 	  
 		  if(wait.until(presenceOfElementLocated(By.xpath("//div[@class='table-wrapper']/table/tbody/tr/td/div/table/tbody/tr/td/input"))).isSelected());
			 wait.until(presenceOfElementLocated(By.xpath("//div[@class='table-wrapper']/table/tbody/tr/td/div/table/tbody/tr/td/input"))).click();

                  numTry++;
		  getSuggestions_savingSuggestKeywords(numTry);
	  }
  }
  
  //=========================================================================================================
  private void getSuggestions_savingSuggestUrl(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();

	  try{getSuggestions_popUp(numTry);
		  Thread.sleep(3000);
		  
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//button[@id='keyword-open-suggest']")))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='suggest-type']/input[2]"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//input[@id='keyword-suggest-input']"))).sendKeys(Keys.CLEAR);
		  wait.until(presenceOfElementLocated(By.xpath("//input[@id='keyword-suggest-input']"))).sendKeys("www.optify.net");
		  driver.findElement(By.xpath("//button[@class='suggest-keywords-submit ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span")).click();
		  String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@class='add-to-list-box']/div/div"))).getAttribute("id");
		  
		  keyWordUrl=wait.until(presenceOfElementLocated(By.xpath("//div[@class='table-wrapper']/table/tbody/tr/td/div/table/tbody/tr/td[2]/span"))).getText();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='table-wrapper']/table/tbody/tr/td/div/table/tbody/tr/td/input"))).click();
		  
		  Thread.sleep(3000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='add-to-list-box']/div/div/div/span")))).perform();
		  Thread.sleep(2000);
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']//li[text()='test']")))).perform();
		  
		  Thread.sleep(3000);
		  builder.release(wait.until(presenceOfElementLocated(By.xpath("//div[@class='add-to-list-box']/div/div/div/span"))));
		  
		  wait.until(presenceOfElementLocated(By.xpath("//button[@id='suggest-keywords-save']"))).click();
	  }
	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  try{wait.until(presenceOfElementLocated(By.xpath("//div[@aria-labelledby='ui-dialog-title-keyword_suggestions']/div/a/span"))).click();
		  }
		  catch(Exception ex){}
		  getSuggestions_savingSuggestUrl(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableSearch_go(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
  	  
  	  try{//Prepare for test:
  		  wait.until(presenceOfElementLocated(By.xpath("//*[@id='keyword_grid']/div/div[2]/div[3]/div"))).click();
  		  Thread.sleep(2000);
  	  }catch(Exception e){}
  		  
	  try{//Select show All:
  		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//span[@class='filter_selection tags']//a[text()='All']")))).perform();
  		  
  		  //Check for rank slider full mode:
  		  //Set back slider left pin:
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//a[@id='handle_rank_slider_min']")))).perform();
		  builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_min']")), Keys.ARROW_LEFT).perform();
		  builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_min']")), Keys.ARROW_LEFT).perform();
		  
		  Thread.sleep(2000);
		  
		  //Set back slider Right pin:
		  builder.clickAndHold(driver.findElement(By.xpath("//a[@id='handle_rank_slider_max']"))).perform();
		  builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_max']")), Keys.ARROW_RIGHT).perform();
		  builder.sendKeys(driver.findElement(By.xpath("//a[@id='handle_rank_slider_max']")), Keys.ARROW_RIGHT).perform();
  		  
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-text-filter data-filter-off']/input"))).sendKeys("cc2");
		  
  	  	  Thread.sleep(5000);
		  assertEquals("get cc2","cc2",wait.until(presenceOfElementLocated(By.xpath("//table[@id='keyword_table']/tbody/tr/td/span/span"))).getText());
  	  }
  	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  tableSearch_go(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableActions_saveKeywordToGroup(int numTry)throws Exception{  
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
  	  
	  try{//Prepare for testing:
		  //Select show All:
  		  wait.until(presenceOfElementLocated(By.xpath("//span[@class='filter_selection tags']//a[text()='All']"))).click();
  		  
  		  //Check for viewing test keyword:
  		  int numTry2=0;
  		  tableSearch_go(numTry2);
		  
		  builder.sendKeys(Keys.PAGE_DOWN).perform();
		  Thread.sleep(1000);
		  builder.sendKeys(Keys.PAGE_DOWN).perform();
		  Thread.sleep(1000);
		  builder.sendKeys(Keys.PAGE_DOWN).perform();
		  Thread.sleep(2000);
		  
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//table[@id='keyword_table']/tbody/tr")))).perform();
		  
		  Thread.sleep(3000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='Assign to list']")))).perform();
		  
		  Thread.sleep(3000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table menu']/div/ul//span[text()='test']")))).perform();
		  
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table menu']//span[text()='test']"))).click();
		  Thread.sleep(5000);
		  
		  //Check cc1 & cc2 are in test group:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-text-filter-clear']"))).click();
		  
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//span[@class='filter_selection tags']//a[text()='test']"))).click();
		  
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='keyword_table']/tbody//span[text()='cc2']")));
	  }
	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  builder.click(wait.until(presenceOfElementLocated(By.xpath("//span[@class='filter_selection tags']//a[text()='test']")))).perform();
		  tableActions_saveKeywordToGroup(numTry);
	  }
  }
  
  //=========================================================================================================
  private void tableActions_googleViewResults(int numTry,String winHandleBefore)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
  
	  try{builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//table[@id='keyword_table']/tbody/tr")))).perform();
		  Thread.sleep(3000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='View results']")))).perform();
		  Thread.sleep(3000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table menu']//div[text()='See results on Google US']")))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table menu']//div[text()='See results on Google US']"))).click();
		  Thread.sleep(5000);
		  
		  switcWindow();
		  Thread.sleep(5000);
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  tableActions_googleViewResults(numTry,winHandleBefore);
	  }
  }
  
  //=========================================================================================================
  private void tableActions_twitterViewResults(int numTry,String winHandleBefore)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
  
	  try{builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//table[@id='keyword_table']/tbody/tr")))).perform();
		  Thread.sleep(3000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='View results']")))).perform();
		  Thread.sleep(3000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table menu']//div[text()='See results on Twitter']")))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table menu']//div[text()='See results on Twitter']"))).click();
		  Thread.sleep(5000);
		  
		  switcWindow();
		  Thread.sleep(5000);
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  tableActions_twitterViewResults(numTry,winHandleBefore);
	  }
  }
  
  //=========================================================================================================
  private void tableActions_bingViewResults(int numTry,String winHandleBefore)throws Exception{  
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
  
	  try{builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//table[@id='keyword_table']/tbody/tr")))).perform();
		  Thread.sleep(3000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='View results']")))).perform();
		  Thread.sleep(3000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table menu']//div[text()='See results on Bing US']")))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table menu']//div[text()='See results on Bing US']"))).click();
		  Thread.sleep(3000);
		  
		  switcWindow();
		  Thread.sleep(3000);
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
	  }
	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  tableActions_bingViewResults(numTry,winHandleBefore);
	  }
  }
  
  //=========================================================================================================
  private void tableActions_yahooViewResults(int numTry,String winHandleBefore)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
  
	  try{builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//table[@id='keyword_table']/tbody/tr")))).perform();
		  Thread.sleep(3000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='View results']")))).perform();
		  Thread.sleep(3000);
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table menu']//div[text()='See results on Yahoo!']")))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table menu']//div[text()='See results on Yahoo!']"))).click();
		  Thread.sleep(3000);
	  
		  switcWindow();
		  Thread.sleep(5000);
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
		  
		  //Remove:
		  while(!wait.until(presenceOfElementLocated(By.xpath("//table[@id='keyword_table']/tbody/tr"))).getText().equals("Your search has returned no results. Clear search box or adjust your search query.")){
			  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//table[@id='keyword_table']/tbody/tr")))).perform();
			  Thread.sleep(3000);
			  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='Remove']")))).perform();
			  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']//div[text()='Remove']"))).click();
			  Thread.sleep(3000);
			  builder.click(wait.until(presenceOfElementLocated(By.xpath("//a[@class='confirm_delete_ok']/button")))).perform();
			  Thread.sleep(3000);
		  }
	  }
	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  tableActions_yahooViewResults(numTry,winHandleBefore);
	  }
  }
  
  //=========================================================================================================
  private void searchEngines_searchEngineReplacing(int numTry)throws Exception{  
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
  
	  try{builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//select[@id='search_engine_select']")))).perform();
		  
	  	  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//select[@id='search_engine_select']/option[2]"))).click();
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//select[@id='search_engine_select']")))).perform();
		  Thread.sleep(5000);
		  
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//select[@id='search_engine_select']")))).perform();
		  
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//select[@id='search_engine_select']/option"))).click();
		  builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//select[@id='search_engine_select']")))).perform();
		  
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  searchEngines_searchEngineReplacing(numTry);
	  }
  }
  
  //=========================================================================================================
  private void searchEngines_addMoreSearchEngines(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
	  
	  try{builder.clickAndHold(wait.until(presenceOfElementLocated(By.xpath("//select[@id='search_engine_select']")))).perform();
		  Thread.sleep(2000);
		  wait.until(presenceOfElementLocated(By.xpath("//select[@id='search_engine_select']//option[text()='Add more search engines...']"))).click();
		  
		  Thread.sleep(3000);
		  
		  assertEquals("Add more search engines:","Settings | Optify",driver.getTitle());
		  driver.navigate().back();
		  
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  searchEngines_addMoreSearchEngines(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_openHelp(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-dialog trainer-minimized']/div/div/div/a"))).click();
		  Thread.sleep(3000);
		  
		  String help1=('"'+"Keywords"+'"'+" is where you'll manage your keyword strategy and track your ranking success. Let's get started!");
		  assertEquals("help 1/9:",help1,wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-left']/div"))).getText());
	  }
	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  needHelp_openHelp(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_openVideo(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  goBase();
  
	  try{//Open video:
		  wait.until(presenceOfElementLocated(By.xpath("//a[@class='launch-video']/img"))).click();
		  
		  //Close video:
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='video-close']")));
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='video-close']"))).click();
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  needHelp_openVideo(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_learnMoreAboutKeywords(int numTry, String winHandleBefore)throws Exception{  
  	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
  
  	  try{builder.click(wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-actions']/ul/li")))).perform();
		  switcWindow();
		  assertEquals("Learn more about Keywords:","Keywords application reference : Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
  	  }
  	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  needHelp_learnMoreAboutKeywords(numTry,winHandleBefore);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_forwordOverview(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a")));
	  	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
		  
		  String help2=("The "+'"'+"Overview"+'"'+" gives you a summary of the value you're driving from organic (unpaid) searches on your target keywords.");
		  assertEquals("help 2/9:",help2,wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-left']/div"))).getText());
	  }
  	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  needHelp_forwordOverview(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_watchAVideoOnKeywords(int numTry)throws Exception{  
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-actions']//a[text()='Watch a video on Keywords']")));
	  	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-actions']//a[text()='Watch a video on Keywords']"))).click();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='video-close']")));
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all video-dialog ui-draggable']//div[@class='video-close']"))).click();
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  needHelp_watchAVideoOnKeywords(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_learnMoreAboutKeywords2(int numTry,String winHandleBefore)throws Exception{  
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
  	  
  	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-actions']//a[text()='Learn more about Keywords']"))).click();
		  switcWindow();
		  assertEquals("Learn more about Keywords:","Keywords application reference : Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
  	  }
	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  needHelp_learnMoreAboutKeywords2(numTry,winHandleBefore);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_optifyBestPractices(int numTry,String winHandleBefore)throws Exception{  
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
  
  	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-actions']//a[text()='Optify Best Practices: Choosing Target keywords']"))).click();
		  switcWindow();
		  assertEquals("Optify Best Practices: Choosing Target keywords:","Optify best practices: Choosing target keywords : Help and Support",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
  	  }
  	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  needHelp_optifyBestPractices(numTry,winHandleBefore);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_forwordKeywordList(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
	  
  	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a")));
  	      wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
		  
		  String help3=("The Keyword List shows the target keywords you are tracking as well as helpful details to create a successful keyword strategy.");
		  assertEquals("help 3/9:",help3,wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-left']/div"))).getText());
  	  }
  	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  needHelp_forwordKeywordList(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_forwordAddKeyword(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a")));
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
		  
		  String help4=("Use the Add Keywords button when you know what keywords you want to track. A good starting point is to add words or phrases that describe your product or company.");
		  assertEquals("help 4/9:",help4,wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-left']/div"))).getText());
	  }
  	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  needHelp_forwordAddKeyword(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_forwordGetSuggestions(int numTry)throws Exception{  
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
 
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a")));
	  	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
		  
		  String help5=("Great job! Now, keyword research is also key to find relevant search terms that can drive quality traffic to your site. Enter a term, phrase or URL and we'll suggest the keywords!");
		  assertEquals("help 5/9:",help5,wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-left']/div"))).getText());
	  }
  	  catch(Exception e){
		  if(numTry>3)
			  throw e;
	 
		  numTry++;
		  needHelp_forwordGetSuggestions(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_forwordTableActions(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
  	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a")));
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
		  
		  String help6=("There are several actions you can take at the keyword level to stay organized and manage your SEO efforts and campaigns.");
		  assertEquals("help 6/9:",help6,wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-left']/div"))).getText());
	  }
	  catch(Exception e){
	 	if(numTry>3)
		  throw e;
 
	 	numTry++;
	 	needHelp_forwordTableActions(numTry);
	  }	
  }
  
  //=========================================================================================================
  private void needHelp_forwordKeywordGroup(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
  	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a")));
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
		  
		  String help7=("Assigning keywords to lists helps you focus your SEO efforts. You can use these lists to associate keywords with a campaign, or assign keywords to various projects.");
		  assertEquals("help 7/9:",help7,wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-left']/div"))).getText());
	  }
	  catch(Exception e){
	 	if(numTry>3)
		  throw e;
	
	 	numTry++;
	 	needHelp_forwordKeywordGroup(numTry);
	  }	
  }
  
  //=========================================================================================================
  private void needHelp_forwordKeywordView(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a")));
	  	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
		  
		  String help8=("We offer several "+'"'+"views"+'"'+" of your keyword data to help with decision making across all stages of your keyword selection, optimization, and performance tracking.");
		  assertEquals("help 8/9:",help8,wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-left']/div"))).getText());
	  }
	  catch(Exception e){
		 	if(numTry>3)
			  throw e;
		
		 	numTry++;
		 	needHelp_forwordKeywordView(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_forwordFinal(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
 
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a")));
	  	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
		  
		  String help9=("And, that's it! If you have additional questions, check out our help pages, send us an email or give us a call at 1-877-2-OPTIFY");
		  assertEquals("help 9/9:",help9,wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-left']/div"))).getText());
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		 	if(numTry>3)
			  throw e;
		
		 	numTry++;
		 	needHelp_forwordFinal(numTry);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_enterHelp(int numTry,String winHandleBefore)throws Exception{  
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-text']/a"))).click();
		  switcWindow();
		  assertEquals("Help:","Help and Support : Using Optify",driver.getTitle());
		  driver.close();
		  driver.switchTo().window(winHandleBefore);
		  Thread.sleep(3000);
	  }
	  catch(Exception e){
		 	if(numTry>3)
			  throw e;
		
		 	numTry++;
		 	needHelp_enterHelp(numTry,winHandleBefore);
	  }
  }
  
  //=========================================================================================================
  private void needHelp_nextTakePageTour(int numTry)throws Exception{ 
	  WebDriverWait wait = new WebDriverWait(driver, 10);
  	  goBase();
	 
  	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-next-actions']/a"))).click();
		  assertEquals("Next: Take the Pages Tour:","Pages | Optify",driver.getTitle());
		  driver.navigate().back();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-buttons']/a")));
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-buttons']/a"))).click();
  	  }
  	  catch(Exception e){
	 	if(numTry>3)
		  throw e;
	
	 	numTry++;
	 	needHelp_nextTakePageTour(numTry);
  	  }
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

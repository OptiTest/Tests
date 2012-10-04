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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

@RunWith(BlockJUnit4ClassRunner.class)
public class Links extends TestCase {

  //Set test parameters:
  private static ChromeDriverService service;
  private static WebDriver driver;
  Actions builder = new Actions(driver);
  String homeAddress="https://staging.optifyit.com";
  String userName="orasnin@gmail.com";
  String password="wrwmfy9m";
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
  }
  
  @Test
  public void enterToLinks1() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
		  //Log in Optify:
		  driver.get(homeAddress+"/login");
		  driver.findElement(By.id("j_username")).sendKeys(userName);
		  driver.findElement(By.id("j_password")).sendKeys(password);
		  driver.findElement(By.id("login_button")).click();
		  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
		  
		  //Enter to Links:
		  wait.until(presenceOfElementLocated(By.xpath("//li[@class='drive']/a/span")));
		  builder.clickAndHold(driver.findElement(By.xpath("//li[@class='drive']/a/span"))).perform();
		  Thread.sleep(3000);
		  driver.findElement(By.xpath("//html/body/div/div[2]/ul/li[2]/ul/li[3]/a")).click();
		  assertEquals("Links page","Links | Optify",driver.getTitle());
		  Thread.sleep(3000);
  }
  
  @Test
  public void addUrls() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='view links_actions']/div/button"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='links_add_urls']"))).sendKeys("www.ynet.co.il");
	  Thread.sleep(2000);
	  
	  String getId=driver.findElement(By.xpath("//div[@id='list-control']/div")).getAttribute("id");
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='add-to-list ']/div/div/span[2]"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']//input[@placeholder='Create new list']"))).sendKeys("test");
	  Thread.sleep(2000);
	  builder.sendKeys(Keys.ENTER).perform();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//button[@id='add-url-save']/span")).click();
	  
	  Thread.sleep(5000);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='view links_actions']/div/button"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//textarea[@id='links_add_urls']"))).sendKeys("www.walla.co.il");
	  Thread.sleep(2000);
	  
	  driver.findElement(By.xpath("//button[@id='add-url-save']/span")).click();
	  
	  Thread.sleep(5000);
	  
	  addNewFilter();
  }
  
  @Test
  public void addNewFilter() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Add filters
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='report-filter-list']/div[2]/span[2]")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@id='report-filter-list']/div[2]/span[2]"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='report-filter-list-pulldown']/ul/li"))).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//li[@class='token-input-input-token']/input")).sendKeys("ynet");
	  builder.sendKeys(Keys.ENTER).perform();
	  driver.findElement(By.xpath("//li[@class='token-input-input-token']/input")).sendKeys("walla");
	  builder.sendKeys(Keys.ENTER).perform();
	  
	  //Check received values:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']//a[text()='http://www.ynet.co.il']")));
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']//a[text()='http://www.walla.co.il']")));
	  
	  //Go over all values in table:
	  int sumOfRows=getRowCount(By.xpath("//table[@id='links-table']"));
	  for(int i=1; i<sumOfRows;i++){
		  if((driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr["+i+"]/td/span/a")).getAttribute("title").matches("(?i).*ynet.*")||driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr["+i+"]/td/span/a")).getAttribute("title").matches("(?i).*walla.*")))
			  ;
		  
		  else throw new Exception("Filter values hasn't be found!");
	  }
	  
	  //Delete filters:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-link_source_url0']/ul/li[2]/span"))).click();
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-link_source_url0']/ul/li/span"))).click();
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//span[@class='icon-remove icon-white']"))).click();
	  Thread.sleep(3000);
  }
  
  @Test
  public void assignToList() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Add url into new list:
	  String value=driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr/td/span/a")).getAttribute("title");
	  
	  builder.moveToElement(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr/td/span/a"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='action button-action action-tags']")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='action button-action action-tags']"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//input[@class='new-tag']"))).sendKeys("test");
	  Thread.sleep(2000);
	  builder.sendKeys(Keys.ENTER).perform();
	  Thread.sleep(3000);
	  
	  //Check values in list:
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@id='filter-list-pulldown']/div/span[2]"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-list-pulldown-pulldown']//label[text()='test']"))).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@id='filter-list-pulldown']/div/span[2]")).click();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']//a[text()='http://www.ynet.co.il']")));
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']//a[text()='"+value+"']")));
	  
	  Thread.sleep(3000);
	  
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@id='filter-list-pulldown']/div/span[2]"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-list-pulldown-pulldown']//label[text()='test']"))).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@id='filter-list-pulldown']/div/span[2]")).click();
	  Thread.sleep(3000);
  }
  
  @Test
  public void tableSort() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Test source sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_source_url first last']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td/span")));
	  
	  if(driver.findElement(By.xpath("//table[@id='links-table']/thead/tr/th")).getAttribute("class").equals("first sorted-desc"))
		  ;
	  else
	  	  driver.findElement(By.xpath("//div[@class='th-inner tip-link_source_url first last']")).click();
	  
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td/span")));
	  
	  try{if(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr/td/span")).getText().compareTo(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr[2]/td/span")).getText())==1)
		  	throw new Exception("Sort down abnormal");
	  
	  //Check up down sort:
	      driver.findElement(By.xpath("//div[@class='th-inner tip-link_source_url first last']")).click();
	      wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td/span")));
	 	  
	 	  if(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr/td/span")).getText().compareTo(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr[2]/td/span")).getText())==-1)
	  			throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
	  
	  //Test priority sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_priority first last']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[2]/div/div")));
	  
	  if(driver.findElement(By.xpath("//table[@id='links-table']/thead/tr/th")).getAttribute("class").equals("first sorted-desc"))
		  ;
	  else
		  driver.findElement(By.xpath("//div[@class='th-inner tip-link_priority first last']")).click();
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[2]/div/div")));
	  
	  try{if(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr/td[2]/div/div/span")).getText().compareTo(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[2]/div/div/span")).getText())==-1)
		  	throw new Exception("Sort down abnormal");
	  	
	  //Check up down sort:
	 	  driver.findElement(By.xpath("//div[@class='th-inner tip-link_priority first last']")).click();
	 	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[2]/div/div")));
	 	  Thread.sleep(2000);
	 	  if(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr/td[2]/div/div/span")).getText().compareTo(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[2]/div/div/span")).getText())==1)
	 		  throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
	  
	  //Test Link to my site sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_to_my_site first last']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[3]/span")));
	  
	  if(driver.findElement(By.xpath("//table[@id='links-table']/thead/tr/th")).getAttribute("class").equals("first sorted-desc"))
		  ;
	  else
		  driver.findElement(By.xpath("//div[@class='th-inner tip-link_to_my_site first last']")).click();
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[3]/span")));
	  
	  try{if(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr/td[3]/span")).getText().compareTo(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[3]/span")).getText())==1)
		  	throw new Exception("Sort down abnormal");
	  
	  //Check up down sort:
	      driver.findElement(By.xpath("//div[@class='th-inner tip-link_to_my_site first last']")).click();
	      wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[3]/span")));
	 	  
	 	  if(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr/td[3]/span")).getText().compareTo(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[3]/span")).getText())==-1)
	  			throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
	  
	  //Test link from my site sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_from_my_site first last']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[4]")));
	  
	  if(driver.findElement(By.xpath("//table[@id='links-table']/thead/tr/th")).getAttribute("class").equals("first sorted-desc"))
		  ;
	  else
		  driver.findElement(By.xpath("//div[@class='th-inner tip-link_from_my_site first last']")).click();
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[4]")));
	  
	  try{if(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr/td[4]")).getText().compareTo(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[4]")).getText())==1)
		  	throw new Exception("Sort down abnormal");
	  
	  //Check up down sort:
	      driver.findElement(By.xpath("//div[@class='th-inner tip-link_from_my_site first last']")).click();
	      wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[4]")));
	 	  
	 	  if(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr/td[4]")).getText().compareTo(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[4]")).getText())==-1)
	  			throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
	  
	  //Test link to competitors sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-num_competitors_with_links first last']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[5]")));
	  
	  if(driver.findElement(By.xpath("//table[@id='links-table']/thead/tr/th")).getAttribute("class").equals("first sorted-desc"))
		  ;
	  else
		  driver.findElement(By.xpath("//div[@class='th-inner tip-num_competitors_with_links first last']")).click();
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[5]")));
	  
	  try{if(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr/td[5]")).getText().compareTo(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[5]")).getText())==1)
		  	throw new Exception("Sort down abnormal");
	  
	  //Check up down sort:
	      driver.findElement(By.xpath("//div[@class='th-inner tip-num_competitors_with_links first last']")).click();
	      wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[5]")));
	 	  
	 	  if(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr/td[5]")).getText().compareTo(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[5]")).getText())==-1)
	  			throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
	  
	  //Test page rank sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-link_page_rank first last']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[6]/span")));
	  
	  if(driver.findElement(By.xpath("//table[@id='links-table']/thead/tr/th")).getAttribute("class").equals("first sorted-desc"))
		  ;
	  else
		  driver.findElement(By.xpath("//div[@class='th-inner tip-link_page_rank first last']")).click();
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[6]/span")));
	  
	  try{if(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr/td[6]/span")).getText().compareTo(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[6]/span")).getText())==1)
		  	throw new Exception("Sort down abnormal");
	  
	  //Check up down sort:
	      driver.findElement(By.xpath("//div[@class='th-inner tip-link_page_rank first last']")).click();
	      wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td[6]/span")));
	 	  
	 	  if(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr/td[6]/span")).getText().compareTo(driver.findElement(By.xpath("//table[@id='links-table']/tbody/tr[2]/td[6]/span")).getText())==-1)
	  			throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
	  
	  
  }
  
  @Test
  public void helpWithThisPage() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='help_for_this_page iconlink track']")));
	  String winHandleBefore = driver.getWindowHandle();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='help_for_this_page iconlink track']"))).click();
	  switcWindow();
	  assertEquals("Help with this page:","Links overview : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  Thread.sleep(3000);
  }
  
  @Test
  public void importLinks() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String winHandleBefore = driver.getWindowHandle();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='view sharing']/div/a"))).click();
	  
	  //Test link "How to format your file":
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='import-links-dialog']/p[3]/a"))).click();
	  switcWindow();
	  assertEquals("How to format your file:","How to import links into Optify : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  //Here we can add some file to test.
	  
	  
	  //Close the frame:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@aria-labelledby='ui-dialog-title-import-links-dialog']/div/a/span"))).click();
	  Thread.sleep(3000);
  }
  
  @Test
  public void exportCSV() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='view sharing']/div/a[2]"))).click();
	  
	  //Here we can add some file to test.
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void exportPDF() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='view sharing']/div/a[3]"))).click();
	  
	  //Here we can add some file to test.
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void nextAndPrev() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='table-pager data-pager']//a[text()='2']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody/tr/td/span")));
	  wait.until(presenceOfElementLocated(By.xpath("//span[@class='active number']")));
	  assertEquals("Pgae active number:","2",driver.findElement(By.xpath("//span[@class='active number']")).getText()); 
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='table-pager data-pager']//a[text()='Next']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//span[@class='active number']")));
	  assertEquals("Pgae active number:","3",driver.findElement(By.xpath("//span[@class='active number']")).getText()); 
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='table-pager data-pager']//a[text()='Prev']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//span[@class='active number']")));
	  assertEquals("Pgae active number:","2",driver.findElement(By.xpath("//span[@class='active number']")).getText()); 
  }
  
  @Test
  public void showResults() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  int SUMFIF=50;
	  int SUMTF=25;
	  int SUMTH=10;
	  
	  //show 50:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='results_count_pager']/select"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select/option[3]"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody")));
	  Thread.sleep(3000);
	  assertEquals("count 50:",SUMFIF,getRowCount(By.xpath("//table[@id='links-table']/tbody")));
	  Thread.sleep(3000);
	  
	  //show 25:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='results_count_pager']/select"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select/option[2]"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody")));
	  Thread.sleep(3000);
	  assertEquals("count 25:",SUMTF,getRowCount(By.xpath("//table[@id='links-table']/tbody")));
	  Thread.sleep(3000);
	  
	  //show 10:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='results_count_pager']/select"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select/option"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/select"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='links-table']/tbody")));
	  Thread.sleep(3000);
	  assertEquals("count 10:",SUMTH,getRowCount(By.xpath("//table[@id='links-table']/tbody")));
	  Thread.sleep(3000);
  }
  
  //@Test
  public void getSuggestions() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Get Url suggest:
	  wait.until(presenceOfElementLocated(By.xpath("//button[@id='suggest-links']/span"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//input[@id='suggest-links-input']"))).sendKeys("photograph");
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='suggest-url-popup']/div/button"))).click();
	  wait = new WebDriverWait(driver, 100);
	  String getUrl=wait.until(presenceOfElementLocated(By.xpath("//table[@class='inner_table']/tbody/tr/td/span/a"))).getText();
	  wait = new WebDriverWait(driver, 10);
	  Thread.sleep(2000);

	  //Save Url to test group:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@class='inner_table']/tbody/tr/td/input"))).click();	
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='suggest-list-control']/div/div/span[2]")));	
	  String getId=wait.until(presenceOfElementLocated(By.xpath("//div[@id='suggest-list-control']/div"))).getAttribute("id");
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@id='suggest-list-control']/div/div/span[2]"))).perform();
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='"+getId+"-pulldown']//input[@placeholder='Create new list']"))).sendKeys("test");
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//button[@id='suggest-links-save']/span"))).click();
	  
	  //Check saved url in status group:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='report-filters]/div/div/div/span[2]")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='report-filters']/div/div/div/span[2]"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-list-pulldown-pulldown]/ul//label[text()='test']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@class='inner_table']/tbody//a[text()='"+getUrl+"']")));
	  
	  //Delete url & group:
	  int numRows=getRowCount(By.xpath("//table[@id='links-table']/tbody"));
	  for(int i=1;i<numRows;i++){
		  wait.until(presenceOfElementLocated(By.xpath("//table[@class='inner_table']/tbody/tr["+i+"]")));
		  builder.moveToElement(driver.findElement(By.xpath("//table[@class='inner_table']/tbody/tr["+i+"]"))).perform();
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-table on-hover-menu']"))).click();
	  }
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='report-filters]/div/div/div/span[2]")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='report-filters']/div/div/div/span[2]"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-list-pulldown-pulldown]/ul//label[text()='test']")));
	  builder.moveToElement(driver.findElement(By.xpath("//div[@id='filter-list-pulldown-pulldown]/ul//label[text()='test']")));
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='filter-list-pulldown-pulldown]//a[@class='delete']"))).click();
  }
  
  //===============================================================================================
  private Function<WebDriver, WebElement> presenceOfElementLocated(final By locator) {
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
  
  //================================================================================================
  private int getRowCount(By by) throws Exception {
      try { WebElement table = driver.findElement(by);
            List<WebElement> rows = table.findElements(By.tagName("tr"));
            return rows.size();
      
      } catch (Exception e) {
          return -1;
      }
  }
 }

package com.optifyTest;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.By;
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
public class LeadIntelligence extends TestCase {

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
	
	enterToLeadIntelligence();
  }
  
  public static void enterToLeadIntelligence() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  Actions builder = new Actions(driver);
	  
		  //Log in Optify:
		  driver.get(homeAddress+"/login");
		  driver.findElement(By.id("j_username")).sendKeys(userName);
		  driver.findElement(By.id("j_password")).sendKeys(password);
		  driver.findElement(By.id("login_button")).click();
		  assertEquals("Dashboard","Dashboard | Optify",driver.getTitle());
		  
		  //Enter to LeadIntelligence:
		  wait.until(presenceOfElementLocated(By.xpath("//li[@class='enable']/a/span")));
		  builder.clickAndHold(driver.findElement(By.xpath("//li[@class='enable']/a/span"))).perform();
		  Thread.sleep(3000);
		  wait.until(presenceOfElementLocated(By.xpath("//html/body/div/div[2]/ul/li[4]/ul/li/a"))).click();
		  assertEquals("Lead Intelligence page","Lead Intelligence | Optify",driver.getTitle());
		  Thread.sleep(3000);
  }
  
  @Test
  public void helpWithThisPage() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='help_for_this_page iconlink track']")));
	  String winHandleBefore = driver.getWindowHandle();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='help_for_this_page iconlink track']"))).click();
	  switcWindow();
	  assertEquals("Help with this page:","Lead Intelligence overview : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  Thread.sleep(3000);
  } 
  
  @Test
  public void showResults() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  int SUMFIF=50;
	  int SUMTF=25;
	  int SUMTH=10;
	  
	  //Check all Visitors for full testing
	  wait.until(presenceOfElementLocated(By.xpath("//input[@id='show_all']"))).click();
	  
	  //Select 90 days:
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-90d']"))).click();
	  }
	  catch(Exception ex){}
	  
	  //show 50:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/span/select")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='results_count_pager']/span/select"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/span/select/option[3]"))).click();
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/span/select"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody")));
	  Thread.sleep(3000);
	  
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='lead_pager']//a[@class='next']")));
	  	  assertEquals("Showing 50:",SUMFIF,getRowCount(By.xpath("//table[@id='lead_table']/tbody")));
	  }
	  catch(Exception ex){ if(getRowCount(By.xpath("//table[@id='lead_table']/tbody"))>SUMFIF)
	  		throw new Exception("Showing more than 50 rows!");
	  }
	  
	  Thread.sleep(3000);
	  
	  //show 25:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/span/select")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='results_count_pager']/span/select"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/span/select/option[2]"))).click();
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/span/select"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody")));
	  Thread.sleep(3000);
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='lead_pager']//a[@class='next']")));
  	  assertEquals("Showing 25:",SUMTF,getRowCount(By.xpath("//table[@id='lead_table']/tbody")));
	  }
	  catch(Exception ex){ if(getRowCount(By.xpath("//table[@id='lead_table']/tbody"))>SUMTF)
	  		throw new Exception("Showing more than 25 rows!");
	  }
	  
	  Thread.sleep(3000);
	  
	  //show 10:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/span/select")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='results_count_pager']/span/select"))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/span/select/option"))).click();
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='results_count_pager']/span/select"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody")));
	  Thread.sleep(3000);
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='lead_pager']//a[@class='next']")));
  	  assertEquals("Showing 10:",SUMTH,getRowCount(By.xpath("//table[@id='lead_table']/tbody")));
	  }
	  catch(Exception ex){ if(getRowCount(By.xpath("//table[@id='lead_table']/tbody"))>SUMTH)
	  		throw new Exception("Showing more than 10 rows!");
	  }
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void nextAndPrev() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 15);
	  boolean isChecked=wait.until(presenceOfElementLocated(By.xpath("//input[@id='hideISP']"))).isSelected();
	  
	  //Prepare test:
	  try{ wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-90d']"))).click();
		 }
		 catch(Exception e){}
	  
	  if(isChecked)
			 wait.until(presenceOfElementLocated(By.xpath("//label[@id='hideISPLabel']"))).click();
	  
	 //Set Check all Visitors
     wait.until(presenceOfElementLocated(By.xpath("//input[@id='show_all']"))).click();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='lead_pager']//a[text()='2']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr/td")));
	  wait.until(presenceOfElementLocated(By.xpath("//span[@class='active number']")));
	  assertEquals("Pgae active number:","2",driver.findElement(By.xpath("//span[@class='active number']")).getText()); 
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='lead_pager']//a[@class='next']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr/td")));
	  wait.until(presenceOfElementLocated(By.xpath("//span[@class='active number']")));
	  assertEquals("Pgae active number:","3",driver.findElement(By.xpath("//span[@class='active number']")).getText()); 
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='lead_pager']//a[@class='previous']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr/td")));
	  wait.until(presenceOfElementLocated(By.xpath("//span[@class='active number']")));
	  assertEquals("Pgae active number:","2",driver.findElement(By.xpath("//span[@class='active number']")).getText()); 
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void calendar() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  Calendar todayDate=Calendar.getInstance();
	  final int SEVEN = -7;
	  final int THIRTY = -30;
	  final int NINETY = -90;
	  
	  //Test 7d dates:
	  todayDate.add(Calendar.DAY_OF_MONTH,SEVEN);
	  try{ wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-7d']"))).click();
	  }
	  catch(Exception e){}
	  
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr")));
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-7d selected']")));
	  
	  driver.findElement(By.xpath("//div[@class='interval_date_picker']/button/span")).click();
	  Thread.sleep(3000);
	  assertEquals("7d day",Integer.toString(todayDate.get(Calendar.DAY_OF_MONTH)+1),driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).getText());
	  assertEquals("7d month",todayDate.get(Calendar.MONTH),returnMonthInt(driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText()));
	  assertEquals("7d year",Integer.toString(todayDate.get(Calendar.YEAR)),driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());
	  
	  //Test 90d dates:
	  todayDate=Calendar.getInstance();
	  todayDate.add(Calendar.DAY_OF_MONTH,NINETY);
	  driver.findElement(By.xpath("//a[@class='interval_change_link interval-90d']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr")));
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-90d selected']")));
	  
	  driver.findElement(By.xpath("//div[@class='interval_date_picker']/button/span")).click();
	  Thread.sleep(3000);
	  assertEquals("90d day",Integer.toString(todayDate.get(Calendar.DAY_OF_MONTH)+1),driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).getText());
	  assertEquals("90d month",todayDate.get(Calendar.MONTH),returnMonthInt(driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText()));
	  assertEquals("90d year",Integer.toString(todayDate.get(Calendar.YEAR)),driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());
	  
	  //Test 30d dates:
	  todayDate=Calendar.getInstance();
	  todayDate.add(Calendar.DAY_OF_MONTH,THIRTY);
	  driver.findElement(By.xpath("//a[@class='interval_change_link interval-30d']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr")));
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-30d selected']")));
	  
	  driver.findElement(By.xpath("//div[@class='interval_date_picker']/button/span")).click();
	  Thread.sleep(3000);
	  assertEquals("30d day",Integer.toString(todayDate.get(Calendar.DAY_OF_MONTH)+1),driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).getText());
	  assertEquals("30d month",todayDate.get(Calendar.MONTH),returnMonthInt(driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText()));
	  assertEquals("30d year",Integer.toString(todayDate.get(Calendar.YEAR)),driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());
	  
	  //Test 24h dates:
	  todayDate=Calendar.getInstance();
	  driver.findElement(By.xpath("//a[@class='interval_change_link interval-24h']")).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr")));
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-24h selected']")));
	  
	  todayDate.add(Calendar.DAY_OF_MONTH,-1);
	  driver.findElement(By.xpath("//div[@class='interval_date_picker']/button/span")).click();
	  Thread.sleep(3000);
	  assertEquals("24h day",Integer.toString(todayDate.get(Calendar.DAY_OF_MONTH)),driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).getText());
	  assertEquals("24h month",todayDate.get(Calendar.MONTH),returnMonthInt(driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText()));
	  assertEquals("24h year",Integer.toString(todayDate.get(Calendar.YEAR)),driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText());
	  driver.findElement(By.xpath("//div[@class='interval_date_picker']/button/span")).click();
	  Thread.sleep(3000);
	  
	  driver.findElement(By.xpath("//a[@class='interval_change_link interval-90d']")).click();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void needHelp() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  String winHandleBefore = driver.getWindowHandle();
	 	  
	  //Open need help:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-dialog trainer-minimized']/div/div/div/a"))).click();
	  Thread.sleep(3000);
	  
	  String help1=("Lead intelligence is critical to optimizing your site and closing more deals. Let's get started on the path to higher conversion rates!");
	  assertEquals("help 1/6:",help1,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  
	  //Learn more about Lead Intelligence:
	  driver.findElement(By.xpath("//div[@class='trainer-actions']/ul/li/a")).click();
	  switcWindow();
	  assertEquals("Learn more about Lead Intelligence:","Lead Intelligence overview : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  //Lunch video:
	  wait.until(presenceOfElementLocated(By.xpath("//a[@class='launch-video']/img"))).click();
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='video-close']/a/img"))).click();
	  
	  //Go forward2:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
	  String help=("The "+'"'+"Overview"+'"'+" provides an at-a-glance perspective of the number and quality of visitors and leads hitting your site.");
	  assertEquals("help 2/6:",help,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Check qtip-tip:
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='These values highlight how well you’re attracting visitors and converting leads.']")));
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Ideally, you want to see more yellow (visitors with high scores) and less blue (visitors with low scores).']")));
	  
	  //Learn more about Lead Intelligence:
	  driver.findElement(By.xpath("//div[@class='trainer-actions']/ul/li/a")).click();
	  switcWindow();
	  assertEquals("Learn more about Lead Intelligence:","Lead Intelligence overview : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  //Lunch video:
	  driver.findElement(By.xpath("//div[@class='trainer-actions']/ul/li[2]/a")).click();
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@style='display: block; width: auto; min-height: 0px; height: 393px; ']/div/a/img"))).click();
	  
	  //Go forward3:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
	  help=("Assigning lead scores is an easy way to filter and prioritize your visitors based on the characteristics that matter most to your business.");
	  assertEquals("help 3/6:",help,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Check qtip-tip:
	  String qtipStr='"'+"Active Visitors"+'"'+" is our default score set, but you'll want to create your own custom score sets later.";
	  Thread.sleep(3000);
	  assertEquals("Qtip:",qtipStr,driver.findElement(By.xpath("//html/body/div[17]/div[2]/div/div")).getText());
	  
	  //Learn more about Lead Scoring:
	  driver.findElement(By.xpath("//div[@class='trainer-actions']/ul/li[2]/a")).click();
	  switcWindow();
	  assertEquals("Learn more about Lead Scoring:","Lead Scoring and Exclusion overview : Help and Support",driver.getTitle());
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
	  //Go forward4:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
	  help=("These are your recent visitors and leads. You'll see details on where they came from, how often they visited, and any profile details we've captured.");
	  assertEquals("help 4/6:",help,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Check qtip-tip:
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Leads will appear with name and company details.']")));
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='You can find a particular person or company using the search box.']")));
	  
	  //Go forward5:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
	  help=("Before we dive into a specific lead, there are a few other actions that will help you organize your leads.");
	  assertEquals("help 5/6:",help,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Check qtip-tip: 
	  qtipStr="You can sort by any column, depending on what's most important to you.";
	  Thread.sleep(3000);
	  assertEquals("Qtip:",qtipStr,driver.findElement(By.xpath("//html/body/div[17]/div[2]/div/div")).getText());
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='Click the down arrow to:']")));
	  
	  //Go forward6:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='trainer-paginator-forward']/a"))).click();
	  
	  help=("The time has come to learn even more about the leads that most interest you. Click on a lead now!");
	  assertEquals("help 6/6:",help,driver.findElement(By.xpath("//div[@class='trainer-left']/div")).getText());
	  
	  //Check qtip-tip:
	  qtipStr="You can click on the lead's name to get more details, or...";
	  Thread.sleep(3000);
	  assertEquals("Qtip:",qtipStr,driver.findElement(By.xpath("//html/body/div[17]/div[2]/div/div")).getText());
	  wait.until(presenceOfElementLocated(By.xpath("//div[text()='...you can click on the magnifying glass to get more details.']")));
	  
	  driver.findElement(By.xpath("//div[@class='trainer-hide']")).click();
	  Thread.sleep(3000);
  }
  
  @Test
  public void overview() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  try{ wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-90d']"))).click();
		 }
		 catch(Exception e){
			 wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-24h']"))).click();
			 wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-90d']"))).click();
		 }
	
	  try{wait.until(presenceOfElementLocated(By.xpath("//div[@id='lead_pager']//a[text()='1']"))).click();
	  }
	  catch(Exception ex){}
	  
	  //Prepare data table for testing:
	  boolean isChecked=wait.until(presenceOfElementLocated(By.xpath("//input[@id='hideISP']"))).isSelected();
	 
	  if(!isChecked)
		  wait.until(presenceOfElementLocated(By.xpath("//label[@id='hideISPLabel']"))).click();
	 
	  //Set Check all Visitors
	  wait.until(presenceOfElementLocated(By.xpath("//input[@id='show_all']"))).click();
	  
	  //Test total visitors qtip:
	  wait.until(presenceOfElementLocated(By.xpath("//h5[@class='tip-total-visitors']/span/span"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='qtip-content qtip-content']//*[text()=' shows the number of visitors to your website within the time frame selected. To change the time frame, use the ']")));
	  
	  //Test Apply score set qtip:
	  wait.until(presenceOfElementLocated(By.xpath("//h5[@class='tip-apply-score-set']/span/span"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='qtip-content qtip-content']//*[text()=' applies the rules of a score set (a collection of rules for evaluating leads) to the lead data shown on this page. You can create as many score sets as you want.']")));
	  
	  //Test drop down list:
	  wait.until(presenceOfElementLocated(By.xpath("//select[@id='lead_rule_set']")));
	  builder.clickAndHold(driver.findElement(By.xpath("//select[@id='lead_rule_set']")));
	  wait.until(presenceOfElementLocated(By.xpath("//select[@id='lead_rule_set']/option[2]"))).click();
	  Thread.sleep(3000);
	  builder.clickAndHold(driver.findElement(By.xpath("//select[@id='lead_rule_set']")));
	  wait.until(presenceOfElementLocated(By.xpath("//select[@id='lead_rule_set']/option"))).click();
	  Thread.sleep(3000);
	  
	  //Test Total Visitors:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr")));
	  int rowNum=getRowsNum(By.xpath("//table[@id='lead_table']/tbody"));
	  assertEquals("Total Visitors number:",driver.findElement(By.xpath("//big[@id='total_visitors']")).getText(),Integer.toString(rowNum));
	  
	  //Test Total Leads:
	  //Set Check for leads:
	  wait.until(presenceOfElementLocated(By.xpath("//input[@id='show_leads']"))).click();
	  if(!wait.until(presenceOfElementLocated(By.xpath("//td[@class='first last']"))).getText().equals("No results.")){
		  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr")));
		  rowNum=getRowsNum(By.xpath("//table[@id='lead_table']/tbody"));
		  assertEquals("Total Leads number:",driver.findElement(By.xpath("//big[@id='total_leads']")).getText(),Integer.toString(rowNum));
	  }
	  
	  Thread.sleep(3000);
	  
	  //Set Check all Visitors
	  wait.until(presenceOfElementLocated(By.xpath("//input[@id='show_all']"))).click();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void editLeadsReport() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  wait.until(presenceOfElementLocated(By.xpath("//span[@class='icon-cog white-icon']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//input[@name='phone']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//input[@name='email']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//input[@name='city']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//input[@name='state']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//input[@name='zip']"))).click();
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='ui-dialog-buttonpane ui-widget-content ui-helper-clearfix']/div/button/span"))).click();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-phone first last']")));
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-email first last']")));
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-city first last']")));
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-state first last']")));
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-zip first last']")));
	  
	  wait.until(presenceOfElementLocated(By.xpath("//span[@class='icon-cog white-icon']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//input[@name='phone']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//input[@name='email']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//input[@name='city']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//input[@name='state']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//input[@name='zip']"))).click();
	  Thread.sleep(3000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='ui-dialog-buttonpane ui-widget-content ui-helper-clearfix']/div/button/span"))).click();
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void tableSort() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 15);
	  
	  //Prepare data table for testing:
	  boolean isChecked=wait.until(presenceOfElementLocated(By.xpath("//input[@id='hideISP']"))).isSelected();
	 
	  if(!isChecked)
		  wait.until(presenceOfElementLocated(By.xpath("//label[@id='hideISPLabel']"))).click();
	 
	  //Set Check all Visitors
	  wait.until(presenceOfElementLocated(By.xpath("//input[@id='show_all']"))).click();
	  
	  try{ wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-90d']"))).click();
		 }
		 catch(Exception e){
			 wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-24h']"))).click();
			 wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-90d']"))).click();
		 }
	  
	  System.out.print("Hide Isps:"+driver.findElement(By.xpath("//*[@id='leads_report']/div/div[2]/span[3]/script")).getAttribute("value"));
	  
	  //Test Last Visit Date:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-date first last']"))).click();
	  try{wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr/td/div")));
	  }
	  catch(WebDriverException ex){wait.until(presenceOfElementLocated(By.xpath("//input[@id='show_all']"))).click();
	  }
	  
	  try{driver.findElement(By.xpath("//div[@class='sort-indicator first last']"));
	  		
	  }catch(WebDriverException ex){
		  driver.findElement(By.xpath("//div[@class='th-inner tip-date first last']")).click();
	  }
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr/td/div")));
	  
	  Thread.sleep(3000);

	  try{if(driver.findElement(By.xpath("//table[@id='lead_table']/tbody/tr/td/div")).getText().compareTo(driver.findElement(By.xpath("//table[@id='lead_table']/tbody/tr[2]/td/div")).getText())==1)
		  	throw new Exception("Sort down abnormal");
	  
	  	
	  //Check up down sort:
	 	  driver.findElement(By.xpath("//div[@class='th-inner tip-date first last']")).click();
	 	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr/td/div")));
	 	  Thread.sleep(3000);
	 	  
	 	  if(driver.findElement(By.xpath("//table[@id='lead_table']/tbody/tr/td/div")).getText().compareTo(driver.findElement(By.xpath("//table[@id='lead_table']/tbody/tr[2]/td/div")).getText())==-1)
	  			throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
	  
	  //Test name sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-name first last']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr/td[2]/a")));
	  
	  try{driver.findElement(By.xpath("//div[@class='sort-indicator first last']"));
	  		
	  }catch(WebDriverException ex){
		  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-name first last']"))).click();
	  }
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr/td[2]/a")));
	  
	  try{if(driver.findElement(By.xpath("//table[@id='lead_table']/tbody/tr/td[2]/a")).getText().compareTo(driver.findElement(By.xpath("//table[@id='lead_table']/tbody/tr[2]/td[2]/a")).getText())==-1)
		  	throw new Exception("Sort down abnormal");
	  
	  	  //Check up down sort:
	  	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-name first last']"))).click();
	      wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr/td[2]/a")));
	 	  
	 	  if(driver.findElement(By.xpath("//table[@id='lead_table']/tbody/tr/td[2]/a")).getText().compareTo(driver.findElement(By.xpath("//table[@id='lead_table']/tbody/tr[2]/td[2]/a")).getText())==1)
	  			throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
	  
	  //Test Company sort:=======================================================
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='th-inner tip-company first last']"))).click();
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr/td[3]")));
	  
	  try{driver.findElement(By.xpath("//div[@class='sort-indicator first last']"));
	  		
	  }catch(WebDriverException ex){
		  driver.findElement(By.xpath("//div[@class='th-inner tip-company first last']")).click();
	  }
	  
	  //Check down up sort:
	  wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr/td[3]")));
	  
	  try{if(driver.findElement(By.xpath("//table[@id='lead_table']/tbody/tr/td[3]")).getText().compareTo(driver.findElement(By.xpath("//table[@id='lead_table']/tbody/tr[2]/td[3]")).getText())==1)
		  	throw new Exception("Sort down abnormal");
	  	
	  	  //Check up down sort:
	 	  driver.findElement(By.xpath("//div[@class='th-inner tip-company first last']")).click();
	 	 wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr/td[3]")));
	 	  
	 	  if(driver.findElement(By.xpath("//table[@id='lead_table']/tbody/tr/td[3]")).getText().compareTo(driver.findElement(By.xpath("//table[@id='lead_table']/tbody/tr[2]/td[3]")).getText())==-1)
	  			throw new Exception("Sort up abnormal");
  		  
	  }
	  finally{}
	  
	  Thread.sleep(3000);
  }
  
  @Test
  public void serch()throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	 //Set Check all Visitors
	 wait.until(presenceOfElementLocated(By.xpath("//input[@id='show_all']"))).click();
	 
	 //Prepare data table for testing:
	 boolean isChecked=wait.until(presenceOfElementLocated(By.xpath("//input[@id='hideISP']"))).isSelected();
	 
	 if(!isChecked)
		 wait.until(presenceOfElementLocated(By.xpath("//label[@id='hideISPLabel']"))).click();
	 
	 try{ wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-90d']"))).click();
	 }
	 catch(Exception e){}
	 
	 //Get name of company to examine:
	 String companyName=wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr/td[3]"))).getText();
	 
	 wait.until(presenceOfElementLocated(By.xpath("//input[@id='search']"))).sendKeys(companyName);
	 Thread.sleep(3000);
	 
	 assertEquals("Search results:",companyName,wait.until(presenceOfElementLocated(By.xpath("//table[@id='lead_table']/tbody/tr/td[3]"))).getText());
	 
	 wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-text-filter-clear']"))).click();
	 
	 Thread.sleep(3000);
  }
  
  @Test 
  public void showMoreFromThisCompany() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	 //Prepare data table for testing:
	 boolean isChecked=wait.until(presenceOfElementLocated(By.xpath("//input[@id='hideISP']"))).isSelected();
	 
	 if(!isChecked)
		 wait.until(presenceOfElementLocated(By.xpath("//label[@id='hideISPLabel']"))).click();
	 
	 //Set Check all Visitors
	 wait.until(presenceOfElementLocated(By.xpath("//input[@id='show_all']"))).click();
	 
	 //Select 90 days:
	 try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-90d']"))).click();
	 }
	 catch(Exception ex){}
	 
	 String name=wait.until(presenceOfElementLocated(By.xpath("//*[@id='lead_table']/tbody/tr/td[3]"))).getText();
	 
	 //Test show more from this company:
	 wait.until(presenceOfElementLocated(By.xpath("//div[@class='action action-menu button-action']")));
	 builder.clickAndHold(driver.findElement(By.xpath("//div[@class='action action-menu button-action']"))).perform();
	 wait.until(presenceOfElementLocated(By.xpath("//div[@class='action action-see-more-company button-action']"))).click();
	 
	 wait.until(presenceOfElementLocated(By.xpath("//*[@id='lead_table']/tbody/tr/td[3]")));
	 
	 int rowsNum=getRowsNum(By.xpath("//*[@id='lead_table']/tbody"));
	 
	 for(int i=1;i<=rowsNum;i++)
		 assertEquals("Show more from this company:",name,driver.findElement(By.xpath("//*[@id='lead_table']/tbody/tr["+i+"]/td[3]")).getText());
	 
	 //Clear the search box.
	 wait.until(presenceOfElementLocated(By.xpath("//div[@class='data-text-filter-clear']"))).click();
	 
	 Thread.sleep(3000);
  }
  
  @Test
  public void unwatchWatch() throws Exception{
	 WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	 //Prepare data table for testing:
	 boolean isChecked=wait.until(presenceOfElementLocated(By.xpath("//input[@id='hideISP']"))).isSelected();
	 
	 if(!isChecked)
		 wait.until(presenceOfElementLocated(By.xpath("//label[@id='hideISPLabel']"))).click();
	 
	 //Set Check all Visitors
	 wait.until(presenceOfElementLocated(By.xpath("//input[@id='show_all']"))).click();
	
	 //Select 90 days:
	 try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-90d']"))).click();
	 }
	 catch(Exception ex){}
	 
	 builder.moveToElement(wait.until(presenceOfElementLocated(By.xpath("//*[@id='lead_table']/tbody/tr")))).perform();
	 wait.until(presenceOfElementLocated(By.xpath("//div[@class='action action-menu button-action']")));
	 builder.clickAndHold(driver.findElement(By.xpath("//div[@class='action action-menu button-action']"))).perform();
	 
	 try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='action action-watch button-action']"))).click();
	 }
	 catch(Exception ex){
		 wait.until(presenceOfElementLocated(By.xpath("//div[@class='action action-unwatch button-action']"))).click();
	 }
	 
	 Thread.sleep(3000);
	 
	 //Test edit settings:
	 wait.until(presenceOfElementLocated(By.xpath("//*[@id='lead_table']/tbody/tr[2]/td/div/a/strong"))).click();
	
	 assertEquals("Edit settings:","Alerts | Optify",driver.getTitle());
	 driver.navigate().back();
	 
	 //Return to unwatch:
	 wait.until(presenceOfElementLocated(By.xpath("//*[@id='lead_table']/tr/td")));
	 builder.moveToElement(driver.findElement(By.xpath("//*[@id='lead_table']/tr/td"))).perform();
	 wait.until(presenceOfElementLocated(By.xpath("//div[@class='action action-menu button-action']")));
	 builder.clickAndHold(driver.findElement(By.xpath("//div[@class='action action-menu button-action']"))).perform();
	 try{wait.until(presenceOfElementLocated(By.xpath("//div[@class='action action-unwatch button-action']"))).click();
	 }
	 catch(Exception ex){
		 wait.until(presenceOfElementLocated(By.xpath("//div[@class='action action-watch button-action']"))).click();
	 }
	 
	 Thread.sleep(3000);
  }
  
  @Test
  public void hideUndo() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Prepare data table for testing:
	  boolean isChecked=wait.until(presenceOfElementLocated(By.xpath("//input[@id='hideISP']"))).isSelected();
	 
	  if(!isChecked)
		  wait.until(presenceOfElementLocated(By.xpath("//label[@id='hideISPLabel']"))).click();
	 
	  //Set Check all Visitors
	  wait.until(presenceOfElementLocated(By.xpath("//input[@id='show_all']"))).click();
	  
	  //Select 90 days:
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-90d']"))).click();
	  }
	  catch(Exception ex){}
	  
	  builder.moveToElement(wait.until(presenceOfElementLocated(By.xpath("//*[@id='lead_table']/tbody/tr")))).perform();
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='action action-menu button-action']")));
	  builder.clickAndHold(driver.findElement(By.xpath("//div[@class='action action-menu button-action']"))).perform();
	 
	  Thread.sleep(2000);
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='action action-hide button-action']"))).click();
	  
	  wait.until(presenceOfElementLocated(By.xpath("//span[@class='undo']/a"))).click();
	
	  Thread.sleep(3000);
  }
  
  @Test
  public void leadDetail() throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  
	  //Prepare data table for testing:
	  boolean isChecked=wait.until(presenceOfElementLocated(By.xpath("//input[@id='hideISP']"))).isSelected();
	 
	  if(!isChecked)
		  wait.until(presenceOfElementLocated(By.xpath("//label[@id='hideISPLabel']"))).click();
	 
	  String winHandleBefore = driver.getWindowHandle();
	  
	  //Set Check all Visitors
	  wait.until(presenceOfElementLocated(By.xpath("//input[@id='show_all']"))).click();
	  
	  //Select 90 days:
	  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='interval_change_link interval-90d']"))).click();
	  }
	  catch(Exception ex){}
	  
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='action action-view button-action']"))).click();
	  
	  switcWindow();
	  assertEquals("Lead Detail:","Lead Detail | Optify",driver.getTitle());
	  
	  String winHandlePageDetail = driver.getWindowHandle();
	  
	  //Test Help with this page (Lead Detail page):
	  wait.until(presenceOfElementLocated(By.xpath("//div[@class='page-help']/a"))).click();
	  switcWindow();
	  assertEquals("Lead Detail overview:","Lead Detail overview : Help and Support",driver.getTitle());
	  
	  driver.close();
	  driver.switchTo().window(winHandlePageDetail);
	  
	  //Test Search in Salesforce:
	  wait.until(presenceOfElementLocated(By.xpath("//div[@id='salesforce_search']/a"))).click();
	  switcWindow();
	  assertEquals("Salesforce:","salesforce.com - Customer Secure Login Page",driver.getTitle());
	  
	  driver.close();
	  driver.switchTo().window(winHandlePageDetail);
	  
	  driver.close();
	  driver.switchTo().window(winHandleBefore);
	  
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
  private void switcWindow(){
		  for(String winHandle : driver.getWindowHandles())
			  driver.switchTo().window(winHandle);
	}
  
  //================================================================================================
  private int getRowCount(By by) throws Exception {
	  Thread.sleep(3000);
      try { WebElement table = driver.findElement(by);
            List<WebElement> rows = table.findElements(By.tagName("tr"));
            return rows.size();
      
      } catch (Exception e) {
          return -1;
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
  
  //================================================================================================
  private int getRowsNum(By by) throws Exception{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  int num=0;
	  int sum=0;
	  
	  while(true){
		  wait.until(presenceOfElementLocated(by));
		  num=getRowCount(by);
		  sum+=num;
		  
		  try{wait.until(presenceOfElementLocated(By.xpath("//a[@class='next']"))).click();
		  }
		  catch (Exception e) {
	          return sum;
	      }
	  }
	   
  }
  
}
  
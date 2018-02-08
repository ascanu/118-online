package testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;

import excelReader.Xls_Reader;
import testutils.Utills;



public class TestBase extends Utills{


	
	public static Properties Repository = new Properties();
	public File f;
	public FileInputStream FI;
	public Xls_Reader Data;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static ExtentTestInterruptedException testexception;
	
	
	
	public void init() throws IOException{
		loadPropertiesFile();
		selectBrowser(Repository.getProperty("browser"));
		impliciteWait(30);
		driver.get(Repository.getProperty("url"));
	}
	
	
	public void loadPropertiesFile() throws IOException {
		f = new File(System.getProperty("user.dir")+"\\src\\main\\java\\config.properties");
		FI = new FileInputStream(f);
		Repository.load(FI);
		
		f = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\loginpage.properties");
		FI = new FileInputStream(f);
		Repository.load(FI);
		
		f = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\updateprofile.properties");
		FI = new FileInputStream(f);
		Repository.load(FI);
		
		//f = new File(System.getProperty("user.dir")+"\\src\\pageLocators\\timeTracks.properties");
		//FI = new FileInputStream(f);
		//Repository.load(FI);
		
		//f = new File(System.getProperty("user.dir")+"\\src\\pageLocators\\taskPage.properties");
		//FI = new FileInputStream(f);
		//Repository.load(FI);
		
		/*f = new File(System.getProperty("user.dir")+"\\src\\Config\\AppText.properties");
		FI = new FileInputStream(f);
		AppText.load(FI);	*/	
		
	}
	
	public static WebElement getLocator(String locator) throws Exception {
        String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname"))
				|| (locatorType.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag")))
			return driver.findElement(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link")))
			return driver.findElement(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css")))
			return driver.findElement(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}
	
	public static List<WebElement> getLocators(String locator) throws Exception {
        String[] split = locator.split(":");
		String locatorType = split[0];
		String locatorValue = split[1];

		if (locatorType.toLowerCase().equals("id"))
			return driver.findElements(By.id(locatorValue));
		else if (locatorType.toLowerCase().equals("name"))
			return driver.findElements(By.name(locatorValue));
		else if ((locatorType.toLowerCase().equals("classname"))
				|| (locatorType.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("tagname"))
				|| (locatorType.toLowerCase().equals("tag")))
			return driver.findElements(By.className(locatorValue));
		else if ((locatorType.toLowerCase().equals("linktext"))
				|| (locatorType.toLowerCase().equals("link")))
			return driver.findElements(By.linkText(locatorValue));
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElements(By.partialLinkText(locatorValue));
		else if ((locatorType.toLowerCase().equals("cssselector"))
				|| (locatorType.toLowerCase().equals("css")))
			return driver.findElements(By.cssSelector(locatorValue));
		else if (locatorType.toLowerCase().equals("xpath"))
			return driver.findElements(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}
	
	public static WebElement getWebElement(String locator) throws Exception{
		return getLocator(Repository.getProperty(locator));
	}
	
	public List<WebElement> getWebElements(String locator) throws Exception{
		return getLocators(Repository.getProperty(locator));
	}
	
	public void closeBrowser(){
		driver.quit();
	}
	
	public Object[][] getData(String ExcelName, String testcase) {
		Data = new Xls_Reader(System.getProperty("user.dir") + "//src//com//actiTime//testData//"+ExcelName);
		int rowNum = Data.getRowCount(testcase);
		System.out.println(rowNum);
		int colNum = Data.getColumnCount(testcase);
		Object sampleData[][] = new Object[rowNum - 1][colNum];
		for (int i = 2; i <= rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				sampleData[i - 2][j] = Data.getCellData(testcase, j, i);
			}
		}
		return sampleData;
	}
	@BeforeSuite(groups = { "Sanity" })
	public void beforeSuite()
	{
	//Report Directory and Report Name
		extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html",true);
		//extent.addSystemInfo("Environment","Environment Name")
		extent
                .addSystemInfo("Host Name", "118-Online")
                .addSystemInfo("Environment", "QA-Sanity")//It will provide Execution Machine Information
                .addSystemInfo("User Name", "Amupam Chauhan");
                //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
                //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
                extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	 
	}
	@BeforeTest(groups = { "Sanity" })
	public void beforeTest()
	{
	System.out.println(" in Before Test ");
	}
	

	@AfterTest(groups = { "Sanity" })
		public void AfterTest1(){
		extent.endTest(logger);
		extent.flush(); 
	System.out.println(" in After Test2");
	//extent.close();
	
	
	}
	
	@AfterSuite()
	public void afterSuite()
	{
		
	System.out.println("in afterSuite123");
		// close the Test Suite
	
	}
}

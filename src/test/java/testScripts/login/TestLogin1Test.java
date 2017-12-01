package testScripts.login;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageLibrary.loginPage.LoginPage;
import testbase.TestBase;


public class TestLogin1Test extends TestBase {
	
	@BeforeMethod 
	public void setUp() throws IOException {
		
		init();
	}

	@Test
	public void testLogin() throws Exception {	
		
		LoginPage login = new LoginPage();	
		driver.findElement(By.xpath(".//*[@id='form1']/header/div/div[2]/nav/ul/li[1]/a")).click();
		login.loginToApplication();
		String Actualtitle=driver.getTitle();
		 System.out.println(Actualtitle);	
		Assert.assertEquals(Actualtitle, "Free Online Business Directory in UK for Local Business Listing");
		System.out.println("test1");
	}

	@AfterMethod
	public void tearDown(ITestResult result)
	{
	 
	// Here will compare if test is failing then only it will enter into if condition
	if(ITestResult.FAILURE==result.getStatus())
	{
	try 
	{
	// Create refernce of TakesScreenshot
	TakesScreenshot ts=(TakesScreenshot)driver;
	 
	// Call method to capture screenshot
	File source=ts.getScreenshotAs(OutputType.FILE);
	 
	// Copy files to specific location here it will save all screenshot in our project home directory and
	// result.getName() will return name of test case so that screenshot name will be same
	FileUtils.copyFile(source, new File("./Screenshots/"+result.getName()+".png"));
	 
	System.out.println("Screenshot taken");
	} 
	catch (Exception e)
	{
	 
	System.out.println("Exception while taking screenshot "+e.getMessage());
	} 
	 
	 
	 
	}
	// close application
	driver.quit();
	}
	 
	 
	 
}

package testScripts.login;


import java.io.File;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pageLibrary.loginPage.LoginPage;
import pageLibrary.loginPage.Updateprofile;
import testbase.TestBase;


public class UpdateProfile1Test extends TestBase {
	
	
	@BeforeMethod(groups = { "Sanity" })
	public void setUp(Method method) throws Exception {
		logger = extent.startTest((this.getClass().getSimpleName() +" :: "+ method.getName()),method.getName()); //Test Case Start Here
		logger.assignAuthor("Anupam Chauhan"); //Test Script Author Name
		init();
		LoginPage login = new LoginPage();	
		driver.findElement(By.xpath(".//*[@id='form1']/header/div/div[2]/nav/ul/li[1]/a")).click();
		login.loginToApplication();
	}

	@Test
	public void updateprofile() throws Exception {
		logger = extent.startTest("updateprofile");
		Thread.sleep(2000);
		
	driver.findElement(By.xpath(".//*[@id='form1']/header/div/div[2]/nav/ul/li[2]/a")).click();
	Thread.sleep(2000);	
	driver.findElement(By.xpath(".//*[@id='form1']/nav/ul/li[1]/a[1]")).click();	
	Updateprofile.updatetoprofile();
	logger.log(LogStatus.PASS, "Test Case (updateprofile) Status is passed");
	
	}
	@AfterMethod(groups = { "Sanity" })
	public void afterMethod(ITestResult result)
	{	// Here will compare if test is failing then only it will enter into if condition
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
		driver.quit();	
		}
	
	
	}
	

		
	

	
	 



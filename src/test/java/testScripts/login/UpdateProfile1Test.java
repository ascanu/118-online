package testScripts.login;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageLibrary.loginPage.LoginPage;
import pageLibrary.loginPage.Updateprofile;
import testbase.TestBase;


public class UpdateProfile1Test extends TestBase {

	@BeforeClass
	public void setUp() throws Exception {
		init();
		LoginPage login = new LoginPage();	
		driver.findElement(By.xpath(".//*[@id='form1']/header/div/div[2]/nav/ul/li[1]/a")).click();
		login.loginToApplication();
	}

	@Test
	public void updateprofile() throws Exception {		
		Thread.sleep(2000);
		
	driver.findElement(By.xpath(".//*[@id='form1']/header/div/div[2]/nav/ul/li[2]/a")).click();
	Thread.sleep(2000);	
	driver.findElement(By.xpath(".//*[@id='form1']/nav/ul/li[1]/a[1]")).click();	
	Updateprofile.updatetoprofile();
	
	}


		
	

	@AfterClass
	public void testDown() {
		driver.quit();
	}

}

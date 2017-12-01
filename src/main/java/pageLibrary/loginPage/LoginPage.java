package pageLibrary.loginPage;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import testbase.TestBase;



public class LoginPage extends TestBase{
	
	public void loginToApplication() throws Exception{
		WebElement userName = getWebElement("118.login.username");
		Reporter.log("entering userName to the userName textbox");
		userName.sendKeys(Repository.getProperty("userName"));
		WebElement password = getWebElement("118.login.password");
		Reporter.log("entering password to the password textbox");
		password.sendKeys(Repository.getProperty("password"));
		Reporter.log("clicking on login button");
		getWebElement("118.login.loginButton").click();
		//driverwait();
	}
	
	public void loginToApplication(String userName, String password) throws Exception{
		WebElement UserName = getWebElement("actiTime.login.username");
		Reporter.log("entering userName to the userName textbox");
		UserName.sendKeys(userName);
		WebElement Password = getWebElement("actiTime.login.password");
		Reporter.log("entering password to the password textbox");
		Password.sendKeys(password);
		Reporter.log("clicking on login button");
		getWebElement("actiTime.login.loginButton").click();
	}

}

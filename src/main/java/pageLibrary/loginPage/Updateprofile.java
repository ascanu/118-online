package pageLibrary.loginPage;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import testbase.TestBase;

public class Updateprofile  extends TestBase{
	public static void updatetoprofile() throws Exception{
		System.out.println("insert to update profile method");
		WebElement firstName = getWebElement("118.update.firstName");
		Reporter.log("entering First name to the FirstName textbox");
		firstName.clear();
		firstName.sendKeys("testdata");
		WebElement lastName = getWebElement("118.update.lastName");
		Reporter.log("Update LastName to the Lastname textbox");
		lastName.clear();
		lastName.sendKeys("Lastname");
		WebElement postcode = getWebElement("118.update.postCode");
		Reporter.log("Update postCode to the Postcode textbox");
		postcode.clear();
		postcode.sendKeys("9348943573");
		WebElement updaetpassword = getWebElement("118.update.updatepassword");
		Reporter.log("Update postCode to the Postcode textbox");
		updaetpassword.clear();
		updaetpassword.sendKeys("test@123");
		
		WebElement update = getWebElement("118.update.updatebutton");
		update.click();
		
		
		
		
		
	}

}

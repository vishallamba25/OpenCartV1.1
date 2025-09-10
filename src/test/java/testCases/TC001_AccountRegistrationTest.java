package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	

	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("****Starting TC001_AccountRegistrationTest******");
		
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account Link");
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details....");
		
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		
		String email = (randomString()+"@yopmail.com");
		regpage.setEmail(email);
		
		regpage.setTelephone(randomNumber());
		
		String password = randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		System.out.println("Email is: "+email+"\t"+"Password is: "+password);
		
		regpage.checkPrivacyPolicy();
		regpage.continueButton();
		
		logger.info("Validating expected message........");
		Assert.assertEquals(regpage.getConfirmationMsg(),"Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.error("Test Failed...");
			logger.debug("Debug logs....");
			Assert.fail();
		}
		
		logger.info("****Finished TC001_AccountRegistrationTest******");
		
	}
	
	
	
}

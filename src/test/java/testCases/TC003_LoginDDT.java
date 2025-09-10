package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utitlities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="Datadriven") // getting data provider from different
																				// class
	public void verify_loginDDT(String email, String password, String result) {

		logger.info("***Starting Login Data Driven Test***");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmailAddress(email);
			lp.setPassword(password);
			lp.loginClick();

			MyAccountPage myacc = new MyAccountPage(driver);
			Boolean targetpage = myacc.isMyAccountPageExists();
			Assert.assertTrue(targetpage);

			if (result.equalsIgnoreCase("Valid")) {
				if (targetpage == true) {
					myacc.clickLogout();
					Assert.assertTrue(true);

				} else {
					Assert.assertTrue(false);
				}
			}
			if (result.equalsIgnoreCase("Invalid")) {
				if (targetpage == true) {
					myacc.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("***Finished Login Data Driven Test***");
	}
}

package stepdefinitions;

import org.testng.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.controller.Driver;
import framework.utils.WebDriverUtil;
import pagefactory.HomePage;

public class Home {

	HomePage homepage;

	@When("I Launch home page")
	public void launchHomePage() {
		WebDriverUtil.launchURL();
	}

	@Then("I should see home page logo")
	public void seeHomePageLogo() {
		homepage = new HomePage(Driver.getWebDriver());
		Assert.assertTrue(homepage.logo.isDisplayed(), "Google logo is not exist");
	}

}

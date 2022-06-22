package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import baseclass.BaseClass;

public class AirIndiaPage extends BaseClass {
	public AirIndiaPage() {
		PageFactory.initElements(getWebDriver(), this);
	}

	@FindBy(xpath = "//h1[contains(text(),'Air India')]")
	public WebElement airIndiaPageHeader;

	public void verifyAirIndiaPageHeader(String expectedHeader) {
		String actualHeader = airIndiaPageHeader.getText();
		//System.out.println(actualHeader);
		Assert.assertEquals(expectedHeader.trim(), actualHeader.trim(), "Page header mismatch");
	}

}

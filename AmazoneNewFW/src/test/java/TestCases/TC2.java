package TestCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import baseclass.BaseClass;
import pages.AirIndiaPage;
import pages.HomePage;
import utilities.ExcelUtil;

public class TC2 extends BaseClass {
	HomePage homePage;
	AirIndiaPage  airIndiaPage;
	@BeforeClass
	public void setup() throws IOException {
		browserInitialization();
		homePage=new HomePage();
		airIndiaPage=new AirIndiaPage();
		ExcelUtil.setExcelFIle();
	}
	@Test
	public void validateNavigationToAirIndiaPage() {
		String parentWindow=homePage.clickOnAirIndiaLink();
		System.out.println("Parent window = "+parentWindow);
		switchToChildWindow();
		airIndiaPage.verifyAirIndiaPageHeader(ExcelUtil.getCellData(2, 7));
		getWebDriver().close();
		switchToParentWindow(parentWindow);
	}
	
	
}

package TestCases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import baseclass.BaseClass;
import pages.HomePage;
import utilities.ExcelUtil;

public class TC1 extends BaseClass {
	HomePage homePage;

	@BeforeClass
	public void setup() throws IOException {
		browserInitialization();
		homePage = new HomePage();
		ExcelUtil.setExcelFIle();
	}

	@Test
	public void userSelectTravelType() {
		homePage.clickOnOneWayBtn();
	}

	@Test(dependsOnMethods = "userSelectTravelType")
	public void userSelectTravelDetails() {
		homePage.enterFromLocation(ExcelUtil.getCellData(1, 1));
		homePage.enterToLocation(ExcelUtil.getCellData(1, 2));
		homePage.clickOnStartDate();
		homePage.enterStartDate(ExcelUtil.getCellData(1, 3));
		homePage.selectAdultCount(ExcelUtil.getCellData(1, 5));
		homePage.clickOnFindFlightsBtn();
	}

}

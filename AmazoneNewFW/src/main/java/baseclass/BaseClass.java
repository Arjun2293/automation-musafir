package baseclass;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
	/// <summary>
	/// To Start Browser
	/// </summary>
	public void browserInitialization() throws IOException {
		WebDriverManager.chromedriver().setup();
		setWebDriver(new ChromeDriver());
		getWebDriver();
		getWebDriver().manage().window().maximize();
		String url = readFromProperty("url");
		getWebDriver().get(url);
	}

	protected static void setWebDriver(RemoteWebDriver driverValue) {
		driver.set(driverValue);
	}

	public RemoteWebDriver getWebDriver() {
		return driver.get();
	}

	public String readFromProperty(String property) throws IOException {
		Properties prop =new Properties();
		String filePath = System.getProperty("user.dir") + "\\TestData\\globalProperties.property";
		FileInputStream inputStream = new FileInputStream(filePath);
		prop.load(inputStream);
		String value=prop.getProperty(property);
		return value;
	}

	/// <summary>
	/// To insert data into text box
	/// </summary>
	public void insertTextData(WebElement element, String dataToInsert) {
		element.clear();
		element.sendKeys(dataToInsert);
	}

	/// <summary>
	/// To select value from dropdown
	/// </summary>
	public void selectDdByValue(WebElement element, String value) {
		Select dropDown = new Select(element);
		dropDown.selectByValue(value);
	}

	/// <summary>
	/// To switch to ChildWindow
	/// </summary>
	public void switchToChildWindow() {
		for (String window : getWebDriver().getWindowHandles()) {
			getWebDriver().switchTo().window(window);
		}
	}

	/// <summary>
	/// To switch to ParentWindow
	/// </summary>
	public void switchToParentWindow(String parent) {
		getWebDriver().switchTo().window(parent);
	}

	/// <summary>
	/// To get screenshot
	/// </summary>
	public String takeScreenShot(String stepName) throws IOException {
		Date date = new Date();
		String timeStamp = stepName + date.getTime();
		String filePath = System.getProperty("user.dir") + "\\screenshots\\" + timeStamp + ".png";
		TakesScreenshot srcShot = ((TakesScreenshot) getWebDriver());
		File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(filePath);
		FileUtils.copyFile(srcFile, destFile);
		return filePath;
	}

	/// <summary>
	/// To close browser after each test execution
	/// </summary>

	@AfterClass
	public void closeBrowser() {
		getWebDriver().close();
	}

}

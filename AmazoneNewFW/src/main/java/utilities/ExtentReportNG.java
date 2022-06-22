package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	static ExtentReports extent;
	
	public static ExtentReports getReport() {
	String path=System.getProperty("user.dir")+"\\reports\\index.html";
	ExtentSparkReporter spark = new ExtentSparkReporter(path);
	spark.config().setReportName("Musafir Web Automation");
	spark.config().setDocumentTitle("Test Result");
	extent=new ExtentReports();
	extent.attachReporter(spark);
	extent.setSystemInfo("Tester", "Arjun");
	return extent;
	}
}

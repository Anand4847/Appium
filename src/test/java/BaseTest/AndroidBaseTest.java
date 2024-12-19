package BaseTest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Utils.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AndroidBaseTest extends AppiumUtils{

	public static ExtentReports extent ;


	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	
@BeforeTest(alwaysRun = true)
	
	public static ExtentReports config()
	{
		
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");;
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	    extent.setSystemInfo("Tester", "Anand Soni");
		return extent;
	    
	 }



	@BeforeMethod(alwaysRun = true)

	public void ConfigureAppium() throws Exception {

		
		Properties prop = new Properties();
		FileInputStream fis  = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\config.properties");
		prop.load(fis);
		String ipAddress = prop.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		String url = prop.getProperty("ipAddress"+"/:"+"port");
		
		service = startAppiumServer(ipAddress, Integer.parseInt(port));
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("AndroidDevice"));
//		options.setApp("E:\\Eclipse Workspace 2\\Projects\\RahulShetty_Appium_Dec24\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		options.setChromedriverExecutable("D:\\Learnings\\Rahul Shetty Appium\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		options.setApp(
				System.getProperty("user.dir")+"\\src\\test\\java\\resources\\General-Store.apk");

		driver = new AndroidDriver((service.getUrl()), options);
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	
	
	
	
	@AfterClass(alwaysRun = true)
	public void teardown() {

		 if (driver != null) {
		        System.out.println("Driver is closing down");
		        driver.quit();
		        System.out.println("Driver is closed");
		    } else {
		        System.out.println("Driver was not initialized");
		    }		


//		service.stop();
		
	}
}

package Utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public abstract class AppiumUtils {
	AppiumDriverLocalService service;

	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws Exception, IOException

	{

		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public void waitForElementToAppear(WebElement ele, AppiumUtils driver) {

		WebDriverWait wait = new WebDriverWait((WebDriver) driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));

	}

	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port)

	{

		service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\admin\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(ipAddress).usingPort(port).build();
		return service;
//		service.start();

	}

	
	
	public String getScreenshot(String testCaseName, AppiumDriver driver) throws Exception
	
	{
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"//reports"+testCaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
		
	}
}

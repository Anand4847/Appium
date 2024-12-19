package AllTest;
import org.openqa.selenium.By;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import BaseTest.AndroidBaseTest;
import PageObjects.CartPage;
import io.appium.java_client.android.AndroidDriver;

public class SecondTest extends AndroidBaseTest {


	@Test

	public void ToastMessage()

	{
//	driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("AnandSoni");
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();

		CartPage cp = new CartPage(driver);
		cp.ScrollToCountry("Brazil");

		driver.findElement(By.xpath("//android.widget.TextView[@text='Brazil']")).click();
		driver.findElement(
				By.xpath("//android.widget.Button[@resource-id='com.androidsample.generalstore:id/btnLetsShop']"))
				.click();
		String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");

		AssertJUnit.assertEquals(toastMessage, "Please enter your name");

	}

	@Test(dependsOnMethods = "ToastMessage")

	public void PositiveFlow()

	{
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("AnandSoni");
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();

		CartPage cp = new CartPage(driver);
		cp.ScrollToCountry("Brazil");

		driver.findElement(By.xpath("//android.widget.TextView[@text='Brazil']")).click();
		driver.findElement(
				By.xpath("//android.widget.Button[@resource-id='com.androidsample.generalstore:id/btnLetsShop']"))
				.click();
		AssertJUnit.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size() < 2);

	}
}

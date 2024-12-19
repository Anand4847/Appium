package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions {

	AndroidDriver driver;

	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
	private WebElement femaleOption;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
	private WebElement maleOption;

	@AndroidFindBy(id = "android:id/text1")
	private WebElement dropdownClick;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Brazil']")
	private WebElement selectCountry;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.androidsample.generalstore:id/btnLetsShop']")
	private WebElement shopBtn;

	public void submitform() {

		shopBtn.click();
	}

	public void SetCountry(String country)

	{

		dropdownClick.click();

		ScrollToCountry(country);

		driver.findElement(By.xpath("//android.widget.TextView[@text='" + country + "']")).click();
	
	}

	public void SetGender(String gender)

	{
		if (gender.equalsIgnoreCase("Male"))

		{
			maleOption.click();
		}

		if (gender.equalsIgnoreCase("Female"))

		{
			femaleOption.click();
		}

	}

	public void SetnameField(String name)

	{
		nameField.sendKeys(name);
		driver.hideKeyboard();

	}
}

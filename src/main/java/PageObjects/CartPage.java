package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {

	AndroidDriver driver;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addtoCart;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productprices;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement CartBtn;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalamt;

	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement checkbox;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement closeBtn;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement submitBtn;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement termsandcond;
	
	
	

	public WebElement TotalAmount()
	
	{
		return totalamt;

	}
	
	
	public void CheckBox()
	
	{
		checkbox.click();

	}

	public void TermsClick()

	{
		LongPressAction(termsandcond);
		closeBtn.click();

	}

	public void SubmitOrder()

	{
		submitBtn.click();

	}

	
	public void AddingtoCart(int index)

	{
		addtoCart.get(index).click();
		addtoCart.get(index).click();

	}

	public List<WebElement> ProductPrice()

	{

		return productprices;
	}

	public double TotalAmtDisplay()

	{
		return getFormattedAmount(totalamt.getText());
	}

	public Double getFormattedAmount(String amount)

	{

		Double totalvalue = Double.parseDouble(amount.substring(1));
		return totalvalue;
	}

	public double getProductSum()

	{

		int count = productprices.size();
		double producttotal = 0;

		for (int i = 0; i < count; i++)

		{

			String amountString = productprices.get(i).getText();

			String prices = amountString.substring(1);

			Double price = Double.parseDouble(prices);

			producttotal = producttotal + price;

		}

		return producttotal;
	}
	
	
	
	


}


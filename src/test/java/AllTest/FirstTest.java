package AllTest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import BaseTest.AndroidBaseTest;
import PageObjects.CartPage;
import PageObjects.FormPage;
import PageObjects.ProductCatalogue;

public class FirstTest extends AndroidBaseTest {

	
	
	
	
	@Test(dataProvider="getData")

	public void firsttesting(HashMap<String, String> input) throws Exception

	{

		ExtentTest test = extent.createTest("First Test");
		FormPage fp = new FormPage(driver);

		fp.SetnameField(input.get("name"));

		fp.SetGender(input.get("gender"));

		fp.SetCountry(input.get("country"));

		fp.submitform();

		ProductCatalogue pc = new ProductCatalogue(driver);
		pc.AddingtoCart(0);

		pc.CartBtnClick();
		CartPage cp = new CartPage(driver);

	
		
		double totalSum = cp.getProductSum();

		double displayformattedsum = cp.TotalAmtDisplay();

		AssertJUnit.assertEquals(totalSum, "10");

		cp.ProductPrice();

		cp.CheckBox();
		cp.TermsClick();

		cp.SubmitOrder();
		test.fail("Results do not match");
		
		
		
		
	}
	@Test(dataProvider="getData", groups={"Smoke"})

	public void Secondtesting(HashMap<String, String> input) throws Exception

	{

		ExtentTest test = extent.createTest("First Test");
		FormPage fp = new FormPage(driver);

		fp.SetnameField(input.get("name"));



		fp.SetGender(input.get("gender"));

		fp.SetCountry(input.get("country"));

		fp.submitform();

		ProductCatalogue pc = new ProductCatalogue(driver);
		pc.AddingtoCart(0);

		pc.CartBtnClick();
		CartPage cp = new CartPage(driver);

	
		
		double totalSum = cp.getProductSum();

		double displayformattedsum = cp.TotalAmtDisplay();

		AssertJUnit.assertEquals(totalSum, displayformattedsum);

		cp.ProductPrice();

		cp.CheckBox();
		cp.TermsClick();

		cp.SubmitOrder();
		
		
		
		
		
	}
	
	@DataProvider
	
	public Object[][] getData() throws IOException, Exception
	
	{
		
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\testData\\ecommerce.json");
		return new Object[][] {{data.get(0)}};
		
	}

	
}

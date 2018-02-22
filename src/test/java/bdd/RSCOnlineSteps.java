package bdd;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import jxl.read.biff.BiffException;
import pom.BasketSummary;
import pom.HomePage;
import pom.Products;
import pom.TestUtility;


public class RSCOnlineSteps {
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static WebDriver driver;
	String path = System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties";
	
	@Before
	public void tearshutup() {
		log.info("Set up");
	}
	
	@After
	public void tearshutdown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	@Given("I login into RS online website home page")
	public void I_login_into_RS_online_website_home_page() throws IOException {
		log.info("I login into RS online website home page");
		
		Properties config = new Properties();
		FileInputStream fis = new FileInputStream(path);
		config.load(fis);
		
		if (config.getProperty("browser").equals("firefox")){
			driver = new FirefoxDriver();
		} else if (config.getProperty("browser").equals("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +  "\\src\\test\\resources\\executable\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (config.getProperty("browser").equals("ie")){
			// Implementation pending
		}
		
		driver.get(config.getProperty("websiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getProperty("implicit.wait")), TimeUnit.SECONDS);

		HomePage home = new HomePage();
		home.selectHomePage(driver);
		
		TestUtility.captureScreenshot(driver);
	}
	
	@And("I ensure my basket is empty")
	public void I_ensure_my_basket_is_empty() throws IOException {
		log.info("I ensure my basket is empty");
		
		// Here I#m making basket empty as we are not doing purchase
		// Also we are adding 1 item at a time. So remove is added earlier item
		BasketSummary basket = new BasketSummary();
				
		try {
			basket.emptyBasket(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TestUtility.captureScreenshot(driver);
		}
	}
	
	@When("^I search '(.*?)' and added 1 item$")
	public void I_search_Products_and_added_1_item(String searchString) throws IOException {
		log.info("I search " + searchString + " and added 1 item");
				
		if (searchString.contains("All Products")) {
			Products product = new Products();
			product.purhaseBattery(driver);
		}
		else if (searchString.contains("Our Brands")){
			Products product = new Products();
			product.purchaseBrand(driver);
		}	
	}
	
	@Then("I verify 1 item get added in basket")
	public void I_verify_1_item_get_added_in_basket() throws IOException {
		log.info("I verify 1 item get added in basket");
		
		Products product = new Products();
		if (product.viewBasketAppear(driver))
		{
			System.out.println("Item added successfully");
			log.debug("Item added successfully");
		}	
	}
	
	@When("^I search product by '(.*?)' provided in excel sheet$")
	public void I_search_Products(String searchString) throws IOException, BiffException {
		log.info("I search product by " + searchString);
				
		Products product = new Products();
		product.serachProduct(driver, searchString);
	}
	
	@Then("I check the product exists or not")
	public void I_check_the_product_exists_or_not() throws IOException {
		log.info("I check the product exists or not");
	}
	
}

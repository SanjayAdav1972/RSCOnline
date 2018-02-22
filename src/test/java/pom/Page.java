package pom;

import org.openqa.selenium.WebDriver;

// Base class for all pages
public class Page {
	public WebDriver driver;
	
	// Path of Object repository properties file
	String path = System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.Properties";
	// D:\Projects\RSCOnline\src\test\resources\properties\OR.properties
	
	// Path of Object config properties file
	String patConfigh = System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.Properties";

}
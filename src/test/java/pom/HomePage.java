package pom;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {
			
	/*
	 * Author: 			Sanjay Adav
	 * Method: 			selectHomePage
	 * Parameter(s):	WebDriver
	 * Return type:		No 
	 * 
	 */
	public void selectHomePage(WebDriver driver) throws IOException {
		this.driver = driver;
		
		Properties config = new Properties();
		FileInputStream fis = new FileInputStream(path);
		config.load(fis);
		
		this.driver.findElement(By.xpath(config.getProperty("homeLink"))).click();
	}

}
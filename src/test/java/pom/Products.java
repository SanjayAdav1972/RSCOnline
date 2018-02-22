package pom;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jxl.read.biff.BiffException;

public class Products extends Page {
	
	/*
	 * Author: 			Sanjay Adav
	 * Method: 			purhaseBattery
	 * Parameter(s):	WebDriver
	 * Return type:		No 
	 * 
	 */
	public void purhaseBattery(WebDriver driver) throws IOException {
		this.driver = driver;
		
		Properties config = new Properties();
		FileInputStream fis = new FileInputStream(path);
		config.load(fis);
		
		this.driver.findElement(By.xpath(config.getProperty("menuAllProducts"))).click();
		
		WebElement battery = this.driver.findElement(By.xpath(config.getProperty("lnkBatteries")));
		WebElement charger = this.driver.findElement(By.xpath(config.getProperty("lnkBatCharger")));
		
		Actions action = new Actions(driver);
		action.moveToElement(battery).click(charger).build().perform();
		
		this.driver.findElement(By.xpath(config.getProperty("Category1"))).click();
		this.driver.findElement(By.xpath(config.getProperty("addBtn1"))).click();
	}
	
	/*
	 * Author: 			Sanjay Adav
	 * Method: 			purchaseBrand
	 * Parameter(s):	WebDriver
	 * Return type:		No 
	 * 
	 */
	public void purchaseBrand(WebDriver driver) throws IOException{
		this.driver = driver;
		
		Properties config = new Properties();
		FileInputStream fis = new FileInputStream(path);
		config.load(fis);
		
		this.driver.findElement(By.xpath(config.getProperty("menyOurBrands"))).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		// Wait till List of brand appears and select ABB brand
		WebElement brandABB = this.driver.findElement(By.xpath(config.getProperty("brandABB")));
		wait.until(ExpectedConditions.elementToBeClickable(brandABB)).click();
			
		// Wait till list of categories appear and select 1st category
		WebElement elementCat1 = this.driver.findElement(By.xpath(config.getProperty("brandCat1")));
		wait.until(ExpectedConditions.elementToBeClickable(elementCat1)).click();
		
		this.driver.findElement(By.xpath(config.getProperty("addBtn1"))).click();
	}
	
	/*
	 * Author: 			Sanjay Adav
	 * Method: 			viewBasketAppear
	 * Parameter(s):	WebDriver
	 * Return type:		No 
	 * 
	 */
	public boolean viewBasketAppear(WebDriver driver) throws IOException {
		boolean basketAppear = false;
		
		this.driver = driver;
		
		Properties config = new Properties();
		FileInputStream fis = new FileInputStream(path);
		config.load(fis);
		
		if (this.driver.findElement(By.xpath(config.getProperty("addToBasketMessage"))).isDisplayed()) {
			basketAppear = true;
		}
		
		return basketAppear;
	}
	
	/*
	 * Author: 			Sanjay Adav
	 * Method: 			serachProduct
	 * Parameter(s):	WebDriver
	 * Return type:		No 
	 * 
	 */
	public void serachProduct(WebDriver driver, String searchString) throws IOException, BiffException {
		this.driver = driver;
		
		Properties config = new Properties();
		FileInputStream fis = new FileInputStream(patConfigh);
		config.load(fis);
		
		Properties orConfig = new Properties();
		FileInputStream fisOR = new FileInputStream(path);
		orConfig.load(fisOR);
		
		String prodFile = config.getProperty("ProductSearchFile");
		// System.out.println(vrFile);
		String sheetName = config.getProperty("sheetName");
		// System.out.println(sheetName);
		ExcelReader DT = new ExcelReader();
		
		String searchItem = "";
		
		if (searchString.equals("Keyword")) {
			// battery chargers
			searchItem = DT.returnCellData(prodFile, sheetName, 1, 0);
		}
		else if (searchString.equals("PartNo")){
			// MIKROE-1198
			searchItem = DT.returnCellData(prodFile, sheetName, 1, 1);
		}	
		else if (searchString.equals("RSStockNo")){
			// 882-8904
			searchItem = DT.returnCellData(prodFile, sheetName, 1, 2);
		}
		
		System.out.println(searchItem);
		this.driver.findElement(By.xpath(orConfig.getProperty("inputSearch"))).sendKeys(searchItem);
		this.driver.findElement(By.xpath(orConfig.getProperty("inputSearch"))).submit();
		//this.driver.findElement(By.xpath(orConfig.getProperty("btnSearch"))).click();
		
		if (searchString.equals("PartNo") || searchString.equals("RSStockNo")) {
			int i = this.driver.findElements(By.xpath(orConfig.getProperty("prodDetails"))).size();
			if (i > 0) 	{
				System.out.println("Product exists!");
			}
			else {
				System.out.println("No matching Product exists!");
			}
		}
		else {
			int i = this.driver.findElements(By.xpath(orConfig.getProperty("resultSearch"))).size();
			if (i > 0) 	{
				System.out.println("Product exists!");
			}
			else {
				System.out.println("No matching Product exists!");
			}
		}
		
	}

}
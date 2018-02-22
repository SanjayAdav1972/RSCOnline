package pom;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestUtility extends Page {
	
	public static String fileName;

	/*
	 * Author: 			Sanjay Adav
	 * Method: 			captureScreenshot
	 * Parameter(s):	WebDriver
	 * Return type:		No 
	 * 
	 */
	public static void captureScreenshot(WebDriver driver) throws IOException {

		Date d = new Date();
		fileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scr,
				new File(System.getProperty("user.dir") + "\\screenshots\\" + fileName));
		FileUtils.copyFile(scr, new File(System.getProperty("user.dir") + "\\screenshots\\" + fileName));

	}

}
package com.automation.bms;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import com.automation.library.ApplicationVariables;

public class IEBasicAuthTest {
	public static WebDriver driver;
	@Test
	public void basicAuth() {
		try {
			System.setProperty("webdriver.ie.driver", ApplicationVariables.webDrivePathIE);
			WebDriver driver=new InternetExplorerDriver(); 
			driver.manage().window().maximize();
			System.out.println("Done");
			Runtime.getRuntime().exec(ApplicationVariables.basicAuthFile);
			driver.get("https://bristolmyerssquibb-qa.adobecqms.net/about-us/contact-us.automation.html");
			Thread.sleep(5000);
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source =ts.getScreenshotAs(OutputType.FILE);
			String dest = System.getProperty("user.dir")+"\\drivers\\test.png";

			File destination = new File (dest);
			FileUtils.copyFile(source,destination );

			File file = new File (dest);
			if (!file.exists()) {
				file.mkdirs();
			}
			System.out.println("Complete");

			List<WebElement> list=driver.findElements(By.tagName("a"));
			System.out.println(list.size());

			for(WebElement link : list) {
				try {
					System.out.println(link.getText());
				} catch (Exception e) {
					continue;
				}
			}

			driver.close();
		}catch (Exception e) {
			e.printStackTrace();

		}
	}
}

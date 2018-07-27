package com.automation.bms;

import org.openqa.selenium.WebDriver;
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
			System.out.println("Complete");
		}catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}
package com.automation.bms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.library.Utility;

public class testFilter {
	public static WebDriver driver;
	public static void main(String[] args) {

		driver=Utility.initializeDriver();

		driver.get("https://www.bms.com/researchers-and-partners/in-the-pipeline.html");

		Utility.waitForpageToLoad(driver);

		validateFilter("M");

	}
	public static boolean validateFilter(String filter) {
		boolean flag=true;
		try {
			List<WebElement> filterList=driver.findElements(By.xpath("//div[@class='pipeline-listing-container']//div[@class='phase-listing']"));
			for(WebElement list : filterList) {
				if(list.getText().contains(filter)) {
					return false;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}


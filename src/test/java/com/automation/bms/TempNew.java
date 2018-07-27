package com.automation.bms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;



public class TempNew {
  	WebDriver driver = null;
	/**
	 * This method will apply Arrow mark up and delete the same 
	 * @throws Exception
	 */
  	@Test
	public void applyArrowMarkup() throws Exception{
  		System.setProperty("webdriver.chrome.driver", System.getenv("Automation_Home")+"\\Drivers\\chromedriver.exe");
  		driver = new ChromeDriver();
  		driver.get("https://www.bms.com/researchers-and-partners/in-the-pipeline.html");
  		driver.findElement(By.xpath("/html/body/div[3]/div/main/div/div[6]/div[1]/div/div[1]/button[1]")).click();
  		applyModality("modalityall");	
  		Thread.sleep(1000);
  		Assert.assertFalse(driver.findElement(By.xpath("//button[@class='primary-button apply']")).isEnabled(), "Apply icon is disabled.");
  		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='apply-button-error-message']")).isDisplayed());
  		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='apply-button-error-message']")).getText(),"Please select at-least one item in each category.");
	}


  	/*private void applyPhase(String value, String check) {
 		if("phase".equals(value))
 			driver.findElement(By.xpath("//label[@for='phase-all']")).click();
 		else if("2-phaseii".equals(value))
 			driver.findElement(By.xpath("//label[@for='2-phaseii']")).click();
 		else if("1-phasei".equals(value))
 			driver.findElement(By.xpath("//label[@for='1-phasei']")).click();
 		else if("3-phaseiii".equals(value))
 			driver.findElement(By.xpath("//label[@for='3-phaseiii']")).click();
 		else if("m-marketedproductdevelopment".equals(value))
 			driver.findElement(By.xpath("//label[@for='m-marketedproductdevelopment']")).click();
	}*/

  	
  	
  	

 	private void applyModality(String value) {
 		if("modalityall".equals(value))
 			driver.findElement(By.xpath("//label[@for='modality-all']")).click();
 		else if("smallMolecule".equals(value)){
 			
 			driver.findElement(By.xpath("//label[@for='smallmolecule']")).click();
 		}
 		else if("biologic".equals(value))
 			driver.findElement(By.xpath("//label[@for='biologic']")).click();
	}



	public void applySortOrder(String value) {
 		if("ascending".equals(value))
 			driver.findElement(By.xpath("//input[@value='asc']")).click();
 		else if("decending".equals(value))
 			driver.findElement(By.xpath("//input[@value='desc']")).click();
	}

	public void applySortBy(String value){
 		if("diseasearea".equals(value))
 			driver.findElement(By.xpath("//input[@value='diseasearea']")).click();
 		else if("compound".equals(value))
 			driver.findElement(By.xpath("//input[@value='compoundname']")).click();
 		else if("GenericName".equals(value))
			driver.findElement(By.xpath("//input[@value='genericname']")).click();
 		else if("BrandName".equals(value))
			driver.findElement(By.xpath("//input[@value='brandname']")).click();
 		else if("Phase".equals(value))
			driver.findElement(By.xpath("//input[@value='phase']")).click();
 		else if("Modality/compound".equals(value))
			driver.findElement(By.xpath("//input[@value='modality']")).click(); 		
  	}
}
 	

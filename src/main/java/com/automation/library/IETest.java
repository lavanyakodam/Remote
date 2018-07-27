package com.automation.library;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IETest {
	public static WebDriver driver;
	public static void main(String[] args) throws InterruptedException, AWTException, IOException {
		
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();   
		caps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "https://test-abovems.biogen-support.com");

		System.setProperty("webdriver.ie.driver", ApplicationVariables.webDrivePathIE);
        WebDriver driver=new InternetExplorerDriver(caps); 
		driver.manage().window().maximize();
		//driver.get("https://test-abovems.biogen-support.com");
		try {
			//Thread.sleep(2000);
			Runtime.getRuntime().exec("C:\\Users\\srinivas.gurrala\\Desktop\\basicauth5.exe");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Done");
		
		driver.navigate().refresh();
		
		
		System.out.println("Complete");
		
		/*DesiredCapabilities capability = new DesiredCapabilities();
		capability.setBrowserName("ie");
		capability.setCapability("ignoreProtectedModeSettings", "true");
		capability.setCapability("unexpectedAlertBehaviour", "accept");
		
		System.setProperty("webdriver.ie.driver", ApplicationVariables.webDrivePathIE);
		driver= new InternetExplorerDriver(capability);
		driver.manage().window().maximize();

		driver.get("https://test-abovems.biogen-support.com");

		//driver.get("https://abovems:JOn2A46jbws4kolLE94b%40test-abovems.biogen-support.com");

		//driver.manage().timeouts().implicitlyWait(ApplicationVariables.SynchronizationTime, TimeUnit.SECONDS);
		System.out.println("Done");
		Thread.sleep(2000);

		Runtime.getRuntime().exec("C:\\Users\\srinivas.gurrala\\Desktop\\basicauth.exe");
		
		Set<String> availableWindows = driver.getWindowHandles();
		System.out.println("Before : "+availableWindows.size());
		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				if (driver.switchTo().window(windowId).getTitle().equals("DocCheck Login")) {
					break;
				}
			}
		}
		System.out.println("======================");
		availableWindows = driver.getWindowHandles();
		System.out.println("After : "+availableWindows.size());
		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				if (driver.switchTo().window(windowId).getTitle().equals("DocCheck Login")) {
					break;
				}
			}
		}*/
		/*System.out.println("Open");
		UserAndPassword UP = new UserAndPassword("userName","Password");
		driver.switchTo().alert().authenticateUsing(UP);

		System.out.println("Text : "+driver.switchTo().alert().getText());*/

		/*Robot rb = new Robot();

		// Enter user name in username field 
		StringSelection username = new StringSelection("User name");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(username, null);            
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);

		// press tab to move to password field
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);

		//Enter password in password field
		StringSelection pwd = new StringSelection("Password");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pwd, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);

		//press enter
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);*/
	}

}

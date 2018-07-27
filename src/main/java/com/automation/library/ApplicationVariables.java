package com.automation.library;
public class ApplicationVariables {
	public static String argument_Browser = System.getProperty("Browser");
	public static String argument_Url = System.getProperty("Environment");
	
	public static String webDrivePathChrome = System.getProperty("user.dir")+"\\drivers\\chromedriver.exe";
	public static String webDrivePathIE =     System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe";
	public static String webDrivePathGecko=   System.getProperty("user.dir")+"\\drivers\\geckodriver.exe";
	public static String basicAuthFile=   System.getProperty("user.dir")+"\\drivers\\BasicAuth.exe";
	public static long   SynchronizationTime = 90;
	public static String default_Browser    = "chrome";
	
	public static String default_Stg_Url    = "https://www.bms-staging.com";
}
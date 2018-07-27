package com.automation.library;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Logger
{
	private static String logFilePath = null;
	private static BufferedWriter out = null;
	private static String logFileName = null;
	public static LinkedHashMap<String, String> htmlResultMap=new LinkedHashMap<String, String>();

	public static void initializeLogger(String filePath, String fileName) {
		try {
			System.out.println("Results Initialized");
			logFilePath = filePath;
			logFileName = fileName;
			File newFile = new File(logFilePath + logFileName);
			newFile.createNewFile();
			newFile.setWritable(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String GetLogFilePath(){
		return logFilePath + logFileName;
	}

	public static void logTestInfo(String text) {	
		try {
			Date currentTimeStamp = new Date();
			SimpleDateFormat format = new SimpleDateFormat("MMM dd yyyy, HH:mm:ss");
			out = new BufferedWriter(new FileWriter(logFilePath + logFileName, true));
			out.write("\r\n" + "[" + format.format(currentTimeStamp) + "]" + "[INFO]: ");	
			out.write(text);		
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void logTestStatus(String tcId,String expectedValue,String actualValue,String Status){	
		try{
			
			System.out.println(tcId+"-"+expectedValue+"-"+actualValue+"-"+Status);
			String screenShotPath=logFilePath+"\\screenshots\\";
			File file = new File (screenShotPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			WebDriver driver=Utility.getDriver();

			/*TakesScreenshot ts = (TakesScreenshot)driver;
			File src= ts.getScreenshotAs(OutputType.FILE);
			String screenshotname=tcId+"~"+actualValue+".png";
			FileUtils.copyFile(src, new File(screenShotPath+screenshotname));*/
			String screenshotname=tcId+"~"+actualValue+".png";
			String pagePathName= screenShotPath+screenshotname;
			Screenshot footer = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500)).takeScreenshot(driver);
			File resFile=new File(pagePathName);

			if(!resFile.exists()){
				resFile.mkdirs();
			}
			ImageIO.write(footer.getImage(), "PNG", resFile);

			htmlResultMap.put(tcId+"~"+expectedValue, expectedValue+"~"+actualValue+"~"+Status);
			
			Date currentTimeStamp = new Date();
			SimpleDateFormat format = new SimpleDateFormat("MMM dd yyyy, HH:mm:ss");

			out = new BufferedWriter(new FileWriter(logFilePath + logFileName, true));
			out.write("\r\n" + "[" + format.format(currentTimeStamp) + "]" + "[" + Status.toString() + "]: ");	
			out.write(tcId+"-"+expectedValue+"-"+actualValue+"-"+Status);		
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
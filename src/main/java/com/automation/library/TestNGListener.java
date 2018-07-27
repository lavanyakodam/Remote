package com.automation.library;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
	public static SimpleDateFormat sdfDate;
	public static String strDate;
	public static String endDate;
	public static Date now;
	public static String totalTime;

	public void onStart(ITestContext context) {
		try{
			sdfDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			now = new Date();
			strDate = sdfDate.format(now);

			Utility.initializeReport();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext result) {
		try {

			Date later = new Date();
			endDate = sdfDate.format(later);
			Date d1 = null,d2 = null;

			d1 = sdfDate.parse(strDate);
			d2 = sdfDate.parse(endDate);

			long diff = d2.getTime() - d1.getTime();
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			totalTime=diffHours + " Hours, "+diffMinutes + " minutes, "+diffSeconds + " seconds.";

			Utility.tearDown();

			System.out.println("**************************************************************************************************************");
			System.out.println("Execution Completed At : "+endDate);
			System.out.println("Execution Time :: "+diffHours + " Hours, "+diffMinutes + " minutes, "+diffSeconds + " seconds.");
			System.out.println("**************************************************************************************************************");

			Logger.logTestInfo("**************************************************************************************************************");
			Logger.logTestInfo("Execution Completed At : "+endDate);
			Logger.logTestInfo("Execution Time :: "+diffHours + " Hours, "+diffMinutes + " minutes, "+diffSeconds + " seconds.");
			Logger.logTestInfo("**************************************************************************************************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestStart(ITestResult result) {
		System.out.println("Testcase "+ result.getName() + " Started");
		Logger.logTestInfo("-------------------------------------------------------------------------------------------------------------");
		Logger.logTestInfo("Testcase "+ result.getName() + " Started");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Testcase "+ result.getName() + " Success");
		Logger.logTestInfo("-------------------------------------------------------------------------------------------------------------");
		Logger.logTestInfo("Testcase "+ result.getName() + " Completed");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Testcase "+ result.getName()+ " Failed");
		Logger.logTestInfo("-------------------------------------------------------------------------------------------------------------");
		Logger.logTestInfo("Testcase "+ result.getName() + " Failed");
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Testcase "+ result.getName()+ " Skipped");
		Logger.logTestInfo("-------------------------------------------------------------------------------------------------------------");
		Logger.logTestInfo("Testcase "+ result.getName() + " Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}
}
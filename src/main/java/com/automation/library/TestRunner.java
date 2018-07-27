package com.automation.library;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.TestNG;

public class TestRunner {

	public static void main(String[] args){
		try{
			LinkedHashMap<String,String>testData = new LinkedHashMap<String,String>();
			ExcelFileReader excel=new ExcelFileReader();
			excel.prepareTestDataSheet(System.getProperty("user.dir")+"\\testdata\\BMS_Automation.xlsx", "Configuration");
			int noOfTC=excel.getTotalNoOfRows()-1;;
			System.out.println("Total No Of Test Cases : "+noOfTC);
			for(int i=1;i<excel.getTotalNoOfRows();i++){
				if(excel.getValueBy(i, "Test Run").equalsIgnoreCase("Yes")){
					//String tcId=excel.getValueBy(i, "TCID");
					String className=excel.getValueBy(i, "Class");
					String methodName=excel.getValueBy(i, "Method");

					testData.put(className+"~"+methodName, className+"~"+methodName);
				}
			}

			String filePath=System.getProperty("user.dir")+"\\";
			String fileName="testng.xml";
			generateXML(filePath, fileName,testData);
			Thread.sleep(2000);
			//TriggerTestNGxml(filePath, fileName);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void TriggerTestNGxml(String xmlPath,String xmlName) {
		System.out.println("***Test Runner Started***");
		TestNG runner = new TestNG();
		List<String> suitefiles = new ArrayList<String>();
		String path1 = xmlPath+xmlName;
		suitefiles.add(path1);
		runner.setTestSuites(suitefiles);
		runner.run();
		System.out.println("***Test Runner End***");
	}

	public static void generateXML(String xmlPath,String xmlName,LinkedHashMap<String,String> tests){
		try {
			System.out.println("Test NG XML Generation Completed");

			StringBuilder htmlStringBuilder=new StringBuilder();
			htmlStringBuilder.append("<?xml version='1.0' encoding='UTF-8'?>\n");
			htmlStringBuilder.append("<!DOCTYPE suite SYSTEM 'http://testng.org/testng-1.0.dtd'>\n");
			htmlStringBuilder.append("<suite name='Suite' thread-count='3' parallel='tests'>\n");
			htmlStringBuilder.append("<listeners><listener class-name='com.automation.library.TestNGListener' /></listeners>\n");
			htmlStringBuilder.append("<test name='ChromeTest' parallel='false' preserve-order='true'>\n");
			htmlStringBuilder.append("<classes>\n");

			String beforeClass1="";

			for(String key : tests.keySet()){
				String className=key.split("~")[0];

				if(!beforeClass1.equalsIgnoreCase(className)){
				htmlStringBuilder.append("\t <class name =\"com.automation.bms."+className+"\"><methods>\n");

				for(String key1 : tests.keySet()){
					String className1=key1.split("~")[0];
					String methodName1=key1.split("~")[1];
					if(className1.equalsIgnoreCase(className)){
						htmlStringBuilder.append("\t \t <include name=\""+methodName1+"\" />\n");
					}
				}

				htmlStringBuilder.append("\t </methods>");
				htmlStringBuilder.append("</class>\n");
				beforeClass1=className;
				}
			}

			htmlStringBuilder.append("</classes>\n</test>\n</suite>");
			String filePathAndName = xmlPath + xmlName;
			File resFile=new File(filePathAndName);
			OutputStream outputStream = new FileOutputStream(resFile.getAbsoluteFile());
			Writer writer=new OutputStreamWriter(outputStream);
			writer.write(htmlStringBuilder.toString());
			writer.close();
			System.out.println("Test NG XML Generation Completed");
			System.out.println("----------------------------------------------------------------");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
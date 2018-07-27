package com.automation.library;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
public class HtmlReport {
	public static LinkedHashMap<String,String> resultsMap = new LinkedHashMap<String,String>();
	public static void main(String[] args)
	{}

	public static void generateHTMLReport(String filePath,String date,String browser,String version,String startTime,String endTime,String totalTime) throws IOException {
		try {
			resultsMap=Logger.htmlResultMap;
			int passCount=0;
			int failCount=0;

			for(String key : resultsMap.keySet()){
				if(resultsMap.get(key).split("~")[2].equalsIgnoreCase("PASS")){
					passCount=passCount+1;
				}
				if(resultsMap.get(key).split("~")[2].equalsIgnoreCase("FAIL")){
					failCount=failCount+1;
				}
			}

			System.out.println("HTML Report Generation Started");

			StringBuilder htmlStringBuilder=new StringBuilder();

			htmlStringBuilder.append("<!DOCTYPE html><html><head><title>Summary Report</title><style>"
					+ "th img {border: 1px solid #ddd;border-radius: 4px;padding: 5px;width: 120px;}"
					+ "img:hover {box-shadow: 0 0 2px 1px rgba(0, 140, 186, 0.5);}"
					+ "table {font-family: arial, sans-serif;border-collapse: collapse;width: 90%;}"
					+ "th{border: 0.5px solid black;padding: 0.5px;}"
					+ "img {border: 1px solid #ddd;border-radius: 4px;padding: 5px;width: 100%;}"
					+ "h1 img {border: 1px solid #ddd;border-radius: 10px;padding: 5px;width: 10%;}</style></head>"
					+ "<body  style='background-color: powderblue;'>"
					+ "<h1 align='center'><img src='"+System.getProperty("user.dir")+"\\drivers\\Accenture.png"+" '; alt='logo' align='left' />"
					+ "<span style='text-decoration: underline;'>"
					+ "Test Execution Summary Report</span>"
					+ "<img src='"+System.getProperty("user.dir")+"\\drivers\\Accenture.png"+"'; alt='logo' align='right' /></h1>");

			htmlStringBuilder.append("<br/><br/><br/><table align='center' border='1'><tr bgcolor=#DAA520>"
					+ "<th>Browser</th>"
					+ "<th>Browser Version</th>"
					+ "<th>Environment Base URL</th>"
					+ "<th>Total TC's</th>"
					+ "<th>PASS</th>"
					+ "<th>FAIL</th>"
					+ "<th>Execution Started Time</th>"
					+ "<th>Execution Completed Time</th>"
					+ "<th>Total Execution Time</th></tr>");

			htmlStringBuilder.append("<tr bgcolor='#F0FFFF'>"
					+ "<td>"+browser+"</td>"
					+ "<td>"+version+"</td>"
					+ "<td>"+ApplicationVariables.default_Stg_Url+"</td>"
					+ "<td align='center'>"+resultsMap.size()+"</td>"
					+ "<td bgcolor='#98FB98' align='center'>"+passCount+"</td>"
					+ "<td bgcolor='#E9967A' align='center'>"+failCount+"</td>"
					+ "<td>"+startTime+"</td>"
					+ "<td>"+endTime+"</td>"
					+ "<td>"+totalTime+"</td></tr></table>");

			htmlStringBuilder.append("<h2 style='text-decoration: underline;text-indent: 2.7em;'>Execution Summary :</h2>");

			htmlStringBuilder.append("<table align='center' border='1'><tr bgcolor='#1E90FF'>"
					+ "<th align='center'>S.No</th>"
					+ "<th>Test Case Name</th>"
					+ "<th>Expected Value</th>"
					+ "<th>Actual Value</th>"
					+ "<th align='center'>Status</th></tr>");
			int i=1;
			System.out.println("++++++++++++++++++++++++++++");
			for(String key : resultsMap.keySet()){
				String imgSrc=key.split("~")[0]+"~"+resultsMap.get(key).split("~")[1]+".png";

				System.out.println("TC "+key.split("~")[0]);
				System.out.println("EXP"+resultsMap.get(key).split("~")[0]);
				System.out.println("ACT "+resultsMap.get(key).split("~")[1]);
				System.out.println("STATUS "+resultsMap.get(key).split("~")[2]);

				if(resultsMap.get(key).split("~")[2].contains("PASS")){
					htmlStringBuilder.append("<tr bgcolor='#98FB98'>");
				}else{
					htmlStringBuilder.append("<tr bgcolor='#E9967A'>");
				}
				htmlStringBuilder.append("<td align='center'  width='5%'>"+i+"</td>");
				htmlStringBuilder.append("<td width='20%'>"+key.split("~")[0]+"</td>");
				htmlStringBuilder.append("<td width='30%'>"+resultsMap.get(key).split("~")[0]+"</td>");
				htmlStringBuilder.append("<td width='30%'>"+resultsMap.get(key).split("~")[1]+"</td>");
				htmlStringBuilder.append("<td align='center'  width='10%'><a target='_blank' href='../"+browser+"/screenshots/"+imgSrc+"'><img src='../"+browser+"/screenshots/"+imgSrc+"'  style='width: 150px'></a></td>");
				i++;
			}
			System.out.println("++++++++++++++++++++++++++++");

			htmlStringBuilder.append("</table><br /><br />");
			htmlStringBuilder.append("<footer align='center'><br /> © Copyright 2017, All Rights Reserved</footer>");
			htmlStringBuilder.append("</body></html>");

			String fileName="Summary Results_"+date+".html";
			String filePathAndName = filePath + fileName;
			File resFile=new File(filePathAndName);
			OutputStream outputStream = new FileOutputStream(resFile.getAbsoluteFile());
			Writer writer=new OutputStreamWriter(outputStream);
			writer.write(htmlStringBuilder.toString());
			writer.close();
			System.out.println("HTML Report Generation Completed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
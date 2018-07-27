package com.automation.library;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelFileReader {
	public static XSSFSheet sheet = null;
	int columnIndexByCoumnName = 1;
	public static String testDataFile=null;
	public static String sheetName=null;
	public static XSSFWorkbook workbook;
	public static DataFormatter fmt ;
	public void testMain(Object[] args) {

	}

	public void closeExcelIfOpen(File file) throws IOException{
		File sameFileName = new File(file.getAbsolutePath());
		if(!(file.renameTo(sameFileName))){
			System.out.println("Closing file "+file.getName());
			Runtime.getRuntime().exec("cmd /c taskkill /f /im excel.exe");
		}
	}

	public void prepareTestDataSheet(String excelfileName, String excelSheetName){
		testDataFile=excelfileName;
		FileInputStream input=null;
		try {			
			File file=new File(testDataFile);
			//closeExcelIfOpen(file);
			input= new FileInputStream(file);
			workbook = new XSSFWorkbook(input);
			sheet = workbook.getSheet(excelSheetName);
			sheetName=excelSheetName;
		} catch (Exception e) {
			System.out.println("Test data Sheet Crashed");
			e.printStackTrace();
		}
		finally{
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public XSSFSheet getXSSFSheet(){
		return sheet;
	}

	public int getTotalNoOfRows(){
		return sheet.getPhysicalNumberOfRows();
	}

	public int getColumnIndexByColumnName(String columnName) {
		int defColIndex=-1;
		try {			
			Row row = sheet.getRow(0);
			Iterator<Cell> cells = row.cellIterator();
			while (cells.hasNext()) {
				Cell cell = cells.next();
				String value = convertToString(cell);
				if (value.equalsIgnoreCase(columnName)) {
					defColIndex = cell.getColumnIndex();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defColIndex;
	}

	public int getRowIndexByRowName(String rowName) {
		int reqRowIndex=-1;
		try {			
			Row row = sheet.getRow(0);
			Iterator<Cell> cells = row.cellIterator();
			while (cells.hasNext()) {
				Cell cell = cells.next();
				String value = convertToString(cell);
				if (value.equalsIgnoreCase(rowName)) {
					reqRowIndex = cell.getRowIndex();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return reqRowIndex;
	}

	public String getCellValue(int rowIndex, int columnIndex){
		if(rowIndex == -1){
			System.out.println("Row is not identified with specified text");
			return "";
		}
		Row row = sheet.getRow(rowIndex);
		Cell cell = row.getCell(columnIndex);
		if(cell == null){
			return "";
		}
		return convertToString(cell);
	}


	public void setCellValue(int rowIndex, int columnIndex,String value){
		FileOutputStream fos = null;
		try{
			if(rowIndex == -1){
				System.out.println("Row is not identified with specified text");
			}
			Row row = sheet.getRow(rowIndex);
			Cell cell = row.createCell(columnIndex);
			cell.setCellValue(value);
			System.out.println("OK");
			fos = new FileOutputStream(new File(testDataFile));
			workbook.write(fos);
			fos.flush();
			fos.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(null != fos && null != workbook) {
				try {
					fos.close();
					workbook = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public String getValueBy(int rowIndex, String columnName){
		int colIndex = getColumnIndexByColumnName(columnName);
		String value = getCellValue(rowIndex, colIndex);
		return value;
	}

	public int getRowIndexBy(String propertyName) {
		int rowIndex= -1;
		for(int i=1;i<=sheet.getLastRowNum();i++){
			Row row = sheet.getRow(i);
			Cell cell = row.getCell(0);
			String value = convertToString(cell);
			if (value.equalsIgnoreCase(propertyName)) {
				rowIndex = row.getRowNum();
				break;
			}
		}
		return rowIndex;
	}

	private String convertToString(Cell cell){
		Object value="";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			value = cell.getNumericCellValue();
			break;
		case Cell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		}
		if(value == null){
			value="";
		}

		return value.toString();
	}


	public String getValueByRowText(String rowIdentifier,String columnIdentifier){
		int rowIndex=getRowIndexBy(rowIdentifier);
		int colIndex = getColumnIndexByColumnName(columnIdentifier);
		//System.out.println(rowIndex);
		//	System.out.println(colIndex);
		String value = getCellValue(rowIndex, colIndex);
		//System.out.println("value : "+value);
		return value;
	}
	public String getValueByRowText(int rowIdentifier,String columnIdentifier){
		try {
			int rowIndex=rowIdentifier;
			int colIndex = getColumnIndexByColumnName(columnIdentifier);
			//System.out.println(rowIndex);
			//	System.out.println(colIndex);
			return getCellValue(rowIndex, colIndex);
			//System.out.println("value : "+value);
		} catch (Exception e) {
			return null;
		}
	}
	
	public int getValueByRowTextInt(String rowIdentifier,String columnIdentifier){
		int rowIndex=getRowIndexBy(rowIdentifier);
		int colIndex = getColumnIndexByColumnName(columnIdentifier);
		int value = Integer.parseInt(getCellValue(rowIndex, colIndex));
		return value;
	}

	public void setCellValueByRowColNumbers(int rowId,int colId,String value){
		try{
			FileOutputStream fos = null;
			FileInputStream fis = new FileInputStream(new File(testDataFile));
			workbook = new XSSFWorkbook (fis);
			sheet = workbook.getSheet(sheetName);
			System.out.println(colId);
			System.out.println(rowId);
			Cell reqCell=getCell(rowId, colId);				
			reqCell.setCellValue(value);
			fos = new FileOutputStream(new File(testDataFile));
			System.out.println(workbook.getActiveSheetIndex());
			fis.close();
			workbook.write(fos);
			fos.flush();
			fos.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void setCellValueByRowColText(String rowId,String colId,String value){
		FileOutputStream fos = null;
		try{
			FileInputStream fis = new FileInputStream(new File(testDataFile));
			workbook = new XSSFWorkbook (fis);
			sheet = workbook.getSheet(sheetName);
			int colIndex=getColumnIndexByColumnName(colId);
			int rowIndex=getRowIndexBy(rowId);
			Cell reqCell=getCell(rowIndex, colIndex);				
			reqCell.setCellValue(value);
			fos = new FileOutputStream(new File(testDataFile));
			fis.close();
			workbook.write(fos);
			fos.flush();
			fos.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Cell getCell(int rowIndex, int columnIndex){
		Row row = sheet.getRow(rowIndex);
		Cell cell = row.getCell(columnIndex);
		if(null != cell) {
			return cell;
		} else {
			cell = row.createCell(columnIndex);
			return cell;
		}
	}

	public int getColumnCount(){
		Row row = sheet.getRow(0);
		return row.getPhysicalNumberOfCells();
	}
	
	public int getRowCount(){
		int rowCount=-1;
		try{
			rowCount = sheet.getPhysicalNumberOfRows();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;	
	}
}
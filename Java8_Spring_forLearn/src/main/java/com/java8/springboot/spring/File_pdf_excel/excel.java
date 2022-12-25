package com.java8.springboot.spring.File_pdf_excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


//https://www.javatpoint.com/java-create-excel-file
public class excel {
	@Value("path-excel")
	private String excel;
	
	private Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
	private String pathExcel = String.format(excel, CURRENT_FOLDER.toString());
	
	public void exportExcel(String nameFile, String nameSheet) {
		pathExcel = String.format(pathExcel, nameFile);
		File file = new File(pathExcel);
		// Kiểm tra ổ đĩa có tồn tại 
		if(!file.getParentFile().exists()) file.mkdir();
		
		//Tạo mới WorkBook, có khá nhiều hàm hay liên quan đến sheet tìm hiểu xem sao nhé.
		try {
			Workbook wb2007 = new XSSFWorkbook(file);
			// Ghi tiếp vào sheet nếu nó đã tồn tại                                                                                
			Sheet sheet = wb2007.getSheet(nameSheet);
			if(sheet == null) {
				String safeName = WorkbookUtil.createSafeSheetName(nameSheet);
				sheet = wb2007.createSheet(safeName);
			}
			int rowIndex = getRowCurrent(wb2007);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
	
	private int getRowCurrent(Workbook wb2007) {
		// TODO Auto-generated method stub
		  Sheet sheet = wb2007.getSheet("user");
		  System.out.println(sheet+"sheet");
		  Integer index = 0;
		 try {
		  index = sheet.getLastRowNum();
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 return 0;
		}
		return index;
	}

}

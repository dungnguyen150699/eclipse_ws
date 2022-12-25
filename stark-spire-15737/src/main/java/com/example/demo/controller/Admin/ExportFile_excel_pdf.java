package com.example.demo.controller.Admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.util.IntegerField;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.weaver.patterns.IScope;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
@RequestMapping(value = "/exportfile")
public class ExportFile_excel_pdf {
	private Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
	private static CellStyle cellStyleFormatNumber = null;

	@Autowired
	private UserService userService;

	@Value("${path-userexcel}")
	private String pathUserExcel;

	@RequestMapping(value = "/userexcel")
	public String export_excel(Model model) throws IOException, InvalidFormatException {
//		create folder if not exist
		String mkdir = String.format(pathUserExcel, CURRENT_FOLDER.toString());
		System.out.println(mkdir);
		File filedir = new File(mkdir);
		model.addAttribute("fileStatus", "");
		if (!filedir.exists())
			filedir.mkdir();
		else {
//			create file if not exist
			String pathUserExcel = mkdir + "\\userexcel.xlsx";
			System.out.println(pathUserExcel);
//			File fileUserExcel = new File(pathUserExcel);
//			if(!fileUserExcel.exists()) fileUserExcel.createNewFile();
//			Workbook wb2007 = new XSSFWorkbook(fileUserExcel);
			Workbook wb2007 = new XSSFWorkbook();
			System.out.println(wb2007);
			// Tạo sheet với tên MySheetName
			Sheet sheet = wb2007.getSheet("user");
			if (sheet == null) {
				String safeName = WorkbookUtil.createSafeSheetName("user");
				sheet = wb2007.createSheet(safeName);
			}
			int rowIndex = getRowCurrent(wb2007);
			// Write data
			for (User u : userService.getAll()) {
				// Create row
				Row row = sheet.createRow(rowIndex++);
				// Write data on row
				writeUser(u, row);
//	            rowIndex++;
			}

			OutputStream os = new FileOutputStream(new File(pathUserExcel));
			// Auto resize column witdth ghi vào đây thì lại đc ????
			// => chỉ có thể ghi header footer sau khi ghi data
			int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();

			writeHeader(wb2007, 0);
			// chỉ có thể autosize khi đã ghi xong mọi thứ ghi excel rất lỗi cần chú ý
			autosizeColumn(sheet, numberOfColumn);
			wb2007.write(os);
			os.flush();
			os.close();
			wb2007.close();
			model.addAttribute("listUser", userService.getAll());
			model.addAttribute("fileStatus", "ghi file thanh cong");
		}

		return "ADMIN/index";
	}

	private int getRowCurrent(Workbook wb2007) {
		// TODO Auto-generated method stub
		Sheet sheet = wb2007.getSheet("user");
		System.out.println(sheet + "sheet");
		Integer index = 0;
		try {
			index = sheet.getLastRowNum();
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return index;
	}

	// Write data
	private static void writeUser(User u, Row row) {
		Workbook workbook = row.getSheet().getWorkbook();
//        if (cellStyleFormatNumber == null) {
//            // Format number
//            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
//            // DataFormat df = workbook.createDataFormat();
//            // short format = df.getFormat("#,##0");
//             
//            //Create CellStyle
//            cellStyleFormatNumber = workbook.createCellStyle();
//            cellStyleFormatNumber.setDataFormat(format);
//        }

		// * VD set style for cell
		CellStyle c_style = workbook.createCellStyle();
		DataFormat format = workbook.createDataFormat();
//        c_style.setDataFormat(format.getFormat("0.0"));
//        Row row = workbook.getSheet("user").createRow(0);
		int idex = 0;
		while (row.getFirstCellNum() != -1) {
			row.removeCell(row.getCell(row.getFirstCellNum()));
		}
//        Cell cell = row.createCell((short)idex);
////        cell.setCellStyle(cellStyleFormatNumber);
//        cell.setCellValue("id:" + u.getId());
////        cell.setCellStyle(c_style);

//        Kết luận ghi file cần đưa vào vòng for : (Nếu bỏ ++ thì nó lỗi cell đầu = 1)
		System.out.println("id:" + u.getId());
		System.out.println(cellStyleFormatNumber);
		Cell cell = row.createCell((short) idex++);
		cell.setCellStyle(cellStyleFormatNumber);
		cell.setCellValue(u.getId());

		cell = row.createCell((short) idex++);
		cell.setCellValue(u.getUsername());

		cell = row.createCell((short) idex++);
		cell.setCellValue(u.getPassword());

		cell = row.createCell((short) idex++);
		cell.setCellValue(u.getPhone());;

		cell = row.createCell((short) idex++);
		cell.setCellValue(u.getAddress());

		cell = row.createCell((short) idex++);
		StringBuilder roleName = new StringBuilder("");
		u.getRoles().stream().forEach(r -> roleName.append(r.getName()));
		cell.setCellValue(roleName.toString());
		// Create cell formula
		// totalMoney = price * quantity
//        cell = row.createCell(COLUMN_INDEX_TOTAL, CellType.FORMULA);
//        cell.setCellStyle(cellStyleFormatNumber);
//        int currentRow = row.getRowNum() + 1;
//        String columnPrice = CellReference.convertNumToColString(COLUMN_INDEX_PRICE);
//        String columnQuantity = CellReference.convertNumToColString(COLUMN_INDEX_QUANTITY);
//        cell.setCellFormula(columnPrice + currentRow + "*" + columnQuantity + currentRow);

	}

	public void writeHeader(Workbook wb, int index) {
		Sheet sheet = wb.getSheet("user");
		// create CellStyle
		CellStyle cellStyle = createStyleForHeader(sheet);

		int idex = index;
		// Create row
		Row row = sheet.createRow(idex);

		// Create cells
		Cell cell = row.createCell(idex++);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Id");

		cell = row.createCell(idex++);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("UserName");

		cell = row.createCell(idex++);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Password");

		cell = row.createCell(idex++);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Phone");

		cell = row.createCell(idex++);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Address");

		cell = row.createCell(idex++);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("Role");
		
	}

	// Create CellStyle for header
	private static CellStyle createStyleForHeader(Sheet sheet) {
		// Create font
		Font font = sheet.getWorkbook().createFont();
		font.setFontName("Times New Roman");
		font.setBold(true);
		font.setFontHeightInPoints((short) 14); // font size
		font.setColor(IndexedColors.WHITE.getIndex()); // text color

		// Create CellStyle
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		return cellStyle;
	}

	// Write footer
//    private static void writeFooter(Sheet sheet, int rowIndex) {
//        // Create row
//        Row row = sheet.createRow(rowIndex);
//        Cell cell = row.createCell(COLUMN_INDEX_TOTAL, CellType.FORMULA);
//        cell.setCellFormula("SUM(E2:E6)");
//    }

	// Auto resize column width
	private static void autosizeColumn(Sheet sheet, int lastColumn) {
		for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
		}
	}
}

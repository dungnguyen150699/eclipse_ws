package com.java8.springboot.spring.File_pdf_excel;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

// https://viblo.asia/p/doc-va-ghi-file-pdf-trong-java-l5XRBJQeRqPe
public class pdf {
	public Path path = Paths.get(System.getProperty("user.dir"));

	public Document create_GetLink_FilePdf(String nameFile) throws FileNotFoundException, DocumentException {
		File file = new File(path.resolve("file/" + nameFile).toString());
		// Tạo đối tượng tài liệu
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		// Tạo đối tượng PdfWriter (tạo file pdf đấy)
		PdfWriter.getInstance(document, new FileOutputStream(file));
		return document;
	}
	
	public String readFilePdf(String pathFile) throws IOException {
		//Khai báo PdfReader với giá trị truyền vào là path của file cần đọc
        PdfReader reader = new PdfReader(pathFile);
        //In ra số lượng page của file đã đọc
        System.out.println("This PDF has "+reader.getNumberOfPages()+" pages.");
        //Sử dụng PdfTextExtractor để đọc toàn bộ text ở trang 100
        String page = PdfTextExtractor.getTextFromPage(reader, 1);
        System.out.println("Page 100 Content:\n\n"+page+"\n\n");
        return page;
	}
		
	public void writeFilePdf(Document document) throws DocumentException{
		
		// Mở file để thực hiện ghi
		document.open();

		// Thêm nội dung sử dụng add function
		document.add(new Paragraph("Viblo Asia1"));
//		document.add

		Anchor anchorTarget = new Anchor("Second page of the document.");
		anchorTarget.setName("BackToTop");
		document.add(anchorTarget);

		// Đóng File
		document.close();
		System.out.println("Write file succes!");
	}
	// Đang bị lỗi gì đấy
//	 public String insertImageToFile(String filePdfPath, String fileImagePath) throws IOException, DocumentException {
//		int pageNumber = 1;
//		PdfReader pdfReader = null;
//		PdfStamper pdfStamper = null;
//		
////		float o_paddingLeft = 10f;
////		float o_paddingTop = 10f;
//		try {
//		File fileImage = new File(fileImagePath);
//		
//		Image img = Image.getInstance(fileImage.getAbsolutePath());
//		File file = new File(filePdfPath);
//		pdfReader = new PdfReader(filePdfPath);
//		pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(file));
//		Rectangle pdfPageSize = pdfReader.getPageSize(pageNumber);
//		
//		float pdfPageHeight = pdfPageSize.getHeight();
//		Rectangle location = new Rectangle(10f, 10f);
//		img.setAbsolutePosition(location.getLeft(), location.getTop());
//		PdfContentByte pageContentByte = pdfStamper.getOverContent(pageNumber);
//		pageContentByte.addImage(img);
//		} finally {
//		if (pdfStamper != null) {
//		pdfStamper.close();
//		
//		}
//		if (pdfReader != null) {
//		pdfReader.close();
//		}
//	}
//		return filePdfPath;
//	}
	
	public void insertImageToPdf(String filePdfPath,String filePdfPath_ModifyCreate, String fileImagePath) throws DocumentException, IOException {
		PdfReader reader = new PdfReader(filePdfPath);
		//Create file modify
		create_GetLink_FilePdf("test1.pdf");
	    PdfStamper stamper = new PdfStamper(reader,new FileOutputStream(filePdfPath_ModifyCreate));
	    Image img = Image.getInstance(fileImagePath);
	    img.setAbsolutePosition(200, 400);
	    BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
	    PdfContentByte under, over;
	    int total = reader.getNumberOfPages() + 1;
	    for (int i = 1; i < total; i++) {
	      stamper.setRotateContents(false);
	      under = stamper.getUnderContent(i);
	      under.addImage(img);

	    }
	    stamper.close();
	    reader.close();
	}

	
	public static void main(String[] args) throws IOException, DocumentException {
		pdf p = new pdf();
//		// Tạo file và ghi
//		p.writeFilePdf(p.create_GetLink_FilePdf("test.pdf"));
//		//Lấy file sẵn có và ghi chuwa lamf dc ( dùng 	PdfStamper)
		String filePdfPath = p.path.resolve("file/test.pdf").toString();
		String filePdfPath_Append = p.path.resolve("file/test1.pdf").toString();
		String imagePath = p.path.resolve("file/bag.jpg").toString();
		p.insertImageToPdf(filePdfPath,filePdfPath_Append, imagePath);
	}
}
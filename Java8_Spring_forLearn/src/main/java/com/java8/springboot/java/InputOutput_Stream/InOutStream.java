package com.java8.springboot.java.InputOutput_Stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.logging.log4j.message.StringFormattedMessage;

//  https://openplanning.net/13367/java-bytearrayoutputstream
/*
 * Chỉ xử dụng ByteArrayOutput Stream vs ObjectOutStream Trong TH TCP,UDP (Socket)
 * Nếu chỉ đọc ghi File Txt thông thường nên sử dụng Buffer (nhanh hơn - đọc thì udngf Scanner cũng đc)
 * https://viettuts.vn/vi-du-java-io/doc-ghi-file-trong-java
 */

public class InOutStream {
	public String linkFile = "%s\\logs\\%s";
	
	public String getPathFile(String nameFile) {
		String pathFile = String.format(linkFile, System.getProperty("user.dir"),nameFile);
		return pathFile;
	}

		
	public String readFile_Scanner(String pathFile) throws FileNotFoundException {
		StringBuffer result = new StringBuffer();
		// Phải là new File thì mới đọc file đưa String vào thì mặc định Scanner đọc String đó
		Scanner sc = new Scanner(new File(pathFile));
		while(sc.hasNext()) {
			result.append(sc.nextLine());
			result.append("\n");
		}
		sc.close();
		return result.toString();
	}
	
	// Ví dụ: Sử dụng Wrapper cho byte stream
	public void writeReadFile_BufferByte() throws IOException {
		BufferedInputStream bufferIn = null;
        BufferedOutputStream bufferOut = null;
 
        try {
        	File fileIn = new File(getPathFile("severe-logger.log"));
        	
        	File fileOut = new File(getPathFile("output.txt"));
        	if(!fileIn.exists()) fileIn.createNewFile();
        	
            InputStream inputStream = new FileInputStream(fileIn);
            OutputStream outputStream = new FileOutputStream(fileOut);
          
            bufferIn = new BufferedInputStream(inputStream);
            bufferOut = new BufferedOutputStream(outputStream); 
            int c;                    
            // đọc từng kí tự 1 theo kiểu byte c = bufferIn.read() ( convert byte to Char )
            int dem = 0;
            while ((c = bufferIn.read()) != -1) {
                bufferOut.write(c);

                if(dem<=10) {
                	System.out.print((char)c);
                    dem ++;
                }
            }            
        } finally {
            if (bufferIn != null) {
                bufferIn.close();
            }
            if (bufferOut != null) {
                bufferOut.close();
            }            
        }         
	}
	
	// Ví dụ: Sử dụng Wrapper cho character stream
	public void writeReadFile_BufferChar() throws IOException {
		BufferedReader bufferIn = null;
		BufferedWriter bufferOut = null;
 
        try {
        	File fileIn = new File(getPathFile("severe-logger.log"));
        	
        	File fileOut = new File(getPathFile("output.txt"));
        	if(!fileIn.exists()) fileIn.createNewFile();
        	
            Reader inputStream = new FileReader(fileIn);
            Writer outputStream = new FileWriter(fileOut);
          
            bufferIn = new BufferedReader(inputStream);
            bufferOut = new BufferedWriter(outputStream); 
            int c;                    
            // đọc từng kí tự 1 theo kieu char (covert thanh int nen phair covert lai)
            int dem = 0;
            while ((c = bufferIn.read()) != -1) {
                bufferOut.write(c);
                if(dem<=10) {
                	System.out.print((char)c);
                    dem ++;
                }
            }            
        } finally {
            if (bufferIn != null) {
                bufferIn.close();
            }
            if (bufferOut != null) {
                bufferOut.close();
            }            
        }         
	}
	

//    public void SendData(ServerReceiveData srd) {
//        ObjectOutputStream oos = null;
//        try {
//            oos = new ObjectOutputStream(userSocket.getOutputStream());
//            oos.flush();
//            try {
//                oos.writeObject(srd);
//            } catch (IOException ex) {
//                Logger.getLogger(UserControl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(UserControl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
	
//    public ServerSendData ReceiveData() {
//        ObjectInputStream ois = null;
//        ServerSendData ssd = null;
//        try {
//            ois = new ObjectInputStream(userSocket.getInputStream());
//            ssd = (ServerSendData) ois.readObject();
//        } catch (IOException ex) {
//            Logger.getLogger(UserControl.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UserControl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return ssd;
//    }
	
	public static void main(String[] args) throws IOException {
		InOutStream io = new InOutStream();
//		System.out.println(io.readFile_Scanner(io.getPathFile("severe-logger.log")));
		// Nên dùng 2 cách này cho ghi file Text
		io.writeReadFile_BufferByte();
		io.writeReadFile_BufferChar();
		// Ghi file Object thì làm như sau
	}
}

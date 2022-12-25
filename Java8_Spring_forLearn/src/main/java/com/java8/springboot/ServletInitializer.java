package com.java8.springboot;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;

//public class ServletInitializer extends SpringBootServletInitializer {
public class ServletInitializer  {

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(Java8SpringForLearnApplication.class);
//	}
	
	private static final String dir = System.getProperty("user.dir");
    private static Path path = Paths.get(dir);

    public static boolean createFile() throws IOException {
        Boolean result = false;
        // Tạo file để lưu username
        File file_username = new File(path.resolve("logs1/username.txt").toString());
        if(!file_username.getParentFile().exists()) file_username.getParentFile().mkdir();
        if(!file_username.exists()){
            result = file_username.createNewFile();
        }else  return true;
        return result;
    }

    public static void writeFile(String str) throws IOException {
        File file_write = null;
        BufferedWriter bufferedWriter = null;
        try{
            file_write = new File(path.resolve("logs1/username.txt").toString());
            Writer outputStream = new FileWriter(file_write,false);
            bufferedWriter = new BufferedWriter(outputStream);
            bufferedWriter.write(str);
        }catch (IOException ioE){
            System.err.println(ioE.getMessage());
        }finally {
            bufferedWriter.flush();
            bufferedWriter.close();
        }
    }

    public static String readFile_username() throws FileNotFoundException{
        StringBuffer result = new StringBuffer("");
        Scanner sc = new Scanner(new File(path.resolve("logs1/username.txt").toString()));
//	        while(sc.hasNext()) {
	        	result.append(sc.nextLine());
//	            result.append("\n");
//	        }
        sc.close();
        return result.toString();
    }
	    
    public static void main(String...strings) {
		// TODO Auto-generated method stub
    	try {
//			System.out.println(createFile());
//			writeFile("hello");
			System.out.println(readFile_username());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	    

}


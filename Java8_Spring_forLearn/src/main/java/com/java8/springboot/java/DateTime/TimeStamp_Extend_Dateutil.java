package com.java8.springboot.java.DateTime;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

public class TimeStamp_Extend_Dateutil {
	public static void main(String[] args) {
//		Timestamp ts=new Timestamp(System.currentTimeMillis());  
//		System.out.println(ts);
//        Date date=ts;  
//        System.out.println(date);  
//        Double price = 1243.21443;
        try{
            new URL("https://www.geeksforgeeks.org/check-if-url-is-valid-or-not-in-java/").toURI();
        }catch (Exception ex){
        	ex.printStackTrace();
        }
        String text = "Helo\"xx\"22 \"xx2\" đe";
//        int index = text.ind
        Integer lastIndex = text.lastIndexOf("\"");
        Integer lastIndex2 = text.lastIndexOf("\"", lastIndex - 1);
        
        System.out.println(text.substring(lastIndex2,lastIndex+1));
	}

}

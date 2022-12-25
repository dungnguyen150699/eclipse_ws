package com.java8.springboot.java.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.java8.springboot.java.java8.Regex;
import com.sun.el.parser.ParseException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TimeUnit_test {
	// Time Unit dùng như thread nhưng là cho luồng chính
    private static void sleep(long second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
    
 // Làm như này cũng thế nhưng cái kia tiện hơn
    private static void simulateSlowService() {
        try {
            long time = 3000L; //3 Second
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
    public void convertDateX2() {
   	 String textDate = "031801";
     Date actualDate = null;

     SimpleDateFormat yy = new SimpleDateFormat( "MMddyy" );
     SimpleDateFormat yyyy = new SimpleDateFormat( "MM/dd/yyyy" );

     try {
		actualDate = yy.parse( textDate );
	} catch (java.text.ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

     System.out.print( textDate + " enhanced:  " );
     System.out.println( yyyy.format( actualDate ) );
    }

    public static void main(String[] args) {
//		sleep(10);
//    	simulateSlowService();
//    	System.out.println("xxxx");
//    	Date date = new java.util.Date();
//    	System.out.println(date+"-------");
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    	
//    	System.out.println(sdf.format(date));
//    	
    	new TimeUnit_test().convertDateX2();
	}

}

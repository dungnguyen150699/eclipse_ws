package com.java8.springboot.java.DateTime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateHelper {
	public static final String DEFAULT_FORMAT_TIME = "HH:mm:ss";
	
	public static Date now() {
	    LocalDateTime localDateTime = LocalDateTime.now();
//	    System.out.println(ZoneId.systemDefault());
	    /**
	     * Phương thức from (Instant inst) của lớp Java Date trả về một phiên bản của ngày tháng được lấy từ một đối tượng Instant.
	     */
	    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
			/*
			 * Field Detail
		Link: https://docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html
		EST - -05:00
		HST - -10:00
		MST - -07:00
		ACT - Australia/Darwin
		AET - Australia/Sydney
		AGT - America/Argentina/Buenos_Aires
		ART - Africa/Cairo
		AST - America/Anchorage
		BET - America/Sao_Paulo
		BST - Asia/Dhaka
		CAT - Africa/Harare
		CNT - America/St_Johns
		CST - America/Chicago
		CTT - Asia/Shanghai
		EAT - Africa/Addis_Ababa
		ECT - Europe/Paris
		IET - America/Indiana/Indianapolis
		IST - Asia/Kolkata
		JST - Asia/Tokyo
		MIT - Pacific/Apia
		NET - Asia/Yerevan
		NST - Pacific/Auckland
		PLT - Asia/Karachi
		PNT - America/Phoenix
		PRT - America/Puerto_Rico
		PST - America/Los_Angeles
		SST - Pacific/Guadalcanal
		VST - Asia/Ho_Chi_Minh
		The map is unmodifiable.
	 */
	
	  public static LocalDateTime asLocalDateTime(Date date) {
		 return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	  }
	  
	  public static String toTimeString(Date date) {
	    DateTimeFormatter formatter = null;

	    formatter = DateTimeFormatter.ofPattern(DEFAULT_FORMAT_TIME);
	    //LocalDateTime , TimeStamp mới có format ; format = String luôn
	    return asLocalDateTime(date).format(formatter);
	  }
	public static void main(String[] args) {
		ZoneId asiaVietNam = null ;
//		asiaVietNam = ZoneId.of("Asia/Ho_Chi_Minh");
		
		LocalDateTime localDateTime = LocalDateTime.now();
		/*
		 * perfix  GMT : Giờ Quốc tế (Greenwich Mean Time) ;
		 *  UTC Giờ Phối hợp quốc tế (Coordinated Universal Time) ,
		 *  UT Giờ quốc tế (Universal Time):
		 *  Tóm lại cái ZoneOffset nó lấy giờ theo múi
		 */
		asiaVietNam = ZoneId.ofOffset("GMT",ZoneOffset.of("+07:00"));
		/*
		 * As stated above, ZoneId is a representation of the time-zone such as ‘Europe/Paris‘.
		 * ZoneOffset extends ZoneId and defines the fixed offset of the current time-zone with GMT/UTC, such as +02:00
		 */
		System.out.println(asiaVietNam.toString());
		System.out.println("Modify: "+ Date.from(localDateTime.atZone(asiaVietNam).toInstant()) + "\nOrigin: " + now().toString());
//		--------------------------------------
		System.out.println("\n\n\n");
		System.out.println("DateTime to String: " + toTimeString(now()));
		// create a long variable for milliseconds
        long milliseconds
            = 999999000;
  
        // get Instant using ofEpochMilli() method
        Instant instant
            = Instant.ofEpochMilli(milliseconds);
  
        // print result
        System.out.println("Instant: "
                           + instant);
        // Vì localDateTime ; time Stamp có plusDay,Hours,Month,Years rồi nên dừng lại tại đây
	}
}

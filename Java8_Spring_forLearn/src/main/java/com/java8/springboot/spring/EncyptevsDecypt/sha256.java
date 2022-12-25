package com.java8.springboot.spring.EncyptevsDecypt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.java8.springboot.java.Sercure_Random;

public class sha256 {
	
	public static String sha256(String base) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
        System.out.println("Hash_Byte[]_UTF8 to String:"+ new String(hash));
        StringBuilder stringBuilder = new StringBuilder();
        
        // Quick convent Hex
        //We use %02X to print two places (02) of Hexadecimal (X) value and store it in the string st.
        for (byte b : hash) {
            stringBuilder.append(String.format("%02X", b));
        }
        System.out.println("Hash_Byte_UTF8_Append %02X: \n(Quick convent UTF16)" + stringBuilder.toString());
//        return stringBuilder.toString();
        String str = bytesToHex(hash);
        System.out.println("Hash_Utf16: " + str);
        return str;
	}
	
	private static String bytesToHex(byte[] hash) {
	    StringBuilder hexString = new StringBuilder(2 * hash.length);
	    for (int i = 0; i < hash.length; i++) {
	        String hex = Integer.toHexString(0xff & hash[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
	public String other_way(byte[] bytes) {
		final char[] hexArray = "0123456789ABCDEF".toCharArray();
	        char[] hexChars = new char[bytes.length * 2];
	        for ( int j = 0; j < bytes.length; j++ ) {
	            int v = bytes[j] & 0xFF;
	            hexChars[j * 2] = hexArray[v >>> 4];
	            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	        }
	    return new String(hexChars);
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		Sercure_Random sr = new Sercure_Random();
		String str = sr.RandomString();
		System.out.println("SercureRandom String: "+str);
		sha256 s = new sha256();
		System.out.println("Hex encode"+s.sha256(str));
//		------------------------
		System.out.println("----------------------");
//        byte[] bytes = {10, 2, 15, 11};

        String str1 = s.other_way(str.getBytes());
        System.out.println(str1);
		
	}
}

package com.java8.springboot.java.java8;

import java.util.HashSet;
import java.util.Set;

/**
 * @author DELL
 *	nếu viết HashCode vào thì hàm Set sẽ ko bị lặp
 *	hàm equal chỉ giúp bạn so sánh giá trị bằng nhau của object
 */

public class equal_hashcode{
	private Integer id;
	private String description;
	
	public equal_hashcode(int id , String dc) {
		this.id = id;
		this.description = dc;
	}
	
    public boolean equals(Object obj) {
        if (obj instanceof equal_hashcode) {
        	equal_hashcode another = (equal_hashcode) obj;
            if (this.id.equals(another.id)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
    	return "id:" + this.id + "___ description:" + this.description;
    }
    @Override
    public int hashCode() {
        return 31 + id.hashCode();
    }
    
	public static void main(String[] args) {
		equal_hashcode equal_hashcode1 = new equal_hashcode(123, "Cong");
        equal_hashcode equal_hashcode2 = new equal_hashcode(123, "Congxxx");
        equal_hashcode equal_hashcode3 = new equal_hashcode(456, "Dung");
 
        Set<equal_hashcode> setequal_hashcodes = new HashSet<equal_hashcode>();
        setequal_hashcodes.add(equal_hashcode1);
        setequal_hashcodes.add(equal_hashcode2);
        setequal_hashcodes.add(equal_hashcode3);
 
        // in các phần tử của set ra màn hình
        for (equal_hashcode equal_hashcode : setequal_hashcodes) {
            System.out.println(equal_hashcode.toString());
            System.out.println(equal_hashcode.hashCode());
        }
	}
}

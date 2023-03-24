package com.java8.springboot.java.java8.lamda;

import java.util.concurrent.atomic.AtomicReference;

import org.apache.poi.hssf.util.HSSFColor.AUTOMATIC;

public abstract class Fuji {
    abstract void loveApple();
    
    public static void main(String[] args) {
		
	}
}

class Apple {
    public static void main(String[] args) {
    	AtomicReference<String> appleName = new AtomicReference<>("Fuji");

        Fuji fuji = new Fuji() {
            @Override
            void loveApple() {
                appleName.set(appleName.get().toUpperCase());
                System.out.println("i love " + appleName + " apple.");
            }
        };

        fuji.loveApple();
    }
}
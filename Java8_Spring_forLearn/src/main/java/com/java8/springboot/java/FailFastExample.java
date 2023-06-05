package com.java8.springboot.java;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// Java code to illustrate
// Fail Fast Iterator in Java
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

  
public class FailFastExample {
    public static void main(String[] args)
    {
        Map<String, String> cityCode = new HashMap<String, String>();
        cityCode.put("Delhi", "India");
        cityCode.put("Moscow", "Russia");
        cityCode.put("New York", "USA");
  
        List<String> list = new ArrayList(Arrays.asList("Delhi","2","3","4"));
//        Iterator iterator = cityCode.keySet().iterator();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
//            System.out.println(cityCode.get(iterator.next()));
  
            // adding an element to Map
            // exception will be thrown on next call
            // of next() method.
//            cityCode.put("Istanbul", "Turkey");
            if("Delhi".equals(iterator.next()+"")) iterator.remove();
        }
    }
}
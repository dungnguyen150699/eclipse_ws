package com.java8.springboot.java.filesIO;
import java.io.IOException;

public class FileInputStream {
    /**
     *  Param: pathname to readfile
     *  Return: time execution: milis
     */
    public static long read(String pathName) {
        long start = System.currentTimeMillis();

        try (java.io.FileInputStream fin = new java.io.FileInputStream(pathName)) {
            int data = fin.read();
            while (data != -1) {
                data = fin.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}
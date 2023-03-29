package com.java8.springboot.java.filesIO;
import java.io.IOException;

public class BufferedInputStream {
    /**
     *  Param:
     *      + pathname file
     *      + sizeBuffered
     *  Return: time execution: milis
     */
    public static long read(String pathName, int sizeBuffered) {
        long start = System.currentTimeMillis();
        try (java.io.BufferedInputStream fin = new java.io.BufferedInputStream(new java.io.FileInputStream(pathName), sizeBuffered)) {
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
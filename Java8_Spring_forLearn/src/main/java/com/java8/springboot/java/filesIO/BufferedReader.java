package com.java8.springboot.java.filesIO;
import java.io.IOException;

public class BufferedReader {

    /**
     * Param:
     * + pathname file
     * Return: time execution: milis
     */
    public static long read(String pathName, int size) {
        long start = System.currentTimeMillis();

        try (java.io.BufferedReader fin = new java.io.BufferedReader(new java.io.FileReader(pathName), size)) {
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
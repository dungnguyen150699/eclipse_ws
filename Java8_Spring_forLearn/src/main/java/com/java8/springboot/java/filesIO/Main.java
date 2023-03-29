package com.java8.springboot.java.filesIO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        BufferedWriter bufferedWriter = null;
        try {
            String pathName = "./src/vnedict.txt";
            String output = "./src/output.txt";

            int n = 100;
            long[] averageTimeFileInputStream = new long[n];
            long[] averageTimeBufferedInputStream = new long[n];
            long[] averageTimeFileReader = new long[n];
            long[] averageTimeBufferedReader = new long[n];

            bufferedWriter = new BufferedWriter(new FileWriter(output));

            System.out.println("STARTING");
            System.out.println("--------------------------------");

            for (int i = 0; i < n; i++) {
                System.out.println("Reading at times: " + (i + 1));
                averageTimeFileInputStream[i] = FileInputStream.read(pathName);
                averageTimeBufferedInputStream[i] = BufferedInputStream.read(pathName, 8192);
                averageTimeFileReader[i] = FileReader.read(pathName);
                averageTimeBufferedReader[i] = BufferedReader.read(pathName, 8192);
            }

            bufferedWriter.write("The average time of FileInputStream: " + averageTime(averageTimeFileInputStream, n) + "ms");
            bufferedWriter.newLine();
            bufferedWriter.write("The average time of BufferedInputStream: " + averageTime(averageTimeBufferedInputStream, n) + "ms");
            bufferedWriter.newLine();
            bufferedWriter.write("The average time of FileReader: " + averageTime(averageTimeFileReader, n) + "ms");
            bufferedWriter.newLine();
            bufferedWriter.write("The average time of BufferedReader: " + averageTime(averageTimeBufferedReader, n) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static double averageTime(long[] arr, int n) {
        double sum = 0;
        for (long e : arr)
            sum += e;
        return sum / n;
    }

}
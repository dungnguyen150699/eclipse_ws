package com.example.elasticsearch.ultis;

import java.io.FileInputStream;
import java.util.Properties;

public class ElasticConfig {
    public static String conn;
    public static String eUsername;
    public static String ePassword;
    public static boolean enableSecurity;
    
    public static String KPI_INDEX;
    public static String ORDER_COL;

    public static boolean loadDbConfig(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            Properties prop = new Properties();
            prop.load(fis);
            enableSecurity = prop.getProperty("elastic.secutiry.enable").equals("true");
            eUsername = prop.getProperty("elastic.username");
            ePassword = prop.getProperty("elastic.password");
            conn = prop.getProperty("elastic.hosts");
            KPI_INDEX = prop.getProperty("elastic.kpi_index");
            ORDER_COL = prop.getProperty("elastic.order_col");
            return true;
        } catch (Exception var4) {
            return false;
        }
    }
}

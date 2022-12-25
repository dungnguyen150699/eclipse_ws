package DAO;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class dao {
    public static Connection con;
    public dao() throws SQLException,ClassNotFoundException {
//    	String url = "jdbc:sqlserver://localhost:1433;databaseName=db1;";
//    	String url = "jdbc:sqlserver://PC\\MSSQLSERVER_2021:1433;databaseName=db1;";
    	String url = "jdbc:mysql://localhost:3306/demo";
		String username="root";
		String password="b17dccn160";
//		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url,username,password);
    }
    public static void main(String[] args) {
        try {
        	//{} ĐỂ khai báo biến còn [] chưa biết làm gì luôn chịu java thât
        
			System.out.println(new dao().con);
//			String[][] x = {{"x"},{"1","2"}};
//			System.out.println(x.clone());
			
//			HashMap<String, String> hm = new HashMap();
//			hm.put("1", "xxx");
//			System.out.println(hm.entrySet());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

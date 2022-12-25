package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dao {
    public static Connection con;
    public dao() throws SQLException,ClassNotFoundException {
    	String url = "jdbc:mysql://localhost:3306/vinh_exam";
		String username="root";
		String password="b17dccn160";
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url,username,password);
    }
    public static void main(String[] args) {
        try {
			System.out.println(new dao().con);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

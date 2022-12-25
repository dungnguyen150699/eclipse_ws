package DAO;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.User;

public class userDAO extends dao {
	
	public userDAO() throws SQLException, ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<User> getAll() throws SQLException {
		List <User> listU = new ArrayList();
		
		String selectAll = "Select * from Users";
		PreparedStatement pre = con.prepareStatement(selectAll);
		ResultSet rs = pre.executeQuery();
		while(rs.next()) {
			User u = new User();
			u.setId(rs.getInt("id"));
			u.setBirth(rs.getDate("birth"));
			u.setName(rs.getString("name"));
			listU.add(u);
		}
		
		return listU;
	}
	
	public List<User> getUsersByCondition(User user) throws SQLException {
//		------- Create list User
		List <User> users = new ArrayList();
//		------- JDBC
		String sqlCallProcedure = "{Call getUsersByCondition(?,?,?)}";
		CallableStatement callStatement = con.prepareCall(sqlCallProcedure);
		callStatement.setInt(1, user.getId());
		callStatement.setString(2, user.getName());
		callStatement.setDate(3, user.getBirth());
		ResultSet rs = callStatement.executeQuery();
		
		while(rs.next()) {
			User u = new User();
			u.setId(rs.getInt("id"));
			u.setName(rs.getString("name"));
			u.setBirth(rs.getDate("birth"));
			users.add(u);
		}
		
		return users;
	}
	
	public boolean edit(User u) {
		boolean result = true;
		
		String sqlEdit = "Update Users set name = ? , birth =? where id =?";
		PreparedStatement p;
		try {
			p = con.prepareStatement(sqlEdit);
			p.setString(1, u.getName());
			p.setDate(2, u.getBirth());
			p.setInt(3, u.getId());
			
			p.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean delete(User u) {
		boolean result = false;
		String sqlDelete = "Delete from Users where (:id is null or id = :id)";
		return result;
	}
	
	public static void main (String []args) {
		try {
			List<User> x = new userDAO().getAll();
			System.out.println(x.get(0).getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

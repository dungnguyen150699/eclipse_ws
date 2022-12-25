package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.User;

public class findAllUserDAO extends dao {
	
	public findAllUserDAO() throws SQLException, ClassNotFoundException {
		super();
	}
	
    public List<User> Find_All() throws SQLException{
        List <User> arr = new ArrayList();
        String sql = "Select * from user";
        PreparedStatement p = con.prepareStatement(sql);
        ResultSet rs = p.executeQuery();
        while(rs.next()){
            User u = new User();
            u.setID(rs.getInt("id"));
            u.setNAME(rs.getString("name"));
            u.setBIRTHPLACE(rs.getString("birthplace"));
            u.setBIRTHDAY(rs.getDate("birthday"));
            arr.add(u);
        }
        return arr;
    }
}

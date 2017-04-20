package auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.MySQLUtil;

public class Auth {
	public static boolean login(String username, String password) {
		Connection conn = MySQLUtil.getConnection(); 
		String sql = "SELECT * FROM user where username = ? and password = ?";
		boolean isAuthenticated = false;
		try {  
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);  
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();  
            if (rs.next()){  
            	isAuthenticated = true;
            }  
            rs.close();  

        } catch (Exception e) {  
            e.printStackTrace();  
        } finally{  
            MySQLUtil.closeConnection(conn);  
        }  
		return isAuthenticated;
	}
}

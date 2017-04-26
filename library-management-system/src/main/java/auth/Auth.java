package auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

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
			JOptionPane.showMessageDialog(null, "登录过程中数据库出错！", "系统信息", JOptionPane.PLAIN_MESSAGE);
            e.printStackTrace();  
        } finally{  
            MySQLUtil.closeConnection(conn);  
        }  
		return isAuthenticated;
	}
}

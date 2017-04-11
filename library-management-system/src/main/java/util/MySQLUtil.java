package util;
import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLUtil {
    public static Connection getConnection(){  
        Connection conn = null;  
        try {  
//            Class.forName("com.mysql.jdbc.Driver");  
//        	Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/library?serverTimezone=UTC&useSSL=false";  
            conn = DriverManager.getConnection(url, "root", "yhq");  
        } catch (Exception e) {  
        	System.out.println("数据库连接错误！");
            e.printStackTrace();  
        }  
        return conn;  
    }  
    
    public static void closeConnection(Connection conn){  
        if(conn != null){  
            try {  
                conn.close();    
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    } 
    
	public static void main(String[] args) {
		Connection conn = MySQLUtil.getConnection(); 
		String sql = "SELECT * FROM user where username = ? and password = ?";
		try {  
			PreparedStatement ps = conn.prepareStatement(sql);
			String username = "yanghongquan";
			String password = "yhq";
			ps.setString(1, username);  
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();  
            if (rs.next()){  
            	System.out.println(rs.getString("username") + "\t" + rs.getString("password"));   	
            }  
            rs.close();  

        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            MySQLUtil.closeConnection(conn);  
        }  
	}

}

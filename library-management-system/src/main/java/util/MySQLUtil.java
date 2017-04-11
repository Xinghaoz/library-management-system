package util;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;

public class MySQLUtil {
    public static Connection getConnection(){  
        Connection conn = null;  
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
            String url = "jdbc:mysql://localhost:3306/db_database";  
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
		// TODO Auto-generated method stub
		Connection conn = MySQLUtil.getConnection(); 
	}

}

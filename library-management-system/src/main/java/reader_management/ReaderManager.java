package reader_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.MySQLUtil;

public class ReaderManager {
	public static List<Reader> getAll() {
		List<Reader> result = new ArrayList<>();
		Connection conn = MySQLUtil.getConnection();
		String sql = "SELECT * FROM reader;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(); 
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String studentNumber = rs.getString("student_number");

				Reader reader = new Reader(id, name, gender, studentNumber);
				result.add(reader);
			}
		} catch (Exception e) {  
            e.printStackTrace();  
        } finally{  
            MySQLUtil.closeConnection(conn);  
        }
		return result;
	}
	
	public static int add(Reader reader) {
		Connection conn = MySQLUtil.getConnection(); 
		String sql = "SELECT * FROM reader WHERE id = " + reader.getId() + ";";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();  
	        if (rs.next()){  
	        	return 1;
	        } 
	        sql = "INSERT INTO reader VALUES (?, ?, ?, ?);";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, reader.getId());  
            ps.setString(2, reader.getName());
            ps.setString(3, reader.getGender());
            ps.setString(4, reader.getStudentNumber());
	        ps.execute();
		} catch (Exception e) {  
            e.printStackTrace();  
            return 2;
        } finally{  
            MySQLUtil.closeConnection(conn);  
        }		
		return 0;
	}
	
	public static boolean delete(String id) {
		Connection conn = MySQLUtil.getConnection();
		String sql = "SELECT * FROM reader WHERE id = " + id + ";";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();  
			if (rs.next()) {
				sql = "DELETE FROM reader WHERE id = " + id + ";";
				ps = conn.prepareStatement(sql);
				ps.execute();  
				return true;
			} else return false;
		} catch (Exception e) {  
            e.printStackTrace();  
        } finally{  
            MySQLUtil.closeConnection(conn);  
        }		
		return false;
	}
}

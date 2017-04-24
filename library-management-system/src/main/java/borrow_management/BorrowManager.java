package borrow_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.MySQLUtil;

public class BorrowManager {
	public static List<Borrow> getAll() {
		List<Borrow> result = new ArrayList<>();
		Connection conn = MySQLUtil.getConnection();
		String sql = "SELECT * FROM borrow;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(); 
			while (rs.next()) {
				String bookId = rs.getString("book_id");
				String readerId = rs.getString("reader_id");
				Date borrowDate = (Date) rs.getDate("borrow_date");
//				Date returnDate = rs.getDate("return_date");

				Borrow borrow = new Borrow(bookId, readerId, borrowDate);
				result.add(borrow);
			}
		} catch (Exception e) {  
            e.printStackTrace();  
        } finally{  
            MySQLUtil.closeConnection(conn);  
        }
		return result;
	}
	
	public static int add(String bookId, String readerId) {
		Connection conn = MySQLUtil.getConnection(); 
		String sql = "SELECT * FROM book WHERE id = " + bookId + ";";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();  
	        if (!rs.next()){  
	        	return 1;
	        } 
	        
	        sql = "SELECT * FROM reader WHERE id = " + readerId + ";";
	        ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();  
	        if (!rs.next()){  
	        	return 2;
	        }   
	        
	        sql = "SELECT * FROM borrow WHERE book_id = " + bookId + " AND reader_id = " + readerId + ";";
	        ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();  
	        if (rs.next()){  
	        	return 3;
	        } 
	        
	        int result = checkCanBorrow(conn, readerId);
	        if (result < 0) return result;
	        
	        sql = "INSERT INTO borrow VALUES(?, ?, now());";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, bookId);  
            ps.setString(2, readerId);
            ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return 4;
		}		
		return 0;
	}
	
	public static int add(String bookId, String readerId, String borrowDate) {
		Connection conn = MySQLUtil.getConnection(); 
		String sql = "SELECT * FROM book WHERE id = " + bookId + ";";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();  
	        if (!rs.next()){  
	        	return 1;
	        } 
	        
	        sql = "SELECT * FROM reader WHERE id = " + readerId + ";";
	        ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();  
	        if (!rs.next()){  
	        	return 2;
	        }    
	        sql = "SELECT * FROM borrow WHERE book_id = " + bookId + " AND reader_id = " + readerId + ";";
	        ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();  
	        if (rs.next()){  
	        	return 3;
	        } 
	        
	        int result = checkCanBorrow(conn, readerId);
	        if (result < 0) return result;
	        
	        sql = "INSERT INTO borrow VALUES(?, ?, " + "DATE(\"" + borrowDate + "\"));";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, bookId);  
            ps.setString(2, readerId);
            ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return 4;
		}		
		return 0;
	}
	
	public static int returnBook(String bookId, String readerId) {
		Connection conn = MySQLUtil.getConnection(); 
		String sql = "SELECT * FROM book WHERE id = " + bookId + ";";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();  
	        if (!rs.next()){  
	        	return 1;
	        } 
	        
	        sql = "SELECT * FROM reader WHERE id = " + readerId + ";";
	        ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();  
	        if (!rs.next()){  
	        	return 2;
	        }    
	        
	        sql = "SELECT * FROM borrow WHERE book_id = " + bookId + " AND reader_id = " + readerId + ";";
	        ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();  
	        if (!rs.next()){  
	        	return 3;
	        } 
	        
	        sql = "DELETE FROM borrow WHERE book_id = ? AND reader_id = ?;";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, bookId);  
            ps.setString(2, readerId);
            ps.execute();
	      
//	        
//	        sql = "UPDATE borrow SET return_date = now() WHERE book_id = ? AND reader_id = ?;";
//	        ps = conn.prepareStatement(sql);
//	        ps.setString(1, bookId);  
//            ps.setString(2, readerId);
//            ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			return 4;
		}		
		return 0;
	}
	
//	public static int returnBook(String bookId, String readerId, String borrowDate) {
//		Connection conn = MySQLUtil.getConnection(); 
//		String sql = "SELECT * FROM book WHERE id = " + bookId + ";";
//		try {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();  
//	        if (!rs.next()){  
//	        	return 1;
//	        } 
//	        
//	        sql = "SELECT * FROM reader WHERE id = " + readerId + ";";
//	        ps = conn.prepareStatement(sql);
//			rs = ps.executeQuery();  
//	        if (!rs.next()){  
//	        	return 2;
//	        }    
//	        sql = "SELECT * FROM borrow WHERE book_id = " + bookId + " AND reader_id = " + readerId + ";";
//	        ps = conn.prepareStatement(sql);
//			rs = ps.executeQuery();  
//	        if (!rs.next()){  
//	        	return 3;
//	        } 
//	        
//	        sql = "UPDATE borrow SET return_date = DATE(\"?\") WHERE book_id = ? AND reader_id = ?;";
//	        ps = conn.prepareStatement(sql);
//	        ps.setString(1, bookId);  
//            ps.setString(2, readerId);
//            ps.setString(3, borrowDate);
//            ps.execute();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return 4;
//		}		
//		return 0;
//	}
	
	public static int checkCanBorrow(Connection conn, String readerId) {
        String sql = "SELECT COUNT(*) AS count FROM borrow WHERE reader_id = ?;";
        try {
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, readerId);
	        ResultSet rs = ps.executeQuery();  	        
	        if (rs.next()){  
	        	int number = Integer.valueOf(rs.getString("count"));
	        	if (number == 3) return -1;
	        } 
	        
	        sql = "SELECT * FROM borrow WHERE reader_id = ? AND DATE(now()) - borrow_date > 15;";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, readerId);
	        rs = ps.executeQuery();
	        if (rs.next()){  
	        	return -2;
	        } 
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return 0;
	}
}

package book_management;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import util.MySQLUtil;

public class BookManager {
	public static boolean add(Book book) {
		Connection conn = MySQLUtil.getConnection(); 
		String sql = "SELECT * FROM book WHERE id = " + book.getId() + ";";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();  
	        if (rs.next()){  
	        	return false;
	        } 
	        sql = "INSERT INTO book VALUES (?, ?, ?, ?, ?, ?, ?);";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, book.getId());  
            ps.setString(2, book.getName());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getPublisher());
            ps.setDate(5, (Date) book.getDate());
            ps.setFloat(6, book.getPrice());
            ps.setString(7, book.getCategory());
	        ps.execute();
		} catch (Exception e) {  
            e.printStackTrace();  
        } finally{  
            MySQLUtil.closeConnection(conn);  
        }		
		return true;
	}
	
	public static boolean delete(String id) {
		Connection conn = MySQLUtil.getConnection();
		String sql = "SELECT * FROM book WHERE id = " + id + ";";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();  
			if (rs.next()) {
				sql = "DELETE FROM book WHERE id = " + id + ";";
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
	
	public static List<Book> getAll() {
		List<Book> result = new ArrayList<Book>();
		Connection conn = MySQLUtil.getConnection();
		String sql = "SELECT * FROM book;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(); 
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date date = (Date) rs.getDate("date");
				float price = rs.getFloat("price");
				String category = rs.getString("category");
				Book book = new Book(id, name, author, publisher, date, price, category);
				result.add(book);
			}
		} catch (Exception e) {  
            e.printStackTrace();  
        } finally{  
            MySQLUtil.closeConnection(conn);  
        }
		return result;
	}
	
	public static Book get(String queryId) {
//		List<Book> result = new ArrayList<Book>();
		Connection conn = MySQLUtil.getConnection();
		String sql = "SELECT * FROM book WHERE id = " + queryId + ";";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(); 
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date date = (Date) rs.getDate("date");
				float price = rs.getFloat("price");
				String category = rs.getString("category");
				Book book = new Book(id, name, author, publisher, date, price, category);
//				result.add(book);
				return book;
			}
		} catch (Exception e) {  
            e.printStackTrace();  
        } finally{  
            MySQLUtil.closeConnection(conn);  
        }
		return null;
	}
	
	public static void modify(String id, Book book) {
		Connection conn = MySQLUtil.getConnection();
		String sql = "DELETE FROM book WHERE id = " + id + ";";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute(); 
			sql = "INSERT INTO book VALUES (?, ?, ?, ?, ?, ?, ?);";
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, book.getId());  
            ps.setString(2, book.getName());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getPublisher());
            ps.setDate(5, (Date) book.getDate());
            ps.setFloat(6, book.getPrice());
            ps.setString(7, book.getCategory());
            ps.execute();
		} catch (Exception e) {  
            e.printStackTrace();  
        } finally{  
            MySQLUtil.closeConnection(conn);  
        }
	}
	
	public static List<Book> search(String key, String value) {
		List<Book> result = new ArrayList<>();
		Connection conn = MySQLUtil.getConnection();
		String sql = "SELECT * FROM book WHERE " + key + " LIKE \"%" + value + "%\";";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(); 
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date date = (Date) rs.getDate("date");
				float price = rs.getFloat("price");
				String category = rs.getString("category");
				Book book = new Book(id, name, author, publisher, date, price, category);
				result.add(book);
			}
			
		} catch (Exception e) {  
            e.printStackTrace();  
        } finally{  
            MySQLUtil.closeConnection(conn);  
        }
		return result;
	}
	
	public static List<Book> searchPrice(String from, String to) {
		List<Book> result = new ArrayList<>();
		try {
			Float.valueOf(from);
			Float.valueOf(to);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "输入不合法！", "系统信息", JOptionPane.PLAIN_MESSAGE);
			return result;
		}
		
		Connection conn = MySQLUtil.getConnection();
		String sql = "SELECT * FROM book WHERE price >= " + from + " && price <= " + to + ";";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(); 
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				Date date = (Date) rs.getDate("date");
				float price = rs.getFloat("price");
				String category = rs.getString("category");
				Book book = new Book(id, name, author, publisher, date, price, category);
				result.add(book);
			}
			
		} catch (Exception e) {  
            e.printStackTrace();  
        } finally{  
            MySQLUtil.closeConnection(conn);  
        }
		return result;
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		List<Book> list = BookManager.getAll();
		List<Book> list = BookManager.search("author", "li");
		for (Book b : list) {
			System.out.println(b);
			System.out.println();
		}
		Book newBook = new Book("256", "Book256", "One", "Z", new Date(0), (float) 256.256, "Fight");
//		BookManager.add(newBook);
		BookManager.modify("256", newBook);
	}

}

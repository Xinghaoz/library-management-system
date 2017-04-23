package reader_management;

import java.util.Date;
import java.util.List;

import book_management.Book;

public class Reader {
	private String id;
	private String name;
	private String gender;
	private String studentNumber;
	
	public Reader(String id, String name, String gender, String studentNumber) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.studentNumber = studentNumber;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

}

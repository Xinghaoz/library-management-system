package book_management;

import java.util.Date;

public class Book {
	private String id;
	private String name;
	private String author;
	private String publisher;
	private Date date;
	private float price;
	private String category;
	
	public Book() {}
	
	public Book(String id, String name, String author, String publisher, Date date, float price, String category) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.date = date;
		this.price = price;
		this.category = category;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String toString() {
		String dateString = "";
		return "id = " + this.id + "\n" + 
						   "name = " + this.name + "\n" +
						   "author = " + this.author + "\n" +
						   "publisher = " + this.publisher + "\n" +
						   "date = " + (date == null ? "null" : this.date.toString()) + "\n" +
						   "price = " + this.price + "\n" +
						   "category = " + this.category;
	}
}

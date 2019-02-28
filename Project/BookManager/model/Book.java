package liang.model;

public class Book {
	
	private String ISBN;
	private String type;
	private String title;
	private String publisher;
	private int publish_year;
	private String author;
	private float price;
	private int number;
	private int stock;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String ISBN, String type, String title, String publisher, int publish_year, String author, Float price,
			int number, int stock) {
		super();
		this.ISBN = ISBN;
		this.type = type;
		this.title = title;
		this.publisher = publisher;
		this.publish_year = publish_year;
		this.author = author;
		this.price = price;
		this.number = number;
		this.stock = stock;
	}

	public Book(String type, String title, String publisher, int publish_year, String author, Float price, int number,
			int stock) {
		super();
		this.type = type;
		this.title = title;
		this.publisher = publisher;
		this.publish_year = publish_year;
		this.author = author;
		this.price = price;
		this.number = number;
		this.stock = stock;
	}
	
	public Book(String[] book_information) {
		this.ISBN = book_information[0];
		this.type = book_information[1];
		this.title = book_information[2];
		this.publisher = book_information[3];
		this.publish_year = Integer.valueOf(book_information[4]);
		this.author = book_information[5];
		this.price = Float.valueOf(book_information[6]);
		this.number = Integer.valueOf(book_information[7]);
		this.stock = Integer.valueOf(book_information[7]);
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPublish_year() {
		return publish_year;
	}

	public void setPublish_year(int publish_year) {
		this.publish_year = publish_year;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
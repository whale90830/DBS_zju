package liang.model;

import java.util.Date;

public class Record {

	public static final int TIMELIMIT = 30;
	
	private String book_ISBN;
	private int card_id;
	private java.util.Date borrow_date;
	private java.util.Date return_date;
	private int manager_id;
	
	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 不允许用户自定义日期
	 * @param book_ISBN
	 * @param card_id
	 * @param borrow_date
	 * @param manager_id
	 */
	public Record(String book_ISBN, int card_id, int manager_id) {
		super();
		this.book_ISBN = book_ISBN;
		this.card_id = card_id;
		this.manager_id = manager_id;
	}

	public String getBook_ISBN() {
		return book_ISBN;
	}

	public void setBook_ISBN(String book_ISBN) {
		this.book_ISBN = book_ISBN;
	}

	public int getCard_id() {
		return card_id;
	}

	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}

	public java.util.Date getBorrow_date() {
		return borrow_date;
	}

	public void setBorrow_date(java.util.Date borrow_date) {
		this.borrow_date = borrow_date;
	}

	public java.util.Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(java.util.Date return_date) {
		this.return_date = return_date;
	}

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

}

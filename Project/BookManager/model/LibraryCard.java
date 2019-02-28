package liang.model;

public class LibraryCard {

	private int card_id;
	private String username;
	private String company;
	private String type;
	
	public LibraryCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LibraryCard(int card_id, String username, String company, String type) {
		super();
		this.card_id = card_id;
		this.username = username;
		this.company = company;
		this.type = type;
	}

	public LibraryCard(String username, String company, String type) {
		super();
		this.username = username;
		this.company = company;
		this.type = type;
	}
	
	public LibraryCard(int card_id) {
		super();
		this.card_id = card_id;
	}

	public int getCard_id() {
		return card_id;
	}

	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}

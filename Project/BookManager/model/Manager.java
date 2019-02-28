package liang.model;

public class Manager {

	private int manager_id;
	private String name;
	private String password;
	private String connection;
	
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Manager(int manager_id, String name, String password, String connection) {
		super();
		this.manager_id = manager_id;
		this.name = name;
		this.password = password;
		this.connection = connection;
	}

	public Manager(String name, String password, String connection) {
		super();
		this.name = name;
		this.password = password;
		this.connection = connection;
	}
	
	public Manager(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	
	public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}
	
}

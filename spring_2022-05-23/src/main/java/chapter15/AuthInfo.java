package chapter15;

public class AuthInfo {
	private long id;
	private String email;
	private String name;
	
	public AuthInfo(long id, String email, String name) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

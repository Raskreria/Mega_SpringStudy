package chapter15;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import exception.WrongIdPasswordException;

public class Member {
	
	private long id;
	private String email;
	@JsonIgnore
	private String password;
	private String name;
	// JsonFormat 애노테이션은 서버가 클라이언트에게 데이터를 보낼 때 이와같은 형식으로 변환해서 보냄.
	// 또한 클라이언트가 서버로 데이터를 보냈을 때 이와 같은 형식으로 보냈다면 멤버변수의 데이터 타입에 맞게 변환해서 저장함.
//	@JsonFormat(pattern = "yyyyMMddHHmmss")
	private LocalDateTime registerDateTime;

	public Member(long id,String email, String password, String name, LocalDateTime registerDateTime) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDateTime = registerDateTime;
	}
	
	public Member(String email, String password, String name, LocalDateTime registerDateTime) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDateTime = registerDateTime;
	}
	public void changePassword(String oldPw, String newPw) throws WrongIdPasswordException{
		if(!password.equals(oldPw)) {
			throw new WrongIdPasswordException();
		}
		
		password= newPw;
	}
	
	public boolean matchPassword(String password) {
		return this.password.equals(password);
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getRegisterDateTime() {
		return registerDateTime;
	}
	public void setRegisterDateTime(LocalDateTime registerDateTime) {
		this.registerDateTime = registerDateTime;
	}
	
	
}

package chapter05;

public class Client2{

	private String host;
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void send() {
		System.out.println("send to : " + host);
	}
	
	public void connect() {
		System.out.println("connect 메서드 호출");
	}
	public void close() {
		System.out.println("close 메서드 호출");
	}

	
	
}

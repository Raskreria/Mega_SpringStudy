package chapter01;
// 클래스에 싱글톤을 적용하려면
// 클래스의 인스턴스를 마음대로 생성할 수 없게 막아둬야함.
// -> 생성자를 private으로 만든다.
//		-> 인스턴스를 생성할 수 없는 생성자.
//
public class Singleton {
	private static Singleton instance = null;
	
	//생성자를 private으로 한다.
	private Singleton() {
		
	}
	
	public static Singleton getInstance() {
		if(instance == null) {
			instance = new Singleton();
		}
		
		return instance;
	}
}

package chapter05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = {"chapter05"})
//설정 클래스 Context
public class Context {
	
	// 내가 지정한 메서드로 생성과 소멸에 관여할 때는
	// 생성, 소멸시 호출되는 메서드에 매개변수가 반드시 없어야함.
	@Bean(initMethod="connect", destroyMethod="close")
	@Scope("prototype")
	public Client2 client2() {
		Client2 client2 = new Client2();
		
		client2.setHost("localhost");
		
		return client2;
	}
	
	
}

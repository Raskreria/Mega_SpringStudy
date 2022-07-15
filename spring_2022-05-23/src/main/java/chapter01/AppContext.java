package chapter01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 이 클래스를 스프링 설정 클래스로 지정하는 애노테이션
@Configuration
public class AppContext {
	
	@Bean
	public Greeter greeter() {
		Greeter g = new Greeter();
		g.setFormat("%s, 드디어 시작!");
		return g;
	}
	@Bean
	public Greeter greeter1() {
		Greeter g = new Greeter();
		g.setFormat("%s, 드디어 시작!");
		return g;
	}
	@Bean
	public Greeter greeter2() {
		Greeter g = new Greeter();
		g.setFormat("%s, 드디어 시작!");
		return g;
	}
	
}

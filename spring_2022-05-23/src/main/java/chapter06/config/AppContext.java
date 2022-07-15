package chapter06.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import chapter06.Calculator;
import chapter06.ImplCalculator1;
import chapter06.aspect.CacheAspect;
import chapter06.aspect.ExecTimeAspect;


// 스프링은 AOP를 적용할 때 프록시 디자인 패턴을 사용해서 적용한다.
// AOP가 적용된 핵심 기능을 가지고 있는 클래스의 타입은 우리 모르게 $Proxy~~ 타입의 빈으로 등록됨.

// 스프링에서는 인터페이스를 많이 활용함
// AOP가 적용된 핵심기능을 가지고 있는 인터페이스가 일반적임.
// 하지만 AOP가 적용된 핵심기능을 가지고 있는 클래스를 빈으로 등록하고 싶다면
// @EnableAspectJAutoProxy(proxyTargetClass = ture)로 사용해야함.

@Configuration
@EnableAspectJAutoProxy
// execution 명시자를 해석해서 두 빈을 연결시키기 위한 애노테이션
public class AppContext {
	
	@Bean
	public ExecTimeAspect execTimeAspect() {
		return new ExecTimeAspect();
	}
	
	@Bean
	public CacheAspect cacheAspect() {
		return new CacheAspect();
	}
	@Bean
	public Calculator calculator() {
		return new ImplCalculator1();
	}
	

	
}

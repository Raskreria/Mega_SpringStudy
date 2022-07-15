package chapter04;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 자동 의존 주입을 적용하려면 컨테이너가 자동 의존 주입 타입의 빈을 가지고 있어야함.
// 자동 의존 주입을 할 때는 자동 의존 주입 대상 객체의 타입을 봄
// 이 클래스를 스프링 설정 클래스로 지정하는 애노테이션
@Configuration
@ComponentScan(
		basePackages = {"chapter04"}
		)
public class AppContext2 {
	@Bean
	public DateTimeFormatter dtf() {
		return DateTimeFormatter.ofPattern("yyyy년 MM일 dd일 HH시 mm분 ss초");
	}
	
//	컴포넌트 스캔의 대상이 되는 빈과
//	수동으로 등록한 빈의 이름이 같아서 충돌이 발생하면
//	예외는 발생하지 않고 수동으로 등록한 빈이 살아남음
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	@Qualifier("printer2")
	public MemberSummaryPrinter memberSummaryPrinter() {
		return new MemberSummaryPrinter();
	}
	

	
	@Bean
	public MemberListPrinter memberListPrinter() {
		return new MemberListPrinter();
	}
	
	@Bean
	public MemberInfoPrinter memberInfoPrinter() {
		MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();
		
		return memberInfoPrinter;
	}

	
}

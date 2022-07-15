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
		basePackages = {"chapter04"},
//		excludeFilters = @Filter(type = FilterType.REGEX, pattern = "chapter04\\..*Dao")
		excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = MemberDao.class)
		//속성 = 속성값
		)
public class AppContext {
	@Bean
	public DateTimeFormatter dtf() {
		return DateTimeFormatter.ofPattern("yyyy년 MM일 dd일 HH시 mm분 ss초");
	}
	
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

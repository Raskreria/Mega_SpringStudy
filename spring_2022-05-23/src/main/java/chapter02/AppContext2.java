package chapter02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext2 {
	// 자동 의존 주입 애노테이션
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberPrinter memberPrinter;
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao);
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao);
		return pwdSvc;
	}
	
	@Bean
	public MemberListPrinter memberListPrinter() {
		return new MemberListPrinter(memberDao, memberPrinter );
		
	}
	
	@Bean
	public MemberInfoPrinter memberInfoPrinter() {
		MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();
//		memberInfoPrinter.setMemberDao( memberDao );
//		memberInfoPrinter.setMemberPrinter( memberPrinter );
		
		return memberInfoPrinter;
	}

}

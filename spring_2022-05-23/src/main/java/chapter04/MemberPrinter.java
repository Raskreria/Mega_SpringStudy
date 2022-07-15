package chapter04;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component("memberPrinter")
public class MemberPrinter {
	// 자동 의존 주입이 선택사항일 때 멤버 변수에 이와 같이 붙일 수 있음.
	// @Autowired(requied = flase)
	// 또는
	// @Autowired
	// @Nullable
	
//	@Autowired(required = false)
//	@Nullable
	private DateTimeFormatter dtf;
	
	public void print(Member member) {
		String text1 = "아이디=" + member.getId();
		String text2 = "이메일=" + member.getEmail();
		String text3 = "이름=" + member.getName();
		String text4 = "등록일=" + member.getRegisterDateTime();
		
		if(dtf != null) {
			// dtf가 null이 아니라면 dtf에  설정한 날짜 형식으로 등록일을 보여주겠다.
			text4 = "등록일=" + dtf.format(member.getRegisterDateTime());
		}
		
		String text = "회원 정보 : " + text1 + ", " + text2 + ", " + text3 + ", " + text4;
		
		System.out.println(text);
		
	}
	// required = false => 자동의존주입이 필수가 아니게 됨.
	// 컨테이너안에 해당 타입(DateTimeFormatter)의 빈이있으면 의존 주입하고 아니라면 의존주입하지 않게 됨.
	@Autowired(required = false)
	public void setDateTimeFormmater(DateTimeFormatter dtf) {
		this.dtf = dtf;
	}
	
//	//@Nullable => 자동 의존 주입이 선택사항이 됨.
//	@Autowired
//	public void setDateTimeFormmater(@Nullable DateTimeFormatter dtf) {
//		this.dtf = dtf;
//	}
}

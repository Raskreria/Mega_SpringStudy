package chapter07;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import exception.MemberNotFoundException;
import exception.WrongIdPasswordException;

public class Ex02 {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		ChangePasswordService cps = ctx.getBean("changePasswordService", ChangePasswordService.class);
		try {
			cps.changePassword("a@gmail.com", "4321", "1234");
			
			System.out.println("비밀번호 변경 성공");
		}catch(MemberNotFoundException e) {
			System.out.println("비밀번호 변경 실패");
			System.out.println("이메일을 확인하세요.");
			
		}catch(WrongIdPasswordException e) {
			System.out.println("비밀번호 변경 실패");
			System.out.println("기존 비밀번호를 확인하세요");
		}
		
		ctx.close();
		
	}

}

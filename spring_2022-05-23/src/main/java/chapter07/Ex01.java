package chapter07;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex01 {
	private static MemberDao memberDao;
	
	public static void main(String[] args) {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		// appcontext를 사용해서 컨테이너 하나 만들기
		memberDao = ctx.getBean("memberDao", MemberDao.class);
		
		// =====Dao를 활용하는 코드를 넣어보자=====
		
		// 가입된 모든 회원 정보 조회
		selectAll();
		
		// 회원 가입
//		join();

		// 회원 정보 수정 이용해보기.
		updateMember();

		// 가입된 모든 회원 정보 조회
		selectAll();

		
		
		
		ctx.close();
		
		
	}
	private static void selectAll() {
		System.out.println("------ SelectAll ------");
		int total = memberDao.count();
		System.out.println("전체 가입자 수 : " +total);
		Collection<Member> members = memberDao.selectAll();
		if(members != null) {
			for(Member member : members) {
				System.out.println(member.getId() + ", "+ member.getEmail() + ", " + member.getName());
			}
		}
		System.out.println();
	}
	private static void join() {
		System.out.println("------ Join ------");
		String email = "b@gmail.com";
		String password = "1234";
		String name = "고영희";
		LocalDateTime registerDateTime = LocalDateTime.now();
		
		Member newMember = new Member(email,password,name,registerDateTime);
		
		memberDao.insert(newMember);
		
		
	}
	private static void updateMember() {
		System.out.println("----- updateMember -----");
		
		Member member = memberDao.selectByEmail("a@gmail.com");
		
		String oldPw = member.getPassword();
		String newPw = "4321";
		member.changePassword(oldPw, newPw);
		
		member.setName("김철수");
		
		memberDao.update(member);
		
	}
}

package chapter07;

public class MemberSummaryPrinter extends MemberPrinter {

	@Override
	public void print(Member member) {
		String text1 = "이메일=" + member.getEmail();
		String text2 = "이름=" + member.getName();
		String text = "회원 정보 : " + text1 + ", " + text2;
		System.out.println(text);

	}

}

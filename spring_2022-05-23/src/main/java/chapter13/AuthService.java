package chapter13;

import exception.WrongIdPasswordException;

public class AuthService {
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		
	}
	
	public AuthInfo authenticate(String email, String password) {
		Member member = memberDao.selectByEmail(email);
		
		if(member ==null) {
			throw new WrongIdPasswordException();
		}
		
		if(!member.matchPassword(password)) {
			throw new WrongIdPasswordException();
		}
		
		return new AuthInfo(member.getId(),member.getEmail(),member.getName());
		//아이디와 비밀번호를 제대로 입력했다면 로그인정보를 생성하도록 한 것.
	}
}

package chapter02;

import exception.MemberNotFoundException;
import exception.WrongIdPasswordException;

public class ChangePasswordService {
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void changePassword(String email, String oldPw,String newPw) throws MemberNotFoundException, WrongIdPasswordException{
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		
		member.ChangePassword(oldPw, oldPw);
		
		memberDao.update(member);
	}
}

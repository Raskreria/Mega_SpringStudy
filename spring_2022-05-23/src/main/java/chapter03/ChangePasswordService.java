package chapter03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import exception.MemberNotFoundException;
import exception.WrongIdPasswordException;

@Component
public class ChangePasswordService {
	@Autowired
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

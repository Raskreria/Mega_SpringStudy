package chapter07;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import exception.DuplicateMemberException;
public class MemberRegisterService {
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public long regist(RegisterRequest req) throws DuplicateMemberException{
		// 이메일로 회원 정보 조회
		Member member = memberDao.selectByEmail(req.getEmail());
		
		if(member != null) {
			// 같은 이메일을 가진 회원 이존재한다면 예외 발생
			throw new DuplicateMemberException("이미 사용 중인 이메일 입니다.");
		}
		//같은 이메일을 가진 회원이 존재하지 않으면 DB에 저장
		Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
		
		memberDao.insert(newMember);
		return newMember.getId();
	}
}

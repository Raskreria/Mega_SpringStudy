package chapter13;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MemberDetailController {
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		
	}
	
	@GetMapping("/member/{id}/detail")
	public String detail(@PathVariable("id") long memberId, Model model) {
		
		Member memberInfo = memberDao.selectById(memberId);
		
		if(memberInfo == null) {
			return "member/404";
		}
		
		model.addAttribute("memberInfo", memberInfo);
		
		return "member/detail";
	}

	@ExceptionHandler(TypeMismatchException.class)
	public String handlerTypeMismatchException(TypeMismatchException e) {
		
		return "member/invalid";
	}
	
}

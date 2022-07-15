package chapter15;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import exception.WrongIdPasswordException;

@Controller
@RequestMapping("/edit")
public class ChangePwdController {
	private ChangePasswordService changePwdSvc;

	public ChangePwdController(ChangePasswordService changePwdSvc) {
		this.changePwdSvc = changePwdSvc;
	}

	@GetMapping("/changePassword")
	public String changePwd(@ModelAttribute("command") ChangePasswordRequest changePasswordRequest) {
		// 세션에 들어 있는 로그인 상태 정보를 꺼내기
		
		return "edit/changePwdForm";
	}

	@PostMapping("/changePassword")
	public String submit(@ModelAttribute("command") ChangePasswordRequest changePwdReq, Errors errors,
			HttpSession session) {
		new ChangePasswordRequestValidator().validate(changePwdReq, errors);
		if (errors.hasErrors()) {
			return "edit/changePwdForm";
		}

		// 비밀번호 변경 코드
		// - 1. 세션에 들어있는 로그인 상태 정보 꺼내기
		// 1-1 : 로그인 상태정보를 무슨이름으로 저장하는지 로그인 컨트롤러에서 확인한뒤 세션에서 꺼내기
		AuthInfo authInfo;
		if(session.getAttribute("authInfo") == null) {
			return "redirect:/login";
		}
		authInfo = (AuthInfo) session.getAttribute("authInfo");
		// - 2. 로그인 상태 정보의 이메일, 커맨드 객체의 현재 비번, 바꿀 비번을 사용해서
		// - 3. 비밀번호 변경
		try {
			changePwdSvc.changePassword(authInfo.getEmail(), changePwdReq.getCurrentPassword(),changePwdReq.getNewPassword());
			
			return "edit/changedPwdSuccess";
		} catch (WrongIdPasswordException e) {
			errors.rejectValue("currentPassword", "NotMatching");
			return "edit/changePwdForm";
		}

	}

}

package chapter13;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import exception.WrongIdPasswordException;

@Controller
@RequestMapping("/login")
public class LoginController {
	private AuthService authService;

	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	@GetMapping
	public String form(LoginRequest loginRequest, @CookieValue(value="rememberEmail", required=false) Cookie cookie) {
		
		if(cookie != null) {
			loginRequest.setEmail(cookie.getValue());
			loginRequest.setRememberEmail(true);
		}
		return "login/loginForm";
	}

	@PostMapping
	public String submit(LoginRequest loginRequest, Errors errors, HttpSession session, HttpServletResponse response) {

		
		new LoginRequestValidator().validate(loginRequest, errors);

		if (errors.hasErrors()) {
			return "login/loginForm";
		}
		try {
			AuthInfo authInfo = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
			
			session.setAttribute("authInfo", authInfo);
			//1. 이메일 기억하기를 눌렀다면
			if(loginRequest.getRememberEmail( )){
				// 2.  이름이 rememberEmail인 쿠키에 로그인한 사용자의 이메일을 추가
				// 쿠키 지속시간 30일
				Cookie cookie = new Cookie("rememberEmail",authInfo.getEmail());
				cookie.setMaxAge(60*60*24*30);
				
				response.addCookie(cookie);
			}
			
			return "login/loginSuccess";
			
		} catch (WrongIdPasswordException e) {
			errors.reject("idPasswordNotMatching");

			return "login/loginForm";
		}
	}

}

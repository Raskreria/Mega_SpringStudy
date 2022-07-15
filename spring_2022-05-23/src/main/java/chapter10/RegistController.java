package chapter10;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import exception.DuplicateMemberException;

//GETMapping, PostMapping, ... 애노테이션 -> 스프링 4.3버전에서 추가된 것
//스프링 4.3 미만 버전에서는 @RequestMapping 밖에 없음.
@Controller
@RequestMapping("/register")
public class RegistController {
	private MemberRegisterService memberRegSvc;
	
	public RegistController(MemberRegisterService memberRegSvc) {
		this.memberRegSvc = memberRegSvc;
	}
	
	// 이 경로로 들어오는 GET 요청을 처리
	@GetMapping("/step1")
	public String handlerStep1() {
		return "register/step1";
	}

	// 이 경로로 들어오는 POST 요청을 처리
	@PostMapping("/step2")
	public String handlerStep2(Model model, @RequestParam(value="agree", defaultValue="false") boolean agree) {
		if(!agree) {
			return "register/step1";
		}
		
		//step2.jsp 에서 formData라는 이름의 RegisterRequest 타입 커맨드 객체가 필요해 전달해주는 코드
		model.addAttribute("formData", new RegisterRequest());
		
		return "register/step2";
	}
	
	@GetMapping("/step2")
	public String handlerStep2Get() {
		// 뷰 페이지를 반환하면 RequestDispatcher의 forward방식으로 이동함.
		return "redirect:/register/step1";
	}
	
	
	//이 경로로 들어오는 모든 요청 처리
	@RequestMapping(value="/step3", method=RequestMethod.POST)
	public String handlerStep3(@ModelAttribute("formData") RegisterRequest regReq) {
		//클라이언트가 보내는 데이터를 꺼내서 출력하세요

		//...
		
		// DB를 활용한 회원 가입
		
		// ...
		try {
			memberRegSvc.regist(regReq);
			return "register/step3";
		}catch(DuplicateMemberException e) {
			return "register/step2";	
		}
		
	}
	
}


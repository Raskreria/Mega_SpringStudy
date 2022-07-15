package chapter12;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
	public String handlerStep3(@Valid @ModelAttribute("formData") RegisterRequest regReq, Errors errors) {
		
		if(errors.hasErrors()) {
			// 커맨드 객체 값 검증에 실패 했다면 ( 클라이언트가 올바르지 않은 값을 전달했다면 )
			return "register/step2";
		}else {
			// 커맨드 객체 값 검증에 성공 했다면
			// DB를 활용한 회원 가입
			try {
				memberRegSvc.regist(regReq);
				return "register/step3";
			}catch(DuplicateMemberException e) {
				errors.rejectValue("email","duplicate");
				
				
//				// 커맨드 객체 자체에 문제가 있다 라고 에러 코드를 남기고 싶다면
//				// errors.reject() 메서드를 사용하면 된다.
//				// 이 메서드를 사용해서 에러 코드를 남기면 그 에러 코드는 '글로벌 에러코드' 라고 부름.
//				errors.reject("worng id or pw");
				
				return "register/step2";	
			}
		}
	}
	
	@GetMapping("login")
	public String form(Model model){

	List<String> loginTypes = new ArrayList<>();

	loginTypes.add("일반회원");

	loginTypes.add("가입회원");

	loginTypes.add("헤드헌터회원");
	

	 

	model.addAttribute("loginTypes", loginTypes);

	 

	return "login/form";

	}
	
	// 단일 컨트롤러 범위의 Validator란
	// 해당 컨트롤러 안에서 자주 사용되는 Validator의 경우
	// 필요할 때 마다 선언하면 불편하므로
	// 해당 컨트롤러 한정으로 글로벌 Validator처럼 사용할 수 있는 Validator
	
//	@InitBinder // 단일 컨트롤러 범위의 Validator를 설정할 대 사용하는 애노테이션
//	protected void initBinder(WebDataBinder binder) {
//		binder.setValidator(new RegisterRequestValidator());
//		
//	}
	
	// 지금 우리는 RegisterRequestValidator 탕딥의 Validator가 
	// 글로벌 Validator로 등록되어있고
	// 단일 컨트롤러 Validator로도 등록되어있음.
	// Q. 글로벌 범위의 validator가 먼저 동작할까? 단일 컨트롤러 범위의 validator가 먼저 동작할까?
	//  -> 우리가 단일 컨트롤러 Validator를 등록할 때 serValidator() 메서드를 사용해서 등록했으므로
	//     단일 컨트롤러 Validator만 동작한다.
	// setValidator() 메서드 : 기존에 등록한 Validator들을 모두 삭제하고 새로운 Vaildator를 등록하는 메서드
	// 	
	// 글로벌 범위의 Vaildator도 동작하고
	// 단일 컨트롤러 Validator도 동작하도록 하려면
	// addVaildator() 메서드를 사용해서 단일 컨트롤러 Validator를 등록해야함.
	
	
}


















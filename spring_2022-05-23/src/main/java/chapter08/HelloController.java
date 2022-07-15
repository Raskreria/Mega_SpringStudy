package chapter08;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
//스프링 MVC가 요청을 처리할 컨트롤러로 사용
public class HelloController {

	// http://서버IP:포트번호/프로젝트path/hello 로 들어온 GET방식 요청을 처리하겠다. 라는 애노테이션
	// 서블릿에서 @WebServlet("/hello") 했던 것 처럼
	@GetMapping("/hello")
	public String hello(Model model, @RequestParam(value = "name", required=false) String name) {
		//** 이 메서드가 반환한 값은 HandlerAdapter를 통해 ModelAndView로 변환돼서 전달 됨.
		//** 아래와 같이 Model 매개변수에 값을 저장하면
		//** 뷰 ( hello.jsp )가 전달 받게 되고
		//** 모델을 전달 받은 뷰는 request.setAttribute("greeting", name + " 님 안녕하세요~!"); 로 변환해서
		//** request 영역에 저장함.
		//** 그래서 뷰에서는 Model에 담겨있는 값을 request 영역 안에서 꺼내 쓸 수 있음
		//** ( 컨트롤러가 전달한 값을 뷰에서 꺼내서 쓸 수 있음 )
		model.addAttribute("greeting", name + " 님 안녕하세요~!");
		
		return "hello";
	}
	
}

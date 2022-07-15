package chapter15;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	// 이 컨트롤러는 단순히 요청이 들어왔을 때 뷰를 전달해주는 컨트롤러
	// 이런 컨트롤러가 많아지면 프로젝트 규모가 불필요하게 커져보이고 구조가 복잡해짐
	// 요청이 들어왔을 때 단순히 뷰만 전달하는 컨트롤러는 MvcConfig 설정 클래스 안에
	// addViewControllers() 메서드를 오버라이딩해서 간단하게 구현할 수 있음.
	@GetMapping("/main")
	public String main() {
		return "index";
	}
}

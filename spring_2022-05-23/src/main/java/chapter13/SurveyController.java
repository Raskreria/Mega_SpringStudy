package chapter13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/survey")
public class SurveyController {

//	@GetMapping
//	public String form(Model model) {
//		
//		List<Question> questions = createQuestions();
//		
//		model.addAttribute("questions", questions);
//		
//		return "survey/form";
//	}
	
	@GetMapping
	public ModelAndView form() {
		List<Question> questions = createQuestions();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("questions", questions);
		mav.setViewName("survey/form");
		
		return mav;
	}
	
	private List<Question> createQuestions(){
		List<String> job = new ArrayList<>();
		job.add("서버"); job.add("프론트"); job.add("풀스택");
		
		List<String> tool = new ArrayList<>();
		tool.add("이클립스"); tool.add("인텔리-제이"); tool.add("VSCode");
		
		Question q1 = new Question("당신의 역할은 무엇입니까?", job);
		Question q2 = new Question("많이 사용하는 개발도구는 무엇입니까?", tool);
		Question q3 = new Question("하고싶은 말이 있으신가요?", null);
		

		
		return Arrays.asList(q1,q2,q3);
	}
	
	@PostMapping
	public String submit(@ModelAttribute("ansData") AnsweredData ansData) {
		return  "survey/submitted";
	}
	
}

package chapter15;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import exception.DuplicateMemberException;
import exception.MemberNotFoundException;

// 뷰 대신 JSON을 리턴할 컨트롤러
@RestController
public class MemberListController {
	private MemberDao memberDao;
	private MemberRegisterService memberRegSvc;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void setMemberRegSvc(MemberRegisterService memberRegSvc) {
		this.memberRegSvc = memberRegSvc;
	}

	@GetMapping("/members")
	public List<Member> list(@ModelAttribute("cmd") ListRequest listRequest, Errors errors,
			HttpServletResponse response) {
		new MemberListRequestValidator().validate(listRequest, errors);
		if (errors.hasErrors()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		List<Member> resultList = memberDao.selectByRegdate(listRequest.getFrom(), listRequest.getTo());

		return resultList;
	}

	// 컨트롤러의 동작 결과를 뷰로 반환하지 말고 JSON으로 반환하도록 수정하세요.
	@GetMapping("/member/{id}/detail")
	public ResponseEntity<Object> detail(@PathVariable("id") long memberId, HttpServletResponse response) {
		Member memberInfo = memberDao.selectById(memberId);
		if (memberInfo == null) {
			throw new MemberNotFoundException();
//			return ResponseEntity.status(HttpStatus.NOT_FOUND)
//					.body(new ResponseMessage("not found"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(memberInfo);
	}

	@PostMapping("/member")
	public ResponseEntity<Object> newMember(@RequestBody RegisterRequest regReq, HttpServletResponse response) {
		try {
			long newMemberId = memberRegSvc.regist(regReq);
			// #sendredirect라는 메서드가 어떤 동작을 하는지 알아보자
//			response.sendRedirect("http://localhost/spring_2022-05-23/member/" + newMemberId+"/detail");

//			response.setHeader("Location", "http://localhost/spring_2022-05-23/member/" + newMemberId+ "/detail");
//			response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);

			ResponseEntity<Object> re = ResponseEntity.status(HttpServletResponse.SC_MOVED_TEMPORARILY)
					.header("Location", "http://localhost/spring_2022-05-23/member/" + newMemberId + "/detail").build();
			return re;
		} catch (DuplicateMemberException e) {

			// sendError 로 상태코드를 응답하면 상태 코드만 응답되는게 아니라
			// 서버 설정에 따라서 상태코드에 맞는 기본 에러 페이지가 같이 응답될 수 있다.
//				response.sendError(HttpServletResponse.SC_CONFLICT);
			
			ResponseEntity<Object> re = ResponseEntity
					.status(HttpServletResponse.SC_BAD_REQUEST)
					.contentType(MediaType.TEXT_PLAIN)
					.body("already used email");
			return re;
		}
	}
	
	@ExceptionHandler(MemberNotFoundException.class)
	public ResponseEntity<Object> handleNotFound(){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseMessage("not found"));
	}
	
}
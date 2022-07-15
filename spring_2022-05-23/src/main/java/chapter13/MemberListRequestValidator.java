package chapter13;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
//member/list페이지에서 listRequest커맨드 객체를 검증할 validator
public class MemberListRequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ListRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "from", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "to", "required");
		
		
		
	}

}

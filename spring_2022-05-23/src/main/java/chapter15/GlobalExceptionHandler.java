package chapter15;

import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("chapter13")
public class GlobalExceptionHandler {
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handlerTypeMismatchException(TypeMismatchException e) {
		
		return "member/invalid";
	}
	
	@ExceptionHandler(NullPointerException.class)
	public String handlerNullPointerException() {
		return "~~~";
	}
	
}

package exception;

public class DuplicateMemberException extends RuntimeException {
	
	//예외 사유를 생성자를 통해서 만듬
	public DuplicateMemberException(String msg) {
		super(msg);
	}

}

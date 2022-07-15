package chapter06.aspect;
import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

// @Aspect 애노테이션이 적용된 클래스는
// Advice와 Pointcut을 함께 구현해야함
@Aspect
@Order(2)
public class ExecTimeAspect {
	// @Pointcut 애노테이션은 공통 기능을 적용할 대상을 설정하는 애노테이션
	// 지금 쓴 execution 명시자의 의미 : 
	//			chapter06 패키지에 위치한 모든 클래스 중에서 접근제어자가 public인 모든 메서드가 실행될 때 동작할 공통 기능이다. 라고 설정
	@Pointcut("execution(public * chapter06.*.*(..))")
	private void publicTarget() {
	}
	// Around Advice : 핵심기능이 실행되기 전/후(Around)에 사용될 공통 기능(Aspect) 이다. 
	@Around("publicTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{
		// Pointcut에 명시한 메서드가 동작하기 전 시간 측정
		long start = System.nanoTime();
	
		try {
			// 다음 Pointcut이 동작하거나 핵심 기능이 동작함.
			Object result = joinPoint.proceed();
			// 핵심기능이 return을 할 수도 있으니 핵심 기능이 return 하는 값이 있다면 return도 할 수 있도록 한 것.
			return result;
				
		}finally {
			// Pointcut에 명시한 메서드가 동작 한 후 시간 측정.
			long finish = System.nanoTime();
			
			// getSignatur() -> 핵심 기능(메서드)의 정보를 가지고 있는 객체를 반환
			// 자바의 시그니쳐 라는 용어 -> 메서드 이름, 매개변수.
			Signature sig = joinPoint.getSignature();
			// getTarget() -> 핵심 기능을 가지고 있는 클래스 정보 반환.
			String simpleName = joinPoint.getTarget().getClass().getSimpleName();
			// getName() -> 핵심 기능의 이름을 반환
			String name = sig.getName();
			// getArgs() -> 매개변수로 전달한 값(인자) 목록 
			String args = Arrays.toString(joinPoint.getArgs());
			// Pointcut에 명시한 메서드의 실행 시간.
			long duration = finish - start;
			
			System.out.println(simpleName + "." + name + "(" + args + ") 실행시간 => " + duration );
		}
	}
}

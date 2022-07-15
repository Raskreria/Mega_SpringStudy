package chapter06.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class CacheAspect {
	private Map<Long, Object> cache = new HashMap<>();
	
	@Pointcut("execution(public * chapter06.*.*(..))")
	public void cacheTarget() {
		
	}
	
	@Around("cacheTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		Long num = (Long) joinPoint.getArgs()[0];
		// 핵심기능에 전달된 매개변수 첫번째를 꺼내서
		if(cache.containsKey(num)) {
			//첫번째 매개변수를 키값으로 찾았을 때 있다면 핵심기능 실행없이 return
			long result = (long) cache.get(num);
			
			System.out.println("CacheAspect: Cache에서 "+ num + "key 의 값 " + result + " 찾음");
			
			return result;
		}
		
		//키값으로 찾았을 때 없다면 핵심기능을 실행하고 return
		Object result = joinPoint.proceed();
		
		cache.put(num, result);
		
		System.out.println("CacheAspect: Cache에 key = " + num + ", value = " + result + " 추가" );
		
		return result;
		
	}
}

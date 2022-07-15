package chapter01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex01 {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		Greeter g1 = ctx.getBean("greeter", Greeter.class);
		Greeter g2 = ctx.getBean("greeter", Greeter.class);
		
		String msg = g1.greet("스프링");
		
		System.err.println(msg);
		
		System.out.println(g1 == g2);
		
		ctx.close();
		
		
	}

}

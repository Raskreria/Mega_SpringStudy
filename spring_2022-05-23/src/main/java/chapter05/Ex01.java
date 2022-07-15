package chapter05;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex01 {

	public static void main(String[] args) {
		
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Context.class);
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Context.class);
		Client2 client2 = ctx.getBean("client2", Client2.class);
		Client2 client3 = ctx.getBean("client2", Client2.class);
		
		System.out.println(client2==client3);
		
		
		ctx.close();
		
//		Client client = ctx.getBean("client", Client.class);
//		client.setHost("127.0.0.1");
//		client.send();
//		
//		
//		ctx.close();
//		
	}

}

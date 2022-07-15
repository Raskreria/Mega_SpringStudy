package chapter15;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/",".jsp");
	}
	

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}


	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/main").setViewName("index");
	}
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		
		ms.setBasename("message.label");
		ms.setDefaultEncoding("UTF-8");
		
		return ms;
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authCheckInterceptor()).addPathPatterns("/edit/**");
	}

	@Bean
	public AuthCheckInterceptor authCheckInterceptor(){
		return new AuthCheckInterceptor();
	}
	
	// 객체를 JSON으로 변환할 때 지정한 타입의 멤버변수는 모두 같은 규칙으로 변환되도록 할 수 있음
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//		스프링은 자바 객체를 JSON으로 변환할 때 HttpMessageConver를 사용
//		Jackson을 이용해서 자바 객체를 JSON으로 변환하면 MappingJackson2HttpMessageConverters를 사용함
//		아래 코드는 스프링이 기본적으로 사용하는 변환 규칙이 아니라 내가 원하는 규칙으로 사용하기 위해서 내가 새롭게 등록을 한 것.
//		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
//				.json()
//				.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
////				스프링은 날짜 타입 멤버 변수의 값을 유닉스 타임스탬프로 변환해 사용하는것이 기본 설정
////				 featuresToDisable() 메서드를 사용해서 기본 설정을 없앤 것
////				 그러면 스프링은 날짜 타입 멤버 변수의 값을 ISO-8601(yyyy-MM-ddTHH:mm:ss)형식으로 변환해 사용함
//				.build();
////		날짜를 유닉스 타임스탬프를 사용하는 것에서 비활성화를 함으로써 ISO-8601이라는 형식으로 바꿈.
		
		//내가 지정한 형식으로 변환 되도록 하려면 serializerByType() 메서드를 호출하면 됨.
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
		
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
				.json()
				.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(dtf))
				.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(dtf))
				.build();
		
		converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
	}

	
//	@Override
//	public Validator getValidator() {
//		return new RegisterRequestValidator();
//	}

	
}

































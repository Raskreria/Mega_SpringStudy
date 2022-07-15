package chapter07;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class AppContext {
	// 스프링이 제공하는 DB연동 기능을 활용하려면 DataSorce 타입 빈을 컨테이너에 등록해야함.
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		// DataSource라고 불리우는 커넥션 풀 생성.
		DataSource ds = new DataSource();
		
		ds.setDriverClassName("org.mariadb.jdbc.Driver");
		ds.setUrl("jdbc:mariadb://localhost:3306/spring_2022-06-02");
		ds.setUsername("root");
		ds.setPassword("1234");
		
		//커넥션 풀이 생성될 때 준비할 연결의 개수
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		//커넥션 풀을 반납하지 않았을 때 n초 후 자동 반납이 되도록 설정
		
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		
		return tm;
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
	
	@Bean
	public ChangePasswordService changePasswordService() {
		ChangePasswordService changePasswordService = new ChangePasswordService();
		changePasswordService.setMemberDao(memberDao());
		
		return changePasswordService;
	}
}

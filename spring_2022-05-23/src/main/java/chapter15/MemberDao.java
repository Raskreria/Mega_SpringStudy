package chapter15;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
public class MemberDao {

	// 스프링은 쿼리를 실행하고 결과를 가져오기 위해서 JDBCTemplate을 사용함.
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate =new JdbcTemplate(dataSource);
		
	}
	
	public Member selectById(long memberId) {
		List<Member> results = jdbcTemplate.query("SELECT * FROM member WHERE memberNumber = ?", new MemberMapper(), memberId);
		
		return results.isEmpty() ? null : results.get(0);
	}
	
	public List<Member> selectByRegdate(LocalDateTime from, LocalDateTime to){
		List<Member> results = jdbcTemplate.query("SELECT * FROM member WHERE regdate BETWEEN ? AND ? ORDER BY regdate DESC", new MemberMapper(), from, to);
		return results;
	}
	
	
	public Member selectByEmail(String email) {
		String sql = "SELECT * FROM member WHERE email = ?";
		
		//첫번째 인자 sql , 두번째 인자 RowMapper, 세번째 인자 인덱스
		//리턴 값 List
		//쿼리를 실행하고 실행결과를 어떻게 할지 정하고 , 인덱스가 있다면 인덱스를 넣어주고.
		List<Member> results = jdbcTemplate.query(sql, new MemberMapper(), email);
		return results.isEmpty() ? null : results.get(0);
		//조회한 회원정보가 없으면 null 리턴 있으면 조회한 회원정보 리턴
	}
	public void insert(Member member) {

//		jdbcTemplate.update("INSERT INTO member('email', 'password', 'name','regdate' ) VALUES(?, ?, ?, ?)",
//				member.getEmail(), member.getPassword(), member.getName(), Timestamp.valueOf(member.getRegisterDateTime()));
//		
//		// 회원 가입 후 가입한회원의 번호를 알고 싶다.
//		int memberNumber = jdbcTemplate.queryForObject("SELECT memberNumber FROM member WHERE email = ?",Integer.class, member.getEmail());
//		//가입을 하고 가입한 회원이 무조건 있으니까 queryForObject메서드를 활용하기
//		
//		System.out.println("가입한 사용자의 번호는 " + memberNumber + "번입니다.");
//		
		// ====== [KeyHolder를 사용해 회원 가입 후 가입한 회원의 번호를 알고 싶다. 를 해결 ] ======
		// KeyHolder를 사용해서 AUTO_INCREMENT 칼럼의 값을 알아내려면 반드시 PreparedStatement를 사용해서 INSERT 쿼리를 실행해야함.
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "INSERT INTO member(email, password, name, regdate) VALUES (?,?,?,?)";
				
				PreparedStatement pstmt = con.prepareStatement(sql, new String[] {"memberNumber"});
				pstmt.setString(1,member.getEmail());
				pstmt.setString(2,member.getPassword());
				pstmt.setString(3,member.getName());
				pstmt.setTimestamp(4,Timestamp.valueOf(member.getRegisterDateTime()));
				
				return pstmt;
			}
			
		}, keyHolder);
		
		Number keyValue = keyHolder.getKey();
		
		long memberNumber = keyValue.longValue();
		
		System.out.println("가입한 사용자의 번호는 " + memberNumber+ "번입니다.");
		
		member.setId(memberNumber);
		
	}
	public void update(Member member) {
		//SELECT나 UPDATE를 할 때 인덱스 파라미터는 세번째 인자부터 넣어줬음.
//		jdbcTemplate.update("UPDATE member SET name = ?, password = ? WHERE email = ?", member.getName(), member.getPassword(), member.getEmail());
		
		//jdbcTemplate을 사용해서 쿼리를 실행할 때 Servlet, JSP처럼 PreparedStatement를 사용할 수 있음.
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "UPDATE member SET name = ? , password = ?  WHERE email = ?";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, member.getName());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getEmail());
				
				return pstmt;
			}
			
		});
		
		// WHERE 조건에 맞는 데이턱 없어 UPDATE 쿼리가 정보를 수정하지 못했다면
		// EmptyResultDateAccessException 예외가 발생함.
	}
	
	public Collection<Member> selectAll() {
		String sql = "SELECT * FROM member";
		Collection<Member> results = jdbcTemplate.query(sql, new MemberMapper());
		
		return results;
	}
	
	
	//회원 가입한 사용자들의 수
	public int count() {
		String sql = "SELECT COUNT(*) FROM member";
		//queryForObject() 메서드를 사용했을 때 쿼리 실행 결과가 없다면
		
		//queryForObject() 메서드가 EmptyResultDateAccessException 예외를 발생시킴
		//queryForOnject메서드는 실행결과가 반드시 있는 SELECT에서만 사용하는 게 좋음
		//지금은 count니까 오류가 안발생하는데 만약 쿼리가 SELECT name FROM member WHERE email = ? 이고
		//int count = jdbcTemplate.queryForObject(sql, String.class, email);라면 결과가 없을 수 도 있겠지?
		
		int count  = jdbcTemplate.queryForObject(sql, Integer.class);		
		return count;
	}
}

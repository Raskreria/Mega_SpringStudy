package chapter12;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

public class MemberMapper implements RowMapper<Member>{

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		//rs.next할 필요가 없음.
		String email = rs.getString("email");
		String password = rs.getString("password");
		String name = rs.getString("name");
		LocalDateTime regdate = rs.getTimestamp("regdate").toLocalDateTime();

		Member member = new Member(email, password, name, regdate);
		
		return member;
	}
	

}

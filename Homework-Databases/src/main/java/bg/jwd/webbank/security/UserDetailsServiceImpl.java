package bg.jwd.webbank.security;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import bg.jwd.webbank.constants.DatabaseConstants;

public final class UserDetailsServiceImpl implements UserDetailsService {

	public static final GrantedAuthority BANK_EMPLOYEE_AUTHORITY = new SimpleGrantedAuthority("ROLE_BANK_EMPLOYEE");

	private static final GrantedAuthority USER_AUTHORITY = new SimpleGrantedAuthority("ROLE_USER");
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(String username) {

		String role = null;
		String password = null;
		String sql = String.format("SELECT * FROM users WHERE username='%s'", username);
		try (Connection connection = DriverManager.getConnection(DatabaseConstants.URL, DatabaseConstants.USERNAME,
				DatabaseConstants.PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);) {

			resultSet.next();
			password = resultSet.getString("password");
			role = resultSet.getString("role");
		} catch (SQLException e) {
			logger.error("Getting user details from database failed because of SQL Exception", e);
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(USER_AUTHORITY);

		if ("ROLE_BANK_EMPLOYEE".equals(role)) {
			authorities.add(BANK_EMPLOYEE_AUTHORITY);
		}

		return new UserDetailsImpl(username, password, authorities);
	}
}

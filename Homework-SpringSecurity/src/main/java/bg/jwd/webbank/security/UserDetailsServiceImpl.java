package bg.jwd.webbank.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public final class UserDetailsServiceImpl implements UserDetailsService {

	public static final GrantedAuthority BANK_EMPLOYEE_AUTHORITY = new SimpleGrantedAuthority("ROLE_BANK_EMPLOYEE");

	private static final String BANK_EMPLOYEE = "Spiro";

	@Override
	public UserDetails loadUserByUsername(String username) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		if (BANK_EMPLOYEE.equals(username)) {
			authorities.add(BANK_EMPLOYEE_AUTHORITY);
		}

		return new UserDetailsImpl(username, "e10adc3949ba59abbe56e057f20f883e", authorities);
	}
}

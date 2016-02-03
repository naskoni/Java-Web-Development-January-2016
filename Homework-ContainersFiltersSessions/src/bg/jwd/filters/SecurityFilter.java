package bg.jwd.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class SecurityFilter implements Filter {

	private static final String USER_ATTR = "username";
	private static final String HOME_PAGE = "/Homework-ContainersFiltersSessions/pages/HomePage.jsp";
	private static final String LOGIN_PAGE = "/Homework-ContainersFiltersSessions/pages/LoginForm.jsp";
	
	private final String user = "Pesho";	
	private final String password = "123";	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;			
					
		String requestUsername = req.getParameter("user");
		String requestPassword = req.getParameter("pwd");
			
		if (user.equals(requestUsername) && password.equals(requestPassword)) {			
			request.getSession().setAttribute(USER_ATTR, requestUsername);
			request.getSession().setMaxInactiveInterval(60);				
			
			response.sendRedirect(HOME_PAGE);
		} else {			
			if (request.getSession().getAttribute(USER_ATTR) != null) {				;
				chain.doFilter(req, res);
			} else {
				HttpSession session = request.getSession(false);
				String loginURL = request.getContextPath() + "/pages/LoginForm.jsp";
				
				boolean isLogginRequest = loginURL.equals(request.getRequestURI());
				boolean loggedIn = session != null && session.getAttribute(USER_ATTR) != null;
				if (loggedIn || isLogginRequest) {
					chain.doFilter(req, res);			
				} else {
					response.sendRedirect(LOGIN_PAGE);
				}	
			}
		}		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}

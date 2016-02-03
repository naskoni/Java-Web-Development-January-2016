package bg.jwd.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class SecurityFilter implements Filter {

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
			request.getSession().setAttribute("username", requestUsername);
			request.getSession().setMaxInactiveInterval(60);
			
			Cookie cookie = new Cookie("user", requestUsername);
			cookie.setMaxAge(60);			
			response.addCookie(cookie);
			
			response.sendRedirect("/Homework-ContainersFiltersSessions/pages/HomePage.jsp");
		} else {			
			if (request.getSession().getAttribute("username") != null) {				;
				chain.doFilter(req, res);
			} else {
				HttpSession session = request.getSession(false);
				String loginURL = request.getContextPath() + "/pages/LoginForm.jsp";
				
				boolean isLogginRequest = loginURL.equals(request.getRequestURI());
				boolean loggedIn = session != null && session.getAttribute("username") != null;
				if (loggedIn || isLogginRequest) {
					chain.doFilter(req, res);			
				} else {
					response.sendRedirect("/Homework-ContainersFiltersSessions/pages/LoginForm.jsp");
				}	
			}
		}		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

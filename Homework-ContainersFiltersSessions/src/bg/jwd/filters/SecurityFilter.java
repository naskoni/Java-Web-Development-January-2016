package bg.jwd.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

	private final Map<String, String> users = new HashMap<>();	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		users.put("Pesho", "123");
		users.put("Gosho", "456");		
		
		String requestUsername = request.getParameter("user");
		String requestPassword = request.getParameter("pwd");
		boolean isInputValid = requestUsername != null 
				&& !requestUsername.isEmpty() 
				&& requestPassword != null
				&& !requestPassword.isEmpty();		
		if (isInputValid) {
			boolean userExistInDatabase = users.containsKey(requestUsername);
			boolean isPasswordCorrect =  users.get(requestUsername).equals(requestPassword);
			if (userExistInDatabase && isPasswordCorrect) {
				httpRequest.getSession().setAttribute("username", requestUsername);
				//System.out.println(httpRequest.getSession().getAttribute("username"));
				httpResponse.sendRedirect("/Homework-ContainersFiltersSessions/pages/HomePage.jsp");			
			}
		} else {			
			if (httpRequest.getSession().getAttribute("username") != null) {
				//System.out.println(httpRequest.getSession().getAttribute("username"));
				chain.doFilter(request, response);
			} else {
				HttpSession session = httpRequest.getSession(false);
				String loginURL = httpRequest.getContextPath() + "/pages/LoginForm.jsp";
				
				boolean isLogginRequest = loginURL.equals(httpRequest.getRequestURI());
				boolean loggedIn = session != null && session.getAttribute("username") != null;
				if (loggedIn || isLogginRequest) {
					chain.doFilter(request, response);			
				} else {
					httpResponse.sendRedirect("/Homework-ContainersFiltersSessions/pages/LoginForm.jsp");
				}	
			}
		}		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

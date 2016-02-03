package bg.jwd.listeners;

import java.util.Date;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class RequestLogger implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void requestInitialized(ServletRequestEvent event) {
		HttpServletRequest httpRequest = (HttpServletRequest) event.getServletRequest();
		
		System.out.println("IP address: " + httpRequest.getLocalAddr());		
		System.out.println("Session ID: " + httpRequest.getSession().getId());
		
		String methodName = httpRequest.getMethod().toLowerCase();
		String requestType = methodName.equals("post") ? "login attempt" : "normal request";		
		System.out.println("Request type: " + requestType);
		
		System.out.println("Date and time of the request: " + new Date().toLocaleString());
	}
}

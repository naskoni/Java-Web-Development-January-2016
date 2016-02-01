package bg.jwd.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Form
 */
@WebServlet("/Form")
public class Form extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Form() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
        out.println("<body bgcolor=\"yellow\">");
        out.println("<head>");
        out.println("<title> Problem 3 - Form (Servlet) </title>");
        out.println("</head>");
        out.println("</body>");
        
        // input fields
        out.println("ID: <input type=\"text\" name=\"ID\"><br>");
        out.println("<br>");
        out.println("Topic: <input type=\"text\" name=\"Topic\"><br>");
        out.println("<br>");
        out.println("Date: <input type=\"text\" name=\"Date\"><br>");
        
        // buttons
		out.println("<P>");
        out.print("<form action=\"");
        out.print("\" ");
        out.println("method=POST>");
        out.println("<br>");
        out.println("<input type=submit value=\"Submit\">");
        out.println("</form>");
        
        out.println("<P>");
        out.print("<form action=\"");
        out.print("\" ");
        out.println("method=POST>");
        out.println("<br>");
        out.println("<input type=submit value=\"Cancel\">");
        out.println("</form>");		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

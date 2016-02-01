package bg.jwd.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Table
 */
@WebServlet("/Table")
public class Table extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Table() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		printTable(request, response);
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void printTable(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<P ALIGN='left'><TABLE BORDER=1>");
		
		// table header
		out.println("<TR>");
		out.println("<TH>Topic</TH>");
		out.println("<TH>Date</TH>");
		out.println("</TR>");
		
		// table body
		out.println("<TR>");
		out.println("<TH>Web Development Basics</TH>");
		out.println("<TH>14.01.2016</TH>");
		out.println("</TR>");
		out.println("<TR>");
		out.println("<TH>Servlets and Pages</TH>");
		out.println("<TH>21.01.2016</TH>");
		out.println("</TR>");
		
		out.println("</TABLE></P>");
	}
}

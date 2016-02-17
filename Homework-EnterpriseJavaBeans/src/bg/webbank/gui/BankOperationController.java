package bg.webbank.gui;

import java.io.IOException;
import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.webbank.bussiness.WebBank;

@WebServlet("/BankOperationController")
public class BankOperationController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private WebBank webBank;

	public BankOperationController() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String selectedOperation = request.getParameter("operation");
		String selectedCurrency = request.getParameter("currency");

		if (selectedOperation != null) {
			BigDecimal amount = new BigDecimal(request.getParameter("amount"));
			if (selectedOperation.equals("Withdraw")) {
				webBank.withdraw(username, amount, selectedCurrency);
			} else if (selectedOperation.equals("Deposit")) {
				webBank.deposit(username, amount, selectedCurrency);
			}
		}

		request.getSession().setAttribute("username", username);
		request.getSession().setAttribute("currency", selectedCurrency);
		request.getSession().setAttribute("balance", webBank.getAccounts().get(username).getBalance());
		request.getRequestDispatcher("/WebBankingPage.jsp").forward(request, response);
	}
}

package bg.jwd.webbank.gui;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bg.jwd.webbank.business.WebBank;

@Controller
public class BankOperationController {

	private static final String BANKING_PAGE = "webBankingPage";
	private static final Logger logger = LoggerFactory.getLogger(BankOperationController.class);

	@Autowired
	private WebBank webBank;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loadWebBank() {
		return BANKING_PAGE;
	}

	@RequestMapping(value = "/webBankingPage", method = RequestMethod.POST)
	public String doBanking(Model model, HttpServletRequest request) {

		String username = request.getParameter("username");
		BigDecimal amount = new BigDecimal(request.getParameter("amount"));
		String selectedOperation = request.getParameter("operation");
		String selectedCurrency = request.getParameter("currency");

		if ("Withdraw".equals(selectedOperation)) {
			webBank.withdraw(username, amount, selectedCurrency);
		} else if ("Deposit".equals(selectedOperation)) {
			webBank.deposit(username, amount, selectedCurrency);
		}

		model.addAttribute("username", username);

		String accountCurrency = webBank.getAccounts().get(username).getCurrency();
		String currency = accountCurrency == null ? selectedCurrency : accountCurrency;
		model.addAttribute("currency", currency);

		BigDecimal roundedBalance = webBank.getAccounts().get(username).getBalance().setScale(2,
				BigDecimal.ROUND_HALF_UP);
		model.addAttribute("balance", roundedBalance);

		return BANKING_PAGE;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest request, Exception exception) {
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("errorMessage", exception.getMessage());
		modelAndView.setViewName("error");

		return modelAndView;
	}
}

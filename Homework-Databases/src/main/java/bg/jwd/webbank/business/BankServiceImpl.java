package bg.jwd.webbank.business;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.jwd.webbank.dao.OperationDao;

@Service
public final class BankServiceImpl implements BankService {

	private final CurrencyConverter currencyConverter;

	@Autowired
	private AccountsService accountsService;

	@Autowired
	private OperationDao operationDao;

	public BankServiceImpl() {
		this.currencyConverter = new CurrencyConverter();
	}

	@Override
	public void deposit(String username, String accountNumber, BigDecimal amount, String currency) {
		ensureAccountExist(accountNumber);
		validateAmount(amount);

		Account account = accountsService.getAllAccounts().stream()
				.filter(a -> a.getAccountNumber().equals(accountNumber)).findFirst().get();

		String accountCurrency = account.getCurrency();
		BigDecimal newAmount = getAmountInAccountCurrency(amount, currency, accountCurrency);

		account.deposit(newAmount);
		operationDao.addOperation("deposit", username, accountNumber, amount, currency);
	}

	@Override
	public void withdraw(String username, String accountNumber, BigDecimal amount, String currency) {
		ensureAccountExist(accountNumber);
		validateAmount(amount);

		Account account = accountsService.getAllAccounts().stream()
				.filter(a -> a.getAccountNumber().equals(accountNumber)).findFirst().get();

		String accountCurrency = account.getCurrency();
		BigDecimal newAmount = getAmountInAccountCurrency(amount, currency, accountCurrency);

		account.withdraw(newAmount);
		operationDao.addOperation("withdraw", username, accountNumber, amount, currency);
	}

	private BigDecimal getAmountInAccountCurrency(BigDecimal amount, String currency, String accountCurrency) {
		if (!accountCurrency.equals(currency)) {
			return currencyConverter.convert(amount, currency, accountCurrency);
		}

		return amount;
	}

	private void validateAmount(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("The amount of withdraw should be positive.");
		}
	}

	private void ensureAccountExist(String accountNumber) {
		if (!accountsService.getAllAccounts().stream().filter(a -> accountNumber.equals(a.getAccountNumber()))
				.findFirst().isPresent()) {
			throw new IllegalArgumentException("This account does not exist in database.");
		}
	}
}

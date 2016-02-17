package bg.webbank.bussiness;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public final class WebBankImpl implements WebBank {

	private Map<String, Account> accounts;
	private String lastUsername;
	private BigDecimal remainderAllowed;
	private BigDecimal halfBalance;
	private CurrencyConverter currencyConverter;

	public WebBankImpl() {
		this.accounts = new HashMap<String, Account>();
		this.currencyConverter = new CurrencyConverter();
	}

	@Override
	public Map<String, Account> getAccounts() {
		return accounts;
	}

	@Override
	public void withdraw(String username, BigDecimal amount, String currency) {
		if (!accounts.containsKey(username)) {
			throw new IllegalArgumentException("This client does not exist in database");
		}

		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("The amount of withdraw should be positive");
		}

		BigDecimal currentBalance = accounts.get(username).getBalance();
		boolean sameSession = username.equals(lastUsername);

		String accountCurrency = accounts.get(username).getCurrency();
		if (!accountCurrency.equals(currency)) {
			amount = currencyConverter.convert(amount, currency, accountCurrency);
		}

		if (lastUsername == null || !sameSession) {
			halfBalance = currentBalance.divide(new BigDecimal(2));
			if (amount.compareTo(halfBalance) > 0) {
				throw new IllegalArgumentException("The amount of withdraw cannot be more than halfbalance");
			}

			accounts.get(username).withdraw(amount);

			lastUsername = username;
			remainderAllowed = halfBalance.subtract(amount);
		} else {
			if (amount.compareTo(remainderAllowed) > 0) {
				throw new IllegalArgumentException(
						"The amount of withdraw per session cannot be more than halfbalance");
			}

			accounts.get(username).withdraw(amount);
			remainderAllowed = remainderAllowed.subtract(amount);
		}
	}

	@Override
	public void deposit(String username, BigDecimal amount, String currency) {
		if (amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("The amount of deposit should be positive");
		}

		if (!accounts.containsKey(username)) {
			accounts.put(username, new AccountImpl(username, amount, currency));
		} else {
			String accountCurrency = accounts.get(username).getCurrency();
			if (!accountCurrency.equals(currency)) {
				amount = currencyConverter.convert(amount, currency, accountCurrency);
			}

			accounts.get(username).deposit(amount);
		}
	}
}

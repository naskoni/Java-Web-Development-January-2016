package bg.jwd.webbank.business;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public final class WebBankImpl implements WebBank {

	private Map<String, Account> accounts;
	private String lastUsername;
	private BigDecimal remainderAllowed;
	private BigDecimal halfBalance;
	private CurrencyConverter currencyConverter;

	public WebBankImpl() {
		this.accounts = new HashMap<>();
		this.currencyConverter = new CurrencyConverter();
	}

	@Override
	public Map<String, Account> getAccounts() {
		return accounts;
	}

	@Override
	public void deposit(String username, BigDecimal amount, String currency) {
		validateAmount(amount);

		if (!accounts.containsKey(username)) {
			accounts.put(username, new AccountImpl(username, amount, currency));
		} else {
			String accountCurrency = accounts.get(username).getCurrency();
			BigDecimal newAmount = getAmountInAccountCurrency(amount, currency, accountCurrency);

			accounts.get(username).deposit(newAmount);
		}
	}

	@Override
	public void withdraw(String username, BigDecimal amount, String currency) {
		validateUsername(username);
		validateAmount(amount);

		BigDecimal currentBalance = accounts.get(username).getBalance();
		boolean sameSession = username.equals(lastUsername);

		String accountCurrency = accounts.get(username).getCurrency();
		BigDecimal newAmount = getAmountInAccountCurrency(amount, currency, accountCurrency);

		if (lastUsername == null || !sameSession) {
			halfBalance = currentBalance.divide(new BigDecimal(2));
			if (amount.compareTo(halfBalance) > 0) {
				throw new IllegalArgumentException("The amount of withdraw cannot be more than halfbalance.");
			}

			accounts.get(username).withdraw(newAmount);

			lastUsername = username;
			remainderAllowed = halfBalance.subtract(amount);
		} else {
			if (amount.compareTo(remainderAllowed) > 0) {
				throw new IllegalArgumentException(
						"The amount of withdraw per session cannot be more than halfbalance.");
			}

			accounts.get(username).withdraw(newAmount);

			remainderAllowed = remainderAllowed.subtract(amount);
		}
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

	private void validateUsername(String username) {
		if (!accounts.containsKey(username)) {
			throw new IllegalArgumentException("This client does not exist in database.");
		}
	}
}

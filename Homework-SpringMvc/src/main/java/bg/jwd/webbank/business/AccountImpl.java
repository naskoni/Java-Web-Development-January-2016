package bg.jwd.webbank.business;

import java.math.BigDecimal;

public final class AccountImpl implements Account {

	private final String username;
	private BigDecimal balance;
	private final String currency;

	public AccountImpl(String username, BigDecimal balance, String currency) {
		if (username == null || username.trim().isEmpty()) {
			throw new IllegalArgumentException("Username cannot be null or empty.");
		}

		this.username = username.trim();

		if (balance.compareTo(new BigDecimal(0)) <= 0) {
			throw new IllegalArgumentException("Balance should be positive.");
		}

		this.balance = balance;

		this.currency = currency;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public BigDecimal getBalance() {
		return balance;
	}

	@Override
	public String getCurrency() {
		return currency;
	}

	@Override
	public void withdraw(BigDecimal amount) {
		if (amount.compareTo(balance) > 0) {
			throw new IllegalArgumentException("The amount of withdraw cannot be more than balance");
		}

		balance = balance.subtract(amount);
	}

	@Override
	public void deposit(BigDecimal amount) {
		balance = balance.add(amount);
	}
}

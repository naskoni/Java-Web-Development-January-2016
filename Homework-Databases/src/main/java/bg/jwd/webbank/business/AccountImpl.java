package bg.jwd.webbank.business;

import java.math.BigDecimal;

public final class AccountImpl implements Account {

	private final String username;
	private final String accountNumber;
	private BigDecimal balance;
	private final String currency;
	private final String createdBy;

	public AccountImpl(String username, String accountNumber, BigDecimal balance, String currency, String createdBy) {
		validate(username);
		validate(accountNumber);
		validate(createdBy);

		if (balance.compareTo(new BigDecimal(0)) <= 0) {
			throw new IllegalArgumentException("Balance should be positive.");
		}

		this.username = username.trim();
		this.accountNumber = accountNumber.trim();
		this.balance = balance;
		this.currency = currency;
		this.createdBy = createdBy.trim();
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getAccountNumber() {
		return accountNumber;
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
	public String getCreatedBy() {
		return createdBy;
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

	private void validate(String str) {
		if (str == null || str.trim().isEmpty()) {
			throw new IllegalArgumentException(str + " cannot be null or empty.");
		}
	}
}

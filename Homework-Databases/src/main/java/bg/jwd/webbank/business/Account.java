package bg.jwd.webbank.business;

import java.math.BigDecimal;

public interface Account {

	String getUsername();

	String getAccountNumber();

	BigDecimal getBalance();

	String getCurrency();

	String getCreatedBy();

	void withdraw(BigDecimal amount);

	void deposit(BigDecimal amount);

}
package bg.webbank.bussiness;

import java.math.BigDecimal;

public interface Account {

	String getUsername();

	BigDecimal getBalance();

	String getCurrency();

	void withdraw(BigDecimal amount);

	void deposit(BigDecimal amount);

}
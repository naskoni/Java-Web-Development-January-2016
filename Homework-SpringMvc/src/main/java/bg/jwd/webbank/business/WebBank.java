package bg.jwd.webbank.business;

import java.math.BigDecimal;
import java.util.Map;

public interface WebBank {

	Map<String, Account> getAccounts();

	void withdraw(String username, BigDecimal amount, String currency);

	void deposit(String username, BigDecimal amount, String currency);
}

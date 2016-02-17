package bg.webbank.bussiness;

import java.math.BigDecimal;
import java.util.Map;

import javax.ejb.Local;

@Local
public interface WebBank {

	Map<String, Account> getAccounts();

	void withdraw(String username, BigDecimal amount, String currency);

	void deposit(String username, BigDecimal amount, String currency);
}

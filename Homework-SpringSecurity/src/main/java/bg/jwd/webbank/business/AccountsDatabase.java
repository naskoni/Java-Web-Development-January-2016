package bg.jwd.webbank.business;

import java.util.List;

public interface AccountsDatabase {

	List<Account> getAllAccounts();

	List<Account> getAccountsByUsername(String username);

	void addAccount(Account account);
}

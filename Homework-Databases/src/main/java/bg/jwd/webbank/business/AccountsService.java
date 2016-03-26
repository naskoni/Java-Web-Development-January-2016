package bg.jwd.webbank.business;

import java.util.List;

public interface AccountsService {

	List<Account> getAllAccounts();

	List<Account> getAccountsByUsername(String username);

	boolean addAccount(Account account);
}

package bg.jwd.webbank.dao;

import java.util.List;

import bg.jwd.webbank.business.Account;

public interface AccountDao {

	List<Account> getAllAccounts();

	boolean addAccount(Account account);

}

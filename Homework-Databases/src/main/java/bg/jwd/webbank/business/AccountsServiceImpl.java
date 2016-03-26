package bg.jwd.webbank.business;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.jwd.webbank.dao.AccountDao;

@Service
public final class AccountsServiceImpl implements AccountsService {

	@Autowired
	private AccountDao accountDao;

	@Override
	public boolean addAccount(Account account) {
		List<Account> allAccounts = accountDao.getAllAccounts();

		if (allAccounts.stream().anyMatch(a -> a.getAccountNumber().equals(account.getAccountNumber()))) {
			throw new IllegalArgumentException("This account already exist in database.");
		}

		return accountDao.addAccount(account);
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountDao.getAllAccounts();
	}

	@Override
	public List<Account> getAccountsByUsername(String username) {
		List<Account> allAccounts = accountDao.getAllAccounts();

		if (!allAccounts.stream().anyMatch(a -> a.getUsername().equals(username))) {
			throw new IllegalArgumentException("This user do not have any accounts.");
		}

		return allAccounts.stream().filter(a -> a.getUsername().equals(username)).collect(Collectors.toList());
	}
}

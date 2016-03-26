package bg.jwd.webbank.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class AccountsDatabaseImpl implements AccountsDatabase {

	private static final List<Account> accounts = new ArrayList<>();

	@Override
	public void addAccount(Account account) {
		if (accounts.stream().anyMatch(a -> a.getAccountNumber().equals(account.getAccountNumber()))) {
			throw new IllegalArgumentException("This account already exist in database.");
		}

		accounts.add(account);
	}

	@Override
	public List<Account> getAllAccounts() {
		return accounts;
	}

	@Override
	public List<Account> getAccountsByUsername(String username) {
		if (!accounts.stream().anyMatch((a -> a.getUsername().equals(username)))) {
			throw new IllegalArgumentException("This user do not have any accounts.");
		}

		return accounts.stream().filter(a -> a.getUsername().equals(username)).collect(Collectors.toList());
	}
}

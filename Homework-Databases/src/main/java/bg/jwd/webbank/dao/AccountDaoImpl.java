package bg.jwd.webbank.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import bg.jwd.webbank.business.Account;
import bg.jwd.webbank.business.AccountImpl;
import bg.jwd.webbank.constants.DatabaseConstants;

@Repository
public final class AccountDaoImpl implements AccountDao {

	private static final Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);
	private long idCounter;

	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			logger.error("Class oracle.jdbc.OracleDriver not found", e);
		}
	}

	@Override
	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<>();

		String sql = "SELECT * FROM account";
		try (Connection connection = DriverManager.getConnection(DatabaseConstants.URL, DatabaseConstants.USERNAME,
				DatabaseConstants.PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);) {

			while (resultSet.next()) {
				String username = resultSet.getString("username");
				String accountNumber = resultSet.getString("account_no");
				BigDecimal amount = resultSet.getBigDecimal("amount");
				String currency = resultSet.getString("currency");
				String createdBy = resultSet.getString("created_by");

				Account account = new AccountImpl(username, accountNumber, amount, currency, createdBy);
				accounts.add(account);
			}

		} catch (SQLException e) {
			logger.error("Getting all accounts from database failed because of SQL Exception", e);

			return Collections.emptyList();
		}

		idCounter = accounts.size();

		return accounts;
	}

	@Override
	public boolean addAccount(Account account) {
		String sql = "INSERT INTO account (account_id, account_no, username, amount, currency, created_by) values (?,?,?,?,?,?)";

		try (Connection connection = DriverManager.getConnection(DatabaseConstants.URL, DatabaseConstants.USERNAME,
				DatabaseConstants.PASSWORD); PreparedStatement statement = connection.prepareStatement(sql);) {

			statement.setLong(1, ++idCounter);
			statement.setString(2, account.getAccountNumber());
			statement.setString(3, account.getUsername());
			statement.setBigDecimal(4, account.getBalance());
			statement.setString(5, account.getCurrency());
			statement.setString(6, account.getCreatedBy());

			statement.executeQuery();

		} catch (SQLException e) {
			logger.error("Adding account to database failed because of SQL Exception", e);

			return false;
		}

		return true;
	}
}

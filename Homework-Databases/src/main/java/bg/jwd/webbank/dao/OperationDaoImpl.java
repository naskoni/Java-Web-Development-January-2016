package bg.jwd.webbank.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import bg.jwd.webbank.constants.DatabaseConstants;

@Repository
public class OperationDaoImpl implements OperationDao {

	private static final Logger logger = LoggerFactory.getLogger(OperationDaoImpl.class);

	private long idCounter;

	@Override
	public boolean addOperation(String operation, String username, String accountNumber, BigDecimal amount,
			String currency) {
		String sql = "INSERT INTO operation (operation_id, account_no, operation, amount, currency, performed_by) values (?,?,?,?,?,?)";

		try (Connection connection = DriverManager.getConnection(DatabaseConstants.URL, DatabaseConstants.USERNAME,
				DatabaseConstants.PASSWORD); PreparedStatement statement = connection.prepareStatement(sql);) {

			statement.setLong(1, ++idCounter);
			statement.setString(2, accountNumber);
			statement.setString(3, operation);
			statement.setBigDecimal(4, amount);
			statement.setString(5, currency);
			statement.setString(6, username);

			statement.executeQuery();

		} catch (SQLException e) {
			logger.error("Adding operation to database failed because of SQL Exception", e);

			return false;
		}

		return true;
	}

}

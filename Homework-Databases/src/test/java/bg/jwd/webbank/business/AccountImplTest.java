package bg.jwd.webbank.business;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class AccountImplTest {

	private static final String CURRENCY = "BGN";
	private static final String USER = "user";
	private static final String ACCOUNT_NUMBER = "1000";
	private static final String CREATED_BY = "admin";

	@Test
	public void testCreateAccount() {
		Account account = new AccountImpl(USER, ACCOUNT_NUMBER, BigDecimal.ONE, CURRENCY, CREATED_BY);

		Assert.assertEquals(USER, account.getUsername());
		Assert.assertEquals(ACCOUNT_NUMBER, account.getAccountNumber());
		Assert.assertEquals(BigDecimal.ONE, account.getBalance());
		Assert.assertEquals(CURRENCY, account.getCurrency());
		Assert.assertEquals(CREATED_BY, account.getCreatedBy());
	}

	@Test
	public void testCreateAccountTrimmedUsername() {
		Account account = new AccountImpl(" user	", ACCOUNT_NUMBER, BigDecimal.ONE, CURRENCY, CREATED_BY);

		Assert.assertEquals(USER, account.getUsername());
	}

	@Test
	public void testDepositNewAccountShouldIncreaseBalance() {
		Account account = new AccountImpl(USER, ACCOUNT_NUMBER, BigDecimal.ONE, CURRENCY, CREATED_BY);
		Assert.assertEquals(BigDecimal.ONE, account.getBalance());
	}

	@Test
	public void testWithdrawExistingAccountShouldDecreaseBalance() {
		Account account = new AccountImpl(USER, ACCOUNT_NUMBER, BigDecimal.TEN, CURRENCY, CREATED_BY);
		account.withdraw(BigDecimal.ONE);
		Assert.assertEquals(new BigDecimal(9), account.getBalance());
		account.withdraw(BigDecimal.ONE);
		Assert.assertEquals(new BigDecimal(8), account.getBalance());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithdrawMoreThanBalance() {
		Account account = new AccountImpl(USER, ACCOUNT_NUMBER, BigDecimal.ONE, CURRENCY, CREATED_BY);
		account.withdraw(BigDecimal.TEN);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAccountEmptyUsername() {
		new AccountImpl("", ACCOUNT_NUMBER, BigDecimal.ONE, CURRENCY, CREATED_BY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAccountWhitespaceUsername() {
		new AccountImpl(" ", ACCOUNT_NUMBER, BigDecimal.ONE, CURRENCY, CREATED_BY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAccountNullUsername() {
		new AccountImpl(null, ACCOUNT_NUMBER, BigDecimal.ONE, CURRENCY, CREATED_BY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAccountZeroBalance() {
		new AccountImpl(USER, ACCOUNT_NUMBER, BigDecimal.ZERO, CURRENCY, CREATED_BY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAccountNegativeBalance() {
		new AccountImpl(USER, ACCOUNT_NUMBER, new BigDecimal(-1), CURRENCY, CREATED_BY);
	}
}

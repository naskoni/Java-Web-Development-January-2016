package bg.webbank.bussiness;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class AccountImplTest {

	private static final String CURRENCY = "BGN";
	private static final String USER = "user";

	@Test
	public void testCreateAccount() {
		Account account = new AccountImpl("user", BigDecimal.ONE, CURRENCY);
		Assert.assertEquals(BigDecimal.ONE, account.getBalance());
		Assert.assertEquals(USER, account.getUsername());
	}

	@Test
	public void testCreateAccountTrimmedUsername() {
		Account account = new AccountImpl(" user	", BigDecimal.ONE, CURRENCY);
		Assert.assertEquals(BigDecimal.ONE, account.getBalance());
		Assert.assertEquals(USER, account.getUsername());
	}

	@Test
	public void testDepositNewAccountShouldIncreaseBalance() {
		Account account = new AccountImpl("user", BigDecimal.ONE, CURRENCY);
		Assert.assertEquals(BigDecimal.ONE, account.getBalance());
	}

	@Test
	public void testDepositExistingAccountShouldIncreaseBalance() {
		Account account = new AccountImpl(USER, BigDecimal.ONE, CURRENCY);
		account.deposit(BigDecimal.ONE);
		Assert.assertEquals(new BigDecimal(2), account.getBalance());
	}

	@Test
	public void testWithdrawExistingAccountShouldDecreaseBalance() {
		Account account = new AccountImpl(USER, BigDecimal.TEN, CURRENCY);
		account.withdraw(BigDecimal.ONE);
		Assert.assertEquals(new BigDecimal(9), account.getBalance());
		account.withdraw(BigDecimal.ONE);
		Assert.assertEquals(new BigDecimal(8), account.getBalance());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithdrawMoreThanBalance() {
		Account account = new AccountImpl(USER, BigDecimal.ONE, CURRENCY);
		account.withdraw(BigDecimal.TEN);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAccountEmptyUsername() {
		new AccountImpl("", BigDecimal.ONE, CURRENCY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAccountWhitespaceUsername() {
		new AccountImpl(" ", BigDecimal.ONE, CURRENCY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAccountNullUsername() {
		new AccountImpl(null, BigDecimal.ONE, CURRENCY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAccountZeroBalance() {
		new AccountImpl(USER, BigDecimal.ZERO, CURRENCY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAccountNegativeBalance() {
		new AccountImpl(USER, new BigDecimal(-1), CURRENCY);
	}
}

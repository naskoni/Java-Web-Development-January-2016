package bg.webbank.bussiness;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WebBankImplTest {

	private static final String CURRENCY = "BGN";
	private static final String USER = "user";

	private WebBankImpl webBank;

	@Before
	public void setUp() {
		webBank = new WebBankImpl();
	}

	// deposit tests
	@Test(expected = IllegalArgumentException.class)
	public void testDepositNegativeAmount() {
		webBank.deposit(USER, new BigDecimal(-1), CURRENCY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDepositZeroAmount() {
		webBank.deposit(USER, BigDecimal.ZERO, CURRENCY);
	}

	@Test
	public void testDepositNewAccountShouldIncreaseBalance() {
		webBank.deposit(USER, BigDecimal.ONE, CURRENCY);
		Assert.assertEquals(BigDecimal.ONE, webBank.getAccounts().get(USER).getBalance());
	}

	@Test
	public void testDepositExistingAccountShouldIncreaseBalance() {
		webBank.deposit(USER, BigDecimal.ONE, CURRENCY);
		webBank.deposit(USER, BigDecimal.ONE, CURRENCY);
		Assert.assertEquals(new BigDecimal(2), webBank.getAccounts().get(USER).getBalance());
	}

	// withdraw tests
	@Test(expected = IllegalArgumentException.class)
	public void testWithdrawNegativeAmount() {
		webBank.withdraw(USER, new BigDecimal(-1), CURRENCY);
		;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithdrawZeroAmount() {
		webBank.withdraw(USER, BigDecimal.ZERO, CURRENCY);
	}

	@Test
	public void testWithdrawLessThanHalfBalance() {
		webBank.deposit(USER, BigDecimal.TEN, CURRENCY);
		webBank.withdraw(USER, BigDecimal.ONE, CURRENCY);
		Assert.assertEquals(new BigDecimal(9), webBank.getAccounts().get(USER).getBalance());
		webBank.withdraw(USER, BigDecimal.ONE, CURRENCY);
		Assert.assertEquals(new BigDecimal(8), webBank.getAccounts().get(USER).getBalance());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithdrawMoreThanHalfBalanceAtOnce() {
		webBank.deposit(USER, BigDecimal.TEN, CURRENCY);
		webBank.withdraw(USER, new BigDecimal(6), CURRENCY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithdrawMoreThanHalfBalanceAtSteps() {
		webBank.deposit(USER, BigDecimal.TEN, CURRENCY);
		webBank.withdraw(USER, BigDecimal.ONE, CURRENCY);
		Assert.assertEquals(new BigDecimal(9), webBank.getAccounts().get(USER).getBalance());
		webBank.withdraw(USER, BigDecimal.ONE, CURRENCY);
		Assert.assertEquals(new BigDecimal(8), webBank.getAccounts().get(USER).getBalance());
		webBank.withdraw(USER, new BigDecimal(4), CURRENCY);
	}

}

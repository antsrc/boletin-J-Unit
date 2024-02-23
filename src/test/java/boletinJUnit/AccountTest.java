package boletinJUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.NumberFormat;
import java.util.stream.Stream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

class AccountTest {

	Account account;

	@BeforeEach
	public void setUp() {
		account = new Account("Juan Pérez", 123456789, 10000);
	}

	@Test
	public void testDeposit() {
		assertThat(account.deposit(500), is(true));
		assertThat(account.deposit(0), is(true)); // No debería dejar depositar 0€, pero no genera problemas
		assertThat(account.deposit(-1), is(false));
	}

	private static Stream<Arguments> withdrawData() {
		return Stream.of(Arguments.of(0, 0, true), // No debería dejar retirar 0€, pero no genera problemas
				Arguments.of(1000, 10, true), Arguments.of(10000, 0, true), Arguments.of(9500, 600, false),
				Arguments.of(1000, -10, false), Arguments.of(-1000, 10, false), Arguments.of(-1000, -10, false));
	}

	/*
	 * El método withdraw() y el método isValidWithdrawl() han sido modificados para
	 * que funcionen coherentemente, véanse los comentarios en Account.java
	 */

	@ParameterizedTest
	@MethodSource("withdrawData")
	public void testWithdraw(float amount, float fee, boolean result) {
		assertThat(account.withdraw(amount, fee), is(result));
	}

	@Test
	public void testAddInterest() {
		account.addInterest();
		assertThat(account.getBalance(), is(10450f));
	}

	@Test
	public void toStringTest() {
		String balance = NumberFormat.getCurrencyInstance().format(account.getBalance());
		String expected = "123456789\tJuan Pérez\t" + balance;
		assertThat(account.toString(), equalTo(expected));
	}
	
}

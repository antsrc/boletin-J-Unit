package boletinJUnit.sutil;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import boletinJUnit.Account;

public class AccountTestSutil {

	Account account;

	@BeforeEach
	public void setUp() {
		account = new Account("Juan Pérez", 123456789, 10000);
	}

	@Test
	public void testBalanceUpdate() {
		for (int i = 0; i < 5; i++) {
			account.deposit(0.1f);
		}
		assertThat(account.getBalance(), is(10000.5f)); // Las sumas sucesivas de 1 decimal acarrean error
	}

	@Test
	public void testAccumulatedInterest() {
		for (int i = 0; i < 5; i++) {
			account.addInterest();
		}
		assertThat(account.getBalance(), is(12461.8193765f)); // La multiplicación parece funcionar bien
	}

	/*
	 * El defecto más sutil es la utilización de float, dada su imprecisión. No
	 * resultaría en absoluto adecuado para la aplicación de un banco
	 */

}

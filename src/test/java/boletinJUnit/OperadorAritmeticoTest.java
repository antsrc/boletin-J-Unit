package boletinJUnit;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

class OperadorAritmeticoTest {

	@Test
	void testSuma() {
		assertThat(OperadorAritmetico.suma(138, 57), is(195));
		assertThat(OperadorAritmetico.suma(-138, 57), is(-81));
		assertThat(OperadorAritmetico.suma(138, -57), is(81));
		assertThat(OperadorAritmetico.suma(-138, -57), is(-195));
	}

	@Test
	public void testDivision() {
		try {
			assertThat(OperadorAritmetico.division(138, 57), is(2));
			assertThat(OperadorAritmetico.division(-138, 57), is(-2));
			assertThat(OperadorAritmetico.division(138, -57), is(-2));
			assertThat(OperadorAritmetico.division(-138, -57), is(2));
		} catch (Exception e) {
			fail("No debería lanzarse la excepción");
		}
		try {
			assertThat(OperadorAritmetico.division(138, 0), is(null));
			fail("Debería lanzarse la excepción");
		} catch (Exception e) {
			System.err.println("No se puede dividir entre 0");
		}

	}

}

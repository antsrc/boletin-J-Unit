package boletinJUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class SubscripcionTest {

	Subscripcion subscripcion;

	static Stream<Arguments> datos() {
		return Stream.of(Arguments.of(0, 0, 0), Arguments.of(0, 12, 0), Arguments.of(100, 0, 0),
				Arguments.of(-100, -12, 0), Arguments.of(-100, 12, 0), Arguments.of(100, -12, 0),
				Arguments.of(90, 12, 8.5), // con resto
				Arguments.of(120, 12, 10) // sin resto
		);
	}

	@ParameterizedTest
	@MethodSource("datos")
	public void testPrecioPorMes(int precio, int periodo, double resultado) {
		subscripcion = new Subscripcion(precio, periodo);
		assertThat(subscripcion.precioPorMes(), is(resultado));
	}

	@Test
	void testCancel() {
		subscripcion = new Subscripcion(120, 12);
		subscripcion.cancel();
		assertThat(subscripcion.precioPorMes(), is(0.0));
	}

}

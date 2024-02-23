package boletinJUnit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class BoaTest {

	static Stream<Arguments> isHealthyData() {
		return Stream.of(Arguments.of("Boa", 6, "granola bars", true), Arguments.of("Boa", 6, "mice", false));
	}

	@ParameterizedTest
	@MethodSource("isHealthyData")
	public void testIsHealthy(String name, int length, String favoriteFood, boolean result) {
		Boa boa = new Boa(name, length, favoriteFood);
		assertThat(boa.isHealthy(), is(result));
	}

	static Stream<Arguments> fitsInCageData() {
		return Stream.of(Arguments.of("Boa", 5, "granola bars", 6, true),
				Arguments.of("Boa", 6, "granola bars", 6, false));
	}

	@ParameterizedTest
	@MethodSource("fitsInCageData")
	public void testFitsInCage(String name, int length, String favoriteFood, int cageLength, boolean result) {
		Boa boa = new Boa(name, length, favoriteFood);
		assertThat(boa.fitsInCage(cageLength), is(result));
	}

}

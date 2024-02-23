package boletinJUnit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeEach;

class FridgeTest {

	private Fridge fridge;

	@BeforeEach
	public void setUp() {
		fridge = new Fridge();
		fridge.put("milk");
		fridge.put("tomato");
		fridge.put("carrot");
	}

	private static Stream<Arguments> putData() {
		return Stream.of(Arguments.of("cheese", true), Arguments.of("apple", true), Arguments.of("milk", false),
				Arguments.of("tomato", false), Arguments.of("carrot", false));
	}

	@ParameterizedTest
	@MethodSource("putData")
	public void testPut(String item, boolean result) {
		assertThat(fridge.put(item), is(result));
	}

	private static Stream<Arguments> containsData() {
		return Stream.of(Arguments.of("cheese", false), Arguments.of("apple", false), Arguments.of("milk", true),
				Arguments.of("tomato", true), Arguments.of("carrot", true));
	}

	@ParameterizedTest
	@MethodSource("containsData")
	public void testContains(String item, boolean result) {
		assertThat(fridge.contains(item), is(result));
	}

	private static Stream<Arguments> takeData() {
		return Stream.of(Arguments.of("cheese", true), Arguments.of("apple", true), Arguments.of("milk", false),
				Arguments.of("tomato", false), Arguments.of("carrot", false));
	}

	@ParameterizedTest
	@MethodSource("takeData")
	public void testTake(String item, boolean exceptionExpected) {
		if (exceptionExpected) {
			try {
				fridge.take(item);
				fail("Debería lanzarse la excepción");
			} catch (NoSuchItemException e) {
				System.err.println("Item not found in the fridge"); // Ya que está todo en inglés...
			}
		} else {
			try {
				fridge.take(item);
			} catch (NoSuchItemException e) {
				fail("Exception should not be thrown");
			}
		}
	}

}

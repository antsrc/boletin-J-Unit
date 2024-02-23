package boletinJUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class PilaTest {

	Pila pila;

	@BeforeEach
	public void setUp() {
		pila = new Pila();
	}

	@Test
	public void testPushYTop() {
		assertThat(pila.top(), is(nullValue()));
		pila.push(10);
		assertThat(pila.top(), is(10));
		pila.push(2);
		assertThat(pila.top(), is(10));
		pila.push(20);
		assertThat(pila.top(), is(10));
	}

	@Test
	public void testPop() {
		pila.push(5);
		pila.push(10);
		assertThat(pila.pop(), is(10));
		assertThat(pila.pop(), is(5));
		assertThat(pila.pop(), is(nullValue()));
	}

	@Test
	public void testIsEmpty() {
		assertThat(pila.isEmpty(), is(true));
		pila.push(10);
		assertThat(pila.isEmpty(), is(false));
		pila.pop();
		assertThat(pila.isEmpty(), is(true));
	}
}

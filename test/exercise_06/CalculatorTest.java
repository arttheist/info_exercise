package test.exercise_06;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testPrecedence() {
		assertEquals(6,"1+2+3");
		assertEquals(6,"1*2*3");
		assertEquals(5,"1*2+3");
		assertEquals(7,"1+2*3");
		assertEquals(14,"1*2+3*4");
		assertEquals(11,"1+2*3+4");
		assertEquals(10,"1*2*3+4");
		assertEquals(15,"1+2+3*4");
		assertEquals(9,"1*2+3+4");
		assertEquals(25,"1+2*3*4");
		assertEquals(5,"1*(2+3)");
		assertEquals(5,"(1*2)+3");
		assertEquals(20,"1*(2+3)*4");
		assertEquals(21,"(1+2)*(3+4)");
		assertEquals(14,"1*2*(3+4)");
	}

}

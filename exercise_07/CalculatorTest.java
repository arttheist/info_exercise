package exercise_07;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testPrecedence() {
		CalcEngine c = new CalcEngine();
		c.setDisplayValue("1+2+3");
		c.equals();
		assertEquals("6",c.getDisplayValue());
		c.setDisplayValue("1*2*3");
		c.equals();
		assertEquals("6",c.getDisplayValue());
		c.setDisplayValue("1*2+3");
		c.equals();
		assertEquals("5",c.getDisplayValue());
		c.setDisplayValue("1+2*3");
		c.equals();
		assertEquals("7",c.getDisplayValue());
		c.setDisplayValue("1*2+3*4");
		c.equals();
		assertEquals("14",c.getDisplayValue());
		c.setDisplayValue("1+2*3+4");
		c.equals();
		assertEquals("11",c.getDisplayValue());
		c.setDisplayValue("1*2*3+4");
		c.equals();
		assertEquals("10",c.getDisplayValue());
		c.setDisplayValue("1+2+3*4");
		c.equals();
		assertEquals("15",c.getDisplayValue());
		c.setDisplayValue("1*2+3+4");
		c.equals();
		assertEquals("9",c.getDisplayValue());
		c.setDisplayValue("1+2*3*4");
		c.equals();
		assertEquals("25",c.getDisplayValue());
		c.setDisplayValue("1*(2+3)");
		c.equals();
		assertEquals("5",c.getDisplayValue());
		c.setDisplayValue("(1*2)+3");
		c.equals();
		assertEquals("5",c.getDisplayValue());
		c.setDisplayValue("1*(2+3)*4");
		c.equals();
		assertEquals("20",c.getDisplayValue());
		c.setDisplayValue("(1+2)*(3+4)");
		c.equals();
		assertEquals("21",c.getDisplayValue());
		c.setDisplayValue("1*2*(3+4)");
		c.equals();
		assertEquals("14",c.getDisplayValue());
	}

}

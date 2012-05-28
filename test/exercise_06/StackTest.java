package test.exercise_06;

import static org.junit.Assert.*;

import org.junit.Test;

import exercise_06.*;

public class StackTest {
	private Stack<Integer> stack = new Stack<Integer>();

	@Test
	public void testPushNPop() throws StackUnderflow {
		stack.push(1);
		stack.push(2);
		stack.push(3);
		assertEquals(new Integer(3), stack.pop());
		assertEquals(new Integer(2), stack.pop());
		assertEquals(new Integer(1), stack.pop());
	}
	
	@Test
	public void testToString() {
		stack.push(1);
		stack.push(2);
		stack.push(3);
		assertEquals("123", stack.toString());
	}
}

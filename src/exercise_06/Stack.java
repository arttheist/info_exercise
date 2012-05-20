package exercise_06;

public class Stack<E> {
	private LinkedList<E> stack;
	
	public Stack() {
		stack = new LinkedList<E>();
	}
	
	public void push(E data) {
		stack.appendLast(data);
	}
	
	public E pop() throws StackUnderflow {
		if( stack.isEmpty() ) 
			throw new StackUnderflow();
		return stack.removeLast();
	}
	
	public String toString() {
		String result = "";
		while( !stack.isEmpty() ) {
			result += stack.removeFirst().toString();
		}
		return result;
	}

}

package exercise_06;

public class Stack<E> {
	private LinkedList<E> stack;
	
	public Stack() {
		stack = new LinkedList<E>();
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	public void push(E data) {
		stack.appendLast(data);
	}

	public E pop() throws StackUnderflow {
		if( stack.isEmpty() ) 
			throw new StackUnderflow();
		return stack.removeLast();
	}

	public E top() throws StackUnderflow {
		if( stack.isEmpty() ) 
			throw new StackUnderflow();
		return stack.getLast();		
	}
	
	public String toString() {
		String result = "";
		while( !stack.isEmpty() ) {
			result += stack.removeLast().toString();
		}
		return result;
	}

}

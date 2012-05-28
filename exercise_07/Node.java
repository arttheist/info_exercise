package exercise_07;

public class Node<E> {
	private E content;
	private Node<E> next = null;
	
	public Node(E content) {
		this.content = content;
	}
	
	public void setNext(Node<E> next) {
		this.next = next;
	}
	
	public Node<E> getNext() {
		return next;
	}
	
	public E getContent() {
		return content;
	}
	
	public void setContent(E newContent) {
		content = newContent;
	}
}

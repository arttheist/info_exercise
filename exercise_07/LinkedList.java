package exercise_07;

public class LinkedList<E> {
	private Node<E> dummyNode;
	
	public LinkedList() {
		dummyNode = new Node<E>(null);
		dummyNode.setNext(dummyNode);
	}
	
	public Node<E> getFirstNode() {
		return dummyNode.getNext();
	}
	
	public boolean isLastNodeReached(Node<E> current) {
		return current.getNext() == dummyNode;
	}
	
	public void appendFirst(E s) {
		Node<E> newFirst = new Node<E>(s);
		newFirst.setNext(getFirstNode());
		dummyNode.setNext(newFirst);
	}
	
	public void appendLast(E s) {
		Node<E> current = getFirstNode();
		while(current.getNext() != dummyNode) {
			current = current.getNext();
		}
		Node<E> last = new Node<E>(s);
		current.setNext(last);
		last.setNext(dummyNode);
	}
	
	public boolean isEmpty() {
		if(dummyNode.getNext() == dummyNode) {
			return true;
		}
		return false;
	}
	
	public E getLast() {
		Node<E> current = getFirstNode();
		while(current.getNext() != dummyNode) {
			current = current.getNext();
		}
		return current.getContent();
	}
	
	public E removeLast() {
		Node<E> current = getFirstNode();
		while(current.getNext().getNext() != dummyNode) {
			current = current.getNext();
		}
		Node<E> result = current.getNext();
		current.setNext(dummyNode);
		return result.getContent();
	}
	
	public E removeFirst() {
		Node<E> result = dummyNode.getNext();
		dummyNode.setNext(result.getNext());
		return result.getContent();		
	}
	
	/*public void insert(E data);
	public E remove(E data);
	public E remove(Node<E> data);*/
}

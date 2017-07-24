// package pq;

public class Node<T> {
	private T data;
	// The next and previous Node(s) in the doubly-linked list. Usging
	// boubly-linked list in order to avoid having to traverse the entire
	// list for each dequeue.
	private Node<T> next;
  private Node<T> previous;

	public Node(T data){
    this.data = data;
    this.next = null;
    this.previous = null;
	}
	public void setNext(Node<T> n){
		this.next = n;
	}
  public Node<T> getNext(){
    return this.next;
  }
  public void setPrevious(Node<T> p) {
    this.previous = p;
  }
  public Node<T> getPrevious() {
    return this.previous;
  }
	public T getData(){
		return this.data;
	}
} // end class Node<T>

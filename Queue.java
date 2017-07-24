// package pq;

public class Queue<T>{
	private Node<T> head;  // Head (or the first element) of queue.
	private Node<T> tail;  // Tail (or the last element) of the queue.
	private int length;    // Number of elements.

	public Queue() {
		this.head = null;
    this.tail = null;
		this.length = 0;
	}

  // Add an element to the end of the queue.
  public void enqueue(T data) {
    Node<T> new_node = new Node<T>(data);
    if (this.tail == null) {
      // The queue is empty. In this case, just add the first node and update
      // both head and tail pointers.
      this.head = new_node;
      this.tail = new_node;
      return;
    }
    // There is at least one element in the queue. In this case, simply
    // add the new node to the end of the doubly-linked list.
    new_node.setNext(this.tail);
    this.tail.setPrevious(new_node);
    this.length++;
  }

  // Remove and return the first item in the queue.
	public T dequeue() throws QueueEmptyException {
		if (this.length == 0) {
			throw new QueueEmptyException();
		}
    assert (this.head != null) : "head of Q should not be null at this point!";
    Node<T> head = this.head;
    head.setPrevious(null);
    Node<T> prev_node = head.getPrevious();
    if (prev_node != null) {
      prev_node.setNext(null);
    }
    if (this.tail == head) {
      // There is only one element in the queue. In this case, make sure the
      // new tail is null.
      this.tail = null;
    }
    this.length--;
		return head.getData();

	}
	public int getLength(){
		return this.length;
	}
} // end class Queue<T>

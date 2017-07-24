// package pq;
import java.util.*;

public class Queue<T>{
	private Node<T> head;  // Head (or the first element) of queue.
	private Node<T> tail;  // Tail (or the last element) of the queue.
	private int length;    // Number of elements.
  // An index (or hashmap) over the items in the queue, primarly
  // used to seeduup lookups.
  private HashMap<T, Node<T>> index;
  // Note: because of this index <T>'s in the Queue must be unique!
  // This uniqueness is enforced in NewNode() methods.

	public Queue() {
		this.head = null;
    this.tail = null;
		this.length = 0;
    index = new HashMap<T, Node<T>>();
	}

  public String toString() {
    String out = "[" + this.length + ":" + index.size() + ":";
    Node<T> it = this.tail;
    while (it != null) {
      out += " " + it.getData().toString();
      if (it.getVIPLevel() > 0) {
        out += "(" + it.getVIPLevel() + ")";
      }
      it = it.getNext();
    }
    out += "]";
    return out;
  }

  // Add an element to the end of the queue.
  public void enqueue(T data) throws DuplicateException {
    Node<T> new_node = NewNode(data);
    if (this.tail == null) {
      // The queue is empty. In this case, just add the first node and update
      // both head and tail pointers.
      this.head = new_node;
      this.tail = new_node;
      this.length++;
      return;
    }
    // There is at least one element in the queue. In this case, simply
    // add the new node to the end of the doubly-linked list.
    new_node.setNext(this.tail);
    this.tail.setPrevious(new_node);
    this.tail = new_node;
    this.length++;
  }

  // Remove and return the first item in the queue.
	public T dequeue() throws QueueEmptyException {
		if (this.length == 0) {
			throw new QueueEmptyException();
		}
    assert (this.head != null) : "head of Q should not be null at this point!";
    Node<T> head = this.head;
    Node<T> prev_node = head.getPrevious();  // Note: could be null.
    this.index.remove(head.getData());  // Remove this node from the index.
    head.setPrevious(null);
    this.head = prev_node;
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

	public int getLength() {
		return this.length;
	}

  public Node<T> find(T data) throws NotFoundException {
    Node<T> it = this.index.get(data);
    if (it == null) {
      throw new NotFoundException("The node was not in the queue!");
    }
    return it;
  }

  // Leave() -> allow people to leave (unserved) from inside the queue
  public void leave(T data) throws NotFoundException {
    Node<T> node = find(data);
    this.index.remove(data);
    if (this.tail == node) {
      this.tail = node.getNext();
    }
    if (this.head == node) {
      this.head = node.getPrevious();
    }
    Node<T> prev = node.getPrevious(); // note: prev could be null
    Node<T> next = node.getNext();     // note: next could be null
    if (prev != null) {
      prev.setNext(next);
    }
    if (next != null) {
      next.setPrevious(prev);
    }
    this.length--;
  }

  // enter() -> allow someone to enter the queue further towards the front.
  // Similar to enqueue() but allows the person to cut based on towards
  // the front based on their vip_level.
  public void enter(T data, int vip_level) throws DuplicateException {
    if (vip_level == 0) {
      // This item does not have a high priority, hence,
      // add it "normally" to the end of the queue.
      enqueue(data);
      return;
    }
    // Scan the queue from the back until we come across a node with equals
    // or higher VIP level, or come to front of the queue.
    Node<T> it = this.tail;
    while (it != null && it.getVIPLevel() < vip_level) {
      it = it.getNext();
    }
    // Either we have reached front of the queue or have found
    // one element with equal or higher VIP level.
    Node<T> new_node = NewNode(data, vip_level);
    if (it == null) {
      // We have reached front of the queue: insert this item at
      // the head of the list.
      if (this.tail == null) {
        this.tail = new_node;
      }
      if (this.head == null) {
        this.head = new_node;
      } else {
        this.head.setNext(new_node);
        new_node.setPrevious(this.head);
        this.head = new_node;
      }
    } else {
      // 'it' is the next item in the queue with equal or higher VIP level.
      Node<T> previous = it.getPrevious(); // Note: it could be null.
      it.setPrevious(new_node);
      new_node.setNext(it);
      new_node.setPrevious(previous);
      if (previous != null) {
        previous.setNext(new_node);
      }
      if (this.tail == it) {
        this.tail = new_node;
      }
    }
    this.length++;
  } // end enter()

  // Creates and returns new Node with the appropriate data making sure
  // the item is unique.
  // It also indexes the new Node to support find() operation.
  private Node<T> NewNode(T data) throws DuplicateException {
    try {
      Node<T> n = find(data);
      throw new DuplicateException();
    } catch (NotFoundException e) {
      // The new item is unique. Good!
      Node<T> new_node = new Node<T>(data);
      this.index.put(data, new_node);  // Add the node to the index.
      return new_node;
    }
  }

  private Node<T> NewNode(T data, int vip_level) throws DuplicateException {
    Node<T> new_node = NewNode(data);
    new_node.setVIPLevel(vip_level);
    return new_node;
  }
} // end class Queue<T>

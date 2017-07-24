// package pq;

public class Node<T> {
    private T data;
    // The next and previous Node(s) in the doubly-linked list. Usging
    // boubly-linked list in order to avoid having to traverse the entire
    // list for each dequeue.
    private Node<T> next;
    private Node<T> previous;
    private int vip_level;  // Rank or "vip level" of the node. Defaults to 0.

    public Node(T data){
	this.data = data;
	this.next = null;
	this.previous = null;
	this.vip_level = 0;
    }
    public Node(T data, int vip_level) {
	this(data);
	this.vip_level = vip_level;
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
    public int getVIPLevel() {
	return this.vip_level;
    }
    public void setVIPLevel(int level) {
	this.vip_level = level;
    }
} // end class Node<T>

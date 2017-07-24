// package pq;

public class PeopleQueue extends pq.Queue<String> {
    // Define PeopleQueue to be a queue of strings. That's it!

    private static void log(Object obj) {
	System.out.println(String.valueOf(obj));
    }
    
    public static void main(String[] args) {
	PeopleQueue q = new PeopleQueue();
	log(q.toString());
    } // end of main()
    
} // end class PeopleQueue

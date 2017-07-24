public class PeopleQueue extends Queue<String> {
  // Everything is inherited!

  private static void log(Object obj) {
    System.out.println(String.valueOf(obj));
  }

  public static void main(String[] args) throws QueueEmptyException,
    NotFoundException, DuplicateException {
    String p;
    Queue<String> q = new Queue<String>();

    // Test enqueue() and dequeue()
    log(q.toString());
    q.enqueue("one");
    log(q.toString());
    q.enqueue("two");
    log(q.toString());
    q.enqueue("three");
    log(q.toString());
    try {
      q.enqueue("two");
    } catch (DuplicateException e) {
      log("cool!  Duplicate exception worked!");
    }
    p = q.dequeue();
    log("dequeued:" + p);
    log(q.toString());
    p = q.dequeue();
    log("dequeued:" + p);
    log(q.toString());
    p = q.dequeue();
    log("dequeued:" + p);
    log(q.toString());
    try {
      p = q.dequeue();
    } catch (QueueEmptyException e) {
      log("cool!  Dequeue of emtpy queue worked!");
    }
    log(q.toString());

    // Test leave.
    q.enqueue("one");
    q.enqueue("two");
    q.enqueue("three");
    log(q.toString());
    try {
      q.leave("five");
    } catch (NotFoundException e) {
      log("cool!  Leave of not found worked!");
    }
    q.leave("two");
    log("Leave of \"two\"");
    log(q.toString());
    q.leave("one");
    log("Leave of \"one\"");
    log(q.toString());
    q.leave("three");
    log("Leave of \"three\"");
    log(q.toString());

    // Test enter()
    q.enqueue("one");
    q.enqueue("two");
    q.enqueue("three");
    q.enter("five", 2);
    log(q.toString());
    q.enter("six", 1);
    log(q.toString());
    q.enter("seven", 2);
    log(q.toString());
  } // end of main()


} // end class PeopleQueue

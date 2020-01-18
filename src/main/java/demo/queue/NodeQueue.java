package demo.queue;

/**
 * @author liulei
 **/
public class NodeQueue {

    private int size;
    private Node head;
    private Node tail;

    //队尾增加元素
    public boolean offer(Object object) {
        if(object == null) {
            return false;
        }
        Node node = new Node(object);
        if(head == null || tail == null) {
            head = node;
            tail = node;
        } else {
            Node temp = tail;
            tail = node;
            temp.setNext(node);
        }
        size++;
        return true;
    }

    public Object poll() {
        if(head == null) {
            return null;
        }
        if(head == tail) {
            tail = null;
        }
        Node temp = head;
        head = temp.getNext();
        size--;
        return temp.getObject();
    }

    public Object peek() {
        if (head == null) {
            return null;
        }
        return head.getObject();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public long size() {
        return size;
    }

    public static void main(String[] args) {
        NodeQueue queue = new NodeQueue();
        System.out.println(queue.offer(1));
        System.out.println(queue.peek());
        queue.offer("rewr");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.isEmpty());
        System.out.println("finish");
    }
}

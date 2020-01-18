package demo.queue;

/**
 * @author liulei
 **/
public class Node {
    private Object object;
    private Node next;


    Node (Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

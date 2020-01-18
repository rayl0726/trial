package demo.queue;

/**
 * @author : liulei
 **/
public class ArrayQueue {
    private String[] items;
    private int n = 0;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int capacity) {
        this.n = capacity;
        this.items = new String[capacity];
    }

    public boolean enQueue(String item) {
        if(tail == n) {
            return false;
        }

        items[tail] = item;
        tail++;
        return true;
    }
    public String deQueue() {
        if(head == tail) {
            return null;
        }
        String item = items[head];
        head++;
        return item;
    }

}

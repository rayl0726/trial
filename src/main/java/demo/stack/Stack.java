package demo.stack;

/**
 * @author : liulei
 **/
public class Stack {
    private Node first;
    private int N;

    public boolean isEmpty(){
        return first == null;
    }

    public int size() {
        return N;
    }

    public void push(int data) {
        Node oldFirst = first;
        first = new Node(data,oldFirst);
        N++;
    }

    public int pop() {
        if (N > 0) {
            int ret = first.getData();
            first = first.getNext();
            N--;
            return ret;
        } else {
            return -1;
        }
    }

    public int peak() throws Exception{
        if (N <= 0) {
            throw new Exception("当前栈为空!");
        }
        return first.getData();
    }

    public static void main(String[] args) throws Exception {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(3);
        stack.push(6);
        stack.push(2);
        stack.push(5);
        System.out.println(stack.peak());
        int length = stack.size();
        for (int i = 0; i < length; i++) {
            System.out.println(stack.pop());
        }


    }

}

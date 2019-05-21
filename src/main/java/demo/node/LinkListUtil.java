package demo.node;

/**
 * 判断单项链表是否相交：链表a长度n，链表b长度m
 * 1.遍历两个链表，一个一个对比，有相同的节点就是相交，时间复杂度m*n
 * 2.将链表a的各个节点hash，链表b的节点如果都能在链表a中找到，则两个链表必然相交
 * 3.将链表a的尾节点指向链表b的头结点，如果形成环，则两个链表相交
 * 4.两个链表相交，则必然最后一个节点相同，直接比较最后一个节点是否相同，
 * 如果要找到相交的点
 * @author : liulei
 **/
public class LinkListUtil {

    //判断链表是否有环
    public static boolean hasCircle(Node n) {
        if(n == null || n.next == null) {
            return false;
        }
        Node slow = n;
        Node fast = n;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    //获取快慢节点相遇点
    public static Node getMeetNode(Node n) {
        Node slow = n;
        Node fast = n;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                return slow;
            }
        }
        return slow;
    }

    //循环反转
    public static Node reverse(Node temp) {
        if(temp == null || temp.next == null) {
            return temp;
        }
        Node pre = null;
        Node next = null;
        while(temp != null) {
            next = temp.next;
            temp.next = pre;
            pre = temp;
            temp = next;
        }
        return pre;
    }

    //递归反转
    public static Node reverse2(Node temp) {
        if(temp == null || temp.next == null) {
            return temp;
        }
        Node newNode = reverse2(temp.next);
        temp.next.next = temp;
        temp.next = null;
        return newNode;
    }

    //打印链表
    public static void print(Node n) {
        Node temp = n;
        while(temp != null) {
            System.out.println(temp.hashCode() + " data: " + temp.data);
            temp = temp.next;
        }
    }

    //创建普通单链表
    public static Node createNode(Node head) {
        Node temp = head;
        for (int i = 0; i < 10 ; i++) {
            Node next = new Node();
            next.data = i + 1;
            temp.next = next;
            temp = next;
        }
        return head;
    }

    //创建有环链表
    public static Node createCircleNode(Node head, int k) {
        Node temp = head;
        Node nodec = null;
        int length = 10;
        for (int i = 0; i < length ; i++) {
            if(i == (k - 1)) {
                nodec = temp;
            }
            if(i == length-1 ) {
                temp.next = nodec;
                break;
            }
            Node next = new Node();
            next.data = temp.data + 1;
            temp.next = next;
            temp = next;
        }
        return head;
    }

    //查找倒数第k个节点
    public static Node getNodeReciprocal(int k, Node n) {
        if(n == null || n.next == null) {
            return n;
        }
        //头结点开始往后走k步
        Node head = n;
        //当头结点走k步后再开始从头向后走
        Node resp = n;
        int count = 1;
        while(head != null) {
            if(count > k) {
                resp = resp.next;
            }
            head = head.next;
            count++;
        }
        return resp;
    }

    //链表的长度
    public static int length(Node n) {
        Node temp = n;
        int count = 0;
        while(temp != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }

    //获取环的入口，头结点到入口点的距离和快慢节点相遇点到入口点的距离相等
    public static Node getCircleEntry(Node n) {
        Node head = n;
        Node meetNode = getMeetNode(n);
        while(head != meetNode) {
            head = head.next;
            meetNode = meetNode.next;
        }
        return head;
    }

    //查找中间节点,考虑奇数和偶数的问题，遍历获取返回
    //两种方式，一种是先获取长度，在从头找中间的节点，或者利用快慢指针的方式一遍遍历获取，这样还能获取n分之一的节点。
    public static Node getMiddle(Node n) {
        return null;
    }

    public static void main(String[] args) {

        Node head = new Node();
        head.data = 1;

//        Node ret = createNode(head);
//        print(ret);
//        System.out.println("-------------------------");
//        System.out.println(length(ret));
//        Node revert = reverse2(ret);
//        System.out.println("-------------------------");
//
//        Node nodeReciprocal = getNodeReciprocal(2, ret);
//        System.out.println(nodeReciprocal.hashCode() + " data: " + nodeReciprocal.data);


        Node ret = createCircleNode(head, 5);
        Node connectNode = getMeetNode(ret);
        System.out.println(connectNode.hashCode() + " data:" + connectNode.data);
        System.out.println("-------------------------");

        Node circleEntry = getCircleEntry(ret);
        System.out.println(circleEntry.hashCode() + " data:" + circleEntry.data);

    }

}

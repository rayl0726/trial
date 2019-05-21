package demo.tree;

import java.util.Date;

/**
 * @author : liulei
 **/
public class TreeNode {
    public int data;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "data=" + data;
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println((int)new Date(l).getTime());
    }
}

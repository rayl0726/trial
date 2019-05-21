package demo.tree;

import java.util.*;

/**
 * @author : liulei
 **/
public class TreeUtils {

    public static TreeNode createBinaryTree(int[] array, int index) {
        TreeNode  treeNode = null;
        if(index < array.length) {
            treeNode = new TreeNode(array[index]);
            treeNode.left = createBinaryTree(array, 2 * index + 1);
            treeNode.right = createBinaryTree(array, 2 * index + 2);
        }
        return treeNode;
    }
    public static void print(TreeNode node) {
        System.out.println(node);
        System.out.println("--------------------------");
    }

    //前序遍历递归
    public static void preOrder(TreeNode node) {
        if(node != null) {
            print(node);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    //中序遍历递归
    public static void inOrder(TreeNode node) {
        if(node != null) {
            preOrder(node.left);
            print(node);
            preOrder(node.right);
        }
    }

    //后序遍历递归
    public static void postOrder(TreeNode node) {
        if(node != null) {
            preOrder(node.left);
            preOrder(node.right);
            print(node);
        }
    }

    //前序遍历非递归
    public static void preOrderNoRecursion(TreeNode node) throws Exception{
        Stack<TreeNode> stack = new Stack<>();
        if(node != null) {
            stack.push(node);
            while(!stack.empty()) {
                node = stack.pop();
                print(node);
                if(node.right != null) {
                    stack.push(node.right);
                }
                if(node.left != null) {
                    stack.push(node.left);
                }
            }
        }
    }

    //中序遍历非递归
    public static void inOrderNoRecursion(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = node;
        while(p != null || !stack.empty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }

            if(stack.size() > 0) {
                p = stack.pop();
                print(p);
                p = p.right;
            }
        }
    }

    //后序遍历非递归
    public static void postOrderNoRecursion(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = node;
        while(node != null) {
            for (; node.left != null; node = node.left) {
                stack.push(node);
            }
            while(node != null &&(node.right == null || node.right == pre)) {
                print(node);
                pre = node;
                if(stack.empty()) {
                    return;
                }
                node = stack.pop();
            }
            stack.push(node);
            node = node.right;
        }
    }

    //广度搜索
    public static void levelTraverse(TreeNode node) {
        if(node == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()) {
            TreeNode p = queue.poll();
            print(p);
            if(p.left != null) {
                queue.offer(p.left);
            }
            if(p.right != null) {
                queue.offer(p.right);
            }
        }
    }


    //递归，非递归的话各种深度遍历不打印直接计数的方式也可以
    public static int getNodeNum(TreeNode node) {
        if(node == null) {
            return 0;
        }
        return getNodeNum(node.left) + getNodeNum(node.right) + 1;
    }

    //深度遍历，递归或者非递归
    public static int getTreeDepth(TreeNode node) {
        if(node == null) {
            return 0;
        }
        int depthLeft = getTreeDepth(node.left);
        int depthRight = getTreeDepth(node.right);
        return depthLeft > depthRight ? depthLeft+1 : depthRight +1;
    }


    public static boolean isAVL(TreeNode node) {
        if(node == null) {
            return true;
        }
        if(!isAVL(node.left)) {
            return false;
        }
        if(!isAVL(node.right)) {
            return false;
        }

        int treeDepthLeft = getTreeDepth(node.left);
        int treeDepthRight = getTreeDepth(node.right);
        return (Math.abs(treeDepthLeft - treeDepthRight) <= 1);
    }

    public static TreeNode getMirror(TreeNode node) {
        if(node == null) {
            return null;
        }
        TreeNode leftMirror = getMirror(node.left);
        TreeNode rightMirror = getMirror(node.right);
        node.left = rightMirror;
        node.right = leftMirror;
        return node;
    }
    public static void main(String[] args) throws Exception{
        int[] list = {1,4,56,7,32,98,65,23,78,54,90,38,84};
        TreeNode binaryTree = createBinaryTree(list, 0);
//        System.out.println(getNodeNum(binaryTree));
//        System.out.println(getTreeDepth(binaryTree));

//        preOrder(binaryTree);
//        inOrder(binaryTree);
//        postOrder(binaryTree);

//        preOrderNoRecursion(binaryTree);
//        inOrderNoRecursion(binaryTree);
//        postOrderNoRecursion(binaryTree);
//        levelTraverse(binaryTree);

//        System.out.println(isAVL(binaryTree));
//        preOrder(binaryTree);
//        preOrder(getMirror(binaryTree));
        System.out.println(isAVL(binaryTree));
    }
}

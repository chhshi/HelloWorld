package DivideConquerAndTraverse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BinaryTreeTraversal {

    //***************************** Pre Order *********************************************

    /**
     * https://leetcode.com/problems/binary-tree-preorder-traversal/description/
     * Given a binary tree, return the preorder traversal of its nodes' values.
     * 分别用 Recursion - traverse, Recursion - Divide & conquer, Non - Recursion 实现
     *
     * @return the preorder traversal of its nodes' values.
     *
     * pre-order: root -> left tree -> right tree
     *
     */

    // iterative solution:
    // Visit + go left & push right until no more nodes, then pop a right node as new root. ONLY Push right
    // right最后，root在left之前，所以一路先visit并往left走，并保存right
    private static List<Integer> preOrderIterative(BinaryTreeNode root) {

        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode curr = root;
        while (curr != null || !stack.empty()) {
            if (curr != null) {
                result.add(curr.val);                               //visit
                if (curr.right != null) stack.push(curr.right);     //push right
                curr = curr.left;                                   //go left
            } else {
                curr = stack.pop();                                 //pop right
            }
        }
        return result;
    }

    private static List<Integer> preOrderDivideConquer(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        //check null
        if (root == null)  return result;
        //divide
        List<Integer> leftResult = preOrderDivideConquer(root.left);
        List<Integer> rightResult = preOrderDivideConquer(root.right);
        //conquer
        result.add(root.val);
        result.addAll(leftResult);
        result.addAll(rightResult);
        return result;
    }

    private static List<Integer> preOrderTraverse(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper1(root, result);
        return result;
    }
    private static void helper1(BinaryTreeNode root, List<Integer> result) {
        if (root == null) return;
        result.add(root.val);
        helper1(root.left, result);
        helper1(root.right, result);
    }


    //***************************** IN Order *********************************************
    /**
     * https://leetcode.com/problems/binary-tree-inorder-traversal/description/
     * Given a binary tree, return the inorder traversal of its nodes' values.
     * 分别用 Recursion - traverse, Recursion - Divide & conquer, Non - Recursion 实现
     *
     * @return
     *
     * in-order: left tree -> root -> right tree
     */

    // iterative solution:
    // push + go till left-most, then pop & visit it ; treat right as new root do the same thing;
    // right最后，root在left之后，所以只能借助stack来反向，先left走到底再开始pop+visit，每个root都被保存了不用保存right
    private static List<Integer> inOrderIterative(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode curr = root;
        while (curr != null || !stack.empty()) {
            if (curr != null) {
                stack.push(curr);   //keep push
                curr = curr.left;   //go left
            } else {
                curr = stack.pop();   //pop
                result.add(curr.val);  //visit
                curr = curr.right;     //treat right as new root
            }
        }
        return result;
    }

    private static List<Integer> inOrderDivideConquer(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        //check null
        if (root == null) return result;
        //divide
        List<Integer> leftResult = inOrderDivideConquer(root.left);
        List<Integer> rightResult = inOrderDivideConquer(root.right);
        //conquer
        result.addAll(leftResult);
        result.add(root.val);
        result.addAll(rightResult);
        return result;
    }

    private static List<Integer> inOrderTraverse(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper2(root, result);
        return result;
    }
    private static void helper2(BinaryTreeNode root, List<Integer> result){
        if (root == null) return;
        helper2(root.left, result);
        result.add(root.val);
        helper2(root.right, result);
    }



    //***************************** POST Order *********************************************

    /**
     * https://leetcode.com/problems/binary-tree-postorder-traversal/description/
     * Given a binary tree, return the post traversal of its nodes' values.
     * 分别用 Recursion - traverse, Recursion - Divide & conquer, Non - Recursion 实现
     *
     * @return
     *
     * post-order: left tree -> right tree -> root
     */

    // iterative solution:
    // push + go left-most, if stack-peek node has right & right NOT last-visited , treat right as new root
    // root在最后，root总是紧跟着right-subtree 的root被visit，所以要记录last visited node
    private static List<Integer> postOrderIterative(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode curr = root;
        BinaryTreeNode lastVisited = null;

        while (curr != null || !stack.empty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                BinaryTreeNode peek = stack.peek();
                if (peek.right != null && peek.right != lastVisited) {
                    curr = peek.right;
                } else {
                    result.add(peek.val);
                    lastVisited = stack.pop();
                }
            }
        }

        return result;
    }

    private static List<Integer> postOrderDivideConquer(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        //check null
        if (root == null) return result;
        //divide
        List<Integer> leftResult = postOrderDivideConquer(root.left);
        List<Integer> rightResult = postOrderDivideConquer(root.right);
        //conquer
        result.addAll(leftResult);
        result.addAll(rightResult);
        result.add(root.val);
        return result;
    }

    private static List<Integer> postOrderTraverse(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper3(root, result);
        return result;
    }
    private static void helper3(BinaryTreeNode root, List<Integer> result){
        if (root == null) return;
        helper3(root.left, result);
        helper3(root.right, result);
        result.add(root.val);
    }


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, null, 2, 3));
        System.out.println(list);
    }

}

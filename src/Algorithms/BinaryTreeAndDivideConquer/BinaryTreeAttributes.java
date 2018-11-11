package Algorithms.BinaryTreeAndDivideConquer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class BinaryTreeAttributes {

    /********************************************************************************************************************
     * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
     *
     * @return maximun depth of a given binary tree
     * <p>
     * [The maximum depth] is the number of nodes along the longest path from the root node down to the farthest leaf node.
     * [A leaf] is a node with no children.
     * <p>
     * n为node个数： T(n) = 2T(n/2) + O(1) = 4T(n/4) + O(3) = 8T(n/8) + O(7) ====> nT(1) + O(n) ====> O(n)
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        //if (root.left == null && root.right == null) return 1;  叶子判断也是多余
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public int maxDepthBFS(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return level;
    }

    //用stack实现DFS---preOrder的另一种写法
    public int maxDepthDFS(TreeNode root) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> value = new Stack<>();
        stack.push(root);
        value.push(1);
        int max = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int temp = value.pop();
            max = Math.max(temp, max);
            if (node.left != null) {
                stack.push(node.left);
                value.push(temp + 1);
            }
            if (node.right != null) {
                stack.push(node.right);
                value.push(temp + 1);
            }
        }
        return max;
    }

    //postOrder DFS整个tree，同步数level, push时候level++, pop时候level--
    public int maxDepthDFS2(TreeNode root) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        int level = 0;
        int maxLevel = 0;
        TreeNode lastVisited = null;
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                level++;
                maxLevel = Math.max(level, maxLevel);
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right != null && peek.right != lastVisited) {
                    curr = peek.right;
                } else {
                    lastVisited = stack.pop();
                    level--;
                }
            }
        }
        return maxLevel;
    }


    /********************************************************************************************************************
     * https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
     *
     * @return minimum depth
     * <p>
     * [The minimum depth] is the number of nodes along the shortest path from the root node down to the nearest leaf node.
     * [A leaf] is a node with no children.
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }


    /********************************************************************************************************************
     * https://leetcode.com/problems/balanced-binary-tree/description/
     * Given a binary tree, determine if it is height-balanced.
     *
     * [height balanced tree] a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
     *
     * @return  if it is height-balanced.
     *
     * 显然是个BottomUp的问题，divide conquer is best. O(n) = 2*O(n/2)+O(1) =>O(n)
     * 如果要用iterative 的解法，似乎应该是post-order-traverse比较合适，因为bottom-up, root需要最后判断
     */

    //Define a return type (or Array) to return height & isBalanced
    public boolean isBalancedBinaryTree1(TreeNode root) {
        return helper1(root).isBalanced;
    }
    private class ReturnType {
        int height;
        boolean isBalanced;
        ReturnType(int h, boolean b) {
            this.height = h;
            this.isBalanced = b;
        }
    }
    private ReturnType helper1(TreeNode root) {
        if (root == null) return new ReturnType(0, true);
        ReturnType isNotBalanced = new ReturnType(0, false);
        ReturnType left = helper1(root.left);
        if (!left.isBalanced) {
            return isNotBalanced;   //return ASAP
        }
        ReturnType right = helper1(root.right);
        if (!right.isBalanced || Math.abs(left.height - right.height) > 1) {
            return isNotBalanced;
        }
        return new ReturnType(Math.max(left.height, right.height) + 1, true);
    }

    //use -1 to denote unbalanced tree height, then upper height diff will definitely > 1
    public boolean isBalancedBinaryTree2(TreeNode root) {
        return helper2(root) != -1;
    }
    private int helper2(TreeNode root) {
        if (root == null) return 0;
        int left = helper2(root.left);
        if (left == -1) return -1;   //return ASAP
        int right = helper2(root.right);
        if (right == -1 || Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }





  /********************************************************************************************************************
   * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
   * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
   * All of the nodes' values will be unique.
   * p and q are different and both values will exist in the binary tree.
   *
   * @return LCA
   * [lowest common ancestor (LCA)]  the lowest node in Algorithms.Tree that has both p and q as descendants (where we allow a node to be a descendant of itself)
   */
  public TreeNode lowestCommonAncestor_DivideConquer(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;
    TreeNode left = lowestCommonAncestor_DivideConquer(root.left, p, q);
    TreeNode right = lowestCommonAncestor_DivideConquer(root.right, p, q);
    if (left == null) return right;
    if (right == null) return left;
    return root;
  }

  public TreeNode lowestCommonAncestor_iter_DFS(TreeNode root, TreeNode p, TreeNode q) {
    Map<TreeNode, TreeNode> parent = new HashMap<>();
    Stack<TreeNode> stack = new Stack<>();
    parent.put(root, null);
    stack.push(root);
    while (!stack.empty() && (!parent.containsKey(p) || !parent.containsKey(q))) {
      TreeNode node = stack.pop();
      if (node.left != null) {
        parent.put(node.left, node);
        stack.push(node.left);
      }
      if (node.right != null) {
        parent.put(node.right, node);
        stack.push(node.right);
      }
    }
    Set<TreeNode> ancestors = new HashSet<>();
    while (p != null) {
      ancestors.add(p);
      p = parent.get(p);
    }
    while (!ancestors.contains(q)) {
      q = parent.get(q);
    }
    return q;
  }

//  public TreeNode lowestCommonAncestor_iter_BFS(TreeNode root, TreeNode p, TreeNode q) {
//    Map<TreeNode, TreeNode> parent = new HashMap<>();
//    Queue<TreeNode> queue = new Algorithms.LinkedList<>();
//    parent.put(root, null);
//    queue.offer(root);
//
//    while (!queue.isEmpty()) {
//      int size = queue.size();
//      for (int i = 0; i < size; i++) {
//
//      }
//    }
//
//
//  }

}

class Main {

    public static void main(String[] args) {

        BinaryTreeAttributes bta = new BinaryTreeAttributes();

        TreeNode root1 = TreeNode.constructBinaryTreeFromBFSTraversal(Arrays.asList(2,4,5,6,null,null,8,9));
        TreeNode root2 = TreeNode.constructBinaryTreeFromBFSTraversal(Arrays.asList(3,9,20,null,null,15,7));
        TreeNode root3 = TreeNode.constructBinaryTreeFromBFSTraversal(Arrays.asList(0,2,4,1,null,3,-1,5,1,null,6,null,8));
        TreeNode root4 = TreeNode.constructBinaryTreeFromBFSTraversal(Arrays.asList(3,9,20,null,null,15,7));

        System.out.println("--maxDepth");
        System.out.println(bta.maxDepth(root1) == 4);
        System.out.println(bta.maxDepth(root2) == 3);
        System.out.println(bta.maxDepth(root3) == 4 );
        System.out.println(bta.maxDepth(root4) == 3);
        System.out.println(bta.maxDepthBFS(root1) == 4);
        System.out.println(bta.maxDepthBFS(root2) == 3);
        System.out.println(bta.maxDepthBFS(root3) == 4);
        System.out.println(bta.maxDepthBFS(root4) == 3);
        System.out.println(bta.maxDepthDFS(root1) == 4);
        System.out.println(bta.maxDepthDFS(root2) == 3);
        System.out.println(bta.maxDepthDFS(root3) == 4);
        System.out.println(bta.maxDepthDFS(root4) == 3);


//        System.out.println(maxDepth(root1) == 4);
//        System.out.println(maxDepth(root2) == 3);

        System.out.println("--minDepth");
        System.out.println(bta.minDepth(root1) == 3);
        System.out.println(bta.minDepth(root2) == 2);



    }
}

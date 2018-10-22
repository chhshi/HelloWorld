package Algorithms.BinaryTreeAndDivideConquer;

import java.util.*;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) {
        this.val = x;
    }

    @Override
    public String toString() {
//        if (this == null) return "[]";    //this == null is always false
        StringBuilder sb = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this);
        sb.append(this.val);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                    sb.append(",").append(node.left.val);
                } else {
                    sb.append(",").append("null");
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    sb.append(",").append(node.right.val);
                } else {
                    sb.append(",").append("null");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public List<Integer> toIntegerList() {

        List<Integer> list = new ArrayList<>();
//        if (this == null) return list;  //this == null is always false

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(this);
        list.add(this.val);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                    list.add(node.left.val);
                } else {
                    list.add(null);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                   list.add(node.right.val);
                } else {
                    list.add(null);
                }
            }
        }
        //remove all the tailing nulls
        while (list.get(list.size() - 1) == null) {
            list.remove(list.size() - 1);
        }
        return list;

    }


    /**
     * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     * You may assume that duplicates do not exist in the tree.
     *
     * @return root node
     */
    protected static TreeNode constructBinaryTreeFromPreorderAndInorderTraversal(int[] perorder, int[] inorder) {
        return null;
    }

    /**
     * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
     * Given inorder and postorder traversal of a tree, construct the binary tree.
     * NO Duplicate value in the tree.
     *
     * @return root node
     *
     * O(n) time, O(n) space
     */
    protected static TreeNode constructBinaryTreeFromInorderAndPostorderTraversal(int[] inorder, int[] postorder) {
        //check null or empty or corner case
        if (inorder == null || postorder == null
                || inorder.length == 0 || postorder.length == 0
                || inorder.length != postorder.length) {
            return null;
        }
        //use a map, O(n) space to make search for root idx faster.
        Map<Integer, Integer> map = new HashMap<>();
        for (int idx = 0; idx < inorder.length; idx++) {
            map.put(inorder[idx], idx);
        }

        return constructHelper1(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }
    private static TreeNode constructHelper1(int[] inorder, int i, int j, int[] postorder, int p, int q, Map<Integer, Integer> map) {
        int rootVal = postorder[q];
//        int rootIdx = findRootIdx(inorder, i, j, rootVal);
//        if (rootIdx < 0) return null;
        int rootIdx = map.get(rootVal);
        int leftLen = rootIdx - i;
        int rightLen = j - rootIdx;

        TreeNode root = new TreeNode(rootVal);
        if (leftLen > 0) {
            root.left = constructHelper1(inorder, i, rootIdx - 1, postorder, p, p + leftLen - 1, map);
        }
        if (rightLen > 0) {
            root.right = constructHelper1(inorder, rootIdx + 1, j, postorder, q - rightLen, q - 1, map);
        }
        return root;
    }
    private static int findRootIdx(int[] inorder, int i, int j, int val) {
        for (int idx = i; idx <= j; idx ++) {
            if (inorder[idx] == val) {
                return idx;
            }
        }
        return -1;
    }


    /**
     *  因为要存null代表中间空节点，所以选用List而不是array
     *  因为要遍历，所有用 Iterator
     *  因为从BFS遍历来构建，所以用Queue，且不要往Queue里插null
     */
    protected static TreeNode constructBinaryTreeFromBFSTraversal(List<Integer> input) {
        if (input == null || input.size() == 0) {
            return null;
        }
        Iterator<Integer> iter = input.iterator();
        Integer value = iter.next();
        TreeNode root;
        if (value != null) {
            root = new TreeNode(value);
        } else {
            return null;
        }
        //Make sure -NEVER- offer null into a Queue
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (iter.hasNext() && !queue.isEmpty()) {
            int qSize = queue.size();
            for (int i = 0; i < qSize; i++) {
                TreeNode node = queue.poll();
                if (iter.hasNext()) {
                    Integer val = iter.next();
                    if (val != null) {
                        node.left = new TreeNode(val);
                        queue.offer(node.left);
                    }
                }
                if (iter.hasNext()) {
                    Integer val = iter.next();
                    if (val != null) {
                        node.right = new TreeNode(val);
                        queue.offer(node.right);
                    }
                }
            }
        }
        return root;
    }


    public static void main(String[] args) {
        System.out.println(constructBinaryTreeFromBFSTraversal(Arrays.asList(1, 2, 3, 4, 5, null, 6)).toIntegerList());
        System.out.println(constructBinaryTreeFromBFSTraversal(Arrays.asList(1, 2, 3, 4, 5, null, 6, null, null, null)).toIntegerList());
        System.out.println(constructBinaryTreeFromBFSTraversal(Arrays.asList(1, 2, null, 4, 5, null, 6)).toIntegerList());
    }

}

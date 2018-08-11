package DivideConquerAndTraverse;

import java.util.List;

public class BinaryTreeNode {
    int val;
    BinaryTreeNode left;
    BinaryTreeNode right;
    BinaryTreeNode(int x) {
        this.val = x;
    }

    /**
     * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     * You may assume that duplicates do not exist in the tree.
     *
     * @return root node
     */
    protected static BinaryTreeNode constructBinaryTreeFromPreorderAndInorderTraversal(int[] perorder, int[] inorder) {
        return null;
    }

    /**
     * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
     * Given inorder and postorder traversal of a tree, construct the binary tree.
     * You may assume that duplicates do not exist in the tree.
     *
     * @return root node
     */
    protected static BinaryTreeNode constructBinaryTreeFromInorderAndPostorderTraversal(int[] inorder, int[] postorder) {
        return null;
    }

    protected static BinaryTreeNode constructBinaryTreeFromBFSTraversal(List<Integer> input) {
        return null;
    }


}

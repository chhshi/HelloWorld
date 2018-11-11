package Algorithms.DynamicProgramming;

import Algorithms.BinaryTreeAndDivideConquer.TreeNode;
import java.util.Arrays;

public class HouseRobber {

  /**
   *
   * You are a professional robber planning to rob houses along a street. Robber adjacent houses will alert police.
   * Given a list of non-negative integers representing the amount of money of each house,
   * @return the maximum amount of money you can rob tonight without alerting the police.
   *
   * https://leetcode.com/problems/house-robber/description/
   *
   * i有两个state，visit和不visit
   *    visit-i 则 一定不visit i-1，取max (不visit i-1)
   *    不visit-i 则 不care i-1，直接取 max（i-1）
   * 最后 max(i) = max（visit - 1 , 不visit i-1）
   *
   * 所以 下一个node的计算依赖于 上一个node i-1的两个值 max（i-1）和 不 vist i-1的值
   *
   *
   */
  public int robOne(int[] A) {

    //A[i] has 2 state: include or exclude (rob or not rob, it's only decided by previous state)
    if (A.length == 0) return 0;
    int include = A[0];
    int exclude = 0;

    for (int i = 1; i < A.length; i++) {
      int prevInclude = include;
      include = exclude + A[i];
      exclude = Math.max(prevInclude, exclude);
    }

    return Math.max(include, exclude);

//    if (A.length == 0) return 0;
//    if (A.length == 1) return A[0];

//    int n = A.length;
//    int[] f = new int[2];
//
//    f[0] = A[0];
//    f[1] = Math.max(A[0], A[1]);
//    for (int i = 2; i < n; i++) {
//      f[i%2] = Math.max(f[(i - 2) % 2] + A[i], f[(i - 1) %2]);
//    }
//
//
//    return f[(n-1)%2];


  }


  /**
   *  All houses at this place are arranged in a circle.
   *  That means the first house is the neighbor of the last one.
   *  Meanwhile, adjacent houses have security system connected and it will automatically contact the police
   * @return the maximum amount of money you can rob tonight without alerting the police.
   *
   */

  public int robTwo(int[] A) {
    if (A.length == 0) return 0;
    if (A.length == 1) return A[0];

    //Rewrite robOne to get rob a range function,do not copy array!
    return Math.max(robOne(Arrays.copyOfRange(A, 1, A.length)), robOne(Arrays.copyOfRange(A, 0, A.length - 1)));
  }

  /**
   *  There is only one entrance to this area, called the "root." , all houses in this place forms a binary tree".
   * It will automatically contact the police if two directly-linked houses were broken into on the same night.
   * @return  the maximum amount of money the thief can rob tonight without alerting the police.
   */

  public int robThree(TreeNode root) {
    if (root == null) return 0;
    return Math.max(robIncludeRoot(root), robExcludeRoot(root));
  }
  public int robIncludeRoot(TreeNode root) {
    if (root == null) return 0;
    return robExcludeRoot(root.left) + robExcludeRoot(root.right) + root.val;
  }
  public int robExcludeRoot(TreeNode root) {
    if (root == null) return 0;
    return robThree(root.left) + robThree(root.right);
  }


  //Below solution is much much much faster!!!!!!!!!!上面solution有重复计算子树，比如robExcludeRoot(root.left)
  public int rob(TreeNode root) {
    if(root == null) return 0;

    int[] sums = robSubSum(root);
    return Math.max(sums[0], sums[1]);
  }

  private int[] robSubSum(TreeNode root) {
    if(root == null) return new int[]{0,0};  //each node 2 state, store in 2 length array

    int[] leftSum = robSubSum(root.left);
    int[] rightSum = robSubSum(root.right);

    int[] sums = new int[2];
    // case of skip this node
    sums[0] = Math.max(leftSum[0],leftSum[1]) + Math.max(rightSum[0],rightSum[1]);
    // case of count this node
    sums[1] = Math.max(sums[0], root.val + leftSum[0] + rightSum[0]);

    return sums;
  }



}

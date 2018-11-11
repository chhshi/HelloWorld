package Algorithms.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 比较 recursion & Algorithms.DP
 * minPath是定性搜索问题，不需要求所有解集，因此用DP就足够
 * recursion的解法 复杂度是指数级的 O(2^n)
 * DP的复杂度是多项式级的  O(n^a)
 */
public class ClassicalTriangle {

    /**
     * https://leetcode.com/problems/triangle/description/
     * Given a triangle, find the minimum path sum from top to bottom.
     * Each step you may move to adjacent numbers on the row below.
     *
     * @return minimum path sum from top to bottom
     *
     * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
     */

    /** # 1 Traversal: 遍历一遍所有solution，并maintain一个最小Sum
     * Basically Visited all binary tree nodes using In-order traversal, if height==nums.size()==n, time = O(2^n) :
     * 走到底层后再回溯到上一层从另一个分支往下走
     * 虽然triangle不是binary tree：因为一个元素的右下和邻居的左下是重叠的，
     * 但是重叠node在这种recursion解法的recursion-tree里是分开的，也就是说这些重叠点都被重复访问了。
     */
    private static int minPathSumTraverse(List<List<Integer>> nums) {
        count = 0;  // ---recording only---
        minSum = Integer.MAX_VALUE;
        traverseHelper(nums, 0,0, nums.get(0).get(0));
        return minSum;
    }
    private static int minSum;
    private static int count = 0;
    //【recursion意义描述】从(0,0)到(x,y)处的path的sum值
    private static void traverseHelper(List<List<Integer>> nums, int x, int y, int sum) {
        if (x == nums.size() - 1){
            minSum = Math.min(minSum, sum);
            return;
        }
        count++;   System.out.print(nums.get(x).get(y) + "  ");  // ---recording only---
        traverseHelper(nums, x + 1, y, sum + nums.get(x + 1).get(y));
        traverseHelper(nums, x + 1, y + 1, sum + nums.get(x + 1).get(y + 1));
    }


    /**
     * #2 Divide Conquer: 把起点到终点的min path问题转化成 两个起点邻居到终点min path的问题
     * 经过的node path和traversal是一模一样的
     * 未经过优化的话，时间复杂度 time = O(2^n)
     */
    private static int minPathSumDivideConquer(List<List<Integer>> nums) {
        count = 0;  // ---recording only---
        return divideConquerHelper(nums, 0,0);
    }
    private static int divideConquerHelper(List<List<Integer>> nums, int x, int y) {
        if (x == nums.size() - 1) return nums.get(x).get(y);
        count++; System.out.print(nums.get(x).get(y) + "  ");   // ---recording only---
        return nums.get(x).get(y) + Math.min(divideConquerHelper(nums, x + 1, y), divideConquerHelper(nums, x+ 1, y + 1));
    }

    /**
     * #3 Divide Conquer Memorized优化: 把起点到终点的min path问题转化成 两个起点邻居到终点min path的问题
     * 把已经算过到终点min path的node结果都记录下来
     */
    private static int minPathSumDivideConquerMemorized(List<List<Integer>> nums) {
        count = 0;  // ---recording only---
        return divideConquerHelperMemorized(nums, 0, 0 , new Integer[nums.size()][nums.size()]);
    }
    private static int divideConquerHelperMemorized(List<List<Integer>> nums, int x, int y, Integer[][] memo) {
        if (x == nums.size() - 1) return nums.get(x).get(y);

        if (memo[x][y] != null) {
            System.out.print("*" + nums.get(x).get(y) + "*  ");
            return memo[x][y];
        } else {
            count++; System.out.print(nums.get(x).get(y) + "  ");   // ---recording only---
            int currentMinPath = nums.get(x).get(y)
                    + Math.min(divideConquerHelperMemorized(nums, x + 1, y, memo)
                    , divideConquerHelperMemorized(nums, x+ 1, y + 1, memo));
            memo[x][y] = currentMinPath;
            return currentMinPath;
        }
    }



    private static int minPathSumDP (List<List<Integer>> nums) {

        //状态定义： sum[i][j]代表从起点到(i,j)处的min_sum,
        //状态转移： (i,j)可以有两个upstream，因此sum[i][j] = nums.get(i).get(j) + Math.min(sum[i-1][j-1], sum[i-1][j])
        int[][] sum = new int[nums.size()][nums.size()];
        //初始化：第一列和对角线，因为他们都只有一种upstream
        sum[0][0] = nums.get(0).get(0);
        for (int i = 1; i < nums.size(); i++) {
            sum[i][0] = sum[i-1][0] + nums.get(i).get(0);  //初始化第一列
            sum[i][i] = sum[i - 1][i - 1] + nums.get(i).get(i);  //初始化对角线
        }
        //循环求解
        for (int i = 2; i < nums.size(); i++) {
            for (int j = 1; j < i; j++) {
                sum[i][j] = nums.get(i).get(j) + Math.min(sum[i-1][j-1], sum[i-1][j]);
            }
        }
        //获得结果
//        int minSum = Integer.MAX_VALUE;  => NNNOOOO!!!!! 只要能初始化成一个合理的值 DO NOT 初始化成这个
        int minSum = sum[nums.size() - 1][0];
        for (int j = 0; j < nums.size(); j++) {
            minSum = Math.min(minSum , sum[nums.size() - 1][j]);
        }
        return minSum;
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(2));
        list.add(Arrays.asList(3,4));
        list.add(Arrays.asList(6,5,7));
        System.out.println(" MinPath is: " + minPathSumTraverse(list) + ", count = " + count);
        System.out.println(" MinPath is: " + minPathSumDivideConquer(list) + ", count = " + count);
        System.out.println(" MinPath is: " + minPathSumDivideConquerMemorized(list) + ", count = " + count);
        System.out.println(" MinPath is: " + minPathSumDP(list));

        list.add(Arrays.asList(4,1,8,3));
        System.out.println(" MinPath is: " + minPathSumTraverse(list) + ", count = " + count);
        System.out.println(" MinPath is: " + minPathSumDivideConquer(list) + ", count = " + count);
        System.out.println(" MinPath is: " + minPathSumDivideConquerMemorized(list) + ", count = " + count);
        System.out.println(" MinPath is: " + minPathSumDP(list));

        list.add(Arrays.asList(4,1,8,3,9));
        System.out.println(" MinPath is: " + minPathSumTraverse(list) + ", count = " + count);
        System.out.println(" MinPath is: " + minPathSumDivideConquer(list) + ", count = " + count);
        System.out.println(" MinPath is: " + minPathSumDivideConquerMemorized(list) + ", count = " + count);
        System.out.println(" MinPath is: " + minPathSumDP(list));

        list.add(Arrays.asList(5,1,8,3,9,11));
        System.out.println(" MinPath is: " + minPathSumTraverse(list) + ", count = " + count);
        System.out.println(" MinPath is: " + minPathSumDivideConquer(list) + ", count = " + count);
        System.out.println(" MinPath is: " + minPathSumDivideConquerMemorized(list) + ", count = " + count);
        System.out.println(" MinPath is: " + minPathSumDP(list));

        list.add(Arrays.asList(5,1,8,3,9,11,12));
        System.out.println(" MinPath is: " + minPathSumTraverse(list) + ", count = " + count);
        System.out.println(" MinPath is: " + minPathSumDivideConquer(list) + ", count = " + count);
        System.out.println(" MinPath is: " + minPathSumDivideConquerMemorized(list) + ", count = " + count);
        System.out.println(" MinPath is: " + minPathSumDP(list));

        System.out.println("每增加一层，traverse & divide conquer的时间 * 2， 所以是O(2^n); " +
                "\n经过memorize优化的Divide Conquer则是多项式级增长，O(n^2)");
    }
}

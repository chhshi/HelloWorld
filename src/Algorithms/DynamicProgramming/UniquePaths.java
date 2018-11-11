package Algorithms.DynamicProgramming;

import java.util.List;

public class UniquePaths {



  /**
   * https://leetcode.com/problems/unique-paths/description/
   */

  public int uniquePaths(int m, int n) {

    //corner cases
    if (m == 0 || n == 0) return 1;

    int[] f = new int[n];
    //第一行的初始化也可以写入双层loop
//    for (int i = 0; i <= n - 1; i++) {
//      f[i] = 1;
//    }
    f[0] = 1;

    for (int i = 0; i <= m - 1; i++) {
      for (int j = 1; j <= n - 1; j++) {
        //f[i][j] = f[i-1][j] + f[i][j-1], next row is depend on prev row
        f[j] = f[j] + f[j - 1];
      }
    }

    return f[n-1];
  }



  /**
   * https://leetcode.com/problems/unique-paths-ii/description/
   */
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    if (m == 0) return 1;
    int n = obstacleGrid[0].length;
    if (n == 0) return 1;

    int[] f = new int[n];
    //第一行的初始化也可以写入双层loop
//    for (int j = 0; j <= n - 1; j++) {
//      if (obstacleGrid[0][j] == 1) break;
//      f[j] = 1;
//    }
    f[0] = 1;  //每一行第一个为1

    for (int i = 0; i <= m - 1; i++) {
      if (obstacleGrid[i][0] == 1) {  //每一行第一个若出现obstacle则从此变0，否则一直为1
        f[0] = 0;
      }
      for (int j = 1; j <= n - 1; j++) {
        if (obstacleGrid[i][j] == 1) {
          f[j] = 0;
        } else {
          f[j] = f[j] + f[j - 1];
        }
      }
    }
    return f[n - 1];
  }




  /**
   * https://leetcode.com/problems/climbing-stairs/description/
   *
   * 解是斐波那契数列  1,2,3,5,8 etc....
   *  表达法1   f[i] = f[i-1] + f[i-2]
   *  表达法2   f[i % 2] = f[(i - 2) % 2] + f[(i -1) % 2];
   *  表达法3    tmp = a; a = b; b = tmp + b;
   */

  public int climbStairs(int n) {

//         if (n < 3) return n;

//         int[] f = new int[2];
//         f[0] = 1;
//         f[1] = 2;
//         for (int i = 2; i <= n-1 ; i++) {
//             f[i % 2] = f[(i - 2) % 2] + f[(i -1) % 2];
//         }

//         return f[(n-1)%2];


    if (n < 3) return n;

    // int[] f = new int[2];
    int a = 1;
    int b = 2;
    for (int i = 2; i <= n-1 ; i++) {
      int tmp = a;
      a = b;
      b = tmp + b;
    }

    return b;
  }




  /**
   * https://leetcode.com/problems/min-cost-climbing-stairs/description/
   *
   * i-th step has some non-negative cost cost[i] assigned (0 indexed)
   * Once you pay the cost, you can either climb one or two steps.
   * and you can either start from the step with index 0, or the step with index 1.
   *
   * cost will have a length in the range [2, 1000].
   * Every cost[i] will be an integer in the range [0, 999].
   *
   * @return  minimum cost to reach the top of the floor
   *
   *
   * f[i] = min(f[i-1] + cost[i-1] , f[i-2] + cost[i-2])
   * tmp = a;  a = b;  b = min(tmp - cost[i -1], b + cost[i - 2])
   */

  public int minCostClimbingStairs(int[] cost) {
    if (cost.length == 2) {
      return Math.min(cost[0], cost[1]);
    }

    int a = 0;
    int b = 0;
    for (int aCost : cost) {
      int temp = a;
      a = b;
      b = Math.min(temp, b) + aCost;
    }
    return Math.min(a,b);
  }

  /**
   * Given a m x n grid filled with non-negative numbers,
   * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
   *
   * Note: You can only move either down or right at any point in time.
   */
  public int minPathSum(int[][] grid) {
    int m = grid.length;
    if (m == 0) return 0;
    int n = grid[0].length;
    if (n == 0) return 0;

    int[] f = new int[n];
    f[0] = grid[0][0];
    for (int j = 1; j < n; j++) {
      f[j] = f[j - 1] + grid[0][j];
    }


    for (int i = 1; i < m; i++) {
      f[0] = f[0] + grid[i][0];
      for (int j = 1; j < n; j++) {
        f[j] = Math.min(f[j], f[j - 1]) + grid[i][j];
      }
    }
    return f[n - 1];
  }

  /**
   *
   * https://leetcode.com/problems/triangle/description/
   *
   * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
   *
   * For example, given the following triangle
   * [
   *      [2],
   *     [3,4],
   *    [6,5,7],
   *   [4,1,8,3]
   * ]
   * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
   */
  public int minimumTotal(List<List<Integer>> triangle) {
    int m = triangle.size();
    if (m == 0) return 0;

    int[] f = new int[m];

    //从最后一行往上走，好处是只用一维array就够了，可以不停覆盖。反过来不行。
    for (int i = m -1; i >= 0; i--) {
      //加速：不要每次都triangle.get().get()
      List<Integer> row = triangle.get(i);
      for (int j = 0; j <= i; j++) {
        if (i == m - 1) {
          f[j] = row.get(j);
        } else {
          f[j] = Math.min(f[j], f[j + 1]) + row.get(j);
        }
      }
    }
    return f[0];
  }


}

class main {
  public static void main(String[] args) {
    UniquePaths up = new UniquePaths();
    System.out.println(up.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    System.out.println(up.minCostClimbingStairs(new int[]{10, 15, 20}));
    System.out.println(up.minCostClimbingStairs(new int[]{ 0,0,1,1}));



  }
}

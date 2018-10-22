package UnionFind;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {

  /**
   * https://leetcode.com/problems/number-of-islands/description/
   *
   * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
   * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
   * You may assume all four edges of the grid are all surrounded by water.
   *
   */

  public int numIslandsBFS(char[][] grid) {

    if (grid == null) return 0;
    int m = grid.length;
    if (m == 0) return 0;
    int n = grid[0].length;
    if (n == 0) return 0;

    int count = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] != '1') {
          continue;
        }
        //if it's not 2 means it's not conencted to previous island, because we always explore whole island
        count++;
        bfsExplore(grid, i, j);  //explore whole island and mark visited with 2
      }
    }
    return count;
  }

  //[i,j]=> i * m + j    idx => [idx/n, idx%n]
  private void bfsExplore(char[][] grid, int a, int b) {
    int m = grid.length;
    int n = grid[0].length;

    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{a,b});
    while (!queue.isEmpty()) {
      int[] idx = queue.poll();
      int i = idx[0];
      int j = idx[1];
      if (grid[i][j] == '1') {  //mark it visited use'2' and then explore its '1' neighbours
        grid[i][j] = '2';
        if (i + 1 < m && grid[i+1][j] == '1') queue.offer(new int[]{i + 1, j});
        if (i - 1 >= 0 && grid[i-1][j] == '1') queue.offer(new int[]{i - 1, j});
        if (j + 1 < n && grid[i][j+1] == '1') queue.offer(new int[]{i, j + 1});
        if (j - 1 >= 0 && grid[i][j-1] == '1') queue.offer(new int[]{i, j - 1});
      } // '0' or '2' do nothing
    }
  }






}

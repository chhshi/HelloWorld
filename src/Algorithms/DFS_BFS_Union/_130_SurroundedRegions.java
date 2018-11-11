package Algorithms.DFS_BFS_Union;

import java.util.LinkedList;
import java.util.Queue;

public class _130_SurroundedRegions {

  //DFS beats 100%
  //BFS exceeds time limit


  /**
   * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
   *
   * A region is captured by flipping all 'O's into 'X's in that surrounded region.
   *
   * Example:
   *
   * X X X X
   * X O O X
   * X X O X
   * X O X X
   * After running your function, the board should be:
   *
   * X X X X
   * X X X X
   * X X X X
   * X O X X
   */

  class Solution {
    public void solve(char[][] board) {
      if (board == null || board.length == 0 || board[0].length == 0) return;

      int m = board.length;
      int n = board[0].length;

      Queue<int[]> q = new LinkedList<>();

      for (int i = 0; i < m; i++) {
        if (board[i][0] == 'O')
          // bfs(board, i, 0, q);
          dfs(board, i, 0);
        if (n > 1 && board[i][n - 1] == 'O')
          // bfs(board, i, n - 1, q);
          dfs(board, i, n - 1);
      }
      for (int j = 0; j < n; j++) {
        if (board[0][j] == 'O')
          // bfs(board, 0, j, q);
          dfs(board, 0, j);
        if (m > 1 && board[m-1][j] == 'O')
          // bfs(board, m - 1, j, q);
          dfs(board, m - 1, j);
      }
      //convert all remaining 'O' into 'X'
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (board[i][j] == 'O') board[i][j] = 'X';
          else if (board[i][j] == 'C') board[i][j] = 'O';
        }
      }
    }

    //explore (i,j) set all connected 0s to Cs
    //idx = i * m + n
    //i = idx/n, j = idx%n
    private void bfs(char[][]board, int i, int j, Queue<int[]> q) {
      int m = board.length;
      int n = board[0].length;
      // Queue<int[]> q = new LinkedList<>();
      q.offer(new int[]{i,j});
      while(!q.isEmpty()) {
        int[] idx = q.poll();
        i = idx[0];
        j = idx[1];
        // System.out.println(i + " " + j);
        board[i][j] = 'C';
        if (i + 1 < m && board[i + 1][j] == 'O') q.offer(new int[]{i + 1, j});
        if (i - 1 >= 0 && board[i - 1][j] == 'O') q.offer(new int[]{i - 1, j});
        if (j + 1 < n && board[i][j + 1] == 'O') q.offer(new int[]{i, j + 1});
        if (j - 1 >=0 && board[i][j - 1] == 'O') q.offer(new int[]{i, j - 1});
      }
    }


    private void dfs(char[][] board, int i, int j) {
      int m = board.length;
      int n = board[0].length;

      board[i][j] = 'C';
      if (i + 1 < m && board[i + 1][j] == 'O') dfs(board, i + 1, j);
      if (i - 1 >= 0 && board[i - 1][j] == 'O') dfs(board, i - 1, j);
      if (j + 1 < n && board[i][j + 1] == 'O') dfs(board, i, j+1);
      if (j - 1 >=0 && board[i][j - 1] == 'O') dfs(board, i, j-1);

    }
  }
}

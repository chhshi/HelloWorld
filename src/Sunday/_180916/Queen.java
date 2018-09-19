package Sunday._180916;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
public class Queen {

  //Given an integer n, return all distinct solutions to the n-queens puzzle.
  // Each solution contains a distinct board configuration of the n-queens' placement,
  // where 'Q' and '.' both indicate a queen and an empty space respectively.
  public List<List<String>> solveNQueens(int n) {

    List<List<String>> result = new ArrayList<>();
    if (n == 0) return result;

    List<Integer> existing = new ArrayList<>(); //store existing col locations.
    List<String> solution = new ArrayList<>();
    helper(result, n, solution, existing);
    return  result;
  }

  private void helper(List<List<String>> result, int n, List<String> sol, List<Integer> existing) {

    System.out.println("sol: " + sol);
    if (sol.size() == n) {
      result.add(new ArrayList<>(sol));
      //backtracking here
    } else {
      StringBuilder row = new StringBuilder(n);
      for (int i = 0; i < n; i++) {
        StringBuilder tmp = new StringBuilder(row);
        boolean canPlace = canPlaceQueenAtCol(existing, i);
//        System.out.println("canPlace: " + canPlace + " " +  i);
        if (!canPlace) {
          row.append('.');
        } else {
          row.append('Q');
          existing.add(i);
//          System.out.println("existing" + existing);
          while (row.length() < n) row.append('.');
          sol.add(row.toString());
          helper(result, n, sol, existing);
          //backtracking to here
//          if (i != n - 1) {
            row = tmp;
            row.append('.');
            existing.remove(existing.size() - 1);
            sol.remove(sol.size() - 1);
          //} //else backtracking to last row
        }
      }
      //row.length == 4, no solution, backtracking
    }
  }

  public boolean canPlaceQueenAtCol(List<Integer> existing, int col) {
    //not in a row(never), not in a col (value not same), not in 对角线(sum & diff不一样)
//    System.out.println("check existing: " + existing.toString() + ' ' + col);

    int n = existing.size();
    for (int i = 0; i < n; i++) {
      int colI = existing.get(i);
      if (col == colI) return false;
      if (col + n == colI + i || col - n == colI - i) return false;
    }
    return true;
  }

}


class Main {
  public static void main(String[] args) {
    Queen queen = new Queen();
    System.out.println((queen.solveNQueens(4)));
//    System.out.println((queen.canPlaceQueenAtCol(Arrays.asList(0), 4)));

  }
}

package BinarySearch;//https://leetcode.com/problems/search-a-2d-matrix-ii/description/
/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

        Integers in each row are sorted in ascending from left to right.
        Integers in each column are sorted in ascending from top to bottom.

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

*/

public class SearchA2DMatrixII {


    private static boolean find(int[][] nums, int target) {
        int m = nums.length;
        int n = nums[0].length;
        int i = 0, j = n - 1;
        while (i <= m - 1 && j >= 0) {
            if (nums[i][j] == target) {
                System.out.println("[" + i + "," + j + "]");
                return true;
            } else if (nums[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        int[][] arr = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        System.out.println(find(arr, 23));
    }
}

package BinarySearch;

public class Search2DMatrix {

    private static int count = 0;
    /**
     * https://leetcode.com/problems/search-a-2d-matrix/description/
     * searches for a value in an m x n matrix
     * Integers in each row are sorted from left to right.
     * The first integer of each row is greater than the last integer of the previous row.
     * @return value exists or not
     * O(log(m*n)) == O(logm + logn)
     */
    private static boolean search2DMatrix(int[][] nums, int target) {

        //check null or empty
        //check null
        if (nums == null || nums.length == 0 || nums[0].length == 0) return false;

        //search mxn array as an 1-D array, idx: 0 ~ m*n-1
        int m = nums.length;
        int n = nums[0].length;
        int start = 0;
        int end = m * n - 1;
        count = 0;

        //check 2+ elements
        while (start < end) {   //search existence 不怕死循环
            ++count;
            int mid = start + (end - start) / 2;
            int midValue = nums[mid / n][mid % n];
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        //check 1 element
        return nums[start / n][start % n] == target;
    }


    /**
     * https://leetcode.com/problems/search-a-2d-matrix-ii/description/
     * searches for a value in an m x n matrix
     * Integers in each row are sorted in ascending from left to right.
     * Integers in each column are sorted in ascending from top to bottom.
     * @return value exists or not
     * O(m+n)
     */
    private static boolean search2DMatrixII(int[][] nums, int target) {
        count = 0;

        //check null or empty
        if (nums == null || nums.length == 0) return false;

        int m = nums.length;
        int n = nums[0].length;
        int i = 0, j = n - 1;
        while (i <= m - 1 && j >= 0) {
            ++count;
            if (nums[i][j] == target) {
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
        int[][] arr = {
                {1,  3,   5,  7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}};
        System.out.println(search2DMatrix(arr, 50));
        System.out.println(count);

        int[][] arr2 = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        System.out.println(search2DMatrixII(arr2, 18));
        System.out.println(count);
    }
}

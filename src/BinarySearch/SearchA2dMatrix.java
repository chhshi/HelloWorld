package BinarySearch;//https://leetcode.com/problems/search-a-2d-matrix/description/


public class SearchA2dMatrix {


    private static boolean find(int[][] nums, int target) {

        int m = nums.length;
        int n = nums[0].length;

        int left = 0;
        int right = n * m - 1;
        //写<=的话 update指针要+1 -1不能死循环
        //写left + 1 < right的话是为了方便mid 和 mid+1 mid-1比较
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midNum = nums[mid/n][mid%n];
            if (midNum == target) {
                return true;
            } else if (midNum < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        int[][] arr = {
                {1,  3,   5,  7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}};
        System.out.println(find(arr, 30));
    }
}

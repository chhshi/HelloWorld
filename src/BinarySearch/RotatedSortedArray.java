package BinarySearch;



/*
* an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
* [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]
* */
public class RotatedSortedArray {


    /**
     * https://leetcode.com/problems/search-in-rotated-sorted-array/description/
     * An array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     * No duplicate elements
     *
     * @return return index of target value, otherwise return -1.
     *
     * O(log n)
     */
    private static int searchInRotatedSortedArray(int[] nums, int target) {

        //check null or empty
        if (nums == null || nums.length == 0) return -1;

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target == nums[mid]) return mid;
            if (target < nums[mid]) {
                if (nums[start] <= target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (nums[end] >= target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }

    /**
     * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
     * This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
     *
     * @return index of target value, otherwise return -1.
     */
    private static void searchInRotatedSortedArrayWithDuplicate(int[] nums) {

    }

    private static void sortRotatedSortedArray(int[] nums) {

    }

    /**
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
     *
     * @return minimum element value
     */
    private static void findMinInRotatedSortedArray(int[] nums) {

    }

    /**
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/description/
     *
     * @return minimum element value
     */
    private static void findMinInRotatedSortedArrayWithDuplicates(int[] nums) {

    }

    public static void main (String[] args) {

    }
}

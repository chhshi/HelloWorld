package BinarySearch;


/**
 * https://leetcode.com/problems/search-insert-position/description/
 *
 */
public class SearchInsertPosition {

    /**
     * SearchInsertPosition
     * 等价于 find target or first larger than target
     *
     * 关于用不用start + 1 < end这种循环：
     * 当剩下两个元素时候, mid == start, 如果出现 start = mid (不能丢掉mid 不能用mid+1) 的更新条件 就会死循环
     */
    private static int findTargetOrFirstLargerThan(int[] nums, int target) {

        //check null or empty
        if (nums == null || nums.length == 0) return 0;

        int start = 0, end = nums.length - 1;

        // >= 3 elements
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        //check 1 or 2 elements
        if (nums[start] >= target) {
            return start;
        } else if (nums[end] >= target) {
            return end;
        } else {
            return end + 1;
        }
    }


    private static int findTargetOrLastSmallerThan(int[] nums) {



        return 0;
    }

    public static void  main(String[] args) {

        System.out.println(findTargetOrFirstLargerThan(new int[]{1,3,5,6},5) == 2);
        System.out.println(findTargetOrFirstLargerThan(new int[]{1,3,5,6},7) == 4);
        System.out.println(findTargetOrFirstLargerThan(new int[]{1,3,5,6},2) == 1);
        System.out.println(findTargetOrFirstLargerThan(new int[]{1,3,5,6},0) == 0);

    }

}

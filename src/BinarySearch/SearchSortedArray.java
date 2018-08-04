package BinarySearch;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * Given a sorted array of integers and a target value
 */
public class SearchSortedArray {

    /**
     * Given a sorted array and a target value
     * @return if the value exists in the array
     */
    private static boolean classicalBinarySearch(int[] nums, int target) {

        //check null or empty
        if (nums == null || nums.length == 0) {
            return false;
        }

        int start = 0;
        int end = nums.length - 1;

        //check 2+ elements
        while (start < end) {
            int mid = start + (end - start) / 2;
            int midValue = nums[mid];
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        // check 1 element
        return nums[start] == target;
    }


    /**
     * https://leetcode.com/problems/search-insert-position/description/
     * Given a sorted array and a target value,
     * @return the index if the target is found. If not, return the index where it would be if it were inserted in order.
     *
     * start + 1 < end （3+ elements) 还是 start < end (2+ elements)?
     * 先确定条件更新方式：start = mid的更新会在只剩两个元素时候出现死循环。end = mid在只剩1个元素时候才会出现死循环
     * 本题等价于 find target or first larger than target
     *  nums[mid] == target: return
     *  nums[mid] < target: start = mid + 1
     *  nums[mid] > target: end = mid (mid不可丢，could be the first larger than)
     *
     */
    private static int searchInsertPosition(int[] nums, int target) {

        //check null or empty
        if (nums == null || nums.length == 0) return 0;

        int start = 0, end = nums.length - 1;

        // >= 2 elements
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        //check 1 element
        if (nums[start] >= target) {
            return start;
        }  else {
            return end + 1;
        }
    }

    /**
     * Given an array of integers nums sorted in ascending order, with duplicates
     * @return first position of target if exists
     *
     * nums[mid] = target: end = mid; (mid不能丢）
     * nums[mid] < target: start = mid + 1; (mid能丢） 不怕死循环
     * nums[mid] > target: end = mid - 1; (mid能丢)
     */
    private static int searchFirstPosition(int[] nums, int target) {

        //check null or empty
        if (nums == null || nums.length == 0) return -1;

        int start = 0;
        int end = nums.length - 1;

        //check 2+ elements
        while (start < end) {
            int mid = start + (end - start) / 2;
            int midValue = nums[mid];
            if (midValue == target) {
                end = mid;
            } else if (midValue < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
         //check 1 element
        if (nums[start] == target) return start;
        else return -1;
    }


    /**
     * Given an array of integers nums sorted in ascending order, with duplicates
     * @return last position of target if exists
     *
     * nums[mid] = target: start = mid; (mid不能丢） => 2个element时候死循环
     * nums[mid] < target: start = mid + 1; (mid能丢）
     * nums[mid] > target: end = mid - 1; (mid能丢)
     */
    private static int searchLastPosition(int[] nums, int target) {

        //check null or empty
        if (nums == null || nums.length == 0) return -1;

        int start = 0;
        int end = nums.length - 1;

        //check 3+ elements
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int midValue = nums[mid];
            if (midValue == target) {
                start = mid;
            } else if (midValue < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        //check 1 or 2 element, due to find last, so check end first
        if (nums[end] == target) return end;
        else if (nums[start] == target) return start;
        else return -1;
    }


    /**
     * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
     *
     * Given an array of integers nums sorted in ascending order, with duplicates
     * @return first and last position of target if exists else return [-1, -1]
     *
     */
    private static int[] searchRange(int[] nums, int target) {

        //check null or empty
        if (nums == null || nums.length == 0) return new int[]{-1,-1};

        int first = searchFirstPosition(nums, target);
        int last = searchLastPosition(nums, target);

        return new int[]{first, last};
    }


    /**
     * https://www.geeksforgeeks.org/find-closest-number-array/
     *
     * Given an array of sorted integers, may contain duplicate values and negative numbers.
     *
     * @return  closest value to the given number. (What if there're 2 closest value????? return smaller one)
     *
     * target == nums[mid]: return nums[mid]
     * target > nums[mid]: start = mid (mid不能丢） => 2 个element时候会死循环
     * target < nums[mid]: end = mid (mid不能丢）
     *
     * Math.abs()
     * IllegalArgumentException
     */
    private static int findClosestNumber (int[] nums, int target) {
        //check null & empty
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
//            return Integer.MAX_VALUE;
        }

        int start = 0;
        int end = nums.length - 1;

        //check 3+ elements
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int midValue = nums[mid];
            if (midValue == target) {
                return midValue;
            } else if (midValue < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        //check 1 or 2 elements:
        if (Math.abs(target - nums[start]) > Math.abs(nums[end] -target)){
            return nums[end];
        } else {
            return nums[start];
        }
    }


    private static int findClosestNumberIndex (int[] nums, int target) {
        //check null & empty
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
//            return Integer.MAX_VALUE;
        }

        int start = 0;
        int end = nums.length - 1;

        //check 3+ elements
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int midValue = nums[mid];
            if (midValue == target) {
                return mid;
            } else if (midValue < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        //check 1 or 2 elements:
        if (Math.abs(target - nums[start]) > Math.abs(nums[end] -target)){
            return end;
        } else {
            return start;
        }
    }

    /**
     * https://leetcode.com/problems/find-k-closest-elements/description/
     * Given a sorted array of integers, two integers k and target,
     * k is positive and will always be smaller than the length of the sorted array.
     *
     * @return k closest elements to target in the array, in ascending order,
     * If there is a tie, the smaller elements are always preferred.
     *
     *
     * log(n) + k
     */
    private static List<Integer> findKClosestNumbers (int[] nums, int target, int k) {


        //Solution #1 O(log(n) + k)
        //find closest idx in O(logN), then do idx-- idx++ find k consecutive elements in O(k),
        /*
        int closestIndex = findClosestNumberIndex(nums, target);
        List<Integer> result = new LinkedList<>();
        result.add(nums[closestIndex]);

        int start = closestIndex - 1;
        int end = closestIndex + 1;
        while (k > 1 && (start >= 0 && end <= nums.length - 1)) {
            if (target - nums[start] <= nums[end] - target) {
                result.add(0, nums[start--]);
            } else {
                result.add(nums[end++]);
            }
            k--;
        }
        while (k > 1 && start >= 0) {
            result.add(0, nums[start--]);
            k--;
        }
        while (k > 1 && end <= nums.length - 1) {
            result.add(nums[end++]);
            k--;
        }
        return result;
        */




        //Solution #2  O(log(n-k) + k)
        //return的结果一定是"原array的连续子集"，而且是"固定长度k"，定了开头or结尾，整个序列就定了
        //整个序列的搜索是窗口移动过程，窗口是移动标准是，开头元素low和末尾后一个元素low+k的较量，末尾后一个元素离target更近的话就向右移1格
        //整个序列的搜索，其实是开头元素的搜索，开头元素的搜索范围是[0, len - k]
        //开头元素也是sorted，搜索sorted用binary search, start = 0, end = len - k;
        // Math.abs(nums[mid] - target) > Math.abs(nums[mid + k] - target): start = mid + 1;
        // Math.abs(nums[mid] - target) <= Math.abs(nums[mid + k] - target): end = mid

        int start = 0;
        int end = nums.length - k;
        int low = 0;

        //at least 2 choices
        while (start < end) {
            int mid = (start + end) / 2;
            if (Math.abs(nums[mid] - target) > Math.abs(nums[mid + k] - target)) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        //1 choice, start must be the same as end now
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(nums[start + i]);
        }
        return result;
    }

    /**
     * https://www.geeksforgeeks.org/find-position-element-sorted-array-infinite-numbers/
     * https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/description/
     *
     * Given an integer array sorted in ascending order, the array size is unknown to you.
     * assume that all elements in the array are unique.
     * No need to check index out of bound issue
     *
     * @return index of the target if exists in the array, else return -1
     *
     * 2 * O(logN) => O(logN)
     */
    private static int searchInfiniteSortedArray(int[] nums, int target) {

        //check special cases
        if (nums[0] == target) return 0;
        if (nums[1] == target) return 1;


        //find boundary n, O(logn)
        int n = 1;
        while (target > nums[n]) {
            n *= 2;
        }
        if (nums[n] == target) {
            return n;
        }

        //binary search, O(logn)
        int start = n / 2;
        int end = n;

        // check 2+ elements
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        // only 1 element
        if (nums[start] == target) return start;
        return -1;
    }


    public static void  main(String[] args) {


        System.out.println("classicalBinarySearch");
        System.out.println(classicalBinarySearch(new int[]{1, 3, 5, 6}, 5) == true);
        System.out.println(classicalBinarySearch(new int[]{1, 3, 5, 6}, 7) == false);
        System.out.println(classicalBinarySearch(null, 2) == false);
        System.out.println(classicalBinarySearch(new int[]{}, 0) == false);

        System.out.println("searchInsertPosition");
        System.out.println(searchInsertPosition(new int[]{1, 3, 5, 6}, 5) == 2);
        System.out.println(searchInsertPosition(new int[]{1, 3, 5, 6}, 7) == 4);
        System.out.println(searchInsertPosition(new int[]{1, 3, 5, 6}, 2) == 1);
        System.out.println(searchInsertPosition(new int[]{1, 3, 5, 6}, 0) == 0);


        System.out.println("searchFirstPosition");
        System.out.println(searchFirstPosition(new int[]{1, 5, 5, 6}, 5) == 1);
        System.out.println(searchFirstPosition(new int[]{1, 1, 1, 6}, 1) == 0);
        System.out.println(searchFirstPosition(new int[]{1, 3, 5, 6}, 2) == -1);
        System.out.println(searchFirstPosition(new int[]{1, 3, 5, 6}, 6) == 3);

        System.out.println("searchLastPosition");
        System.out.println(searchLastPosition(new int[]{1, 5, 5, 6, 7}, 5) == 2);
        System.out.println(searchLastPosition(new int[]{1, 1, 1, 6, 7}, 1) == 2);
        System.out.println(searchLastPosition(new int[]{1, 3, 5, 6}, 2) == -1);
        System.out.println(searchLastPosition(new int[]{5, 7, 7, 8, 8, 10}, 8) == 4);

        System.out.println("searchRange");
        System.out.println(Arrays.equals(searchRange(new int[]{1, 5, 5, 6, 7}, 5), new int[]{1, 2}));
        System.out.println(Arrays.equals(searchRange(new int[]{1, 5, 5, 6, 7}, -1), new int[]{-1, -1}));
        System.out.println(Arrays.equals(searchRange(new int[]{1, 5, 5, 6, 7}, 6), new int[]{3, 3}));
        System.out.println(Arrays.equals(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8), new int[]{3, 4}));

        System.out.println("findClosestNumber");
        System.out.println(findClosestNumber(new int[]{1, 2, 4, 5, 6, 6, 8, 9}, 11) == 9);
        System.out.println(findClosestNumber(new int[]{2, 5, 6, 7, 8, 8, 9}, 4) == 5);
        try {
            System.out.println(findClosestNumber(new int[]{}, 4) == 5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("findKClosestNumbers");
        System.out.println(findKClosestNumbers(new int[]{1, 2, 3, 4, 5}, 3, 4)
                .equals(Arrays.asList(1, 2, 3, 4)));
        System.out.println(findKClosestNumbers(new int[]{1, 2, 3, 4, 5}, -1, 4)
                .equals(Arrays.asList(1, 2, 3, 4)));
        System.out.println(findKClosestNumbers(new int[]{1, 2, 3, 3, 6, 6, 7, 7, 9, 9}, 8, 8)
                .equals(Arrays.asList(3, 3, 6, 6, 7, 7, 9, 9)));


        System.out.println("searchInfiniteSortedArray");
        System.out.println(searchInfiniteSortedArray(new int[]{3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170}, 10) == 4);
        System.out.println(searchInfiniteSortedArray(new int[]{3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170}, 5) == 1);
        System.out.println(searchInfiniteSortedArray(new int[]{3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170}, -1) == -1);

    }

}

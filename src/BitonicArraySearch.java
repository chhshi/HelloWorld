//https://www.geeksforgeeks.org/find-element-bitonic-array/

public class BitonicArraySearch {


    private static int find(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        // 1) find peak
        int peak = 0;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) {
                peak = mid;
                break;
            } else if (nums[mid -1] < nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        int[] arr = {-3, 8, 9, 20, 17, 5, 1};
        System.out.println(find(arr, 5));
    }
}

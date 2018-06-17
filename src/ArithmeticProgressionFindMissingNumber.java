//https://www.geeksforgeeks.org/find-missing-number-arithmetic-progression/

public class ArithmeticProgressionFindMissingNumber {


    private static int find(int[] nums) {

        //前提：nums.length >= 3
        int n = nums.length - 1;
        int gap = (nums[n] - nums[0]) / (n + 1);

        int left = 0;
        int right = n;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid + 1] == nums[mid] + gap * 2) return nums[mid] + gap;
            if (nums[mid - 1] + gap * 2 == nums[mid]) return nums[mid - 1] + gap;
            if (nums[0] + gap * mid == nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr =  {2, 4, 8, 10, 12, 14};
        int[] arr2 = {-1, -6, -11, -16, -21, -31};
        System.out.println(find(arr));
        System.out.println(find(arr2));
    }
}

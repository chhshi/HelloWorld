package Sunday._180916;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class _234sum {


  /**
   * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
   * Find all unique triplets in the array which gives the sum of zero.
   * The solution set must not contain duplicate triplets.
   */
  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    if (nums == null || nums.length < 3) {
      return new ArrayList<>();
    }
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 2; i++) {
      //remove duplicate
      if (i > 0 && nums[i] == nums[i - 1]) continue;

      //two sum: [i+1, nums.length - 1] find sum == target
      int target = 0 - nums[i];
      int left = i + 1;
      int right = nums.length - 1;
      while (left < right) {
        if (left > i + 1 && nums[left] == nums[left - 1]  //remove duplicate
            || nums[left] + nums[right] < target) {
          left++;
        } else if (right < nums.length - 1 && nums[right] == nums[right + 1] //remove duplicate
            || nums[left] + nums[right] > target){
          right--;
        } else { //if (nums[left] + nums[right] == target)
          result.add(Arrays.asList(nums[i], nums[left], nums[right]));
          left++; right--;  //remove duplicate
        }
      }
    }
    return result;
  }
}

class main {
  public static void main(String[] args) {

  }
}

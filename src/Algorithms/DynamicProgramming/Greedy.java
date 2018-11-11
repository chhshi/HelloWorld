package Algorithms.DynamicProgramming;

public class Greedy {

  /**
   * https://leetcode.com/problems/jump-game/description/
   * Given an array of non-negative integers, you are initially positioned at the first index of the array.
   * Each element in the array represents your maximum jump length at that position.
   * Determine if you are able to reach the last index.
   *
   * Input: [2,3,1,1,4]  Output: true
   * Input: [3,2,1,0,4]  Output: false
   */


  public boolean canJump(int[] nums) {
    int n = nums.length;
    if (n <= 1) return true;

    int start = 0;
    int max = nums[start];

    while (start < n - 1) {
      int tmp = max;
      for (int i = start + 1; i <= tmp; i++) {
        if (nums[i] + i >= n - 1) return true;
        if (nums[i] + i > max) max = nums[i] + i;
        //else do nothing i++
      }
      if (tmp == max) {
        return false;
      }
      else {
        start = tmp;
      }
    }
    return true;



//    int max = 0;
//    for (int i = 0; i < nums.length;i++){
//      if (i > max) return false;
//      max = Math.max(nums[i]+i, max);
//    }
//    return true;


//    int nearest = nums.length - 1;
//    for (int i = nums.length - 1; i >= 0; i--) {
//      if (i + nums[i] >= nearest) {
//        nearest = i;
//      }
//    }
//    return nearest == 0;

  }

  /**
   * https://leetcode.com/problems/jump-game-ii/description/
   * Given an array of non-negative integers, you are initially positioned at the first index of the array.
   * Each element in the array represents your maximum jump length at that position.
   * @return  minimum number of jumps.
   */

  public int jump(int[] nums) {
    int n = nums.length;
    if (n <= 1) return 0;

    int count = 1;  //和canJump比增加了count
    int start = 0;
    int max = nums[start];
    if (max >= n - 1) return count;  //和canJump比增加了count

    while (start < n - 1) {
      count++;   //和canJump比增加了count
      int tmp = max;
      for (int i = start + 1; i <= tmp; i++) {
        if (nums[i] + i >= n - 1) return count;  //和canJump比增加了count
        if (nums[i] + i > max) max = nums[i] + i;
        //else do nothing i++
      }
      if (tmp == max) {
        return -1;
      }
      else {
        start = tmp;
      }
    }
    return count;  //和canJump比增加了count
  }
}

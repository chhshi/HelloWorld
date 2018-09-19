package Sunday._180916;

public class trapping_rain_water {
/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 */
  public int trap(int[] height) {

    // 一句话概括思维：自己位置处能存的水h = Min(左边的比自己高的最高的h,右边的比自己高的最高的h)和自己的高度差

    //Input: [0,1,0,2,1,0,1,3,2,1,2,1]
    //stack    0 1 1 2 2 2 2 3 3 3 3 3 or max
    //left   [0,0,1,0,1,2,1,0,1,2,1,2] 从left到right: max - [i] > 0 ? max - [i] : 0

    //Input: [0,1,0,2,1,0,1,3,2,1,2,1]
    //stack    3 3 3 3 3 3 3 2 2 2 1 0 or max
    //right  [3,2,2,1,2,3,2,0,0,1,0,0] 从right到left: max - [i] > 0 ? max - [i] : 0

    //left   [0,0,1,0,1,2,1,0,1,2,1,2]
    //right  [3,2,2,1,2,3,2,0,0,1,0,0]
    //min    [0,0,1,0,1,2,1,0,0,1,0,0] sum, 并入right to left步骤

    //left to right
    int max = 0;
    int[] diff = new int[height.length];
    for (int i = 0; i < height.length; i++) {
      diff[i] = max > height[i] ? max - height[i] : 0;  //generate diff
      if (height[i] > max) max = height[i];
    }
    //right to left
    max = 0;
    int sum = 0;
    for (int j = height.length - 1; j >= 0; j--) {
      diff[j] = Math.min(diff[j], max > height[j] ? max - height[j] : 0);  //diff & min
      sum += diff[j];             //sum
      if (height[j] > max) max = height[j];
    }
    return sum;
  }
}

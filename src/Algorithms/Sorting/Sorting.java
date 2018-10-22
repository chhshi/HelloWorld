package Algorithms.Sorting;

import java.util.Arrays;

public class Sorting {

  //In-place sort!!
  public int[] quickSort(int[] nums) {
    if (nums != null && nums.length > 1) {
      quickSortRecursion(nums, 0, nums.length - 1);
    }
    return nums;
  }
  private void quickSortRecursion(int[] nums, int start, int end) {
    if (start >= end) return;
    int idx = partitionRandomSwapWithEnd(nums, start, end);
    quickSortRecursion(nums, start, idx - 1);
    quickSortRecursion(nums, idx + 1, end);
  }

  //Random Pivot, return idx, where nums[<=idx] <= pivot, nums[>idx] > pivot;
  private int partition(int[] nums, int start, int end) {
    int pivot = nums[start + (int)(Math.random() * (end + 1 - start))];
    int left = start;
    int right = end;
    while (left < right) {
      while (left < right && nums[left] <= pivot) left++;
      while (right > left && nums[right] > pivot) right--;
      if (left < right) {
        swap(nums, left, right);
        left++;
        right--;
      }
    }
    if (left == right) {
      if (nums[left] < pivot) return left; //此处不能<=， 考虑[1,1]这种情况 会死循环
      else return left - 1;
    } else {
      return right;
    }
  }

  /*
  *
  * Yay!!!!!!!!!!
  * Be familiar with this: random picked pviot, swap to head, and do normal partition
  *
  * */
  private int partitionRandomSwapWithEnd(int[] nums, int start, int end) {
    int p = start + (int)(Math.random() * (end - start + 1));
    int pivotValue = nums[p];
    swap(nums, p, start);
    p = start;
    start = start + 1;
    //pivot在第一个，start在第二个，end在最后一个
    //这三个指针把array分隔成：[小于pivotValue的数] - pivotValue - start未知数 - [未访问的数] - end未知数 - [大于pivotValue的数]
    while (start <= end) {
      if (nums[start] > pivotValue) {
        swap(nums, start, end);
        end--;           //swap完以后, end值一定比pivotValue大，但start为未知数
      } else {
        swap(nums, start, p);
        start++;        //pivot永远紧跟着start，swap完以后，pivotValue现在在旧start处，start挪向下一个未知数
        p++;
      }
    }
    return p;
  }

  private void swap(int[] nums, int left, int right) {
    int tmp = nums[left];
    nums[left] = nums[right];
    nums[right] = tmp;
  }


  }

class Main {
  public static void main(String[] args) {
    Sorting s = new Sorting();
    System.out.println( Arrays.equals(s.quickSort(new int[]{9,3,4,2,6,7,1,8,5}), new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}) );
    System.out.println(Arrays.equals( s.quickSort(new int[]{1,0,1,0,1,0,2,1,0}), new int[]{0, 0, 0, 0, 1, 1, 1, 1, 2}));
    System.out.println(Arrays.equals(s.quickSort(new int[]{2,0,2,1,1,0}), new int[]{0, 0, 1, 1, 2, 2}));
    System.out.println(Arrays.equals(s.quickSort(new int[]{1,3,1,5,1,2,9,3}), new int[]{1, 1, 1, 2, 3, 3, 5, 9}));
    System.out.println(Arrays.equals(s.quickSort(new int[]{9,8,7,6,5,4,3,2,1,1,1,1,1}), new int[]{1, 1, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9}));
  }
}
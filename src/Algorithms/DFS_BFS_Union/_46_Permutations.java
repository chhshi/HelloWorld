package Algorithms.DFS_BFS_Union;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _46_Permutations {

  /**
   * Given a collection of distinct integers, return all possible permutations.
   *
   * Example:
   *
   * Input: [1,2,3]
   * Output:
   * [
   *   [1,2,3],
   *   [1,3,2],
   *   [2,1,3],
   *   [2,3,1],
   *   [3,1,2],
   *   [3,2,1]
   * ]
   */

  class Solution {

    public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> res = new ArrayList<>();
      if  (nums == null || nums.length == 0) return res;
      List<Integer> prefix = new ArrayList<>();  //prefix is empty for root node
      // Set<Integer> visited = new HashSet<>();
      //replace set with int[] use 0 to denote unvisited, 1 to denote visited
      int[] visited = new int[nums.length];
      search(res, prefix, visited, nums);
      return res;
    }

    private void search(List<List<Integer>> res, List<Integer> prefix, int[] visited, int[] nums) {
      if (prefix.size() == nums.length) {
        res.add(new ArrayList<Integer>(prefix));
        return;
      }
      for (int i = 0; i < nums.length; i++) {
        if (visited[i] == 1) continue;
        visited[i] = 1;
        prefix.add(nums[i]);
        search(res, prefix, visited, nums);
        //backtracking
        visited[i] = 0;
        prefix.remove(prefix.size() - 1);
      }
    }



    //用permution本身的数学思想, n个元素的排列构建： 第一个元素，第二个元素有两个插入位置，其中每个使得：第三个元素可以插入在3个位置
    //              {}
    //             {1}
    //      {21}               {12}
    // {321} {231} {213}  - {312} {132} {123}
    // 实现时候用不断取上一行每一个list出来遍历每个可行位置插入并建立新list 不断swap res 和 不断新建permute
    public List<List<Integer>> permuteIterative(int[] nums) {

      List<List<Integer>> res = new ArrayList<>();
      if (nums == null || nums.length == 0) return res;

      List<Integer> permute = new ArrayList<>();  // {}
      res.add(permute);  //{{}}
      for (int i = 0; i < nums.length; i++) {
        List<List<Integer>> newRes = new ArrayList<>();
        for (int j = 0; j <= i; j++) {  // nums[i] can be inserted to previous permute at location 0~i
          for (List<Integer> eachPermute : res) {
            List<Integer> newPermute = new ArrayList<>(eachPermute);
            newPermute.add(j, nums[i]);
            newRes.add(newPermute);
          }
        }
        res = newRes;
      }
      return res;
    }


  //iterative的做法本质可以用queue来做BFS，queue of permute, 用permute的length来判断做到哪一行了
  public List<List<Integer>> permuteWithQueue(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    if (nums == null || nums.length == 0) return res;
    Queue<List<Integer>> queue = new LinkedList<>();
    queue.offer(new ArrayList<>());  //{}
    while(!queue.isEmpty()) {
      List<Integer> permute = queue.poll();  //{}
      int idx = permute.size();     //0
      if (idx == nums.length) {
        res.add(permute);
      } else {
        int value = nums[idx];        //1
        for (int i = 0; i <= idx; i++) {
          List<Integer> newPermute = new ArrayList<>(permute);   // {} -> {1} -> {21}/{12}
          newPermute.add(i, value);  //{1} -> {21}/{12}  -> {321}{231} etc.....
          queue.offer(newPermute);
        }
      }
    }
    return res;
  }
  }

}

package DFS_BFS_Union;

import java.util.ArrayList;
import java.util.List;

public class _131_PalindromePartitioning {


  /**
   * Given a string s, partition s such that every substring of the partition is a palindrome.
   *
   * Return all possible palindrome partitioning of s.
   *
   * Example:
   *
   * Input: "aab"
   * Output:
   * [
   *   ["aa","b"],
   *   ["a","a","b"]
   * ]
   */
  class Solution {
    public List<List<String>> partition(String s) {
      List<List<String>> list = new ArrayList<>();
      if (s == null) return list;
      List<String> partition = new ArrayList<>();
      char[] charArray = s.toCharArray();
      //partition charArray from position 0, and add partion to list when finished
      partitionRecursion(charArray, list, partition, 0);
      return list;
    }

    private void partitionRecursion(char[] chars, List<List<String>> res, List<String> partition, int start) {
      if (start > chars.length - 1) {
        res.add(new ArrayList<>(partition));
        return;
      }
      for (int end = start; end < chars.length; end++) {
        if (isPalindrome(chars, start, end)) {
          partition.add(new String(chars, start, end - start + 1));
          partitionRecursion(chars, res, partition, end + 1);
          partition.remove(partition.size() - 1);
        }//else continue;
      }
    }


    private boolean isPalindrome(char[] chars, int left, int right) {
      while (left < right) {
        if (chars[left] != chars[right]) return false;
        left++;
        right--;
      }
      return true;
    }
  }

}

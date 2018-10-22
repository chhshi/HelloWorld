package DynamicProgramming;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class WordBreak {

  /**
   * https://leetcode.com/problems/word-break/description/
   *
   * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
   * The same word in the dictionary may be reused multiple times in the segmentation.
   * You may assume the dictionary does not contain duplicate words.
   * @return if s can be segmented into a space-separated sequence of one or more dictionary words.
   *
   * Input: s = "applepenapple", wordDict = ["apple", "pen"]
   * Output: true
   * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
   * Output: false
   */

  public boolean wordBreak(String s, List<String> wordDict) {

    int n = s.length();
    if (n == 0) return false;
    if (wordDict.size() == 0) return false;

    //    wordDict.stream().forEach(each -> set.add(each));  => lambda can be replaced with method reference ???
    //    wordDict.stream().forEach(set::add);    => stream chain can be replaced with forEach
    //    wordDict.forEach(set::add);   => replace iteration with bulk add Collection.addAll
    //    set.addAll(wordDict);   => allAll call can be replaced with parameterized constructor
    Set<String> set = new HashSet<>(wordDict);

    List<Object> objects = null;

    List<String> numbers = Optional.ofNullable(objects)
        .orElse(Collections.emptyList())
        .stream()
        .filter(BigInteger.class::isInstance)
        .map(BigInteger.class::cast)
        .map(BigInteger::toString)
        .collect(Collectors.toList());



    int maxlen = 0;
    for (String ss : wordDict) {
      maxlen = Math.max(maxlen, ss.length());  //dict里有的最长的单词是什么
    }

    boolean[] canBreak = new boolean[n+1]; //前i个字母能不能break，因为需要前0个的情况，default is false
    canBreak[0] = true;
    for (int i = 0; i < n; i++) {  //i是index，前i+1个
      for (int j = i; j >= 0; j--) {  //j是根据下面的等式需求写的：j需要有 前0~前i个
        canBreak[i + 1] = canBreak[j] && set.contains(s.substring(j, i+1));
        if (canBreak[i + 1] || i + 1 - j > maxlen) break;   //maxlen的优化非常非常重要！！！DP一定要做细节优化！！
      }
    }
    return canBreak[n];
  }



}

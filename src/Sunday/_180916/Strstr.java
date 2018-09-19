package Sunday._180916;

public class Strstr {



  // https://leetcode.com/problems/implement-strstr/description/
  // Return the index of the first occurrence of target in source, or -1 if target is not part of source.

  public int strStr(String source, String target) {

    if (target.length() == 0 && source.length() == 0 || target.length() == 0 ) return 0;
    if (target.length() > source.length() ) return -1;

    int s = source.length();
    int t = target.length();
    //abcd4 4-2=2
    //  ab
    for (int i = 0; i <= s - t; i++) {
      if (source.charAt(i) == target.charAt(0)) {
        int j = 1;
        while(j < t) {
          if (source.charAt(i + j) != target.charAt(j)) break;
          else j++;
        }
        if (j == t) return i;
      }

//      if (target.equals(source.substring(i, i + t))) {
//        return i;
//      }
    }
    return -1;
  }

}

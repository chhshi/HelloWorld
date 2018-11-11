package Algorithms.Unclassified;


/*
* https://leetcode.com/problems/implement-strstr/description/
*
* 取决于：能不能用库函数？？？charAt(), substring(), equals(), indexOf()
*
* */
public class StrStr {

    /**
     * 解法1 不用库函数，只用了charAt()这一个库函数
     * @param source
     * @param target
     * @return firstIndex of occurence
     */
    private static int findPosition(String source, String target) {

        //null check
        if (source == null || target == null)  return -1;

        //source.toCharArray()似乎更快
        int lenS = source.length();
        int lenT = target.length();

        //""empty string check
        if (lenT == 0) return 0;
        //length check
        if (lenS < lenT) return -1;

        //target is not empty && source is longer than target
        for (int k = 0; k < lenS - lenT + 1; k++) {
            if (source.charAt(k) != target.charAt(0)) continue;
            int i = 0;
            while (i < lenT && k + i < lenS) {
                if (source.charAt(k + i) != target.charAt(i)) break;
                if (i == lenT - 1) return k;
                i++;
            }
        }
        return -1;
    }

    /**
     * 解法2 用了indexOf这个库函数
     * @param source
     * @param target
     * @return
     */
    private static int findPosition2(String source, String target) {
        if (source == null || target == null) {
            return -1;
        }
        return source.indexOf(target);
    }


    /**
     * 解法3 用了substring 和 equals 这俩库函数
     * @param source
     * @param target
     * @return
     */
    private static int findPosition3(String source, String target) {
        if (source == null || target == null)  return -1;

        int lenS = source.length();
        int lenT = target.length();

        if (lenT == 0) return 0;
        if (lenS < lenT) return -1;

        for (int k = 0; k < lenS - lenT + 1; k++) {
            if (source.substring(k, k + lenT).equals(target)) return k;
        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(findPosition("hello", "ll") == 2);
        System.out.println(findPosition("aaaaa", "bba") == -1);
        System.out.println(findPosition("", "") == 0);
        System.out.println(findPosition("a", "a") == 0);

        System.out.println(findPosition2("hello", "ll") == 2);
        System.out.println(findPosition2("aaaaa", "bba") == -1);
        System.out.println(findPosition2("", "") == 0);
        System.out.println(findPosition2("a", "a") == 0);

        System.out.println(findPosition3("hello", "ll") == 2);
        System.out.println(findPosition3("aaaaa", "bba") == -1);
        System.out.println(findPosition3("", "") == 0);
        System.out.println(findPosition3("a", "a") == 0);

    }

}

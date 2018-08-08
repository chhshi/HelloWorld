package BinarySearch;

public class BinarySearchMisc {

    /**
     * https://www.lintcode.com/problem/wood-cut/description
     *
     * Given n pieces of wood with length L[i] (integer array).
     * Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length.
     * Given L & k
     *
     * @return the longest length you can get from the n pieces of wood, else return 0
     *
     * countPieces(L, mid) < k: 不够k段，只能切更短，mid必须舍弃 end = mid - 1
     * countPieces(L, mid) >= k: 超过或刚够k段，能尝试切更短，mid不能舍弃，start = mid
     * => 在只剩两个选择时候会死循环，选择 start +1 < end 这种loop方式
     *
     * O(n log Len), where Len is the longest length of the wood.
     */
    private static int woodCut(int[] L, int k) {

        //check null and empty
        if (L == null || L.length == 0) return 0;

        int start = 1;
        int end = findLongestWood(L);

        //check 3+ numbers
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;  //防止溢出
            if (countPieces(L, mid) < k) {
                end = mid - 1;
            } else {  // countPieces(L, mid) >= k, can be cut longer
                start = mid;  //可能有死循环
            }
        }
        //check 2 or 1 numbers
        if (countPieces(L, end) >= k) return end;
        else if (countPieces(L, start) >= k) return start;
        else return 0;
    }
    private static int findLongestWood(int[] L) {
        int max = 0;
        for (int len: L) {
            max = Math.max(max, len);
        }
        return max;
    }
    // O(n), n is the length of L
    private static int countPieces(int[] L, int cutLength) {
        int count = 0;
        for (int len : L) {
            count += len / cutLength;
        }
        return count;
    }

    /**
     * * https://leetcode.com/problems/sqrtx/description/
     *
     *  Compute and return the square root of x,
     *  where x is guaranteed to be a non-negative integer.
     *
     * @return the square root of x
     *
     * sqrt(x) > x / 2  => x > x^2 / 4 => x < 4
     * 只有x<4时候sqrt(x) > x / 2 => 除了x < 4的情况，都从x/2开始搜索
     */
    private static int sqrt(int x) {

        //check corner cases
        if (x == 0) return 0;
        if (x < 4) return 1;

        //search 2 ~ x/2， 也可以不check，改进不大
        int start = 2;
        int end = x/2;

        //check 3+ numbers
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (mid == x / mid) {     //mid * mid 会overflow  用除法判断
                return mid;
            } else if (mid < x / mid) {  //mid不可抛
                start = mid;
            } else {
                end = mid - 1;
            }
        }

        //check 2 or 1 elements
        if (end <= x / end) return end;
        return start;

    }


    /**
     * https://leetcode.com/problems/first-bad-version/description/
     *
     * @return
     */
    private static int firstBadVersion(int[] nums) {

        return -1;
    }

    public static void  main(String[] args) {
        System.out.println("woodCut");
        System.out.println(woodCut(new int[]{1,2,3,4,6,8}, 3) == 4);
        System.out.println(woodCut(new int[]{1,2,3,4,6,8}, 4) == 4);
        System.out.println(woodCut(new int[]{1,2,3,4,6,8}, 5) == 3);
        System.out.println(woodCut(new int[]{232, 124, 456}, 7) == 114);
        System.out.println(woodCut(new int[]{2147483644,2147483645,2147483646,2147483647}, 4) == 2147483644);

        System.out.println("sqrt");
        System.out.println(sqrt(3) == (int)Math.sqrt(3));
        System.out.println(sqrt(4) == (int)Math.sqrt(4));
        System.out.println(sqrt(80));
        System.out.println(sqrt(0) == (int)Math.sqrt(0));

    }

}

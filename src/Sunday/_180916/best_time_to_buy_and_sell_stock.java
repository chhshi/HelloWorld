package Sunday._180916;

public class best_time_to_buy_and_sell_stock {

  /**
   * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
   *
   * Say you have an array for which the ith element is the price of a given stock on day i.
   * Design an algorithm to find the maximum profit. You may complete at most two transactions.
   * You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
   */

  public int maxProfitIII(int[] prices) {

    //      [3,3,5,0,0,3,1,4]
    //min MAX,3,3,3,0,0,0,0,0
    //      [0,0,2,0,0,3,1,4]
    // max  [0,0,2,2,2,3,3,4]

    //      [3,3,5,0,0,3,1,4]
    //max   5,5,5,4,4,4,4,4,MIN
    //      [2,2,0,4,4,1,3,0]
    // max  [3,3,3,3,3,3,3,0]

    // left max  [0,0,2,2,2,3,3,4]
    // right max [3,3,3,3,3,3,3,0]
    //=>          [3,3,5,5,5,6,3,4]

    if (prices == null || prices.length < 2) return 0;
    //find max from left
    int[] maxDiffFromLeft = new int[prices.length];
    int min = prices[0];
    maxDiffFromLeft[0] = 0;
    for (int i = 1; i < prices.length; i++) {
      maxDiffFromLeft[i] = Math.max(maxDiffFromLeft[i - 1], prices[i] - min);
      min = Math.min(min, prices[i]);
    }

    //find max from right
    int[] maxDiffFromRight = new int[prices.length];
    int max = prices[prices.length - 1];
    maxDiffFromRight[prices.length - 1] = 0;
    for (int j = prices.length - 2; j >= 0; j--) {
      maxDiffFromRight[j] = Math.max(maxDiffFromRight[j + 1], max - prices[j]);
      max = Math.max(max, prices[j]);
    }


//    int result = Math.max(maxDiffFromLeft[prices.length - 1], maxDiffFromRight[0]);
//    for (int k = 1; k < prices.length; k++) {
//      result = Math.max(result, maxDiffFromLeft[k - 1] + maxDiffFromRight[k]);
//    }

    int result = 0;
    for (int k = 0; k < prices.length; k++) {
      result = Math.max(result, maxDiffFromLeft[k] + maxDiffFromRight[k]);
    }

    return result;
  }
}

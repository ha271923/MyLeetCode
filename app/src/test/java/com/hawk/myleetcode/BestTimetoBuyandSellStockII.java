package com.hawk.myleetcode;

import org.junit.Test;

/**
 * Created by Edward on 28/07/2017.
 */
public class BestTimetoBuyandSellStockII {
    @Test
    public void main() {
        int[] input = {7, 1, 5, 3, 6, 4};
        int out = maxProfit_H(input);
        System.out.println("out=" + out);
    }
    /**
     * 難在題意: 英文題目容易誤導，這題目是要「有賺就賣」的策略且無限制交易次數，並無要求「最大化利潤」, 且限制time : O(n)，這只有1 Loop不可能最大化利潤
     * 122. Best Time to Buy and Sell Stock II
     * Say you have an array for which the ith element is the price of a given stock on day i.

     Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However,
     you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

     輸入: prices = [7,1,5,3,6,4]
     輸出: 7

     解釋:
     第 2 天 (價格=1) 買入, 第 3 天 (價格=5) 賣出 → 利潤 = 4
     第 4 天 (價格=3) 買入, 第 5 天 (價格=6) 賣出 → 利潤 = 3
     總利潤 = 4 + 3 = 7

     time : O(n);
     space : O(1);
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
    public static int maxProfit_H(int[] prices) {
        int maxProfit = 0;
        for(int i=1; i< prices.length; i++){
            if(prices[i] - prices[i-1] > 0) {
                maxProfit += prices[i] - prices[i-1];
            }
        }

        return maxProfit;
    }
}

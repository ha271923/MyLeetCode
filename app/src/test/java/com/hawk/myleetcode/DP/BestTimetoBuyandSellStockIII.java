package com.hawk.myleetcode.DP;

import org.junit.Test;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BestTimetoBuyandSellStockIII
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 123. Best Time to Buy and Sell Stock III
 */
public class BestTimetoBuyandSellStockIII {

    @Test
    public void main() {
        int[] input = {3,3,5,0,0,3,1,4}; // out=6, 依據題目的2限制, 我們可以在第四天買入,第六天賣出(賺3); 在第七天買入, 第八天賣出(賺3)。所以總共賺6
        int out = maxProfit(input);
        System.out.println("out=" + out);
    }
    /**
     * 題目要求
     * 給定一個整數陣列 prices，其中 prices[i] 表示第 i 天的股票價格。
     * 限制1: 你最多只能進行 兩次交易（買入和賣出各算一次交易）。 You may complete at most two transactions.
     * 限制2: 你不能同時在還沒賣掉手上股票前, 又買入另一天的股票 (手上沒股票時才能買股票)
     * 設計一個演算法來計算可以獲得的最大利潤。
     *
     * Example 1:
     * Input: prices = [3,3,5,0,0,3,1,4]  Output: 6
     * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
     * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
     *
     * Example 2:
     * Input: prices = [1,2,3,4,5] Output: 4
     * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
     * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
     *
     * Example 3:
     * Input: prices = [7,6,4,3,1] Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     *
     * time : O(n)
     * space : O(1)
     * @param prices
     * @return
     */

    public int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE; // 再Math.max中,任何一筆購買都可更新這個值
        int revenue1 = 0, revenue2 = 0; // revenue=buy-price,其中revenue2是累加revenue1所獲取的利潤
        for (int price : prices) {
            buy1 = Math.max(buy1, -price); // KEY: 當買入時，需要支付該天的price，因此對於利潤來說是負值。
            revenue1 = Math.max(revenue1, buy1 + price); // KEY: 這裡是用ADD

            buy2 = Math.max(buy2, revenue1 - price);
            revenue2 = Math.max(revenue2, buy2 + price);

            System.out.println("price="+price+"  buy1=" + buy1+"  revenue1="+revenue1+"  buy2="+buy2+"  revenue2="+revenue2);
        }
        return revenue2;
    }

}

package com.hawk.myleetcode;

import org.junit.Test;

/**
 * Created by Edward on 28/07/2017.
 */
public class AddBinary {
    /**
     * 67. Add Binary
     * Given two binary strings, return their sum (also a binary string).

     For example,
     a = "11"
     b = "1"
     Return "100".

     time : O(n);
     space : O(n);
     * @param a
     * @param b
     * @return
     */
    @Test
    public void main() {
        System.out.println("----------------------------------------------------------------");
        // System.out.println(addBinary("11","1"));
        System.out.println(addBinary_H("1010","1011"));
    }
/*
   LOOP:   9876543210
           ----------
   sBin1         1010
   sBin2         1011
           ----------
                    1
                  10    => 2 -> sum=a[1]+b[1]+carry, carry=sum/2=1, sb=sum%2=0
                  0
                10      => 2 -> sum=a[3]+b[3]+carry, carry=sum/2=1, sb=sum%2=0
           ----------
     Ans        10101

 */
    public static String addBinary(String sBin1, String sBin2) {
        StringBuilder sb = new StringBuilder();
        int i1 = sBin1.length() - 1;
        int i2 = sBin2.length() - 1;
        int carry = 0; // 餘數
        while (i1 >= 0 || i2 >= 0) { // LOOP: i1與i2長度可能不相等
            int sum = carry;
            if (i1 >= 0) // 此時i2可能已經 < 0
                sum += sBin1.charAt(i1) - '0'; // 因為ASCII的數字字元是連續編排
            if (i2 >= 0) // 此時i1可能已經 < 0
                sum += sBin2.charAt(i2) - '0';
            // KEY: algorithm
            carry = sum / 2;
            sb.append(sum % 2);

            System.out.println("i1="+i1+"  i2="+i2+" sb="+sb +"  sum="+sum);
            i1--;i2--;
        }
        if (carry != 0) { // Tips: 最後的餘數
            sb.append(carry);
        }
        return sb.reverse().toString(); // KEY: 輸出前將append後的次序,反轉
    }

    /*
    AI:
    初始化變數：
        使用 StringBuilder 來構建結果字串。
        i1 和 i2 分別指向 sBin1 和 sBin2 的最後一個字符（從右向左處理）。
        carry 用於存儲進位值。
    處理二進制加法：
        使用 while 循環，直到兩個字串都處理完（即 i1 和 i2 都小於 0）。
        每次迴圈開始時，將 carry 的值賦給 sum。
        如果 i1 尚未處理完，將 sBin1 的當前字符轉換為數字並加到 sum，然後將 i1 減 1。
        如果 i2 尚未處理完，將 sBin2 的當前字符轉換為數字並加到 sum，然後將 i2 減 1。
    計算當前位與進位：
        將 sum % 2（當前位的值）附加到 StringBuilder。
        更新 carry 為 sum / 2（進位值）。
    處理剩餘進位：
        如果最後還有進位（carry != 0），將其附加到 StringBuilder。
    反轉結果：
        由於結果是從低位到高位構建的，最後需要反轉字串以獲得正確的二進制順序。
    返回結果：
        返回反轉後的字串。
     */
    public static String addBinary_AI(String sBin1, String sBin2) {
        StringBuilder sb = new StringBuilder();
        int i1 = sBin1.length() - 1; // Pointer for sBin1
        int i2 = sBin2.length() - 1; // Pointer for sBin2
        int carry = 0; // Carry for binary addition

        // Loop until both strings are processed
        while (i1 >= 0 || i2 >= 0) {
            int sum = carry; // Start with the carry value

            // Add the binary digit from sBin1 if available
            if (i1 >= 0) {
                sum += sBin1.charAt(i1) - '0';
                i1--;
            }

            // Add the binary digit from sBin2 if available
            if (i2 >= 0) {
                sum += sBin2.charAt(i2) - '0';
                i2--;
            }

            // Append the current binary digit (sum % 2) to the result
            sb.append(sum % 2);

            // Update the carry (sum / 2)
            carry = sum / 2;
        }

        // If there is a remaining carry, append it
        if (carry != 0) {
            sb.append(carry);
        }

        // Reverse the result to get the correct binary order
        return sb.reverse().toString();
    }

    //  索引方向, 演算法, 文字反轉
    //   sBin1         1010
    //   sBin2         1011
    public static String addBinary_H(String sBin1, String sBin2) {
        StringBuilder strOutput = new StringBuilder();

        int idx1=sBin1.length() -1; // 因為len作為char的index, 所以比length少1
        int idx2=sBin2.length() -1;
        int v1=0,v2=0;
        int carry=0, sum=0;

        while(idx1 >=0 || idx2 >=0 || carry != 0){
            v1 = 0; v2=0;
            if(idx1 >= 0) {
                v1 = sBin1.charAt(idx1) - '0';
                idx1--;
            }

            if(idx2 >= 0) {
                v2 = sBin2.charAt(idx2) - '0';
                idx2--;
            }

            sum = v1+v2+carry;
            carry = sum/2;
            strOutput.append(sum%2);
        }
        strOutput = strOutput.reverse();
        return strOutput.toString();
    }
}

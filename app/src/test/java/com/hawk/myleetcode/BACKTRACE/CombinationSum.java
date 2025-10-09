package com.hawk.myleetcode.BACKTRACE;

import com.hawk.myleetcode.basic.utils.Out;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : CombinationSum
 * Creator : Edward
 * Date : Aug, 2017
 * Description : TODO
 */
public class CombinationSum {
    @Test
    public void main() {
        int[] dataSet = {2, 3, 6, 7};
        List<List<Integer>> out = combinationSum_H(dataSet, 7);
        System.out.println("Result -------------------------------------------------------");
        if(out != null)
            Out.printListList(out);
    }
    /**
     * 39. Combination Sum
     * Given a set of candidate numbers (C) (without duplicates) and a target number (T),
     * find all unique combinations in C where the candidate numbers sums to T.

     The same repeated number may be chosen from C unlimited number of times.

     Note:
     All numbers (including target) will be positive integers.
     The solution set must not contain duplicate combinations.
     For example, given candidate set [2, 3, 6, 7] and target 7,
     A solution set is:
     [
       [7],
       [2, 2, 3]
     ]

     time : O(2^n)
     space : O(n)
     * @param dataSet
     * @param target
     * @return
     */
    /**
     *
     A. 數字可重複時, 此例recursive深度最高為 4, 迴圈數 4^2
       r0=[2, 3, 6, 7]
       r1=[2, 3, 6, 7]
       r2=[2, 3, 6, 7]
       r3=[2, 3, 6, 7]

     B. 數字不可重複時, 此例recursive深度最高為 4, 迴圈數 4!
       r0=[2, 3, 6, 7]
       r1=[   3, 6, 7]
       r2=[      6, 7]
       r3=[         7]

     PS:適當的剪枝可以減少不必要的計算
       1. if (target < 0)
       2. hashMap, 當只要數量不需數組時, 這時候不需往下列舉出所有數組
     */
    // 先畫出挑數字過程如下,發現數字規律有一層一層往下探(backtrack/DFS),也有剪枝(backtrack),所以用backtrack/DFS來思考此題:
    //  recur1 recur2   recur3     recur4
    //  [2] -> [2,2] -> [2,2,2] (6)
    //                          -> [2,2,2,2] xx (8>7) Backtrack: 因為數列是排序過,不須往下個數字掃
    //               -> [2,2,3] == (7) 退回上一層後,因為數字2已經列舉完,直接往下個數字3掃,因為已經等於7,所以不需往223的下層[2,2,3,2]掃
    //               -> [2,2,6] xx (10>7) Backtrack: 因為6掃完了(後面7不用掃),故退回上一層
    //      -> [2,3] (5)
    //               -> [2,3,3] xx (8>7) Backtrack: 遞迴邏輯是基於「組合」而非「排列」,因此start變數確保了每次遞迴只能選擇當前數字或之後的數字,所以列舉過程不會有[2,3,2]
    //      -> [2,6] xx (8>7)
    //      -> [2,7] xx (9>7)
    //  [3] -> [3,3] (6)
    //               -> [3,3,3] xx (9>7)
    //      -> [3,6] xx (9>7)
    //  [6] xx (6)
    //  [7] == (7)
    //
    public List<List<Integer>> combinationSum_backtrack(int[] dataSet, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(dataSet, target, 0, new ArrayList<>(), result);
        return result;
    }

    // backtrack func參數設計思考: dataSet 可挑選的數組 , target 目標數字 , index 選擇dataSet陣列中的一個數字 , path 記錄嘗試過的數組 , result 輸出結果
    public void backtrack(int[] dataSet, int target, int index, List<Integer> path, List<List<Integer>> result) {
        if (target < 0) // 6a. 超過target, 不要再往下recursive
            return;
        if (target == 0) {
            result.add(new ArrayList<>(path)); // 6b. 獲得一組答案, 往回退一層, 探索下一個數字
            return;
        }

        for (int i = index; i < dataSet.length; i++) { // 1. 依序列舉所有的數
            // 2. 取过的数不再取
            path.add(dataSet[i]); // 3. 取出一个数
            System.out.println(path); // for debug
            backtrack(dataSet, target - dataSet[i], i, path, result); // 4. 持續縮小問題, 此題往下遍歷, 並提供正確參數, ，pos不須+1, 因為此題:取过的数仍可再取
            path.remove(path.size() - 1); // 5. 重要！！遍历过此节点后，要回溯到上一步，因此要把加入到结果中的此点去除掉！
        }
    }
    // ERROR: 若 path.remove(i) 是基於索引移除元素，但 path 的大小可能與 dataSet 的大小不同，這會導致錯誤。
    /*
    1. Backtracking: 剪枝是核心，會在遞迴過程中動態檢查約束條件，提前排除不符合條件的分支。
    2. DFS (深度優先搜索): 主要用於遍歷所有可能的路徑或節點，重點在於「遍歷(搜索)」。剪枝不是 DFS 的核心，但可以根據需求加入。

        在 Combination Sum 問題中，兩者的實現幾乎相同，差異主要在於思維方式和應用場景的側重點。
    * */
    public List<List<Integer>> combinationSum_DFS(int[] dataSet, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(dataSet, target, 0, new ArrayList<>(), result);
        return result;
    }

    // DFS func參數設計思考: dataSet 可挑選的數組 , target 目標數字 , index 選擇dataSet陣列中的一個數字 , path 記錄嘗試過的數組 , result 輸出結果
    private void dfs(int[] dataSet, int target, int index, List<Integer> path, List<List<Integer>> result) {
        if (target < 0) {
            return; // 如果目標值小於 0，直接返回
        }
        if (target == 0) {
            result.add(new ArrayList<>(path)); // 如果目標值為 0，將當前路徑加入結果
            return;
        }
        for (int i = index; i < dataSet.length; i++) {
            path.add(dataSet[i]); // 選擇當前數字
            System.out.println(path); // for debug
            dfs(dataSet, target - dataSet[i], i, path, result); // 繼續遞迴，允許重複選擇
            path.remove(path.size() - 1); // 回溯，移除最後一個數字
        }
    }
    public List<List<Integer>> combinationSum_H(int[] dataSet, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrace_H(dataSet, target, 0, new ArrayList<>(), result);
        System.out.println();
        return result;
    }
    private void backtrace_H(int[] dataSet, int target, int index, List<Integer> path, List<List<Integer>> result){
        for(int i=index; i<dataSet.length; i++) {
            if(target - dataSet[i] == 0) { // found
                path.add(dataSet[i]);
                result.add(new ArrayList<>(path)); // path 是一個引用類型，當它被修改時，會影響到已經存入 result 的結果, 應該存入 path 的一個拷貝，而不是直接存入引用
                path.remove(path.size() -1);
                return;
            } else if((target - dataSet[i]) > 0){ // find next val
                path.add(dataSet[i]);
                backtrace_H(dataSet, target - dataSet[i], i, path, result); // find next val
                path.remove(path.size() -1);
            } else if((target - dataSet[i]) < 0) { // over target
                return;
            }
        }
    }

}

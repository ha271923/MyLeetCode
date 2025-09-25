package com.hawk.myleetcode;

import static com.hawk.myleetcode.utils.Out.print_ListNode;

import com.hawk.myleetcode.data.ListNode;
import org.junit.Test;
/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PartitionList
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 86. Partition List
 */
public class PartitionList {
    @Test
    public void main() {
        int x = 3;
        ListNode[] test = new ListNode[6];
        test[0] = new ListNode(1);
        test[1] = new ListNode(4);
        test[2] = new ListNode(3);
        test[3] = new ListNode(2);
        test[4] = new ListNode(2);
        test[5] = new ListNode(5);
        test[0].next = test[1];
        test[1].next = test[2];
        test[2].next = test[3];
        test[3].next = test[4];
        test[4].next = test[5];
        test[5].next = null;
        ListNode head = test[0];

        ListNode current = head;

        // CallByRef, 未使用new ListNode
        partition_CallByRef(current, x);
        print_ListNode(current);

        // CallByValue, 使用new ListNode
        // print_ListNode(partition_CallByValue(current, x));
    }

    /**
     * KEY: 看懂題目意思!! 僅針對所有比x小的node, 挑出來並放到比x大的數前面,不是全體排序,而是局部符合條件者排序
     * 给定一个LinkedList和一个特定值 x，对LinkedList进行分隔，使得
     * 所有小于 x 的Node都在大于或等于 x 的Node之前。
     * 你应当保留两个分区中每个Node的初始相对位置, 不要重新排序
     *
     * Given a linked list and a value x, partition it such that all nodes less than x come before nodes
     * greater than or equal to x.

     You should preserve the original relative order of the nodes in each of the two partitions.

     For example,
     Given 1->4->3->2->5->2 and x = 3,
     return 1->2->2->4->3->5.


     1->4->3->2->5->2 and x = 3
     smallHead -> 1 -> 2 -> 2 ->
                          smallPtr(移動)
     bigHead -> 4 -> 3 -> 5 ->
                         bigPtr(移動)

     time : O(n)
     space : O(n)


     Q1: [1,4,3,2,5,2]        A1: [1,2,2,4,3,5]
     Q2: [1,4,7,3,2,8,5,2]    A2: [1,2,2,4,7,3,8,5]
     把小於x=3的數值都放在3的前面

     * @param head
     * @param x
     * @return
     */
    // 1. 兩個新節點空頭smallHead and bigHead, 此固定不移動
    // 1. 掃描兩個新節點空頭的指標smallPTR and bigPTR, 此固定不移動
    // 2. 掃head的值, 掃到小於就串接到small, 掃到大於就串接到big
    // 3. 將(small的PTR)跟(big的Head)接起來,
    // 4. 因為一開始有new 最前方的ListNode(0), 所以返回smallHead.next才能消除Node(0)
    static public void partition_CallByRef(ListNode head, int x) {
        ListNode leftHead = new ListNode(0); // fix at head
        ListNode rightHead = new ListNode(0);
        ListNode leftTail = leftHead; // moveable pointer
        ListNode rightTail = rightHead;

        while(head != null) {
            if(head.val < x){
                leftTail.next = head;
                leftTail = leftTail.next;
            }else{
                rightTail.next = head;
                rightTail = rightTail.next;
            }
            head = head.next;
        }
        rightTail.next = null;
        leftTail.next = rightHead.next;
        head = leftHead.next;
    }

    static public ListNode partition_CallByValue(ListNode head, int x) {
        ListNode leftHead = new ListNode(0); // fix at head
        ListNode rightHead = new ListNode(0);
        ListNode leftTail = leftHead, rightTail = rightHead; // moveable pointer

        while (head != null) { // 從頭掃到尾
            if (head.val < x) {
                leftTail.next = new ListNode(head.val); // 使用New
                leftTail = leftTail.next;
            } else {
                rightTail.next = new ListNode(head.val);
                rightTail = rightTail.next;
            }
            head = head.next;
        }

        // 確保右邊結束
        rightTail.next = null;

        // 接起來
        leftTail.next = rightHead.next;

        return leftHead.next;
    }

    static public void testAdd(int a){
        a++;
    }


}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode plusOne(ListNode head) {
        if (helper(head) == 0)
            return head;
        else {
            ListNode newHead = new ListNode(1);
            newHead.next = head;
            return newHead;
        }
    }
    
    private int helper(ListNode head) {
        if (head == null) return 1;
        
        int carry = helper(head.next);
        
        if (carry == 0) return 0;
        
        int tmp = head.val + 1;
        head.val = tmp % 10;
        return tmp / 10;
    }
}

recursion

https://discuss.leetcode.com/topic/49541/java-recursive-solution
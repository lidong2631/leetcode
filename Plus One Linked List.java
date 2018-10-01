Given a non-negative number represented as a singly linked list of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.

Example:
Input:
1->2->3

Output:
1->2->4



Java:
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
        if (helper(head) == 0) return head;
        ListNode dummy = new ListNode(1);
        dummy.next = head;
        return dummy;
    }
    
    private int helper(ListNode p) {    // helper returns carry
        if (p == null) return 1;
        
        int carry = helper(p.next);
        
        if (carry == 0) return 0;
        
        int tmp = p.val + carry;
        p.val = tmp % 10;
        return tmp / 10;
    }
}

recursion

https://discuss.leetcode.com/topic/49541/java-recursive-solution
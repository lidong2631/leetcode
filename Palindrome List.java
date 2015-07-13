/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head==null || head.next==null)
            return true;
        int len = 0;
        ListNode curr = head;
        while(curr!=null) {
            curr = curr.next;
            len++;
        }
        curr = head.next;
        ListNode prev = head;
        head.next = null;
        for(int i=0; i<len/2-1; i++) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        if(len%2==1)
            curr = curr.next;
        while(curr!=null) {
            if(prev.val!=curr.val)
                return false;
            prev = prev.next;
            curr = curr.next;
        }
        return true;
    }
}

先将链表前半reverse 再逐个比较 O(n) O(1)
https://leetcode.com/discuss/44840/my-ac-java-solution-reverse-and-compare-o-n-time-o-1-space
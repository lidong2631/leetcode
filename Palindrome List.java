Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?



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
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode p1 = head, p2 = slow.next, curr = slow.next.next;
        slow.next = null;
        p2.next = null;     // careful do not forget
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = p2;
            p2 = curr;
            curr = next;
        }
        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }
}



先将链表前半reverse 再逐个比较 O(n) O(1)
https://leetcode.com/discuss/44840/my-ac-java-solution-reverse-and-compare-o-n-time-o-1-space
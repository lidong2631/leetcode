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
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode p1 = head, p2 = slow.next, curr = slow.next.next;
        slow.next = null;
        p2.next = null;
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
        if (head == null || head.next == null) return true;
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }
        p = head;
        for (int i = 0; i < len / 2 - 1; i++) p = p.next;
        ListNode prev = p.next;
        if (len % 2 == 0) p.next = null;
        else {
            prev = prev.next;
            p.next.next = null;
        }
        ListNode curr = prev.next;
        prev.next = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        p = head;
        while (prev != null && p != null) {
            if (prev.val != p.val) return false;
            prev = prev.next; p = p.next;
        }
        return true;
    }
}

先将链表前半reverse 再逐个比较 O(n) O(1)
https://leetcode.com/discuss/44840/my-ac-java-solution-reverse-and-compare-o-n-time-o-1-space
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = head;
        while (curr.next != null) {
            if (curr.val > curr.next.val) {
                ListNode prev = dummy;
                while (prev.next.val < curr.next.val) prev = prev.next;
                ListNode tmp = curr.next;
                curr.next = curr.next.next;
                tmp.next = prev.next;
                prev.next = tmp;
            }
            else curr = curr.next;
        }
        return dummy.next;
    }
}
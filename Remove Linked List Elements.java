/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy;
        while (p.next !=null) {
            if (p.next.val == val)
                p.next = p.next.next;
            else p = p.next;
        }
        return dummy.next;
    }
}

时间O(n) 空间O(1)
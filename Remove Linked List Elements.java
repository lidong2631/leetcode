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
        if(head==null)
            return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy;
        while(p!=null && p.next!=null) {
            if(p.next.val==val) {
                ListNode tmp = p.next;
                while(tmp!=null && tmp.val==val)
                    tmp = tmp.next;
                p.next = tmp;
            }
            p = p.next;
        }
        return dummy.next;
    }
}

简单题 但要注意几个corner case 如链表所有节点都是val值 还是老办法在头节点前加一个dummy node比较好处理

类似题目remve nth node from end of list

时间O(n) 空间O(1)
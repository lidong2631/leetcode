/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode curr = head.next, prev = head;
        head.next = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}

O(n) O(1)





Recursive
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next==null)
            return head;
        ListNode curr = head;
        List<ListNode> res = new ArrayList<ListNode>();
        helper(curr, res);
        return res.get(0);
    }
    
    private void helper(ListNode curr, List<ListNode> res) {
        if(curr.next==null) {
            res.add(curr);
            return;
        }
        helper(curr.next, res);
        curr.next.next = curr;
        curr.next = null;
    }
}






public void reverse(ListNode curr) {
    if(curr==null)  //check for empty list
        return;

    if(curr.next==null) {   //base case
        head = curr;
        return;
    }

    reverse(curr.next);
    curr.next.next = curr;
    curr.next = null;
}
Iterative
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
        ListNode second = head.next;
        ListNode third = head.next.next;
        
        second.next = head;
        head.next = null;
        if(third==null)
            return second;
        ListNode curr = third; ListNode prev = second;
        while(curr!=null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}

from programmerInterview O(n) O(1)





public void reverse(ListNode head) {
    if(head==null || head.next==null)
        return;
    ListNode second = head.next;
    ListNode third = second.next;

    second.next = head; //reverse
    head.next = null;

    if(third==null)
        return;

    ListNode curr = third;
    ListNode pre = second;
    while(curr!=null) { //iterative reverse
        ListNode next = curr.next;
        curr.next = pre;
        pre = curr;
        curr = next;
    }
    head = pre;
}





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
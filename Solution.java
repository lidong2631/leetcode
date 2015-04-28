public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode p1 = new ListNode(2);
        ListNode p2 = new ListNode(1);
        p1.next = p2;
        p2.next = null;
        ListNode p = s.partition(p1, 2);
        // while(p!=null)
            // System.out.println(p.val);
    }
    
    public ListNode partition(ListNode head, int x) {
        ListNode p1 = new ListNode(0), head1 = p1;
        ListNode p2 = new ListNode(0), head2 = p2;
        ListNode p = head;
        while(p!=null) {
            if(p.val<x) {
                p1.next = p;
                p = p.next;
                p1 = p1.next;
            }
            else {
                p2.next = p;
                p = p.next;
                p2 = p2.next;
            }
        }
        System.out.println(p1.val);
        System.out.println(p2.val);
        p1.next = head2.next;
        System.out.println(p1.val);
        System.out.println(p1.next.val);
        System.out.println(p1.next.next.val);
        System.out.println(p1.next.next.next.val);
        return head1.next;
    }
}
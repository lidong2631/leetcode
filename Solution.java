public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(1);
        s.rotateRight(head, 0);
    }
    public ListNode rotateRight(ListNode head, int n) {
        if(head==null)
            return head;
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        p.next = head;
        int length = 0;
        while(p.next!=null) {  
            p = p.next;
            length++;
        }
        n = n%length;   
        p.next = head;  
        for(int i=0; i<length-n; i++) { 
            System.out.println(dummy.val);
            dummy = dummy.next;
        }
        System.out.println(dummy.val);
        System.out.println(dummy.next.val);
        ListNode newHead = dummy.next; 
        dummy.next = null;
        return newHead;
    }
}
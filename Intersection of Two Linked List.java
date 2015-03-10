/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null || headB==null)
            return null;
        int lenA = 1; int lenB = 1;
        ListNode pA = headA; ListNode pB = headB;
        while(pA.next!=null) {
            lenA++;
            pA = pA.next;
        }
        while(pB.next!=null) {
            lenB++;
            pB = pB.next;
        }
        pA = headA; pB = headB;
        if(lenA>lenB) {
            for(int i=0; i<lenA-lenB; i++)
                pA = pA.next;
        }
        else {
            for(int i=0; i<lenB-lenA; i++)
                pB = pB.next;
        }
        while(pA!=pB) {
            pA = pA.next;
            pB = pB.next;
        }
        return pA;
    }
}
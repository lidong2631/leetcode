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
        while(pA!=pB) {     //这里有个有意思的地方 当A和B没有交点 则最终pA pB均为null 在java中null==null
            pA = pA.next;
            pB = pB.next;
        }
        return pA;
    }
}

注意这题引发一个问题就是java中null值是可以跟object相比较 null和null也可以比较 在java中是相等的(null==null)=true

另外这题有多个解法 看cleanbook
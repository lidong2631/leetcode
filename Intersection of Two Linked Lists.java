Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
Credits:
Special thanks to @stellari for adding this problem and creating all test cases.





Java:
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
        if (headA == null || headB == null) return null;
        int lenA = 1, lenB = 1;
        ListNode pA = headA, pB = headB;
        while (pA.next != null) {
            lenA++;
            pA = pA.next;
        }
        while (pB.next != null) {
            lenB++;
            pB = pB.next;
        }
        pA = headA; pB = headB;
        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; i++) 
                pA = pA.next;
        }
        else {
            for (int i = 0; i < lenB - lenA; i++) 
                pB = pB.next;
        }
        while (pA != pB) {
            pA = pA.next;
            pB = pB.next;
        }
        return pA;
    }
}

注意这题引发一个问题就是java中null值是可以跟object相比较 null和null也可以比较 在java中是相等的(null==null)=true

另外这题有多个解法 看cleanbook
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @return a list node
    def detectCycle(self, head):
        if head == None or head.next == None:   #如果head为空或只有一个节点 返回None
            return None

        slow = fast = head
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
            if slow == fast:                #循环直到slow==fast 或fast，fast.next为None
                break

        if slow == fast:                    #如果slow==fast说明存在环路 否则无环路返回None
            slow = head
            while slow!=fast:
                slow = slow.next
                fast = fast.next
            return slow
        return None




解题思路：这道题有点意思。首先使用快慢指针技巧，如果fast指针和slow指针相遇，则说明链表存在环路。具体技巧参见上一篇http://www.cnblogs.com/zuoyuan/p/3701639.html

在fast指针和slow指针相遇后，fast指针不动，slow指针回到head，然后slow指针和fast指针同时向前走，只不过这一次两个指针都是一步一步向前走。两个指针相遇的节点就是环路的起点。

示意图：



原理说明：图中，head到环路起点的距离为K，起点到fast和slow的相遇点的距离为M，环路周长为L。假设，在fast和slow相遇时，fast走过了Lfast，slow走过了Lslow。根据题意：

　　　　　Lslow=K+M；Lfast=K+M+n*L（n为正整数）；Lfast=2*Lslow

　　　　   可以推出：Lslow=n*L；K=n*L-M

　　　　　则当slow重新回到head，而fast还在相遇点，slow和fast都向前走，且每次走一个节点。

　　　　   则slow从head走到起点走了K，而fast从相遇点出发也走了K，而fast向前走了距离K后到了哪里呢？由于K=（n-1）*L+（L-M），所以fast转了n-1圈，再走L-M，也到了起点。这样起点就找到了。

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @return a list node
    def detectCycle(self, head):
        if head == None or head.next == None:
            return None
        slow = fast = head
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
            if fast == slow:
                break
        if slow == fast:
            slow = head
            while slow != fast:
                slow = slow.next
                fast = fast.next
            return slow
        return None






/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head==null || head.next==null)
            return null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast)
                break;
        }
        if(slow==fast)
        {
            slow = head;
            while(slow!=fast)
            {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
        return null;
    }
}

Note: 公式推倒南郭讲的比code ganker易懂 理解原理后编程很容易 就是快慢指针第一次相遇后将慢指针放回head 然后两指针以同样速度前进 相遇既是cycle起始点





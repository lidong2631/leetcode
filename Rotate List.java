Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL



Python:
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def rotateRight(self, head, k):
        """
        :type head: ListNode
        :type k: int
        :rtype: ListNode
        """
        if not head or head.next == None:
            return head
        
        right = head
        length = 1

        while right.next:
            right = right.next
            length += 1

        k %= length

        right = head
        for i in range(k):
            right = right.next
        if not right:
            return head
        left = head
        while right.next:
            left = left.next
            right = right.next

        right.next = head
        head = left.next
        left.next = None
        return head




Java:
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode right = head;
        int len = 1;
        while (right.next != null) {
            right = right.next;
            len++;
        }
        k = k % len;                    // careful need to mod k with len. k is possibly larger than len
        right = head;                   // careful set back right
        for (int i = 0; i < k; i++)     // right go k step ahead
            right = right.next;
        if (right == null) return head; // when k = len no move

        ListNode left = head;
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        right.next = head;              // connect with head
        head = left.next;               // set new head
        left.next = null;               // cut off
        return head;
    }
}


connect list cycle first then go to cut off point set new head and cut off




from code ganker:

这是一道链表操作的题目，基本思路是用walker-runner定位到要旋转的那个结点，然后将下一个结点设为新表头，并且把当前结点设为表尾。

需要注意的点就是旋转的结点数可能超过链表长度，

所以我们要对这个进行取余。定位旋转的尾结点的不超过链表的长度，所以时间复杂度是O(n)，空间复杂度是O(1)。

public ListNode rotateRight(ListNode head, int n) {
    if(head == null)
    {
        return null;
    }
    ListNode walker = head;
    ListNode runner = head;
    int idx = 0;
    while(runner!=null && idx<n)
    {
        runner = runner.next;
        idx++;
    }
    if(runner == null)
    {
        n %= idx;
        runner = head;
        idx=0;
        while(runner!=null && idx<n)
        {
            runner = runner.next;
            idx++;
        }
    }
    while(runner.next!=null)
    {
        walker = walker.next;
        runner = runner.next;
    }
    runner.next = head;
    ListNode newHead = walker.next;
    walker.next = null;
    return newHead;
}

上面的实现中采取的方式是直接走到那个结点，如果没超过长度就直接旋转了，如果超过了，就进行取余，并且重新跑到结尾点。其实也可以先直接求长度，然后取余之后再走。

其实各有利弊，所以大家根据喜好实现即可哈
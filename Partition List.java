Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:

Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5



# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @param x, an integer
    # @return a ListNode
    def partition(self, head, x):
        head1 = ListNode(0)
        head2 = ListNode(0)
        Tmp = head
        phead1 = head1
        phead2 = head2
        while Tmp:
            if Tmp.val < x:
                phead1.next = Tmp
                Tmp = Tmp.next
                phead1 = phead1.next
                phead1.next = None
            else:
                phead2.next = Tmp
                Tmp = Tmp.next
                phead2 = phead2.next
                phead2.next = None
        phead1.next = head2.next
        head = head1.next
        return head





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
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        ListNode p = head;
        ListNode p1 = new ListNode(0);
        ListNode p2 = new ListNode(0);
        ListNode dummy1 = p1;
        ListNode dummy2 = p2;
        
        while (p != null) {
            if (p.val < x) {
                p1.next = p;
                p1 = p1.next;
                p = p.next;
                p1.next = null;         // careful here
            } else {
                p2.next = p;
                p2 = p2.next;
                p = p.next;
                p2.next = null;         // careful here
            }
        }
        p1.next = dummy2.next;
        return dummy1.next;
    }
}



from code ganker:

这是一道链表操作的题目，要求把小于x的元素按顺序放到链表前面。我们仍然是使用链表最常用的双指针大法，一个指向当前小于x的最后一个元素，

一个进行往前扫描。如果元素大于x，那么继续前进，否则，要把元素移到前面，并更新第一个指针。这里有一个小细节，

就是如果不需要移动（也就是已经是接在小于x的最后元素的后面了），那么只需要继续前进即可。算法时间复杂度是O(n)，空间只需要几个辅助变量，是O(1)。

代码如下：

public ListNode partition(ListNode head, int x) {
    if(head == null)
        return null;
    ListNode helper = new ListNode(0);
    helper.next = head;
    ListNode walker = helper;
    ListNode runner = helper;
    while(runner.next!=null)
    {
        if(runner.next.val<x)
        {
            if(walker!=runner)
            {
                ListNode next = runner.next.next;
                runner.next.next = walker.next;
                walker.next = runner.next;
                runner.next = next;
            }
            else
                runner = runner.next;
            walker = walker.next;
        }
        else
        {
            runner = runner.next;
        }
    }
    return helper.next;
}

这道题思路比较清晰，不过还是有点细节的，第一次写可能不容易完全写对，可以练习练习。


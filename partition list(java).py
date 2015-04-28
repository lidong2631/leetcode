<<<<<<< HEAD
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
        head1 = ListNode(0)     #
        head2 = ListNode(0)
        tmp = head              #
        p1 = head1              #
        p2 = head2
        while tmp:              #
            if tmp.val < x:         #
                p1.next = tmp
                tmp = tmp.next
                p1 = p1.next
                p1.next = None
            else:                   #
                p2.next = tmp
                tmp = tmp.next
                p2 = p2.next
                p2.next = None
        p1.next = head2.next        #
        return head1.next

Note: 这题也不难 要熟记套路



题意：

Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.

解题思路：解决链表问题时，最好加一个头结点，问题会比较好解决。对这道题来说，创建两个头结点head1和head2，head1这条链表是小于x值的节点的链表，

head2链表是大于等于x值的节点的链表，然后将head2链表链接到head链表的尾部即可。

代码：


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
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(0);
        ListNode tmp = head;
        ListNode p1 = head1;
        ListNode p2 = head2;
        while(tmp!=null)
        {
            if(tmp.val<x)
            {
                p1.next = tmp;
                tmp = tmp.next;
                p1 = p1.next;
                p1.next = null;     //注意这里一定要将next置为null 否则最后会形成cycle list
            }
            else
            {
                p2.next = tmp;
                tmp = tmp.next;
                p2 = p2.next;
                p2.next = null;
            }
        }
        p1.next = head2.next;
        return head1.next;
    }
}


Note: 这题我用的python版改编 没有看code ganker的解法 时间空间复杂度都差不多 python版比较好理解 主要就是建立两个list 一个val都小于x

一个都大于等于x 最后连接起来 用python版就够了

注意这题111行到114行不可以写成
p1.next = tmp;
p1 = p1.next;
p1.next = null;
tmp = tmp.next;
也就是先写113行是不行的 因为p1.next = null会将原始链表截断 所以要将让原节点tmp先跳到下一个节点再执行p1 = p1.next和p1.next = null



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


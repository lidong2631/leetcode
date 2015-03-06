# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @param k, an integer
    # @return a ListNode
    def rotateRight(self, head, k):
        if head == None or k == 0:
            return head
        p = ListNode(0); p.next = head 	#新建一头节点
        length = 0
        while p.next:			#计算链表长度
            p = p.next
            length += 1
        p.next = head 		#重置p.next = head
        k = k % length		#因为k可能大于链表长度 所以先取余数
        step = length - k		#rotate to the right 所以length－k既是从左数第几个几点开始
        for i in range(step):		#循环step步 到达要rotate的前一个节点
            p = p.next
        head = p.next		#将head设为p.next p.next指向null
        p.next = None
        return head





题意：

Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.

解题思路：循环右移一条链表，比如k=2，（1，2，3，4，5）循环右移两位变为（4，5，1，2，3）。由于k值有可能比链表长度大很多，所以先要用一个count变量求出链表的长度。而k%count就是循环右移的步数。

代码：

复制代码
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param head, a ListNode
    # @param k, an integer
    # @return a ListNode
    def rotateRight(self, head, k):
        if k == 0:
            return head
        if head == None:
            return head
        dummy = ListNode(0)
        dummy.next = head
        p = dummy
        count = 0
        while p.next:
            p = p.next
            count += 1
        p.next = dummy.next
        step = count - ( k % count )
        for i in range(0, step):
            p = p.next
        head = p.next
        p.next = None
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
    public ListNode rotateRight(ListNode head, int n) {
        if(head==null)
            return null;
        ListNode p = new ListNode(0);
        p.next = head;
        int length = 0;
        while(p.next!=null)
        {
            p = p.next;
            length+=1;
        }
        n = n%length;
        int step = length - n;
        p.next = head;
        for(int i=0; i<step; i++)
            p = p.next;
        head = p.next;
        p.next = null;
        return head;
    }
}

Note: 没什么难度的题 python版和code ganker版都差不多








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
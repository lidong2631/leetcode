题意：

Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.


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
            return head;
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        p.next = head;
        int length = 0;
        while(p.next!=null) {   //扫一遍
            p = p.next;
            length++;
        }
        n = n%length;   //如果n大于length
        p.next = head;  //将尾指针指向head
        for(int i=0; i<length-n; i++) { //移动length－n个位置
            dummy = dummy.next;
        }
        ListNode newHead = dummy.next; //得到新的头指针 并将尾部断开
        dummy.next = null;
        return newHead;
    }
}

connect list cycle first then go to cut off point set new head and cut off


Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.



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
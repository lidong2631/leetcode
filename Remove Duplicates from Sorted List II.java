Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Example 1:

Input: 1->2->3->3->4->4->5
Output: 1->2->5
Example 2:

Input: 1->1->1->2->3
Output: 2->3



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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode l = dummy, r = head;
        while (r != null) {
            while (r.next != null && l.next.val == r.next.val) r = r.next;
            if (l.next == r) l = l.next;
            else l.next = r.next;
            r = r.next;
        }
        return dummy.next;
    }
}




from code ganker:

这道题跟Remove Duplicates from Sorted List比较类似，只是这里要把出现重复的元素全部删除。其实道理还是一样，

只是现在要把前驱指针指向上一个不重复的元素中，如果找到不重复元素，则把前驱指针知道该元素，否则删除此元素。算法只需要一遍扫描，时间复杂度是O(n)，

空间只需要几个辅助指针，是O(1)。代码如下： 

public ListNode deleteDuplicates(ListNode head) {
    if(head == null)
        return head;
    ListNode helper = new ListNode(0);
    helper.next = head;
    ListNode pre = helper;
    ListNode cur = head;
    while(cur!=null)
    {
        while(cur.next!=null && pre.next.val==cur.next.val)
        {
            cur = cur.next;
        }
        if(pre.next==cur)
        {
            pre = pre.next;
        }
        else
        {
            pre.next = cur.next;
        }
        cur = cur.next;
    }
    
    return helper.next;
}

可以看到，上述代码中我们创建了一个辅助的头指针，是为了修改链表头的方便。前面介绍过，一般会改到链表头的题目就会需要一个辅助指针，是比较常见的技巧。

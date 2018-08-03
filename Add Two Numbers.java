题意：

You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8



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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode newNode = new ListNode(0);
        ListNode p = newNode;
        int val = 0;
        int carry = 0;
        while (l1 != null && l2 != null) {
            val = (l1.val + l2.val + carry) % 10;
            carry = (l1.val + l2.val + carry) / 10;
            p.next = new ListNode(val);
            p = p.next; l1 = l1.next; l2 = l2.next;
        }
        while (l1 != null) {
            val = (l1.val + carry) % 10;
            carry = (l1.val + carry) / 10;
            p.next = new ListNode(val);
            p = p.next; l1 = l1.next;
        }
        while (l2 != null) {
            val = (l2.val + carry) % 10;
            carry = (l2.val + carry) / 10;
            p.next = new ListNode(val);
            p = p.next; l2 = l2.next;
        }
        if (carry != 0)
            p.next = new ListNode(carry);
        return newNode.next;
    }
}

Note: 这题简单 只是实现细节要注意不能出错 另外可以改成数组相加 也要会 另外扩展看code ganker



Python:
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def addTwoNumbers(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        dummy = cur = ListNode(0)
        carry = 0
        while l1 or l2 or carry:
            if l1:
                carry += l1.val
                l1 = l1.next
            if l2:
                carry += l2.val
                l2 = l2.next
            cur.next = ListNode(carry%10)
            cur = cur.next
            carry //= 10
        return dummy.next



Golang:
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
    carry := 0
    head := new(ListNode)
    cur := head
    for l1 != nil || l2 != nil || carry != 0 {
        n1, n2 := 0, 0
        if l1 != nil {
            n1, l1 = l1.Val, l1.Next
        }
        if l2 != nil {
            n2, l2 = l2.Val, l2.Next
        }
        num := n1 + n2 + carry
        carry = num / 10
        cur.Next = &ListNode{Val:num%10, Next:nil}
        cur = cur.Next
    }
    return head.Next
}





from code ganker:

这道题比较简单，是cc150里面的题，思路很明确，就是按照位数读下去，维护当前位和进位，时间复杂度是O(n),空间复杂度是O(1).代码如下： 

public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int carry = 0;
    int digit = 0;
    ListNode head = null;
    ListNode pre = null;
    while(l1!=null && l2!=null)
    {
        digit = (l1.val+l2.val+carry)%10;
        carry = (l1.val+l2.val+carry)/10;
        ListNode newNode = new ListNode(digit);
        if(head==null)
            head = newNode;
        else
            pre.next = newNode;
        pre = newNode;
        l1 = l1.next;
        l2 = l2.next;
    }
    while(l1!=null)
    {
        digit = (l1.val+carry)%10;
        carry = (l1.val+carry)/10;
        ListNode newNode = new ListNode(digit);
        if(head==null)
            head = newNode;
        else
            pre.next = newNode;
        pre = newNode;
        l1 = l1.next;            
    }
    while(l2!=null)
    {
        digit = (l2.val+carry)%10;
        carry = (l2.val+carry)/10;
        ListNode newNode = new ListNode(digit);
        if(head==null)
            head = newNode;
        else
            pre.next = newNode;
        pre = newNode;
        l2 = l2.next;            
    }        
    if(carry>0)
    {
        ListNode newNode = new ListNode(carry);
        pre.next = newNode;
    }
    return head;
} 

实现中注意维护进位，陷阱的话记住最后还要判一下有没有进位，如果有再生成一位。 

这道题还是有一些扩展的，比如这个其实是BigInteger的相加，数据结果不一定要用链表，也可以是数组，面试中可能两种都会问而且实现。然后接下来可以考一些OO设计的东西，

比如说如果这是一个类应该怎么实现，也就是把数组或者链表作为成为成员变量，再把这些操作作为成员函数，进一步的问题可能是如何设计constructor，

这个问题除了基本的还得对内置类型比如int, long的constructor, 类似于BigInteger(int num), BigInteger(int long). 总体来说问题还是比较简单，但是这种问题不能出错，

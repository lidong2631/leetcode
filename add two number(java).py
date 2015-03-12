<<<<<<< HEAD
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @return a ListNode
    def addTwoNumbers(self, l1, l2):
        if l1 == None:          #如果了l1或l2为空
            return l2
        if l2 == None:
            return l1
        newNode = ListNode(0)       #新建头节点
        p = newNode; carry = 0
        while l1 and l2:            #l1和l2都还有值
            num = (l1.val + l2.val + carry) % 10        #一定要先计算num在算carry 不然num会用新的carry计算错误结果
            carry = (l1.val + l2.val + carry) / 10      #后计算进位
            p.next = ListNode(num)                  #新建一个节点放到p的后面
            l1 = l1.next; l2 = l2.next; p = p.next  #l1 l2 p都向后移动
        while l1:                           #如果l1还有值
            num = (l1.val + carry) % 10
            carry = (l1.val + carry) / 10
            p.next = ListNode(num)
            l1 = l1.next; p = p.next
        while l2:                       #如果l2还有值
            num = (l2.val + carry) % 10
            carry = (l2.val + carry) / 10
            p.next = ListNode(num)
            l2 = l2.next; p = p.next
        if carry != 0:                  #如果最后算完carry还有值
            p.next = ListNode(carry)
        return newNode.next

Note: 链表的基本操作 熟记 记得一般都要在链表前加一节点 方便操作




题意：

You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8

解题思路：链表的操作。

代码：


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @return a ListNode
    def addTwoNumbers(self, l1, l2):
        if l1 == None: return l2
        if l2 == None: return l1
        flag = 0
        dummy = ListNode(0); p = dummy
        while l1 and l2:
            p.next = ListNode((l1.val+l2.val+flag) % 10)
            flag = (l1.val+l2.val+flag) / 10
            l1 = l1.next; l2 = l2.next; p = p.next
        if l2:
            while l2:
                p.next = ListNode((l2.val+flag) % 10)
                flag = (l2.val+flag) / 10
                l2 = l2.next; p = p.next
        if l1:
            while l1:
                p.next = ListNode((l1.val+flag) % 10)
                flag = (l1.val+flag) / 10
                l1 = l1.next; p = p.next
        if flag == 1: p.next = ListNode(1)
        return dummy.next




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
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
        ListNode newNode = new ListNode(0);
        ListNode p = newNode;
        int val = 0;
        int carry = 0;
        while(l1!=null && l2!=null)
        {
            val = (l1.val+l2.val+carry)%10;
            carry = (l1.val+l2.val+carry)/10;
            p.next = new ListNode(val);
            p = p.next; l1 = l1.next; l2 = l2.next;
        }
        while(l1!=null)
        {
            val = (l1.val+carry)%10;
            carry = (l1.val+carry)/10;
            p.next = new ListNode(val);
            p = p.next; l1 = l1.next;
        }
        while(l2!=null)
        {
            val = (l2.val+carry)%10;
            carry = (l2.val+carry)/10;
            p.next = new ListNode(val);
            p = p.next; l2 = l2.next;
        }
        if(carry!=0)
            p.next = new ListNode(carry);
        return newNode.next;
    }
}

Note: 这题简单 只是实现细节要注意不能出错 另外可以改成数组相加 也要会 另外扩展看code ganker





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

解题思路：由于题目对时间复杂度和空间复杂度要求比较高，所以查看了各种解法，最好的解法就是归并排序，由于链表在归并操作时并不需要像数组的归并操作那样分配一个临时数组空间，所以这样就是常数空间复杂度了，当然这里不考虑递归所产生的系统调用的栈。

　　　　   这里涉及到一个链表常用的操作，即快慢指针的技巧。设置slow和fast指针，开始它们都指向表头，fast每次走两步，slow每次走一步，fast到链表尾部时，slow正好到中间，这样就将链表截为两段。

　　　　   运行时需要将中文注释删掉，leetcode oj平台里面不支持中文字符。


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def merge(self, head1, head2):      #define merge method
        if head1 == None:
            return head2
        if head2 == None:
            return head1
        newNode = ListNode(0)       #create a new linked list node
        p = newNode                 #附值给p参与计算
        while head1 and head2:
            if head1.val < head2.val:
                p.next = head1      #三步
                head1 = head1.next
                p = p.next
            else:
                p.next = head2
                head2 = head2.next
                p = p.next
        if head1:               #收尾
            p.next = head1
        if head2:
            p.next = head2
        return newNode.next
    
    # @param head, a ListNode
    # @return a ListNode
    def sortList(self, head):
        if head == None or head.next == None:   #如果head为空 或head已经被分成唯一一个node(head.next == None) 返回head
            return head
        slow = head; fast = head                #设置快慢指针
        while fast.next and fast.next.next:
            slow = slow.next
            fast = fast.next.next               #快指针到头慢指针只走一半
        head1 = head; head2 = slow.next         #指针1指向头 指针2指向另一半的头
        slow.next = None                        #分割链表为两个链表
        head1 = self.sortList(head1)            #recursive call sortList to divide
        head2 = self.sortList(head2)
        head = self.merge(head1, head2)         #merge and sort
        return head

Note: mergesort(linklist/array), ;, self








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
    public ListNode sortList(ListNode head) {
        return MergeSort(head);   
    }
    
    private ListNode MergeSort(ListNode head) {     //快慢指针技巧
        if(head==null || head.next==null)       //注意这里一定要先写head==null再接head.next==null ！！不然如果head指向{} 先到了head.next==null会报空指针错
            return head;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null && fast.next.next!=null)  //慢指针到队中间 快指针到队尾
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode p1 = head;
        ListNode p2 = slow.next;
        slow.next = null;       //辟半
        p1 = MergeSort(p1);     //分别递归 最后merge
        p2 = MergeSort(p2);
        return Merge(p1,p2);
    }
    
    private ListNode Merge(ListNode h1, ListNode h2) {      //完全套用merge two sorted list那一题
        if(h1==null) return h2;
        if(h2==null) return h1;
        ListNode newHead = new ListNode(0);
        ListNode tmp = newHead;
        while(h1!=null & h2!=null)
        {
            if(h1.val<h2.val)
            {
                tmp.next = h1;
                h1 = h1.next;
                tmp = tmp.next;
            }
            else
            {
                tmp.next = h2;
                h2 = h2.next;
                tmp = tmp.next;
            }
        }
        while(h1!=null)
        {
            tmp.next = h1;
            h1 = h1.next;
            tmp = tmp.next;
        }
        while(h2!=null)
        {
            tmp.next = h2;
            h2 = h2.next;
            tmp = tmp.next;
        }
        return newHead.next;
    }
}

Note：mergesort 熟记！！ 其他基本sorting algorithm也要熟记quicksort, insertion sort, heapsort, bucketsort...


from code ganker:

这道题跟Insertion Sort List类似，要求我们用O(nlogn)算法对链表进行排序，但是并没有要求用哪一种排序算法，我们可以使用归并排序，快速排序，

堆排序等满足要求的方法来实现。对于这道题比较容易想到的是归并排序，因为我们已经做过Merge Two Sorted Lists，这是归并排序的一个subroutine。

剩下我们需要做的就是每次找到中点，然后对于左右进行递归，最后用Merge Two Sorted Lists把他们合并起来。代码如下：



不过用归并排序有个问题就是这里如果把栈空间算上的话还是需要O(logn)的空间的。对于其他排序算法，用兴趣的同学可以实现一下哈。

排序是面试中比较基础的一个主题，所以对于各种常见的排序算法大家还是要熟悉，不了解的朋友可以参见排序算法 - Wiki。特别是算法的原理，很多题目虽然没有直接考察排序的实现，

但是用到了其中的思想，比如非常经典的topK问题，就用到了快速排序的原理，关于这个问题在Median of Two Sorted Arrays中有提到，有兴趣的朋友可以看看



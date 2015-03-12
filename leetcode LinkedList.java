Swap Nodes in Pairs
一对对翻转链表 建一个dummy节点 然后遵循四步 1 tmp记录当前组2号节点 2 当前组1号节点指向下一组1号节点 3 当前组2号节点指向1号节点 4 上一组2号节点指向当前组1号节点

重要代码如下:
while(p.next!=null && p.next.next!=null) {
    ListNode tmp = p.next.next;
    p.next.next = tmp.next;
    tmp.next = p.next;
    p.next = tmp;
    p = p.next.next;    
}
时间O(n) 空间O(1)


Sort List
这题主要复习归并排序的链表实现 先用快慢指针找到链表中值 然后左右递归继续归并排序 最后return Merge(left, right) 时间O(nlogn) 空间O(n)



Rotate List
将一个链表的右边k位移动到左边 先建立dummy节点 扫一遍链表统计长度 然后n跟length取模因为有可能n大于length 最后dummy.next=head将链表连在一起

之后dummy点移动length－n位 将head设为dummy.next dummy.next=null完成新链表 时间O(n) 空间O(1)



Reverse Nodes in k group
先建立dummy节点 从头扫链表 每到k个节点就调用reverse(pre, next)方法反转这个组的所有节点 反转方法类似swap nodes in pairs 都是先保存curr的下一节点

然后curr.next = pre.next pre，curr分别移到下一个节点 时间O(2*n)=O(n) 空间O(1)



Reverse Linked List i
这题没有 只是复习下反转链表的两种方法 迭代和递归



Reverse Linked List ii
给定范围反转而不是整个链表反转 先建立dummy节点 prev走m－1步到m节点前 循环n－m次每次反转一个节点即可
ListNode tmp = prev.next;
prev.next = curr.next;
curr.next = curr.next.next;
prev.next.next = tmp;
时间O(n) 空间O(1)



Reorder List
三步 1分半 用快慢指针 2reverse 还是老套路 3merge 时间O(n) 空間O(1)



Remove Nth Node From End of List
先让第一个指针移动n 判断是否是合法的n 如果是则第二个指针开始移动 当p1.next=null时 将p2.next = p2.next.next即可



Remove Duplicates from Sorted List
将有重复的值只保留一个 扫一遍链表 如果p.val==p.next.val 则p.next = p.next.next



Remove Duplicates from Sorted List ii
将有重复的值全部删掉 设p为dummy节点 tmp为head即p.next = tmp 两节点一起移动如果p.next.val==tmp.next.val tmp一直移动直到跟p.next.val不同

跳出while循环后判断如果p.next==tmp说明p和tmp值不同 一起移动一格 否则是重复值p.next = tmp.next
while(tmp.next!=null && tmp.next.val==p.next.val)
    tmp = tmp.next;
if(p.next==tmp) {
    p = p.next;
    tmp = tmp.next;
}
else
    p.next = tmp.next;



Partition List
根据一个value将链表分成两部分 新建两个头节点p1 p2 遍历一遍原链表 小于value的放p1后面 否则放p2后面最后将p1和p2接在一起



Merge Two Sorted Lists

























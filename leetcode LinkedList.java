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
简单题 新建一个节点 三个while l1!=null&&l2!=null l1!=null l2!=null 每次判断大小接到节点后即可 时间O(m+n) 空间O(1)



Merge k Sorted Lists
分布式应用中重要操作 两个做法 第一用mergesort 实现方式大致等同Sort List 这里是left=0 right=lists.size()-1 每次int mid=(left+right)/2

然后递归mergesort(lists,left,mid) mergesort(lists,mid+1,right) merge部分跟Merge Two Sorted Lists相同 时间O(nklogk) 空间O(n)

另一种解法是heapsort 思路是维护一个heap 先将k sorted lists所有头节点放进去 根据heap的特性heap里的值就排好序了 然后每次将堆顶元素弹出

放到最终结果中并将弹出元素的next元素放入堆中 时间O(nklogk) 空间O(n)



Linked List Cycle
设置快慢指针 若两指针能最终遇到则有cycle 否则如果跳出循环则没有cycle 时间O(n) 空间O(1)



Linked List Cycle ii
找cycle起始点 主要是数学证明 知道原理写程序很简单 就是先快慢指针当他们相遇时 将快指针放回head处然后两指针一起以相同速度走再次相遇即为cycle起始点



Intersection of Two Linked Lists
这题有很多做法 我的做法是先计算两个链表的长度让长的那个先走l2-l1步 然后两指针一起走 相遇时即为Intersection



Insertion Sort List
插入排序的实现 新建头节点 开始扫链表如果不满足递增顺序就将不满足的元素插入到前面排好序序列中对应位置 时间O(n^2) 空间O(1)



Copy List with Random Pointer
两种做法 第一种借助哈希表 先扫一遍原链表 每次新建一个对应的新节点 并将map.put(oldNode, newNode) 然后在扫一次原链表 这次copy random指针

每次copyNode.random = map.get(node.random); 时间O(2*n)=O(n) 空间O(n)

第二种方法不需要线性空间 要扫三次链表 前面我们需要一个哈希表的原因是当我们访问一个结点时可能它的随机指针指向的结点还没有访问过，结点还没有创建，

所以我们需要线性的额外空间。想避免使用额外空间，我们只能通过利用链表原来的数据结构来存储结点 第一次扫描对每个结点进行复制，然后把复制出来的

新节点接在原结点的next 第二次扫描中我们把旧结点的随机指针赋给新节点的随机指针node.next.random = node.random.next 最后一次扫描我们把链表拆成两个

只要把每隔两个结点分别相连对链表进行分割即可 时间O(n) 空间O(1)



Convert Sorted List to Binary Search Tree
先扫一遍得到节点数量 之后递归建立左子树 然后建立root 将ListNode设为下一个节点 递归右子树 return root 时间O(logn) 空间O(logn)+O(n)



Add Two Numbers
每次新建一个节点 将l1.val l2.val carry加起来存在节点里 然后如果l1 l2还有剩余值将他们都加起来 最后如果carry还有一位要再新建节点







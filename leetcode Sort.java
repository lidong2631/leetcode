Sort List
Mergesort链表实现
public ListNode sortList(ListNode head) {
        return MergeSort(head);
    }
    
private ListNode MergeSort(ListNode head) {
    if(head==null || head.next==null)   //如果只有一个元素或是null值 直接返回
        return head;
    ListNode slow = head; ListNode fast = head; //快慢指针
    while(fast.next!=null && fast.next.next!=null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    ListNode newHead = slow.next;
    slow.next = null;           //劈半
    ListNode p1 = MergeSort(head);
    ListNode p2 = MergeSort(newHead);
    return Merge(p1, p2);
}

private ListNode Merge(ListNode p1, ListNode p2) {
    if(p1==null)
        return p2;
    if(p2==null)
        return p1;
    ListNode dummy = new ListNode(0);
    ListNode p = dummy;
    while(p1!=null && p2!=null) {
        if(p1.val<p2.val) {
            p.next = p1;
            p = p.next;
            p1 = p1.next;
        }
        else {
            p.next = p2;
            p = p.next;
            p2 = p2.next;
        }
    }
    while(p1!=null) {
        p.next = p1;
        p1 = p1.next;
        p = p.next;
    }
    while(p2!=null) {
        p.next = p2;
        p = p.next;
        p2 = p2.next;
    }
    return dummy.next;
}

O(nlogn) O(logn)





Sort Colors
Counting sort
int[] helper = new int[3];
int[] res = new int[A.length];
for(int i=0; i<A.length; i++)
	helper[A[i]]++;
for(int i=1; i<helper.length; i++)
	helper[i]+=helper[i-1];
for(int i=A.length-1; i>=0; i--) {
	res[helper[A[i]]-1] = A[i]；
	helper[A[i]]--;
}
for(int i=0; i<A.length; i++)
	A[i] = res[i];

O(n) O(n)



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
1 计数排序
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

2 0,1指针
for(int i=0; i<A.length; i++) {
	if(A[i]==0) {
		A[i] = 2;
		A[index1++] = 1；
		A[index0++] = 0;
	}
	else if(A[i]==1) {
		A[i] = 2;
		A[index1++] = 1;
	}
}

O(n) O(1)





Merge Intervals

Comparator<Interval> cmp = new Comparator<Interval>() {
    public int compare(Interval i1, Interval i2) {
        if(i1.start==i2.start)
            return i1.end-i2.end;
        return i1.start-i2.start;
    }
};

Collections.sort(intervals, cmp);
for(int i=1; i<intervals.size(); i++) {
    if(intervals.get(i).start<=intervals.get(i-1).end) {
        intervals.get(i-1).end = Math.max(intervals.get(i-1).end, intervals.get(i).end);
        intervals.remove(i);
        i--;
    }
}
return intervals;

O(nlogn)排序 O(1)





Maximum Gap
桶排序 现根据抽屉原则知道最大差值最小值不小于(B-A)/(N-1) 构造桶大小为(B-A)/(N-1) 对数组中所有数依次放入对应的桶 并维护每个桶最大小值
因为最大差值最小值不会小于桶大小 所以一定是某个桶最大值和它下一个非空桶最小值得差值

if(num==null || num.length==0)
	return 0;
int max = num[0], min = num[0];
for(int i=1; i<num.length; i++) {
	max = Math.max(max, num[i]);
	min = Math.min(min, num[i]);
}

int bucket = Math.ceiling((max-min)/(num.length-1));
int[] bucketMin = new int[num.length];
int[] bucketMax = new int[num.length];
Arrays.fill(bucketMax, Integer.MIN_VALUE);
Arrays.fill(bucketMin, Integer.MAX_VALUE);

for(int i=0; i<num.length; i++) {
	int index = (num[i]-min)/bucket;
	bucketMin[index] = Math.min(num[i], bucketMin[index]);
	bucketMax[index] = Math.max(num[i], bucketMax[index]);
}
int maxGap = Integer.MIN_VALUE;
int prev = min;
for(int i=0; i<num.length; i++) {
	if(bucketMin[i]==Integer.MAX_VALUE || bucketMax[i]==Integer.MIN_VALUE)
		continue;
	maxGap = Math.max(maxGap, bucketMin[i]-prev);
	prev = bucketMax[i];
}
return maxGap;





Largest Number
有点怪的排序 通过比较两个数转成字符串值大小来排序 
if(num==null || num.length==0)
    return null;
List<Integer> res = new ArrayList<Integer>();
for(int i=0; i<num.length; i++)
    res.add(num[i]);
Collections.sort(res, new Comparator<Integer>() {
    public int compare(Integer i1, Integer i2) {
        String s1 = i1 + i2;
        String s2 = i2 + i1;
        return s2.compareTo(s1);
    }
});
StringBuilder sb = new StringBuilder();
for(int i=0; i<res.size(); i++)
    sb.append(res.get(i));
if(sb.charAt(0)=='0')
    return "0";
return sb.toString();

O(logn) O(n)





Insertion Sort
插入排序链表版
if(head==null)
    return null;
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode curr = head;
while(curr.next!=null) {
    if(curr.val>curr.next.val) {
        ListNode prev = dummy;
        while(prev.next.val<curr.next.val)
            prev = prev.next;
        ListNode tmp = curr.next;
        curr.next = curr.next.next;
        tmp.next = prev.next;
        prev.next = tmp;
    }
    else
        curr = curr.next;
}
return dummy.next;

O(n^2) O(1)





Insert Interval
先找到newInterval插入的位置 合并start并插入 然后往后一个个扫 只要在newInterval end范围内就并入newInterval并删除原始的interval
if(intervals==null || intervals.size()==0) {
    List<Interval> res = new LinkedList<Interval>();
    res.add(newInterval);
    return res;
}
int i=0;
while(i<intervals.size() && newInterval.start>intervals.get(i).end)
    i++;
if(i<intervals.size())
    newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
intervals.add(i, newInterval);
i++;
while(i<intervals.size() && newInterval.end>=intervals.get(i).start) {
    newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
    intervals.remove(i);
}
return intervals;  

O(n) O(1)





















Merge k Sorted Lists
1 merge sort
if(lists==null || lists.length==0)
    return null;
return helper(lists, 0, lists.length-1);

private ListNode helper(ListNode[] lists, int left, int right) {
	if(left<right) {
        int mid = (left+right)/2;
        return merge(helper(lists, left, mid), helper(lists, mid+1, right));
    }
    return lists[left];
}

private ListNode merge(ListNode l1, ListNode l2) {
    if(l1==null)
        return l2;
    if(l2==null)
        return l1;
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;
    while(l1!=null && l2!=null) {
        if(l1.val<l2.val) {
            curr.next = l1;
            l1 = l1.next;
            curr = curr.next;
        }
        else {
            curr.next = l2;
            l2 = l2.next;
            curr = curr.next;
        }
    }
    while(l1!=null) {
        curr.next = l1;
        l1 = l1.next;
        curr = curr.next;
    }
    while(l2!=null) {
        curr.next = l2;
        l2 = l2.next;
        curr = curr.next;
    }
    return dummy.next;
}

O(nklogk) O(logk)

2 heapsort
if(lists==null || lists.length==0)
    return null;
PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(10, new Comparator<ListNode>() {
    public int compare(ListNode l1, ListNode l2) {
        return l1.val-l2.val;
    }   
});
for(int i=0; i<lists.length; i++) {
    if(lists[i]!=null)
        heap.offer(lists[i]);
}
ListNode curr = heap.poll();
ListNode pre = curr;
ListNode head = curr;
while(heap.size()>0) {
    if(curr.next!=null)
        heap.offer(curr.next);
    curr = heap.poll();
    pre.next = curr;
    pre = curr;
}
return head;

O(nklogk) O(k)





Median of Two Sorted Arrays
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if((nums1.length+nums2.length)%2==1)
        return helper(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, (nums1.length+nums2.length)/2+1);
    else
        return (helper(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, (nums1.length+nums2.length)/2) +
                helper(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, (nums1.length+nums2.length)/2+1))/2.0;
}

private double helper(int[] nums1, int l1, int r1, int[] nums2, int l2, int r2, int k) {
    int m = r1-l1+1;
    int n = r2-l2+1;
    if(m>n)
        return helper(nums2, l2, r2, nums1, l1, r1, k);
    if(m==0)
        return nums2[l2+k-1];
    if(k==1)
        return Math.min(nums1[l1], nums2[l2]);
    int pos1 = Math.min(k/2, m);
    int pos2 = k-pos1;
    if(nums1[l1+pos1-1]==nums2[l2+pos2-1])
        return nums1[l1+pos1-1];
    else if(nums1[l1+pos1-1]<nums2[l2+pos2-1])
        return helper(nums1, l1+pos1, r1, nums2, l2, l2+pos2-1, k-pos1);
    else
        return helper(nums1, l1, l1+pos1-1, nums2, l2+pos2, r2, k-pos2);
}

O(log(m+n)) O(log(m+n))





Maximum Subarray
1 divide and conquer
public int maxSubArray(int[] nums) {
    return helper(nums, 0, nums.length-1);
}

private int helper(int[] nums, int left, int right) {
    if(left==right)
        return nums[left];
    int mid = (left+right)/2;
    return Math.max(helper(nums, left, mid), Math.max(helper(nums, mid+1, right), helper1(nums, left, mid, right)));
}

private int helper1(int[] nums, int left, int mid, int right) {
    int sum = 0;
    int left_sum = Integer.MIN_VALUE;
    for(int i=mid; i>=left; i--) {
        sum+=nums[i];
        if(sum>left_sum)
            left_sum = sum;
    }
    sum = 0;
    int right_sum = Integer.MIN_VALUE;
    for(int i=mid+1; i<=right; i++) {
        sum+=nums[i];
        if(sum>right_sum)
            right_sum = sum;
    }
    return left_sum+right_sum;
}

O(nlogn)

2 动态规划 局部解全局解
if(A==null || A.length==0)
    return 0;
int local = A[0];
int global = A[0];
for(int i=1; i<A.length; i++)
{
    local = Math.max(A[i], local+A[i]);
    global = Math.max(local, global);
}
return global;
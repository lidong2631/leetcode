Array

Two Sum (注意:题目假设只有一个解)
两种方法 第一种用HashMap 
int[] res = new int[2];
Map<Integer, Integer> map = new HashMap<Integer, Integer>();
for(int i=0; i<numbers.length; i++) {
    if(map.containsKey(target-numbers[i])) {
        res[0] = map.get(target-numbers[i]) + 1;
        res[1] = i+1;
        return res;
    }
    map.put(numbers[i], i);
}
return null;

时间O(n) 空间O(n)


第二种先排序然后两边夹逼 这种比较好应付不只有一个解的情况 不过这题要求输出数字的index而不是数字本身 所以要多开一个HashMap纪录数字原始的index
时间O(nlogn＋n)=O(nlogn) 空间O(n)





Two Sum ii - input array is sorted
这题假设数组已排好序 可以直接用两边夹逼的方法做出 
int left = 0, right = numbers.length-1;
int[] res = new int[2];
while(left<right) {
    if(numbers[left]+numbers[right]==target) {
        res[0] = left+1;
        res[1] = right+1;
        return res;
    }
    else if(numbers[left]+numbers[right]>target)
        right--;
    else
        left++;
}
return null;

时间O(n) 空间O(1)





Two Sum iii - Data Structure Design
1 add O(n)  find O(1)   Space: O(n^2)
Store all possible sum pairs in a hashtable. We also need a list of all added numbers. Each add go through the list and form
new sum pairs that put into the hashtable. For find, it is just a look up in the hashtable. This is useful if the find operation
is far exceed the add operation

public class TwoSum {

    private Set<Integer> set = new HashSet<Integer>();
    private List<Integer> list = new ArrayList<Integer>();
    
    public void add(int number) { 
        for(Integer num : list) {
            if(set.contains(num+number))
                return;
            else
                set.add(num+number);
        }
        list.add(number);
        if(list.size()==1)
            set.add(number);
    }

    public boolean find(int value) {
        if(set.contains(value) && list.size()!=1)
            return true;
        else
            return false;
    }
}





2 add O(n)  find O(n)   Space: O(n)
Maintain a sorted array. For add, use method in Search Insert Position to find the place to insert in O(logn). Also we need to
move all the elements after the insert element in the array so that would be O(n). For find, use two pointer method in Two Sum
and it will cost O(n)

3 add O(1)  find O(nlogn)   Space: O(n)
No need to Maintain the sorted array. For add, just insert the element in the array. For find, we first sort the array in O(nlogn)
then use two sum method to find the element in O(n)

4 add O(1)  find O(n)   Space O(n)
Use a hashtable to save the number. Key is the added number and value is the count number of this number. For add, we update the
hashtable. For find, we look up the hashtable. Be careful for the duplicate number

public class TowSum {

    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    public void add(int number) {
        if(map.containsKey(number))
            map.put(number, map.get(number)+1);
        else
            map.put(number, 1);
    }

    public int find(int number) {
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num1 = entry.getKey();
            int num2 = number - num1;
            if(num1==num2) {        //如果重复元素 要判断是否出现2次以上
                if(map.get(num2)>=2)
                    return true;
            } else if(map.containsKey(num2))    //否则如果包含num2 返回true
                return true;
        }
        return false;
    }
}





3Sum (数组中可能有重复值)
List<List<Integer>> res = new ArrayList<List<Integer>>();
if(num==null || num.length==0)
    return res;
Arrays.sort(num);
for(int i=num.length-1; i>=2; i--) {
    if(i<num.length-1 && num[i]==num[i+1])
        continue;
    List<List<Integer>> tmp = twoSum(num, i-1, -num[i]);
    for(int j=0; j<tmp.size(); j++)
        tmp.get(j).add(num[i]);
    res.addAll(tmp);
}
return res;

private List<List<Integer>> twoSum(int[] num, int end, int target) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if(num==null || num.length<=1)
        return res;
    int left = 0, right = end;
    while(left<right) {
        int sum = num[left]+num[right];
        if(sum==target) {
            List<Integer> tmp = new ArrayList<Integer>();
            tmp.add(num[left]);
            tmp.add(num[right]);
            res.add(tmp);
            left++;
            right--;
            while(left<right && num[left]==num[left-1])
                left++;
            while(left<right && num[right]==num[right+1])
                right--;
        }
        else if(sum<target)
            left++;
        else
            right--;
    }
    return res;
}

时间O(n^2)    空间O(n)





3Sum Closest (find three integers that sum is closest to a given number. Return the sum. Only one solution)

int global_minDiff = num[0]+num[1]+num[2]-target
Arrays.sort(num);
for(int i=0; i<num.length-2; i++) {
    int localMinDiff = twoSumDiff(num, i+1, target-num[i]);
    global_minDiff = Math.min(Math.abs(global_minDiff), Math.abs(localMinDiff));
}
return global_minDiff+target;

private int twoSum(int[] num, int start, int target) {
    int minDiff = num[start]+num[start+1]-target;
    int left = start, int right = num.length-1;
    while(left<right) {
        if(num[left]+num[right]==target)
            return 0;
        int localDiff = num[left]+num[right]-target;
        if(Math.abs(minDiff)<Math.abs(localDiff))
            minDiff = localDiff;
        if(num[left]+num[right]>target)
            right--;
        else
            left++;
    }
    return minDiff;
}

O(n^2) O(1)





4Sum
仍是先对数组排序 从后往前扫一遍数组 对于非重复元素 调用3Sum subroutine后面跟3Sum一样 时间O(n^3)

此题还有一种解法可以达到O(n^2logn) 有时间看





Best Time to Buy and Sell Stock (一次交易的最大利润)
动态规划 局部解全局解
int local = 0, global = 0;
for(int i=1; i<prices.length; i++) {
    local = Math.max(local+prices[i]-prices[i-1], 0);
    global = Math.max(global, local);
}
return global;

时间O(n) 空间O(1)





Best Time to Buy and Sell Stock ii (无限次交易的最大利润)
这题更简单 不限定交易次数那么只要差价大于0就可以一直累加 
int profit = 0;
for(int i=1; i<prices.length; i++) {
    int diff = prices[i]-prices[i-1];
    if(diff>0)
        profit+=diff;
}
return profit;

时间O(n) 空间O(1)





Best Time to Buy and Sell Stock iii (限定2次交易 扩展到k次)
local[i][j]=max(local[i-1][j]+diff, global[i-1][j-1]+max(diff,0)) 
global[i][j]=max(local[i][j],global[i-1][j])

int global[] = new int[3];
int local[] = new int[3];
for(int i=1; i<prices.length; i++) {
    int diff = prices[i]-prices[i-1];
    for(int j=2; j>=1; j--) {
        local[j] = Math.max(local[j]+diff, global[j-1]+Math.max(diff,0));
        global[j] = Math.max(local[j], global[j]);
    }
}
return global[2];

O(n) O(1)





Best Time to Buy and Sell Stock iv
ii iii结合

if(k>prices.length)
    return maxProfit(prices)
int local = new int[k+1];
int global = new int[k+1];
for(int i=1; i<prices.length; i++) {
    int diff = prices[i]-prices[i-1];
    for(int j=k; j>=1; j--) {
        local[j] = Math.max(local[j]+diff, global[j-1]+Math.max(diff, 0));
        global[j] = Math.max(global[j], local[j]);
    }
}
return global[k];

private int maxProfit(prices) {
    int profit = 0;
    for(int i=1; i<prices.length; i++) {
        int diff = prices[i]-prices[i-1];
        if(diff>0)
            profit+=diff;
    }
    return profit;
}

O(n), O(k)





Combination Sum (找和为指定target的集合 每个元素可以用无限次)
NP问题
List<List<Integer>> res = new ArrayList<List<Integer>>();
if(candidates==null || candidates.length==0)
    return res;
Arrays.sort(candidates);
helper(candidates, 0, target, new ArrayList<Integer>(), res);
return res;

private void helper(int[] candidates, int start, int target, List<Integer> item, List<List<Integer>> res) {
    if(target==0) {
        res.add(new ArrayList<Integer>(item));
        return;
    }
    if(target<0)
        return;
    for(int i=start; i<candidates.length; i++) {
        if(i>0 && candidates[i]==candidates[i-1])
            continue;
        item.add(candidates[i]);
        helper(candidates, i, target-candidates[i], item, res);
        item.remove(item.size()-1);
    }
}





Combination Sum ii (每个元素只能用一次)
List<list<Integer>> res = new ArrayList<List<Integer>>();
if(num==null || num.length==0)
    return res;
Arrays.sort(num);
helper(num, 0, target, new ArrayList<Integer>(), res);
return res;

private void helper(int[] num, int start, int target, List<Integer> item, List<List<Integer>> res) {
    if(target==0) {
        res.add(new ArrayList<Integer>(item));
        return;
    }
    if(target<0 || start>=num.length)
        return;
    for(int i=start; i<num.length; i++) {
        if(i>start && num[i]==num[i-1])
            continue;
        item.add(num[i]);
        helper(num, i+1, target-num[i], item, res);
        item.remove(item.size()-1);
    }
}





Combination Sum iii
给定取数个数k 和target n 找combination sum 取值范围只取1到9
List<List<Integer>> res = new ArrayList<List<Integer>>();
if(n<=0)
    return res;
int[] num = {1,2,3,4,5,6,7,8,9};
helper(num, k, 0, n, new ArrayList<Integer>(), res);
return res;

private void helper(int[] num, int k, int start, int target, List<Integer> item, List<List<Integer>> res) {
    if(target==0 && item.size()==k) {
        res.add(new ArrayList<Integer>(item));
        return;
    }
    if(target<0 || item.size()>k)
        return;
    for(int i=start; i<num.length; i++) {
        item.add(num[i]);
        helper(num, k, i+1, target-num[i], item, res);
        item.remove(item.size()-1);
    }
}





Subsets
递归
if(S==null)
    return null;
Arrays.sort(S);
return helper(S, S.length-1);

private helper(int[] S, int index) {
    if(index==-1) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> tmp = new ArrayList<Integer>();
        res.add(tmp);
        return res;
    }
    List<List<Integer> res = helper(S, index-1);
    int size = res.size();
    for(int i=0; i<size; i++) {
        List<Integer> item = new ArrayList<Integer>(res.get(i));
        item.add(num[index]);
        res.add(item);
    }
    return res;
}

非递归
List<list<Integer>> res = new ArrayList<List<Integer>>(new ArrayList<Integer>());
res.add(new ArrayList<Integer>());
Arrays.sort(num);
if(num==null)
    return res;
for(int i=0; i<num.length; i++) {
    int size = res.size();
    for(int j=0; j<size; j++) {
        List<Integer> item = new ArrayList<Integer>(res.get(j));
        item.add(num[i]);
        res.add(item);
    }    
}
return res;

时间O(2^n) 空集O(2^n)





Subsets ii
递归
if(num==null || num.length==0)
    return null;
Arrays.sort(num);
List<Integer> lastSize = new ArrayList<Integer>();
lastSize.add(0)
return helper(num, num.length-1, lastSize);

private helper(int[] num, int index, List<Integer> lastSize) {
    if(index==-1) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> tmp = new ArrayList<Integer>();
        res.add(tmp);
        return res;
    }
    List<List<Integer> res = helper(num, index-1);
    int size = res.size();
    int start = (index>0 && num[index]==num[index-1])? lastSize.get(0):0;
    for(int i=start; i<size; i++) {
        List<Integer> item = new ArrayLisT<Integer>(res.get(i));
        item.add(num[index]);
        res.add(item);
    }
    lastSize.set(0, size);
    return res;
}

非递归
Arrays.sort(num);
List<list<Integer>> res = new ArrayList<List<Integer>>(new ArrayList<Integer>());
int start = 0;
for(int i=0; i<num.length; i++) {
    int size - res.size();
    for(int j=start; j<size; j++) {
        List<Integer> item = new ArrayList<Integer>(res.get(j));
        item.add(num[i]);
        res.add(item);
    }
    if(i<num.length-1 && num[i]==num[i+1])
        start = size;
    else start = 0;    
}
return res;

时间O(2^n) 空集O(2^n)




Construct Binary Tree from Preorder and Inorder Traversal
树题 先用map保存中序值和索引的对应关系 然后递归 利用preorder第一个元素是root
Map<Integer, Integer> map = new HashMap<Integer, Integer>();
for(int i=0; i<inorder.length; i++)
    map.put(inorder[i], i);
return helper(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, map);

private TreeNode helper(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR, Map<Integer, Integer> map) {
    if(inL>inR || preL>preR)
        return null;
    TreeNode root = new TreeNode(preorder[preL]);
    int index = map.get(preorder[preL]);
    root.left = helper(preorder, preL+1, preL+index-inL, inorder, inL, index-1, map);
    root.right = helper(preorder, index-inL+preL+1, preR, inorder, index+1, inR, map);
    return root;
}

O(n) O(n)





Construct Binary Tree from Inorder and Postorder Traversal
同上思路
Map<Integer, Integer> map = new HashMap<Integer, Integer>();
for(int i=0; i<inorder.length; i++) {
    map.put(inorder[i], i);
}
return helper(postorder, 0, postorder.length01, inorder, 0, inorder.length-1, map);

private TreeNode helper(int[] postorder, int postL, int postR, int[] inorder, int inL, int inR, map) {
    if(inL>inR || postL>postR)
        return null;
    TreeNode root = new TreeNode(postorder[postR]);
    int index = map.get(postorder[postR]);
    root.left = helper(postorder, postL, postL+index-inL-1, inorder, inL, index-1, map);
    root.right = helper(postorder, postR-(inL-index), postR-1, inorder, index+1, inR, map);
    return root;
}

O(n) O(n)





Contains Duplicate
用hashset
if(nums==null || nums.length==0)
    return false;
Set<Integer> set = new HashSet<Integer>();
for(int i=0; i<nums.length; i++) {
    if(set.contains(nums[i]))
        return true;
    set.add(nums[i]);
}
return false;

O(n) O(n)





Contains Duplicate ii
找序列中是否有相差不大于k的相等元素 维护一个区间k 用hashset扫一遍
Set<Integer> set = new HashSet<Integer>();
int i=0, j=0;
while(j<nums.length && j-i<=k) {
    if(set.contains(nums[j]))
        return true;
    else
        set.add(nums[j]);
    j++;
}
while(j<nums.length) {
    set.remove(nums[i++]);
    if(set.contains(nums[j]))
        return true;
    else
        set.add(nums[j]);
    j++;
}
return false;

O(n) O(n)





Contains Duplicate iii
找两个元素 值相差最大为t 距离最大为k
if(nums==null || nums.length==0)
    return false;
TreeSet<Integer> tree = new TreeSet<Integer>();
for(int i=0; i<nums.length; i++) {
    Integer rightSub = tree.floor(nums[i]+t);
    Integer leftSub = tree.ceiling(nums[i]-t);
    if((rightSub!=null && rightSub>=nums[i]) || (leftSub!=null && leftSub<=nums[i]))
        return true;
    tree.add(nums[i]);
    if(i-k>0)
        tree.remove(nums[i-k]);
}
return false;

O(nlogk) O(k)





Container With Most Water
求两条垂直x轴的线和x轴围成的container里能乘的最大水量 夹逼＋贪心
if(height==null || height.length==0)
    return 0;
int left = 0, right = height.length-1, maxWater = 0;
while(left<right) {
    int diff = height[left]-height[right];
    if(diff<0) {
        int localWater = (right-left)*height[left];
        maxWater = Math.max(maxWater, localWater);
        left++;
    }
    else {
        int localWater = (right-left)*height[right];
        maxWater = Math.max(maxWater, localWater);
        right--;
    }
} 
return maxWater;

时间O(n) 空间O(1)





Trapping Rain Water
上一题的进阶版 不是和x轴围 而是和中间其他高度围

两种解法 第一种扫两遍 先左扫一遍 再右扫一遍
for(int i=1; i<A.length-1; i++) {
    leftMax = Math.max(leftMax, A[i-1]);
    maxL[i] = leftMax;
}
int rightMax = 0;
for(int i=A.length-2; i>0; i--) {
    rightMax = Math.max(rightMax, A[i+1]);
    water+=Math.min(maxL[i], rightMax)>A[i]?Math.min(maxL[i], rightMax)-A[i]:0;
}

时间O(2*n)=O(n) 空间O(n)

第二种解法只需要扫一次数组 比较左右指针 取小的那边开始走 如果下一个元素更小就把差值加到结果中 否则重新比较左右指针大小 
if(A==null || A.length<2)
    return 0;
int left = 0, right = A.length-1, water = 0;
while(left<right) {
    int minHeight = Math.min(A[left], A[right]);
    if(A[left]==minHeight) {
        left++;
        while(left<right && A[left]<=minHeight) {
            water+=minHeight-A[left];
            left++;
        }
    }
    else {
        right--;
        while(left<right && A[right]<=minHeight) {
            water+=minHeight-A[right];
            right--;
        }
    }
}
return water;

时间O(n) 空间O(1)





Find Minimum in Rotated Array i (i假定没有重复元素 二有)
int left = 0, right = num.length-1;
while(left<right &&　num[left]>num[right]) {
    int mid = (left+right)/2;
    if(num[mid]>num[right])
        left = mid + 1;
    else
        right = mid;
}
return num[left];

时间O(logn) 空间O(1)





Find Minimum in Rotated Array ii
int left = 0, right = num.length-1;
while(left<right && num[left]>=num[right]) {
    int mid = (left+right)/2;
    if(num[mid]>num[right])
        left = mid + 1;
    else if(num[mid]<num[right])
        right = mid;
    else
        right--;
}
return num[left];

时间O(logn) 空间O(1)





Search in Rotated Sorted Array
while(left<=right) {
    if(target==A[mid])
        return mid;
    if(A[mid]>A[right]) {
        if(A[left]<=target && target<A[mid])
            right = mid - 1;
        else
            left = mid + 1;
    }
    else {
        if(A[mid]<target && target<=A[right])
            left = mid + 1;
        else
            right = mid - 1;
    }
}
时间O(logn) 空间O(1)





Search in Rotated Sorted Array ii
while(left<=right) {
    if(target==A[mid])
        return mid;
    if(A[mid]>A[right]) {
        if(A[left]<=target && target<A[mid])
            right = mid - 1;
        else
            left = mid + 1;
    }
    else if(A[mid]<A[right]) {
        if(A[mid]<target && target<=A[right])
            left = mid + 1;
        else
            right = mid - 1;
    }
    else
        right--;
}
时间最坏是O(n) 空间O(1)





Search Insert Position
两种解法都可以
while(l<r) {
    int mid = (l+r)/2;
    if(A[mid]<target)
        l = mid + 1;
    else
        r = mid;
}
return A[l]<target? l+1:l;
时间O(logn) 空间O(1)

while(l<r) {
    int mid = (l+r)/2;
    if(A[mid]==target)
        return mid;
    else if(A[mid]<target)
        l = mid + 1;
    else
        r = mid - 1;
}
return l;
时间O(logn) 空间O(1)





Find Peak Element
还是二分法思路 
while(left<right) {
    int mid = (left+right)/2;
    if(num[mid]<num[mid+1])
        left = mid + 1;
    else
        right = mid;
}
return left;
时间O(logn) 空间O(1)





First Missing Positive integer
利用数组的index来作为数字本身的索引，把正数按照递增顺序依次放到数组中
for(int i=0; i<A.length; i++) {
    if(A[i]>0 && A[i]<A.length+1 && A[A[i]-1]!=A[i]) {
        int tmp = A[A[i]-1];
        A[A[i]-1] = A[i];
        A[i] = tmp;
        i--;
    }
}
for(int i=0; i<A.length; i++) {
    if A[i]!=i+1;
        return i+1;
}
return A.length+1;

时间O(n) 空间O(1)





Merge Intervals
Comparator<Interval> cmp = new Comparator<Interval>(){
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
时间O(n) 空间O(1)





Insert Intervals
这题是上一题的进阶版 
if(intervals==null || intervals.size()==0) {
    List<Interval> res = new LinkedList<Interval>();
    res.add(newInterval);
    return res;
}
int i=0;
while(i<intervals.size() && intervals.get(i).end<newInterval.start)
    i++;
if(i<intervals.size());
    newInterval.start = Math.min(newInterval, intervals.get(i).start);
intervals.add(i, newInterval);
i++;
while(i<intervals.size() &&　newInterval.end>=intervals.get(i).start) {
    newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
    intervals.remove(i);
}
return intervals;

时间O(n) 空间O(1)





Jump Game
一维dp
int reach = 0;
for(int i=0; i<A.length&&i<=reach; i++)
    reach = Math.max(reach, i+A[i]);
if(reach<A.length-1)
    return false;
return true;

时间O(n) 空间O(1)





Jump Game ii
上一题扩展 一维dp + 贪心
int step = 0, reach = 0, lastReach = 0;
for(int i=0; i<A.length&&i<=reach; i++) {
    if(i>lastReach)
        step++;
        lastReach = reach;
    reach = Math.max(reach, i+A[i]);
}
if(reach<A.length-1)
    return -1;
return step;

时间O(n) 空间O(1)





Largest Rectangle in Histogram
第一种做法对于每一个高度 分别往左右走找到它的最大面积 最后取所有最大面积中最大的 时间O(n^2) 空间O(1)

第二种做法 利用栈维护一个递增序列
if(height==null || height.length==0)
    return 0;
LinkedList<Integer> stack = new LinkedList<Integer>();
int i=0, maxArea = 0;
for(; i<height.length; i++)
    if(stack.isEmpty() || height[i]>height[stack.peek()])
        stack.push(i);
    else {
        int currIndex = stack.pop();
        int width = stack.isEmpty()? i:i-stack.peek()-1;
        maxArea = Math.max(maxArea, width*height[i]);
        i--;
    }
while(!stack.isEmpty()) {
    int currIndex = stack.pop();
    int width = stack.isEmpty()? i:i-stack.peek()-1;
    maxArea = Math.max(maxArea, width*height[i]);
}
return maxArea;

时间O(n) 空间O(n)





Maximal Rectangle
这题是上一题的扩展 
if(matrix==null || matrix.length==0 || matrix[0].length==0)
    return 0;
int maxArea = 0;
int height = new int[matrix[0].length];
for(int i=0; i<matrix.length; i++) {
    for(int j=0; j<matrix[0].length; j++) {
        height[j] = matrix[i][j]=='0'? 0:height[j]+1;
    }
    maxArea = Math.max(maxArea, largestRect(height));
}
return maxArea;

private int largestRect(int[] height) {
    if(height==null || height.length==0)
        return 0;
    Stack<Integer> stack = new Stack<Integer>();
    int maxArea = 0, i = 0;
    for(; i<height.length; i++) {
        if(stack.empty() || height[i]>stack.peek())
            i++;
        else {
            int index = stack.pop();
            int width = stack.empty()?i:i-stack.peek()-1;
            maxArea = Math.max(maxArea, width*height[index]);
        }
    }
    return maxArea;
}

时间上Largest Rectangle in Histogram的复杂度为O(n) 而计算一行高度的复杂度也为O(n) 所以每一行的复杂度是O(n+n)=O(n) 

总时间复杂度O(m*n) 空间上需要保存一行直方图的高度O(n)加上Largest Rectangle in Histogram所使用的空间O(n) 总空间复杂度还是O(n)





Longest Consecutive Sequence
1 先排序在统计最长连续子序列 时间O(nlogn)

2 HashSet<Integer> set 记录所有数
if(num==null || num.length==0)
    return 0;
Set<Integer> set = new HashSet<Integer>();
int maxLen = 0;
for(int i=0; i<num.length; i++)
    set.add(num[i]);
while(!set.isEmpty()) {
    Iterator i = set.iterator();
    int n = (int)i.next();
    set.remove(n);
    int left = n-1; lenL = 0;
    while(set.contains(left)) {
        lenL++;
        set.remove(left);
        left--;
    }
    int right = n+1; lenR = 0;
    while(set.contains(right)) {
        lenR++;
        set.remove(right);
        right++;
    }
    maxLen = Math.max(maxLen, lenL+1+lenR);
}
return maxLen;

时间O(n) 空间O(n)





Majority Element
Moore voting algorithm

int counter = 0, curr = 0;
for(int i=0; i<num.length; i++) {
    if(counter==0) {
        curr = num[i];
        counter++;
    }
    else if(curr==num[i])
        counter++;
    else counter--;
}
int counter = 0;
for(int i=0; i<num.length; i++) {
    if(num[i]==curr)
        counter++;
}
if(counter<=num.length/2)
    return -1;
return curr;

时间O(n) 空间O(1)





Maximum Subarray
动态规划问题 递推式为 local[i+1] = Math.max(A[i+1], local[i]+A[i+1]), global[i+1] = Math.max(local[i+1], global[i])
int local = A[0], global = A[0];
for(int i=1; i<A.length; i++) {
    local = Math.max(local+A[i], A[i]);
    global = Math.max(global, local);
}
return global;

时间O(n) 空间O(1)





Maximum Product Subarray
上一题的扩展 还需要维护一个最小 因为乘法性质可能负负得正
int maxLocal = A[0], minLocal = A[0], maxCopy = A[0];
for(int i=1; i<A.length; i++) {
    int maxCopy = maxLocal;
    maxLocal = Math.max(maxLocal*A[i], Math.max(A[i], minLocal*A[i]));
    minLocal = Math.min(minLocal*A[i], Math.min(A[i], maxCopy*A[i]));
    global = Math.max(global, maxLocal);
}

时间O(n) 空间O(1)





Median of Two Sorted Arrays
if((A.length+B.length)%2==1)
    return helper(A, 0, A.length-1, B, 0, B.length-1, (A.length + B.length)/2+1);
else
    return (helper(A, 0, A.length-1, B, 0, B.length-1, (A.length+B.length)/2) + 
            helepr(A, 0, A.length-1, B, 0, B.length-1, (A.length+B.length)/2+1))/2.0;

private int helper(A, startA, endA, B, startB, endB, k) {
    lenA = endA-startA+1;
    lenB = endB-startB+1;
    if(lenA>lenB)
        return helper(B, startB, endB, A, startA, endA, k);
    if(k==1)
        return Math.min(A[startA], B[startB]);
    if(lenA==0)
        return B[startB+k-1];
    int posA = Math.min(k/2, lenA);
    int posB = k-posA;
    if(A[startA+posA-1]==B[startB+posB-1])
        return A[startA+posA-1];
    else if(A[startA+posA-1]>B[startB+posB-1])
        return helper(A, startA, startA+postA-1, B, startB+posB, endB, k-posB);
    else
        return helper(A, startA+posA, endA, B, startB, startB+posB+1, k-posA);
}

时间O(log(m+n)) 空间O(logk)





Merge Sorted Array
int indexA = m-1, indexB = n-1, index = m+n-1;
while(indexA>=0 && indexB>=0) {
    if(A[indexA]>B[indexB])
        A[index--] = A[indexA--];
    else
        A[index--] = B[indexB--];
}
while(indexB>=0)
    A[index--] = B[indexB--];

时间O(n) 空间O(1)





Unique Paths
动态规划 递推式为res[i][j] = res[i][j-1]+res[i-1][j]
int[] res = new int[n];
res[0] = 1;
for(int i=0; i<m; i++)
    for(int j=1; j<n; j++)
        res[j] += res[j-1];
return res[n-1];

时间O(m*n) 空间O(n)





Unique Paths ii
思路同上一题 只是这里有不能走的格子 递推式是若当前格子不为1则同上一题res[i][j]=res[i][j-1]+res[i-1][j] 否则res[i][j]=0 
if(obstacleGrid==null | obstacleGrid.length==0 || obstacleGrid[0].length==0)
    return 0;
int[] res = new int[obstacleGrid[0].length];
res[0] = 1;
for(int i=0; i<obstacleGrid.length; i++)
{
    for(int j=0; j<obstacleGrid[0].length; j++)     //j从0开始 因为每次要判断当前行第一个元素是不是障碍
    {
        if(obstacleGrid[i][j]==1)               //check
            res[j] = 0;
        else
        {
            if(j>0)                             //记得j>)才可以用res[j]+=res[j-1]
                res[j] += res[j-1];
        }
    }
}
return res[obstacleGrid[0].length-1];

时间O(m*n) 空间O(n)




Minimum Size Subarray Sum
if(nums==null || nums.length==0)
    return 0;
int left = 0, right = 0;
int minLen = nums.length+1;
int res = 0;
boolean sign = false;
while(right<nums.length) {
    res+=nums[right];
    while(res>=s) {
        sign = true;
        res-=nums[left];
        minLen = Math.min(minLen, right-left+1);
        left++;
    }
    right++;
}
return sign?minLen:0;

O(n) O(1)




Minimum Path Sum
带权重的最小路径和 如果是第一列元素res[j]=res[j-1]+grid[i][j] 否则res[j] = Math.min(res[j], res[j-1]) + grid[i][j] 
res[0] = grid[0][0];
for(int i=1; i<grid[0].length; i++)
    res[j] = res[j-1] + grid[0][i];
for(int i=1; i<grid.length; i++) {
    for(int j=0; j<grid[0].length; j++) {
        if(j==0)
            res[j]+=grid[i][0];
        else
            res[j] = grid[i][j]+Math.min(res[j], res[j-1]);
    }
}

时间O(m*n) 空间O(n)





Missing Range
人为加两个元素在lower-1 upper+1可以避免所有pesky case
if(A==null ||　A.length==0)
    res.add(getRange(lower, upper));
int prev = lower-1;
for(int i=0; i<=A.length; i++) {
    int curr = (i==A.length)? upper+1:A[i];
    if(curr-prev>=2)
        res.add(getRange(prev+1, curr-1));
    prev = curr;
}

private String getRange(int from, int to)
    return (from==to)? String.valueOf(from):String.valueOf(from) + "->" + String.valueOf(to);

时间O(A.length) 空间O(1)




Summary Ranges
public List<String> summaryRanges(int[] nums) {
    List<String> res = new ArrayList<String>();
    int from = 0, to = 0;
    while(to<nums.length) {
        to++;
        while(to<nums.length && nums[to]-nums[to-1]==1)
            to++;
        res.add(getRange(nums[from], nums[to-1]));
        from = to;
    }
    return res;
}

private String getRange(int from , int to) {
    return (from==to)?String.valueOf(from):String.valueOf(from)+"->"+String.valueOf(to);
}

O(n) O(1)





Next Permutation
int i=num.length-2;
while(i>=0 && num[i]>=num[i+1])
    i--;
if(i<0);
    reverse(num, 0);
    return;
int mark = i;
i = num.length-1;
while(num[i]<=num[mark])
    i--;
int tmp = num[mark];
num[mark] = num[i];
num[i] = tmp;
reverse(num, mark+1);

时间O(3*n)=O(n) 空间O(1)





Pascal Triangle
if rowNums<0
    return res;
List<Integer> first
first.add(1);
res.add(first);
for i=1; i<rowNums; i++
    List<Integer>　row = new ArrayList<Integer>()
    row.add(1);
    for j=1; j<i; j++
        row.add(res.get(i-1).get(j-1)+res.get(i-1).get(j));
    row.add(1);
    res.add(row);

时间O(n^2) 空间O(1)





Pascal Triangle ii
List<Integer>　res
if(rowIndex<0)
    return res;
res.add(1);
for i=1; i<=rowIndex; i++
    for j=res.size()-1; j>=0; j--
        res.set(j, res.get(j)+res.get(j-1));
    res.add(1);

时间O(n^2) 空间O(1)





Triangle
动态规划题 递推式sum[i][j]=min(sum[i-1][j-1],sum[i-1][j])+triangle[i][j]
for i=1; i<triangle.size(); i++
    sum[i] = sum[i-1] + triangle.get(i).get(i);     
    for(int j=i-1; j>=1; j--) {                    
        sum[j] = sum[j]<sum[j-1]?sum[j]:sum[j-1] + triangle.get(i).get(j);
    }
    sum[0] += triangle.get(i).get(0);

时间O(n^2) 空间O(n)




Product of Array Except Self
public int[] productExceptSelf(int[] nums) {
    int[] res = new int[nums.length];
    res[0] = 1;
    for(int i=1; i<nums.length; i++) {
        res[i] = res[i-1]*nums[i-1];
    }
    int right = 1;
    for(int i=nums.length-1; i>=0; i--) {
        res[i]*=right;
        right*=nums[i];
    }
    return res;
}

O(n) O(1)





Plus One
for i=digits.length-1; i>=0; i--
    if(digits[i]<9) {
        digits[i]+=1;
        return digits;
    }
    else
        digits[i] = 0;
int[] res = new int[digits.length+1];
res[0] = 1;

时间O(n) 空间一般是O(1) 最坏为O(n)





Remove Duplicates from Sorted Array
index = 1
for i=1; i<A.length; i++
    if(A[i]!=A[index-1])
        A[index]=A[i];
        index++;
return index;

时间O(n) 空间O(1)





Remove Duplicates from Sorted Array ii
if A.length<3
    return A.length;
index = 2;
for i=2; i<A.length; i++
    if(A[i]!=A[index-2])
        A[index]=A[i];
        index++;
return index;
时间O(n) 空间O(1)





Remove Element
两种解法 思路差不多 从前往后扫 时间O(n) 空间O(1)
int j = A.length-1;
for(int i=0; i<=j; i++) {
    if(A[i]==elem)
        A[i--] = A[j--]; 
}

从后往前
int j = A.length-1;
for(int i=A.length-1; i>-1; i--)
{
    if(A[i]==elem)  
    {
        A[i] = A[j--];   
    }
}



Rotate Array
if(k<=0)return;
k = k%nums.length;
reverse(nums, 0, nums.length-1);
reverse(nums, 0, k-1);
reverse(nums, k, nums.length-1);
时间O(n) 空间O(1)





Rotate Image
矩阵相关操作 考察数组的操作 一层一层旋转 顺时针旋转和逆时针旋转90度差不多只是赋值不同 而旋转180度则为对应两行每次逆序交换数值
for(int layer=0; layer<matrix.length/2; layer++) {
    for(int i=layer; i<matrix.length-1-layer; i++) {
        int tmp = matrix[layer][i];
        matrix[layer][i] = matrix[matrix.length-1-i][layer];
        matrix[matrix.length-1-i][layer] = matrix[matrix.length-1-layer][matrix.length-1-i];
        matrix[matrix.length-1-layer][matrix.length-1-i] = matrix[i][matrix.length-1-layer];
        matrix[i][matrix.length-1-layer] = tmp;
    }
}

时间O(n^2) 空间O(1)





Search a 2D Matrix
while(l<=r)
    if(matrix[mid][0]==target)
        return true;
    else if(matrix[mid][0]>target)
        r = mid - 1;
    else l = mid +　１；
if(r<0)
    return false;
int row = l;
l = 0;
r = matrix[0].length-1;
while(l<=r)
    if(matrix[row][mid]==target)
        return true;
    else if 
    else
return false;
时间O(logn) 空间O(1)





Search for a Range
public int[] searchRange(int[] A, int target) {
    int[] res = new int[2];
    res[0] = -1;
    res[1] = -1;
    if(A==null || A.length==0)
        return res;
    int left = 0;
    int right = A.length-1;
    int mid = 0;
    
    while(left<=right)
    {
        mid = (left+right)/2;
        if(A[mid]==target)
            break;
        else if(A[mid]<target)
            left = mid + 1;
        else
            right = mid - 1;
    }
    if(A[mid]!=target)
        return res;
        
    int newL = 0;
    int newR = mid;
    while(newL<=newR)
    {
        int m = (newL+newR)/2;
        if(A[m]==target)        //区别
            newR = m - 1;
        else
            newL = m + 1;
    }
    res[0] = newL;
    
    newL = mid;
    newR = A.length-1;
    while(newL<=newR)
    {
        int m = (newL+newR)/2;
        if(A[m]==target)            //区别
            newL = m + 1;
        else
            newR = m - 1;
    }
    res[1] = newR;
    return res;
时间O(logn) 空间O(1)







Set Matrix Zeroes
int firstRowMark = 1; int firstColMark = 1;
for(int i=0; i<matrix.length; i++)
{
    if(matrix[i][0]==0)
    {
        firstRowMark = 0;
        break;
    }
}
for(int i=0; i<matrix[0].length; i++)
{
    if(matrix[0][i]==0)
    {
        firstColMark = 0;
        break;
    }
}
for(int i=1; i<matrix.length; i++)
{
    for(int j=1; j<matrix[0].length; j++)
    {
        if(matrix[i][j]==0)
        {
            matrix[0][j] = 0;
            matrix[i][0] = 0;
        }
    }
}
for(int i=1; i<matrix.length; i++)
    for(int j=1; j<matrix[0].length; j++)
        if(matrix[0][j]==0 || matrix[i][0]==0)
            matrix[i][j] = 0;
if(firstRowMark==0)
for(int i=0; i<matrix.length; i++)
    matrix[i][0] = 0;
if(firstColMark==0)
for(int i=0; i<matrix[0].length; i++)
    matrix[0][i] = 0;
}

时间O(m*n) 空间O(1)





Sort Colors

1 计数排序
int[] c = new int[3];
int[] res = new int[A.length];
for int i=0; i<A.length; i++
    c[A[i]]+=1;
for int i=1; i<3; i++
    c[i]+=c[i-1];
for int i=A.length-1; i>=0; i--
    res[c[A[i]]-1] = A[i];
    c[A[i]]--;
for int i=0; i<A.length; i++
    A[i] = res[i];

2 
int index1 = 0, index2 = 0;
for(int i=0; i<A.length; i++)
    if(A[i]==0)
        A[i]=2;
        A[index1++] = 1;
        A[index0++] = 0;
    else if(A[i]==1)
        A[i]=2;
        A[index1++] = 1;

时间O(n) 空间O(1)



Spiral Matrix
List<Integer> res = new ArrayList<Integer>();
if(matrix==null || matrix.length==0 || matrix[0].length==0)
    return res;
int left = 0; int right = matrix[0].length-1; int up = 0; int down = matrix.length-1;
int direction = 0;
while(true)
{
    if(direction==0)
    {
        for(int i=left; i<right+1; i++)
        {
            res.add(matrix[up][i]);
        }
        up++;
    }
    if(direction == 1)
    {
        for(int i=up; i<down+1; i++)
        {
            res.add(matrix[i][right]);
        }
        right--;
    }
    if(direction == 2)
    {
        for(int i=right; i>=left; i--)
        {
            res.add(matrix[down][i]);
        }
        down--;
    }
    if(direction == 3)
    {
        for(int i=down; i>=up; i--)
        {
            res.add(matrix[i][left]);
        }
        left++;
    }
    if(left>right || up>down)
        return res;
    direction = (direction+1)%4;
}
时间O(m*n) 空间O(1)



Spiral Matrix ii
if(n<0)
    return null;
int[][] res = new int[n][n];
int direction = 0;
int left=0; int right = n-1; int up = 0; int down = n-1;
int num = 1;
while(true)
{
    if(direction==0)
    {
        for(int i=left; i<right+1; i++)
        {
            res[up][i] = num;
            num+=1;
        }
        up++;
    }
    if(direction==1)
    {
        for(int i=up; i<down+1; i++)
        {
            res[i][right] = num;
            num+=1;
        }
        right--;
    }
    if(direction==2)
    {
        for(int i=right; i>=left; i--)
        {
            res[down][i] = num;
            num+=1;
        }
        down--;
    }
    if(direction==3)
    {
        for(int i=down; i>=up; i--)
        {
            res[i][left] = num;
            num+=1;
        }
        left++;
    }
    if(up>down || left>right)
        return res;
    direction = (direction+1)%4;
}
时间O(n*n) 空间O(1)





Triangle
动态规划题 建立一个数组sum[triangle.size()] sum[0]=triangle.get(0).get(0) 从第二行开始每次从后往前扫 单独处理第一个和最后一个元素 对于一般的元素

取上一行相邻两个元素中最小的那个加上当前行对应元素值 代码如下
sum[i] = sum[i-1] + triangle.get(i).get(i);     
for(int j=i-1; j>=1; j--) {                    
    sum[j] = sum[j]<sum[j-1]?sum[j]:sum[j-1] + triangle.get(i).get(j);
}
sum[0] += triangle.get(i).get(0);





Word Search
public boolean exist(char[][] board, String word) {
    if(word==null || word.length()==0)
        return true;
    if(board==null || board.length==0 || board[0].length==0)
        return false;
    boolean[][] used = new boolean[board.length][board[0].length];
    for(int i=0; i<board.length; i++) {
        for(int j=0; j<board[0].length; j++) {
            if(search(board, 0, i, j, used, word))
                return true;
        }
    }
    return false;
}

private boolean search(char[][] board, int index, int i, int j, boolean[][] used, String word) {
    if(index==word.length())
        return true;
    if(i<0 || j<0 || i>=board.length || j>=board[0].length || used[i][j] || word.charAt(index)!=board[i][j])
        return false;
    used[i][j] = true;
    boolean res = (search(board, index+1, i+1, j, used, word)
                    || search(board, index+1, i-1, j, used, word)
                    || search(board, index+1, i, j+1, used, word)
                    || search(board, index+1, i, j-1, used, word));
    used[i][j] = false;
    return res;
}
时间复杂度 空间O(m*n)


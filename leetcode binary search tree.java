Leetcode Binary Search Tree

Contains Duplicate iii
找在array中是否存在两个不同的位置i j 满足nums[i] nums[j]差值不超过t 同时i j的差值不超过k
maintain a window of size k of the previous values that can be queried for value ranges. 

The best data structure to do that is Binary Search Tree. As a result maintaining the tree of size k will result in 

time complexity O(N lg K). In order to check if there exists any value of range abs(nums[i] - nums[j]) to simple queries 

can be executed both of time complexity O(lg K)

if(nums==null || nums.length==0)
	return false;
TreeSet<Integer> tree = new TreeSet<Integer>();
for(int i=0; i<nums.length; i++) {
	Integer rightSub = tree.floor(nums[i]+t);
	Integer leftSub = tree.ceiling(nums[i]-t);
	if((leftSub!=null && leftSub<=nums[i]) || (rightSub!=null && rightSub>=nums[i]))
		return true;
	tree.add(nums[i]);
	if(i-k>0)
		tree.remove(nums[i]);
}
return false;

O(nlogk) O(k)
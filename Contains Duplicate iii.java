public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        int i = 0, j = 0;
        while (i < nums.length) {
            Integer rightSub = treeSet.floor(nums[i] + t);
            Integer leftSub = treeSet.ceiling(nums[i] - t);
            if ((leftSub != null && leftSub <= nums[i]) || (rightSub != null && rightSub >= nums[i])) return true;
            treeSet.add(nums[i]);
            i++;
            if (i - j > k) treeSet.remove(nums[j++]);
        }
        return false;
    }
}

O(nlogk) O(k)

This problem requires to maintain a window of size k of the previous values that can be queried for value ranges. 

The best data structure to do that is Binary Search Tree. As a result maintaining the tree of size k will result in 

time complexity O(N lg K). In order to check if there exists any value of range abs(nums[i] - nums[j]) to simple queries 

can be executed both of time complexity O(lg K)

https://leetcode.com/discuss/38177/java-o-n-lg-k-solution


题目是问是不是存在一个符合条件的数对，维护一个大小为 k 的二叉搜索树，来一个新的元素时，在BST上二分搜索有没有符合条件的数对，

动态更新这个BST。因为BST的大小为 k 或不超过 k，所以这里面的数下标的差值一定是符合条件的。还有几点要注意的就是nums[i]与nums[j]的差值的是绝对值，

所以要分别找lower_bound跟upper_bound

http://www.cnblogs.com/easonliu/p/4544073.html


顺便复习java TreeMap TreeSet java中bst可以用这两个class 基于red black tree实现的 相关方法了解一下ceiling() floor() contains() add() remove()

如果12行不加'=' 会出错
Input:  [1,2], 0, 1
Output: true
Expected:   false
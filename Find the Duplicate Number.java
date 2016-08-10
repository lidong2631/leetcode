public class Solution {
    public int findDuplicate(int[] nums) {
        for(int i=0; i<nums.length; i++) {
            if(nums[nums[i]-1]!=nums[i]) {
                int tmp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = tmp;
                i--;
            }
        }
        for(int i=0; i<nums.length; i++) {
            if(nums[i]!=i+1)
                return nums[i];
        }
        return nums[nums.length-1];
    }
}

same as first missing number


Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. 
Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.




public class Solution {
    public int findDuplicate(int[] nums) {
        int left = 1, right = nums.length-1;        //left=0 也可以ac
        while(left<right) {
            int mid = left + (right-left)/2;    //取这组数中的平均数
            int count = 0;
            for(Integer i : nums) {     //记录小于等于平均数的数的个数
                if(i<=mid)
                    count++;
            }
            if(count<=mid)          //如果count小于等于mid 说明小于等于平均数的数多 重复在此
                left = mid + 1;
            else
                right = mid;
        }
        return left;        //写right也可以
    }
}

O(nlogn) O(1)

https://leetcode.com/discuss/60830/solutions-explanation-space-without-changing-input-array
http://www.cnblogs.com/grandyang/p/4843654.html



public class Solution {
    public int findDuplicate(int[] nums) {
        if(nums.length>1) {
            int slow = nums[0];
            int fast = nums[nums[0]];
            while(slow!=fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }
            fast = 0;
            while(slow!=fast) {
                slow = nums[slow];
                fast = nums[fast];
            }
            return fast;
        }
        return -1;
    }
}

这解法牛逼 得深刻理解LinkedList Cycle ii 将序列看成一个有环的图 本质是Floyd cycle-finding algorithm (Tortoise and hare)

2   5   1   1   4   3
slow:2         slow:5
fast:1         fast:0

slow:1         slow:3
fast:3         fast:2

slow:5         slow:1
fast:5         fast:1


O(n) O(1)

https://leetcode.com/discuss/61514/understood-solution-space-without-modifying-explanation
https://leetcode.com/discuss/61086/java-time-and-space-solution-similar-find-loop-in-linkedlist
http://bookshadow.com/weblog/2015/09/28/leetcode-find-duplicate-number/
https://en.wikipedia.org/wiki/Cycle_detection
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
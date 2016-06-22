public class NumArray {

    private int nums[];

    public NumArray(int[] nums) {
        for(int i=1; i<nums.length; i++)
            nums[i]+=nums[i-1];
        this.nums = nums;
    }

    public int sumRange(int i, int j) {
        if(i==0)
            return nums[j];
        return nums[j]-nums[i-1];
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);


Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3

O(n) O(1)


https://leetcode.com/discuss/68696/java-simple-o-n-init-and-o-1-query-solution
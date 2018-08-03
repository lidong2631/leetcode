Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].


public int[] twoSum(int[] numbers, int target) {
    int[] res = new int[2];
    if (numbers == null || numbers.length < 2)
        return null;
    HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
    for (int i = 0; i < numbers.length; i++) {
        if (map.containsKey(target - numbers[i])) {
            res[0] = map.get(target-numbers[i]) + 1;
            res[1] = i + 1;
            return res;
        }
        map.put(numbers[i], i);
    }
    return null;
}


Golang:
func twoSum(nums []int, target int) []int {
    var res []int
    if len(nums) < 2 {
        return res
    }
    m := make(map[int]int)
    for i, v := range nums {
        val, ok := m[target-v]
        if ok {
            res = append(res, i)
            res = append(res, val)
            return res
        }
        m[v] = i
    }
    return res
}



Python:
class Solution:
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        d = {}
        res = []
        for i in range(len(nums)):
            if target - nums[i] in d:
                res.append(d[target-nums[i]])
                res.append(i)
                return res
            d[nums[i]] = i
        return res



follow up: what if we can have duplicate results? (2, 3, 3, 7, 7, 9) target = 10
use method 1 but hashmap value should be a list of all index for the specific number instead of a single value


第二种解法是先对数组进行排序，然后使用夹逼的方法找出满足条件的pair，原理是因为数组是有序的，那么假设当前结果比target大，

那么左端序号右移只会使两个数的和更大，反之亦然。所以每次只会有一个选择，从而实现线性就可以求出结果。该算法的时间复杂度是O(nlogn+n)=O(nlogn)，

空间复杂度取决于排序算法。代码如下：

public int[] twoSum(int[] numbers, int target) {
    int[] res = new int[2];
    if (numbers == null || numbers.length < 2)
        return null;
    Arrays.sort(numbers);
    int l = 0;
    int r = numbers.length - 1;
    while (l < r) {
        if (numbers[l] + numbers[r] == target) {
            res[0] = number[l];
            res[1] = number[r];
            return res;
        }
        else if (numbers[l] + numbers[r] > target) {
            r--;
        } else
            l++;
    }
    return null;
}



注意，在这里，输出结果改成了满足相加等于target的两个数，而不是他们的index。因为要排序，如果要输出index，需要对原来的数的index进行记录，

方法是构造一个数据结构，包含数字的值和index，然后排序。所以从这个角度来看，这道题第二种解法并没有第一种解法好，空间也是O(n). 

在LeetCode原题中是假设结果有且仅有一个的，一般来说面试时会要求出所有的结果，这个时候会涉及到重复pair的处理，相关的内容会在3Sum那道题目中涉及到，

请参见3Sum -- LeetCode.

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]



Python:
class Solution:
    def threeSum(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        nums.sort()
        res = []
        for i in range(len(nums)-2):
            if i == 0 or nums[i] > nums[i-1]:
                left = i + 1; right = len(nums) - 1
                while left < right:
                    if nums[left] + nums[right] == -nums[i]:
                        res.append([nums[i], nums[left], nums[right]])
                        left += 1; right -= 1
                        while left < right and nums[left] == nums[left-1]: left +=1
                        while left < right and nums[right] == nums[right+1]: right -= 1
                    elif nums[left] + nums[right] < -nums[i]:
                        while left < right:
                            left += 1
                            if nums[left] > nums[left-1]: break
                    else:
                        while left < right:
                            right -= 1
                            if nums[right] < nums[right+1]: break
        return res




Java:
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] > nums[i-1]) {
                int l = i+1, r = nums.length-1;
                while (l < r) {
                    if (nums[l] + nums[r] == -nums[i]) {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[l]); tmp.add(nums[r]); tmp.add(nums[i]);
                        res.add(tmp);
                        l++; r--;
                        while (l < r && nums[l] == nums[l-1]) l++;
                        while (l < r && nums[r] == nums[r+1]) r--;
                    } else if (nums[l] + nums[r] < -nums[i]) {
                        while (l < r) {
                            l++;
                            if (nums[l] > nums[l-1]) break;
                        }
                    } else {
                        while (l < r) {
                            r--;
                            if (nums[r] < nums[r+1]) break;
                        }
                    }
                }
            }
        }
        return res;
    }
}

O(n^2)




Golang:
func threeSum(nums []int) [][]int {
    sort.Ints(nums)
    var res [][]int
    for i := 0; i < len(nums) - 2; i++ {
        if i == 0 || nums[i] > nums[i-1] {
            l, r := i+1, len(nums)-1
            for l < r {
                if nums[l] + nums[r] == -nums[i] {
                    tmp := []int{nums[l], nums[r], nums[i]}
                    res = append(res, tmp)
                    l++; r--
                    for l < r && nums[l] == nums[l-1] { l++ }
                    for l < r && nums[r] == nums[r+1] { r-- }
                } else if nums[l] + nums[r] < -nums[i] {
                    for l < r {
                        l++
                        if nums[l] > nums[l-1] { break }
                    }
                } else {
                    for l < r {
                        r--
                        if nums[r] < nums[r+1] { break }
                    }
                }
            }
        }
    }
    return res
}





from code ganker:

这道题是Two Sum的扩展，brute force时间复杂度为O(n^3), 对每三个数进行比较。这道题和Two Sum有所不同，使用哈希表的解法并不是很方便，

因为结果数组中元素可能重复，如果不排序对于重复的处理将会比较麻烦，因此这道题一般使用排序之后夹逼的方法，

总的时间复杂度为O(n^2+nlogn)=(n^2),空间复杂度是O(n),代码如下：

public ArrayList<ArrayList<Integer>> threeSum(int[] num)
{
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if(num==null || num.length<=2)
        return res;
    Arrays.sort(num);
    for(int i=num.length-1;i>=2;i--)
    {
        if(i<num.length-1 && num[i]==num[i+1])
            continue;
         ArrayList<ArrayList<Integer>> curRes = twoSum(num,i-1,-num[i]);
         for(int j=0;j<curRes.size();j++)
         {
             curRes.get(j).add(num[i]);
         }
         res.addAll(curRes);
    }
    return res;
}
private ArrayList<ArrayList<Integer>> twoSum(int[] num, int end,int target)
{
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if(num==null || num.length<=1)
        return res;
    int l = 0;
    int r = end;
    while(l<r)
    {
        if(num[l]+num[r]==target)
        {
            ArrayList<Integer> item = new ArrayList<Integer>();
            item.add(num[l]);
            item.add(num[r]);
            res.add(item);
            l++;
            r--;
            while(l<r&&num[l]==num[l-1])
                l++;
            while(l<r&&num[r]==num[r+1])
                r--;
        }
        else if(num[l]+num[r]>target)
        {
            r--;
        }
        else
        {
            l++;
        }
    }
    return res;
}



注意，在这里为了避免重复结果，对于已经判断过的数会skip掉，这也是排序带来的方便。 这道题考察的点其实和Two Sum差不多，

Two Sum是3Sum的一个subroutine, 不过更加综合一些，实现上更加全面，需要注意细节，面试中比较常见的一道题。

此题更加复杂的扩展是4Sum，请参见4Sum -- LeetCode
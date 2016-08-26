题意：从一个数组中找到三个数，使这三个数的和为0。有可能存在多组解，也有可能存在重复的解，所以需要去重。比如：num=[-1,0,1,2,-1,-4];

那么存在两组解：[[-1,0,1],[-1,-1,2]]，解中的数需要是从小到大排序状态。

解题思路：1，先将数组排序。

　　　　　2，排序后，可以按照TwoSum的思路来解题。怎么解呢？可以将num[i]的相反数即-num[i]作为target，

        然后从i+1到len(num)-1的数组元素中寻找两个数使它们的和为-num[i]就可以了。下标i的范围是从0到len(num)-3。

　　　　　3，这个过程要注意去重。

　　　　   4，时间复杂度为O(N^2)。

代码：


class Solution:
    # @return a list of lists of length 3, [[val1,val2,val3]]
    def threeSum(self, num):
        num.sort()
        res = []
        for i in range(len(num)-2):
            if i == 0 or num[i] > num[i-1]:
                left = i + 1; right = len(num) - 1
                while left < right:
                    if num[left] + num[right] == -num[i]:
                        res.append([num[i], num[left], num[right]])
                        left += 1; right -= 1
                        while left < right and num[left] == num[left-1]: left +=1
                        while left < right and num[right] == num[right+1]: right -= 1
                    elif num[left] + num[right] < -num[i]:
                        while left < right:
                            left += 1
                            if num[left] > num[left-1]: break
                    else:
                        while left < right:
                            right -= 1
                            if num[right] < num[right+1]: break
        return res




public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            if (i < nums.length - 1 && nums[i] == nums[i+1]) continue;
            List<List<Integer>> twoSum = getTwoSum(nums, i-1, -nums[i]);
            for (int j = 0; j < twoSum.size(); j++) {
                twoSum.get(j).add(nums[i]);
            }
            res.addAll(twoSum);
        }
        return res;
    }
    
    private List<List<Integer>> getTwoSum(int[] nums, int end, int target) {
        int left = 0, right = end;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(nums[left]);
                list.add(nums[right]);
                res.add(list);
                left++; right--;
                while (left < right && nums[left] == nums[left-1]) left++;
                while (left < right && nums[right] == nums[right+1]) right--;
            }
            else if (nums[left] + nums[right] > target)
                right--;
            else
                left++;
        }
        return res;
    }
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
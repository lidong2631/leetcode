class Solution:
    # @return a list of lists of length 3, [[val1,val2,val3]]
    def threeSum(self, num):
        num.sort()          #将num先排好序 res用来保存结果
        res = []
        for i in range(len(num)-2):         #循环从0到len(num)-3 因为一共要3个数相加等于target 到倒数第三个数即可结束 后面数已经凑不出3个数
            if i == 0 or num[i] > num[i-1]:         #num[i]>num[i-1]是为了保证这个元素不是重复元素
                left = i + 1; right = len(num) - 1      #设左右指针 找num[i]后面两个数和是-num[i]
                while left < right:                         #如果两指针没有相遇
                    if num[left] + num[right] == -num[i]:           #如果相等 将结果append进res 注意append顺序是从小到大 将左右指针各移一位
                        res.append([num[i], num[left], num[right]])
                        left+=1; right-=1
                        while left < right and num[left] == num[left-1]:    #如果移动完后一个元素是重复元素 继续移动直到非重复元素或两指针相遇
                            left+=1
                        while left < right and num[right] == num[right+1]:  #同理
                            right-=1
                    elif num[left] + num[right] < -num[i]:              #如果两个数和小于-num[i] 移动做指针 同样要注意是重复元素就一直移动
                        left+=1
                        while left < right and num[left] == num[left-1]:
                            left+=1
                    else:                           #大于同理
                        right-=1
                        while left < right and num[right] == num[right+1]:
                            right-=1
        return res

Note: 这题有好几个地方要去重复元素要多注意 循环遍历到倒数第三个元素即可 还有append时按递增排序这个小细节要注意 其他思路跟two sum大致






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
    public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num==null || num.length<=2)
            return res;
        Arrays.sort(num);
        for(int i=num.length-1; i>=2; i--)      //这里从后往前遍历是因为two sum那个函数的实现是从0到end的，所以选取元素作为第三个是从后面选起
        {
            if(i<num.length-1 && num[i]==num[i+1])      //去重 注意这里不能就写num[i]==num[i+1] 那样碰上[0,0,0]case会返回空集 这样写当前值不管是否有重都会先执行一次
                continue;
            List<List<Integer>> twosumRes = TwoSum(num, i-1, -num[i]);  //拿到two sum为-num[i]的集合
            
            for(int j=0; j<twosumRes.size(); j++)   
                twosumRes.get(j).add(num[i]);       //得到最终结果 加到res
            res.addAll(twosumRes);                   //这里将twosumRes加到res 必须用addAll()函数
        }
        return res;
    }
    
    private List<List<Integer>> TwoSum(int[] num, int end, int target) {    //求two sum
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num==null || num.length<=1)
            return res;
        int left = 0;
        int right = end;
        while(left<right)
        {
            if(num[left]+num[right]==target)
            {
                List<Integer> tmp = new ArrayList<Integer>();   //不一样
                tmp.add(num[left]);                             //
                tmp.add(num[right]);                            // 
                res.add(tmp);                                   //
                left++;     //这里单独写left++ right--不然相等后就不会继续找下一个解而是停住 之前我省略这里以为就写whlile里就够不对
                right--;
                while(left<right && num[left]==num[left-1]) //不一样
                    left++;
                while(left<right && num[right]==num[right+1])   //
                    right--;
            }
            else if(num[left]+num[right]<target)
                left++;
            else
                right--;
        }
        return res;
    }
}

Note: 根据code ganker改编 TwoSum函数由上一题得来 稍作修改返回的是一个list里面包含所有的解而不是index 另外需要判断下重复元素

最终res.addAll(twosumRes) addAll里也可以加二位List





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
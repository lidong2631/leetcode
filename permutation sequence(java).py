class Solution:
    # @return a string
    def getPermutation(self, n, k):
        k-=1; res = ''; fac = 1            #k-=1 是为了让下标从0开始，这样下标就是从0到n-1，不用考虑n时去取余，更好地跟数组下标匹配。
        num = [1,2,3,4,5,6,7,8,9]
        for i in range(1, n):       #将fac初始值设为(n-1)!
            fac*=i
        for i in range(n-1, -1, -1):    #循环 逆序是为了每次fac可以除掉最大的因数 如n-1
            tmp = num[k/fac]    #k/fac对应它在num数组中的索引值
            res += str(tmp)     #将结果加到res中
            num.remove(tmp)     #将这个值从num list中剃掉
            if i!=0:        #每次找完一个数 k要跟fac取余数准备判断下一个数 fac要除以i
                k %= fac
                fac /= i
        return res

Note：这题自己算时在k%fac = 0时很迷惑







题意：

The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
 

Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.

解题思路：刚开始用dfs，但一直TLE。貌似用java和c++写dfs可以过，看来python确实慢啊。只能采用一种更巧妙的思路了。

　　　　　

其实本题数据不大，n最多为9，9! = 362880，枚举应该能够通过（我没试验）。

 

我采用的方法是计算第k个Permutation。

假设n = 6，k = 400

先计算第一位，

第一位为6，那么它最少也是第5! * 5 + 1个排列，这是因为第一位为1/2/3/4/5时，都有5!个排列，因此第一位为6时，至少是第5! * 5 + 1个排列（这个排列为612345）。

5! * 5 + 1 = 601 > k，所以第一位不可能是6.

一个一个地枚举，直到第一位为4时才行，这时，4xxxxx至少为第5! * 3 + 1 = 361个排列。

 

然后计算第二位，

与计算第一位时的区别在于，46xxxx至少为第4! * 4 + 1 = 97个排列，这是因为比6小的只有5/3/2/1了。

最后可以计算出第二位为2。

 

最终得出第400个排列为425361。

代码：

复制代码
class Solution:
    # @return a string
    # def dfs(self, n, k, string, stringlist):
    #     if len(stringlist) == n: 
    #         Solution.count += 1
    #         if Solution.count == k: 
    #             print stringlist
    #             return
    #     for i in range(len(string)):
    #         self.dfs(n, k, string[:i]+string[i+1:], stringlist+string[i])

    # def getPermutation(self, n, k):
    #     string = ''
    #     for i in range(n): string += str(i+1)
    #     Solution.count = 0
    #     self.dfs(n, k, string, '')
    def getPermutation(self, n, k):
        res = ''
        k -= 1
        fac = 1
        for i in range(1, n): fac *= i
        num = [1, 2, 3, 4, 5, 6, 7, 8, 9]
        for i in reversed(range(n)):
            curr = num[k/fac]
            res += str(curr)
            num.remove(curr)
            if i !=0:
                k %= fac
                fac /= i
        return res







public class Solution {
    public String getPermutation(int n, int k) {
        if(k<=0)
            return "";
        k--;            //一上来k－1 因为num list从0开始
        StringBuilder res = new StringBuilder();
        List<Integer> num = new ArrayList<Integer>();
        int fac = 1;
        for(int i=2; i<n; i++)      //i到n－1 fac=(n-1)*(n-2)*...*1
            fac*=i;
        for(int i=1; i<=n; i++)
            num.add(i);
        int round = n-1;            //round = n-1
        while(round>=0)
        {
            int index = k/fac;      //每次定位k在不在这个fac group
            k = k%fac;              
            res.append(num.get(index));     //将num[index]值加到res中
            num.remove(index);              //从num中移除index对应的值
            if(round>0)                     //如果round大于0 fac到下一个group fac=(n-2)*(n-3)*...*1
                fac/=round;
            round--;                        //每一轮round减1
        }
        return res.toString();
    }
}

Note: 看注释 没什么说的

时间O(n^2) 空间O(n)





from code ganker:

这道题目算法上没有什么特别的，更像是一道找规律的数学题目。我们知道，n个数的permutation总共有n阶乘个，基于这个性质我们可以得到某一位对应的数字是哪一个。

思路是这样的，比如当前长度是n，我们知道每个相同的起始元素对应(n-1)!个permutation，也就是(n-1)!个permutation后会换一个起始元素。

因此，只要当前的k进行(n-1)!取余，得到的数字就是当前剩余数组的index，如此就可以得到对应的元素。如此递推直到数组中没有元素结束。

实现中我们要维护一个数组来记录当前的元素，每次得到一个元素加入结果数组，然后从剩余数组中移除，因此空间复杂度是O(n)。时间上总共需要n个回合，

而每次删除元素如果是用数组需要O(n),所以总共是O(n^2)。这里如果不移除元素也需要对元素做标记，所以要判断第一个还是个线性的操作。代码如下：

public String getPermutation(int n, int k) {
    if(n<=0)
        return "";
    k--;
    StringBuilder res = new StringBuilder();
    int factorial = 1;
    ArrayList<Integer> nums = new ArrayList<Integer>();
    for(int i=2;i<n;i++)
    {
        factorial *= i;
    }
    for(int i=1;i<=n;i++)
    {
        nums.add(i);
    }
    int round = n-1;
    while(round>=0)
    {
        int index = k/factorial;
        k %= factorial;
        res.append(nums.get(index));
        nums.remove(index);
        if(round>0)
            factorial /= round;
        round--;
    }
    return res.toString();
}

上面代码还有有个小细节，就是一开始把k--，目的是让下标从0开始，这样下标就是从0到n-1，不用考虑n时去取余，更好地跟数组下标匹配。

如果有朋友有更好的思路来实现线性的时间复杂度，欢迎指教哈，可以留言或者发邮件到linhuanmars@gmail.com给我。


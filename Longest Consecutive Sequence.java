Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.

解题思路：使用一个哈希表，在Python中是字典dict数据类型。dict中的映射关系是{x in num：False}，这个表示num中的x元素没有被访问过，

如果被访问过，则为True。如果x没有被访问过，检查x+1，x+2...，x-1，x-2是否在dict中，如果在dict中，就可以计数。最后可以求得最大长度。

代码：

复制代码
class Solution:
    # @param num, a list of integer
    # @return an integer
    def longestConsecutive(self, num):
        dict = {x: False for x in num} # False means not visited
        maxLen = -1
        for i in dict:
            if dict[i] == False:
                curr = i+1; lenright = 0
                while curr in dict:
                    lenright += 1; dict[curr] = True; curr += 1
                curr = i-1; lenleft = 0
                while curr in dict:
                    lenleft += 1; dict[curr] = True; curr -= 1
                maxLen = max(maxLen, lenleft+1+lenright)
        return maxLen




public class Solution {
    public int longestConsecutive(int[] nums) {
        int maxLen = 0;
        Set<Integer> set = new HashSet<>();
        for (int n : nums) set.add(n);
        while (!set.isEmpty()) {
            Iterator i = set.iterator();
            Integer curr = (Integer)i.next();
            set.remove(curr);
            int left = curr - 1, right = curr + 1, maxL = 0, maxR = 0;
            while (set.contains(left)) {
                set.remove(left);
                maxL++;
                left--;
            }
            while (set.contains(right)) {
                set.remove(right);
                maxR++;
                right++;
            }
            maxLen = Math.max(maxLen, maxL + 1 + maxR);
        }
        return maxLen;
    }
}




from code ganker:

这道题是要求出最长的整数连续串。我们先说说简单直接的思路，就是先排序，然后做一次扫描，记录当前连续串长度，如果连续串中断，

则比较是否为当前最长连续串，并且把当前串长度置0。这样时间复杂度是很明确，就是排序的复杂度加上一次线性扫描。如果不用特殊的线性排序算法，

复杂度就是O(nlogn)。

其实这个题看起来是数字处理，排序的问题，但是如果要达到好的时间复杂度，还得从图的角度来考虑。思路是把这些数字看成图的顶点，而边就是他相邻的数字，

然后进行深度优先搜索。通俗一点说就是先把数字放到一个集合中，拿到一个数字，就往其两边搜索，得到包含这个数字的最长串，

并且把用过的数字从集合中移除（因为连续的关系，一个数字不会出现在两个串中）。最后比较当前串是不是比当前最大串要长，是则更新。

如此继续直到集合为空。如果我们用HashSet来存储数字，则可以认为访问时间是常量的，那么算法需要一次扫描来建立集合，第二次扫描来找出最长串，

所以复杂度是O(2*n)=O(n)，空间复杂度是集合的大小，即O(n)。代码如下：

public int longestConsecutive(int[] num) {
    if(num == null || num.length == 0)
        return 0;
    HashSet<Integer> set = new HashSet<Integer>();
    int res = 1;
    for(int i=0;i<num.length;i++)
    {
        set.add(num[i]);
    }
    while(!set.isEmpty())
    {
        Iterator iter = set.iterator();
        int item = (Integer)iter.next();
        set.remove(item);
        int len = 1;
        int i = item-1;
        while(set.contains(i))
        {
            set.remove(i--);
            len++;
        }
        i = item+1;
        while(set.contains(i))
        {
            set.remove(i++);
            len++;
        }
        if(len>res)
            res = len;
    }
    return res;
}

这是一个非常不错的题目，有比较好的算法思想，看起来是一个排序扫描的题目，其实想要优化得借助图的算法，模型也比较简单，很适合在面试中提问。

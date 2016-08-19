题意：找出数组numbers中的两个数，它们的和为给定的一个数target，并返回这两个数的索引，注意这里的索引不是数组下标，而是数组下标加1。

比如numbers={2,7,11,17}; target=9。那么返回一个元组(1,2)。这道题不需要去重，对于每一个target输入，只有一组解，索引要按照大小顺序排列。

解题思路：1，由于要找到符合题意的数组元素的下标，所以先要将原来的数组深拷贝一份，然后排序。

　　　　   2，然后在排序后的数组中找两个数使它们相加为target。这个思路比较明显：使用两个指针，一个指向头，一个指向尾，

        两个指针向中间移动并检查两个指针指向的数的和是否为target。如果找到了这两个数，再将这两个数在原数组中的位置找出来就可以了。

　　　　　3，要注意的一点是：在原来数组中找下标时，需要一个从头找，一个从尾找，要不无法通过。如这个例子：numbers=[0,1,2,0]; target=0。

        如果都从头开始找，就会有问题。

代码：


class Solution:
    # @return a tuple, (index1, index2)
    def twoSum(self, num, target):
        index = []
        numtosort = num[:]; numtosort.sort()
        i = 0; j = len(numtosort) - 1
        while i < j:
            if numtosort[i] + numtosort[j] == target:
                for k in range(0,len(num)):
                    if num[k] == numtosort[i]:
                        index.append(k)
                        break
                for k in range(len(num)-1,-1,-1):
                    if num[k] == numtosort[j]:
                        index.append(k)
                        break
                index.sort()
                break
            elif numtosort[i] + numtosort[j] < target:
                i = i + 1
            elif numtosort[i] + numtosort[j] > target:
                j = j - 1

        return (index[0]+1,index[1]+1)





public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if(numbers==null || numbers.length<2)
            return null;
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0; i<numbers.length; i++)
        {
            if(map.containsKey(target-numbers[i]))
            {
                res[0] = map.get(target-numbers[i])+1;
                res[1] = i+1;
                return res;
            }
            map.put(numbers[i], i);
        }
        return null;
    }
}

Note: 根据code ganker第一种解法改编 用哈希表记录 时间空间都是O(n) code ganker第二种解法是先排序 再用两边夹逼的方法 跟python版解法一样

但是需要一个额外的空间记录原数据的索引 所以空间上仍是O(n) 并且时间上也没有更好是O(nlogn) 综上第一种方法更好






这是一道非常经典的题目，brute force时间复杂度为O(n^2), 对每一对pair两两比较。 优化的方法一般有两种，第一种是用哈希表，时间复杂度为O(n),

同时空间复杂度也是O(n),代码如下：

public int[] twoSum(int[] numbers, int target) {
    int[] res = new int[2];
    if(numbers==null || numbers.length<2)
        return null;
    HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    for(int i=0;i<numbers.length;i++)
    {
        if(map.containsKey(target-numbers[i]))
        {
            res[0]=map.get(target-numbers[i])+1;
            res[1]=i+1;
            return res;
        }
        map.put(numbers[i],i);
    }
    return null;
}



第二种解法是先对数组进行排序，然后使用夹逼的方法找出满足条件的pair，原理是因为数组是有序的，那么假设当前结果比target大，

那么左端序号右移只会使两个数的和更大，反之亦然。所以每次只会有一个选择，从而实现线性就可以求出结果。该算法的时间复杂度是O(nlogn+n)=O(nlogn)，

空间复杂度取决于排序算法。代码如下：

public int[] twoSum(int[] numbers, int target) {
    int[] res = new int[2];
    if(numbers==null || numbers.length<2)
        return null;
    Arrays.sort(numbers);
    int l = 0;
    int r = numbers.length-1;
    while(l<r)
    {
        if(numbers[l]+numbers[r]==target)
        {
            res[0] = number[l];
            res[1] = number[r];
            return res;
        }
        else if(numbers[l]+numbers[r]>target)
        {
            r--;
        }
        else
        {
            l++;
        }
    }
    return null;
}

注意，在这里，输出结果改成了满足相加等于target的两个数，而不是他们的index。因为要排序，如果要输出index，需要对原来的数的index进行记录，

方法是构造一个数据结构，包含数字的值和index，然后排序。所以从这个角度来看，这道题第二种解法并没有第一种解法好，空间也是O(n). 

在LeetCode原题中是假设结果有且仅有一个的，一般来说面试时会要求出所有的结果，这个时候会涉及到重复pair的处理，相关的内容会在3Sum那道题目中涉及到，

请参见3Sum -- LeetCode.

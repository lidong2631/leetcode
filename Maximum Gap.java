题意：给定一个未排序的数组，返回其排序后的数组中 相邻元素之差 最大的值。
比如给定：[5,9,8,3,15]
排序后为：[3,5,8,9,15]，相邻元素之差最大的是15-9=6，返回6。
复杂度要求：时间空间均为O(n)。

这道题最直接的解法是，先排序，得到有序数组，然后再对相邻元素作差，找出差最大的




下面用桶排序实现，这也是leetcode上给出的参考解法，我直接copy过来：

Suppose there are N elements and they range from A to B.

Then the maximum gap will be no smaller than ceiling[(B - A) / (N - 1)]

Let the length of a bucket to be len = ceiling[(B - A) / (N - 1)], then we will have at most num = (B - A) / len + 1 of bucket

for any number K in the array, we can easily find out which bucket it belongs by calculating loc = (K - A) / len and 

therefore maintain the maximum and minimum elements in each bucket.

Since the maximum difference between elements in the same buckets will be at most len - 1, so the final answer will not be 

taken from two elements in the same buckets.

For each non-empty buckets p, find the next non-empty buckets q, then q.min - p.max could be the potential answer to the question. 

Return the maximum of all those values.

public class Solution {
    public int maximumGap(int[] num) {
        if(num==null || num.length<2)
            return 0;
        int numMax = num[0], numMin = num[0];
        for(int i=1; i<num.length; i++) {
            numMax = Math.max(numMax, num[i]);  //求最大值最小值
            numMin = Math.min(numMin, num[i]);
        }

        int gap = (numMax-numMin-1)/(num.length-1)+1;   //len = ceiling[(B-A)/(N-1)] 不能用Math.ceil() 因为他返回double
        int bucketMin[] = new int[num.length];      //维护一个记录每个bucket最小值的数组和一个记录每个bucket最大的数组
        int bucketMax[] = new int[num.length];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);  //将两个数组分别填入最大整数和最小整数 后面判断用
        Arrays.fill(bucketMax, Integer.MIN_VALUE);
        for(int i=0; i<num.length; i++) {       //循环一遍将每个元素放入对应bucket中
            int index = (num[i]-numMin)/gap;
            bucketMin[index] = Math.min(bucketMin[index], num[i]);  //更新bucketMin和bucketMax if necessary
            bucketMax[index] = Math.max(bucketMax[index], num[i]);
        }

        int maxGap = Integer.MIN_VALUE; //因为同一个bucket中元素最大差gap－1 而已知最大gap不小于gap 所以结果不可能在同一个bucket中
        int previous = numMin;          //又因为是连续数字最大差 解只可能是某个bucket的最大值和它下一个bucket最小值的差值
        for(int i=0; i<num.length; i++) {   //扫一遍数组
            if(bucketMin[i]==Integer.MAX_VALUE && bucketMax[i]==Integer.MIN_VALUE)  //排除空的bucket
                continue;
            maxGap = Math.max(maxGap, bucketMin[i]-previous);   //依次更新maxGap去当前bucket最小值－上一个bucket最大值
            previous = bucketMax[i];
        }
        return maxGap;
    }
}

O(n) O(n)

假设有N个元素A到B。

那么最大差值不会小于ceiling[(B - A) / (N - 1)] (最小也要等于 即都是平均分配的情况下 ex 1 3 5 抽屉原理)

1 令bucket（桶）的大小len = ceiling[(B - A) / (N - 1)]，则最多会有(B - A) / len + 1个桶

2 对于数组中的任意整数K，很容易通过算式loc = (K - A) / len找出其桶的位置，然后维护每一个桶的最大值和最小值

由于同一个桶内的元素之间的差值至多为len - 1，因此最终答案不会从同一个桶中选择。 (最大差值最小也要等于len 即桶的大小)

3 对于每一个非空的桶p，找出下一个非空的桶q，则q.min - p.max可能就是备选答案。返回所有这些可能值中的最大值。




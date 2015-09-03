public class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length;
        int[] arr = new int[len+1];
        for(int i=0; i<len; i++) {
            if(citations[i]>len)    //超出数组范围的值 累加到最后元素
                arr[len]++;
            else                    //没超出范围的值 对应元素加1
                arr[citations[i]]++;
        }
        int res = 0;
        for(int i=arr.length-1; i>=0; i--) {
            res+=arr[i];
            if(res>=i)
                return i;
        }
        return 0;
    }
}

O(n) O(n)
利用计数排序原理 H-index只可能在0到n之间 

https://leetcode.com/discuss/55952/my-o-n-time-solution-use-java
https://leetcode.com/discuss/55944/share-my-o-n-solution-with-explaination





传统解法

public class Solution {
    public int hIndex(int[] citations) {
        if(citations==null || citations.length==0)
            return 0;
        Arrays.sort(citations);
        int res = 0;
        for(int i=citations.length-1; i>=0; i--) {
            if(citations[i]>=citations.length-i)
                res = citations.length-i;
        }
        return res;
    }
}

O(nlogn) O(n)
https://en.wikipedia.org/wiki/H-index

public class Solution {
    public int hIndex(int[] citations) {
        if(citations==null || citations.length==0)
            return 0;
        Arrays.sort(citations, Collections.reverseOrder());
        int res = 0;
        for(int i=0; i<citations.length; i++) {
            if(citations[i]>=i+1)
                res = i+1;
        }
        return res;
    }
}
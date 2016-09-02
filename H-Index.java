Note: 这道题不可能有 多个h-index 也就是只可能存在唯一的h-index
https://leetcode.com/discuss/56001/is-it-possible-to-have-two-this-problem-please-give-an-example

citations = [3, 0, 6, 1, 5]

public class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length;
        int[] res = new int[len+1];
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] > len) res[len]++;
            else res[citations[i]]++;
        }
        int hIndex = 0;
        for (int i = len; i >= 0; i--) {
            hIndex += res[i];
            if (hIndex >= i) return i;
        }
        return 0;
    }
}

O(n) O(n)
bucket sort

https://discuss.leetcode.com/topic/40765/java-bucket-sort-o-n-solution-with-detail-explanation
https://leetcode.com/discuss/55952/my-o-n-time-solution-use-java
https://leetcode.com/discuss/55944/share-my-o-n-solution-with-explaination



Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, 

and the other N − h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had 

received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining 

two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.




method 2: first sort then use H-index II

public class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int left = 0, right = citations.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (citations[mid] == citations.length - mid) return citations[mid];
            else if (citations[mid] > citations.length - mid) right = mid - 1;
            else left = mid + 1;
        }
        return citations.length - left;
    }
}

O(nlogn) O(n)

https://en.wikipedia.org/wiki/H-index

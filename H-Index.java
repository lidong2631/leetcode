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



method 2: first sort then use H-index II



public class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int res = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations.length - i <= citations[i]) res = citations.length - i;
            else break;
        }
        return res;
    }
}

O(nlogn) O(n)

https://en.wikipedia.org/wiki/H-index

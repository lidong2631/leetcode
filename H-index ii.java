public class Solution {
    public int hIndex(int[] citations) {
        int left = 0, right = citations.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (citations[mid] == citations.length - mid) return citations.length - mid;
            else if (citations[mid] > citations.length - mid) right = mid - 1;
            else left = mid + 1;
        }
        return citations.length - left;
    }
}

The idea is to search for the first index from the sorted array so that :

citations[index] >= length(citations) - index. 

And return (length - index) as the result.


Just binary search, each time check citations[mid]
case 1: citations[mid] == len-mid, then it means there are citations[mid] papers that have at least citations[mid] citations.
case 2: citations[mid] > len-mid, then it means there are citations[mid] papers that have moret than citations[mid] citations, so we should continue searching in the left half
case 3: citations[mid] < len-mid, we should continue searching in the right side

https://discuss.leetcode.com/topic/23399/standard-binary-search
https://discuss.leetcode.com/topic/23424/java-binary-search-simple-and-clean

O(logn) O(1)
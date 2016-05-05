public class Solution {
    public int hIndex(int[] citations) {
        if(citations==null || citations.length==0)
            return 0;
        int left = 0, right = citations.length-1;
        while(left<=right) {
            int mid = (left+right)/2;
            if(citations[mid]==citations.length-mid)
                return citations.length-mid;
            else if(citations[mid]<citations.length-mid)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return citations.length-left;
    }
}

0 1 3 5 6
0 1 2 5 6
0 1 4 5 6

need to understand why return citations.length - left

follow up for H-index. The citations array is pre-ordered in ascending order. here we use Binary Search
O(logn) O(1)
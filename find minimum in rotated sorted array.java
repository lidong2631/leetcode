from cleanCode

public class Solution {
    public int findMin(int[] num) {
        int left = 0, right = num.length - 1;
        while (left < right && num[left] >= num[right]) {   // careful
            int mid = (left + right) / 2;
            if (num[mid] > num[right]) left = mid + 1;
            else right = mid;
        }
        return num[left];
    }
}

看cleanCode 说的很清楚 当A[mid]<A[right] 最小值在A[left]到A[mid]之间 当A[mid]>A[right] 最小值在A[mid+1]到A[right]之间

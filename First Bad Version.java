https://leetcode.com/discuss/56582/whats-the-difference-between-left-right-and-left-right-left

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) right = mid;
            else left = mid + 1;
        }
        return left;    // return either left or right is fine
    }
}

O(logn) O(1)

注意int mid = left+(right-left)/2; 如果写成(left+right)/2可能会overflow超过2147483647
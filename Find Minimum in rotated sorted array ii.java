public class Solution {
    public int findMin(int[] num) {
        int left = 0, right = num.length-1;
        while (left < right && num[left] >= num[right]) {       //careful
            int mid = (left + right) / 2;
            if (num[mid] > num[right])
                left = mid + 1;
            else if(num[mid] < num[right])
                right = mid;
            else
                left++;
        }
        return num[left];
    }
}
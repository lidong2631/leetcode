public class Solution {
    public String reverseStr(String s, int k) {
        if (s == null || s.length() == 0)
            return s;
        char[] arr = s.toCharArray();
        if (arr.length <= k) {
            reverse(arr, 0, arr.length-1);
            return new String(arr);
        }
        int left = 0, right = k * 2;
        while (left + k - 1 < arr.length || left < arr.length) {
            if (left + k - 1 > arr.length) {
                reverse(arr, left, arr.length-1);
                break;
            }
            reverse(arr, left, left+k-1);
            left = right;
            right += 2 * k;
        }
        return new String(arr);
    }
    
    public void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++;
            right--;
        }
    }
}

O(n)


Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"
Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]
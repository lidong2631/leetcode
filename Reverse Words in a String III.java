public class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0)
            return s;
        int left = 0, right = 0;
        char[] arr = s.toCharArray();
        while (right < arr.length) {
            while (right < arr.length && arr[right] != ' ')
                right++;
            int next = right + 1;
            reverse(arr, left, right-1);
            left = next;
            right = next;
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


Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.
Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".



Java:
public class Solution {
    public String reverseString(String s) {
        char[] arr = s.toCharArray();
        int left = 0, right = s.length()-1;
        while (left < right) {
            char tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++; right--;
        }
        return String.valueOf(arr);
    }
}

O(n)
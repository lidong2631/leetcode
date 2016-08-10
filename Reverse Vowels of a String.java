public class Solution {
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0)
            return s;
            
        Set<Character> set = new HashSet<Character>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        
        int left = 0, right = s.length() - 1;
        char[] arr = s.toCharArray();
        while (true) {
            while (left < right && !set.contains(Character.toLowerCase(arr[left])))
                left++;
            while (left < right && !set.contains(Character.toLowerCase(arr[right])))
                right--;
            if (left >= right)
                break;
            swap(arr, left, right);
            left++; right--;
        }
        return String.valueOf(arr);
    }
    
    private void swap(char[] arr, int left, int right) {
        char tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }
}

Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".


O(n)
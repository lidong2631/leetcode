public class Solution {
    public void reverseWords(char[] s) {
        if(s==null || s.length==0)
            return;
        int left = 0;
        int right = s.length-1;
        while(left<right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
        left = 0; right = 0;
        while(right<s.length) {    
            while(right<s.length && s[right]!=' ') {
                right++;
            }
            int next = right+1;
            right--;
            while(left<right) {
                char tmp = s[left];
                s[left] = s[right];
                s[right] = tmp;
                left++;
                right--;
            }
            left = next; right = next;
        }
    }
}

同rotate array及reverse string i第二种解法 先全部反转再局部反转 时间O(n) 空间 O(1)
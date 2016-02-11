public class Solution {
    public void reverseWords(char[] s) {
        if(s==null || s.length==0)
            return;
        int left = 0;
        int right = s.length-1;
        reverse(s, left, right);
        left = 0; right = 0;
        while(right<s.length) {
            while(right<s.length && s[right]!=' ')
                right++;
            int next = right+1;
            right--;
            reverse(s, left, right);
            left = next; right = next;
        }
    }
    
    private void reverse(char[] charArr, int left, int right) {
        while(left<right) {
            char tmp = charArr[left];
            charArr[left] = charArr[right];
            charArr[right] = tmp;
            left++;
            right--;
        }
    }
}

同rotate array及reverse string i第二种解法 先全部反转再局部反转 时间O(n) 空间 O(1)

看一下cleanCode challenge 1 2
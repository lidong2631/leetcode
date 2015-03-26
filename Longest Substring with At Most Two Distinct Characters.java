From cleanCode

public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int i = 0, j = -1, maxLen = 0;      //i为左窗口  j为窗口中前一个distinct字符
        for(int k=1; k<s.length(); k++) {   //k为右窗口 同时也是窗口中后一个distinct字符
            if(s.charAt(k)==s.charAt(k-1))  //每步都先判断k和它前一个字符是否相同
                continue;
            if(j>=0 && s.charAt(j)!=s.charAt(k)) {  //如果k和j的字符不同 同时上一步判断k和它前一个字符也不同 说明是一个新的distinct字符
                maxLen = Math.max(maxLen, k-i);     //更新maxLen 左窗口i跳到j的下一个字符 此时窗口中仍维护2个不同字符 k指向的一个 i指向(即原先k指向的)一个
                i = j + 1;
            }
            j = k - 1;  //每次循环如果k和它前一个字符不等 就更新j为k前一个字符 否则直接continue
        }
        maxLen = Math.max(maxLen, s.length()-i);    //最后循环结束再比较一下maxLen的值
        return maxLen;
    }
}

这种解法只能处理2个不同字符的情况 利用了只能有2个不同字符的特性 通过比较相邻字符和j k所指字符是否相等就可以判断是否有多于2个的不同字符

时间O(n) 空间O(n)




public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int i = 0, maxLen = 0;
        int[] count = new int[256];     //记录字符出现了几次
        int numDistinct = 0;
        for(int j=0; j<s.length(); j++) {
            if(count[s.charAt(j)]==0)   //如果count中对应值为0 说明是新字符
                numDistinct++;
            count[s.charAt(j)]++;
            while(numDistinct>2) {  //只要numDistinct大于2 移动左窗口直到窗口中只有2个distinct字符
                count[s.charAt(i)]--;
                if(count[s.charAt(i)]==0)
                    numDistinct--;
                i++;
            }
            maxLen = Math.max(maxLen, j-i+1);
        }
        return maxLen;
    }
}

这种解法可以扩展为求最多允许k个不同字符的最大子串长度 只要改numDistinct即可 思路跟我的解法差不多 只是更加简洁 要多学习

时间O(2*n) 空间O(n)




public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s==null || s.length()==0)
            return 0;
        int maxLen = 0;
        int left = 0; int right = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int count = 0;
        while(right<s.length()) {
            while(count<3 && right<s.length()) {    //只要没有3个不同字符出现有窗口一直走
                maxLen = Math.max(maxLen, right-left);  //更新maxLen if possible
                if(map.containsKey(s.charAt(right)))    //如果当前字符已经在字典里 更新字典值
                    map.put(s.charAt(right), map.get(s.charAt(right))+1);
                else {
                    map.put(s.charAt(right), 1);    //如果是新的字符 新建一个键在字典 count++
                    count++;
                }
                right++;
            }
            while(count>2) {    //当count大于2时 移动左窗口 每次将对应字符字典里的值-1 如果某个字符字典值变为0说明它被去除
                map.put(s.charAt(left), map.get(s.charAt(left))-1); //掉了 可以把count--
                if(map.get(s.charAt(left))==0) {
                    count--;
                    map.remove(s.charAt(left));
                }
                left++;
            }
        }
        maxLen = Math.max(maxLen, right-left);  //最后再比较一下maxLen
        return maxLen;
    }
}

时间O(n) 空间O(n)
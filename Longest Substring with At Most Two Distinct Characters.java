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
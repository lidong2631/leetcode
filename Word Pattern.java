public class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] strArr = str.split(" ");
        if(strArr.length!=pattern.length())
            return false;
        Map map = new HashMap();
        for(int i=0; i<strArr.length; i++) {
            if(!Objects.equals(map.put(strArr[i], i),
                                map.put(pattern.charAt(i), i)))
                return false;
        }
        return true;
    }
}

牛逼解法 利用map.put(k,v) 返回上一次的value

https://leetcode.com/discuss/62374/9-lines-simple-java





public class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] arr= str.split(" ");
        HashMap<Character, String> map = new HashMap<Character, String>();
        if(arr.length!= pattern.length())
            return false;
        for(int i=0; i<arr.length; i++){
            char c = pattern.charAt(i);
            if(map.containsKey(c)){
                if(!map.get(c).equals(arr[i]))
                    return false;
            }else{
                if(map.containsValue(arr[i]))
                    return false;
                map.put(c, arr[i]);
            }    
        }
        return true;
    }
}
note map.containsValue(arr[i]) is O(n)

https://leetcode.com/discuss/62876/very-fast-3ms-java-solution-using-hashmap






public class Solution {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> map1 = new HashMap<Character, String>();
        Map<String, Character> map2 = new HashMap<String, Character>();
        int index = 0;
        for(Character c : pattern.toCharArray()) {
            StringBuilder sb = new StringBuilder();
            while(index<str.length() && str.charAt(index)!=' ') {
                sb.append(str.charAt(index++));
            }
            index++;
            if(sb.length()==0)
                return false;
            String tmp = sb.toString();
            if(map1.containsKey(c)) {
                if(!map1.get(c).equals(tmp))
                    return false;
            }
            if(map2.containsKey(tmp)) {
                if(map2.get(tmp)!=c)
                    return false;
            }
            map1.put(c, tmp);
            map2.put(tmp, c);
        }
        return (index-1)==str.length();
    }
}

跟isomorphic strings 思路一样 但要注意字符串比较用equals 并且要判断长度是否match

O(n) O(n)
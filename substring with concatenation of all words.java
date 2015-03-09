public class Solution {
    public List<Integer> findSubstring(String S, String[] L) {
        if(S==null || L==null || S.length()==0 || L.length==0 || L[0].length()==0)
            return null;
        List<Integer> res = new ArrayList<Integer>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(int i=0; i<L.length; i++)
        {
            if(map.containsKey(L[i]))					//map存L中每个词出现次数
                map.put(L[i], map.get(L[i])+1);
            else map.put(L[i], 1);
        }
        for(int i=0; i<L[0].length(); i++)
        {
            int left = i;						 //left记录当前次遍历从哪里开始 相当于左窗口
            int count = 0;					//count记录单词出现次数
            HashMap<String, Integer> currMap = new HashMap<String, Integer>();
            for(int j=i; j<=S.length()-L[0].length(); j+=L[0].length())		//以L[0].length()为长度跳着走 j相当于右窗口
            {
                String tmpStr = S.substring(j, j+L[0].length());			//拿到当前的单词
                if(map.containsKey(tmpStr))											//如果map中有这个词
                {
                    if(currMap.containsKey(tmpStr))							//首先更新当前字典currMap 具体不说了
                        currMap.put(tmpStr, currMap.get(tmpStr)+1);
                    else
                        currMap.put(tmpStr, 1);
                    if(currMap.get(tmpStr)<=map.get(tmpStr))    //如果currMap里这个词出现次数小于等于map中的出现次数 
                        count++;                                //说明还没有重复多余的词出现 count为有效 可以加1
                    else
                    {
                        while(currMap.get(tmpStr)>map.get(tmpStr))		//否则如果大于 则单词出现次数大于了L中词出现次数 需要左移左窗口 
                        {                //每次将对应词在currMap出现次数－1 直到将tmpStr这个词的值减到小于等于map中出现次数 才可以继续右移右窗口
                            String str = S.substring(left, left+L[0].length());
                            currMap.put(str, currMap.get(str)-1);
                            left+=L[0].length();
                        }
                    }
                    if(count==L.length)		//如果count等于L的长度 则找到所有合法相连的单词 将结果加到res中 将左窗口左移一个单词长
                    {                       //从这个位置开始下一轮检索 同时更新currMap将当前单词value－1 继续右移找下一个合法结果
                        res.add(left);
                        String str = S.substring(left, left+L[0].length());
                        currMap.put(str, currMap.get(str)-1);
                        left+=L[0].length();
                        count--;
                    }
                }
                else					//如果map中没有这个词了 清空currMap之前记录 左移左窗口 将count重置0
                {
                    currMap.clear();
                    left = j + L[0].length();
                    count = 0;
                }
            }
        }
        return res;
    }
}

Note: code ganker版改写 还是窗口思想 左移左窗口右移右窗口 感觉是minimum window substring, longest substring without repeating和这题中最难的 细节很多

注意这个解法不对 过不了OJ 会报如下错误 

Input:  "aaabbbc", ["a","a","b","b","c"]
Output: [4]
Expected:   []

错误原因是少了如下代码
if(currMap.containsKey(removeStr)) {
    currMap.put(removeStr, currMap.get(removeStr)-1);
    if(currMap.get(removeStr)<map.get(removeStr))
        count--;
}
也就是对应的count没有－－ 导致其一直是4最后碰到c直接符合条件返回了 下面的代码是正确的 以下面的为准 注意105到110和117到119





public class Solution {
    public List<Integer> findSubstring(String S, String[] L) {
        if(S==null || S.length()==0 || L==null || L.length==0)
            return null;
        List<Integer> res = new ArrayList<Integer>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(int i=0; i<L.length; i++) {
            if(map.containsKey(L[i]))
                map.put(L[i], map.get(L[i])+1);
            else
                map.put(L[i], 1);
        }
        for(int i=0; i<L[0].length(); i++) {
            int left = i; int count = 0;
            HashMap<String, Integer> currMap = new HashMap<String, Integer>();
            for(int j=i; j<=S.length()-L[0].length(); j+=L[0].length()) {
                String tmp = S.substring(j, j+L[0].length());
                if(map.containsKey(tmp)) {
                    if(currMap.containsKey(tmp))
                        currMap.put(tmp, currMap.get(tmp)+1);
                    else
                        currMap.put(tmp, 1);
                    if(currMap.get(tmp)<=map.get(tmp))
                        count++;
                    else {
                        while(currMap.get(tmp)>map.get(tmp)) {
                            String removeStr = S.substring(left, left+L[0].length());
                            if(currMap.containsKey(removeStr)) {
                                currMap.put(removeStr, currMap.get(removeStr)-1);
                                if(currMap.get(removeStr)<map.get(removeStr))
                                    count--;
                            }
                            left+=L[0].length();
                        }
                    }
                    if(count==L.length) {
                        res.add(left);
                        String removeStr = S.substring(left, left+L[0].length());
                        if(currMap.containsKey(removeStr)) {
                            currMap.put(removeStr, currMap.get(removeStr)-1);
                        }
                        left+=L[0].length();
                        count--;
                    }
                }
                else {
                    currMap.clear();
                    left= j + L[0].length();
                    count = 0;
                }
            }
        }
        return res;
    }
}








from code ganker:

这道题看似比较复杂，其实思路和Longest Substring Without Repeating Characters差不多。因为那些单词是定长的，所以本质上和单一个字符一样。

和Longest Substring Without Repeating Characters的区别只在于我们需要维护一个字典，然后保证目前的串包含字典里面的单词有且仅有一次。

思路仍然是维护一个窗口，如果当前单词在字典中，则继续移动窗口右端，否则窗口左端可以跳到字符串下一个单词了。假设源字符串的长度为n，字典中单词的长度为l。

因为不是一个字符，所以我们需要对源字符串所有长度为l的子串进行判断。做法是i从0到l-1个字符开始，得到开始index分别为i, i+l, i+2*l, ...的长度为l的单词。

这样就可以保证判断到所有的满足条件的串。因为每次扫描的时间复杂度是O(2*n/l)(每个单词不会被访问多于两次，一次是窗口右端，一次是窗口左端)，

总共扫描l次（i=0, ..., l-1)，所以总复杂度是O(2*n/l*l)=O(n)，是一个线性算法。空间复杂度是字典的大小，即O(m*l)，其中m是字典的单词数量。代码如下：

public ArrayList<Integer> findSubstring(String S, String[] L) {
    ArrayList<Integer> res = new ArrayList<Integer>();
    if(S==null || S.length()==0 || L==null || L.length==0)
        return res;
    HashMap<String,Integer> map = new HashMap<String,Integer>();
    for(int i=0;i<L.length;i++)
    {
        if(map.containsKey(L[i]))
        {
            map.put(L[i],map.get(L[i])+1);
        }
        else
        {
            map.put(L[i],1);
        }
    }
    for(int i=0;i<L[0].length();i++)
    {
        HashMap<String,Integer> curMap = new HashMap<String,Integer>();
        int count = 0;
        int left = i;
        for(int j=i;j<=S.length()-L[0].length();j+=L[0].length())
        {
            String str = S.substring(j,j+L[0].length());
            if(map.containsKey(str))
            {
                if(curMap.containsKey(str))
                    curMap.put(str,curMap.get(str)+1);
                else
                    curMap.put(str,1);
                if(curMap.get(str)<=map.get(str))
                    count++;
                else
                {
                    while(curMap.get(str)>map.get(str))
                    {
                        String temp = S.substring(left,left+L[0].length());
                        curMap.put(temp,curMap.get(temp)-1);
                        left += L[0].length();
                    }
                }
                if(count == L.length)
                {
                    res.add(left);
                    String temp = S.substring(left,left+L[0].length());
                    curMap.put(temp,curMap.get(temp)-1);
                    count--;
                    left += L[0].length();
                }
            }
            else
            {
                curMap.clear();
                count = 0;
                left = j+L[0].length();
            }
        }
    }
    return res;
}

这种移动窗口的方法在字符串处理的问题中非常常见，是一种可以把时间复杂度降低到线性的有效算法，大家可以熟悉一下。还有非常类似的题目Minimum Window Substring，

思路完全一样，只是移动窗口的规则稍微不同而已。
<<<<<<< HEAD
class Solution:
    # @param strs, a list of strings
    # @return a list of strings
    def anagrams(self, strs):
        dict = {}
        for word in strs:                   #建立一个哈希表 以strs中每一个词排好序后的字符串为索引 value为其所有变位词
            sortedWord = ''.join(sorted(word))
            dict[sortedWord] = [word] if sortedWord not in dict else dict[sortedWord]+[word]    #第一次出现就建立该映射关系 否则将它加到已有的value里 注意这里word是放在[]里 代表它是个list 如dict = {'abc': ['bac', 'cba']} 还有sorted（）函数
        res = []
        for item in dict:
            if len(dict[item]) >= 2:    #如果dict[item]有多余一个值 则说明item有变位词 需要加到res list中
                res += dict[item]
        return res

Note: 这题也很经典要熟记 这题有几个相关问题 如判断两个词是不是变位词 列举一个词所有变位词 要好好掌握

python 中可以用sorted()给字符串排序 但返回的是一个list 如果要获得字符串要用join（）

>>> a = 'ZENOVW'
>>> b = sorted(a)
>>> print b
['E', 'N', 'O', 'V', 'W', 'Z']sorted returns a list, so you can make it a string again using join:

>>> c = ''.join(b)which joins the items of b together with an empty string '' in between each item.

>>> print c
'ENOVWZ'





题意：

Given an array of strings, return all groups of strings that are anagrams.

Note: All inputs will be in lower-case.

解题思路：anagram的意思是：abc，bac，acb就是anagram。即同一段字符串的字母的不同排序。将这些都找出来。这里使用了哈希表，即Python中的dict。针对前面的例子来讲，映射为{abc：abc，bac，acb}。

代码：


class Solution:
    # @param strs, a list of strings
    # @return a list of strings
    def anagrams(self, strs):
        dict = {}
        for word in strs:
            sortedword = ''.join(sorted(word))
            dict[sortedword] = [word] if sortedword not in dict else dict[sortedword] + [word]
        res = []
        for item in dict:
            if len(dict[item]) >= 2:
                res += dict[item]
        return res






public class Solution {
    public List<String> anagrams(String[] strs) {
        List<String> res = new ArrayList<String>();
        if(strs==null || strs.length==0)
            return res;
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for(int i=0; i<strs.length; i++) {
            char[] str = strs[i].toCharArray();
            Arrays.sort(str);
            String tmpStr = String.valueOf(str);
            if(!map.containsKey(tmpStr)) {
                List<String> list = new ArrayList<String>();
                list.add(strs[i]);
                map.put(tmpStr, list);
            }
            else {
                map.get(tmpStr).add(strs[i]);
            }
        }
        for(List<String> l : map.values()) {    //这里我和code ganker写法不一样 要复习遍历map的几种方式
            if(l.size()>1)
                res.addAll(l);
        }
        return res;
    }
}

第二遍写的 思路就是每读一个string先排序 然后在hashmap里看有没有这个纪录 如果有把这个string放进去 否则建一个新的键值对放它

这样走一遍后再扫一遍hashmap将值有2个或以上的string加入最终结果即可 该算法的时间复杂度是O(nklogk)，其中O(klogk)是对每一个字符串排序（如果用线性算法也可以提高）。

空间复杂度则是O(nk)，即hashmap的大小








public class Solution {
    public List<String> anagrams(String[] strs) {
        List<String> res = new ArrayList<String>();
        if(strs==null || strs.length==0)
            return res;
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for(int i=0; i<strs.length; i++)
        {
            char[] charStr = strs[i].toCharArray();     //这里将string转成char Array排序后再转回
            Arrays.sort(charStr);
            String str = new String(charStr);
            if(map.containsKey(str))
            {
                map.get(str).add(strs[i]);      //如果已经有映射关系 直接在对应list里面加上这个string就好了
            }
            else
            {
                List<String> item = new ArrayList<String>();
                item.add(strs[i]);
                map.put(str, item);             //否则建立映射关系 map键是string value是anagram是这个key string的字符串组
            }
        }
        Iterator iter = map.values().iterator();            //迭代map的values
        while(iter.hasNext())
        {
            List<String> anagrams = (List<String>)iter.next();      //这里要转成list类型
            if(anagrams.size()>1)
                res.addAll(anagrams);           //
        }
        return res;
    }
}

Note: 根据code ganker改写 面试常见题 要熟记 相关的一些问题也都要很明白 细节上多注意注释的地方




from code ganker:

这是一道很经典的面试题了，在cc150里面也有，就是把一个数组按照易位构词分类。易位构词其实也很好理解，就是两个单词所包含的字符和数量都是一样的，

只是顺序不同。

这个题简单的版本是判断两个单词是不是anagram，一般来说有两种方法。第一种方法是用hashmap，key是字符，value是出现的次数，

如果两个单词构成的hashmap相同，那么就是anagram。实现起来就是用一个构建hashmap，然后另一个在前面的hashmap中逐个去除，最后如果hashmap为空，

即返回true。这个方法时间复杂度是O(m+n)，m，n分别是两个单词的长度。而空间复杂度是O(字符集的大小)。第二种方法是将两个单词排序，如果排序之后结果相同，

就说明两个单词是anagram。这种方法的时间复杂度取决于排序算法，一般排序算法是O(nlogn)，如果字符集够小，也可以用线性的排序算法。

不过总体来说，如果是判断两个单词的，第一种方法要直接简单一些。

接下来我们看看这道题，是在很多字符串里面按照anagram分类，如果用hashmap的方法，然后两两匹配，在分组会比较麻烦。而如果用排序的方法则有一个很大的优势，

就是排序后的字符串可以作为一个key，也就是某一个class的id，如此只要对每一个字符串排序，然后建立一个hashmap，key是排序后的串，

而value是所有属于这个key类的字符串，这样就可以比较简单的进行分类。假设我们有n个字符串，字符串最大长度是k，那么该算法的时间复杂度是O(nklogk)，

其中O(klogk)是对每一个字符串排序（如果用线性算法也可以提高）。空间复杂度则是O(nk)，即hashmap的大小。实现代码如下：

public ArrayList<String> anagrams(String[] strs) {
    ArrayList<String> res = new ArrayList<String>();
    if(strs == null || strs.length == 0)
        return res;
    HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    for(int i=0;i<strs.length;i++)
    {
        char[] charArr = strs[i].toCharArray();
        Arrays.sort(charArr);
        String str = new String(charArr);
        if(map.containsKey(str))
        {
            map.get(str).add(strs[i]);
        }
        else
        {
            ArrayList<String> list = new ArrayList<String>();
            list.add(strs[i]);
            map.put(str,list);
        }
    }
    Iterator iter = map.values().iterator();
    while(iter.hasNext())
    {
        ArrayList<String> item = (ArrayList<String>)iter.next();
        if(item.size()>1)
            res.addAll(item);
    }
    return res;
}

=======
class Solution:
    # @param strs, a list of strings
    # @return a list of strings
    def anagrams(self, strs):
        dict = {}
        for word in strs:                   #建立一个哈希表 以strs中每一个词排好序后的字符串为索引 value为其所有变位词
            sortedWord = ''.join(sorted(word))
            dict[sortedWord] = [word] if sortedWord not in dict else dict[sortedWord]+[word]    #第一次出现就建立该映射关系 否则将它加到已有的value里 注意这里word是放在[]里 代表它是个list 如dict = {'abc': ['bac', 'cba']} 还有sorted（）函数
        res = []
        for item in dict:
            if len(dict[item]) >= 2:    #如果dict[item]有多余一个值 则说明item有变位词 需要加到res list中
                res += dict[item]
        return res

Note: 这题也很经典要熟记 这题有几个相关问题 如判断两个词是不是变位词 列举一个词所有变位词 要好好掌握

python 中可以用sorted()给字符串排序 但返回的是一个list 如果要获得字符串要用join（）

>>> a = 'ZENOVW'
>>> b = sorted(a)
>>> print b
['E', 'N', 'O', 'V', 'W', 'Z']sorted returns a list, so you can make it a string again using join:

>>> c = ''.join(b)which joins the items of b together with an empty string '' in between each item.

>>> print c
'ENOVWZ'





题意：

Given an array of strings, return all groups of strings that are anagrams.

Note: All inputs will be in lower-case.

解题思路：anagram的意思是：abc，bac，acb就是anagram。即同一段字符串的字母的不同排序。将这些都找出来。这里使用了哈希表，即Python中的dict。针对前面的例子来讲，映射为{abc：abc，bac，acb}。

代码：


class Solution:
    # @param strs, a list of strings
    # @return a list of strings
    def anagrams(self, strs):
        dict = {}
        for word in strs:
            sortedword = ''.join(sorted(word))
            dict[sortedword] = [word] if sortedword not in dict else dict[sortedword] + [word]
        res = []
        for item in dict:
            if len(dict[item]) >= 2:
                res += dict[item]
        return res






public class Solution {
    public List<String> anagrams(String[] strs) {
        List<String> res = new ArrayList<String>();
        if(strs==null || strs.length==0)
            return res;
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for(int i=0; i<strs.length; i++) {
            char[] str = strs[i].toCharArray();
            Arrays.sort(str);
            String tmpStr = String.valueOf(str);
            if(!map.containsKey(tmpStr)) {
                List<String> list = new ArrayList<String>();
                list.add(strs[i]);
                map.put(tmpStr, list);
            }
            else {
                map.get(tmpStr).add(strs[i]);
            }
        }
        for(List<String> l : map.values()) {    //这里我和code ganker写法不一样 要复习遍历map的几种方式
            if(l.size()>1)
                res.addAll(l);
        }
        return res;
    }
}

第二遍写的 思路就是每读一个string先排序 然后在hashmap里看有没有这个纪录 如果有把这个string放进去 否则建一个新的键值对放它

这样走一遍后再扫一遍hashmap将值有2个或以上的string加入最终结果即可 该算法的时间复杂度是O(nklogk)，其中O(klogk)是对每一个字符串排序（如果用线性算法也可以提高）。

空间复杂度则是O(nk)，即hashmap的大小








public class Solution {
    public List<String> anagrams(String[] strs) {
        List<String> res = new ArrayList<String>();
        if(strs==null || strs.length==0)
            return res;
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for(int i=0; i<strs.length; i++)
        {
            char[] charStr = strs[i].toCharArray();     //这里将string转成char Array排序后再转回
            Arrays.sort(charStr);
            String str = new String(charStr);
            if(map.containsKey(str))
            {
                map.get(str).add(strs[i]);      //如果已经有映射关系 直接在对应list里面加上这个string就好了
            }
            else
            {
                List<String> item = new ArrayList<String>();
                item.add(strs[i]);
                map.put(str, item);             //否则建立映射关系 map键是string value是anagram是这个key string的字符串组
            }
        }
        Iterator iter = map.values().iterator();            //迭代map的values
        while(iter.hasNext())
        {
            List<String> anagrams = (List<String>)iter.next();      //这里要转成list类型
            if(anagrams.size()>1)
                res.addAll(anagrams);           //
        }
        return res;
    }
}

Note: 根据code ganker改写 面试常见题 要熟记 相关的一些问题也都要很明白 细节上多注意注释的地方




from code ganker:

这是一道很经典的面试题了，在cc150里面也有，就是把一个数组按照易位构词分类。易位构词其实也很好理解，就是两个单词所包含的字符和数量都是一样的，

只是顺序不同。

这个题简单的版本是判断两个单词是不是anagram，一般来说有两种方法。第一种方法是用hashmap，key是字符，value是出现的次数，

如果两个单词构成的hashmap相同，那么就是anagram。实现起来就是用一个构建hashmap，然后另一个在前面的hashmap中逐个去除，最后如果hashmap为空，

即返回true。这个方法时间复杂度是O(m+n)，m，n分别是两个单词的长度。而空间复杂度是O(字符集的大小)。第二种方法是将两个单词排序，如果排序之后结果相同，

就说明两个单词是anagram。这种方法的时间复杂度取决于排序算法，一般排序算法是O(nlogn)，如果字符集够小，也可以用线性的排序算法。

不过总体来说，如果是判断两个单词的，第一种方法要直接简单一些。

接下来我们看看这道题，是在很多字符串里面按照anagram分类，如果用hashmap的方法，然后两两匹配，在分组会比较麻烦。而如果用排序的方法则有一个很大的优势，

就是排序后的字符串可以作为一个key，也就是某一个class的id，如此只要对每一个字符串排序，然后建立一个hashmap，key是排序后的串，

而value是所有属于这个key类的字符串，这样就可以比较简单的进行分类。假设我们有n个字符串，字符串最大长度是k，那么该算法的时间复杂度是O(nklogk)，

其中O(klogk)是对每一个字符串排序（如果用线性算法也可以提高）。空间复杂度则是O(nk)，即hashmap的大小。实现代码如下：

public ArrayList<String> anagrams(String[] strs) {
    ArrayList<String> res = new ArrayList<String>();
    if(strs == null || strs.length == 0)
        return res;
    HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    for(int i=0;i<strs.length;i++)
    {
        char[] charArr = strs[i].toCharArray();
        Arrays.sort(charArr);
        String str = new String(charArr);
        if(map.containsKey(str))
        {
            map.get(str).add(strs[i]);
        }
        else
        {
            ArrayList<String> list = new ArrayList<String>();
            list.add(strs[i]);
            map.put(str,list);
        }
    }
    Iterator iter = map.values().iterator();
    while(iter.hasNext())
    {
        ArrayList<String> item = (ArrayList<String>)iter.next();
        if(item.size()>1)
            res.addAll(item);
    }
    return res;
}

>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
理清了思路，实现起来还是比较简单的，这道题考察排序，hashmap，字符串处理，比较全面，在面试中非常常见，大家一定要熟悉哈
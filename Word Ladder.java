public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
        if(start==null || end==null || start.length()==0 || end.length()==0 || start.length()!=end.length())
            return 0;
        LinkedList<String> queue = new LinkedList<String>();    //这里换成List<String> queue = new LinkedList<String>()就不行不知道为什么
        HashSet<String> visited = new HashSet<String>();    //记录访问过的节点
        int level = 1;      //第一个词
        int lastNum = 1;    //记录队列中现有元素个数？
        int currNum = 0;    //记录当前循环队列中新增加元素个数？
        queue.offer(start);
        visited.add(start);
        while(!queue.isEmpty()) {   //dfs
            String curr = queue.poll(); //弹出一个元素开始
            lastNum--;
            for(int i=0; i<curr.length(); i++) {
                char[] charCurr = curr.toCharArray();   //对于curr字符串的每个字符分别尝试替换成a到z中的字符看是否有结果或在字典中
                for(char c='a'; c<='z'; c++) {
                    charCurr[i] = c;    //替换掉一个字符
                    String tmp = new String(charCurr);
                    if(tmp.equals(end))     //如果匹配了end 返回结果
                        return level+1;
                    if(dict.contains(tmp) && !visited.contains(tmp)) {  //如果在字典中且没被访问过
                        currNum++;                                  //新加入一个节点 currNum加1 (当前找到字典匹配值)
                        queue.offer(tmp);                           //加到队列中
                        visited.add(tmp);                           //标记已访问
                    }
                }
            }
            if(lastNum==0) {    //如果lastNum为0 说明这一层的节点都遍历完 要去下一层
                lastNum = currNum;  //将这层新加入的元素数赋值给lastNum
                curNumm = 0;        //currNum重设为0
                level++;        //level加1说明进入图的下一层
            }
        }
        return 0;   //如果没找到结果 返回0
    }
}




from code ganker:

这道题看似一个关于字符串操作的题目，其实要解决这个问题得用图的方法。我们先给题目进行图的映射，顶点则是每个字符串，

然后两个字符串如果相差一个字符则我们进行连边。接下来看看这个方法的优势，注意到我们的字符集只有小写字母，而且字符串长度固定，假设是L。

那么可以注意到每一个字符可以对应的边则有25个（26个小写字母减去自己），那么一个字符串可能存在的边是25*L条。接下来就是检测这些边对应的字符串是否在字典里，

就可以得到一个完整的图的结构了。根据题目的要求，等价于求这个图一个顶点到另一个顶点的最短路径，

一般我们用广度优先搜索（不熟悉搜索的朋友可以看看Clone Graph）即可。这个算法中最坏情况是把所有长度为L的字符串都看一下，或者把字典中的字符串都看一下，

而长度为L的字符串总共有26^L，所以时间复杂度是O(min(26^L, size(dict))，空间上需要存储访问情况，也是O(min(26^L, size(dict))。代码如下：

public int ladderLength(String start, String end, HashSet<String> dict) {
    if(start==null || end==null || start.length()==0 || end.length()==0 || start.length()!=end.length())
        return 0;
    LinkedList<String> queue = new LinkedList<String>();
    HashSet<String> visited = new HashSet<String>();
    int level= 1;
    int lastNum = 1;
    int curNum = 0;
    queue.offer(start);
    visited.add(start);
    while(!queue.isEmpty())
    {
        String cur = queue.poll();
        lastNum--;
        for(int i=0;i<cur.length();i++)
        {
            char[] charCur = cur.toCharArray();
            for(char c='a';c<='z';c++)
            {
                charCur[i] = c;
                String temp = new String(charCur);
                if(temp.equals(end))
                    return level+1;
                if(dict.contains(temp) && !visited.contains(temp))
                {
                    curNum++;
                    queue.offer(temp);
                    visited.add(temp);
                }
            }
        }
        if(lastNum==0)
        {
            lastNum = curNum;
            curNum = 0;
            level++;
        }
    }
    return 0;
}

可以看出代码框架其实就是广度优先搜索的基本代码，就是判断边的时候需要换字符和查字典的操作，对于这些图的搜索等基本算法，还是要熟悉哈
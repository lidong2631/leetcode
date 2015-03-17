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
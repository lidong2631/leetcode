public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
        if(start==null || end==null || start.length()==0 || end.length()==0 || start.length()!=end.length())
            return 0;
        LinkedList<String> queue = new LinkedList<String>();
        HashSet<String> visited = new HashSet<String>();
        int level = 1;      //第一次变换
        int lastNum = 1;    //记录队列中现有元素个数？
        int currNum = 0;    //记录当前循环队列中新增加元素个数？
        queue.offer(start);
        visited.add(start);
        while(!queue.isEmpty()) {   //dfs
            String curr = queue.poll(); //弹出一个元素开始
            lastNum--;
            for(int i=0; i<curr.length(); i++) {
                char[] charCurr = curr.toCharArray();   //对于curr字符串的每个字符分别尝试替换成a到z中的字符是否有结果或在字典中
                for(char c='a'; c<='z'; c++) {
                    charCurr[i] = c;    //替换掉一个字符
                    String tmp = new String(charCurr);
                    if(tmp.equals(end))     //如果匹配了end 返回结果
                        return level+1;
                    if(dict.contains(tmp) && !visited.contains(tmp)) {  //如果在字典中且没被访问过
                        currNum++;                                  //当前找到字典匹配值加1
                        queue.offer(tmp);                           //加到队列中
                        visited.add(tmp);                           //标记已访问
                    }
                }
            }
            if(lastNum==0) {    //如果lastNum为0 则更新之 并将level加1表示又匹配完一个字符
                lastNum = currNum;
                curNumm = 0;
                level++;
            }
        }
    }
}
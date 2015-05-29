Leetcode Breath-First Breath
将每个字符串看成图的一个节点 边连接两个相差一个字符的字符串 等价于求图一个顶点到另一个顶点的最短路径 用bfs
if(start==null || start.length()==0 || end==null || end.length==0 || start.length()==end.length())
    return 0;
Queue<String> queue = new LinkedList<String>();
Set<String> set = new HashSet<String>();
queue.add(start);
set.add(start);
int curr = 1, next = 0, level = 1;
while(!queue.isEmpty()) {
    String curr = queue.poll();
    curr--;
    for(int i=0; i<curr.length(); i++) {
        char[] charStr = curr.toCharArray();
        for(char c='a'; c<='z'; c++) {
            charStr[i] = c;
            String tmp = new String(charStr);
            if(tmp.equals(end))
                return level+1;
            if(dict.contains(tmp) && !set.contains(tmp)) {
                set.add(tmp);
                queue.add(tmp);
                next++;
            }
        }
    }
    if(curr==0) {
        curr = next;
        next = 0;
        level++;
    }
}
return 0;
public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<int[]>();
        if(buildings==null || buildings.length==0 || buildings[0].length==0)
            return res;
        res = findSkyline(buildings, 0, buildings.length-1);    //merge sort套路
        for(int i=0; i<res.size(); i++) {          //处理consecutive horizontal lines of equal height的情况
            if(i>0 && res.get(i)[1]==res.get(i-1)[1]) {
                res.remove(i);
                i--;
            }
        }
        return res;
    }
    
    private List<int[]> findSkyline(int[][] buildings, int left, int right) {
        if(left==right) {   //base case 如果只有一个元素 将它拆成(起始,高度) (结束,0)存入结果集返回
            List<int[]> res = new ArrayList<int[]>();
            res.add(new int[]{buildings[left][0], buildings[left][2]});
            res.add(new int[]{buildings[left][1], 0});  //注意结束位置高度永远是0 不可忘!!!!
            return res;
        }
        int mid = (left+right)/2;       //merge sort套路
        List<int[]> l1 = findSkyline(buildings, left, mid);
        List<int[]> l2 = findSkyline(buildings, mid+1, right);
        List<int[]> res = merge(l1, l2);
        return res;
    }
    
    private List<int[]> merge(List<int[]> l1, List<int[]> l2) { //merge时所有点都会加入结果集 所以可能会有高度相同 x不同的情况 根据题目要求要去重 对应第7行
        List<int[]> res = new ArrayList<int[]>();
        int i = 0, j = 0, h1 = 0, h2 = 0;
        while(i<l1.size() && j<l2.size()) {
            if(l1.get(i)[0]<l2.get(j)[0]) {
                int left = l1.get(i)[0];
                h1 = l1.get(i)[1];
                res.add(new int[]{left, Math.max(h1, h2)});
                i++;
            }
            else if(l1.get(i)[0]>l2.get(j)[0]){
                int left = l2.get(j)[0];
                h2 = l2.get(j)[1];
                res.add(new int[]{left, Math.max(h1, h2)});
                j++;
            }
            else {
                h1 = l1.get(i)[1];
                h2 = l2.get(j)[1];
                res.add(new int[]{l1.get(i)[0], Math.max(h1, h2)});
                i++;
                j++;
            }
        }
        while(i<l1.size()) {
            res.add(new int[]{l1.get(i)[0], l1.get(i)[1]});
            i++;
        }
        while(j<l2.size()) {
            res.add(new int[]{l2.get(j)[0], l2.get(j)[1]});
            j++;
        }
        return res;
    }
}

类似Merge sort解法 只是这里是二维的 O(nlogn)

The idea is similar to merge of merge sort, start from first strips of two skylines, compare x coordinates. 

Pick the strip with smaller x coordinate and add it to result. 

The height of added strip is considered as maximum of current heights from skyline1 and skyline2.

merge:
Height of new Strip is always obtained by takin maximum of following
     (a) Current height from skyline1, say 'h1'.  
     (b) Current height from skyline2, say 'h2'
  h1 and h2 are initialized as 0. h1 is updated when a strip from
  SkyLine1 is added to result and h2 is updated when a strip from 
  SkyLine2 is added.

http://www.geeksforgeeks.org/divide-and-conquer-set-7-the-skyline-problem/





还有一个解法用heap
https://leetcode.com/discuss/37494/my-java-solution-using-a-max-queue
http://www.shuatiblog.com/blog/2014/07/01/The-Skyline-Problem/
https://briangordon.github.io/2014/08/the-skyline-problem.html


public class Solution {
    public static class building implements Comparable<building> {  //建立一个building类
        int x;
        int height;
        boolean flag;   // indicate a start line or a end line
        
        public building(int x, int h, boolean f) {
            this.x = x;
            this.height = h;
            this.flag = f;
        }
        
        boolean isStart() {
            return flag;
        }
        
        public int compareTo(building b) {  //根据优先级 1 x, 2 起始／结束位置 3 高度 排序
            if(x!=b.x)
                return x-b.x;
            else if(flag!=b.flag)   //如果x相同 则结束点位置在起始点前面
                return flag?1:-1;
            else if(height!=b.height) { //如果x和flag都相同 则高度矮的在前面
                if(flag)
                    return b.height-height;
                else
                    return height-b.height;
            }
            return 0;   //都相同判定为相同
        }
    }
    
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<int[]>();
        List<building> list = new ArrayList<building>();
        for(int i=0; i<buildings.length; i++) {         //将buildings的值拆分成(起始,高度) (结束,高度)放入list中
            list.add(new building(buildings[i][0], buildings[i][2], true));
            list.add(new building(buildings[i][1], buildings[i][2], false));
        }
        Collections.sort(list);     //排序 按照compareTo方法定义
        Queue<Integer> heap = new PriorityQueue<Integer>(10, Collections.reverseOrder());   //建立一个max heap
        building prev = null;
        for(int i=0; i<list.size(); i++) {  //开始扫
            building curr = list.get(i);        //得到当前building和下一个building
            building next = i+1<list.size()?list.get(i+1):null;
            if(curr.isStart()) {            //如果当前取的是一个building的起始位置
                if(prev==null || !(curr.height == prev.height && curr.x == prev.x)) {//这里如果curr和prev高度 x值都一样 直接忽略
                    if(heap.isEmpty())           //否则如果heap为空或当前高度大于heap的顶点(heap顶点既是前面点的最大高度) 则存在一上升拐点 要将它加入结果中
                        res.add(new int[]{curr.x, curr.height});
                    else if(curr.height>heap.peek())
                        res.add(new int[]{curr.x, curr.height});
                }
                heap.add(curr.height);  //只要是起始点就加入heap中
            }
            else {      //否则如果当前取得是一个building的结束位置
                heap.remove(curr.height);   //遇到结束点 要将对应building移除
                if(next==null || curr.x!=next.x) {
                    if(!heap.isEmpty() && curr.height>heap.peek())  //如果移除building是heap中最大值 则会出现一个下降的挂点 加它加入结果集
                        res.add(new int[]{curr.x, heap.peek()});
                    else if(heap.isEmpty()) //处理 he ground in between any two adjacent buildings should be considered part of the skyline contour
                        res.add(new int[]{curr.x, 0});
                }
            }
            prev = curr;
        }
        return res;
    }
}

O(nlogn)

思路还是蛮清晰的 首先将buildings的点拆成(起始x, height) (结束x, height) 然后排序 之后扫一遍维护一个max heap 碰到起始点将其高度加入heap 

heap顶点存的是之前对应区域的最大高度 如果当前点高度大于heap顶点则说明存在一个上升拐点 将该点加入结果集 碰到结束点 从heap中删除 如果删除后heap顶点小于当前高度

则说明存在一个下降拐点 将该点存入结果集 注意如果heap为空 则说明到地平线ground 也要将点加入结果集










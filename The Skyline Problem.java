public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<int[]>();
        if(buildings==null || buildings.length==0 || buildings[0].length==0)
            return res;
        res = findSkyline(buildings, 0, buildings.length-1);
        for(int i=0; i<res.size(); i++) {
            if(i>0 && res.get(i)[1]==res.get(i-1)[1]) {
                res.remove(i);
                i--;
            }
        }
        //int[] end = {buildings[buildings.length-1][1], 0};
        //res.add(end);
        return res;
    }
    
    private List<int[]> findSkyline(int[][] buildings, int left, int right) {
        if(left==right) {
            List<int[]> res = new ArrayList<int[]>();
            res.add(new int[]{buildings[left][0], buildings[left][2]});
            res.add(new int[]{buildings[left][1], 0});
            return res;
        }
        int mid = (left+right)/2;
        List<int[]> l1 = findSkyline(buildings, left, mid);
        List<int[]> l2 = findSkyline(buildings, mid+1, right);
        List<int[]> res = merge(l1, l2);
        return res;
    }
    
    private List<int[]> merge(List<int[]> l1, List<int[]> l2) {
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

http://www.geeksforgeeks.org/divide-and-conquer-set-7-the-skyline-problem/


还有一个中解法用heap
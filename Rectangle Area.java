public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int Area1 = (C-A)*(D-B);
        int Area2 = (G-E)*(H-F);
        if(A>G || E>C || B>H || F>D)
            return Area1+Area2;
        int length = Math.min(C, G) - Math.max(A, E);
        int height = Math.min(D, H) - Math.max(B, F);
        return Area1+Area2-length*height;
    }
}

http://articles.leetcode.com/2011/05/determine-if-two-rectangles-overlap.html
http://www.geeksforgeeks.org/find-two-rectangles-overlap/
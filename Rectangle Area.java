public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C - A) *(D - B), area2 = (G - E) * (H - F);
        if (A >= G || B >= H || E >= C || F >= D) return area1 + area2;
        int length = Math.min(C, G) - Math.max(A, E);
        int width = Math.min(D, H) - Math.max(B, F);
        return area1 + area2 - length * width;
    }
}

Following is a simpler approach. Two rectangles do not overlap if one of the following conditions is true.
1) One rectangle is above top edge of other rectangle.
2) One rectangle is on left side of left edge of other rectangle.

http://articles.leetcode.com/2011/05/determine-if-two-rectangles-overlap.html
http://www.geeksforgeeks.org/find-two-rectangles-overlap/



Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area
Assume that the total area is never beyond the maximum possible value of int.
题意：Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.

解题思路：找出矩阵中最大的矩形，矩形中只包含1。这道题需要利用上一道题（Largest Rectangle in Histogram）的结论。比如对于以下矩阵。

　　　　　　　　0 0 0 0

　　　　　　　　0 0 1 0

　　　　　　　　0 1 1 0

　　　　　　　　1 0 1 1

　　　　　对于这个矩阵，对于每一行，我们按照上一道题的算法求解一遍，最后得出的就是最大的矩阵。

代码：


class Solution:
    # @param matrix, a list of lists of 1 length string
    # @return an integer
    def largestRectangleArea(self, height):
        stack=[]; i=0; area=0
        while i<len(height):
            if stack==[] or height[i]>height[stack[len(stack)-1]]:
                stack.append(i)
            else:
                curr=stack.pop()
                width=i if stack==[] else i-stack[len(stack)-1]-1
                area=max(area,width*height[curr])
                i-=1
            i+=1
        while stack!=[]:
            curr=stack.pop()
            width=i if stack==[] else len(height)-stack[len(stack)-1]-1
            area=max(area,width*height[curr])
        return area
        
    def maximalRectangle(self, matrix):
        if matrix==[]: return 0
        a=[0 for i in range(len(matrix[0]))]; maxArea=0
        for i in range(len(matrix)):
            for j in range(len(matrix[i])):
                a[j]=a[j]+1 if matrix[i][j]=='1' else 0
            
            maxArea=max(maxArea, self.largestRectangleArea(a))
        
        return maxArea





public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] height = new int[matrix[0].length];
        int maxArea = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxArea, largestRect(height));
        }
        return maxArea;
    }
    
    private int largestRect(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int i = 0, maxArea = 0;
        for (; i < height.length; i++) {
            if (stack.isEmpty() || height[stack.peek()] < height[i])
                stack.push(i);
            else {
                int index = stack.pop();
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height[index] * width);
                i--;
            }
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            int width = stack.isEmpty() ? i : i - stack.peek() - 1;
            maxArea = Math.max(maxArea, height[index] * width);
        }
        return maxArea;
    }
}



from code ganker:

这是一道非常综合的题目，要求在0-1矩阵中找出面积最大的全1矩阵。刚看到这道题会比较无从下手，brute force就是对于每个矩阵都看一下，

总共有m(m+1)/2*n(n+1)/2个子矩阵（原理跟字符串子串类似，字符串的子串数有n(n+1)/2，只是这里是二维情形，所以是两个相乘），复杂度相当高，肯定不是面试官想要的答案，就不继续想下去了。

这道题的解法灵感来自于Largest Rectangle in Histogram这道题，假设我们把矩阵沿着某一行切下来，然后把切的行作为底面，将自底面往上的矩阵看成一个直方图（histogram）。

直方图的中每个项的高度就是从底面行开始往上1的数量。根据Largest Rectangle in Histogram我们就可以求出当前行作为矩阵下边缘的一个最大矩阵。

接下来如果对每一行都做一次Largest Rectangle in Histogram，从其中选出最大的矩阵，那么它就是整个矩阵中面积最大的子矩阵。

算法的基本思路已经出来了，剩下的就是一些节省时间空间的问题了。

我们如何计算某一行为底面时直方图的高度呢？ 如果重新计算，那么每次需要的计算数量就是当前行数乘以列数。然而在这里我们会发现一些动态规划的踪迹，如果我们知道上一行直方图的高度，

我们只需要看新加进来的行（底面）上对应的列元素是不是0，如果是，则高度是0，否则则是上一行直方图的高度加1。利用历史信息，我们就可以在线行时间内完成对高度的更新。

我们知道，Largest Rectangle in Histogram的算法复杂度是O(n)。所以完成对一行为底边的矩阵求解复杂度是O(n+n)=O(n)。接下来对每一行都做一次，那么算法总时间复杂度是O(m*n)。

空间上，我们只需要保存上一行直方图的高度O(n)，加上Largest Rectangle in Histogram中所使用的空间O(n)，所以总空间复杂度还是O(n)。代码如下：

public int maximalRectangle(char[][] matrix) {
    if(matrix==null || matrix.length==0 || matrix[0].length==0)
    {
        return 0;
    }
    int maxArea = 0;
    int[] height = new int[matrix[0].length];
    for(int i=0;i<matrix.length;i++)
    {
        for(int j=0;j<matrix[0].length;j++)
        {
            height[j] = matrix[i][j]=='0'?0:height[j]+1;
        }
        maxArea = Math.max(largestRectangleArea(height),maxArea);
    }
    return maxArea;
}
public int largestRectangleArea(int[] height) {
    if(height==null || height.length==0)
    {
        return 0;
    }
    int maxArea = 0;
    LinkedList<Integer> stack = new LinkedList<Integer>();
    for(int i=0;i<height.length;i++)
    {
        
        while(!stack.isEmpty() && height[i]<=height[stack.peek()])
        {
            int index = stack.pop();
            int curArea = stack.isEmpty()?i*height[index]:(i-stack.peek()-1)*height[index];
            maxArea = Math.max(maxArea,curArea);
        }
        stack.push(i);
    }
    while(!stack.isEmpty())
    {
        int index = stack.pop();
        int curArea = stack.isEmpty()?height.length*height[index]:(height.length-stack.peek()-1)*height[index];
        maxArea = Math.max(maxArea,curArea);            
    }
    return maxArea;
}

这道题最后的复杂度是非常令人满意的，居然在O(m*n)时间内就可以完成对最大矩阵的搜索，可以看出这已经是下界（因为每个元素总要访问一下才知道是不是1）了。

难度还是比较大的，相信要在面试当场想到这种方法是很不容易的。个人很喜欢这道题，既用到了别的题目，又有动态规划的思想，复杂度还非常漂亮，又一次体现了算法的魅力哈。

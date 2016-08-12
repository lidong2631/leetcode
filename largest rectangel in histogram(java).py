class Solution:
    # @param height, a list of integer
    # @return an integer
    def largestRectangleArea(self, height):
        stack = []; i = 0; maxArea = 0
        while(i < len(height)):         #遍历数组height中的每一个元素
            if stack == [] or height[i] > height[stack[len(stack)-1]]:      #如果stack为空或当前i指向的元素高度大于栈顶元素 就将当前元素push进栈 注意栈中存放的是元素的索引 栈中维护的是一个递增序列
                stack.append(i)
            else:
                curr = stack.pop()      #如果当前i指向的元素高度小于等于栈顶元素 首先pop出栈顶元素
                width = i if stack == [] else i - stack[len(stack)-1] - 1       #然后如果pop之后栈为空width=i 栈不为空width=i-stack.peek()-1
                maxArea = max(maxArea, width * height[curr])            #计算maxArea 并取最大值
                i-=1        #i递减1 防止i在往前走
            i+=1            #每次循环一次i递增1
        while stack!=[]:        #最后如果i已经到最后了 但栈中还有元素 就一一pop出栈计算面积 取所有面积中最大的 过程跟else中相同
            curr = stack.pop()
            width = i if stack == [] else i - stack[len(stack)-1] - 1
            maxArea = max(maxArea, width * height[curr])
        return maxArea

Note: 看照片每一步如何进行 还有LeetCode 笔记系列 17 Largest Rectangle in Histogram 这个解法时间复杂度为O(n)






常规解法，所有的面积都算一遍，时间复杂度O(N^2)。不过会TLE。

代码：


class Solution:
    # @param height, a list of integer
    # @return an integer
    # @good solution!
    def largestRectangleArea(self, height):
        maxarea=0
        for i in range(len(height)):        #遍历每一个height中的元素
            min = height[i]         #min初始值为当前i指向的元素的高度
            for j in range(i, len(height)):         #对于每一个i 遍历它后面所有元素 如果有更小高度的元素 更新min 并以新的min计算面积 否则说明后面元素高度大于等于当前i指向元素 继续用当前min计算面积
                if height[j] < min: min = height[j]
                if min*(j-i+1) > maxarea: maxarea = min*(j-i+1)
        return maxarea





public class Solution {
    public int largestRectangleArea(int[] height) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (; i < height.length; i++) {
            if (stack.isEmpty() || height[stack.peek()] < height[i])
                stack.push(i);
            else {
                int index = stack.pop();
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;     // cannot write i-index think about case [1,2,2]
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

这道题brute force的方法很直接，就是对于每一个窗口，找到其中最低的高度，然后求面积，去其中最大的矩形面积。总共有n^2个窗口，找最低高度是O(n)的操作，所以复杂度是O(n^3)。


接下来我们讨论一种比较容易理解的思路，就是从每一个bar往两边走，以自己的高度为标准，直到两边低于自己的高度为止，然后用自己的高度乘以两边走的宽度得到矩阵面积。

因为我们对于任意一个bar都计算了以自己为目标高度的最大矩阵，所以最好的结果一定会被取到。每次往两边走的复杂度是O(n)，总共有n个bar，所以时间复杂度是O(n^2)。

因为代码比较简单，这里就不列出来了哈。

最后我们谈谈最优的解法，思路跟Longest Valid Parentheses比较类似，我们要维护一个栈，这个栈从低向上的高度是依次递增的，如果遇到当前bar高度比栈顶元素低，

那么就出栈直到满足条件，过程中检测前面满足条件的矩阵。关键问题就在于在出栈时如何确定当前出栈元素的所对应的高度的最大范围是多少。答案跟Longest Valid Parentheses中括号的范围相似，

就是如果栈已经为空，说明到目前为止所有元素（当前下标元素除外）都比出栈元素高度要大（否则栈中肯定还有元素），所以矩阵面积就是高度乘以当前下标i。如果栈不为空，

那么就是从当前栈顶元素的下一个到当前下标的元素之前都比出栈元素高度大（因为栈顶元素第一个比当前出栈元素小的）。具体的实现代码如下：

public int largestRectangleArea(int[] height) {
    if(height==null || height.length==0)
        return 0;
    int max = 0;
    LinkedList<Integer> stack = new LinkedList<Integer>();
    for(int i=0;i<height.length;i++)
    {
        while(!stack.isEmpty() && height[i]<=height[stack.peek()])
        {
            int index = stack.pop();
            int curArea = stack.isEmpty()?i*height[index]:(i-stack.peek()-1)*height[index];
            max = Math.max(max,curArea);
        }
        stack.push(i);
    }
    while(!stack.isEmpty())
    {
        int index = stack.pop();
        int curArea = stack.isEmpty()?height.length*height[index]:(height.length-stack.peek()-1)*height[index];
        max = Math.max(max,curArea);            
    }
    return max;
}

实现中注意最后还要对剩下的栈做清空并且判断，因为算法中每次是对于前面的元素面积进行判断，所以循环结束中如果栈中仍有元素，还是要继续维护面积直到栈为空。

这道题思路还是比较绕的，大家可能要仔细想想才能理清。不过解法确实很简练，总共只需要扫描一遍，所以时间复杂度是O(n)，空间复杂度是栈的大小，最坏情况是O(n)。

这道题的扩展题目是Maximal Rectangle，用到了这道题作为subroutine，是一道比较复杂的题目，有兴趣的朋友可以看看。





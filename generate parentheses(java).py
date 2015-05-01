<<<<<<< HEAD
class Solution:
    def dfs(self, left, right, currItem, res):
        if left > right:            #如果left大于right 说明我们已经用得右括号数要大于左括号数 产生的括号对一定不符合要求 ex: )() 直接return
            return
        if left == 0 and right == 0:    #如果left right都等于0 则找到一组合法的括号对
            res.append(currItem)
        if left > 0:                                    #如果left还有剩余 总可以继续递归调用下去
            self.dfs(left-1, right, currItem + '(', res)
        if right > 0:                                   #如果左括号为0了 则开始添加右括号 或者递归回调处理
            self.dfs(left, right-1, currItem + ')', res)
    # @param an integer
    # @return a list of string
    def generateParenthesis(self, n):
        res = []
        self.dfs(n, n, '', res)
        return res

Note: 理解dfs如何运作 主要还是递归 看照片 看看n=2, n=3程序如何运行




题意：

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"

解题思路：列举出所有合法的括号匹配，使用dfs。如果左括号的数量大于右括号的数量的话，就不能产生合法的括号匹配。

代码：


class Solution:
    # @param an integer
    # @return a list of string
    # @draw a decision tree when n == 2, and you can understand it!
    def helpler(self, l, r, item, res):
        if r < l:
            return
        if l == 0 and r == 0:
            res.append(item)
        if l > 0:
            self.helpler(l - 1, r, item + '(', res)
        if r > 0:
            self.helpler(l, r - 1, item + ')', res)
    
    def generateParenthesis(self, n):
        if n == 0:
            return []
        res = []
        self.helpler(n, n, '', res)
        return res




public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        if(n<=0)
            return res;
        helper(n, n, new String(), res);
        return res;
    }
    
    private void helper(int left, int right, String item, List<String> res) {
        if(right<left)
            return;
        if(left==0 && right ==0)
            res.add(item);          
        if(left>0)
            helper(left-1, right, item+"(", res);
        if(right>0)
            helper(left, right-1, item+")", res);
    }
}

Note: 根据code ganker改编 递归题 cc150也有 

看code ganker评论

顺便看下卡特兰数 如果是求输出结果有多少直接用卡特兰递推公式就可以了 见下面code ganker讲解和wikipedia 

http://zh.wikipedia.org/wiki/%E5%8D%A1%E5%A1%94%E5%85%B0%E6%95%B0

EX: 2

2, 2, "", res
    1, 2, "(", res
        0, 2, "((", res
            0, 1, "(()", res
                0, 0, "(())", res   (get one solution!)
        1, 1, "()", res
            0, 1, "()(", res
                0, 0, "()()", res   (get another solution!)
            1, 0, "())", res    (return)
    2, 1, ")", res  (return)





from code ganker:

这道题其实是关于卡特兰数的，如果只是要输出结果有多少组，那么直接用卡特兰数的公式就可以。关于卡特兰数，请参见卡特兰数-维基百科，里面有些常见的例子，这个概念还是比较重要的，

因为很多问题的原型其实都是卡特兰数，大家可以看看。特别是其中



这个递推式的定义，很多这类问题都可以归结成这个表达式。这个题对于C的定义就是第一对括号中包含有几组括号。因为第一组括号中包含的括号对数量都不同，所以不会重复，

接下来就是一个递归定义，里面又可以继续用更小的C去求组合可能性。

说完卡特兰数的内容，我们来看看这个具体问题怎么解决。一般来说是用递归的方法，因为可以归结为子问题去操作。在每次递归函数中记录左括号和右括号的剩余数量，

然后有两种选择，一个是放一个左括号，另一种是放一个右括号。当然有一些否定条件，比如剩余的右括号不能比左括号少，或者左括号右括号数量都要大于0。

正常结束条件是左右括号数量都为0。算法的复杂度是O(结果的数量)，因为卡特兰数并不是一个多项式量级的数字，所以算法也不是多项式复杂度的。代码如下： 

public ArrayList<String> generateParenthesis(int n) {
    ArrayList<String> res = new ArrayList<String>();
    if(n<=0)
        return res;
    helper(n,n,new String(),res);
    return res;
}
private void helper(int l, int r, String item, ArrayList<String> res)
{
    if(r<l)
        return;
    if(l==0 && r==0)
    {
        res.add(item);
    }
    if(l>0)
        helper(l-1,r,item+"(",res);
    if(r>0)
        helper(l,r-1,item+")",res);
}

这道题目主要考查的是递归思想的实现，当然如果可以看透背后是一个卡特兰数的模型，会更好一些

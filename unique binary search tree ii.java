Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3



Java:
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n <=0 ) 
            return new ArrayList<TreeNode>();
        return helper(1, n);
    }
    
    private List<TreeNode> helper(int left, int right) {
        List<TreeNode> res = new ArrayList<>();
        
        if (left > right) {
            res.add(null);
            return res;
        }
        
        for (int i = left; i <= right; i++) {
            List<TreeNode> leftTree = helper(left, i-1);
            List<TreeNode> rightTree = helper(i+1, right);
            
            for (int j = 0; j < leftTree.size(); j++) {
                for (int k = 0; k < rightTree.size(); k++) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree.get(j);
                    root.right = rightTree.get(k);
                    res.add(root);
                }
            }
        }
        return res;
    }
}








这道题是求解所有可行的二叉查找树，从Unique Binary Search Trees中我们已经知道，可行的二叉查找树的数量是相应的卡特兰数，

不是一个多项式时间的数量级，所以我们要求解所有的树，自然是不能多项式时间内完成的了。算法上还是用求解NP问题的方法来求解，

也就是N-Queens中介绍的在循环中调用递归函数求解子问题。思路是每次一次选取一个结点为根，然后递归求解左右子树的所有结果，

最后根据左右子树的返回的所有子树，依次选取然后接上（每个左边的子树跟所有右边的子树匹配，而每个右边的子树也要跟所有的左边子树匹配，

总共有左右子树数量的乘积种情况），构造好之后作为当前树的结果返回。代码如下： 

public ArrayList<TreeNode> generateTrees(int n) {
    return helper(1,n);
}
private ArrayList<TreeNode> helper(int left, int right)
{
    ArrayList<TreeNode> res = new ArrayList<TreeNode>();
    if(left>right)
    {
        res.add(null);
        return res;
    }
    for(int i=left;i<=right;i++)
    {
        ArrayList<TreeNode> leftList = helper(left,i-1);
        ArrayList<TreeNode> rightList = helper(i+1,right);
        for(int j=0;j<leftList.size();j++)
        {
            for(int k=0;k<rightList.size();k++)
            {
                TreeNode root = new TreeNode(i);
                root.left = leftList.get(j);
                root.right = rightList.get(k);
                res.add(root);
            }
        }
    }
    return res;
}

实现中还是有一些细节的，因为构造树时两边要遍历所有左右的匹配，然后接到根上面。

当然我们也可以像在Unique Binary Search Trees中那样存储所有的子树历史信息，然后进行拼合，虽然可以省一些时间，

但是最终还是逃不过每个结果要一次运算，时间复杂度还是非多项式的，并且要耗费大量的空间，感觉这样的意义就不是很大了。


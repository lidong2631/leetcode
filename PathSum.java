import java.util.*;

public class PathSum {
    public List<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        List<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(root==null)
            return res;
        ArrayList<Integer> item = new ArrayList<Integer>();
        item.add(root.val);
        helper(root, sum-root.val, item, res);
        return res;
    }
    
    private void helper(TreeNode root, int sum, ArrayList<Integer> item, List<ArrayList<Integer>> res) {
        if(root==null)
            return;
        if(root.left==null && root.right==null && sum==0)
        {
            //res.add(new ArrayList(item));
            for(int i=0; i<item.size(); i++)
                System.out.print(item.get(i));
            System.out.println();
            res.add(item);
            return;
        }
        if(root.left!=null)
        {
            item.add(root.left.val);            //
            helper(root.left, sum-root.left.val, item, res);    //
            item.remove(item.size()-1);
        }
        if(root.right!=null)
        {
            item.add(root.right.val);           //
            helper(root.right, sum-root.right.val, item, res);  //
            item.remove(item.size()-1);
        }
    }
}
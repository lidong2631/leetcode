<<<<<<< HEAD
import java.util.*;

public class generateTree {
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

if(leftList.get(j)==null)
    System.out.print("leftList: " + leftList.get(j));
else System.out.print("leftList: " + leftList.get(j).val);
System.out.println("    leftList size: " + leftList.size());
System.out.println();

                for(int k=0;k<rightList.size();k++)
                {

if(rightList.get(j)==null)
    System.out.print("rightList: " + rightList.get(j));
else System.out.print("rightList: " + rightList.get(j).val);
System.out.println("    rightList size: " + rightList.size());
System.out.println();

                    TreeNode root = new TreeNode(i);
                    root.left = leftList.get(j);
                    root.right = rightList.get(k);
                    res.add(root);
                    
System.out.println("current root is: " + root.val);
System.out.println();
                }
            }
        }
        //System.out.println("...");
        //for(int i=0; i<res.size(); i++)
        //    System.out.println(res.get(i).val);
        return res;
    }
}
=======
import java.util.*;

public class generateTree {
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

if(leftList.get(j)==null)
    System.out.print("leftList: " + leftList.get(j));
else System.out.print("leftList: " + leftList.get(j).val);
System.out.println("    leftList size: " + leftList.size());
System.out.println();

                for(int k=0;k<rightList.size();k++)
                {

if(rightList.get(j)==null)
    System.out.print("rightList: " + rightList.get(j));
else System.out.print("rightList: " + rightList.get(j).val);
System.out.println("    rightList size: " + rightList.size());
System.out.println();

                    TreeNode root = new TreeNode(i);
                    root.left = leftList.get(j);
                    root.right = rightList.get(k);
                    res.add(root);
                    
System.out.println("current root is: " + root.val);
System.out.println();
                }
            }
        }
        //System.out.println("...");
        //for(int i=0; i<res.size(); i++)
        //    System.out.println(res.get(i).val);
        return res;
    }
}
>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120

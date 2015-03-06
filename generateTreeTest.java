import java.util.*;

public class generateTreeTest {
	public static void main(String[] args) {
		generateTree test = new generateTree();
		ArrayList<TreeNode> res = new ArrayList<TreeNode>();
		res = new ArrayList(test.generateTrees(3));
for(int i=0; i<res.size(); i++)
	System.out.println(res.get(i).val);	
	}
}
import java.util.*;

public class PathSumTest {
	public static void main(String[] args) {
		List<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		TreeNode node1 = new TreeNode(2);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		node1.left = node2;
		node2.left = node3;
		PathSum ps = new PathSum();
		res = ps.pathSum(node1, 7);
		for(int i=0; i<res.size(); i++)
			for(int j=0; j<res.get(i).size(); j++)
				System.out.println(res.get(i).get(j));
	}
}
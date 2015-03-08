<<<<<<< HEAD
import java.util.*;

public class permutationII {
    public static void main(String[] args) {
        permutationII p = new permutationII();
        int[] num = {1,1,1};
        List<List<Integer>> res = p.permuteUnique(num);
        for(int i=0; i<res.size(); i++)
            System.out.println(res.get(i));
    }

    public List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num==null || num.length==0)
            return res;
        Arrays.sort(num);
        helper(num, new boolean[num.length], new ArrayList<Integer>(), res);
        return res;
    }
    
    private void helper(int[] num, boolean[] used, List<Integer> item, List<List<Integer>> res) {
        if(item.size()==num.length)
        {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for(int i=0; i<num.length; i++)
        {
            System.out.println("i= " + i);
            if(i>0 && used[i-1] && num[i]==num[i-1])
                continue;
            if(!used[i])
            {
                used[i] = true;
                item.add(num[i]);
                helper(num, used, item, res);
                item.remove(item.size()-1);
                used[i] = false;
            }
        }
    } 
=======
import java.util.*;

public class permutationII {
    public static void main(String[] args) {
        permutationII p = new permutationII();
        int[] num = {1,1,1};
        List<List<Integer>> res = p.permuteUnique(num);
        for(int i=0; i<res.size(); i++)
            System.out.println(res.get(i));
    }

    public List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num==null || num.length==0)
            return res;
        Arrays.sort(num);
        helper(num, new boolean[num.length], new ArrayList<Integer>(), res);
        return res;
    }
    
    private void helper(int[] num, boolean[] used, List<Integer> item, List<List<Integer>> res) {
        if(item.size()==num.length)
        {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        for(int i=0; i<num.length; i++)
        {
            System.out.println("i= " + i);
            if(i>0 && used[i-1] && num[i]==num[i-1])
                continue;
            if(!used[i])
            {
                used[i] = true;
                item.add(num[i]);
                helper(num, used, item, res);
                item.remove(item.size()-1);
                used[i] = false;
            }
        }
    } 
>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
}
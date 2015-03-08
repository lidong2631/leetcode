<<<<<<< HEAD
import java.util.*;

public class SimplifyPath {
    public static void main(String[] args) {
        SimplifyPath sp = new SimplifyPath();
        String path = sp.simplifyPath("/.");
        System.out.println(path);
    }

    public String simplifyPath(String path) {
        if(path==null || path.length()==0)
            return "";
        StringBuilder res = new StringBuilder();
        LinkedList<String> stack = new LinkedList<String>();
        int i=0;
        
        while(i<path.length())
        {
            int index = i;
            StringBuilder tmp = new StringBuilder();
            
            while(i<path.length()&&path.charAt(i)!='/')
            {    
                tmp.append(path.charAt(i));
                i++;
            }
            if(index!=i)
            {
                String tmpStr = tmp.toString();
                if(tmpStr.equals(".."))
                {
                    if(!stack.isEmpty())
                        stack.pop();
                }
                else if(!tmpStr.equals("."))
                    stack.push(tmpStr);
            }
            i++;
        }
        if(!stack.isEmpty())
        {
            String[] str = stack.toArray(new String[stack.size()]);
            for(int j=str.length-1; j>=0; j--)
            {
                res.append("/"+str[j]);
            }
        }
        if(res.length()==0)
            return "/";
        return res.toString();
    }
=======
import java.util.*;

public class SimplifyPath {
    public static void main(String[] args) {
        SimplifyPath sp = new SimplifyPath();
        String path = sp.simplifyPath("/.");
        System.out.println(path);
    }

    public String simplifyPath(String path) {
        if(path==null || path.length()==0)
            return "";
        StringBuilder res = new StringBuilder();
        LinkedList<String> stack = new LinkedList<String>();
        int i=0;
        
        while(i<path.length())
        {
            int index = i;
            StringBuilder tmp = new StringBuilder();
            
            while(i<path.length()&&path.charAt(i)!='/')
            {    
                tmp.append(path.charAt(i));
                i++;
            }
            if(index!=i)
            {
                String tmpStr = tmp.toString();
                if(tmpStr.equals(".."))
                {
                    if(!stack.isEmpty())
                        stack.pop();
                }
                else if(!tmpStr.equals("."))
                    stack.push(tmpStr);
            }
            i++;
        }
        if(!stack.isEmpty())
        {
            String[] str = stack.toArray(new String[stack.size()]);
            for(int j=str.length-1; j>=0; j--)
            {
                res.append("/"+str[j]);
            }
        }
        if(res.length()==0)
            return "/";
        return res.toString();
    }
>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
}
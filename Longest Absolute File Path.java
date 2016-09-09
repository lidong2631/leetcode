public class Solution {
    public int lengthLongestPath(String input) {
        int maxLen = 0, curr = 0;
        Stack<Integer> stack = new Stack<>();
        String[] str = input.split("\n");
        for (String s : str) {
            int level = helper(s);  // calculate how many \t
            while (stack.size() > level) curr -= stack.pop(); // if current directory/file depth is lower that the top directory/file on the stack, pop from stack 
            int len = s.replaceAll("\t", "").length() + 1; // +1 here because a "/" needs to be counted following each diretory
            curr += len;
            if (s.contains(".")) maxLen = Math.max(maxLen, curr-1); // if s contains ".", we have found a file update maxLen if necessary
            stack.push(len);
        }
        return maxLen;
    }
    
    private int helper(String s) {
        String tmp = s.replaceAll("\t", ""); // careful \t only takes 1 in length()
        return s.length() - tmp.length();
    }
}

System.out.println("\tabc".length()); // 4
System.out.println("abc".length());   // 3

O(n) O(n)

https://discuss.leetcode.com/topic/55088/java-o-n-solution-using-stack




Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext
The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.

Note:
The name of a file contains at least a . and an extension.
The name of a directory or sub-directory will not contain a ..
Time complexity required: O(n) where n is the size of the input string.

Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
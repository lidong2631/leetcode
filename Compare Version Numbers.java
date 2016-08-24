public class Solution {
    public int compareVersion(String version1, String version2) {
        for (int i = 0, j = 0; i < version1.length() || j < version2.length(); i++, j++) {  // careful "||" here
            int ver1 = 0;
            while (i < version1.length() && version1.charAt(i) != '.') ver1 = 10 * ver1 + version1.charAt(i++) - '0';
            
            int ver2 = 0;
            while (j < version2.length() && version2.charAt(j) != '.') ver2 = 10 * ver2 + version2.charAt(j++) - '0';
            
            if (ver1 > ver2) return 1;
            else if (ver1 < ver2) return -1;
        }
        return 0;
    }
}

O(n)




public class Solution {
    public int compareVersion(String version1, String version2) {
        int[] i1 = new int[1];
        int[] i2 = new int[1];
        for(i1[0]=0, i2[0]=0; i1[0]<version1.length() || i2[0]<version2.length(); i1[0]++, i2[0]++) {
            int Len1 = getLength(version1, i1); //调用getLength函数得到从begin开始走 碰到'.'或string结束点为止的长度
            int Len2 = getLength(version2, i2);
            
            if(Len1<Len2)   //如果长度不等则返回结果
                return -1;
            else if(Len1>Len2)
                return 1;
                
            for(int i=0; i<Len1; i++) { //否则逐一比较字符
                if(version1.charAt(i1[0])<version2.charAt(i2[0]))
                    return -1;
                else if(version1.charAt(i1[0])>version2.charAt(i2[0]))
                    return 1;
                i1[0]++;
                i2[0]++;
            }
        }
        return 0;
    }
    
    private int getLength(String str, int[] begin) {    //这里用数组是因为要传递地址
        while(begin[0]<str.length()&&str.charAt(begin[0])=='0') //排除开始的'0'
            begin[0]++;
        int end = begin[0];
        for(; end<str.length()&&str.charAt(end)!='.'; end++)    //计算到'.'的距离
        ;
        return end-begin[0];
    }
}

cpcs基本版 http://www.meetqun.com/thread-3331-1-1.html

两个点之间的整数（不知道有没有为空的情况，空就认为0），比较它们的大小。数据似乎比较弱，可以转成int。但是我的建议就是不转换。

而且最好别用substr这种东西，否则空间应该不是O(1)，我的方法时间复杂度O(n)——n是字符串总长度，空间复杂度O(1)

从begin开始沿着s走，走到"."或者s的结束点为止，返回走的长度，注意begin也可能变化，这是为了跳过整数开头的0，这些0是无效的。

例如"00110.xx"从开头走，end会走到那个'.'，而begin会到“110”的第一个字符‘1’，返回的长度是3。如果begin超过了字符串长度，则end = begin，

最后返回长度为0

我们传入的参数是string的引用，并没有复制任何东西，所以空间复杂度O(1)。

接下来就是比较两个字符串begin1, begin2,长度为length1, length2的部分，当然我们可以转化为整数，也可以substr直接比较。

我是自己循环比较的，因为没有首0，长度长的肯定大，长度相同的话，再逐个比较。 

所以时间复杂度就是最差把两个串分别扫两遍的复杂度（第一遍确定每个begin,length，第二遍是比较），所以是O(n)的





public class Solution {
    public int compareVersion(String version1, String version2) {
        String[] ver1 = version1.split("\\.");
        String[] ver2 = version2.split("\\.");
        
        int length = Math.max(version1.length(), version2.length());
        for(int i=0; i<length; i++) {
            int v1 = i<ver1.length ? Integer.parseInt(ver1[i]):0;
            int v2 = i<ver2.length ? Integer.parseInt(ver2[i]):0;
            if(v1<v2)
                return -1;
            else if(v1>v2)
                return 1;
        }
        return 0;
    }
}

利用java split函数将字符串分割成数组 也是将字符变成ascii值比较大小 这里利用Integer.parseInt(ver1[i])转换时会自动将起始0消掉

时间还是O(m+n) 但空间要多两个数组开销 O(m+n)


三种方法思路不同 都可以看看
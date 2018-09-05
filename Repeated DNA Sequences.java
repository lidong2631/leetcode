All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, 
it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

Output: ["AAAAACCCCC", "CCCCCAAAAA"]




Java:
public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> checkSet = new HashSet<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < s.length() - 9; i++) {
            String str = s.substring(i, i + 10);
            if (!checkSet.add(str)) set.add(str);   // cool !
        }
        return new ArrayList<String>(set);
    }
}

O(n) O(n)


public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 9; i < s.length(); i++) {
            String tmp = s.substring(i-9, i+1);
            if (map.containsKey(tmp)) {
                if (map.get(tmp) == 1) res.add(tmp);
                map.put(tmp, map.get(tmp)+1);
            }
            else map.put(tmp, 1);
        }
        return res;
    }
}


All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, 

it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].



public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<String>();
        if(s==null || s.length()<10)        //长度小于10就直接返回
            return res;
        int mask = 0x3FFFF; //这里mask一共18位 是因为DNA序列有ACGT四种字符 可以用2bits表示一个字符 每次我们都用10个字符的sequence的后9位
        int sequence = 0;   //与新进入的一个字符组合 所以这里掩码想取9个字符*2bits 就是18位即0x3FFFF
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        sequence = toInt(s.substring(0, 10));   //toInt将一个string转成我们要的二进制序列
        map.put(sequence, 1);
        
        for(int i=1; i<s.length()-9; i++) { //循环从第11个字符开始取 到字符串结束
            sequence = (sequence&mask)<<2|toInt(s.charAt(i+9)); //每次sequence左移2位加入新进来的一个字符 组成新的sequence
            if(map.containsKey(sequence)) {     //如果字典里有 将这个sequence加入最终哦给你结果 否则将这个sequence加入字典
                if(map.get(sequence)==1)
                    res.add(s.substring(i, i+10));
                map.put(sequence, map.get(sequence)+1); //这里是为防止多次重复产生重复结果
            }
            else
                map.put(sequence, 1);
        }
        return res;
    }
    
    private int toInt(String str) {
        int result = 0;
        for(int i=0; i<str.length(); i++) {
            result<<=2;
            result |= toInt(str.charAt(i));
        }
        return result;
    }
    
    private int toInt(char c) {
        if(c=='A')
            return 0;
        else if(c=='C')
            return 1;
        else if(c=='G')
            return 2;
        else 
            return 3;
    }
}

Rolling Hash思想 实质是Rabin Karp Algorithm 见RabinKarp.java得实现

时间O(n)  ?空间最坏O(n^2)  这题主要考核位运算 要注意凡是一定范围的字符都可以考虑转换成二进制表示 

这题可以扩展为不止4个字符 k个字符








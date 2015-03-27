public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<String>();
        if(s==null || s.length()<10)
            return res;
        int mask = 0x7FFFFFF;
        int index = 0; int sequence = 0;    //index为当前字符指针 sequ
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        while(index<9) {
            sequence = (sequence<<3)|(s.charAt(index++)&7);
        }
        while(index<s.length()) {
            sequence = (sequence&mask)<<3|(s.charAt(index++)&7);
            if(map.containsKey(sequence)) {
                if(map.get(sequence)==1)
                    res.add(s.substring(index-10, index));
                map.put(sequence, map.get(sequence)+1);
            }
            else
                map.put(sequence, 1);
        }
        return res;
    }
}

from meetqun
用acgt二进制表示的末三位来唯一标识他们，所以一个字符在我的表示中等价为3位，现在要10长度的字符串，那么就是10*3=30位，

我每次都是要取上一个窗口10个char的后9个与新进入窗口的字符组合，所以掩码是想取后9个char，就是27位的mask，27个1=0x7ffffff。

当然你用30位的掩码也可以做的，原理一样，具体操作有不同罢了






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

时间O(n)  ?空间最坏O(n^2)  这题主要考核位运算 要注意凡是一定范围的字符都可以考虑转换成二进制表示 

这题可以扩展为不止4个字符 k个字符





public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<String>();
        if(s==null || s.length()<10)
            return res;
        int mask = 0x3FFFF;
        int sequence = toInt(s.substring(0, 10));
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(sequence, 1);
        for(int i=10; i<s.length(); i++) {
            sequence = ((sequence&mask)<<2)|toInt(s.charAt(i));
            if(map.containsKey(sequence)) {
                if(map.get(sequence)==1)
                    res.add(s.substring(i-9, i+1));
                map.put(sequence, map.get(sequence)+1);
            }
            else
                map.put(sequence, 1);
        }
        return res;
    }
    
    private int toInt(String str) {
        int res = 0;
        for(int i=0; i<str.length(); i++) {
            res = (res<<2)|toInt(str.charAt(i));
        }
        return res;
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









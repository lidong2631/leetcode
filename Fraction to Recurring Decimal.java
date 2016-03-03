public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator==0)
            return "0";
        if(denominator==0)
            return "";
        
        String res = "";
        if((numerator<0)^(denominator<0))
            res += "-";
        
        long num = Math.abs((long)numerator);   // prevent overflow like Integer.MIN_VALUE. Math.abs(Integer.MIN_VALUE)==Integer.MIN_VALUE
        long den = Math.abs((long)denominator);
        
        long integer_part = num/den;
        res += String.valueOf(integer_part);
        
        long remainder = (num%den)*10;
        if(remainder==0)                        // if no decimal return
            return res;
        
        res+=".";
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        while(remainder!=0) {
            if(map.containsKey(remainder)) {
                String partA = res.substring(0, map.get(remainder));
                String partB = res.substring(map.get(remainder), res.length());
                return partA + "(" + partB + ")";
            }
            map.put(remainder, res.length());
            long result = remainder/den;
            res += String.valueOf(result);
            remainder = (remainder%den)*10;
        }
        return res;
    }
}

自己写的 注意一旦有long类型的数字计算 其结果也应该是long类型 不然会出现如下错误

Line 15: error: incompatible types: possible lossy conversion from long to int

-2147483648 的绝对值还是自身 http://stackoverflow.com/questions/5444611/math-abs-returns-wrong-value-for-integer-min-value

另外复习 原码 反码 补码 http://blog.csdn.net/zq602316498/article/details/39404043

http://blog.csdn.net/ljiabin/article/details/42025037

http://www.meetqun.com/thread-3328-1-1.html

补充下 remainder在map里可以是int 因为余数达不到最大绝对值的整数， 但是因为reminder要＊10，所以map的key用int 而reminder还应该用long long

时间O(n) 空间O(n)





public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator==0)
            return "0";
        if(denominator==0)
            return "";
        
        StringBuffer res = new StringBuffer();
        if((numerator<0)^(denominator<0))
            res.append('-');
        
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        
        long integer_part = num/den;
        res.append(integer_part);
        
        long remainder = (num%den)*10;
        if(remainder==0)
            return res.toString();
        
        res.append('.');
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        while(remainder!=0) {
            if(map.containsKey(remainder)) {
                String partA = res.substring(0, map.get(remainder)).toString();
                String partB = res.substring(map.get(remainder), res.length()).toString();
                return partA + "(" + partB + ")";
            }
            map.put(remainder, res.length());
            long result = remainder/den;
            res.append(result);
            remainder = (remainder%den)*10;
        }
        return res.toString();
    }
}

根据上面的解法改用StringBuffer 可以节省空间









public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator==0)        //被除数为0情况
            return "0";
        if(denominator==0)      //除数为0
            return "";
            
        String res = "";
        
        if((numerator<0)^(denominator<0))   //判断符号正反 多想想几种方法检测opposite number
            res += "-";
        
        long num = numerator; long den = denominator;   //转成long type 因为test case有Integer.MIN_VALUE
        num = Math.abs(num); den = Math.abs(den);
        
        String quotient = String.valueOf(num/den);  //先把商加到最终结果里
        res += quotient;
        
        long remainder = num%den*10;    //算余数 乘以10
        if(remainder==0)                //如果余数为0 直接返回结果
            return res;
        
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();  //否则开始循环 map中键是余数乘以10 value是对应结果下标
        res+=".";                                   //将"."加到结果中
        while(remainder!=0) {
            if(map.containsKey(remainder)) {        //如果map中有余数的记录 说明循环开始了 取res对应的substring并加上括号返回
                res = res.substring(0, map.get(remainder))+"("+res.substring(map.get(remainder))+")";
                return res;
            }
            map.put(remainder, res.length());   //否则将记录加到map
            res += String.valueOf(remainder/den);   //将新的商加到res中
            remainder = (remainder%den)*10;         //算新的余数
        }
        return res;
    }
}

这题很绕 要好好捉摸 几个问题
1 注意java中整数的存储 正整数 0~2^31 负整数-2^32~-1

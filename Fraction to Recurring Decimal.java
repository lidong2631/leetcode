<<<<<<< HEAD
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


http://blog.csdn.net/ljiabin/article/details/42025037

=======
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


http://blog.csdn.net/ljiabin/article/details/42025037

>>>>>>> e1726386107db545bdcfa0e769c3d529b5cda120
http://www.meetqun.com/thread-3328-1-1.html
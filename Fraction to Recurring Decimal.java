public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        if (denominator == 0) return "";
        
        int sign = 1;
        if ((numerator > 0 && denominator < 0) || (numerator < 0 && denominator > 0)) sign = -1;
        
        long num = Math.abs((long)numerator); long den = Math.abs((long)denominator);
        String integral = (sign == 1) ? String.valueOf(num / den) : "-" + String.valueOf(num / den);
        long remainder = (num % den) * 10;
        
        if (remainder == 0) return integral;
        
        StringBuffer res = new StringBuffer();
        res.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                return integral + res.substring(0, map.get(remainder)).toString() + 
                    "(" + res.substring(map.get(remainder), res.length()).toString() + ")";
            }
            map.put(remainder, res.length());       // careful
            res.append(remainder / den);
            remainder = (remainder % den) * 10;
        }
        return integral + res.toString();
    }
}

O(n) O(n)



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

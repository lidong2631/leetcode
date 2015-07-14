ZigZag Conversion
纯字符串操作题
if(s==null || s.length==0 || nRows==0)
    return "";
if(nRows==1)
    return s;
StringBuilder res = new StringBuilder();
int size = 2*nRows-2;
for(int i=0; i<nRows; i++) {
    for(int j=i; j<s.length(); j+=size) {
        res.append(s.charAt(j));
        if(i>0 && i<nRows-1 && j+size-2*i<s.length())
            res.append(s.charAt(j+size-2*i));
    }
}
return res.toString();

时间O(n) 空间O(1)





Valid Parentheses
借助于栈
Map<Character, Character> map = new HashMap<Character, Character>();
map.put('(', ')');
map.put('[', ']');
map.put('{', '}');
Stack<Character> stack = new Stack<Character>();
for(char c : s.toCharArray()) {
    if(map.containsKey(c))
        stack.push(c);
    else if(stack.empty() || map.get(stack.pop())!=c)
        return false;
}
return stack.empty();

时间O(n) 空间O(n)





Longest Valid Parentheses
for(int i=0; i<s.length(); i++) {
    if(s.charAt(i)=='(')
        stack.push(i);
    else {
        if(stack.empty())
            start = i+1;
        else {
            stack.pop();
            if(stack.empty())
                maxLen = Math.max(maxLen, i-start+1);
            else
                maxLen = Math.max(maxLen, i-stack.peek());
        }
    }
}

O(n) O(n)

))()()  ))(()





Valid Palindrome
双指针
int left = 0, right = s.length()-1;
while(left<right) {
    while(left<right && !Character.isLetterOrDigit(s.charAt(left))) //注意这里只判断字符和数字
        left++;
    while(left<right && !Character.isLetterOrDigit(s.charAt(right)))
        right--;
    if(Character.toLowerCase(s.charAt(left))!=Character.toLowerCase(s.charAt(right)))
        return false;
    left++;
    right--;
}
return true;

时间O(n) 空间O(1)





Valid Number
whitespace + (sign + number + '.' + number) + (e + sign + number) + whitespace
int i=0;
while(i<s.length() && Character.isWhitespace(s.charAt(i)))
    i++;
boolean isNumeric = false;
if(i<s.length() && (s.charAt(i)=='+' || s.charAt(i)=='-'))
    i++;
while(i<s.length() && Character.isDigit(s.charAt(i))) {
    i++;
    isNumeric = true;
}
if(i<s.length() && s.charAt(i)=='.') {
    i++;
    while(i<s.length() && Character.isDigit(s.charAt(i))) {
        i++;
        isNumeric = true;
    }
}
if(i<s.length() && s.charAt(i)=='e' && isNumeric) {
    i++;
    isNumeric = false;
    if(i<s.length() && (s.charAt(i)=='+' || s.charAt(i)=='-'))
        i++;
    while(i<s.length() && Character.isDigit(s.charAt(i))) {
        i++;
        isNumeric = true;
    }
}
while(i<s.length() && Character.isWhitespace(s.charAt(i)))
    i++;
return (i==s.length()) && isNumeric;

时间O(n) 空间O(1)





String to Integer
atoi 注意溢出问题
int maxDiv10 = Integer.MAX_VALUE/10;
int i=0, n=str.length();
while(i<n && Character.isWhitespace(str.charAt(i))) //这里每个while if都写i<n主要为了处理""情况 也可以在开始if(str==null || str.length()==0)类似code ganker写法
    i++;
int sign = 1;
if(i<n && str.charAt(i)=='+')
    i++;
else if(i<n && str.charAt(i)=='-') {
    i++;
    sign = -1;
}
int num = 0;
while(i<n && Character.isDigit(str.charAt(i))) {
    int digit = Character.getNumericValue(str.charAt(i));   //注意这里不能用(int)str.charAt(i);
    if(num>maxDiv10 || (num==maxDiv10 && digit>=8))
        return sign==1? Integer.MAX_VALUE:Integer.MIN_VALUE;
    num = num*10 + digit;
    i++;
}
return sign*num;

时间O(n) 空间O(1)





Simplify Path
while(i<path.length()) {
    while(i<path.length() && path.charAt(i)!='/')
        tmp.append(path.charAt(i));
        i++;
    if(index!=i) {
        if(tmpStr.equals("..")) {
            if(!stack.empty())
                stack.pop();
        }
        else if(!tmpStr.equals("."))
            stack.push(tmpStr);
    }
}
if(stack.empty())
    return "/";
String res = "";
while(!stack.empty())
    res = "/"+stack.pop()+res;

时间O(n) 空间O(n)





Scramble String
三维dp
boolean[][][] res = new boolean[s1.length()][s2.length()][s1.length()+1];
for(int i=0; i<s1.length(); i++) {
    for(int j=0; j<s2.length(); j++) {
        res[i][j][1] = (s1.charAt(i)==s2.charAt(j));
    }
}

for(int len=2; len<s1.length()+1; len++) {
    for(int i=0; i<s1.length()-len+1; i++) {
        for(int j=0; j<s2.length()-len+1; j++) {
            for(int k=1; k<len; k++) {
                res[i][j][len] |= res[i][j][k] && res[i+k][j+k][len-k]) || res[i][j+len-k][k] && res[i+k][j][len-k];
            }
        }
    }
}
return res[0][0][s1.length()];

时间O(n^4) 空间O(n^3)





Roman to Integer
关键就是如果前一个数大于后一个数 就加后一个数 否则就加后一个数并减去2倍前一个数
Map<Character, Integer> map = new HashMap<Character, Integer>();
map.put('I', 1);
map.put('V', 5);
map.put('X', 10);
map.put('L', 50);
map.put('C', 100);
map.put('D', 500);
map.put('M', 1000);

int curr = 0, prev = 0, total = 0;
for(Character c : s.toCharArray()) {
    curr = map.get(c);
    total+=curr>prev ? curr-2*prev:curr;
    prev = curr;
}

时间O(n) 空间O(1)





Integer to Roman
int digit = 1000;
List<Integer> tmp = new ArrayList<Integer>();
while(digit>0) {
    tmp.add(num/digit);
    num%=digit;
    digit/=10;
}
StringBuilder res = new StringBuilder();
res.append(convert(tmp.get(0), ' ', ' ', 'M'));
res.append(convert(tmp.get(1), 'M', 'D', 'C'));
res.append(convert(tmp.get(2), 'C', 'L', 'X'));
res.append(convert(tmp.get(3), 'X', 'V', 'I'));

private String convert(int digit, char ten, char five, char one) {
    StringBuilder sb = new StringBuilder();
    switch(digit) {
        case 9:
            sb.append(one);
            sb.append(ten);
            break;
        case 8: case 7: case 6: case 5:
            sb.append(five);
            for(int i=5; i<digit; i++)
                sb.append(one);
            break;
        case 4:
            sb.append(one);
            sb.append(five);
            break;
        case 3: case 2: case 1:
            for(int i=0; i<digit; i++)
                sb.append(one);
            break;
    }
    return sb.toString();
}

O(n) O(1)





Reverse Words in a String
从后往前扫一遍 两个指针定位到每一个字符串起始和结束的位置 依次存入StringBuilder
int j = s.length();
StringBuilder res = new StringBuilder();
for(int i=s.length-1; i>=0; i--) {
    if(s.charAt(i)==' ')
        j = i;
    else if(i==0 || s.charAt(i-1)==' ') {
        if(res.length!=0)
            res.append(' ');
        res.append(s.substring(i, j));
    }
}

时间O(n) 空间O(n)





Reverse Words in a String ii
同rotate array 全局翻转后再局部翻转
int left = 0, right = s.length-1;
while(left<right) {
    char tmp = s[right];
    s[right] = s[left];
    s[left] = tmp;
    left++;
    right--;
}
left = 0; right = 0;
while(right<s.length) {
    while(right<s.length && s[right]!=' ')
        right++;
    int next = right+1;
    right--;
    while(left<right) {
        ...
    }
    left = next; right = next;
}

时间O(n) 空间O(1)





Restore IP Address
类似NP的套路 循环中递归

helper(s, 0, 1, "", res);

private void helper(String s, int index, int segment, String item, List<String> res) {
    if(segment==4) {
        String str = s.substring(index);
        if(isValid(str)) {
            res.add(item+"."+str);
        }
        return;
    }
    for(int i=1; i<4&&(index+i<=s.length()); i++) {
        String tmp = s.substring(index, index+i);
        if(isValid(tmp)) {
            if(segment==1)
                helper(s, index+i, segment+1, tmp, res);
            else
                helper(s, index+i, segment+1, item+"."+tmp, res);
        }
    }
}

boolean isValid(String s) {
    if(s.length()>3)
        return false;
    if(s.charAt(0)=='0' && s.length()>1)
        return false;
    if(Integer.parseInt(s)>=0 && Integer.parseInt(s)<=255)
        return true;
    return false;
}

时间类似于O(2^n) 空间O(1)





Read N Characters Given Read4
函数只被call一次
char[] buffer = new char[4];
int readBytes = 0;
boolean eof = false;
while(!eof && readBytes<n) {
    int size = read4(buffer);
    if(size<4)
        eof = true;
    int bytes = Math.min(n-readBytes, size);
    System.arraycopy(buffer, 0, buf, readBytes, bytes);
    readBytes+=bytes;
}

时间O(n) 空间O(1)





Read N Characters Given Read4 ii
函数可以被call多次 要保存之前状态
char[] buffer = new char[4];
int offset = 0, remainSize = 0;

int read(char[] buf, int n) {
    int readBytes = 0;
    boolean eof = false;
    while(!eof && readBytes<n) {
        if(remainSize==0) {
            remainSize = read4();
            eof = remainSize<4;
        }
        int bytes = Math.min(n-readBytes, remainSize);
        System.arraycopy(buffer, offset, buf, readBytes, bytes);
        offset = (offset+bytes)%4;
        remainSize-=bytes;
        readBytes+=bytes;
    }
}

时间O(n) 空间O(1)





One Edit Distance
modify insert append
int m = s.length(), n = t.length();
if(m>n)
    return isOneEditDistance(t, s);
if(n-m>1)
    return false;
int i = 0;
while(i<m && s.charAt(i)==t.charAt(i))
    i++;
if(i==m)
    return n-m==1;  //append
if(n==m)    //modify
    i++;
while(i<m && s.charAt(i)==t.charAt(i+n-m))  //modify insert
    i++;
return i==m;

时间O(n) 空间O(1)





Edit Distance





Multiply Strings
模拟乘法 每一位数的求解 对应位相乘最后相加

for(int i=num1.length()+num2.length(); i>0; i--) {
    for(int j=Math.min(i-1, num1.length()); j>0; j--) {
        if(i<=j+num2.length()) {
            num+=(int)(num1.charAt(j-1)-'0')*(int)(num2.charAt(i-1-j)-'0');
        }
    }
    if(i!=1 || num!=0)
        res.append(num%10);
    num/=10;
}
return res.reverse().toString();

O((m+n)^2) O(1)





Minimum Window Substring
双指针
int leftP = 0, count = 0;
for(int i=0; i<T.length(); i++) {
    if(map.containsKey(T.charAt(i)))
        map.put(T.charAt(i), 1);
    else
        map.put(T.charAt(i), map.get(T.charAt(i))+1);
}
for(int i=0; i<S.length(); i++) {
    if(map.containsKey(S.charAt(i)))
        map.put(S.charAt(i), map.get(S.charAt(i))-1);
        if(map.get(S.charAt(i))>=0)
            count++;
    while(count==T.length()) {
        if(map.containsKey(S.charAt(leftP))) {
            map.put(S.charAt(leftP), map.get(S.charAt(leftP))+1);
            if(map.get(S.charAt(leftP))>0) {
                if(i-leftP+1<maxLen) {
                    maxLen = i-leftP+1;
                    res = S.substring(leftP, i-leftP+1);
                }
                count--;
            }
        }
        leftP++;
    }
}

O(n) O(T)





Longest Substring Without Repeating Character
双指针
boolean[] charMap = new boolean[256];
int j = 0, maxLen = 0;
for(int i=0; i<s.length(); i++) {
    while(charMap[s.charAt(i)]) {
        charMap[s.charAt(j)] = false;
        j++;
    }
    charMap[s.charAt(i)] = true;
    maxLen = Math.max(maxLen, i-j+1);
}

O(n) O(1)





Longest Substring With At Most Two Distinct Characters
双指针
int[] count = new int[256];
int maxLen = 0, j = 0, numDistinct = 0;
for(int i=0; i<s.length(); i++) {
    if(count[s.charAt(i)]==0)
        numDistinct++;
    count[s.charAt(i)]++;
    while(numDistinct>2) {
        count[s.charAt(j)]--;
        if(count[s.charAt(j)]==0)
            numDistinct--;
        j++;
    }
    maxLen = Math.max(maxLen, i-j+1);
}





Longest Common Prefix
暴力法 一个个比较
StringBuilder res = new StringBuilder();
if(strs==null ||　strs.length==0)
    return res;
int index = 0;
while(index<strs[0].length()) {
    for(int i=0; i<strs.length; i++) {
        if(index>=strs[0].length() ||　strs[i].charAt(i)!=strs[0].charAt(i))
            return res.toString();
    }
    res.append(strs[0].charAt(index));
    index++;
}

O(m*n) O(m)





Letter Combination of a Phone Number
类似于组合的题目 要熟悉递归非递归解法
1 递归
helper(digits, 0, "", res);

private void helper(String digits, int index, String item, List<String> res) {
    if(index==digits.length()) {
        res.add(item);
        return;
    }
    String curr = map.get(digits.charAt(index));
    for(int i=0; i<curr.length(); i++) {
        helper(digits, index+1, item+Character.toString(curr.charAt(i)), res);
    }
}

O(k^n) O(k^n)

2 非递归
res.add("");
for(int i=0; i<digits.length(); i++) {
    String curr = map.get(digits.charAt(i));
    List<String> item = new ArrayList<String>();
    for(int j=0; j<res.size(); j++) {
        for(int k=0; k<curr.length(); k++) {
            item.add(res.get(j)+Character.toString(curr.charAt(k)));
        }
    }
    res = item;
}

O(k^n) O(k^n)





Length of Last Word
for(int i=s.length（）-1； i>=0; i--) {
    if(s.charAt(i)==' ')
        j = i;
    else if(i==0 || s.charAt(i-1)==' ') {
        count = j-i;
        break;
    }
}

O(n) O(1)





Interleaving String


Generate Parentheses
helper(n, n, new String(), res);
return res;

private void helper(int left, int right, String item, List<String> res) {
    if(right<left)
        return;
    if(left==0 && right==0)
        res.add(item);
    if(left>0)
        helper(left-1, right, item+"(", res);
    if(right>0)
        helper(left, right-1, item+")", res);
}

O(结果)





Distinct Subsequences
二维dp 空间降到一维 好理解
if(s.length()==0)
    return 0;
if(t.length()==0)
    return 1;
int[] res = new int[t.length()+1];
res[0] = 1;
for(int i=0; i<s.length(); i++) {
    for(int j=t.length()-1; j>=0; j++)
        res[j+1] = (s.charAt(i)==t.charAt(j)?res[j]:0) + res[j+1];
}
return res[t.length()];

O(m*n) O(m)





Decode Ways
一维dp
int num1 = 1, num2 = 1, num3 = 1;
for(int i=1; i<s.length(); i++) {
    if(s.charAt(i)=='0') {
        if(s.charAt(i-1)=='1' or s.charAt(i-1)=='2')
            num3 = num1;
        else
            return 0;
    }
    else {
        if(s.charAt(i-1)=='0' || s.charAt(i-1)>='3')
            num3 = num2;
        else {
            if(s.charAt(i-1)=='2' && s.charAt(i)>='7')
                num3 = num2;
            else
                num3 = num1 + num2;
        }
    }
    num1 = num2;
    num2 = num3;
}

O(n) O(1)





Count and Say
具体实现题
String res = "1";
for(int i=2; i<=n; i++) {
    StringBuilder tmp = new StringBuilder();
    int count = 1;
    for(int j=1; j<res.length(); j++) {
        if(res.charAt(j)==res.charAt(j-1))
    }
}





Compare Version Number
将version字符转换成数字比较大小
for(int i1=0, i2=0; i1<version1.length() || i2<version2.length(); i1++, i2++) {
    int num1 = 0;
    while(i1<version1.length() && version1.charAt(i1)!='.')
        num1 = 10*num1 + (int)(version1.charAt(i1++) - '0');
    int num2 = 0;
    while(i2<version2.length() && version2.charAt(i2)!='.')
        num2 = 10*num2 + (int)(version2.charAt(i2++) - '0');
    if(num1>num2) return 1;
    else if(num1<num2) return -1;
}
return 0;

O(n) O(1)





Anagrams

for(int i=0; i<strs.length; i++) {
    char[] arr = strs[i].toCharArray();
    Arrays.sort(arr);
    String str = new String(arr);
    if(map.containsKey(str)) {
        map.get(str).add(strs[i]);
    }
    else {
        List<String> item = new ArrayList<String>();
        item.add(strs[i]);
        map.put(str, item);
    }
}
Iterator i = map.values().iterator();
while(i.hasNext()) {
    List<String> anagram = (List<String>)i.next();
    if(anagram.size()>1)
        res.addAll(anagram);
}

O(n*klogk) O(nk)





Add Binary
数值操作
int indexA = a.length()-1, indexB = b.length()-1;
int carry = 0;
while(indexA>=0 && indexB>=0) {
    int num = (int)(a.charAt(indexA)-'0' + b.charAt(indexB) - '0') + carry;
    carry = num/2;
    num%=2;
    res.append(num);
    indexA--; indexB--;
}
while(indexA>=0)

while(indexB>=0)

if(carry==1)

return res.reverse().toString();

O(n) O(1)






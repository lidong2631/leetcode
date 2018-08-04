The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.




Python:
class Solution:
    def countAndSay(self, n):
        """
        :type n: int
        :rtype: str
        """
        s = '1'
        for _ in range(n-1):
            let, temp, count = s[0], '', 0
            for l in s:
                if let == l:
                    count += 1
                else:
                    temp += str(count)+let
                    let = l
                    count = 1
            temp += str(count)+let
            s = temp
        return s




Java:
class Solution {
    public String countAndSay(int n) {
        String str = "1";
        for (int i = 0; i < n - 1; i++) {
            char let = str.charAt(0);
            StringBuffer tmp = new StringBuffer();
            int count = 0;
            for (char c : str.toCharArray()) {
                if (let == c) 
                    count += 1;
                else {
                    tmp.append(count);
                    tmp.append(let);
                    let = c;
                    count = 1;
                }
            }
            tmp.append(count);
            tmp.append(let);
            str = tmp.toString();
        }
        return str;
    }
}




Golang: (have problem)
func countAndSay(n int) string {
    s := "1"
    for i := 0; i < n - 1; i++ {
        let, count := string(s[0]), 0
        var tmp []string
        for _, c := range s {
            if let == string(c) {
                count++
            } else {
                tmp = append(tmp, string(count) + let)
                let = string(c)
                count = 1
            }
        }
        tmp = append(tmp, string(count) + let)
        s = strings.Join(tmp, "")
    }
    return s
}









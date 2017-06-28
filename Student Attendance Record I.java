public class Solution {
    public boolean checkRecord(String s) {
        int countA = 0, continuousL = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                countA++;
                continuousL = 0;
            }
            else if (s.charAt(i) == 'L') {
                continuousL++;
            }
            else {
                continuousL = 0;
            }
            if (countA > 1 || continuousL > 2) {
                return false;
            }
        }
        return true;
    }
}

O(n)


using regex:
public boolean checkRecord(String s) {
    return !s.matches(".*LLL.*|.*A.*A.*");
}





You are given a string representing an attendance record for a student. The record only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True
Example 2:
Input: "PPALLL"
Output: False
Hide Company Tags Google
Hide Tags String
Hide Similar Problems (H) Student Attendance Record II

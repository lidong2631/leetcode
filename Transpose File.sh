ncol=`head -n 1 file.txt | wc -w`

for i in `seq 1 $ncol`
do
    echo `cut -d ' ' -f $i file.txt`
done

https://leetcode.com/discuss/31308/simple-bash-solution-that-oj-hates
easy to understand but memory limit exceed




use awk

awk '
{
    for (i = 1; i <= NF; i++) {
        if(NR == 1) {
            s[i] = $i;
        } else {
            s[i] = s[i] " " $i;
        }
    }
}
END {
    for (i = 1; s[i] != ""; i++) {
        print s[i];
    }
}' file.txt

https://leetcode.com/discuss/29462/ac-solution-using-awk-and-statement-just-like-c





use bash array

column=`head -n1 file.txt|awk '{print NF}'`
for((i=1;i<=$column;i++))
do

while read line;do
temp=`echo $line|awk '{print $v1}' v1="$i"`
str1=${str1}" $temp"
done < file.txt

echo $str1
str1=""
done
https://leetcode.com/discuss/35936/my-ac-code-just-use-bash-array
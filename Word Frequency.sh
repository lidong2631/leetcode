cat words.txt | tr -s [:space:] '\n' | sort | uniq -c | sort -r | awk '{ print $2 $1 }'


https://leetcode.com/discuss/29049/my-simple-solution-one-line-with-pipe


tr
http://www.thegeekstuff.com/2012/12/linux-tr-command/
uniq
http://www.computerhope.com/unix/uuniq.htm
awk
http://www.thegeekstuff.com/2010/01/awk-introduction-tutorial-7-awk-print-examples/
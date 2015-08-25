(xxx) xxx-xxxx or xxx-xxx-xxxx

grep -E '^(\d{3}-|\(\d{3}-\))\d{3}-\d{4}$' file.txt

sed -n -r '/^([0-9]{3}-|\([0-9]{3}\) )[0-9]{3}-[0-9]{4}$/p' file.txt

awk '/^([0-9]{3}-|\([0-9]{3}\) )[0-9]{3}-[0-9]{4}$/' file.txt

https://leetcode.com/discuss/29476/three-different-solutions-using-grep-sed-and-awk
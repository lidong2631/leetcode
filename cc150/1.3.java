public void removeDup(char[] str) {
	if(str==null)
		return;
	if(str.length<2)
		return;

	int tail = 1;
	for(int i=1; i<str.length; i++)
	{
		int j;
		for(int j=0; j<tail; j++)
		{
			if(str[j]==str[i])
				break;
		}
		if(j==tail)
		{
			str[tail] = str[i];
			tail++;
		}
	}
	str[tail] = '#';
}

O(n^2), O(1)

test case:

1 abcd

2 null string

3 empty string

4 aabb

5 abab

6 aaaaa


public void removeDup(char[] str) {
	if(str==null)
		return;
	if(str.length<2)
		return;

	boolean[] hash = new boolean[256];

	for(int i=0; i<256; i++)
		hash[i] = false;

	int tail = 1;
	hash[str[0]] = true;

	for(int i=1; i<str.length; i++)
	{
		if(!hash[i])
		{
			hash[str[i] = true;
			str[tail] = str[i];
			tail++;
		}
	}
	str[tail] = '#';
}

O(n), O(n)
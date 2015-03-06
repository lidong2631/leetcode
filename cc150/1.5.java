public void replaceSpace(char[] str, int length) {
	
	int spaceCount = 0;

	for(int i=0; i<length; i++)
		if(str[i]==' ')
			spaceCount++;

	int newLength = length + 2*spaceCount;
	char[] newStr = new char[newLength];

	for(int i=length-1; i>=0; i--)
	{
		if(str[i]==' ')
		{
			newStr[newLength-1] = '0';
			newStr[newLength-2] = '2';
			newStr[newLength-3] = '%';
			newLength-=3;
		}
		else
		{
			newStr[newLength-1] = str[i];
			newLength--;
		}
	}
}
public String numToStr(int num) {
	StringBuilder sb = new StringBuilder();

	int len = 1;
	while((Math.pow*(double)10, (double)len)<num)
		len++;

	String[] word1 = {"", "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine "};

	String[] word11 = {"", "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "};

	String[] word10 = {"", "Ten ", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};

	String[] word100 = {"", "Hundred", "Thousand"};

	int tmp;
	if(num==0)
		sb.append("Zero");
	else
	{
		if(len>3 && len%2==0)	//如果num是4位数或6位数
			len++;
		do
		{
			if(len>3)		//num is larger than 999
			{
				tmp = (num/(int)Math.pow((double)10, (double)len-2));
				if(tmp/10==1 && tmp%10!=0)		//处理num是5位数且是最高两位是11到19
					sb.append(word11[tmp%10]);
				else							//处理num是4位数或6位数
				{
					sb.append(word10[tmp/10]);
					sb.append(word1[tmp%10]);
				}
				if(tmp>0)
					sb.append(word100[len/2]);
				num = num%(int)(Math.pow((double)10, (double)len-2));
				len-=2;
			}
			else
			{
				tmp = num/100;		//num is less than 1000
				if(tmp!=0)		//百位有数
				{
					sb.append(word1[tmp]);
					sb.append(word100[len/2]);
				}
				tmp = num%100;
				if(tmp/10==1 && tmp%10!=0)		//tmp为11到19之间的数
					sb.append(word11[tmp%10]);
				else
				{
					sb.append(word10[tmp/10]);	//tmp为大于19的数
					sb.append(word1[tmp%10]);
				}
				len = 0;
			}
		}	
		while(len>0);
	}
	return sb.toString();
}

Note：这题过程较繁琐 要注意怎么分步一个个处理




















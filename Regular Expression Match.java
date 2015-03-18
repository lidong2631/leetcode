public boolean isMatch(String s, String p) {
	return helper(s, p, 0, 0);
}

private boolean helper(String s, String p, int i, int j) {
	if(j==p.length())		//如果p已被遍历到底 就看s是否也被遍历到底 是则匹配 否则不匹配
		return i==s.length();

	//p的下一个字符不是'*'
	if(j==p.length()-1 ||　p.charAt(j+1)!='.') {	//如果p下一个字符不是'.'或者j不是p最后一个字符 判断s的i个字符和p的j个字符是否相等
		if(i==s.length() ||　(s.charAt(i)!=p.charAt(j) && p.charAt(j)!='.'))	//若两字符不等货s已被遍历完 返回false
			return false;
		else
			return helper(s, p, i+1, j+1);	//若两字符相等 递归继续判断
	}

	//p的下一个字符是'*'
	while(i<s.length() && (p.charAt(j)=='.' || s.charAt(i)==p.charAt(j))) { //当前字符相等或p当前字符是'.'
		if(helper(s, p, i, j+2))	//若s[i] s[i+1]...s[i+k]等于p当前字符 则都需要递归判断一遍
			return true;
		i++;
	}

	return helper(s, p, i, j+2);	//若while中都不匹配 则继续判断后面的字符	类似于aaaaab	  a*b这样
}









public boolean isMatch(String s, String p) {
    if(s.length()==0 && p.length()==0)
        return true;
    if(p.length()==0)
        return false;
    boolean[][] res = new boolean[s.length()+1][p.length()+1];
    res[0][0] = true;
    for(int j=0;j<p.length();j++)
    {
        if(p.charAt(j)=='*')
        {
            if(j>0 && res[0][j-1]) res[0][j+1]=true;
            if(j<1) continue;
            if(p.charAt(j-1)!='.')
            {
                for(int i=0;i<s.length();i++)
                {
                    if(res[i+1][j] || j>0&&res[i+1][j-1] 
                    || i>0 && j>0 && res[i][j+1]&&s.charAt(i)==s.charAt(i-1)&&s.charAt(i-1)==p.charAt(j-1))
                        res[i+1][j+1] = true;
                }
            }
            else
            {
                int i=0;
                while(j>0 && i<s.length() && !res[i+1][j-1] && !res[i+1][j])
                    i++;
                for(;i<s.length();i++)
                {
                    res[i+1][j+1] = true;
                }
            }
        }
        else
        {
            for(int i=0;i<s.length();i++)
            {
                if(s.charAt(i)==p.charAt(j) || p.charAt(j)=='.')
                    res[i+1][j+1] = res[i][j];
            }
        }
    }
    return res[s.length()][p.length()];
}
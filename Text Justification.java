public ArrayList<String> fullJustify(String[] words, int L) {
    ArrayList<String> res = new ArrayList<String>();
    if(words==null || words.length==0)
        return res;
    

    int count = 0;      //count记录当前行已经存储了几个字符
    int last = 0;       //last记录上一轮走到了数组里第几个单词 即上一轮最后一个没加到上一行结果的单词 也是当前行的起始单词
    

    for(int i=0;i<words.length;i++)     //大循环框架
    {
        
        /*多余L的情况*/
        if(count+words[i].length()+(i-last)>L)  //如果当前行加上这个单词超过了L的长度 就要将现有的单词按规则加到结果集中
        {
            /*计算spaceNum extraNum*/
            int spaceNum = 0;   //记录平均每个单词间应加的空格数
            int extraNum = 0;   //记录多出无法平均分配的空格数
            if(i-last-1>0)  //如果i-last-1>0 说明有多于一个单词在这一行 可以按公式计算空格数 否则这一行只有一个单词 不需要计算空格数 
            {                   //所有空格都在这个词后面
                spaceNum = (L-count)/(i-last-1);
                extraNum = (L-count)%(i-last-1);
            }
            
            /*按规则加空格处理当前行*/
            StringBuilder str = new StringBuilder();    //新建一个StringBuilder保存这一行结果
            for(int j=last;j<i;j++)     //从last开始 到i结束 这里i是这一行没加进去的单词的index
            {
                str.append(words[j]);   //先将单词加进
                if(j<i-1)           //这里i-1是这一行的最后一个单词 这句是判断只要没到这一行最后一个单词就可以将算好的spaceNum extraNum加进去
                {                   //否则如果只有一个单词就要对应35行
                    for(int k=0;k<spaceNum;k++) //加平均分配的空格
                    {
                        str.append(" ");
                    }
                    if(extraNum>0)  //多余无法平均分配的从最左开始依次分下去 If the number of spaces on a line do not divide evenly between words, 
                    {               //the empty slots on the left will be assigned more spaces than the slots on the right
                        str.append(" ");
                    }
                    extraNum--; //减去1
                }
            }
            
            /*单独处理一行只有一个单词的情况*/
            for(int j=str.length();j<L;j++) //处理这一行只有一个单词的情况 直接在后面加空格
            {
                str.append(" ");
            }       
            
            /*加入结果集*/
            res.add(str.toString());    //将结果加到最终结果集中 重设count last=i
            count=0;
            last=i;
        }
        
        /*当前行单词字符数还不到L的情况*/
        count += words[i].length();     //如果当前行加上这个单词不到L继续加下一个单词
    }
    
    /*处理最后一行*/
    StringBuilder str = new StringBuilder();
    for(int i=last;i<words.length;i++)  //单独处理最后一行的情况
    {
        str.append(words[i]);       //最后一行要求left justified no extra space between words 所以要将单词加入结果 每个单词间只有一个空格
        if(str.length()<L)
            str.append(" ");
    }
    for(int i=str.length();i<L;i++) //补齐最后一行余下的空格
    {
        str.append(" ");
    }
    

    res.add(str.toString());
    return res;
}





细节很多的一题 注意46行不可以写成for(int j = 0; j < L - str.length(); j++) 见code ganker评论

这两种写法是有区别的哈～ 因为在循环过程中str.length()是在变得，所以如果你放在for的中间 j < L - str.length()，那么判断时条件是在变化的，

比如原来j是0，L是6，str长度是2，现在append一个之后，长度变化（变成3）了，那么条件会变成j<6-3=3.而本意应该是要继续4的，

这样自然就会少append几个空格了～ 而我的代码那种写法就只赋值一次，后面不会受改变影响



时间上我们需要扫描单词一遍，然后在找到行尾的时候在扫描一遍当前行的单词，不过总体每个单词不会被访问超过两遍，所以总体时间复杂度是O(n)。

而空间复杂度则是结果的大小（跟单词数量和长度有关，不能准确定义，如果知道最后行数r，则是O(r*L)）





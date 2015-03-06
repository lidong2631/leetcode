这题看hawstein blog讲的很清楚

public int Rand7() {
	int x = Integer.MAX_INT;

	while(x > 21)
		x = 5*(Rand5()-1) + Rand5();

	return x%7+1;
}


让我们把这个问题泛化一下，从特殊到一般。现在我给你两个生成随机数的函数Randa， Randb。Randa和Randb分别产生1到a的随机数和1到b的随机数，a，b不相等 

(相等就没必要做转换了)。现在让你用Randa实现Randb。

通过上文分析，我们可以得到步骤如下：

1.如果a > b，进入步骤2；否则构造Randa2 = a * (Randa - 1) + Randa，表示生成1到a2 随机数的函数。如果a2 仍小于b，

	继教构造 Randa3 = a * (Randa2 - 1) + Randa…直到ak > b，这时我们得到Randak , 我们记为RandA。

2.步骤1中我们得到了RandA(可能是Randa或Randak )，其中A > b，我们用下述代码构造Randb：

// A > b
int Randb(){
    int x = ~(1<<31); // max int
    while(x > b*(A/b)) // b*(A/b)表示最接近A且小于A的b的倍数
        x = RandA();
    return x%b + 1;
}
从上面一系列的分析可以发现，如果给你两个生成随机数的函数Randa和Randb，你可以通过以下方式轻松构造Randab，生成1到a*b的随机数。

Randab = b * (Randa - 1) + Randb
Randab = a * (Randb - 1) + Randa

如果再一般化一下，我们还可以把问题变成：给你一个随机生成a到b的函数，用它去实现一个随机生成c到d的函数。有兴趣的同学可以思考一下，这里不再讨论。
